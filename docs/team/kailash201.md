---
  layout: default.md
  title: "Kailash's Project Portfolio Page"
---

### Project: ProjectPRO

ProjectPRO is a desktop application designed to help university students organize their projects. ProjectPRO is optimized for use via a Command Line Interface (CLI) while preserving the advantages of maintaining an attractive user interface. Utilizing simple and easy-to-remember commands to execute different tasks, it helps users streamline their project management.


Given below are my contributions to the project.

* **New Feature**: Added the ability to allow user add free time interval into their contacts.
  * What it does: allows the user to add non-clashing free time interval into their contacts.
  * Justification: This feature allows the user to see when their contacts or group mates are free. In addition to that, this feature allows `findfreetime` feature to work.
  * Highlights: This feature needed to ensure that the time interval added to a contact by the user does not clash with other time interval that contact has. This feature allows multiple time intervals to be added in a single input. Due to that, a lot of challenges were faced in the parsing logic as making it convenient for the user in terms of input will open up room for more invalid errors.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&chartGroupIndex=36&chartIndex=2%23%2F%23%2F)

* **Project management**:
  * Managed releases `v1.3` - `v1.3.3` (4 releases) on GitHub

* **Enhancements to existing features**:
  **Improving `Add` feature**: Added the ability to allow the user to add a optional Group name along with other attributes.
  * What it does: Allows the user to add contact into the contact list and add that contact to the group at the same time.
  * Justification: This feature makes it convenient for the user to their new contacts into the group at the same time with just one command.
  * Highlights: This feature needed to handle input when there is no group name type and when there is group name type. It also needed to ensure that when the group name is typed, the contact needed to be added inside the `uniquePersonList` and into `projectPRO_grouplist` if the group does not exist. It also needed to ensure that in the group was added into contact and the contact was added into the group because group and contact are dependent with one another.
*  **Updated the GUI to fit ProjectPRO requirements** (Pull requests [\#70](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/70), [\#100](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/100), [\#136](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/136), [\#136](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/137))
   * Justification: This update allows the user to see a weekly schedule and track when their next group meeting is.
   * Highlights: The challenges faced was converting the data to GUI friendly and ensuring the GUI gets updated when new group meeting time was added to a group



* **Documentation**:
  * User Guide:
    * Added documentation for the features `list`, `new`, `delete`, `find`. [\#145](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/146)
  * Developer Guide:
    * Added implementation details of the `add time` feature.
    * Added planned enhancements section.

* **Community**:


* **Tools**:


* _{you can add/remove categories in the list above}_
