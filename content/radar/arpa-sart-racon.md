---
id: radar.arpa-sart-racon
title: "ARPA, SARTs and racons"
module: radar
tickets: [master-limited-60gt]
order: 6
summary: "What automatic tracking aids and ARPA do and where they are required, their limits, and the three radar-related devices a mariner must recognise: the radar SART, the racon and the radar reflector, including Rule 40 and Rule 41."
objectives:
  - Say what an ATA and an ARPA do and which vessels must carry one under the NSR.
  - List the main limits of automatic tracking and the inputs it depends on.
  - Describe how a radar SART appears on a rescuer's screen and which band it answers.
  - Describe a racon and a radar reflector and say who must carry a reflector.
  - Explain why a SART can only be used in distress.
prerequisites: [radar.target-course-speed-and-action]
estimated_minutes: 14
key_facts:
  - A vessel of 300 GT or more but under 500 GT must carry an automatic tracking aid (ATA) or an automatic radar plotting aid (ARPA), and a vessel of 500 GT or more must carry an ATA or ARPA (NSR ss. 110(d), 111(b)).
  - An ARPA automatically plots the range and bearing of at least 20 targets and is connected to a device showing speed and distance through the water (NSR s. 110(d)(i)).
  - An ATA or ARPA needs heading information from a transmitting heading device or a gyro-compass (NSR s. 110(d)(ii)), so a wrong heading input gives wrong true vectors.
  - "An automatic tracker can only follow what the radar shows: poor tuning or gain causes false or missed targets, and a target the radar loses is shown as lost."
  - A target is flagged as dangerous only when both the CPA limit and the TCPA limit are breached.
  - A radar SART answers only 9 GHz X-band (3 cm) radar and appears as a line of 12 dots, which become arcs and then circles as the rescuer closes within about 1 nm.
  - A radar SART can be triggered by an X-band radar within about 8 nm.
  - A racon is a radar transponder that sends a characteristic signal, often a Morse letter, when triggered by a ship's radar.
  - Under Rule 40, a vessel under 20 m, or built primarily of non-metallic material, shall if practicable carry a radar reflector or other means of detection at 3 GHz or 9 GHz.
  - Under Rule 41, no vessel except a vessel in distress may use a transponder that sends radar responder or beacon signals in the 3 cm or 10 cm bands.
source_ids: [renav-nsr-2020-xml, renav-crc1416-xml, renav-timezero-arpa-target, renav-wiki-sart, renav-bowditch-2002-ch13-radar, renav-nga-pub310-ch4]
review_status: needs_expert_review
---

# ARPA, SARTs and racons

**Study aid only.** Automatic tracking is a help to the watchkeeper, not a decision-maker. This lesson covers the equipment
named in the regulations and the radar devices on the oral.

## What you need to know

Plotting by hand on a plotting sheet is slow and ties up a person. Automatic tracking does the same sums in the set. At
the other end of the scale, three small devices change what the radar sees: a reflector that makes a small boat a better
target, a racon that makes a mark identify itself, and a SART that makes a survivor findable. You need to know what each
does and where the law mentions it.

## ATA and ARPA

An ==automatic tracking aid (ATA)== automatically plots the range and bearing of targets in order to determine collision
risk. An ==automatic radar plotting aid (ARPA)== automatically plots the range and bearing of ==at least 20 targets== and
is connected to a device showing speed and distance through the water. Both give each tracked target a vector, a CPA and a
TCPA, and raise an alarm when a target becomes dangerous.

Where the Navigation Safety Regulations, 2020 require one:

| Vessel | Requirement |
|---|---|
| 300 GT or more but under 500 GT | ATA or ARPA, plus a transmitting heading device or gyro-compass feeding the radar and the ATA/ARPA (s. 110(d)) |
| 500 GT or more | ATA or ARPA (s. 111(b)) |
| 3 000 GT or more, under 10 000 GT | A second, independent ATA or ARPA (s. 112(b)) |
| 10 000 GT or more | An ARPA in addition (s. 113(a)) |

Below 300 GT nothing in this list applies, but a radar fitted voluntarily may still have tracking.

## How automatic tracking works from the watchkeeper's side

The operator acquires a target, either by selecting it manually or by letting the set pick up anything inside a guard
zone. For a few scans the set shows it as still being acquired. Once acquired, the set shows a vector, which is a short
line showing where the target will be in a chosen time if she holds course and speed. The set calculates CPA and TCPA,
and a target that crosses *both* chosen limits is flagged as dangerous. The same ideas that you plotted by hand apply,
with the set doing the arithmetic.

## The limits of automatic tracking

The number on the screen is a calculation, and a calculation is only as good as its inputs.

- **It can only track what the radar sees.** Poor tuning or gain causes false targets and missed targets, and clutter
  controls that hide a small boat from the radar hide it from the tracker too.
- **It depends on heading and speed inputs.** A true-motion vector uses your heading and your speed through the water.
  A wrong heading or a speed from the wrong source gives wrong true vectors on every target at once.
- **It can lose a target.** The set flags a target as lost when it leaves range or the radar can no longer follow it.
  A target that merges with another or with land may be lost or confused.
- **It shows the past, not the intention.** A vector extends the target's recent movement. It will not show an alteration
  that has not happened yet, and for a little while will not show one that has just happened.
- **Alarms need setting sensibly.** Both limits have to be crossed for a target to count as dangerous. Set them too
  small and the first warning comes too late; set them too large and the screen alarms constantly.

Radar plotting and the plotting-by-hand habits you learned still matter. Rule 7(b) asks for proper use of radar,
and Rule 7(c) still forbids assumptions on scanty information.

## Radar reflector (Rule 40)

Small and non-metallic boats are poor radar targets. Rule 40 of the Collision Regulations says a vessel ==less than 20 m
in length or built primarily of non-metallic material== shall, ==if practicable==, be equipped with a ==radar reflector==
or other means to enable detection by vessels navigating by radar at ==3 GHz or 9 GHz==. It does not apply where a vessel
operates in limited traffic, daylight and favourable conditions and compliance is not essential for safety, or where the
vessel's small size or operation away from radar navigation makes compliance impracticable. A common reflector is made of
metal surfaces at right angles to each other, which return the signal back toward its source.

## Racon

A racon is a ==radar transponder== fixed to an aid to navigation. When a ship's radar pulse triggers it, it sends back a
characteristic signal. On the screen, it appears as a short radial line just beyond the mark, or a Morse code letter.
Because it is a transponder, it gives both bearing and range to the mark and is useful for picking out one buoy or
light from another in poor visibility.

## Radar SART

A SART (search and rescue transponder) is carried in a liferaft or lifeboat. It answers ==9 GHz X-band radar only==. The
NSR define a SART as a transponder for search and rescue that works on a radar or AIS frequency. When a rescue ship's
radar triggers it, the screen shows a ==line of 12 dots==, about 0.64 nm apart, running outward from the SART's position.
As the rescuer closes, the dots grow into ==arcs== and, within about 1 nm, into ==full circles==. A radar SART can be
triggered by an X-band radar within about 8 nm, depending on the height of the rescuer's antenna.

Rule 41 explains why you must not switch one on as a test: ==no vessel except a vessel in distress== may use a
transponder that sends radar responder or beacon signals in the 3 cm or 10 cm marine radar bands. Racons are authorised
aids to navigation, run by the Coast Guard, not by vessels. The SART lessons in the radio and bridge-watch modules cover
testing and handling.

## BC example (invented)

A 12 m fishing boat is overdue in northern Hecate Strait in a heavy chop. A 30 m patrol vessel, searching on its X-band
radar, sees a line of dots appear at 6.5 nm on a bearing of 310°. The officer reduces speed and keeps steering toward
the start of the line. At about 1 nm the dots turn to arcs, then circles, and a few minutes later the orange life raft
appears out of the swell. The dots helped to find the survivors, while the weak raft echo alone would not have.

## Common mistakes

- Thinking ARPA makes plotting unnecessary. It does the arithmetic, not the judging.
- Forgetting that a wrong heading or speed input corrupts every true vector.
- Believing a SART shows on any radar. It answers X-band (9 GHz) only.
- Mixing up the SART pattern (12 dots, then arcs, then circles) with a racon (a Morse letter or radial line).
- Testing a SART at sea as though it were an ordinary radar target. Rule 41 limits use to distress.

## Check yourself

1. Which GT threshold brings in the ATA/ARPA requirement?
2. A heading error of 15° is fed to an ARPA. What happens to the true vectors?
3. What does a radar SART look like on the screen, and which band does it answer?
4. Who must carry a radar reflector under Rule 40, and what are the exceptions?
5. What does a racon do?

---

*Study aid. Paraphrased from the Navigation Safety Regulations, 2020, the Collision Regulations, manufacturer
documentation and textbooks read through summaries. It is not the official text.*
