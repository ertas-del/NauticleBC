---
id: stability.moving-adding-removing-weight
title: "Loading: moving, adding and removing weight"
module: stability
tickets: [master-limited-60gt]
order: 50
summary: How G moves when weight is shifted, added or removed, with the shift formula GG1 = w x d / displacement, the effect on GM, and how a sideways shift causes a list whose angle follows tan(list) = GG1 / GM.
objectives:
  - Say which way G moves when weight is moved, loaded or discharged.
  - Calculate the shift of G with GG1 = w x d / displacement and the new GM after a vertical change.
  - Calculate the angle of list from a sideways shift of G.
  - Tell a list from a loll, and say how each is put right.
prerequisites: [stability.righting-lever-gz-curve]
estimated_minutes: 24
key_facts:
  - When a weight is moved, G moves in the same direction as the weight, parallel to the path of the weight.
  - The shift of G is GG1 = w x d / displacement, where w is the weight moved, d the distance it moves and the displacement is the final one.
  - When weight is added, G moves toward the added weight; when weight is removed, G moves away from the place it was removed; the same shift formula applies with the final displacement.
  - Adding weight above G, or removing weight below G, raises G and reduces GM; adding weight below G, or removing weight above G, lowers G and increases GM.
  - A sideways shift of G puts G off the centreline and the vessel lists until the lines of weight and buoyancy are in line again.
  - For a small list, tan(angle of list) = GG1 / GM, where GG1 is the sideways shift of G; a smaller GM gives a larger list for the same shift.
  - A list is cured by moving or adding weight on the high side; a loll (negative GM) is cured by lowering G, not by moving weight sideways.
  - A vessel that leans to one side only is listing; a vessel that falls to either side, changing sides as it rolls, is lolling.
source_ids: [stab-swos-dca-4-2, stab-mt4241-stability-trim, stab-fao-ftp517-ch6-example]
review_status: needs_expert_review
---

# Loading: moving, adding and removing weight

**Study aid only. Invented numbers.** Never load a vessel by these sums alone; use your vessel's stability documents and the master.

## What you need to know

A vessel's centre of gravity G is the balance point of everything aboard. Anything you move, load or discharge changes where G is. This lesson is one rule and one formula, used four ways. Learn them and you can predict what a loading change does before you do it.

## The rule: G goes with the weight

==When a weight is moved, G moves in the same direction.== Move a 2 t catch from the deck down into the hold and G drops. Slide it to starboard and G goes to starboard. G moves parallel to the path of the weight, and further the heavier the weight, the longer the move, and the lighter the vessel.

==When weight is added, G moves toward it.== ==When weight is removed, G moves away from where it was.== Load a weight above G and G rises. Discharge a weight from above G and G falls (you took away something high). Load a weight below G and G falls. Take away a weight below G (burn fuel from a low tank) and G rises.

## The formula

==GG1 = w x d / displacement== where GG1 is the shift of G (metres), w the weight moved (tonnes) and d the distance it moves (metres). For a move, the displacement is unchanged. For adding or removing, use the ==new (final) displacement== and let d be the distance between the weight and the old G (for adding) or the weight's former position and G (for removing).

The formula follows from the idea that G is a weighted average: moving 2 t of a 40 t vessel by 1 m shifts the average by 1/20 of a metre.

### Worked example 1: moving weight vertically

The Tern (invented): displacement 40 t, KG 1.95 m, KM 2.40 m, GM 0.45 m. Move 2 t of catch from the deck (centre at 2.30 m above the keel) down to the hold (centre at 1.00 m). Distance moved = 2.30 - 1.00 = 1.30 m downward.

1. GG1 = 2 x 1.30 / 40 = **0.065 m**, downward.
2. New KG = 1.95 - 0.065 = 1.885 m.
3. New GM = 2.40 - 1.885 = **0.515 m**. (KM does not change when weight is only moved.)

### Worked example 2: adding weight

The Tern loads 2 t of spare gear on the cabin roof, its centre 3.00 m above the keel. The new displacement is 42 t.

1. Distance from old G: 3.00 - 1.95 = 1.05 m (above G, so G rises).
2. GG1 = 2 x 1.05 / 42 = **0.05 m**. New KG = **2.00 m**.
3. Check the long way: moments 78.0 + 2 x 3.00 = 84.0 t.m, and 84.0 / 42 = 2.00 m. They agree.
4. At 42 t, suppose the table gives KM 2.42 m. New GM = 2.42 - 2.00 = **0.42 m**. It fell from 0.45.

### Worked example 3: removing weight

From the loaded Tern (40 t, KG 1.95 m), discharge 3 t of catch whose centre was 2.30 m above the keel. New displacement is 37 t.

1. Distance from G: 2.30 - 1.95 = 0.35 m (the weight was above G).
2. GG1 = 3 x 0.35 / 37 = **0.0284 m**, away from the removed weight, so downward.
3. New KG = 1.95 - 0.0284 = **1.9216 m**. Check: moments 78.0 - 3 x 2.30 = 71.10; 71.10 / 37 = 1.9216 m.

Removing high weight helped. But the same vessel burning fuel from low tanks has the opposite effect, which is why a vessel is often at its weakest on the way home.

### The vertical rule in one table

| Change | G moves | GM |
|---|---|---|
| Add weight above G | up | smaller |
| Add weight below G | down | larger |
| Remove weight above G | down | larger |
| Remove weight below G | up | smaller |
| Move a weight up | up | smaller |
| Move a weight down | down | larger |

## Sideways shifts: the list

If G moves sideways, off the centreline, weight and buoyancy are no longer in line. The vessel heels until B has moved far enough toward the low side to be directly under G again. The result is a ==list==. For small angles:

==tan(angle of list) = GG1 / GM==

where GG1 is the **sideways** shift of G. So for the same sideways shift, a vessel with a smaller GM lists more. This is the sum that makes tender vessels dangerous.

### Worked example 4: a catch slides across

A 0.5 t load moves 2.0 m across the deck on a 25 t vessel with GM 0.50 m (invented).

1. GG1 = 0.5 x 2.0 / 25 = **0.04 m** sideways.
2. tan(list) = 0.04 / 0.50 = 0.08.
3. List = **4.6 degrees**.

### Worked example 5: same shift, tender vessel

Take the same vessel after it picks up top weight, so GM falls to 0.25 m. The same move gives tan(list) = 0.04 / 0.25 = 0.16 and a list of **9.1 degrees**. A halving of GM doubled the list angle.

### Worked example 6: 2 t shifted 1.2 m on the Tern

GG1 = 2 x 1.2 / 40 = 0.06 m. tan(list) = 0.06 / 0.45 = 0.1333; list = **7.6 degrees**.

## List or loll?

Both look like a lean, but the cause and the cure are different.

| | List | Loll |
|---|---|---|
| Cause | G off the centreline (positive GM) | Negative GM |
| Leans to | One side only | Either side; can flop |
| Cure | Move or add weight on the high side, or remove it from the low side | Lower G (remove top weight, press up slack tanks, add low weight) |

A quick test is to watch the lean as the vessel rolls. A list stays on the same side. A loll changes sides as seas hit her. If in doubt, assume a loll, because the wrong cure for a loll (shifting weight about) can tip her the other way. This lesson gives the principle; follow the master and your vessel's procedures.

## BC picture

A herring skiff takes a heavy last bin on the starboard side and lists. Move a bin to port, bring it level, and the work is done. A seine boat leaving a dock with the deck load stacked high finds the lean changes sides as she rolls: that is a loll, and stacking more on the high side will not help. Lower the load.

## Exam traps

Use the **final** displacement when adding or removing weight. The distance d is measured from the old G to the weight, not from the keel. G moves toward added weight and away from removed weight. The sideways shift formula gives a **tangent**, not an angle: take the arctangent. And moving weight does not change KM; adding or removing it does, because the draught changes.

## Check yourself

1. 3 t is moved up 1.5 m on a 60 t vessel. By how much does G rise? (3 x 1.5 / 60 = 0.075 m.)
2. A 2 t weight is loaded 2.0 m above G on a vessel that now displaces 50 t. What is GG1? (2 x 2.0 / 50 = 0.08 m, upward.)
3. G has moved 0.06 m sideways. GM is 0.40 m. What is the list? (tan = 0.15; 8.5 degrees.)
4. How would you tell a list from a loll?
