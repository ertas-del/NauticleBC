---
id: stability.righting-lever-gz-curve
title: "Righting lever and the GZ curve"
module: stability
tickets: [master-limited-60gt, fishing-master-4]
order: 40
summary: GZ is the lever that turns a heeled vessel back upright. This lesson covers GZ = GM x sin(angle), righting moment, how to read a GZ curve (maximum, range, vanishing angle, downflooding) and how a steady wind heels a vessel to a settled angle.
objectives:
  - Define the righting lever GZ and calculate GZ and the righting moment at small angles of heel.
  - Read a GZ curve for maximum GZ, the angle of maximum GZ, the range of stability and the angle of vanishing stability.
  - Explain dynamic stability as the area under the GZ curve and why freeboard and downflooding shorten the useful curve.
  - Find the angle at which a steady heeling force settles.
prerequisites: [stability.metacentric-height]
estimated_minutes: 24
key_facts:
  - The righting lever GZ is the horizontal distance between the upward buoyancy line through B and the downward weight line through G of a heeled vessel.
  - Righting moment = displacement x GZ; a larger GZ means a stronger push back to upright.
  - At small angles of heel (up to roughly 10 to 15 degrees) GZ = GM x sin(angle of heel).
  - The GZ curve plots GZ against the angle of heel; its starting slope is set by GM, so a tender vessel's curve starts low and a stiff vessel's curve starts steeply.
  - The range of stability is the span of angles from upright to the angle of vanishing stability, where GZ falls back to zero; beyond it GZ is negative and the vessel capsizes.
  - Dynamic stability is the area under the GZ curve; it measures the energy needed to heel the vessel to a given angle.
  - The downflooding angle is the heel at which an opening that cannot be closed weathertight goes under; a vessel should not be counted as stable beyond it.
  - Less freeboard brings the deck edge under sooner and shortens the useful part of the GZ curve.
  - A steady heeling force, such as wind, settles the vessel where the righting lever GZ equals the heeling lever.
source_ids: [stab-mt4241-stability-trim, stab-fao-ftp517-ch6-example, stab-wikipedia-metacentric-height, stab-ecfr-46cfr28-510]
review_status: needs_expert_review
---

# Righting lever and the GZ curve

**Study aid only.** All figures are invented. Real GZ curves come from your vessel's stability documents.

## What you need to know

GM tells you how strongly a vessel pushes back when it is leaned a **small** amount. The GZ curve tells you how it behaves over the **whole** range, up to the angle where it gives up. GM is the first step; the GZ curve is the full picture.

## GZ: the righting lever

Heel a vessel and B shifts toward the low side, while G stays put. Weight pulls down through G; buoyancy pushes up through the new B. These two parallel forces are a small distance apart, and that distance is the lever of a turning force. Drop a line from G to meet the buoyancy line at a right angle: the length of that line is ==GZ, the righting lever==. The turning effect is:

==Righting moment = displacement x GZ==

A bigger GZ, or a heavier vessel, means a stronger push back to upright. M comes into it because, at small angles, the geometry gives:

==GZ = GM x sin(angle of heel)==

This holds only for ==small angles, roughly 10 to 15 degrees==. Beyond that the hull shape changes the answer and you need the real curve.

### Worked example 1: righting moment of the Tern

Example Tern (invented): displacement 40 t, GM 0.45 m. Heeled 10 degrees:

1. GZ = 0.45 x sin 10 = 0.45 x 0.1736 = **0.0781 m**.
2. Righting moment = 40 x 0.0781 = **3.13 t.m** (tonne-metres).

At 5 degrees GZ is 0.039 m and the moment 1.57 t.m; at 15 degrees, 0.117 m and 4.66 t.m. Double the GM and every one of these doubles.

### Worked example 2: from GZ back to the moment

A heeled vessel of 40 t has a measured GZ of 0.12 m. Righting moment = 40 x 0.12 = **4.8 t.m**. Same vessel, GZ only 0.06 m: 2.4 t.m. Halving GZ halves the push.

## The GZ curve

Plot GZ (up) against angle of heel (across) and you get the ==GZ curve==. Here is the Tern's curve as a table (invented), with GZ at each angle:

| Heel (degrees) | 0 | 10 | 20 | 30 | 40 | 50 | 60 | 70 |
|---|---|---|---|---|---|---|---|---|
| GZ (m) | 0 | 0.08 | 0.17 | 0.24 | 0.26 | 0.22 | 0.12 | 0.00 |

Landmarks to read:

- ==The slope at the start is GM.== For small angles GZ is GM times the angle in radians, so a tender vessel's curve rises gently and a stiff vessel's curve rises steeply. (A tangent drawn at the origin reaches a GZ equal to GM at 57.3 degrees, which is one radian.)
- ==Maximum GZ== (here 0.26 m) and the ==angle of maximum GZ== (here 40 degrees). The bigger the maximum and the later it occurs, the better.
- The ==angle of vanishing stability== is where the curve falls back to zero (here 70 degrees). Past it GZ is negative and the vessel capsizes.
- The ==range of stability== is the span from upright to that angle (here 0 to 70 degrees).

For a vessel with a **negative GM** the curve starts below zero, and then rises through zero at the ==angle of loll==. The vessel rests at that angle. Push it past the next zero (the vanishing angle) and it capsizes.

### Dynamic stability: area under the curve

Static stability asks how hard the vessel pushes back at an angle. ==Dynamic stability== asks how much energy it takes to get it there, and that is the ==area under the GZ curve==. A gust or a big wave delivers energy, not a steady push, so the area matters as much as the height. Areas are found with the trapezoidal rule: add up (average of two neighbouring GZ values) x (the step in radians) for each step. One degree is 0.01745 radians.

### Worked example 3: areas for the Tern's curve

Step is 10 degrees = 0.1745 rad.

1. 0 to 10: (0 + 0.08) / 2 x 0.1745 = 0.00698
2. 10 to 20: (0.08 + 0.17) / 2 x 0.1745 = 0.02181
3. 20 to 30: (0.17 + 0.24) / 2 x 0.1745 = 0.03577
4. Area 0 to 30 = **0.0646 m.rad**.
5. 30 to 40: (0.24 + 0.26) / 2 x 0.1745 = **0.0436 m.rad**.
6. Area 0 to 40 = **0.1082 m.rad**.

Keep these numbers; the last lesson of the module compares them with the standard criteria.

## What cuts the curve short

The curve above assumes the vessel keeps its watertight hull and its buoyancy at every angle. In real life two things end it early.

First, the **deck edge**. The curve is helped by the wedge of hull going under on the low side. When the deck edge goes under, that wedge stops growing and the curve flattens, then falls. Less ==freeboard== means the deck edge goes under at a smaller angle. A vessel 3.5 m wide with 0.45 m of freeboard keeps its deck edge clear until about 14 degrees; with 0.30 m, only until about 10 degrees (the freeboard lesson shows the sum).

Second, **downflooding**. The ==downflooding angle== is the heel at which the first opening that cannot be closed weathertight (a vent, an engine-room door left open, an air pipe) goes under water. Water pours in, the vessel gets heavier and lower, and the curve is of no use beyond it. Criteria therefore use the curve only up to the downflooding angle when that angle is smaller than the angle of interest.

## Steady heeling forces

Wind on the cabin and rigging, the pull of a towline, or the outward push in a turn can each be treated as a ==heeling lever==. The vessel settles where ==GZ equals the heeling lever==, because then the two turning effects cancel.

### Worked example 4: wind heel

A steady wind produces a heeling moment of 3.0 t.m on the 40 t Tern. Heeling lever = 3.0 / 40 = **0.075 m**. For a small angle, GZ = GM x sin(angle), so sin(angle) = 0.075 / 0.45 = 0.1667 and the angle is **9.6 degrees**. Now the same wind with a tender vessel, GM 0.225 m: sin(angle) = 0.075 / 0.225 = 0.333, angle **19.5 degrees**. Halving GM doubled the heel. The wind and the vessel are the same; only the stability changed.

Winds are gusty. A gust that hits a vessel already heeled can carry it much further. That is where the area under the curve, not just one GZ value, decides the outcome.

## BC picture

A seiner in Johnstone Strait with a full deck load might heel only a few degrees in steady wind and then roll to 25 degrees in a beam sea with the gale gusting. The first number looks fine. The second shows how little reserve there is. A vessel's GZ curve is a measure of that reserve.

## Exam traps

GZ is a horizontal distance, not a vertical one. GZ = GM x sin(angle) is a **small-angle** formula only. Righting moment needs displacement; the same GZ on a heavier vessel is a bigger moment. The angle of vanishing stability is where the curve crosses zero, not where it peaks. The range of stability is not the same as the angle of maximum GZ. And a high maximum GZ does not help if the downflooding angle comes first.

## Check yourself

1. A vessel of 60 t has GM 0.50 m. What is the righting moment at 8 degrees? (GZ 0.0696 m, moment 4.17 t.m.)
2. What does the starting slope of the GZ curve tell you?
3. A GZ curve crosses zero at 65 degrees. What is that angle called?
4. Why does the area under the curve matter more than one GZ value when a gust hits?
