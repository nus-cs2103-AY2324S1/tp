---
layout: default.md
title: Low Jun Hong's Project Portfolio Page
---

### Project: UniMate

UniMate is a desktop address book and calendar infused in one application intended for National University of Singapore
(NUS) students to save group mate's contacts and sync calendars to schedule classes and group project meetings. The user
interacts with it using primarily the Command Line Interface (CLI), but can choose to interact with the GUI using a
mouse. Its GUI created with JavaFX, and the entire project is written in Java, and has about 25 kLoC.

Given below are my contributions to the project.

* **Add Contact Event Command**
  * What it does: Facilitates the overall functionality of the calendar by adding events.
  * Justification: Facilitates the overall functionality of comparing calendars between user and contact.
  * Highlights: Maintains syntactic consistency with the "Add Event" command, ensuring that users can confidently engage with the application in a seamless and intuitive manner.
  * Credits: The command uses some methods and modified code implemented by lihongguang00 for the Calendar class.

* **Delete Contact Event Command**
  * What it does: Adds an event to the personal calendar of a contact.
  * Justification: Facilitates the overall functionality of the calendar by deleting events.
  * Highlights: Maintains syntactic consistency with the "Delete Event" command, ensuring that users can confidently engage with the application in a seamless and intuitive manner.
  * Credits: The command uses some methods and modified code implemented by fallman2 and lihongguang00.

* **Storage classes**
  * What it does: Manages the storage for the TaskList, Calendar and contact's Calendars.
  * Justification: Facilitates the overall functionality of the application by maintaining proper storage of data.
  * Highlights: This feature integrates with the current storage class, ensuring compatibility without disrupting the existing implementation or future iterations. The design prioritizes a non-intrusive collaboration, allowing the new functionality to coexist harmoniously with the established storage infrastructure.
  * Credits: Adapted code from AB3 addressbook's storage.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=junhonglow&tabRepo=AY2324S1-CS2103-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added UG documentation for the features `addContactEvent`, `deleteContactEvent` and all `Storage` functionality.
  * Developer Guide:
    * Added use cases 8 - 11.
    * Updated PUML diagram for the storage class.
    * Added necessary test instructions and test cases needed for storage testing.
      
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#36](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/36),
      [\#40](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/40), [\#53](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/53),
      [\#63](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/63), [\#64](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/64),
      [\#70](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/70), [\#74](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/74),
      [\#76](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/76), [\#78](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/78).
  * Reported bugs and suggestions for other teams in the class (examples: 
      [\#160](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/160), [\#172](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/172),
      [\#175](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/175), [\#178](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/178),
      [\#185](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/185), [\#186](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/186),
      [\#187](https://github.com/AY2324S1-CS2103T-T11-1/tp/issues/187).
