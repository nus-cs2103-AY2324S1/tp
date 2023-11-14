git---
layout: page
title: Faiz's Project Portfolio Page
---

### Project: MediLink Contacts

MediLink Contacts aims to help medical staff including nurses/doctors navigate through patient details in
their high workload and time-pressured working environment. When medical emergencies arise, it becomes crucial to
provide rapid access to emergency contacts for patients and access other details of the patients to make decisions more
quickly. It is optimised for CLI so that users can quickly access the information. There is also a GUI created with
JavaFX.

Given below are my contributions to the project.

* **Code contributed**:
  [Link to TP code dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=Faiz&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **New Feature**: Created new Doctor class
  * Adapted UI and Storage to store Doctors instead of Person
  * Changed Person card to Doctor card so that it can get displayed on the UI
  * Modified UI to also display Doctors as a List alongside the Patients
* **New Feature**: Modified current add person command to add new Doctor class
* **New Feature**: Created a new Class Appointment to represent an Appointment between a pre-existing Doctor and Patient
  * Added `new-appt` command to add new Appointment
* **New Feature**: Updated part of the sample data to include the new features
* **Code Quality**: Removed Unnecessary Code and Classes to prevent confusion
  * Removed AddCommandClass and made adjustments to code to accomodate changes

* **Testing**: Created JUnit Test Classes
  * Created Testcases for Doctor and related Classes (e.g AddDoctorCommand, AddDoctorCommandParser)

* **Project management**:
  * Managed milestones and issues
  * Did Peer Reviews on other teammates' Pull Requests
  * Resolved Merge Conflicts where applicable
  * Did Code Reviews on documentation and variable naming to ensure uniformity

* **Documentation**:
  * User Guide:
    * Updated existing content to our project
    * Added the section on adding new doctors
    * Added the section on adding new appointments
    * Added the section on Common Pitfalls
    * Proofread other sections and added the commands/sample commands to a summary table.
  * Developer Guide:
    * Added Use Cases 1 and 2


