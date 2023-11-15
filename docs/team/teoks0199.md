---
layout: page
title: Kai Sheng's Project Portfolio Page
---

### Project: FoodNotes

FoodNotes is a desktop address book application used to record reviews on food stalls and menus. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit stalls and menu items in FoodNotes.
    * What it does: Allows the user to edit the details of a stall or menu item.
    * Justification: This feature improves the product significantly because it allows the user to correct any mistakes made when adding a stall or menu item, or even update them with new information, for example if the price of a menu item has increased.
    * Highlights: This enhancement affects existing commands and commands to be added in the future as any change to the fields of a stall or item will change how it is edited. It required an in-depth analysis of design alternatives, for example how to handle editing reviews if they don't already exist, as it is not compulsory for stalls or items to have reviews in FoodNotes.
    * Credits: The ability to have optional parameters while editing was adapted from the `EditCommand` class in the original AB3.

* **New Feature**: Added the ability to find stalls in FoodNotes.
  * What it does: Allows the user to find stalls by name, location or menu items.
  * Justification: This feature improves the product significantly because it allows the user to find stalls that they are looking for easily, for example if they want to find a stall that sells a particular dish.
  * Highlights: This enhancement required the creation of new predicate classes such as `MenuContainsKeywordsPredicate` and `LocationContainsKeywordsPredicate` to handle the filtering of different types of search queries.
  * Credits: The ability to find by multiple keywords was adapted from the `FindCommand` class in the original AB3.

* **Code contributed**:
    * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=teoks0199&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=teoks0199&tabRepo=AY2324S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Contributions to team-based tasks**:
  * Refactored `Person` from the original AB3 into `Stall` for FoodNotes. This was challenging to do at the beginning as I had to remove unnecessary fields and methods, and add new ones to suit the needs of FoodNotes. Every command, even simple ones like adding, had to be refactored for the `Stall` class. I also had to change all existing tests to suit the new `Stall` class.

* **Project management**:
  * Released v1.3 along with the jar file on GitHub.

* **Enhancements to existing features**:
  * Refactored `AddCommand` from AB3 to `AddStallCommand` for FoodNotes.
  * Refactored `DeleteCommand` from AB3 to `DeleteStallCommand` for FoodNotes.

* **Documentation**:
  * User Guide:
    * PR(s): [#138](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/138), [#142](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/142), [#144](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/144)
    * Added documentation for item management features in section 4.5 of the UG.
    * Added command summary for all features in section 6 of the UG.
  * Developer Guide:
    * PR(s): [#84](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/84)
    * Added implementation details of the `find-by-item` feature.
    * Added `FindItemSequenceDiagram`.
    * Updated UML diagrams for `ArchitectureSequenceDiagram`, `CommitActivityDiagram`, `LogicClassDiagram`, `ParserClasses`, `StorageClassDiagram`, `UiClassDiagram`.
      

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#60](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/60), [#63](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/63), [#72](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/72), [#101](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/101)
  * Reported bugs and suggestions for other teams in the class: [PE-D Issue Tracker](https://github.com/teoks0199/ped/issues)
  * Peer evaluated iPs and gave insightful feedback to peers on Canvas.
    * iPs reviewed: [iP-1](https://github.com/ldinghan/ip/releases), [iP-2](https://github.com/nicleongyj/Taskmaster/releases)
  * Some parts of Storage, such as `JsonAdaptedStall`  and `JsonAdaptedItem`, were written by me. My teammates pulled my implementation from my forked repo before committing to the main repo.


