---
id: chartwork.course-to-steer
title: "Course to steer to counter a current"
module: chartwork
tickets: [master-limited-60gt]
order: 180
summary: "The vector triangle for the course to steer and speed made good in a current, its reverse (track made good from a course steered), a sine check, a worked passage with ETA and compass course, and the case where the current is too strong."
objectives:
  - Draw the triangle of velocities to find the course to steer and the speed made good for a wanted track.
  - Find the track and speed made good from a given course, speed and current.
  - Convert the course to steer from true to compass, and work an ETA from the speed made good.
  - Check a plotted answer with a sine formula, and say when no course can hold the track.
prerequisites: [chartwork.set-and-drift, chartwork.compass-error-tvmdc]
estimated_minutes: 20
key_facts:
  - "To find the course to steer: from the start draw the wanted track; from the same start draw the current vector (set direction, length equal to the rate for one hour); from the end of the current vector swing an arc of radius equal to the ship's speed through the water to cut the track; the line from the end of the current vector to that point is the course to steer."
  - "The distance along the track from the start to that point is the speed made good (in knots, for one hour)."
  - "The course to steer is in true and must be converted to compass (subtract easterly error, add westerly) before it is given to the helm."
  - "Steer up-current: the ship's head points to the up-current side of the track, by the angle that cancels the cross-track part of the current."
  - "Check by formula: sine of the correction angle = (rate x sine of the angle between set and track) / ship's speed."
  - "To find the track and speed made good from a course steered: draw the course and speed vector from the start, add the current vector from its end; the line from the start to the end is the track and speed made good."
  - "ETA = distance along the track / speed made good, added to the departure time."
  - "If the cross-track part of the current is greater than the ship's speed, no course can hold the track."
source_ids: [chw-a-bowditch-2019-ch9-dr, sixty-bcit-naut-1475, sixty-bowditch-2019-compass-conversions]
review_status: needs_expert_review
---

# Course to steer to counter a current

**Study aid only. Not for navigation.** All tracks, speeds and currents are invented.

## What you need to know

A DR from the log says where you will go if the water is still. If the water is moving, you go where the water takes you as well. To arrive at a point rather than somewhere near it, you must ==point the bow to one side of the wanted track== so that the current pushes you back onto it. The question is: how far to one side, and how fast will I make good?

The answer is a ==triangle of velocities==, drawn on the chart or on paper.

## The plot: course to steer

You know: the wanted ==track== (course over the ground, from the chart), the ==ship's speed through the water==, and the current's ==set== and ==rate==.

1. Draw the wanted track from your starting point A.
2. From A, draw the ==current vector== AC: in the direction of the set, with a length equal to the rate (in nautical miles for one hour, so a 2-knot current is 2 nm).
3. With the dividers set to the ship's speed (in nautical miles for one hour, from the latitude scale) and the point on C, swing an arc that cuts the track at D.
4. Join C to D. The direction C to D is the ==course to steer== (true).
5. The length A to D is the ==speed made good== (knots, because the vectors are for one hour).

All three vectors must be drawn for the same length of time. If the ship does 8 knots and the current 2 knots, use 8 nm and 2 nm, not 8 nm and 1 nm.

The idea: the ship's own motion (CD) plus the current (AC) adds up to the motion over the ground (AD), which lies along the track.

## Worked example 1 (invented): the standard problem

Wanted track 090° T. Ship's speed 8 knots. Current: set 040°, rate 2 knots.

1. The current vector AC is 2 nm toward 040°.
2. An arc of 8 nm from C cuts the track at D. Measuring the plot: CD points to about ==101° T== and AD is about ==9.1 nm==.
3. So the ==course to steer is 101° T== and the ==speed made good is 9.1 knots==.

**Why 101?** The current sets toward 040°, which has a component along the track (helping) and a component toward the north (left of the track). To cancel the northward push, the bow is pointed south of the track: 11° to the right.

**Check by formula.** The angle between the set (040°) and the track (090°) is 50°. Sine of the correction = 2 × sin 50° ÷ 8 = 2 × 0.766 ÷ 8 = 0.1915, so the correction is ==11.0°==. Course to steer = 090 + 11.0 = ==101.0° T==. Speed made good = 8 × cos 11.0° + 2 × cos 50° = 7.85 + 1.29 = ==9.1 knots==.

**Compass course.** Variation 16° E, deviation on this heading 2° W, so compass error 14° E. Compass course = 101 − 14 = ==087° C==. Helm: "steer 087".

**ETA.** The track is 18.3 nm. Time = 18.3 ÷ 9.14 = 2.0 hours.

## Worked example 2: a second problem

Track 095° T, speed 12 knots, current set 170°, rate 2.5 knots. The angle between set and track is 75°. Sine of correction = 2.5 × sin 75° ÷ 12 = 0.2012, so the correction is 11.6°. The current sets to the right (south) of the track, so the bow goes to the left: 095 − 11.6 = ==083.4° T==. Speed made good = 12 × cos 11.6° + 2.5 × cos 75° = 11.76 + 0.65 = ==12.4 knots==. A plot gives about 083½° and 12.4 knots.

## The reverse: track made good from a course steered

When you are given a course and speed (your DR) and a current, you find where you will really go.

1. From A draw AB: course steered, length equal to speed.
2. From B draw BC: set, length equal to rate.
3. Join A to C. The direction is the ==track made good==; the length is the ==speed made good==.

### Worked example 3 (invented)

Steering 270° at 6 knots. Current sets 180° at 1.5 knots. West 6 nm, then south 1.5 nm. The track is 270° − arctan(1.5 ÷ 6) = 270 − 14.0 = ==256° T==, and the speed made good is √(6² + 1.5²) = ==6.2 knots==.

## Worked example 4: a passage

Track 330° T for 14.0 nm. Ship's speed 6.5 knots. Current sets 240° at 1.8 knots (to the south-west). Without the current the passage would take 14.0 ÷ 6.5 = 2.15 h = 129 min.

1. The current vector points 240°, which is 90° to the left of the track (240 − 330 = −90), a pure cross current.
2. Sine of correction = 1.8 ÷ 6.5 = 0.277, so the correction is 16.1°, to the right (starboard): 330 + 16.1 = ==346° T==.
3. Speed made good = 6.5 × cos 16.1° = 6.25 knots (there is no component along the track).
4. Time = 14.0 ÷ 6.25 = 2.24 h = ==134 minutes==.

If the skipper had steered the track (330°) and ignored the current, the water would have carried her 1.8 × 2.15 = 3.9 nm toward the south-west during the 2.15 hours of the DR passage, and she would have arrived that far down-current of the destination.

## When the current is too strong

The arc of radius equal to ship's speed must reach the track. If the ==cross-track part of the current is bigger than the ship's speed==, the arc falls short and there is no solution: no course holds the track.

Example: a boat doing 4 knots with a 4.5-knot current setting straight across her track cannot hold it. She can only choose to be carried down-current, to turn back, or to wait for slack water. With a 3-knot cross current instead, the correction is 48.6° and the speed made good is only 2.6 knots. In the passes of the BC coast the rates can be large enough for this to matter. This is why passes are planned for slack water (lesson 22).

## BC example

A skipper crossing a wide channel in a cross tide works out her course to steer and sets it. Halfway across she takes a fix. If it lies down-current of the wanted line, the current is stronger than predicted, and she increases the correction; if it lies up-current, it is weaker. She plots a new triangle from the new fix. The current changes through the passage, so the first triangle is a good start but not a promise.

## Exam traps

- Pointing the bow to the wrong side. The bow goes to the up-current side of the track, which is the side the water comes from.
- Drawing the current vector from the end of the track instead of from the start.
- Mixing the lengths: ship's speed for one hour with the current for a different time.
- Giving the course to steer in true when the question asks for compass.
- Using the ship's speed as the speed made good.
- Reading the set as the direction the current comes from.

## Check yourself

1. What are the three vectors in the triangle of velocities, and what does each represent?
2. Track 000°, speed 8 knots, current setting 090° at 2 knots. Which side of the track does the bow point, and why?
3. How is the speed made good found on the plot?
4. A boat doing 4 knots meets a current whose cross-track part is 5 knots. Can she hold her track?
5. The course to steer is 120° T and the compass error is 14° W. What is the compass course?
