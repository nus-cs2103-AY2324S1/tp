---
layout: page
title: Samuel Mui's Project Portfolio Page
---

### Project: FoodNotes

FoodNotes is a desktop address book application used to record reviews on food stalls and menus. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort stalls by average price in FoodNotes. (PRs [#113](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/113), [#115](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/115))
    * What it does: Allows the user to sort stalls by average price in ascending order to find the cheapest stalls.
    * Justification: This feature improves the product significantly because it allows the user to find the cheapest stalls easily,
  our target users who are students are likely to be more budget conscious and would find this feature useful.
    * Highlights: This enhancement required the creation of a new `Comparator` class, `StallPriceComparator`,
  to handle the sorting of stalls in the model by average price. It also required a new method, `getAveragePrice`, in the `Stall` class
  to dynamically calculate the average price of the stall when necessary and a new `Price` class to represent the price of each menu item.


* **New Feature**: Added the ability to add/delete reviews for menu items in FoodNotes. (PR [#66](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/66))
  * What it does: Allows the user to review menu items in a stall or remove a review from an item.
  * Justification: This feature improves the product significantly because it allows the user to review items in a stall,
  helping users to remember their thoughts on the menu item.
  * Highlights: This enhancement required the creation of a new `ItemReview` class to represent the review of a menu item.
  The review makes use of the `Rating` class to represent the rating of the item which uses star ratings for improved
  user-friendliness.


* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=samuelmui8&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=samuelmui8&tabRepo=AY2324S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Contributions to team-based tasks**:
  * Update the project website from the original AB3 to suit the needs of FoodNotes and update site-wide settings. (PRs [#49](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/49), [#271](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/271))

<div style="page-break-after: always;"></div>

* **Project management**:
  * Released v1.3.trial along with the jar file on GitHub.
  * Released v1.4 along with the jar file on GitHub.

* **Enhancements to existing features**:
    * Adapted the existing storage features to support the new classes that are added into FoodNotes, by writing new
  storage components such as `JsonAdaptedItemReview`, `JsonAdaptedStallReview` and `JsonSerializableMenu`(PRs [#83](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/83),  [#90](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/90)).
  This was particularly challenging because the existing storage components were not designed to support the nested nature of our new classes, where `AddressBook` contains a list of `Stall` objects which itself contains a list of `Item` objects.
    * Wrote additional tests for the storage components, increasing coverage from 54% to 58% (PR [#124](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/124))

* **Documentation**:
  * User Guide:
      * Added documentation for stall sorting features in section 4.3 of the UG. (PR [#136](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/136))
      * Added screenshots for stall finding features in section 4.4 of the UG. (PR [#146](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/146/files))
  * Developer Guide:
    * Added implementation details of the `sort-stalls-price` feature. (PR [#85](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/85))
    * Wrote and formatted user stories. (PR [#278](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/278))
    * Wrote the section on use cases. (PR [#270](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/270))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#27](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/27),
  [#41](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/41), [#57](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/57),
  [#65](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/65), [#97](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/97)
  * Peer evaluated iPs and provided feedback on canvas.
    * iPs reviewed: [iP-1](https://github.com/flexibo/ip), [iP-2](https://github.com/amosting/ip)
  * Reported bugs and provided suggestions for team F.A.K.E.J.A.R.V.I.S. during PED ([Issue Tracker](https://github.com/samuelmui8/ped/issues))
