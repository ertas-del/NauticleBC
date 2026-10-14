#!/usr/bin/env python3
"""Validate everything under content/ against schemas/ and the cross-file rules in CLAUDE.md.

Exit code 0 = clean (warnings allowed), 1 = errors.
"""
from __future__ import annotations

import sys

from nbc_content import load_content


def main() -> int:
    content = load_content()
    rep = content.report
    for w in rep.warnings:
        print(f"warning: {w}")
    for e in rep.errors:
        print(f"error: {e}")
    print(
        f"{len(content.sources)} sources, {len(content.lessons)} lessons, "
        f"{len(content.questions)} questions, {len(content.mock_exams)} mock exams, "
        f"{len(content.charts)} charts, {len(content.chart_exercises)} chart exercises; "
        f"{len(rep.errors)} errors, {len(rep.warnings)} warnings"
    )
    return 1 if rep.errors else 0


if __name__ == "__main__":
    sys.exit(main())
