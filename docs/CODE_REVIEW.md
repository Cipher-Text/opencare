# Code Review & Architecture Observations

> **Reviewed:** April 2026
> **Scope:** `open-care-backend` (Spring Boot) · `open-care-frontend` (Next.js 15)
> **Purpose:** Technical debt tracking, refactoring roadmap, and future architecture guidance

---

## Table of Contents

1. [Backend — High Priority (Security)](#backend--high-priority-security)
2. [Backend — Medium Priority](#backend--medium-priority)
3. [Frontend — Critical Issues](#frontend--critical-issues)
4. [Frontend — High Priority](#frontend--high-priority)
5. [Frontend — Medium Priority](#frontend--medium-priority)
6. [Cross-Cutting Concerns](#cross-cutting-concerns)
7. [Future Architecture Roadmap](#future-architecture-roadmap)
8. [Quick Wins](#quick-wins)
9. [Health Summary](#health-summary)

---

## Backend — High Priority (Security)

> These were identified in the April 2026 platform re-evaluation. They must be resolved before any expansion of patient-facing features.

### 1. IDOR on Health Vitals — Any Authenticated User Can Read Any Patient's Records
**File:** `HealthVitalApiController.java:93`

`GET /api/health-vitals/profile/{profileId}`, `/profile/{profileId}/history`, and `/profile/{profileId}/latest` accept an integer path parameter with no ownership check. Any user with a valid JWT can enumerate every other user's complete health history by brute-forcing the profile ID.

The `/self` endpoints correctly extract `keycloakUserId` from the JWT — the per-profile endpoints must do the same.

```java
// Fix: extract caller's profile from JWT and verify ownership
String keycloakUserId = ((Jwt) authentication.getPrincipal()).getSubject();
Profile callerProfile = profileService.getByKeycloakId(keycloakUserId);
if (!callerProfile.getId().equals(profileId) && !hasAdminPermission(authentication)) {
    throw new AccessDeniedException("Cannot access another user's health vitals");
}
```

For admin read access, gate behind an explicit `read-health-vitals` permission rather than any authenticated user.

---

### 2. File Download Is Completely Unauthenticated
**File:** `SecurityConfig.java:54`

```java
.requestMatchers("/api/files/**").permitAll()
```

Every file stored in MinIO — including future medical reports, prescriptions, and patient documents — is downloadable by anyone with the URL. Split the rule: public CDN assets (profile images, hospital logos) stay `permitAll`; private documents require authentication and an ownership/permission check.

```java
.requestMatchers("/api/files/public/**").permitAll()
.requestMatchers("/api/files/private/**").authenticated()
```

---

### 3. CORS Wildcard + Duplicate CORS Bean
**File:** `SecurityConfig.java:180–202`

Both `corsConfigurationSource()` and `corsFilter()` are registered with `allowedOrigins("*")`. This means:
1. Any origin can make credentialed cross-site requests in production.
2. The duplicate bean registration causes CORS headers to be added twice on some responses.

Fix: remove the `CorsFilter` bean entirely (Spring Security's CORS support via `CorsConfigurationSource` is sufficient). Externalize allowed origins to an environment variable and lock them to the frontend domain in production.

```java
config.setAllowedOrigins(List.of(allowedOriginsFromEnv));
// Remove the @Bean CorsFilter method completely
```

---

### 4. Missing `@Valid` on Health Vital Write Endpoints
**File:** `HealthVitalApiController.java:179, 196`

```java
public ResponseEntity<HealthVitalResponse> createHealthVital(
        @RequestBody HealthVitalRequest request) {  // @Valid missing
```

Without `@Valid`, Jakarta Bean Validation annotations on `HealthVitalRequest` are silently ignored. Negative blood pressure values, invalid glucose readings, and malformed measurement types are persisted without error.

**Fix:** Add `@Valid @RequestBody` to `createHealthVital` and `updateHealthVital`.

---

### 5. Health Vital Hard-Delete Removes Medical Records Permanently
**File:** `HealthVitalApiController.java:232`

All other entities in the system use `is_active = false` soft-delete to preserve audit trails. Health vitals are the most medically sensitive data in the platform and are the only entity that performs a hard `DELETE`. Regulatory expectations for medical data require retention even after user-initiated deletion.

**Fix:** Add `is_active` column to `health_vital` table (migration required). Change the delete endpoint to set `is_active = false`. Exclude inactive records from all read queries.

---

### 6. Blood Donor Contact Information Is Fully Public
**File:** `SecurityConfig.java:266`

```java
"/api/blood-donors/**"  // GET — no auth required
```

Donor phone numbers and home addresses are returned unauthenticated. While connecting patients to donors is the goal, completely open access enables data harvesting, donor harassment, and violates reasonable privacy expectations.

**Fix:** Move `/api/blood-donors/**` from the public whitelist to `authenticated()`. Consider returning full contact details only when the requesting user has an active, approved blood requisition.

---

### 7. `health_data_consent` Is Never Enforced
**File:** `Profile.java:114`

The `healthDataConsent` boolean exists on the `Profile` entity but is never checked before writing health vitals. Storing medical data for a user who has not given consent is a legal liability.

**Fix:** Service-layer guard in `HealthVitalServiceImpl.createHealthVital`:

```java
if (!profile.getHealthDataConsent()) {
    throw new BusinessRuleException("Profile has not consented to health data storage");
}
```

---

## Backend — Previously Resolved High Priority

*All original high priority backend issues (items 1–12 from the initial review) have been resolved. ✅*

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

### 19. Appointment Table Has No Entity Layer; Prescription Table Will Be Built in Main Project
**Files:** `db/changelog/v-0.1.0/tables/appointment.yaml`, `tables/prescription.yaml`

Both tables are fully migrated into PostgreSQL with foreign keys and indexes, but there is no corresponding Java entity, repository, service, or controller for either in the main backend. Liquibase creates the tables on startup but they are unreachable from the application.

**Appointment:** Resolving this is a prerequisite for the appointment booking feature that appears throughout the product roadmap and `USER_FUNCTIONALITY_MATRIX.md`.

**Prescription:** A temporary standalone service (`open-care-prescription-management`) exists to unblock early prescription workflows. That service will be **retired** once prescription features are built natively in the main backend and frontend. The `prescription` table in the main DB is the schema placeholder for that future work. Do not build new integrations against the standalone service.

---

### 20. Doctor Entity Has Redundant String Fields for Degrees and Specializations
**File:** `entity/Doctor.java:30–37`

`Doctor.degrees` and `Doctor.specializations` are plain `String` columns that duplicate data already managed relationally via `doctor_degree` and `DoctorAssociation` junction tables. This creates two competing sources of truth: search and filtering operate on the relational tables, but something may populate the string fields instead. The string fields should be removed and callers migrated to the relational model.

---

### 21. `Profile.dateOfBirth` Uses Legacy `java.util.Date`
**File:** `entity/Profile.java:42`

Every other date/time field in the codebase uses `java.time.LocalDate` or `LocalDateTime`. `java.util.Date` carries known timezone pitfalls and has been superseded since Java 8.

**Fix:** Migrate to `LocalDate`. A Liquibase migration is not required since the column type (`date`) is unchanged.

---

### 22. Keycloak Role Fallback Makes a Blocking Reactive Call on Every Request
**File:** `SecurityConfig.java:143–151`

When a JWT has no `realm_access` roles claim, the converter calls `keycloakService.getUserRealmRoleNames(userId).block()` — a blocking call on the reactive pipeline, executed on every unauthenticated or misconfigured request. This creates a latency cliff on each such request and means every authenticated request fails entirely if the Keycloak admin API is unavailable.

Keycloak should always include role claims in `realm_access`; the fallback should log a warning and return empty authorities rather than making a synchronous API call.

---

### 23. Unbounded Health Vital History Endpoint
**File:** `HealthVitalApiController.java:111–127`

`GET /api/health-vitals/profile/{profileId}/history` returns all records as an unpaginated `List`. A patient with daily vital recordings for a year (60+ fields per record) will produce a response that is hundreds of kilobytes. The endpoint needs to accept `page` and `size` parameters, following the same pattern as the already-paginated `/self` endpoint.

---

### 24. Doctor Verification Is a Boolean With No Workflow
**File:** `entity/Doctor.java:44`

`isVerified = false` is a single flag with no audit trail. There is no verification queue, no document upload path for BMDC certificates, and no record of who verified a doctor or when. The public-facing product claims "Verified Credentials" as a trust feature.

A minimal trustworthy implementation requires:
- `verification_status` enum: `PENDING | UNDER_REVIEW | VERIFIED | REJECTED`
- `verified_by` (varchar) and `verified_at` (timestamp) columns
- An admin/moderator endpoint to approve or reject with a reason
- The existing `isVerified` boolean can remain as a derived convenience column

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

### 33. `health-vitals.ts` Uses Raw `fetch` While Every Other API File Uses `apiGet`
**File:** `src/api/health-vitals.ts`

All other API integration files (`doctors.ts`, `hospitals.ts`, etc.) use the `apiGet`/`apiPost`/`apiPut` wrappers from `lib/api-client`. `health-vitals.ts` calls `fetch()` directly with a manually constructed `Authorization` header. This bypasses the centralized error handling, token refresh, and response normalization in the wrapper. It also breaks if the base URL config changes.

**Fix:** Rewrite `health-vitals.ts` to use `apiGet`/`apiPost` and expand it to cover the other 7 health vital endpoints that exist in the backend but have no frontend integration.

---

### 34. No Optimistic Updates on Mutations
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

**Recommendation:** Add `/api/v1/` prefix now while the API surface is still manageable. This is the right time — before the mobile app ships and before third-party integrations lock into the current URL shape.

---

## Future Architecture Roadmap

### Near-Term (this quarter)

| # | Item | Justification |
|---|------|---------------|
| F1 | Add `.env.example` to backend + frontend repos | Unblocks contributors, prevents dev-hitting-prod |
| ~~F2~~ | ~~Enable Caffeine TTL on all caches~~ | ✅ Done — `spring.cache.caffeine.spec` set with TTL + size bounds |
| ~~F3~~ | ~~Add `PageResponse<T>` DTO~~ | ✅ Done — `PageResponse<T>` record in use across all paginated endpoints |
| F4 | HikariCP connection pool explicit config | Default pool (10 connections) may bottleneck under load |
| F5 | Structured JSON logging | Required for any log aggregation pipeline |
| F6 | **Fix IDOR on health vitals** (Security item 1 above) | Patient health data is accessible to any authenticated user |
| F7 | **Gate private file downloads behind auth** (Security item 2 above) | Medical documents are currently unauthenticated |
| F8 | **Lock CORS to specific origins + remove duplicate bean** (Security item 3) | Wildcard CORS is inappropriate for a healthcare platform |
| F9 | **Enforce `health_data_consent` before writing health vitals** (Security item 7) | Legal liability for unconsented health data storage |

---

### Medium-Term (next quarter)

| # | Item | Justification |
|---|------|---------------|
| M1 | **Outbox pattern** for Keycloak sync | Eliminates registration saga / orphaned user problem permanently |
| M2 | **CQRS split** on Doctor/Hospital read path | Read models via Elasticsearch, write models via PostgreSQL — already architecturally close |
| M3 | **OpenTelemetry** across all services | End-to-end request tracing across Frontend → Backend → Keycloak → ES |
| M4 | **API versioning** (`/api/v1/`) | Enables backward-compatible evolution; important before mobile app ships so it doesn't need forced upgrades |
| M5 | **React Query + RSC hybrid** on frontend | Use Next.js 15 Server Components for initial data (no client bundle), React Query for mutations and real-time |
| M6 | **Token refresh queue** on frontend | Prevents race condition on concurrent 401 responses |
| M7 | **Elasticsearch index for doctors** | Doctor search currently falls back to PostgreSQL `LIKE` queries; will degrade beyond ~100k records |
| M8 | **Rate limiting on public endpoints** | `/api/doctors`, `/api/hospitals`, `/api/blood-donors` are trivially scrapable; add Bucket4j or API gateway throttling |
| M9 | **Doctor verification workflow** (Security item 24 above) | Replace boolean flag with `VerificationStatus` enum + audit trail of who verified and when |
| M10 | **Implement Ratings API or remove ratings from marketing** | `Rating`/`RatingOption` entities and DB table exist but there is no write path; product advertises reviews as a live feature |
| M11 | **Build native prescription feature in main backend/frontend; retire standalone service** | `open-care-prescription-management` is a temporary workaround. The `prescription` table already exists in the main DB. Build the entity/service/API layer there and decommission the standalone service. |

---

### Long-Term / Futuristic

| # | Item | Justification |
|---|------|---------------|
| L1 | **Event-driven search indexing** via Redis Streams or Kafka | Replace nightly `ReindexScheduler` with real-time event: `DoctorUpdated → ES index`. Zero-lag search. |
| L2 | **GraphQL or BFF layer** | The `?degrees=true&workplaces=true&associations=true` pattern is the exact over/under-fetching problem GraphQL solves. A BFF (Backend for Frontend) is a lighter alternative. |
| L3 | **Feature flags** (Unleash or LaunchDarkly) | Telemedicine, AI symptom checker, and multi-language features can be deployed dark and enabled per user cohort without code deploys. |
| L4 | **Microservices migration** | The platform is intentionally a monolith now. When user growth and revenue justify the operational complexity, split along natural seams: Auth, Directory (doctors/hospitals), Health Records, Appointments, Notifications, Payments. Do not migrate before that threshold — the overhead is not worth it at current scale. |
| L5 | **Mobile application** (React Native + Expo) | Planned for a future phase once the web platform is feature-complete and generating revenue. |
| L6 | **Multi-tenancy** | If expanding beyond Bangladesh, the Division → District → Upazila hierarchy must become tenant-scoped. Design the schema for this now to avoid a painful migration later. |
| L7 | **Read replica for Elasticsearch** | As doctor/hospital dataset grows, separate read traffic (search) from write traffic (indexing) using ES replica shards. |

---

## Quick Wins

Low effort, high impact — can be done in a single day:

| Task | File | Effort |
|------|------|--------|
| Remove duplicate QueryClient in admin layout | `admin/layout.tsx:4` | 2 min |
| ~~Add Caffeine spec to application.yml~~ | ~~`application.yml`~~ | ✅ Done |
| Add `.env.example` to both repos | Both repos | 15 min |
| Fix enum valueOf without try/catch in mappers | `HospitalMapper.java:65` | 10 min |
| ~~Fix exception handler leaking `ex.getMessage()`~~ | ~~`GlobalExceptionHandler.java:178`~~ | ✅ Done |
| Add token expiry check to middleware | `middleware.ts` | 20 min |
| Add error boundary to root layout | `src/app/layout.tsx` | 30 min |
| ~~Enable ShedLock on schedulers~~ | ~~`ReindexScheduler.java`~~ | ✅ Done |
| Fail loudly on missing API URL config | `src/config/config.ts` | 5 min |
| ~~Add try/catch to ReindexScheduler methods~~ | ~~`ReindexScheduler.java`~~ | ✅ Done |
| Add `@Valid` to health vital write endpoints | `HealthVitalApiController.java:179,196` | 2 min |
| Remove duplicate `CorsFilter` bean | `SecurityConfig.java:190–202` | 5 min |
| Migrate `Profile.dateOfBirth` to `LocalDate` | `entity/Profile.java:42` | 10 min |
| Add paginated variant to health vital history endpoint | `HealthVitalApiController.java:111` | 15 min |

---

## Health Summary

| Area | Backend | Frontend | Notes |
|------|---------|----------|-------|
| Architecture | ✅ Query/Command split (DoctorServiceImpl) | ⚠️ Inconsistent patterns | Frontend still needs targeted refactoring |
| Authentication | ✅ Saga compensated (Keycloak rollback) | 🔴 Token desync bug | Frontend is a live bug |
| Data Fetching | ✅ N+1 fixed (JOIN FETCH) | 🔴 Mixed Query vs useEffect | Dashboard needs full rewrite |
| Type Safety | ✅ MapStruct + DTOs + PageResponse | ⚠️ Inline type redefs | Backend stronger than frontend |
| Security (Auth) | ✅ Auth rate limiting added | 🔴 Client-side auth checks | Frontend still needs hardening |
| Security (Data) | 🔴 IDOR on health vitals, public files, CORS wildcard | 🔴 Client-side auth checks | **Active vulnerabilities — fix before patient data expansion** |
| Privacy / Consent | 🔴 `health_data_consent` unenforced | — | Legal liability for health data storage without consent |
| Performance | ✅ Queries capped at 1000 | ⚠️ No code splitting | Unbounded health vital history endpoint also needs fix |
| Error Handling | ✅ Sanitized (no internal leaks) | 🔴 No error boundaries | Frontend still needs error boundaries |
| Observability | ❌ No tracing | ❌ No tracing | Neither has distributed tracing |
| Testing | ❓ TestContainers present | ❓ No tests reviewed | Needs dedicated review pass |

**Legend:** ✅ Good · ⚠️ Needs attention · 🔴 Active issue · ❌ Missing

---

*Last updated: April 11, 2026 — platform re-evaluation added security section (items 1–7), new backend medium-priority items (19–24), new frontend medium-priority item (33), and expanded near/medium-term roadmap. Re-review recommended after each major feature sprint.*
