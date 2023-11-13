---
layout: page
title: Elgin's Project Portfolio Page
---

### Project: TAvigator

**TAvigator** aims to provide a one-stop platform for tutors to keep track of the work submission and attendance of each student via a contact management application. Allowing tutors to manage their tutorial groups with ease, from marking attendance to tracking individual work submissions.

Given below are my contributions to the project.

* **New Feature**: Added the ability to store multiple addressbooks for different courses through the `course` command.
    * What it does: Allows users to have independent addressbooks for different courses.
    * Justification: This feature improves the organisation of student contacts for tutors tutoring for multiple classes.
    * Highlights: This feature minimized the impact on existing commands as from each command's point of view, the `getAddressBook` function will simply
    return the currently selected addressbook. This feature, however, required rewriting some parts of the existing storage code to allow the serializing and deserialzing the new internal state.
  
* **New Enhancement**: Added `FilterSettings` to `UserPrefs` to allow for storing of multiple predicates.
    * What it does: Allows multiple predicates to be applied to the current list.
    * Justification: This feature improves the product by allowing the user to have more flexibility in what they want to view instead of only by a single condition.
    * Highlights: The implementation of the command was relatively straightforward as it was quite similar to how `GuiSettings` was implemented and existing interfaces had to be updated with the new functions.

* **New Enhancement**: Improved the UI of the app
    * What it does: Allows the user to see what course is selected and the status on number of students.
    * Justification: This feature improves the product intuitiveness as it provides users with a clearer and more user-friendly interface. By displaying the selected course and the real-time status of the number of students, users can quickly and easily access important information at a glance.

* **Code contributed**: [Reposense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ElginTZM&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Contribution to team-based tasks**:
  * Managed issues on issue tracker
  * Updated `README.md` (PR: [#49](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/49))

* **Enhancements to existing features**:
  * Updated `UniquePersonList` to use `ID` as criteria for uniqueness

* **Documentation**:
    * User Guide:
        * Added details for `CourseCommand`
    * Developer Guide:
        * To be added later

* **Review/ mentoring contributions**:
  * Reviewed 9 PRs

* **Contributions beyond project team**:
  * Reported bugs other teams with comprehensive detailing and suggestions. (Bug Reports: [1](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/176), [2](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/175), [3](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/173), [4](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/169), [5](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/167), [6](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/166), [7](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/162), [8](https://github.com/AY2324S1-CS2103T-T17-1/tp/issues/158))
