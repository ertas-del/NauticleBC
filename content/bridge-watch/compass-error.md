---
id: bridge-watch.compass-error
title: "Compass error: variation, deviation, gyro error"
module: bridge-watch
tickets: [bridge-watch-rating, master-limited-60gt]
order: 170
summary: What variation, deviation and compass error are, the True-Variation-Magnetic-Deviation-Compass order, and how to convert between true and compass headings with simple east and west sums, including gyro error. Full chartwork conversions come later.
objectives:
  - Define variation, deviation, compass error and gyro error, and say what each depends on.
  - Apply the rule for east and west error to convert a compass or gyro heading to true and true to compass.
  - Work the chain True, Variation, Magnetic, Deviation, Compass in both directions with simple numbers.
  - Explain why a deviation card is needed and why deviation is not on the chart.
prerequisites: [bridge-watch.magnetic-and-gyro-compass]
estimated_minutes: 18
key_facts:
  - "Variation is the angle between true north and magnetic north; it depends on where you are."
  - "Deviation is the angle between magnetic north and compass north, caused by the ship's own magnetism; it changes with the ship's heading (and with trim, loading and the compass position)."
  - "Compass error is the angle between true north and compass north, and equals variation plus deviation, adding easterly values and subtracting westerly values."
  - "The order of the chain is True, Variation, Magnetic, Deviation, Compass (T V M D C)."
  - "Easterly error makes the compass read lower than true; westerly error makes it read higher."
  - "Compass to true: add easterly error, subtract westerly error. True to compass: the opposite (subtract easterly, add westerly)."
  - "Gyro error is named east or west and is applied the same way: gyro heading plus easterly error, or minus westerly error, gives true."
  - "Deviation is not printed on charts because it belongs to the individual ship, her trim and loading, her heading and the compass position; it is read from the ship's deviation card or curve."
source_ids: [bw-bowditch-compass-conversions, bw-nsr-2020-sor-2020-216, bw-steer-wiki-gyrocompass]
review_status: needs_expert_review
---

# Compass error: variation, deviation, gyro error

**Study aid only.** This lesson uses invented numbers. For a real passage, use the variation on your current chart and
the deviation card for your own compass. The full chartwork methods (position plotting, transits and tides) belong to
the chartwork lessons, not here.

## What you need to know

A compass reading is rarely the true direction. A helmsman who steers "one-two-five by compass" needs to know what true
course that really is. The gap between them is the **compass error**, and it has two causes:

- ==Variation==: magnetic north is not at true north. The angle between them depends on **where you are**.
- ==Deviation==: the ship's own iron, engines and electrical gear pull the card away from magnetic north. The angle
  depends on the **ship's heading** (and on her trim, loading and where the compass sits).

==Compass error== = variation + deviation.

A gyro-compass has **no variation and no deviation**, but it can have a small ==gyro error==, east or west, which you apply
in the same way.

## Naming east and west

Every one of these angles is named **east (E)** or **west (W)** by which side of north the compass north (or magnetic
north) lies:

- ==Easterly== error: the card (or reference north) lies to the **east** of true north.
- ==Westerly== error: it lies to the **west** of true north.

An east error rotates the card clockwise, so a **smaller** number sits at the lubber line. Therefore:

- ==Easterly error: the compass reads LOW== (less than true).
- ==Westerly error: the compass reads HIGH== (more than true).

## The two rules

| Direction | Easterly error | Westerly error |
|---|---|---|
| **Compass to True** (read the compass, want true) | ==Add== | ==Subtract== |
| **True to Compass** (want the heading to steer) | ==Subtract== | ==Add== |

A memory phrase many students use is **CADET**: **C**ompass **A**dd **E**ast **T**o true (the D is just there to make
the word). Westerly is then the opposite. Use whichever phrase works for you, but check every answer against the table
and the rule above: **easterly error means the compass reads low, so going from compass to true you must add**.

## The chain T V M D C

Remember the order of the five headings: ==True, Variation, Magnetic, Deviation, Compass== (a common phrase is "True
Virgins Make Dull Company"). Going **left to right** is true to compass. Going **right to left** is compass to true.

```
True  --(variation)-->  Magnetic  --(deviation)-->  Compass
```

At each arrow, apply the E/W rule for **true to compass** (subtract easterly, add westerly), or reverse it going back.

## Worked examples

All numbers are invented. Headings are in degrees and shown in three digits.

### 1. Compass to true with one total error

The compass reads **042**. Compass error is **5 degrees east**. Compass to true: add easterly.

True = 042 + 5 = **047**.

### 2. The whole chain, true to compass

True heading **120**. Variation **15 E**. Deviation for this heading **3 W**.

| Step | Working | Result |
|---|---|---|
| True | | 120 |
| Magnetic | 120 - 15 (easterly variation, true to magnetic) | **105** |
| Compass | 105 + 3 (westerly deviation, magnetic to compass) | **108** |
| Compass error | 15 E + 3 W = 15 - 3 | **12 E** |

Check: error 12 E, true to compass means subtract: 120 - 12 = 108. The two methods agree.

### 3. Gyro error

The gyro repeater reads **253**. Gyro error is **2 W**. Gyro to true: subtract westerly.

True = 253 - 2 = **251**.

### 4. Heading to steer by compass

The chart says to steer true **075**. Variation is **18 E**. Deviation card shows **2 W** on this heading.

- Magnetic = 075 - 18 = **057**.
- Compass = 057 + 2 = **059**.

The helmsman steers **059 by compass**. Total error = 18 E + 2 W = **16 E**. Check: 075 - 16 = 059.

### 5. Going past north (360 wraps to 000)

The compass reads **355**, error **10 E**. Compass to true: add easterly: 355 + 10 = 365. Subtract 360: true is **005**.

Going the other way: true **010**, error **14 W**. True to compass: add westerly: 010 + 14 = **024**.

### 6. Opposite signs

Variation **20 E**, deviation **4 E**, true **350**: Magnetic = 350 - 20 = **330**. Compass = 330 - 4 = **326**. Total error 20 E + 4 E = **24 E**. (Here both are east, so they add; and the compass reads 24 lower than true.)

## Why the deviation card?

Deviation is **not printed on charts**. It belongs to the individual ship: her trim, her load, her heading and the
spot where the compass stands (a Bowditch note says this directly). Instead, each magnetic compass has a **table or
curve of residual deviations** kept near it, and during an inspection the master must be able to show the latest
**deviation card** signed by the master and a deck officer (Navigation Safety Regulations, 2020, ss. 106(4) and 132).
Read the deviation for the **heading the card is based on** (the card will say whether that is the compass or the
magnetic heading). Keeping steel tools, phones or magnets near the compass changes deviation, so keep them away.

## A BC example

A tug leaves a BC harbour and the OOW compares the gyro repeater against a visual transit and finds the gyro reads
1 degree higher than the true bearing. That is a **gyro error of 1 W** (the gyro reads high, which is a westerly error).
The OOW logs it and the helmsman applies it when steering by gyro for a chart course: to steer true 090 by gyro, add
westerly error: gyro heading **091**.

## Exam traps

- **Variation depends on place; deviation depends on heading.**
- **Easterly error means the compass reads low**, so compass to true you **add**.
- **Check your direction first**: are you going compass to true, or true to compass? The rule flips.
- **Add or subtract the east and west values from each other** when they have opposite names (15 E and 3 W gives 12 E).
- **Wrap around north**: if you pass 360, subtract 360; if you go below 000, add 360.
- **Gyro error is applied like compass error.**
- **A heading is only meaningful with its type**: true, magnetic, compass or gyro.

## Check yourself

1. Define variation and deviation, and say what each depends on.
2. Compass reads 087, error 6 W. What is the true heading?
3. True heading 270, error 10 E. What compass heading do you steer?
4. True 100, variation 10 W, deviation 3 E. Work out magnetic, compass and total error.
5. Gyro reads 004, gyro error 3 E. What is the true heading?
