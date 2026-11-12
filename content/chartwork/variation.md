---
id: chartwork.variation
title: "Variation and the compass rose"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 60
summary: What variation is, how to read it from the compass rose, how to bring it up to date for the current year using the annual change (including when it crosses zero), and how to convert between true and magnetic.
objectives:
  - Define variation and say it changes with place and slowly with time, not with heading.
  - Read the inner (magnetic) and outer (true) rings of a compass rose and the variation note.
  - Correct a charted variation to the current year using the annual change.
  - Convert true to magnetic and magnetic to true with the correct east and west sign.
prerequisites: [chartwork.chart-symbols, bridge-watch.compass-error]
estimated_minutes: 16
key_facts:
  - "Variation is the angle between the geographic (true) meridian and the magnetic meridian at a place, named east or west according to which side of true north magnetic north lies."
  - "Variation depends on where you are and changes slowly with time; it does not depend on the ship's heading."
  - "The charted variation is for the year printed; correct it to the present year with the annual change before using it."
  - "Chart note format: 17 degrees 30 minutes E 2015 (8 minutes W) means variation 17 degrees 30 minutes east in 2015, and the annual change is 8 minutes toward west, so the easterly variation is decreasing."
  - "Corrected variation = charted variation changed by (annual change x years since the printed year), in the direction of the letter in the bracket: an east change adds to easterly variation and subtracts from westerly variation."
  - "Magnetic = true minus easterly variation (or plus westerly); true = magnetic plus easterly variation (or minus westerly)."
  - "Variation easterly makes magnetic north lie east of true north, so magnetic bearings are smaller than true bearings."
  - "Local magnetic anomalies are marked on the chart, and compass readings near them can be unreliable."
source_ids: [sixty-bowditch-2019-compass-conversions, chw-a-chs-chart1-2022-pdf, chs-pac200-sailing-directions]
review_status: needs_expert_review
---

# Variation and the compass rose

**Study aid only. Not for navigation.** All numbers are invented. Use the variation printed on your own chart, brought up to date.

## What you need to know

The earth's magnetic north pole is not at the geographic North Pole, and it wanders. A magnetic compass needle points to magnetic north. The angle between true north and magnetic north, where you are, is ==variation==. Variation is ==named east or west== by which side of true north magnetic north lies.

- ==Variation depends on place== (and drifts slowly with time).
- ==Variation does not depend on the ship's heading.== That is deviation, in the next lesson.

On the BC coast variation is easterly (magnetic north lies east of true north) and is well over 10°. That is a ballpark for orientation only; read the real figure on your own chart.

## Easterly means magnetic is lower

With easterly variation the card is pulled clockwise away from true north, so a smaller number sits at the lubber line. So ==easterly variation means magnetic bearings are less than true; westerly means magnetic bearings are more==. Therefore:

- ==True to magnetic: subtract easterly, add westerly==.
- ==Magnetic to true: add easterly, subtract westerly==.

Example: true 075°, variation 17° E. Magnetic = 075 − 17 = ==058°==. Back again: 058 + 17 = 075 ✓.

## The compass rose

Canadian charts print one or more ==compass roses==. The outer ring is ==true== (north is the top of the chart). The inner ring is ==magnetic==, turned by the variation. Reading a course on the outer ring gives true, on the inner ring gives magnetic directly.

The variation is printed in the centre of the rose or beside it, in the form:

==17° 30′ E 2015 (8′ W)==

Read it as: variation was 17° 30′ east in 2015, and the annual change is 8′ toward the west, so the easterly variation is ==decreasing== by 8′ a year. Look at the letter in the bracket. ==East in the bracket adds eastward, west adds westward.== An easterly variation with a westerly change gets smaller. A westerly variation with an easterly change also gets smaller. If the two letters are the same, the variation grows.

## Correcting variation to the current year

1. Years elapsed = present year − year printed.
2. Total change = annual change × years elapsed (convert minutes to degrees by dividing by 60).
3. Apply the change in the direction of the bracketed letter.
4. Round as the question asks, usually to the nearest 0.5° or 1° for plotting. Round at the end.

### Worked example 1: decreasing easterly

Rose: 18° 30′ E 2015 (8′ W). Variation in 2026?

1. Years = 2026 − 2015 = 11.
2. Change = 11 × 8′ = 88′ = 1° 28′ toward west.
3. Easterly variation shrinks: 18° 30′ − 1° 28′ = 17° 02′ E.
4. Round: about ==17° E==.

### Worked example 2: increasing easterly

Rose: 15° 20′ E 2012 (4′ E). Variation in 2026?

1. Years = 14.
2. Change = 14 × 4′ = 56′ toward east.
3. Easterly grows: 15° 20′ + 56′ = 16° 16′ E, about ==16° E==.

### Worked example 3: westerly, decreasing

Rose: 4° 30′ W 2007 (8′ E). Variation in 2026?

1. Years = 19. Change = 19 × 8′ = 152′ = 2° 32′ toward east.
2. Westerly shrinks: 4° 30′ − 2° 32′ = 1° 58′ W, about ==2° W==.

### Worked example 4: crossing zero

Rose: 1° 30′ W 2000 (10′ E). Variation in 2026?

1. Years = 26. Change = 26 × 10′ = 260′ = 4° 20′ toward east.
2. Start at 1° 30′ W. Moving 1° 30′ east takes it to zero; the remaining 4° 20′ − 1° 30′ = 2° 50′ goes east.
3. Variation is ==2° 50′ E== (it has changed name).

This shows why you should treat west as negative and east as positive: −1° 30′ + 4° 20′ = +2° 50′ = 2° 50′ E.

### Worked example 5: use it

Corrected variation 17° E. A course made good of 125° T. Magnetic course = 125 − 17 = ==108° M==. A bearing of a light of 340° M becomes 340 + 17 = ==357° T==. A bearing of 350° M becomes 350 + 17 = 367 − 360 = ==007° T==, because you wrap around 360°.

## Anomalies

Where there is magnetic rock or other local disturbance the compass can read wrongly for a stretch. The chart marks local magnetic anomalies. Do not trust a compass bearing taken there.

## BC example

You are using a chart whose rose says 17° 40′ E 2014 (6′ W). In 2026, 12 years × 6′ = 72′ = 1° 12′ toward west. Corrected variation = 17° 40′ − 1° 12′ = 16° 28′ E, about 16½° E. If you used 17° 40′ without correcting, every plotted bearing would be about 1° out.

## Exam traps

- Forgetting to correct for the years since the rose was printed.
- Adding when the bracketed letter makes the variation smaller.
- Using "add east" in the wrong direction (true to magnetic needs subtraction of easterly).
- Rounding the annual change before multiplying.
- Treating variation as depending on heading.

## Check yourself

1. A rose says 16° E 2010 (5′ W). What is the variation in 2026?
2. True course 220°, variation 17° E. Magnetic course?
3. Magnetic bearing 005°, variation 17° E. True bearing?
4. Does variation change if the ship swings from north to east?
