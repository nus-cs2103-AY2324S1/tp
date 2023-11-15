---
layout: page
title: Chew Jing Heng's Project Portfolio Page
---

## Project: NUSCoursemates

### Overview
NUSCoursemates is a desktop address book application used for keeping track of the courses your contacts are taking. 
The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. 
It is written in Java, and has about 20 kLoC.

### Summary of Contributions
Given below are my contributions to the project
[on RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=chewjh1234&breakdown=true).
  
#### New Feature - Sort by Name
* **What it does:** Allows users of the application to sort the student contacts by name. 
* **Justification:** Before this feature was implemented, new contacts were added to the bottom of NUSCoursemates which meant that it could get messy very easily. With this feature, contacts are sorted by name in alphabetical order (aAbBcC...zZ), and it will take the user much less time to search for a contact by name, thereby improving user experience. 
* **Highlights:** The user is also able to specify whether the contacts are to be sorted in an **ascending** or a **descending** alphabetical order with the `sort name-ascending` and `sort name-descending` commands respectively. 
* **Credits:** Referenced Stack Overflow [forum](https://stackoverflow.com/questions/57277475/sort-results-in-ascending-return-in-the-form-a-a-b-b-in-java) for case-insensitive comparator. 

#### New Feature - Sort by Course
* **What it does:** Allows users of the application to sort the student contacts by the number of courses taken. 
* **Justification:** With this feature, users are able to determine the contacts that have the largest number of overlapping courses as them. This helps users to decide the courses they should take in the semester and to also connect with these contacts, which is the main goal of **NUSCoursemates**.
* **Highlights:** The user is also able to specify whether the contacts are to be sorted in an **increasing** or a **decreasing** number of courses taken with the `sort course size-ascending` and `sort course size-descending` commands respectively. 
* **Credits:** None

#### New Feature - Sort by Tags
* **What it does:** Allows users of the application to sort the student contacts by their tags. 
* **Justification:** Similar to how our Instagram close friends' stories appear first, the Sort by Tags function allows users to sort their contacts by tags, so that the users' close friends would appear right at the top of **NUSCoursemates**. This allows users to better keep track of the people who matter to them the most. 
* **Highlights:** As students can have multiple tags, only the highest priority tag will be taken into consideration. This is done by iterating the list of tags to find the one with the highest priority. 
* **Credits:** None

#### Project management
* In charge of the deadlines for tasks, including the creation and closure of various milestones. 
* Released v1.2 and v1.2b NUSCoursemates.jar files on [GitHub](https://github.com/AY2324S1-CS2103T-T17-4/tp/releases). 

#### Documentation
* User Guide
  * Added documentation for the various [sort features](https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html#sorting-by-tags-sort-tags). 
  * Added the [Command Line Interface (CLI) tutorial](https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html#using-the-command-line-interface-cli), [Command Format section](https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html#command-format).
  * Modified [Introduction](https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html#introduction), [Quick Start](https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html#quick-start) and [FAQ](https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html#faq).
* Developer's Guide
  * Added documentation for the various [sort features](https://ay2324s1-cs2103t-t17-4.github.io/tp/DeveloperGuide.html#45-sort-feature) including UML diagrams (Activity diagram, Sequence diagram).
  * Added [Manual Testing](https://ay2324s1-cs2103t-t17-4.github.io/tp/DeveloperGuide.html#appendix-b-instructions-for-manual-testing). 
  * Modified [Product Scope](https://ay2324s1-cs2103t-t17-4.github.io/tp/DeveloperGuide.html#a1-product-scope), [Use Cases](https://ay2324s1-cs2103t-t17-4.github.io/tp/DeveloperGuide.html#a3-use-cases), [Planned Enhancements](https://ay2324s1-cs2103t-t17-4.github.io/tp/DeveloperGuide.html#appendix-c-planned-enhancements). 

#### Review/mentoring contributions
* Reviewed [27](https://github.com/AY2324S1-CS2103T-T17-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Achewjh1234) pull requests.
* Some critical bugs were found when reviewing pull requests. For example, I found a [bug](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/107#pullrequestreview-1691405863) which incorrectly prevented contacts from being added.
* Some notable PR reviews: [#57](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/57#pullrequestreview-1642578187), [#106](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/106#pullrequestreview-1685497544), [#107](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/107#pullrequestreview-1691405863), [#132](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/132#pullrequestreview-1697943613), [#288](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/288#pullrequestreview-1727318899).
* In addition, [bugs](https://github.com/chewjh1234/ped) for another group's project were also found during the Practical Examination dry run.
