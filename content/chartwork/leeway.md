---
id: chartwork.leeway
title: "Leeway"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 190
summary: What leeway is, which way it pushes, how to estimate the leeway angle from the wake, how to apply it to a course steered or to a wanted course through the water, and in what order leeway and current are combined.
objectives:
  - Define leeway and leeway angle and say what makes them larger or smaller.
  - Estimate leeway from the angle of the wake to the fore-and-aft line.
  - "Apply leeway in both directions: from course steered to the course through the water, and from a wanted course through the water to a course to steer."
  - Combine leeway with a current in the right order for a DR, an EP and a course to steer.
prerequisites: [chartwork.dead-reckoning, chartwork.set-and-drift, chartwork.course-to-steer]
estimated_minutes: 16
key_facts:
  - "Leeway is the sideways movement of a vessel through the water, away from the wind, caused by the wind pushing on the hull and superstructure."
  - "The leeway angle is the angle between the course steered (the fore-and-aft line) and the track through the water; the track lies on the downwind side of the heading."
  - "Leeway is greatest with the wind abeam and nil with the wind dead ahead or dead astern; it grows with wind strength and with the area the wind acts on."
  - "Wind from port pushes the vessel to starboard: course through the water = course steered + leeway. Wind from starboard: course through the water = course steered - leeway."
  - "To make a wanted course through the water, steer up into the wind by the leeway angle (the opposite correction)."
  - "Estimate the leeway angle from the wake: it is the angle between the wake (reversed) and the fore-and-aft line, and the wake bends toward the windward side of the stern."
  - "Leeway is an estimated angle, not a vector measured from tables; it is entered with the course, not with the current."
  - "Order of corrections going forward: course steered, apply leeway to get course through the water, then add the current vector to get the track over the ground. Going back, remove current first, then leeway."
  - "A dead reckoning that ignores leeway is not an estimated position; an EP includes leeway, steering error and current."
source_ids: [chw-c-wikipedia-leeway, chw-a-bowditch-2019-ch9-dr, sixty-bcit-naut-1475]
review_status: needs_expert_review
---

# Leeway

**Study aid only. Not for navigation.** All angles, winds and courses are invented.

## What you need to know

A vessel is not only in the water. Her hull and everything above it stand in the wind. The wind pushes on that area and slides her sideways, ==away from the wind==. The sideways slip through the water is ==leeway==. It is separate from current: current moves the water, and the ship with it; leeway is how the ship moves through the water.

The ==leeway angle== is the angle between the line the ship is pointing (the course steered) and the line she is actually going through the water. If you steer 090° and her track through the water is 097°, the leeway is 7°. Because the wind pushes her downwind, the track always lies on the ==downwind side of the heading==.

## What makes it bigger or smaller

- ==Wind direction.== Leeway is largest with the wind ==abeam== (the push is square on). With the wind dead ahead or dead astern the push is along the keel line and the leeway is ==nil==.
- ==Wind strength.== More wind, more leeway.
- ==Windage.== A tall ship with a high bow, a flybridge or stacked containers has more area to push on. A deep-keeled, low vessel slips less. Wikipedia describes exposure as the main factor, and that is the idea to carry into the exam.
- Speed and trim. A fast vessel with way on slips less than a slow one, and a vessel in light ballast slips more than one deeply laden. These are general seamanship points; check them with your instructor.

There is no table that gives your boat's leeway. You learn it by experience on your own vessel, and you estimate it from the wake.

## Estimating from the wake

Look astern. In still water the wake streams straight out behind. With leeway, the wake leaves at an angle to the fore-and-aft line: the stern is being pushed downwind, so the wake appears to lead away on the ==windward side== of the stern.

Take a compass bearing of the wake (or the angle by eye to the fore-and-aft line). Add 180° to turn the bearing into the direction the ship is travelling through the water. The difference between that and the heading is the leeway.

**Example 1.** Head 090° T. The wake is on a bearing of 266° T. The track through the water is 266 − 180 = ==086° T==. The track is 4° to the left of the heading, so the leeway is ==4° to port==, and the wind is on the starboard side.

An estimate is good to a degree or two at best. Use it, and update it when the wind changes.

## Applying leeway

Think of the wind as a hand pushing the bow side to the downwind side.

- ==Wind from port== (left): the ship goes to starboard. ==Course through the water = course steered + leeway.==
- ==Wind from starboard== (right): the ship goes to port. ==Course through the water = course steered − leeway.==

To work the other way, from a wanted course through the water to a course to steer, ==steer up into the wind== by the leeway angle:

- Wind from port: ==course to steer = wanted course − leeway==.
- Wind from starboard: ==course to steer = wanted course + leeway==.

Check each time with a quick picture: wind from the right means the ship slides left, so you must point to the right of where you want to go.

## Worked example 2 (invented): the cost of ignoring it

A vessel steers 045° T at 6 knots for 2 hours. The wind is from the north-west (315° T), on the port beam, and the leeway is 7°.

1. Distance run = 6 × 2 = 12.0 nm.
2. Wind from port, so the course through the water = 045 + 7 = ==052° T==.
3. The DR ignoring leeway lies 12.0 nm along 045° and the corrected DR lies 12.0 nm along 052°. The two points are 12.0 × sin 7° = ==1.5 nm== apart.

That is 1.5 nm of error in two hours from a wind that did not seem all that strong. Over a longer leg, in a narrow channel, it is the margin between clear water and a reef.

## Leeway and current together

Leeway changes the water track; current moves the water. Always do leeway first when going forward:

1. Start from the ==course steered== (true).
2. Apply leeway to get the ==course through the water==.
3. Add the ==current vector== (set and drift) to get the ==track over the ground== and the position.

Working backward, as in a course-to-steer problem, reverse the order:

1. Find the course through the water that the triangle gives (the ship's vector in lesson 18).
2. Apply leeway in the opposite sense to get the ==course to steer==.
3. Convert to compass, using variation and deviation on the new heading.

### Worked example 3 (invented): the whole chain

Wanted track 090° T at 8 knots through the water. Current sets 040° T at 2 knots. The wind is from the south (180° T) and the leeway is 5°.

1. Course-to-steer triangle (lesson 18): the correction is 11.0°, so the course through the water that the ship needs is ==101.0° T==, and the speed made good is 9.1 knots.
2. The ship will be steering about 101° so the wind from 180° is about 75° on her starboard bow, and she slips to port. To get a track through the water of 101° she must point to starboard: course to steer = 101 + 5 = ==106° T==.
3. Variation 16° E, deviation 2° W, so the compass error is 14° E. Compass course = 106 − 14 = ==092° C==.

So the helm is told 092. Without the leeway step he would have been told 087 and the ship would slide to port of the track by 5°, adding about 1.7 nm of error in 20 nm.

Always say in the log or on the plot which corrections you applied, so that the next person can read the plot.

## BC example

A small vessel crosses Haro Strait in a strong westerly with the wind on the starboard bow. The skipper has noticed from the wake that his boat slips about 5° to port in this wind. Without any current worked out, he adds 5° to every course so that the track through the water is what the chart says. When he reaches the other shore and compares the fix with the DR, the remaining difference is the current, and the leeway guess has stopped it from muddying that figure.

## Common mistakes

- Applying the correction the wrong way: steering down-wind of the wanted course instead of up-wind.
- Using the wind direction as the direction it blows toward. A wind is named by where it comes from.
- Treating leeway as a vector with a rate, like a current. It is an angle applied to the course.
- Applying leeway after the current, or twice.
- Leaving leeway out of an EP. A question that lists wind and leeway wants it applied, and the wind gives the side while the leeway gives the amount.
- Using the same leeway when the wind changes direction. Wind astern gives none.

## Check yourself

1. Define leeway and say which side of the heading the track lies.
2. Head 180° T and the wake bears 004° T. What is the leeway and which side is the wind?
3. Wind from port, leeway 6°, course steered 120° T. Course through the water?
4. You want to make good 200° T through the water, with wind from starboard and leeway 4°. What course do you steer?
5. In a course-to-steer problem, does the leeway go on before or after the current triangle?

This lesson is a study aid, not a navigation aid.
