---
id: chartwork.distance-off-lights
title: "Lights: ranges and distance off"
module: chartwork
tickets: [master-limited-60gt]
order: 160
summary: The three ranges of a light (nominal, luminous, geographic), the dipping-distance formula for how far off a light is when it first rises on the horizon, the effect of eye height and tide, and how to turn a first sighting into a position circle or a fix.
objectives:
  - Define nominal, luminous and geographic range and say which one limits how far off you see a light.
  - Work the distance off a light at first sighting from the heights of the light and the observer's eye.
  - Say how eye height and the state of the tide change the distance.
  - Plot a first sighting as a position circle and combine it with a bearing for a fix.
prerequisites: [chartwork.chart-datums, chartwork.transits-and-clearing-bearings]
estimated_minutes: 17
key_facts:
  - "Geographic range is the distance at which the curve of the Earth hides a light; it depends on the height of the light and the height of the observer's eye, not on how bright the light is."
  - "Distance off a light at the moment it first rises (or just dips) on the horizon = 2.08 x (square root of the light's height + square root of the eye's height) nautical miles, with both heights in metres."
  - "The horizon distance for one height is 2.08 x square root of the height in metres (about 1.17 x square root of the height in feet in some books; the constants differ by about 2 percent, so use the one the question gives)."
  - "Nominal range is the distance a light can be seen in standard clear conditions, a rating of the light itself; it is printed on charts."
  - "Luminous range is the distance a light can be seen in the visibility that actually prevails; haze, rain and fog shorten it."
  - "The distance at which you actually see a light is the smaller of its geographic range and its luminous range."
  - "On Canadian charts the heights of lights are normally measured above higher high water, large tide, in tidal waters."
  - "When the tide is below that high-water datum the light is higher above the water than charted, so it is seen slightly sooner than the charted height predicts."
  - "A higher eye sees a light sooner: standing on the flying bridge instead of on deck increases the distance at which it first appears."
  - "A first sighting gives a position circle: an arc of radius equal to the distance off, centred on the light; where it meets the bearing of the light is a fix."
source_ids: [chw-c-oceannavigator-lights, sixty-sailingissues-fixes, chw-a-chs-chart1-2022-pdf, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Lights: ranges and distance off

**Study aid only. Not for navigation.** The lights, heights and distances here are invented.

## What you need to know

At night a light is often the first thing you see of the land. Two questions follow. First, when will I see it? Second, once I have seen it, what does that tell me about where I am? Both depend on three different "ranges", and the exam likes to test whether you can tell them apart.

## The three ranges

- ==Geographic range==: how far away the light can be before the ==curve of the Earth== hides it. It depends only on two heights, that of the light and that of your eye. A dim light on a tall tower has a long geographic range.
- ==Nominal range==: how far the light can be seen in ==standard clear conditions==. It is a rating of the lamp and lens and is the figure printed on the chart. It says nothing about the curve of the Earth.
- ==Luminous range==: how far the light can be seen in the ==visibility that actually prevails==. Haze, rain, snow and fog cut it down, and on the BC coast fog can cut it to a fraction of the nominal range.

You see the light at the ==smaller of the geographic range and the luminous range==. A powerful light behind a hill of water is not seen. A tall light in thick fog is not seen either.

## Geographic range and the dipping distance

A watcher whose eye is at height h metres above the water has a horizon at about ==2.08 × √h nautical miles==. A light of height H has its own horizon at 2.08 × √H. When the two horizons just meet, the light is on the point of showing, so:

==Distance off = 2.08 × (√H + √h)== nautical miles, with H and h in ==metres==.

Some books use feet and the constant ==1.17== instead: a 100-foot light has a horizon at 1.17 × 10 = 11.7 miles and a 9-foot eye at 1.17 × 3 = 3.5 miles. The two constants differ by about 2 percent (1.17 per √foot is about 2.12 per √metre), which is inside the uncertainty of the method. Use the constant and units in the question.

The rule gives the distance when the light is ==first sighted== on the way in, or when it is lost on the way out. The word "dipping" is used for the second case: the light dips below the horizon.

## Worked example 1 (invented): the first sighting

A light is charted at 25 m. Your eye is 3 m above the water.

1. √25 = 5.00 and √3 = 1.732.
2. Sum = 6.732. Multiply by 2.08: ==14.0 nm==.

If the light is first seen at 2150 with a bearing of 262° T, you are 14.0 nm from the light.

## Worked example 2: eye height

The same 25 m light, now seen from a bridge wing with the eye at 6 m: 2.08 × (5.00 + 2.449) = ==15.5 nm==. Three extra metres of eye height buy about 1.5 nm.

A 40 m light from three different eye heights: 2 m gives 16.1 nm, 5 m gives 17.8 nm, 10 m gives 19.7 nm. So you see lights from a high perch first. And a deckhand on a low deck may be the last to call it. If you are expecting a light and cannot see it, ask the watch to look from higher up before you decide it is out.

## Worked example 3: which range limits you

A light is 60 m high, nominal range 22 nm. Your eye is 4 m up.

- Geographic range: 2.08 × (7.746 + 2.000) = ==20.3 nm==.
- In clear weather, the luminous range is about the nominal range, 22 nm, and the geographic range is smaller, so you first see it at about 20.3 nm.
- In haze the luminous range might be only 12 nm. Then it is the smaller one, so you see the light at ==12 nm==, well inside the horizon.

In the second case the first sighting does not tell you the distance off by the dipping formula. Only a light seen on the horizon, in good visibility, gives a distance off.

## The effect of the tide

The height printed for a light is normally measured above ==higher high water, large tide==, a high-water datum (lesson 2). The water is below that level most of the time, so the light stands higher above the water than the chart says. With an invented HHWLT at 4.8 m above chart datum and a present tide of 1.8 m, a 25 m charted light is really 25 + (4.8 − 1.8) = ==28.0 m== above the water. The range for a 3 m eye becomes 2.08 × (5.292 + 1.732) = 14.6 nm instead of 14.0. The difference is about half a mile. Small, but it always runs the same way: the light is ==seen slightly sooner than the chart suggests==. In this lesson, and in most exams, the charted height is used unchanged unless the question gives the tide.

## Using a first sighting

A first sighting gives you one line of position:

1. Note the time, your heading and, if you can, the bearing of the light.
2. Work out the distance off.
3. On the chart, draw an arc of that radius centred on the light. This is a ==position circle==. You are somewhere on it.
4. The bearing of the light, corrected to true, crosses the arc at one point on the near side of the light. That point is your ==fix==. With no bearing, the arc still limits where you can be; use it with your DR and with the course line to pick the likely spot.

Be honest about accuracy. Refraction varies, the eye height is an estimate, the light might be first glimpsed in a wave trough, and the light may be higher or lower than the chart says. Treat the circle as a band, not a hair-fine line. It is a useful check against the DR and GNSS, not a primary fix.

## BC example

A skipper in the Strait of Georgia is making for a lighthouse at the entrance of a channel. The List of Lights and the chart give a height of 25 m and a nominal range of 15 nm (invented). With her eye at 3 m, the geographic range is 14.0 nm. She expects the light at about 14 nm in clear weather. Her DR puts her 11 nm off at the time she first sees it. The 3 nm difference is the warning: her DR is wrong, her eye height is not what she thought, the light seen is another, or the visibility is playing tricks. She checks the characteristic against the List of Lights, takes a bearing and reconsiders.

## Common mistakes

- Using nominal range as the distance at which the light is first seen. It can be more or less than the geographic range.
- Using feet with the metric constant, or metres with 1.17.
- Forgetting the eye term and using the light's height alone. Both heights count.
- Treating a haze-limited sighting as a dipping distance.
- Taking the arc on the wrong side. The ship is on the seaward side of the light if she is approaching from the sea, so use the bearing to decide.
- Believing every light is visible all round. Sectors and obstructions can hide it from some directions (see Chart 1, section P).

## Check yourself

1. Name the three ranges and say what limits each.
2. A 36 m light and a 4 m eye. What is the distance off at first sighting? (Answer: 2.08 × (6 + 2) = 16.6 nm.)
3. Visibility is 8 nm and the geographic range is 15 nm. At what range do you see the light?
4. Why does a light look slightly higher than charted at low tide?
5. How do you turn a first sighting into a fix?

Remember: this is a study aid, not a navigation aid. Real light data come from the current List of Lights, Buoys and Fog Signals and the current chart.
