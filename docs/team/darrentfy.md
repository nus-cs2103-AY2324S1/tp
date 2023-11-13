---
layout: page
title: Darren's Project Portfolio Page
---

### Project: WellNUS

WellNUS is a desktop application used by NUS Counsellors to manage and schedule appointments with their student clients.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Summary of Contributions:

* **Enhancements implemented:**

1. Refactored the Person class into Student class [#101](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/101)
    * Existing Person class in AddressBook-Level3(AB3)  does not align with what we wanted with WellNUS, had some
        redundant fields and some missing fields, for example Email class was removed.
    * This was quite a large refactor as removing fields from Person involved changes to all other classes and tests
        in AB3 which had dependencies or associations with the Person class.

2. Added the ability to add/delete a note for a Student [#131](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/131)
    * Counsellors may want to add additional notes for each student to help them monitor progress.
    * This feature allows counsellors to add a note and overwrite existing notes.

3. Added functionality to the 'delete Student' feature [#127](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/127),
    * Initially, when a Student was deleted from WellNUS, the Appointments with the Student's name were not removed,
        which resulted in Appointments containing Students that were no longer in WellNUS.
    * Added functionality such that deleting a Student will delete the related Appointments.

4. Wrote tests for the Schedule Command and its parser [#113](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/113)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=darrentfy&breakdown=true)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `schedule` and `note` [#48](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/48),
          [#151](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/151)
        * Added links to relevant sections in Command Summary
    * Developer Guide:
        * Added implementation details of the `note` feature [#136](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/136)
        * Added Use Cases 3, 4 and 5 [#52](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/52)

* **Contributions to team-based tasks**
    * Managed v1.2.1 and v1.3 releases

* **Review/mentoring contributions**
    * PRs reviewed: [#80](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/80),
        [#83](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/83),
        [#92](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/92),
        [#137](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/137),
        [#149](https://github.com/AY2324S1-CS2103T-W13-4/tp/pull/149)
