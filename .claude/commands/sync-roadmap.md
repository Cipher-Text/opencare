You are reconciling the OpenCare roadmap across two files:

- `README.md` (this repo) — public-facing roadmap in Phase 1/2/3/4 format
- `open-care-backend/docs/PROJECT_STATUS.md` — authoritative implementation checklist

## Goal

These two files must tell the same story. Find every inconsistency and fix it.

## What to do

1. Read both files fully.

2. For every item in `PROJECT_STATUS.md` marked `[x]` (done):
   - Find the corresponding item in `README.md` Phase 1 or 2.
   - If it is listed as `[ ]` (not done) in README, mark it `[x]`.
   - If it is missing from README entirely, decide: is it worth adding? If yes, add it
     to the appropriate phase. If it's an internal implementation detail not meaningful
     to external readers, skip it.

3. For every item in `PROJECT_STATUS.md` marked `[ ]` (not done):
   - Find the corresponding item in `README.md`.
   - If README marks it `[x]`, remove the tick — it is not done.
   - If it is missing from README and is user-facing, add it to the appropriate phase.

4. After syncing, cross-check `docs/USER_FUNCTIONALITY_MATRIX.md` implementation status
   table for any inconsistencies with the same items.

5. Report what you changed and what (if anything) is still ambiguous.

## Rules

- **Never mark something done without code evidence.** A DB schema alone is not done.
  Check `open-care-backend/src/main/java/.../controller/` for a controller.
- The mobile app is never ✅. It is not started.
- The prescription service row in README must say "temporary service; will be rebuilt
  natively" — not "done" or "integrated".
- Do not add Phase 4 or future microservices items to Phase 1/2 — they belong in Phase 4.
- Phase descriptions: Phase 1 = core directory & auth, Phase 2 = care services & records,
  Phase 3 = advanced features + mobile, Phase 4 = scale & expansion + microservices.

## Start

Read `open-care-backend/docs/PROJECT_STATUS.md` and `README.md` now, then report the
inconsistencies you found before making any changes.
