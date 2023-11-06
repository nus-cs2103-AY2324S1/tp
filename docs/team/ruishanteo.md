---
layout: page
title: Teo Rui Shan’s Project Portfolio Page
---

### Project: TutorConnect

This is an address book made for tuition centre managers to easily track, schedule, and notify tutors of their upcoming schedule, optimised for users who prefer a CLI.

Given below are my contributions to the project.

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ruishanteo&breakdown=true)

* **Enhancements implemented**:
  * **New feature**: Added the ability to edit schedules. (Pull request [#136](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/136), [#171](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/171))
    * What it does: Allows the user to edit schedules.
    * Justification: This feature improves the product by allowing users to modify start and end time of schedules 
      that are in the address book.
  * **New feature**: Added the ability to change theme. (Pull request [#197](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/197))
    * What it does: Allows user to change colour themes.
    * Justification: This feature improves the product by allowing users to view the address book with different 
      colour palettes.
    * Highlights: The implementation was challenging as it required parsing the theme input (e.g. `dark`) and 
      assigning the filepath for the style to be used on top of parsing the command (i.e. `theme`). This feature also 
      required modifications to `UserPrefs` in order to save the previously used theme.
  * **Refactored feature**: Refactored the AddCommand to AddTutorCommand. (Pull request [#61](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/61))
    * What it does: Add tutors to the address book.
    * Highlights: Safe removal of address and tags of a `Person` object.

* **Documentation**:
  * User Guide (Pull requests [#51](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/51), [#149](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/149), [#195](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/195), [#239](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/239))
    * Added usage of User Guide section.
    * Added details of feature to add tutor.
    * Added details of feature to edit schedule.
    * Added details of feature to change theme.
    * Updated feature categories and added signposting with 'you' language.
    * Added glossary.
  * Developer Guide (Pull request [#140](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/140), [#244](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/244))
    * Added implementation details of feature to add tutor.
    * Added implementation details of feature to edit schedule.
    * Added implementation details of feature to change theme.
    * Added planned enhancements for edit schedule.
    * Added planned enhancements for tutor name case sensitivity.

* **Contribution to team-based tasks**:
  * Implement schedule structure: added UniqueScheduleList. (Pull request [#97](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/97))
  * Fix bug: deleting a tutor must delete all associated schedules. (Pull request [#117](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/117))
  * Triage PED bugs.

* **Review/ mentoring contributions**:
  * Review PRs: [#35](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/35), [#52](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/52) [#75](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/75), [#91](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/91), [#102](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/102), [#106](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/106), [#111](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/111), [#142](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/142), [#150](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/150), [#151](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/151), [#153](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/153), [#154](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/154), [#156](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/156), [#158](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/158), [#164](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/164), [#174](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/174), [#181](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/181), [#184](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/184), [#186](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/186), [#202](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/202), [#204](https://github.com/AY2324S1-CS2103T-T17-3/tp/pull/204) 
