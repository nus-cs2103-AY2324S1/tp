---
layout: default.md
title: "Ngee Yong's Project Portfolio Page"
---

### Class Manager 2023

*Class Manager 2023 aims to provide fast access to CS2103/T TAs who need help in maintaining student information across multiple classes. It also aims to help TAs visualize data across and within classes.*

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ngeeyonglim&breakdown=true)

* **New Feature**: Edited Student to be referenced by Student Number instead of position in the list. (PR: [#51](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/51))
  * What it does: This allows for easier and more accurate referencing of students in the list.
  * Justification: This is a more accurate way of referencing students as the position of the student in the list may change.
  It will allow the TA to also be able to search for, and perform actions on students by their student number, instead of having to look for them manually in the list.
  Student number is a String that will be unique to each student in NUS, so it could be used as an identifier for each student.
  * Highlights: This enhancement required a lot of changes to the codebase, as the student number is now used as the key for the student, and every constructor of objects referencing students had to be changed.
  We also had to consider whether there was a need to remove the index of the students in the list, as it was no longer needed in normal referencing, but decided to leave it in for possible future features.
  
* **New Feature**: Added the base classes for Class Details and Class Information. (PR: [#73](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/73))
  * What it does: This allows for the storage of information about each student in the class, keeping track of things like their grades, attendance, and other information.
  * Justification: This class is needed in order to properly manage a tutorial class, as the TA would need to manage these information about the student instead of just contact information.
  * Highlights: Thinking of a way to store the information about each student was a challenge, as there were many ways to do so. 
  We decided to use a class to store each instance, and then use trackers to manage the list of these instances, as it would be easier to add more information about the student in the future.

* **New Feature**: Changed UI of the application to enable data visualisation. (PR: [#93](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/93))
  * What it does: This allows the user to view the data in a more intuitive way. 
  Each student in the list will have a card that displays their information (Assignment Grades, Attendance, Class Participation) in a bargraph for easy visualisation of the percentage value.
  * Justification: This is a more intuitive way of viewing the data, as the user can see the data in a more visual way, instead of having to read the data from a table.
  * Highlights: This enhancement required a lot of changes to the codebase, as the UI had to be changed to display the data in a more visual way. 
  There was also a need to experiment with different ways of displaying the data, and we decided to use a bar graph as it was the most intuitive way of displaying the data.
  
* **New Feature**: `comment` command (PR: [#98](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/98))
  * What it does: This allows the user to add comments to each student, which can be used to keep track of the student's progress.
  * Justification: This is a useful feature as it allows the user to keep track of the student's progress, and also allows the user to add notes about the student.
  
* **Edit of existing feature**: Changed help function table. (PR: [#123](https://github.com/AY2324S1-CS2103T-T11-1/tp/pull/123))
  * What it does: This allows the user to view the help function in a more intuitive way, through a list of possible commands and parameters.

* **Documentation**:
  * User Guide:
    * GUI overview
    * Viewing Help
    * Adding a Student
    * Adding comment to a student
    * Added to glossary
  * Developer Guide:
    * Added explanation and design considerations for the Class Details (Added UML Diagrams in this section)
    * Added product scope and user stories
    * Added manual testing appendix and test cases

* **Team-based tasks**:
  * Helped out teammates whenever they needed
  * Helped review pull requests of teammates
  * Helped in setting up the GitHub team repository
  * Maintaining the issue tracker 
  * Updating user/developer docs that are not specific to a feature e.g. documenting the target user profile


