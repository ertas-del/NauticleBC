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
lights and lighthouses map, sea surface temperature, BC coast map hub with nautical chart layer (needs CHS licence),
marine weather, towing logs.

## On hold at the owner's request

Marine first aid, MED module, MED practice. Kept in the plan, not scheduled.

## Build order

1. Foundation: repo, schemas, lint, Android skeleton, CI (this PR).
2. Content engine: bundle builder, in-app lesson reader, quiz engine, progress storage.
3. In parallel after schemas merge: COLREGs content, COLREGs drills, Tides content/tools, QA and licences.
