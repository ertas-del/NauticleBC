# Fishing Master, Fourth Class: syllabus brief (draft for docs/syllabus/fishing-master-4.md)

Match the format to your existing briefs before committing. I haven't seen them.

## Ticket
Fishing Master, Fourth Class (FM4). Ticket id used: `fishing-master-4`. Modules used: `fm4-overview`, `fm4-chartwork`, `fm4-navigation-safety`, `fm4-stability-construction`, `fm4-oral` (split by exam so each practice exam can name its own module in your blueprint format).

## What it allows (TP 2293 Ch. 24, 2016 edition, read directly)
- Master of a fishing vessel of not more than 100 GT on a near coastal or sheltered waters voyage.
- Officer in charge of the watch on a fishing vessel of any tonnage on near coastal or sheltered waters voyages.

## Requirements (Marine Personnel Regulations s.142, current to 2024-10-30, read directly)
- Sea service: at least 12 months performing deck duties on fishing vessels 6 m or more. Up to 6 months from an approved training program. Up to half may be on normal trading vessels 6 m or more.
- Certificates to show the examiner: MED with respect to basic safety, ROC-MC, SEN Limited, marine advanced first aid.
- Exams: Chartwork and Pilotage level 1, Navigation Safety level 1, Ship Construction and Stability level 1, then the oral exam on general seamanship (last).
- Training providers also mention a seafarer's medical and a fatigue-management course before MED. Confirm with Transport Canada.

## The four exams (TP 2293 Ch. 24, 12.3 and 11.4)
| Exam | Format (per TP 2293) | Course content here |
|---|---|---|
| C/P 1 Chartwork and Pilotage | practical chartwork and multiple choice, unlimited time. C/P 2 may be substituted. | 5 `fm4.chart.*` lessons plus the 3 dyslexia-friendly chartwork lessons |
| NS 1 Navigation Safety | multiple choice, 1.5 hours | 5 `fm4.colregs.*` lessons, 2 `fm4.watch.*` lessons |
| SCS 1 Ship Construction and Stability | multiple choice and calculations from a stability booklet, 3 hours, open book. SCS 2 may be substituted. | 7 `fm4.stab.*` lessons, 3 `fm4.constr.*` lessons |
| FM 4-O Oral, General Seamanship | scenario questions, unlimited time | 8 `fm4.oral.*` lessons with model answers |
The number of questions and the pass marks are not stated in TP 2293.

## What is in this pack
- `lessons/`: 34 lessons: 1 path lesson, 5 C/P 1, 7 NS 1, 10 SCS 1, 8 oral, plus 3 dyslexia-friendly chartwork lessons (`chartwork.compass.formula-cards`, `chartwork.plotting.formula-cards`, `chartwork.depth.tide-formula-cards`). The dyslexia lessons are tagged for `svop`, `fishing-master-4` and `master-limited-60gt`. Under your one-set-per-ticket rule you may want to copy them per ticket.
- `questions/`: 307 questions in your question schema, one file per lesson, all `needs_expert_review`.
- `exams/`: 5 practice exams in your mock exam format (`mock.fm4-ns1-practice-1/2` 35 questions, `mock.fm4-scs1-practice-1/2` 30, `mock.fm4-cp1-practice-1` 25). Each blueprint names one module. The planned question ids per exam, with no overlap between exams of the same subject, are in `docs/fm4-mock-draw.json`. Placeholders: pass mark 70%, time limits NS 1 60, SCS 1 90, C/P 1 60 minutes, all flagged unconfirmed in the notes.
- Sources to register: `tp2293-2016` (Chapters 24, 11.4, 12.3 read directly), `mpr-sor-2007-115` (s.142 read directly), `tp14692` (for the dyslexia lessons).

## Gaps and cautions
- C/P 1 includes practical chartwork. The app can drill the sums and concepts but not replace plotting on a real chart with parallel rules and dividers. Pair it with paper chart exercises.
- SCS 1 uses a real vessel's stability booklet in the exam. The lessons teach the method with worked examples. Practise on a real booklet.
- Not read directly: TP 10038 (Small Fishing Vessel Safety Manual), the Code of Safety for Fishermen and Fishing Vessels, stability criteria documents, the Canadian modifications to the collision regulations, and Transport Canada's own FM4 page. Lessons describe these in general terms only.
- Stability pass criteria numbers (for example areas under the GZ curve) are deliberately not quoted. The booklet states the limits for each vessel.
- TP 2293 is the 2016 edition. Check for a newer one.

## Expert-review items
- Collision regulations details: visibility ranges, fishing lights (Rule 26), fog signals, narrow channel and traffic scheme wording, Canadian modifications.
- Definition of vessel engaged in fishing (trolling excluded) and Rule 18 priorities.
- Stability formulas, worked examples, loll correction advice, free surface calculation, FWA and rolling test constant.
- Hull integrity and freeing port wording, fire protection, inspection regime wording.
- Weather warning categories and pollution reporting contacts.
- Single-letter flag meanings, including the fishing-vessel meanings of G, Z, P and T.
- Requirement details in s.142 and the MED and training prerequisites.
