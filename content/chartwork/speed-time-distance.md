---
id: chartwork.speed-time-distance
title: "Speed, time and distance"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 100
summary: The knot, the formula distance = speed x time and its two rearrangements, the three-minute and six-minute rules, converting minutes to hours, average speed over several legs, and the difference between speed through the water and speed over the ground.
objectives:
  - Use D = S x T, T = D / S and S = D / T with consistent units and convert minutes and hours correctly.
  - Use the six-minute and three-minute rules for quick distances.
  - Work an ETA from a departure time, and an average speed over two legs.
  - Say the difference between speed through the water and speed over the ground, and which one the log and GNSS give.
prerequisites: [chartwork.latitude-longitude-distance]
estimated_minutes: 14
key_facts:
  - "A knot is one nautical mile per hour; say 'knots', never 'knots per hour'."
  - "Distance = speed x time; time = distance / speed; speed = distance / time (distance in nautical miles, speed in knots, time in hours)."
  - "To use minutes, divide by 60: distance = speed x minutes / 60."
  - "Six-minute rule: the distance run in 6 minutes is the speed divided by 10 (8 knots gives 0.8 nm); in 3 minutes it is the speed divided by 20."
  - "One minute at a speed of S knots covers S / 60 nautical miles; at 12 knots that is 0.2 nm per minute."
  - "Average speed over a passage is total distance divided by total time, not the average of the speeds."
  - "A log measures speed through the water; GNSS gives speed over the ground; the two differ by the current (and leeway) along the track."
  - "ETA is departure time plus the time to run, in the same time convention (24-hour clock, four figures)."
source_ids: [chw-a-bowditch-2019-ch9-dr, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Speed, time and distance

**Study aid only. Not for navigation.** All figures are invented.

## What you need to know

Everything in dead reckoning hangs on three quantities. A ==knot is one nautical mile per hour==, so a vessel doing 15 knots covers 15 nm in an hour. The three are tied by one formula and its two rearrangements:

- ==Distance = speed × time==
- ==Time = distance ÷ speed==
- ==Speed = distance ÷ time==

Units must match: nautical miles, knots and hours. When time is in minutes, ==divide by 60==. So ==distance = speed × minutes ÷ 60==. It is wrong to say "knots per hour"; a knot already contains the hour.

## The six-minute rule

Six minutes is a tenth of an hour. So ==the distance run in 6 minutes is the speed ÷ 10==: at 8 knots, 0.8 nm; at 9.5 knots, 0.95 nm. In 3 minutes it is the speed ÷ 20. One minute is a sixtieth of the speed: at 12 knots, 0.2 nm each minute. For anything else, use the formula.

A related memory aid is "60 D Street": 60 × distance = speed × minutes. It is the same formula, rearranged so that you can work with minutes without dividing.

## Worked examples (invented numbers)

### Example 1: time and ETA

A passage is 14.5 nm at 9 knots. Time = 14.5 ÷ 9 = 1.611 h. Multiply the 0.611 by 60: 36.7 minutes. So 1 h 36.7 min, rounded to ==1 h 37 min==. Departing at 0830, the ETA is 0830 + 1 h 37 min = ==1007==.

### Example 2: speed from time and distance

A vessel covers 2.5 nm between two buoys in 18 minutes. Speed = 2.5 ÷ (18 ÷ 60) = 2.5 ÷ 0.3 = ==8.3 knots==.

### Example 3: a short distance quickly

How far in 7 minutes at 12 knots? Formula: 12 × 7 ÷ 60 = ==1.4 nm==. By the rule: 6 minutes at 12 knots is 1.2 nm, plus one minute at 0.2 nm per minute is 1.4 nm ✓.

### Example 4: average speed over two legs

Leg 1: 6.0 nm at 8 knots takes 6.0 ÷ 8 = 0.75 h = 45 min. Leg 2: 9.0 nm at 6 knots takes 9.0 ÷ 6 = 1.5 h = 90 min. Total 15.0 nm in 135 min = 2.25 h.

Average speed = 15.0 ÷ 2.25 = ==6.7 knots==. It is not (8 + 6) ÷ 2 = 7, because more time was spent at the slower speed. Departing 1400, the ETA is 1400 + 2 h 15 min = ==1615==.

### Example 5: minutes without dividing

A vessel steams at 9 knots for 37 minutes. Distance = 9 × 37 ÷ 60 = 333 ÷ 60 = ==5.55 nm== (about 5.6 nm).

## Through the water or over the ground?

Two speeds matter:

- ==Speed through the water== (from the log, or the engine's revolutions calibrated against it) is what dead reckoning uses. It does not feel a current.
- ==Speed over the ground== (from GNSS) is the vessel's true progress along her track. It includes the effect of any current and leeway.

Steaming at 7 knots through the water with a 1-knot fair current gives about 8 knots over the ground. Against a 1-knot current it is 6. Later lessons use both: the DR is made from the log speed, and the difference between the DR and a fix measures the current.

## Time and the 24-hour clock

Write times as four figures on the 24-hour clock (0830, 1615). The CHS tide and current tables for BC are given in standard time, so on summer time you must subtract an hour before using them. Keep one convention on the plot and the log and write it down.

## BC example

A skipper plans a 22 nm crossing of the Strait of Georgia in a boat that cruises at 10 knots. Time = 22 ÷ 10 = 2.2 h = 2 h 12 min. She wants to be through the narrows at the far side before a flooding stream builds, so she subtracts the 2 h 12 min from the time she needs to arrive and gets her latest departure time. If the stream will cost her 1 knot over the ground for the whole crossing, her speed over the ground is 9 knots, the crossing takes 22 ÷ 9 = 2.44 h = 2 h 27 min, and she leaves 15 minutes earlier.

## Exam traps

- Putting minutes straight into the formula: 9 knots for 37 minutes is not 9 × 37 nm.
- Averaging two speeds instead of dividing total distance by total time.
- "0.5 hours" is 30 minutes, not 50. Convert the decimal by multiplying by 60.
- Using GNSS speed (over the ground) as the speed for a DR.
- Forgetting that a time on a tide table may be standard time while the clock is on summer time.

## Check yourself

1. How far does a vessel go in 6 minutes at 10.5 knots?
2. How long to run 7.0 nm at 8 knots?
3. A vessel covers 4.2 nm in 28 minutes. What speed?
4. She leaves at 0945 and needs 2 h 20 min. ETA?
5. Which speed does a DR use, and why?
