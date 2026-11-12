# Handoff note (updated 2026-11-12)

Written so another person or Claude session can pick up without the earlier conversation.

## Where things live

- Repo: https://github.com/ertas-del/NauticleBC (renamed from `nauticlebc`; the old URL still redirects).
- Living plan: Claude Docs doc "Marine Ticket Prep App: Plan" (https://claude.ai/code/artifact/19398f14-8ff9-4bee-8c45-8a80b032975e). Edit it in place; do not recreate it. In-repo summary: `docs/plan.md` (includes the maps and charts decision of 2026-10-13).
- Rules for every contributor: `CLAUDE.md`. Lanes and parallel workflow: `docs/workflow.md`. Licences: `docs/licence-checklist.md`. Writer instructions: `docs/writer-guide.md`.
- Syllabus briefs in `docs/syllabus/`: Bridge Watch Rating, Master Limited 60 GT, SVMO, SVOP, Fishing Master Fourth Class.

## Status (2026-11-12, main after PRs #69-#72 and #49)

Lint: 304 sources, 281 lessons, 2,762 questions, 11 mock exams, 1 chart, 9 chart exercises; 0 errors, 0 warnings.
CI on main is green (content lint, Android build, alpha release). Every lesson is `needs_expert_review`; none is `expert_reviewed`.

| Module | Lessons | Questions |
|---|---|---|
| colregs | 47 | 356 |
| seamanship | 30 | 318 |
| chartwork | 28 | 304 |
| bridge-watch | 26 | 260 |
| svop | 27 | 247 |
| machinery (SVMO) | 25 | 252 |
| canadian-regulations | 16 | 218 |
| stability | 12 | 110 |
| radio | 8 | 98 |
| tides | 8 | 76 |
| meteorology | 6 | 72 |
| radar | 6 | 64 |
| electronic-navigation | 5 | 53 |
| sixty-ton | 6 | 57 |
| fm4-* (overview, chartwork, navigation safety, oral, stability and construction) | 31 | 277 |

Mock exams: NS1 practice, Bridge Watch practice, SVMO 1-2, SVOP 1-2, FM4 CP1, FM4 NS1 1-2, FM4 SCS1 1-2. The nine SVMO, SVOP and FM4 mocks now use a fixed draw (`question_ids`, copied from `docs/*-mock-draw.json`; practice 1 and 2 share no question). NS1 and Bridge Watch practice still draw at random from their modules.

Fishing Master Fourth Class: 173 lessons carry `fishing-master-4` (the 34 fm4-* lessons plus the 139 "Yes" lessons from section 3.2 of `docs/syllabus/fishing-master-4.md`, merged in #49 and checked line by line against that list; "Optional" and "No" lessons are untagged).

Build: the Gradle 8.10.2 wrapper is committed (#72) and Android CI builds and tests with `./gradlew`.

All eleven writer assignments from the 2026-10-06 note are done (seamanship emergencies #44, chartwork #33 and #43, regulations #38 and #47, stability #34, radio and meteorology #37, radar and electronic navigation #39, tides 7-8 #43). The draft PRs #26 and #27 mentioned in older notes were superseded.

### App (as of 2026-11-12)

- Home is a **Today** view with a Tickets row; each ticket lists its subjects, lessons and mock exams.
- Lesson reader with exam-focus card, highlights, pipe tables as row cards, collapsible figures, sources behind a link.
- Reading mode: text size, relaxed spacing, read aloud with teacher-style pacing and the best offline voice on the device.
- Quizzes, progress, mock exams (drawn at random from the module banks).
- Exam practice: missed questions, plotting, closed-book mocks, oral cards.
- Practice sums (random compass/TVMDC, depth, tide, speed-distance-time drills) and an invented Sun almanac drill.
- Practice chart screen, stage 1: Training Harbour drawn from data, pan and zoom, nine graded exercises.
- SVMO lessons have study drawings. Nautical theme.
- Alpha APK: rolling GitHub pre-release `alpha`, rebuilt on every push to main. Every alpha is now signed with the same key, so updates install over each other.

## Open items

1. **Answer-length giveaway (top content priority).** `python3 tools/option_balance.py` reports that in 70% of questions (1,934 of 2,762) the correct option is the single longest one, against 25% by chance. Always picking the longest answer would score about 70%, which is the placeholder pass mark. Worst modules: fm4-navigation-safety 86%, canadian-regulations 80%, machinery 80%, fm4-oral 78%, radio 78%, svop 75%. Best: chartwork 45%. Fix by rewriting distractors so they are as long and as specific as the correct option (or trimming the correct option), one module per PR, keeping the facts and `source_ids` unchanged. Correct-answer positions are already well spread (A 735, B 797, C 653, D 577). Consider a lint warning once the banks are fixed.
2. **Branch `add-xai-grok-build`** (2026-11-12, no PR): adds the xAI grok-build source under `vendor/xai/grok-build` (about 4,500 files, 2.1 million lines of Rust, including third-party crates). It has nothing to do with the marine ticket app and would bloat the repo. Do not merge it into main without the owner's decision and a licence check of every vendored crate; if it is wanted, it belongs in its own repo.
3. Fixed draws for NS1 practice and Bridge Watch practice, if wanted (write a draw file, copy it into `question_ids`; lint checks it).
4. Chart simulator: remaining training charts (Narrows, Islands, Coastal Leg), then the real BC learning map and lights layer once each data licence is recorded (see `docs/plan.md`).
5. Stability gaps: following and quartering seas and towing stability are only touched on. IS Code numbers came from a secondary page; verify against TP 7301.
6. Gradle wrapper has no `distributionSha256Sum` yet; add it from https://gradle.org/release-checksums/.
7. Device pass on the Samsung S26: TalkBack, large fonts, rotation mid-quiz, dark theme, read aloud.
8. `tp14814e` (SVMO) is a 2008 third-party copy; check for a newer Transport Canada edition.
9. Fishing Master "Optional" lessons (43 lessons, about 460 questions in section 3.2 of the FM4 brief) are untagged; the owner decides.

## Waiting on the owner

- Confirm the first-release boundary (Bridge Watch Rating + Master Limited < 60 GT, COLREGs and tides first). The app now also carries SVMO, SVOP and FM4 content.
- A certified master or examiner to review regulatory content (nothing may be marked `expert_reviewed` without one). Each PR lists its expert-review items.
- Decide what to do with the `add-xai-grok-build` branch.
- Open plan questions: SVMO first target (unrestricted vs restricted), CHS licence route for real tide and current predictions, sea-temperature coverage, purpose of the lights map, flags set, log-towing handbook title or link.
- Licence open items in `docs/licence-checklist.md` (Reproduction of Federal Law Order wording, TP editions, coastline source, Coast Guard List of Lights licence).

## Working notes

- The owner has dyslexia and said the current swift, to-the-point lesson style works for them. Keep it.
- Official law text: clone github.com/justicecanada/laws-lois-xml (no checkout) and read the XML; Justice Laws and TC web pages are often blocked from the sandbox.

## Known gaps and cautions

- Several sources were not read directly (Justice Laws, TC pages blocked; some PDFs behind an archive interstitial). They are marked `read_directly: false`; verify before relying on them.
- CHS tide and current data is free only for non-navigational, non-commercial use. The app is a study aid, not a navigation aid. Tide exercises use synthetic tables until the licence is cleared.
- This sandbox cannot open pull requests with `gh pr create` (GraphQL blocked); use `gh api repos/ertas-del/NauticleBC/pulls`.
