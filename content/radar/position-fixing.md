---
id: radar.position-fixing
title: "Fixing by radar"
module: radar
tickets: [master-limited-60gt]
order: 3
summary: "How to fix a position from radar ranges and bearings, why ranges beat bearings, how to choose objects and angles, what goes wrong with identification, and what parallel indexing is for."
objectives:
  - Fix a position from two or three radar ranges and say why this is preferred over bearings.
  - Choose objects and angles of cut that give a good radar fix.
  - Explain how a bearing error turns into a position error.
  - Describe the weak points of a single range-and-bearing fix and a tangent bearing.
  - Explain the idea of parallel indexing.
prerequisites: [radar.displays-and-errors, chartwork.fixes-ranges-and-mixed]
estimated_minutes: 14
key_facts:
  - A fix from radar ranges to two or more objects is more accurate than a fix from radar bearings.
  - A radar range arc is plotted by setting dividers to the range and swinging an arc from the charted object; the ship lies on the arc.
  - The best angle of cut between two position lines is close to 90 degrees; a very small angle gives a stretched, unreliable fix.
  - Two range arcs cross at two points, and the right one is chosen with the DR position, a bearing or the depth.
  - Range and bearing to a single object gives a quick fix, but it depends on picking the right object and on a bearing that is only good to a few degrees.
  - A tangent bearing to a steep-sided feature is among the least accurate radar fixes, because the beam width must be allowed for.
  - A bearing error of 5 degrees at a range of 3 nm moves the position by about 0.26 nm (about 490 m).
  - Radar can show a different part of a coast than the charted shoreline, so every object used must be positively identified on the chart.
  - Parallel indexing uses a line on the radar screen parallel to the planned track to show whether the ship is staying on it.
source_ids: [renav-bowditch-2002-ch13-radar, renav-nga-pub310-ch4, renav-raymarine-radar-settings, renav-crc1416-xml]
review_status: needs_expert_review
---

# Fixing by radar

**Study aid only.** A radar fix is one source of position. It is not a substitute for the chart, the compass, the depth
or a look outside, and this lesson is not a navigation guide.

## What you need to know

Radar is the one position-fixing tool that works in fog, at night and when the shore is a grey blur. The previous
lessons gave you the key idea: ==ranges are more accurate than bearings==. So a good radar fix is mostly made from
==ranges==, and bearings are the backup. The electronic-navigation lessons extend the same thought to every electronic aid:
never trust one source alone.

## The range fix

Pick two or three charted objects that show clearly on the screen, ideally points of land, small steep islands or
isolated rocks. Read the range to each with the VRM. Then, on the chart:

1. Set dividers to the first range, using the latitude scale on the chart's side edge. One minute of latitude is one
   nautical mile.
2. Place the point of the dividers on the object and swing an arc through the sea area where you think you are.
3. Repeat for the second object.
4. Your position is where the arcs ==cross==. A third arc should pass through the same point. If it forms a small
   triangle, take the worst-case corner or the centre as your position, and be cautious.

Two circles cross at two points. Use the DR position, the depth, or a quick bearing to pick the right one. Never
choose the one that happens to look safer.

The ==angle of cut== matters. If the two objects are almost in line, the arcs run almost parallel and cross at a shallow
angle, and a small error in range moves the crossing a long way. Choose objects so that the bearings differ by about
==90 degrees==, or use three objects roughly 120 degrees apart.

## Timing: you are moving

You cannot read two ranges at the same instant. In the time between them you travel, and a range to an object
ahead or astern changes at nearly your full speed while a range to an object abeam hardly changes at all.

Worked example (invented numbers). Own speed 10 knots. You read the range to a headland dead ahead, then two minutes
later the range to an island on the beam.

1. In 2 minutes you travel 10 × 2 ÷ 60 = **0.33 nm**.
2. The headland range is stale by about 0.33 nm by the time of the second reading. The island range is barely stale.
3. Cure: read the quickly changing range (ahead or astern) as close as possible to the plotting time, and plot the fix for
   that time. Read quickly, and allow for the run between readings if the gap is long.

Different course books give different orders for the readings, so follow the method your instructor teaches. What matters is
that you understand why: the fix time is a single moment, and the faster the range changes, the more the delay costs.

## Why bearings come second

Radar bearings depend on beam width, and a bearing is typically accurate to only a few degrees. A bearing error
becomes a position error that grows with range.

Worked example. A bearing is 5 degrees out on an object 3.0 nm away.

1. Sideways error ≈ range × tan(5°) = 3.0 × 0.0875 = **0.26 nm**.
2. In metres that is 0.26 × 1852 ≈ **486 m**, nearly half a kilometre.

A range error of a few tens of metres on the same object would be much smaller. That is why bearings are secondary.

## Range and bearing from one object

One echo gives both a range and a bearing, so one object is enough for a quick fix. It is fast, but it depends on
having the right object and on a bearing that is only good to a few degrees. Use it when only one mark is available, and
confirm it with depth or the DR as soon as you can.

## Tangent bearings

When you cannot see a point of land clearly, you can take a tangent bearing to the edge of a steep-sided island.
Because the beam has width, the echo's edge is pushed out by about half the beam width. The right-hand tangent must be
corrected by subtracting it and the left-hand tangent by adding it. This is among the least accurate radar fixes, and
you should confirm it with a range.

## Identify before you trust

The radar can show a headland that does not exist on the chart and miss a low spit that does. Low, flat land and
smooth beaches may not show until you are very close, while a wooded hill behind the shore can paint as the "coast".
A small boat close to shore can merge with the shoreline echo. So:

- identify every object by its position, shape and range pattern on both chart and screen;
- prefer steep, isolated and prominent features;
- check the result against another source, such as depth or a visual bearing.

## Parallel indexing

Parallel indexing is a way to watch cross-track error using the screen itself. You draw, or electronically place, a line
on the radar picture that is ==parallel to the planned track==, set to the distance by which a chosen object should pass
you. If you are on track, the echo of that object slides along the line as you proceed. If it moves off the line, you
have been set to one side of your track by an amount you can read off the screen. It works best on a ==stabilised==
display, because a head-up picture swings with every yaw, and it needs a good, clear target.

## BC example (invented)

A tug is heading up the middle of Haro Strait at night with poor visibility. At 0200, the mate reads 2.8 nm to a
prominent headland and 3.1 nm to the southern tip of an island, roughly 85 degrees apart. The two arcs cross on the
water side of the chart, close to the DR position. At 0206 a third range to a steep rock confirms it. The mate logs the
fix as a radar fix and keeps an eye on the depth. A single range or bearing would not have been enough.

## Common mistakes

- Using bearings when ranges are available.
- Picking two objects nearly in line, so the arcs cross at a shallow angle.
- Taking ranges minutes apart on a fast vessel and plotting them as one fix.
- Forgetting that an echo may not come from the charted shoreline.
- Accepting a fix because it is "close enough" to the DR, without a second source.

## Check yourself

1. Why are radar ranges preferred over radar bearings for a fix?
2. A radar bearing is 4 degrees out on an object 5.0 nm away. About how big is the position error?
3. What angle of cut gives the best fix?
4. Two range arcs cross at two places. How do you choose the right one?
5. What is parallel indexing for?

---

*Study aid. Paraphrased from US Government radar texts (Bowditch, NGA Pub. 310) read through summaries. Not an
official Transport Canada or Canadian Hydrographic Service text.*
