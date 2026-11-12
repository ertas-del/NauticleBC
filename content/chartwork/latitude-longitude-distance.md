---
id: chartwork.latitude-longitude-distance
title: "Latitude, longitude and distance"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 30
summary: How latitude and longitude name a position, how to plot and read one on the chart, why one minute of latitude is one nautical mile, how to measure distance and direction between two positions, and the plane-sailing check.
objectives:
  - Write, plot and read a position in degrees and minutes of latitude and longitude.
  - Measure a distance on the latitude scale level with the work and read a course or bearing with parallel rules.
  - Work out difference of latitude, difference of longitude, departure, course and distance between two positions by calculation, to check a chart answer.
  - Apply the reciprocal rule (add or subtract 180) for a bearing seen from the other end.
prerequisites: [chartwork.charts-and-projections]
estimated_minutes: 18
key_facts:
  - "Latitude is measured north or south of the equator from 0 to 90 degrees; longitude is measured east or west of Greenwich from 0 to 180 degrees. BC lies in north latitude and west longitude."
  - "A position is written latitude first, then longitude, in degrees and minutes (60 minutes = 1 degree), e.g. 49 degrees 10.0 minutes N, 123 degrees 40.0 minutes W."
  - "One minute of latitude is one nautical mile, and the international nautical mile is exactly 1852 m."
  - "Measure distance on the latitude scale at the side of the chart, level with the part of the chart you are measuring, never on the longitude scale along the top or bottom."
  - "A minute of longitude equals a nautical mile only at the equator; it shrinks with latitude (departure = difference of longitude x cos latitude)."
  - "Reciprocal bearing: add or subtract 180 degrees (for example 047 degrees becomes 227 degrees, 310 degrees becomes 130 degrees)."
  - "Directions are measured clockwise from north in three figures: 007, 047, 310 degrees."
  - "Plane sailing: difference of latitude (minutes) = north-south distance in nm; departure = difference of longitude x cos(mean latitude); distance = square root of (dlat squared + departure squared); course = arctan(departure / dlat) named from the quadrant."
source_ids: [chw-a-bowditch-2019-ch4-charts, chw-a-wikipedia-nautical-mile, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Latitude, longitude and distance

**Study aid only. Not for navigation.** All positions are invented.

## What you need to know

Position on a chart is a pair of angles. ==Latitude== is how far you are north or south of the equator (0° to 90°). ==Longitude== is how far you are east or west of the Greenwich meridian (0° to 180°). Everywhere in BC is ==north latitude, west longitude==. The BC coast and its waters lie roughly between 48° and 55° N and between 122° and 134° W (Juan de Fuca Strait in the south, Dixon Entrance in the north, Haida Gwaii in the west).

Each degree is divided into ==60 minutes==. Positions on Canadian coastal work are normally given in degrees and minutes with one decimal: **49° 10.0′ N, 123° 40.0′ W**. Always write latitude first.

## The most useful fact in chartwork

==One minute of latitude is one nautical mile.== The nautical mile is defined internationally as exactly ==1852 m==. A knot is one nautical mile per hour.

So the latitude scale down the side of the chart is also your distance scale. The rule: ==measure distance on the latitude scale, level with the part of the chart you are working on==. Take the distance on the dividers, carry them across to the side scale at the same height on the chart, and read the minutes. On a Mercator chart the scale grows toward the north, so using a scale at a different latitude gives a wrong answer. Never use the longitude scale: a minute of longitude is a nautical mile only at the equator and gets shorter as you go north.

## Plotting a position

1. Lay the parallel rules along the nearest parallel of latitude, and walk them to the latitude you want (read on the side scale).
2. Mark the latitude line lightly.
3. With the dividers or the rules, find the longitude on the top or bottom scale and mark where the meridian meets your line.
4. Label it with the time and the type of position (fix, DR, EP).

To read a position, work the other way round: walk the rules to the point to read its latitude, then drop a vertical to the longitude scale.

## Direction

Directions are measured ==clockwise from north through 360°, always three figures== (007°, 047°, 310°). True directions are read on the true compass rose, or by parallel rules against a meridian. To transfer a course, walk the parallel rules from the line to the rose and read where the edge cuts the rose. ==The reciprocal== (the same line seen from the other end) is ==± 180°==: 047° ↔ 227°, 310° ↔ 130°. If adding 180 passes 360, subtract 360 instead.

## Worked example 1: course and distance on the chart

Invented positions:

- A: 49° 10.0′ N, 123° 40.0′ W
- B: 49° 34.0′ N, 124° 12.0′ W

On the chart you plot both points, join them, lift the distance on the dividers and read it on the latitude scale beside the middle of the line. You find about 31.8 nm. You walk parallel rules to the rose and read 319°.

### The check by calculation (plane sailing)

1. Difference of latitude = 49° 34.0′ − 49° 10.0′ = 24.0′ N, which is 24.0 nm.
2. Difference of longitude = 124° 12.0′ − 123° 40.0′ = 32.0′ W.
3. Mean latitude = (49° 10.0′ + 49° 34.0′) ÷ 2 = 49° 22.0′ = 49.37°.
4. Departure = 32.0 × cos 49.37° = 32.0 × 0.6512 = 20.8 nm west.
5. Distance = √(24.0² + 20.8²) = √(576 + 434) = ==31.8 nm==.
6. Course: tan = departure ÷ dlat = 20.8 ÷ 24.0 = 0.868, angle 41.0°. Moving north and west is the north-west quadrant, so the course is N 41° W = ==319°==.

The calculation agrees with the chart. If they differ by more than a mile or a few degrees, check the plot.

## Worked example 2: reciprocal bearing

From the ship, a lighthouse bears 072° true. The bearing of the ship from the lighthouse is 072 + 180 = ==252°== (the light is east-north-east of you, so you are west-south-west of it). If a lighthouse bears 205° from the ship, the ship bears 205 − 180 = ==025°== from the light.

## Worked example 3: positions from a distance and direction

Start at 49° 00.0′ N, 123° 30.0′ W. Go 15.0 nm on 000° (due north). Latitude increases by 15.0′, so the new position is 49° 15.0′ N, 123° 30.0′ W, because the longitude does not change on a north-south run.

Now go 12.0 nm due west from there. Departure is 12.0 nm. Difference of longitude = departure ÷ cos latitude = 12.0 ÷ cos 49.25° = 12.0 ÷ 0.6521 = 18.4′ W. New position: 49° 15.0′ N, 123° 48.4′ W.

The last step shows why a minute of longitude is not a mile: at 49°, 12 miles west is 18.4 minutes of longitude.

## Worked example 4: distance to a mark

You measure 5.4 nm to a buoy on a bearing of 134° true. Pairs of numbers like this are what you plot for a fix. From your position go 5.4 nm along 134° and you reach the buoy.

## BC practical example

The Strait of Georgia at 49° N: a fishing boat reports 20 nm "west of Point Atkinson" and a crew member suggests measuring the 20 nm on the longitude scale. At that latitude 20 nm is about 31′ of longitude, not 20′. The correct tool is the latitude scale beside the work, or the departure formula above.

## Exam traps

- Reading distance off the longitude scale.
- Writing longitude first, or mixing up N/S, E/W.
- Reading a position in degrees and decimal degrees when the chart is in degrees and minutes. 49° 30′ is 49.5°, not 49.30°.
- Forgetting that a bearing from the ship and from the object differ by 180°.
- Reading a bearing in two figures. Write 007°, not 7°.

## Check yourself

1. How many metres in a nautical mile?
2. A position is 49° 45.5′ N. How many minutes north of 49° 00′?
3. Why can't you use the longitude scale to measure distance?
4. The reciprocal of 306° is?
5. Difference of latitude 18′ N, departure 12.0 nm east. Distance and course?
