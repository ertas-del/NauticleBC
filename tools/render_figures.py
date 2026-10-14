#!/usr/bin/env python3
"""Render content/figures/*.svg and content/charts/*.svg to PNG (same name, 2x scale) with headless Chromium via Playwright.

The app shows the PNGs; the SVGs stay the editable source. Run after drawing or changing a figure:
    python3 tools/render_figures.py
"""
from __future__ import annotations

import re
import sys
from pathlib import Path

from playwright.sync_api import sync_playwright

CONTENT = Path(__file__).resolve().parent.parent / "content"
FIGURES = CONTENT / "figures"
CHARTS = CONTENT / "charts"


def main() -> int:
    svgs = sorted([*FIGURES.glob("*.svg"), *CHARTS.glob("*.svg")])
    with sync_playwright() as p:
        browser = p.chromium.launch()
        for svg in svgs:
            text = svg.read_text(encoding="utf-8")
            m = re.search(r'viewBox="0 0 (\d+) (\d+)"', text)
            w, h = int(m.group(1)), int(m.group(2))
            page = browser.new_page(viewport={"width": w, "height": h}, device_scale_factor=2)
            page.goto(svg.resolve().as_uri())
            page.screenshot(path=str(svg.with_suffix(".png")), clip={"x": 0, "y": 0, "width": w, "height": h})
            page.close()
            print("rendered", svg.name)
        browser.close()
    return 0


if __name__ == "__main__":
    sys.exit(main())
