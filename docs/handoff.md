# Handoff note (updated 2026-10-06)

Written so another person or Claude session can pick up without the earlier conversation.

## Where things live

- Repo: https://github.com/ertas-del/NauticleBC (renamed from `nauticlebc`; the old URL still redirects).
- Living plan: Claude Docs doc "Marine Ticket Prep App: Plan" (https://claude.ai/code/artifact/19398f14-8ff9-4bee-8c45-8a80b032975e). Edit it in place; do not recreate it.
- Rules for every contributor: `CLAUDE.md`. Lanes and parallel workflow: `docs/workflow.md`. Licences: `docs/licence-checklist.md`.

## Status (2026-10-06)

On `main`: 101 lessons, 880 questions, 2 mock exams (NS1 practice, Bridge Watch practice), 156 sources. Lint clean, CI green. All content is `needs_expert_review`.

- COLREGs complete: Rules 1-38, Part F (Canadian Rules 39-46), Annexes I-IV, encounter/lights/sound drills, NS1 traps review.
- Tides module 1 (6 lessons).
- Bridge watch: 24 lessons (watchkeeping, lookout, reports, handover, calling the master, helm orders, steering, compass, alarms, MOB, pyrotechnics, survival craft).
- Seamanship 1-18: buoyage, light characteristics, ship terms, vessel types, ropes, knots, splices, wire and shackles, mooring, anchoring, deck machinery, access equipment, flags and single-letter signals, draft marks, cargo, maintenance. Bridge Watch syllabus is now fully written (except the optional radio/stability/regs rating lessons in items 5, 7, 8, 9).
- Sixty-ton pathway: 6 lessons (certificate, requirements, sea service, exams, oral guide, know your vessel).
- App: lesson reader, exam-focus card and highlights, quizzes, progress. Debug APK is built by CI (Actions artifact `app-debug`).
- Syllabus briefs: `docs/syllabus/bridge-watch-rating.md`, `docs/syllabus/master-limited-60gt.md`. Writer instructions: `docs/writer-guide.md` (official law text via the Justice Canada XML repo, cloned no-checkout at /home/claude/justicecanada/laws-lois-xml in the old sandbox; re-clone github.com/justicecanada/laws-lois-xml).

## Work in progress (draft PRs, cut off by usage limits)

- PR #26 chartwork lessons 1-7 written (charts, datums, symbols, lat/long, compass types, variation, deviation card). Missing: questions for 5 of them, the rest of chartwork 1-12 (Canadian publications, chart correction, TVMDC conversions, bearings).
- PR #27 stability lessons 1-4 written (awareness, terms, G/B/M, metacentric height). Missing: questions for all four, and lessons 5-11 of the stability list.
- Chartwork 13-24 (fixes, DR, set and drift) not started.
- Next Claude: check out each branch, add 6-10 original questions per lesson, finish the lesson list from docs/syllabus/master-limited-60gt.md, run `python3 tools/lint_content.py`, mark the PR ready and merge when CI is green.
- The user has dyslexia and said the current swift, to-the-point lesson style works for them. Keep it. Reading mode and read-aloud are still to build in the app.

## SVMO (Small Vessel Machinery Operator) track
Uploaded from another Claude on 2026-10-10. Brief: `docs/syllabus/svmo.md`. Learner plan: `docs/svmo-study-plan.md`.
- Module `machinery`, ticket `svmo`, sources in `content/sources/svmo.json`.
- Written (9 lessons, 83 questions): diesel basics (100), start/run/faults (110), fuels (120), cooling (130), electricity basics/protection/batteries (200-220), pumps (300), piping (310).
- Parked: `docs/svmo-pending/questions-svmo-pending.json` holds 54 questions for 13 lessons that do not exist yet (materials, fire hazards, fire/watertight equipment, safe working, pollution/bunkering, shafting/gearboxes, steering, underwater fittings, deck machinery, lubricants, hydraulics, pneumatics, watchkeeping). Move each group into `content/machinery/` when its lesson is written (ids must match the lesson id).
- Still to write: about 22 lessons per the lesson map in the brief, then two 60-question SVMO mocks.
- Expert review items are listed in the brief (MED course name, pass mark, oral vs practical, figures such as bilge blower time and battery electrolyte values).
- `tp14814e` is a 2008 third-party copy; check for a newer TC edition.

## Remaining writer assignments (run 3-4 at a time; each follows docs/writer-guide.md)

1. DONE (#24): seamanship 100-180 (Bridge Watch brief 3.4): wire/chain/shackles, mooring, anchoring, deck machinery and hand signals, access equipment, flags and single-letter signals, draft marks and soundings, cargo and stores, maintenance. Branch seamanship/deck-operations, sources sea-b.json.
2. seamanship 200+: the 16 master-level lessons in the 60 GT brief 'Module seamanship' (emergencies, fire, flooding, abandon ship, cold water, heavy weather, towing). sources sea-c.json.
3. chartwork 5 + 60 GT chartwork 1-12 (charts, datums, lat/long, publications, compass error and TVMDC). sources chw-a.json.
4. chartwork 60 GT 13-24 (DR, fixes, running fix, set and drift, course to steer, clearing bearings, passage planning, CP1 technique). sources chw-b.json.
5. canadian-regulations 60 GT 1-7 + Bridge Watch safe working, enclosed spaces, pollution (orders 200-220). sources regs-a.json.
6. canadian-regulations 60 GT 8-14 (Navigation Safety Regs 2020, VTS/MCTS BC (rules changed 2026-03-31), pollution, TSB reporting). sources regs-b.json.
7. stability 5 + 60 GT stability 1-11 (full first-principles stability with worked calculations). sources stab.json.
8. radio 5 + 60 GT radio 1-7, meteorology 1-6. sources rmet.json.
9. radar 1-6, electronic-navigation 1-5 + Bridge Watch bridge-equipment-tour (60) and radar-for-lookouts (70). sources renav.json.
10. tides additions 7-8 (60 GT brief).
11. Update mock-exam blueprints to include bridge-watch and seamanship questions.

## Before an alpha test

- Bridge Watch / COLREGs alpha: ready to sideload now. Do a device pass (TalkBack, large fonts, rotation mid-quiz, dark theme) on the Samsung S26.
- Add the Gradle wrapper (`gradle wrapper --gradle-version 8.10.2`).
- Get one certified master to skim COLREGs and bridge-watch content; each PR lists its expert-review items.
- Full Master Limited alpha needs items 3-7 above (chartwork and stability are the core of CP1 and the oral).

## Waiting on the user

- Confirm the first-release boundary (Bridge Watch Rating + Master Limited < 60 GT, COLREGs and tides first).
- A certified master or examiner to review regulatory content (nothing may be marked `expert_reviewed` without one).
- Open plan questions: SVMO first target (unrestricted vs restricted), TTS route, CHS licence route and chart count, sea-temperature coverage, purpose of the lights map, flags set, log-towing handbook title or link.

## Known gaps and cautions

- Several sources were not read directly (Justice Laws, TC pages blocked; some PDFs behind an archive interstitial). They are marked `read_directly: false`; verify before relying on them.
- CHS tide and current data is free only for non-navigational, non-commercial use. The app is a study aid, not a navigation aid.
- This sandbox cannot open pull requests with `gh pr create` (GraphQL blocked); use `gh api repos/ertas-del/NauticleBC/pulls`.
