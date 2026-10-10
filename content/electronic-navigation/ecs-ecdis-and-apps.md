---
id: electronic-navigation.ecs-ecdis-and-apps
title: "Plotters, ECDIS, phones and tablets"
module: electronic-navigation
tickets: [master-limited-60gt]
order: 3
summary: "ENC, raster and paper charts, what makes an ECDIS different from a chart plotter (ECS), when an electronic chart counts as the carried chart under NSR s. 143, the under-100 GT exception, safety contour settings, and the limits of phones and tablets."
objectives:
  - Distinguish an ENC, a raster chart and a paper chart.
  - Distinguish an ECDIS from an electronic chart system (ECS) or an app.
  - State the conditions in NSR s. 143 for using an electronic chart as the required chart.
  - Explain why the safety contour and other settings need checking against the vessel's draught.
  - Say what CHS is doing with raster charts, and where phones and tablets fit.
prerequisites: [electronic-navigation.gnss, chartwork.charts-and-projections]
estimated_minutes: 15
key_facts:
  - An ENC is a vector electronic navigational chart database, standardised in content, structure and format, issued for use with ECDIS by or under the authority of a hydrographic office such as the Canadian Hydrographic Service (NSR s. 143(2)).
  - A raster navigational chart (RNC) is a picture of a printed chart made of pixels, with no underlying object data and no automatic alarms.
  - Under NSR s. 143, a required chart may be in electronic form only if it is displayed on an ECDIS that uses the ENC where one is available and meets IHO S-52 display standards.
  - An ECDIS used in raster (RCDS) mode must be used with other printed charts, and an ECDIS must have a backup arrangement or printed charts on which the position is being plotted for a safe takeover.
  - An ECDIS backup arrangement must be on main and emergency power, with an emergency source giving at least 30 minutes of uninterrupted power (NSR s. 143(1)(e)).
  - A chart plotter or electronic chart system (ECS) has less input, display and function than an ECDIS, and is not named in s. 143, so treat it as an aid and not as the chart the regulations require.
  - NSR s. 117 requires an ECDIS only on new Canadian passenger vessels of 500 GT or more and other new vessels of 3 000 GT or more, excluding cable ferries and pleasure crafts.
  - NSR s. 142(3) exempts a vessel under 100 GT from carrying the s. 142(1) charts if the person in charge has sufficient knowledge of the area; charts must be corrected from Notices to Mariners before use (s. 146).
  - The Canadian Hydrographic Service is discontinuing its digital raster charts (BSB) in stages, and keeps S-57 ENCs and paper charts for navigation; PDF charts are not for navigation.
  - The safety contour and safety depth are set by the mariner; a safety contour shallower than the vessel's draught plus margin lets the system show unsafe water as safe.
source_ids: [renav-nsr-2020-xml, renav-bowditch-2017-ch5-ecdis, renav-chs-rnc-discontinuation, renav-chs-enc-opendata, renav-nautinst-ecdis-grounding]
review_status: needs_expert_review
---

# Plotters, ECDIS, phones and tablets

**Study aid only.** Electronic charts shown in this lesson are not substitutes for the official charts, tide and current
tables and Notices to Mariners that the Navigation Safety Regulations, 2020 require. Where the law is unclear for your
vessel, ask Transport Canada or your instructor.

## What you need to know

Electronic charts come in several grades, and the words are easy to mix up. A paper chart is the traditional standard.
An ==ENC== is the official vector database that an ==ECDIS== reads. A ==raster chart== is a scanned-looking picture of a
paper chart. A ==chart plotter== or app (an ==electronic chart system, ECS==) shows charts and your position but is not an
ECDIS. Only the ECDIS route can, under conditions, take the place of the paper charts the regulations require.

## ENC, raster and paper

A paper chart is a printed picture, corrected by hand from Notices to Mariners.

A ==raster navigational chart (RNC)== is a digital copy of the paper chart, a grid of pixels. You can pan and zoom, but there
is no information behind a symbol, so the system cannot warn you that you are heading toward a rock.

An ==electronic navigational chart (ENC)== is a ==vector== database. Every feature (a depth area, a rock, a light, a
contour) is an object with attributes, in layers you can switch on or off. The NSR define it as a database that is
==standardised as to content, structure and format==, ==issued for use with an ECDIS== by or under the authority of the
Canadian Hydrographic Service or another government hydrographic office, and containing all the chart information necessary
for safe navigation. Because the system understands the objects, it can raise alarms, such as for crossing a safety
contour. The ENC is built to the IHO S-57 standard, and the CHS open-data ENC record lists a weekly update cycle (that open
service is itself marked not for navigation).

The Canadian Hydrographic Service is phasing out its digital raster charts (the BSB format) in stages, starting where
good ENC coverage already exists. It continues to offer ==S-57 ENCs and paper charts== for navigation, and PDF charts for
==non-navigation use only==. Check the current CHS notices for which charts have been cancelled.

## What the NSR say

Section 142(1)(a) requires a vessel to carry the reference catalogue and the applicable charts at the largest scale, from
the Canadian Hydrographic Service in Canadian waters. Section 143 then says when that chart may be electronic. It must be
displayed on an ==ECDIS== that:

1. uses the ==ENC== wherever one exists;
2. uses a raster chart that is a facsimile of an official chart only where there is no ENC, and in that raster mode is used
   together with other printed charts;
3. meets the ==IHO S-52== standard for chart content and display; and
4. has either an ==ECDIS backup arrangement== (meeting the IMO standard, connected to main and emergency power, with
   ==at least 30 minutes of emergency power==) or ==printed charts== on which the position is plotted often enough for a safe
   takeover if the ECDIS fails.

Two other rules frame this. Section 117 requires an ECDIS only on new vessels: passenger vessels of 500 GT or more and other
vessels of 3 000 GT or more, constructed after the section came into force, not including cable ferries and pleasure crafts.
That is far above Master Limited size. And ==section 142(3)== says that a vessel of ==less than 100 GT== does not have to
carry the s. 142(1) charts and publications if the person in charge has enough knowledge of the shipping routes, lights, buoys,
marks and hazards of the area, and of its tides, currents, ice and weather. Whether and how that exception interacts with the
plotting rule in s. 144(3) is a question for your instructor or Transport Canada. Section 146 says charts and publications
that are required must be corrected from Notices to Mariners and navigational warnings before they are used.

## Plotters and apps: ECS

A chart plotter on a small boat, a laptop running navigation software, a phone or tablet app: all are electronic chart
systems. The ECS lacks some of the input, display and function of an ECDIS, and the regulations name the ECDIS, not the ECS,
as the electronic route to a legal chart. The practical reading is to ==treat a plotter or app as a navigation aid that must
be cross-checked==. That reading is our inference from s. 143, not a quotation, and the point is flagged for expert review.

Common limits of plotters and apps:

- **Source and age of the chart.** Where did the data come from, and how old is it? An unofficial or old chart can be wrong.
- **Overscale.** Zooming in past the accuracy of the underlying data makes the boat look as if it can pass between two
  rocks that the survey could never resolve.
- **Display clutter.** Settings decide what is shown. Information can be hidden at the scale you are looking at.
- **Datum.** If the position and the chart use different datums, the boat appears in the wrong place.
- **Power and weather.** Phones and tablets run out of battery, overheat, wash out in sun, fog in damp air and lose GPS
  under cover. They can help with planning and as a cross-check, but poor as the only instrument.

## Safety settings

The mariner chooses a ==safety contour== and ==safety depth==, and the system uses them to colour the water and to raise
alarms. If they are set badly, the system will cheerfully show unsafe water as safe. A published case study of a bulk
carrier grounding found a 10 m safety contour on a ship drawing 10.63 m, an audible alarm that had been disconnected, and
a display the watchkeeper could not easily see.

Worked example (invented numbers). A tug draws 2.8 m. The chart has contours at 2 m, 5 m and 10 m. The skipper wants 1.0 m
of margin, so he needs water of at least 2.8 + 1.0 = **3.8 m**.

1. Choose the next contour deeper than 3.8 m: the **5 m** contour.
2. Setting the safety contour at 2 m would let areas charted at 2.5 m, which a 2.8 m draught cannot enter, show as safe.
3. Setting it at 5 m shows everything shoaler than 5 m as unsafe, and any route crossing that line triggers an alarm.
4. Test the alarm once at the dock to confirm that it works.

## BC example (invented)

A skipper takes a 9 m aluminum boat through Active Pass using only a phone app set to its smallest scale. The icon crosses a
patch of land near the pass entrance. He believes the app. A more cautious mate zooms out, sees the chart's source note,
compares with the paper chart and the radar, and finds that the chart is overscale and its detail is poor. The skipper
now treats the phone as an aid and keeps the paper chart folded open on the table.

## Common mistakes

- Saying "I have electronic charts, so I don't need paper charts." Only an ECDIS meeting s. 143 counts, and small vessels
  have the s. 142(3) exception, not a plotter exception.
- Using raster mode and forgetting the printed charts that must go with it.
- Never changing the default safety contour.
- Trusting a zoomed-in display beyond the chart's real accuracy.
- Leaving a phone as the only chart source with no backup, no charger and no paper.

## Check yourself

1. What does ENC stand for, and is it raster or vector?
2. Under s. 143, what must accompany an ECDIS (two options)?
3. Which vessels must be fitted with an ECDIS under s. 117?
4. What does s. 142(3) say about vessels under 100 GT?
5. What should you check about a safety contour before sailing?

---

*Study aid. Paraphrased from the Navigation Safety Regulations, 2020, CHS announcements and Bowditch ECDIS material read
through summaries, and a Nautical Institute case study. It is not the official text.*
