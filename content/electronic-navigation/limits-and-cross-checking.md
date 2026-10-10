---
id: electronic-navigation.limits-and-cross-checking
title: "Limits of electronic aids and cross-checking"
module: electronic-navigation
tickets: [master-limited-60gt]
order: 5
summary: "Why every electronic aid can mislead, the common failure patterns (settings, datum, overscale, muted alarms, spoofing), a table of quick cross-checks, how to weigh disagreeing sources, and the legal duties when required equipment fails."
objectives:
  - Explain why electronic aids support, and do not replace, a proper look-out and traditional navigation.
  - List the common ways each aid can mislead and a quick cross-check for each.
  - Decide which of several disagreeing sources to trust and what to do next.
  - State the NSR duties when required equipment is not working.
  - Describe the lessons of a published electronic-chart grounding.
prerequisites: [electronic-navigation.ecs-ecdis-and-apps, electronic-navigation.plotters-and-integration, radar.position-fixing]
estimated_minutes: 14
key_facts:
  - Rule 5 requires a look-out by sight and hearing and by all available means, and Rule 7(a) requires all available means to be used to decide whether risk of collision exists, so electronic aids add to the look-out and never replace it.
  - Required equipment must be kept in effective operating condition, and if it stops working the master must restore it as soon as possible (NSR s. 5(2) and 5(3)).
  - If repair facilities are not available where the vessel is, the master must plan and carry out a safe voyage to a port where they are (NSR s. 5(4)).
  - A vessel must not begin a voyage without the equipment required by the NSR, except under force majeure or to save life or property (NSR s. 5(1)).
  - Electronic chart monitoring improves situational awareness but does not replace it, and a prudent navigator does not rely on any single aid.
  - GNSS can give a hazardously wrong position without any alarm, so a mismatch between the plotter position and radar or visual bearings is a warning.
  - A safety contour shallower than the vessel's draught plus margin, a disconnected audible alarm and a display that cannot be seen from the watch position are all ways an electronic chart can fail to warn.
  - In the published bulk-carrier case, the watchkeeper did not check the ship's position for more than 15 minutes after a series of course changes and missed the visual grounding alarm.
  - Charts and publications required under the NSR must be corrected from Notices to Mariners and navigational warnings before being used to plan and execute a voyage (NSR s. 146).
  - The master must display the route on a chart referred to in s. 142(1)(a) and plot and monitor the vessel's position throughout the voyage on that chart (NSR s. 144(3)).
source_ids: [renav-nsr-2020-xml, renav-crc1416-xml, renav-bowditch-2017-ch5-ecdis, renav-nautinst-jamming-spoofing, renav-nautinst-ecdis-grounding, renav-gps-gov-accuracy, renav-tp10936-html]
review_status: needs_expert_review
---

# Limits of electronic aids and cross-checking

**Study aid only.** This lesson is about judgement. It does not replace Transport Canada rules, the master's standing orders
or your training.

## What you need to know

Electronic aids are accurate, quick and tireless, and that is exactly the danger. A screen that is usually right teaches
the watchkeeper to stop checking. The published failures are rarely a broken box. They are a working box set up wrongly,
believed too much, or left unwatched. The cure is not to distrust electronics but to give each aid a second opinion from a
source that fails in a different way.

## The legal frame

The rules point one way. Rule 5 asks for a look-out by sight and hearing ==and by all available means==, and Rule 7(a)
asks for ==all available means== to determine if risk of collision exists. Electronic aids are some of those means. They
are never a substitute for the others. The Navigation Safety Regulations, 2020 add three duties about equipment:

- Required equipment must be installed, tested and maintained so it is in ==effective operating condition== (s. 5(2)), and if it
  stops, the master must ==restore it as soon as possible== (s. 5(3)).
- If repair facilities are not readily available where the vessel is, the master must ==plan and carry out a safe voyage== to a
  port where they are, bearing in mind the defect (s. 5(4)).
- The vessel must not engage in a voyage unless fitted with the required equipment, except ==under force majeure or to save life
  or property== (s. 5(1)).

On charts, s. 146 says required charts must be ==corrected from Notices to Mariners and navigational warnings== before being used
to plan and execute a voyage, and s. 144(3) says the master must display the route and ==plot and monitor the vessel's
position== throughout the voyage on a chart referred to in s. 142(1)(a). Check with your instructor how these interact with
the under-100 GT exception in s. 142(3).

## Where each aid fails, and the cross-check

| Aid | Typical way it misleads | Quick independent check |
|---|---|---|
| GNSS | Blocked or reflected signals, datum mismatch, jamming, spoofing, no alarm | Radar range to a mark; visual bearing; depth against the chart |
| Plotter or ECDIS | Wrong safety settings, overscale, old chart, alarm off, wrong datum | Paper chart or a second chart source; depth; radar overlay |
| Radar | Clutter controls hide small targets, false echoes, shadow sectors | Look-out; AIS; move the range scale; switch off over-used clutter controls |
| ARPA or tracking | Wrong heading or speed input, lost target | Hand plot of a key target; VRM/EBL on the echo |
| AIS | Vessels without it, wrong inputs, line of sight | Radar and eyes |
| Echo sounder | Wrong offset, wrong setting or wrong units | Chart depths, allowing for the tide |
| Autopilot | Wrong mode, undetected off-course, helmsman not ready | Compass, rudder-angle indicator, qualified helmsman standing by |

## What the published failures look like

A case study from a bulk carrier shows the typical pattern. The ECDIS safety contour was set at 10 m on a ship drawing 10.63
m, so the system could treat 10.13 m of water as safe. The anti-grounding audible alarm had been disconnected, and no one
on board knew. The screen sat where the watchkeeper had to turn away from the view ahead to read it. Officers had generic
rather than equipment-specific training. The watchkeeper did not check position for more than 15 minutes after course
changes, and missed a visual alarm. The lessons are set out plainly: verify safety settings against draught and route,
test alarms, place displays where they can be seen, give equipment-specific training, and do not let continuous
electronic monitoring breed complacency. None of these needs a bigger ship than a small BC vessel.

## Weighing disagreeing sources

Worked example (invented). Passing a headland at night, a tug has three sources of information.

| Source | Says |
|---|---|
| Plotter (GNSS) | 0.80 nm off the headland |
| Radar, VRM on the headland | 0.52 nm off |
| Echo sounder | 14 m, where the chart shows 31 m at the plotter position |

1. The difference between the plotter and radar is 0.80 − 0.52 = 0.28 nm, about 520 m.
2. Radar and the sounder fail in different ways, yet both say the boat is closer to the shore than the plotter does.
3. So suspect the plotter position (a datum, receiver or interference problem) and ==believe the two independent sources==
   until you find out otherwise.
4. Act: slow down, alter away from the shore towards deeper water, tell the master, and then look for the cause.

The rule of thumb is: if two independent sources agree and a third does not, suspect the third. But do not stop thinking
there. Two sources can share a hidden error, such as a wrong heading feeding both an overlay and a tracker. Keep looking.

## Good habits

- **Fix by more than one method** and record the fix times in the log.
- **Look at the data-source status** at the start of the watch and after any power event.
- **Set alarms to suit the water** and test them. Never silence one without a reason and a note.
- **Place a position check on the clock** whenever you alter course, as the grounding case suggests.
- **Know your equipment**: generic training does not teach you your own set's safety features.
- **Practise the old way** with chart, compass, bearings and depth. If the electronics fail, that is what you will use.

## BC example (invented)

Approaching Seymour Narrows with a flood tide, a 16 m tug's GNSS drops out for eleven seconds as a cliff blocks the
sky, then returns with the plotter position jumped 0.2 nm sideways. The mate is not alarmed by the lack of an alarm.
She takes a radar range and a bearing to a prominent point, sees it agrees with the DR position and not the
plotter, and calls the master. The receiver is restarted. The tug had never been out of the channel.

## Common mistakes

- Treating the absence of an alarm as proof of safety.
- Trusting the aid you used last time.
- Leaving default safety settings or alarm limits unchanged.
- Checking one source against itself, for example comparing two displays fed by the same GNSS receiver.
- Letting the screen hold the whole watch's attention while the outside goes unwatched.
- Using out-of-date charts because the electronic chart looks fine.

## Check yourself

1. Which rules say all available means must be used, and what do they mean for electronic aids?
2. What must the master do when required equipment stops working?
3. Plotter, radar and sounder disagree, and the radar and sounder agree. Which one do you suspect?
4. Name three ways an ECDIS or plotter can fail to warn of danger.
5. What does s. 146 require of charts before a voyage?

---

*Study aid. Paraphrased from the Navigation Safety Regulations, 2020 and Collision Regulations (read from the Justice
Canada XML), GPS.gov, Nautical Institute publications and Bowditch read through summaries. The scenarios are invented.
It is not the official text.*
