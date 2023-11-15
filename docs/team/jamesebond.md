---
layout: page
title: Du Kaixuan's Project Portfolio Page
---

### Project: FoodNotes

FoodNotes is a desktop address book application used to record reviews on food stalls and menus. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

<hr>
Given below are my contributions to the project.

* **New Feature**: Added the ability to add and delete stall reviews FoodNotes.
  * **PR(s):** [#63](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/63) [#68](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/68)
  * **What it does:** Allows the user to add reviews to a stall.
  * **Justification:** This feature improves the product significantly because it allows the user to add reviews to a stall, which is a core feature of FoodNotes.
  * **Highlights:** This enhancement required the creation of a new `Review` class to handle the addition of reviews to a stall. It also required an in-depth analysis of design alternatives, for example how to handle adding reviews if they don't already exist, as it is not compulsory for stalls to have reviews in FoodNotes.

* **Code contributed:**
  * View my code contributions on RepoSense [here](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=jamesebond&tabRepo=AY2324S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Project management:**
  * come up with the idea of FoodNotes together with my teammates.

* **Documentation**:
  * Developer Guide:
    1. Add in User Stories
      * PR(s): [#28](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/28/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
    2. Add Sequence and Activity Diagram for `ReviewStallCommand`.
      * PR(s): [#91](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/91)
      * Added UML diagrams
  * User Guide:
    1. Update Content page and command features.
      * PR(s): [#96](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/96/files) [#135](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/135/files)

* **Community**:
  * Peer evaluated iPs and gave insightful feedback to peers on Canvas.
    * iPs reviewed: [iP-1](https://github.com/joel-foo/ip/releases), [iP-2](https://github.com/LoMaply/ip/releases)
  * [Reported bugs and provided suggestions](https://github.com/jamesebond/ped) for team CampusConnect
