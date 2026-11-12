---
id: stability.watertight-integrity-and-freeboard
title: "Freeboard, watertight and weathertight"
module: stability
tickets: [master-limited-60gt, fishing-master-4]
order: 80
summary: Why freeboard and closed openings matter. Covers watertight versus weathertight, downflooding, the angle at which the deck edge or an opening goes under, the master's duty about load lines, and the habits that keep a vessel's integrity.
objectives:
  - Tell watertight from weathertight, and say what downflooding is.
  - Calculate the angle at which the deck edge goes under for a box-shaped hull, and how it changes with loading.
  - State the master's duty about load lines and the Small Vessel Regulations on watertight integrity.
  - List the closures, openings and drains a crew must keep right.
prerequisites: [stability.free-surface]
estimated_minutes: 22
key_facts:
  - Watertight means water cannot pass in either direction under a head of water; weathertight means water will not get in under any sea condition, but it may not hold under pressure.
  - Downflooding is the entry of sea water through an opening into the hull or superstructure of an undamaged vessel.
  - The downflooding angle is the heel at which the first opening that cannot be closed weathertight goes under water.
  - For a box-shaped hull the deck edge goes under when tan(angle) = freeboard / half the beam; a smaller freeboard means a smaller angle.
  - Loading the vessel deeper reduces both freeboard and reserve buoyancy, and reduces the range of stability.
  - The master must ensure the applicable load lines are not submerged (Canada Shipping Act, 2001, s. 110(2)); the penalty is a fine up to $500,000 for each centimetre or part of a centimetre submerged, or imprisonment up to 18 months, or both (s. 122).
  - The Small Vessel Regulations require a non-pleasure vessel's design to maintain watertight integrity and prevent downflooding (s. 714), and the owner must inform the Minister of a major modification that affects watertight integrity or stability (s. 710).
  - Doors, hatches, ports and vents that open to the weather must be kept closed at sea, and freeing ports and scuppers kept clear.
source_ids: [stab-ecfr-46cfr28-510, stab-csa-2001-ss110-112, stab-svr-sor-2010-91-stability, stab-fao-ftp517-ch6-example]
review_status: needs_expert_review
---

# Freeboard, watertight and weathertight

**Study aid only. Invented numbers.** Maximum loading and closure rules for your vessel come from its own documents and the master.

## What you need to know

A vessel's stability depends on the hull shape staying intact as it heels. Once water gets in, or the deck edge goes under, the vessel loses the buoyancy it was counting on and the GZ curve collapses. This lesson is about keeping the hull shape: freeboard, tight closures and clear drains.

## Watertight and weathertight

Two words that are often mixed up:

- ==Watertight==: built so that water cannot pass through it in either direction when it is under a head of water. A watertight bulkhead or a watertight door that is dogged shut is meant to keep flooding inside one compartment.
- ==Weathertight==: built so that, in any sea conditions, water will not get through. A weathertight hatch or door sheds spray and green water, but need not hold under sustained pressure.

Weathertight is the standard for openings on deck. Watertight is the standard for compartment boundaries below.

## Downflooding

==Downflooding== is the entry of sea water, through an opening, into the hull or superstructure of an undamaged vessel. Take an engine-room door that is left open, a vent without a closing flap, or an air pipe cut too low. If the vessel heels far enough that one goes under, water pours in. The vessel gets heavier, it sinks lower, free surface appears in the engine room and the stability falls with it.

The ==downflooding angle== is the angle of heel at which the first opening that cannot be closed weathertight goes under water. Beyond that angle the GZ curve is of no use, because the vessel is no longer a closed hull. Openings you leave open, or cannot close, set the downflooding angle.

## Freeboard and the deck edge

You know freeboard: the height from the waterline up to the deck edge. As a vessel heels, the low-side deck edge comes closer to the water. For a box-shaped hull the deck edge enters the water when:

==tan(angle) = freeboard / (half the beam)==

### Worked example 1: the Tern's deck edge

Example Tern (invented): beam 3.5 m, so half beam 1.75 m.

| Condition | Freeboard (m) | tan | Angle |
|---|---|---|---|
| Light | 0.45 | 0.45 / 1.75 = 0.257 | **14.4 degrees** |
| Loaded | 0.35 | 0.35 / 1.75 = 0.200 | **11.3 degrees** |
| Overloaded | 0.25 | 0.25 / 1.75 = 0.143 | **8.1 degrees** |

Each 0.1 m of freeboard lost takes about 3 degrees off the angle. This matters because the range over which the vessel can fight back is shorter, and because green water over the deck edge becomes a wide free surface.

### Worked example 2: how much does extra load cost?

The Tern at 0.45 m freeboard (light) takes on 8 t. TPC is 0.4305 t/cm, so it sinks 8 / 0.4305 = 18.6 cm. Freeboard falls to 0.45 - 0.186 = 0.264 m. The deck-edge angle falls from 14.4 degrees to atan(0.264 / 1.75) = **8.6 degrees**. About 40 percent of the angle is gone, from 8 t aboard.

### Worked example 3: an opening above the deck

An engine-room door has its sill 0.30 m above the deck, and 1.40 m from the centreline. With 0.35 m of freeboard, the sill is 0.35 + 0.30 = 0.65 m above the waterline, and it is wet when tan(angle) = 0.65 / 1.40 = 0.464, an angle of **24.9 degrees**. If the door were left open, this would be the downflooding angle; shut and weathertight, it is no longer a downflooding point. The numbers use a plain box, so a real vessel needs her own data.

## Reserve buoyancy

The enclosed, tight volume of hull above the waterline is the ==reserve buoyancy==. It is the vessel's cushion against being pushed down by waves, spray, ice and extra cargo. The more you load, the less there is. A closed hatch is part of it; an open one is a hole in it.

## The law

The master has a legal duty on loading. Under the Canada Shipping Act, 2001, s. 110(2), the master must ensure that ==the applicable load lines are not submerged==. The offence is serious: s. 122 allows a fine of up to ==$500,000 for each centimetre or part of a centimetre== of load line that is under water, or up to 18 months in prison, or both. Many small vessels have no load line at all; for those, the limit on loading comes from the vessel's stability documents and builder's limits. Check what applies to your vessel.

For smaller vessels the Small Vessel Regulations (SOR/2010-91) require a vessel other than a pleasure craft to be designed to ==maintain watertight integrity and prevent downflooding== (s. 714) and to have adequate stability for its intended operations (s. 716). The owner must tell the Minister about a major modification, which includes a change that affects watertight integrity or stability (s. 710). Cutting a new opening in the cabin side, or enlarging a hatch, may be one.

## The habits

- ==Keep doors, hatches, ports and vents closed at sea== that open to the weather, and dogged properly, not just pulled to. A door in constant use is the likely downflooding point.
- Keep ==freeing ports and scuppers== clear, and the flaps free. Never block them with gear, never lash them shut.
- Check seacocks, sounding pipes and caps, vent heads and hull fittings before leaving. A fitting that fails below the waterline is a flooding hazard however good the stability.
- Pump bilges early, because water in the bilge has a free surface and weight.
- Report loose or damaged closures and gaskets to the master.
- Do not overload, and keep the freeboard that the vessel was designed for. A load that "looks fine" at the dock leaves less margin in a seaway, where the vessel rides lower and rolls.

## BC picture

A small workboat crosses the Strait of Georgia with the cabin door hooked back to ventilate the engine space. A wave over the quarter and a roll to 25 degrees put the sill under water, and the engine room fills as she lies over. All the stability numbers on paper were for a closed hull. The door was the downflooding point.

## Exam traps

Watertight and weathertight are not the same. Freeboard is measured to the deck edge, not the cabin roof. Smaller freeboard means a smaller deck-edge angle, not a larger one. The load line duty is in the Canada Shipping Act, 2001, and the master carries it. The Small Vessel Regulations section on watertight integrity applies to vessels other than pleasure craft. And an opening that is closed weathertight is not a downflooding point; an open one is.

## Check yourself

1. Beam 4.0 m, freeboard 0.40 m. At what angle does the deck edge go under? (tan = 0.40 / 2.0 = 0.2; 11.3 degrees.)
2. What is the downflooding angle?
3. Who has the legal duty not to submerge the applicable load lines, and what is the maximum fine per centimetre?
4. Name three things a deckhand can do to keep a vessel's watertight integrity.
