# SVMO syllabus brief (draft for docs/syllabus/svmo.md)

Match this to the format of your two existing briefs before committing. I haven't seen them.

## Ticket
Small Vessel Machinery Operator (SVMO), unrestricted, and SVMO-R (restricted, one named vessel).

## Official requirements (Transport Canada page, modified 2025-10-06; read directly)
- Qualifying service: at least 2 months, including at least 1 month of sea service as an engineer or engine-room rating on motor vessels.
- Certificates shown to the examiner: MED with respect to small passenger vessel safety, and marine basic first aid.
- Written exam on general engineering knowledge of small vessels. Then, for the unrestricted ticket, an oral exam. For the restricted ticket, a practical exam aboard the vessel.
- SVMO-R: vessel under 750 kW, limited near coastal Class 2 or sheltered waters. Either approved training on that vessel's propulsion and safety systems, or at least 10 days of engine-room sea service on that vessel or one of the same class.
- Sea service paperwork: discharge book, testimonial Form 82-0666, extra declarations when the vessel doesn't require a certified engineer.

## Exam structure (TP 14814E, 2nd ed., June 2008; read directly via a third-party copy)
- Written (SVMO-G): 60 multiple-choice questions, four options each, 2 hours (TP 2293 Chapter 33.3, March 2016 edition, read directly).
- Guide is 19 topics, each a list of discussion questions plus one sample question.
- Level: recognise technical terms, know what machinery does, and work safely around it. Not deep engineering.
- Pass mark: not stated in TP 14814E or TP 2293 Chapter 33. Confirm with Transport Canada.
- TP 2293 (2016) syllabus matches the 19 topics in TP 14814E. It adds: alarm and navigation-light circuits, cross connections in piping, carburettors and ignition systems, and recording and calculating fuel supply.
- The guide says "choose the MOST appropriate answer". Questions often have several defensible options.

## Lessons written so far
- machinery.engines.how-a-diesel-works (order 100)
- machinery.engines.start-run-and-faults (order 110)
- machinery.fuels.storage-and-contamination (order 120)
- machinery.cooling.fresh-and-raw-water (order 130)
- machinery.electrical.basics-and-meters (order 200)
- machinery.electrical.protection-and-distribution (order 210)
- machinery.electrical.batteries (order 220)
- machinery.pumps.types-and-faults (order 300)
- machinery.piping.systems-and-precautions (order 310)

## Proposed lesson map (one lesson per topic, then split large ones)
| Sec | Topic | Suggested lessons |
|---|---|---|
| 3.1 | Construction materials | 1 |
| 3.2 | Recognising fire hazards | 1 |
| 3.3 | Safety and fire equipment | 2 (fixed systems and doors; quick-closing valves, stops) |
| 3.4 | Safe working practices | 2 (WHMIS, PPE, lockout; hot work, confined space) |
| 3.5 | Pollution prevention | 1 |
| 3.6 | Pumps | 2 (types; priming and faults) |
| 3.7 | Piping | 2 (bilge, ballast, valves; water hammer, expansion) |
| 3.8 | Power transmission | 2 (shaft, thrust, stern gland; gearbox, propellers) |
| 3.9 | Steering gear | 1 |
| 3.10 | Underwater fittings | 1 |
| 3.11 | Deck machinery | 1 (overlaps seamanship deck machinery; keep separate per ticket) |
| 3.12 | Fuels | 2 (storage, sounding, transfer; contamination) |
| 3.13 | Lubricants | 1 |
| 3.14 | Cooling systems | 1 |
| 3.15 | Electricity | 3 (V, A, W, ohm and meters; protection, buses, paralleling; batteries) |
| 3.16 | Hydraulic systems | 1 |
| 3.17 | Pneumatic systems | 1 |
| 3.18 | Internal combustion engines | 4 (cycles; systems and pre-start; faults and smoke; alarms and crankcase) |
| 3.19 | Watchkeeping procedures | 2 (rounds, logs, voyage checks; emergencies) |
| | **Total** | **~31 lessons** |

## Question targets
- At least 8 questions per lesson, original wording, four options, one most-appropriate answer.
- Two mock exams, 60 questions each, weighted by the topic list (blueprint to be set once you pick weights).
- 83 questions are attached to the nine lessons written so far (questions/). 54 more are in questions-pending/ with planned lesson_ids and must stay out of the repo until those lessons exist, or lint will fail.
- Module name used: `machinery`. Ticket id used: `svmo`. Source ids used: `tp14814e`, `tp2293-2016` (add them to your sources file).

## Oral exam (SVMO-O) - syllabus from TP 2293 Ch. 33.4 (2016)
Indefinite duration. Topics: use of official language; watchkeeping procedures and routine duties; safe working practices (including precautions before entering enclosed spaces); safety and emergency procedures; preventing, controlling and fighting fire (including oil system fires); operating lifesaving appliances (abandon-ship drills, survival craft, rescue boats); fire equipment; pollution prevention; internal combustion engines; main and auxiliary machinery and controls; pumping systems; alternators, generators and control systems (including power failure and emergency power); fuels; lubricants; cooling; hydraulics; electricity; pneumatics; and basic knowledge of the Canada Shipping Act, 2001 (steering, lifesaving and fire equipment, drills, dangerous goods, oil, sewage and garbage pollution, discharge reporting, safe working, casualty reporting, crewing).
The practical exam (SVMO-P) uses the same syllabus, limited to the vessel's specific equipment.
**Discrepancy:** the 2016 chapter text says the oral and practical apply to the restricted certificate (s.151(2)). The current TC page says oral for unrestricted and practical for restricted. Treat the TC page as authoritative and confirm with an examiner.

Note the oral covers lifesaving appliances and the Canada Shipping Act, which the written syllabus does not. An oral-practice lane needs lessons on these, which overlap with your Bridge Watch and survival craft content (separate versions per ticket, as decided).

## What the certificate allows (TP 2293 Ch. 33.2, 2016 edition)
The holder may act as chief or watchkeeping engineer on a motor vessel, within these limits:
| Vessel | Sheltered waters and limited near coastal Class 2 | Same, under 6 hours | Within 5 NM of a sheltered dock |
|---|---|---|---|
| Passenger | under 750 kW | under 1500 kW (MPR 219(2) conditions) | n/a |
| Cargo | n/a | under 2000 kW (MPR 220(2) conditions) | n/a |
| Tug | n/a | under 3000 kW (MPR 221(2) conditions) | tug under 500 GRT (MPR 221(3) conditions) |
SVMO-R is valid only on the specified passenger vessel under 750 kW. Verify these limits against the current Marine Personnel Regulations before using them in lessons.

## Sources to log
| id | what | read_directly |
|---|---|---|
| tp14814e | TP 14814E, SVMO Examination Study Guide, 2nd ed. 2008 (third-party copy at dieselduck.ca) | true, but check for a newer edition |
| tc-svmo-req | TC "Small Vessel Machinery Operator requirements", modified 2025-10-06 | true |
| tp2293-2016 | TP 2293E Rev. 6 (03/2016), Chapter 33 (user-supplied PDF) | true, but 10 years old. Check for a newer edition |
| mpr-s151 | Marine Personnel Regulations s.151 | false (snippets only) |
| tp10655e | TP 10655E approved courses (11/2025) | false |

## Expert-review items
- MED course name: DVS vs MED A2 vs DPVS for the SVMO requirement.
- Whether the online fatigue-management prerequisite applies (one provider's claim, unverified).
- Whether a seafarer medical is required for the SVMO. TC's page doesn't mention it.
- Pass mark, and the oral versus practical discrepancy noted above.
- Pollution reporting contacts for BC.
- All 137 questions (83 attached, 54 pending) and the nine lessons. Specific figures to check: bilge blower time, coolant temperature range, compression ratios, cooler pressure order, crankcase wait time, battery electrolyte specific gravity and the earth-lamp description, and the relief-valve and accumulator advice.

## Cautions
- TP 14814E is from 2008 and may have been superseded. Check TC's current publications list.
- Don't copy TP wording or its five sample questions into lessons. Write original text.
- Regulations (pollution, safety) change. Verify each regulatory statement against current law before expert sign-off.
