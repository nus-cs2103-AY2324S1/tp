---
layout: page
title: Pang Yen's Project Portfolio Page
---

## Project: CareCentral

CareCentral is an app designed specifically for healthcare professionals, including doctors, nurses, and hospital staff aged between 25-60 years.
It is focused on helping healthcare professionals to manage their patients' information and appointments. While it has a GUI,
it is designed to be used via a Command Line Interface (CLI) for users who prefer typing.

### My Contributions to the Project:

Code contributed: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=pangyyen&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

- **New Model:**
- Added the `Appointment` model and its associated classes (Pull Request [#80](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/80), [#102](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/102))
  - What it does: Stores the details of a patient's appointment, including the date and the time for the upcoming appointment.
  - Justification: Managing appointment is one of the core features of CareCentral to improve the quality of patient care and administrative efficiency; therefore, this model is the crucial segment of the application to achieve this target.

- **New Features:**
  - Added the ability for user to add, delete, edit and view appointment list for a particular patient (Pull Request [#80](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/80), [#102](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/102/files))
    - What it does: Allows users to add, delete, and view appointment list of patients
    - Justification: This feature improves the product significantly because it allows users to keep track of their patients' appointment list, which is important for healthcare professionals to make informed decisions about their patients' health.
    - Highlights: Implementing the project posed significant challenges, particularly in how data was presented to users. We considered to show the appointment data under every patient, but this might compromise the clarity and the cleanliness of the UI when displaying the patients, and increase the difficulties for implementation and testing. Eventually, despite the challenges, to ensure the quality of the user interface, we decided to integrate multiple tabs into the GUI design with one of the tab being a separate space to display the appointments for chosen patient.

- **Enhancements**:
  - Refactored the package structure of appointment and medical history under logic/parser and model. (Pull Request [#80](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/80))
  - Configured logic package to tailor the package to patient instead of person. (Pull Request [#52](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/52), [#57](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/57), [#61](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/61))

- **Testing**:
  - Added tests for the `Appointment` feature (Pull Requests [#80](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/80/files#diff-5dcbf874cc5f555b7a6d37b2ac9c38f319e55520a89260b2bdb2ce63a0b47607))
  - Added tests for the `Appointment Commands` (Pull Request [#220](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/220))

- **Documentation:**
  - Developer Guide:
    - Craft the initial version of Developer Guide. (Pull Request [#19](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/19))
    - Added in glossary for the terminologies used in the project (Pull Request [#20](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/20))
    - Added implementation details of the `Appointment` model (Pull Request [#103](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/103))
    - Added the design architecture, i.e. UI component, Logic component, Model component, Storage component, Command component, and proposed enhancement features by modifying the class diagrams and their description to fit in the current design. (Pull Request [#109](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/109), [#111](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/111), [#120](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/120))
    - Added the Use cases for all the commands, including the actor, MSS and the extensions for each command (Pull Request [#19](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/19), [#103](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/103), [#120](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/120))
    - Added the Non-Functional Requirements (Pull Request [#19](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/19))
    - Added the saving data instructions for manual testing (Pull Request [#123](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/123/files))
  - User Guide:
    - Crafted the initial version of UserGuide (Pull Request [#18](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/18/files))
    - Added documentation for the appointment-related commands (Pull Request [#80](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/80/files))
