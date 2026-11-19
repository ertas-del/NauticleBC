#!/usr/bin/env python3
"""Make a 1080x1920 reel: a steady boomerang loop of your clip with text cards on top.

    python3 tools/reel/make_reel.py tools/reel/example-fire.json out/fire-reel.mp4

The clip loops forward then backward over a short steady stretch, so there is no
zooming or ghosting. Cards are drawn in Atkinson Hyperlegible and faded in and out.
Card kinds: "tag" (red tag plus big words), "big" (big words), "step" (coloured label,
headline, sub line), "end" (closing card with the brand line).
"""
import json, subprocess, sys, tempfile, html
from pathlib import Path
from playwright.sync_api import sync_playwright

HERE = Path(__file__).resolve().parent
FONT = HERE.parent / "guide" / "fonts"
W, H = 1080, 1920
CSS = f"""
@font-face {{ font-family: A; src: url('{(FONT/'AtkinsonHyperlegible-Bold.ttf').as_uri()}'); font-weight: 700; }}
@font-face {{ font-family: A; src: url('{(FONT/'AtkinsonHyperlegible-Regular.ttf').as_uri()}'); font-weight: 400; }}
html,body {{ margin:0; width:{W}px; height:{H}px; background:transparent; font-family:A, sans-serif; color:#fff; }}
.wrap {{ position:absolute; left:70px; right:70px; }}
.tag {{ display:inline-block; background:#dc2626; font-weight:700; font-size:54px; letter-spacing:.06em; padding:14px 30px; border-radius:14px; }}
.big {{ font-weight:700; font-size:92px; line-height:1.08; text-shadow:0 4px 24px rgba(0,0,0,.85); }}
.mid {{ font-weight:700; font-size:66px; line-height:1.15; text-shadow:0 3px 18px rgba(0,0,0,.9); margin-top:30px; }}
.card {{ background:rgba(16,42,67,.86); border-left:16px solid #f97316; border-radius:28px; padding:44px 52px; }}
.step {{ font-weight:700; font-size:76px; line-height:1.12; }}
.sub {{ font-size:48px; line-height:1.3; color:#e6f4f1; margin-top:20px; }}
.brand {{ font-weight:700; font-size:40px; letter-spacing:.22em; color:#8bd3c7; margin-top:44px; }}
.lab {{ display:inline-block; font-weight:700; font-size:46px; letter-spacing:.08em; padding:10px 26px; border-radius:12px; margin-bottom:26px; }}
.small {{ font-size:32px; color:#d9e2ec; margin-top:12px; }}
"""

def e(s): return html.escape(s).replace("\n", "<br>")

def card_html(c, brand, disclaimer):
    k = c.get("kind", "step"); col = c.get("color", "#f97316"); top = c.get("top", 560)
    if k == "tag":
        return f'<div class="wrap" style="top:{c.get("top",300)}px"><div class="tag">{e(c["label"])}</div><div class="big" style="margin-top:34px">{e(c["title"])}</div></div>'
    if k == "big":
        sub = f'<div class="mid">{e(c["body"])}</div>' if c.get("body") else ""
        return f'<div class="wrap" style="top:{c.get("top",300)}px"><div class="big">{e(c["title"])}</div>{sub}</div>'
    if k == "end":
        return (f'<div class="wrap" style="top:{c.get("top",430)}px"><div class="card" style="border-left-color:#0f766e">'
                f'<div class="step">{e(c["title"])}</div><div class="sub" style="margin-top:34px">{e(c["body"])}</div>'
                f'<div class="brand">{e(brand)}</div><div class="small">{e(disclaimer)}</div></div></div>')
    return (f'<div class="wrap" style="top:{top}px"><div class="card" style="border-left-color:{col}">'
            f'<div class="lab" style="background:{col}">{e(c["label"])}</div><div class="step">{e(c["title"])}</div>'
            f'<div class="sub">{e(c.get("body",""))}</div></div></div>')

def run(cmd):
    r = subprocess.run(cmd, capture_output=True, text=True)
    if r.returncode: sys.exit("ffmpeg failed:\n" + r.stderr[-1500:])

def main(spec_path, out):
    spec = json.loads(Path(spec_path).read_text(encoding="utf-8"))
    spec_dir = Path(spec_path).resolve().parent
    clip = (spec_dir / spec["video"]) if not Path(spec["video"]).is_absolute() else Path(spec["video"])
    cards = spec["cards"]; total = sum(c["seconds"] for c in cards)
    start, loop = float(spec.get("start", 0)), float(spec.get("loop_seconds", 1.8))
    brand = spec.get("brand", "NAUTICLEBC")
    disc = spec.get("disclaimer", "Study aid. Not a substitute for training.")
    fade = 0.3
    with tempfile.TemporaryDirectory() as t:
        t = Path(t)
        with sync_playwright() as p:
            b = p.chromium.launch(); pg = b.new_page(viewport={"width": W, "height": H})
            for i, c in enumerate(cards):
                pg.set_content(f"<html><head><style>{CSS}</style></head><body>{card_html(c, brand, disc)}</body></html>")
                pg.evaluate("document.fonts.ready")
                pg.screenshot(path=str(t / f"c{i}.png"), omit_background=True)
            b.close()
        vf = f"scale={W}:{H}:force_original_aspect_ratio=increase,crop={W}:{H},fps=30,setsar=1,format=yuv420p"
        run(["ffmpeg", "-y", "-ss", str(start), "-t", str(loop), "-i", str(clip), "-an", "-vf", vf, str(t / "fw.mp4")])
        run(["ffmpeg", "-y", "-i", str(t / "fw.mp4"), "-vf", "reverse", str(t / "bw.mp4")])
        (t / "list.txt").write_text("file 'fw.mp4'\nfile 'bw.mp4'\n")
        run(["ffmpeg", "-y", "-f", "concat", "-safe", "0", "-i", str(t / "list.txt"), "-c", "copy", str(t / "boom.mp4")])
        # Darken a little so white text reads on bright footage.
        inputs = ["-stream_loop", "-1", "-i", str(t / "boom.mp4")]
        for i in range(len(cards)): inputs += ["-loop", "1", "-i", str(t / f"c{i}.png")]
        parts = ["[0:v]eq=brightness=-0.08[v0]"]; at = 0.0
        for i, c in enumerate(cards):
            s, d = at, c["seconds"]; at += d
            parts.append(f"[{i+1}:v]format=rgba,fade=t=in:st={s}:d={fade}:alpha=1,fade=t=out:st={s+d-fade}:d={fade}:alpha=1[o{i}]")
            parts.append(f"[v{i}][o{i}]overlay=enable='between(t,{s},{s+d})'[v{i+1}]")
        run(["ffmpeg", "-y", *inputs, "-filter_complex", ";".join(parts), "-map", f"[v{len(cards)}]",
             "-t", str(total), "-c:v", "libx264", "-preset", "veryfast", "-crf", "23", "-pix_fmt", "yuv420p",
             "-movflags", "+faststart", str(out)])
    print(f"wrote {out} ({total:.0f} s)")

if __name__ == "__main__":
    if len(sys.argv) != 3: sys.exit(__doc__)
    main(sys.argv[1], sys.argv[2])
