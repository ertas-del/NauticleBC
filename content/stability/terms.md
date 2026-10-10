---
id: stability.terms
title: "Displacement, draught, freeboard and deadweight"
module: stability
tickets: [master-limited-60gt]
order: 10
summary: The basic words of stability - weight and buoyancy, displacement, lightship, deadweight, draught, freeboard, reserve buoyancy, trim, list, heel and loll - with worked examples of displacement from draught and sinkage from loading.
objectives:
  - Explain why a floating vessel's weight equals the weight of the water it displaces, and use that to find displacement from draught.
  - Define lightship, deadweight, draught, freeboard and reserve buoyancy, and find freeboard and deadweight from given figures.
  - Work out how much a vessel sinks when weight is added, using tonnes per centimetre, and explain why it sits deeper in fresh water.
  - Tell list, heel, loll and trim apart.
estimated_minutes: 20
key_facts:
  - A floating vessel displaces its own weight of water (Archimedes); its displacement is the mass of that water, in tonnes.
  - Displacement (t) = underwater volume (m3) x water density; seawater is about 1.025 t/m3 and fresh water 1.000 t/m3.
  - Lightship is the vessel as built with no fuel, water, stores, crew or cargo; deadweight is displacement minus lightship, which is everything you have added.
  - Draught is the vertical distance from the waterline down to the keel; freeboard is the vertical distance from the waterline up to the deck edge.
  - Reserve buoyancy is the watertight volume above the waterline; it is what keeps the vessel afloat when weight is added or a wave lifts it.
  - Tonnes per centimetre immersion (TPC) = waterplane area x density / 100; sinkage (cm) = added weight (t) / TPC.
  - A vessel floats deeper in fresh water than in salt water because fresh water is less dense, so more volume must be displaced.
  - List is a lean caused by G being off the centreline; heel is a lean caused by an outside force such as wind or a turn; loll is a lean caused by negative GM and the vessel flops to either side.
  - Trim is the difference between the draught forward and the draught aft.
source_ids: [sixty-fao-ftp517-stability, stab-fao-ftp517-ch6-example, stab-mt4241-stability-trim, stab-csa-2001-ss110-112]
review_status: needs_expert_review
---

# Displacement, draught, freeboard and deadweight

**Study aid only. Not for navigation or loading decisions.** Every figure here is **invented** for teaching. Use your vessel's own documents for real numbers.

## What you need to know

Stability starts with two plain facts: things have weight, and water pushes up. Every term in this lesson is just a way of saying how much weight, how deep, and how much room is left. Get these words clear and the next lessons (G, B, M and GM) will make sense.

## Weight and buoyancy from first principles

A vessel is pulled down by gravity. Its weight is a downward force, and for ship sums we measure it as a mass in ==tonnes== (1 tonne = 1000 kg). A vessel floats because the water it pushes aside pushes back. This is Archimedes' principle: the upward force on a floating body equals the ==weight of the water it displaces==. A floating vessel settles until the weight of the water it displaces exactly equals its own weight. If you add 2 tonnes of fish, the vessel sinks until it displaces 2 tonnes more water. If you take 2 tonnes off, it rises.

That mass of displaced water is called the vessel's ==displacement==, Δ (the Greek capital delta). Because displaced water weighs what the vessel weighs, displacement is also the vessel's total weight at that moment, fuel, crew and cargo included.

To go from the underwater volume to a displacement, multiply by the density of the water:

==Displacement (t) = underwater volume (m3) x density (t/m3)==

Seawater is about ==1.025 t/m3==. Fresh water is ==1.000 t/m3==. A cubic metre of seawater weighs 1.025 tonnes.

### Worked example 1: displacement from draught

"Example Tern" is an invented 12.0 m workboat. To keep the arithmetic simple, imagine its hull is a plain box underwater: 12.0 m long, 3.5 m wide, floating at a draught of 0.90 m in seawater.

1. Underwater volume = 12.0 x 3.5 x 0.90 = **37.8 m3**.
2. Displacement = 37.8 x 1.025 = **38.745 t**, about **38.7 t**.

A real hull is not a box, so a real vessel uses a table of hydrostatic data (its "curves of form") to turn a draught into a displacement. The idea is identical.

## Lightship and deadweight

==Lightship== is the vessel as built, ready to float, but with no fuel, fresh water, stores, crew, gear or cargo. It is the starting weight. ==Deadweight== is everything you have put aboard since: fuel, water, stores, crew, catch or cargo. So:

==Deadweight = displacement - lightship==

### Worked example 2: deadweight

The Tern has lightship **30.0 t**. Loaded to leave harbour with a catch on deck, it displaces **40.0 t**. Deadweight = 40.0 - 30.0 = **10.0 t**. Check it adds up: fuel 4.0 + fresh water 1.0 + crew, gear and stores 2.0 + catch 3.0 = 10.0 t. Yes.

## Draught, freeboard and reserve buoyancy

==Draught== is the depth of the vessel below the waterline: the vertical distance from the ==waterline to the keel== (the bottom). On a real vessel it is read from the draught marks painted on the bow and stern. ==Freeboard== is the other way: the vertical distance from the ==waterline up to the deck edge== (the top of the side where water could first come aboard).

Together, draught + freeboard = the depth of the hull from keel to deck edge. If the Tern is 1.30 m deep from keel to deck edge and floats at 0.85 m draught, freeboard = 1.30 - 0.85 = **0.45 m**. Load it down to 0.95 m draught and freeboard shrinks to 0.35 m.

==Reserve buoyancy== is the volume of the hull above the waterline that is watertight (or weathertight) and so could provide extra buoyancy if the vessel is pushed down. It is the vessel's safety margin against overloading and against waves. More freeboard means more reserve buoyancy. Lower freeboard also means the deck edge goes under water at a smaller heel, which shortens the range over which the vessel can right itself (more in the GZ curve lesson). That is one reason the law says that ==the master of a vessel shall ensure the applicable load lines are not submerged== (Canada Shipping Act, 2001, s. 110(2)).

## How far does loading sink the vessel?

For small changes of weight, assume the sides are vertical at the waterline, so each extra centimetre of draught adds the same extra volume. The weight needed to sink the vessel one centimetre is called ==tonnes per centimetre immersion (TPC)==:

==TPC = waterplane area (m2) x density (t/m3) / 100==

The waterplane area is the area of the vessel's cross-section at the waterline, seen from above. For our box Tern it is 12.0 x 3.5 = 42.0 m2. Then ==sinkage (cm) = added weight (t) / TPC==.

### Worked example 3: sinkage

Tern's TPC in seawater = 42.0 x 1.025 / 100 = **0.4305 t/cm**. Loading **2.0 t** of catch sinks it 2.0 / 0.4305 = **4.65 cm**, about 4.7 cm. Loading **3.0 t** sinks it about **7.0 cm**. The new draught is 0.90 + 0.0465 = about 0.946 m for the 2.0 t case.

### Worked example 4: fresh water

The Tern, at 38.745 t, moves from the sea into a freshwater river. Fresh water is less dense, so to displace the same weight it must displace more volume. New volume = 38.745 / 1.000 = 38.745 m3. Draught = volume / waterplane area = 38.745 / 42.0 = **0.9225 m**, which is 2.25 cm deeper than the 0.90 m in seawater. The reverse is true when going out to sea: the vessel rises. For a vessel loaded close to its limit, that extra depth matters. In BC, a boat loading to the line in the salt chuck then running up a river can submerge a mark it did not submerge before.

## Lean, list, heel, loll and trim

Four different words describe four different causes, and exams love to ask which is which.

==List== is a lean to one side caused by weight, so that G sits off the centreline. A vessel with a cargo shifted to port lists to port and stays there. ==Heel== is a lean caused by an outside force, such as wind pressure on the superstructure or the centrifugal push in a turn; when the force goes away, the heel goes away. ==Loll== is the lean of a vessel with negative GM, which is unstable when upright, so it falls to one side and settles at the angle of loll, and may flop to the other side. A lolling vessel is in danger, because it is at a small angle from a capsize. ==Trim== is a different matter altogether: it is the difference between the draught forward and the draught aft, which is a fore-and-aft tilt, not a sideways one.

## Exam traps

List and heel are not the same: list is a lasting lean due to weight, heel is a temporary lean due to an outside force. Freeboard is measured to the deck edge, not to the cabin top. Deadweight is not the same as displacement. And a vessel does not "weigh" 38 tonnes in a river and 38 tonnes in the sea at the same draught; the weight is the same, but the draught changes.

## Check yourself

1. A box-shaped barge 10.0 m by 4.0 m floats at 0.80 m draught in seawater. What is its displacement? (32.0 m3 x 1.025 = 32.8 t.)
2. A vessel has lightship 25 t and displaces 31 t. What is its deadweight? (6 t.)
3. Which word describes a lean that disappears when the wind drops?
4. Does a vessel float higher or lower on entering fresh water, and why?
