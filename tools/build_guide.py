#!/usr/bin/env python3
"""Build a printable, illustrated PDF study guide for one ticket from the app's own content.

    python3 tools/build_guide.py svop            # -> dist/guides/svop-guide.pdf
    python3 tools/build_guide.py svop --final    # leave out the DRAFT marks (only after a ticket holder has read it)

The guide uses the same lessons, key facts, figures and questions as the app, so a fix to a lesson fixes both.
Parts and titles come from tools/guide/guides.yaml. Needs: PyYAML, Markdown, pypdf, Playwright with Chromium.
"""
from __future__ import annotations

import argparse
import html
import json
import re
import sys
from pathlib import Path

import markdown
import yaml
from playwright.sync_api import sync_playwright
from pypdf import PdfReader, PdfWriter

ROOT = Path(__file__).resolve().parent.parent
CONTENT = ROOT / "content"
FIGURES = CONTENT / "figures"
GUIDE = Path(__file__).resolve().parent / "guide"
OUT = ROOT / "dist" / "guides"

MARK_PREFIX = "NBCPAGEMARK-"
LETTERS = "ABCDEFGH"
SHORT_FOCUS = 5  # key facts that fit at the top of a lesson


# ---------- loading ----------

def read_lesson(path: Path) -> dict | None:
    text = path.read_text(encoding="utf-8")
    m = re.match(r"^---\n(.*?)\n---\n(.*)$", text, re.S)
    if not m:
        return None
    meta = yaml.safe_load(m.group(1)) or {}
    meta["body"] = m.group(2)
    meta["path"] = path
    return meta


def load(ticket: str) -> tuple[list[dict], dict[str, list[dict]], dict[str, dict]]:
    lessons = []
    for path in sorted(CONTENT.rglob("*.md")):
        if path.name == "README.md":
            continue
        meta = read_lesson(path)
        if meta and ticket in (meta.get("tickets") or []):
            lessons.append(meta)
    ids = {l["id"] for l in lessons}
    questions: dict[str, list[dict]] = {}
    for path in sorted(CONTENT.rglob("questions-*.json")):
        data = json.loads(path.read_text(encoding="utf-8"))
        for q in data.get("questions", []):
            if q.get("lesson_id") in ids and (ticket in (q.get("tickets") or []) or not q.get("tickets")):
                questions.setdefault(q["lesson_id"], []).append(q)
    sources: dict[str, dict] = {}
    files = [CONTENT / "sources.json", *sorted((CONTENT / "sources").glob("*.json"))]
    for path in files:
        for s in json.loads(path.read_text(encoding="utf-8")).get("sources", []):
            sources[s["id"]] = s
    return lessons, questions, sources


def arrange(lessons: list[dict], parts_cfg: list[dict]) -> list[dict]:
    lessons = sorted(lessons, key=lambda l: (l.get("module", ""), l.get("order", 0), l["id"]))
    taken: set[str] = set()
    parts = []
    for cfg in parts_cfg:
        def rank(lesson: dict) -> int:
            return next(i for i, p in enumerate(cfg["prefixes"]) if lesson["id"].startswith(p))

        mine = [l for l in lessons if l["id"] not in taken and any(l["id"].startswith(p) for p in cfg["prefixes"])]
        # Prefix order first, so a part can take a whole topic and then a card from another module.
        mine.sort(key=lambda l: (rank(l), l.get("order", 0), l["id"]))
        taken.update(l["id"] for l in mine)
        if mine:
            parts.append({"title": cfg["title"], "intro": cfg.get("intro", ""), "lessons": mine})
    rest = [l for l in lessons if l["id"] not in taken]
    if rest:
        print(f"note: {len(rest)} lesson(s) not in any part, put in 'More topics'", file=sys.stderr)
        parts.append({"title": "More topics", "intro": "", "lessons": rest})
    return parts


# ---------- lesson body ----------

def body_html(lesson: dict, figure_list: list[str], pages: dict[str, int]) -> str:
    body = lesson["body"]
    # One disclaimer for the whole guide, not one per lesson.
    body = re.sub(r"\n---\s*\n\s*\*Study aid.*$", "\n", body, flags=re.S)
    # The lesson title is printed by the guide.
    body = re.sub(r"^\s*# .*\n", "", body, count=1)
    figs = {f["id"]: f for f in lesson.get("figures") or []}

    def figure(m: re.Match) -> str:
        fid = m.group(1)
        f = figs.get(fid, {})
        png = FIGURES / f"{fid}.png"
        if not png.exists():
            return ""
        cap = html.escape(f.get("caption", ""))
        if fid in figure_list:
            # Shown earlier in this guide: point back to it instead of printing it again.
            n = figure_list.index(fid) + 1
            page = pages.get(f"fig-{fid}")
            where = f" on page {page}" if page else " earlier in this guide"
            return f'\n\n<p class="see-fig">See Picture {n}, <em>{cap}</em>,{where}.</p>\n\n'
        figure_list.append(fid)
        n = len(figure_list)
        alt = html.escape(f.get("alt", cap), quote=True)
        # A tall picture gets more of the page, or its labels would print too small to read.
        from PIL import Image
        with Image.open(png) as im:
            tall = im.height > 1.3 * im.width
        cls = ' class="tall"' if tall else ""
        return (f'\n\n<figure{cls}>{mark("fig-" + fid)}<img src="{png.as_uri()}" alt="{alt}">'
                f'<figcaption>Picture {n}. {cap}</figcaption></figure>\n\n')

    body = re.sub(r"^\{\{figure:([a-z0-9-]+)\}\}\s*$", figure, body, flags=re.M)
    body = re.sub(r"==(.+?)==", r"<mark>\1</mark>", body)
    out = markdown.markdown(body, extensions=["tables", "fenced_code", "sane_lists"])
    # Boxes around the worked example and the mistakes, so the eye can find them.
    out = wrap_section(out, "A BC example", "callout")
    out = wrap_section(out, "Common mistakes", "callout mistakes")
    # Keep the short self-check together so one question never spills onto a page by itself.
    out = wrap_section(out, "Check yourself", "check")
    return out


def wrap_section(out: str, heading: str, cls: str) -> str:
    pat = re.compile(r"(<h2>" + re.escape(heading) + r"</h2>.*?)(?=<h2>|\Z)", re.S)
    return pat.sub(lambda m: f'<div class="{cls}">{m.group(1)}</div>', out, count=1)


# ---------- pages ----------

def esc(s: str) -> str:
    return html.escape(str(s))


def mark(key: str) -> str:
    return f'<p class="mark">{MARK_PREFIX}{key}</p>'


def toc_html(parts: list[dict], pages: dict[str, int]) -> str:
    rows = []
    for pi, part in enumerate(parts, 1):
        key = f"part-{pi}"
        rows.append(
            f'<li class="part"><a href="#{key}">Part {pi}. {esc(part["title"])}</a>'
            f'<span class="dots"></span><span class="num">{pages.get(key, "")}</span></li>'
        )
        for lesson in part["lessons"]:
            rows.append(
                f'<li class="lesson"><a href="#{esc(lesson["id"])}">{esc(lesson["title"])}</a>'
                f'<span class="dots"></span><span class="num">{pages.get(lesson["id"], "")}</span></li>'
            )
        rows.append(
            f'<li class="lesson"><a href="#test-{pi}">Test yourself: part {pi}</a>'
            f'<span class="dots"></span><span class="num">{pages.get(f"test-{pi}", "")}</span></li>'
        )
    if "next" in pages:
        rows.append(f'<li class="part"><a href="#next">Next step</a><span class="dots"></span><span class="num">{pages["next"]}</span></li>')
    rows.append(f'<li class="part"><a href="#answers">Answers</a><span class="dots"></span><span class="num">{pages.get("answers", "")}</span></li>')
    rows.append(f'<li class="part"><a href="#sources">Sources</a><span class="dots"></span><span class="num">{pages.get("sources", "")}</span></li>')
    return '<ul class="toc">' + "".join(rows) + "</ul>"


def build_html(cfg: dict, parts: list[dict], questions: dict[str, list[dict]], sources: dict[str, dict],
               pages: dict[str, int], draft: bool) -> str:
    title = cfg["title"]
    ticket_name = cfg["ticket_name"]
    q_count = sum(len(questions.get(l["id"], [])) for p in parts for l in p["lessons"])
    lesson_count = sum(len(p["lessons"]) for p in parts)
    figures: list[str] = []
    h: list[str] = []

    # How to use this guide
    h.append('<section>')
    h.append(f'<div class="kicker">{esc(ticket_name)} study guide</div><h1>How to use this guide</h1>')
    h.append(
        f'<p class="lead">This guide follows the {esc(ticket_name)} topics, one at a time: '
        f'{lesson_count} lessons in {len(parts)} parts, with pictures, the facts most likely to be asked, '
        f'and {q_count} practice questions with answers.</p>'
    )
    h.append(
        '<p>Each lesson has an <strong>Exam focus</strong> box with the facts most likely to be asked: at the start when it is short, at the end as a recap when it is long. '
        'Then read the lesson and look at its pictures. '
        'Words shown like <mark>this</mark> are the exact terms and numbers worth learning by heart. '
        'Each part ends with practice questions. Try them before you look at the answers at the back.</p>'
        '<p>Every lesson has a worked example from BC waters, and a short list of the mistakes people make.</p>'
    )
    h.append(
        '<div class="note"><strong>Please read.</strong> This is an independent study companion. '
        'It is not a Transport Canada course, it is not approved or endorsed by Transport Canada or any course provider, '
        'and it does not replace the approved course you must take. It is not for navigation: always use official, '
        'up-to-date charts, tide tables and publications on the water. Rules change; check the current regulations.</div>'
    )
    if draft:
        h.append(
            '<div class="draft"><strong>Draft for review.</strong> This copy has not yet been checked by a certified '
            'master or examiner. Do not sell or share it as a finished guide.</div>'
        )
    h.append("</section>")

    # Contents
    h.append('<section class="page"><h1>Contents</h1>' + toc_html(parts, pages) + "</section>")

    # Parts, lessons, tests
    for pi, part in enumerate(parts, 1):
        h.append(f'<section class="page part-opener" id="part-{pi}">{mark(f"part-{pi}")}')
        h.append(f'<p class="part-number">{pi}</p><h1>{esc(part["title"])}</h1>')
        if part["intro"]:
            h.append(f'<p class="lead">{esc(part["intro"])}</p>')
        h.append("<ol>" + "".join(f"<li>{esc(l['title'])}</li>" for l in part["lessons"]) + "</ol></section>")

        for li, lesson in enumerate(part["lessons"], 1):
            h.append(f'<section class="page" id="{esc(lesson["id"])}">{mark(lesson["id"])}')
            h.append(f'<div class="kicker">Part {pi} · Lesson {li}</div><h1>{esc(lesson["title"])}</h1>')
            if lesson.get("summary"):
                h.append(f'<p class="lead">{esc(lesson["summary"])}</p>')
            mins = lesson.get("estimated_minutes")
            if mins:
                h.append(f'<p class="meta">About {int(mins)} minutes to read and learn.</p>')
            objs = lesson.get("objectives") or []
            if objs:
                h.append('<div class="objectives"><h3>You will be able to</h3><ul>'
                         + "".join(f"<li>{esc(o)}</li>" for o in objs) + "</ul></div>")
            # A short Exam focus box goes up front so the reader knows what to look for.
            # A long one would fill the first page before the lesson starts, so it closes the lesson as a recap.
            facts = lesson.get("key_facts") or []
            box = "".join(f"<li>{esc(f)}</li>" for f in facts)
            if facts and len(facts) <= SHORT_FOCUS:
                h.append(f'<div class="exam-focus"><h3>Exam focus: learn these</h3><ul>{box}</ul></div>')
            h.append(body_html(lesson, figures, pages))
            if len(facts) > SHORT_FOCUS:
                h.append(f'<div class="exam-focus"><h3>Exam focus: check you know these</h3><ul>{box}</ul></div>')
            h.append("</section>")

        # Test yourself
        h.append(f'<section class="page" id="test-{pi}">{mark(f"test-{pi}")}')
        h.append(f'<div class="kicker">Part {pi}</div><h1>Test yourself</h1>')
        h.append('<p class="meta">Pick one answer for each. The answers and the reason for each are at the back.</p>')
        for lesson in part["lessons"]:
            for q in questions.get(lesson["id"], []):
                qn = q["_n"]
                opts = "".join(f"<li>{esc(o)}</li>" for o in q["options"])
                h.append(f'<div class="q"><div class="stem">{qn}. {esc(q["stem"])}</div><ol>{opts}</ol></div>')
        h.append("</section>")

    # Closing page: the next step after this ticket.
    closing = cfg.get("closing")
    if closing:
        h.append(f'<section class="page part-opener" id="next">{mark("next")}')
        h.append(f'<div class="kicker">Next step</div><h1>{esc(closing["title"])}</h1>')
        for i, para in enumerate(closing.get("paragraphs", [])):
            cls = ' class="lead"' if i == 0 else ""
            h.append(f"<p{cls}>{esc(para)}</p>")
        h.append("</section>")

    # Answers
    h.append(f'<section class="page answers" id="answers">{mark("answers")}<h1>Answers</h1>')
    for pi, part in enumerate(parts, 1):
        h.append(f"<h2>Part {pi}. {esc(part['title'])}</h2>")
        for lesson in part["lessons"]:
            for q in questions.get(lesson["id"], []):
                letter = LETTERS[q["correct_index"]]
                why = esc(q.get("explanation", ""))
                h.append(f'<p><span class="a-num">{q["_n"]}.</span> <span class="a-letter">{letter}</span> {why}</p>')
    h.append("</section>")

    # Sources
    used: list[str] = []
    for part in parts:
        for lesson in part["lessons"]:
            for sid in lesson.get("source_ids") or []:
                if sid not in used:
                    used.append(sid)
    h.append(f'<section class="page" id="sources">{mark("sources")}<h1>Sources</h1>')
    h.append("<p>The lessons are written in our own words from these publications. "
             "Read the originals for the full rules.</p><ul>")
    shown: set[str] = set()
    for sid in used:
        s = sources.get(sid, {"title": sid})
        # Readers need the publication, not our note on which sections or which file format we read.
        title = re.sub(r"\s*\((?:consolidated|Justice Laws|copy of)[^)]*\)", "", s["title"])
        title = re.sub(r"\s*:\s*(?:ss?\.|Part|Rules?|stability|whole|general).*$", "", title).strip()
        if title.lower() in shown:
            continue
        shown.add(title.lower())
        publisher = re.sub(r"\s*\([^)]*\)", "", (s.get("publisher") or "").split(";")[0]).strip()
        pub = f" ({esc(publisher)})" if publisher else ""
        h.append(f"<li>{esc(title)}{pub}</li>")
    h.append("</ul>")
    h.append('<div class="note">Made with NauticleBC, a study app for BC marine tickets. '
             'Pictures are study drawings, not to scale and not for navigation.</div></section>')

    css = (GUIDE / "guide.css").as_uri()
    return (f'<!doctype html><html lang="en"><head><meta charset="utf-8"><title>{esc(title)}</title>'
            f'<link rel="stylesheet" href="{css}"></head><body>{"".join(h)}</body></html>')


COVER_ART = """
<svg class="art" viewBox="0 0 816 520" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMax slice">
  <defs>
    <linearGradient id="sky" x1="0" y1="0" x2="0" y2="1"><stop offset="0" stop-color="#102a43"/><stop offset="1" stop-color="#2c5d73"/></linearGradient>
    <linearGradient id="sea" x1="0" y1="0" x2="0" y2="1"><stop offset="0" stop-color="#1f6f78"/><stop offset="1" stop-color="#0b3c49"/></linearGradient>
  </defs>
  <rect width="816" height="520" fill="url(#sky)"/>
  <circle cx="640" cy="150" r="46" fill="#fde68a" opacity="0.9"/>
  <path d="M0 300 L90 210 L160 260 L250 160 L330 240 L420 180 L520 260 L600 200 L700 270 L816 210 L816 330 L0 330 Z" fill="#24485c"/>
  <path d="M0 330 L70 280 L150 315 L240 255 L340 310 L450 262 L560 318 L660 270 L760 312 L816 290 L816 350 L0 350 Z" fill="#1b3a4b"/>
  <path d="M60 342 l14 -40 l14 40 Z M96 344 l11 -30 l11 30 Z M700 340 l13 -36 l13 36 Z M730 342 l10 -28 l10 28 Z" fill="#14303f"/>
  <rect y="345" width="816" height="175" fill="url(#sea)"/>
  <g stroke="#7cc4c0" stroke-width="2" opacity="0.45" fill="none">
    <path d="M40 400 q20 -8 40 0 t40 0"/><path d="M600 430 q20 -8 40 0 t40 0"/><path d="M140 470 q20 -8 40 0 t40 0"/>
    <path d="M520 480 q20 -8 40 0 t40 0"/><path d="M700 390 q16 -6 32 0 t32 0"/>
  </g>
  <!-- workboat -->
  <g transform="translate(250 330)">
    <path d="M0 60 L300 60 L280 98 L28 98 Z" fill="#f8fafc"/>
    <path d="M0 60 L300 60 L296 68 L4 68 Z" fill="#b4413a"/>
    <rect x="150" y="14" width="96" height="46" rx="4" fill="#e6f4f1"/>
    <rect x="160" y="22" width="22" height="16" fill="#102a43"/><rect x="188" y="22" width="22" height="16" fill="#102a43"/><rect x="216" y="22" width="22" height="16" fill="#102a43"/>
    <rect x="198" y="-16" width="4" height="30" fill="#e6f4f1"/>
    <circle cx="200" cy="-18" r="4" fill="#ffffff"/>
    <rect x="40" y="44" width="70" height="16" rx="2" fill="#cbd5e1"/>
    <path d="M-20 104 q40 -10 80 0 t80 0 t80 0 t80 0" stroke="#e6f4f1" stroke-width="3" fill="none" opacity="0.6"/>
  </g>
  <!-- lighthouse -->
  <g transform="translate(96 250)">
    <path d="M0 96 L8 20 L24 20 L32 96 Z" fill="#f8fafc"/><rect x="4" y="44" width="24" height="10" fill="#b4413a"/>
    <rect x="6" y="8" width="20" height="12" fill="#fde68a"/><path d="M4 8 L16 -2 L28 8 Z" fill="#b4413a"/>
    <path d="M26 12 L120 -6 L120 32 Z" fill="#fde68a" opacity="0.18"/>
  </g>
</svg>
"""


def cover_html(cfg: dict, draft: bool) -> str:
    css = (GUIDE / "guide.css").as_uri()
    stamp = '<div class="stamp">DRAFT FOR REVIEW · NOT FOR SALE</div>' if draft else ""
    return f"""<!doctype html><html lang="en"><head><meta charset="utf-8"><link rel="stylesheet" href="{css}">
<style>@page {{ size: Letter; margin: 0; }} .cover {{ height: 11in; margin: 0; }}</style></head><body>
<div class="cover">{COVER_ART}
<div class="brand">NAUTICLEBC</div>
<div class="title"><h1>{esc(cfg["title"])}</h1><p class="sub">{esc(cfg["subtitle"])}</p><p class="line">{esc(cfg["cover_line"])}</p></div>
{stamp}
<div class="foot">Independent study companion. Not a Transport Canada course, not endorsed by Transport Canada, not for navigation.</div>
</div></body></html>"""


# ---------- render ----------

FOOTER = """<div style="font-family: Liberation Sans, sans-serif; font-size: 8pt; color: #627d98; width: 100%;
 padding: 0 0.8in; display: flex; justify-content: space-between;">
<span>{left}</span><span><span class="pageNumber"></span></span></div>"""


def render(browser, html_text: str, out: Path, footer_left: str | None) -> None:
    tmp = out.with_suffix(".html")
    tmp.write_text(html_text, encoding="utf-8")
    page = browser.new_page()
    page.goto(tmp.as_uri(), wait_until="load")
    page.evaluate("document.fonts.ready")
    kwargs = {"path": str(out), "format": "Letter", "print_background": True, "prefer_css_page_size": True}
    if footer_left is not None:
        kwargs.update(display_header_footer=True, header_template="<span></span>",
                      footer_template=FOOTER.format(left=esc(footer_left)))
    page.pdf(**kwargs)
    page.close()
    tmp.unlink()


def find_pages(pdf: Path) -> dict[str, int]:
    pages: dict[str, int] = {}
    for i, page in enumerate(PdfReader(str(pdf)).pages, 1):
        for key in re.findall(MARK_PREFIX + r"([A-Za-z0-9._-]+)", page.extract_text() or ""):
            pages.setdefault(key, i)
    return pages


def main() -> int:
    ap = argparse.ArgumentParser(description=__doc__.splitlines()[0])
    ap.add_argument("ticket")
    ap.add_argument("--final", action="store_true", help="leave out the draft marks")
    args = ap.parse_args()
    configs = yaml.safe_load((GUIDE / "guides.yaml").read_text(encoding="utf-8"))
    cfg = configs.get(args.ticket)
    if not cfg:
        print(f"no guide set up for '{args.ticket}' in tools/guide/guides.yaml", file=sys.stderr)
        return 1
    draft = not args.final
    lessons, questions, sources = load(args.ticket)
    if not lessons:
        print(f"no lessons tagged '{args.ticket}'", file=sys.stderr)
        return 1
    parts = arrange(lessons, cfg["parts"])
    n = 0
    for part in parts:
        for lesson in part["lessons"]:
            for q in questions.get(lesson["id"], []):
                n += 1
                q["_n"] = n

    OUT.mkdir(parents=True, exist_ok=True)
    body_pdf = OUT / f"{args.ticket}-body.pdf"
    cover_pdf = OUT / f"{args.ticket}-cover.pdf"
    final_pdf = OUT / f"{args.ticket}-guide.pdf"
    footer_left = cfg["title"] + (" · DRAFT for review" if draft else "") + " · Study aid, not for navigation"
    with sync_playwright() as p:
        browser = p.chromium.launch()
        # Pass 1 finds the page of every part and lesson; pass 2 prints those numbers in the contents.
        render(browser, build_html(cfg, parts, questions, sources, {}, draft), body_pdf, footer_left)
        pages = find_pages(body_pdf)
        render(browser, build_html(cfg, parts, questions, sources, pages, draft), body_pdf, footer_left)
        if find_pages(body_pdf) != pages:
            print("warning: page numbers moved between passes", file=sys.stderr)
        render(browser, cover_html(cfg, draft), cover_pdf, None)
        browser.close()

    writer = PdfWriter()
    for src in (cover_pdf, body_pdf):
        for page in PdfReader(str(src)).pages:
            writer.add_page(page)
    writer.add_metadata({"/Title": cfg["title"], "/Author": "NauticleBC"})
    with open(final_pdf, "wb") as fh:
        writer.write(fh)
    cover_pdf.unlink()
    body_pdf.unlink()
    total = len(PdfReader(str(final_pdf)).pages)
    print(f"{final_pdf.relative_to(ROOT)}: {total} pages, {sum(len(p['lessons']) for p in parts)} lessons, {n} questions")
    return 0


if __name__ == "__main__":
    sys.exit(main())
