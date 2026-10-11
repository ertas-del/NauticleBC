# Fishing Master, Fourth Class: syllabus brief and writer plan

Research lane output, 2026-10-10. Branch `fishing/syllabus-brief`. Candidate ticket id: `fishing-master-4`.

**What this is.** A sourced brief that tells content writers what a British Columbia candidate for the Transport
Canada (TC) **Fishing Master, Fourth Class** certificate must hold and pass, what the exams cover, which existing
NauticleBC lessons already serve the ticket, and which new lessons still have to be written. Section 1 sets out the
certificate requirements, section 2 the syllabus exam by exam, section 3 the reuse list and the proposed lesson map,
section 4 what needs an expert, section 5 cautions and discrepancies, section 6 the sources to log.

**What this is not.** It is not legal advice and not an official syllabus. The authority is the Marine Personnel
Regulations (MPR) and TC's TP 2293. Regulatory facts in lessons stay `needs_expert_review` until a certified master
or examiner signs off (CLAUDE.md). This PR is docs only: no change to `content/`, `schemas/` or `app/`.

## How to read the tags

| Tag | Meaning |
|---|---|
| **[C: id]** | Confirmed in that source, which I read directly for this brief. `fm4-mpr-sor-2007-115-xml` is the official consolidated XML (Justice Canada repository commit b7292cf, consolidation current to 2025-07-24, point in time 2023-12-20), read word for word. `fm4-tp2293-rev6` is the user-supplied text of TP 2293E Revision 6 (03/2016), read word for word. |
| **[P: id]** | A training-provider, college or study-site claim, read through an automated fetch-and-summarise tool. Not a regulation or a TC document. |
| **[I]** | Inferred, or standard knowledge I did not find in a source read for this brief. The writer must find a source before using it. |

Source ids tagged `fm4-` are the entries proposed in section 6; they do not exist in the repo yet. Ids without that
prefix (`tp2293-2016`, `sixty-...`, `stab-...`) already exist in `content/sources.json` or `content/sources/*.json`.

## 0. Short answers

- **Legal basis**: MPR s. 100(s) (the certificate), **s. 142** (requirements) and **s. 212 Table 2 item 4** (what it allows). [C: fm4-mpr-sor-2007-115-xml]
- **What it allows**: **Master of a fishing vessel of up to 100 gross tonnage** on near coastal Class 1, near coastal Class 2 and sheltered waters voyages, and **officer in charge of the watch** on near coastal voyages (TP 2293 says on a fishing vessel of any tonnage). Nothing on unlimited voyages. Not STCW-endorsed. [C: fm4-mpr-sor-2007-115-xml, fm4-tp2293-rev6]
- **Requirements**: age 18 at issue; **12 months of sea service doing deck duties on fishing vessels of 6 m or more** (up to 6 months may come from an approved training program; up to half of the rest may be on normal trading vessels of 6 m or more); **MED basic safety**, **ROC-MC**, **SEN Limited**, **marine advanced first aid**; a **medical certificate** (this ticket is not on the exemption list in s. 111(b)); and the four exams below. [C: fm4-mpr-sor-2007-115-xml]
- **Exams**: **Chartwork and Pilotage level 1 (C/P 1)**, **Navigation Safety level 1 (NS 1)**, **Ship Construction and Stability level 1 (SCS 1)**, then an **oral on general seamanship (FM 4-O)** last. Stability and a chart problem exam both apply. [C: fm4-mpr-sor-2007-115-xml, fm4-tp2293-rev6]
- **Pass marks**: TP 2293 sets **70 %** for NS, SCS and C/P written exams; the oral's mark is not stated. [C: fm4-tp2293-rev6]
- **New finding, unresolved**: BC and Nova Scotia providers say a separate **NS-D (Navigation Safety Domestic)** exam is credited for Fishing Master 4 and Master Limited under 60 GT as an alternative to NS 1, with 100 multiple-choice questions in 3 hours and a syllabus of COLREGs, light/shape/sound signals and **TP 10038**. TP 2293 Rev. 6 does not mention it. See 1.7. [P: fm4-hc-nsd]
- **Most of the written-exam content already exists** in the repo: 139 lessons and about 1 340 questions can carry the new ticket (section 3.2). The gap is the fishing-specific half of SCS 1 and the fishing oral: about **31 new lessons** (section 3.4).

---

## 1. Certificate requirements

### 1.1 What the certificate is

- Fishing Master, Fourth Class is certificate 100(s) in the MPR list. It is the lowest of four fishing master grades (100(p) to (s)). [C: fm4-mpr-sor-2007-115-xml]
- Do not confuse it with the **Certificate of Service as Master of a Fishing Vessel of Less Than 60 Gross Tonnage** (100(t), MPR s. 143, TP 2293 Ch. 25), which is a grandfather certificate for people who already had 12 months as master before the section came into force. It cannot be earned now. [C: fm4-mpr-sor-2007-115-xml, fm4-tp2293-rev6]
- MPR s. 212 Table 2 item 4 (the validity table for fishing masters): **unlimited voyage: N/A**; **near coastal Class 1** and **near coastal Class 2**: master of a fishing vessel of up to 100 gross tonnage, and officer in charge of the watch; **sheltered waters**: master of a fishing vessel of up to 100 gross tonnage. [C: fm4-mpr-sor-2007-115-xml] (The XML shows the officer-in-charge-of-the-watch cells only under the two near coastal columns. TP 2293 24.2 says "near coastal and sheltered waters". Ask an examiner which is right for sheltered waters; see section 4.)
- TP 2293 24.2 (Rev. 6) words it as: master of a fishing vessel "of not more than 100 gross tonnage engaged on a near coastal or sheltered waters voyage" and officer in charge of the watch of "a fishing vessel of any tonnage" on those voyages. [C: fm4-tp2293-rev6]
- The certificate is **not endorsed as meeting STCW** (s. 120(2)(a): valid only on a fishing vessel; also (b): near coastal Class 2 or sheltered only). It is a domestic certificate. [C: fm4-mpr-sor-2007-115-xml] (Item 4 also lists near coastal Class 1, so the (b) reason does not cover it fully; (a) does.)
- Rights are subject to any limitation written on the certificate and on the latest medical certificate (s. 120(3)). [C: fm4-mpr-sor-2007-115-xml]
- Voyage classes and the BC sheltered-waters list are the same as in the Master, Limited brief (section 1.1 there). Reuse `canadian-regulations.voyage-classes`.
- Related certificates in the same table: **Small Vessel Operator Proficiency (SVOP)** lets the holder master a fishing vessel of up to 15 GT or not more than 12 m on near coastal Class 2 and sheltered voyages; the **Pleasure Craft Operator Card** covers the same size within 2 nm of shore (Table 2 items 6 and 7). A master of such a vessel with at least 7 fishing seasons before the section came into force needs no SVOP (s. 212(8)). These are the people who typically upgrade to Fishing Master 4. [C: fm4-mpr-sor-2007-115-xml]
- Typical BC use (inferred from the limits): prawn, crab and shrimp trap boats, trollers, gillnetters, seiners, longliners, small trawlers and dive-fishery vessels under 100 GT. [I]

### 1.2 Eligibility and application

- **Age**: at least **18 on the day the certificate is received**, and the qualifying sea service must have **started at 15 or older when acquired on a fishing vessel** for a fishing certificate (16 for other certificates) (MPR s. 110(1)). [C: fm4-mpr-sor-2007-115-xml]
- Give the examiner, **at least two weeks before the first exam**: signed application (Schedule 2), birth certificate or equivalent, discharge books or certificates, sea-service **testimonials** (Schedule 4 or 5 content), and any certificates held (s. 110(2)). [C: fm4-mpr-sor-2007-115-xml]
- The application stays valid **1 year** from receipt (s. 110(3)); exam fees are paid each time an exam is taken (s. 110(5)). If you train in an approved program you must present the completed, satisfactorily evaluated training record book (s. 110(4)). [C: fm4-mpr-sor-2007-115-xml]
- Before issue the applicant proves they are a **qualified person** (s. 111(a); a Canadian citizen or permanent resident under CSA 2001 s. 2) and provides a **medical certificate or provisional medical certificate** (see 1.5). [C: fm4-mpr-sor-2007-115-xml; sixty-csa-2001-xml]
- TC services need a Candidate Document Number (CDN); the free TC/CCOHS **Small Fishing Vessel Stability e-course** also needs one. [C: sixty-tc-stability-training]

### 1.3 Sea service

- MPR s. 142 item 1: **at least 12 months of sea service performing deck duties on one or more fishing vessels that are 6 m or more in length**. **Up to 6 months** may come from an **approved training program** if the applicant completed it. [C: fm4-mpr-sor-2007-115-xml]
- s. 142(2): up to **half** of the required deck service, **excluding any approved-program service**, may be service on **normal trading vessels of 6 m or more in length**. [C: fm4-mpr-sor-2007-115-xml]
- **No voyage-class minimum**: unlike Fishing Master Third Class (25 GT or more, voyages other than sheltered), FM4 names only the 6 m length. TP 2293 s. 3.8(1)(g) confirms that service **entirely in sheltered waters is accepted** for Fishing Master, Fourth Class. [C: fm4-mpr-sor-2007-115-xml, fm4-tp2293-rev6]
- Counting (s. 115): **8 hours = 1 day**; other watches are prorated to a maximum of **12 hours per calendar day, and a 12-hour watch equals 1.5 days**; **months = credited days ÷ 30**. So 12 months = 360 credited days, which is 240 calendar days of 12-hour work. [C: fm4-mpr-sor-2007-115-xml] (arithmetic [I])
- Service on vessels not clearly "fishing" or "trading" is assessed under TP 2293 Chapter 3 (s. 116). [C: fm4-mpr-sor-2007-115-xml] (Chapter 3 beyond 3.8 not read.)
- A year as master while holding FM4 is what counts toward Fishing Master Third Class (s. 141 item 1(b)): a reason candidates keep their testimonials. [C: fm4-mpr-sor-2007-115-xml]
- Providers: "12 months of sea service, deck duties, fishing vessel of at least 6 m (19 feet 8 inches)". [P: fm4-mun-tp15141e]

### 1.4 Training certificates (MPR s. 142 item 2: "certificates to be provided to the examiner")

| Item | Requirement as written | Tag |
|---|---|---|
| (a) | **MED with respect to basic safety** (Marine Emergency Duties) | [C: fm4-mpr-sor-2007-115-xml] |
| (b) | **Restricted Operator's Certificate (ROC-MC)** issued under the Radiocommunication Act. Unlike Master, Limited, this is required whether or not a VHF is fitted. | [C: fm4-mpr-sor-2007-115-xml] |
| (c) | **SEN Limited** (Simulated Electronic Navigation, limited) | [C: fm4-mpr-sor-2007-115-xml] |
| (d) | **Marine advanced first aid** (not the basic first aid that Master, Limited asks for) | [C: fm4-mpr-sor-2007-115-xml] |

- Course currency (s. 103): the **marine emergency duties** and **simulated electronic navigation** courses must have been completed **within the 5 years before the application date**, or be backed by a refresher in that period, by 12 months of deck service in the last 5 years (plus a drills testimonial for MED), or by 3 months of deck service in the last 12 months (plus the drills testimonial). After the certificate is issued most course passes are valid for life, **except** MED/SEN, **first aid**, tanker and passenger-vessel courses. [C: fm4-mpr-sor-2007-115-xml]
- Providers name the current MED course **Domestic Vessel Safety (MED-DVS)** (formerly MED A1/A2), the first aid course **Marine Advanced First Aid (MAFA)** and the radio course **ROC-MC**. Whether MED-DVS is accepted as "MED with respect to basic safety" is a provider translation, not in the MPR text. [P: fm4-hc-fishing-master, fm4-nscc-fm4, fm4-saferoceans-fm4]
- Typical course lengths (guide to depth only): Camosun 2022-23: CP1 90 h, NS1 60 h, ROC-MC 24 h, SEN-L 30 h, MAFA 26 h, MED-DVS 28 h, SCS1 self-directed. Holland College 2026-27: CP1, NSD and SCS1 are each 15 days; MED-DVS 4 days, MAFA 5 days, SEN-L 5 days, ROC-MC 4 days, FM4 oral prep 4 days. [P: sixty-camosun-fm4-courses, fm4-hc-fishing-master]
- Several providers also sell **self-study packages** followed by a challenge of the TC exam. [P: fm4-hc-fishing-master]

### 1.5 Medical

- s. 111(b) requires a medical certificate or provisional medical certificate **except** for certificates in s. 131 (not passenger-carrying), s. 138, s. 143, s. 151 and s. 174. **Fishing Master, Fourth Class (s. 142) is not on that list, so a medical certificate (or provisional) is required.** [C: fm4-mpr-sor-2007-115-xml]
- A provider guide agrees ("valid Marine Medical Certificate or a Provisional Marine Medical Certificate"). [P: fm4-mun-tp15141e]
- The medical standards are in Division 8 of Part 2 of the MPR, which I did not read. [I] for the content.

### 1.6 Examinations

MPR s. 142 item 3: pass **(a) chartwork and pilotage, level 1; (b) navigation safety, level 1; (c) ship construction and stability, level 1; and (d) oral examination on general seamanship, after meeting the other requirements of this table.** [C: fm4-mpr-sor-2007-115-xml]

| Exam | Format (TP 2293 Rev. 6) | Pass mark | Status |
|---|---|---|---|
| **C/P 1 Chartwork and Pilotage, level 1** (12.3, via 24.3) | Practical chartwork **and** multiple-choice; **unlimited duration**; C/P 2 may be substituted at the applicant's request | **70 %** (4.3(1)(k)) | [C: fm4-tp2293-rev6]. Providers describe 10 MCQ plus 5 chartwork problems in 3 hours for Master, Limited; not verified for FM4. [P: sixty-holland-cp1] |
| **NS 1 Navigation Safety, level 1** (11.4, via 24.4) | Multiple-choice; **1.5 hours**; COLREGs with Canadian modifications incl. distress signals; STCW A-VIII/2 and B-VIII/2 watchkeeping; bridge teamwork | **70 %** (4.3(1)(a)) | [C: fm4-tp2293-rev6]. Providers say 100 questions in 2 hours. [P: sixty-holland-ns1] See 1.7 for NS-D. |
| **SCS 1 Ship Construction and Stability, level 1** (24.5) | Multiple-choice **and** practical calculation questions **based on a vessel's stability data booklet**; **3 hours**; **open book** (list below); SCS 2 may be substituted at the applicant's request | **70 %** (4.3(1)(f)) | [C: fm4-tp2293-rev6] |
| **Oral on general seamanship, FM 4-O** (24.6) | Scenario-based oral; **unlimited duration**; held last, after the other requirements | Not stated for this oral (see below) | [C: fm4-tp2293-rev6] |

- **SCS 1 open-book resources** allowed in the room: (a) Code of Safety for Fishermen and Fishing Vessels, Parts A and B; (b) FAO/ILO/IMO Voluntary Guidelines for the design, construction and equipment of small fishing vessels; (c) B/P Skate, Ship Stability Booklet; (d) Small Fishing Vessel Inspection Regulations; (e) Large Fishing Vessel Inspection Regulations. [C: fm4-tp2293-rev6] The two inspection regulations have since been replaced (the current regulation is the Fishing Vessel Safety Regulations, C.R.C., c. 1486, which applies to Canadian fishing vessels of up to 24.4 m and up to 150 GT, s. 3). Check today's list with an examiner. [C: fm4-fvsr-crc-1486-xml for the regulation; the replacement claim [I]]
- **Pass marks, TP 2293 4.3**: 70 % for the nautical written exams listed, including "navigation safety, levels 1 and 2", "ship construction and stability, levels 1 to 5", "chartwork and pilotage, level 1 and level 2" and "general seamanship"; 60 % for any exam not named (4.3(3)). The list heads "nautical written examinations", so whether the 70 % applies to the **oral** is unclear. [C: fm4-tp2293-rev6] Our mocks should use 70 % as a placeholder for all four.
- **Retakes (2.12)**: after failing a 70 % exam the wait before the same exam is **none** for 61-69 %, **1 month** for 51-60 %, then one more month per 10 points down to **6 months** below 10 %. Within **5 days** of results you may ask the examiner for a review (2.13). Following the exam procedures is mandatory: not following them is an automatic fail (MPR s. 112). [C: fm4-tp2293-rev6, fm4-mpr-sor-2007-115-xml]
- **Scheduling (4.4)**: the standard written blocks include NS 1 (fourth business day, a.m.) and SCS 1 (fourth business day of the Block B week, p.m.), but a note says "Fishing Master examinations are held on appointment only when the examiner is travelling to a location to administer the examinations". Oral exams are always by appointment, and a candidate who does not appear forfeits the fee (4.5). [C: fm4-tp2293-rev6] The current booking practice is not verified.
- **Fees (MPR s. 121)**: written exam **$27.50**; oral exam for a limited or restricted certificate $27.50; oral exam for any other certificate **$55.00**. FM4 is neither limited nor restricted, so the oral is **$55.00** [I from the table]. [C: fm4-mpr-sor-2007-115-xml]
- **A passing grade stays valid 5 years** after the exam date for exams taken for a Master, Mate, Able Seafarer or Bridge Watch Rating certificate, and for life once the certificate is obtained (s. 113). [C: fm4-mpr-sor-2007-115-xml] (That an FM4 exam counts as "a Master" exam is [I].)
- Exams written in order: no order is prescribed except that the oral comes after the other requirements (TP 2293 4.2; MPR s. 142). [C]

### 1.7 NS 1 versus NS-D (open question)

- **Claim**: Holland College's NSD page says TC allows **NS-D (Navigation Safety Domestic)** as an alternative to NS 1 for **Fishing Master, Fourth Class** and **Master, Limited under 60 GT** only. NS-D is "a three-hour exam and consists of 100 multiple choice questions worth 1 mark each", covering "Collision Regulations under the Canada Shipping Act, 2001; International Light, Shape and Sound Signals; and Small Fishing Vessel Safety Manual TP10038". Holders who want a higher certificate must write NS 1 instead. [P: fm4-hc-nsd]
- Holland College's FM4 course list shows **NSD, not NS 1**, for Fishing Master 4; its Fishing Master 3 list shows NS 1. [P: fm4-hc-fishing-master]
- Other providers still list NS 1 for FM4 (NSCC "Navigation Safety 1 (NS 1, MARI 7121)"; Safer Oceans "NS1 ... for Master Limited, <60 GT; Fishing Master 4; and Master 150"). [P: fm4-nscc-fm4, fm4-saferoceans-fm4]
- **TP 2293 Rev. 6 has no NS-D**: its FM4 chapter says NS 1 (24.4). NS-D may come from a later TP 2293 edition or a TC notice. TC's TP 2293 landing page is dated 2024-03-26 and mentions neither. I could not find the TC notice that Holland College cites. [C: fm4-tp2293-rev6; P: fm4-tc-tp2293-landing]
- **Plan**: build for NS 1 (COLREGs plus watchkeeping), which the repo largely has, and add the **TP 10038** lesson (3.4) so the candidate is covered for NS-D too. Do not write NS-D questions until TC or an examiner confirms the exam exists and its syllabus.

### 1.8 The oral, FM 4-O (TP 2293 24.6)

Candidates must show they can "apply the knowledge ... by appropriate responses, anticipations and reactions to a range of routine, non-routine and contingency scenarios as presented by the examiner" from the point of view of the duties of a Fourth Class fishing master. The exam is of **unlimited duration**. The subjects (section 2.4) are:

- Communications: lifesaving and distress signals.
- Manoeuvre the vessel: ship-handling in various conditions.
- Meteorology: forecasts and warnings.
- Safety and emergencies: safe working practices; heavy weather; responding to emergencies.
- Stability: practical use of stability information; stability during fishing operations and in adverse weather.
- Pollution prevention.
- Safe navigation: master's responsibilities; the navigational watch and the Collision Regulations.

[C: fm4-tp2293-rev6]

General oral procedure (2.11): the oral measures suitability through practical situations and the ability to communicate; questions concentrate on the practical duties and the applicable regulations, with attention to weaknesses seen in the written exams; an individual question sheet with marks per subject is prepared for each applicant; questions and answers are recorded so a third party can understand them. [C: fm4-tp2293-rev6] A BC provider sells a 4-day "FM4 Oral Exam Preparation" course. [P: fm4-hc-fishing-master]

### 1.9 Limits written on the certificate and on the vessel

- The master of a fishing vessel must have on board **a master, and enough deck officers for the deck watch** (MPR s. 212(4)). The person in charge of the deck watch holds a certificate that authorizes watchkeeping and radio-watch duties, and, if the vessel has a VHF, a radio operator certificate appropriate to the class of vessel and area (s. 216(2)(a)). Vessels of 5 GT or more need an additional person in the deck watch, who must hold an Able Seafarer or Bridge Watch Rating certificate only if the vessel is 500 GT or more (s. 216(2)(b)). [C: fm4-mpr-sor-2007-115-xml]
- Engineers on fishing vessels: s. 222 requires a **Fourth-class** engineer on near coastal Class 2 or sheltered voyages for propulsive power of **750 to 2 999 kW** (Third-class from 3 000 kW). Below 750 kW no engineer certificate is required by that table. [C: fm4-mpr-sor-2007-115-xml]
- **Hours of work and rest**: MPR Part 3 does not apply to fishing vessels except ss. 319, 334 and 335 (s. 301(2)); ss. 320, 322 and 323 apply to fishing vessels of **100 GT or more** (s. 319(1)). A vessel of up to 100 GT is therefore mostly outside the hours-of-rest rules, with the edge case of a vessel of exactly 100 GT. Do not tag the hours-of-rest lessons for this ticket without an expert check. [C: fm4-mpr-sor-2007-115-xml]
- **FVSR scope**: the Fishing Vessel Safety Regulations (C.R.C., c. 1486) apply to Canadian fishing vessels of up to 24.4 m in length and up to 150 GT (s. 3), so they cover every vessel an FM4 holder is likely to command. They are the working law behind the SCS 1 stability half and much of the oral. [C: fm4-fvsr-crc-1486-xml] Provisions read word for word for this brief:
  - **s. 3.04**: keep the vessel, machinery and equipment in safe operating condition and keep **maintenance records**. **s. 3.05**: no operating in an area under an Environment Canada **freezing-spray warning** unless the stability assessment shows the vessel can operate safely in freezing spray, and the vessel must carry a means to remove accumulated ice. **s. 3.12**: keep a **record of modifications** that affect stability. **s. 3.13**: no starting a gasoline-powered vessel until the engine-space blower has run for **at least four minutes**. **s. 3.14**: no fuel leakage; fuel and oil discharges only as the pollution regulations allow.
  - **Division 3, Stability (ss. 3.44 to 3.61)**: applies to engine-propelled fishing vessels (3.44). An existing vessel that needs no assessment must still have stability adequate for its intended operations (3.45). New vessels over 6 m and up to 9 m follow recommended practices suited to the type (3.46); new vessels of 6 m or less follow Section 4 of TP 1332 (3.47). A **stability assessment by a competent person** is required (3.48(1)) when the hull is over 9 m and the vessel is new or has had a major modification or change in activity likely to hurt stability, for certain older herring/capelin vessels over 15 GT, and whenever the vessel is **fitted with an anti-roll tank**. It must be a **full** assessment when fish in bulk with free surface effect are carried unless the exceptions apply (3.48(2)). A full assessment produces a **stability booklet**; a simplified one a **record of stability** (3.57(b)); a **stability notice** with a graphical statement of operating practices and whether the vessel was assessed for freezing spray is also prepared (3.57 and following). The authorized representative must **keep a copy on board** and give the Minister a copy on request (3.58), the vessel must be **operated within its safe operating limits and as the booklet or record says** (3.59), and the **stability notice must be posted conspicuously and the crew familiar with it** (3.6). Where the notice does not fully describe the operating practices, written plain-language procedures are required (3.61).
  - The remaining headings (life-saving appliances, navigation and firefighting equipment, bilge pumping, fuel systems, steering gear, underwater fittings, hull construction, electrical, immersion suits, recovery and rescue boats) were listed but not read; writers read them before writing the construction and equipment lessons.
- The examiner may modify the exam for the vessel, area and equipment where a certificate is limited; that scope-trimming rule (TP 2293 12.11) is written for Master, Limited. Whether any equivalent applies to FM4 is not stated in Chapter 24. [C: fm4-tp2293-rev6; the FM4 conclusion is [I]]

### 1.10 Validity and renewal

- Every Canadian maritime document is valid for the period the Minister specifies (CSA 2001 s. 17); the commonly quoted 5 years is not in the texts read. [C: sixty-csa-2001-xml; 5 years [I]]
- Renewal (MPR s. 106, which covers certificates 100(a) to (t), so FM4) is by **one** of: (a) **12 months** of sea service as master or mate in charge of the deck watch in the last 5 years, or **3 months** in the last 12; (b) a **refresher in marine emergency duties** in the last 5 years **plus a written exam on navigation safety and an oral exam on general seamanship** passed in that period (s. 106(1)(b)(ii)); (c) refresher MED plus a ship management course; or (d) 24 months in listed shore marine jobs in the last 5 years, or a prorated mix (s. 106(2)). The "on the vessel and in the area" restriction in s. 106(3) is written for Master, Limited only. [C: fm4-mpr-sor-2007-115-xml]

### 1.11 Things candidates confuse

| Item | Status for Fishing Master, Fourth Class | Tag |
|---|---|---|
| **Fishing Master 4 vs Master, Limited < 60 GT** | Different tickets: FM4 is for fishing vessels only, up to 100 GT, needs 12 months of service (not 2), the radio, SEN-L and advanced first aid certificates always, a medical always, and the SCS 1 exam. | [C: fm4-mpr-sor-2007-115-xml] |
| **SEN Limited** | Required for FM4 (item 2(c)); not required for Master, Limited under 60 GT (s. 131). | [C: fm4-mpr-sor-2007-115-xml] |
| **Marine advanced first aid vs marine basic first aid** | FM4 needs advanced. | [C: fm4-mpr-sor-2007-115-xml] |
| **Radio** | FM4 needs ROC-MC regardless of whether a VHF is fitted. | [C: fm4-mpr-sor-2007-115-xml] |
| **Medical** | Required (not exempt under s. 111(b)). | [C: fm4-mpr-sor-2007-115-xml] |
| **Certificate of Service, fishing vessel < 60 GT** | Grandfather certificate, s. 143; cannot be earned now. | [C: fm4-mpr-sor-2007-115-xml] |
| **SVOP** | Lets you master a fishing vessel up to 15 GT or 12 m; not a Fishing Master ticket. | [C: fm4-mpr-sor-2007-115-xml] |
| **SCS 1 vs the Master, Limited stability exams** | FM4 uses SCS 1 (fishing vessels, stability booklet). Master, Limited under 60 GT uses SCS 3 or SCS 4 per TP 2293 12.5 and 12.6. | [C: fm4-tp2293-rev6] |
| **NS 1 vs NS-D** | See 1.7. | [P: fm4-hc-nsd] |

---

## 2. Syllabus by exam and subject

Each item: topic, then tag. Wording below is a paraphrase of TP 2293 Rev. 6 (the TP grants permission to copy with
acknowledgement, but we write in our own words). "Exam" says where it is most likely examined: **C/P 1**, **NS 1**,
**SCS 1**, **Oral**.

### 2.1 Chartwork and pilotage (C/P 1; TP 2293 12.3 via 24.3)

Competence: *plan and conduct safe navigation*. [C: fm4-tp2293-rev6]

- How charts are built and used: the chart as an aid; practical effects of projection distortion, numbering and presentation of information; what affects reliability; using Mercator and polyconic charts.
- Publications: light characteristics, colours and sound signals; List of Lights, Buoys and Fog Signals; tide tables; radio aids to marine navigation; sailing directions; the Canadian buoyage system; Notices to Shipping and Notices to Mariners and chart corrections; Chart No. 1 symbols and abbreviations.
- Position by landmarks, by aids to navigation (lighthouses, beacons, buoys) and by dead reckoning allowing for wind, tides, currents and estimated speed.
- Chartwork exercises: position from simultaneous true bearings, or bearing and distance, or two or more distances; latitude and longitude both ways; laying off courses and measuring direction and distance; DR from course, speed and time; appreciation that current or wind changes course and speed over the ground; speed over the ground and true course made good between fixes.
- Log book and compass-error record: keeping the record; care of dividers and parallel rules; operator checks and compass error from true terrestrial bearings or headings; recording deviation; magnetic against gyro; correcting courses and bearings for error, variation and deviation; using the deviation table.

### 2.2 Navigation Safety (NS 1; TP 2293 11.4 via 24.4)

Competence: *maintain a safe navigational watch*. [C: fm4-tp2293-rev6]

- Thorough knowledge of the Collision Regulations with Canadian modifications as amended, including distress signals.
- The principles of keeping a navigational watch in STCW Code A-VIII/2 (at sea, in different conditions and areas, at anchor, in port) and the guidance in B-VIII/2.
- Bridge teamwork: conduct, handover and relief of the watch; proper look-out at all times; responsibility for navigation clearly defined, including when the master is on the bridge and under pilotage; coordinated bridge tasks; understood communications and acknowledgements; verified grounds for decisions; prompt reporting and correction of mistakes and unclear situations.
- Fishing-relevant rules inside the COLREGs: Rule 26 (fishing vessels), Annex II (fishing vessels in close proximity), Rules 9 and 18 (fishing vessels and narrow channels, order of priority). [I for emphasis]

### 2.3 Ship Construction and Stability (SCS 1; TP 2293 24.5)

[C: fm4-tp2293-rev6] Two competences.

**A. Maintain vessel stability**

- *Basic terminology*: displacement, deadweight, lightship weight, load displacement; list, heel, loll; gravity, centre of gravity (G), KG; buoyancy, centre of buoyancy (B), reserve buoyancy; righting lever (GZ), metacentre (M), metacentric height (GM), roll period as an indication of initial stability; centre of flotation (F) and trim; draft, freeboard, deck-edge immersion, downflooding.
- *Transverse stability principles*: effect of water density on draft and freeboard and the **Fresh Water Allowance**; a sketch of a heeled vessel showing how G and B create GZ; adding, removing, transferring and suspending weights; stable, unstable and neutral equilibrium; correcting unstable and neutral equilibrium and angle of loll; stiff and tender vessels; negative GM and angle of loll; free surface effect and slack tanks; moment of statical stability; effect of reduced freeboard and overloading.
- *Practical use of stability data supplied to fishing vessels*: **displacement and TPI/TPC scales** to get displacement from draft and back; reading a fishing vessel's **stability booklet**; using **pre-calculated operating conditions**; recognising when the vessel is outside them and rectifying it; **fish loading limits** given fuel, water, crew and provisions; interpreting **curves of statical stability**; effects of reduced freeboard and overloading.
- *Effect of operations and catch handling*: active versus passive gear; deploying and embarking gear; dangerous external forces when gear snags on the bottom or acts on a high point; effect of adding, removing, transferring, raising, lowering or suspending weights on draft, list and trim with free surface; changes during the voyage; strength and purpose of **division bulkheads** in fish holds; fish carried in bulk versus subdivided holds or containers; **fish on deck**; overloading and excessive trim by the stern; **anti-rolling devices** (paravane stabilizers, anti-rolling tanks), their effect and risks.
- *Environmental effects*: severe wind and rolling, especially in **following seas**; water on deck and free surface; **ice accretion**.
- *Modifications*: gear or fishing-gear modifications; hull or superstructure modifications; holds converted from dry to wet stowage.
- *Estimating GM and KG*: general understanding of the **inclining test** and the **rolling test**.

**B. Maintain seaworthiness of the vessel (construction)**

- *Terminology*: length overall, length between perpendiculars, breadth, depth, moulded dimensions, baseline, gross and net tonnage; open and closed construction; weathertight and watertight; principal structural members and proper names of parts.
- *Types and methods*: wood, fibre-reinforced plastic, steel and aluminium hulls; design variations of trap vessels, stern and side trawlers, seiners, longliners, gillnetters, dredgers.
- *Integrity*: how weathertight and watertight integrity is kept; **freeing ports and water-freeing arrangements** and how the minimum size and number are determined; doors, door sills, windows, portholes and access openings; ventilators and air pipes; **hatch closures and fish scuttle covers**; sounding devices; crew protection by bulwarks, rails and guards; preventing water entering through hull valves and the shaft.
- *Survivability and damage control*: bulkheads as strength members and for watertight integrity, especially the **collision bulkhead**; bilge and pump systems and water-level detectors; damage-control techniques for small and large hull breaches, damaged through-hull fittings, split piping, chafed hoses and packing glands.
- *Fire protection*: purpose and operation of **quick-closing valves on fuel tanks**, **fire dampers on ventilators** and fire-extinguishing systems.
- *Inspection and maintenance*: maintaining compliance, hull, machinery and equipment in good order, **corrosion and cathodic protection**; awareness of TC's inspection regime (construction and installation, initial, periodic, random, annual self-inspection, targeted).

### 2.4 Oral on general seamanship (FM 4-O; TP 2293 24.6)

[C: fm4-tp2293-rev6]

- **Lifesaving and distress signals**: recognise and know the meanings of the distress signals in the Collision Regulations and the lifesaving and distress signals in the International Code of Signals.
- **Ship-handling**: basic manoeuvring and handling of a fishing vessel; practical berthing and unberthing and manoeuvres during fishing operations; effect of wind, tide and current.
- **Meteorology**: why forecasts matter and how to consult them; using forecasts in voyage planning; using actual and forecast weather to carry on or interrupt fishing, return to port or seek shelter; Environment Canada warnings and how they are broadcast.
- **Safe working practices**: practical knowledge of safe working on fishing vessels; the Code of Safe Working Practices as it applies to fishing vessels; awareness of the **Small Fishing Vessel Safety Manual (TP 10038)**.
- **Heavy weather**: precautions before it arrives (close and secure watertight hatches, fish scuttles, doors and portholes; lower and secure weights; make sure freeing arrangements work); preventing water ingress and flooding; monitoring such as water detection in compartments; action if water gets in or the vessel is disabled; boat handling; preventing ice and what to do when ice forms.
- **Emergencies**: the master's responsibilities in fire, collision, flooding, grounding, man overboard and evacuation; rescuing people; assisting vessels and aircraft in distress.
- **Practical stability and stability information**: practical use of the vessel's stability data; reading the vessel's plans and specifications; weights added, removed, transferred, raised, lowered or suspended; free surface; change over the voyage; stiff and tender; reduced freeboard.
- **Stability during fishing operations and in adverse weather**: forces from snagged or high-lead gear; division bulkheads in holds; fish in bulk and on deck; overloading; water on deck; ice; paravanes and anti-rolling tanks.
- **Pollution prevention**: precautions during fuelling; statutory requirements to report pollution incidents; preventing pollution by oil, garbage and other pollutants; acting on pollution incidents on board and found at sea.
- **Master's responsibilities**: the duties of the master of a small vessel under the Canada Shipping Act; OOW duties and tasks; principles of keeping a navigational watch; the Collision Regulations.

---

## 3. What exists, what to reuse, what to write

### 3.1 State of `content/` (origin/main 7d18bd8)

- 13 lesson modules; the relevant ones: `chartwork` (25 lessons), `tides` (8), `colregs` (47), `stability` (12), `meteorology` (6), `bridge-watch` (26), `canadian-regulations` (10), `seamanship` (30), `radio` (8), `radar` (6), `electronic-navigation` (5), `machinery` (22, SVMO), `sixty-ton` (6). Question files are named `questions-<slug>.json` with lesson ids in `lesson_id`; about 2 090 questions in all.
- **There is no fishing content at all**: no lesson mentions TP 10038, the Fishing Vessel Safety Regulations' stability booklet, division boards, anti-rolling devices or fishing gear and stability, and there is no `fishing-master-4` ticket or `fishing` module in `schemas/common.schema.json`.
- The ticket enum today is `bridge-watch-rating`, `master-limited-60gt`, `svmo`. Lessons, questions, scenarios and mock exams each carry a ticket, and a mock exam has exactly one `ticket` string.

### 3.2 Reuse: existing lessons by module, with the recommendation

"Add `fishing-master-4`?" means: add the new ticket id to the lesson's `tickets` list **and** to the `tickets` of its questions. **Yes** = syllabus topic in TP 2293 24.3 to 24.6; **Optional** = useful background or course support (decide later); **No** = out of scope or misleading for this ticket. Question counts are from `questions-*.json` on origin/main. Current tags: BWR = `bridge-watch-rating`, ML60 = `master-limited-60gt`, SVMO = `svmo`.

**Totals if every "Yes" is tagged:** 139 lessons and about 1 340 questions; "Optional" adds 43 lessons and about 460 questions.

#### `chartwork` (25 lessons: 25 Yes, 0 Optional)

Current tags: BWR/ML60, ML60. Recommendation: **Yes for all 25** (CP1). Lesson ids, in order:

`chartwork.charts-and-projections`, `chartwork.chart-datums`, `chartwork.latitude-longitude-distance`, `chartwork.chart-symbols`, `chartwork.compass-types`, `chartwork.variation`, `chartwork.deviation-card`, `chartwork.compass-error-tvmdc`, `chartwork.compass-error-by-transit`, `chartwork.speed-time-distance`, `chartwork.dead-reckoning`, `chartwork.fixes-bearings`, `chartwork.fixes-ranges-and-mixed`, `chartwork.running-fix`, `chartwork.transits-and-clearing-bearings`, `chartwork.distance-off-lights`, `chartwork.set-and-drift`, `chartwork.course-to-steer`, `chartwork.leeway`, `chartwork.course-speed-made-good`, `chartwork.tides-in-chartwork`, `chartwork.current-tables-and-passes`, `chartwork.publications-and-corrections`, `chartwork.voyage-planning-and-log`, `chartwork.cp1-problem-technique`

#### `tides` (8 lessons: 8 Yes, 0 Optional)

Current tags: BWR/ML60, ML60. Recommendation: **Yes for all 8** (CP1 (tide tables, heights, currents)). Lesson ids, in order:

`tides.what-causes-tides`, `tides.spring-and-neap`, `tides.vocabulary`, `tides.types-of-tide`, `tides.tide-tables-and-heights`, `tides.tidal-currents`, `tides.current-stations-and-slack`, `tides.planning-a-pass`

#### `colregs` (47 lessons: 47 Yes, 0 Optional)

Current tags: BWR/ML60. Recommendation: **Yes for all 47** (NS1). Lesson ids, in order:

`colregs.rule-01.application`, `colregs.rule-02.responsibility`, `colregs.rule-03.definitions`, `colregs.rule-04.section-application`, `colregs.rule-05.lookout`, `colregs.rule-06.safe-speed`, `colregs.rule-07.risk-of-collision`, `colregs.rule-08.action-to-avoid`, `colregs.rule-09.narrow-channels`, `colregs.rule-10.traffic-separation`, `colregs.rule-11.section-ii-application`, `colregs.rule-12.sailing-vessels`, `colregs.rule-13.overtaking`, `colregs.rule-14.head-on`, `colregs.rule-15.crossing`, `colregs.rule-16.give-way-action`, `colregs.rule-17.stand-on-action`, `colregs.rule-18.responsibilities-between-vessels`, `colregs.rule-19.restricted-visibility`, `colregs.steering-sailing.situations`, `colregs.rule-20.lights-shapes-application`, `colregs.rule-21.light-definitions`, `colregs.rule-22.visibility-of-lights`, `colregs.rule-23.power-driven-underway`, `colregs.rule-24.towing-pushing`, `colregs.rule-25.sailing-oars`, `colregs.rule-26.fishing-vessels`, `colregs.rule-27.nuc-ram`, `colregs.rule-28.constrained-by-draught`, `colregs.rule-29.pilot-vessels`, `colregs.rule-30.anchored-aground`, `colregs.rule-31.seaplanes-wig`, `colregs.lights.night-recognition`, `colregs.rule-32.definitions`, `colregs.rule-33.sound-equipment`, `colregs.rule-34.manoeuvring-warning`, `colregs.rule-35.restricted-visibility-signals`, `colregs.rule-36.attract-attention`, `colregs.rule-37.distress-signals`, `colregs.signals.sound-drill`, `colregs.rule-38.exemptions`, `colregs.part-f.canadian-provisions`, `colregs.annex-i.positioning-technical`, `colregs.annex-ii.fishing-close-proximity`, `colregs.annex-iii.sound-signal-appliances`, `colregs.annex-iv.distress-signals`, `colregs.ns1-review-traps`

#### `stability` (12 lessons: 11 Yes, 0 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 5 | `stability.stability-awareness` | BWR/ML60 | Yes | SCS1 |
| 10 | `stability.terms` | ML60 | Yes | SCS1 |
| 20 | `stability.g-b-and-m` | ML60 | Yes | SCS1 |
| 30 | `stability.metacentric-height` | ML60 | Yes | SCS1 |
| 40 | `stability.righting-lever-gz-curve` | ML60 | Yes | SCS1 |
| 50 | `stability.moving-adding-removing-weight` | ML60 | Yes | SCS1 |
| 60 | `stability.suspended-weights-and-lifting` | ML60 | Yes | SCS1 |
| 70 | `stability.free-surface` | ML60 | Yes | SCS1 |
| 80 | `stability.watertight-integrity-and-freeboard` | ML60 | Yes | SCS1 |
| 90 | `stability.icing` | ML60 | Yes | SCS1 |
| 100 | `stability.rolling-period-test` | ML60 | Yes | SCS1 |
| 110 | `stability.passengers-standards-and-assessment` | ML60 | No | passenger focus; write the fishing booklet lesson instead |

#### `meteorology` (6 lessons: 6 Yes, 0 Optional)

Current tags: ML60. Recommendation: **Yes for all 6** (oral: meteorology). Lesson ids, in order:

`meteorology.weather-systems`, `meteorology.wind-and-beaufort`, `meteorology.marine-warnings`, `meteorology.bc-marine-forecasts`, `meteorology.bc-local-effects`, `meteorology.go-no-go`

#### `bridge-watch` (26 lessons: 14 Yes, 10 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 10 | `bridge-watch.certificate-pathway` | BWR | No | Bridge Watch Rating pathway |
| 20 | `bridge-watch.deck-watch-and-roles` | BWR/ML60 | Yes | NS1 bridge teamwork, OOW duties |
| 25 | `bridge-watch.watchkeeping-principles` | BWR/ML60 | Yes | NS1 STCW A-VIII/2 principles |
| 30 | `bridge-watch.lookout-duties` | BWR/ML60 | Yes | NS1 watchkeeping |
| 40 | `bridge-watch.relative-bearings-and-points` | BWR/ML60 | Optional | rating-level; optional |
| 50 | `bridge-watch.lookout-reports` | BWR/ML60 | Yes | NS1 watchkeeping |
| 60 | `bridge-watch.bridge-equipment-tour` | BWR/ML60 | Optional | rating-level; optional |
| 60 | `bridge-watch.distress-recognition` | BWR/ML60 | Yes | oral: distress signals |
| 70 | `bridge-watch.radar-for-lookouts` | BWR/ML60 | Optional | rating-level; optional |
| 70 | `bridge-watch.watch-handover` | BWR/ML60 | Yes | NS1 conduct, handover and relief |
| 75 | `bridge-watch.calling-the-master` | BWR/ML60 | Yes | NS1 bridge teamwork |
| 80 | `bridge-watch.anchor-and-port-watch` | BWR/ML60 | Yes | NS1 anchor and port watch |
| 85 | `bridge-watch.restricted-visibility-watch` | BWR/ML60 | Yes | NS1 watch under different conditions |
| 90 | `bridge-watch.bridge-communications` | BWR/ML60 | Yes | NS1 bridge teamwork, closed-loop communication |
| 100 | `bridge-watch.fatigue-and-hours-of-rest` | BWR/ML60 | No | MPR Part 3 hours rules mostly do not apply to fishing vessels under 100 GT (ss. 301(2), 319) |
| 110 | `bridge-watch.helm-orders` | BWR/ML60 | Optional | rating-level; optional |
| 120 | `bridge-watch.steering-technique` | BWR/ML60 | Optional | rating-level; optional |
| 130 | `bridge-watch.steering-in-confined-waters` | BWR/ML60 | Optional | rating-level; optional |
| 140 | `bridge-watch.steering-systems-and-checks` | BWR/ML60 | Optional | rating-level; optional |
| 150 | `bridge-watch.autopilot-changeover` | BWR/ML60 | Optional | rating-level; optional |
| 160 | `bridge-watch.magnetic-and-gyro-compass` | BWR/ML60 | Optional | rating-level; optional |
| 170 | `bridge-watch.compass-error` | BWR/ML60 | Optional | rating-level; optional |
| 180 | `bridge-watch.emergency-alarms-and-muster` | BWR/ML60 | Yes | oral: emergencies, evacuation |
| 190 | `bridge-watch.man-overboard` | BWR/ML60 | Yes | oral: respond to emergencies |
| 200 | `bridge-watch.pyrotechnics-epirb-sart` | BWR/ML60 | Yes | oral: lifesaving and distress signals |
| 210 | `bridge-watch.survival-craft-and-rescue-boats` | BWR/ML60 | Yes | oral: vessel evacuation |

#### `canadian-regulations` (10 lessons: 5 Yes, 3 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 10 | `canadian-regulations.csa-roles-and-duties` | ML60 | Yes | oral: master responsibilities under the CSA |
| 20 | `canadian-regulations.distress-assistance-collision` | ML60 | Yes | oral: rescuing persons, assisting vessels and aircraft |
| 30 | `canadian-regulations.voyage-classes` | ML60 | Yes | certificate validity is by voyage class |
| 40 | `canadian-regulations.mpr-crewing-and-watch` | ML60 | Optional | fishing vessel crewing differs (s. 212 Table 2); needs a fishing version |
| 50 | `canadian-regulations.crew-training-and-first-aid` | BWR/ML60 | Optional | check s. 205 applies to the candidate's vessel |
| 60 | `canadian-regulations.hours-of-rest` | BWR/ML60 | No | Part 3 hours rules mostly do not apply to fishing vessels under 100 GT |
| 70 | `canadian-regulations.svr-passenger-vessels` | ML60 | No | passenger vessel rules |
| 200 | `canadian-regulations.safe-working-practices` | BWR | Yes | oral: safe working practices (check applicability to fishing vessels) |
| 210 | `canadian-regulations.enclosed-spaces-and-permits` | BWR | Optional | useful for fish holds and tanks |
| 220 | `canadian-regulations.pollution-prevention` | BWR/ML60 | Yes | oral: pollution prevention and reporting |

#### `seamanship` (30 lessons: 17 Yes, 10 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 10 | `seamanship.buoyage-lateral` | BWR/ML60 | Yes | CP1: Canadian buoyage |
| 20 | `seamanship.buoyage-cardinal-and-other-marks` | BWR/ML60 | Yes | CP1: Canadian buoyage |
| 30 | `seamanship.special-buoys-daybeacons-ranges` | BWR/ML60 | Yes | CP1: aids to navigation |
| 40 | `seamanship.light-characteristics` | BWR/ML60 | Yes | CP1: light characteristics |
| 50 | `seamanship.ship-terms-and-structure` | BWR/ML60 | Yes | SCS1 construction terms |
| 60 | `seamanship.vessel-types-and-organisation` | BWR/ML60 | Optional | optional |
| 70 | `seamanship.rope-types-and-strength` | BWR/ML60 | Optional | optional |
| 80 | `seamanship.knots-bends-hitches` | BWR/ML60 | Optional | optional |
| 90 | `seamanship.splices-whippings-seizings` | BWR | Optional | optional |
| 100 | `seamanship.wire-chain-shackles` | BWR/ML60 | Optional | optional |
| 110 | `seamanship.mooring-operations` | BWR/ML60 | Yes | oral: berthing and unberthing |
| 120 | `seamanship.anchoring-operations` | BWR/ML60 | Optional | optional |
| 130 | `seamanship.deck-machinery-and-signals` | BWR/ML60 | Optional | optional |
| 140 | `seamanship.rigging-access-equipment` | BWR/ML60 | Optional | optional |
| 150 | `seamanship.flags-and-single-letter-signals` | BWR/ML60 | Yes | oral: International Code of Signals |
| 160 | `seamanship.draft-marks-and-soundings` | BWR/ML60 | Yes | SCS1 draft and freeboard |
| 170 | `seamanship.cargo-and-stores` | BWR/ML60 | No | cargo ship focus |
| 180 | `seamanship.maintenance-and-repair` | BWR | No | deck maintenance for ratings |
| 200 | `seamanship.machinery-and-systems` | ML60 | Optional | optional |
| 210 | `seamanship.shiphandling` | ML60 | Yes | oral: ship-handling |
| 220 | `seamanship.heavy-weather` | ML60 | Yes | oral: heavy weather |
| 230 | `seamanship.towing-and-girding` | ML60 | Optional | optional |
| 240 | `seamanship.fire-response` | BWR/ML60 | Yes | oral: fire |
| 250 | `seamanship.flooding-and-damage-control` | ML60 | Yes | oral and SCS1: flooding, damage control |
| 260 | `seamanship.man-overboard-recovery` | BWR/ML60 | Yes | oral: man overboard |
| 270 | `seamanship.grounding-and-collision-response` | ML60 | Yes | oral: collision, grounding |
| 280 | `seamanship.abandon-ship-cold-water` | BWR/ML60 | Yes | oral: vessel evacuation |
| 290 | `seamanship.calling-for-help` | BWR/ML60 | Yes | oral: distress |
| 300 | `seamanship.drills-and-emergency-procedures` | ML60 | Yes | oral: emergency response |
| 310 | `seamanship.passenger-safety-management` | ML60 | No | passenger focus |

#### `radio` (8 lessons: 2 Yes, 6 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 10 | `radio.vhf-basics-for-ratings` | BWR/ML60 | Optional | ROC-MC course support, not examined by TC |
| 20 | `radio.certificates-and-carriage` | ML60 | Optional | ROC-MC course support, not examined by TC |
| 30 | `radio.vhf-channels-and-listening-watch` | BWR/ML60 | Optional | ROC-MC course support, not examined by TC |
| 40 | `radio.navigation-safety-calls` | ML60 | Optional | ROC-MC course support, not examined by TC |
| 50 | `radio.dsc-and-gmdss` | ML60 | Optional | ROC-MC course support, not examined by TC |
| 60 | `radio.distress-urgency-safety` | BWR/ML60 | Yes | oral: distress; ROC-MC |
| 70 | `radio.false-alerts-and-records` | ML60 | Optional | ROC-MC course support, not examined by TC |
| 80 | `radio.epirb-sart-plb` | ML60 | Yes | oral: distress alerting |

#### `radar` (6 lessons: 0 Yes, 6 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 1 | `radar.principles-and-controls` | ML60 | Optional | SEN-L course support, not examined by TC |
| 2 | `radar.displays-and-errors` | ML60 | Optional | SEN-L course support, not examined by TC |
| 3 | `radar.position-fixing` | ML60 | Optional | SEN-L course support, not examined by TC |
| 4 | `radar.plotting-cpa-tcpa` | ML60 | Optional | SEN-L course support, not examined by TC |
| 5 | `radar.target-course-speed-and-action` | ML60 | Optional | SEN-L course support, not examined by TC |
| 6 | `radar.arpa-sart-racon` | ML60 | Optional | SEN-L course support, not examined by TC |

#### `electronic-navigation` (5 lessons: 0 Yes, 5 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 1 | `electronic-navigation.gnss` | ML60 | Optional | SEN-L course support, not examined by TC |
| 2 | `electronic-navigation.ais` | ML60 | Optional | SEN-L course support, not examined by TC |
| 3 | `electronic-navigation.ecs-ecdis-and-apps` | ML60 | Optional | SEN-L course support, not examined by TC |
| 4 | `electronic-navigation.plotters-and-integration` | ML60 | Optional | SEN-L course support, not examined by TC |
| 5 | `electronic-navigation.limits-and-cross-checking` | ML60 | Optional | SEN-L course support, not examined by TC |

#### `machinery` (22 lessons: 4 Yes, 3 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 10 | `machinery.materials.common-ship-materials` | SVMO | Yes | SCS1: corrosion and cathodic protection |
| 100 | `machinery.engines.how-a-diesel-works` | SVMO | No | engineer-level (SVMO) |
| 110 | `machinery.engines.start-run-and-faults` | SVMO | No | engineer-level (SVMO) |
| 120 | `machinery.fuels.storage-and-contamination` | SVMO | No | engineer-level (SVMO) |
| 130 | `machinery.cooling.fresh-and-raw-water` | SVMO | No | engineer-level (SVMO) |
| 140 | `machinery.lubricants.oils-and-handling` | SVMO | No | engineer-level (SVMO) |
| 200 | `machinery.electrical.basics-and-meters` | SVMO | No | engineer-level (SVMO) |
| 210 | `machinery.electrical.protection-and-distribution` | SVMO | No | engineer-level (SVMO) |
| 220 | `machinery.electrical.batteries` | SVMO | No | engineer-level (SVMO) |
| 230 | `machinery.hydraulics.systems` | SVMO | No | engineer-level (SVMO) |
| 240 | `machinery.pneumatics.compressors-and-receivers` | SVMO | No | engineer-level (SVMO) |
| 300 | `machinery.pumps.types-and-faults` | SVMO | Optional | bilge pumps; engineer-level otherwise |
| 310 | `machinery.piping.systems-and-precautions` | SVMO | Optional | bilge systems; engineer-level otherwise |
| 320 | `machinery.transmission.shafting-and-gearboxes` | SVMO | No | engineer-level (SVMO) |
| 330 | `machinery.steering.gear-and-checks` | SVMO | No | engineer-level (SVMO) |
| 340 | `machinery.underwater.fittings` | SVMO | Yes | SCS1: water ingress through hull openings and shaft |
| 350 | `machinery.deck.deck-machinery` | SVMO | No | engineer-level (SVMO) |
| 400 | `machinery.safety.fire-hazards-and-storage` | SVMO | Optional | engine-room fire hazards |
| 410 | `machinery.safety.fire-and-watertight-equipment` | SVMO | Yes | SCS1: quick-closing valves, dampers, extinguishing systems |
| 420 | `machinery.safety.safe-working-practices` | SVMO | No | engineer-level (SVMO) |
| 430 | `machinery.pollution.prevention-and-bunkering` | SVMO | Yes | oral: precautions during fuelling |
| 500 | `machinery.watchkeeping.rounds-logs-and-emergencies` | SVMO | No | engineer-level (SVMO) |

#### `sixty-ton` (6 lessons: 0 Yes, 0 Optional)

| Order | Lesson id | Current tags | Add `fishing-master-4`? | Why |
|---|---|---|---|---|
| 10 | `sixty-ton.certificate-overview` | ML60 | No | Master, Limited pathway; write a fishing version |
| 20 | `sixty-ton.requirements-checklist` | ML60 | No | Master, Limited pathway; write a fishing version |
| 30 | `sixty-ton.counting-sea-service` | ML60 | No | Master, Limited pathway; write a fishing version |
| 40 | `sixty-ton.exams-and-format` | ML60 | No | Master, Limited pathway; write a fishing version |
| 50 | `sixty-ton.oral-exam-guide` | ML60 | No | Master, Limited pathway; write a fishing version |
| 60 | `sixty-ton.know-your-vessel` | ML60 | No | Master, Limited pathway; write a fishing version |


Notes on the tables above:

- **Tagging touches content owned by other lanes** (and every question file's `tickets`), so it should be one dedicated tagging PR after the schema PR, not part of each content PR. See 3.7.
- `bridge-watch` and `seamanship` lessons tagged Yes are rating-level in places (look-out, man overboard, survival craft). They are included because the FM4 oral asks the master about the same emergencies and the NS 1 watchkeeping topics are identical; the master-level lessons (`seamanship.fire-response`, `flooding-and-damage-control`, `abandon-ship-cold-water`) matter more.
- `radar`, `electronic-navigation` and `radio` are course support for **SEN Limited** and **ROC-MC**, which are required certificates but not TC written exams in TP 2293 Ch. 24. Leave them Optional until the user wants to support those courses.
- `machinery` is the SVMO engineer module. Only four lessons overlap with the SCS 1 construction list (corrosion and anodes, underwater fittings, quick-closing valves and dampers, fuelling precautions). They carry the `svmo` ticket today; adding a second ticket is fine, but the writers should read them for engineer-level depth that FM4 does not need.
- Do **not** tag `canadian-regulations.hours-of-rest`, `svr-passenger-vessels` or `stability.passengers-standards-and-assessment`: they are written for passenger vessels or for rules that mostly do not apply to fishing vessels (1.9).

### 3.3 Gaps: what the written exams and the oral need that the repo does not have

| TP 2293 topic | Existing cover | Gap |
|---|---|---|
| Stability terms, G/B/M, GM, GZ, weights, free surface, icing, rolling period | `stability.*` (11 lessons, 101 questions) | Fishing framing only; fine as a base |
| Stability booklet, pre-calculated operating conditions, fish loading limits | none | Whole topic; the SCS 1 calculation exam is built on it |
| Displacement from draft, TPI/TPC scales, Fresh Water Allowance | `stability.terms` touches displacement | TPC/TPI scale reading and FWA calculations |
| Statical stability curves in a booklet | `stability.righting-lever-gz-curve` | Reading a booklet curve and its limits |
| Fishing gear, snagged gear, catch handling, division boards, fish in bulk and on deck | none | Whole topic |
| Anti-rolling devices (paravanes, tanks) | none | Whole topic |
| Modifications and wet/dry hold conversions | none | Whole topic; FVSR s. 3.12 and 3.48 |
| Inclining test | `stability.rolling-period-test` covers the rolling test | Inclining test overview |
| Hull terms, hull materials, fishing vessel types | `seamanship.ship-terms-and-structure`, `machinery.materials...` | Fishing-vessel construction and types |
| Freeing ports, hatches, fish scuttles, vents, collision bulkhead, detectors | `stability.watertight-integrity-and-freeboard`, `seamanship.flooding-and-damage-control` | Fishing-vessel specifics |
| TC inspection regime | none | Whole topic |
| TP 10038 and the Code of Safe Working Practices as applied to fishing | `canadian-regulations.safe-working-practices` (general) | TP 10038 not read; fishing deck hazards |
| FVSR operating obligations | none | Whole topic |
| International Code of Signals life-saving signals | `seamanship.flags-and-single-letter-signals`, COLREGs Annex IV | Life-saving signal table |
| Fishing-vessel ship-handling | `seamanship.shiphandling`, `mooring-operations` | Fishing-operation manoeuvres |
| Weather decisions while fishing | `meteorology.*` (6 lessons) | Scenario lesson |
| Pathway, exams and oral guidance | `sixty-ton.*` (Master, Limited) | Fishing version (the rules differ) |

### 3.4 Proposed lesson map (new lessons)

**Module and ticket.** Propose a new module **`fishing`** and ticket id **`fishing-master-4`**, both added to `schemas/common.schema.json` in their own schema PR (CLAUDE.md: the schema needs its own PR). If the user would rather not add a module, the same lessons can live in `stability` (orders 300 to 390) and `seamanship` (orders 400 and up) with the same slugs.

**Conventions.** Lesson id = `fishing.<slug>`. Every lesson tagged `fishing-master-4` (some also `master-limited-60gt` where the content is shared; the writer decides). `review_status: needs_expert_review`. Question target **at least 9 original questions per lesson**, four options, scenario and calculation items where relevant; the SCS 1 lessons (200 to 290) aim for **10 to 12** with numbers. Every `key_facts` item tested by at least one question. Source ids prefixed `fm4-`; new sources go in `content/sources/fm4.json` (section 6), written in its own first PR.

**Invented vessel.** Use one invented vessel across the stability lessons (for example a 13.7 m, 28 GT prawn and crab trap boat with a stability booklet and fish hold) so the worked examples build on each other. Do not copy the B/P Skate booklet or any real TC exam question.

#### Block A: pathway (6 lessons)

1. **fishing.certificate-overview** (10), "The Fishing Master, Fourth Class certificate". Facts: MPR s. 100(s); validity in s. 212 Table 2 item 4 (up to 100 GT; near coastal 1 and 2, sheltered; OOW); not STCW endorsed (s. 120(2)(a)); not the same as SVOP or the Certificate of Service. Sources: fm4-mpr-sor-2007-115-xml, fm4-tp2293-rev6. 9 questions.
2. **fishing.requirements-checklist** (20), "What you need before the oral". Facts: age 18, service started at 15; 12 months, 6 m; MED basic safety, ROC-MC, SEN Limited, marine advanced first aid; medical required; documents two weeks before the first exam; application valid 1 year; oral last. Sources as above. 10 questions.
3. **fishing.counting-sea-service** (30), "Counting sea service on fishing vessels". Facts: 8 hours = 1 day, 12 hours = 1.5 days, ÷ 30 per month; 6 m minimum length; up to 6 months from an approved program; half of the rest may be trading vessels; sheltered service accepted (TP 2293 3.8); testimonials. Worked examples with invented fishing seasons (trap fisheries with days in port). Sources: fm4-mpr..., fm4-tp2293-rev6. 10 questions.
4. **fishing.exams-and-format** (40), "The four exams". Facts: C/P 1, NS 1, SCS 1, oral; formats and durations from TP 2293; 70 % pass mark; SCS 1 open-book list; C/P 2 and SCS 2 substitutions; retake waiting periods (Table VIII); review within 5 days; fees ($27.50 written, $55.00 oral [I]); passing grade valid 5 years; NS 1 versus NS-D flagged. Sources: fm4-tp2293-rev6, fm4-mpr..., fm4-hc-nsd. 10 questions.
5. **fishing.oral-exam-guide** (50), "How the oral works". Facts: FM 4-O unlimited duration; scenarios (routine, non-routine, contingency); question sheet with marks per subject; the 10 competence areas of 2.4; how to answer (state the rule, then the action, then who you tell). Sources: fm4-tp2293-rev6. 9 questions.
6. **fishing.know-your-vessel** (60), "Know your own vessel". Facts: what the examiner asks about the candidate's vessel (type, gear, hold layout, tanks, freeing ports, stability booklet limits, EPIRB and life-saving equipment, engine space ventilation); where the vessel's documents are kept. A checklist lesson with no new regulatory claims beyond FVSR ss. 3.58 to 3.61. Sources: fm4-fvsr-crc-1486-xml. 9 questions.

#### Block B: construction and integrity (SCS 1 part B; 6 lessons)

7. **fishing.hull-terms-and-dimensions** (100), "Hull dimensions and construction terms". Facts: length overall, length between perpendiculars, breadth, depth, moulded dimensions, baseline, gross and net tonnage; **closed construction** = more than 50 % of the length covered full width at or above gunwale level by decks or permanent enclosures, otherwise open (FVSR s. 2); structural members and parts names; weathertight versus watertight. Link `seamanship.ship-terms-and-structure`. Sources: fm4-fvsr-crc-1486-xml, fm4-tp2293-rev6, sea-c-fao-ftp517 (check). 10 questions.
8. **fishing.hull-materials-and-vessel-types** (110), "Hulls and fishing vessel types". Facts: wood, FRP, steel and aluminium construction: strengths, weaknesses, repair and corrosion issues; BC types (trap, troll, gillnet, seine, longline, trawl, dive). Sources: needs a source (FAO/ILO/IMO Voluntary Guidelines for small fishing vessels; FAO FTP 517). 9 questions.
9. **fishing.watertight-integrity-on-fishing-vessels** (120), "Keeping water out". Facts: freeing ports and bulwarks, how minimum size and number are set; door sills, windows, portholes, access openings; ventilators and air pipes; hatch and fish scuttle covers; sounding devices; rails and guards; hull valves and the shaft; keep closures secured at sea. Link `stability.watertight-integrity-and-freeboard`. Sources: fm4-tp2293-rev6, fm4-fvsr-crc-1486-xml (read the construction headings first), sixty-fao-ftp517-stability. 10 questions.
10. **fishing.bulkheads-bilges-and-detection** (130), "Bulkheads, bilge systems and water detectors". Facts: collision bulkhead; strength and watertight role of bulkheads; bilge pumping arrangements (FVSR part on bilge pumping, to read); water-level detectors and alarms; damage-control actions for hull breach, through-hull failure, split pipe, chafed hose, packing gland. Links `seamanship.flooding-and-damage-control`, `machinery.underwater.fittings`. 10 questions.
11. **fishing.fire-protection-fishing-vessels** (140), "Quick-closing valves, dampers and extinguishing systems". Facts: purpose and operation of fuel-tank quick-closing valves, ventilator fire dampers, fixed and portable extinguishing systems; gasoline blower four minutes (FVSR s. 3.13); engine-space fire and shutdown order [I]. Links `machinery.safety.fire-and-watertight-equipment`, `seamanship.fire-response`. 9 questions.
12. **fishing.inspection-maintenance-and-corrosion** (150), "Inspection, maintenance and corrosion". Facts: maintain the vessel, machinery and equipment and keep maintenance records (FVSR s. 3.04); corrosion and cathodic protection; TC inspection regime: construction and installation, initial, periodic, random, annual self-inspection, targeted. Link `machinery.materials.common-ship-materials`. 9 questions.

#### Block C: stability for fishing vessels (SCS 1 part A; 10 lessons)

13. **fishing.stability-booklet-and-record** (200), "The stability booklet and record of stability". Facts: full assessment gives a booklet, simplified assessment a record of stability (FVSR s. 3.57(b)); a competent person does it; **when an assessment is required** (3.48(1): hull over 9 m and new, major modification or change in activity, fitted with an anti-roll tank, certain herring/capelin vessels); full assessment needed for bulk fish with free surface; copy kept on board (3.58(1)(c)); vessel operated within safe operating limits (3.59); stability notice posted and crew familiar (3.6); written procedures (3.61); transfer of ownership (3.58(2)). Sources: fm4-fvsr-crc-1486-xml, stab-fvsr-crc-1486-stability. 11 questions.
14. **fishing.displacement-tpc-and-fwa** (210), "Displacement, TPC/TPI and fresh water allowance". Facts: read displacement from draft on a booklet scale and back; TPC (tonnes per centimetre) or TPI (tons per inch) as the weight to change draft by one unit; effect of water density on draft and freeboard; FWA concept; the formulas are [I] and need a naval architecture source. Worked examples with the invented vessel. 11 questions.
15. **fishing.fish-loading-limits** (220), "Fish loading limits and pre-calculated conditions". Facts: loading conditions in the booklet; maximum catch given fuel, water, crew and provisions; recognise when the vessel is outside a pre-calculated condition and rectify (move or jettison weight, fill or empty slack tanks, lower weights); dangers of reduced freeboard and overloading. Worked example: the invented vessel leaving with half fuel, catch limit. 12 questions.
16. **fishing.statical-stability-curves** (230), "Reading a curve of statical stability". Facts: GZ against heel angle, maximum GZ and its angle, range of stability and angle of vanishing stability, deck-edge immersion and downflooding angles, initial GM from the slope, moment of statical stability = displacement × GZ [I]. Link `stability.righting-lever-gz-curve`. 10 questions.
17. **fishing.fishing-gear-and-stability** (240), "Gear, snagged gear and stability". Facts: active versus passive gear; effect of deploying and embarking gear; **external forces when gear catches the bottom or acts on a high point**; raised, lowered and suspended weights change draft, list, trim and free surface; stability changes as fuel burns and catch comes aboard. Link `stability.suspended-weights-and-lifting`. 11 questions.
18. **fishing.catch-handling-and-stowage** (250), "Catch handling and stowage". Facts: division boards and bulkheads in fish holds; **fish in bulk versus subdivided holds or containers**; fish on deck; excessive trim by the stern; free surface of fish, water and ice slurry; FVSR s. 3.48(2) conditions for needing a full assessment when carrying fish in bulk. Link `stability.free-surface`. 10 questions.
19. **fishing.anti-rolling-devices** (260), "Paravanes and anti-rolling tanks". Facts: how paravane stabilizers and anti-rolling tanks work, benefits, risks (a tank has a free surface; a tank fitted means a stability assessment is required, FVSR s. 3.48(1)(c); paravane gear snagging or lost). 9 questions.
20. **fishing.following-seas-and-icing-fishing** (270), "Severe weather, following seas and ice". Facts: effect of severe wind and rolling especially in following seas; water on deck and free surface; freezing-spray warning prohibition (FVSR s. 3.05) and ice removal means; ice accretion raises G and reduces freeboard. Links `stability.icing`, `seamanship.heavy-weather`. 10 questions.
21. **fishing.modifications-and-conversions** (280), "Gear, hull and hold modifications". Facts: gear modifications, hull or superstructure changes, dry-to-wet hold conversion; **record of modifications** (FVSR s. 3.12); a major modification or change in activity triggers a new assessment (s. 3.48(1)(a)(ii)); weight added high raises G. 9 questions.
22. **fishing.inclining-and-rolling-tests** (290), "Estimating GM and KG: inclining and rolling tests". Facts: purpose of each test; the inclining test finds GM and KG from measured heel when a known weight is moved; the rolling test estimates GM from the roll period and is approximate; when each is used. Link `stability.rolling-period-test`. 9 questions.

#### Block D: regulations and the oral (9 lessons)

23. **fishing.fvsr-master-duties** (300), "The Fishing Vessel Safety Regulations: what the master must do". Facts: applies to Canadian fishing vessels up to 24.4 m and up to 150 GT (s. 3); safe operating condition and maintenance records (3.04); freezing spray (3.05); modification record (3.12); gasoline blower four minutes (3.13); no fuel leakage (3.14); life-saving, navigation and firefighting equipment parts to read first. Sources: fm4-fvsr-crc-1486-xml. 11 questions.
24. **fishing.crewing-and-certificates-fishing** (310), "Crew, deck watch and certificates on a fishing vessel". Facts: s. 212 Table 2 for all four grades plus SVOP and PCOC; master and deck officers required (s. 212(4)); deck watch certificates and radio certificate when VHF fitted (s. 216(2)(a)); additional person at 5 GT; when an engineer is required (s. 222: 750 kW and up). Sources: fm4-mpr-sor-2007-115-xml. 10 questions.
25. **fishing.tp10038-small-fishing-vessel-safety-manual** (320), "TP 10038: the Small Fishing Vessel Safety Manual". Facts: to be written **after TP 10038 is read** (not read for this brief). The oral requires awareness of its content and practices, and a provider says NS-D examines it (1.7). Sources: fm4-tp10038, fm4-hc-nsd. 10 questions.
26. **fishing.safe-working-on-deck** (330), "Safe working practices on the fishing deck". Facts: Code of Safe Working Practices as it applies to fishing vessels (to find and register); hauling and setting gear, winches, bights, lone-hand work, lifejackets, man-overboard prevention; links `canadian-regulations.safe-working-practices`, `canadian-regulations.enclosed-spaces-and-permits` (fish holds and tanks). Sources: needs sources (TP 10038, Code of Safe Working Practices). 9 questions.
27. **fishing.heavy-weather-fishing** (340), "Heavy weather on a fishing vessel". Facts: the precautions list in 2.4 (hatches, fish scuttles, doors, portholes, weights, freeing ports); monitoring for water in compartments; actions if water gets in or the vessel is disabled; boat handling; ice prevention and action. Link `seamanship.heavy-weather`. 10 questions.
28. **fishing.emergencies-fishing-master** (350), "The master's emergency duties". Facts: fire, collision, flooding, grounding, man overboard, evacuation; rescuing persons; assisting vessels and aircraft in distress (CSA duties; link `canadian-regulations.distress-assistance-collision`). Scenario-heavy; no new regulatory claims beyond the existing lessons. 10 questions.
29. **fishing.shiphandling-fishing-operations** (360), "Handling a fishing vessel". Facts: berthing, unberthing and manoeuvres while setting or hauling gear; effect of wind, tide and current; keeping clear of gear in the water. Needs a source for gear-specific handling. Links `seamanship.shiphandling`, `seamanship.mooring-operations`. 9 questions.
30. **fishing.weather-and-fishing-decisions** (370), "Weather and the decision to fish". Facts: use of forecasts in voyage planning; carry on, interrupt, return or shelter decisions; Environment Canada warnings and how they are broadcast. A scenario lesson built on `meteorology.*` and `fishing.following-seas-and-icing-fishing`. 9 questions.
31. **fishing.lifesaving-and-distress-signals** (380), "Distress and life-saving signals". Facts: distress signals of COLREGs Annex IV; the International Code of Signals life-saving signals (source to register); recognising and reporting. Links `colregs.annex-iv.distress-signals`, `seamanship.flags-and-single-letter-signals`. 10 questions.

**Totals:** 31 new lessons and about **300 questions** (6 + 6 + 10 + 9 lessons). With the Yes-tagged existing content the FM4 bank would be about **170 lessons and 1 640 questions**, plus the Optional set.

Items the lesson map deliberately **does not** add: a pollution lesson (reuse `canadian-regulations.pollution-prevention` and `machinery.pollution.prevention-and-bunkering`), a COLREGs or chartwork refresher (reuse), and a SEN Limited or ROC-MC lesson (separate courses, 3.2).

### 3.5 Mock exams and the oral tester

- `mock-exams/fm4-ns1-practice.json`: **100 questions, 90 minutes, 70 %** (90 minutes from TP 2293 Rev. 6 11.4; providers say 2 hours). Blueprint: `colregs` 75, `bridge-watch` 25 (TP 2293 11.4 names only COLREGs, A-VIII/2 watchkeeping and bridge teamwork; the existing Master, Limited NS 1 mock also draws 5 from `tides`, which 11.4 does not list). Practice format, not the official exam.
- `mock-exams/fm4-cp1-practice.json`: multiple-choice practice from `chartwork` 18, `tides` 3, `seamanship` 4 (buoyage and lights) until the quiz engine can mark worked chart answers. Real C/P 1 is practical chartwork plus multiple choice with unlimited time.
- `mock-exams/fm4-scs1-practice-1.json` and `-2.json`: **40 questions each, 180 minutes, 70 %** [question count is our choice; TP 2293 gives only the 3-hour duration and the multiple-choice-plus-calculation shape]. Blueprint: `fishing` 24, `stability` 12, `machinery` 2, `seamanship` 2. Provide the invented stability booklet extract as a lesson attachment when the app supports it; until then calculation questions state the needed data in the stem.
- `mock-exams/fm4-nsd-practice.json`: **on hold** until NS-D is confirmed (1.7).
- The mock schema has one `ticket` per mock and only module-level blueprints, so each FM4 mock is its own file, and draws that avoid overlap need a `docs/fm4-mock-draw.json` like `docs/svmo-mock-draw.json` until the schema allows question ids.
- **Oral scenarios** (schema reserved in `scenario.schema.json`; module `fishing`, ticket `fishing-master-4`): about 33 scenarios, three for each of the ten oral competence areas in 2.4 plus three that combine them (for example, a heavy-weather passage with a snagged trap line, rising bilge and a freezing-spray warning). Each has examiner prompts and expected points tied to lesson ids. The tester (app lane) comes later; writing the scenarios is a content task that can start after Blocks C and D.

### 3.6 Question targets

- New lessons: at least 9 each, 10 to 12 for the calculation lessons. About 300 in all.
- Existing Yes-tagged content already has about 1 340 questions; do not duplicate them.
- Add a small set (about 20) of fishing-flavoured COLREGs questions (Rule 26, Annex II, a trap boat in a narrow channel) to `colregs`, as an issue for the COLREGs lane, since NS 1 candidates for fishing tickets meet these more often.

### 3.7 Suggested PR sequence

1. This brief (docs only).
2. **Schema PR** (Foundation lane): add ticket `fishing-master-4` and module `fishing` to `schemas/common.schema.json`; decide whether mock exams may list several tickets.
3. **Sources PR**: `content/sources/fm4.json` with the section 6 entries. Merge before any content PR that cites them.
4. **Tagging PR**: add `fishing-master-4` to the lessons and questions marked Yes in 3.2. Needs a small script (tools lane) so it is mechanical and lint stays green.
5. **Content PRs**: Block A, B, C, D in parallel, each with its own `content/sources/fm4-<x>.json` for any further sources; branch names `fishing/<short-task>`.
6. Mocks, draw document and oral scenarios after the lessons they draw on exist.

---

## 4. Expert-review items

1. **Current TP 2293 edition.** The text read is Revision 6 (March 2016, signed September 2016). TC's catalogue lists later editions (Rev. 8, 2021, per the Master, Limited brief) and the TC page is dated 2024-03-26. A later edition may change Chapter 24, the open-book list, pass marks or add NS-D. **Highest priority: ask a TC Marine Safety exam centre for the current Chapter 24 and the SCS 1 open-book list.**
2. **NS-D**: does it exist, is it credited for FM4, and what are its format and syllabus? TP 2293 Rev. 6 says NS 1 is 1.5 hours; providers say 2 hours (and NS-D 3 hours).
3. **Oral pass mark and fee** (4.3 does not name FM 4-O clearly; s. 121 table gives $55.00 for a non-limited oral).
4. **Officer in charge of the watch in sheltered waters**: MPR Table 2 item 4 versus TP 2293 24.2.
5. **Medical**: confirm a medical certificate is needed (s. 111(b) does not exempt s. 142) and the standard.
6. **Course names**: whether MED-DVS satisfies "MED with respect to basic safety"; whether TC accepts the current marine advanced first aid and SEN Limited courses; ROC-MC (explicit in s. 142).
7. **Sea service**: how 12 months is counted for seasonal fisheries; whether sheltered-only service is really accepted (TP 2293 3.8 says yes); how approved training program time is evidenced.
8. **FVSR content**: ss. 3.04, 3.05, 3.12 to 3.14, 3.44 to 3.61 were read here; the whole stability division, the equipment parts and the Records and Safety Procedures headings need a full read and a check against any 2024 to 2026 amendment. The FVSR consolidation read is current to 2025-07-24.
9. **Stability formulas and numbers** for lessons 14 to 16 and 22 (TPC/TPI, FWA, GZ, inclining test): need a naval architecture source and expert sign-off. IS Code numbers in `stability.*` came from secondary pages (handoff note).
10. **Hours of rest** edge case at exactly 100 GT (MPR s. 319(1)).
11. **TP 10038** (2003) is not read; check whether TC has replaced or updated it.
12. **All new lessons and questions**, and the tagging of existing lessons to this ticket.

## 5. Cautions and discrepancies

- **Study aid, not navigation or stability authority.** A lesson must never be presented as the vessel's own stability booklet or notice; the real booklet is vessel-specific and approved. Use only an invented vessel.
- **Do not copy** the B/P Skate booklet, TP 2293 wording beyond short attributed quotes, provider course material, or real TC exam questions. The SCS 1 open-book list is for the candidate's room; we only describe it.
- TP 2293 Rev. 6 names the **Small and Large Fishing Vessel Inspection Regulations** in the SCS 1 open-book list; the current instrument in the XML repository is the Fishing Vessel Safety Regulations, which govern vessels up to 24.4 m and 150 GT. Writers cite the current regulation and flag the old names as exam-room history.
- **Camosun's FM4 schedule** (existing source `sixty-camosun-fm4-courses`) is from 2022-23; Holland College's is 2026-27 and lists NSD for FM4. Provider prices and durations drift; use them only as a guide to depth.
- **CHS tide and current data** used in the tide lessons stays within `docs/licence-checklist.md` (non-navigational teaching use).
- **Cross-brief notes for the Master, Limited brief** (not changed here): (a) the user-supplied TP 2293 Rev. 6 contains Chapter 12 (Master, Limited), including section 12.2 (CP1 and NS 1 listed for the certificate) and the 18-item competence table in 12.11 with scope-trimming rules, which settles the "TP 2293 not read" and CP1 open points in that brief; (b) NS 1 is 1.5 hours in Rev. 6; (c) NS-D is claimed to be credited for Master, Limited under 60 GT too; (d) the NS 1 syllabus in 11.4 does not list tides.
- The user-supplied TP 2293 text carries TC's permission to copy "as required" with the usual no-responsibility wording. It stays outside the repo; this brief paraphrases it.

## 6. Sources to log (`content/sources/fm4.json`, ids start with `fm4-`)

| id | what | kind | read_directly | reproduction |
|---|---|---|---|---|
| fm4-mpr-sor-2007-115-xml | Marine Personnel Regulations, SOR/2007-115, consolidated XML (ss. 100, 103, 106, 110 to 113, 115, 116, 120, 121, 141 to 143, 212 incl. Table 2, 216, 222, 301, 319); repository commit b7292cf, consolidation current to 2025-07-24 | regulation | true | permitted-with-acknowledgement |
| fm4-fvsr-crc-1486-xml | Fishing Vessel Safety Regulations, C.R.C., c. 1486, consolidated XML (ss. 2, 3, 3.04, 3.05, 3.12 to 3.14, 3.44 to 3.61; headings of the rest); same commit, current to 2025-07-24 | regulation | true | permitted-with-acknowledgement |
| fm4-tp2293-rev6 | TP 2293E Revision 6 (03/2016), The Examination and Certification of Seafarers: Ch. 2, 3.8, 4, 11.4, 12.1 to 12.11, 24, 25 (user-supplied text; public copy listed at publications.gc.ca T29-24-2016-eng.pdf, not fetched) | tc-publication | true | permitted-with-acknowledgement |
| fm4-tc-tp2293-landing | TC page "TP 2293 – The Examination and Certification of Seafarers", modified 2024-03-26 | tc-publication | false | unclear |
| fm4-tp10038 | TP 10038 Small Fishing Vessel Safety Manual (2003); TC page and PDF found, not read | tc-publication | false | unclear |
| fm4-hc-fishing-master | Holland College Marine Training Centre, Fishing Master page (FM4 course list, short courses, self-study) | provider-page | false | not-permitted |
| fm4-hc-nsd | Holland College, NSD Navigation Safety Domestic page (cites a TC notice) | provider-page | false | not-permitted |
| fm4-nscc-fm4 | NSCC, Marine Foundations: Fishing Master, Fourth Class | provider-page | false | not-permitted |
| fm4-saferoceans-fm4 | Safer Ocean Systems, Fishing Master 4 course category | provider-page | false | not-permitted |
| fm4-mun-tp15141e | "Certificates and Training Requirements for Masters and Mates" (TP 15141E copy hosted by Memorial University Marine Institute; date not stated) | other | false | unclear |

Existing ids to reuse: `tp2293-2016` (TP 2293 Rev. 6 Ch. 33, SVMO lane), `sixty-camosun-fm4-courses`, `sixty-tc-stability-training`, `sixty-fao-ftp517-stability`, `sea-c-fao-ftp517`, `stab-fvsr-crc-1486-stability`, `sixty-fvsr-crc-1486-xml`, `sixty-csa-2001-xml`, `sixty-holland-cp1`, `sixty-holland-ns1`, and the COLREGs, chartwork, tides and meteorology source files for the reused lessons.

Sources still to find and register before the lessons that need them: TP 10038 text (read it), the Code of Safe Working Practices as applied to fishing, FAO/ILO/IMO Voluntary Guidelines and Code of Safety for Fishermen and Fishing Vessels (Parts A and B), TP 1332 section 4 (small fishing vessel buoyancy and flotation), the International Code of Signals life-saving signals table, and a naval architecture reference for TPC/TPI, FWA and the inclining test.

### Fetch notes (for reproducibility)

- TC pages: two guessed TC certificate-requirement URLs returned `ROBOTS_DISALLOWED` from the fetch tool; the TP 2293 landing page loaded and gave only its modified date. I could not read TC's own Fishing Master Fourth Class requirements page or the notice that Holland College cites for NS-D. `laws-lois.justice.gc.ca` was not needed (official XML from the repository clone). The Safer Oceans catalogue URL redirected to a login host; its arlo.co course page was read instead.
- Provider pages (Holland College, NSCC, Safer Oceans, Memorial University) were read through the fetch-and-summarise tool, not word for word.
