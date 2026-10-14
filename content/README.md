# Content

Everything the app teaches lives here as data files. Read `CLAUDE.md` content rules before writing anything.

- `sources.json` and `sources/<lane>.json` — every source content may cite. Add new sources in your own `sources/<lane>.json` first.
- `<module>/*.md` — lessons. YAML frontmatter validated by `schemas/lesson.schema.json`.
- `<module>/questions*.json` — original multiple-choice questions, `schemas/question.schema.json`.
- `charts/<id>.chart.json` and `charts/<id>.exercises.json` — original fictional training charts and their graded exercises, `schemas/chart.schema.json` and `schemas/chart-exercise.schema.json`. Never copied from CHS charts; see `docs/plan.md` (Maps and charts).
- `mock-exams/*.json` — practice exam blueprints, `schemas/mock-exam.schema.json`.

Modules: `colregs`, `tides`, `bridge-watch`, `sixty-ton`.

Validate with `python3 tools/lint_content.py`.
