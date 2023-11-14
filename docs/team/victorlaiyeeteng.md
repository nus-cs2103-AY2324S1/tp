---
layout: page
title: Victor Lai's Project Portfolio Page
---

### Project: FriendBook

FriendBook - is a desktop app for managing friend contacts and plan details,
optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX.
If you can type fast, FriendBook can get your plans management done faster than traditional GUI apps.
It is written in Java, and has about 17kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the functionality for users to add plan.
  * What it does: allows user to add a plan to his FriendBook, and associate the plan to an existing friend.
  * Justification: This feature is a key functionality of FriendBook which requires plans to be added, so that user can view and manipulate with
  the added plan as required.
  * Highlights:
    * Decided on `FRIEND_NAME` as the most user-friendly identifier of the friend that is to be associated with the plan,
    as it is not only unique to each friend, but also easily retrievable
    * Implemented a search method to `getPersonByName` and check if the friend exists

* **New Feature**: Added the functionality for users to find plans by existing friends' names.
  * What it does: allows user to find existing plans that are associated with the given friend.
  * Justification: This feature provides a clean and neat way for users to filter and view their plans that are of his
  interest (related to queried friend).
  * Highlights:
    * Obtaining the queried friend (if exists) by the `FRIEND_NAME` using the added function stated above was
    needed to get the filtered list of plans associated with the friend
    * Filtering plans based on `FRIEND_NAME` required implementation of `PlanContainsFriendPredicate` to fulfill the previous highlight

* **New Feature**: Added the functionality for users to list all their plans.
  * What it does: allows user to get all their plans without any filters.
  * Justification: This feature provides users the ability to view all their plans after the scenario where they execute the above `find-plan` command to filter
  the plans.

* **Enhance Existing Delete Command**: Modified `DeleteCommand` to ensure friends are deleted safely.
  * What it does: prevents and alerts user when he attempts to delete a friend that is involved in at least 1 plan.
  * Justification: This modification provides logical sense for user who wishes to view complete details of friend associated to his existing
  plans, allowing for better user experience.
  * Highlights: Utilised previously implemented `PlanContainsFriendPredicate` from above to find plans still associated to the friend attempted to be deleted.

* **Enhance Existing Edit Command**: Built on `EditCommand` to ensure edited friends' detailed are reflected in associated plans.
  * What it does: updates relevant plans which are associated to the edited friends' details
  * Highlights:
    * After analysing existing AB3 `EditCommand` implementation that it creates a new `Person` object and replaces the old one,
    hence a similar replacement had to be done in each associated plan.

* **Testing**: Added relevant test and helper files to aid testing of above implementations
  * What it does: tests all new functionalities and bug fixes for optimal code coverage
  * Highlights: Added test files for above implementations. Added test helper files(`TypicalPlans`, `PlanBuilder`) used in other tests.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=victorlaiyeeteng&tabRepo=AY2324S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Set up team's GitHub organization, set up repo, CodeCov and CI
  * Managed releases `v1.3(trial) - v1.3(final)`(2 releases) on GitHub
  * Documented V1.2 postmortem and V1.3 product demo on developer document

* **Documentation**:
  * User Guide:
    * Added documentation for `add-plan`, `find-plan`, `list-plan`, `clear`, `help`, `exit` commands
    * Update `Command summary` portion to match it with the available commands
  * Developer Guide:
    * Added implementation details of the `list-plan` feature.
    * Wrote Appendix Sections: `Product scope`, `User Stories`, 2 `Use Cases`, `NFR`,
    `Deleting a person`, `Editing a plan` and `Saving data`

* **Review / Mentoring Contributions**:
  * Review bugs flagged out during PE-D and categorised them while removing repeated bugs
