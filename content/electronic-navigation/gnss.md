---
id: electronic-navigation.gnss
title: "GPS and GNSS"
module: electronic-navigation
tickets: [master-limited-60gt]
order: 1
summary: "What GNSS and GPS are, where the Navigation Safety Regulations, 2020 require a receiver, how accurate a position is and what spoils it, why the chart datum matters, and how jamming and spoofing can mislead you without any alarm."
objectives:
  - Say what GNSS is and which satellite systems the NSR recognise.
  - State the GNSS carriage rule (150 GT) and the distress-alert position rule.
  - Describe typical GNSS accuracy and the main things that degrade it.
  - Explain why the chart's horizontal datum matters and where to find the adjustment.
  - Describe jamming and spoofing and say how to catch them.
prerequisites: [chartwork.chart-datums, chartwork.latitude-longitude-distance]
estimated_minutes: 14
key_facts:
  - GNSS is the global navigation satellite system family; GPS is one system, and the NSR also list GLONASS, Galileo, BeiDou, DGPS/DGLONASS beacon receivers and multi-system receivers.
  - A vessel of 150 GT or more must carry a GNSS receiver that can be used at all times throughout the intended voyage to establish and update its position (NSR s. 109(1)(a)).
  - If radio equipment can send the vessel's position automatically in a distress alert, the position and the time of the position must come from a GNSS receiver (NSR s. 221).
  - The published GPS commitment is a signal-in-space range error of 2.0 m or better, with 95% probability, and this is not the same as the error of a receiver on your boat.
  - A smartphone is typically accurate to about 4.9 m radius under open sky and worse near buildings, bridges and trees.
  - GNSS errors come from blocked signals, reflected signals (multipath), the atmosphere and the receiver; a good fix still has error.
  - The Canadian Hydrographic Service charts use WGS84, which is compatible with GNSS; NAD83 is treated as equivalent.
  - If a chart uses an older horizontal datum, GNSS positions must be adjusted using that chart's Horizontal Datum note, so always check the note.
  - Jamming degrades or denies GNSS and spoofing replaces the signal with false ones that can offset the position slowly without an alarm.
  - Cross-check a GNSS position regularly against radar ranges, visual bearings and depth.
source_ids: [renav-nsr-2020-xml, renav-gps-gov-accuracy, renav-chs-notmar-g45, renav-nautinst-jamming-spoofing]
review_status: needs_expert_review
---

# GPS and GNSS

**Study aid only.** A GNSS position is a very good indication of where you are, not a guarantee. Nothing in this lesson
replaces the chart, the depth, the compass or a look outside.

## What you need to know

A satellite receiver listens to signals from several satellites, measures the tiny differences in when each signal
arrives, and works out where it is. The family of such systems is called a ==global navigation satellite system
(GNSS)==. The American one is GPS. The others are GLONASS (Russia), Galileo (Europe) and BeiDou (China). The Navigation
Safety Regulations, 2020 (NSR) list receiver types for all four, along with differential beacon receivers (DGPS and
DGLONASS) and multi-system receivers. In exam English, "GPS" and "GNSS" are often used loosely for the same thing.

The receiver gives latitude and longitude, plus the time, and from changes in position it works out ==course over
ground (COG)== and ==speed over ground (SOG)==. COG and SOG describe how you are moving over the bottom, so they include
the effect of current and leeway. They are not your heading and not your speed through the water.

## What the law requires

The rule is short and sits in the NSR, which applies to Canadian vessels anywhere:

| Rule | What it says |
|---|---|
| s. 109(1)(a) | Every vessel of ==150 gross tonnage or more== must be fitted with a GNSS receiver (of a type in Schedule 1) that can be used ==at all times throughout the intended voyage== to establish and update the vessel's position by electronic means. |
| s. 221 | If radio equipment can automatically send the vessel's position in a distress alert, the ==position and time== must be available from a ==GNSS receiver==. |
| s. 5(2) | Required equipment must be installed, tested and maintained so that it is in effective operating condition. |

Many small BC vessels are under 150 GT and have no legal duty to carry a GNSS receiver, but nearly all do. A VHF set with
DSC works best when it is wired to the GNSS, so the distress alert carries your position without anyone typing it.

## How accurate is it?

Be careful with figures. The US government's GPS commitment is a ==signal-in-space range error of 2.0 m or better, with
95% probability==. That describes the satellites, not your receiver. For phones, the same US site says typical accuracy
is about ==4.9 m radius under open sky==, getting worse near buildings, bridges and trees.

Sources of error on a boat:

- **Blocked signals.** Cliffs, tall structures, a metal cabin roof or a receiver tucked under a deck cut off satellites.
- **Multipath.** Signals bounce off a hard surface before reaching the antenna and give a false distance.
- **The atmosphere.** The signal slows down passing through the upper atmosphere. Better receivers correct part of this.
- **The receiver.** A badly sited antenna or a loose antenna cable makes the fix worse.

So the position is a dot with a small cloud of doubt around it. Even a good fix has an error, and what you plot on a
chart is only as precise as the chart.

## The datum problem

A position is meaningless unless you know what map grid it refers to. GNSS works natively in WGS84. The Canadian Hydrographic
Service has adopted ==WGS84==, and treats NAD83 as equivalent. Most CHS charts therefore need no conversion. A few
use an older horizontal datum, and these carry a ==Horizontal Datum note== that tells you how to adjust GNSS positions to
plot them correctly.

Worked example (invented offset). A chart's note says: move a satellite position **0.04 minute north** and **0.12
minute west** to plot it on this chart. The ship is at 49° N.

1. Latitude: 0.04 minute = 0.04 nm ≈ 74 m (one minute of latitude is one nautical mile).
2. Longitude: one minute of longitude is shorter at 49° N. 0.12 minute × cos 49° = 0.12 × 0.656 = 0.079 nm ≈ 146 m.
3. Total shift = √(0.04² + 0.079²) = 0.088 nm ≈ **163 m**.

Forgetting this on a narrow channel chart puts you about 160 m from where you think you are. Read the datum note on every
chart before you start plotting.

## Jamming and spoofing

*Jamming* swamps the signals so the receiver's position gets worse or disappears. *Spoofing* sends counterfeit signals that
take over the receiver, so the position can be pushed off slowly and look perfectly normal. A spoofed receiver may show no
alarm. A frozen or drifting position, a position that disagrees with radar or visual bearings, and odd jumps in other
systems that use the position (such as AIS reports) are warnings.

The Nautical Institute's advice is simple: cross-check often, use every source of information, practise traditional
skills, and do not rely on the absence of an alarm.

## Cross-checking in practice

After each GNSS position, compare it with something independent: a radar range to a headland, a visual bearing, the depth
on the echo sounder against the chart. If the GNSS says you are 0.8 nm off a headland and the radar says 0.5 nm, you have a
0.3 nm (556 m) disagreement. Stop, investigate, and trust the evidence nearest your hull until you find the cause.

## BC example (invented)

A 15 m charter boat leaves Howe Sound after dark. The plotter says the boat is 0.3 nm clear of Passage Island. Looking
at the radar, the mate reads 0.55 nm to the island from the VRM. They disagree by a quarter of a mile. The mate slows,
checks the plotter's position source and finds the receiver is showing a poor fix because its antenna cable has worked
loose. After the cable is reseated, the two sources agree again. Nothing went wrong, because the mate had a second source.

## Common mistakes

- Treating a GNSS position as exact. It has an error that is not shown on the screen.
- Taking COG as heading or SOG as speed through the water.
- Ignoring a chart's Horizontal Datum note.
- Believing no alarm means no problem.
- Relying on a phone's GPS inside a steel wheelhouse or under a canopy.

## Check yourself

1. At what size must a vessel carry a GNSS receiver under the NSR?
2. Where must the position in a distress alert come from, if it is sent automatically?
3. What datum do the CHS charts normally use, and what do you do on a chart that does not?
4. What is the difference between jamming and spoofing?
5. Why is "no alarm" not proof that the position is right?

---

*Study aid. Paraphrased from the Navigation Safety Regulations, 2020, GPS.gov, the Notices to Mariners datum note and
Nautical Institute guidance. The datum offset in the example is invented. It is not the official text.*
