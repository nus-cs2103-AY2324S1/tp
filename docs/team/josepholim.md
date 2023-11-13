---
  layout: josepholim.md
  title: "Joseph Oliver Lim's Project Portfolio Page"
---

### Project: KeepInTouch

KeepInTouch is a desktop app for managing contacts for people in the working industry (recruiters, engineers, etc.) as well as events for career purposes.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=josepholim&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=josepholim&tabRepo=AY2324S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
    * Added `add note` commands and tests. ([\#39](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/39), [\#42](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/42))
        * What it does: Supports adding a note to a contact.
        * Justification: This feature allows the user to record additional information about each contact.
    * Added `delete note` commands and tests. ([\#39](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/39), [\#42](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/42))
        * What it does: Supports deleting a note from a contact.
        * Justification: This feature allows the user to remove additional information that are no longer relevant.
    * Added support for duplicate contact names. ([\#72](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/72))
        * Previously in AB3, two contacts are considered duplicates if they have the same name. I modified the definition of duplicate contacts to those with the same names and data fields.
        * Justification: This feature allows the user to have multiple contacts with the same name.
    * Fixed bug for empty fields in `add note` and `delete note` commands. ([\#60](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/60))
        * Justification: Added regex validation to ensure that the fields for the commands cannot be an empty string or a string with only whitespaces.
    * Increased coverage by adding tests. ([\#42](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/42), [\#183](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/183))
        * Justification: Coverage increased by 1% and 2% in each PR.

* **Documentation**:
    * User Guide:
        * Adapt the UG for add note and delete note by id ([\#39](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/39))
        * Modify the UG for list events feature ([\#158](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/158))
        * Fix Table of Content ([\#156](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/156))
        * Fix typos and formatting ([\#177](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/177))
    * Developer Guide:
        * Added Table of Content ([\#192](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/192))
        * Added Implementation for Notes Feature ([\#182](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/182))
        * Added User Stories ([\#52](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/52), [\#179](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/179))
        * Added Use Cases ([\#25](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/25), [\#52](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/52), [\#179](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/179))
        * Added Non-Functional Requirements ([\#12](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/12), [\#180](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/180))
        * Added Instructions for Manual Testing for Notes feature ([\#193](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/193))

* **Review/Mentoring contributions**:
    * Reviewed a total of 22 PRs.
    * Answering queries from team members in the Telegram group chat.

* **Contributions beyond the project team**:
    * Reported 21 bugs for the other team during the PED.
