---
id: radar.target-course-speed-and-action
title: "Target course and speed, and taking action"
module: radar
tickets: [master-limited-60gt]
order: 5
summary: "How the R-T-M triangle turns a relative plot into the target's true course and speed, what Rule 19(d), Rule 8 and Rule 19(e) say about acting on radar information in restricted visibility, and a worked example of how a manoeuvre changes the CPA."
objectives:
  - Draw the R-T-M triangle and read the target's true course and speed from it.
  - State the Rule 19(d) limits on altering course when a target is detected by radar alone.
  - Explain why an alteration must be large and made early (Rule 8).
  - Describe what to do under Rule 19(e) if a close-quarters situation cannot be avoided.
  - Judge the effect of a planned manoeuvre on CPA and TCPA.
prerequisites: [radar.plotting-cpa-tcpa, colregs.rule-19.restricted-visibility, colregs.rule-08.action-to-avoid]
estimated_minutes: 15
key_facts:
  - In the R-T-M triangle, RM is the target's relative movement, RT is own ship's run drawn from R, and TM is the target's true movement over the same time.
  - Own ship's run in six minutes in nautical miles is own speed in knots divided by 10.
  - The target's true speed in knots is the length of TM in nautical miles for six minutes, multiplied by 10.
  - The target's true course is the direction of TM relative to own heading, plus own course (subtract 360 degrees if the total is over 360).
  - Under Rule 19(d), a vessel that detects another by radar alone must determine whether a close-quarters situation is developing or risk of collision exists, and if so take avoiding action in ample time.
  - Under Rule 19(d), so far as possible avoid altering to port for a vessel forward of the beam (other than one being overtaken), and avoid altering towards a vessel abeam or abaft the beam.
  - Under Rule 8(b), an alteration of course or speed to avoid collision should be large enough to be readily apparent to another vessel observing visually or by radar, and a succession of small alterations should be avoided.
  - Under Rule 19(e), a vessel that cannot avoid a close-quarters situation with a vessel forward of her beam must reduce speed to the minimum at which she can be kept on her course, take all way off if necessary, and navigate with extreme caution until danger of collision is over.
  - Under Rule 8(d), the effectiveness of the action must be carefully checked until the other vessel is finally past and clear.
source_ids: [renav-oceannavigator-rapid-plotting, renav-crc1416-xml]
review_status: needs_expert_review
---

# Target course and speed, and taking action

**Study aid only.** The rules named here are taught in the COLREGs module. This lesson shows how they apply to a radar
plot. Always follow the actual rules and the master's orders.

## What you need to know

The six-minute plot told you where the target is heading relative to you. For a decision it helps to know what she is
actually doing: her true course and true speed. Then you can judge what sort of vessel she is, which rule applies if you
meet in sight, and what an alteration by you will do. The tool is the ==R-T-M triangle==. After it come the rules that
govern what you do with the answer in fog.

## The R-T-M triangle

Start with the plot you already have: R (the first position), M (the position six minutes later), and the line RM.
The extra step takes your own motion out of it.

1. From R, draw a line straight down the plot (on a head-up plot, astern) of length equal to ==your own run in six minutes==.
   Your own run in nautical miles is your speed in knots divided by 10. Mark the end ==T==.
2. Join T to M. The line ==TM== is the target's own movement over six minutes.
3. Length of TM in nautical miles × 10 = the target's ==true speed== in knots.
4. Measure the direction of TM from your heading line. Add your own course (subtract 360° if the total is over 360°). That
   is the target's ==true course==.

The triangle is called R-T-M because those are its corners: RM is relative motion, RT is your reverse motion, and TM is the
target's motion. If T lands right on M the target is stationary: a buoy, a moored boat or a rock.

## Worked example

Use the same invented target as the last lesson: R at 6.0 nm, 045° relative; M at 4.8 nm, 043° relative. Own ship is on
course **030°T** at **10 knots**.

1. Own run in six minutes = 10 ÷ 10 = **1.0 nm**. T is 1.0 nm straight down the plot from R.
2. TM, from T to M, is **1.0 nm** long and points about **285°** from your heading line.
3. Target true speed = 1.0 × 10 = **10 knots**.
4. Target true course = 285° + 030° = **315°T**.
5. Her true bearing from you at M is 030° + 043° = **073°T**, range 4.8 nm.

So a vessel making 10 knots on about 315°, bearing about 073° from you, is crossing from your starboard side towards
your port side. Your own course is 030° and the CPA was 0.83 nm. In sight, this would be a crossing situation, but in
fog Rule 19 applies, not the crossing rule.

## Rule 19: acting on radar alone

Rule 19 applies to vessels that are not in sight of one another in or near restricted visibility. You must proceed at a
safe speed with engines ready for immediate manoeuvre (Rule 19(b)). Rule 19(d) covers the radar-only case:
the vessel that detects another by radar alone ==determines whether a close-quarters situation is developing and/or risk
of collision exists==, and if so ==takes avoiding action in ample time==. When that action is an alteration of course,
so far as possible avoid:

- an alteration to ==port== for a vessel ==forward of the beam==, other than a vessel being overtaken;
- an alteration ==towards a vessel abeam or abaft the beam==.

Rule 19 does not tell you which way to turn in every case. It tells you what to avoid. The usual reasoning is that a
turn to port for a target forward of the beam can put you across her path if she is also turning, and a turn towards a
vessel abeam or abaft the beam closes the distance quickly and leaves little time.

## Rule 8: do it early, do it big, check it

Rule 8(b) says any alteration to avoid collision should be ==large enough to be readily apparent== to another vessel
observing visually or by radar, and ==a succession of small alterations should be avoided==. A 10° wobble does not
show up on another vessel's radar. Rule 8(d) adds that the action must be carefully ==checked until the other vessel is
finally past and clear==. You are not done when you turn; you are done when she is past.

## Testing a manoeuvre on the plot

You can check the effect of a planned alteration before making it. Work from the target's true vector (10 knots, 315°
for this example) and your proposed new course and speed. The results below were calculated with the target and own
ship holding course and speed after an instant change at M (a simplification).

| Action at M | New CPA | TCPA |
|---|---|---|
| No change (030°T, 10 knots) | 0.83 nm | 23 min |
| Alter 40° to starboard (070°T) | 2.4 nm | 15 min |
| Alter 40° to port (350°T) | 0.82 nm | 48 min |
| Slow to 5 knots, same course | 2.7 nm | 24 min |

An alteration to starboard of 40° opens the CPA to about 2.4 nm, a large and readily apparent change. A port
alteration of the same size opens nothing and leaves her passing at 0.8 nm for most of an hour, exactly the sort of
move Rule 19(d)(i) warns against. A speed reduction also works here, but it only works if she holds her course, and a slow
vessel is harder to manoeuvre.

## If a close-quarters situation cannot be avoided: Rule 19(e)

Rule 19(e) says that unless it has been determined that risk of collision does not exist, a vessel that hears the fog
signal of another apparently forward of her beam, or that cannot avoid a close-quarters situation with a vessel forward of her
beam, must ==reduce speed to the minimum at which she can be kept on her course==. She must, if necessary, ==take all
her way off== and in any event navigate with ==extreme caution== until danger of collision is over.

## BC example (invented)

A 22 m work vessel is inbound to Victoria in thick fog off Race Rocks. At 0615 the radar shows a target at 5.5 nm. After two
six-minute plots the mate gets target course 255°T, speed 12 knots, CPA 0.4 nm. The mate calls the master and the master
orders a bold 50° alteration to starboard, well before anything is in sight, and the engine to standby. The mate keeps
plotting, and the CPA opens to 2 nm. The target never knew there had been a problem.

## Common mistakes

- Reading target course from the relative line RM, which is not her true course.
- Forgetting to add own course (or to take 360° off).
- Altering to port by reflex for a target forward of the beam.
- Making a string of 5° and 10° alterations that no one can see on radar.
- Resuming course before the other vessel is past and clear.

## Check yourself

1. Own speed is 12 knots. How long is the own-run line RT in a six-minute plot?
2. TM is 0.8 nm long. What is the target's speed?
3. TM points 100° from your heading line and your course is 280°T. What is the target's true course?
4. Which direction of turn does Rule 19(d) say to avoid for a target on the port bow?
5. What does Rule 19(e) tell you to do if you cannot avoid a close-quarters situation?

---

*Study aid. Paraphrased from Rules 8 and 19 of the Collision Regulations and a published rapid-plotting article. The
numbers are invented and the manoeuvre table is calculated for teaching; it is not an official text.*
