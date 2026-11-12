---
id: tides.tide-tables-and-heights
title: "Tide tables and heights"
module: tides
tickets: [bridge-watch-rating, master-limited-60gt, fishing-master-4]
order: 5
summary: How a tide table is laid out, how secondary ports use a reference port, and how to work out the height of tide at a given time, with worked examples.
objectives:
  - Explain what a tide table gives and the difference between a reference port and a secondary port.
  - Find the height of tide at a time between a high and a low water, using the rule of twelfths as an approximation.
  - Turn a height of tide into a depth of water over a charted shoal, and find when there is enough.
  - State the cautions that apply, including the time standard a table uses, predictions versus real weather, and the need to use current CHS publications.
prerequisites: [tides.vocabulary, tides.types-of-tide]
estimated_minutes: 14
key_facts:
  - A tide table gives the times and heights of high and low waters for each day, with heights measured up from chart datum.
  - Reference ports get full daily predictions; secondary ports apply time and height corrections (or a height factor) to a nearby reference port.
  - University notes state the CHS tables for BC use Pacific Standard Time, so add one hour during daylight saving time; confirm in your edition.
  - "Rule of twelfths: in each sixth of a rise or fall the water covers 1, 2, 3, 3, 2 and 1 twelfths of the range."
  - By the rule of twelfths the water has covered half the range (6/12) after three of the six periods.
  - The rule of twelfths is a rough approximation that assumes a smooth rise or fall of about six hours; it is not for close decisions.
  - Depth of water = charted depth + height of tide; water under the keel = depth of water minus draught.
  - Tide tables are astronomical predictions; wind, air pressure and river flow can make the real height differ.
source_ids: [chs-tide-current-tables-vol5, ubc-atsc113-tide-current-tables, noaa-tide-predictions-help, wikipedia-tide-table, wikipedia-rule-of-twelfths, chs-vertical-datum-chart-references, noaa-tides-currents-glossary, noaa-tides-currents-faq, noaa-tides-tutorial-othereffects]
review_status: needs_expert_review
---

# Tide tables and heights

**Study aid only. Not for navigation.** Every number in this lesson is **invented** for teaching. The ports are
made up. This app does not give real tide data. For any real passage, use the **current** CHS tide and current tables
(or other current CHS publications) for your area, and follow the instructions in them.

## What a tide table gives you

A tide table lists, for each day, the ==times and heights of the high waters and low waters== at a place. Heights are
measured ==up from chart datum== (lesson 3). In Canada, CHS publishes its tables in regional volumes; for example,
Volume 5 covers Juan de Fuca Strait and the Strait of Georgia. Always check you have the edition for the **right year**
and the right region.

Typical entries for one day at an invented port, "Example Cove":

| Event | Time | Height above chart datum |
|---|---|---|
| Low water | 0600 | 0.6 m |
| High water | 1200 | 4.2 m |
| Low water | 1810 | 0.7 m |
| High water | 0025 (next day) | 3.8 m |

Notice the two lows and two highs differ: it is a mixed tide (lesson 4).

## Time standard: a classic one-hour trap

Check which clock the table uses. University notes on the CHS tables for BC state that the tables use ==Pacific Standard Time==,
so you ==add one hour during daylight saving time==. Confirm this in the edition you have. Mixing standard
and daylight time is one of the easiest ways to be out by an hour, and an hour can be most of a tide.

## Reference ports and secondary ports

Predicting tides needs long records of observation. That is practical only at some places, which are called
==reference ports== (some books say standard ports). They get full predictions for every day.

For other places, called ==secondary ports==, the tables give **corrections** that you apply to a nearby reference
port's predictions. These are typically a ==time difference and a height difference== (or a height factor). The general
idea is the same in the US system, where predictions for secondary stations come from a reference station plus time
and height adjustments.

**Invented example.** Reference port "Example Cove" has high water at 1640 with a height of 3.9 m. The table for the
secondary port "Example Bay" (invented) says: time difference **minus 0 h 35 min**, height difference **plus 0.3 m**.

- Time: 1640 minus 35 minutes = **1605**.
- Height: 3.9 m plus 0.3 m = **4.2 m**.

If your table gives a height **factor** instead (say a factor of 0.85), you multiply: a reference height of 4.0 m
becomes 4.0 x 0.85 = **3.4 m**. Do exactly what your publication's instructions say. Check the sign of every
difference.

## Finding the height at a time between high and low water

You often need the height at a time that is not listed, for example when you will cross a shoal. The water does not rise
at a steady speed. It starts slowly, speeds up in the middle of the rise, and slows again near high water. So
a straight line between the two heights is not accurate.

### Method 1: the rule of twelfths (quick approximation)

Take a rise or fall that lasts about six hours and split it into six equal periods (about one hour each). In those
periods the water covers this share of the **total range**:

| Period | 1st | 2nd | 3rd | 4th | 5th | 6th |
|---|---|---|---|---|---|---|
| Share of range | ==1/12== | ==2/12== | ==3/12== | ==3/12== | ==2/12== | ==1/12== |
| **Running total** | 1/12 | 3/12 | ==6/12== | 9/12 | 11/12 | 12/12 |

The same pattern applies on the fall, counted from high water.

If the rise or fall is not six hours, make each period one sixth of the actual time between the two waters.

### Worked example 1 (whole hours)

Using the Example Cove morning: LW 0600 at 0.6 m, HW 1200 at 4.2 m. Find the height at **0900**.

1. Range = 4.2 - 0.6 = **3.6 m**. One twelfth = 3.6 / 12 = **0.3 m**.
2. 0900 is three hours after low water. The running total is 6/12, so the water has risen 6 x 0.3 = **1.8 m**.
3. Height at 0900 = 0.6 + 1.8 = **2.4 m**.

The rest of the rise, hour by hour: 0700 = 0.9 m, 0800 = 1.5 m, 0900 = 2.4 m, 1000 = 3.3 m, 1100 = 3.9 m, 1200 = 4.2 m.

### Worked example 2 (between whole hours)

Same tide, find the height at **0830** (two and a half hours after low water).

1. After 2 hours the running total is 3/12. After 3 hours it is 6/12. Half way through the third hour, we take half of
   the third period's 3/12: 3/12 + 1.5/12 = 4.5/12.
2. Rise = 4.5 x 0.3 = **1.35 m**.
3. Height = 0.6 + 1.35 = **1.95 m**, call it about **2.0 m**.

### Worked example 3 (depth over a shoal, and when you can cross)

A charted sounding on a shoal is **1.2 m**. Your draft is **1.6 m**, and you want at least **1.0 m** of water under
the keel.

1. Depth you need = 1.6 + 1.0 = 2.6 m.
2. Height of tide needed = needed depth - charted depth = 2.6 - 1.2 = **1.4 m**.
3. From the hourly heights above: 0700 gives 0.9 m (not enough), 0800 gives **1.5 m** (enough).
4. So the first whole hour with enough water is about **0800**. In practice, add a margin, because the real height at
   0800 may differ, and the tide has not "arrived" until you have checked it.

### How good is the rule of twelfths?

It assumes a ==smooth, regular rise of about six hours==. On the invented tide above, a smooth-curve calculation gives
about 0.84 m at 0700, where the rule gives 0.90 m. For this 3.6 m range and a perfectly regular tide, the two never
differ by more than about 0.1 m; the gap grows in proportion to the range, to roughly 2.5% of it at worst. Real tides
are less tidy: the rise and fall may last unequal times, mixed tides can distort the curve, and wind and air pressure
shift the actual height. So the rule of twelfths is a ==rough estimate only==. Where accuracy matters, use the method
in the CHS publication (usually a tide curve or a calculation form), and add a safety margin. Never treat it as the
official height.

## Predictions are not the real water level

Tide tables are based on the sun and moon only. ==Strong winds, big changes in air pressure, and heavy river flow== can
make the real height differ from the table, sometimes by a lot. Look at the water as well as the table.

## Common mistakes

- Using last year's table, the wrong region's volume, or the wrong port.
- Forgetting to apply the daylight saving hour, or applying it twice.
- Adding the secondary-port correction with the wrong sign.
- Drawing a straight line between high and low water for the height in between.
- Forgetting charted depth. Height of tide is **not** depth. ==Depth = charted depth + height of tide==.
- Treating the rule of twelfths as exact.

## Check yourself

1. High water 4.2 m, low water 0.6 m. What is the range, and how much does the water rise in the first hour by the rule of
   twelfths?
2. A sounding is 2.0 m. The height of tide is 2.4 m. How deep is the water?
3. Why should you not rely on the rule of twelfths for a close decision?
