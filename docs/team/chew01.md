---
  layout: default.md
  title: "Jun Heng's Project Portfolio Page"
---

### Project: FreelanceBuddy

**Overview:** FreelanceBuddy is a **powerful and efficient Command Line Interface (CLI)
optimised app designed for freelancers**.
It's a one-stop solution for freelancers, helping them manage multiple clients, track project statuses,
and stay on top of their financial reports.

Given below are my contributions to the project.

* **New Feature**: Filter by finance type (commission/expense)
  * What it does: Filters all the finances by their type
  * Justification: Full-time freelancers are likely to have many finance entries recorded in the FreelanceBuddy finance tracker, especially if they are committed to logging their personal finances as well.
Therefore, it is important to filter the entries by type so that freelancers can have an easier time locating the entries that they want to find.
  * Highlights: The `list` command allows three different inputs leading to three different outcomes (commission only, expense only, all entries).
It also works on an already filtered list, such as one that is already filtered by client name.

* **New Feature**: Add expense command
  * What it does: Adds an expense with information such as amount, associated client and description.
  * Justification: For a finance tracker to be effective, freelancers must be able to record not just their earnings, but also their expenses, so that they can get a more holistic and consolidated view of their financial situation.
  * Highlights: If the client name is given, expenses can only be added to FreelanceBuddy if the client exists in the Contacts List. 
This serves as not just a quick integrity check but also helps to remind the user that they may have added an erroneous name.

* **New Feature**: Integrity check for add commission/expense commands
  * What it does: When adding finance entries with client input, checks if client exists in the Contacts List and rejects command if it does not.
  * Justification: Freelancers would want to guarantee that they label their transactions with the correct client name so that they will be able to find and filter the entries they want later on.
Having typographical errors or non-existent would hinder their workflows, so it is important to have such a check.
  * Highlights: Not only does FreelanceBuddy do a check against the Contacts List, it also attempts to fetch the object and store a copy of it in the Finance entry if the check passes.
This means that if the contact is somehow deleted from the Contacts List, there will not be an orphaned finance entry.

* **New Feature**: Delete finance command
  * What it does: Deletes a finance entry with the given index number.
  * Justification: Freelancers may add erroneous or duplicate entries to the Finance List, so there must be a functionality that allows them to delete these entries to maintain a valid database that corresponds to their real financial situation.
  * Highlights: The specified index will be checked if it is a valid number within the currently displayed list.
This means that if the list is filtered, the filtered entries are renumbered and FreelanceBuddy will remove the entry that corresponds to the entry in the renumbered list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=chew01&breakdown=true)

* **Project management**:
  * Set up milestone v1.3 issue tracker.
  * Closed milestone v1.4.

* **Enhancements to existing features**:
  * Added tests for every feature implemented above. 
  * Wrote additional tests for existing files such as UniquePersonList (PR [\#113](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/113)).
  * Modified `filter-t` Finance command to take in a range as input instead of a specific date (PR [\#128](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/128)).

* **Documentation**:
  * User Guide:
    * Restructured user guide to be more intuitive and user-friendly (PR [\#236](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/236)). 
    * Updated project site sidebar to be consistent with the user guide (PR [\#245](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/245), [\#258](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/258)).
    * Added Features section to explain the semantics of data entries in FreelanceBuddy (PR [\#236](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/236), [\#239](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/239)).
    * Added helpful links for different audience groups (new, advanced) in Getting Started section (PR [\#236](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/236)).
    * Added more helpful links in Quick Start and Features section so that users can easily get to related sections (PR [\#236](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/236)).
    * Added documentation for the following Finance-related features: `add-e`, `list`, `delete` (PR [\#146](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/146)).
  * Developer Guide:
    * Structured use cases for the project (PR [\#34](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/34)). 
    * Added Finance-related user stories and use cases (PR [\#34](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/34), [\#119](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/119)).
    * Added "Add Finance Command" implementation details, with sequence diagrams (PR [\#270](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/270)).
    * Added manual testing section for Finance commands (PR [\#282](https://github.com/AY2324S1-CS2103T-W09-2/tp/pull/282)).

* **Community**:
  * Reviewed PRs ([Link](https://github.com/AY2324S1-CS2103T-W09-2/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+is%3Aclosed))
  * Assisted group members with Git workflows and helped to troubleshoot merge conflict resolution.

* **Tools**:
  * Set up GitHub team organisation and repository.
  * Set up GitHub workflows and CI.
  * Set up deploy as a MarkBind site.
  * Set up Codecov.
  * Added Codecov and CI status indicators to repository.
