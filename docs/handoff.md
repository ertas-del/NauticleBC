# Handoff note (2026-10-05)

Written so another person or Claude session can pick up without the earlier conversation.

## Where things live

- Repo: https://github.com/ertas-del/NauticleBC (renamed from `nauticlebc`; the old URL still redirects).
- Living plan: Claude Docs doc "Marine Ticket Prep App: Plan" (https://claude.ai/code/artifact/19398f14-8ff9-4bee-8c45-8a80b032975e). Edit it in place; do not recreate it.
- Rules for every contributor: `CLAUDE.md`. Lanes and parallel workflow: `docs/workflow.md`. Licences: `docs/licence-checklist.md`.

## Done

- Plan doc written (all modules, sources, licensing, exam facts, open questions).
- Decisions: Android first (Kotlin + Compose); first release = Bridge Watch Rating + Master Limited < 60 GT, starting with COLREGs and tides; text-to-speech for audio later; MED, MED practice and marine first aid on hold; speech recognition later.
- `main` has a bootstrap commit. PR #1 (`foundation/scaffold`) adds:
  - repo layout, `CLAUDE.md`, docs, issue and PR templates
  - JSON Schemas: source, lesson, question, mock exam, oral-exam scenario (reserved)
  - `tools/lint_content.py` and `tools/build_bundle.py` (tested, including against deliberately broken content)
  - sample COLREGs Rule 5 lesson plus 3 original questions (pipeline sample, `needs_expert_review`)
  - Android skeleton: home screen, bundle models and loader, one unit test
  - CI: content lint (passed on the PR) and Android build (was still running when this was written)

## Needs doing next

1. **Check PR #1's Android CI.** The Kotlin and Gradle code has never been compiled (no Android SDK in the authoring sandbox). Fix whatever breaks, then merge.
2. **Add the Gradle wrapper**: `gradle wrapper --gradle-version 8.10.2`, commit it, switch `android.yml` to `./gradlew`. It could not be generated offline.
3. **Open one GitHub issue per lane task**, then start parallel sessions in git worktrees (`claude -w <name>`):
   - Content engine: lesson reader, quiz and mock-exam engine, progress storage.
   - COLREGs content: lessons per rule (Parts A to F), from cited sources, in our own words.
   - COLREGs drills: original questions and situation drills.
   - Tides content/tools: tide science lessons, table-reading exercises, BC region summaries.
   - QA and licences: source registry upkeep, reviewer hand-off lists.
   Lanes 3 to 5 start once the schema PR is merged.
4. **Then**: oral exam tester, knots, SVOP, SVMO (video plus TTS), flags, radio, lights map, sea temperature, BC coast map hub, weather, towing logs.

## Waiting on the user

- Confirm the first-release boundary (Bridge Watch Rating + Master Limited < 60 GT, COLREGs and tides first).
- A certified master or examiner to review regulatory content (nothing may be marked `expert_reviewed` without one).
- Open plan questions: SVMO first target (unrestricted vs restricted), TTS route, CHS licence route and chart count, sea-temperature coverage, purpose of the lights map, flags set, log-towing handbook title or link.

## Known gaps and cautions

- Several sources were not read directly (Justice Laws, TC pages blocked; some PDFs behind an archive interstitial). They are marked `read_directly: false`; verify before relying on them.
- CHS tide and current data is free only for non-navigational, non-commercial use. The app is a study aid, not a navigation aid.
- This sandbox cannot open pull requests with `gh pr create` (GraphQL blocked); use `gh api repos/ertas-del/NauticleBC/pulls`.
