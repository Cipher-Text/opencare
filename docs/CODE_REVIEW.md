# Code Review & Architecture Observations

> **Reviewed:** April 2026
> **Scope:** `open-care-backend` (Spring Boot) · `open-care-frontend` (Next.js 15)
> **Purpose:** Technical debt tracking, refactoring roadmap, and future architecture guidance

---

## Table of Contents

1. [Backend — Critical Issues](#backend--critical-issues)
2. [Backend — High Priority](#backend--high-priority)
3. [Backend — Medium Priority](#backend--medium-priority)
4. [Frontend — Critical Issues](#frontend--critical-issues)
5. [Frontend — High Priority](#frontend--high-priority)
6. [Frontend — Medium Priority](#frontend--medium-priority)
7. [Cross-Cutting Concerns](#cross-cutting-concerns)
8. [Future Architecture Roadmap](#future-architecture-roadmap)
9. [Quick Wins](#quick-wins)
10. [Health Summary](#health-summary)

---

## Backend — Critical Issues

## Backend — High Priority

### 6. Stringly-Typed Paginated Responses
**Files:** `controller/DoctorApiController.java:124–128`, `controller/HospitalApiController.java:120–124`

`HashMap<String, Object>` used for all paginated responses. No compile-time safety, no IDE autocomplete, breaks silently on key rename.

```java
// Fix: generic PageResponse record
public record PageResponse<T>(
    List<T> content,
    int currentPage,
    long totalItems,
    int totalPages
) {}
```

---

### 7. Service Layer Bloat — DoctorServiceImpl
**File:** `service/DoctorServiceImpl.java` — 771 lines, 20+ methods

Mixes read queries, write commands, specification building, and validation. Hard to test, violates Single Responsibility Principle.

**Split into:**
- `DoctorQueryService` — all read operations
- `DoctorCommandService` — create / update / delete
- `SpecificationFactory` — reusable filter/specification builder (currently duplicated across Doctor, Hospital, BloodDonation services)

---

### 8. ShedLock Disabled on All Schedulers
**File:** `scheduler/ReindexScheduler.java:24`

`@SchedulerLock` is **commented out**. In any multi-instance deployment (Docker Compose replicas, Kubernetes), every instance runs the nightly reindex simultaneously — causes duplicate writes to Elasticsearch and wasted compute.

```java
// Fix: uncomment and configure
@SchedulerLock(name = "reindexHospitals", lockAtMostFor = "PT2H", lockAtLeastFor = "PT5M")
@Scheduled(cron = "0 0 2 * * *", zone = "Asia/Dhaka")
public void reindexHospitalsNightly() { ... }
```

Also missing: `try/catch` around `hospitalSearchService.indexAllHospitals()`. If Elasticsearch is unreachable, the failure is swallowed silently.

---

### 9. Detached Entity Creation in Mappers
**File:** `mapper/DoctorMapper.java:108–144`

`Tag`, `District`, `Upazila`, `Union` entities are created with only their ID set and returned from mapper methods without a persistence context. Hibernate may attempt phantom inserts on flush.

```java
// Current: creates detached entity
Tag tag = new Tag();
tag.setId(id);
return tag;

// Fix: use JPA proxy reference — no query, no insert
return tagRepository.getReferenceById(id);
```

---

### 10. Caffeine Cache Has No TTL or Size Bounds
**File:** `src/main/resources/application.yml:46–77`

15+ named caches are declared but no `spring.cache.caffeine.spec` is set. Caches grow indefinitely and never expire.

```yaml
spring:
  cache:
    caffeine:
      spec: "maximumSize=10000,expireAfterWrite=15m"
```

Individual caches needing different TTLs (e.g., `githubContributors` = 1h, `realtime dashboard` = 30s) should be configured per cache name using `CaffeineCacheManager` bean.

---

### 11. Unbounded `findAll()` Calls
Found in 8+ service methods across `DoctorServiceImpl`, `HospitalServiceImpl`, etc.:

```java
return doctorRepository.findAll(); // loads entire table into memory
```

With 10k+ records this will OOM. Either remove or enforce pagination everywhere.

---

### 12. No Rate Limiting on Auth Endpoints
**File:** `config/SecurityConfig.java:51–54`

`/api/auth/**` is `permitAll()` with no request throttling. This opens:
- Login endpoint to brute-force attacks
- Registration endpoint to account enumeration + spam
- Password reset to user enumeration

Add a `RateLimitFilter` specific to auth paths (5 req/min per IP).

---

## Backend — Medium Priority

### 13. Inconsistent Fetch Strategies on Doctor Entity
**File:** `entity/Doctor.java:50`

`Profile` is `FetchType.EAGER`, associations and tags are `FetchType.LAZY`. Every doctor query eagerly loads the profile regardless of whether it's needed.

**Rule:** Use `LAZY` everywhere. Use `JOIN FETCH` in queries when the relation is needed.

---

### 14. Profile Entity Column Explosion
**File:** `entity/Profile.java`

16+ nullable social media URL columns (`facebook_profile_url`, `linkedin_profile_url`, `x_profile_url`, `research_gate_profile_url`, `facebook_page_url`, `instagram_profile_url`, `youtube_channel_url`, etc.) all on one table.

```java
// Fix: extract as @Embeddable
@Embeddable
public class SocialLinks {
    private String facebook;
    private String linkedin;
    private String twitter;
    private String instagram;
    private String youtube;
    private String researchGate;
}

// In Profile:
@Embedded
private SocialLinks socialLinks;
```

---

### 15. Enum Conversion Without Error Handling in Mappers
**File:** `mapper/HospitalMapper.java:60–78`

```java
return HospitalType.valueOf(hospitalType); // throws IllegalArgumentException on bad input
```

Invalid enum strings return a 500 instead of a 400. Wrap in a try/catch that throws `BadRequestException`.

---

### 16. Coordinate Validation Missing
**File:** `entity/Hospital.java:64–68`

`lat` and `lon` stored as `BigDecimal` with no validation. `(0.0, 0.0)` (Gulf of Guinea) is a valid insertion. Add `@DecimalMin`/`@DecimalMax` constraints or a custom validator.

---

### 17. No `DataIntegrityViolationException` Handler
**File:** `exception/GlobalExceptionHandler.java`

Unique constraint violations (duplicate email, duplicate BMDC number) currently fall through to the generic 500 handler. Should be mapped to `409 Conflict`.

---

### 18. Async Executor Not Explicitly Configured
No `AsyncConfig.java` with explicit `corePoolSize`, `maxPoolSize`, and `queueCapacity` found. Spring's default async executor uses an unbounded queue, which can silently accept work faster than it's processed until memory is exhausted.

---

## Frontend — Critical Issues

### 19. Dual Token Storage — Auth Desync Bug
**Files:** `src/lib/auth-client.ts` (localStorage), `src/lib/auth.ts` (cookies)

Login stores tokens in cookies (server action). Client code reads from localStorage (never updated until page refresh). Middleware reads cookies. These three sources diverge.

**Fix:** Use httpOnly cookies exclusively. Remove all localStorage token logic. Client reads session from a `/api/auth/session` server route or server component props.

---

### 20. Duplicate QueryClient Instance
**Files:** `src/app/(admin)/admin/layout.tsx:4–14`, `src/components/common/ClientProviders.tsx:8–18`

Two `QueryClient` instances exist. Admin data is cached separately from main app data. Cross-page cache invalidation, deduplication, and React Query Devtools don't work correctly.

**Fix:** Delete the `QueryClient` in `admin/layout.tsx`. Use the single instance from `ClientProviders`.

---

### 21. Dashboard Uses Manual `useEffect` + `setInterval` Instead of React Query
**File:** `src/app/(admin)/admin/dashboard/page.tsx:27–133`

5 separate `useEffect` blocks with `useState` + `setInterval` for polling. Bypasses all React Query benefits (deduplication, background refetch, stale-while-revalidate, devtools).

```typescript
// Fix: use React Query with refetchInterval
const { data: overview } = useQuery({
  queryKey: ["dashboard", "overview"],
  queryFn: getDashboardOverview,
});

const { data: realTime } = useQuery({
  queryKey: ["dashboard", "realtime"],
  queryFn: getRealTimeStats,
  refetchInterval: 10_000,
});
```

---

### 22. No Error Boundaries
Zero `ErrorBoundary` components in the codebase. Any unhandled render-time throw produces a **white screen** for the user with no recovery path.

Add at minimum:
- Root layout error boundary
- Per-page error boundary
- Per-section error boundary for independent widgets (dashboard cards)

---

### 23. Middleware Does Not Validate Token Expiry
**File:** `src/middleware.ts:3–30`

Any access token, even an expired one, passes the protected route check. An expired token grants access until the server rejects it with a 401.

```typescript
// Fix: check exp claim in middleware
const payload = decodeJwt(token.value);
if (payload.exp * 1000 < Date.now()) {
  const response = NextResponse.redirect(new URL("/login", request.url));
  response.cookies.delete("access_token");
  return response;
}
```

---

## Frontend — High Priority

### 24. Client-Side Permission Checking Is Not Security
**File:** `src/lib/permissions.ts:165–185`

JWT is decoded on the client without signature verification. A user can craft a JWT with elevated `realm_access.roles` claims and bypass all frontend permission guards.

**Rule:** Frontend permission checks are for **UI rendering only** (show/hide buttons). All actual authorization must be enforced server-side in Spring Security. Never trust client-decoded permissions for data access.

---

### 25. Token Refresh Race Condition
**File:** `src/lib/auth.ts:122–144`

Multiple concurrent 401 responses all trigger `refreshAccessToken()` simultaneously. Multiple refresh tokens are consumed in parallel, invalidating each other and logging the user out.

```typescript
// Fix: singleton refresh promise
let refreshPromise: Promise<boolean> | null = null;

export function refreshAccessToken(): Promise<boolean> {
  return (refreshPromise ??= performRefresh().finally(() => {
    refreshPromise = null;
  }));
}
```

---

### 26. Hardcoded Role-Permission Map
**File:** `src/lib/permissions.ts:13–161`

51-entry permission array per role hardcoded in source. If backend adds or renames a permission, the frontend silently breaks with no error.

**Fix:** Fetch role-permission mappings from `/api/superadmin/roles` at runtime. Cache with React Query (`staleTime: Infinity`).

---

### 27. Inconsistent API Layer — Two Error Patterns
Some API functions throw on error (`fetchDoctors`); others return `ApiResponse` objects (`getDashboardOverview`). Consumers must handle both patterns differently, causing bugs when mixed.

**Fix:** Standardize on always throwing. React Query catches thrown errors automatically.

```typescript
// All API functions should follow this pattern:
export async function fetchDoctors(params = {}): Promise<DoctorListResponse> {
  const response = await apiGet<DoctorListResponse>(buildUrl("/doctors", params));
  if (!response.ok) throw new ApiError(response.status, response.error);
  return response.data!;
}
```

---

### 28. Config Silently Falls Back to Production URL
**File:** `src/config/config.ts:1–6`

If `NEXT_PUBLIC_API_BASE_URL` is missing, the app silently calls `https://api.opencarebd.com/api`. A developer running locally without `.env.local` unknowingly reads and mutates **production data**.

```typescript
// Fix: fail loudly
const apiUrl = process.env.NEXT_PUBLIC_API_BASE_URL;
if (!apiUrl && process.env.NODE_ENV !== "production") {
  throw new Error("NEXT_PUBLIC_API_BASE_URL is not set. Create a .env.local file.");
}
export const baseUrl = (apiUrl ?? "https://api.opencarebd.com/api").replace(/\/$/, "");
```

---

## Frontend — Medium Priority

### 29. Admin Bundle Loaded on Public Pages
Admin components (`AdminSidebar`, `AdminHeader`, large tables) are statically imported. The public-facing homepage downloads admin JavaScript unnecessarily.

```typescript
// Fix: dynamic import for all admin-only components
const AdminSidebar = dynamic(() => import("@/components/admin/admin-sidebar"), {
  ssr: false,
  loading: () => <SidebarSkeleton />,
});
```

---

### 30. Delete Actions Not Implemented
**File:** `src/app/(admin)/admin/doctors/columns.tsx:36`

```typescript
const handleDelete = () => {}; // no-op
```

All admin table delete buttons are wired to empty handlers. Same pattern exists across hospitals, blood management, and other admin pages.

---

### 31. Doctor List Page Uses Mock Data
**File:** `src/app/(main)/doctors/page.tsx`

Public-facing doctor list uses static placeholder data instead of the real API. Pagination component is rendered but `onPageChange` is `console.log` only.

---

### 32. Inline Type Redefinitions
**File:** `src/components/doctors/DoctorsList.tsx:6–22`

`Doctor` interface re-declared locally instead of importing from `src/types/doctors.ts`. Will drift from the canonical type over time.

---

### 33. No Optimistic Updates on Mutations
All create/update/delete mutations wait for server round-trip before updating the UI. For a healthcare platform with slow mobile connections this makes the UI feel unresponsive. React Query's `onMutate` + `onError` (rollback) pattern should be used for common operations.

---

## Cross-Cutting Concerns

### 34. No `.env.example` Files
Neither `open-care-backend` nor `open-care-frontend` ships an `.env.example`. New contributors have no template for required environment variables and either hit cryptic startup errors or unknowingly call production services.

**Action:** Add `.env.example` to both repos. Env var documentation now exists in `docs/ENVIRONMENT_VARIABLES.md` in this repo.

---

### 35. No Structured / Centralized Logging
Backend root log level defaults to `error`. No JSON structured logging configured. Makes log aggregation (ELK, Loki, Datadog) impossible without parsing freeform strings.

```yaml
logging:
  pattern:
    console: '{"timestamp":"%d","level":"%p","logger":"%c","message":"%m"}%n'
```

---

### 36. No Distributed Tracing
No OpenTelemetry or Spring Cloud Sleuth integration. When a request spans Frontend → Backend → Keycloak → Elasticsearch, there is no correlation ID to trace it end-to-end across logs.

**Add:** `spring-boot-starter-actuator` + Micrometer Tracing + OTLP exporter to backend. `@vercel/otel` to frontend.

---

### 37. No API Versioning
All endpoints are `/api/*` with no version prefix. Any breaking change to a response shape requires a coordinated deploy of frontend and backend simultaneously — no backward compatibility possible.

**Recommendation:** Add `/api/v1/` prefix now while the API surface is still manageable. Keep in mind that the mobile app may be on an older version.

---

## Future Architecture Roadmap

### Near-Term (this quarter)

| # | Item | Justification |
|---|------|---------------|
| F1 | Add `.env.example` to backend + frontend repos | Unblocks contributors, prevents dev-hitting-prod |
| F2 | Enable Caffeine TTL on all caches | Prevents stale data and memory creep |
| F3 | Add `PageResponse<T>` DTO | Type-safe pagination across all endpoints |
| F4 | HikariCP connection pool explicit config | Default pool (10 connections) may bottleneck under load |
| F5 | Structured JSON logging | Required for any log aggregation pipeline |

---

### Medium-Term (next quarter)

| # | Item | Justification |
|---|------|---------------|
| M1 | **Outbox pattern** for Keycloak sync | Eliminates registration saga / orphaned user problem permanently |
| M2 | **CQRS split** on Doctor/Hospital read path | Read models via Elasticsearch, write models via PostgreSQL — already architecturally close |
| M3 | **OpenTelemetry** across all services | End-to-end request tracing across Frontend → Backend → Keycloak → ES |
| M4 | **API versioning** (`/api/v1/`) | Enables backward-compatible evolution once mobile app is in production |
| M5 | **React Query + RSC hybrid** on frontend | Use Next.js 15 Server Components for initial data (no client bundle), React Query for mutations and real-time |
| M6 | **Token refresh queue** on frontend | Prevents race condition on concurrent 401 responses |

---

### Long-Term / Futuristic

| # | Item | Justification |
|---|------|---------------|
| L1 | **Event-driven search indexing** via Redis Streams or Kafka | Replace nightly `ReindexScheduler` with real-time event: `DoctorUpdated → ES index`. Zero-lag search. |
| L2 | **GraphQL or BFF layer** | The `?degrees=true&workplaces=true&associations=true` pattern is the exact over/under-fetching problem GraphQL solves. A BFF (Backend for Frontend) is a lighter alternative. |
| L3 | **Feature flags** (Unleash or LaunchDarkly) | Telemedicine, AI symptom checker, and multi-language features can be deployed dark and enabled per user cohort without code deploys. |
| L4 | **Multi-tenancy** | If expanding beyond Bangladesh, the Division → District → Upazila hierarchy must become tenant-scoped. Design the schema for this now to avoid a painful migration later. |
| L5 | **Read replica for Elasticsearch** | As doctor/hospital dataset grows, separate read traffic (search) from write traffic (indexing) using ES replica shards. |

---

## Quick Wins

Low effort, high impact — can be done in a single day:

| Task | File | Effort |
|------|------|--------|
| Remove duplicate QueryClient in admin layout | `admin/layout.tsx:4` | 2 min |
| Add Caffeine spec to application.yml | `application.yml` | 5 min |
| Add `.env.example` to both repos | Both repos | 15 min |
| Fix enum valueOf without try/catch in mappers | `HospitalMapper.java:65` | 10 min |
| Fix exception handler leaking `ex.getMessage()` | `GlobalExceptionHandler.java:178` | 10 min |
| Add token expiry check to middleware | `middleware.ts` | 20 min |
| Add error boundary to root layout | `src/app/layout.tsx` | 30 min |
| Enable ShedLock on schedulers | `ReindexScheduler.java` | 10 min |
| Fail loudly on missing API URL config | `src/config/config.ts` | 5 min |
| Add try/catch to ReindexScheduler methods | `ReindexScheduler.java` | 10 min |

---

## Health Summary

| Area | Backend | Frontend | Notes |
|------|---------|----------|-------|
| Architecture | ⚠️ Good foundation | ⚠️ Inconsistent patterns | Both need targeted refactoring |
| Authentication | ✅ Saga compensated (Keycloak rollback) | 🔴 Token desync bug | Frontend is a live bug |
| Data Fetching | ✅ N+1 fixed (JOIN FETCH) | 🔴 Mixed Query vs useEffect | Dashboard needs full rewrite |
| Type Safety | ✅ MapStruct + DTOs | ⚠️ Inline type redefs | Backend stronger than frontend |
| Security | ⚠️ Missing rate limits | 🔴 Client-side auth checks | Both need hardening |
| Performance | ⚠️ Unbounded queries | ⚠️ No code splitting | Neither is production-scale ready |
| Error Handling | ✅ Sanitized (no internal leaks) | 🔴 No error boundaries | Frontend still needs error boundaries |
| Observability | ❌ No tracing | ❌ No tracing | Neither has distributed tracing |
| Testing | ❓ TestContainers present | ❓ No tests reviewed | Needs dedicated review pass |

**Legend:** ✅ Good · ⚠️ Needs attention · 🔴 Active issue · ❌ Missing

---

*Last updated: April 2026. Re-review recommended after each major feature sprint.*
