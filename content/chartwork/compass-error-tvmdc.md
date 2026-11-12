---
id: chartwork.compass-error-tvmdc
title: "Converting True, Magnetic and Compass"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 80
summary: The True-Variation-Magnetic-Deviation-Compass chain, compass error as the algebraic sum of variation and deviation, the add-east rule for compass to true, true to compass in either direction, bearings versus courses, and ten practice conversions.
objectives:
  - Write the chain T V M D C and say which error links which pair of directions.
  - Convert a compass course or bearing to true and a true course to a compass course, in steps and with the single compass error.
  - Apply the right deviation for the ship's heading when converting a bearing, and wrap results through 000 and 360.
  - Convert a gyro heading or bearing to true, and a true bearing to a relative bearing.
prerequisites: [chartwork.variation, chartwork.deviation-card]
estimated_minutes: 20
key_facts:
  - "The order is True, Variation, Magnetic, Deviation, Compass (T V M D C): variation links true and magnetic, deviation links magnetic and compass."
  - "Compass error is the algebraic sum of variation and deviation, with east positive and west negative; it links true and compass directly."
  - "Compass to true: add easterly error, subtract westerly (correcting). True to compass: subtract easterly, add westerly (uncorrecting)."
  - "An easterly error makes the compass read lower than true; a westerly error makes it read higher."
  - "Gyro error is named east or west and applied in the same way: gyro to true adds easterly error and subtracts westerly."
  - "Deviation belongs to the ship's heading at the moment, not to the direction of the object, so a bearing is corrected with the compass error for the ship's own heading."
  - "True to compass needs the deviation for the compass heading, which you do not yet know; estimate it, then check it against the card."
  - "Directions are written in three figures; add 360 if a result is negative and subtract 360 if it exceeds 360."
  - "Relative bearing = true bearing minus true heading (add 360 if negative)."
source_ids: [sixty-bowditch-2019-compass-conversions, bw-bowditch-compass-conversions, chw-a-sailingissues-navcourse4]
review_status: needs_expert_review
---

# Converting True, Magnetic and Compass

**Study aid only. Not for navigation.** All numbers are invented. Your own chart, corrected variation and your vessel's deviation card supply the real ones.

## What you need to know

Charts are drawn in true. Compasses read in compass. Between the two sits a chain of three steps, and you will walk up and down it in nearly every chartwork question. The chain, in order, is ==True, Variation, Magnetic, Deviation, Compass==, usually shortened to ==TVMDC==.

- ==Variation== (from the chart rose, corrected to the year) joins True and Magnetic.
- ==Deviation== (from the card, for the heading) joins Magnetic and Compass.
- ==Compass error== is the two together: ==compass error = variation + deviation==, adding easterly values and treating westerly ones as negative. It joins True and Compass in one step.

## The one rule that does the work

Think about which way the card sits. An ==easterly error== swings the compass card clockwise, so the number at the lubber line is ==smaller than true==. A ==westerly error== makes the compass read ==larger than true==. From that:

| Direction of travel | Easterly error | Westerly error |
|---|---|---|
| ==Compass to true== (correcting) | ==add== | ==subtract== |
| ==True to compass== (uncorrecting) | ==subtract== | ==add== |

If you prefer a mnemonic, "Can Dead Men Vote Twice At Elections" runs Compass, Deviation, Magnetic, Variation, True, then "Add East" going that way. Use whichever you will not forget under pressure, but check the answer against the logic above: easterly means the compass reads low.

The same rule serves the **gyro**: gyro error is named east or west, and ==gyro to true adds easterly and subtracts westerly==.

## Worked examples (invented numbers)

### Example 1: true course to compass course, in steps

Course to make good 128° T. Variation 16° E. Deviation on this heading 3° W.

1. True to magnetic: 128 − 16 = ==112° M==.
2. Magnetic to compass: deviation is west, so add: 112 + 3 = ==115° C==.
3. Check with the single error: compass error = 16 E + 3 W = 13° E. Uncorrecting, subtract: 128 − 13 = 115° ✓.

### Example 2: compass course to true

Steering 242° C. Variation 15° E, deviation 4° E.

1. Compass to magnetic: add easterly deviation, 242 + 4 = ==246° M==.
2. Magnetic to true: add easterly variation, 246 + 15 = ==261° T==.
3. Compass error = 15 E + 4 E = 19° E, and 242 + 19 = 261 ✓.

### Example 3: a bearing uses the ship's heading

The ship is heading 075° C. The card gives 2° W deviation for that heading. Variation is 17° E. A lighthouse bears 150° C.

1. Compass error = 17 E + 2 W = ==15° E==.
2. True bearing = 150 + 15 = ==165° T==.

The deviation used is for the ==ship's heading== (075° C), not for the bearing (150° C). The ship's magnetism acts on the compass according to where her head points, whatever she is looking at. A hand-bearing compass held clear of iron needs ==variation only== (lesson 5).

### Example 4: passing through north

True course 004°, variation 17° E: 004 − 17 = −13, add 360 gives ==347° M==. And a bearing of 355° C with a compass error of 14° E: 355 + 14 = 369, subtract 360 gives ==009° T==. Never write a figure below 000 or above 360.

### Example 5: gyro and relative bearing

Gyro heading 214.0° with an error of 1.5° E: true heading = 214.0 + 1.5 = ==215.5° T==. A beacon has a true bearing of 298°, so its ==relative bearing== from a ship heading 215° T is 298 − 215 = ==083°==, that is, just forward of the starboard beam.

## The estimating loop (true to compass)

Going from true to compass, you need the deviation for the compass heading, which you only know after you have the compass heading. In practice:

1. Convert true to magnetic with the variation.
2. Look up the deviation near the magnetic heading and apply it to get a first compass heading.
3. Look the deviation up again at that compass heading (if the card is entered with compass headings). If it has changed by half a degree or less, you are done; if not, repeat once.

With a small deviation this barely matters. With a large one it can move your answer a degree or two, which is why the card says which heading it uses.

## Ten drills

Convert, then check against the answers below.

| # | Given | Find |
|---|---|---|
| 1 | T 084°, V 17° E, D 3° E | M and C |
| 2 | T 215°, V 15° E, D 2° W | M and C |
| 3 | T 350°, V 16° E, D 4° W | M and C |
| 4 | T 010°, V 18° E, D 1° E | M and C |
| 5 | T 147°, V 12° W, D 5° E | M and C |
| 6 | C 063°, V 17° E, D 3° W | M and T |
| 7 | C 188°, V 14° E, D 2° E | M and T |
| 8 | C 342°, V 16° E, D 4° E | M and T |
| 9 | C 270°, V 10° W, D 2° W | M and T |
| 10 | C 005°, V 15° E, D 6° W | M and T |

Answers: (1) M 067°, C 064°. (2) M 200°, C 202°. (3) M 334°, C 338°. (4) M 352°, C 351°. (5) M 159°, C 154°. (6) M 060°, T 077°. (7) M 190°, T 204°. (8) M 346°, T 002°. (9) M 268°, T 258°. (10) M 359°, T 014°.

## BC example

The skipper of a 10 m boat wants to lay a course of 305° T across open water to a buoy. The rose gives variation about 16° E after correction, and the card gives 2° E on a compass heading near 290°. Compass error is 16 + 2 = 18° E, so she steers 305 − 18 = 287° C. Back at the wheel the compass reads 287°, the plotter shows 305° over the ground, and the two agree.

## Exam traps

- Adding easterly error when going from true to compass. Easterly always means the compass reads low.
- Applying the deviation for the bearing direction instead of the ship's heading.
- Using variation alone on a ship's compass bearing, or the ship's deviation on a hand compass.
- Treating "10° W" and "10° E" as the same size when summing variation and deviation: 12 E and 4 W is 8 E, not 16.
- Forgetting to wrap: 371° is 011°, and −13° is 347°.
- Using the rose's printed variation without correcting it to this year.

## Check yourself

1. List the chain in order and say what links which pair.
2. Variation 14° W, deviation 3° E, compass bearing 090°. True bearing?
3. True course 200°, compass error 12° W. Compass course?
4. Gyro heading 092°, gyro error 2° W. True heading?
5. Which deviation do you use for a bearing taken with the ship's compass: the one for the bearing or for the heading?
