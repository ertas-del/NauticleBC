# CLAUDE.md — NauticleBc

Android app (Kotlin + Jetpack Compose) for learning Canadian marine tickets in British Columbia.
First release: Bridge Watch Rating + Master Limited < 60 GT, starting with COLREGs and tides.
Content is plain data files so iOS can reuse it later and so content can be written separately from app code.

## Repo layout

| Path | What lives there | Owner lane |
|---|---|---|
| `app/` | Android app (Kotlin, Compose) | Foundation |
| `schemas/` | JSON Schemas for all content types. **Changing these needs its own PR.** | Foundation |
| `content/` | Lessons (`.md` with YAML frontmatter), questions/scenarios (`.json`), `sources.json` | Content lanes |
| `tools/` | Content lint, bundle builder, later the TTS renderer | Content engine |
| `docs/` | Plan pointer, licence checklist, source registry notes, workflow | Anyone |
| `.github/workflows/` | CI | Foundation |

## Parallel work rules (several Claude sessions run at once)

1. One task = one GitHub issue = one branch = one PR. Branch names: `<lane>/<short-task>` (e.g. `colregs/rule-05-lookout`).
2. Use a git worktree per session (`claude -w <name>`); never work in another session's worktree.
3. Stay in your lane's folders. Do not edit `schemas/` or `app/` from a content lane; open an issue instead.
4. Rebase on `origin/main` before pushing. Do not push to `main`; open a PR.
5. Run `python3 tools/lint_content.py` before every PR that touches `content/` or `schemas/`. CI runs it too.
6. Commit messages: short imperative subject, then the attribution lines the session was given.

## Content rules (non-negotiable)

- **Every factual or regulatory claim cites a source ID** from `content/sources.json` (`source_ids`). No source, no claim.
- **Write in your own words.** Never paste text from textbooks, provider courses, exam banks, or Transport Canada documents unless the source entry says reproduction is permitted, and then keep it to short quotes with attribution.
- Anything regulatory (rule numbers, thresholds, distances, light specifications, exam requirements) is `review_status: needs_expert_review` until a certified master/examiner signs off. Only a human reviewer may set `expert_reviewed`, and they record who and when in `reviewed_by` / `reviewed_on`.
- Questions are original. Do not recreate or paraphrase actual Transport Canada exam questions.
- The app is a **study aid, not a navigation aid.** Nothing in it may be presented as official, as an approved course, or as a substitute for chart/tide/current publications used for navigation.
- Tide, current, chart and lights data used in the app must stay within the licence recorded in `docs/licence-checklist.md`. If the licence is unclear, stop and flag it.

## Code conventions

- Kotlin, Jetpack Compose, Material 3. Single `app` module for now; split only when it earns it.
- Content is bundled into `app/src/main/assets/content/` by `tools/build_bundle.py` (generated, git-ignored). The app reads only the bundle.
- Keep UI strings in `strings.xml`. No hard-coded user-facing text in Kotlin except content from the bundle.
- Accessibility: every screen works with TalkBack and large font sizes.

## Definition of done for a PR

- Lint passes. Android build passes if `app/` changed.
- New content has sources, a review status, and passes the schema.
- PR description says what changed, what is unverified, and what a human reviewer must check.
