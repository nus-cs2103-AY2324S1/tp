---
layout: andrewjanong.md
title: "Andrew Daniel Janong's Project Portfolio Page"
---

### Project: KeepInTouch

KeepInTouch is a desktop app for managing contacts for people in the working industry (recruiters, engineers, etc.) as well as events for career purposes.

Below are my contributions to the team:

* **Code contributed**: [Reposense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=totalCommits&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByNone&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=AndrewJanong&tabRepo=AY2324S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * Added `tag` inputs for `list contact` ([\#76](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/76))
    * What it does: It allows users to find contacts containing certain tags.
    * Justification: This feature improves the product efficiency as the user may now search through contacts more effectively.
  * Fixed bug for questionable date and time string paring for `Event`. ([\#153](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/153))
    * Justification: Added more control-flow statements to make sure parsing works for certain string inputs and also returns the correct success/fail message.
  * Fixed bug for `Event` commands to allow end time to be empty ([\#83](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/83))
    * Justification: Made sure the code does not accept empty start time and sets end time to start time whenever the value is not given.
  * Increased coverage by adding tests. ([\#194](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/194))
    * Justification: Improved test cases for `Event`

* **Fixed bugs reported in PE-D**:
  * Fixed behavior when comparing two `Person` objects ([\#151](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/151))
  * Fixed date parsing bug ([\#153](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/153))
  * Fixed UG bugs ([\#161](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/161))

* **Documentation**:
  * User Guide:
    * Adapt the UG for all must-have features and create Table of Content ([\#13](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/13))
    * Add screenshots and expected success and unsuccessful outputs ([\#168](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/168))
  * Developer Guide:
    * Added Implementation for List Contact feature ([\#184](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/184))
    * Added Implementation for Event features ([\#198](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/198))
    * Added Instructions for Manual Testing for Event features ([\#197](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/197))

* **Review/Mentoring contributions**:
  * Reviewed a total of 14 PRs.
  * Answering queries from team members in the Telegram group chat.

* **Contributions beyond the project team**:
  * Reported 8 bugs for the other team during the PED.
