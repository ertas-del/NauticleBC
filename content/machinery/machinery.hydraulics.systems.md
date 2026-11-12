---
id: machinery.hydraulics.systems
title: "Hydraulic systems: parts, pressure and safe handling"
module: machinery
tickets: [svmo]
order: 230
summary: How a hydraulic system turns pump flow into force, what each main component does, why cleanliness matters, what air, heat and leaks do, and how to work safely around high-pressure oil.
objectives:
  - Explain how fluid pressure transmits force and calculate force from pressure and area.
  - Name the main parts of a hydraulic circuit and the job of each.
  - Describe the effects and likely causes of air, overheating and low oil level.
  - Describe safe practice around pressurised hoses, accumulators and leaks.
prerequisites: [machinery.lubricants.oils-and-handling]
estimated_minutes: 15
key_facts:
  - "Hydraulic oil is practically incompressible, so it transmits force instantly. Force = pressure x area."
  - "A pump makes flow, not pressure. Pressure builds when the flow meets resistance."
  - "The relief valve limits the maximum pressure by sending oil back to the reservoir. Never adjust it without authority."
  - "A cylinder gives straight-line (linear) motion. A hydraulic motor gives rotary motion."
  - "A directional control valve decides which way the oil flows and so which way the actuator moves."
  - "Dirt is the main cause of hydraulic failure, so keep oil clean, keep filters serviced and keep the reservoir closed."
  - "Air in the oil makes the system noisy, spongy and erratic. Bleed it and find where it gets in."
  - "Never use a hand to find a leak. A fine jet of oil can penetrate the skin, and the injury needs urgent medical care."
  - "Depressurise and isolate before opening a line. An accumulator stores pressure even when the pump is stopped."
figures:
  - id: svmo-hydraulic-circuit
    caption: "A simple hydraulic circuit"
    alt: "Oil leaves the reservoir and enters the pump. The pump makes flow. A relief valve returns oil to the tank when pressure is high. A control valve sends oil to a cylinder, which makes straight-line motion. Pressure comes from resistance, not from the pump itself."
source_ids: [tp14814e, tp2293-2016]
review_status: needs_expert_review
---

# Hydraulic systems: parts, pressure and safe handling

On a small commercial vessel hydraulics drive the steering, winches, cranes, thrusters, hatch covers and sometimes the
propeller clutch. They are popular because a small pump and a few hoses can deliver huge force. The SVMO syllabus asks
you to know the ==principle, the main components, the common faults and the safety precautions==.

{{figure:svmo-hydraulic-circuit}}

## The principle

Liquids do not compress in any useful way. Push on oil in a closed space and the push is passed on at once, and the
pressure is the same everywhere in that space. Force depends on pressure and on the area it acts on:

> ==force = pressure x area==

Worked example. A hydraulic cylinder has a piston area of 0.005 square metres (50 square centimetres). The system
pressure is 10 megapascals (10 MPa, which is 100 bar). The force on the piston is 10 000 000 pascals multiplied by
0.005 square metres, which is ==50 000 newtons==, or about 5 tonnes-force. A small pump has delivered a large push.
A bigger piston gives a bigger force at the same pressure, but needs more oil to move the same distance, so it moves
more slowly.

## Pressure comes from resistance

A pump moves oil. It does not create pressure on its own. Pressure appears when the oil meets resistance, such as a load
on a cylinder. If a cylinder reaches the end of its stroke, the oil has nowhere to go and pressure rises sharply. That
is why every circuit needs a ==relief valve==.

## Main components

| Part | Job |
|---|---|
| Reservoir (tank) | Holds the oil, lets air and dirt separate, and cools it a little. Has a breather, filler cap and level gauge. |
| Pump | Pushes oil round the circuit. Gear, vane and piston types are common. |
| ==Relief valve== | Opens at a set pressure and returns oil to the tank, protecting pump, hoses and seals. |
| Directional control valve | Directs oil to one side of the actuator or the other, or to neutral. Hand, solenoid or pilot operated. |
| ==Actuator== | The part that does the work. A cylinder (ram) pushes or pulls in a straight line. A hydraulic motor turns. |
| Filters and strainers | Remove particles from the oil. Return-line, pressure-line and suction strainers are used. |
| Cooler | Removes heat, using sea water, fresh water or air. |
| Hoses and pipes | Carry the oil. Hoses are rated for pressure and have a life limit. |
| ==Accumulator== | A pressure vessel, often with a gas-filled bladder, that stores oil under pressure for surges or emergency movements. |

Oil moves in a loop: from the tank through the pump, past the control valve to the actuator, then back through the
filter and cooler to the tank. When the control valve is in neutral, an open-centre system lets the pump's flow
return to the tank at low pressure.

## Cleanliness comes first

Pumps, valves and cylinders are built to very fine clearances. ==Dirt is the main cause of hydraulic failure==: it
scores surfaces, sticks valve spools and blocks small passages. So:

- Keep the reservoir cap and breather in good order, and wipe a fitting clean before you open it.
- Use only oil of the grade the maker specifies, from a clean, sealed container. Do not mix it with engine oil.
- Change filters on time, and watch any clogging indicator.
- Cap hoses and ports when a line is open.

## Faults and what they mean

**Air in the oil.** Air is compressible, so the system becomes ==spongy, noisy and erratic==, and the oil can look
frothy or milky in the tank. Air enters through a low oil level, a loose suction fitting, a worn shaft seal or after a
repair. Bleed the system by the maker's method and find the way in.

**Overheating.** Causes include a low oil level, a clogged cooler, a worn pump or leaking relief valve (oil passing
through a gap turns pressure into heat), and a long run at relief pressure. Hot oil thins, loses its film and ages
fast. Hot hoses and casings also burn the skin.

**Low level.** Oil does not get used up. A falling level means a leak, so ==find and stop the leak before topping up==.
Oil in the bilge is both a pollution and a fire problem.

**Slow or weak movement.** Worn pump, internal leakage in a cylinder or valve, a blocked filter, a relief valve set too
low or sticking, or thick cold oil.

**Noise.** Whine or rattle often means cavitation (air or starved suction), a blocked suction strainer or a failing pump.

## Working safely

Hydraulic oil at high pressure is dangerous.

- ==Never use your hand to look for a leak.== A pinhole jet can penetrate the skin and carry oil into the tissue, and
  the wound can look harmless. It is a medical emergency. Pass a piece of cardboard near the suspect area, with the
  system shut down and depressurised where you can, and wear eye protection.
- Before you disconnect a hose, stop the pump, isolate the supply and lock it out, then ==relieve the pressure==.
  Operate the control valve to release trapped pressure if the maker says so.
- An ==accumulator holds pressure== after the pump stops. Follow the maker's procedure to discharge it, and never weld
  or cut one.
- Load on a crane or winch can drop when you open a line. Land the load or support it first.
- Inspect hoses for chafing, bulges, kinks and leaking fittings. Replace a damaged hose with the right pressure rating.
- Do not fix a leak with a clamp or tape while the system is pressurised.
- Wipe spills at once and keep oil out of the water. Report any spill into the sea.

## A BC engine-room example

You run a small tug working in the Fraser River. Before leaving the dock you notice the winch hydraulic reservoir sight
glass is well down and the deck beside the winch is oily. You do not top up and carry on. You stop the pump, lock it
out, find the weeping hose on the lift side, replace it and then fill the reservoir with the specified oil and cycle the
winch to bleed it. Only then is the winch released for work.

## Common mistakes

- Topping up a low reservoir without finding the leak.
- Believing a pump creates pressure by itself.
- Opening a line while the system or an accumulator is still pressurised.
- Checking a leak with a bare hand.
- Using engine oil because it is handy.
- Changing the relief valve setting to "get more power".

## Check yourself

1. A cylinder of area 0.002 square metres works at 8 MPa. What force does it produce?
2. What is the job of the relief valve?
3. Why should you never feel for a hydraulic leak with your hand?
4. Name three effects of air in a hydraulic system.
5. Why does an accumulator need special care?

---

*Study aid, not an operating manual. This lesson is written in original words from the syllabus in TP 2293 Chapter 33
and the SVMO study guide TP 14814E. Follow the maker's manual and your company's procedures.*
