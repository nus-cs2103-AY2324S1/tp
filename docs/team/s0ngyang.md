---
layout: page
title: Kee Song Yang's Project Portfolio Page
---

### Project: HouR

HouR is a desktop HR management application used for managing and organising personnel data.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 8 kLoC.

Given below are my contributions to the project.

* **New Feature**: Overtime Feature `overtime`
    * What it does: Allows the user to add overtime hours to a specified employee.
    * Justification: Overtime hours tracking does not follow a fixed schedule, and is often ad-hoc. 
    With this feature, HR managers will be able to keep track of the overtime hours taken by respective employees
    in an organised manner.
    * Highlights: Maximum overtime hours allowed is 72 hours per month, in accordance with the Employment Act.
    Trying to add overtime hours that exceed this limit will result in an error message being displayed.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=s0ngyang&breakdown=true)

* **Project management**:
  * Reviewing, approving and merging pull requests on GitHub (Pull requests [#127](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/127), [#144](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/144), [#149](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/149))
  * Resolve merge conflicts on GitHub ([Merge Commit](https://github.com/AY2324S1-CS2103T-W12-1/tp/commit/7f05c752c5fe887db5fee74b0e9e4b1b8eca145b))
  * Maintaining Team Project Notes on Google Docs

* **Enhancements to existing features**:
  * Improve EmployeeCard GUI (Pull request [#151](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/151))
  * Added "On Leave" attribute to UI (Pull request [#117](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/117))
  * Change `delete` command to delete by employee ID instead of index (Pull request [#95](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/95))

* **Documentation**:
    * User Guide: 
      * Added guide for the feature `overtime`
      * Check through overall formatting and content of UG
    * Developer Guide:
      * Added implementation guide for `overtime` feature

* **Tools**: JavaFX, SceneBuilder, GitHub, Git, Gradle
