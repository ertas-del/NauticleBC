# Handoff note (updated 2026-10-05, late)

Written so another person or Claude session can pick up without the earlier conversation.

## Where things live

- Repo: https://github.com/ertas-del/NauticleBC (renamed from `nauticlebc`; the old URL still redirects).
- Living plan: Claude Docs doc "Marine Ticket Prep App: Plan" (https://claude.ai/code/artifact/19398f14-8ff9-4bee-8c45-8a80b032975e). Edit it in place; do not recreate it.
- Rules for every contributor: `CLAUDE.md`. Lanes and parallel workflow: `docs/workflow.md`. Licences: `docs/licence-checklist.md`.

## Done

- Plan doc written (all modules, sources, licensing, exam facts, open questions).
- Decisions: Android first (Kotlin + Compose); first release = Bridge Watch Rating + Master Limited < 60 GT, starting with COLREGs and tides; text-to-speech for audio later; MED, MED practice and marine first aid on hold; speech recognition later.
- Merged to `main` (all with green CI, Android build included):
  - #1 foundation: layout, `CLAUDE.md`, schemas, content lint and bundle builder, Android skeleton, CI.
  - #4 engine: module list, lesson reader (small Markdown renderer), per-lesson quiz, progress storage, review-status label, sources list.
  - #3 COLREGs Rules 1, 2, 3, 4, 6, 7 (6 lessons, 31 questions).
  - #2 COLREGs Rules 8, 9, 10 (3 lessons, 20 questions) plus Rule 5 sample from #1.
  - #5 Tides module 1: six lessons (causes, spring/neap, vocabulary, types, tables and heights, tidal currents), 41 questions, 20 new sources.
- Totals on `main`: 16 lessons, 95 questions, 26 sources. Everything is `needs_expert_review`.

## Needs doing next

1. **Add the Gradle wrapper**: `gradle wrapper --gradle-version 8.10.2`, commit it, switch `android.yml` to `./gradlew`. It could not be generated offline.
2. **Test on a device.** Nothing has run on a phone yet: check layout, TalkBack, large fonts, rotation mid-quiz, dark theme. Product choices to confirm: finishing a quiz marks the lesson complete; best score compares by fraction correct.
3. **Expert review.** Each merged PR body lists the items a certified master should check (Canadian modifications in Rules 1, 3, 6, 9, 10; BC traffic separation schemes; BC tide type; CHS secondary-port method; rule of twelfths).
4. **Read the primary sources.** Justice Laws, tc.canada.ca and tides.gc.ca could not be fetched, so every COLREGs and tides lesson rests on summaries and secondary sources. Someone with browser access should read the live text and correct anything off.
5. **Next content lanes**: COLREGs Rules 11 to 19 (steering and sailing), 20 to 31 (lights and shapes), 32 to 37 (sound signals); tides: BC regional atlas and current tables; Bridge Watch and 60-ton modules (stability, radar, TimeZero); mock-exam blueprints (`content/mock-exams/`).
6. **QA and licences lane**: licence checklist upkeep, reviewer hand-off lists per module.
7. **Later**: oral exam tester, knots, SVOP, SVMO (video plus TTS), flags, radio, lights map, sea temperature, BC coast map hub, weather, towing logs.

## Waiting on the user

- Confirm the first-release boundary (Bridge Watch Rating + Master Limited < 60 GT, COLREGs and tides first).
- A certified master or examiner to review regulatory content (nothing may be marked `expert_reviewed` without one).
- Open plan questions: SVMO first target (unrestricted vs restricted), TTS route, CHS licence route and chart count, sea-temperature coverage, purpose of the lights map, flags set, log-towing handbook title or link.

## Known gaps and cautions

- Several sources were not read directly (Justice Laws, TC pages blocked; some PDFs behind an archive interstitial). They are marked `read_directly: false`; verify before relying on them.
- CHS tide and current data is free only for non-navigational, non-commercial use. The app is a study aid, not a navigation aid.
- This sandbox cannot open pull requests with `gh pr create` (GraphQL blocked); use `gh api repos/ertas-del/NauticleBC/pulls`.
