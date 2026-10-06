# Master Limited < 60 GT: syllabus brief and writer plan

Research lane output, 2026-10-06. Branch `research/master-limited-syllabus`.

**What this is.** A sourced brief that tells content writers what a British Columbia candidate for the Transport
Canada (TC) **Master, Limited certificate for a vessel of less than 60 gross tonnage** must know to pass the
written exams and the oral, and to do the job. Section 1 sets out the certificate requirements, section 2 the
syllabus topic by topic, section 3 the proposed lessons, section 4 what is still unverified, section 5 the sources.

**What this is not.** It is not legal advice and not an official syllabus. The authority is the Marine Personnel
Regulations (MPR) and TC's TP 2293. Regulatory facts in lessons stay `needs_expert_review` until a certified master
or examiner signs off (CLAUDE.md).

## How to read the tags

| Tag | Meaning |
|---|---|
| **[C: id]** | Confirmed in that source. Regulation sources (`*-xml`) were read word for word from Justice Canada's official consolidated XML. Other sources were read through an automated fetch-and-summarise tool, so their wording still needs a human check (`read_directly: false`). |
| **[P: id]** | A provider or study-site claim, not a regulation or TC document. |
| **[I]** | Inferred, or standard navigation knowledge I did not find in a source read for this brief. The writer must find a source before using it (suggested source given where possible). |
| **[shared-bwr]** | Also needed for Bridge Watch Rating. Write it once (see 3.2). |

Source ids are defined in `content/sources/sixty-ton.json` (new, prefix `sixty-`) or in `content/sources.json` and
the other `content/sources/*.json` files (existing).

## 0. Short answers

- **Legal basis**: MPR s. 131 (requirements) and s. 212 Table 1 item 16 (what the certificate lets you do). [C: sixty-mpr-sor-2007-115-xml]
- **What it allows**: master of any vessel under 60 GT *of a type, tonnage, area and period of operation specified on the certificate*, on **near coastal Class 2** or **sheltered waters** voyages only. Not STCW-endorsed. [C: sixty-mpr-sor-2007-115-xml]
- **Requirements**: age 18 at issue; **2 months** of sea service doing deck duties on vessels of at least the same GT and voyage type (or 1 month in an approved on-board training program); **MED** suited to the vessel (passenger or not); a **radio operator certificate** if the vessel has a VHF; **Marine Basic First Aid**; **medical certificate only if the vessel carries passengers**; and pass the exams TP 2293 sets. [C: sixty-mpr-sor-2007-115-xml]
- **Exams in practice (BC)**: **Chartwork and Pilotage Level 1 (CP1)**, **Navigation Safety Level 1 (NS1)** and an in-person **oral** (general seamanship / master's knowledge), each by TC exam or (for CP1/NS1) an approved course. [P: sixty-wmi-master-limited-under-60, sixty-saferoceans-course-info, sixty-nic-cp1, sixty-saferoceans-cp1]
- **CP1 conflict**: resolved *in practice* in favour of "CP1 is required" (four BC providers agree; the conflicting study site does not discuss the under-60 certificate at all), but **not verified against TP 2293**, which I could not read. See 1.7.
- **SEN Limited is not in MPR s. 131** (it is in s. 130, for 60 GT or more), despite one provider listing it. See 1.11.

---

## 1. Certificate requirements

### 1.1 What the certificate is

- Master, Limited is certificate 100(h) in the MPR list. MPR splits it into s. 130 (60 GT or more) and **s. 131 (less than 60 GT)**. [C: sixty-mpr-sor-2007-115-xml]
- MPR s. 212 Table 1 item 16: the holder may act as **"Master, on any vessel of less than 60 gross tonnage of a type, tonnage, area and period of operation specified on the certificate"** in the near coastal Class 2 and sheltered waters columns; the unlimited and near coastal Class 1 columns are N/A. [C: sixty-mpr-sor-2007-115-xml]
- A Master, Limited certificate is **not endorsed as meeting STCW** (s. 120(2)(m)), so it is a domestic certificate only. [C: sixty-mpr-sor-2007-115-xml]
- Voyage definitions: **near coastal Class 2** = always within **25 nm of shore** (waters contiguous to Canada, the US except Hawaii, or St Pierre and Miquelon) **and within 100 nm of a place of refuge**; **sheltered waters** = listed waters (BC list in Schedule 1 Part 6, e.g. Vancouver Harbour east of Point Atkinson-Point Grey, Fraser River, Victoria/Esquimalt inside Trial Islands-Albert Head, Nanaimo Harbour, Strait of Georgia east of 123°22.666′W, Howe Sound 1 June-30 September, Gulf Islands passages, Sooke, Comox Harbour 1 July-31 October), or lakes/rivers where you cannot be more than 2.5 nm from shore. [C: sixty-vscr-sor-2021-135-xml]
- Providers describe it as a ticket for "a single vessel, or a specific type of vessel", with restrictions the examiner may add. [P: sixty-saferoceans-course-info]
- Typical BC jobs (inferred from the limits): water taxis, small passenger/tour boats, crew boats, small workboats and tugs, aquaculture support, research and survey launches. [I]
- Same certificate scope appears for air cushion vessels under 10 000 kg and wing-in-ground craft (MPR ss. 256-257); not relevant for most candidates. [C: sixty-mpr-sor-2007-115-xml]

### 1.2 Eligibility and application

- Be **at least 18 on the day the certificate is received**, and have **started the qualifying sea service at 16 or older** (MPR s. 110(1)). [C: sixty-mpr-sor-2007-115-xml]
- Give the examiner, **at least two weeks before the first exam**: signed application (Schedule 2), birth certificate or equivalent, discharge books/certificates, sea service **testimonials** (Schedule 4 or 5 content), other certificates held (s. 110(2)). [C: sixty-mpr-sor-2007-115-xml]
- An application stays valid **1 year** from receipt (s. 110(3)). Fees are paid each time an exam is taken (s. 110(5)). [C: sixty-mpr-sor-2007-115-xml]
- Before issue: proof of being a **qualified person** (MPR s. 111) and, for passenger vessels, a medical certificate. A qualified person is a **Canadian citizen or permanent resident** (CSA 2001 s. 2). [C: sixty-mpr-sor-2007-115-xml, sixty-csa-2001-xml]
- A TC **Candidate Document Number (CDN)** is needed for TC services; TC's stability e-course page requires one, and a BC provider says one is needed before a course certificate can be issued. [C: sixty-tc-stability-training] (the provider claim is from quicknav.ca, not registered)

### 1.3 Sea service

- MPR s. 131 item 1: while **performing deck duties** on one or more vessels of a **gross tonnage at least equal** to that of the vessel the certificate is for, on voyages corresponding to that vessel's permitted voyages: **at least 2 months**, or **at least 1 month from an approved program of on-board training**. [C: sixty-mpr-sor-2007-115-xml]
- Counting (s. 115): **8 hours = 1 day**; other watch lengths are prorated up to a maximum of 12 hours per calendar day (**a 12-hour watch = 1.5 days**); **months = credited days ÷ 30**. So 2 months = 60 credited days = 480 hours of 8-hour days, or 40 calendar days of 12-hour watches. [C: sixty-mpr-sor-2007-115-xml] (worked arithmetic [I])
- Service as a junior officer to the mate in charge of the watch counts at two thirds (s. 117) (rarely relevant). Service on a vessel the applicant deserted does not count (s. 118). [C: sixty-mpr-sor-2007-115-xml]
- Other kinds of service are assessed under TP 2293 Chapter 3 (s. 116). [C: sixty-mpr-sor-2007-115-xml]
- Providers: "60 days of commercial seatime on your intended vessel (or similar)", "signed off on by the certified master". [P: sixty-saferoceans-course-info]

### 1.4 Training certificates (MPR s. 131 item 2)

| Item | Requirement | Tag |
|---|---|---|
| MED, passenger-carrying vessel | one of: MED small passenger vessel safety; or MED basic safety + MED small seasonal passenger vessel safety (certificated personnel); or MED small seasonal passenger vessel safety (certificated) + (non-certificated) | [C: sixty-mpr-sor-2007-115-xml] |
| MED, not passenger-carrying | MED basic safety, or a **practical examination on MED using the vessel's emergency equipment** | [C: sixty-mpr-sor-2007-115-xml] |
| Radio | "an appropriate radio operator certificate issued under the Radiocommunication Act **if the vessel is equipped with a VHF radiotelephone installation**" | [C: sixty-mpr-sor-2007-115-xml] |
| First aid | **Marine basic first aid** | [C: sixty-mpr-sor-2007-115-xml] |

- Course currency (s. 103): an **MED** course must have been completed **within 5 years before the application**, or be backed by a refresher in that period, or by 12 months of deck service in the last 5 years, or 3 months in the last 12 months (with a drills testimonial). After the certificate is issued, most course passes are valid for life, **except** MED/SEN, **first aid**, tanker and **passenger-vessel** courses. [C: sixty-mpr-sor-2007-115-xml]
- Providers translate the MED line into the current course name **MED Domestic Vessel Safety (DVS)**, which replaced MED A1 and A2 (26 hours, 3 days). [P: sixty-wmi-master-limited-under-60, sixty-sstl-med-dvs]
- Radio: the regulation says only "appropriate". ISED's RIC-25 says the **ROC-MC** is the certificate for operators on **compulsorily fitted commercial vessels in Sea Area A1**; NSR 2020 s. 204 makes VHF-DSC compulsory for vessels **over 8 m, carrying passengers, or towing** (within A1/VHF coverage or more than 5 nm offshore). So most Master, Limited vessels are compulsorily fitted and the ROC-MC is the safe answer. [C: sixty-ised-ric25-roc-mc, sixty-nsr-2020-xml] (the conclusion is [I]). Providers disagree: WMI lists ROC-MC; Safer Oceans and Georgian say ROC-M or ROC-MC depending on area. [P: sixty-wmi-master-limited-under-60, sixty-saferoceans-roc-m, sixty-georgian-mari-0257]

### 1.5 Medical

- A medical (or provisional medical) certificate is required **unless** the s. 131 certificate is for a vessel that is **not passenger-carrying** (MPR s. 111(b)). [C: sixty-mpr-sor-2007-115-xml]
- Provisional medical certificates last up to 6 months (1 year if renewed) until TC issues the certificate (s. 275). Rights under a certificate are subject to limits written on the latest medical (s. 120(3)). [C: sixty-mpr-sor-2007-115-xml]
- Safer Oceans says a valid marine medical is required without the passenger condition. Treat the MPR as correct. [P: sixty-saferoceans-course-info]

### 1.6 Examinations

MPR s. 131 item 3: "Examinations on subject-matter appropriate to the area of operation and the type and gross
tonnage of the vessel to which the certificate relates as set out in TP 2293." [C: sixty-mpr-sor-2007-115-xml]

| Exam | Format | Pass mark | Status |
|---|---|---|---|
| **NS1 Navigation Safety Level 1** | **100 multiple-choice questions, 2 hours**, 1 mark each | **70 %** | Format [P: sixty-holland-ns1]; pass mark [C: sixty-tp2293-2019-draft] (draft) |
| **CP1 Chartwork and Pilotage Level 1** | **3 hours: 10 multiple-choice (3 marks each) + 5 practical chartwork questions (5-15 marks each)**; non-phone calculator; chart work with parallel rules/dividers | **70 %** | Format [P: sixty-holland-cp1]; pass mark [C: sixty-tp2293-2019-draft] (draft) |
| **Oral (general seamanship / Master, Limited oral-practical)** | In person with a TC examiner, by appointment; taken last, after all other requirements; may be on board the vessel with manoeuvres and drills | Examiner judgement | [P: sixty-nauticalmind-master-limited-practical, sixty-saferoceans-course-info] |

- For CP1 and NS1, the 2019 TP 2293 draft lets competence be shown either by an **approved training course** or by **70 % on the TC exam**; NIC students write the TC CP1 exam on the last course day. [C: sixty-tp2293-2019-draft] [P: sixty-nic-cp1]
- Fees (MPR s. 121): **written exam $27.50**; **oral or practical exam for a limited certificate $27.50**; replacement/issue fees in s. 122. [C: sixty-mpr-sor-2007-115-xml]
- A **passing grade is valid for 5 years** after the exam, and for life once the certificate is obtained (s. 113). Not following TP 2293 exam procedure is an automatic fail (s. 112). [C: sixty-mpr-sor-2007-115-xml]
- Typical BC course hours (guide to depth): CP1 90 h (12 days), NS1 60 h (6 days). [P: sixty-camosun-fm4-courses, sixty-saferoceans-course-info]

### 1.7 The CP1 question

**Conflict:** a study site says CP1 is for the 150 GT Mate and the over-60 GT Master; BC providers list it for
Master, Limited under 60 GT.

**Evidence found:**

- MPR s. 131 names no exam subjects; it defers to TP 2293. [C: sixty-mpr-sor-2007-115-xml]
- The study site (The Nautical Mind) lists CP1 for "the 150 GT Mate and the Over 60 GT Master" but **has no page for the under-60 certificate at all**, so it does not actually say CP1 is *not* required for it. [P: sixty-nauticalmind-cp1]
- **Four BC providers** list CP1 (and NS1) for Master, Limited under 60 GT: Western Maritime Institute, Safer Ocean Systems (CP1 page and course-information page), North Island College ("Master Limited (60 T)"). BCIT's Master Limited courses include CP1-level chartwork and NS-level collision regulations. [P: sixty-wmi-master-limited-under-60, sixty-saferoceans-cp1, sixty-saferoceans-course-info, sixty-nic-cp1, sixty-bcit-naut-1475, sixty-bcit-naut-1480]
- Georgian College (Ontario) lists prerequisites for its under-60 prep course without CP1/NS1, but that page lists course prerequisites, not exams. [P: sixty-georgian-mari-0257]
- The TP 2293 text that decides it (2007 Rev. 5 Chapter 12; 2021 Rev. 8 Master, Limited chapter; 2019 draft section 3.48) **was not reachable** with the tools available. [C: sixty-tp2293-2021-catalogue] (record only)

**Conclusion:** Treat **CP1 + NS1 + oral** as the standard BC route and build all three. **Unresolved points for a
human:** (a) whether TP 2293 makes CP1 conditional on the area of operation (for example, a sheltered-waters-only
certificate may need less chartwork), since s. 131 says the exams must be "appropriate to the area of operation";
(b) whether a challenge exam is accepted in place of the approved course everywhere. Ask a TC Marine Safety office
(Vancouver or Victoria) or read TP 2293 Rev. 8.

### 1.8 The oral

What candidates are told the oral covers (no TC text read; all provider/study-site claims):

- "May cover any of the topics or theory for the above courses, but can also cover any other topic" the examiner deems necessary; study the Canada Shipping Act, stability, emergency procedures, vessel systems and navigation regulations. [P: sixty-saferoceans-course-info]
- Safety procedures; **emergencies: fire, flooding, man overboard, abandonment, collision, grounding**; knowledge of **your ship's systems** (electrical, plumbing, propulsion); **shiphandling**; **communications** (on board, ship to ship, distress); **pollution prevention**; **passenger safety**; **cargo safety**. May be held on board, with the candidate manoeuvring and running drills. [P: sixty-nauticalmind-master-limited-practical]
- Prep courses add collision regulations, bridge resource management, watchkeeping and leadership scenarios. [P: sixty-saferoceans-oral-prep, sixty-wmi-ns1-orals-prep]
- A study site points to TP 2293 (2007) **section 12.11, pages 169-179** for the official oral syllabus. [P: sixty-nauticalmind-master-limited-practical] (not read)

The app's oral tester should therefore draw on every section of part 2 below, weighted towards emergencies, the
master's legal duties, stability, COLREGs and the candidate's own vessel and area.

### 1.9 Limits written on the certificate

- Vessel **type**, **tonnage**, **area** and **period of operation** (MPR s. 212 Table 1 item 16). [C: sixty-mpr-sor-2007-115-xml]
- Examiner may add restrictions based on experience and answers. [P: sixty-saferoceans-course-info]
- Medical limitations, if any, also bind (s. 120(3)). [C: sixty-mpr-sor-2007-115-xml]
- Deck watch: the person in charge of the deck watch must also hold the appropriate radio certificate if VHF is fitted (s. 216(2)(a)(ii)); vessels of **5 GT or more** need **an additional person** on the deck watch, with exceptions (e.g. up to 500 GT with all-round view, in a harbour, under 5 nm, daylight, good visibility) (s. 216(2)(b), (3)); the master counts as part of the deck watch on vessels up to 1 000 GT (s. 215(2)). [C: sixty-mpr-sor-2007-115-xml]

### 1.10 Validity and renewal

- Every Canadian maritime document is valid for **the period the Minister specifies** (CSA 2001 s. 17). The commonly quoted **5-year** period is not stated in the texts read. [C: sixty-csa-2001-xml] [I for 5 years]
- Renewal of a 100(h) certificate (MPR s. 106), one of:
  - (a) sea service as master or mate in charge of the deck watch: **12 months in the last 5 years**, or **3 months in the last 12 months**, which for a Master, Limited must be **on the vessel(s) and in the area** the certificate covers (s. 106(2)-(3));
  - (b) a **refresher MED** course in the last 5 years **plus a practical and oral exam on general seamanship** for the vessel (s. 106(1)(b)(i));
  - (c) 24 months in listed shore marine jobs in the last 5 years, or a prorated mix (s. 106(2)(c)-(d)).
  [C: sixty-mpr-sor-2007-115-xml]

### 1.11 Things candidates confuse

| Item | Status for Master, Limited < 60 GT | Tag |
|---|---|---|
| **SEN Limited** (radar simulator course) | **Not in s. 131.** Required by s. 130 (60 GT or more) if radar fitted. Georgian lists it "if the vessel is equipped with radar" for under 60. Radar still appears in BCIT's course and may come up in the oral. | [C: sixty-mpr-sor-2007-115-xml] [P: sixty-georgian-mari-0257] |
| **SVOP** | Not required for the certificate; Safer Oceans says needed only if you also run small tenders carrying passengers. | [P: sixty-saferoceans-course-info] |
| **SDV-BS** (formerly MED A3) | A 1-day MED for small domestic vessels; providers do not present it as meeting s. 131. | [P: sixty-saferoceans-course-info] |
| **Stability e-course** (TC/CCOHS, free with CDN) | Recommended; TC names Limited Master holders among its audience; not a s. 131 requirement. | [C: sixty-tc-stability-training] |
| **ROC-M vs ROC-MC** | See 1.4. | |
| **Medical** | Only if passenger-carrying (1.5). | [C: sixty-mpr-sor-2007-115-xml] |

---

## 2. Syllabus by exam and subject

Each item: topic, then tag. "Exam" says where it is most likely examined: **CP1**, **NS1**, **Oral**.

### 2.1 Chartwork and pilotage (CP1)

Official competence (2019 draft TP 2293 s. 3.4): *plan and conduct a coastal passage and determine position*:
principles of construction and use of chart types; thorough knowledge and use of publications; position by
landmarks, by aids to navigation (lighthouses, beacons, buoys) and by dead reckoning allowing for wind, tides,
currents and estimated speed; keeping a log book and a record of compass errors. [C: sixty-tp2293-2019-draft]

BC provider checklist (BCIT NAUT 1475 outcomes; Safer Oceans CP1 outline): chart terms and abbreviations;
projection effects; Canadian aids; plot latitude/longitude; bearing and range; course and distance; magnetic
compass construction; variation and deviation; deviation card; compass error by transit; magnetic vs gyro;
time-speed-distance; laying courses; DR and EP; tide and current predictions; set, rate and drift; leeway;
course to steer; course and speed made good; fuel for a coastal passage; fixes by range, bearing and depth;
electronic aids. [P: sixty-bcit-naut-1475, sixty-saferoceans-course-info]

| Topic | Sub-topics | Tag |
|---|---|---|
| Charts and projections | Mercator (rhumb line straight, latitude scale expands), gnomonic (great circle straight), plans; scale (large scale = more detail); chart datum for depths and heights; title block, notes, source diagram, edition and correction record | [C: sixty-tp2293-2019-draft] topic; details [I] (Bowditch) |
| Lat/long and distance | Graticule, plotting a position, 1′ of latitude = 1 nautical mile, measure distance on the latitude scale at the same latitude | [C: sixty-bcit-naut-1475] topic; rule [I] |
| Chart symbols | Canadian Chart 1 sections; depths, drying heights, dangers, lights, buoys, bottom types, cables, anchorages, restricted areas | [C: sixty-chs-chart1] [P: sixty-nauticalmind-cp1] |
| Compass | Magnetic compass construction; steering vs standard compass (NSR s. 106); gyro comparison; deviation table near the compass (NSR s. 106(4)); means to correct to true (s. 106(5)) | [C: sixty-nsr-2020-xml, sixty-bcit-naut-1475] |
| Variation, deviation, compass error | TVMDC chain, east/west sign rules, annual change of variation from the rose, deviation card by heading, compass error = var + dev | [C: sixty-bowditch-2019-compass-conversions, chs-pac200-sailing-directions] |
| Compass error by transit | True bearing of a charted transit vs compass bearing; deviation = error - variation | [C: sixty-bcit-naut-1475] topic; method [I] |
| Speed, time, distance | D = S × T; 6-minute rule | [C: sixty-bcit-naut-1475] topic; formula [I] |
| DR and EP | DR from course/speed through water; EP adds current and leeway | [C: sixty-tp2293-2019-draft, sixty-bcit-naut-1475] |
| Fixes | Cross bearings (angle of cut), ranges (radar), bearing + range, transits, depth contours, cocked hat | [P: sixty-saferoceans-course-info] [I] |
| Running fix, doubling the angle, four-point bearing | Transferred LOP; doubled angle (run = distance off); 45°/90° (run = distance abeam) | [C: sixty-sailingissues-fixes] (secondary) |
| Distance off lights | Dipping/rising range ≈ 2.08 (√H + √h) nm with heights in metres; nominal, luminous and geographic range | [C: sixty-sailingissues-fixes] formula; ranges [I] |
| Clearing (danger) bearings | NMT/NLT bearings on a charted mark | [I] |
| Set, rate, drift | Current vector over time | [C: sixty-bcit-naut-1475] |
| Course to steer and CMG/SMG | Vector triangle; leeway applied | [C: sixty-bcit-naut-1475] |
| ETA and fuel | ETA from SOG; fuel = rate × time + reserve | [C: sixty-bcit-naut-1475] topic; reserve rule [I] |
| Tides in chartwork | Height at a time, secondary ports, depth under keel, vertical clearance, tidal stream from tables/atlas; Nautical Mind says tide calculations have not appeared on CP1 "so far" | [P: sixty-nauticalmind-cp1] [C: chs-tide-current-tables-vol5] |
| Voyage planning | NSR 2020 s. 144 (plan using IMO A.893(21); route accounts for routing systems, sea room, hazards, weather, environmental measures; display route and plot/monitor position) | [C: sixty-nsr-2020-xml] |
| Log book and records | Record of compass errors; navigational record | [C: sixty-tp2293-2019-draft] |

### 2.2 Canadian publications (CP1 and oral)

| Publication | Key points | Tag |
|---|---|---|
| Reference catalogue and charts (CHS) | Most recent versions, **largest scale**, for each area to be navigated (NSR s. 142(1)(a)) | [C: sixty-nsr-2020-xml] |
| Notices to Mariners, annual edition and monthly | Carry the annual edition; charts and publications must be corrected from NOTMAR or navigational warnings before use (s. 142(1)(b), s. 146) | [C: sixty-nsr-2020-xml] |
| Canadian Sailing Directions (CHS), e.g. PAC 200 General Information, Pacific Coast | Required in Canadian waters; PAC 200 covers MCTS, pilotage, dangerous passes, weather | [C: sixty-nsr-2020-xml, chs-pac200-sailing-directions] |
| Canadian Tide and Current Tables (CHS, BC Volumes 5-7) | Required; reference/secondary ports and current stations | [C: sixty-nsr-2020-xml, chs-tide-current-tables-vol5] |
| List of Lights, Buoys and Fog Signals (CCG) | Required | [C: sixty-nsr-2020-xml] |
| Radio Aids to Marine Navigation (CCG) | Required if the vessel must carry radio; also at the radio position (s. 212) | [C: sixty-nsr-2020-xml] |
| Illustrated table of life-saving signals | Vessels other than small pleasure craft outside sheltered waters (s. 142(1)(e)) | [C: sixty-nsr-2020-xml] |
| Canadian Chart 1 | Symbols, abbreviations, terms | [C: sixty-chs-chart1] |
| Canadian Aids to Navigation System | Buoyage | [C: sixty-ccg-atons-2023] |
| **Exception under 100 GT** | s. 142(1) does not apply to a vessel **under 100 GT** if the person in charge of navigation has **sufficient knowledge** of charted routes, lights/buoys/marks, hazards, and prevailing conditions (tides, currents, ice, weather) (s. 142(3)). Very likely oral question. | [C: sixty-nsr-2020-xml] |
| Electronic charts | Count as charts only on an ECDIS meeting s. 143 (ENC, S-52, back-up). An ECS/plotter is not a legal chart substitute. | [C: sixty-nsr-2020-xml] (ECS point [I]) |

### 2.3 Buoyage and lights (CP1, NS1, oral) [shared-bwr]

- IALA **Region B**: "red right returning" from seaward; upstream direction = from seaward toward the headwaters or into harbour. [C: sixty-ccg-atons-2023]
- Port hand: green, flat-topped/cylindrical, odd numbers, green Fl 4s or Q; starboard hand: red, conical, even numbers, red Fl 4s or Q; numbers increase upstream. [C: sixty-ccg-atons-2023]
- Fairway (safe water): red/white vertical stripes, red sphere topmark, white Mo(A) 6s or LFl 10s, keep it to port. Bifurcation: band of the other colour, Fl(2+1). [C: sixty-ccg-atons-2023]
- Cardinals: N black over yellow, cones up, Q or VQ continuous; E black-yellow-black, cones base to base, Q(3) 10s / VQ(3) 5s; S yellow over black, cones down, Q(6)+LFl 15s / VQ(6)+LFl 10s; W yellow-black-yellow, cones point to point, Q(9) 15s / VQ(9) 10s; flashes match the clock face. [C: sixty-ccg-atons-2023]
- Isolated danger: black with red band, two black spheres, white Fl(2). Special marks yellow, Fl 4s; ODAS Fl(5)Y 20s; information/hazard/control/keep-out marks white with orange symbols. [C: sixty-ccg-atons-2023]
- Light characteristics: F, Iso, Fl, LFl (2 s flash), Fl(n), Oc, Q (50-79/min), VQ (80-159/min). [C: sixty-ccg-atons-2023]
- Fog signals are being withdrawn; buoy positions are approximate; do not rely solely on buoys. [C: sixty-ccg-atons-2023]
- Report damaged, missing, out of position or malfunctioning aids **without delay** to MCTS (CSA s. 129). [C: sixty-csa-2001-xml]

### 2.4 Navigation safety (NS1)

Official competence (2019 draft s. 3.10): thorough knowledge of the content, application and intent of the
**COLREGs including Canadian modifications** and of the **principles to be observed in keeping a navigational
watch**. [C: sixty-tp2293-2019-draft] Provider description: Collision Regulations, **STCW Code Chapter VIII**,
light/shape/sound signals, **bridge teamwork**. [P: sixty-holland-ns1]

| Topic | Sub-topics | Tag |
|---|---|---|
| COLREGs Parts A-E, Annexes | Already written (Rules 1-38, Annex IV, drills) | existing `colregs` lessons |
| Canadian Part F (Rules 39-46) | Additional Canadian rules | [C: lights-crc1416-xml] (not yet a lesson) |
| Annex I positioning, Annex II fishing signals, Annex III sound appliances | Partly in Rule 20-31 lessons | existing sources |
| STCW A-VIII/2 Part 2 voyage planning; Part 3-1 navigational watch | Watch arrangements; taking over (not during a manoeuvre); performing the watch; lookout purposes; sole lookout (daylight only, after assessment, help available); calling the master (list); restricted visibility; pilot on board; anchor watch | [C: sixty-amsa-242-watchkeeping]; applied in Canada by MPR ss. 213, 244, 252 [C: sixty-mpr-sor-2007-115-xml] |
| Bridge teamwork / BRM | Communication, situational awareness, fatigue, leadership (SEN-L competence list) | [C: sixty-tp2293-2019-draft] [P: sixty-holland-ns1] |
| Deck watch composition (Canada) | MPR ss. 214-216 | [C: sixty-mpr-sor-2007-115-xml] |
| Navigation safety calls and listening watch | NSR ss. 252-253, Schedule 4 | [C: sixty-nsr-2020-xml] |
| Fitness for duty, rest | MPR ss. 320-322 | [C: sixty-mpr-sor-2007-115-xml] |

### 2.5 Canadian regulations (oral; parts in NS1)

| Topic | Key content | Tag |
|---|---|---|
| CSA 2001 structure and roles | Authorized representative: vessel meets regulations, **develops safe operation and emergency procedures**, ensures crew and passenger **safety training** (s. 106). Master: obtain documents before a voyage (s. 107), **all reasonable steps for safety** (s. 109), **never more persons than certified** and **load lines not submerged** (s. 110), notify dangers (s. 112). Crew: report hazards, obey lawful directions (s. 113). Passengers must comply (s. 115). | [C: sixty-csa-2001-xml] |
| Distress and assistance | Proceed with all speed to persons in distress and inform them; if not proceeding, **enter the reason in the official log** (s. 131); assist anyone found at sea in danger (s. 132); after a **collision**, assist, stand by and exchange names/representative (s. 148) | [C: sixty-csa-2001-xml] |
| Dangers to report | Dangerous ice or derelict, tropical storm, **Beaufort 10+ without a warning**, severe superstructure icing with gale (s. 112) | [C: sixty-csa-2001-xml] |
| Marine Personnel Regulations | Certificates (part 1); crew MED (s. 205(3)); **first aid person** with marine basic or advanced first aid on NC2/sheltered (s. 205(9)); **familiarization before duties, training record** (s. 206); safe manning (s. 211); deck watch (ss. 213-216); hours of rest (s. 320: **6 consecutive hours in 24, 16 hours in 48**, max 18 h between rest periods, for sheltered and NC voyages in Canadian waters) | [C: sixty-mpr-sor-2007-115-xml] |
| Small Vessel Regulations Part 4 (≤ 15 GT, ≤ 12 passengers) | Passenger briefing contents and lifejacket demo; report POB ashore; **cold water under 15 °C** measures; notice before first service; equipment tables by length (lifebuoys, heaving line 15 m, flares 3/6/12, life raft unless ≤ 8.5 m / sheltered / ≤ 2 nm in river or lake, anchors 15/30/50 m, compasses, extinguishers 1A:5B:C to 2A:20B:C, detectors 84 dB); emergency procedures practised | [C: sixty-svr-sor-2010-91-xml] |
| Small Vessel Regulations Part 5 (workboats ≤ 15 GT), Part 6, Part 7 | Similar structure; stability "adequate to safely carry out intended operations" | [C: sixty-svr-sor-2010-91-xml] (skimmed) |
| Inspection regime | Non-Convention vessels **over 15 GT**, or **15 GT or less carrying more than 12 passengers**, fall under the inspection-certificate division of the Vessel Safety Certificates Regulations | [C: sixty-vscr-sor-2021-135-xml] |
| Navigation Safety Regulations 2020 | Equipment by size; AIS; charts/publications; voyage planning; radio Part 2 | [C: sixty-nsr-2020-xml] |
| Pollution | Oily mixtures in Canadian (Section I) waters only through 15 ppm equipment en route (so small vessels without it keep oily bilge water aboard); sewage rules; garbage rules | [C: sixty-vpdcr-sor-2012-69-xml] |
| Occurrence reporting | TSB reportable list; quickest means; rest within 30 days; MCTS report counts | [C: sixty-tsb-regs-sor-2014-37-xml] |
| VTS | New VTS Zones Regulations in force **2026-03-31**; Western zones **Victoria** and **Prince Rupert**; who must report; sailing plan **15 min** before entering/leaving berth; calling-in points; final and deviation reports; reportable events | [C: sixty-vts-zones-sor-2025-275-xml, sixty-ccg-ramn-part3-2025] |
| Pilotage (BC) | Compulsory pilotage areas apply to larger vessels (PAC 200 says over 350 GT); know it exists | [C: chs-pac200-sailing-directions] (needs PPA source) |
| Fire safety and life-saving rules for vessels over 15 GT and under 24 m | Life Saving Equipment Regulations (C.R.C., c. 1436) and Vessel Fire Safety Regulations; VCER applies mainly at 24 m+ | [C: sixty-vcer-sor-2023-257-xml] for VCER scope; others **not read** |
| Collision Regulations Canadian specifics | Existing lessons | existing |

### 2.6 Emergencies and safety equipment (oral; MED background)

| Topic | Sub-topics | Tag |
|---|---|---|
| Fire | Fire triangle; extinguisher classes and ratings (SVR tables); engine-space discharge port and CO2 1.2 kg/m³ (SVR 415-416); boundary cooling; shut fuel/ventilation; MAYDAY/PAN | ratings [C: sixty-svr-sor-2010-91-xml]; procedure [I] (needs TC or MED source) |
| Flooding | Find source, pumps, damage control, stability effect, beach or call for help | [I] |
| Man overboard | Alarm, lifebuoy, spotter, MOB button, recovery turn (Williamson/Anderson), recovery method, radio; SVR reboarding device | reboarding [C: sixty-svr-sor-2010-91-xml]; rest [I] |
| Grounding | Assess, sound tanks, check hull, do not back off blindly, tide state, report to TSB/MCTS | TSB [C: sixty-tsb-regs-sor-2014-37-xml]; rest [I] |
| Collision | Duties (CSA 148), damage control, report | [C: sixty-csa-2001-xml] |
| Abandon ship and cold water | Liferaft hydrostatic release (about 4 m), PFDs, 15 °C rule, cold shock, HELP/huddle | [C: tp-14070, sixty-svr-sor-2010-91-xml]; cold-water physiology [I] |
| Distress signals and alerting | Annex IV (existing), flares by length, EPIRB/PLB/DSC | [C: sixty-svr-sor-2010-91-xml, sixty-nsr-2020-xml] |
| Drills and procedures | Owner/operator procedures and crew practice (SVR 420); familiarization and records (MPR 206); drill records (TP 14070) | [C: sixty-svr-sor-2010-91-xml, sixty-mpr-sor-2007-115-xml, tp-14070] |
| Pre-departure checks | Checklists; gasoline engine blower **4 minutes** | [C: tp-14070] |
| Passenger safety | Briefing, POB report, crowd control, stability effects of passenger movement | [C: sixty-svr-sor-2010-91-xml] |

### 2.7 Stability (oral; no written exam for this certificate)

SCS1 is a Fishing Master IV exam, not listed for Master, Limited < 60 GT. [P: sixty-camosun-fm4-courses]
Stability still features in provider oral prep and the oral tester plan. [P: sixty-saferoceans-course-info]

| Topic | Sub-topics | Tag |
|---|---|---|
| Terms | Displacement, draught, freeboard, lightship, deadweight; list vs heel vs loll | [C: sixty-fao-ftp517-stability] |
| G, B, M | Centre of gravity, centre of buoyancy, transverse metacentre; stable, unstable, neutral equilibrium | [C: sixty-fao-ftp517-stability] |
| GM and GZ | GM = KM − KG; GZ ≈ GM sin θ at small angles; stiff vs tender; GZ curve, range of stability, dynamic stability | concepts [C: sixty-fao-ftp517-stability]; formulas [I] |
| Weight changes | G moves toward added weight, away from removed; adding high or removing low raises G; suspended weights act from the point of suspension | suspended [C: sixty-fao-ftp517-stability]; rest [I] |
| Free surface | Slack tanks and water on deck virtually raise G; keep tanks pressed up or empty; freeing ports | [C: sixty-fao-ftp517-stability, tp-14070] |
| Watertight integrity, freeboard | Watertight vs weathertight; closures; freeing ports clear | [C: sixty-fao-ftp517-stability] |
| Icing | Raises G; remove from high up first; report severe icing (CSA 112); fishing vessels must not operate in freezing-spray warning areas unless assessed (FVSR) | [C: sixty-fao-ftp517-stability, sixty-csa-2001-xml, sixty-fvsr-crc-1486-xml] |
| Rolling period test | Roll period (s) < beam (m) suggests enough stability; ≤ 1.2 × beam when light | [C: sixty-fao-ftp517-stability] |
| Following and quartering seas | Broaching, loss of stability on a crest | [C: sixty-fao-ftp517-stability] |
| Passengers and loading | Passenger movement and grouping affects stability (SVR briefing item) | [C: sixty-svr-sor-2010-91-xml] |
| Requirement | Non-pleasure vessels must have stability adequate for intended operations (SVR Part 7); owner must show it on request | [C: sixty-svr-sor-2010-91-xml] |
| Criteria | 2008 IS Code general criteria (areas under GZ, minimum GZ, angle of max GZ, minimum GM), as modified by TP 7301 (Dec 2023) | reference [C: sixty-vcer-sor-2023-257-xml]; numbers [I] |
| Towing stability and girding | Tow plan, towline, release | [C: sixty-tc-ssb-16-2020-girding, sixty-tc-tp15491-tug-guidance] |

### 2.8 Seamanship and ship knowledge (oral)

| Topic | Sub-topics | Tag |
|---|---|---|
| Hull and construction terms | Keel, frames, bulkheads (collision bulkhead), deck, superstructure, freeboard deck, sheer, camber, hull materials (steel, aluminium, FRP, wood) | [I] (need a source) |
| Machinery and systems | Diesel/gasoline engines, cooling, fuel, bilge, electrical (batteries, shore power), steering; refuelling safety | [P: sixty-nauticalmind-master-limited-practical]; content [I] |
| Ropes and knots | Fibre types, care, strength; bowline, clove hitch, round turn and two half hitches, sheet bend, cleat hitch [shared-bwr] | [I] (knots guide is a later module) |
| Mooring and docking | Bow/stern lines, springs, breasts; wind/current effects; fenders | [C: sixty-bcit-naut-1480] topic; content [I] |
| Anchoring | Choosing an anchorage, scope, ground tackle, checking for dragging, anchor watch | anchor watch [C: sixty-amsa-242-watchkeeping]; SVR rode lengths [C: sixty-svr-sor-2010-91-xml]; scope [I] |
| Shiphandling | Prop walk, pivot point, twin screws, stopping distance, wind and current, narrow channels | [C: sixty-bcit-naut-1480] topic; content [I] |
| Heavy weather | Speed and heading, securing, stability, following seas | [C: sixty-fao-ftp517-stability] partly; [I] |
| Towing | Girding, tow plan, emergency release, towing lights (Rule 24) | [C: sixty-tc-ssb-16-2020-girding] |
| Searchlights, lead line, signal flags | NSR thresholds (towboats over 5 GT: 2 searchlights; lead line at 20 m) | [C: sixty-nsr-2020-xml] |

### 2.9 Radio and GMDSS (oral; ROC course)

| Topic | Key content | Tag |
|---|---|---|
| Carriage | VHF-DSC for vessels **over 8 m**, **carrying passengers**, or **towboats** in A1/VHF coverage or more than 5 nm off the coast; exception: 6 or fewer passengers and not over 8 m with a DSC handheld (NSR s. 204) | [C: sixty-nsr-2020-xml] |
| Sea areas | A1 (VHF DSC coast station coverage), A2 (MF), A3 (satellite), A4 (rest) | [C: sixty-nsr-2020-xml] |
| EPIRB | Float-free EPIRB for NC1/unlimited, or **over 12 m** on NC2/inland; 12 m or less on NC2: float-free or manual EPIRB, PLB, or (under 8 m in A1 only) waterproof DSC handheld; register in the Canadian Beacon Registry (s. 228) | [C: sixty-nsr-2020-xml] |
| Watch and calls | Continuous listening watch from **15 min before underway** (s. 252); Ch 16 outside traffic zones, VTS channel inside (Sched. 4); navigation safety call circumstances, **content in order: identity, location, intended action, course and speed, status**, **1 minute max**, departure calls **15 min before and immediately before** (s. 253) | [C: sixty-nsr-2020-xml] |
| Distress, urgency, safety | ITU Radio Regulations articles 32-34 (s. 243); cancel false alerts per IMO A.814(19) (s. 244); TP 9878 card at the radio (s. 214) | [C: sixty-nsr-2020-xml]; voice formats [I] (TP 9878) |
| Records | Radio record kept **12 months**; time in local time (UTC if international), 24-hour, 4 figures (ss. 246-247) | [C: sixty-nsr-2020-xml] |
| Certificate | ROC-MC: pass 70 %, life validity; syllabus (MMSI, DSC categories, SART, EPIRB, NAVTEX, distress relay) | [C: sixty-ised-ric25-roc-mc] |
| MCTS | Victoria and Prince Rupert MCTS; Continuous Marine Broadcast; VHF/DF | [C: chs-pac200-sailing-directions, sixty-eccc-pacific-products] |

### 2.10 Meteorology and BC marine forecasts (oral)

No MET written exam is known for this certificate; MET1 appears in the MPR exam list for the Master 150 GT
Domestic certificate (s. 129), which names its exams in the regulation, unlike s. 131. [C: sixty-mpr-sor-2007-115-xml]
Weather still comes up in the oral and the BCIT course. [P: sixty-saferoceans-course-info]

| Topic | Key content | Tag |
|---|---|---|
| Warnings | Strong wind **20-33 kn**, gale **34-47**, storm **48-63**, hurricane force **64+** (excluding gusts); strong wind warnings seasonal (Pacific 20 Mar-11 Nov per a 2014 page); freezing spray, squall warnings | [C: sixty-eccc-warning-program-ch1, sixty-ubc-atsc113-warnings] |
| Products | Regular forecast (days 1-2), extended (3-5), technical marine synopsis, marine weather statement, wave forecast, NAVTEX; issued **04:00, 10:30, 16:00, 21:30** local | [C: sixty-eccc-pacific-products, sixty-ccg-ramn-part5-2024] |
| Beaufort | Force 0-12 and sea state | [C: sixty-eccc-beaufort] |
| Systems | Highs, lows, fronts, ridges and troughs from the synopsis; Buys Ballot's law | [I] (ECCC or UBC to source) |
| BC local effects | Outflow/inflow winds in inlets (warnings say which), sea breezes, coastal fog, gap winds, wind against tidal current in passes | [C: sixty-ubc-atsc113-warnings] partly; [C: predictwind-wind-against-current, ubc-atsc113-local-tides-currents] |
| Dissemination | Continuous Marine Broadcast (VHF), ECCC website, phone; Weatheradio status disputed | [C: sixty-eccc-pacific-products, sixty-ccg-ramn-part5-2024] |

### 2.11 Radar and electronic navigation (oral; BCIT course; SEN-L not required)

| Topic | Key content | Tag |
|---|---|---|
| Radar principles and controls | Pulse/echo, range and bearing, gain, sea and rain clutter, tuning, EBL/VRM, display modes | SEN-L competence list [C: sixty-tp2293-2019-draft]; detail [I] |
| Radar errors | False echoes, shadow sectors, bearing vs range accuracy | [C: sixty-tp2293-2019-draft] topic; detail [I] |
| Radar fixing | Ranges preferred over bearings | [I] |
| Relative motion plotting | R-M plot, DRM line, CPA, TCPA, target true course/speed by triangle | [C: sixty-oceannavigator-radar-plotting] |
| ARPA/ATA | Required at 300 GT (NSR s. 110); limitations | [C: sixty-nsr-2020-xml] |
| Radar and COLREGs | Rules 6, 7, 19 (existing lessons) | existing |
| GNSS, AIS, echo sounder, ECS | AIS carriage (NSR s. 118: Class A at 20 m non-pleasure, > 50 passengers, towboats 8 m+; A or B for passenger vessels / 8 m+ with a passenger outside sheltered waters; on 30 min before departure); GPS errors; ECS limits; phones/tablets for planning | [C: sixty-nsr-2020-xml, sixty-tp2293-2019-draft] |
| TimeZero (gear skills, not examined) | Radar overlay needs heading; ARPA acquisition; CPA/TCPA alarms | [C: sixty-timezero-arpa-target] |

### 2.12 Tides and currents

Existing `tides` lessons 1-6 cover causes, springs/neaps, vocabulary, tide types, tables and heights (reference and
secondary ports, rule of twelfths, depth = charted depth + tide), and tidal currents. Gaps for this ticket:
current tables and current stations in BC passes (slack, max flood/ebb, turn times), using currents in chartwork
(set/drift, course to steer), vertical clearance under bridges and cables, and timing a pass. [C: chs-tide-current-tables-vol5, chs-pac200-sailing-directions]

---

## 3. Writer brief

### 3.1 Conventions

- Lesson ids use the module name as the first segment (e.g. `chartwork.compass-error-tvmdc`). Tickets:
  `[master-limited-60gt]`, plus `bridge-watch-rating` where tagged **[shared-bwr]**.
- Everything regulatory: `review_status: needs_expert_review`.
- Calculation lessons: use invented ports and invented numbers, as `tides.tide-tables-and-heights` does, and
  repeat "study aid, not for navigation".
- Facts tagged [I] here need a source before they go into `key_facts`. Good candidates: Bowditch (NGA, public
  domain in the US; register more chapters in `sources/<lane>.json`), CHS publications, CCG publications, TC TPs.
- Do not quote provider or study-site text; they are listed only as evidence of what is examined.
- Original questions only; never recreate TC exam questions.

### 3.2 Overlap and shared lessons

- **Existing, reuse as is:** all 43 `colregs` lessons and drills (NS1 core) and 6 `tides` lessons.
- **[shared-bwr], write once** (suggest the Bridge Watch researcher's plan owns the module placement; ids below
  are proposals): buoyage (2 lessons), light characteristics, compass basics and bearings, lookout and watch
  handover, helm orders, man overboard, fire basics, abandon ship and cold water, distress signals and alerting,
  ropes/knots/mooring, pollution basics, hours of rest and fatigue, VHF procedures.

### 3.3 Proposed lessons by module

108 lesson slots (plus mock exams); see the totals at the end. Order numbers are within each module. Facts
marked [I] need a source first.

#### Module `sixty-ton` (pathway, oral preparation) — 6 lessons

1. **sixty-ton.certificate-overview** — "The Master, Limited < 60 GT certificate" (order 1)
   - Facts: certificate is MPR 100(h), requirements in s. 131; valid as master on vessels **under 60 GT of a type, tonnage, area and period of operation specified**; only **near coastal Class 2 and sheltered waters**; **not STCW-endorsed**; NC2 = within **25 nm** of shore and **100 nm** of refuge; renewal options (s. 106).
   - Sources: sixty-mpr-sor-2007-115-xml, sixty-vscr-sor-2021-135-xml.
2. **sixty-ton.requirements-checklist** — "What you need before the oral" (2)
   - Facts: age **18** at issue, service started at **16**; **2 months** deck sea service on vessels of at least the same GT (or **1 month** approved on-board training); MED by vessel type; radio certificate if VHF fitted; **Marine Basic First Aid**; medical **only if passenger-carrying**; application **2 weeks** before first exam, valid **1 year**; MED course within **5 years**.
   - Sources: sixty-mpr-sor-2007-115-xml; sixty-wmi-master-limited-under-60 (context only).
3. **sixty-ton.counting-sea-service** — "Counting sea service" (3)
   - Facts: **8 h = 1 day**; 12-hour watch = **1.5 days** (cap 12 h per day); months = days ÷ **30**; 2 months = **60 days**; testimonials must carry Schedule 4/5 information.
   - Worked examples: 30 days of 12-hour watches = 45 days = 1.5 months; 10-hour days prorated (10/8 = 1.25 days) [I for the 10-hour proration reading of s. 115(2)].
   - Sources: sixty-mpr-sor-2007-115-xml.
4. **sixty-ton.exams-and-format** — "The written exams and the oral" (4)
   - Facts: NS1 **100 MCQ in 2 h**; CP1 **3 h, 10 MCQ × 3 marks + 5 practical**; pass **70 %**; written and limited oral fees **$27.50**; pass valid **5 years**; oral taken last; CP1 status note (1.7).
   - Sources: sixty-holland-ns1, sixty-holland-cp1, sixty-tp2293-2019-draft, sixty-mpr-sor-2007-115-xml.
5. **sixty-ton.oral-exam-guide** — "How the oral works and what it covers" (5)
   - Facts: in person, by appointment, after all other requirements; may be aboard with manoeuvres and drills; topic list (1.8); examiner may add restrictions; renewal can also require a practical and oral on general seamanship.
   - Sources: sixty-nauticalmind-master-limited-practical, sixty-saferoceans-course-info, sixty-mpr-sor-2007-115-xml.
6. **sixty-ton.know-your-vessel** — "Know your own vessel" (6)
   - Facts: certificate is tied to vessel type/area; examiners ask about your vessel's systems (electrical, plumbing, propulsion), equipment and emergency gear; SVR equipment tables by length; inspection regime if over 15 GT or more than 12 passengers.
   - Activity: a checklist the user fills in for their own vessel (no facts invented).
   - Sources: sixty-nauticalmind-master-limited-practical, sixty-svr-sor-2010-91-xml, sixty-vscr-sor-2021-135-xml.

#### Module `chartwork` — 24 lessons

1. **chartwork.charts-and-projections** — "Charts, projections and scale" (1)
   - Facts [I unless noted]: Mercator: rhumb lines are straight, latitude scale grows toward the poles; gnomonic: great circles straight; large scale (e.g. 1:20 000) shows less area, more detail than small scale (1:80 000); carry the **largest-scale** chart (NSR 142) [C]; check edition and correction record.
   - Sources: sixty-nsr-2020-xml; Bowditch chapter on charts (to register).
2. **chartwork.chart-datums** — "Chart datum, depths, drying heights and heights" (2)
   - Facts: depths and drying heights refer to chart datum (low-water datum); heights of lights and clearances refer to a high-water datum; Canadian datum names per CHS page. Overlap: tides lesson 3.
   - Sources: chs-vertical-datum-chart-references, sixty-chs-chart1.
3. **chartwork.latitude-longitude-distance** — "Latitude, longitude and distance" (3)
   - Facts [I]: latitude 0-90° N/S, longitude 0-180° E/W; BC lies roughly 48°-55° N, 123°-133° W; **1′ latitude = 1 nm = 1852 m**; measure distance on the **latitude** scale level with the work, never the longitude scale.
   - Worked example: plot two invented positions, measure distance and course.
   - Sources: sixty-bcit-naut-1475 (topic); Bowditch (to register).
4. **chartwork.chart-symbols** — "Reading chart symbols (Chart 1)" (4)
   - Facts: Chart 1 is the key to symbols, abbreviations and terms; lettered sections; selected symbols: rocks (awash, drying, submerged), wrecks, cables, anchorages, restricted areas, light abbreviations.
   - Sources: sixty-chs-chart1. Describe symbols in words or redraw; do not copy CHS artwork.
5. **chartwork.compass-types** — "Magnetic and gyro compasses" (5) [shared-bwr]
   - Facts: standard magnetic compass required except vessels **8 m or less within sight of marks**; under 150 GT a **steering compass** may serve; deviation table/curve **near the compass**; means to correct to true; gyro has no variation/deviation but needs power and has gyro error [I].
   - Sources: sixty-nsr-2020-xml, sixty-bcit-naut-1475.
6. **chartwork.variation** — "Variation and the compass rose" (6)
   - Facts: variation = angle between true and magnetic north, named E or W; read from the rose and **correct for annual change** to the current year; changes with place, not heading [I].
   - Worked example: rose "18°30′E 2015, decreasing 8′ annually" → 2026 value (invented numbers).
   - Sources: sixty-bowditch-2019-compass-conversions, chs-pac200-sailing-directions.
7. **chartwork.deviation-card** — "Deviation and the deviation card" (7)
   - Facts: deviation = angle between magnetic and compass north, caused by the vessel's own magnetism, **changes with heading**; read the card against the **compass** heading (or magnetic, as the card states) [I]; keep electronics and steel away from the compass [I].
   - Worked example: interpolate deviation from an invented card.
   - Sources: sixty-bowditch-2019-compass-conversions, sixty-nsr-2020-xml.
8. **chartwork.compass-error-tvmdc** — "Converting True, Magnetic and Compass" (8)
   - Facts: T ↔ V ↔ M ↔ D ↔ C; compass error = algebraic sum of var and dev; **compass to true: add easterly, subtract westerly**; true to compass: opposite; westerly error makes compass read higher than true.
   - Worked examples: Bowditch-style 215° T, 7° W, 1.5° W → 222° M, 223.5° C, CE 8.5° W (use own numbers); 10 drill conversions both ways.
   - Sources: sixty-bowditch-2019-compass-conversions.
9. **chartwork.compass-error-by-transit** — "Finding compass error by a transit" (9)
   - Facts: transit = two charted objects in line; true bearing from chart minus compass bearing gives compass error; deviation = compass error − variation (with signs) [I method].
   - Worked example: invented transit 047° T observed 052° C, var 17° E → CE 5° W, dev 22° W (check sign logic in review).
   - Sources: sixty-bcit-naut-1475; Bowditch (to register).
10. **chartwork.speed-time-distance** — "Speed, time and distance" (10)
    - Facts [I]: D = S × T; T = D ÷ S; 6-minute rule (distance in 6 min = speed ÷ 10); knot = 1 nm per hour.
    - Worked examples: 14.5 nm at 9 kn; ETA from a noon position.
    - Sources: sixty-bcit-naut-1475; Bowditch (to register).
11. **chartwork.dead-reckoning** — "Dead reckoning and estimated position" (11)
    - Facts: DR uses course steered and speed through the water; EP adds current and leeway; plot DR at regular times; competence includes DR "taking into account winds, tides, currents and estimated speed".
    - Sources: sixty-tp2293-2019-draft, sixty-bcit-naut-1475.
12. **chartwork.fixes-bearings** — "Fixing by cross bearings" (12)
    - Facts [I]: two bearings best near 90° apart, three near 60°; take bearings changing slowest first (ahead/astern) and beam last; cocked hat: take the corner nearest danger; convert compass bearings to true before plotting.
    - Sources: sixty-saferoceans-course-info (topic); Bowditch (to register).
13. **chartwork.fixes-ranges-and-mixed** — "Fixing by ranges, range and bearing, and depth" (13)
    - Facts [I]: radar range more accurate than radar bearing; range + bearing of one object = fix; depth contour as check, not a fix alone.
    - Sources: sixty-saferoceans-course-info; Bowditch (to register).
14. **chartwork.running-fix** — "Running fix" (14)
    - Facts: advance the first position line by the run between bearings (course and distance through/over ground); accuracy depends on course/speed; doubling angle on the bow (run = distance off when angle doubles, first angle under 45°); four-point bearing 45°/90° (run = distance abeam).
    - Worked example: invented headland, 10 kn, 45° at 1012, 90° at 1030 → 3.0 nm abeam.
    - Sources: sixty-sailingissues-fixes (secondary; add Bowditch).
15. **chartwork.transits-and-clearing-bearings** — "Transits, leading lines and clearing bearings" (15)
    - Facts [I]: a transit gives an exact LOP without compass error; clearing bearing "not more than / not less than"; leading lines/range lights and daymarks (CCG range daymarks).
    - Sources: sixty-ccg-atons-2023; Bowditch (to register).
16. **chartwork.distance-off-lights** — "Lights: ranges and distance off" (16)
    - Facts: dipping/rising distance ≈ **2.08 (√H + √h)** nm (metres); nominal vs luminous vs geographic range [I]; List of Lights gives characteristics [C: nsr carriage].
    - Worked example: light 25 m, eye 3 m → about 14.0 nm.
    - Sources: sixty-sailingissues-fixes, sixty-nsr-2020-xml.
17. **chartwork.set-and-drift** — "Set, rate and drift" (17)
    - Facts [I]: set = direction the current flows **toward**; rate = speed (kn); drift = distance in the time; found from DR vs fix.
    - Worked example: DR vs fix 1 h apart → set 135° T, drift 1.5 nm.
    - Sources: sixty-bcit-naut-1475; Bowditch (to register).
18. **chartwork.course-to-steer** — "Course to steer to counter a current" (18)
    - Facts [I]: draw the current vector for one hour from the start, swing an arc of boat speed from its end to the track; CTS = that line; SOG along the track; then convert T→C.
    - Worked example: track 090° T, 8 kn, current 040° T at 2 kn (invented).
    - Sources: sixty-bcit-naut-1475; Bowditch (to register).
19. **chartwork.leeway** — "Leeway" (19)
    - Facts [I]: leeway = angle between heading and track through the water due to wind; steer **into** the wind by the leeway angle.
    - Sources: sixty-bcit-naut-1475; Bowditch (to register).
20. **chartwork.course-speed-made-good** — "Course and speed made good, ETA and fuel" (20)
    - Facts [I]: CMG/SMG from start to fix over elapsed time; ETA from SOG; fuel = consumption × time + reserve; plan fuel for the passage (BCIT outcome).
    - Worked example: invented 38 nm passage, 7.5 kn SOG, 22 L/h.
    - Sources: sixty-bcit-naut-1475.
21. **chartwork.tides-in-chartwork** — "Using tide heights in pilotage" (21)
    - Facts: depth of water = charted depth + height of tide; under-keel clearance; vertical clearance under bridges/cables from the high-water datum [I]; CHS BC tables in standard time (existing lesson).
    - Overlap: `tides.tide-tables-and-heights` (link, do not repeat).
    - Sources: chs-tide-current-tables-vol5, chs-vertical-datum-chart-references.
22. **chartwork.current-tables-and-passes** — "Current tables, current stations and BC passes" (22)
    - Facts: current stations give slack and maximum flood/ebb times and rates; secondary current stations corrections; plan passes for slack (e.g. Seymour Narrows) [C: chs-pac200-sailing-directions]; wind against current steepens seas.
    - Sources: chs-tide-current-tables-vol5, chs-pac200-sailing-directions, predictwind-wind-against-current.
23. **chartwork.publications-and-corrections** — "Canadian publications and keeping them current" (23)
    - Facts: NSR 142 list (catalogue and charts at largest scale, NOTMAR annual, Sailing Directions, Tide and Current Tables, List of Lights, RAMN if radio required, life-saving signals table); **under-100 GT local-knowledge exception**; correct from NOTMAR/navigational warnings before use (s. 146); ECDIS-only electronic chart equivalence (s. 143).
    - Sources: sixty-nsr-2020-xml, chs-pac200-sailing-directions.
24. **chartwork.voyage-planning-and-log** — "Voyage planning and the log book" (24)
    - Facts: master must plan before departure taking into account IMO A.893(21); route considers routing systems, sea room, hazards and weather, environmental measures; display route and **plot and monitor position**; log of compass errors; STCW A-VIII/2 Part 2 applies via MPR s. 213.
    - Worked example: plan an invented 3-leg passage with waypoints, clearing bearings, tide window.
    - Sources: sixty-nsr-2020-xml, sixty-tp2293-2019-draft, sixty-mpr-sor-2007-115-xml.

#### Module `bridge-watch` (NS1 watchkeeping; most [shared-bwr]) — 9 lessons

1. **bridge-watch.buoyage-lateral** — "Lateral buoyage (IALA B)" (1) [shared-bwr]
   - Facts: red right returning; port green/flat-topped/odd; starboard red/conical/even; Fl 4s or Q; fairway Mo(A) 6s / LFl 10s, keep to port; bifurcation Fl(2+1); numbers increase upstream.
   - Sources: sixty-ccg-atons-2023.
2. **bridge-watch.buoyage-cardinal-special** — "Cardinal, isolated danger and special marks" (2) [shared-bwr]
   - Facts: cardinal colours/topmarks; Q/VQ groups 3-6-9 on the clock; S adds a long flash; isolated danger two black spheres, Fl(2); special yellow Fl 4s; ODAS Fl(5)Y 20s.
   - Sources: sixty-ccg-atons-2023.
3. **bridge-watch.light-characteristics** — "Light characteristics" (3) [shared-bwr]
   - Facts: F, Iso, Fl, LFl (≥ 2 s), Oc, Q 50-79/min, VQ 80-159/min; period; reading "Fl(2)R 6s"; report aids not working (CSA 129).
   - Sources: sixty-ccg-atons-2023, sixty-csa-2001-xml.
4. **bridge-watch.watchkeeping-principles** — "Principles of the navigational watch" (4) [shared-bwr]
   - Facts: factors for composing the watch (visibility, daylight, hazards, equipment state, autopilot, radio duties); officer stays responsible until formally relieved even with master present; check position, course and speed frequently; A-VIII/2 applies by MPR ss. 213/244.
   - Sources: sixty-amsa-242-watchkeeping, sixty-mpr-sor-2007-115-xml.
5. **bridge-watch.taking-over-the-watch** — "Handing over and taking over" (5) [shared-bwr]
   - Facts: relieving officer checks standing orders, position/course/speed/draught, tides/currents/weather, equipment and compass errors, traffic, hazards; **do not hand over during a manoeuvre or avoiding action**; relieve only when fit and eyes adjusted [I for night vision].
   - Sources: sixty-amsa-242-watchkeeping.
6. **bridge-watch.lookout-and-sole-lookout** — "Lookout duties" (6) [shared-bwr]
   - Facts: lookout by sight, hearing and all available means; purposes (collision/stranding risk, persons in distress, wrecks and debris); sole lookout only in **daylight**, after assessment, with help **immediately** available; links Rule 5 lesson.
   - Sources: sixty-amsa-242-watchkeeping, colregs-canada.
7. **bridge-watch.calling-the-master** — "When to call the master" (7)
   - Facts (list): restricted visibility; worrying traffic; difficulty keeping course; land/marks or soundings not as expected; breakdown of engine, steering or essential navigation equipment; radio malfunction; heavy weather damage risk; hazards such as derelicts; any emergency or doubt.
   - Sources: sixty-amsa-242-watchkeeping.
8. **bridge-watch.restricted-visibility-and-anchor-watch** — "Watch in fog and at anchor" (8)
   - Facts: fog: inform master, lookout, sound signals, safe speed, engines ready, radar on, lights on; anchor: fix and check position by bearings, lookout, rounds, weather and tide, call master if dragging, lights/shapes/signals, pollution.
   - Sources: sixty-amsa-242-watchkeeping.
9. **bridge-watch.brm-and-fatigue** — "Bridge teamwork, communication and fatigue" (9) [shared-bwr]
   - Facts: closed-loop communication, situational awareness, challenge and response [I]; fatigue must be considered in scheduling (MPR s. 322); rest **6 consecutive h in 24** and **16 h in 48**, max **18 h** between rest periods (s. 320 vessels); SEN-L/BRM competence topics.
   - Sources: sixty-mpr-sor-2007-115-xml, sixty-tp2293-2019-draft, sixty-holland-ns1.

(Helm orders [shared-bwr] are assumed to be written by the Bridge Watch lane.)

#### Module `colregs` (additions) — 2 lessons

1. **colregs.part-f.canadian-rules** — "Part F: additional Canadian rules (Rules 39-46)" (order after existing)
   - Facts: from the Canadian schedule (dangerous goods signals, radar reflectors, ocean data buoys, blue flashing lights, etc.; confirm each against Part F text).
   - Sources: lights-crc1416-xml.
2. **colregs.ns1-review-traps** — "NS1 review: common traps" (after Part F)
   - Facts: drawn from existing rule lessons' key facts; no new claims.
   - Sources: existing colregs sources.

#### Module `canadian-regulations` — 14 lessons

1. **canadian-regulations.csa-roles-and-duties** — "Who is responsible: the Canada Shipping Act, 2001" (1)
   - Facts: authorized representative's 3 duties (s. 106); master documents before voyage (s. 107); safety of persons (s. 109); persons ≤ certified number, load lines not submerged (s. 110); crew duties (s. 113); passengers obey directions (s. 115); no one may jeopardize safety (s. 118).
   - Sources: sixty-csa-2001-xml.
2. **canadian-regulations.distress-assistance-collision** — "Duties to assist and after a collision" (2)
   - Facts: proceed with all speed to distress and inform; log the reason if not; release when another vessel is requisitioned or assistance unneeded; assist anyone in danger at sea; collision: assist, stand by, give names; SAR coordinators may direct vessels (s. 130).
   - Sources: sixty-csa-2001-xml.
3. **canadian-regulations.voyage-classes** — "Voyage classes and BC sheltered waters" (3)
   - Facts: sheltered, NC2 (25 nm/100 nm), NC1 (200 nm or continental shelf, north of 6° N), unlimited; BC sheltered waters examples; why it matters for the certificate.
   - Sources: sixty-vscr-sor-2021-135-xml, sixty-mpr-sor-2007-115-xml.
4. **canadian-regulations.mpr-crewing-and-watch** — "Crewing, deck watch and certificates on board" (4)
   - Facts: master + enough deck officers (s. 212(4)); deck watch person with radio cert if VHF; additional person at 5 GT+ with exceptions; master counts in watch on vessels ≤ 1 000 GT; safe manning document if inspection certificate.
   - Sources: sixty-mpr-sor-2007-115-xml.
5. **canadian-regulations.crew-training-and-first-aid** — "Crew MED, familiarization and first aid" (5) [shared-bwr]
   - Facts: crew needed for safe manning must obtain the applicable MED **before acquiring 6 months of sea service** (s. 205(3), table by vessel type); familiarization before duties and training record (s. 206) kept aboard or at home port if within 5 nm; first-aid person: marine basic or advanced first aid on NC2/sheltered (s. 205(9)).
   - Sources: sixty-mpr-sor-2007-115-xml, tp-14070.
6. **canadian-regulations.hours-of-rest** — "Hours of work and rest" (6) [shared-bwr]
   - Facts: s. 320 limits; s. 321 limits for other vessels (**14 h work in 24, 72 h in 7 days**, or **10 h rest in 24, 77 h in 7 days**); drills exempt if done to minimize disturbance.
   - Sources: sixty-mpr-sor-2007-115-xml.
7. **canadian-regulations.svr-passenger-vessels** — "Small Vessel Regulations: passenger vessels up to 15 GT and 12 passengers" (7)
   - Facts: briefing list and lifejacket demo; POB to a shore contact; water **below 15 °C** without a life raft → hypothermia measures; notify TC before first service; flares **3 / 6 / 12** by length; life raft rule; anchor rope **15 / 30 / 50 m**; extinguisher ratings; 84 dB detectors.
   - Worked example: equipment list for an invented 11 m water taxi.
   - Sources: sixty-svr-sor-2010-91-xml.
8. **canadian-regulations.svr-workboats-and-construction** — "Workboats, construction and compliance" (8)
   - Facts: Part 5 workboat equipment (writer to read); stability adequate for intended operations (Part 7); compliance notice (Part 8); inspection certificates above 15 GT / 12 passengers.
   - Sources: sixty-svr-sor-2010-91-xml, sixty-vscr-sor-2021-135-xml, sixty-tc-tp15491-tug-guidance.
9. **canadian-regulations.nsr-equipment** — "Navigation equipment rules" (9)
   - Facts: compass rules; AIS thresholds and 30-minute rule; radar for towboats ≥ 5 GT on NC2/inland; GNSS and spare compass at 150 GT; radar/echo sounder/ATA at 300 GT; hand lead line at 20 m; searchlights for towing vessels over 5 GT.
   - Sources: sixty-nsr-2020-xml.
10. **canadian-regulations.pollution-prevention** — "Pollution: oil, sewage and garbage" (10) [shared-bwr]
    - Facts: oily mixtures in Canadian waters only en route through **15 ppm** equipment with alarm (5 ppm inland); sewage for vessels under 400 GT and ≤ 15 persons: treated device or **1 nm** comminuted/disinfected or **3 nm** en route; MSD effluent **250 fecal coliforms/100 mL**; garbage beyond Canadian waters 3/12/25 nm rules; report pollution [I who to].
    - Sources: sixty-vpdcr-sor-2012-69-xml.
11. **canadian-regulations.occurrence-reporting** — "Reporting accidents and hazards" (11)
    - Facts: TSB list; report by quickest means, full info within **30 days**; report via MCTS counts; aids to navigation damage or hazards to MCTS without delay (CSA 129); dangers (CSA 112).
    - Sources: sixty-tsb-regs-sor-2014-37-xml, sixty-csa-2001-xml.
12. **canadian-regulations.vts-west-coast** — "Vessel Traffic Services on the BC coast" (12)
    - Facts: new regulations from **2026-03-31**; zones **Victoria** and **Prince Rupert**; general: **300 GT+**, tows **500 GT combined**, pollutant/DG cargo; western add-ons: **20 m+** (not pleasure < 30 m, not fishing < 24 m and ≤ 150 GT) and tows **45 m combined** or tow **20 m+**; sailing plan **15 min** before; position at calling-in points; final; deviation; listening on VTS channel (NSR Sched. 4); sector channels e.g. Victoria Traffic Ch 11, Prince Rupert Traffic Ch 74 off Juan de Fuca.
    - Sources: sixty-vts-zones-sor-2025-275-xml, sixty-ccg-ramn-part3-2025, sixty-nsr-2020-xml.
13. **canadian-regulations.inspection-and-documents** — "Certificates and documents the vessel needs" (13)
    - Facts: master ensures documents before voyage; inspection certificate regime (> 15 GT, or > 12 passengers); radio inspection certificate where required (NSR s. 240) [read first]; documents on board (radio certificates, RAMN at radio).
    - Sources: sixty-csa-2001-xml, sixty-vscr-sor-2021-135-xml, sixty-nsr-2020-xml.
14. **canadian-regulations.pilotage-and-local-rules** — "Pilotage and local rules in BC" (14)
    - Facts: compulsory pilotage applies to larger vessels; pilot boarding stations; small vessels must still keep clear and use VTS; (needs a Pacific Pilotage Authority source).
    - Sources: chs-pac200-sailing-directions (plus PPA source to register).

#### Module `seamanship` (emergencies, safety, ship knowledge) — 16 lessons

1. **seamanship.hull-and-construction-terms** — "Hull and construction" (1) — needs a source [I].
2. **seamanship.machinery-and-systems** — "Engines, bilge, fuel and electrical systems" (2) — facts: blower **4 min** before starting gasoline engines (TP 14070); bilge alarms; fuel safety; battery hazards [I]. Sources: tp-14070, sixty-nauticalmind-master-limited-practical.
3. **seamanship.ropes-and-knots** — "Ropes and essential knots" (3) [shared-bwr] — [I]; needs source.
4. **seamanship.mooring-and-docking** — "Mooring lines and docking" (4) [shared-bwr] — [I]; needs source.
5. **seamanship.anchoring** — "Anchoring and anchor watch" (5) — facts: SVR minimum rode 15/30/50 m by length; anchor watch duties (A-VIII/2); scope rule [I]. Sources: sixty-svr-sor-2010-91-xml, sixty-amsa-242-watchkeeping.
6. **seamanship.shiphandling** — "Handling a small vessel" (6) — [I]; BCIT lists it. Sources: sixty-bcit-naut-1480 (+ textbook to register).
7. **seamanship.heavy-weather** — "Heavy weather and following seas" (7) — facts: following/quartering seas and broaching (FAO); reduce speed, secure, keep freeing ports clear [I]. Sources: sixty-fao-ftp517-stability.
8. **seamanship.towing-and-girding** — "Towing safely and girding" (8) — facts: girding definition and causes; tow plan; tested emergency release; short towline risk; no passengers when towing except in an emergency (SVR 404(3)). Sources: sixty-tc-ssb-16-2020-girding, sixty-tc-tp15491-tug-guidance, sixty-svr-sor-2010-91-xml.
9. **seamanship.fire-response** — "Fire on board" (9) [shared-bwr] — facts: extinguisher ratings by length (SVR 414); engine-space discharge, CO2 **1.2 kg/m³**, discharge in **60 s** (CO2) / **10 s** (clean agent); detectors; fire triangle and classes [I]. Sources: sixty-svr-sor-2010-91-xml.
10. **seamanship.flooding-and-damage-control** — "Flooding" (10) — [I]; needs MED/TC source.
11. **seamanship.man-overboard** — "Man overboard" (11) [shared-bwr] — facts: SVR lifebuoy/heaving line (15 m) by length and reboarding device over 0.5 m; TSB: person overboard is reportable; manoeuvre and recovery [I]. Sources: sixty-svr-sor-2010-91-xml, sixty-tsb-regs-sor-2014-37-xml.
12. **seamanship.grounding-and-collision-response** — "Grounding and collision" (12) — facts: CSA 148; TSB reportable; VTS report; procedure [I]. Sources: sixty-csa-2001-xml, sixty-tsb-regs-sor-2014-37-xml, sixty-vts-zones-sor-2025-275-xml.
13. **seamanship.abandon-ship-cold-water** — "Abandon ship and cold-water survival" (13) [shared-bwr] — facts: life raft rule (SVR 411); hydrostatic release about **4 m** (TP 14070); under **15 °C** measures (SVR 403); cold shock/1-10-1 [I]. Sources: sixty-svr-sor-2010-91-xml, tp-14070.
14. **seamanship.distress-alerting** — "Calling for help: signals, EPIRB, DSC" (14) [shared-bwr] — facts: flares by length; EPIRB rules; PLB; DSC handheld; links Annex IV lesson. Sources: sixty-svr-sor-2010-91-xml, sixty-nsr-2020-xml.
15. **seamanship.drills-and-emergency-procedures** — "Procedures, drills and records" (15) — facts: owner/operator must establish procedures and crew must practise (SVR 420); AR develops emergency procedures (CSA 106); records (MPR 206, TP 14070). Sources: sixty-svr-sor-2010-91-xml, sixty-csa-2001-xml, sixty-mpr-sor-2007-115-xml, tp-14070.
16. **seamanship.passenger-safety-management** — "Managing passengers" (16) — facts: briefing list; POB ashore; max persons (CSA 110); stability effect of passengers; directions passengers must obey (CSA 115). Sources: sixty-svr-sor-2010-91-xml, sixty-csa-2001-xml.

#### Module `stability` — 11 lessons

1. **stability.terms** — "Displacement, draught, freeboard and deadweight" (1) — facts: definitions (FAO); list (from G off centreline) vs heel (external force) vs loll (negative GM) [C FAO terms; distinctions I]. Sources: sixty-fao-ftp517-stability.
2. **stability.g-b-and-m** — "Centre of gravity, buoyancy and the metacentre" (2) — facts: G, B, M; equilibrium types. Sources: sixty-fao-ftp517-stability.
3. **stability.metacentric-height** — "Metacentric height (GM)" (3) — facts: GM = KM − KG [I]; positive GM stable; large GM stiff (short jerky roll), small GM tender (slow roll) [C FAO stiff/tender]. Worked example: KM 2.40 m, KG 1.95 m → GM 0.45 m (invented).
4. **stability.righting-lever-gz-curve** — "Righting lever and the GZ curve" (4) — facts: GZ ≈ GM × sin θ (small angles) [I]; righting moment = Δ × GZ [I]; range of stability; dynamic stability (FAO). Worked example: Δ 40 t, GZ 0.12 m → RM 4.8 t·m.
5. **stability.moving-adding-removing-weight** — "Loading: moving, adding and removing weight" (5) — facts: shift of G = w × d ÷ Δ [I]; adding high raises G; list angle tan θ = (w × d) ÷ (Δ × GM) [I]. Worked examples (invented): 0.5 t moved 2 m on a 25 t vessel with GM 0.5 m → list ≈ 4.6°.
6. **stability.suspended-weights-and-lifting** — "Lifting and suspended weights" (6) — facts: weight acts at the point of suspension (FAO); lifting over the side heels the vessel and raises G. Sources: sixty-fao-ftp517-stability.
7. **stability.free-surface** — "Free surface effect" (7) — facts: slack liquid reduces effective GM (FAO, TP 14070); keep tanks full or empty, baffles; water on deck; freeing ports. Worked example: compare effective GM with a slack tank given an invented FSC.
8. **stability.watertight-integrity-and-freeboard** — "Freeboard, watertight and weathertight" (8) — facts: FAO definitions; keep closures shut, freeing ports clear; load lines not submerged (CSA 110). Sources: sixty-fao-ftp517-stability, sixty-csa-2001-xml.
9. **stability.icing** — "Icing" (9) — facts: raises G and windage; remove from high first; CSA 112 report; FVSR freezing-spray rule (fishing). Sources: sixty-fao-ftp517-stability, sixty-csa-2001-xml, sixty-fvsr-crc-1486-xml.
10. **stability.rolling-period-test** — "The rolling period check" (10) — facts: time several full rolls, divide; T (s) < B (m) suggests enough stability; ≤ 1.2 B when light. Worked example: 10 rolls in 52 s on a 6.0 m beam → 5.2 s (OK by the rule of thumb).
11. **stability.passengers-standards-and-assessment** — "Passengers, stability standards and assessments" (11) — facts: adequate stability for intended operation (SVR); passenger movement briefing; IS Code/TP 7301 criteria numbers [I, verify]; assessments after major modification (FVSR 3.48 analogy). Sources: sixty-svr-sor-2010-91-xml, sixty-vcer-sor-2023-257-xml, sixty-fvsr-crc-1486-xml.

#### Module `radio` — 7 lessons

1. **radio.certificates-and-carriage** — "Radio certificates and what the vessel must carry" (1) — facts: MPR "appropriate radio operator certificate" if VHF; ROC-MC for compulsorily fitted commercial vessels in A1; VHF-DSC carriage (> 8 m, passengers, towboats); handheld exception. Sources: sixty-mpr-sor-2007-115-xml, sixty-ised-ric25-roc-mc, sixty-nsr-2020-xml, sixty-ised-roc-faq.
2. **radio.vhf-channels-and-listening-watch** — "Listening watch and channels" (2) [shared-bwr] — facts: watch from **15 min** before underway until securely moored/anchored and not a hazard; Ch 16 outside traffic zones, VTS channel inside; interrupted watch allowed with one radio; Ch 70 is DSC only [I]. Sources: sixty-nsr-2020-xml.
3. **radio.navigation-safety-calls** — "Navigation safety calls" (3) — facts: 11 circumstances; content order; **1 minute** max; departure calls **15 min before and immediately before**; does not replace whistle signals or COLREGs (s. 251). Sources: sixty-nsr-2020-xml.
4. **radio.dsc-and-gmdss** — "DSC, MMSI and GMDSS sea areas" (4) — facts: A1-A4 definitions; DSC categories distress/urgency/safety/routine; MMSI; NAVTEX (518 kHz); SART. Sources: sixty-nsr-2020-xml, sixty-ised-ric25-roc-mc.
5. **radio.distress-urgency-safety** — "Mayday, Pan-Pan and Sécurité" (5) [shared-bwr] — facts: ITU RR articles 32-34 apply; TP 9878 card at the radio; voice formats [I, source TP 9878]; inform persons in distress (CSA 131). Sources: sixty-nsr-2020-xml (+ TP 9878 to register).
6. **radio.false-alerts-and-records** — "Cancelling false alerts and the radio record" (6) — facts: cancel immediately per IMO A.814(19); log inadvertent transmissions and cancellation; radio record **12 months**; local time or UTC, 24-hour four figures. Sources: sixty-nsr-2020-xml.
7. **radio.epirb-sart-plb** — "EPIRB, PLB and SART" (7) — facts: float-free EPIRB rules; under 12 m NC2 options; wear/stow manual EPIRB/PLB; register beacons; test per s. 239 [read first]. Sources: sixty-nsr-2020-xml.

#### Module `meteorology` — 6 lessons

1. **meteorology.weather-systems** — "Highs, lows and fronts" (1) — [I]; needs ECCC/UBC-licensed source.
2. **meteorology.wind-and-beaufort** — "Wind and the Beaufort scale" (2) — facts: Beaufort selected forces; CSA 112 force 10 report. Sources: sixty-eccc-beaufort, sixty-csa-2001-xml.
3. **meteorology.marine-warnings** — "Marine warnings" (3) — facts: 20-33 / 34-47 / 48-63 / 64+ kn; seasonal strong wind warnings; freezing spray and squall warnings. Sources: sixty-eccc-warning-program-ch1, sixty-ubc-atsc113-warnings.
4. **meteorology.bc-marine-forecasts** — "Reading a BC marine forecast" (4) — facts: product list, issue times, how to get them; "outflow/inflow" wording; areas. Activity: invented forecast text with questions. Sources: sixty-eccc-pacific-products, sixty-ccg-ramn-part5-2024.
5. **meteorology.bc-local-effects** — "Local winds, fog and seas on the BC coast" (5) — facts: outflow winds in inlets; sea breezes; fog on the west coast (PAC 200); wind against current. Sources: chs-pac200-sailing-directions, predictwind-wind-against-current, ubc-atsc113-local-tides-currents.
6. **meteorology.go-no-go** — "Go or no-go decisions" (6) — scenario lesson combining warnings, vessel limits (design limitations, SVR 404) and passes. Sources: sixty-svr-sor-2010-91-xml, sixty-eccc-warning-program-ch1.

#### Module `radar` — 6 lessons

1. **radar.principles-and-controls** — "How radar works and its controls" (1) — facts: 9-GHz (X-band) radar referenced in NSR; range from echo time; gain/sea/rain/tune; EBL/VRM [I detail]. Sources: sixty-nsr-2020-xml, sixty-tp2293-2019-draft (+ Bowditch radar chapter to register).
2. **radar.displays-and-errors** — "Display modes and radar errors" (2) — head-up/north-up/course-up, relative/true motion; false echoes, shadow sectors [I]. Sources: sixty-tp2293-2019-draft (+ Bowditch).
3. **radar.position-fixing** — "Fixing by radar" (3) — ranges preferred; parallel indexing [I]. Sources: sixty-bcit-naut-1480 (+ Bowditch).
4. **radar.plotting-cpa-tcpa** — "Plotting: CPA and TCPA" (4) — facts: 6-minute plot, DRM line, CPA, TCPA. Worked example: target 6.0 nm at 045° R, 4.8 nm at 043° R six minutes later (invented). Sources: sixty-oceannavigator-radar-plotting.
5. **radar.target-course-speed-and-action** — "Target course and speed, and taking action" (5) — facts: R-T-M triangle; Rule 19 action in restricted visibility (existing lesson); Rule 7(c) scanty information. Sources: sixty-oceannavigator-radar-plotting, colregs-canada.
6. **radar.arpa-sart-racon** — "ARPA, SARTs and racons" (6) — facts: ATA/ARPA at 300 GT (NSR 110); ARPA limits [I]; SART on radar [I]; radar reflector rule (Part F, confirm). Sources: sixty-nsr-2020-xml, lights-crc1416-xml.

#### Module `electronic-navigation` — 5 lessons

1. **electronic-navigation.gnss** — "GPS and GNSS" (1) — facts: GNSS required at 150 GT; errors and datum mismatch [I]; distress alert position from GNSS (NSR 221). Sources: sixty-nsr-2020-xml.
2. **electronic-navigation.ais** — "AIS" (2) — facts: NSR 118 thresholds; 30 minutes before departure; graphic display for Class A vessels; AIS limits [I]. Sources: sixty-nsr-2020-xml.
3. **electronic-navigation.ecs-ecdis-and-apps** — "Plotters, ECDIS, phones and tablets" (3) — facts: only ECDIS meeting s. 143 replaces paper charts; ECS advantages/limits; phones/tablets for planning (SEN-L topic). Sources: sixty-nsr-2020-xml, sixty-tp2293-2019-draft.
4. **electronic-navigation.echo-sounder-autopilot** — "Echo sounder, autopilot and steering checks" (4) — facts: autopilot changeover and qualified helmsman (NSR 133) [read first]; echo sounder at 300 GT. Sources: sixty-nsr-2020-xml.
5. **electronic-navigation.timezero-basics** — "TimeZero: radar overlay and ARPA (gear skills)" (5) — not examined; heading sensor needed; acquisition; alarms. Sources: sixty-timezero-arpa-target.

#### Module `tides` (additions) — 2 lessons (order 7-8)

1. **tides.current-stations-and-slack** — "Current stations, slack water and turn times" (7) — facts: reading current tables; secondary current stations; BC passes. Sources: chs-tide-current-tables-vol5, chs-pac200-sailing-directions. (Coordinate with chartwork lesson 22; one of the two can be dropped.)
2. **tides.planning-a-pass** — "Planning a tidal pass" (8) — scenario with invented data. Sources: chs-tide-current-tables-vol5, predictwind-wind-against-current.

**Totals:** sixty-ton 6, chartwork 24, bridge-watch 9, colregs 2, canadian-regulations 14, seamanship 16,
stability 11, radio 7, meteorology 6, radar 6, electronic-navigation 5, tides 2 = **108 lesson slots**. Two may
merge (tides 7 / chartwork 22), so about **107 lessons**. **19** of them are [shared-bwr]; if the Bridge Watch
lane writes those, about **88** are specific to this ticket.

### 3.4 Mock exams and oral scenarios

- `mock-exams/ns1-*.json`: **100 questions, 120 minutes, pass 70 %**; weight: COLREGs about 70 %, watchkeeping/BRM about 20 %, buoyage/calls about 10 % [I weighting].
- `mock-exams/cp1-*.json`: **180 minutes**; 10 MCQ (3 marks) + 5 chartwork problems (5-15 marks) when the quiz engine supports worked answers; until then MCQ-only practice sets.
- Oral tester topic map for this ticket: emergencies (fire, flooding, MOB, grounding, collision, abandon); master's legal duties (CSA, MPR, SVR); stability and loading; COLREGs and watchkeeping; radio and distress; weather and passes; own-vessel systems; passengers and crew; pollution; VTS and reporting.

---

## 4. Not verified (read this before writing)

1. **TP 2293 itself was not read.** The Master, Limited chapter (2007 Rev. 5 ch. 12, oral syllabus s. 12.11; 2021 Rev. 8; 2019 draft s. 3.48) was beyond what the fetch tool could reach, and direct download was blocked. Everything about exam subjects comes from providers. **Highest priority.**
2. **Whether CP1 is required for every Master, Limited < 60 GT**, or depends on area of operation (e.g. sheltered waters only). Providers say required. (1.7)
3. **Exam formats** (NS1 100 MCQ / 2 h; CP1 3 h, 10 MCQ + 5 practical) come from one provider (Holland College); the **70 % pass mark** from a 2019 TP 2293 draft and the 2007 edition via a summariser.
4. **Radio certificate**: ROC-MC vs ROC-M for this certificate is my inference from RIC-25 + NSR s. 204; providers disagree.
5. **MED course name mapping**: that MED DVS satisfies "basic safety" and "small passenger vessel safety" in s. 131 is a provider claim; check TP 4957.
6. **SEN Limited**: not in s. 131 (confirmed) but one provider lists it; ask TC whether TP 2293 adds it for radar-equipped vessels.
7. **Certificate validity period**: CSA s. 17 says "period specified by the Minister"; the 5-year figure is not confirmed.
8. **Medical**: MPR says only for passenger-carrying vessels; one provider says always.
9. **VTS**: new regulations (in force 2026-03-31) were read, but RAMN Part 3 channels/calling-in points were read only via a summariser, and PAC 200 (2022) and older material describe the repealed regime ("Vancouver Traffic", 20 m).
10. **Weatheradio**: ECCC says no longer in service; RAMN 2024 lists stations.
11. **Strong wind warning season** dates come from a 2014 ECCC page.
12. **Life Saving Equipment Regulations, Vessel Fire Safety Regulations, SVR Part 5 (workboats)** were not read in detail; equipment facts for vessels over 15 GT are not covered by this brief.
13. **Section I/II waters** definitions in the pollution regulations were not read; confirm the garbage prohibition in Canadian waters before writing it.
14. All non-regulation sources were read through an automated summariser, not word for word (`read_directly: false`).
15. All **[I]** items: standard navigation knowledge without a registered source (compass mnemonics, fix technique, radar physics, stability formulas, IS Code criteria numbers, cold-water 1-10-1, anchoring scope, knots, MOB turns, fire classes, voice distress formats).
16. TP 14070 (2010) predates NSR 2020 and the 2025 SVR amendments; use it for practice guidance, not current legal numbers.

## 5. Sources

New sources are in `content/sources/sixty-ton.json` (48 entries). Main URLs:

- Marine Personnel Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2007-115.xml
- Small Vessel Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2010-91.xml
- Navigation Safety Regulations, 2020 (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2020-216.xml
- Vessel Pollution and Dangerous Chemicals Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2012-69.xml
- Canada Shipping Act, 2001 (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/acts/C-10.15.xml
- TSB Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2014-37.xml
- Vessel Safety Certificates Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2021-135.xml
- Vessel Construction and Equipment Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2023-257.xml
- Fishing Vessel Safety Regulations (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/C.R.C.%2C_c._1486.xml
- VTS Zones Regulations 2025 (XML): https://github.com/justicecanada/laws-lois-xml/blob/main/eng/regulations/SOR-2025-275.xml
- TP 2293 2019 draft: https://www.dieselduck.info/training/_docs/2019.04-TC%20Draft%20TP2293E.pdf
- TP 2293 2007 Rev. 5: https://www.dieselduck.info/training/_docs/TP2293E%202007.07%20-%20Examination%20and%20cert%20of%20seafarers.pdf
- TP 2293 2021 Rev. 8 record: https://publications.gc.ca/site/eng/9.911713/publication.html
- TC vessel stability training: https://tc.canada.ca/en/marine-transportation/marine-training-certification-individuals/certification-licensing-training/vessel-stability-training
- SSB 16/2020 girding: https://tc.canada.ca/en/marine-transportation/marine-safety/ship-safety-bulletins/hazards-risks-girding-during-towing-operations-ssb-no-16-2020
- TP 15491 tug guidance: https://tc.canada.ca/en/marine-transportation/publications/tp-15491-small-vessel-compliance-program-tug-guidance-notes-06-2024
- TP 14070 PDF (existing id `tp-14070`): https://tc.canada.ca/sites/default/files/migrated/tp14070e.pdf
- Canadian Aids to Navigation System 2023: https://www.canada.ca/en/canadian-coast-guard/corporate/publications/canadian-aids-navigation-system-2023.html
- RAMN Part 3 (VTS): https://www.ccg-gcc.gc.ca/publications/mcts-sctm/ramn-arnm/part3-eng.html
- RAMN Part 5 (weather): https://www.ccg-gcc.gc.ca/publications/mcts-sctm/ramn-arnm/part5-eng.html
- PAC 200 Sailing Directions (existing id): https://waves-vagues.dfo-mpo.gc.ca/library-bibliotheque/chs-shc-PAC200-eng-202208-41069213.pdf
- Canadian Chart 1: https://shc-chs.gc.ca/publications/chart1-carte1/index-eng.html
- ECCC warning program ch. 1: https://www.canada.ca/en/environment-climate-change/services/general-marine-weather-information/publications/canadian-warning-program/chapter-1.html
- ECCC Pacific products: https://www.canada.ca/en/environment-climate-change/services/general-marine-weather-information/understanding-forecasts/regional/products-services-pacific-coast.html
- ECCC Beaufort table: https://www.canada.ca/en/environment-climate-change/services/general-marine-weather-information/understanding-forecasts/beaufort-wind-scale-table.html
- UBC ATSC 113 warnings: https://www.eoas.ubc.ca/courses/atsc113/sailing/met_concepts/11-met-marine-weather/11b-weather-warnings/
- ISED RIC-25 ROC-MC: https://ised-isde.canada.ca/site/spectrum-management-telecommunications/en/licences-and-certificates/radiocom-information-circulars-ric/ric-25-syllabus-restricted-operators-certificate-maritime-commercial-roc-mc
- ISED ROC FAQ: https://ised-isde.canada.ca/site/professional-radio-operator-certificate-services/en/frequently-asked-questions
- FAO FTP 517 small fishing vessel stability: https://www.fao.org/4/i0625e/i0625e00.htm
- AMSA 242 watchkeeping standards: https://www.amsa.gov.au/sites/default/files/amsa242-watchkeeping-standards.pdf
- Bowditch 2019 compass conversions: https://thenauticalalmanac.com/2019_Bowditch-_American_Practical_Navigator/Volume-_2/09-%20Mathematics%20For%20Navigation/Chapter%205-%20Compass%20Conversions.pdf
- SailingIssues position fixing: https://sailingissues.com/navcourse5.html
- Ocean Navigator rapid radar plotting: https://oceannavigator.com/rapid-radar-plotting/
- TimeZero ARPA target: https://userguide.mytimezero.com/tz-professional/ARPA_Target.htm
- Western Maritime Institute, Master Limited < 60: https://maritimeed.com/master-limited-under-60gt/
- WMI NS1 and orals prep: https://maritimeed.com/courses-availability/navigation-safety-level-1-orals-prep/
- Safer Ocean Systems course information: https://www.saferoceans.com/course-information/
- Safer Ocean Systems CP1: https://saferoceans.arlo.co/w/courses/2-cp1-chartwork-pilotage-level-1
- Safer Ocean Systems oral prep: https://saferoceans.arlo.co/w/courses/41-oral-exam-prep-course/931
- Safer Ocean Systems ROC-M: https://saferoceans.arlo.co/w/courses/9-rocm-restricted-operator-certificate-maritime
- North Island College CP1: https://nic.bc.ca/continuing-education/search-courses/results/NAU/038
- BCIT NAUT 1480: https://www.bcit.ca/courses/master-limited-under-60-grt-naut-1480/
- BCIT NAUT 1475: https://www.bcit.ca/courses/master-limited-60-grt-naut-1475/
- Georgian College MARI 0257: https://ce.georgiancollege.ca/search/publicCourseSearchDetails.do?method=load&courseId=1030871
- Holland College NS1: https://www.hollandcollege.com/about/campuses-and-centres/marine-training-centre/ns1-navigation-safety-level-1.html
- Holland College CP1: https://hollandcollege.com/academics/specialized-training/mtc-courses/cp1-chartwork-and-pilotage-level-1.html
- The Nautical Mind CP1: https://www.nauticalmind.com/blog/transport-canada-exam-guide/exams/cp1-chartwork-pilotage-1/
- The Nautical Mind Master Limited practical: https://www.nauticalmind.com/blog/transport-canada-exam-guide/exams/master-limited-practical/
- SSTL MED DVS: https://www.sstl.com/training-sectors/marine/marine-international/domestic-vessel-safety-med-dvs/
- Camosun Fishing Master 4 courses: https://prosit.camosun.ca/srs/upload/Fishing%20Master%204.pdf
