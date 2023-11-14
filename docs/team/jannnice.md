---
layout: page
title: Jannice's Project Portfolio Page
---

### Project: FriendBook

FriendBook - is a desktop app for managing friend contacts and plan details, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX. If you can type fast, FriendBook can get your plans management done faster than traditional GUI apps. It is written in Java, and has about 17kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the functionality for users to delete a plan.
  * What it does: Allows the user to delete a plan from his FriendBook.
  * Justification: This feature gives the user the ability to delete plans from his FriendBook. Hence, this allows them to have only relevant plans in his FriendBook. 

* **New Feature**: Modified the Parse mechanism to allow the app to differentiate -plan and -friend commands.
  * What it does: Allows the app to correctly identify the command words and execute the corresponding commands.
  * Justification: This modification allows us to minimise the changes made to the pre-existing AB3 command words, while at the same time, ensuring that the correct error messages are thrown.
  * Highlights:
    * Decided to group commands that are not specific (eg. "add", "find") as **unclear commands**, and returning a message for users to specify their commands (instead of "invalid command" message).
    * A command is considered as an **invalid command** if and only if it doesn't match any of the valid commands and the unclear valid commands (eg. "add", "edit").

* **New Feature**: Made `add-plan`, `edit-plan`, and `find-plan` case-insensitive.
  * What it does: Prevents adding and editing of duplicate plans and makes finding plan easier.
  * Justification: This modification allows us to prevent the addition of the same plan but different letter case (eg. "work" and "Work"). By making `find-plan` case-insensitive, users will be able to find their plans more easily by allowing them to not care about the letter cases.
  * Highlights:
    * Converted the plan names to lowercase before checking if they are equal

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=jannnice&tabRepo=AY2324S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Updated the GUI color scheme to match our FriendBook theme
  * Helped and enhanced `complete-plan` feature to allow the completion status of the plan to be reflected accurately in the GUI

* **Project management**:
  * Help to ensure that each member has an issue(s) assigned to them every week

* **Documentation**:
  * User Guide:
    * Added documentation for `complete-plan` and `uncomplete-plan` commands.
    * Added the Glossary section.
    
  * Developer Guide:
    * Added implementation details of the `complete-plan` feature.

* **Community**:
  * Shared with another group within the tutorial group on how to change the GUI color scheme.
  * Shared with a group outside the tutorial group on how to build a sequence diagram for the Developer Guide.
