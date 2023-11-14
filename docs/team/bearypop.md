---
layout: page
title: Yik Leong's Project Portfolio Page
---

### Project: StudentConnect

StudentConnect is a solution for hassle-free team formation for students to browse profiles and connect with others for group projects. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

### New Feature: Create group
* Added a new feature that allows students to create groups in the app [\#77](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/77)
* This feature makes the process of group formation much more convenient as the students can now form groups in the app itself, instead of having to use another channel such as social media to contact other students to form groups.
* Implementing this feature required the creation of many new models and classes such as `Group` and `JsonAdaptedGroup`. The `Addressbook` class had to be modified significantly to support storing groups and the various group operations. Overall, adding this new feature was a complex process due to the large number of additions and changes that had to be made.

### New Feature: List groups
* Added a new feature that allows students to view the list of groups in the app [\#113](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/113)
* This feature allows students to view the groups that have been created, as well as the members of each group. This will help students in finding suitable groups to join as they are able to see which groups are not yet full.
* This feature was particularly time-consuming and challenging to implement as it involved the creation of various new components in the UI to display the group information. Something noteworthy to point out is that conditional rendering is used to display either the person UI or the group UI in the window, based on the last command that the user entered.

### New Feature: Join group
* Added a new feature that allows students to join a group in the app [\#97](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/97)
* This feature is an essential part of the group formation service that our app provides.

### Enhancements to existing features:
* Updated the `exit` feature [\#15](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/15)

### Testing:
* Updated tests for `add` command [\#57](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/57)
* Updated tests for `delete`, `edit` commands and Logic Manager [\#58](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/58)
* Created tests for the `Group` class [\#174](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/174)
* Created tests for `create`, `join` and `listGroup` commands [\#194](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/194), [\#208](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/208)

### Code contributed: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=bearypop&breakdown=true)

### Documentation:
* User Guide:
    * Added documentation for the features `exit` [\#15](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/15), [\#61](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/61)
    * Added documentation for the features `create`, `join` and `listGroup` [\#137](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/137)
* Developer Guide:
    * Added documentation for the features `create` and the `UI` component [\#83](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/83), [\#200](https://github.com/AY2324S1-CS2103T-F12-2/tp/pull/200)
