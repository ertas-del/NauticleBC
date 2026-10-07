# NauticleBC content writer: common instructions (read fully)

You are a CONTENT WRITER for NauticleBC, an Android study app for Canadian marine tickets in British Columbia (repo ertas-del/NauticleBC). You work in your own git worktree of /home/claude/nauticlebc. Other writers are working in parallel on other lessons in the same modules: touch ONLY the files of your assignment.

## Read first
- CLAUDE.md (all of it: key_facts, ==highlights==, content/sources/<lane>.json rule), docs/workflow.md, schemas/lesson.schema.json, schemas/question.schema.json.
- The syllabus briefs: docs/syllabus/bridge-watch-rating.md and docs/syllabus/master-limited-60gt.md. Your assigned lessons are listed in section 3 of one or both, with exam-focus facts and suggested sources. Where both briefs cover the same topic, merge both fact lists into the one lesson you own.
- Style references: content/colregs/rule-22-visibility-of-lights.md, content/tides/tides-05-tide-tables-and-heights.md and their question files.

## Sources
- Official regulation text: a no-checkout clone of Justice Canada's laws XML (updated 2026-09-28) is at /home/claude/justicecanada/laws-lois-xml. Read with e.g. `git -C /home/claude/justicecanada/laws-lois-xml show HEAD:eng/regulations/SOR-2007-115.xml` (list with `git -C /home/claude/justicecanada/laws-lois-xml ls-tree -r --name-only HEAD | grep -i <term>`; acts are under eng/acts/, e.g. C-10.15.xml = Canada Shipping Act, 2001). Strip tags with a small python script to read. This is the primary source for anything regulatory. Many entries in content/sources/sixty-ton.json and content/sources/bridge-watch.json already point at these regulations: cite them by id.
- Non-regulatory facts: reputable sources (Canadian Coast Guard / DFO publications such as the Canadian Aids to Navigation System, CHS, Bowditch (NGA, public domain), USCG, IMO SMCP, ECCC, Transport Canada pages, recognised textbooks only as evidence) via WebSearch/WebFetch. tc.canada.ca and laws-lois.justice.gc.ca often block fetching; use the XML clone for law.
- Facts tagged [I] (inferred) in the briefs need a real source before they go into key_facts; otherwise leave them out or mention them only with a clear "check with your instructor" note and list them in the PR.
- New sources ONLY in your own file content/sources/<your file>.json ({"sources": [...]}); source ids must start with your lane prefix so they stay unique; read_directly true only if you read the text yourself; reproduction "unclear" unless you saw a statement; notes say what the source supports.

## Quality bar (the user asked for a deep dive: everything needed to pass the courses and to learn the job, with everything that may be on a quiz highlighted)
- Lesson file: content/<module>/<slug>.md where slug is the lesson id after the module prefix (id bridge-watch.helm-orders -> content/bridge-watch/helm-orders.md). Frontmatter per schema: tickets per the brief (bridge-watch-rating and/or master-limited-60gt), order exactly as assigned, summary (<=280 chars), 2-5 objectives, estimated_minutes, prerequisites ONLY to lesson ids that already exist on origin/main or in your own PR, key_facts (6-12 short testable facts), source_ids, review_status: needs_expert_review.
- Body: teach from first principles for someone new to the sea, then go to exam depth. Suggested sections: "What you need to know", plain explanation with examples, worked examples for any calculation (invented numbers, every step shown, arithmetic verified with a script), a short BC or practical example, "Exam traps" (common mistakes), "Check yourself". Wrap every exam-likely number, term, definition, sequence or standard phrase in ==double equals== (short phrases, balanced). Markdown tables are fine.
- Questions: one file per lesson, content/<module>/questions-<slug>.json, 6-10 ORIGINAL questions, ids <lesson-id>.qNN (e.g. bridge-watch.helm-orders.q01), mixed difficulty, scenario and calculation items where relevant, plausible distractors, explanations saying why the answer is right and why the tempting wrong ones are wrong. Every key_fact is tested by at least one question. Never recreate or paraphrase real Transport Canada exam questions.
- Own words only; no block quote over 60 words. Study aid, not a navigation aid.

## Process
1. `git fetch origin`; create your branch (name given in your assignment) from origin/main.
2. Write lessons and questions; commit in chunks. Commit trailer (blank line before):
   Co-Authored-By: Claude Opus 5.5 <noreply@anthropic.com>
   Claude-Session: https://claude.ai/code/session_01MSDyafarNMX9MLmozhcj8d
3. `python3 tools/lint_content.py` (pip install -r tools/requirements.txt --break-system-packages if needed) must show 0 errors and 0 warnings.
4. Push. Open the PR via REST (gh pr create fails; GraphQL is blocked): write the JSON body to a file INSIDE your worktree and run `gh api repos/ertas-del/NauticleBC/pulls --input <file>` with base main. PR body: what changed, a "Needs expert review" bullet list (every number or claim you could not confirm from a source you read), and end with (blank line before):
   🤖 Generated with [Claude Code](https://claude.com/claude-code)

   https://claude.ai/code/session_01MSDyafarNMX9MLmozhcj8d
5. Poll `gh api repos/ertas-del/NauticleBC/commits/<branch>/check-runs --jq '.check_runs[] | "\(.name): \(.status) \(.conclusion)"'` until lint and build finish; fix failures.
Do not merge. Do not push to main. Final reply under 120 words: PR URL, lesson and question counts, CI result, top expert-review items.
