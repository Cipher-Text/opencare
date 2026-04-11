You are updating `docs/CODE_REVIEW.md` in the OpenCare documentation hub.

## Context

`CODE_REVIEW.md` is the living technical debt tracker for the OpenCare platform. It covers
`open-care-backend` (Spring Boot) and `open-care-frontend` (Next.js). It is structured as:

- Backend — High Priority (Security): open vulnerabilities (items 1–7)
- Backend — Previously Resolved High Priority
- Backend — Medium Priority: code quality and architecture issues
- Frontend — Critical Issues
- Frontend — High Priority
- Frontend — Medium Priority
- Cross-Cutting Concerns
- Future Architecture Roadmap (Near / Medium / Long-Term)
- Quick Wins table
- Health Summary table

## What to do

The user will describe what has changed — a fix was merged, a new issue was found, or a
sprint just finished. You must:

1. **If marking an item resolved:**
   - First verify the fix exists in the actual code. Ask the user for the file/commit if
     not obvious.
   - Strike through the item title with `~~` and append `✅ Resolved — <brief description>`.
   - Update the Health Summary table row if the area changed.
   - If it was a Quick Win, strike it through there too.

2. **If adding a new issue:**
   - Determine the correct section (security? backend medium? frontend critical? etc.).
   - Assign the next sequential number in that section.
   - Follow the existing format: `### N. Title`, `**File:** path:line`, problem description,
     code snippet if useful, `**Fix:**` section.
   - Add a matching row to the Health Summary table if it affects a tracked area.
   - If it is low-effort, add it to Quick Wins.

3. **In all cases:**
   - Update the `Last updated` line at the bottom with today's date and a one-line summary
     of what changed.

## Rules

- Never mark a security item (1–7) as resolved without the user confirming the fix is merged.
- Do not renumber existing items — it breaks cross-references in other docs.
- Keep the Health Summary legend: ✅ Good · ⚠️ Needs attention · 🔴 Active issue · ❌ Missing.
- Near/Medium/Long-term roadmap items use ~~strikethrough~~ + `✅ Done` when completed.
  They are never deleted, so the history is preserved.

## Start

Ask the user: "What changed — a fix was merged, a new issue was found, or both?"
Then read `docs/CODE_REVIEW.md` before making any edits.
