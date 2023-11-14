---
layout: page
title: Sun Zihan's Project Portfolio Page
---

### Project: CodeSphere

**CodeSphere** is a desktop **student management app, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). It is an app targeted at Teaching Assistants (TAs) in the National University of Singapore (NUS) School of Computing (SoC).

Given below are my contributions to the project.

* **New Feature**: Sort student list by name or performance tag.
  * What it does: The sort command allows the user to specify a sort criteria and sorts the student list accordingly. The supported enum sort criteria are TAG and NAME.
  * Justification: The ability to display all students in a specified order can be helpful to Teaching Assistants (TAs) in many situations. For example, sorting by tag allows TAs to easily analyse students’ performance while sorting by name allows for a more organised student list for easier reference. Overall, sorting improves the user experience by enabling flexible data organisation.
  * Highlights: The sort feature can be combined with the find feature, meaning the user is able to apply a sort criteria on a filtered list, or filter a sorted list.

* **New Feature**: List students with a non-empty pending question field.
  * What it does: This feature allows the user to find and list all students within a course whose pending question field is not empty.
  * Justification: As TAs might sometimes be overwhelmed by the number of questions, some questions may get overlooked. Therefore, listing all students with unanswered questions is essential for TAs to keep track of which questions they have yet to address, and ensures that TAs can provide timely responses and support to students.

* **New Feature**: Reset student list.
  * What it does: The reset command is used after sorting or filtering the student list, to restore the student list to its original state where all students in a course are ordered chronologically by when they were added.
  * Justification: After filtering or sorting the student list, the displayed student list might be altered. The user should therefore have a way to restore the student list to its initial state. This feature is essential for maintaining data integrity, ensuring that users can always revert to the original student list to prevent data loss or confusion.
<br>
* **Enhancements to existing features**:
  * Adapted AB3's original add person command to be used in CodeSphere as the command to add a student into a course (PR [\#65](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/65)).
  * Added summary statistics for each course in the course list to show the total number of students in that course, the number of students with non-empty pending question fields, and the number of students who are tagged as GOOD, AVERAGE and POOR. (PR [\#94](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/94), [\#135](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/135)).
  * Added new tests and modified existing tests (PR [\#241](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/241), [\#253](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/253), [\#256](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/256), [\#260](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/260), [\#261](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/261), [\#262](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/262)).
<br>
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sunzihan23&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=sunzihan23&tabRepo=AY2324S1-CS2103T-W15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
<br>
* **Team-tasks**:
  * Set up the GitHub team organisation and repository.
  * Set up Codecov in the team repository (PR [\#36](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/36)).
<br>
* **Community**:
  * Reviewed PRs, and suggested improvements for things like Javadoc style and code quality.
  * Reported bugs for team CS2103T-T15-4 during PE-D and suggested possible improvements.
<br>
* **Documentation**:
  * User Guide:
    * Added documentation for the features `list` and `reset` (PR [\#293](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/293)).
    * Improved documentation quality for the all sections in the UG (PR [\#363](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/363), [\#381](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/381)).
  * Developer Guide:
    * Described the implementation of the `sort` command (PR [\#349](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/349)) and created the sequence and activity diagrams (PR [\#350](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/350)).
    * Added user stories, use cases and non-functional requirements (PR [\#316](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/316)).
    * Added instructions for manual testing (PR [\#332](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/332)).
