---
layout: page
title: Yiqiao's Project Portfolio Page
---

### Project: TAvigator

TAs sometimes have many tutorial groups to manage, it could be chaotic to manage the contact details and attendance of
each individual student. TAvigator aims to provide a one-stop platform for National University of Singapore
Computer Science modules’ Teaching Assistants (TAs) to keep track of the details of each student via a contact 
management application.

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

#### **New Feature**: Added the tallied attendance feature
* What it does: Displays a simple summary of the number of tutorials the student attended and missed. 
* Justification: Allows the tutor to view at a glance which students have been consistently missing lessons, so that they can take the necessary actions. The main goal of easy access was also why we did not choose to implement this feature as a separate command.
* Highlights: The implementation of this feature was fairly straightforward, but required vigorous testing to ensure that the outputs were correct and displayed with the proper formatting.

#### **New Feature**: Added the merge command
* What it does: Merges two the information of two students. The new student has the name, email, phone number and student id of the primary student. The tags and attendance records of both students are now owned by the new student. 
* Justification: Allows the tutor to merge duplicated contacts, so that the there is no case of the tutor recording attendance under the wrong student. 
* Highlights: The implementation of this feature was tricky in that I had to make sure that there are no duplicated records after merging the two students. 

#### **Code contributed**: 
[RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=fuyiqiao&tabRepo=AY2324S1-CS2103T-T09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### **Contributions to team-based tasks**:
* Managed issues on the issue tracker
* Released v1.3
* Removed traces of AB3 from the code base

#### **Documentation**:
* User Guide:
  * Edit general formatting 
  * Edit inconsistencies (PR [#95](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/95))
  * Added details of merge command 
  * Edit details for add and edit commands
* Developer Guide:
  * Added implementation details for tallied attendance feature 
  * Added implementation details for merge command, including the sequence and activity diagrams
  * Added manual test cases
  * Added use cases
  * Added future enhancements 
  * Added effort section

#### **Review / mentoring contributions**:
  * Reviewed 14 Pull Requests 

#### **Contributions beyond the project team**:
  * Reported bugs and suggestions for other teams during PE-D.
    * Links: [1](https://github.com/fuyiqiao/ped/issues/1), [2](https://github.com/fuyiqiao/ped/issues/2), [3](https://github.com/fuyiqiao/ped/issues/3), [4](https://github.com/fuyiqiao/ped/issues/4), [5](https://github.com/fuyiqiao/ped/issues/5), [6](https://github.com/fuyiqiao/ped/issues/6), [7](https://github.com/fuyiqiao/ped/issues/7), [8](https://github.com/fuyiqiao/ped/issues/8), [9](https://github.com/fuyiqiao/ped/issues/9), [10](https://github.com/fuyiqiao/ped/issues/10)
