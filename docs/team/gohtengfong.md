---
layout: page
title: Goh Teng Fong's Project Portfolio Page
---

### Project: FriendBook

FriendBook - is a desktop app for managing friend contacts and plan details, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. If you can type fast, FriendBook can get your plans management done faster than traditional GUI apps. It is written in Java, and has about 17kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the functionality for users to edit plan.
  * What it does: allows the user to edit a plan in his FriendBook.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights:
    * Allowing the user to edit the friend associated with the plan is challenging due to the concerns of breaking abstraction and needing to check if the friend exists in the FriendBook.
    * Utilised `getPersonByName` to prevent the breaking of abstraction but also being able to obtain the friend instance.

* **New Feature**: Added the functionality for users to complete a plan.
  * What it does: allows the user to mark a plan's completion status as completed in his FriendBook.
  * Justification: This feature is a key functionality of FriendBook which allows a user to be able to mark their plan as completed, so that user can track the completion status of all the plans.
  * Highlights:
    * Deciding to abstract out the `isCompleted` attribute in a plan proved to be crucial since it played an important role in being able to be updated responsively in the UI.

* **New Feature**: Added the functionality for users to uncomplete a plan.
  * What it does: allows the user to unmark a plan's completion status in his FriendBook.
  * Justification: This feature improves the product significantly because a user can make mistakes in marking the plans and the app should provide a convenient way to rectify them instead of deleting and creating the same plan.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=GohTengFong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=GohTengFong&tabRepo=AY2324S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Set up team skeletal PPP
  * Set up team about us page

* **Enhancements to existing features**:
  * **Testing**: Added test and help files to aid the testing of all above 3 implementations
  * **JavaDoc**: Ensure proper and consistent documentation of functions and variables throughout the entire codebase
  * **Accurate UI Messages**: Ensure that the correct successful outputs are shown in the UI.

* **Documentation**:
  * User Guide:
    * Standardized the UG as a whole by ensuring that all edge cases are covered.
    * Incorporated pictures to improve user experience.
  * Developer Guide:
    * Added the implementation details of the `edit-plan` feature.

* **Community**:
  * Suggested alternative implementations of OOP designs to facilitate discussion within the tutorial group.
