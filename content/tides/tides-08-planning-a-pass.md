---
id: tides.planning-a-pass
title: "Planning a tidal pass"
module: tides
tickets: [master-limited-60gt, fishing-master-4]
order: 8
summary: A step-by-step plan for going through a narrow pass at slack water, worked on an invented scenario - table, daylight saving, stream limit, window, arrival and departure times, a go or no-go check at a fix, wind against the stream and what to do if you miss the window.
objectives:
  - Turn a current-table entry into a clock-time window for a pass, given a stream limit chosen in advance.
  - Work back from the window to an arrival time at the pass entrance and a departure time, using speed made good.
  - Test the plan for a slower passage and choose a check point with a clear go or no-go rule.
  - Allow for wind against the stream, a missed slack and the limits of the predictions.
prerequisites: [tides.current-stations-and-slack, tides.tidal-currents]
estimated_minutes: 20
key_facts:
  - "Plan a pass from the current table for that pass and that day, not from the tide table."
  - "Correct the table time to the clock first: tables use standard time, so add one hour in daylight saving time."
  - "Decide the highest stream you will accept before you look at the times; the window is the time either side of slack in which the stream stays below that limit."
  - "Work backward: arrival time at the entrance = chosen time within the window; departure = arrival - (distance / speed made good)."
  - "Use speed made good for the transit time to the pass, and test the plan with a slower speed."
  - "Aim to arrive early in the window and wait outside; arriving late means meeting a stream that is building."
  - "Wind against the stream steepens the sea; check the wind forecast against the stream direction after slack."
  - "Slack is predicted, not guaranteed: strong wind can advance or delay it considerably, and secondary stations are less precise."
  - "Have a plan if you miss the window: a safe waiting place and the time of the next slack, which is hours away."
  - "Recompute the ETA at each fix; if it falls outside the window, switch to the waiting plan."
source_ids: [chw-c-chs-tct-vol6-2026, rmet-ubc-10d-tides-currents, rmet-predictwind-wind-against-current, noaa-tides-currents-faq, dfo-tides-and-tidal-streams-chapter, chw-c-vimarina-seymour-narrows]
review_status: needs_expert_review
---

# Planning a tidal pass

**Study aid only. Not for navigation.** Everything here is invented: the pass, the times, the speeds and the limits. For a real pass use the current CHS tables and Sailing Directions, and ask someone with local knowledge.

## What you need to know

A narrow pass is a place where you do not go when you feel like it. You go when the water lets you. The plan is mostly arithmetic with a clock, and the clock is where people get caught: a wrong hour, a late start, a slow engine. This lesson takes one scenario from the book to the bridge.

## The scenario

"Example Pass" is a narrow channel. Your vessel makes 7.5 knots over the ground on the way to it. The entrance is 18.0 nm from your anchorage at "Example Harbour". It is a summer day, so daylight saving time is in effect.

The current table for the reference station at Example Pass (standard time, PST) shows:

| Event | Time (PST) | Speed |
|---|---|---|
| Max ebb | 1132 | −6.0 kn |
| Slack | 1432 | 0.0 |
| Max flood | 1732 | +6.0 kn |
| Slack | 2045 | 0.0 |

## Step 1: clock time

Add one hour to move the table to the clock: ==slack is at 1532 on the clock==, max flood at 1832, the next slack at 2145. Everything below is in clock time.

## Step 2: choose your limit before looking at times

Before you reach for the table, decide what stream your vessel and crew can accept. For this exercise the limit is ==2.0 knots==. This is an invented number for the exercise. Your own limit depends on the boat, its power, the pass, the sea state and your experience, and you should write it in the plan.

## Step 3: turn the limit into a window

The table gives only slack and maximum, so you must estimate the stream in between. A common simple model is a sine curve from slack to maximum: stream = maximum × sin (90° × minutes since slack ÷ minutes from slack to maximum). Here slack to maximum is 180 minutes and the maximum 6.0 knots:

| Minutes from slack | 0 | 30 | 60 | 90 | 120 | 150 | 180 |
|---|---|---|---|---|---|---|---|
| Stream (kn) | 0.0 | 1.6 | 3.0 | 4.2 | 5.2 | 5.8 | 6.0 |

The stream passes 2.0 knots at sin⁻¹ (2.0 ÷ 6.0) = 19.5°, which is 19.5 ÷ 90 × 180 = ==39 minutes== from slack. So the model says the stream is below 2.0 knots from 0853 to 1611 on... no: from ==1453 to 1611== on the clock (39 minutes either side of 1532).

That is the best case. The model is a teaching approximation, not the CHS method, and real passes can build faster than a sine curve, can be different on the flood and the ebb, and are affected by wind. So make the working window ==narrower than the model==: this exercise uses ==20 minutes either side of slack, 1512 to 1552==.

## Step 4: arrival, then departure

Aim at the early part of the window: arrive at the entrance at ==1517==, 15 minutes before slack. Then work backward.

- Transit time = 18.0 ÷ 7.5 = 2.4 h = ==2 h 24 min==.
- Departure = 1517 − 2 h 24 min = ==1253==.

## Step 5: test the plan

What if the speed made good is lower than planned?

| Speed made good | Passage time | Arrival |
|---|---|---|
| 7.5 knots | 2 h 24 min | 1517 |
| 6.5 knots | 2 h 46 min | 1539 |
| 5.5 knots | 3 h 16 min | 1609 |

At 6.5 knots she is still inside the window (1539, seven minutes after slack). At 5.5 knots she is at 1609, ==outside the window==, and the stream is already over 2.0 knots and building. So the plan can tolerate a drop to about 6 knots (the slowest speed that still reaches the pass by 1552) but no more. Say that out loud, and give it a check: at 1400 take a fix; recompute the ETA from the speed made good; if the ETA is later than 1552, do not carry on into the pass. Go to the waiting plan instead.

## Step 6: wind against the stream

Suppose the forecast is a north-east wind of 20 knots, and the flood sets toward the north-east. The ebb, which runs before slack, flows with the wind. The flood, after slack, flows against it. Moving water shortens and steepens waves, and a stream against the wind makes a steep, confused sea. A turning tide can also build seas with no change in the wind.

The consequence for the plan: arriving early in the window, while the ebb is still running with the wind, is better than arriving late, when the flood has begun to build against it. This is why step 4 put the arrival at 1517 and not 1552. If the forecast were for a wind against the ebb, the best side of the window would be the opposite one.

## Step 7: the waiting plan

A plan that cannot be abandoned is a trap. Write down:

- ==Where you will wait==: a bay or anchorage outside the pass that you have read about in the Sailing Directions and the chart, with depth, holding and shelter. A marina page for this kind of pass names a waiting bay on each side; that is the kind of detail to look up, not to guess.
- ==When the next slack is==: 2145 on the clock, in the dark. That alters the choice: is the pass safe to go through at night? If not, the wait is until the next day.
- Fuel, daylight and crew hours for a wait.

## What can go wrong with the numbers

- ==Wind== can advance or delay slack by a considerable amount, and in a strong wind the table can be wrong by more than your margin.
- ==A secondary station== is less precise than a reference station. If the pass is a secondary station, start by widening the margin.
- ==The clock==. A missing daylight saving hour is the most common error and the most costly.
- ==The speed==. The log speed is not the speed made good. Check the plan against a fix.
- ==The place==. The prediction is for a point or the middle of the channel. The stream at the side may differ.

## BC example

The usual pattern for the northern BC passes is: read the table, add the hour, decide the window, arrive early, wait outside, go through together with the slack, and be ready to turn back. One local marina page claims that on the biggest tides you should arrive within about 15 minutes of slack, and within half an hour on smaller ones. It is not an official source and the numbers in it are not exam facts, but they show how narrow real windows can be, and why a margin of 20 minutes is not generous for the strongest passes.

## Common mistakes

- Starting from the time you want to go and then looking for slack.
- Forgetting the daylight saving hour.
- Using log speed instead of speed made good for the approach.
- Arriving at the pass entrance exactly at slack, with no margin for a slow run.
- Ignoring the wind direction against the stream after slack.
- Having no waiting place or no time for the next slack.

## Check yourself

1. Slack is 1115 PST, daylight saving is in effect. Clock time of slack? (1215.)
2. A pass 12.0 nm away, speed made good 6.0 knots, arrival wanted 1000. Departure? (0800.)
3. Why arrive early in the window rather than late?
4. What would you write in the plan as your go or no-go rule at the check fix?
5. Name two things that can make the real slack differ from the table.
