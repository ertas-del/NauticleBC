---
id: radio.dsc-and-gmdss
title: "DSC, MMSI and GMDSS sea areas"
module: radio
tickets: [master-limited-60gt]
order: 50
summary: How digital selective calling works, the MMSI that identifies your radio, the four DSC call categories, the sea areas A1 to A4 and what they mean for the equipment you carry, and how NAVTEX delivers weather and safety messages on the BC coast.
objectives:
  - Define sea areas A1, A2, A3 and A4 as the NSR 2020 does.
  - Describe DSC, the MMSI and the call categories.
  - Explain the DSC distress alert and its follow-up on Channel 16.
  - Say what NAVTEX and maritime safety information are.
prerequisites: [radio.vhf-basics-for-ratings]
estimated_minutes: 18
key_facts:
  - "Sea area A1 is within the radiotelephone coverage of at least one VHF coast station with continuous DSC alerting; A2 is outside A1 within MF coast station coverage; A3 is outside A1 and A2 within recognised satellite coverage; A4 is everything else."
  - "The RAMN gives roughly 40 nautical miles for A1 and 150 nautical miles for A2; West Coast A1 coverage extends about 40 nautical miles from the Alaska border to Juan de Fuca Strait."
  - "Digital selective calling (DSC) sends coded calls to a particular station or to all stations; it meets ITU-R Recommendation M.493 (NSR 2020 s. 200)."
  - "DSC calls are in these categories: distress, urgency, safety, ship's business and routine."
  - "Every DSC radio must be programmed with the correct nine-digit MMSI; the VHF DSC channel is 70 (156.525 MHz)."
  - "A DSC distress alert is sent by holding the red button for 5 seconds; the voice distress call and message follow on Channel 16, and an all-stations alert is normally acknowledged by an MCTS centre."
  - "A ship that receives a DSC distress alert on VHF or MF must not send a DSC relay; a ship receiving an all-stations DSC safety announcement must not acknowledge it."
  - "Maritime safety information (MSI) is navigational and meteorological warnings, forecasts and other urgent safety messages broadcast to vessels."
  - "The international NAVTEX service broadcasts MSI in English on 518 kHz and is received automatically; on the BC coast Prince Rupert MCTS operates NAVTEX from Digby Island and Amphitrite Point."
  - "A vessel on a voyage with any part outside A1 that carries passengers more than 2 nm from shore, carries more than 6 passengers, or is on a Near Coastal Class 1 or unlimited voyage must be able to reach an MCTS centre by two-way communication at any time (s. 206)."
source_ids: [rmet-nsr-2020-xml, rmet-ramn-general, rmet-ramn-part5-2024, rmet-ised-ric25]
review_status: needs_expert_review
---

# DSC, MMSI and GMDSS sea areas

**Study aid only.** Distances for sea areas are published figures; coverage on the water can differ with antenna height and terrain.

## What you need to know

The Global Maritime Distress and Safety System (GMDSS) is the world's arrangement for calling for help and receiving safety messages at sea. Two ideas sit under it: **digital selective calling (DSC)**, which sends a coded call to a chosen station, and **sea areas**, which say how far from a coast station you are and which radios you therefore need. A deck officer has to know both.

## Sea areas A1 to A4

The NSR 2020 (s. 200) defines them by coverage, not by a drawn line:

| Area | Definition | RAMN distance |
|---|---|---|
| ==A1== | Within radiotelephone coverage of at least one ==VHF== coast station with continuous DSC alerting | about ==40 nm== |
| ==A2== | Outside A1, within coverage of at least one ==MF== coast station with continuous DSC alerting | about ==150 nm== |
| ==A3== | Outside A1 and A2, within coverage of a recognised ==mobile-satellite== service (ship earth station) | |
| ==A4== | Outside A1, A2 and A3 | polar regions |

The RAMN says Canada's West Coast A1 reaches about 40 nm from the Alaska border to the Juan de Fuca Strait. So most BC small-vessel voyages are in A1, which is why the VHF-DSC rule (s. 204) is so central. Gaps exist, so a vessel on a remote reach should not assume coverage.

A vessel with any part of her voyage outside A1 has extra duties (s. 206). She must be able to reach an MCTS centre at any time if she carries ==passengers more than two nautical miles from shore==, ==more than six passengers==, or is on a ==Near Coastal Class 1 or unlimited voyage==.

## DSC and the MMSI

DSC is a system of digital codes (ITU-R Recommendation M.493). On VHF it works on ==Channel 70 (156.525 MHz)==. A DSC radio sends a short digital message that carries:

- who is calling (the ==MMSI==, a ==nine-digit== number identifying the radio or vessel);
- who is being called, or all stations;
- the category of the call;
- on a distress alert, the vessel's position and the time, if the radio is connected to a GNSS receiver.

The radio must be programmed with the ==correct MMSI== or the system cannot identify you. The RAMN examples for Canada are in the 316 series; the number itself is assigned by the radio authority.

The call categories are ==distress, urgency, safety, ship's business and routine== (RIC-25 syllabus). Distress is the one with the red button.

## The DSC distress alert, step by step

1. With the master's permission, ==press and hold the red distress button for five seconds==. The radio sends the alert on Channel 70 with your identity and position.
2. The radio switches to ==Channel 16== for the voice distress call and message.
3. An all-stations DSC distress alert is normally ==acknowledged by an MCTS centre==.
4. A ship that hears a DSC distress alert on VHF or MF ==does not send a DSC relay==. Relays on HF are started by hand.

Two further rules from the RAMN: a ship receiving an **all-stations DSC safety announcement** ==must not acknowledge it==. And a vessel on watch for VTS may, in congested water, pause its Channel 70 DSC watch for a short time while completing a manoeuvre.

## Maritime safety information and NAVTEX

The NSR 2020 defines ==maritime safety information (MSI)== as navigational and meteorological warnings, weather forecasts and other urgent safety messages broadcast to vessels. NAVTEX is the automatic printing receiver for MSI broadcast in English on ==518 kHz==. A vessel that must keep a continuous watch (s. 248) on MSI frequencies can meet that by NAVTEX.

On the BC coast Prince Rupert MCTS runs two NAVTEX transmitters, ==Digby Island== and ==Amphitrite Point==, and the weather products also go out at the usual forecast times. The NSR 2020 requires a NAVTEX receiver on vessels beyond A1 in a NAVTEX area if they carry more than six passengers, are fishing vessels of 24 m or more, are towboats of 150 GT or more, or are 300 GT or more (s. 210(1)). Other vessels on a Near Coastal Class 1 or unlimited voyage must have some means to receive MSI (s. 210(2)).

## A BC example

A 12 m passenger boat makes a trip from Tofino to Hot Springs Cove with 10 passengers. Her whole route lies in A1, so her fixed VHF-DSC is her working link to the coast guard. Had a leg of the voyage run beyond A1, s. 206 would require her to be able to reach an MCTS centre at any time, because she carries more than six passengers. Her operator checks that the MMSI is programmed and that the radio's GNSS position is updating, because a distress alert with no position slows rescuers.

## Common mistakes

- Treating A1 as a line on the chart. It is defined by VHF coverage, and the 40 nm figure is a published approximation.
- Mixing up A2 (MF) and A3 (satellite).
- Believing that DSC replaces the voice call. The alert is followed by a voice call on Channel 16.
- Acknowledging an all-stations DSC safety announcement.
- Relaying a DSC distress alert you heard on VHF.
- Thinking the MMSI is the call sign. They are different identifiers.

## Check yourself

1. Define sea area A2 in your own words.
2. After a DSC distress alert, which channel carries the voice message?
3. What does MSI stand for and give two examples.
4. On what frequency does NAVTEX broadcast in English?
5. Which categories can a DSC call have?

This lesson is a study aid based on the Navigation Safety Regulations, 2020, the RAMN and RIC-25. Coverage figures are published approximations.
