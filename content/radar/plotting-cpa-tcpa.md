---
id: radar.plotting-cpa-tcpa
title: "Plotting: CPA and TCPA"
module: radar
tickets: [master-limited-60gt]
order: 4
summary: "The six-minute radar plot: how two plotted positions give the direction of relative motion, the closest point of approach (CPA), the relative speed and the time to CPA (TCPA), with a fully worked example and what the plot can and cannot tell you."
objectives:
  - Make a six-minute relative plot and draw the direction of relative motion (DRM) line.
  - Read the CPA and work out relative speed and TCPA from the plot.
  - Recognise a collision course on a plot.
  - State what the plot assumes and what makes it invalid.
  - Link plotting to Rule 7(b) and Rule 7(c).
prerequisites: [radar.displays-and-errors, colregs.rule-07.risk-of-collision]
estimated_minutes: 15
key_facts:
  - Rule 7(b) calls for radar plotting or equivalent systematic observation of detected objects, and Rule 7(c) forbids assumptions based on scanty information, especially scanty radar information.
  - A relative plot marks the target's position at the start (R) and again six minutes later (M); the line through R and M, extended past own ship, is the direction of relative motion (DRM) line.
  - CPA is the closest point of approach, read as the shortest distance from own ship (the centre of the plot) to the DRM line.
  - If the DRM line passes through the centre of the plot, the target will collide with own ship if neither changes course or speed.
  - Relative speed in knots is the distance R to M in nautical miles in six minutes multiplied by 10.
  - TCPA is the time to CPA, found by stepping six-minute lengths along the DRM line or by dividing the distance from M to the CPA point by the relative speed.
  - A target whose compass bearing stays almost steady while the range falls is on, or near, a collision course; risk of collision is deemed to exist if the bearing does not appreciably change.
  - The plot assumes both vessels hold course and speed, and any alteration by either vessel makes it invalid.
  - A fixed object plots a line parallel to own ship's course, running back past the centre, because only own ship is moving.
source_ids: [renav-oceannavigator-rapid-plotting, renav-crc1416-xml, renav-timezero-arpa-target]
review_status: needs_expert_review
---

# Plotting: CPA and TCPA

**Study aid only.** Plotting is a skill learned on a plotting sheet with a coach. This lesson gives the method and the
numbers; practise on paper and in your radar course.

## What you need to know

Looking at a radar screen does not tell you what a target is doing. A single glance at a blip shows where it is, not where
it is going. To know whether a target will pass clear or hit you, you need two or more positions over time. That is
what a radar plot gives you, and it is what Rule 7(b) means by ==radar plotting or equivalent systematic observation==. Rule
7(c) adds the warning that goes with it: ==no assumptions on scanty information==, especially scanty radar information. One
sighting is scanty.

## The six-minute plot

Work on a relative display (or a plotting sheet laid out like one), with own ship at the centre.

1. Pick the target. Note the time and read its range and bearing. Plot it and mark it **R**.
2. Wait six minutes. Read the range and bearing again. Plot it and mark it **M**.
3. Join R to M and carry the line on past the centre. This is the ==direction of relative motion line (DRM)==: the path
   the target appears to follow relative to your ship.
4. The closest the DRM line gets to the centre is the ==closest point of approach (CPA)==.
5. The distance R to M, in nautical miles, is how far the target moved relative to you in six minutes. Multiply by 10
   to get the ==relative speed== in knots. (Six minutes is a tenth of an hour.)
6. The distance from M to the CPA point, divided by relative speed, is the ==time to CPA (TCPA)==.

If the DRM line goes through the centre, CPA is zero and you are on a ==collision course==. If the line passes clear, CPA
tells you by how much, and which side. A fixed object, such as a buoy, plots a line that runs parallel to your own
course because only you are moving.

## Worked example

Invented numbers. Own ship is steady on a constant course at 10 knots. A target is seen at **6.0 nm bearing 045°
relative** at the start of the plot (R). Six minutes later it is at **4.8 nm bearing 043° relative** (M).

1. Convert both positions to a sketch with own ship at the centre. R is 4.24 nm to the right and 4.24 nm ahead. M is
   3.27 nm to the right and 3.51 nm ahead.
2. From R to M the echo has moved 0.97 nm to the left and 0.73 nm down the screen. The length of RM is
   √(0.97² + 0.73²) = **1.21 nm**.
3. Relative speed = 1.21 × 10 = **12.1 knots**.
4. The line RM points to a relative direction of about **233°**, down and to the left of the screen.
5. Extend it. The closest it comes to the centre is **0.83 nm**, on the port bow (about 37° off the bow). That is the CPA.
6. The distance from M to the CPA point is 4.73 nm. Time = 4.73 ÷ 12.1 = 0.39 hours = **23 minutes**. That is the TCPA,
   measured from M.

Look at what the bearing did: 045° to 043°, a change of only 2° in six minutes while the range fell by 1.2 nm. Rule 7
says that if the compass bearing of an approaching vessel does not appreciably change, risk of collision is deemed to
exist. The plot shows what "almost steady" means in practice: a target about 6 nm away will pass inside a mile, with
less than half an hour to go. The numbers are invented, but the lesson is real: do not wave a target off because the
bearing is moving a little.

## A faster rule of thumb for a collision course

If the range falls by the same amount in each equal time step while the bearing does not change, the DRM line goes
through the centre. For instance 6.0, then 5.0, then 4.0 nm at six-minute intervals on a steady bearing gives 1.0 nm in
six minutes, a relative speed of 10 knots, and a TCPA of 4.0 ÷ 10 = 0.4 h = 24 minutes from the 4.0 nm point.

## What the plot assumes

Everything on a plot assumes ==both vessels hold course and speed==. If either alters, the DRM changes. That is why a
third plot after another six minutes is valuable: if the third point lies on the line, nothing has changed. If it does not,
the target (or you) has altered, and the whole picture must be restarted. A small alteration by a distant target is hard
to spot, which is one reason to act early rather than late (see Rule 8 and the next lesson).

The plot also assumes the radar picture is accurate: right heading input, a properly identified echo, and a bearing read
well. A target seen once through clutter is still scanty information.

## BC example (invented)

In dusk fog in the Strait of Georgia, a 15 m charter vessel's radar picks up a northbound echo at 5.4 nm. The mate
plots it at 1900 and 1906. The bearing has drifted 1° and the range has fallen 1.1 nm. The DRM line goes within
0.3 nm of the centre. The mate calls the skipper, tells him the CPA, the TCPA of about 24 minutes, and recommends an
early alteration. Nobody waits for a visual to settle the question.

## Common mistakes

- Judging risk from a single sighting.
- Forgetting that a small, steady bearing drift can still mean a very small CPA.
- Using CPA and TCPA from the plot after either vessel has altered course or speed.
- Mixing the six-minute distance and the hourly speed: it is a tenth, so multiply by 10, not 6.
- Calling a target safe because the DRM misses the centre by "a bit" without deciding what a safe distance is.

## Check yourself

1. What do R, M and DRM stand for?
2. A target moves 0.9 nm along the DRM line in six minutes. What is the relative speed?
3. On a steady bearing, a target is 4.0 nm away and closing at 12 knots relative. What is the TCPA?
4. Why is a plot made from one sighting dangerous?
5. What does it mean if the third plotted point is off the line?

---

*Study aid. The method follows a published rapid-plotting article and the radar provisions of the Collision Regulations;
all numbers in the worked example are invented. Not the official text.*
