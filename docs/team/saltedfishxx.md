### Project: Tutor Connect

TutorConnect is an **address book made for tuition centre managers** to easily track, schedule, and notify tutors of their upcoming schedule, optimised for users who prefer a CLI.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=saltedfishxx&breakdown=true)

* **Enhancements implemented**:
    * **New feature [#165](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/165):** Added the ability to find schedules by tutor's name.
        * <u>What it does</u>: Allows the user to keyword search schedules by tutor's name, making it easier to locate specific schedules associated with a particular tutor.
        * <u>Justification</u>: This feature was an extended implementation of AB3's `find` command, with the consideration of many users may find it challenging to quickly access schedules for specific tutors. By enabling searches by tutor's name, this feature enhance user convenience and efficiency in managing schedules.
    * **New feature [#200](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/200), [#209](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/209):** Added the flexibility to list schedules filtered by tutor, and by schedule status. 
        * <u>What it does</u>: This enhancement introduces the option to filter schedules based on multiple criteria, such as selecting a specific tutor, displaying schedules marked as `MISSED`, and those marked as `COMPLETED`. Users can now tailor their schedule views to match their specific needs.
        * <u>Justification</u>: User customization and efficiency were the driving factors for implementing this feature. It provides users with greater control over their scheduling information, allowing them to focus on specific tutor-related schedules or easily identify missed or completed appointments. This feature empowers users to manage their schedules more effectively and access the information that matters most to them.
    * **Refactored feature [#116](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/116)**: Refactored `DeleteCommand` to `DeleteScheduleCommand`.
        * <u>What it does</u>: Deletes schedules from TutorConnect.
    * **Updated feature [#103](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/103)**: Added system's ability to save schedule objects to/from json.
        * <u>What it does</u>: Allows system to read schedules from json and serialize it to `Model`'s type which is `Schedule` object, and vice versa for writing.
* **Documentation**:
    * <u>User Guide</u>:
        * **[#227](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/227)**: Added details of feature to find schedule.
        * **[#50](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/50), [#227](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/227)**: Added details of feature to list schedule.
        * **[#49](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/49)**: Added details of feature to delete schedule.
        * **[#297](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/297)**: Added details of feature to exit TutorConnect.
        * **[#176](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/176)**: Updated words to link to glossary term via hyperlinks.
        * **[#300](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/300)**: Updated quick start to include possible error scenarios.
        * **[#298](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/298)**: Updated footnotes for navigability.
    * <u>Developer Guide</u>:
        * Added implementation details of feature to delete schedule.
        * Added planned enhancements for filter schedules by pending status.

* **Contribution to team-based tasks**:
    * Contributed initial idea on evolving AB3 to a [tutor and schedule management system](https://docs.google.com/document/d/1REGMsfhQjjAkhuf3YNzOJHRS0HQOd5Qh_4Buaj-H2uA/edit?usp=sharing) for Tutor Coordinators.
    * Implement schedule structure: Added `JsonAdaptedSchedule` and upgraded `JsonSerializableAddressBook` to support schedule storage.
    * Implement schedule structure: Contributed in creating `Schedule` object from team discussions.

* **Review/ mentoring contributions**:
    * Review PRs: 

* **Contributions beyond the project team**:
    * Clarifying types of test case scenarios that may be deemed as a bug ([#325](https://github.com/nus-cs2103-AY2324S1/forum/issues/325)).
