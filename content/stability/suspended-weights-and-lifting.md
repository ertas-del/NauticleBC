---
id: stability.suspended-weights-and-lifting
title: "Lifting and suspended weights"
module: stability
tickets: [master-limited-60gt, fishing-master-4]
order: 60
summary: A weight hanging from a boom acts at the point of suspension, not where it was stowed. This lesson shows how lifting raises G, how swinging a load out causes heel, why lifting from the water is the worst case, and the rules for safe lifting.
objectives:
  - Explain why a suspended weight acts as if it were at the point of suspension.
  - Calculate the rise of G and the loss of GM when a load is lifted.
  - Calculate the heel caused by a load hanging off the centreline.
  - List the practical steps that reduce the stability cost of a lift.
prerequisites: [stability.moving-adding-removing-weight]
estimated_minutes: 20
key_facts:
  - A weight that hangs freely from a boom or crane acts at the point of suspension (the boom head or block), not at its own centre of gravity.
  - As soon as a load is lifted clear of the deck, G rises as if the weight had been moved up to the point of suspension.
  - The rise of G when lifting a weight already aboard is GG1 = w x (height of the point of suspension - height of the weight's centre when stowed) / displacement.
  - Lifting a weight from the water or the dock adds it to the vessel at the point of suspension, so the displacement increases and G rises more than if it were landed on deck.
  - A load that hangs off the centreline also moves G sideways, and the vessel heels; tan(heel) = w x offset / (displacement x GM).
  - A higher point of suspension (a taller boom or a higher block) makes the loss of GM larger for the same load.
  - "To reduce the effect: lift as low as possible, keep the load near the centreline, use the shortest boom reach and set it down on deck at once, and use guys or tag lines."
  - If the vessel has a small GM, or tanks are slack, a lift that would otherwise be safe can be dangerous.
source_ids: [stab-mt4241-stability-trim, stab-swos-dca-4-2, stab-fao-ftp517-ch6-example]
review_status: needs_expert_review
---

# Lifting and suspended weights

**Study aid only. Invented numbers.** Never lift a load on the strength of this lesson. Follow your vessel's limits and the master.

## What you need to know

A boat that is stable alongside can lose most of its stability the moment it lifts something. The reason is that a weight hanging from a boom, crane or block does not act through its own centre of gravity. It acts through the ==point of suspension==, the place the load hangs from. That point is high. Lifting a load is, for stability purposes, the same as moving that weight up to the boom head.

## Why the point of suspension counts

Hold a bucket by the handle and let it swing. The bucket's weight pulls down on your hand, and how it hangs doesn't change the force you feel. A boom works the same way: the cable pulls down on the boom head with the full weight. The vessel does not "know" whether the load is a metre or a kilometre below the head. So as soon as the load is clear of the deck or water, ==the weight acts as if it were at the point of suspension==. A longer cable under the head makes no difference; only the height of the head counts.

## Lifting a weight already aboard

Take the same shift formula as the previous lesson, with the weight moved from where it was stowed to the point of suspension:

==GG1 = w x (height of point of suspension - height of the weight's centre when stowed) / displacement==

The displacement does not change, because the load is still aboard.

### Worked example 1: lift a crate

The Tern (invented): 40 t, KG 1.95 m, KM 2.40 m, GM 0.45 m. A 1 t crate stowed with its centre 2.30 m above the keel is lifted by a boom whose head is 5.50 m above the keel.

1. Height gained = 5.50 - 2.30 = 3.20 m.
2. GG1 = 1 x 3.20 / 40 = **0.08 m**, up.
3. New KG = 1.95 + 0.08 = 2.03 m. New GM = 2.40 - 2.03 = **0.37 m**. A 1 t crate on a 40 t vessel cost 18 percent of its GM.

### Worked example 2: swing it out

The boom is now slewed so that the crate hangs 2.0 m off the centreline.

1. Sideways shift of G = 1 x 2.0 / 40 = **0.05 m**.
2. tan(heel) = 0.05 / 0.37 = 0.135; heel = **7.7 degrees**.

Had the boom stayed on the centreline there would be no heel at all. With the load landed, the heel disappears (the boat then lists only if the crate has been set down off the centreline).

## Lifting from the water or the dock

Here the weight is **not** aboard to begin with. It joins the vessel at the instant it comes off the bottom or the dock, and it joins at the point of suspension. That is an addition of weight, high up, and the displacement grows too.

==The weight is effectively added at the boom head.== The shift of G becomes:

GG1 = w x (height of suspension - old KG) / new displacement.

### Worked example 3: hauling a 3 t load from the sea

The same Tern lifts 3 t from the water at the boom head, 5.50 m above the keel. New displacement is 43 t.

1. Height above old G: 5.50 - 1.95 = 3.55 m.
2. GG1 = 3 x 3.55 / 43 = **0.248 m**, up. New KG = **2.198 m**.
3. Check by moments: 78.0 + 3 x 5.50 = 94.5 t.m; 94.5 / 43 = **2.198 m**.
4. Suppose the table gives KM 2.44 m at 43 t. New GM = 2.44 - 2.198 = **0.242 m**. The GM has nearly halved.

Had the same 3 t been lowered onto the deck at 2.30 m, the new KG would be (78.0 + 6.9) / 43 = 1.974 m and GM 0.466 m. Same weight, same vessel: **0.242 m with the load hanging against 0.466 m when it is landed**. Get it down on the deck as soon as you can.

### Worked example 4: and off the centreline

While that load is still hanging, it is 0.8 m off the centreline. Sideways shift = 3 x 0.8 / 43 = 0.056 m. tan(heel) = 0.056 / 0.242 = 0.231; heel = **13.0 degrees**. The small-angle formula is only a rough guide at this size, and that is already a warning: a 3 t load on a 40 t vessel can heel her 13 degrees before she has moved, and a gust or a wave while she is heeling will make it worse.

## Fishing and BC lifts

A small boat in Johnstone Strait hauling a heavy pot, or a work boat picking up a float, a crab trap line or a skiff, is lifting from the water. The first rule is to start with a good GM: lifts that suit a vessel in calm weather with the tanks full can overturn it in a swell with the tanks slack. A swell also makes the load swing, so the load can hang further off the centreline than planned. Never lift with the boom outboard before the load has cleared the water and been steadied.

## Towing is similar

A towline led from a high point pulls from that point, so the pull acts as a heeling force from a high lever, and a sudden sheer or a line made fast too high is a stability hazard. Where the pull comes from matters as much as how hard it is. The point here is only that a tow is a heeling force acting from a high point; the towing arrangements themselves belong to the seamanship and towing lessons.

## What to do

- ==Lift as low as possible== (shortest boom, lowest reach, keep the head down).
- Keep the load ==close to the centreline== and use a guy or tag line so it does not swing.
- Set it down on deck at once, low and near the centreline, and lash it.
- Check your tanks first: slack tanks mean a larger loss of GM.
- If the vessel heels more than expected, ==lower the load, do not hold it up==. Land it and think again.

## Exam traps

A suspended load does not act at its own centre of gravity or at the deck; it acts at the point of suspension. The weight of the load does not matter alone; the height matters too. A load in the water that is lifted aboard is an **addition** of weight; a load already aboard that is lifted is a **move**. The vessel does not get safer if the load is lowered on a longer cable.

## Check yourself

1. Where does a freely hanging load act on the vessel?
2. A 2 t load is lifted from the deck (centre 2.0 m above the keel) to a boom head 6.0 m above the keel on a 50 t vessel. By how much does G rise? (2 x 4.0 / 50 = 0.16 m.)
3. Why is hauling from the water more serious than lifting from the deck?
4. Name two ways to reduce the effect of a lift on stability.
