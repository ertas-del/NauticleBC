---
id: chartwork.deviation-card
title: "Deviation and the deviation card"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 70
summary: What deviation is and what causes it, how a deviation card or table is made by comparing the compass on different headings, how to read it and interpolate between its entries (including across zero), and what upsets it.
objectives:
  - Define deviation and say what it depends on (the ship and her heading), unlike variation.
  - Read a deviation card and interpolate for a heading between two entries, naming the answer east or west.
  - Say whether a card is entered with compass or magnetic heading and why it matters.
  - List things that change deviation and the checks required by the regulations.
prerequisites: [chartwork.variation, chartwork.compass-types]
estimated_minutes: 16
key_facts:
  - "Deviation is the angle between magnetic north and compass north, caused by the ship's own magnetism and iron; it is named east or west."
  - "Deviation changes with the ship's heading, and also with the compass position, her loading, nearby iron and electrical equipment, and the place on the earth."
  - "A deviation card (table) lists the residual deviation measured on a series of headings; the NSR require the table or curve to be on board near the compass."
  - "Read the card heading column carefully: some cards are entered with compass heading, others with magnetic heading; the card must say which."
  - "To interpolate, take the difference between two card entries and scale it by how far the heading lies between them, remembering that west is negative and east is positive when the sign changes."
  - "Compass error = variation + deviation (algebraic sum), with east positive and west negative."
  - "Deviation = compass error - variation; after a transit gives you the compass error, take away the variation to find the deviation."
  - "Keep magnets, electronics, steel tools and phones away from the compass; moving them can change deviation, so recheck the card after changes."
source_ids: [sixty-bowditch-2019-compass-conversions, chw-a-nsr-2020-xml, sixty-nsr-2020-xml]
review_status: needs_expert_review
---

# Deviation and the deviation card

**Study aid only. Not for navigation.** The card here is invented. Use your own vessel's current card.

## What you need to know

A magnetic compass needle is supposed to follow the earth's field. A ship is a magnet too: her engine, steel hull and fittings, electric cables and equipment all pull on the card. The angle between magnetic north and the direction the compass card actually points is ==deviation==, named ==east or west==.

Unlike variation, deviation belongs to the ship. It ==changes with the ship's heading==: pointing north, the iron may pull the card east, pointing east it may pull the card back west. It also depends on where the compass is mounted, how the vessel is loaded, and what electrical gear is switched on nearby.

Once the compass has been adjusted (compensated) with correctors, small errors remain. These ==residual deviations== are measured on a series of headings and written on a ==deviation card== (a table) or drawn as a ==curve==. The Navigation Safety Regulations, 2020 require the table or curve to be on board, in the vicinity of the compass (s. 106(4)). At inspection the latest card, signed and dated by the master and a deck officer, must be shown (s. 132).

## Compass error again

==Compass error = variation + deviation==, adding east and treating west as negative. Variation comes from the chart. Deviation comes from the card. The conversion sums are in lesson 8.

## The card and its headings

A card has two columns: a heading and a deviation (E or W). The heading may be the ==compass heading== or the ==magnetic heading==. Some textbooks enter the card with magnetic headings; many ships' cards use compass headings, because that is what the helmsman sees. If the deviation is small (say 2° or less on all headings), the difference does not matter much. If it is large, it does, so ==always check the column heading==.

An invented card (entered with compass heading):

| Compass heading | 000 | 045 | 090 | 135 | 180 | 225 | 270 | 315 |
|---|---|---|---|---|---|---|---|---|
| Deviation | 2° E | 4° E | 3° E | 0° | 3° W | 5° W | 4° W | 1° W |

## Interpolation

You will rarely steer exactly a card heading. Find the two headings either side, take the difference in deviation, and scale it by how far you are between them. Treat ==east as plus and west as minus==.

### Worked example 1: heading 060° C

Between 045 (4° E = +4) and 090 (3° E = +3). The interval is 45°, you are 15° past 045, so a third of the way.

1. Change between entries = 3 − 4 = −1.
2. Fraction = 15 ÷ 45 = 0.333.
3. Deviation = +4 + (−1 × 0.333) = +3.67 = ==3.7° E== (about 3½° E or 4° E to the nearest half degree or degree).

### Worked example 2: heading 200° C

Between 180 (3° W = −3) and 225 (5° W = −5).

1. Change = −5 − (−3) = −2.
2. Fraction = 20 ÷ 45 = 0.444.
3. Deviation = −3 + (−2 × 0.444) = −3.89 = ==3.9° W== (about 4° W).

### Worked example 3: crossing zero, heading 340° C

Between 315 (1° W = −1) and 360/000 (2° E = +2).

1. Change = +2 − (−1) = +3.
2. Fraction = 25 ÷ 45 = 0.556.
3. Deviation = −1 + 3 × 0.556 = +0.67 = ==0.7° E== (about ½° E).

The answer has crossed from west to east between the two entries. This is where students slip, because they subtract 1 from 2 and get 1 W or E by guesswork. Use plus and minus numbers.

### Worked example 4: from true course to compass course (card by compass heading)

You want to make good 100° true. Variation 17° E (corrected). The card above.

1. Magnetic course = 100 − 17 = 083° M.
2. You must still turn this into a compass course, but the card is entered with compass heading, and you do not know the compass heading yet. Estimate: compass heading is near 083 − 3.5 = 080. Look up 080: between 045 (+4) and 090 (+3), 35 ÷ 45 = 0.778 of the way, so deviation = 4 − 0.778 = +3.22 (3.2° E).
3. Compass heading = magnetic − deviation east = 083 − 3.2 = ==079.8°, steer 080° C==. Check: 080 + 3.2 + 17 = 100.2 ✓ (the little difference is the rounding).

### Worked example 5: card entered with magnetic heading

If the same numbers were a card entered with magnetic headings, you would look up 083° M directly: between 045 (+4) and 090 (+3), 38 ÷ 45 = 0.844, so deviation = 4 − 0.844 = +3.16 = 3.2° E. Compass heading = 083 − 3.2 = ==079.8° C==. Same answer here because the deviation is small.

## What changes deviation

- ==Moving iron or magnets near the compass==: a steel tool box, a speaker, a phone, a new radio or heater.
- ==Electrical circuits==: current through nearby cables makes a field. Switching on lights or equipment can shift the card.
- ==Changes to the ship==: new steel work, a load of ferrous cargo, structural changes.
- ==Change of latitude==, long periods on one heading, shocks or a lightning strike.

Because of this, the card must be checked (compared with known bearings) regularly and after any change. A card that was right last year may be wrong today.

## Deviation from compass error

If a transit or other observation has given you the compass error, ==deviation = compass error − variation==, using east plus, west minus. That is how a new card is built: on each heading, compare the compass with a known true bearing, then take the variation off. Lesson 9 does the arithmetic.

## BC example

A skipper adds a new chart plotter and a speaker box on top of the binnacle shelf. The next day, on a charted transit, the compass shows 4° more error than the card predicts. Moving the speaker 1 m away and checking again brings the error back to the card value. Lesson: check after every change near the compass, and log it.

## Exam traps

- Using the card without checking whether it is entered by compass or magnetic heading.
- Forgetting the sign when interpolating across zero.
- Adding variation and deviation of different names without turning west into minus.
- Thinking deviation changes with latitude only; it mostly changes with heading.
- Keeping a card from an earlier time without rechecking.

## Check yourself

1. What causes deviation?
2. On the card above, what is the deviation at 270° C?
3. Does deviation depend on heading?
4. A transit shows compass error 6° E, variation 17° E. Deviation?
