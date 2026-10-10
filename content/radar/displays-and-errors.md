---
id: radar.displays-and-errors
title: "Display modes and radar errors"
module: radar
tickets: [master-limited-60gt]
order: 2
summary: "Head-up, north-up and course-up displays, relative and true motion, why stabilised modes need a heading input, and the errors that fool radar users: beam and pulse distortion, false echoes, shadow sectors and weak or missing targets."
objectives:
  - Describe head-up, north-up and course-up displays and say which are stabilised.
  - Explain the difference between relative motion and true motion displays.
  - Recognise indirect, multiple, side-lobe and second-trace false echoes and shadow sectors.
  - Explain why echoes look bigger than the target and why bearings are less accurate than ranges.
  - Say which targets radar is likely to miss.
prerequisites: [radar.principles-and-controls]
estimated_minutes: 14
key_facts:
  - On a head-up display the top of the screen is the ship's heading, bearings are relative, and the picture swings when the ship yaws.
  - A north-up display is stabilised with north at the top, so it needs a heading input and shows true bearings.
  - A course-up display keeps the top of the screen on a chosen course or direction; it also depends on a heading input.
  - On a relative motion display own ship stays fixed and everything else moves relative to it; on a true motion display stationary objects stay fixed and moving ones, own ship included, move in true perspective.
  - Radar ranges are more accurate than radar bearings, and radar bearings are typically good only to a few degrees.
  - An echo is stretched sideways by the beam width and stretched in range by about half the pulse length, so targets look larger than they are.
  - An indirect (ghost) echo appears on the bearing of the reflecting surface, at the same range as the real target, often in a shadow sector.
  - A multiple echo lies on the same bearing as the real target at two, three or more times its range, and each is weaker than the last.
  - Side-lobe echoes form arcs or a broken arc beside a large target at the same range, mostly at short range.
  - A shadow or blind sector behind a mast or funnel can hide a small near target while a larger distant one still shows.
  - Metal targets and targets broadside to the radar return stronger echoes than wood, fibreglass or end-on targets.
source_ids: [renav-bowditch-2002-ch13-radar, renav-nga-pub310-ch4, renav-raymarine-radar-settings, renav-raymarine-false-echoes, renav-crc1416-xml]
review_status: needs_expert_review
---

# Display modes and radar errors

**Study aid only.** Controls and menu names differ between sets. Read your radar's manual, and treat this lesson as the
theory behind it.

## What you need to know

A radar picture is built from two things: the echoes, and the way the set chooses to draw them. If you do not know
which way the screen is oriented and how motion is being shown, you will misread both the direction and the movement of
every target. Then there is a second problem: radar can lie. A good radar user learns the standard lies by name.

## Orientation: which way is up?

**Head-up.** The top of the screen is always the ship's heading. Bearings read from the screen are ==relative bearings==.
It needs no compass input, but it is ==not stabilised==: when the ship yaws or alters course, the whole picture swings,
and a steady target smears across the screen. That makes head-up good for avoiding close hazards and poor for judging
whether a bearing is changing.

**North-up.** The top of the screen is north. The picture is ==stabilised== by a heading input from a gyro or electronic
compass, so land stays still when the ship alters course. Bearings read are true bearings. The ship's heading marker
swings instead of the picture.

**Course-up.** The top of the screen is held on a chosen course or direction (many sets hold your intended track). The
picture stays steady while you steer along that course and re-sets when you choose a new one. It also needs a heading
input.

The rule of thumb: ==any stabilised mode needs a heading signal==. If the heading input is wrong or freezes, the picture
is wrong without looking wrong, because every bearing is turned by the same error. Compare the radar's heading marker
with the compass when you take over the watch.

## Motion: who is moving on the screen?

On a ==relative motion== display, own ship stays at one spot (usually the centre) and every other object moves relative
to it. A buoy on the beam drifts down the screen at your speed, and that is the display being honest. A ship on a
steady bearing that closes simply moves straight in towards the centre. This is the display that plotting is built on
(the next lessons).

On a ==true motion== display, own ship moves across the screen and stationary objects stay put, while moving targets
show their true tracks. When own ship nears the screen edge the picture is redrawn. It is easier to see what is a
fixed buoy and what is a moving boat, but it needs reliable heading and speed inputs.

## How accurate is the picture?

Radar ranges are timing measurements, so they are good. Bearings depend on a beam that has a width, so ==ranges are more
accurate than bearings==, and typical radar bearing accuracy is a few degrees (the textbook figure is about 5°). That is
why position fixing uses ranges first (see the fixing lesson).

Two distortions make every echo larger than its target. The beam width spreads each echo sideways by the horizontal beam
width, and the pulse length stretches it in range by at least half the pulse length.

Worked example (invented numbers).

1. Beam width 2° at 4.0 nm. Sideways spread ≈ 4.0 × tan 2° = 4.0 × 0.0349 = **0.14 nm**, which is about 260 m.
2. Pulse of 0.5 microsecond. Half the pulse length ≈ 0.5 × 164 yards = **82 yards**, about 75 m.
3. So a 20 m boat at 4 nm may show as a smear 260 m wide and 75 m deep. It is easy to see it as bigger, and
   easy to merge it with another target beside it.

## False echoes: the standard lies

**Indirect (ghost) echo.** The beam bounces off part of your own ship, or a big ship nearby, before it reaches the target.
The false echo appears on the bearing of the reflecting surface at the same range as the real target. They often turn
up in the shadow sectors and move in a way that makes no sense for a real vessel.

**Multiple echo.** A large, close, strong target returns an echo that bounces back and forth. You see a row of echoes on
the same bearing at two, three or more times the true range, each weaker than the last. It is common when another vessel
passes close on a parallel course.

**Side-lobe echo.** Energy leaking from the sides of the beam makes a row or arc of echoes either side of a large,
close target, at the same range. It is mostly a problem within a few miles. Reducing gain or the sea control often
shrinks it, but may also reduce real detection.

**Second-trace echo.** A strong echo from a target beyond the range you have selected arrives after the next pulse has
gone out, so it is drawn at the wrong (nearer) range. Changing the pulse repetition rate moves a second-trace echo to
a new range, which a real target never does. It is unusual, and normally shows only in abnormal atmospheric conditions.

**Virtual image.** Passing close to a large metal structure, such as a steel bridge, can produce a mirror image of a
nearby target. It is often only temporary.

## Shadow and blind sectors

A funnel, mast or crane near the antenna blocks part of the beam. In that ==shadow sector== the radar can miss a small
nearby target, even though larger, farther ones still show. Know your ship's blind arcs (many bridges post them) and
cross-check those directions with a look-out.

## What radar misses

Metal returns better than wood or fibreglass, and a vessel seen broadside returns a stronger echo than one end-on.
Low, flat land and smooth beaches give weak returns, while wooded islands show strongly. Small boats near shore can merge
with the shoreline echo, and in rough sea clutter even a growler large enough to hurt a boat can go unseen. Rule 6(b)
says as much: small vessels, ice and other floating objects may not be detected at an adequate range.

## BC example (invented)

A 20 m work vessel is passing under the Second Narrows bridges in Burrard Inlet in light rain. A second echo appears
close to a tanker, on the same bearing but at a different range, then vanishes once the bridge is astern. The mate
checks by eye and with the VRM: only one tanker is there. The extra echo was a mirror image from the steel structure.
Keep your head up and compare the screen with what you can see before changing course for a ghost.

## Common mistakes

- Reading a head-up screen as true bearings. They are relative to the heading.
- Trusting a stabilised screen without checking the heading input.
- Calling any unexplained echo "just clutter". Check it by eye, by range and by moving the range scale.
- Assuming a clear screen means clear water.
- Forgetting your own ship's shadow sectors.

## Check yourself

1. Which display modes need a heading input?
2. On a relative motion display, how does a fixed buoy appear to move?
3. Two strong echoes sit on the same bearing at 1.5 nm and 3.0 nm, the second weaker. What is probably happening?
4. Which is more accurate, a radar range or a radar bearing, and why?
5. Name two kinds of target radar may miss.

---

*Study aid. Based on US Government radar texts (Bowditch, NGA Pub. 310) and manufacturer documentation read through
summaries, and on Rule 6 of the Collision Regulations. It is not the official text.*
