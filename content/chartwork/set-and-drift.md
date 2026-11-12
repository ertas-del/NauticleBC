---
id: chartwork.set-and-drift
title: "Set, rate and drift"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 170
summary: What set, rate and drift mean, how to find them by comparing a DR position with a fix, how to apply a known current to get an estimated position and the track and speed made good, and why the offset between DR and fix holds more than the current.
objectives:
  - Define set, rate and drift, including the difference between the direction a current flows toward and the direction wind blows from.
  - Find set, drift and rate from a DR position and a fix at the same time.
  - Apply a given set and rate to a DR to obtain an estimated position, track made good and speed made good.
  - Explain what else is hidden in the offset between a DR and a fix.
prerequisites: [chartwork.dead-reckoning, chartwork.fixes-bearings]
estimated_minutes: 17
key_facts:
  - "Set is the direction toward which a current flows (a current to the east has a set of 090); a wind is named by the direction it comes from."
  - "Rate is the speed of the current in knots; drift is the distance the current carries the ship in the elapsed time (some books use drift for the speed, so read the question)."
  - "Drift (nm) = rate (knots) x time (hours)."
  - "To find set and drift, plot the DR position and a fix for the same time: the direction from the DR to the fix is the set, and the distance between them is the drift."
  - "Rate = drift divided by the hours since the DR was started from the last fix."
  - "The offset between a DR and a fix is the sum of all errors (current, leeway, steering error, compass error, log error); it cannot be separated into causes."
  - "To apply a known current: from the DR position draw the set direction for the drift distance; the end is the estimated position (EP)."
  - "Track made good is the direction from the last fix to the EP or later fix; speed made good is that distance divided by the elapsed time."
source_ids: [chw-a-bowditch-2019-ch9-dr, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Set, rate and drift

**Study aid only. Not for navigation.** The positions and currents here are invented.

## What you need to know

The sea is not still. In many parts of the BC coast the water moves at one, two, or in the passes several knots, and it carries the ship with it. A DR made from course and log speed knows nothing about that. Three words describe the effect:

- ==Set==: the ==direction toward which the current flows==. A current flowing to the south-east has a set of 135°. Contrast with wind, which is named by where it blows ==from==.
- ==Rate==: the ==speed of the current in knots==.
- ==Drift==: the ==distance the current carries you in the time==. Drift = rate × time.

Some textbooks use "drift" for the speed of the current. Read the units in the question: knots means speed, nautical miles means distance. The three-word set, rate and drift is used in the BCIT outcomes for this ticket.

## Finding set and drift from a DR and a fix

You find the current by comparing where you thought you were with where you are.

1. Keep a DR from a fix (the previous good position).
2. When you get a new fix, plot the DR position for the same time.
3. Join the DR position to the fix. The ==direction from the DR to the fix is the set==. The ==length of the line is the drift==.
4. ==Rate = drift ÷ hours since the DR was started==.

### Worked example 1 (invented)

At 0900 you had a fix. The DR for 1000 is 49° 10.0′ N, 123° 30.0′ W. At 1000 a new fix gives 49° 08.9′ N, 123° 28.4′ W. The fix lies south-east of the DR.

- Difference of latitude = −1.1′ (south); departure = 1.6′ × cos 49° = 1.1 nm east.
- Set = 135° T. Drift = √(1.1² + 1.1²) = about ==1.5 nm== (rounded figures).
- Time since the DR began: 1 hour. Rate = 1.5 ÷ 1 = ==1.5 knots==.

### Worked example 2: a longer interval

A DR was started at 0900 and the fix is taken at 1130, 2.5 hours later. The fix lies 3.0 nm from the DR position in the direction 200°. Set = 200°, drift = 3.0 nm, rate = 3.0 ÷ 2.5 = ==1.2 knots==.

The longer the interval, the more the current may have changed in strength or direction, so the answer is an average over the whole interval, not the current now.

## What the offset really contains

The difference between a DR and a fix is the sum of ==everything the DR ignored==: the true current, leeway, steering error, compass error and log error. You cannot tell them apart from one comparison. If the log reads high, the DR runs ahead of the truth, and the offset suggests a current setting astern. If a compass error was left uncorrected, the DR tracks off to one side, and you would think there was a cross current.

So treat "set and drift from DR and fix" as ==the effect to apply to the next DR==, not as proof of the current itself. Check the compass error and the log when the offset is larger than the tables suggest.

## Applying a known current

Sometimes you do know the current, from the Tide and Current Tables, a tidal stream atlas or an observed set. To predict where the ship will be:

1. Lay the DR from the last fix (course and log distance).
2. From the DR position draw a line in the ==set direction== for the ==drift distance== (rate × hours since the last fix).
3. The end of that line is the ==estimated position (EP)==.
4. The line from the fix to the EP gives the ==track made good== (direction) and, divided by the time, the ==speed made good==.

### Worked example 3 (invented)

From a fix at 1000 at 49° 00.0′ N, 123° 30.0′ W the ship steers 270° T at 8.0 knots (log) for 2 hours. The current sets 180° T at 1.5 knots.

1. DR distance = 8.0 × 2 = 16.0 nm west: ==49° 00.0′ N, 123° 54.4′ W==.
2. Drift = 1.5 × 2 = ==3.0 nm== toward 180°.
3. EP: 3.0 nm south of the DR, ==48° 57.0′ N, 123° 54.4′ W==.
4. From the fix to the EP: 16.3 nm on a track of ==259° T==. Speed made good = 16.3 ÷ 2 = ==8.1 knots==.

The ship's head was 270°, but she made good about 259°. She is being set to the south while moving west.

## Directions: a common slip

A current setting 090° pushes you east. A wind from 090° blows toward the west. In a question that gives "current 040° at 2 knots", the direction is where the water is going. In a question that gives "wind NE", the direction is where it comes from. Draw the arrow with the head pointing the way the water goes.

## BC example

On a flooding tide in a narrow BC passage, a boat steers a steady course with the log reading 7 knots and finds she is arriving at each mark about 10 minutes ahead of her DR. Her GNSS shows 8.5 knots over the ground. The rate is about 1.5 knots in the direction of her track (a fair current). She uses the speed made good for her ETAs, and knows that when the tide turns the same stretch will be slower.

## Exam traps

- Reading the set as the direction the current comes from.
- Dividing drift by the wrong time (the time since the fix, not the clock time).
- Applying the offset in the wrong sense: the vector runs from the DR to the fix.
- Forgetting that a DR must be restarted from the fix after the offset is found.
- Treating the offset as pure current when the compass error or log is doubtful.
- Using rate as the drift distance without multiplying by time.

## Check yourself

1. Define set, rate and drift.
2. The fix is 2.0 nm north-east (045°) of the DR after 1.5 hours. Set, drift and rate?
3. A current sets 270° at 2 knots for 45 minutes. Drift?
4. The wind is "from the south-west". The current is "setting south-west". Which one points toward the north-east?
5. Why can the DR-to-fix offset not be called "the current"?
