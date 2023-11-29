---
layout: page
title: Qin Yan Er's Project Portfolio Page
---

{: .no_toc}
### Project: ToothTracker

**Overview**

ToothTracker is an all-in-one solution designed to revolutionize
the management of dental clinic records.

Given below are my contributions to the project.

* **New Feature**: Add appointment ([#64](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/64),
  [#86](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/86), [#88](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/88),
  [#103](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/103))
    * What it does: Allows user to add in an appointment to ToothTracker.
    * Justification: This feature is an essential feature of ToothTracker as it allows user to keep track
      of their clinic's past and future appointments. It helps to simplify the process of recording down an
      appointment.
    * Highlights: The command is heavily intertwined with other commands as it needs to retrieve
      critical information from the model. If other add commands do not work, `add-appointment` would not work either.
      It was difficult trying to retrieve the correct information and also checking for appointment clashes.

* **New Feature**: Delete appointment ([#103](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/103))
    * What it does: Deletes an appointment from ToothTracker permanently. This is done using a unique appointment id.
    * Justification: This is a crucial feature as it allows user to delete incorrect or irrelevant appointment details.

* **New Feature**: List appointment ([#86](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/86), [#88](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/88))
    * What it does: Allows user to list all appointments and their details saved in ToothTracker
    * Justification: This is important as user needs to be able to view all appointments details to keep track of the clinic's records.

* **New Feature**: Filter appointment ([#103](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/103), [#111](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/111))
    * What it does: Filters appointment by dentist id or patient id.
    * Justification: This command provides user with a more convenient way to view appointments. Instead of
      scrolling down a long list of appointments, user can just filter to view the appointment with the specific
      dentist or patient easily.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=qyaner&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Enhancements to existing features**:
    * Add unit tests for appointment features. ([#129](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/129),
      [#151](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/151), [#216](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/216))

* **Documentation**:
    * User Guide:
        * Added documentation for appointment features and commands. ([#91](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/91),
          [#122](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/122))
    * Developer Guide:
        * Added documentation for appointment features under "Implementation" section.
          ([#122](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/122), [#221](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/221))
            * Added `add-appointment` activity and sequence diagrams.
            * Added `filter-appointment` activity and sequence diagrams.
            * Added `delete-appointment` activity and sequence diagrams.
        * Added use cases for all appointment features.
          ([#122](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/122), [#221](https://github.com/AY2324S1-CS2103T-W10-3/tp/pull/221))

* **Community**:
    * Reported bugs and suggestions for other teams in the class ([Link to repo](https://github.com/qyaner/ped))
