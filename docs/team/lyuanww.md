---
layout: page
title: Wong Li Yuan's Project Portfolio Page
---

## Project: CareCentral

CareCentral is an app designed specifically for healthcare professionals, including doctors, nurses, and hospital staff aged between 25-60 years.
It is focused on helping healthcare professionals to manage their patients' information and appointments. While it has a GUI,
it is designed to be used via a Command Line Interface (CLI) for users who prefer typing.

### My Contributions to the Project:

- **New Model:**
  - Added the `Patient Medical History` model and its associated classes (Pull Request [#77](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/77))
    - What it does: Stores the details of a patient's medical history, including the date, the medical condition and the treatment the patient received.
    - Justification: This model is important because it is one of the core features of CareCentral.

- **New Features:**
  - Added the ability to add, delete, edit and view medical histories (Pull Request [#77](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/77))
    - What it does: Allows users to add, delete, and view medical history of patients
    - Justification: This feature improves the product significantly because it allows users to keep track of their patients' medical history,
      which is important for healthcare professionals to make informed decisions about their patients' health.
    - Highlights: The implementation was challenging in terms of the presentation of data to the user, as we had to consider
      various ways of presenting the data in a way that is easy to understand and use. To fit the design of several tabs in the GUI,
      I had to modify the existing codebase so that it allows several displayed lists.
- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lyuanww&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

- **Enhancements**:
  - Refactored the Date and Time to Util Classes to reduce code duplication. (Pull Request [#107](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/107))
  - Rewrote the `add-prescription` command from overwriting the existing prescription to appending to the existing prescription. (Pull Request [#189](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/189))

- **Testing**:
  - Added tests for the `Patient Medical History` feature (Pull Requests [#77](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/77), [#104](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/104))

- **Documentation:**
  - Developer Guide:
    - Added implementation details of the `Patient Medical History` model (Pull Request [#112](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/112/files))
    - Added implementation details of the `Patient` model (Pull Request [#122](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/122))
  - User Guide:
    - Added documentation for the patient-related commands (Pull Request [#94](https://github.com/AY2324S1-CS2103T-F08-1/tp/pull/94))
