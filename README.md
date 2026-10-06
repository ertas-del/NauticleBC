# NauticleBc

Android app for learning and prepping Canadian marine tickets in British Columbia.

First release scope: **Bridge Watch Rating** and **Master Limited < 60 GT**, starting with **COLREGs** and **tides**.

A study aid only. It is not official training, not an approved course, and not for navigation.

## Layout

- `app/` Android app (Kotlin, Jetpack Compose)
- `content/` lessons, questions, sources, as plain data files
- `schemas/` JSON Schemas for all content
- `tools/` content lint and bundle builder (Python)
- `docs/` plan, licence checklist, parallel workflow
- `CLAUDE.md` rules for every contributor, human or Claude

## Working on content

```bash
pip install -r tools/requirements.txt
python3 tools/lint_content.py      # validate content against schemas and source rules
python3 tools/build_bundle.py      # write app/src/main/assets/content/bundle.json
```

## Building the app

Needs JDK 17, the Android SDK, Gradle 8.10+ and Python 3 (the build runs the bundle builder first).

```bash
gradle :app:testDebugUnitTest
gradle :app:assembleDebug
```

No Gradle wrapper is committed yet. Generate one with `gradle wrapper --gradle-version 8.10.2` and commit it.
Android Studio (Ladybug or newer) can open the repo root directly.

## Contributing with several Claudes at once

See `docs/workflow.md`.
