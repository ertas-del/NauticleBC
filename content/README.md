# Content

Everything the app teaches lives here as data files. Read `CLAUDE.md` content rules before writing anything.

- `sources.json` — every source content may cite. Add the source here first.
- `<module>/*.md` — lessons. YAML frontmatter validated by `schemas/lesson.schema.json`.
- `<module>/questions*.json` — original multiple-choice questions, `schemas/question.schema.json`.
- `mock-exams/*.json` — practice exam blueprints, `schemas/mock-exam.schema.json`.

Modules: `colregs`, `tides`, `bridge-watch`, `sixty-ton`.

Validate with `python3 tools/lint_content.py`.
