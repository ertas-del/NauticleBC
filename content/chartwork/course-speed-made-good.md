---
id: chartwork.course-speed-made-good
title: "Course and speed made good, ETA and fuel"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 200
summary: How to find the course and speed made good between two fixes, how to use the speed made good for ETAs and for the speed needed to arrive on time, and how to work fuel for a coastal passage from consumption, time and a reserve.
objectives:
  - Find the course made good and speed made good from two fixes (or a fix and an EP) by plane sailing or on the chart.
  - Say how course and speed made good differ from the course steered and the log speed, and why.
  - Work an ETA, a speed required to arrive on time, and an updated ETA after a fix.
  - Work the fuel for a passage from consumption rate and time, add a reserve, and work the range of the vessel.
prerequisites: [chartwork.speed-time-distance, chartwork.dead-reckoning, chartwork.course-to-steer]
estimated_minutes: 19
key_facts:
  - "Course made good is the direction of the line from the departure fix to a later fix (or EP): the path actually made over the ground."
  - "Speed made good = distance between the two positions / time between them."
  - "Course made good differs from the course steered, and speed made good from log speed, by the effect of current, leeway and steering error."
  - "ETA = time now (or departure time) + distance to go / speed made good; use speed over the ground, not log speed, when a current is running."
  - "Speed needed to arrive on time = distance to go / time available."
  - "Fuel for a passage = consumption rate (litres per hour) x time at that rate; time = distance / speed made good."
  - "A foul current lengthens the time and so raises the fuel burned for the same distance, even if the engine rpm is unchanged."
  - "Use the consumption figure for the rpm and speed you will actually run; consumption changes with speed and load."
  - "Add a reserve to the fuel needed; these sources give no fixed percentage, so the skipper sets it and writes it in the passage plan."
  - "Range = usable fuel / consumption rate x speed made good; usable fuel is less than the tank capacity."
  - "Recalculate the ETA and the fuel remaining at each fix."
source_ids: [sixty-bcit-naut-1475, chw-a-bowditch-2019-ch9-dr, chw-a-wikipedia-nautical-mile]
review_status: needs_expert_review
---

# Course and speed made good, ETA and fuel

**Study aid only. Not for navigation.** Positions, speeds and fuel figures are invented.

## What you need to know

The log says how fast you are going through the water. The compass says where the bow points. Neither says what the ship is really doing over the ground. For that you need two positions and the time between them. The straight line from one to the other is the ==course made good== (CMG) and the distance divided by the time is the ==speed made good== (SMG). Some books say "track made good"; it means the same thing.

CMG and SMG differ from the course steered and the log speed because of ==current, leeway and steering error==. They are what the ship achieved, so they are what you use for the ETA and for the fuel.

## Finding CMG and SMG from two fixes

1. Take the departure fix and a later fix (or EP). Note both times.
2. Join them on the chart. The ==true direction of the line== is the CMG.
3. Measure the length of the line on the latitude scale: that is the distance.
4. ==SMG = distance ÷ time== (time in hours).

### Worked example 1 (invented)

Fix at 0800: 49° 00.0′ N, 123° 30.0′ W. Fix at 1100: 49° 12.4′ N, 123° 10.2′ W.

1. Difference of latitude = 12.4′ north. Difference of longitude = 19.8′ east.
2. Mean latitude = 49° 06.2′. Departure = 19.8 × cos 49.1° = ==13.0 nm== east.
3. Distance = √(12.4² + 13.0²) = ==17.9 nm==. Course = arctan (13.0 ÷ 12.4) = ==046° T==.
4. Time = 3 h. SMG = 17.9 ÷ 3 = ==6.0 knots==.

Compare with the DR. If she had been steering 050° T at a log speed of 7.0 knots, the DR for 1100 would lie 21.0 nm along 050°, which is 3.3 nm beyond and to the north-east of the fix. Joining the DR to the fix gives a set of about ==251° T== and a drift of 3.3 nm, a rate of 1.1 knots (lesson 17). This current sets mostly astern of her track, which is why the SMG of 6.0 knots is a full knot below the log speed. The SMG is the figure for ETAs.

## ETA

==ETA = time now + distance to go ÷ speed made good==. Use the speed over the ground, which already contains the current. Use the log speed only if there is no current at all.

### Worked example 2: ETA after a fix

At 1100 the ship is 21.5 nm from the destination. The SMG since the last fix is 6.0 knots, and the current is expected to hold. Time = 21.5 ÷ 6.0 = 3.58 h = 215 min = 3 h 35 min. ==ETA 1435.==

If the SMG had been taken as the 7.0 knots on the log, the ETA would have been 21.5 ÷ 7.0 = 3.07 h = 3 h 04 min, giving 1404: half an hour early. That is the kind of error that leaves the harbour pilot, the berth or the tide gate waiting for a boat that is not there.

### Speed needed to arrive on time

Rearrange the formula: ==speed needed = distance to go ÷ time available==. A 38 nm passage that must be complete within 4 h 30 min needs 38 ÷ 4.5 = ==8.4 knots over the ground==. Compare with what the boat can really do. If the answer is above her cruising speed, the plan is wrong, not the arithmetic.

## Fuel

==Fuel burned = consumption rate × time==. The consumption comes from the engine's data sheet or, much better, from your own log of fuel used per hour at a given rpm. It depends on rpm, load and sea state. Always use the rate for the speed you will actually run.

The time is the passage time from SMG, not from the log. A current that slows you makes the passage longer and the engine runs longer, so the same distance costs more fuel.

### Worked example 3 (invented): the passage

Distance 38 nm. SMG expected 7.5 knots. Consumption 22 litres per hour.

1. Time = 38 ÷ 7.5 = 5.07 h = ==5 h 04 min==.
2. Fuel = 5.07 × 22 = ==111.5 L==.
3. Reserve: the skipper decides on 30 per cent extra, so the plan is 111.5 × 1.3 = ==144.9 L==, say 145 L.

Leaving at 0730 she arrives at 1234.

### The foul current

Same passage, the stream turns against her and the SMG drops to 6.0 knots. Time = 38 ÷ 6.0 = 6 h 20 min. Fuel = 6.33 × 22 = ==139.3 L==. That is 27.9 L more than the plan, without any change at the throttle. The fuel per nautical mile is 22 ÷ 7.5 = 2.9 L at 7.5 knots and 22 ÷ 6.0 = 3.7 L at 6.0 knots.

### Range

Tank capacity 400 L, of which 90 per cent is usable (the tank is never run dry; the sump and pickup need fuel). Usable = 360 L. Endurance = 360 ÷ 22 = 16.4 h. Range at 7.5 knots SMG = 16.4 × 7.5 = ==122.7 nm==. In a foul current of 1.5 knots it falls to 16.4 × 6.0 = 98 nm. Range is not a fixed number: it depends on the current.

### How big a reserve?

None of the sources used for this app gives one required percentage for a small vessel. Some courses teach a "rule of thirds" (one third to go, one third to return, one third spare). Others want the skipper to name a figure. What the examiner will want is that you ==add a reserve, state how big, and say why==. Cover bad weather, a longer route, a diversion to a different port and a foul current. Remember that the reserve is not extra to burn on the way: if the log says you have used it, you turn back or go to the nearest fuel.

## BC example

A skipper plans a run up the Strait of Georgia with a fuel stop at the far end. The planning speed is 8 knots, but the forecast is a headwind and a flood against her in the second half. She works the time by legs: first leg 14 nm at SMG 8.0, second leg 16 nm at SMG 6.5. Times: 1 h 45 min and 2 h 28 min. Fuel at 25 L/h: 43.8 + 61.5 = 105 L. She adds a reserve and checks the plan against the tank gauge and the dock at the other end. She also notes the fuel on board at each fix in the log, so the plan is tested as the day goes on.

## Common mistakes

- Using log speed for the ETA when a current is running.
- Computing the course made good from the course steered rather than from the positions.
- Dividing by minutes: distance ÷ 45 when the time was 45 minutes, instead of 0.75 h.
- Using the whole tank as usable fuel.
- Using an idling or best-economy consumption for a passage at higher rpm.
- Forgetting that fuel depends on time, not distance, so a foul current costs fuel.
- Reporting the reserve as a figure with no reason.

## Check yourself

1. How is the course made good found from two fixes?
2. Two fixes are 12.0 nm apart and 90 minutes apart. SMG? (8.0 knots.)
3. 30 nm to go and an SMG of 6 knots: ETA if it is now 1000? (1500.)
4. Consumption 18 L/h and a passage of 4 h 20 min. Fuel? (78 L, before reserve.)
5. Why does a head current raise the fuel needed?

This is a study aid, not a navigation aid.
