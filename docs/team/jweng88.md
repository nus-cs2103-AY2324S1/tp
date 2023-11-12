---
layout: page
title: Joshua Weng Hao’s Project Portfolio Page
---

### Project: Tutor Connect

This is an address book made for tuition centre managers to easily track, schedule, 
and notify tutors of their upcoming schedule, optimised for users who prefer a CLI.

Given below are my contributions to the project.

* **Code contributed**: 
  * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jweng88&breakdown=true)

* **Enhancements implemented**:
  * **New feature:** Added the ability to mark schedules. 
  (Pull request [#144](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/144))
    * What it does: Allows the user to mark a specified schedule as MISSED or COMPLETED.
    * Justification: This feature improves the product by allowing users to create and set the status of a specified
    schedule.
    * Highlights: Implementing this feature posed some challenges as it required the addition of the status field in 
    the schedule object, in order to set the status of a specified schedule, and implementing tests to ensure that the
    valid schedule is marked with a valid status. 
  * **New feature:** Added the ability to view both tutor list and schedule list in one panel. 
  (Pull request [#212](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/212))
    * What it does: Allows the user to view the tutor list and schedule list side by side within the main panel.
    * Justification: This feature improves the product by allowing users to easily refer to the tutor list or 
    schedule list when inputting commands like `edit-t` or `edit-s` that requires referring to the index in the list.
    This greatly enhances the user experience, providing greater convenience for the users.
    * Highlights: Implementing this feature necessitated modifications to the `MainWindow.fxml` and the creation of a 
    new `ListsPanel` to store both `PersonListPanel` and `ScheduleListPanel` so that they can be displayed together in 
    the `MainWindow`.
  * **Refactored feature:** Refactored the `ListCommand` to `ListTutorCommand`. 
  (Pull request [#86](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/86))
    * What it does: Updates the existing list command with the context of the application, tutors.
    * Justification: With the addition of schedules, it is necessary to contextualise the `ListTutorCommand` to 
    only display the list of tutors so that the implementation is separated from the schedule list.
  * **Refactored feature:** Refactored the `DeleteCommand` to `DeleteTutorCommand`.
    (Pull request [#87](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/87))
    * What it does: Updates the existing delete command with the context of the application, tutors.
    * Justification: Same as the `ListTutorCommand` above, with the addition of schedules, it is necessary to 
    contextualise the `DeleteTutorCommand` to only delete from the list of tutors, so that the implementation is
    separated from the schedule list.

* **Documentation**:
    * User Guide
        * Added details of list tutor feature [#47](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/47),
        [#105](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/105), 
        [#161](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/161), 
        [#233](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/233).
        * Added details of delete tutor feature [#48](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/48), 
        [#53](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/53), 
        [#105](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/105),
        [#233](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/233).
        * Added details of mark schedule feature [#166](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/166), 
        [#233](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/233).
        *  Update known issues section [#327](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/327).
    * Developer Guide
        * Added implementation details of list tutor feature 
        [#320](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/320).
        * Added implementation details of delete tutor feature 
        [#142](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/142), 
        [#161](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/161).
        * Added implementation details of mark schedule feature 
        [#320](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/320).
        * Added implementation details for SplitMainWindow to display both tutor and schedule list together 
        [#320](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/320).
        * Added detailed instructions for manual testing for all features 
        [#320](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/320).
        * Update acknowledgements section [#320](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/320).

* **Contribution to team-based tasks**:
    * Implement schedule structure: update AddressBook to include schedules. (Pull request 
    [#98](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/98),
    [#101](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/101))
    * Triage PED bugs.
    * Standardising formatting and proofreading UG and DG.

* **Review/ mentoring contributions**:
    * Reviewed PRs: [#24](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/24), 
    [#29](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/29), 
    [#33](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/33),
    [#34](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/34),
    [#35](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/35),
    [#37](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/37),
    [#99](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/99),
    [#131](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/131),
    [#137](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/137),
    [#138](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/138),
    [#139](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/139),
    [#140](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/140),
    [#210](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/210),
    [#235](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/235),
    [#297](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/297),
    [#304](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/304),
    [#305](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/305),
    [#312](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/312),
    [#328](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/328),
    [#333](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/333),
    [#336](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/336),
    [#339](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/339).

