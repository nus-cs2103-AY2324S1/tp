---
layout: page
title: Isaac's Project Portfolio Page
---

### Project: MediLink Contacts

MediLink Contacts aims to help medical staff including nurses/doctors/pharmacists navigate through patient details in
their high workload and time-pressured working environment. When medical emergencies arise, it becomes crucial to
provide rapid access to emergency contacts for patients and access other details of the patients to make decisions more
quickly. It is optimised for CLI so that users can quickly access the information. There is also a GUI created with
JavaFX.

Given below are my contributions to the project.

* **New Feature**: Enhanced the Find feature to function with NRIC, blood type, gender as well as name.
    * What it does: Allows users to search for Patients/Doctors by their NRIC, blood type, gender or simply name
    * Justification: Users can search by a specific attribute, increasing convenience

* **New Feature**: Added a Find Appointment function to locate a specific appointment by NRIC of people involved
* **New Feature** Added a Delete Appointment function to delete a specific appointment by NRIC of people involved

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Wrote an abstract UniqueObjectList that can be inherited to form UniqueLists of Patients, Doctors and Appointments
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `find`, `delete-appt`, `find-appt` [\#72]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
