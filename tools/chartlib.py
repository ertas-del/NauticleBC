"""Helpers for building synthetic training charts: geometry, navigation sums and SVG drawing.

Used by tools/make_training_harbour.py. Needs numpy, contourpy and matplotlib (the last only for point-in-polygon),
and Playwright (tools/render_figures.py) to turn SVG into PNG. Not run in CI; the generated JSON is what CI checks.

Chart frame: x runs east and y runs north in nautical miles from the south-west corner. A minute of latitude is one
nautical mile; a minute of longitude is cos(latitude) nautical miles (a Mercator-style chart for a small area).
"""
from __future__ import annotations

import math

import numpy as np
from matplotlib.path import Path as MplPath

NM_PER_DEG = 60.0


class Frame:
    def __init__(self, lat0: float, lon0: float, width_nm: float, height_nm: float):
        self.lat0, self.lon0, self.w, self.h = lat0, lon0, width_nm, height_nm
        self.cos = math.cos(math.radians(lat0 + height_nm / NM_PER_DEG / 2))

    def pos(self, x: float, y: float) -> dict:
        return {"lat": round(self.lat0 + y / NM_PER_DEG, 5), "lon": round(self.lon0 + x / (NM_PER_DEG * self.cos), 5)}

    def xy(self, p: dict) -> tuple[float, float]:
        return ((p["lon"] - self.lon0) * NM_PER_DEG * self.cos, (p["lat"] - self.lat0) * NM_PER_DEG)

    def extent(self) -> dict:
        n = self.pos(self.w, self.h)
        return {"south": self.lat0, "west": self.lon0, "north": n["lat"], "east": n["lon"]}

    # navigation sums, always from the stored positions so answers match the chart exactly
    def bearing_true(self, a: dict, b: dict) -> float:
        ax, ay = self.xy(a)
        bx, by = self.xy(b)
        return math.degrees(math.atan2(bx - ax, by - ay)) % 360

    def distance_nm(self, a: dict, b: dict) -> float:
        ax, ay = self.xy(a)
        bx, by = self.xy(b)
        return math.hypot(bx - ax, by - ay)

    def advance(self, a: dict, course_true: float, dist_nm: float) -> dict:
        ax, ay = self.xy(a)
        r = math.radians(course_true)
        return self.pos(ax + dist_nm * math.sin(r), ay + dist_nm * math.cos(r))


def fmt_lat(deg: float) -> str:
    m = abs(deg) * 60
    return f"{int(m // 60):02d}°{m % 60:04.1f}′{'N' if deg >= 0 else 'S'}"


def fmt_lon(deg: float) -> str:
    m = abs(deg) * 60
    return f"{int(m // 60):03d}°{m % 60:04.1f}′{'E' if deg >= 0 else 'W'}"


def fmt_pos(p: dict) -> str:
    return f"{fmt_lat(p['lat'])} {fmt_lon(p['lon'])}"


def sgn_var(deg: int, minutes: int, direction: str) -> float:
    return (deg + minutes / 60) * (1 if direction == "E" else -1)


def seg_dist(px, py, ax, ay, bx, by):
    dx, dy = bx - ax, by - ay
    t = np.clip(((px - ax) * dx + (py - ay) * dy) / (dx * dx + dy * dy + 1e-12), 0, 1)
    return np.hypot(px - (ax + t * dx), py - (ay + t * dy))


def land_mask_and_distance(rings: list[list[tuple[float, float]]], xs, ys):
    gx, gy = np.meshgrid(xs, ys)
    pts = np.column_stack([gx.ravel(), gy.ravel()])
    inside = np.zeros(len(pts), dtype=bool)
    dist = np.full(len(pts), 99.0)
    for ring in rings:
        inside |= MplPath(ring).contains_points(pts)
        for i in range(len(ring)):
            a, b = ring[i], ring[(i + 1) % len(ring)]
            dist = np.minimum(dist, seg_dist(pts[:, 0], pts[:, 1], a[0], a[1], b[0], b[1]))
    return inside.reshape(gx.shape), dist.reshape(gx.shape), gx, gy
