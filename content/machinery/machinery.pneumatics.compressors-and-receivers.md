---
id: machinery.pneumatics.compressors-and-receivers
title: "Pneumatics: compressors, receivers and compressed air safety"
module: machinery
tickets: [svmo]
order: 240
summary: How a reciprocating air compressor works, what the unloader, intercooler, receiver, relief valve and drains do, where compressed air is used on small vessels, and the hazards of high-pressure air and water in the system.
objectives:
  - Describe how a reciprocating air compressor compresses air and why two stages are used.
  - Name the fittings on a receiver and state the job of each.
  - Explain why receivers and lines must be drained of condensation.
  - State the safety rules for working with compressed air.
prerequisites: [machinery.hydraulics.systems]
estimated_minutes: 14
key_facts:
  - "Air is compressible, unlike hydraulic oil. Compressing air makes it hot."
  - "A reciprocating compressor draws air through a suction valve, squeezes it and pushes it out through a discharge valve."
  - "A two-stage compressor has an intercooler between the stages. It cools the air, improving efficiency and lowering the final temperature."
  - "An unloader lets the compressor start without working against pressure, so it reaches speed first. A pressure switch starts and stops it between cut-in and cut-out pressures."
  - "A receiver stores the air. Its safety relief valve protects it from over-pressure and must never be altered or blanked off."
  - "Condensation collects in the receiver and lines. Drain it regularly to prevent corrosion and faulty operation."
  - "Never run a compressor with its discharge valve closed. Use the oil grade the maker names, because carbon and oil vapour can burn in hot compressed air."
  - "Never use compressed air on skin or clothing, or point a hose at a person. Air can enter the bloodstream through the skin, and flying particles injure eyes."
source_ids: [tp14814e, tp2293-2016]
review_status: needs_expert_review
---

# Pneumatics: compressors, receivers and compressed air safety

Compressed air is a store of energy. On a small vessel it is used to start larger diesel engines, to blow air horns,
to run tools and to drive some controls and valve actuators. The SVMO syllabus asks you to know the ==principle of the
compressor, the equipment around the receiver, and the safety precautions==. It does not go deep into theory, but it
expects you to recognise the parts and the hazards.

## Air versus oil

Hydraulic oil is almost incompressible, which is why it moves things with a precise, instant push. ==Air is
compressible==. When you squeeze it, the volume falls and the pressure and temperature rise. That has two effects: air
systems are springy and forgiving, so they cushion shocks, and the heat of compression has to be removed. A bicycle pump
gets warm at the outlet for exactly this reason.

## The reciprocating compressor

Most small vessels use a piston compressor, driven by an electric motor or by the engine through a belt.

1. On the downstroke the piston draws air in through the ==suction valve== (also called the inlet valve), which opens
   because the pressure in the cylinder falls.
2. On the upstroke both valves are shut, the air is squeezed and its pressure and temperature rise.
3. When the cylinder pressure exceeds the pressure in the line, the ==discharge valve== lifts and the compressed air
   passes to the cooler and the receiver.

Both valves are simple spring-loaded plates and open and close by pressure difference alone. A leaking valve shows up
as a long fill time, a hot discharge and low final pressure.

### Why two stages

Squeezing air once from atmospheric to 30 bar makes it very hot and wastes energy. So high-pressure compressors work in
stages. The first (low-pressure) cylinder raises the air part way, the air then passes through an ==intercooler== that
removes heat, and the second (high-pressure) cylinder raises it to full pressure. An ==aftercooler== may cool the air
again before it reaches the receiver. Cooling between stages saves power and lowers the final air temperature, which
reduces the risk of oil carbon catching fire.

## Controls

- An ==unloader== holds a valve open, or vents the cylinder, as the compressor starts. The machine reaches speed with no
  back-pressure and then goes on load. It also cuts the load when the receiver is full on some designs.
- A ==pressure switch== starts the compressor at its cut-in pressure and stops it at cut-out. On small systems this is
  how the receiver is kept topped up automatically.
- A high temperature or low oil pressure switch stops the compressor if something is wrong.
- Cooling is by air (finned cylinders) or by water on larger machines. Check cooling water flow if there is a water jacket.
- The crankcase holds lubricating oil like an engine. Check the level and condition, and use the grade the maker lists.
  Oil carry-over into the air reaches the receiver as a mist and as a film in the pipes.

## The receiver and its fittings

The receiver (air bottle) is a pressure vessel that stores air so the compressor can run intermittently and so there is
a supply for engine starting. A typical receiver carries:

| Fitting | Job |
|---|---|
| ==Safety relief valve== | Lifts at a set pressure above normal working pressure and vents air, protecting the receiver from over-pressure. |
| Pressure gauge | Shows the air pressure. |
| ==Drain valve== | At the lowest point, to remove water and oil. |
| Stop (isolating) valve | Isolates the receiver from the line, for example when working on the system. |
| Pressure switch | Controls the compressor. |
| Manhole or inspection point | On larger vessels, for inspection. |

The relief valve is the last line of defence. ==Never adjust, jam or blank it==. If it lifts regularly, find the reason:
usually a faulty pressure switch or unloader. Valves are tested at intervals by lifting them briefly, following the
maker's procedure, and receivers are inspected and tested under the rules that apply to the vessel.

## Condensation and air quality

Atmospheric air carries water vapour. When it is compressed and cooled the water condenses, and it collects in the
receiver, the coolers and the low points of the lines. Water in the system ==causes corrosion, freezes in cold weather,
and makes pneumatic controls stick or act erratically==. So:

- Drain the receiver and the coolers at the lowest points on a regular schedule, and after every long run.
- Open the drain slowly and stand to one side. Water, oil and air come out together under pressure.
- Fit and maintain an air dryer and filter where tools or controls depend on clean, dry air.
- Keep the compressor intake clean and away from fumes. A choked inlet filter cuts the output.

## Oil, heat and fire

Compressor oil that is the wrong grade, or old, can leave carbon in the valves and discharge pipe. In hot, compressed air
this carbon and oil vapour can ignite, and there have been explosions in discharge lines and receivers. Reduce the risk by:

- Using the grade of oil the maker states, and changing it on time.
- Keeping the coolers clean and the cooling water flowing.
- ==Never running with the discharge valve closed==, and never starting the compressor against a closed discharge.
- Draining the receiver so oil and water do not build up.

## Safe use of compressed air

- ==Never use compressed air on skin or clothes==, and never point a hose at anyone. Air can be forced through the skin
  into the bloodstream and cause serious injury.
- Do not use air to dust off a bench without eye protection. Flying chips and dust damage eyes.
- Release the pressure before opening a line, a fitting or a valve. Open a drain to confirm the pressure has gone.
- Whip-checks or ties on hoses stop a loose hose from lashing about under pressure.
- Do not weld, drill or heat a receiver or a line that has been pressurised or that has held oil.
- A receiver whose gauge is unreliable or whose relief valve is faulty is out of service until repaired.

## A BC engine-room example

A 20 m tug out of Prince Rupert uses an air-started main engine. On the morning round you open the receiver drain and a
cupful of rust-coloured water comes out. You drain until only air comes through, write it in the engine log and add a
reminder to drain after each trip. You also notice the compressor takes twice as long to charge as last month, so you
note that the valves may be leaking and report it for service before the engine will not start.

## Common mistakes

- Never draining the receiver, then finding the drain seized.
- Adjusting or gagging a relief valve that keeps lifting.
- Starting a compressor with the discharge closed.
- Using the air hose to clean overalls.
- Believing air pressure is harmless because "it is only air".

## Check yourself

1. What does the intercooler do, and why?
2. Why does a compressor start unloaded?
3. What is the safety relief valve for, and may you adjust it to stop it lifting?
4. Why must the receiver be drained?
5. Why is it dangerous to blow compressed air on your clothing?

---

*Study aid, not an operating manual. This lesson is written in original words from the syllabus in TP 2293 Chapter 33
and the SVMO study guide TP 14814E. Follow the maker's manual and the rules for pressure vessels that apply to your vessel.*
