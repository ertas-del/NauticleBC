---
id: stability.g-b-and-m
title: "Centre of gravity, buoyancy and the metacentre"
module: stability
tickets: [master-limited-60gt]
order: 20
summary: What G, B and M are, why B moves when a vessel heels and G does not, how M is found for a box-shaped hull, and how the position of M relative to G decides stable, neutral or unstable equilibrium.
objectives:
  - Define the centre of gravity (G), centre of buoyancy (B) and transverse metacentre (M) and say what moves and what stays put when a vessel heels.
  - Describe stable, neutral and unstable equilibrium using the positions of G and M.
  - Work out KB, BM and KM for a box-shaped hull and see how KM changes with draught.
  - Explain why the height of G above the keel (KG) matters more than almost anything else.
prerequisites: [stability.terms]
estimated_minutes: 22
key_facts:
  - G, the centre of gravity, is the single point through which the whole weight of the vessel and everything on it acts downward.
  - B, the centre of buoyancy, is the centre of the underwater volume; the whole buoyant force acts upward through it.
  - When a vessel heels, G stays fixed (if nothing shifts) and B moves toward the side that is more deeply immersed.
  - M, the transverse metacentre, is the point where the upward line through B meets the centreline at a small angle of heel; for small angles it can be treated as fixed.
  - "Stable equilibrium: M above G (positive GM); neutral equilibrium: M at G (GM zero); unstable equilibrium: M below G (negative GM)."
  - K is the keel; KB, KG and KM are heights measured up from the keel; BM = I / V, where I is the second moment of area of the waterplane and V is the underwater volume.
  - For a rectangular waterplane, I = length x breadth cubed / 12, so doubling the beam makes I eight times larger and (because the volume only doubles) BM four times larger.
  - Raising KG is the most common cause of lost stability on small vessels.
source_ids: [sixty-fao-ftp517-stability, stab-mt4241-stability-trim, stab-fao-ftp517-ch6-example, stab-wikipedia-metacentric-height]
review_status: needs_expert_review
---

# Centre of gravity, buoyancy and the metacentre

**Study aid only.** The numbers are invented. This is the picture behind every stability calculation.

## What you need to know

Three letters do most of the work in stability: ==G==, ==B== and ==M==. G is where the weight acts. B is where the lifting force acts. M is a reference point that tells you whether the vessel is stable. Learn which one moves, which one does not, and what it means when M sits above or below G.

## G: the centre of gravity

A vessel is made of thousands of things, each with weight: hull, engine, fuel, crew, catch. For working out how the vessel behaves, all that weight can be treated as one downward force acting through a single point. That point is the ==centre of gravity, G==, the balance point of everything aboard. If you could hang the whole vessel from a string tied at G, it would hang level.

G moves only when weight moves, is added or is removed. It does not move because the vessel heels. Add a load high up and G rises. Burn fuel from a low tank and G rises too, because you removed a low weight. G is measured as a height above the keel, written ==KG==. ("K" stands for the keel.)

## B: the centre of buoyancy

The water presses up on the hull. All those upward pushes add up to one force equal to the displacement, and we treat it as acting through one point, the ==centre of buoyancy, B==. B is the geometric centre of the ==underwater volume== of the hull. Its height above the keel is ==KB==.

Here is the key idea. When the vessel is upright and still, the vessel's weight acts down through G and the buoyancy acts up through B, and the two are in line, one above the other. When the vessel heels, the underwater shape changes: on the low side more of the hull goes under, on the high side some comes out. The centre of that new shape moves toward the ==side that went down==. So ==B moves; G does not==. The upward force now acts a little to the low side of G.

## M: the metacentre

If you draw a vertical line up from the new B, it crosses the original centreline of the vessel at a point. For small angles of heel (up to roughly 10 to 15 degrees on many hulls) that point stays almost in the same place as the angle changes. It is called the ==transverse metacentre, M==. Think of M as the pivot point of a pendulum: as the vessel rolls, B swings along an arc whose centre is M. The distance from B up to M is ==BM==, and the height of M above the keel is ==KM==. So ==KM = KB + BM==.

## Why the position of M relative to G decides everything

Imagine the vessel heeled a little. The weight pulls down through G. The buoyancy pushes up along a vertical line that passes through M. If ==M is above G==, the upward line passes on the low side of G and turns the vessel back upright. This is ==stable equilibrium==: like a marble at the bottom of a bowl, it rolls back when pushed. If ==M is exactly at G==, the two forces are in line at any small angle, so there is no turning effect either way. This is ==neutral equilibrium==, like a marble on a flat table: it stays wherever you leave it. If ==M is below G==, the upward line passes on the high side of G, and the turning effect pushes the vessel further over. This is ==unstable equilibrium==, like a marble balanced on top of a dome. The vessel will not stay upright; it falls over to some angle (the angle of loll) or capsizes.

| Position of M | GM | Equilibrium | Behaviour |
|---|---|---|---|
| ==M above G== | positive | ==Stable== | Returns to upright |
| ==M at G== | zero | ==Neutral== | Stays at any small angle |
| ==M below G== | negative | ==Unstable== | Heels further, lolls or capsizes |

The gap between G and M is called GM, the metacentric height. The next lesson is all about it.

## Finding KM for a box-shaped hull

A real hull needs data supplied by a naval architect, but a simple box shows where the numbers come from. For a box: KB is half the draught (the centre of a rectangle). BM is ==BM = I / V==, where V is the underwater volume and I is the second moment of area of the waterplane (the area of the hull's cross-section at the waterline, seen from above). I measures how wide the waterplane is spread out from the centreline, and for a rectangle it is ==I = L x B3 / 12== (length times breadth cubed over twelve). Wide is what counts: breadth is cubed.

### Worked example 1: a box barge

A box barge is 10.0 m long and 4.0 m wide, floating at a draught of 1.0 m.

1. V = 10.0 x 4.0 x 1.0 = **40.0 m3**.
2. I = 10.0 x 4.03 / 12 = 10.0 x 64 / 12 = **53.33 m4**.
3. BM = I / V = 53.33 / 40.0 = **1.333 m**.
4. KB = 1.0 / 2 = **0.50 m**.
5. KM = KB + BM = 0.50 + 1.333 = **1.833 m**.

### Worked example 2: lighter loading

The same barge unloaded to a draught of 0.50 m.

1. V = 10.0 x 4.0 x 0.50 = **20.0 m3**. I is unchanged at **53.33 m4**, because the waterplane has not changed.
2. BM = 53.33 / 20.0 = **2.667 m**. KB = 0.25 m.
3. KM = 0.25 + 2.667 = **2.917 m**.

So KM is higher when the barge is lighter. In general, KM depends on the draught, and a vessel's hydrostatic table gives it for each draught. (For a box, KM falls as the draught increases from very light, reaches a lowest value, then slowly rises again as the draught gets larger. Real hulls follow the same pattern in a more complicated way, which is why you read KM from the hydrostatic table, not from a rule.) Notice the effect of beam too: if the barge were 8.0 m wide (twice as wide) at the same draught, I would be eight times larger (8.0 cubed is eight times 4.0 cubed) and V only twice as large, so BM would be four times larger: 4 x 1.333 = 5.333 m.

### Worked example 3: the same hull, three different KG values

Back to the barge at 1.0 m draught, KM = 1.833 m.

| KG | GM = KM - KG | Equilibrium |
|---|---|---|
| 1.20 m | 1.833 - 1.20 = **+0.633 m** | Stable |
| 1.833 m | **0** | Neutral |
| 1.90 m | 1.833 - 1.90 = **-0.067 m** | Unstable |

The hull did not change; only G moved. A small rise in G, from stowing cargo too high, took the barge from stable to unstable. That is why ==raising KG is the most common cause of lost stability==. The vessel seems fine in the harbour and may roll over in the first swell outside.

## A BC picture

A flat-sided work scow in Burrard Inlet is wide for its length, so BM is large and it is very stable with a low load. Pile bagged cargo three metres high on its deck, and KG climbs fast while KM hardly moves, so a calm-looking scow becomes tender. Beam gives stability; height of weight takes it away.

## Exam traps

G does not move when the vessel heels; B does. M is above G in a stable vessel (it is called metacentric *height* for that reason). KB is measured from the keel, not from the waterline. And "neutral" means GM is zero, not that the vessel is safe. Do not mix up the three heights from the keel: KB and KM come from the hull shape and draught, while KG depends on how the vessel is loaded; in a stable vessel KM is bigger than KG.

## Check yourself

1. Which of G and B moves when a vessel heels, and which way does the mover go?
2. KM is 2.10 m and KG is 2.25 m. Is the vessel stable?
3. A barge is made twice as wide at the same length and draught. By what factor does I change, and by what factor does BM change? (Eight, and four.)
4. Complete: KM = KB + ___.
