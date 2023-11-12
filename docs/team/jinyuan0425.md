---
layout: page
title: Jin Yuan's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.


* **New Feature**: `Solve Count`
  * What it does: Allows users to view how many times they have completed a specific flashcard.
  * Justification: This allows users to keep track of their learning for each specific card and enable them to gauge their familiarity with it.
  * Highlights: Solve count will be displayed at the bottom of each card, along with the question and due date of the card.

* **New Feature**: `Goal`
* What it does: Allows users to set their target number of flashcards to complete for the session, as well as view how many they have completed for the session.
* Justification: This allows users to track their learning for the day and set realistic goals for themselves to achieve in their practice sessions.
* Highlights: On starting the app, goal will be set to deck size. Maximum target cannot be more than Integer.MAX_VALUE.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jinyuan0425&breakdown=true)

* **Enhancements to existing features**:
  * Which enhancements were written by me (include PR number)
  * Created skeleton classes and added test cases for classes `question` and `answer` (PR [\#63](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/63)).
  * Changed the card list to be a priority cue to allow for the cards to be displayed in order of priority, later updated to be the due date (PR [\#84](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/84))
  * e.g. Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * e.g. Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * Which documentation were written by me for which guide (include PR number)
  * User Guide:
    * Updated glossary with keywords used [\#119](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/119)
    * Updated formatting in UG for add and delete features [\#119](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/119)
  * Developer Guide:
    * Added use cases for the functions to be implemented in v1.2 [\#42](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/42)
    * Added proposed features and functions to be implemented in v1.3 [\#97](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/97)

* **Community**:
  * Contributions to the community (include examples i.e. PR number / screenshots)
  * e.g. PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * e.g. Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * e.g. Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * e.g. Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * List of tools used (include examples i.e. PR number / screenshots)
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
