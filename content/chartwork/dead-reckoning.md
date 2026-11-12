---
id: chartwork.dead-reckoning
title: "Dead reckoning and estimated position"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 110
summary: How to advance a known position by course and speed through the water, what a DR ignores, when to plot a DR position, how to label the plot, and how an estimated position adds current, wind and steering effects. Includes a worked two-leg plot with positions by plane sailing.
objectives:
  - Define dead reckoning and estimated position and say what each takes into account.
  - Lay off a DR from a fix with a true course and a distance from speed and time, through a change of course and speed.
  - State when a DR position must be plotted, how it is labelled, and what happens at each new fix.
  - Add a known current to a DR position to give an estimated position.
prerequisites: [chartwork.compass-error-tvmdc, chartwork.speed-time-distance]
estimated_minutes: 18
key_facts:
  - "Dead reckoning (DR) finds a position by advancing from a known position along the course steered (true) and the distance run from speed through the water and time."
  - "A DR position ignores current, wind and leeway, steering error and compass error; it is only as good as its course and speed."
  - "An estimated position (EP) is a DR position corrected for leeway, steering error and current."
  - "Plot a DR position at least every hour on the hour, after every change of course or speed, after every fix or running fix, and after a single line of position."
  - "Restart the DR from each new fix; the next leg is laid from the fix, not from the old DR position."
  - "Convert the compass course to true before laying it on the chart; DR courses are plotted in true."
  - "Label the plot: write the true course and the speed on each course line, and give each position its time in four figures and its type (fix, DR or EP)."
  - "The Master Limited chartwork competence includes determining position by dead reckoning allowing for wind, tides, currents and estimated speed."
source_ids: [chw-a-bowditch-2019-ch9-dr, sixty-tp2293-2019-draft, sixty-bcit-naut-1475, chw-a-sailingissues-navcourse4]
review_status: needs_expert_review
---

# Dead reckoning and estimated position

**Study aid only. Not for navigation.** All positions, courses and speeds are invented.

## What you need to know

==Dead reckoning (DR)== is a method for finding where you should be. You start at a known position (a fix) and advance it along the ==course steered== and the ==distance run== (from speed through the water and time). The method needs no outside reference once you have the start. That is both its strength and its weakness: it is available in fog, with GNSS failed, in the dark, but it drifts away from the truth as time passes.

A DR position takes no account of ==current, wind and leeway, steering error or compass error==. Anything that is not in the course and speed is missing. A ==position corrected for leeway, steering error and current== is an ==estimated position (EP)==. The Master Limited chartwork competence in the draft TP 2293 includes determining position by dead reckoning that allows for wind, tides, currents and estimated speed, which is the EP.

## Laying the DR

1. Start from the last fix or known position, with its time.
2. Convert the course steered to ==true== (lesson 8) and draw the course line from the start.
3. Calculate the distance run: ==distance = speed × time== (lesson 10).
4. Mark that distance along the line, using the latitude scale level with the work. That point is the DR position for that time.
5. Label the course line and the position.

### Labels

On each course line write the ==true course and the speed==. One common convention (Bowditch) is C and the course above the line and S and the speed below it, for example C 128 above and S 7.5 below; use the form your course teaches. Label every position with its time in ==four figures== (0900, 1024) and its type. Symbols for fix, DR and EP differ between institutions: one common convention is a circle for a fix, a semicircle for a DR and a small square for an EP. Use the one your course teaches and use it consistently. A DR position without a time is useless, because you cannot update it.

## When to plot a DR position

Plot a DR position:

- ==at least every hour, on the hour==;
- ==after every change of course or speed==;
- ==after every fix or running fix==;
- ==after plotting a single line of position== (so that you can see where along the line you probably are).

==Every new fix restarts the DR==: draw the next course line from the fix, not from the DR position it replaced. The offset between the DR and the fix is information about the current (lesson 17).

## Worked example 1 (invented): two legs

At 0900 the ship is at a fix of 49° 00.0′ N, 123° 30.0′ W. The helmsman steers 115° C. The compass error is 13° E (variation 16° E, deviation 3° W). The log says 7.5 knots.

1. True course = compass course + error = 115 + 13 = ==128° T==.
2. At 1000, distance = 7.5 nm. Difference of latitude = 7.5 × cos 128° = −4.6′ (south). Departure = 7.5 × sin 128° = 5.9 nm east. At about 49° N, difference of longitude = 5.9 ÷ cos 49° = 9.0′ E.
   **DR 1000: 48° 55.4′ N, 123° 21.0′ W.**
3. At 1024 the skipper changes to 076° T at 6 knots. Time on the first leg: 1 h 24 min = 1.4 h; distance = 1.4 × 7.5 = 10.5 nm. **DR 1024: 48° 53.5′ N, 123° 17.4′ W.** Plot it, because the course and speed changed.
4. Second leg: 1024 to 1100 is 36 minutes, so distance = 6 × 36 ÷ 60 = 3.6 nm on 076°.
   **DR 1100: 48° 54.4′ N, 123° 12.1′ W.** Plot it, because it is on the hour.

Check on the chart: join the 0900 position to the 1100 DR and measure. It should be 13.0 nm on 115° T, which agrees with the plane-sailing answer.

## Worked example 2: from DR to EP

You know, from tide tables and observation, that a current of ==1.0 knot is setting 090° T== (toward the east). From 0900 to 1100 is two hours, so the water has carried you ==2.0 nm toward 090°== (rate × time). Draw that line from the 1100 DR position and mark its end as the EP.

**EP 1100: 48° 54.4′ N, 123° 09.0′ W** (the latitude is unchanged and the longitude is 3.1′ further east).

From the 0900 fix to the EP is 14.9 nm on 112° T in 2 hours: the ==track made good== is 112° and the speed made good is 7.4 knots. The DR said 115° and 6.5 knots over the ground. The difference is the current.

Remember that the current is itself an estimate. An EP is a better guess than a DR but it is still a guess. The next fix tests it.

## Why the DR still matters

- It tells you what you expect to see: when a light should come up, where to start a lookout for a buoy, which of two radar ranges to choose.
- It is the check on every other source. If GNSS puts you 4 nm from the DR in an hour, something is wrong with one of them.
- It carries you through gaps. In fog on the Strait of Georgia, with the plotter off, a skipper who has kept the DR knows roughly where she is and when to expect the next mark.
- It is examined: the competence statement includes it, and it is the fallback when electronics fail.

## Exam traps

- Laying off the compass course without converting to true.
- Using GNSS speed over the ground as the DR speed, which would double-count the current.
- Forgetting to restart from the fix. The old DR is dead once you have a fix.
- Mixing minutes and hours in distance = speed × time.
- Leaving positions unlabelled, or labelling with the wrong time. At 1024 the turn happened; the DR at that time is a position you must plot.
- Calling a DR an EP, or the reverse. An EP has the current and leeway applied.

## Check yourself

1. Define DR and say what it leaves out.
2. A DR is laid from a fix at 1430 with course 200° T and speed 9 knots. How far is the DR at 1500?
3. Name three times you must plot a DR position.
4. What is the difference between a DR and an EP?
5. Why is the next course line laid from the fix and not from the DR?
