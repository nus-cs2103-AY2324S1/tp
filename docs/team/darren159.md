---
  layout: default.md
  title: "Darren's Project Portfolio Page"
---

### Project: MedBook

#### Overview

MedBook is a desktop application crafted specifically for doctors and medical administrative assistants of private clinics. It offers an intuitive and efficient interface for seamless management of patient details and medical records, enabling healthcare professionals to easily monitor and access patient information.

#### Summary of Contributions

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=darren159&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

- **Enhancements Implemented**:

  - **Pin Feature**:

    - Developed a new functionality within MedBook, enabling users to prioritize certain patients by pinning them to the specially designed **Pinned Patient List**. The enhancement comes alongside two commands, `pin` and `unpin` for users to control the pinning of patients.
    - The enhancement required the creation of a separate UI component to display the pinned patients.
    - An `isPinned` field was added to each patient to control whether it would appear in the **Pinned Patient List**.
    - Two commands, `pin` and `unpin` were created to toggle the `isPinned` status of the specific patient

  - **Appointments Feature**:
    - Developed a new functionality within MedBook, enabling users to track and manage patient appointments. This feature includes a dedicated window displaying an **Appointment List** and an integrated calendar, which displays scheduled appointments for the respective dates. The enhancement comes alongside three commands `addappointment`, `deleteappointment` and `viewappointment`.
    - The enhancement required the creation of a seperate UI component to display the appointments. A new window was created to allow for more space to display the appointments and a calendar was created manually using a JavaFX `GridPane`.
    - A new object class `Appointment` was created to contain information about the appointment. A new class `UniqueAppointmentList`, was created alongside to hold the `Appointment` objects. The `UniqueAppointmenList` was then added to each patient to associate each `Appointment` with a patient.
    - The commands, `addappointment` and `deleteappointment` were created to allow the addition and deletion of `Appointment` objects. The `viewappointment` command was created to allow users to open up the **Appointment Window** and view the appointments.
    - A custom method was also created to populate the calendar with appointments.

- **Contributions to the UG**:

  - Added new sections explaining the usage of the `addappointment`, `deleteappointment`, `viewappointment`, `pin` and `unpin` commands which I have implemented.
  - Added **Tips** and **Notes** sections to make the UG more reader-friendly.
  - Enhanced the tone of the UG with a more user-centric tone e.g. you-language.

- **Contributions to the DG**:

  - Added new sections explaining the implementation of the **Appointments Feature** and **Pin Feature** as well as the commands related to it.
  - Created Sequence and Class Diagrams to aid in the explanation of the above implementations.
  - Added Use Cases as well as Instructions for Manual Testing for the above features.

- **Contributions to team-based tasks**:

  1. Setting up the GitHub team org/repo
  2. Setting up of CodeCov
  3. Removing traces of AddressBook Level-3 and renaming to MedBook
  4. Maintaining the issue tracker
  5. Release management
  6. Updating index.md and README.md

- **Review/mentoring contributions**:

  - **PRs reviewed**:
    [\#20](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/20), [\#21](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/21),
    [\#32](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/32), [\#35](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/35),
    [\#36](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/36), [\#37](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/37),
    [\#40](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/40), [\#42](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/42),
    [\#68](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/68), [\#71](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/71),
    [\#79](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/79), [\#80](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/80), [\#81](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/81),
    [\#86](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/86), [\#150](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/150), [\#152](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/152), [\#159](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/159), [\#163](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/163), [\#165](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/165), [\#167](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/167), [\#172](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/172)

- **Contributions beyond the project team**:
  - Reported 16 bugs for the product, HealthSync, during PE-D.
