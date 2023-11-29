---
layout: page
title: Freddy Chen You Ren's Project Portfolio Page
---

{: .no_toc}
### Project: ToothTracker

{: .no_toc}
### Overview

ToothTracker, an all-in-one solution for Front Desk Dental Clinic Administrative Staff to manage patient and dentist records efficiently.
You can interact with ToothTracker via a Command Line Interface (CLI)
along with a Graphical User Interface (GUI) created with JavaFX.
ToothTracker is written in Java and only Java 11 supports ToothTracker.
ToothTracker is optimized for fast-typers interacting with it through CLI.
With ToothTracker, you can add new patient and dentist profiles, add treatments and schedule appointments, delete records, and even list all available profiles in the database, all with just a few simple commands!

{: .no_toc}
### Summary of my contributions
* **New Feature**: Added the ability for receptionists to add dentists (PR [#57](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/57))
  * What it does: The user will be able to add details of a dentist practising in the dental clinic.
  * Justification: This feature is a critical backbone of ToothTracker as it is the starting point for more functionalities relating to dentists. The dental clinic must be able to track the dentist profiles to assign suitable dentists to patients.
  * Highlights: This enhancement affects dentist commands to be added in the future. It required an in-depth analysis of design such as handling parameters. The implementation too was challenging as it required changes to existing commands.
    , edit, and list dentists.

* **New Feature**: Added the ability for receptionists to edit dentist details (PR [#77](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/77))
  * What it does: The user will be able to edit details of a dentist practising in the dental clinic.
  * Justification: This feature is a critical in situations where the dentist profiles must be updated.
  * Highlights: This enhancement affects commands related to displaying dentist details, such as in Appointment and Calendar features.

* **New Feature**: Added the ability for receptionists to list all dentists (PR [#72](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/72))
  * What it does: The user will be able to list all dentists practicing in the clinic.
  * Justification: This is one of the most fundamental feature to have to allow users to view all dentists.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=freddychenyouren2&breakdown=true)

{% include page-break.html %}

* **Enhancements to existing features**:
  * Introduced Optional fields for some of Patient and Dentist fields: Email and Address are optional to provide flexibility in administration. (PR [#65](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/65))
  * Added stronger checks for Specialization field of Dentists: Only Specializations recognized by Ministry of Health will be accepted in the default ToothTracker application. Users need to request developers to change the list of recognized specializations for different contexts. (PR [#141](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/141))
  * Added stronger checks for the validity of birthday date field for Patients: Birthday dates are defined to be any valid dates in the past. Invalid dates such as 30 February, and future dates are not allowed to be put as birthday of a patient. (PR [#218](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/218))
  * Added stronger checks for the validity of names for Patient and Dentist: Names would start with alphabets and end with optional alphanumeric characters to handle duplicates (For example, using last 4 characters of NRIC in Singapore context). (PR [#232](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/232))

* **Documentation**:
  * User Guide:
    * Added documentation for the following dentist features: (PR [#33](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/33))
      * Add a dentist into ToothTracker
      * Edit details of a dentist
      * List all dentists in ToothTracker
    * Standardized command format and User Guide formats during v1.3 iteration (For CS2101 User Guide Draft) (PR [#156](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/156), PR [#112](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/112))
  * Developer Guide:
    * Contributed to user stories and use cases ideation related to adding a dentist.
    * Modify `Model` Class Diagram and descriptions to match ToothTracker design. (PR [#123](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/123))
    * Assist teammate @ruth-lim to Modify `Ui` Class Diagram to match ToothTracker design.
    * Modify `Storage` Class Diagram for better readability. (PR [#235](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/235))
    * Update User Stories to match latest version of ToothTracker. (PR [#229](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/229))
    * Add instructions for Manual Testing on all dentist features. (PR [#127](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/127))
    * Add instructions for Manual Testing on all patient features. (PR [#224](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/224))

* **Community**:
  * PRs reviewed (with non-trivial review comments): PR [#85](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/85)
  * Reported bugs and suggestions for other teams in the class: ([Link to bug report repo](https://github.com/freddychenyouren2/ped))
  * Perform rigorous manual testing to discover more bugs/ambiguity and alert teammates to fix in ToothTracker:
    * `filter-*` commands on multiple `k/` inputs.
    * `delete-treatment` followed by `edit-patient` on a patient who had the deleted treatment.
    * Names field should accept ending alphanumeric characters to handle duplicates.
    * Inconsistencies in Command Output Box of various error input handling before V1.3 release.


