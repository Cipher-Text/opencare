You are updating the Implementation Status table in `docs/USER_FUNCTIONALITY_MATRIX.md`
in the OpenCare documentation hub.

## Context

`USER_FUNCTIONALITY_MATRIX.md` has two tables:

1. **Feature/Role Matrix** (top): what is planned for each user role. Change only when
   the product scope changes.

2. **Implementation Status table** (bottom): the live truth of what is actually built.
   This is what you are updating.

Status legend:
- ✅ Live — backend entity + controller + frontend page + API call all exist
- ⚠️ Partial — some layers exist but not all
- 🚧 Schema/service exists, needs implementation
- ❌ Not started
- 🔮 Future phase (mobile, microservices, etc.)

## What to do

The user will tell you which feature(s) changed. For each:

1. **Verify the claim before updating.** A feature is only ✅ Live if all five exist:
   - `open-care-backend/src/main/java/.../entity/` — Java entity class
   - `open-care-backend/src/main/java/.../controller/` — REST controller
   - `open-care-frontend/src/app/` — page or route
   - `open-care-frontend/src/api/` — API integration function
   - `open-care-backend/src/main/resources/db/changelog/` — DB migration

   If you cannot verify, ask the user to confirm or point to the relevant files.

2. **Update the status cell and notes** for the affected row.

3. **Check if `README.md` needs updating too.** The Phase 1/2/3 roadmap in `README.md`
   must stay consistent with this table. If a feature moves to ✅ Live, mark it `[x]`
   in the roadmap. If it's still partial, leave it `[ ]`.

## Rules

- Do not change the Feature/Role Matrix (top table) unless the product scope changed.
- Never mark something ✅ Live based on a DB schema alone. Schema + no code = 🚧.
- The prescription row must always note: "temporary standalone service; will be replaced
  by native feature in main backend/frontend."
- Mobile-related features stay 🔮 Future — do not change them without explicit instruction.

## Start

Ask the user: "Which feature shipped or changed status?" Then read
`docs/USER_FUNCTIONALITY_MATRIX.md` before making any edits.
