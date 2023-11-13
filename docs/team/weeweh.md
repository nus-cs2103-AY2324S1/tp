---
layout: page
title: Wee Yeow's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Tag [\#94](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/94)
  * What it does: Allows users to categorise cards based on its content and display it visually through the GUI
  * Justification: Allows one to differentiate cards and allow for filtering in planned future enhancements 
  * Highlights: Users can include tags when adding or edit them at a later time. Users can choose to remove all tags as easily.
  All tags are displayed for every individual card for users to categorise them visually.

* **New Feature**: Filter [\#114](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/114)
  * What it does: Allows users to filter for cards based on its question or tag
  * Justification: Allows one to focus on a specific type of content to revise
  * Highlights: Users can either filter for specific tags, a certain phrase a question starts with, or a mix of both.
  Multiple tags can be included on top of a filter for the question.

* **New Feature**: Hint [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134)
  * What it does: Displays a hint to a question of a Card
  * Justification: Users might have trouble recalling the necessary information pertaining the question. They
  could include extra assistance to help them remember the answer in the future when practising the cards.
  * Highlights: A hint is an optional field when adding a new flashcard so a placeholder EmptyHint is added to the
  newly added Card. The hint can be replaced using the Edit feature which only allows for one hint to replace the
  placeholder EmptyHint.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=weeweh&breakdown=true)

* **Project management**:
  * Update JAR file for `1.3(final)` for PE-D

* **Enhancements to existing features**:
  * Refactored all duplicates class created for lesSON that were based on AB3 [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80)
  * Rename some variables for clarity ([\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80),
  [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134))
  * Added standardised JavaDocs naming and remove traces of AB3 ([\#94](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/94),
    [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134))
  * Added `Tag` functionality to `Card`, adapting the `AddCommand` and `EditCommand` to take in the new `Tag` field.
  Added UI logic to displayed tags for cards [\#94](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/94)
  * Wrote additional tests for `AddCommand` and `EditCommand` and their relevant parsers to cover the
  additional `Tag` field for a `Card` [\#94](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/94)
  * Add assertion ([\#108](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/108),
  [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134))
  * Added filter functionality into current `ListCommand` [\#114](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/114) 
    * Users to search cards via 3 ways:
      1. `Question` starting with specified prefix
      2. Cards containing specified `Tag`(s)
      3. or both in the same search
  * Added ability to include hint in a newly added card when using the `AddCommand` and `EditCommand`
  [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134)
  * Refactored the `difficulty` field of a `Card` from a String to an Enumeration, refactored methods to be more clean and
  clear [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134)
  * Added extra unit tests for every Command and for `EditCommandParser` [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134)
  * Added more methods in testutil [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134)
  * Fixed PED bugs [\#200](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/200)

* **Documentation**:
  * User Guide:
    * Added Table of Content and some visual tweaks for all features ([\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80),
    [\#99](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/99), [\#109](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/109),
    [\#114](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/114), [\#134](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/134),
    [\#200](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/200), [\#205](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/205))
    * Added Introduction, Quick Start for lesSON [\#109](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/109)
    * Edit `list` feature to match its functionality to search for specific cards [\#114](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/114)
    * Added `hint`, `help`, `clear`, `import` and `export`, and `goal` feature
    [\#137](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/137)
    * Improved Introduction, Quick Start, and Glossary ([\#205](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/205),
      [\#212](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/212))
    * Revised How to use guide, GUI, Caution, FAQ, Known Issues, and Contact Us sections
      ([\#205](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/205),
      [\#212](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/212))
    * Added page breaks to improve readability on pdf format [\#238](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/238)

  * Developer Guide:
    * Refactored documentation to remove traces of AB3 ([\#41](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/41),
    [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80), [\#99](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/99))
    * Added Target User profile [\#41](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/41)
    * Added Value Proposition [\#41](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/41)
    * Added User Stories [\#41](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/41)
    * Added some more Non-Functional Requirements [\#41](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/41)
    * Added some definitions in Glossary [\#41](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/41)
    * Added Table of Content and some visual tweaks ([\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80),
    [\#99](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/99), [\#114](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/114))
    * Added design considerations to existing documentation of feature `filter` [\#99](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/99)
    * Added proposed implementation and design considerations for the new feature `Markdown support`
    [\#99](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/99)
    * Edit Planned Enhancements [\#227](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/227)
    

  * README:
    * Update key features of lesSON [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80)
    * Added current progress of features [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80)

  * Dev Ops:
    * Added Table of Content [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80)

  * Setting Up:
    * Added Table of Content [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80)

  * Testing:
    * Added Table of Content [\#80](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/80)

  * About Us:
    * Fix bug where teammates images were not displaying [\#82](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/82)
    * Edit typo [\#205](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/205)

* **Community**:
  * Reviewed 37 total PRs for lesSON
  * Reported bugs and suggestions for other teams [Issues](https://github.com/weeweh/ped/issues)
