---
id: stability.metacentric-height
title: "Metacentric height (GM)"
module: stability
tickets: [master-limited-60gt]
order: 30
summary: GM is the gap between G and M. This lesson shows how to find KG from a table of weights and moments, how to get GM = KM - KG, what negative GM means, and how GM makes a vessel stiff or tender.
objectives:
  - Calculate KG for a loaded vessel from a table of weights, heights and moments.
  - Use GM = KM - KG, and say whether a given GM means stable, neutral or unstable.
  - Compare stiff and tender vessels by roll, comfort and risk, and say why too much GM is not a goal.
  - Explain what happens to a vessel with a negative GM (angle of loll).
prerequisites: [stability.g-b-and-m]
estimated_minutes: 24
key_facts:
  - Metacentric height GM = KM - KG, the vertical distance from G up to M.
  - A positive GM means a stable vessel; zero is neutral; a negative GM means an unstable vessel.
  - KG = total moment / total weight, where each moment is weight x height of its centre above the keel (t x m = t.m).
  - KM is read from the vessel's hydrostatic data for the draught; it does not depend on how the cargo is stowed.
  - A large GM makes a stiff vessel with a short, sharp roll period; a small GM makes a tender vessel with a long, slow roll period.
  - A very large GM is not ideal; it gives violent snap rolling that strains the vessel, loads and people.
  - A vessel with a negative GM will not rest upright; it settles at an angle of loll, and may flop from side to side or capsize.
  - Anything that raises KG (top weight, ice, burning low fuel, slack tanks) cuts GM by the amount of the rise, for the same KM.
source_ids: [sixty-fao-ftp517-stability, stab-fao-ftp517-ch6-example, stab-mt4241-stability-trim, stab-wikipedia-metacentric-height, stab-wartsila-intact-criteria]
review_status: needs_expert_review
---

# Metacentric height (GM)

**Study aid only.** Every figure is invented. Real GM values come from your vessel's own stability documents.

## What you need to know

GM is the single most useful stability number you can know: it is the gap between the centre of gravity G and the metacentre M. It tells you whether the vessel is stable, and, when it is, how strongly it fights to stay upright. This lesson is mostly arithmetic, so work through the examples with a pencil.

## GM = KM - KG

From the last lesson, K is the keel, G is the centre of gravity and M is the metacentre. Heights are measured up from the keel. The gap between G and M is therefore the difference of two heights:

==GM = KM - KG==

==KM== comes from the hull: it depends on the draught and is read from the vessel's hydrostatic table or curves. ==KG== comes from the loading: it depends on what is aboard and where. Stowing the same cargo higher or lower changes KG but not KM.

Sign tells the story. ==Positive GM: stable.== ==GM zero: neutral.== ==Negative GM: unstable.==

### Worked example 1: a positive GM

KM = 2.40 m and KG = 1.95 m. GM = 2.40 - 1.95 = **+0.45 m**. Stable. G is 0.45 m below M.

### Worked example 2: a negative GM

KM = 2.10 m and KG = 2.25 m. GM = 2.10 - 2.25 = **-0.15 m**. Unstable. M is 0.15 m below G.

## Finding KG: weights and moments

You cannot just guess KG. You find it as a weighted average of the heights of everything aboard. Each item has a weight (tonnes) and a ==KG of its own== (the height of its own centre above the keel, in metres). Multiply to get its ==moment== (tonne-metres, written t.m). Add up all the weights and all the moments:

==KG = total moment / total weight==

This is the same thing as finding the balance point of a loaded plank. It works because a weight twice as high gives twice the leverage on the balance point.

### Worked example 3: KG for the Tern

"Example Tern" (an invented 12 m workboat) leaves harbour with a catch on deck. Its hydrostatic table gives KM = 2.40 m at 40 t.

| Item | Weight (t) | KG (m) | Moment (t.m) |
|---|---|---|---|
| Lightship (hull, engine, fixed gear) | 30.0 | 2.10 | 63.00 |
| Fuel | 4.0 | 0.60 | 2.40 |
| Fresh water | 1.0 | 0.50 | 0.50 |
| Crew, gear and stores | 2.0 | 2.60 | 5.20 |
| Catch on deck | 3.0 | 2.30 | 6.90 |
| **Total** | **40.0** | | **78.00** |

1. Total weight = 30.0 + 4.0 + 1.0 + 2.0 + 3.0 = **40.0 t**. (This is the displacement.)
2. Total moment = 63.00 + 2.40 + 0.50 + 5.20 + 6.90 = **78.00 t.m**.
3. KG = 78.00 / 40.0 = **1.95 m**.
4. GM = KM - KG = 2.40 - 1.95 = **0.45 m**. Stable.

We will keep coming back to this vessel and these numbers.

### Worked example 4: the vessel changes through the trip

On the way home the Tern has burned fuel and water. Fuel is down to 1.5 t (at KG 0.60 m) and water to 0.5 t (at KG 0.50 m). The catch is still on deck. Displacement is now 37.0 t, and the hydrostatic table gives KM = 2.36 m at that weight. Departure (no catch) was 37.0 t too, so we can compare:

| Condition | Weight (t) | Total moment (t.m) | KG (m) | KM (m) | GM (m) |
|---|---|---|---|---|---|
| Departure, no catch | 37.0 | 71.10 | 1.922 | 2.36 | **0.438** |
| Leaving with catch (Example 3) | 40.0 | 78.00 | 1.950 | 2.40 | **0.450** |
| Homeward, fuel and water burned | 37.0 | 76.25 | 2.061 | 2.36 | **0.299** |

The homeward moment: 63.00 + 0.90 + 0.25 + 5.20 + 6.90 = 76.25 t.m, and 76.25 / 37.0 = 2.061 m. GM falls from 0.450 m to 0.299 m, a loss of about a third, simply because low fuel and water were burned away while the high deck load stayed. Nothing "went wrong". Many a vessel is at its weakest on the way home. The lesson: ==check stability in the worst condition of the trip, not only the start==.

## Stiff and tender

GM tells you how hard the vessel fights to come upright, and that you can feel. A vessel with a ==large GM is stiff==. It returns strongly to upright, so it rolls with a ==short period== (quick, jerky, sharp) and with high acceleration. That is uncomfortable, and it can strain lashings and break loose gear. A vessel with a ==small GM is tender==. It returns weakly, so it rolls with a ==long period== (slow, lazy) and leans a long way for a small push. Tender is safer than negative but much closer to trouble.

Neither extreme is the goal: ==enough stability, but not too much==. A very stiff vessel is safe against capsizing but unpleasant, can damage cargo and tire the crew. On the other hand, a vessel's roll period is a quick way to judge GM at sea (see the rolling period lesson).

## Negative GM and the angle of loll

If GM is negative, an upright vessel is like the marble on top of the dome. The slightest push starts it over, and the turning effect pushes it further. As it heels, the hull's shape gradually begins to help (the wedge of immersed hull on the low side grows and B moves outward), and at some angle the vessel finds a new balance. That is the ==angle of loll==. The vessel sits there, leaning, and may flop over to the other side when a sea hits it (it has two possible angles of loll, one on each side). A vessel that is lolling is on the edge. The cure is to lower G: take off top weight, press up slack tanks, and add low weight such as filling low tanks. Textbooks advise doing it on the low side first, because a sudden change of weight on the high side can make the vessel flop over to the other side. Shifting weight about carelessly, or hauling the vessel upright with a heavy pull, is the wrong response. Treat it as an emergency and follow the master's orders. (An examiner or instructor may want the exact order of steps; this lesson gives the principle only.)

## What changes GM at sea

KM changes little in service. KG changes a lot. A rise of KG cuts GM by the same amount (for the same KM):

- Taking on top weight (deck cargo, ice, water on deck) raises KG.
- Using fuel and water from low tanks raises KG.
- Slack tanks have a ==virtual rise of G==, which cuts the effective GM (a later lesson).
- Landing high weight or filling low tanks lowers KG and raises GM.

## What is a "good" GM?

There is no single figure. It depends on the vessel type and size and is set in its stability documents. For example, the IMO's 2008 intact stability code general criteria for passenger and cargo ships quote a minimum initial GM of **0.15 m**, but that is a minimum for large vessels, with other criteria alongside. A small workboat or fishing vessel is judged against its own standard. Never decide a vessel is "safe" on a GM figure alone; GZ curve criteria matter too (next lesson).

## Exam traps

GM is "KM minus KG", not "KG minus KM". A bigger KG means a smaller GM. A bigger KM means a bigger GM. A stiff vessel has a short, quick roll, and a tender vessel a long, slow one; students swap them all the time. And a tender vessel is not necessarily unstable: tender means small positive GM.

## Check yourself

1. KM = 3.05 m and KG = 2.60 m. What is GM, and is the vessel stiff or tender? (0.45 m; moderate.)
2. A loading table has a total moment of 110 t.m and a total weight of 50 t. What is KG? (2.20 m.)
3. Which has the longer rolling period, a stiff or a tender vessel?
4. What is the name for the angle a vessel with negative GM settles at?
