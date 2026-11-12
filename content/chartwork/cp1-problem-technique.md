---
id: chartwork.cp1-problem-technique
title: "CP1-style chart problem technique"
module: chartwork
tickets: [master-limited-60gt, fishing-master-4]
order: 250
summary: "The reported CP1 format, a routine for any multi-part chartwork problem (data box, work in true, labelled plot, reverse check), and one complete six-part worked problem from course to steer through fix, set and drift, to a new course and ETA."
objectives:
  - State the reported format of the CP1 exam and the tools it expects.
  - Apply a fixed routine to a multi-part chartwork problem, and say which corrections go with which instrument.
  - Work through a six-part problem from course to steer, to fix, to set and drift, to a new course and ETA.
  - Check each answer against a quick independent calculation.
prerequisites: [chartwork.course-to-steer, chartwork.running-fix, chartwork.fixes-bearings, chartwork.compass-error-tvmdc]
estimated_minutes: 25
key_facts:
  - "The CP1 exam is reported as 3 hours: 10 multiple-choice questions worth 3 marks each and 5 practical chartwork questions worth 5 to 15 marks each."
  - "A non-phone calculator, parallel rules and dividers are the tools; a draft TP 2293 gives the pass mark as 70 per cent."
  - "Start every problem by listing the given data (corrected variation, the deviation card, speeds, times) and what each part asks."
  - "Work and plot in true; convert compass to true at the start and true to compass at the end."
  - "A hand-bearing compass held clear of iron needs variation only; a ship's compass bearing needs the compass error for the ship's heading."
  - "Label every position with its time in four figures and its type (fix, DR, EP, RFix) and every course line with its course and speed."
  - "Measure distances on the latitude scale level with the work, and give directions in three figures."
  - "For the course to steer in a current, the wanted track and the current vector both start at the departure point, and the arc is swung from the end of the current vector."
  - "Check each answer by a quick reverse calculation, and ask whether it is believable against the chart."
source_ids: [sixty-holland-cp1, sixty-nauticalmind-cp1, sixty-tp2293-2019-draft, chw-a-bowditch-2019-ch9-dr]
review_status: needs_expert_review
---

# CP1-style chart problem technique

**Study aid only. Not for navigation.** The problem below is invented and uses invented positions. It is not a Transport Canada question.

## What you need to know

The CP1 exam (Chartwork and Pilotage, Level 1) is reported by one provider as ==3 hours: 10 multiple-choice questions at 3 marks each, then 5 practical chartwork questions worth 5 to 15 marks each==. You need a ==non-phone calculator==, ==parallel rules== and ==dividers==. A draft TP 2293 gives the pass mark as ==70 per cent==. These are reports from course providers and a draft, so confirm them with the Transport Canada exam office or your course before you rely on them.

The practical questions are long, with several parts that build on each other. A mistake in part (a) carries into everything after it. The cure is a routine, applied every time.

## The routine

1. **Read the whole question first.** Note what is given and what each part wants. Underline times, speeds and the year.
2. **Build a data box.** Corrected variation (the rose corrected to the year of the question), the deviation card, ship's speed, and the time convention. Do the arithmetic once.
3. **Work in true.** Convert compass courses and bearings to true at the start, plot in true, and convert back at the end.
4. **Pick the right correction.** A ==hand-bearing compass== held clear of iron: ==variation only==. A ==ship's compass== bearing: ==compass error for the ship's heading==. A gyro: the gyro error.
5. **Plot neatly.** Label every position with ==time in four figures and its type== (fix, DR, EP, RFix). Label course lines with the ==true course and the speed== (for example C above the line and S below, if that is your course's convention).
6. **Measure properly.** Distances on the ==latitude scale level with the work==. Directions in ==three figures==. Positions to 0.1′.
7. **Check.** Do a reverse calculation or a sanity check on every answer. Ask: is it believable on the chart?
8. **Show the working.** If a part carries several marks, write the steps. Partial credit is a common practice, but check your course.

A suggested time plan (not from a source): about 30 minutes for the 10 multiple-choice questions, leaving about 2½ hours for five practical questions, so roughly 30 minutes each. Do the multiple-choice questions you are sure of first, and keep five minutes to review.

## A complete worked problem (invented)

**Given.** Year 2026. The rose reads 18° 30′ E 2015 (8′ W). The ship's deviation card (entered with compass heading): 000° 2° E; 045° 4° E; 090° 3° E; 135° 0°; 180° 3° W; 225° 5° W; 270° 4° W; 315° 1° W. Speed 8 knots through the water. At 0800 the ship is at a fix of 49° 00.0′ N, 123° 30.0′ W and wants to reach a point B at 49° 06.0′ N, 123° 42.0′ W.

**(a) Course to steer and distance (no current known).**

1. Corrected variation: 11 years × 8′ = 88′ = 1° 28′; 18° 30′ − 1° 28′ = 17° 02′ E, say ==17° E==.
2. Plane sailing for A to B: difference of latitude 6.0′ N; difference of longitude 12.0′ W; departure = 12.0 × cos 49° = 7.9 nm; distance = √(6.0² + 7.9²) = ==9.9 nm==; course = N 52.6° W = ==307° T==.
3. Magnetic = 307 − 17 = 290° M. Deviation near 290° C is between 270° (4° W) and 315° (1° W): at 292½°, deviation = −4 + 3 × (22.5 ÷ 45) = −2.5, that is 2½° W. Compass = magnetic − deviation = 290 + 2.5 = ==292½° C== (steer 293° or 292½°). Compass error = 17 E + 2½ W = 14½° E, and 292.5 + 14.5 = 307 ✓.

**(b) ETA with no current.** 9.9 ÷ 8 = 1.24 h = 74 minutes. Depart 0800, ETA ==0914==.

**(c) DR at 0900.** One hour at 8 knots on 307° T: 8.0 nm. Difference of latitude = 8.0 × cos 307° = 4.8′ N; departure = 8.0 × sin 307° = 6.4 nm W; difference of longitude = 6.4 ÷ cos 49° = 9.7′ W. **DR 0900: 49° 04.8′ N, 123° 39.7′ W.**

**(d) Fix at 0900.** The skipper takes two bearings with a hand-bearing compass, held clear of iron: a tower (49° 07.6′ N, 123° 41.4′ W) bears 333° and a lighthouse (49° 03.1′ N, 123° 45.6′ W) bears 243°.

1. Hand compass: variation only. 333 + 17 = ==350° T==; 243 + 17 = ==260° T==. The angle of cut is 90°, a good cut. (Using the ship's compass error here would be the mistake.)
2. Lines from the objects along the reciprocals: from the tower along 170°; from the lighthouse along 080°.
3. They cross at **fix 0900: 49° 03.7′ N, 123° 40.4′ W**. It lies 4.0 nm from the tower and 3.5 nm from the lighthouse.

**(e) Set and drift.** From the DR (49° 04.8′ N, 123° 39.7′ W) to the fix (49° 03.7′ N, 123° 40.4′ W): the fix is 1.1′ south and 0.7′ west, which is 0.46 nm west at this latitude (0.7 × cos 49°). Set = 180° + arctan(0.46 ÷ 1.1) = ==203° T==, drift = √(1.1² + 0.46²) = ==1.2 nm== in 1 hour, so rate = ==1.2 knots==.

**(f) New course to B in the current.** From the fix to B (49° 06.0′ N, 123° 42.0′ W): difference of latitude 2.3′ N, difference of longitude 1.6′ W, departure 1.05 nm W, distance = ==2.5 nm==, track = N 24.5° W = ==336° T==.

1. Draw the track from the fix. From the fix draw the current vector, 1.2 nm toward 203°. From its end swing an arc of 8.0 nm to the track.
2. The angle between the set (203°) and the track (336°) is 133°. Sine of the correction = 1.2 × sin 133° ÷ 8.0 = 0.110, so ==6.3°==. The current sets to the left of the track, so steer to the right: 336 + 6.3 = 342.3, say ==342° T==.
3. Speed made good = 8.0 × cos 6.3° + 1.2 × cos 133° = 7.95 − 0.82 = ==7.1 knots==.
4. Time = 2.5 ÷ 7.1 = 0.35 h = 21 min. ETA at B = 0900 + 21 min = ==0921==.
5. Compass course: 342 − 17 = 325° M. Deviation near 325° C is about 0.3° W (between 315° at 1° W and 360° at 2° E). Compass = 325 + 0.3 = ==325° C==.

**What to check.** In (d), the distances to the tower and lighthouse (4.0 and 3.5 nm) fit what you see on the radar. In (e), the drift (1.2 nm) is small compared with the 8 nm run, so a 203° set is believable for a tidal stream. In (f), the new course (342°) is to the right of the track (336°), the same side from which the water is coming. If your answer came out on the other side, you have drawn the vector backwards.

## BC example

On a CP1 course a student finds she always loses marks on the same step, the compass conversion at the end. She adds a final line to her routine: "Is my answer true or compass? Does the question want true or compass?" She checks the chart symbol of the course line, then writes the answer with a "T" or "C" after the three figures.

## Exam traps

- Using the ship's compass error for a hand-bearing compass bearing, or the variation alone for a ship's compass bearing.
- Plotting before converting to true.
- Forgetting to correct the rose to the year in the question.
- Using the wrong side of the track for the course to steer in a current.
- Measuring distance on the longitude scale, or on the latitude scale far from the work.
- Writing a direction in two figures, a time in three, or a position without N/S and E/W.
- Not labelling the plot, so that a correct position cannot earn its marks.

## Check yourself

1. How many marks does each multiple-choice question carry in the reported CP1 format?
2. Which instrument needs only variation to give true?
3. In the worked problem, why is the course to steer in (f) greater than the track?
4. What does a good data box contain?
5. Name two checks you can apply to an answer without replotting.
