---
layout: page
title: Jason's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Practice Date Scheduling [\#93]()
  * What it does: Add a nextPracticeDate field to each card, and make sure the deck is sorted by that field. This puts all cards in the deck in a schedule for the user.
  * Justification: The user can easily know when to practise their cards. This is also better than the previous method of sorting arbitrarily.
  * Highlights: Taking advantage of comparators to make implementing sorting by nextPracticeDate simple

* **New Feature**: Spaced Repetition System [\#93]()
  * What it does: Combines set difficulty and practice date features to make cards set a new practice date according to the new difficulty.
  * Justification: By tying practice date to difficulty, it makes practising the cards more meaningful because users will be able to practise the harder cards more.
  * Highlights: Uses a hidden lastPracticeDate field to calculate a new practice date proportional to the duration between the last two practices.

* **New Feature**: Random Command [\#135]()
  * What it does: Allow user to practise with a random card instead of a specific index
  * Justification: For users that want to test their understanding by practising randomly
  * Highlights: Random command creates a special index r which can be referenced by solve and set commands

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=dioclei&breakdown=true)

* **Enhancements to existing features**:
  * Enhanced Deck class by adding more test cases [\#75]()
  * Enhanced Practice Date Scheduling by making practice date show up on card UI. [\#113]()
  * Enhanced UI and colors, especially card UI [\#118]()

* **Documentation**:
  * Which documentation were written by me for which guide (include PR number)
  * User Guide:
    * Created initial mockups of the UI for user guide
    * Continued updating of ui mockups in v1.3 [\#120]()
    * Wrote description for set difficulty to explain spaced repetition part of set command [\#121]()
    * Updated introduction to have less jargon [\#129]()
    * Updated layout of user guide by adding installation section [\#129]()
    * Wrote an overview for the user interface [\#129]()
    * Wrote a tutorial for new users [\#129]()
    * Added `random` feature [\#136]()
  * Developer Guide:

* **Community**:
  * Contributions to the community (include examples i.e. PR number / screenshots)
  * e.g. PRs reviewed (with non-trivial review comments): [\#44](), [\#94](), [\#116]()
  * e.g. Contributed to forum discussions (examples: )
  * e.g. Reported bugs and suggestions for other teams in the class (examples: )
  * e.g. Some parts of the history feature I added was adopted by several other class mates ()

* **Tools**:
  * List of tools used (include examples i.e. PR number / screenshots)
