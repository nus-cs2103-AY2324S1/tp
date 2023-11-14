---
  layout: default.md
  title: "Kailash's Project Portfolio Page"
---

### Project: ProjectPRO

ProjectPRO is a desktop application designed to help university students organize their projects. ProjectPRO is optimized for use via a Command Line Interface (CLI) while preserving the advantages of maintaining an attractive user interface. Utilizing simple and easy-to-remember commands to execute different tasks, it helps users streamline their project management.


Given below are my contributions to the project.

* **New Feature**: Added the ability to allow user add free time interval into their contacts.
  * What it does: allows the user to add non-clashing free time interval into their contacts.
  * Justification: This feature allows the user to see when their contacts or group mates are free. A lot of consideration was gone through in deciding whether we should allow user to add overlapping time intervals or not overlapping time interval. In the end it was decided to be non-clashing free time interval to reduce the complexity needed to support the `findfreetime` feature.
  * Highlights: This feature needed to ensure that the time interval added to a contact by the user does not clash with other time interval that contact has. An algorithm was made to detect any clashes in the time interval. In addition to that, this feature allows multiple non-clashing time intervals to be added in a single input. Due to that, a lot of challenges were faced in the parsing logic as making it convenient for the user in terms of input will open up room for more invalid errors. However, these errors were managed.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&chartGroupIndex=36&chartIndex=2%23%2F%23%2F)

* **Project management**:
  * Managed releases `v1.3.0` - `v1.4.1` (6 releases) on GitHub

* **Enhancements to existing features**:
  **Improving `Add` feature**: Added the ability to allow the user to add an optional Group name along with other attributes.
  * What it does: Allows the user to add contact into the contact list and add that contact to the group at the same time.
  * Justification: This feature makes it convenient for the user to add their new contacts into the group at the same time with just one command.
  * Highlights: This feature needed to handle input when there is no group name type and when there is group name type. It also needed to ensure that when the group name is typed, the contact needed to be added inside the `uniquePersonList` and the group inside the `addressbook#grouplist` if the group does not exist. It also needed to ensure that in the group was added into contact and the contact was added into the group because group and contact are dependent with one another.
*  **Updated the GUI to fit ProjectPRO requirements** (Pull requests [\#70](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/70), [\#100](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/100), [\#136](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/136))
   * Justification: This update allows the user to see a weekly schedule and track when their next group meeting is.
   * Highlights: The challenges faced was converting the data to GUI friendly and ensuring the GUI gets updated when new group meeting time was added to a group.


* **Documentation**:
  * User Guide:
    * Added documentation for the features `list`, `new`, `delete`, `find`. [\#145](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/145)
    * Made some updates to the documentation for the final release. [\#290](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/290), [\#277](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/277), [\#271](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/271)
  * Developer Guide:
    * Added implementation details of the `add` and `addtime`feature. [\#271](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/271/files).
    * Made diagrams for `add` and `addtime` feature.
    * Updated old architecture models to projectPROs implementation for `model` and `UI` [\#263](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/263/files).
    * Added and updated the planned enhancements section. [\#248](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/248/files).

* **Community**:
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S1-CS2103T-T10-4/tp/issues/232), [2](https://github.com/AY2324S1-CS2103T-T10-4/tp/issues/231), [3](https://github.com/AY2324S1-CS2103T-T10-4/tp/issues/234)).

