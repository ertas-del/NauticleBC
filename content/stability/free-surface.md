---
id: stability.free-surface
title: "Free surface effect"
module: stability
tickets: [master-limited-60gt, fishing-master-4]
order: 70
summary: Liquid that can slosh in a part-full tank, hold or deck well acts like a rise of G and cuts the effective GM. This lesson gives the cause, the formula (breadth cubed), worked losses of GM, and the habits and fittings that limit it.
objectives:
  - Explain why a part-full tank or water on deck reduces stability even though its weight has not changed.
  - Calculate the loss of GM from a rectangular free surface and find the corrected (fluid) GM.
  - Explain why breadth matters far more than length or the amount of liquid, and what baffles do.
  - "State the practical rules: tanks full or empty, clear the deck, keep freeing ports open."
prerequisites: [stability.suspended-weights-and-lifting]
estimated_minutes: 22
key_facts:
  - Free surface is a liquid surface that is free to move as the vessel heels, in a part-full tank, hold, bilge or on deck.
  - Liquid in a part-full tank shifts to the low side when the vessel heels, which acts as a virtual rise of G and reduces the effective GM; the actual centre of gravity of the solid weights has not moved.
  - The loss of GM is free surface moment divided by displacement; for a rectangular tank the free surface moment is density x length x breadth cubed / 12.
  - Breadth is cubed, so halving the width of a tank cuts the free surface effect to one eighth, whereas halving the length only halves it; the amount of liquid in the tank (once it is part-full) matters little.
  - Two longitudinal baffles that divide a tank into thirds cut the free surface effect by a factor of nine; a baffle reduces the effect but does not remove it.
  - A tank that is completely full (pressed up) or completely empty has no free surface.
  - Fluid GM = solid GM - loss from free surface; stability should be judged on the fluid GM.
  - Water on deck and in the bilge has a free surface too; keep freeing ports and scuppers clear so water drains.
source_ids: [stab-swos-dca-4-3, stab-wikipedia-free-surface-effect, stab-wikipedia-metacentric-height, stab-mt4241-stability-trim]
review_status: needs_expert_review
---

# Free surface effect

**Study aid only. Invented numbers.** The formula below is for a plain rectangular tank, and real tanks, holds and decks need the maker's data.

## What you need to know

A tank of fuel or fish that is solid keeps its weight in one place. A tank that is part full does not: the liquid runs to the low side when the vessel leans, and the weight goes with it. That makes the effect of the tank the same as a rise in G, even though no weight has been added and none has been moved by hand. Free surface is a quiet killer because the vessel looks fine until it is leaned.

## The picture

Upright, a half-full tank is level. Heel the vessel and the liquid surface stays level with the horizon while the tank tilts, so a wedge of liquid moves from the high side to the low side. The weight of that wedge now pushes down on the low side, adding to the heeling, and so the righting lever is smaller than it should be. The vessel's G has not moved; the effect is as though G had risen, and so it is called a ==virtual rise of G==. The result is a ==smaller effective GM==, a slower return to upright, and a risk that gets worse in heavy seas, because the liquid in a tank tends to move out of step with the vessel and push the heel on.

## How big is it?

For a rectangular tank, the effect depends on its size and density like this:

==Free surface moment = density x length x breadth^3 / 12==

and the loss of GM is:

==Loss of GM = free surface moment / displacement==

Here length means fore-and-aft, and **breadth** means athwartships (across the vessel). Breadth is **cubed**, so it dominates. The amount of liquid in the tank is not in the formula at all, so a tank at 10 percent is almost as bad as a tank at 90 percent, and it is only a completely full or a completely empty tank that has none.

### Worked example 1: a slack fish hold

The Tern (invented; 40 t, solid GM 0.45 m) has an iced seawater hold 3.0 m long and 2.4 m wide, part full. Density of seawater 1.025 t/m3.

1. Free surface moment = 1.025 x 3.0 x 2.4^3 / 12 = 1.025 x 3.0 x 13.824 / 12 = **3.54 t.m**.
2. Loss of GM = 3.54 / 40 = **0.089 m**.
3. Fluid GM = 0.45 - 0.089 = **0.361 m**.

One slack hold has eaten a fifth of the GM, and the 40 t displacement has not changed.

### Worked example 2: divide it

Fit two fore-and-aft baffles so the hold is three narrow pens, each 3.0 m long and 0.8 m wide.

1. Each pen: 1.025 x 3.0 x 0.8^3 / 12 = 0.131 t.m. Three pens: **0.394 t.m**.
2. Loss of GM = 0.394 / 40 = **0.0098 m**, about 0.01 m.

Nine times smaller: 3.54 / 0.394 = 9. Dividing by thirds cuts the effect to one ninth, because each third has a breadth one third as large and 1/3 cubed is 1/27, and there are three of them. Baffles do not remove free surface; they shrink it. A baffle with large holes, or one that does not run the full length, does less.

### Worked example 3: width against length

A diesel tank 2.0 m long and 1.5 m wide (density 0.85 t/m3): free surface moment = 0.85 x 2.0 x 1.5^3 / 12 = 0.478 t.m; loss of GM on 40 t = **0.012 m**. Fuel is lighter than seawater and the tank is small, so the loss is small, but it adds to the others. Halve the width and the moment falls to one eighth. Halve the length and it falls to a half.

### Worked example 4: water on deck

Suppose a well deck 4.0 m long and 3.0 m wide holds 0.2 m of seawater after a green sea. Weight of water = 4.0 x 3.0 x 0.2 x 1.025 = **2.46 t**, so the displacement rises to about 42.5 t.

1. Free surface moment = 1.025 x 4.0 x 3.0^3 / 12 = **9.23 t.m**.
2. Loss of GM = 9.23 / 42.5 = **0.217 m**.

That is nearly half the Tern's GM lost to one shallow pool of water, **before** counting the weight of the water itself and the freeboard it has used. Water on deck is the worst free surface of all, because it is wide and it is high. This is why freeing ports and scuppers exist.

## Putting it into practice

- ==Keep tanks either full (pressed up) or empty.== Burn fuel down one tank at a time instead of leaving several half full.
- Where tanks must be part full, prefer ==narrow tanks== and ==baffles==.
- Keep fish holds and bilges as dry as you can, and ==clear water off the deck== at once. Keep ==freeing ports and scuppers open==, never blocked by gear or lashed shut.
- Know which tanks are slack on your vessel before heading out and report a slack tank to the master.

## BC picture

A trap boat takes green water over the stern in a Hecate Strait chop. The aft deck holds a wide pool of water as she heels, with her fish tanks half full and the lazy roll getting slower. Three free surfaces are adding together. A clear deck, closed doors and full tanks would have kept the GM she started with.

## Exam traps

The loss of GM depends on the **breadth** of the surface (cubed), not the weight of the liquid. Free surface does not change KG of the solids; the vessel's real G has not moved. The loss is taken off GM (fluid GM = solid GM - loss of GM); it is the same thing as a virtual rise of G, equal to free surface moment / displacement. A tank that is full has no free surface; a tank that is nearly full still has some, and a tiny gap is enough. And a baffle reduces the effect; it does not stop it.

## Check yourself

1. A rectangular tank is 2.0 m long and 1.0 m wide. A second tank is 2.0 m long and 2.0 m wide. How much bigger is the free surface effect of the second? (Eight times.)
2. Free surface moment is 4.0 t.m on a 50 t vessel with a solid GM of 0.60 m. What is the fluid GM? (4.0 / 50 = 0.08; 0.52 m.)
3. Why is a tank 5 percent full still a hazard?
4. Why is water on deck so dangerous?
