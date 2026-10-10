---
id: electronic-navigation.plotters-and-integration
title: "Chart plotters, TimeZero-type software and bridge integration"
module: electronic-navigation
tickets: [master-limited-60gt]
order: 4
summary: "How a chart plotter or navigation software combines GNSS, heading, radar, AIS and depth, how radar overlay and ARPA targets depend on those inputs, what the NSR say about integrated systems, echo sounders and autopilots, and how alarm settings behave."
objectives:
  - Name the sensors a plotter or navigation program depends on and what each one contributes.
  - Explain why radar overlay and tracked targets need a heading input and what a misaligned overlay tells you.
  - State the NSR rules on integrated bridge systems, the echo sounder and heading or track control systems.
  - Predict when a CPA and TCPA alarm will actually sound.
  - Apply the "check the inputs" habit when something on the screen looks wrong.
prerequisites: [electronic-navigation.gnss, electronic-navigation.ais, radar.arpa-sart-racon]
estimated_minutes: 15
key_facts:
  - "A plotter or navigation program only displays what its inputs give it: GNSS for position, a heading sensor for direction, radar for echoes and tracked targets, AIS for other vessels, and a sounder for depth."
  - ARPA-type target tracking in chart software needs a connected radar and a heading sensor, and a radar overlay likewise depends on a good heading.
  - In chart software a target is classed as dangerous only when both the CPA limit and the TCPA limit are crossed, and acquisition can show as a dashed circle for a few radar revolutions.
  - A radar overlay that does not line up with the charted shoreline is a warning of a heading, position or chart error, and the sources should be cross-checked.
  - If equipment offers alternative modes of operation, the mode being used must be indicated (NSR s. 129).
  - A failure of any subsystem of an integrated bridge system must be brought to the attention of the person in charge of the deck watch by audible and visual alarms and must not cause another subsystem to fail (NSR s. 130).
  - If one part of an integrated navigation system fails, it must be possible to operate every other individual piece of equipment or part separately (NSR s. 131).
  - A vessel of 300 GT or more must have an echo sounder to measure and display the available depth of water (NSR s. 110(a)); a towboat on a near coastal Class 1 voyage also needs one (s. 125(1)(b)).
  - When a heading or track control system is used in a high-traffic area, in restricted visibility or in any other hazardous situation, immediate changeover to manual steering must be possible and a qualified helmsman must be available at all times (NSR s. 133).
  - Manual steering must be tested before entering any area where navigation demands special caution and at least once a day (NSR s. 133(4)).
source_ids: [renav-nsr-2020-xml, renav-timezero-arpa-target, renav-timezero-radar-controls, renav-nautinst-jamming-spoofing, renav-bowditch-2017-ch5-ecdis]
review_status: needs_expert_review
---

# Chart plotters, TimeZero-type software and bridge integration

**Study aid only.** TimeZero is named as an example of the type of navigation software used on many small commercial boats.
Transport Canada does not examine any brand, and this lesson covers the ideas, not a particular set of menus.

## What you need to know

A modern plotter is not one instrument but a window onto several. The chart comes from one place, the position from
another, the heading from a third, the radar picture and the other ships from still others. When it works, the
picture is rich and easy. When one input goes wrong, the screen may still look perfect. The skill is knowing what each
input supplies, what happens when it fails, and how to notice.

## The inputs

| Input | What it gives the screen | If it is wrong or missing |
|---|---|---|
| GNSS receiver | Position, COG, SOG, time | Boat symbol in the wrong place; track, ETA and alarms wrong |
| Heading sensor (compass or gyro) | Which way the bow points | Overlay rotated; true vectors wrong; north-up picture wrong |
| Radar | Echoes, tracked targets | Nothing to overlay; ARPA targets missing or false |
| AIS | Other vessels' identity and movement | Missing ships; wrong data from their side |
| Echo sounder | Depth under the transducer | No depth cross-check |
| Speed log | Speed through the water | Wrong speed through the water; true vectors wrong |

Plotters connect to these by networks and cables. You do not need the wiring, but you do need to know which input is
failing when something looks wrong. Most programs show a data-source status page, so look at it at the start of the watch.

## Radar overlay and tracked targets

With ==radar overlay== the radar picture is laid on top of the chart. A programme can then show whether the echoes line up with
the charted shore, which is a powerful cross-check. To turn the radar picture so that it lines up with a north-up chart,
the software has to know which way the bow points. For that reason, ARPA-type tracking in this class of software needs ==both
a radar and a heading sensor==.

When you ask the software to track a target, it ==acquires== it (often shown as a dashed circle for a few radar revolutions),
then shows it as ==acquired== with a vector, and may mark it as ==lost== if the radar can no longer follow it. A target is
classed as ==dangerous== only when it crosses ==both== the CPA limit and the TCPA limit you have set; its icon then flashes
and an alarm sounds. The software warns that poor tuning gives false targets, and too little gain gives missed targets.

The Nautical Institute's advice on jamming and spoofing applies directly: in coastal waters, a mismatch between the
chart position and the radar overlay or visual bearings is a warning sign.

Worked example (invented). The radar overlay on a tug's plotter puts the headland echo 8 degrees away from the charted
point. At 2.0 nm range, an 8° error moves the echo sideways by 2.0 × tan 8° = 2.0 × 0.141 = **0.28 nm**, which is about
520 m. The mate does not just nudge the overlay until it lines up. She checks the heading sensor against the steering
compass, finds the sensor has a fixed offset, and logs it.

## How alarms behave: a worked example

Suppose the limits are CPA 0.5 nm and TCPA 10 minutes. A target 5.0 nm away is closing at a relative speed of 10 knots, with
a CPA of 0.4 nm.

1. Time to CPA now ≈ √(5.0² − 0.4²) ÷ 10 = 4.98 ÷ 10 = 0.498 h ≈ **30 minutes**.
2. CPA 0.4 nm is under the limit, but the TCPA of 30 min is not, so ==no alarm yet==: both limits must be crossed.
3. The alarm sounds when TCPA falls to 10 minutes: at 10 knots that is 10 ÷ 60 × 10 = 1.67 nm away.
4. So the alarm gives only about **10 minutes** of warning for a target closing at 10 knots, and a faster target gives less.

Alarms are an additional check, not the plan. Make your own plot, decide in good time, and set the limits to match the
water you are in, not the factory default.

## What the NSR say about integration

Division 5 of Part 1 of the Navigation Safety Regulations, 2020 applies to vessels not covered by the SOLAS-type Division
2. It covers the following:

- ==Modes of operation (s. 129):== if equipment fitted on or after 1 July 2002 offers alternative modes, the mode in use
  must be indicated.
- ==Integrated bridge systems (s. 130):== failure of any subsystem must be brought to the immediate attention of the person
  in charge of the deck watch by audible and visual alarms, and must not cause another subsystem to fail.
- ==Integrated navigation system failure (s. 131):== if one part fails, every other individual piece of equipment or part must still be
  usable separately.

In other words, a failed chart plotter should not take the radar, the compass or the VHF with it.

## Echo sounder

An echo sounder measures and displays the depth of water. The NSR require one on vessels of ==300 GT or more== (s. 110(a)),
and on a towboat on a ==near coastal Class 1== voyage (s. 125(1)(b)(i)). Even where it is not required, it is the cheapest
cross-check on a plotter. A practical caution: depending on the setting, a sounder may show depth below the transducer,
below the keel or from the waterline. Know which one yours shows, then compare it with the chart.

## Heading or track control (autopilot)

An autopilot is a helper, not a replacement for a helmsman. Under NSR s. 133, when a heading or track control system is
used in an area of ==high traffic density==, in ==restricted visibility== or in any other ==hazardous navigational situation==:

- there must be means for ==immediate changeover to manual steering==;
- the person in charge of the deck watch must make sure a ==qualified helmsman== is available at all times to take over;
- every changeover, either way, is made by or under the supervision of the person in charge of the deck watch;
- manual steering must be ==tested== before entering any area where navigation demands special caution, and at least
  ==once a day==.

The Bridge Watch lesson on autopilot changeover covers the practice.

## BC example (invented)

A 20 m tug heading up Johnstone Strait on a dark night has the plotter, radar overlay and AIS all on one screen. The mate
has set the CPA alarm to 0.5 nm and the TCPA alarm to 12 minutes. A cruise ship appears on the plotter at 6 nm, closing at a
relative speed of about 20 knots. The mate starts a plot at once and calls the master, who alters in good time. The alarm
set at 12 minutes TCPA would not have sounded until the ship was about 12 ÷ 60 × 20 = 4 nm away, only six minutes after she
first appeared. The mate had the plot; the alarm was the second line of defence.

## Common mistakes

- Treating the plotter as one instrument when it is five instruments in one box.
- Believing a perfect-looking screen means every input is healthy.
- Leaving alarm limits at the defaults.
- Silencing alarms permanently to reduce nuisance.
- Using the autopilot in a hazardous situation without a qualified helmsman ready.

## Check yourself

1. Why does a radar overlay need a heading sensor?
2. A CPA of 0.3 nm and a TCPA of 25 minutes with limits of 0.5 nm and 10 minutes. Will the alarm sound? Why?
3. What does NSR s. 130 require if one part of an integrated bridge system fails?
4. When must manual steering be tested?
5. What would a misaligned radar overlay make you check?

---

*Study aid. Paraphrased from the Navigation Safety Regulations, 2020 (read from the Justice Canada XML) and product and
institute documents read through summaries. The scenarios and numbers are invented. It is not the official text.*
