#!/usr/bin/env python3
"""Generate the Training Harbour synthetic chart, its exercises and its drawing from one set of numbers.

Writes content/charts/training-harbour.{chart.json,exercises.json,svg}. Run tools/render_figures.py afterwards for the PNG.
The place is fictional and sits in open ocean where no real land exists. Every answer is computed from the stored chart
data, so the chart, the questions and the answer key cannot disagree. Re-run after changing anything here.

Needs numpy, contourpy, matplotlib (see tools/chartlib.py).
"""
from __future__ import annotations

import json
import math
from pathlib import Path

import contourpy
import numpy as np

from chartlib import Frame, fmt_lat, fmt_lon, fmt_pos, land_mask_and_distance

ROOT = Path(__file__).resolve().parent.parent
OUT = ROOT / "content" / "charts"
CHART_ID = "training-harbour"
VARIATION = {"degrees": 15, "minutes": 0, "direction": "E", "year": 2020, "annual_change_minutes": -5.0}
EXERCISE_YEAR = 2026
VAR_NOW = 15.0 - (5 / 60) * (EXERCISE_YEAR - 2020)  # 14.5 E
DEVIATION = 3.0  # degrees West, on the headings used
TICKETS = ["svop", "master-limited-60gt", "fishing-master-4"]

F = Frame(lat0=33.0, lon0=-150.08333, width_nm=6.0, height_nm=5.0)

# ---- the place (nm from the south-west corner) ----
WEST_HEAD = [(0, 3.7), (0.8, 3.9), (1.6, 3.7), (2.2, 3.9), (2.5, 4.3), (2.45, 5.0), (0, 5.0)]
EAST_SHORE = [(3.2, 5.0), (3.15, 4.4), (3.45, 3.95), (4.3, 3.8), (5.0, 4.1), (6.0, 3.9), (6.0, 5.0)]
PINE_ISLAND = [(3.95, 2.0), (4.1, 1.75), (4.4, 1.7), (4.65, 1.9), (4.7, 2.2), (4.5, 2.4), (4.2, 2.45), (3.98, 2.3)]
GULL_ROCK = [(1.35, 2.45), (1.47, 2.42), (1.45, 2.55), (1.36, 2.55)]
RINGS = {"West Head": ("mainland", WEST_HEAD), "East Shore": ("mainland", EAST_SHORE),
         "Pine Island": ("island", PINE_ISLAND), "Gull Rock": ("island", GULL_ROCK)}
SENTINEL = (2.2, 1.6)
MERCY = (4.9, 0.9)

AIDS = [
    ("harbour-point-light", "Harbour Point Light", "light", (2.5, 4.2), "Fl W 4s", "white", 14, 10, None),
    ("pine-island-light", "Pine Island Light", "light", (4.3, 2.3), "Fl(2) W 8s", "white", 22, 12, None),
    ("gull-rock-beacon", "Gull Rock Beacon", "beacon", (1.41, 2.5), None, "black", None, None, "Unlit beacon on a small rock."),
    ("fairway-buoy", "Fairway Buoy", "buoy", (2.85, 3.2), "Mo(A) W 6s", "red and white vertical stripes", None, None,
     "Safe-water mark for the harbour entrance."),
]


def depth_field(xs, ys):
    inside, dist, gx, gy = land_mask_and_distance([r for _, r in RINGS.values()], xs, ys)
    d = 2.0 + 30.0 * (1 - np.exp(-dist / 0.8))
    d -= 24.0 * np.exp(-((gx - SENTINEL[0]) ** 2 + (gy - SENTINEL[1]) ** 2) / 0.03)
    d -= 24.0 * np.exp(-((gx - MERCY[0]) ** 2 + (gy - MERCY[1]) ** 2) / 0.09)
    d = np.clip(d, 0.4, 40)
    return np.where(inside, 0.0, d), inside, gx, gy


ROSES = [(0.65, 2.95), (5.45, 3.1)]


def keep_clear(px, py):
    """True where a printed sounding would collide with a label, symbol or compass rose."""
    if any(math.hypot(px - rx, py - ry) < 0.62 for rx, ry in ROSES):
        return True
    if any(math.hypot(px - hx, py - hy) < 0.5 for hx, hy in (SENTINEL, MERCY)):
        return True
    for a in AIDS:
        ax, ay = a[3]
        if ax - 0.15 <= px <= ax + 1.45 and ay - 0.3 <= py <= ay + 0.25:
            return True
    return False


def build_chart():
    xs = np.arange(0, 6.0001, 0.05)
    ys = np.arange(0, 5.0001, 0.05)
    depth, inside, gx, gy = depth_field(xs, ys)
    # printed soundings on a jittered grid, water only
    rng = np.random.default_rng(7)
    soundings = []
    for y in np.arange(0.3, 4.7, 0.42):
        for x in np.arange(0.3, 5.8, 0.42):
            px, py = float(x + rng.uniform(-0.1, 0.1)), float(y + rng.uniform(-0.1, 0.1))
            i, j = int(round(py / 0.05)), int(round(px / 0.05))
            if inside[i, j] or depth[i, j] < 0.6:
                continue
            if keep_clear(px, py):
                continue
            d = float(depth[i, j])
            soundings.append({"position": F.pos(px, py), "depth_m": round(d, 1) if d < 21 else float(int(round(d)))})
    hazards = [
        {"id": "sentinel-rock", "kind": "drying-rock", "name": "Sentinel Rock", "position": F.pos(*SENTINEL), "drying_height_m": 1.2},
        {"id": "mercy-shoal", "kind": "shoal", "name": "Mercy Shoal", "position": F.pos(*MERCY), "depth_m": 2.8},
    ]
    aids = []
    for aid, name, kind, xy, ch, colour, h, rng_nm, note in AIDS:
        a = {"id": aid, "name": name, "kind": kind, "position": F.pos(*xy), "colour": colour}
        if ch:
            a["characteristic"] = ch
        if h:
            a["height_m"] = h
        if rng_nm:
            a["range_nm"] = rng_nm
        if note:
            a["notes"] = note
        aids.append(a)
    chart = {
        "id": CHART_ID,
        "title": "Training Harbour",
        "fictional": True,
        "area_note": "Training Harbour is an invented place in open ocean, made only for chartwork practice. It is not a real harbour and must never be used for navigation.",
        "module": "chartwork",
        "datum": {"description": "Soundings are in metres below chart datum, the lowest normal tide level in this fictional area. Drying heights are above chart datum.", "depth_unit": "metres"},
        "extent": F.extent(),
        "scale_ratio": 20000,
        "variation": VARIATION,
        "compass_roses": [{"id": "rose-west", "position": F.pos(*ROSES[0])}, {"id": "rose-east", "position": F.pos(*ROSES[1])}],
        "land": [{"name": n, "kind": k, "ring": [F.pos(*p) for p in r]} for n, (k, r) in RINGS.items()],
        "soundings": soundings,
        "hazards": hazards,
        "aids": aids,
        "legend": [
            {"symbol": "light", "meaning": "Light. Fl = flashing, the number is the period in seconds, then height in metres and range in nautical miles."},
            {"symbol": "buoy", "meaning": "Buoy. Red and white vertical stripes mark safe water."},
            {"symbol": "beacon", "meaning": "Beacon, a fixed unlit mark."},
            {"symbol": "cross with underlined number", "meaning": "Rock that dries. The underlined number is its height above chart datum, in metres."},
            {"symbol": "dotted ring", "meaning": "Shoal. The number is the least depth over it, in metres."},
            {"symbol": "small numbers", "meaning": "Soundings in metres below chart datum."},
            {"symbol": "compass rose", "meaning": "Outer ring is true north. Inner ring is magnetic, with the variation and its yearly change printed in the centre."},
        ],
        "review_status": "draft",
    }
    return chart, (xs, ys, depth, inside)


# ---------- exercises ----------
def pos_of(chart, mark_id):
    for g in ("aids", "hazards"):
        for m in chart[g]:
            if m["id"] == mark_id:
                return m["position"]
    raise KeyError(mark_id)


def hhmm(minutes: float) -> str:
    minutes = int(round(minutes))
    return f"{minutes // 60:02d}:{minutes % 60:02d}"


def rot360(v: float) -> float:
    return v % 360


def build_exercises(chart):
    ex = []
    tide_table = {"low_water": {"time": "10:00", "height_m": 0.6}, "high_water": {"time": "16:00", "height_m": 4.8}}
    var_txt = f"{int(VAR_NOW)}°{round((VAR_NOW % 1) * 60):02d}′E"

    # 1 read a position
    pine = pos_of(chart, "pine-island-light")
    ex.append({
        "id": f"{CHART_ID}.read-pine-island-light", "tickets": TICKETS, "kind": "read-position", "difficulty": "easy",
        "prompt": "Read the latitude and longitude of Pine Island Light from the chart scales.",
        "givens": {},
        "answers": [{"label": "Position of Pine Island Light", "kind": "position", "value": pine, "tolerance": 0.1, "unit": "nautical miles"}],
        "method": ["Draw a faint line from the light across to the side scale and read the latitude.",
                   "Draw a faint line from the light up to the top or bottom scale and read the longitude.",
                   f"The light is at {fmt_pos(pine)}. Latitude comes first, longitude second."],
        "review_status": "needs_expert_review"})

    # 2 plot a position, then bearing and distance to a mark
    plot_pt = F.pos(3.0, 2.6)
    b = F.bearing_true(plot_pt, pine)
    d = F.distance_nm(plot_pt, pine)
    ex.append({
        "id": f"{CHART_ID}.plot-and-measure", "tickets": TICKETS, "kind": "plot-position", "difficulty": "easy",
        "prompt": f"Plot the position {fmt_pos(plot_pt)}. Then measure the true bearing and the distance from that position to Pine Island Light.",
        "givens": {"start_position": plot_pt},
        "answers": [{"label": "True bearing to Pine Island Light", "kind": "bearing", "value": round(b), "tolerance": 2, "unit": "degrees true"},
                    {"label": "Distance to Pine Island Light", "kind": "distance", "value": round(d, 1), "tolerance": 0.1, "unit": "nautical miles"}],
        "method": ["Find the latitude on the side scale and the longitude on the top or bottom scale. Draw faint lines from each. Where they cross is the position.",
                   "Lay the parallel rule from the position to the light. Walk it to the nearest compass rose and read the true (outer) ring.",
                   "Open the dividers from the position to the light and read the distance against the latitude scale, one minute of latitude being one nautical mile.",
                   f"Bearing about {round(b):03d}° true, distance about {d:.1f} nautical miles."],
        "review_status": "needs_expert_review"})

    # 3 course and distance between two positions
    dep = F.pos(1.0, 0.5)
    buoy = pos_of(chart, "fairway-buoy")
    c = F.bearing_true(dep, buoy)
    dist = F.distance_nm(dep, buoy)
    course_int = round(c)
    ex.append({
        "id": f"{CHART_ID}.course-to-fairway-buoy", "tickets": TICKETS, "kind": "course-distance", "difficulty": "easy",
        "prompt": f"A vessel leaves {fmt_pos(dep)} for the Fairway Buoy. Find the true course to steer and the distance to run.",
        "givens": {"start_position": dep},
        "answers": [{"label": "True course", "kind": "bearing", "value": course_int, "tolerance": 2, "unit": "degrees true"},
                    {"label": "Distance", "kind": "distance", "value": round(dist, 1), "tolerance": 0.1, "unit": "nautical miles"}],
        "method": ["Plot the departure position and mark the Fairway Buoy.", "Draw the track between them with the parallel rule.",
                   "Walk the rule to a compass rose and read the true course on the outer ring.",
                   "Step the dividers along the track and read the distance on the latitude scale.",
                   f"Course about {course_int:03d}° true, distance about {dist:.1f} nautical miles."],
        "review_status": "needs_expert_review"})

    # 4 compass conversion (true to compass)
    comp = rot360(course_int - VAR_NOW + DEVIATION)
    ex.append({
        "id": f"{CHART_ID}.true-to-compass", "tickets": TICKETS, "kind": "compass-conversion", "difficulty": "medium",
        "prompt": f"The true course is {course_int:03d}°. The compass rose gives the variation for 2020 and its yearly change. Deviation on this heading is 3° West. The year is {EXERCISE_YEAR}. What course do you steer by compass?",
        "givens": {"course_true": course_int, "deviation": {"degrees": 3, "direction": "W"}},
        "answers": [{"label": "Compass course", "kind": "bearing", "value": round(comp), "tolerance": 1, "unit": "degrees compass"}],
        "method": ["Update the variation: the rose says 15°00′E in 2020, decreasing 5′ a year. Six years is 30′, so variation is 14°30′E.",
                   "Go down from True to Magnetic. East is least, so subtract: true minus 14.5° gives magnetic.",
                   "Go down from Magnetic to Compass. West is best, so add the 3° West deviation.",
                   f"{course_int} − {VAR_NOW:g} + 3 = {comp:.1f}, so steer about {round(comp):03d}° by compass."],
        "assumptions": [f"Variation in {EXERCISE_YEAR} is {var_txt} (15°00′E in 2020, decreasing 5′ a year)."],
        "review_status": "needs_expert_review"})

    # 5 dead reckoning
    start = F.pos(3.4, 0.6)
    dr = F.advance(start, 330, 3.0)
    ex.append({
        "id": f"{CHART_ID}.dr-from-fix", "tickets": TICKETS, "kind": "dr-position", "difficulty": "medium",
        "prompt": f"At 11:15 a vessel is fixed at {fmt_pos(start)}. She steers 330° true at 6 knots with no current. Plot her dead reckoning position for 11:45.",
        "givens": {"start_position": start, "start_time": "11:15", "course_true": 330, "speed_kn": 6, "duration_min": 30},
        "answers": [{"label": "DR position at 11:45", "kind": "position", "value": dr, "tolerance": 0.1, "unit": "nautical miles"}],
        "method": ["Plot the fix and write the time beside it.", "Draw the 330° true course line from the fix.",
                   "Distance = speed × time = 6 knots × 0.5 hour = 3.0 nautical miles.",
                   "Step off 3.0 nautical miles along the course line. Mark a dot with a half circle and write 11:45.",
                   f"The DR position is about {fmt_pos(dr)}."],
        "assumptions": ["No current and no leeway."],
        "review_status": "needs_expert_review"})

    # 6 cross-bearing fix
    hidden = F.pos(3.2, 1.2)
    gull = pos_of(chart, "gull-rock-beacon")
    tb1, tb2 = F.bearing_true(hidden, pine), F.bearing_true(hidden, gull)
    cb1, cb2 = round(rot360(tb1 - VAR_NOW + DEVIATION)), round(rot360(tb2 - VAR_NOW + DEVIATION))
    ex.append({
        "id": f"{CHART_ID}.fix-two-marks", "tickets": TICKETS, "kind": "cross-bearing-fix", "difficulty": "hard",
        "prompt": f"At the same moment a vessel takes compass bearings of Pine Island Light ({cb1:03d}°) and Gull Rock Beacon ({cb2:03d}°). Deviation is 3° West and the year is {EXERCISE_YEAR}. Find her position.",
        "givens": {"deviation": {"degrees": 3, "direction": "W"},
                   "bearings": [{"mark_id": "pine-island-light", "bearing_compass": cb1}, {"mark_id": "gull-rock-beacon", "bearing_compass": cb2}]},
        "answers": [{"label": "Position of the vessel", "kind": "position", "value": hidden, "tolerance": 0.15, "unit": "nautical miles"}],
        "method": ["Update the variation to 14°30′E, as in the compass conversion exercise.",
                   "Go up from Compass to True. Compass Add East: deviation West is subtracted, then variation East is added. That is compass − 3 + 14.5 = compass + 11.5.",
                   f"Pine Island Light: {cb1} + 11.5 ≈ {round(rot360(cb1 + 11.5)):03d}° true. Gull Rock Beacon: {cb2} + 11.5 ≈ {round(rot360(cb2 + 11.5)):03d}° true.",
                   "A bearing line is plotted from the mark, so use the reciprocal (add or subtract 180°) and draw each line back from its mark towards the vessel.",
                   f"The lines cross at about {fmt_pos(hidden)}. The two bearings differ by well over 30°, which gives a good fix."],
        "assumptions": [f"Variation in {EXERCISE_YEAR} is {var_txt}. Compass bearings are rounded to the nearest degree."],
        "review_status": "needs_expert_review"})

    # 7 depth and under-keel clearance at a charted sounding
    cand = [s for s in chart["soundings"] if 4.0 <= s["depth_m"] <= 9.0]
    mx, my = MERCY
    snd = min(cand, key=lambda s: math.hypot(F.xy(s["position"])[0] - mx, F.xy(s["position"])[1] - my))
    tide, draught = 1.8, 1.6
    depth_now = snd["depth_m"] + tide
    ex.append({
        "id": f"{CHART_ID}.under-keel-clearance", "tickets": TICKETS, "kind": "under-keel-clearance", "difficulty": "medium",
        "prompt": f"The sounding at {fmt_pos(snd['position'])} is {snd['depth_m']} m. The height of tide is 1.8 m and the vessel's draught is 1.6 m. How much water is there, and what is the clearance under the keel?",
        "givens": {"start_position": snd["position"], "height_of_tide_m": tide, "draught_m": draught},
        "answers": [{"label": "Depth of water", "kind": "height", "value": round(depth_now, 1), "tolerance": 0.1, "unit": "metres"},
                    {"label": "Under-keel clearance", "kind": "height", "value": round(depth_now - draught, 1), "tolerance": 0.1, "unit": "metres"}],
        "method": ["Charted depth is measured from chart datum, so the tide is added: depth = charted depth + height of tide.",
                   f"{snd['depth_m']} + 1.8 = {depth_now:.1f} m of water.",
                   "Clearance = depth − draught.", f"{depth_now:.1f} − 1.6 = {depth_now - draught:.1f} m under the keel."],
        "review_status": "needs_expert_review"})

    # 8 height of tide by rule of twelfths
    lw, hw = 0.6, 4.8
    rng_t = hw - lw
    twelfths = [1, 2, 3, 3, 2, 1]
    heights = [lw]
    for t in twelfths:
        heights.append(heights[-1] + rng_t * t / 12)
    ex.append({
        "id": f"{CHART_ID}.tide-height-1300", "tickets": TICKETS, "kind": "tide-height", "difficulty": "medium",
        "prompt": "Low water is at 10:00, height 0.6 m. High water is at 16:00, height 4.8 m. Use the rule of twelfths to find the height of tide at 13:00.",
        "givens": {**tide_table, "query_time": "13:00"},
        "answers": [{"label": "Height of tide at 13:00", "kind": "height", "value": round(heights[3], 1), "tolerance": 0.1, "unit": "metres"}],
        "method": ["Range = 4.8 − 0.6 = 4.2 m. The tide takes six hours to rise.",
                   "Each hour the water rises 1, 2, 3, 3, 2, 1 twelfths of the range. 13:00 is three hours after low water.",
                   "Twelfths so far: 1 + 2 + 3 = 6. That is 6/12 of the range: 4.2 × 6/12 = 2.1 m.",
                   f"Height = 0.6 + 2.1 = {heights[3]:.1f} m."],
        "assumptions": ["The rule of twelfths is an approximation that assumes a regular six-hour tide. These tide figures are invented for the exercise."],
        "review_status": "needs_expert_review"})

    # 9 passage window over a drying rock
    need = 1.2 + 1.5 + 0.5
    k = next(i for i in range(6) if heights[i + 1] >= need)
    t_hour = 10 + k + (need - heights[k]) / (heights[k + 1] - heights[k])
    t_min = round(t_hour * 60)
    ex.append({
        "id": f"{CHART_ID}.window-over-sentinel-rock", "tickets": TICKETS, "kind": "passage-window", "difficulty": "hard",
        "prompt": "Sentinel Rock dries 1.2 m. A vessel drawing 1.5 m wants at least 0.5 m of water under her keel when she passes over the rock. Low water is at 10:00, height 0.6 m. High water is at 16:00, height 4.8 m. What height of tide is needed, and at what time on the rising tide is it first reached?",
        "givens": {**tide_table, "draught_m": 1.5, "margin_m": 0.5},
        "answers": [{"label": "Height of tide needed", "kind": "height", "value": round(need, 1), "tolerance": 0.05, "unit": "metres"},
                    {"label": "Earliest time, as minutes after midnight on the 24-hour clock", "kind": "time", "value": t_min, "tolerance": 10, "unit": "minutes after midnight"}],
        "method": ["A drying height is measured above chart datum, so water over the rock = height of tide − drying height.",
                   "The water over the rock must cover the draught plus the margin: 1.5 + 0.5 = 2.0 m.",
                   "Tide needed = 1.2 + 2.0 = 3.2 m.",
                   "Work out the tide hour by hour from the rule of twelfths: 10:00 0.6 m, 11:00 0.95 m, 12:00 1.65 m, 13:00 2.7 m, 14:00 3.75 m.",
                   f"3.2 m falls between 13:00 and 14:00. It is reached about {int(round((need - heights[3]) / (heights[4] - heights[3]) * 60))} minutes after 13:00, so about {hhmm(t_min)}."],
        "assumptions": ["Tide figures are invented for the exercise. Treat the rise within each hour as steady."],
        "review_status": "needs_expert_review"})
    return {"chart_id": CHART_ID, "exercises": ex}


# ---------- drawing ----------
S = 150  # px per nm
X0, Y0 = 90, 90
W_PX, H_PX = int(F.w * S), int(F.h * S)
CANVAS_W, CANVAS_H = X0 + W_PX + 400, Y0 + H_PX + 110
INK = "#1b2a41"


def px(x, y):
    return X0 + x * S, Y0 + (F.h - y) * S


def ppos(p):
    return px(*F.xy(p))


def esc(t):
    return t.replace("&", "&amp;").replace("<", "&lt;")


def text(x, y, s, size=16, anchor="middle", fill=INK, weight="normal", style="normal", extra=""):
    return f'<text x="{x:.1f}" y="{y:.1f}" font-size="{size}" text-anchor="{anchor}" fill="{fill}" font-weight="{weight}" font-style="{style}" font-family="Inter, Arial, sans-serif" {extra}>{esc(s)}</text>'


def poly_path(points):
    return "M" + " L".join(f"{x:.1f} {y:.1f}" for x, y in (px(*p) for p in points)) + " Z"


def band_paths(depth, xs, ys):
    gen = contourpy.contour_generator(xs, ys, depth, fill_type=contourpy.FillType.OuterCode)
    bands = [(0, 2, "#8fc1da"), (2, 5, "#acd2e5"), (5, 10, "#c9e3f0"), (10, 41, "#eef7fb")]
    out = []
    for lo, hi, col in bands:
        pts, codes = gen.filled(lo, hi)
        d = []
        for poly, cd in zip(pts, codes):
            for (x, y), c in zip(poly, cd):
                sx, sy = px(x, y)
                d.append(("M" if c == 1 else "Z" if c == 79 else "L") + (f"{sx:.1f} {sy:.1f}" if c != 79 else ""))
        out.append(f'<path d="{" ".join(d)}" fill="{col}" fill-rule="evenodd"/>')
    lines = []
    lgen = contourpy.contour_generator(xs, ys, depth)
    for lvl in (2, 5, 10):
        for line in lgen.lines(lvl):
            lines.append(f'<path d="M{" L".join(f"{px(x, y)[0]:.1f} {px(x, y)[1]:.1f}" for x, y in line)}" fill="none" stroke="#5d8fab" stroke-width="1.2" stroke-dasharray="{"" if lvl == 5 else "6 3" if lvl == 2 else "2 3"}"/>')
    return out + lines


def rose(cx, cy, r, var_deg):
    s = f'<g><circle cx="{cx}" cy="{cy}" r="{r}" fill="#ffffffcc" stroke="{INK}" stroke-width="1.5"/><circle cx="{cx}" cy="{cy}" r="{r*0.74}" fill="none" stroke="#9b2c6a" stroke-width="1.5"/>'
    for ring, rad, col, rot in ((0, r, INK, 0), (1, r * 0.74, "#9b2c6a", var_deg)):
        for a in range(0, 360, 5):
            ln = 12 if a % 10 == 0 else 6
            ang = math.radians(a + rot)
            x1, y1 = cx + (rad - ln * (0.5 if ring else 0.7)) * math.sin(ang), cy - (rad - ln * (0.5 if ring else 0.7)) * math.cos(ang)
            x2, y2 = cx + rad * math.sin(ang), cy - rad * math.cos(ang)
            s += f'<line x1="{x1:.1f}" y1="{y1:.1f}" x2="{x2:.1f}" y2="{y2:.1f}" stroke="{col}" stroke-width="1"/>'
            if a % 30 == 0 and ring == 0:
                lx, ly = cx + (r + 14) * math.sin(ang), cy - (r + 14) * math.cos(ang) + 5
                s += text(lx, ly, f"{a:03d}", 12, fill=INK)
    ang = math.radians(var_deg)
    s += f'<line x1="{cx}" y1="{cy - r*0.74}" x2="{cx}" y2="{cy - r}" stroke="{INK}" stroke-width="3"/>'
    s += f'<g transform="rotate({var_deg} {cx} {cy})"><polygon points="{cx},{cy - r*0.74 + 2} {cx-5},{cy - r*0.74 + 14} {cx+5},{cy - r*0.74 + 14}" fill="#9b2c6a"/></g>'
    s += text(cx, cy - 6, "VAR 15°00′E", 13, fill="#9b2c6a", weight="bold") + text(cx, cy + 10, "2020", 12, fill="#9b2c6a") + text(cx, cy + 25, "decr. 5′/yr", 11, fill="#9b2c6a")
    return s + "</g>"


def scale_frame(chart):
    s = f'<rect x="{X0}" y="{Y0}" width="{W_PX}" height="{H_PX}" fill="none" stroke="{INK}" stroke-width="2"/>'
    # latitude scale left and right: boxes every 0.2 minute, label every minute
    for i in range(int(F.h * 5)):
        y = Y0 + H_PX - (i + 1) * S / 5
        col = INK if i % 2 == 0 else "#ffffff"
        for xx in (X0 - 10, X0 + W_PX):
            s += f'<rect x="{xx}" y="{y:.1f}" width="10" height="{S/5:.1f}" fill="{col}" stroke="{INK}" stroke-width="0.8"/>'
    for m in range(int(F.h) + 1):
        y = Y0 + H_PX - m * S
        lab = f"{int(F.lat0)}°{m:02d}′N"
        s += text(X0 - 16, y + 5, lab, 13, anchor="end") + text(X0 + W_PX + 16, y + 5, lab, 13, anchor="start")
    # longitude scale top and bottom: one box per 0.2 minute of longitude
    px_per_min = S * F.cos
    nmin = int(F.w / F.cos) + 1
    west_min = abs(F.lon0) * 60  # minutes west
    total = int(W_PX / (px_per_min / 5))
    for i in range(total):
        x = X0 + i * px_per_min / 5
        col = INK if i % 2 == 0 else "#ffffff"
        for yy in (Y0 - 10, Y0 + H_PX):
            s += f'<rect x="{x:.1f}" y="{yy}" width="{px_per_min/5:.1f}" height="10" fill="{col}" stroke="{INK}" stroke-width="0.8"/>'
    first = math.ceil(west_min - F.w / F.cos)  # first whole minute west edge to the right? recompute below
    # whole-minute labels, east edge has the smaller west-minute value
    for k in range(0, nmin + 1):
        wm = math.floor(west_min) - k  # whole minute value (minutes west)
        xnm = (west_min - wm) * F.cos
        if xnm < 0 or xnm > F.w:
            continue
        x = X0 + xnm * S
        lab = f"{int(wm // 60)}°{int(wm % 60):02d}′W"
        s += text(x, Y0 - 16, lab, 13) + text(x, Y0 + H_PX + 28, lab, 13)
    return s


def draw(chart, grid):
    xs, ys, depth, inside = grid
    o = [f'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 {CANVAS_W} {CANVAS_H}" width="{CANVAS_W}" height="{CANVAS_H}" role="img" aria-labelledby="t d">',
         '<title id="t">Training Harbour, a fictional training chart</title>',
         '<desc id="d">A made-up chart of a harbour with a mainland coast to the north, Pine Island and Gull Rock, Sentinel Rock that dries, Mercy Shoal, two lights, a beacon and a fairway buoy. Fictional, not for navigation.</desc>',
         f'<rect width="{CANVAS_W}" height="{CANVAS_H}" fill="#ffffff"/>',
         f'<clipPath id="c"><rect x="{X0}" y="{Y0}" width="{W_PX}" height="{H_PX}"/></clipPath><g clip-path="url(#c)">']
    o += band_paths(depth, xs, ys)
    for n, (k, ring) in RINGS.items():
        o.append(f'<path d="{poly_path(ring)}" fill="#f1e3b3" stroke="#6b5b2a" stroke-width="2"/>')
        cx = sum(p[0] for p in ring) / len(ring)
        cy = sum(p[1] for p in ring) / len(ring)
        if n in ("Pine Island", "West Head", "East Shore"):
            lx, ly = px(cx, cy - (0.25 if n != "Pine Island" else 0))
            o.append(text(lx, ly, n.upper() if k == "mainland" else n, 15 if k == "island" else 17, fill="#6b5b2a", weight="bold" if k == "mainland" else "normal", style="italic"))
    for s in chart["soundings"]:
        x, y = ppos(s["position"])
        v = s["depth_m"]
        o.append(text(x, y + 4, f"{v:g}", 13, fill="#27506b", style="italic"))
    for h in chart["hazards"]:
        x, y = ppos(h["position"])
        if h["kind"] == "drying-rock":
            o.append(f'<path d="M{x-8} {y} h16 M{x} {y-8} v16 M{x-6} {y-6} l12 12 M{x+6} {y-6} l-12 12" stroke="{INK}" stroke-width="1.6"/>')
            o.append(text(x + 24, y - 6, f"{h['drying_height_m']:g}", 14, fill=INK, style="italic", extra='text-decoration="underline"'))
            o.append(text(x, y + 26, h["name"], 13, style="italic"))
        else:
            o.append(f'<circle cx="{x:.1f}" cy="{y:.1f}" r="30" fill="none" stroke="#27506b" stroke-width="1.6" stroke-dasharray="2 4"/>')
            o.append(text(x, y + 4, f"{h['depth_m']:g}", 14, fill="#27506b", style="italic"))
            o.append(text(x, y + 48, h["name"], 13, style="italic"))
    for a in chart["aids"]:
        x, y = ppos(a["position"])
        label = a["name"]
        if a["kind"] == "light":
            o.append(f'<circle cx="{x:.1f}" cy="{y:.1f}" r="4" fill="{INK}"/><path d="M{x} {y} l-9 -22 a30 30 0 0 1 22 0 z" fill="#c2399a" stroke="#c2399a" stroke-width="1" opacity="0.85"/>')
            o.append(text(x + 16, y - 6, label, 14, anchor="start", weight="bold"))
            o.append(text(x + 16, y + 11, f"{a['characteristic']} {a['height_m']}m {a['range_nm']}M", 13, anchor="start", fill="#9b2c6a"))
        elif a["kind"] == "beacon":
            o.append(f'<path d="M{x} {y-14} l-7 14 h14 z" fill="{INK}"/>')
            o.append(text(x + 12, y - 4, label, 13, anchor="start", weight="bold"))
        else:
            o.append(f'<circle cx="{x:.1f}" cy="{y:.1f}" r="9" fill="#ffffff" stroke="#c0392b" stroke-width="3"/><path d="M{x-4} {y-8} v16 M{x+4} {y-8} v16" stroke="#c0392b" stroke-width="3"/>')
            o.append(text(x + 16, y - 2, label, 14, anchor="start", weight="bold"))
            o.append(text(x + 16, y + 14, a["characteristic"], 13, anchor="start", fill="#9b2c6a"))
    for r_ in chart["compass_roses"]:
        x, y = ppos(r_["position"])
        o.append(rose(x, y, 0.42 * S, 15.0))
    o.append("</g>")
    o.append(scale_frame(chart))
    # right panel
    rx = X0 + W_PX + 120
    o.append(text(rx, Y0 + 18, "TRAINING HARBOUR", 26, anchor="start", weight="bold"))
    o.append(text(rx, Y0 + 44, "A fictional place for chartwork practice", 14, anchor="start", fill="#444"))
    o.append(f'<rect x="{rx}" y="{Y0 + 62}" width="230" height="64" rx="6" fill="#b00020"/>')
    o.append(text(rx + 115, Y0 + 88, "FICTIONAL TRAINING CHART", 15, fill="#fff", weight="bold") + text(rx + 115, Y0 + 110, "NOT FOR NAVIGATION", 15, fill="#fff", weight="bold"))
    yy = Y0 + 160
    for line in ["Depths in metres below chart datum.", "Drying heights above chart datum.", "Positions in degrees and minutes.", "Distances in nautical miles:", "1′ of latitude = 1 nautical mile.", f"Nominal scale 1:{chart['scale_ratio']:,}."]:
        o.append(text(rx, yy, line, 14, anchor="start")); yy += 22
    yy += 14
    o.append(text(rx, yy, "Legend", 17, anchor="start", weight="bold")); yy += 24
    short = ["Light: Fl = flashing, then period, height (m), range (M)", "Buoy, safe water (red and white stripes)", "Beacon, fixed unlit mark", "Rock that dries; underlined number is height above datum (m)", "Shoal; number is least depth (m)", "Soundings in metres", "Compass rose: outer ring true, inner ring magnetic"]
    import textwrap
    for line in short:
        wrapped = textwrap.wrap(line, 34)
        for i, w in enumerate(wrapped):
            o.append(text(rx + (14 if i else 0), yy, ("• " if i == 0 else "") + w, 13, anchor="start")); yy += 18
        yy += 4
    # distance scale bar
    bx, by = rx, Y0 + H_PX - 40
    o.append(f'<rect x="{bx}" y="{by}" width="{S}" height="8" fill="{INK}"/><rect x="{bx + S/2}" y="{by}" width="{S/2}" height="8" fill="#fff" stroke="{INK}"/>')
    o.append(text(bx, by + 26, "0", 13) + text(bx + S, by + 26, "1 nautical mile", 13, anchor="start"))
    o.append(text(X0 + W_PX / 2, CANVAS_H - 24, "Fictional training chart. Not for navigation. Positions, depths and hazards are invented. NauticleBC study aid.", 14, fill="#b00020", weight="bold"))
    o.append("</svg>")
    return "\n".join(o)


def main():
    chart, grid = build_chart()
    exercises = build_exercises(chart)
    OUT.mkdir(parents=True, exist_ok=True)
    (OUT / f"{CHART_ID}.chart.json").write_text(json.dumps(chart, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    (OUT / f"{CHART_ID}.exercises.json").write_text(json.dumps(exercises, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    (OUT / f"{CHART_ID}.svg").write_text(draw(chart, grid), encoding="utf-8")
    print(f"wrote {CHART_ID}: {len(chart['soundings'])} soundings, {len(exercises['exercises'])} exercises")


if __name__ == "__main__":
    main()
