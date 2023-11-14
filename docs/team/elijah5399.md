---
layout: page
title: Elijah Chia's Project Portfolio Page
---

### Project: FriendBook

FriendBook - is a desktop app for managing friend contacts and plan details, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. If you can type fast, FriendBook can get your plans management done faster than traditional GUI apps. It is written in Java, and has about 17kLoC.

Given below are my contributions to the project.

* **New Feature**: Implemented the Plan, PlanDateTime, PlanName classes.
  * What it does: Supports the implementation of the all plan-related commands.
  * Justification: The plan-related classes is FriendBook's unique selling point, which makes it a viable application for everyone who has things to do.
  * Highlights:
    * The `isSamePlan` method supports `edit-plan` and `add-plan` by ensuring that duplicate plans are not entered into FriendBook.
    * The `PlanDateTime` class contains extensive checking to ensure that only valid date-times are entered into FriendBook be the user.

* **New Feature**: Added JavaFX code for the Plans panel.
  * What it does: Provides the user with a GUI to see their plans.
  * Justification: The Plans panel allows the user to see their plans without using the terminal.
  * Highlights:
    * Above the list of plans, a header "Upcoming Plans" was included to make the application more self-explanatory.
    * PlanListPanel allows users to scroll through their plans if they have too many.
    * PlanListPanel responds to `list-plan`, `delete-plan`, `add-plan` and `edit-plan` commands.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=Elijah5399&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Elijah5399&tabRepo=AY2324S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Suggested features for v1.2 and v1.3
  * Suggested object-oriented designs for new features

* **Enhancements to existing features**:
  * Added initial welcome message to the GUI, prompting users to use the 'help' button on launching the app.
  * Added extensive testing for plan-related classes, such as `PlanDateTime` and `PlanName`.
  * Modified the `isSamePlan` function to check for all fields, instead of just `PlanName`.

* **Documentation**:
  * User Guide:
    * Added sidebar navigation for User Guide.
    * Added page breaks to print PDF version of User Guide.
  * Developer Guide:
    * Added template documentation for `add-plan`, `edit-plan` and `delete-plan`.
    * Completed documentation for `add-plan`.
    * Added sequence diagrams for `AddPlanCommand`, `EditPlanCommand` and `DeletePlanCommand`, and activity diagram for `AddPlanCommand`.
    * Added page breaks to print PDF version of Developer Guide.

* **Community**:
  * Suggested possible alternative to answers during tutorial sessions.
  * Found multiple high-severity bugs in peer projects during PE dry run.

* **Tools**:
  * CSS and JavaFX for design of FriendBook GUI
