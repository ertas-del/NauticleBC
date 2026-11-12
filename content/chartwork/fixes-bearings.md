---
id: chartwork.fixes-bearings
title: "Fixing by cross bearings"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 120
summary: How to take two or three compass bearings of charted objects, turn them into true lines of position, plot them from the objects toward the ship, judge the angle of cut and the cocked hat, and treat buoys with caution. Includes a worked fix with positions.
objectives:
  - Take, correct and plot cross bearings to give a fix, labelled with its time.
  - Choose objects so the lines cross at a good angle, and say why a small angle magnifies errors.
  - Read a cocked hat, say what it tells you, and choose the corner to use near a danger.
  - Say which objects give a fix and which only an estimated position.
prerequisites: [chartwork.compass-error-tvmdc, chartwork.dead-reckoning]
estimated_minutes: 17
key_facts:
  - "A fix by cross bearings is the intersection of the bearing lines (lines of position) to two or more charted objects."
  - "A bearing is the direction from the ship to the object, so the line of position is drawn from the object back toward the ship, on the reciprocal."
  - "Convert each compass bearing to true with the compass error for the ship's heading before plotting it."
  - "Two bearings are best about 90 degrees apart; with three, about 60 degrees between neighbouring lines; avoid angles of cut below about 30 degrees."
  - "For the same bearing errors, a fix with a 30 degree angle of cut is about twice as far out as one with a 90 degree cut (error varies as the cosecant of the angle)."
  - "Use three lines of position when you can; if they do not meet at a point, the triangle (a cocked hat) shows that at least one bearing is wrong."
  - "Near a danger, take the corner of the cocked hat that is closest to the danger."
  - "A bearing of a floating buoy gives only an estimated position because the buoy swings on its mooring."
  - "Take the bearings quickly, one after another, and note the time of each, because the ship is moving."
source_ids: [chw-a-bowditch-2002-ch8-piloting, chw-a-sailingissues-navcourse4, sixty-saferoceans-course-info]
review_status: needs_expert_review
---

# Fixing by cross bearings

**Study aid only. Not for navigation.** The objects and positions are invented.

## What you need to know

A single bearing of a charted object puts you on a straight line, a ==line of position (LOP)==. Two lines cross at one place and give a ==fix==. A third line checks the first two. This is the commonest way to fix in coastal pilotage, and the one a CP1 candidate must do quickly and cleanly.

A bearing is the direction ==from the ship to the object==. So the ship lies on the line through the object, on the side the bearing points away from: plot the line from the object ==back toward you== (the reciprocal, bearing ± 180°), and draw only the part near your DR.

## The procedure

1. **Choose objects.** Charted, fixed, and positively identified: lighthouses, beacons, prominent towers and steeples, tangents of steep land. Check each against the chart and your DR before you take a bearing. Mistaking one tower for another is the commonest cause of a wild fix.
2. **Choose a good angle.** Two lines are best when they cross at about ==90°==. With three, about ==60°== between neighbouring lines (about 120° between the outer two) is best. Avoid ==less than about 30°==.
3. **Take the bearings quickly.** Note the ship's compass heading, then bearing one, bearing two, (bearing three) and the time of each. The ship moves between them, so the faster the better. Practice differs about the order. A sensible rule is to leave the bearing that changes fastest (one near the beam) until last, so that it is closest to the time of the fix.
4. **Convert to true.** Apply the compass error for the ship's heading (lesson 8), or variation only for a hand compass held clear of iron.
5. **Plot** each line from its object along the reciprocal, extending it across your DR position.
6. **Label** the fix with a circle and the time in four figures, and restart the DR from it.

## Worked example 1 (invented)

The ship is heading 100° C. Compass error 14° E (variation 16° E, deviation 2° W). At 1430 the skipper takes a bearing of a lighthouse (49° 04.3′ N, 123° 33.8′ W): 316° C. Immediately after, a tower (49° 02.6′ N, 123° 25.3′ W): 036° C.

1. True bearings: 316 + 14 = ==330° T== for the lighthouse; 036 + 14 = ==050° T== for the tower. The angle between them is 80°, a good cut.
2. Lines from the objects on the reciprocals: from the lighthouse along 150°; from the tower along 230°.
3. The lines cross at ==49° 00.0′ N, 123° 30.0′ W==. That is the fix at 1430.

By geometry, this is 5.0 nm from the lighthouse and 4.0 nm from the tower, which you can check with dividers on the latitude scale.

## Why the angle of cut matters

Suppose two bearings of objects 5 nm away are each wrong by 3°, in opposite directions. The resulting fix is out by about:

| Angle between the bearings | Fix error |
|---|---|
| ==90°== | about 0.4 nm |
| 60° | about 0.6 nm |
| ==30°== | about 1.3 nm |
| 20° | about 2.2 nm |

The error grows with the cosecant of the angle of cut: at 30° it is about twice what it is at 90°, and it rises quickly below that. If your only objects are nearly in line, a small angle gives a poor fix. Do not trust it; add a range, a depth or another bearing.

## The cocked hat

Plot three lines. If all were perfect they would cross at one point. In practice they form a small triangle, the ==cocked hat==. Its size is a warning, not a result.

- A **large** hat means a mistake: a wrong identification, an arithmetic error in the conversion, the wrong sign for compass error, or a plotting slip. Check them, then re-take the bearings.
- A **small** hat is normal. The ship is probably near its middle if the errors are random. A constant bearing error (an uncorrected compass error) shifts the whole figure and the hat does not betray it, so also compare the result with the DR.
- ==Near a danger, use the corner closest to the danger.== You must assume the worst. For example, with three bearings that give a hat of about 0.3 nm and a rock 0.4 nm from one corner, plan as if you were at that corner.

## Buoys

Bearings of buoys give only an ==estimated position==. A buoy swings on its mooring and may be off station or adrift. Use buoys to confirm a position fixed from fixed objects, not to establish it, and never to clear a danger by a narrow margin.

## BC example

Leaving harbour in haze, a skipper cannot see the lighthouse but can see a charted tower and a bluff with a beacon. The two are about 40° apart. She takes a bearing of each, plots a fix, and notes that the angle is smaller than she would like. She adds a radar range (lesson 13) from the bluff and the fix is confirmed. The next time, she waits until a third object comes into view and takes three bearings, so the cocked hat tells her the compass error is small.

## Exam traps

- Plotting the compass bearing without converting to true.
- Drawing the line on the wrong side of the object (away from the ship) so that the lines never cross where the ship is.
- Using the deviation for the direction of the bearing instead of the ship's heading.
- Taking two bearings that are almost in line and trusting the result.
- Ignoring a large cocked hat.
- Calling a position from buoy bearings a fix.

## Check yourself

1. What is a line of position, and how many do you need for a fix?
2. A lighthouse bears 072° T from the ship. Where on the chart is the line drawn, and along what bearing?
3. Why is 90° the best angle between two bearings?
4. Three bearings give a cocked hat with a rock close to one corner. Which corner do you use?
5. Why are buoy bearings less reliable than bearings of a lighthouse?
