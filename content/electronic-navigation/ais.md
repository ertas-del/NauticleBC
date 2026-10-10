---
id: electronic-navigation.ais
title: "AIS"
module: electronic-navigation
tickets: [master-limited-60gt]
order: 2
summary: "What AIS transmits and how, the Canadian carriage thresholds in NSR s. 118 (who needs Class A, who can use Class A or B), the 30-minute rule, and the limits of AIS as a collision-avoidance tool."
objectives:
  - Describe what AIS broadcasts and on which frequencies.
  - Apply NSR s. 118 to decide whether a vessel needs Class A, Class A or B, or no AIS.
  - State the graphic display, heading connection and 30-minute operating rules.
  - Compare Class A and Class B in power and reporting.
  - Explain why AIS supports radar and look-out but does not replace them.
prerequisites: [radar.arpa-sart-racon, colregs.rule-05.lookout]
estimated_minutes: 15
key_facts:
  - Class A AIS is required on vessels of 20 m or more (other than pleasure craft), vessels carrying more than 50 passengers, vessels carrying IMDG Code goods, vessels carrying pollutants in bulk, dredges or floating plants that are collision hazards, and towboats of 8 m or more (NSR s. 118(1)).
  - A vessel not covered by s. 118(1), on a voyage other than a sheltered waters voyage, must have AIS Class A or Class B if it is a passenger vessel or is 8 m or more and carries a passenger (NSR s. 118(2)).
  - A passenger vessel in the NSR is a vessel that carries more than 12 passengers.
  - Vessels under s. 118(1) must have a means of displaying graphically the relative ranges and bearings received by the AIS (s. 118(3)).
  - If a Class A AIS is fitted with a transmitting heading device or gyro-compass, they must be connected so that AIS transmits heading (s. 118(4)).
  - The AIS must be kept in operation for at least 30 minutes immediately before departure and for the entire voyage (s. 118(5)).
  - AIS transmits static data such as name, MMSI and dimensions, dynamic data such as position, course over ground and time, and voyage data such as destination (voyage data on Class A).
  - AIS works on two VHF channels, 161.975 MHz (87B) and 162.025 MHz (88B).
  - Class A transmits at about 12.5 W and reports every few seconds; Class B transmits at about 2 W and reports about every 30 seconds.
  - AIS only shows vessels that carry it, switch it on and send correct data, and it is limited by VHF line of sight, so it supports but never replaces the look-out and radar.
source_ids: [renav-nsr-2020-xml, renav-nato-ais-overview, renav-digitalyacht-ais-guide, renav-nautinst-jamming-spoofing, renav-crc1416-xml]
review_status: needs_expert_review
---

# AIS

**Study aid only.** Carriage thresholds change. The text of the Navigation Safety Regulations, 2020 is the authority.
Read the current regulation before relying on the figures here.

## What you need to know

The Automatic Identification System (AIS) is a VHF broadcast system in which ships send their identity, position and movement
to each other and to shore stations. Fitted with a display, it tells you the name, size, course and speed of nearby ships
and works out CPA and TCPA, whether or not you can see them or whether they are showing on your radar. It began as a
collision-avoidance tool and is now also used for traffic management, search and rescue and tracking.

## What AIS sends

A unit broadcasts three kinds of data:

- **Static:** vessel name, MMSI number, call sign, length and beam, ship type and where the position antenna sits. It is sent
  about every 6 minutes or on request.
- **Dynamic:** position (with an accuracy flag), time, course over ground and speed, and heading when a heading device
  is connected. This is sent often, and how often depends on speed.
- **Voyage:** destination, ETA, draught and similar items, which are entered by hand. Voyage data is sent by Class A only.

The signals travel on two VHF channels, ==161.975 MHz (channel 87B)== and ==162.025 MHz (channel 88B)==. They are line-of-sight
signals like the rest of VHF, which makes the range depend on antenna height and power. The position comes from the
vessel's own GNSS receiver, so a transponder needs a position source to work.

## Class A and Class B

| | Class A | Class B |
|---|---|---|
| Typical power | About 12.5 W | About 2 W |
| Reporting | Every few seconds when underway | About every 30 seconds |
| Typical range | About 20 to 25 nm | About 7 to 8 nm |
| Voyage data | Yes | No |
| Time slot | Guaranteed | Not guaranteed |

Treat the figures as typical. They come from a manufacturer's guide, and range changes with antenna height and conditions.

## Who must carry AIS in Canada (NSR s. 118)

**Class A** is required for any of these:

1. a vessel of ==20 m or more in length==, other than a pleasure craft;
2. a vessel carrying ==more than 50 passengers==;
3. a vessel carrying goods covered by the ==IMDG Code==;
4. a vessel carrying ==pollutants in bulk==;
5. a dredge or floating plant in a place where it is a collision hazard for other vessels;
6. a ==towboat of 8 m or more==.

**Class A or Class B** is required for any other vessel on a voyage ==other than a sheltered waters voyage== if it is a
==passenger vessel== (carrying more than 12 passengers) or if it is ==8 m or more in length and carries a passenger==.

Other rules in s. 118 apply to equipment and use:

- Vessels under s. 118(1) must have a graphic display of the ranges and bearings of AIS targets.
- If a Class A is fitted with a transmitting heading device or gyro-compass, they must be connected so the AIS sends heading.
- The AIS must be in operation for ==at least 30 minutes immediately before departure== and for ==the whole voyage==.
- Some exceptions apply to the 30-minute rule (for example international rules protecting navigational information, and certain
  government vessels).

Also, any AIS fitted on a vessel the Part applies to must be type approved (s. 101(1)).

## Worked example: which AIS?

These are invented vessels, all Canadian, all on the BC coast. Use the list above.

| Vessel | Result and reason |
|---|---|
| 24 m work boat, no passengers | **Class A**: 20 m or more and not a pleasure craft (s. 118(1)(a)). |
| 12 m tug moving a log tow | **Class A**: towboat of 8 m or more (s. 118(1)(f)). |
| 9 m water taxi, 6 passengers, near coastal Class 2 voyage | **Class A or B**: 8 m or more, carries a passenger, not a sheltered-waters voyage (s. 118(2)(b)). |
| The same taxi on a sheltered waters voyage | **Not required** under s. 118: not in (1), and (2) applies only outside sheltered waters. |
| 14 m fishing boat, no passengers, not towing | **Not required** under s. 118. |
| 22 m pleasure craft | **Not required**: the 20 m rule excludes pleasure craft. |
| 32 m ferry with 80 passengers | **Class A**: more than 50 passengers (and 20 m or more). |

## Using AIS well

AIS is a bonus on top of radar. It is good at identification, since you get a name to call on VHF. It sees round
corners and beyond radar's line of sight, and it gives a vessel's course over ground and, when her heading device is connected, her heading too.
The best picture comes from AIS and radar together. When both show a contact, you trust it more. When only AIS shows a
contact, find out why the radar does not.

## The limits

- **Not everyone has it.** Small craft, many fishing boats and recreational boats may have none, and exemptions exist.
- **It can be off or wrong.** AIS can be turned off in some situations, voyage data is typed in by hand, and the position is only as
  good as the other ship's GNSS. If her GNSS is wrong or spoofed, her AIS position is wrong.
- **It is line of sight.** A low antenna or hills can block the signal. Class B has a smaller range.
- **Class B might not get a slot.** Class B transmissions are not guaranteed a time slot.
- **It does not show intention.** It shows where she is and how she moves, not what she will do next.
- **It is not a substitute for Rule 5.** A proper look-out must still be kept by sight, by hearing and by all available means.

## BC example (invented)

Dawn in Juan de Fuca Strait. A 14 m tug sees a northbound target on the AIS display: bulk carrier, 190 m, 14 knots,
CPA 0.4 nm, TCPA 18 minutes. The mate calls her on VHF by name and learns she will alter to starboard in ten minutes.
Meanwhile the mate keeps watching radar and the horizon, because the AIS shows no sign of the small fishing boat
that sits on the same track, and the fishing boat shows on radar and not on AIS.

## Common mistakes

- Memorising "AIS at 300 GT". That is the SOLAS rule. In Canada the 20 m, 8 m and passenger rules in s. 118 matter here.
- Forgetting the 30-minute rule: on 30 minutes before departure, not on once you are clear of the dock.
- Treating a vessel with no AIS target as no vessel.
- Believing AIS CPA is more reliable than a radar plot. It depends on the other vessel's data.
- Mixing up Class A or B (passenger rule) and Class A only (the s. 118(1) list).

## Check yourself

1. A 10 m towboat. Which AIS class does it need?
2. A 15 m vessel carrying 4 passengers on an exposed coast voyage. Which AIS rule applies?
3. How long before departure must the AIS be on?
4. Name two kinds of data AIS broadcasts.
5. Give two reasons AIS cannot replace radar and a look-out.

---

*Study aid. Paraphrased from the Navigation Safety Regulations, 2020 (read from the Justice Canada XML) and from NATO
and manufacturer AIS guides read through summaries. It is not the official text.*
