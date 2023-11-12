---
layout: page
title: Gian Sen's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Card Display [\#72](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/72)
  * What it does: Hide the answer from the card class from user view
  * Justification: Prevents users from seeing the flashcard answers from the start
  * Highlights: Answer field is in the card but not shown on the Ui

* **New Feature**: HelpCommand [\#130](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/130)
  * What it does: Pops up a window to show the user guide to be used for new users
  * Justification: Ease of access to a user guide link
  * Highlights: New window pop up that allows for automatic copying of link

* **New Feature**: ClearCommand
  * What it does: Removes all cards in the deck, giving users an empty deck [\#124](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/124)
  * Justification: For advance users to clear the deck and add own cards
  * Highlights: Ability to wipe lesSON with a clear command to allow ease of reset

* **New Feature**: SolveCount Ui and Storage [\#128](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/128)
  * What it does: SolveCount can now be displayed in display card and Ui, stored locally as well
  * Justification: Storing solve count in json file will allow ease of reference and statistics tracker
  * Highlights: Solve counts keeps track of number of times a card is solved in history

* **New Feature**: Export [\#133](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/133)
  * What it does: Showcases the current deck of cards and allows users to copy them and send to their peers.
  * Justification: Allows one to share the generated cards with their peers
  * Highlights: Separate window pops up for export

* **New Feature**: Import [\#133](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/133)
  * What it does: Allows users to input the text that they have received from their peers
  * Justification: Users will need to import the cards their peers shared with them in a fixed format
  * Highlights: Separate window pops up for import, will close the app after importing for best Ui experience

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=gsgiansen&breakdown=true)

* **Project management**:
  * Release `1.2`
  * Release `1.3`
  * Release `1.3(final)` for PE-D
  * Release `1.4` for PE

* **Enhancements to existing features**:
  * Removal of test cases that were only applicable to the previous project. [#83](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/83)
  * Hid the answers from the Display Card to simulate exam conditions. [#72](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/72)
  * Wrote additional tests for existing features to increase coverage from 60% to 63%. [\#116](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/116)
  * Enhance existing test cases to increase code coverage from 65% to 69% . [\#209](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/209)

* **Documentation**:
  * User Guide:
    * Edit user guide to remove traces of AB3 and to reflect lesSON for v1.1 [\#44](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/44)
    * Add new screenshots of lesSON at the 1.3 stage [\#110](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/110)
    * Addition of codeCov badge into README [\#107](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/107)
    * Addition of user guide for export and import functionality [\#190](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/190)
    * Add updated screenshots for `v1.2` release [\#44](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/44)
    * Add updated screenshots for `v1.3` release [\#110](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/110)
    * Add updated screenshots for `v1.3b` release [\#190](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull190)

  * Developer Guide:
    * Add search implementation for future developments [\#98](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/98)
    * Updated the diagrams in the guide to reflect latest project flow [\#190](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/190)
    * Included implementation for Export and Import and finalised proposed features [\#190](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/190)
    * Added sequence and activity diagrams for Add and Delete commands [#213](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/214)
    * Added object diagrams illustrating Card and Deck classes [#213](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/213)


* **Tools**:
  * Integrated codecov into GitHub actions and CodeCov badge [\#107](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/107)

