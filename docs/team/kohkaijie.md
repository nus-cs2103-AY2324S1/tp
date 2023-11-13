---
layout: page
title: Kai Jie's Project Portfolio Page
---

### Project: MediLink Contacts

MediLink Contacts aims to help medical staff including nurses/doctors/pharmacists navigate through patient details in
their high workload and time-pressured working environment. When medical emergencies arise, it becomes crucial to
provide rapid access to emergency contacts for patients and access other details of the patients to make decisions more
quickly. It is optimised for CLI so that users can quickly access the information. There is also a GUI created with
JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands (done with Tanveer).
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Allowed only specific tags to be added for patients and doctors respectively.
  * What it does: restricts the user to only enter specific tags for patients and doctors. Patients can only have up to one tag specifying their priority level. Doctors can have up to multiple tags specifying their specialisations.
  * Justification: This feature ensures that tags have a clear purpose and users cannot enter tags that do not add value to the patient or doctor (e.g adding Engineer tag for a doctor).
  * Highlights: While this enhancement itself did not affect much other commands, the implementation itself was challenging as it required choosing between different implementation methods, and choosing the most future-proof and OOP-friendly one.

* **Code contributed**: [Link to TP code dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kohkaijie&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Project management**:
  * Managed releases `v1.1` - `v1.3` (3 releases) on GitHub
  * Added issues on GitHub for various tasks.

* **Enhancements to existing features**:
  * Created classes for new fields for Patient and Doctor classes.
  * Wrote additional tests for existing features such as Patient fields, UndoCommand and Tags.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and usage of special tags.
  * Developer Guide:
    * Added implementation details of the `delete` feature.
    * Modified UML diagram for DeleteSequenceDiagram.
    * Added Use Case for UndoCommand and extensions for EditCommand.

* **Team-based tasks**:
  * Reviewed PRs and approved when needed for merging.


