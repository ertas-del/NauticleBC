---
id: chartwork.compass-error-by-transit
title: "Finding compass error by a transit"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 90
summary: How two charted objects in line give a true bearing you can compare with the compass, how to turn the difference into compass error and deviation with the right signs, how to check the answer is believable, and how to record it.
objectives:
  - Explain what a transit is and why it gives the compass error without knowing where you are along the line.
  - Find the compass error and then the deviation from a true and a compass bearing of a transit, with the correct east and west names.
  - Judge whether a result is believable and name the usual causes of an impossible one.
  - Say what to record, and how a series of observations builds a deviation card.
prerequisites: [chartwork.compass-error-tvmdc]
estimated_minutes: 15
key_facts:
  - "A transit (range) is two charted objects seen in line; the ship is somewhere on the extension of the line joining them."
  - "The true bearing of the transit is taken from the chart; it is the same wherever you are on the line, so you need no fix to use it."
  - "Compass error = true bearing minus compass bearing; the error is east if true is the larger and west if the compass is the larger."
  - "Deviation = compass error minus variation (east positive, west negative), using the variation corrected to the present year."
  - "The deviation found applies to the ship's compass heading at the moment of the observation, so note the heading."
  - "Use the direction from the ship toward the objects (from the front object to the rear one, seen from seaward); the reciprocal is 180 degrees wrong."
  - "A deviation far bigger than the card predicts means a mistake (wrong variation, reciprocal, wrong marks) or a disturbance near the compass."
  - "The same method gives gyro error: gyro error = true bearing minus gyro bearing."
  - "Record every compass error observation in the log with date, time, heading and bearing."
source_ids: [chw-a-bowditch-2002-ch8-piloting, sixty-bowditch-2019-compass-conversions, sixty-tp2293-2019-draft, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Finding compass error by a transit

**Study aid only. Not for navigation.** The marks, bearings and errors here are invented.

## What you need to know

You cannot trust a compass until you have checked it against something you know. The simplest check at sea is a ==transit==: two charted objects (a beacon and a tower, two lights, a range's front and rear marks) that you see ==in line== from the wheelhouse. Anywhere on that line, they bear the same direction, and the ==true bearing== of the line can be read from the chart. Compare that with what the compass says, and the difference is the compass error.

The method needs no position fix. It does need ==well-charted fixed objects== (lighthouses, beacons, towers, not floating buoys) and an exact alignment.

## The method

1. Pick a transit on the chart whose objects you can identify and that lies roughly where you can see it from the ship.
2. From the chart, read the ==true bearing== of the line from the front object to the rear one. Use the true rose or a calculation from the charted positions. This is the direction from you toward them, as long as you are on the seaward side.
3. When the two objects come exactly in line, read the ==compass bearing== (over the steering compass, a pelorus or a bearing compass set on the ship's compass). At the same instant note the ==ship's compass heading==.
4. Work out:
   - ==Compass error = true bearing − compass bearing==. If the true bearing is larger, the compass reads low: error ==east==. If the compass is larger, the error is ==west==.
   - ==Deviation = compass error − variation==, east plus and west minus, with the ==variation corrected to this year== (lesson 6).
5. Log it, with the date, time, heading, bearing and the answer.

The deviation found belongs to the ship's ==heading at that moment==, because a ship's deviation changes as she swings (lesson 7).

## Worked examples (invented numbers)

### Example 1: error and deviation

A beacon and a tower are in line. The chart gives their true bearing as 047°. The ship's head is 062° C. The compass bearing of the transit is 033°. The corrected variation is 17° E.

1. Compass error = 047 − 033 = ==14° E== (true is larger).
2. Deviation = 14 E − 17 E = 14 − 17 = −3, which is ==3° W== on a heading of 062° C.
3. Check by working forward: 033 + 14 = 047 ✓.

### Example 2: an impossible answer

Another transit has a charted true bearing of 312°. The compass reads 317°. The variation is 17° E.

1. Compass error = 312 − 317 = −5, that is ==5° W==.
2. Deviation = −5 − (+17) = −22, that is ==22° W==.

A deviation of 22° would be huge on a compass that was compensated and has a card showing a few degrees. Stop and look for the mistake. Typical culprits: the variation is the wrong sign or uncorrected; you have used a different transit from the one plotted; you read the reciprocal of the bearing; a speaker, tool box or phone is near the compass. If nothing is found, treat the compass as unreliable and steer by other means until it is investigated.

### Example 3: building a card

The same transit (047° T) is observed again each time the ship's head is on a different heading, and the compass bearings are noted. Variation 17° E.

| Compass heading | Compass bearing | Compass error | Deviation |
|---|---|---|---|
| 000° | 027° | 047 − 027 = 20° E | 20 − 17 = ==3° E== |
| 090° | 032° | 047 − 032 = 15° E | 15 − 17 = ==2° W== |
| 180° | 031° | 047 − 031 = 16° E | 16 − 17 = ==1° W== |
| 270° | 026° | 047 − 026 = 21° E | 21 − 17 = ==4° E== |

Deviation is entered against the compass heading, which gives the entries of a card. A full card has more headings (every 15° or 45°, as the compass adjuster sets out), and it is repeated after any change near the compass.

### Example 4: the correction to this year

The rose reads 18° 30′ E 2015 (8′ W). In 2026: 11 years × 8′ = 88′ = 1° 28′, so the variation is 17° 02′ E, which rounds to 17° E. Using the uncorrected 18° 30′ would put every deviation about 1½° out.

### Example 5: gyro error

The same transit (047° T) bears 048.5° by the gyro. Gyro error = 047.0 − 048.5 = ==1.5° W==. The gyro reads high, so to get true from this gyro you subtract 1.5°. The method is the same as for the magnetic compass but there is no variation to take off, because a gyro has none.

## Using a range in practice

Some transits are charted leading lines or range lights, which are designed to keep you on a safe track. They serve as compass-error checks as well, provided you note that the leading line bearing is the direction toward the objects, not the direction you are travelling. A hand-bearing compass, if used, is held clear of iron and needs variation only (lesson 5).

## BC example

Leaving a small harbour, a skipper lines up the front and rear marks of a charted range as she passes the entrance. The chart gives 133° true; the compass bearing is 119°; the corrected variation is 16° E. Compass error is 133 − 119 = 14° E, and deviation is 14 − 16 = 2° W. The card says 1° W on that heading, so everything is within a degree and she logs the check.

## Exam traps

- Reading the bearing from the ship's heading instead of the line of sight: the transit gives a bearing of the objects, not a course.
- Taking the reciprocal (a 180° mistake) by measuring from the rear object back to the front.
- Using true minus compass for the error but then adding the variation to get deviation. Deviation is error minus variation.
- Forgetting that deviation depends on the ship's heading and applying the answer to every heading.
- Forgetting to correct the rose to the present year.

## Check yourself

1. Define a transit. Why does it give compass error without a fix?
2. True bearing 220°, compass bearing 213°. Compass error?
3. Compass error 15° E, variation 17° E. Deviation?
4. A computed deviation of 20° W. Name two possible causes.
5. What should be written in the log after a compass check?
