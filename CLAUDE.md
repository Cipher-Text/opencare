# OpenCare — Documentation Hub

This is the **single source of truth** for the OpenCare platform. All product decisions,
architecture choices, feature status, code review findings, and roadmap items are documented
here. When something changes in any repo, this repo is updated to reflect it.

Read this file fully before making any changes. Every section below is load-bearing.

---

## What This Repo Is (and Is Not)

**Is:** Platform-wide documentation, architecture decisions, feature roadmap, code review
findings, user functionality matrix, UI/UX guidelines, environment variable reference.

**Is not:** Source code. Do not add runnable code here. Do not copy implementation details
from the code repos — reference them by file path and line number instead.

---

## Platform Mental Model

Open Care is a healthcare information platform for Bangladesh. It connects patients,
doctors, hospitals, social organizations, and institutions through a searchable directory
and eventually a set of care services.

### Current Architecture: Intentional Monolith

```
Frontend (Next.js)  ←→  Backend (Spring Boot)  ←→  PostgreSQL
                              ↓                          ↓
                         Keycloak               Elasticsearch (search)
                              ↓                          ↓
                           MinIO                   Liquibase (migrations)
```

**Do not suggest microservices.** The platform is a deliberate monolith. Migration to
microservices is a long-term item (Phase 4) gated on user growth and revenue. The
current architecture is the right choice for this stage.

### Temporary Service

`open-care-prescription-management` is a **temporary standalone service** built to unblock
early prescription workflows. It will be **retired** once prescription features are built
natively in the main backend and frontend. Do not suggest integrating it deeper or building
new features on top of it.

### Mobile App

Not yet started. Planned for Phase 3. Do not reference it as existing or active.

### Repositories

| Repo | Purpose | Status |
|------|---------|--------|
| `open-care-backend` | Spring Boot API, PostgreSQL, Keycloak, MinIO, Elasticsearch | Active |
| `open-care-frontend` | Next.js web app | Active |
| `open-care-prescription-management` | Temporary prescription service | Temporary — to be retired |
| `open-care-mobile` | React Native mobile app | Future — not started |
| `open-care-pages` | Astro static pages for doctor/hospital public profiles | Active |
| `open-care-landing` | Marketing landing page | Active |
| `opencare` | This repo — documentation hub | Active |

---

## File Map — What Each Document Does

Before editing any file, understand its purpose and audience.

### `README.md`
Public-facing project overview. Read by: contributors, evaluators, external parties.
- Keep the Repositories table and Roadmap in sync with actual implementation status.
- Do not mark a feature as done (✅) unless it is verifiably live in both backend and frontend.
- Do not list mobile app as active — it is not started.
- Architecture diagram must match current deployed state (no mobile box).

### `docs/CODE_REVIEW.md`
Living technical debt tracker. Read by: developers, Claude when working in code repos.
- **Security section** (items 1–7): open vulnerabilities. Never mark resolved without
  verifying the fix is merged in `open-care-backend`.
- **Backend/Frontend Medium Priority**: accumulated issues to address in future sprints.
- **Health Summary table**: updated when items are resolved or new issues found.
- **Future Architecture Roadmap**: Near/Medium/Long-term items. Do not move items forward
  without confirmation that work has been done.
- Update the `Last updated` line at the bottom when you edit this file.

### `docs/USER_FUNCTIONALITY_MATRIX.md`
Maps features to user roles. Two tables:
1. The feature/role matrix — what is planned per role.
2. The Implementation Status table — what is **actually** built.
Keep these in sync. The status table must reflect reality, not aspiration.

### `docs/OPEN_CARE_OVERVIEW.md`
Mermaid data model diagram. Update only when the database schema changes in
`open-care-backend/src/main/resources/db/changelog/`.

### `docs/ENVIRONMENT_VARIABLES.md`
All environment variables across all services. Update when a new env var is added to
any repo's `.env.example` or `application.yml`.

### `docs/UI_UX_GUIDE.md`
Information architecture and page tree for the frontend. Defines the full URL/route
structure, what data each page needs, and which backend entity it maps to. Also includes
the priority implementation order, critical missing elements, and Keycloak integration
points. This is a planning document, not a design system — it covers structure and data
flow, not visual tokens, colours, or typography.

### `wireframe/`
SVG wireframes for planned screens. Do not delete wireframes for unbuilt features —
they are the design intent.

---

## Key Domain Facts

Memorize these. They come up in almost every task.

- **BMDC number** — Bangladesh Medical and Dental Council registration. Primary identifier
  for doctors. Unique constraint in DB.
- **Location hierarchy** — Division → District → Upazila → Union. Bangladesh-specific.
  Never flatten or rename these.
- **Keycloak** — handles all authentication. JWT `realm_access.roles` carries permissions.
  Never suggest replacing Keycloak with a custom auth system.
- **Blood management** — donors, donations, requisitions. Donor contact details are
  sensitive and must not be fully public (open security issue — see CODE_REVIEW.md item 6).
- **Health vitals** — the most sensitive data in the system. IDOR vulnerability exists
  (CODE_REVIEW.md security item 1). Do not write docs that imply this feature is safe
  for broad patient use until that issue is resolved.
- **User types** — DOCTOR, PATIENT, HOSPITAL_ADMIN, SOCIAL_ORGANIZATION_ADMIN,
  INSTITUTION_ADMIN, SUPER_ADMIN, MODERATOR, OPERATOR.
- **Permission style** — granular named permissions (`create-doctor`, `update-hospital`),
  not broad roles. Defined in `open-care-backend/docs/PERMISSIONS.md`.

---

## Rules for Working in This Repo

### Always do
- Verify facts against the actual code repos before writing them here. A claim like
  "feature X is implemented" requires seeing an entity, service, and controller for it
  in `open-care-backend` and a page/component in `open-care-frontend`.
- Cross-check `README.md` roadmap against `open-care-backend/docs/PROJECT_STATUS.md`
  whenever updating either.
- Keep the Implementation Status table in `USER_FUNCTIONALITY_MATRIX.md` accurate.
- Update `Last updated` lines when editing `CODE_REVIEW.md`.

### Never do
- Mark a security issue in `CODE_REVIEW.md` as resolved without seeing the fix in code.
- Add mobile app content as if it exists.
- Suggest integrating the prescription service deeper — it is temporary.
- Invent features or roadmap items not discussed with the team.
- Change the platform architecture (monolith → microservices) in docs without explicit
  instruction — this is a deliberate strategic decision.
- Remove wireframes for unbuilt features.

### When unsure
- Check `open-care-backend/docs/ai/AI_CONTEXT.md` for the platform constitution.
- Check `open-care-backend/docs/PROJECT_STATUS.md` for what is actually built.
- Ask rather than assume implementation status.

---

## How to Cross-Check Implementation Status

When someone asks "is feature X implemented?", verify like this:

1. **Backend entity**: `open-care-backend/src/main/java/.../entity/` — does a Java class exist?
2. **Backend API**: `open-care-backend/src/main/java/.../controller/` — does a controller exist?
3. **Frontend page**: `open-care-frontend/src/app/` — is there a route/page?
4. **Frontend API call**: `open-care-frontend/src/api/` — is the API function written?
5. **DB migration**: `open-care-backend/src/main/resources/db/changelog/` — is the table created?

A feature is only ✅ Live if all five exist. A feature is 🚧 Partial if only some exist.

---

## Custom Commands

Common tasks are available as slash commands. Run them by typing `/command-name`.

| Command | When to use |
|---------|-------------|
| `/update-code-review` | After a sprint, security audit, or review pass — add new findings or mark items resolved |
| `/update-feature-status` | When a feature ships or status changes — update the implementation status table |
| `/sync-roadmap` | Reconcile the README roadmap with backend PROJECT_STATUS.md |

---

## Related AI Guidance in Other Repos

- `open-care-backend/docs/ai/AI_CONTEXT.md` — platform constitution (applies to all repos)
- `open-care-backend/docs/ai/AI_BACKEND.md` — backend-specific rules
- `open-care-backend/docs/ai/AI_POLICY.md` — data privacy and AI usage policy
- `open-care-backend/CLAUDE.md` — Claude entry point for the backend repo
