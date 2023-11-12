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

<div style="page-break-after: always;"></div>

* **Documentation**:
    * <u>User Guide</u>:
        * **[#227](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/227)**: Added details of feature to find schedule.
        * **[#50](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/50), [#227](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/227)**: Added details of feature to list schedule.
        * **[#49](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/49)**: Added details of feature to delete schedule.
        * **[#297](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/297)**: Added details of feature to exit TutorConnect.
        * **[#176](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/176)**: Updated words to link to glossary term via hyperlinks.
        * **[#300](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/300)**: Updated quick start to include possible error scenarios.
        * **[#298](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/298)**: Updated footnotes for navigability.
        * **[#331](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/331)**: Added hyperlinks to command summary.
    * <u>Developer Guide</u>:
        * **[#318](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/318)**: Added implementation details of feature to list schedules.
        * **[#318](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/318)**: Added implementation details of feature to find schedules.
        * **[#162](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/162)**: Added implementation details of feature to delete schedule.
        * **[#315](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/315)**: Added planned enhancements for filter schedules by pending status.

* **Contribution to team-based tasks**:
    * Contributed initial idea on evolving AB3 to a [tutor and schedule management system](https://docs.google.com/document/d/1REGMsfhQjjAkhuf3YNzOJHRS0HQOd5Qh_4Buaj-H2uA/edit?usp=sharing) for Tutor Coordinators.
    * Implement schedule structure: Added `JsonAdaptedSchedule` and upgraded `JsonSerializableAddressBook` to support schedule storage.
    * Implement schedule structure: Contributed in creating `Schedule` object from team discussions.
    * Contributed to proofreading and formatting for UG and DG (refer to review PR section).

* **Review/ mentoring contributions**:
    * Review PRs: [#346](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/346),
      [#339](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/339),
      [#332](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/332),
      [#328](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/328),
      [#323](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/323),
      [#320](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/320),
      [#312](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/312),
      [#310](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/310),
      [#303](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/303),
      [#302](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/302),
      [#291](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/291),
      [#211](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/211),
      [#180](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/180),
      [#177](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/177),
      [#166](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/166),
      [#114](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/114),
      [#72](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/72),
      [#71](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/71),
      [#51](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/51),
      [#35](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/35)

* **Contributions beyond the project team**:
    * Clarifying types of test case scenarios that may be deemed as a bug ([#325](https://github.com/nus-cs2103-AY2324S1/forum/issues/325)).
