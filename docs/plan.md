# Plan

The living plan is the Claude Docs document "Marine Ticket Prep App: Plan"
(https://claude.ai/code/artifact/19398f14-8ff9-4bee-8c45-8a80b032975e). This file is a short in-repo summary so
every session sees the same scope.

## First release

- Audience: candidates for **Bridge Watch Rating** and **Master Limited < 60 GT** in British Columbia.
- Modules: **COLREGs** (lessons, drills, mock quiz) and **Tides** (science, reading tables, BC regions overview).
- Platform: Android first (Kotlin + Compose). Content is data-driven for a later iOS port.

## Later (not in the first build)

Oral exam tester (speech recognition later), knots, SVOP, SVMO (video + text-to-speech audio), flags, radio calls,
lights and lighthouses map, sea surface temperature, BC learning map, synthetic chartwork simulator (see Maps and charts),
marine weather, towing logs.

## On hold at the owner's request

Marine first aid, MED module, MED practice. Kept in the plan, not scheduled.

## Build order

1. Foundation: repo, schemas, lint, Android skeleton, CI (this PR).
2. Content engine: bundle builder, in-app lesson reader, quiz engine, progress storage.
3. In parallel after schemas merge: COLREGs content, COLREGs drills, Tides content/tools, QA and licences.

## Maps and charts (decided 2026-10-13, replaces the CHS nautical chart layer)

There is **no CHS nautical chart layer** and no chart licence gate. Two separate environments instead:

1. **BC learning map** (real places, later). Built only from independently permitted open data: coastline and islands
   (OpenStreetMap or a BC open-government dataset), lights and lighthouses from the Coast Guard List of Lights, tide
   stations and tidal passes as regional learning content, sea-surface temperature from DFO lightstation records and
   NOAA satellite products. CHS NONNA bathymetry is an optional source for independently drawn depth shading, only after
   its terms are confirmed. Always labelled "Learning map, not for navigation". Missing data is left out, never invented
   and never copied from a CHS chart.
2. **Synthetic chartwork simulator** (fictional places). Original training charts, deliberately placed where no real land
   exists so they cannot be mistaken for BC. Each chart is one data file (coastline, depths, hazards, lights, buoys,
   compass roses with variation, legend). A script generates the drawing, and the exercises and their answer keys come
   from the same numbers, with stated tolerances, so chart, question and answer cannot disagree. Always labelled
   "Fictional training chart, not for navigation". Tools: virtual parallel rule, dividers and plotter.

Real CHS tide and current predictions stay a separate, unresolved licence question. Until cleared, tide exercises use
synthetic tables and the BC atlas shows regional learning content and links, never invented predictions.

### Order

1. Schema for charts and chart exercises (own PR), lint and bundle support.
2. One proof chart, **Training Harbour**, with a handful of graded exercises, to show draw-and-grade stays consistent.
3. Random-number drills that need no chart: compass conversion (TVMDC), depth and tide sums, rule of twelfths,
   speed, distance and time.
4. App: chart view with the plotting tools and grading.
5. Remaining training charts (Narrows, Islands, Coastal Leg), then the real BC learning map and lights layer once each
   data licence is read and recorded in `docs/licence-checklist.md`.

Every synthetic exercise needs expert review of its method and tolerance before it is marked `expert_reviewed`.
