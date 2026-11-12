---
id: chartwork.running-fix
title: "Running fix"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 140
summary: "Getting a fix from one object by advancing the first line of position along the run to the time of the second, allowing for a current, and the two short cuts: doubling the angle on the bow and the four-point (45 and 90 degree) bearing."
objectives:
  - Plot a running fix by advancing the first line of position by the course and distance run, and label it correctly.
  - Explain why a running fix is less reliable than a simultaneous fix and how a current affects it.
  - Use doubling the angle on the bow and the 45-and-90 bearing to find the distance off without plotting.
  - Work out the distance abeam from the distance at the second bearing.
prerequisites: [chartwork.fixes-bearings, chartwork.speed-time-distance]
estimated_minutes: 18
key_facts:
  - "A running fix uses lines of position taken at different times; the earlier line is advanced by the course and distance run to the time of the later line, and the intersection is the fix."
  - "The advance is made with the ship's course and distance over the ground between the observations; if only the run through the water is known, a known current must be added."
  - "A running fix is less reliable than a simultaneous fix, because errors in speed, course and current are carried into the position."
  - "Label a running fix RFix with the time of the later observation."
  - "Doubling the angle on the bow: take the first bearing when it is less than 45 degrees on the bow and the second when the angle on the bow has doubled; the distance run between them equals the distance off at the second bearing."
  - "Four-point bearing: take a bearing 45 degrees on the bow and again when the object is abeam (90 degrees); the distance run equals the distance off when abeam."
  - "When doubling the angle, the distance abeam is the distance at the second bearing multiplied by the sine of the second angle on the bow."
  - "The angle on the bow is the relative bearing from the ship's head; both short cuts need a steady course and speed between the bearings."
  - "Before advancing a bearing, convert it to true with the compass error for the ship's heading."
source_ids: [chw-a-bowditch-2002-ch8-piloting, sixty-sailingissues-fixes, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Running fix

**Study aid only. Not for navigation.** All numbers are invented.

## What you need to know

Often you can see only one fixed object, such as a lighthouse on a headland. One bearing gives one line of position (LOP). If you wait, the ship moves, the bearing changes, and you can take another. The two lines come from different places and different times, but you know how far and in what direction you moved between them. So you ==advance the first line== by that run, and the second line crosses it at your position. That is a ==running fix==.

## The procedure

1. Take the first bearing, convert it to true, note the time, and plot the LOP through the object.
2. Keep a steady course and speed. Note the log or time.
3. Take the second bearing, convert it to true, note the time, and plot that LOP.
4. Find the ==run== between the two times: the course and the distance, from speed × time.
5. ==Advance the first LOP==: pick any point on it (the object itself is easiest) and move that point by the run, in the direction of travel. Draw through the new point a line ==parallel== to the first LOP.
6. The point where the advanced first line crosses the second line is the ==running fix==. Label it with a circle, "RFix" and the time of the second bearing. Restart the DR from it.

The run you move by should be your course and distance ==over the ground==. If you only know the course steered and the log distance (through the water), a current will carry you off the DR. When the current is known, add it to the run by vector (lesson 17).

## Worked example 1: the 45-and-90 shortcut (invented)

A ship makes 10 knots. At 1012 a headland light bears 45° on the bow. At 1030 it is abeam.

- Time between = 18 min. Run = 10 × 18 ÷ 60 = ==3.0 nm==.
- The triangle has a 45° angle at the first position and a right angle at the second, so it is isosceles: the side from the second position to the light equals the run. The ==distance off when abeam is 3.0 nm==.

No plot is needed to know how far off the light will be at its closest. This is the ==four-point bearing== (four points is 45°).

## Worked example 2: doubling the angle (invented)

Heading 090° T, speed 7.5 knots. At 1100 the light bears 060° T, which is 30° on the port bow (090 − 060). At 1124 it bears 030° T, which is 60° on the port bow: the angle has doubled.

1. Run = 7.5 × 24 ÷ 60 = ==3.0 nm==.
2. The triangle ship-ship-light has angles 30° at the first position, 120° at the second, 30° at the light, so it is isosceles. The ==distance off at the second bearing equals the run: 3.0 nm==.
3. The distance when the light comes abeam is the distance at the second bearing times the sine of 60°: 3.0 × 0.866 = ==2.6 nm==.

The condition: the ==first angle must be less than 45° on the bow==, so that the doubled angle is less than 90° and the object is still forward of the beam at the second bearing. You learn how close you will pass before you get there, which is the whole value of the method.

## Worked example 3: a full plot with a current (invented)

A ship steers 270° T at 8.0 knots (log). At 1000 a tower bears 331° T. At 1030 it bears 020° T. A current of 1.0 knot sets 180° T.

- Run through the water: 8.0 × 30 ÷ 60 = 4.0 nm on 270°.
- Current: 1.0 × 30 ÷ 60 = 0.5 nm toward 180°.
- Run over the ground: the sum of the two vectors, 4.0 nm west plus 0.5 nm south, is 4.03 nm on 263°.

Advance the 331° line by the ==ground run==, not the water run. The line then crosses the 020° line at the right place, here ==49° 00.0′ N, 123° 30.0′ W== (the tower is 49° 04.7′ N, 123° 27.4′ W).

Had the current been ignored, the fix would be about 0.3 nm out for this geometry. With a stronger current, a longer interval or a smaller angle between the bearings, the error is larger. That is the key weakness of a running fix: the position is only as good as the run.

## Reading a running fix

- ==Less reliable than a simultaneous fix==. Treat it as such, and confirm with a transit, a depth or a ranges fix when you get the chance.
- A shorter interval between bearings reduces the effect of an uncertain run but also reduces the angle between the lines.
- The angle between the two lines should be big: 30° or more, and preferably 45° or more.
- It is still much better than a DR, which has no check at all.

## BC example

A skipper running down a coast sees a single lighthouse on a point. She takes it at 45° on the bow, then abeam 20 minutes later at 9 knots. The run is 3.0 nm, so the lighthouse will be 3.0 nm off when abeam. The chart shows a reef extending 2.5 nm off the point, which would leave only 0.5 nm clearance. She sees at once that she must alter away, and can do so while the light is still on the bow, not when the reef is already abeam.

## Exam traps

- Moving the first line in the wrong direction (backwards).
- Using the distance through the water as the run when a known current has not been added.
- Moving the first line by the correct distance but drawing the advanced line at the wrong angle (it must stay parallel).
- Applying doubling the angle when the first angle is 45° or more.
- Quoting the distance at the second bearing as the distance abeam. It is longer by a factor of 1 ÷ sine of the angle.
- Forgetting to convert the compass bearings to true first.

## Check yourself

1. In a running fix, what is advanced, and by how much?
2. A vessel at 12 knots takes a bearing 45° on the bow at 0800 and abeam at 0815. How far off is the object when abeam?
3. A ship at 6 knots takes an object 25° on the bow, then 50° on the bow 30 minutes later. What is the distance off at the second bearing?
4. Why is a running fix less reliable than a simultaneous fix?
5. How do you label a running fix?
