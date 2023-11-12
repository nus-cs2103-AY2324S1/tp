---
layout: page
title: Wee Yeow's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Hint
  * What it does: Displays a hint to a question of a Card
  * Justification: Users might have trouble recalling the necessary information pertaining the question. They could include extra assistance to help them remember the answer in the future when practising the cards.
  * Highlights: A hint is an optional field when adding a new flashcard so a placeholder EmptyHint is added to the newly added Card. The hint can be replace using the Edit feature which only allows for one hint to replace the placeholder EmptyHint.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=weeweh&breakdown=true)
* **Enhancements to existing features**:
  * Which enhancements were written by me (include PR number)
  * Added `Tag` functionality to `Card`. Allowing users to categorise cards based on content (Pull requests )
  * Wrote additional tests for `AddCommand` and `EditCommand` and their relevant parsers to cover the
  additional `Tag` field for a `Card` (Pull requests [\#36](), [\#38]())
  * Added search functionality in `ListCommand`. Allowing users to search cards via 3 ways:
    * `Question` starting with specified prefix
    * Cards containing specified `Tag`(s)
    * or both in the same search
  * Added ability to include hint in a newly added card when using the `AddCommand`
  * Likewise, when editing a card with a new hint using the `EditCommand`
  * Refactored the difficulty field of a `Card` from a String to an Enumeration, editing the methods involving the `setDifficultyCommand`
  * Added extra unit tests for most of the Commands.

* **Documentation**:
  * Which documentation were written by me for which guide (include PR number)
  * User Guide:
    * Added documentation for the feature `Markdown support` [\#72]()
    * Added Table of Content and some visual tweaks for all features
    * Added User Stories
    * Added Non-Functional Requirements
    * Refactored documentation to remove traces of AB3
    * Edit `list` feature to match its functionality to search for specific cards
    * Added `hint` feature
    * Added `help` feature
    * Added `import` and `export` feature
    * Added `goal` feature
  * Developer Guide:
    * Refactored documentation to remove traces of AB3
    * Added Table of Content and some visual tweaks
    * Added implementation details of the `delete` feature
    * Added documentation for the feature `Markdown support` [\#72]()
    * Added design considerations to existing documentation of feature `filter`: [\#74]()

* **Community**:
  * Contributions to the community (include examples i.e. PR number / screenshots)
  * e.g. PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * e.g. Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * e.g. Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * e.g. Some parts of the history feature I added was adopted by several other classmates ([1](), [2]())

* **Tools**:
  * List of tools used (include examples i.e. PR number / screenshots)
