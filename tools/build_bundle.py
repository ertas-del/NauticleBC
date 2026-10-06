#!/usr/bin/env python3
"""Build the single content bundle the Android app reads.

Output: app/src/main/assets/content/bundle.json (git-ignored, generated).
Runs the same checks as lint_content.py first and refuses to build on errors.
"""
from __future__ import annotations

import argparse
import json
import sys
from pathlib import Path

from nbc_content import ROOT, load_content

DEFAULT_OUT = ROOT / "app" / "src" / "main" / "assets" / "content" / "bundle.json"


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("--out", type=Path, default=DEFAULT_OUT)
    args = parser.parse_args()

    content = load_content()
    for w in content.report.warnings:
        print(f"warning: {w}")
    if content.report.errors:
        for e in content.report.errors:
            print(f"error: {e}")
        print("not building: fix the errors above", file=sys.stderr)
        return 1

    def strip_path(item: dict) -> dict:
        return {k: v for k, v in item.items() if k != "path"}

    bundle = {
        "format_version": 1,
        "sources": sorted(content.sources.values(), key=lambda s: s["id"]),
        "lessons": [strip_path(x) for x in sorted(content.lessons, key=lambda x: (x["module"], x["order"], x["id"]))],
        "questions": [strip_path(x) for x in sorted(content.questions, key=lambda x: x["id"])],
        "mock_exams": [strip_path(x) for x in sorted(content.mock_exams, key=lambda x: x["id"])],
    }
    args.out.parent.mkdir(parents=True, exist_ok=True)
    args.out.write_text(json.dumps(bundle, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"wrote {args.out}: {len(bundle['lessons'])} lessons, {len(bundle['questions'])} questions")
    return 0


if __name__ == "__main__":
    sys.exit(main())
