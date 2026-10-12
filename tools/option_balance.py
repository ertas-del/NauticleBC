#!/usr/bin/env python3
"""Reports multiple-choice questions where the correct option is much longer than the distractors.

Learners (and exam-takers) can guess such questions by length alone. Usage:
    python3 tools/option_balance.py [folder ...]   # default: all of content/
Prints the share of questions whose correct option is more than 1.3x the average distractor length,
and lists the worst offenders with --list.
"""
import glob
import json
import sys

args = [a for a in sys.argv[1:] if not a.startswith("--")]
show = "--list" in sys.argv
folders = args or ["content"]
rows = []
for folder in folders:
    for f in sorted(glob.glob(f"{folder}/**/questions-*.json", recursive=True)):
        for q in json.load(open(f, encoding="utf-8"))["questions"]:
            lens = [len(o) for o in q["options"]]
            correct = lens[q["correct_index"]]
            others = (sum(lens) - correct) / (len(lens) - 1)
            rows.append((correct / others if others else 1.0, q["id"], f))
bad = [r for r in rows if r[0] > 1.3]
print(f"{len(rows)} questions, {len(bad)} ({100 * len(bad) // max(len(rows), 1)}%) have a correct option over 1.3x the distractor average")
if rows:
    print(f"mean ratio {sum(r[0] for r in rows) / len(rows):.2f}")
if show:
    for ratio, qid, f in sorted(bad, reverse=True)[:50]:
        print(f"{ratio:.2f} {qid}")
