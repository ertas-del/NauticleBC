# Licence checklist

Status: **draft, needs human verification before release.** This records what the research found; it is not legal advice.

| Material | Status for this app | Action |
|---|---|---|
| Canadian Collision Regulations (federal regulation) | Federal regulations may be reproduced free if accurate and not presented as the official version (Reproduction of Federal Law Order). | Quote sparingly with a "not the official version" note; link to Justice Laws. Re-check the order text before release. |
| TC publications (TP 14070, TP 2293, TP 4957E, TP 968) | Editions found state permission to copy or reproduce with acknowledgement. Not all editions confirmed. | Check the edition statement of each TP we use; record it in `content/sources.json`. |
| CHS tide and current data | Free for non-navigational, non-commercial use only. | Use only for teaching examples. Do not present as navigational. Seek a CHS licence before any commercial or in-app live predictions. |
| CHS charts (ENC/RNC) | **Excluded.** No purchase, display, tracing or close recreation of CHS charts (decision 2026-10-13). | Use original synthetic training charts instead. Do not bring chart-derived content in by another route. |
| Original synthetic training charts | Our own work, fictional places. | Record authorship and review. Draw chart symbols from scratch; do not copy CHS artwork. |
| CHS NONNA bathymetry | OGL-Canada, non-navigational use. | Fine for visual context only. |
| Coast Guard List of Lights / TP 968 | Published by the Canadian Coast Guard / DFO. Licence terms to confirm. | Needed only for the later lights map. |
| ECCC marine forecasts | Data Server End-use Licence permits commercial use with attribution. | Needed only for the later weather module. |
| Provider courses, textbooks, exam banks | Copyrighted. | Do not copy. Use only as inspiration; write original content. |
| OpenStreetMap / MapLibre / PMTiles | ODbL (credit, share-alike for derived databases) / open-source licences with attribution. | Read ODbL against our use before the learning map; compare a BC open-government coastline dataset. Attribution screen in the app. |

## Open items

- Confirm the Reproduction of Federal Law Order wording and our "not official" notice.
- Confirm edition and reproduction statement for each TP used in the first release.
- Choose the coastline source for the BC learning map (OpenStreetMap or BC open data) after reading its terms.
- Confirm the Coast Guard Open Information Licence covers the List of Lights data we want to show.
