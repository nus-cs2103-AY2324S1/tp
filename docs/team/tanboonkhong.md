---
layout: page
title: Tan Boon Khong's Project Portfolio Page
---

### Project: CareCentral

CareCentral is an app designed specifically for healthcare professionals, including doctors, nurses, and hospital staff aged between 25-60 years.
It is focused on helping healthcare professionals to manage their patients' information and appointments. While it has a GUI,
it is designed to be used via a Command Line Interface (CLI) for users who prefer typing.

### My Contributions to the Project:

Code contributed: [Reposense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tanboonkhong&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&since=2023-09-22)

- **New Features**:
    - **Prescription Management:** Implemented a feature for adding and deleting prescriptions linked to appointments. (Pull Request [#106](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/106))
      - What it does: Allows users to add and delete prescriptions for each appointment events
      - Justification: This feature improves the product significantly because it allows users to keep track of their patients' prescriptions,
        which is important for healthcare professionals to make informed decisions about their patients' health.
      - Highlights: The implementation was challenging in terms of adding and editing the data, as the data is stored in a list which is attributed to the
        appointment event. The implementation required me to modify the appointment event by adding a new attribute to store the prescription. And modifying
        it using the logic behind edit command logic.

- **Enhancements**:
    - **Attribute Overhaul:** Transformed the `address` attribute to `age`, including new validations and classes, aligning it with healthcare requirements (Pull Request [#108](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/108))
    - **Code Refactoring:** Streamlined the code base to better suit project objectives (Pull Request [#70](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/70))
    - **Storage Model Update:** Adapted the Storage model to handle ClinicBook data (Pull Request [#51](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/51))
    - **NRIC Integration:** Introduced the `Nric` class and its associated modifications (Pull Request [#43](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/43/files))
    - **Person Class Extension:** Added an alternative constructor for the `Person` class, enhancing flexibility (Pull Request [#43](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/43/files))

- **Testing**:
    - Added comprehensive tests for `UniquePatientListTest`, `UserPrefsTest`, `JsonAdaptedPatientTest`, and `JsonUserPrefsStorageTest` to ensure robustness in handling complex scenarios (Pull Request [#71](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/71))
    - Modified tests from original code for our project (Pull Request [#60](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/60))

- **Documentation**:
    - User Guide:
        - Added documentation for the medical history related commands (Pull Request [#128](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/128))
        - Added documentation for the appointment event related commands (Pull Request [#128](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/128))
        - Restructured the user guide to make it more user-centric and easier to understand and adding parameter lists for all commands (Pull Request [#128](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/128))
        - Added command summary to aid users in using the product (Pull Request [#128](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/128))
    - Developer Guide:
        - Added the Planned Enhancement section to the Developer Guide (Pull Request [#198](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/198))
        - Added implementation details of AppointmentEvent (Pull Request [#204](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/204))
        - Added implementation details of Prescription (Pull Request [#204](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/204))
        - Created the UML sequence diagram for the `add-prescription` command (Pull Request [#204](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/204))
        - Created the UML sequence diagram for the `list-appointments` command (Pull Request [#204](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/204))

- **Team Tasks**:
    - Morphing the product into a healthcare management system
    - Renamed the test cases to match our project (Pull Request [#64](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/64/files))
    - Fixed bugs from morphing the project (Pull Request [#67](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/67))
    - Maintaining the issue tracker
    - Maintaining the milestones
    - Reviewed and Merge Pull Requests
