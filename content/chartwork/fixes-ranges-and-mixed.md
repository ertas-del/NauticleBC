---
id: chartwork.fixes-ranges-and-mixed
title: "Fixing by ranges, range and bearing, and depth"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 130
summary: How a measured range puts you on a circle round a charted object, why two ranges are the most accurate radar fix, how one object's range and bearing give a fix, and how a depth contour or sounding is used as a check once corrected for the tide.
objectives:
  - Plot a fix from two ranges, choose between the two crossing points, and plot a fix from one object's range and bearing.
  - Say why radar range is preferred to radar bearing, and when a range fix is poor.
  - Reduce an echo-sounder reading to the chart's datum and use it as a check on a position.
  - Mix lines of position (bearing, range, transit, depth) sensibly in one fix.
prerequisites: [chartwork.fixes-bearings, chartwork.chart-datums]
estimated_minutes: 16
key_facts:
  - "A measured range to a charted object puts the ship on a circle of position centred on the object, drawn as an arc with the dividers set on the latitude scale."
  - "Radar range is more accurate than radar bearing, so a fix from two ranges is preferred to one from range and bearing."
  - "Two range arcs cross at two points; the DR (or a third line) tells you which one is the ship."
  - "As with bearings, the best range fix has the objects roughly 90 degrees apart as seen from the ship."
  - "The range and bearing of one object give a fix: from the object, go the range along the reciprocal of the true bearing."
  - "A depth contour is a line of position used as a check; one sounding alone is not a fix."
  - "To compare an echo sounder reading with the chart: add the transducer's depth below the waterline to get depth of water, then subtract the height of tide to get the charted equivalent."
  - "Measure a range with dividers on the latitude scale level with the work, never on the longitude scale."
  - "Use objects that are positively identified on the radar and have a well-defined edge, such as steep cliffs and small islands, and be wary of low shores."
source_ids: [chw-a-bowditch-2002-ch8-piloting, sixty-saferoceans-course-info, chw-a-bowditch-2019-ch4-charts]
review_status: needs_expert_review
---

# Fixing by ranges, range and bearing, and depth

**Study aid only. Not for navigation.** The objects, ranges and positions are invented.

## What you need to know

A bearing tells you the direction to an object. A ==range== tells you how far away it is. Distance alone puts you on a ==circle of position== centred on the object: you could be anywhere on that circle. Two ranges give two circles, which cross at a ==fix==.

Radar is the usual source of ranges at sea. Its ==range measurement is more accurate than its bearing==, because the bearing depends on the width of the beam and on the heading marker, while the range depends on the timing of the echo. For that reason a fix from ==two radar ranges is preferred to range and bearing== from one object.

## Plotting a range fix

1. Identify the objects on the radar against the chart (shape, distance, bearing roughly from the DR).
2. Measure the range to each object as quickly as possible and note the time.
3. Set the dividers to the range, measured on the ==latitude scale at the side of the chart, level with the work==.
4. Put one point on the charted object and scribe a short arc across your DR. Repeat from the second object.
5. The arcs cross twice. ==The DR chooses== which crossing is the ship. If the two crossings are close together, take a third range or a bearing.
6. The best crossing angle is, again, about ==90°== as seen from the ship. Objects nearly in line give nearly tangent circles and a poor fix.

### Worked example 1: two ranges (invented)

At 1215 the radar gives 4.2 nm to a point (49° 02.1′ N, 123° 35.5′ W) and 5.0 nm to an island (49° 03.8′ N, 123° 25.1′ W). The point is on a bearing near 300° and the island near 040°, so they are about 100° apart, a good angle.

The arcs cross at ==49° 00.0′ N, 123° 30.0′ W== and at 49° 05.7′ N, 123° 32.2′ W. The second crossing is almost 6 nm from the first, far outside any believable DR error, so the first crossing is the fix. If the DR had been poor, a quick bearing of either object would settle it.

## Range and bearing of one object

Radar gives a range and a bearing of the same object at the same instant. The ship is at that range along the reciprocal of the true bearing. This is a fix, but a weaker one, because it carries the bearing error of the radar and the compass or heading marker.

### Worked example 2

A lighthouse (49° 06.0′ N, 123° 40.0′ W) is at 3.1 nm, bearing 255° true. Reciprocal = 255 − 180 = 075°. From the lighthouse go 3.1 nm on 075°:

- Difference of latitude = 3.1 × cos 75° = 0.8′ N.
- Departure = 3.1 × sin 75° = 3.0 nm; difference of longitude = 3.0 ÷ cos 49.1° = 4.6′ E.
- Fix: ==49° 06.8′ N, 123° 35.4′ W==.

A 2° bearing error at 3.1 nm moves the fix by about 0.1 nm sideways, which is small. A range error of 5 per cent moves it about 0.15 nm along the line. At short ranges, range and bearing are good enough; at long ranges the bearing error grows with distance.

## Depth as a line of position

An echo sounder reading, plotted on the chart against the soundings and contours, tells you which charted depth you are over. A sounding is not a fix on its own: many places have the same depth. But if you are crossing a steep slope, a well-defined contour (say the 20 m line) is a line of position, and it can confirm or reject a position from other lines. It is an especially useful check when you have only one bearing.

First reduce the reading to the chart's datum.

### Worked example 3 (invented)

At 1015 the sounder shows 14.2 m. The transducer is 0.8 m below the waterline. The tide height from the tables for 1015 is 2.4 m.

1. Depth of water = 14.2 + 0.8 = ==15.0 m== (the sounder measures from the transducer).
2. Charted depth = depth of water − height of tide = 15.0 − 2.4 = ==12.6 m==.

Now compare 12.6 m with the chart. If the DR says you should be between the 10 m and 15 m contours, you are where expected. If the sounder had read 4.2 m, you would be in much shallower water than the chart shows here and would need to re-examine the position at once. Treat the check as a warning system, not as a position.

## Mixing the lines

Lines of position of different types can be combined in one fix. A bearing and a range from different objects, a transit and a range, a bearing and a depth contour all give a position when their lines cross at a good angle. A transit (lesson 15) is an exceptionally good line because it needs no compass.

When there are three or more lines, the best position is the one where they agree, and any large disagreement is a warning that one is wrong.

## BC example

In a channel with high steep cliffs on both sides the skipper uses radar, because the cliffs return clean echoes. A range of 0.8 nm to the steep face on one side and 1.3 nm to a small islet ahead cross at a good angle, giving a fix between the two bearing lines she could not see in the haze. Near a wide beach with a low sloping shore, the radar range to "the coast" would be unreliable because the echo comes from a part of the shore that is not at the charted waterline. She leaves such ranges out of the fix.

## Exam traps

- Measuring the range on the longitude scale.
- Ignoring the second crossing of the arcs.
- Using one sounding as a fix, or comparing it with the chart without correcting for tide.
- Forgetting the transducer offset when the sounder reads below the transducer.
- Preferring radar bearings to radar ranges.
- Trusting a range to a low, sloping coast, or to a radar echo you cannot identify.

## Check yourself

1. Why does one range not give a fix?
2. Why is a radar range better than a radar bearing?
3. The sounder reads 8.5 m, the transducer is 0.5 m deep and the tide is 1.5 m. What is the charted-equivalent depth?
4. A lighthouse bears 120° T at 2.5 nm. In which direction from the lighthouse is the ship?
5. Two range arcs cross at points 4 nm apart. How do you decide?
