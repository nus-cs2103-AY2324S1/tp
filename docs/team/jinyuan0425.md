---
layout: page
title: Jin Yuan's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: `Card` [\#63](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/63)
  * What it does: Key class for flashcards to be created by users.
  * Justification: This class, along with the `question` and `answer` classes, allow for uses to create a flashcard 
  object using lesSON.
  * Highlights: The created flashcard will be displayed in the deck on the GUI.

* **New Feature**: `Priority Queue` [\#84](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/63)
  * What it does: Allows the flashcards created by users to be sorted by priority.
  * Justification: This allows users to practise their set of flashcards in a dynamic order, depending on the urgency
  of the card, making the revision session more effective..
  * Highlights: The cards now contain a priority field which determines the order in which they are displayed in the 
  deck on the GUI.

* **New Feature**: `Solve Count` ([\#125](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/125), [\#127](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/127))
  * What it does: Allows users to view how many times they have completed a specific flashcard.
  * Justification: This allows users to keep track of their learning for each specific card and enable them to gauge their familiarity with it.
  * Highlights: Solve count will be displayed at the bottom of each card, along with the question and due date of the card.

* **New Feature**: `Goal` [\#132](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/132)
  * What it does: Allows users to set their target number of flashcards to complete for the session, as well as view how many they have completed for the session.
  * Justification: This allows users to track their learning for the day and set realistic goals for themselves to achieve in their practice sessions.
  * Highlights: On starting the app, goal will be set to deck size. Maximum target cannot be more than 2147483647.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jinyuan0425&breakdown=true)

* **Enhancements to existing features**:
  * Added test cases for classes `question`, `answer` and `card`. This included creating
  `TypicalCards` and `CardBuilder` classes to facilitate testing. [\#63](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/63)
  * Added priority field to card to facilitate priority queue feature. Added new cards to `TypicalCards` and new test
  cases for `UniqueCardList` to test the new feature. [\#84](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/84)
  * Added more test cases for `Card` to test for `SolveCount`. [\#125](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/125)

* **Documentation**:
  * User Guide:
    * Updated glossary with keywords used [\#119](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/119)
    * Updated formatting and images in UG for add and delete features [\#119](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/119)
    * Fixed bugs identified during PE-D relating to documentation in UG [\#201](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/201)
    * Updated formatting of UG for features [\#214](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/214) 
    * Added exit feature into UG [\#215](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/215)
    * Revamped the UG with a new standardised format. This included formatting, images as well as overall 
    standardisation for each feature. ([\#218](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/218), 
    [\#221](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/221), [\#222](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/222), 
    [\#226](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/226), [\#229](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/229),
    [\#232](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/232), [\#234](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/234))
  * Developer Guide:
    * Added use cases for the functions to be implemented in v1.2 [\#42](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/42)
    * Added proposed features and functions to be implemented in v1.3 [\#97](https://github.com/AY2324S1-CS2103T-W17-4/tp/pull/97)

* **Community**:
  * Reviewed 10 total PRs for lesSON
  * Reported bugs and suggestions for other teams [Issues](https://github.com/jinyuan0425/ped/issues)
