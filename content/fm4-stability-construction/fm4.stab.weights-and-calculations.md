---
id: fm4.stab.weights-and-calculations
title: "Adding, removing, shifting and suspending weights"
module: fm4-stability-construction
tickets: [fishing-master-4]
order: 420
summary: "How adding, removing, transferring and suspending weights changes displacement, KG, GM and list, with worked calculations and the rules for each case."
objectives:
  - "Calculate the new KG and GM after adding or removing a weight."
  - "Calculate the effect of shifting a weight vertically."
  - "Calculate the list caused by a transverse shift."
  - "Explain the effect of a suspended weight."
prerequisites: []
estimated_minutes: 22
key_facts:
  - "New KG = total moments about the keel \u00f7 new displacement."
  - "Shift of G: GG1 = (weight \u00d7 distance moved) \u00f7 displacement."
  - "A weight hanging from a boom acts as if at the point of suspension, raising G."
source_ids: [tp2293-2016, mpr-sor-2007-115]
review_status: needs_expert_review
---

# Adding, removing, shifting and suspending weights

Every change of load changes where G sits. The calculations use moments about the keel.

## Adding a weight

> New KG = (Δ × KG + w × kg) ÷ (Δ + w)

**Example.** Δ = 80 t, KG = 3.50 m. Add 5 t at kg = 4.50 m. New KM from the booklet at 85 t = 4.05 m.

| Item | Weight (t) | KG (m) | Moment (t·m) |
|---|---|---|---|
| Vessel | 80 | 3.50 | 280.0 |
| Added | 5 | 4.50 | 22.5 |
| **Total** | **85** | | **302.5** |

```
New KG = 302.5 ÷ 85 = 3.56 m
New GM = 4.05 − 3.56 = 0.49 m
```

A weight added above G raises G and reduces GM.

## Removing a weight

Use a minus for the weight removed.

**Example.** From Δ = 85 t, KG = 3.56 m (moments 302.5), discharge 6 t from kg = 2.20 m. KM at 79 t = 4.12 m.

```
Moments = 302.5 − 6 × 2.20 = 302.5 − 13.2 = 289.3
New Δ = 79 t
New KG = 289.3 ÷ 79 = 3.66 m
New GM = 4.12 − 3.66 = 0.46 m
```

Removing a low weight raises G. This is why a vessel is often at her weakest near the end of a trip, when fuel and low weights are used up.

## Rules in words

- Add a weight ==above== G: G rises, GM falls.
- Add a weight ==below== G: G falls, GM rises.
- Remove a weight ==above== G: G falls.
- Remove a weight ==below== G: G rises.

## Shifting a weight vertically

> GG1 = (w × d) ÷ Δ

**Example.** Move 4 t up by 2 m on an 80 t vessel.

```
GG1 = 4 × 2 ÷ 80 = 0.10 m (G rises)
```

GM falls by 0.10 m.

## Shifting a weight sideways: list

> Step 1: GG1 = (w × d) ÷ Δ  (G moves sideways)
> Step 2: tan θ = GG1 ÷ GM

**Example.** Move 2 t sideways by 3 m. Δ = 80 t. GM = 0.50 m.

```
GG1 = 2 × 3 ÷ 80 = 0.075 m
tan θ = 0.075 ÷ 0.50 = 0.15
θ = 8.5°
```

The vessel lists about 8.5 degrees.

## Suspended weights

When a weight is lifted off the deck and hangs from a boom, it acts as if it were at the ==point of suspension== (the boom head or the block), not where it was lying. This raises G.

**Example.** Lift 2 t with the block 4.5 m above the weight's original height. Δ = 80 t.

```
GG1 = 2 × 4.5 ÷ 80 = 0.11 m (G rises)
```

The higher the lift point, the worse the effect. Keep lifts low.

## Draft and trim

Adding a weight on the centre of flotation sinks the vessel bodily. Adding it forward or aft causes trim. Use the booklet's TPC and moment-to-change-trim data to estimate drafts.

## A BC example

A skipper in Bamfield lifts a 2 t crab trap stack onto the dock with his boom. He knows G rises and GM falls while the load is in the air, so he does it in calm water with the boom low and the tanks pressed full.

## Common mistakes

- Forgetting to add the weight to displacement.
- Using KM for the old displacement.
- Wrong sign when removing weights.
- Forgetting that weights hanging act at the point of suspension.

## Check yourself

1. Δ 80 t, KG 3.5 m. Add 5 t at 4.5 m. What is the new KG?
2. Move 4 t up 2 m on an 80 t vessel. How far does G move?
3. What happens to G when a weight is lifted by a boom?

---

*Study aid, not an operating manual or official training. Written in original words from the Fishing Master, Fourth Class syllabus in TP 2293 and the Marine Personnel Regulations. Always confirm current requirements with Transport Canada and follow your vessel's stability booklet and approved procedures.*
