---
layout: default.md
title: "Hanjoo's Project Portfolio Page"
---

### Project: MedBook

**Overview**: MedBook is a desktop application crafted specifically for doctors and medical administrative assistants of private clinics. It offers an intuitive and efficient interface for seamless management of patient details and medical records, enabling healthcare professionals to easily monitor and access patient information.

**Contributions**: Click [here](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=hjoneweek&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos) to view my contribution to the project in RepoSense.

- **Enhancement 1**: Person Class and Add Patient Feature [#31](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/31)
  - Added more attributes appropriate to hospital/clinic settings to the `Person` class. Updated attributes include `BloodType`, `Age`, `Gender`, and `Allergy`.
  - The enhancement includes more essential information about `Person` for doctors and medical staffs to have a deeper understanding about their patients.

- **New Feature 1**: Record Class and Add Record Feature [#36](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/36) [#68](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/68)
  - An essential feature that allows doctors to add medical records to patients after their visit to the clinic. A `Record` includes information about the date and time of the visit, the conditions of the patient, and the prescribed medications for the patient.
  - Created the `Record` class and `UniqueRecordList` class to ensure uniqueness of each `Record`.
  - Created the `DateTime`, `Condition`, and `Medication` classes, which are attributes of the `Record` class.
  - Created the `AddRecordCommandParser` class and the `AddRecordCommand` class to execute the `addrecord` command.

- **New Feature 2**: Search Record Feature [#86](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/86)
  - Another essential feature that allows doctors to use keywords to search for the past records of the patient that they are currently viewing.
  - Created the `RecordContainsKeywordPredicate` class, `FindRecordCommandParser` class, and the `FindRecordCommand` class to execute the `searchrecord` command.

- **New Feature 3** Delete Record Feature [#88](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/88)
  - Allows doctors to delete records of patients if necessary.
  - Created the `DeleteRecordCommandParser` class and the `DeleteRecordCommand` class to execute the `deleterecord` command.

- **Documentation**
  - User Guide
    - Added new sections explaining how to use the newly implemented features and commands, which are `addrecord`, `searchrecord`, and `deleterecord` command.
    - Modified `addpatient` command (which was originally `add` command) section according to the updated attributes of the patient.
    - Reviewed the User Guide altogether as a team.
    
  - Developer Guide
    - Added the **Record Feature** section (except for the implementation details of the `editrecord` command).
    - Created the sequence diagrams of the executions of the `addrecord`, `searchrecord`, `deleterecord`, and `addpatient` commands.
    - Created the class diagrams of the `Record`, and `Person` classes.
    - Updated the class diagrams for `UI`, `Storage`, and `Model` components of MedBook.
    - Reviewed the Developer Guide altogether as a team.

- **Community**
  - PRs reviewed: [#24](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/24), [#92](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/92), 
                  [#96](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/96), [#148](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/148),
                  [#155](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/155), [#156](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/156), 
                  [#158](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/158), [#164](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/164), 
                  [#166](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/166), [#169](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/169), 
                  [#171](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/171), [#174](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/174),  
                  [#177](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/177), [#178](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/178),
                  [#179](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/179), [#181](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/181)
  - Reported bugs and suggestions for other teams in the class by participating in the PE-D. Click [here](https://github.com/hjoneweek/ped/issues) to view the issues I posted for the project I reviewed.
