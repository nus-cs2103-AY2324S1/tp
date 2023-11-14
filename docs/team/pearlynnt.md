---
layout: page
title: Pearlynn Toh's Project Portfolio Page
---

### Overview
StudentConnect is the solution for hassle-free team formation for CS2103T students to browse profiles and connect with others for group work.

### Summary of Contributions
Given below are my contributions to the project.

**Code contributed**
* Link to my [RepoSense report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=pearlynnt&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

**Enhancements implemented**
* **Edit Student Feature**: Added the ability to edit a student's personal details
  * What it does: This feature allows the user to edit a student's personal details in the system.
  * Justification: This feature helps users to keep their personal details up-to-date.
  * Highlights: Modified the code to allow the new student fields to be editable.
* **Find Students Feature**: Added the ability to find student(s) by name
  * What it does: This feature allows the user to find student(s) by name with any combination of partial keyword(s).
  * Justification: This feature helps users to find other students more quickly and easily.
  * Highlights: Modified the code to check if each word in the students' name contains the keyword(s) instead of having to match the keyword(s).
* **Filter Students Feature**: Added the ability to filter students by tutorial
  * What it does: This feature allows the user to filter students by tutorial with any combination of tutorial slot(s).
  * Justification: This feature helps users to search for other students who are also interested in attending or are assigned to the same tutorial slot.
  * Highlights: A new Predicate class had to be written to support this feature. Invalid tutorial slot(s) provided by the user had to be handled gracefully.
* **Find Group Feature**: Added the ability to find a group by group number
  * What it does: This feature allows the user to find a project group by a group number.
  * Justification: This feature helps users to find a specific group more quickly and easily.
  * Highlights: A new Predicate class had to be written to support this feature. Invalid group number provided by the user had to be handled gracefully.
* **Filter Groups Feature**: Added the ability to filter groups by tutorial
  * What it does: This feature allows the user to filter groups by a tutorial slot.
  * Justification: This feature helps users to search for groups that belong to a particular tutorial that they may prefer.
  * Highlights: A new Predicate class had to be written to support this feature. Invalid tutorial slot provided by the user had to be handled gracefully.
* **Check Group Feature**: Added the ability to check if a group fulfils the diversity requirements of the course
  * What it does: This feature allows the user to check the composition of a group's members.
  * Justification: This feature helps users to check if a group fulfils the diversity group formation requirements of the course with a single command.
  * Highlights: Different warning messages were written to inform users of what the group may fall short of in terms of group formation. Extensive test cases and test data were written to rigorously test the different possible composition of the group members.

**Contributions to the UG**
* Added the 'Edit Command', 'Find Command', 'Filter Command', 'Find Group Command', 'Filter Group Command', 'Check Command' sections.
* Ensured consistent formatting of the content in the User Guide.

**Contributions to the DG**
* Added the 'Edit a Student', 'Find a student', 'Filter students', 'Find a group', 'Filter groups', 'Check a group' use cases.
* Updated the Storage Class Diagram using PlantUML.

**Contributions to team-based tasks**
* Set up the team's Github Repository, and project documentation.
* Managed the milestones on Github.
* Led the team's project meetings.

**Review/mentoring contributions**
* Reviewed the team's Github pull requests and gave comments where appropriate. (e.g., [#106](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/106), [#177](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/177), [#190](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/190))

**Contributions beyond the project team**
* Tested another team's product and reported bugs during PE-D. ([list of issues](https://github.com/PearlynnT/ped/issues))
* Participated in load testing.
