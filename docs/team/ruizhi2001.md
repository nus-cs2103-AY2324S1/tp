---
layout: page
title: RuiZhi's Project Portfolio Page
---

### Project: FoodNotes

FoodNotes is a desktop address book application used to record reviews on food stalls and menus. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

<hr>
Given below are my contributions to the project.

* **New Feature**: Added the ability to add and delete items from stalls in FoodNotes.
    * **PR(s):** [#62](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/62)
    * **What it does:** Allows the user to add items to a stall.
    * **Justification:** This feature improves the product significantly because it allows the user to add items to a stall, which is a core feature of FoodNotes.
    * **Highlights:** This enhancement affects existing commands and commands to be added in the future as any change to the fields of a stall or item will change how it is added. It required an in-depth analysis of design alternatives, for example how to handle reviews in other features, as it is not compulsory for items to have reviews in FoodNotes.
    * **Credits:** The ability format of parameters was adapted from the `AddCommand` class in the original AB3.


* **New Feature**: Added the ability to sort stalls by location or rating in FoodNotes.
  * **PR(s):** [#101](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/101)
  * **What it does:** Allows the user to sort stalls by location or rating.
  * **Justification:** This feature improves the product significantly because it allows the user to sort stalls, which is a core feature of FoodNotes.
  * **Highlights:** This enhancement required the creation of new comparator classes such as `StallLocationComparator` and `StallRatingComparator` to handle the sorting of different types of search queries.


* **Bug fixing**: Fixed UI bugs in FoodNotes.
    * **PR(s):** [#207](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/207)
    * **What it does:** Fixed UI bugs in FoodNotes.
    * **Justification:** This feature improves the product significantly because it provides convenience and thus allows the user to have a better user experience when using FoodNotes.
    * **Highlights:** This enhancement affects existing commands and commands to be added in the future as any change to the fields of a stall or item will change how the UI is rendered. For example, how the view of the UI changes when a new command is added in the future.
    * **Credits:** This design is adapted from the original AB3 UI.


* **Code contributed:**
  * View my code contributions on RepoSense [here](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=Ruizhi2001&tabRepo=AY2324S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).


* **Project management:**
  * Set up Github team organization and project repo.
  * Set up CodeCov for continuous integration.
  * Decided and improved on the workflow to follow for reviewing PRs.


* **Enhancements to existing features:**

  * **Enhancement**: Enhanced UI component of AB3 for FoodNotes.
    * **PR(s):** [#114](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/114), [#117](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/117), [#126](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/126), [#132](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/132)
    * **What it does:** Refactored the UI component of AB3 to suit the needs of FoodNotes.
    * **Justification:** This feature improves the product significantly because it provides convenience and thus allows the user to have a better user experience when using FoodNotes.
    * **Highlights:** This enhancement affects existing commands and commands to be added in the future as any change to the fields of a stall or item will change how the UI is rendered. For example, how the view of the UI changes when a new command is added in the future.
    * **Credits:** This design is adapted from the original AB3 UI.


* **Documentation**:
  * Developer Guide:
    1. Add in User Stories
       * PR(s): [#28](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/28/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
    2. Add Sequence and Activity Diagram for `AddItemCommand`.
       * PR(s): [#87](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/87/files)
       * Added UML diagrams for `AddItemActivityDiagram`, `AddItemSequenceDiagram`
    3. Add Class Diagram for Model Class
       * PR(s): [#87](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/87/files)
       * Updated UML diagrams for `BetterModelClassDiagram`, `ModelClassDiagram`
  * User Guide:
    1. Update Table of Contents and hyperlinks in UserGuide.
       * PR(s): [#145](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/145), [#292](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/292)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#60](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/60), [#65](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/65)
  * Peer evaluated iPs and gave insightful feedback to peers on Canvas.
    * iPs reviewed: [iP-1](https://github.com/WangCheng0116/ip/releases), [iP-2](https://github.com/LamJiuFong/ip/releases)
  * [Reported bugs and provided suggestions](https://github.com/Ruizhi2001/ped) for team CampusConnect
