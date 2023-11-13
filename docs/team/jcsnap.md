---
layout: page
title: Justin's Project Portfolio Page
---

## Project Name: TaskWise

TaskWise is a project management application used by CS2103/T project managers.

The user interacts with it via the Command Line Interface (CLI) and it has a Graphical User
Interface (GUI) made using JavaFX. It is written in Java, and it has about 14107 LoC.

Given below are my contributions to the project.

### New Features and Components

* New Feature: Note
    * What it does: Add a note to a task.
    * Justifications: There might be additional information that the user wants to add to a task, but is too long to be included in the description.
    * Highlights: It can be edited in the edit command as well.
    * Credits: The validation REGEX to check for validity of `note`'s input by the user was reused from ChatGPT's
      generated code.
* New Feature: View
    * What it does: Displays the complete information of the task in the side panel.
    * Justifications: It is useful for the user to see the complete information of the task. It is a CLI implementation of "clicking" the task.
    * Highlights: Using esc will clear the side panel with the selected task.
    * Challenge: Since this requires interaction with the UI elements, it is difficult to figure out how to let the UI know which task card is selected.

### Enhancement To Existing Features

* Refactored the underlying code in the AddressBook to fit the use case of TaskWise. This is a bit challenging as the
      code is very coupled and it is difficult to change the code without breaking the code.
* Edited some existing test cases and Builders to fit the use case of TaskWise.


* Code contributed: [Link to Reposense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=JCSnap&tabRepo=AY2324S1-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Project Management

* Project management: Reviewed teammates' pull requests, gave feedback and suggestions for improvements and merged pull
  requests. Added and tracked issues.
* Enhancement to existing features:
    * Refactored the underlying code in the AddressBook to fit the use case of TaskWise. This is a bit challenging as the
      code is very coupled and it is difficult to change the code without breaking the code.
    * Edited some existing test cases and Builders to fit the use case of TaskWise.
    * Added note as an optional field in the `edit` command.
* Documentation:
    * User Guide
        * Added documentation on the `delete` function (1.2), user stories and use cases.
        * Added documentation on the `note` feature, user stories and use cases.
        * Added documentation on the `view` feature, user stories and use cases.
        * Added `Installation Guide`.
        * Added the formatting guides in `Usage of User Guide`.
        * Added proposed enhancement for `find` feature.
    * Developer Guide:
        * Added documentation on the `delete` function (1.2), user stories and use cases.
        * Added documentation on the `note` feature, user stories and use cases.
        * Added documentation on the `view` feature, user stories and use cases.
        * Added the `Manual Testing` section.
        * Added proposed enhancement for `find` feature.

### Contributions To Team-based Tasks
* Helped create issues and manage issues.
* Contributed to demo video.
* Checking links and grammar mistakes in User Guide and Developer Guide.
* Helped create issues and manage issues.
* Contributed to demo video.

### Contributions To Community
* Review/mentoring contributions:
    * Guided teammates on using Git workflow and GitHub.
    * Reviewed teammates' pull requests, gave feedback and suggestions for improvements and merged pull requests.
* Contribution to community:
    * Reviewed with non trivial comments: [#61](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/61), [#213](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/213), [#84](https://github.com/AY2324S1-CS2103T-T17-1/tp/pull/84).
