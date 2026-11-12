"""Shared loading and validation for NauticleBc content. Used by lint_content.py and build_bundle.py."""
from __future__ import annotations

import json
import re
from dataclasses import dataclass, field
from pathlib import Path

import yaml
from jsonschema import Draft202012Validator, FormatChecker
from referencing import Registry, Resource
from referencing.jsonschema import DRAFT202012

ROOT = Path(__file__).resolve().parent.parent
SCHEMA_DIR = ROOT / "schemas"
CONTENT_DIR = ROOT / "content"

FRONTMATTER_RE = re.compile(r"\A---\n(.*?)\n---\n?(.*)\Z", re.DOTALL)
MAX_QUOTE_WORDS = 60


@dataclass
class Report:
    errors: list[str] = field(default_factory=list)
    warnings: list[str] = field(default_factory=list)

    def error(self, where: str, msg: str) -> None:
        self.errors.append(f"{where}: {msg}")

    def warn(self, where: str, msg: str) -> None:
        self.warnings.append(f"{where}: {msg}")


@dataclass
class Content:
    sources: dict[str, dict] = field(default_factory=dict)
    lessons: list[dict] = field(default_factory=list)  # frontmatter + "body" + "path"
    questions: list[dict] = field(default_factory=list)  # question + "module" + "path"
    mock_exams: list[dict] = field(default_factory=list)
    charts: list[dict] = field(default_factory=list)  # synthetic charts + "path"
    chart_exercises: list[dict] = field(default_factory=list)  # exercise + "chart_id" + "path"
    report: Report = field(default_factory=Report)


def _rel(path: Path) -> str:
    return str(path.relative_to(ROOT))


def _registry() -> Registry:
    registry = Registry()
    for schema_path in sorted(SCHEMA_DIR.glob("*.schema.json")):
        schema = json.loads(schema_path.read_text(encoding="utf-8"))
        registry = registry.with_resource(schema["$id"], Resource.from_contents(schema, DRAFT202012))
    return registry


def _validator(name: str, registry: Registry) -> Draft202012Validator:
    schema = json.loads((SCHEMA_DIR / f"{name}.schema.json").read_text(encoding="utf-8"))
    return Draft202012Validator(schema, registry=registry, format_checker=FormatChecker())


def _validate(validator: Draft202012Validator, data, where: str, report: Report) -> bool:
    ok = True
    for err in sorted(validator.iter_errors(data), key=lambda e: list(e.absolute_path)):
        loc = "/".join(str(p) for p in err.absolute_path) or "(root)"
        report.error(where, f"{loc}: {err.message}")
        ok = False
    return ok


def _load_json(path: Path, report: Report):
    try:
        return json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as exc:
        report.error(_rel(path), f"cannot read JSON: {exc}")
        return None


def _check_quotes(body: str, where: str, report: Report) -> None:
    quote_lines: list[str] = []
    for line in body.splitlines() + [""]:
        if line.lstrip().startswith(">"):
            quote_lines.append(line.lstrip()[1:].strip())
            continue
        words = len(" ".join(quote_lines).split())
        if words > MAX_QUOTE_WORDS:
            report.error(where, f"block quote of {words} words exceeds {MAX_QUOTE_WORDS}; paraphrase instead")
        quote_lines = []


FIGURE_DIR = CONTENT_DIR / "figures"
FIGURE_REF_RE = re.compile(r"^\{\{figure:([a-z0-9-]+)\}\}$", re.MULTILINE)
_seen_figures: dict[str, str] = {}


def _check_figures(meta: dict, body: str, where: str, report: Report) -> None:
    """Each declared figure needs an .svg and .png, a unique id and exactly one {{figure:id}} line in the body."""
    declared = [f["id"] for f in meta.get("figures", [])]
    used = FIGURE_REF_RE.findall(body)
    for fid in declared:
        if fid in _seen_figures:
            report.error(where, f"figure id {fid} also used in {_seen_figures[fid]}")
        _seen_figures[fid] = where
        for ext in ("svg", "png"):
            if not (FIGURE_DIR / f"{fid}.{ext}").is_file():
                report.error(where, f"figure {fid}: missing content/figures/{fid}.{ext}")
        if used.count(fid) != 1:
            report.error(where, f"figure {fid}: needs exactly one {{{{figure:{fid}}}}} line in the body (found {used.count(fid)})")
    for fid in used:
        if fid not in declared:
            report.error(where, f"body places figure {fid} that is not listed in frontmatter figures")


def load_content() -> Content:
    _seen_figures.clear()
    c = Content()
    r = c.report
    registry = _registry()
    v_source = _validator("source", registry)
    v_lesson = _validator("lesson", registry)
    v_question = _validator("question", registry)
    v_mock = _validator("mock-exam", registry)
    v_chart = _validator("chart", registry)
    v_chart_ex = _validator("chart-exercise", registry)
    modules = set(json.loads((SCHEMA_DIR / "common.schema.json").read_text())["$defs"]["module"]["enum"])

    # Sources: content/sources.json plus any content/sources/*.json (one file per lane avoids merge conflicts)
    src_paths = [CONTENT_DIR / "sources.json", *sorted((CONTENT_DIR / "sources").glob("*.json"))]
    for src_path in src_paths:
        data = _load_json(src_path, r)
        if data is not None and _validate(v_source, data, _rel(src_path), r):
            for s in data["sources"]:
                if s["id"] in c.sources:
                    r.error(_rel(src_path), f"duplicate source id {s['id']}")
                c.sources[s["id"]] = s

    # Lessons
    for path in sorted(CONTENT_DIR.rglob("*.md")):
        if path.name == "README.md":
            continue
        where = _rel(path)
        match = FRONTMATTER_RE.match(path.read_text(encoding="utf-8"))
        if not match:
            r.error(where, "missing YAML frontmatter")
            continue
        try:
            meta = yaml.safe_load(match.group(1)) or {}
        except yaml.YAMLError as exc:
            r.error(where, f"invalid YAML frontmatter: {exc}")
            continue
        # YAML parses unquoted dates into date objects; schema wants strings.
        if "reviewed_on" in meta:
            meta["reviewed_on"] = str(meta["reviewed_on"])
        if not _validate(v_lesson, meta, where, r):
            continue
        body = match.group(2).strip()
        if not body:
            r.error(where, "lesson body is empty")
        if body.count("==") % 2 != 0:
            r.error(where, "unbalanced '==' highlight markers in body")
        if "key_facts" not in meta:
            r.warn(where, f"{meta['id']}: no key_facts (list the facts a candidate could be tested on)")
        _check_quotes(body, where, r)
        _check_figures(meta, body, where, r)
        folder = path.relative_to(CONTENT_DIR).parts[0]
        if folder in modules and meta["module"] != folder:
            r.error(where, f"module '{meta['module']}' does not match folder '{folder}'")
        c.lessons.append({**meta, "body": body, "path": where})

    # Questions
    for path in sorted(CONTENT_DIR.rglob("questions*.json")):
        where = _rel(path)
        data = _load_json(path, r)
        if data is None or not _validate(v_question, data, where, r):
            continue
        folder = path.relative_to(CONTENT_DIR).parts[0]
        if folder in modules and data["module"] != folder:
            r.error(where, f"module '{data['module']}' does not match folder '{folder}'")
        for q in data["questions"]:
            if q["correct_index"] >= len(q["options"]):
                r.error(where, f"{q['id']}: correct_index {q['correct_index']} out of range")
            if len({o.strip().lower() for o in q["options"]}) != len(q["options"]):
                r.error(where, f"{q['id']}: duplicate options")
            c.questions.append({**q, "module": data["module"], "path": where})

    # Mock exams
    for path in sorted((CONTENT_DIR / "mock-exams").glob("*.json")):
        where = _rel(path)
        data = _load_json(path, r)
        if data is None or not _validate(v_mock, data, where, r):
            continue
        if sum(b["count"] for b in data["blueprint"]) != data["question_count"]:
            r.error(where, "blueprint counts do not add up to question_count")
        c.mock_exams.append({**data, "path": where})

    # Synthetic charts and their exercises
    for path in sorted((CONTENT_DIR / "charts").glob("*.chart.json")):
        where = _rel(path)
        data = _load_json(path, r)
        if data is not None and _validate(v_chart, data, where, r):
            c.charts.append({**data, "path": where})
    for path in sorted((CONTENT_DIR / "charts").glob("*.exercises.json")):
        where = _rel(path)
        data = _load_json(path, r)
        if data is None or not _validate(v_chart_ex, data, where, r):
            continue
        for ex in data["exercises"]:
            c.chart_exercises.append({**ex, "chart_id": data["chart_id"], "path": where})

    _cross_check(c)
    _check_charts(c)
    return c


def _in_extent(pos: dict, ext: dict) -> bool:
    return ext["south"] <= pos["lat"] <= ext["north"] and ext["west"] <= pos["lon"] <= ext["east"]


def _check_charts(c: Content) -> None:
    """Charts: unique ids, everything inside the extent. Exercises: known chart and marks, givens inside the extent."""
    r = c.report
    charts: dict[str, dict] = {}
    for ch in c.charts:
        if ch["id"] in charts:
            r.error(ch["path"], f"duplicate chart id {ch['id']}")
        charts[ch["id"]] = ch
        ext = ch["extent"]
        if ext["south"] >= ext["north"] or ext["west"] >= ext["east"]:
            r.error(ch["path"], "extent must have south < north and west < east")
        for ext_ in ("svg", "png"):
            if not (CONTENT_DIR / "charts" / f"{ch['id']}.{ext_}").is_file():
                r.error(ch["path"], f"chart {ch['id']}: missing content/charts/{ch['id']}.{ext_} (run the chart generator, then tools/render_figures.py)")
        ids: set[str] = set()
        for group in ("compass_roses", "hazards", "aids"):
            for item in ch[group]:
                if item["id"] in ids:
                    r.error(ch["path"], f"duplicate id {item['id']} on chart {ch['id']}")
                ids.add(item["id"])
                if not _in_extent(item["position"], ext):
                    r.error(ch["path"], f"{item['id']}: position outside the chart extent")
        for s in ch["soundings"]:
            if not _in_extent(s["position"], ext):
                r.error(ch["path"], f"sounding at {s['position']} is outside the chart extent")
        for land in ch["land"]:
            for p in land["ring"]:
                if not _in_extent(p, ext):
                    r.warn(ch["path"], f"land '{land.get('name', land['kind'])}' has a point outside the extent (allowed, runs off the chart)")
                    break
        for src in ch.get("source_ids", []):
            if src not in c.sources:
                r.error(ch["path"], f"chart {ch['id']}: unknown source id '{src}'")
    seen: dict[str, str] = {}
    for ex in c.chart_exercises:
        label = ex["id"]
        if label in seen:
            r.error(ex["path"], f"duplicate chart exercise id {label} (also in {seen[label]})")
        seen[label] = ex["path"]
        chart = charts.get(ex["chart_id"])
        if chart is None:
            r.error(ex["path"], f"{label}: unknown chart_id {ex['chart_id']}")
            continue
        marks = {a["id"] for a in chart["aids"]} | {h["id"] for h in chart["hazards"]}
        g = ex["givens"]
        if "start_position" in g and not _in_extent(g["start_position"], chart["extent"]):
            r.error(ex["path"], f"{label}: start_position is outside the chart extent")
        for b in g.get("bearings", []):
            if b["mark_id"] not in marks:
                r.error(ex["path"], f"{label}: bearing to unknown mark {b['mark_id']}")
            if "bearing_compass" not in b and "bearing_true" not in b:
                r.error(ex["path"], f"{label}: bearing to {b['mark_id']} needs bearing_compass or bearing_true")
        for a in ex["answers"]:
            if a["kind"] == "position" and not (isinstance(a["value"], dict) and "lat" in a["value"]):
                r.error(ex["path"], f"{label}: answer '{a['label']}' is kind position but value is not a position")
            if a["kind"] == "position" and isinstance(a["value"], dict) and not _in_extent(a["value"], chart["extent"]):
                r.error(ex["path"], f"{label}: answer '{a['label']}' lies outside the chart extent")
            if a["kind"] in ("bearing", "distance", "time", "height", "number") and not isinstance(a["value"], (int, float)):
                r.error(ex["path"], f"{label}: answer '{a['label']}' needs a numeric value")
            if a["kind"] != "text" and "tolerance" not in a:
                r.error(ex["path"], f"{label}: answer '{a['label']}' needs a tolerance")
        for sid in ex.get("source_ids", []):
            if sid not in c.sources:
                r.error(ex["path"], f"{label}: unknown source id '{sid}'")


def _check_mock_draws(c: Content) -> None:
    """A mock with a fixed `question_ids` draw must be playable exactly as written."""
    r = c.report
    by_id = {q["id"]: q for q in c.questions}
    for m in c.mock_exams:
        ids = m.get("question_ids")
        if not ids:
            continue
        where = m["path"]
        if len(ids) != m["question_count"]:
            r.error(where, f"{m['id']}: question_ids has {len(ids)} ids but question_count is {m['question_count']}")
        per_module: dict[str, int] = {}
        for qid in ids:
            q = by_id.get(qid)
            if q is None:
                r.error(where, f"{m['id']}: unknown question id {qid}")
                continue
            if m["ticket"] not in q.get("tickets", []):
                r.error(where, f"{m['id']}: {qid} is not tagged for ticket {m['ticket']}")
            per_module[q["module"]] = per_module.get(q["module"], 0) + 1
        planned = {b["module"]: b["count"] for b in m["blueprint"]}
        if all(qid in by_id for qid in ids) and per_module != planned:
            r.error(where, f"{m['id']}: question_ids per module {per_module} does not match blueprint {planned}")


def _cross_check(c: Content) -> None:
    r = c.report
    lesson_ids: dict[str, str] = {}
    for les in c.lessons:
        if les["id"] in lesson_ids:
            r.error(les["path"], f"duplicate lesson id {les['id']} (also in {lesson_ids[les['id']]})")
        lesson_ids[les["id"]] = les["path"]

    question_ids: dict[str, str] = {}
    for q in c.questions:
        if q["id"] in question_ids:
            r.error(q["path"], f"duplicate question id {q['id']} (also in {question_ids[q['id']]})")
        question_ids[q["id"]] = q["path"]
        if q["lesson_id"] not in lesson_ids:
            r.error(q["path"], f"{q['id']}: unknown lesson_id {q['lesson_id']}")

    for item, label in (
        *((les, les["id"]) for les in c.lessons),
        *((q, q["id"]) for q in c.questions),
        *((m, m["id"]) for m in c.mock_exams),
    ):
        for sid in item["source_ids"]:
            src = c.sources.get(sid)
            if src is None:
                r.error(item["path"], f"{label}: unknown source id '{sid}' (add it to content/sources.json first)")
            elif item["review_status"] == "expert_reviewed" and not src["read_directly"]:
                r.warn(item["path"], f"{label}: reviewed content cites '{sid}', which the author did not read directly")

    _check_mock_draws(c)

    for les in c.lessons:
        for pre in les.get("prerequisites", []):
            if pre not in lesson_ids:
                r.error(les["path"], f"{les['id']}: unknown prerequisite {pre}")

    orders: dict[tuple[str, int], str] = {}
    for les in c.lessons:
        key = (les["module"], les["order"])
        if key in orders:
            r.warn(les["path"], f"{les['id']}: same order {les['order']} as {orders[key]} in module {les['module']}")
        orders[key] = les["id"]

    for les in c.lessons:
        if not any(q["lesson_id"] == les["id"] for q in c.questions):
            r.warn(les["path"], f"{les['id']}: no questions yet")
