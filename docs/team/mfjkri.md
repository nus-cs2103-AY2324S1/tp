---
layout: page
title: Muhammad Fikri’s Project Portfolio Page
---

### Project: TutorConnect

TutorConnect is an **address book made for tuition centre managers** to easily track, schedule, and notify tutors of 
their upcoming schedule, optimised for users who prefer a CLI.

Given below are my contributions to the project.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=mfjkri&breakdown=true)

* **Enhancements implemented**:
  * **New feature**: Added the ability to unmark schedules [#137](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/137).
    * What it does: Allows user to remove status from a specified schedule.
    * Justification: This feature improves the product by allowing users to remove status set with the `mark` command.
  * **New feature**: Added the ability to view schedules as a calendar [#196](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/196).
    * What it does: Allows user to view schedules that fall on a specified date in a calendar form.
    * Justification: This feature improves the product by allowing users to quickly visualise the allocation of 
      schedules by tutor in a calendar view.
    * Highlights: This feature required in-depth analysis as it required a different view to be displayed on 
      activation. The implementation too was challenging as it required a calendar view to be dynamically created 
      which has not yet been done in AB-3.
  * **Refactored feature**: Refactored the `EditCommand` to `EditTutorCommand` [#74](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/74).
    * What it does: Updates the existing add command with the context of the application, tutors.
  * **Refactored feature**: Refactored the `FindCommand` to `FindTutorCommand` [#60](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/60).
      * What it does: Updates the existing find command with the context of the application, tutors.

* **Documentation**:
    * User Guide:
        * Added details of edit tutor feature [#156](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/156), 
          [#221](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/221).
        * Added details of find tutor feature [#52](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/52), 
          [#221](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/221),
        * Added details of unmark schedule feature [#153](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/153), 
          [#221](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/221).
        * Added details of show calendar feature [#221](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/221).
        * Added Navigating the User Guide section [#154](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/154), 
          [#221](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/221).
        * Added more questions in the FAQs section [#154](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/154).
    * Developer Guide:
        * Updated Requirements section in Appendix [#35](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/35).
        * Updated Design section [#207](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/207),
          [#312](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/312).
        * Added implementation details of edit tutor feature [#139](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/139).
        * Added implementation details of find tutor feature [#312](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/312).
        * Added implementation details of unmark schedule feature [#312](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/312).
        * Added implementation details of show calendar feature [#312](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/312).
        * Added planned enhancement for disallowing future schedules to be marked [#304](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/304).
        * Added planned enhancement for switching back to list view from calendar view [#304](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/304).
        * Added planned enhancement for calendar UI colors [#304](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/304).

* **Contribution to team-based tasks**:
    * Added schedule list to ModelManager [#92](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/92).
    * Updated SampleDataUtil to include sample schedules [#145](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/145).
    * Added status to Schedule [#133](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/133).
    * Triage PED bugs. 
    * Fix bugs: 
      [#113](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/113),
      [#185](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/185),
      [#271](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/271),
      [#276](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/276).

* **Review/ mentoring contributions**:
    * Reviewed PRs:
    [#49](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/49),
    [#50](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/50),
    [#102](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/102),
    [#103](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/103),
    [#106](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/106),
    [#151](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/151),
    [#165](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/165),
    [#177](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/177),
    [#188](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/188),
    [#200](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/200),
    [#210](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/210),
    [#211](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/211),
    [#212](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/212),
    [#227](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/227),
    [#233](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/233),
    [#238](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/238),
    [#239](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/239).
