---
layout: default.md
title: "Eric Xiong's Project Portfolio Page"
---

### Project: MediSync

MediSync is a desktop app specifically used for head nurses to manage staff and patients, optimised for use via a Command Line Interface (CLI). Instead of tracking data of staff and patients using existing applications such as Microsoft Excel, using a CLI-centric application like MediSync will help head nurses track and handle contact management more quickly.

Given below are my contributions to the project.

* **New Feature**: Added the affiliation command, `affn`
    * What it does: Displays the affiliations of a doctor/patient
    * Justification: This feature adds on the specific functionality for a head nurse, by allowing a nurse to find out the doctors of a patient, or the patients of a doctor.
    * Highlights: This implementation was challenging as the "tag" functionality had to be changed to "affiliation", which required project-wide renaming and refactoring, across both code and comments. The implementation required certain additions to Logic classes, and the addition of a new predicate.

* **New Feature**: Added the shift command, `shift`
  * What it does: Updates the shift days of the specified staff member.
  * Justification: This feature is essential for allowing a head nurse to track the shifts of the various staff, and other commands such as `onduty` rely on a consistent shift adding system so that it can return the proper personnel.
  * Functionality: This feature was implemented to be as fast as possible for a head nurse to type out, optimising for CLI syntax. This is because it only needs the head nurse to specify the index of the person, as well as the shift days in numerical form without spaces, minimising key presses.
  * Highlights: This implementation was challenging as it required integration with the `ShiftDays` class created by a team member, thus it required first understanding the functionality of the class before being able to implement the command. It also required new parsing classes and methods, which added to the complexity, especially in testing the class.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ericxiong420&breakdown=true)

* **Enhancements to existing features**:
    * Added to and redefined the `find` command to allow it to search and filter using attributes other than name. This is useful as head nurses may wish to search for a specific role or phone number. This implementation was challenging as it required redefining how the find arguments are parsed, creating several new classes and predicates for filtering by the various attributes, and ensuring that all possible errors are caught by the application.

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `affn` [\#68](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/68)
        * Added documentation for the feature `shift` and the updated `find` command [\#168](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/168)
    * Developer Guide:
        * Updated documentation for the `Logic` component and added implementation details for the `shift` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#109](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/109), [\#160](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/160), [\#161](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/161), [\#214](https://github.com/AY2324S1-CS2103-T16-2/tp/pull/214)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/155), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/164))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S1-CS2103-T16-2/tp/issues/136))
