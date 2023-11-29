---
layout: page
title: Ruth Lim Sze Ern's Project Portfolio Page
---

{: .no_toc}
### Project: ToothTracker

ToothTracker is an all-in-one application designed for front desk administration staff to efficiently manage clinic operations,
including patient, dentist and appointment management.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete dentist records.
(PR: [#71](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/71))
  * What it does: Allows the user to delete dentist records from ToothTracker using the `DENTIST_ID`.
  * Justification: This feature is vital for ToothTracker users to manage their dentist database effectively by allowing dentist records to be removed easily.

* **New Feature**: Added the `view-calendar` feature and `CalendarWindow`
  * What it does: Allows the user to view all appointments in a Calendar.
  * Justification: User can visualise all appointments rather than scrolling through a long list.
  * Credits: `CalendarWindow` makes use of the [CalendarFX](https://github.com/dlsc-software-consulting-gmbh/CalendarFX) library, an open source calendar framework for JavaFX 8.

* **New Feature**: Added the Quick Notes Box
  * What it does: Provides a dedicated space for users to jot down quick reminders or notes directly within the ToothTracker application.
The Quick Notes Box is accessible at all times at the bottom of the main window.
  * Justification: This feature enhances user productivity by allowing the seamless tracking of to-dos and reminders
without the need for a separate application or physical note-taking. It's especially useful during patient or dentist
record management when the user needs to make a quick note of follow-ups or important information.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ruth-lim&breakdown=true)

* **Contributions to team-based tasks**:
  * Set up GitHub team organization and project repo
  * Set up CodeCov for continuous integration

{% include page-break.html %}

* **Enhancements to existing features**:
  * Change the theme of the GUI: Created mockups of UI in Figma and updated the styling from AB3 to the mockup designed.
([#67](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/67) and [#76](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/76))
  * Added unit testing for JsonAdaptedPatient, AddPatientCommand, ListPatientCommand and Patient, increasing code coverage by 8.26%: ([#102](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/102))
  * Refactored `Service` to `TreatmentName`: ([#113](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/113))
  * Assisted teammate @techjay-c to update `VALIDATION_REGEX` of Phone to be strictly 8 digits: ([#135](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/135)) 
  * Added unit testing for ViewCalendarCommand, ListTreatmentCommand, DeleteTreatmentCommand and AddressBookParser, increasing code coverage by 5.27%: ([#203](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/203))
  * Added unit testing for JsonSerializableAddressBook and UniquePatientList, increasing code coverage by 1.18%: ([#217](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/217))

* **Documentation**:
  * User Guide:
    * Modified site-wide settings: ([#26](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/26), [#50](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/50))
    * Renovated website: ([#96](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/96), [#153](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/153),)
    * Added documentation for Calendar feature
    * Added "How to use ToothTracker's User Guide" section
    * Added "Icons used in this guide" section
    * Added "Quick Reference Guide" section
    * Added auto numbering to Headings and Table of Content
      * Credits: [Pure CSS auto-incrementing heading counters](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/docs/_sass/toc.scss) adapted from [@gvgramazio on StackOverflow](https://stackoverflow.com/a/51007932/9311854)
    * Updated Command Summary Table ([#115](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/115))
    * Added screenshots for all examples: ([#158](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/158))
  * Developer Guide:
    * Added implementation details for `delete-dentist` ([#126](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/126))
    * Update Sequence Diagram for `Logic` component
    * Update Class Diagram for `Ui` component
    * Added instructions for Manual Testing for appointment, treatment and calendar-related features ([#220](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/220))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#114](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/114),
[#119](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/119), [#145](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/145)
  * Reported bugs and suggestions for other teams in the class ([Link to repo](https://github.com/ruth-lim/ped))

* **Tools**:
  * Integrated a third party library (CalendarFX) to the project ([#125](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/125))

