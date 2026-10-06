# Parallel workflow

## Lanes

1. **Foundation** — repo, CI, Android skeleton, schemas.
2. **Content engine** — bundle builder, lesson reader, quiz/mock-exam engine, progress storage.
3. **COLREGs content** — lessons per rule, from cited sources, in our own words.
4. **COLREGs drills** — original questions and situation drills per rule group.
5. **Tides content/tools** — tide science lessons, table-reading exercises, BC region summaries.
6. **QA and licences** — licence checklist upkeep, source registry, lint rules, reviewer hand-off lists.

Lanes 3–5 start once the schema PR is merged. Lane 2 can start alongside it.

## Running sessions

- Each session works in its own worktree: `claude -w colregs-rules-1-10`.
- Pick an issue, say which lane you are in, work only in that lane's folders.
- Open a PR early as a draft; mark ready when lint is green.
- A human (or the QA lane) merges. Merge schema PRs before content PRs that depend on them.

## Handing content to a human reviewer

The QA lane produces a list of `needs_expert_review` items per module. A certified master or examiner signs off by
setting `review_status: expert_reviewed`, `reviewed_by`, and `reviewed_on` in a PR of their own.
