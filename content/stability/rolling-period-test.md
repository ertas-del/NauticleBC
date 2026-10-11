---
id: stability.rolling-period-test
title: "The rolling period check"
module: stability
tickets: [master-limited-60gt, fishing-master-4]
order: 100
summary: You can judge stability by timing the roll. A tender vessel rolls slowly, a stiff one quickly. This lesson covers how to time rolls correctly, the rule of thumb that compares the period with the beam, a rough GM estimate, and the mistakes that give false comfort.
objectives:
  - Describe how to carry out a rolling period test and what counts as one roll.
  - Calculate the rolling period from a timed count of rolls.
  - Apply the beam rule of thumb and a rough GM estimate, and say what they cannot tell you.
  - List the mistakes that give a false result.
prerequisites: [stability.free-surface]
estimated_minutes: 18
key_facts:
  - "The rolling period is the time for one complete roll: from one side out and back to the same side."
  - Time several complete rolls (for example 10) and divide the total time by the number of rolls; a count of half-rolls gives a period that is too short and false comfort.
  - A small GM gives a long rolling period (tender); a large GM gives a short period (stiff); for the same vessel, the longer the period, the lower the stability.
  - "Rule of thumb for small vessels: a rolling period in seconds that is less than the beam in metres suggests enough stability; this is only a screening check."
  - The period is roughly T = C x beam / square root of GM, so GM is roughly (C x beam / T) squared, where C is a constant for the vessel type (about 0.8 in this lesson's examples; use the vessel's own figure).
  - Do the test in calm water, with the vessel free of lines and gear that restrain her, and in the loading condition you want to check.
  - A rolling period that gets longer over a trip warns that GM is shrinking, for instance from burned fuel, ice, slack tanks or top weight.
  - A passed rolling-period check is not an assessment of stability and does not replace the vessel's stability documents.
source_ids: [sixty-fao-ftp517-stability, stab-wikipedia-metacentric-height, stab-mt4241-stability-trim]
review_status: needs_expert_review
---

# The rolling period check

**Study aid only. Invented numbers.** The rule of thumb here is a screening check quoted for small vessels. It does not replace the stability documents.

## What you need to know

The way a vessel rolls tells you about her stability, and the test costs nothing. A pendulum is slow when it is long and quick when it is short; a roll is the same. A vessel with a **small GM** returns to upright with little force, so she hangs at the end of each swing and takes a **long time** to come back. A vessel with a **large GM** is hauled back quickly and rolls with a **short period**. So, for a given vessel in a given loading condition, ==the longer the period, the less stable she is==.

## Measuring the period

The rolling period is the time for one ==complete roll==: from the vessel leaning to one side, across to the other side, and back to the first side. Half a swing is not a roll.

1. Get her in ==calm water, free of lines== and anything else that restrains her, and in the loading condition you are checking (full, half-empty, deck load on).
2. Start her rolling by pushing, or by the crew moving across the deck in time with the roll and then stopping.
3. Start the stopwatch as the vessel reaches the **extreme** of a swing. Count complete rolls, say ==10 of them==, and stop at the same extreme.
4. ==Period = total time / number of complete rolls.== Repeat two or three times and average.

### Worked example 1: the period

Example Heron (invented) has a beam of 6.0 m. Ten complete rolls take 52 seconds. Period T = 52 / 10 = **5.2 seconds**.

## A rule of thumb and a rough GM

One rule of thumb for small vessels says that ==the rolling period in seconds should be less than the beam in metres==. A period below the beam suggests enough stability; a longer one is a reason to look harder. A related rule quoted for a light vessel allows up to about 1.2 times the beam. These are screening rules: they tell you when to ask questions, not that all is well.

### Worked example 2: the Heron against the rule

T = 5.2 s and beam = 6.0 m. 5.2 is less than 6.0, so the check passes.

### Rough GM from the period

Physics gives the period as roughly inversely proportional to the square root of GM:

==T = C x beam / square root of GM==, so ==GM = (C x beam / T) squared==

C depends on the vessel's shape and how weight is spread; about 0.8 is used here for teaching. For the Heron: GM = (0.8 x 6.0 / 5.2) squared = (0.923) squared = **0.85 m**. Treat this as a ballpark. Only the vessel's own data gives a proper C.

### Worked example 3: the same boat, slack and loaded

Loading changes the period. After fuel is burned and a tank left slack, the Heron takes 75 seconds for 10 rolls.

1. T = 75 / 10 = **7.5 seconds**. This is longer than the beam of 6.0 m. The rule of thumb is not met.
2. Rough GM = (0.8 x 6.0 / 7.5) squared = (0.64) squared = **0.41 m**.

The roll period has gone from 5.2 s to 7.5 s, and the rough GM from 0.85 m to 0.41 m. Nothing about the boat changed except the load and a slack tank. This is what the deckhand sees as "she's gone lazy".

## What gives a false result

- ==Counting half-rolls.== If the crew count each swing, 10 "rolls" are really 5, and the period is half what it should be. In the Heron's first test, counting 20 swings in 52 s gives 2.6 s and a rough GM of 3.4 m: nonsense, and false comfort. Count full rolls, and check by eye that you start and stop on the same side.
- ==Testing in waves.== A vessel in a seaway is pushed at the wave period, not her own. Do the test in calm water, and in a seaway read it only as a warning.
- Testing while tied up hard. Lines, fenders and a quay hold her back and change the roll.
- Testing in the wrong loading condition. The result applies only to the load she carried during the test.
- Starting the clock at a random point or timing only one roll. Always time several.
- Forgetting that free surface and top weight both lengthen the period. The test sees the effective (fluid) GM, which is what you want.

## Use at sea

The roll is a stability instrument you carry everywhere. Learn how your vessel rolls in her normal conditions. A period that gets **longer** over a trip, a roll that hangs at the end, or a lean that doesn't go away means GM is shrinking. Look for a slack tank, ice, water on deck, top weight, or burned-off low fuel. Tell the master.

But a pass is not an approval. The rolling check cannot say anything about the shape of the GZ curve, the downflooding angle or the range of stability. A vessel can have an acceptable GM and a poor curve (for instance with little freeboard). Use the check as a quick test, and rely on the vessel's stability documents for the real limits.

## BC picture

A gillnetter returning from a long trip in November, with the tanks half burned and a net pile on the stern, notices that she now rolls slower than on the way out. Her skipper times ten rolls on the next calm stretch: 8 seconds each against 5 on the way out. That's enough to move the net pile to a low position, press up the tank and slow down before the next crossing.

## Exam traps

One roll is out and back, not one swing. Longer period means **lower** GM, not higher. The rule of thumb compares the period in seconds with the beam in metres; the numbers are not in the same units, so do not try to make them equal. The formula for GM uses the period squared in the bottom. And a good result at the dock in calm water doesn't guarantee safety in a seaway.

## Check yourself

1. 10 complete rolls take 46 seconds. What is the period? (4.6 s.)
2. Beam is 5.0 m and the period is 6.2 s. Does the check pass? (No: 6.2 is longer than 5.0.)
3. Why does a longer period mean a lower GM?
4. Name two mistakes that make a rolling test give a false result.
