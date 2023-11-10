---
Janssen Lau's Project Portfolio Page
---

### Project: HealthSync

HealthSync is an application designed to streamline the workflow of healthcare center frontdesk staff by allowing them to efficiently register and access patient information within 2-3 minutes. It offers a user-friendly platform that enhances patient management, appointment scheduling, and health record retrieval, ultimately improving care delivery and saving valuable time for healthcare professionals.


Given below are my contributions to the project.

* **New Feature**: Added the ability to undo a Command
    * What it does: allows the user to undo an edit, add, delete and clear command.
    * Justification: This feature allows users to be able to quickly recover from errors made without having to go through a series of other commands to achieve the same goal.
    * Implementation: A new abstract class undo-able command was implemented to do this.
    * Note: The undo for log, alog and clog was implemented by another team member.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kanna-1&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Enhancements to existing features**:
  Added the ability to edit patient fields based on their name and/or ID
    * What it does: allows the user to edit existing patient fields with name and/or ID instead of index. If the fields do not exist, the field will be created automatically.
    * Justification: This feature improves the product significantly because this feature allows the user to elegantly update patient details with unique identification.
    * Highlight: When both a name and ID is provided, the command ensure that both the name and ID refers to the same person.


* **Project management**:
    * Updated the application name ([#80](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/80))
    * Updated the application icon ([#126](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/126))
    * Refactored entire code base to remove all traces of the inherited `Tags` field ([#198](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/198))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `edit`
        * Added documentation for the features `undo`
        * Re-factored the features description and ordering in the UG ([#137](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/137))
        * Added relevant tips to the UG and the corresponding shorcuts for features ([#137](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/137))

    * Developer Guide:
        * Added implementation details of the `edit` feature
        * Added implementation details of the `undo` feature
        * Added non-functional requirements in the DG


* **Community**:
    * PRs reviewed (with non-trivial review comments): [#52](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/52), [#64](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/64), [#78](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/78), [#204](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/204)
    * Reported bugs and suggestions for other teams in the class (Issues examples: [#1](https://github.com/AY2324S1-CS2103T-W17-3/tp/issues/274), [#2](https://github.com/AY2324S1-CS2103T-W17-3/tp/issues/275), [#3](https://github.com/AY2324S1-CS2103T-W17-3/tp/issues/245))





