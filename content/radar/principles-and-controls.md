---
id: radar.principles-and-controls
title: "How radar works and its controls"
module: radar
tickets: [master-limited-60gt]
order: 1
summary: "How a radar turns a timed pulse into range and bearing, what the 9 GHz and 3 GHz bands mean, what the screen shows, what gain, sea, rain, tuning, EBL and VRM do, and which vessels must carry radar under the Navigation Safety Regulations, 2020."
objectives:
  - Explain how radar measures range from echo time and bearing from antenna direction, and convert an echo delay into a range.
  - Name the radar bands in the regulations and say which vessels must carry radar.
  - Describe what gain, sea clutter, rain clutter, tuning, EBL, VRM and range rings are for.
  - Explain why over-using the clutter controls is dangerous.
  - State what Rule 7(b) expects of a vessel with working radar.
prerequisites: [colregs.rule-07.risk-of-collision, colregs.rule-06.safe-speed]
estimated_minutes: 14
key_facts:
  - Radar finds range by timing a pulse's round trip to the target, and bearing from the direction the antenna points when the echo returns.
  - A radar echo takes about 12.4 microseconds per nautical mile of range (the round trip), so range in nautical miles is the delay in microseconds divided by 12.4.
  - The Navigation Safety Regulations, 2020 name a 9-GHz radar for vessels of 300 GT or more (s. 110(b)); a 3-GHz or 9-GHz radar is allowed as the second radar at 3 000 GT (s. 112(a)).
  - A towboat of 5 GT or more on an inland or near coastal Class 2 voyage must carry a radar (NSR s. 125(1)(a)).
  - Gain sets how strong a return must be to show; too high floods the screen with noise, too low loses weak echoes.
  - Sea clutter control suppresses wave echoes near own ship and rain control suppresses precipitation echoes; set too high, either can erase real targets.
  - Range resolution is about half the pulse length, and a narrower beam gives better bearing resolution.
  - Rule 7(b) requires proper use of radar, including long-range scanning for early warning and radar plotting or equivalent systematic observation.
  - Rule 6(b) adds radar factors to safe speed, including the radar's limitations and the chance that small vessels, ice and floating objects are not detected in time.
  - EBL measures bearing and VRM measures range; both are moved onto an echo to read it.
source_ids: [renav-nsr-2020-xml, renav-crc1416-xml, renav-bowditch-2002-ch13-radar, renav-nga-pub310-ch4, renav-timezero-radar-controls, renav-raymarine-radar-sensitivity, renav-raymarine-radar-settings, renav-timezero-arpa-target]
review_status: needs_expert_review
---

# How radar works and its controls

**Study aid only.** This lesson explains radar for exam and oral purposes. It is not an operating manual for any
particular set and not a substitute for the manufacturer's instructions, a radar course or a proper look-out.

## What you need to know

Radar is a stopwatch with an antenna. The set sends out a very short burst of radio energy, the antenna turns, and
anything solid in the path sends a little of that energy back. The set measures ==how long the echo took== to return and
==which way the antenna was pointing== when it arrived. Time gives ==range==. Antenna direction gives ==bearing==. Every
other radar skill in this module rests on those two ideas, and on remembering that radar only shows what reflects
radio waves well enough to be seen.

## From pulse to range

Radio waves travel at a fixed, enormous speed. A pulse that has to go out one nautical mile and come back has travelled
two nautical miles, and that takes about ==12.4 microseconds==. So the rule of thumb is simple: **range in nautical
miles = echo delay in microseconds ÷ 12.4**. The 12.4 is calculated from the speed of radio waves, so no set will
ever show you a different number.

Worked example (invented numbers). An echo returns 37.1 microseconds after the pulse left.

1. Range = 37.1 ÷ 12.355 = 3.003 nautical miles.
2. So the target is about **3.0 nm** away.
3. Check the other way: a target at 6.0 nm needs 6.0 × 12.355 = 74.1 microseconds, twice as long as 3.0 nm.

Nobody does this sum on the bridge. The set does it thousands of times a second. The point is to see why range is
trustworthy: it is a timing measurement. Bearing is read from where a narrow beam is pointing, and a beam is never
perfectly narrow, which is why bearings are less accurate (see the next lessons).

## The bands: 9 GHz and 3 GHz

Marine radar uses two bands. The ==9 GHz band (X-band)== has a wavelength of about 3 cm. The ==3 GHz band (S-band)==
has a wavelength of about 10 cm. The regulations use the frequencies, not the letters. The Navigation Safety Regulations, 2020 (NSR) require a **9-GHz radar** on vessels
of 300 GT or more (s. 110(b)) and allow a **3-GHz or 9-GHz** radar as the second, independent radar on vessels of 3 000
GT or more (s. 112(a)). The Collision Regulations use the same two figures for radar reflectors (Rule 40). A rescue
transponder (SART) answers 9 GHz only, which is covered in the SART lesson.

## Who must carry radar

Most small BC vessels are not required to carry radar, but many do, and the oral will ask. Under the NSR:

| Vessel | Radar rule |
|---|---|
| 300 GT or more | 9-GHz radar (s. 110(b)); under 500 GT also an automatic tracking aid or ARPA (s. 110(d)) |
| Towboat of 5 GT or more, inland or near coastal Class 2 voyage | A radar (s. 125(1)(a)) |
| Towboat on a near coastal Class 1 voyage | Two independent radars (s. 125(1)(b)) |
| 3 000 GT or more | A second, independent radar (s. 112(a)) |

The radar required must be able to show the range and bearing of other craft, obstructions, buoys, shorelines and
navigational marks, and a transponder's response. Required equipment must also be kept in ==effective operating
condition== (s. 5(2)), so a defective required radar is a legal problem as well as a safety one.

## What the screen shows

Most displays are a plan position indicator (PPI). **Own ship is at the centre.** A bright line from the centre is the
==heading marker==. Distance from the centre is range, and ==range rings== at equal steps help you estimate it. Two
movable tools do the measuring: the ==electronic bearing line (EBL)== is a line from the centre that you swing onto an
echo to read its bearing, and the ==variable range marker (VRM)== is a ring you expand until it touches the echo to read
its range.

## The controls

The first four are the ones to know cold.

**Gain** is the receiver's sensitivity. Raise it and more returns appear, but so does random speckle. Lower it when the
screen is full of noise. Too little gain means weak echoes, such as a small boat, are never seen.

**Sea clutter** (also called STC or anti-clutter sea) suppresses the mass of wave echoes around own ship. Raise it
until the clutter close to the centre just clears, and no further. **Rain clutter** (FTC) suppresses echoes from
rain and is for use only when rain is actually there; otherwise leave it off. Both controls work by removing returns,
and they cannot tell a wave from a small boat or a growler. The manufacturer guide for one popular set warns that too
much rain suppression can erase legitimate targets. Over-using these controls is how a small vessel disappears from the
screen.

**Tuning** matches the receiver to the transmitter. Many sets tune themselves, but a badly tuned radar gives a weak or
false picture, and one chart-plotter guide warns that poor tuning causes false targets and missed targets.

**Range scale** sets how far the screen covers. Use a longer scale for early warning and a shorter one for close
detail, and switch between them often. **Pulse length**, normally chosen by the set with the range scale, matters
because ==range resolution is about half the pulse length==: two targets closer together than that blur into one.
Bearing resolution is better when the ==beam is narrower== and the range is shorter.

**Standby** stops the antenna turning and the transmitting but keeps the radar powered, so it is ready within seconds.
Switching it fully off means a warm-up period before the picture returns (one set guide quotes one to two minutes).

## What the collision rules expect

Rule 7(b) says ==proper use shall be made of radar== if it is fitted and operational, including ==long-range scanning==
to obtain early warning of risk of collision and ==radar plotting or equivalent systematic observation== of detected
objects. Rule 6(b) adds radar to the safe-speed factors: the radar's characteristics, efficiency and limitations, the
range scale in use, the effect of sea state, weather and interference, the possibility that small vessels, ice and
floating objects are ==not detected at an adequate range==, the number and movement of vessels detected, and the better
estimate of visibility radar can give. Rule 5 still applies: radar is part of the look-out, never a replacement for
sight and hearing.

## BC example (invented)

A 14 m towboat is crossing Haro Strait in fog with a barge on the hip. The radar is on the 6 nm scale. The master has
turned the sea clutter up to clear the screen in a short chop and left it there. A small open boat with a fibreglass hull
sits three-quarters of a mile away and does not show. At the next scale change the master sees a faint patch, backs the
sea control off, and the boat appears at 0.6 nm. Lesson: clutter controls hide targets as readily as clutter, so adjust
them to the weather, then check them after every scale change.

## Common mistakes

- Turning clutter controls up and leaving them, so that small craft vanish.
- Believing radar sees everything. Rule 6(b) says small vessels, ice and floating objects may not be detected in time.
- Mixing up the bands: 9 GHz is X-band, about 3 cm; 3 GHz is S-band, about 10 cm.
- Remembering "radar at 300 GT" and forgetting the 5 GT towboat rule.
- Treating a bearing as reliable as a range. Range is a timing measurement, bearing depends on beam width.

## Check yourself

1. An echo returns 25 microseconds after the pulse leaves. About how far away is the target?
2. Which regulation figure names the radar band for vessels of 300 GT or more?
3. A 9 m towboat of 6 GT is on an inland voyage. Must it carry a radar?
4. Why is a high rain-clutter setting dangerous on a clear day?
5. What does Rule 7(b) say about how to use radar?

---

*Study aid. This lesson paraphrases the Navigation Safety Regulations, 2020, the Collision Regulations, and NGA
and manufacturer radar guidance, and is not the official text. Radar training and your set's own manual come first.*
