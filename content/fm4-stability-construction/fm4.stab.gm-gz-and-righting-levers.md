---
id: fm4.stab.gm-gz-and-righting-levers
title: "GM, GZ and righting moment: the formulas"
module: fm4-stability-construction
tickets: [fishing-master-4]
order: 410
summary: "The formulas for GM, GZ and the moment of statical stability with worked examples, and how G and B create the righting lever when heeled."
objectives:
  - "Calculate GM from KM and KG."
  - "Calculate GZ for a small angle."
  - "Calculate the righting moment."
  - "Draw and explain a sketch of a heeled vessel showing G, B and Z."
prerequisites: []
estimated_minutes: 20
key_facts:
  - "GM = KM \u2212 KG."
  - "GZ = GM \u00d7 sin \u03b8 for small angles (up to about 10 to 15 degrees)."
  - "Moment of statical stability (righting moment) = displacement \u00d7 GZ."
source_ids: [tp2293-2016, mpr-sor-2007-115]
review_status: needs_expert_review
---

# GM, GZ and righting moment: the formulas

The calculations in SCS 1 are short. They need the right formula, careful units and a clear layout. Learn them one at a time.

## Sketch of a heeled vessel

Draw the vessel heeled to one side. Mark ==G== (weight acting down), ==B== moved toward the low side (buoyancy acting up), and ==M== above G on the centreline. Drop a perpendicular from G to the line through B. The distance between the two lines is ==GZ==. The weight acts through G and the buoyancy through B, and together they form the righting couple.

## GM from KM and KG

> GM = KM − KG

KM comes from the stability booklet for the displacement. KG comes from your loading.

**Example.** KM = 4.10 m, KG = 3.50 m.

```
GM = 4.10 − 3.50 = 0.60 m
```

GM is positive, so the vessel is stable.

## GZ

> GZ = GM × sin θ

Valid for small angles of heel.

**Example.** GM = 0.60 m, θ = 8° (sin 8° = 0.139).

```
GZ = 0.60 × 0.139 = 0.0835 m
```

Set the calculator to ==degrees==.

## Moment of statical stability

> Righting moment = Δ × GZ

**Example.** Δ = 80 t, GZ = 0.0835 m.

```
RM = 80 × 0.0835 = 6.7 t·m
```

This is the turning effect that returns the vessel upright. At larger angles read GZ from the stability curve rather than calculating it.

## Effect of changes

- Raising G reduces GM and GZ.
- Lowering G increases GM and GZ.
- Reducing freeboard shortens the range of stability.

## Units

Heights and distances in ==metres==, weights in ==tonnes==, moments in ==tonne-metres==. Write the units on each line.

## A BC example

A skipper checks the booklet at departure: Δ = 80 t, KM = 4.10 m. He estimates KG at 3.50 m and calculates GM = 0.60 m. The booklet minimum is 0.35 m, so he sails with a comfortable margin.

## Common mistakes

- Using KG instead of KM, or the reverse.
- Leaving the calculator in radians.
- Using the GZ formula for large angles.
- Forgetting units.

## Check yourself

1. KM 4.20 m, KG 3.60 m. What is GM?
2. GM 0.50 m, heel 10° (sin 10° = 0.174). What is GZ?
3. What is the righting moment for Δ = 60 t and GZ = 0.10 m?

---

*Study aid, not an operating manual or official training. Written in original words from the Fishing Master, Fourth Class syllabus in TP 2293 and the Marine Personnel Regulations. Always confirm current requirements with Transport Canada and follow your vessel's stability booklet and approved procedures.*
