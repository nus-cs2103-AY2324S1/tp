---
layout: page
title: Jin Yin's Project Portfolio Page
---

### Project: HouR

HouR is a desktop HR management application used for managing and organising personnel data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 8 kLoC.

Given below are my contributions to the project.

* **New Feature**: Add Leave Feature `addleave`
  * What it does: Allows the user to add a leave period to a specified employee.
  * Justification: Leave tracking is an important component of employee management systems. With this feature, HR managers will be able to keep track of the leaves taken by respective employees.
  * Highlights: Deciding whether it would be better to add a single date each time or a specified period. 
    Ultimately, the team decided that it would be best to implement the command flexibly, with the current format allowing the addition of both a single leave date and a leave period with multiple days.


* **New Feature**: List Leave Feature `listleave`
  * What it does: Allows the user to view the list of employees on leave on a particular day.
  * Justification: Provide the user a fast and easy method to view the employees that are on leave on a particular day, rather than checking through each and every employee one by one to see if they are on leave on the date of interest.
  * Highlights: Initially, this feature was only meant to check which employees were currently on leave (in real time). However, I realised that it would be more useful if the user could check leaves taken on specified days, to help with the employee management process.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=miljyy&breakdown=true)


* **Project management**:
  * Reviewing, approving and merging pull requests on GitHub (Pull requests [#76](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/76), [#144](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/144), [#211](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/211))
  * Creating and closing issues using Github's issue tracker
  * Maintaining Team Project Notes on Google Docs


* **Enhancements to existing features**:
  * Added **Salary attribute** to the existing Employee class (Pull request [#92](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/92))
  * Edited **Phone attribute** to only accept local mobile numbers (Pull request [#109](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/109))
  * Added **LeaveList attribute** to the existing Employee class (Pull request [#124](https://github.com/AY2324S1-CS2103T-W12-1/tp/pull/124))


* **Documentation**:
  * User Guide:
    * Added Table of Contents, Glossary and FAQ section
    * Included v1.2 commands into command summary and parameter information
    * Added guides for list leave feature
    * Check through overall formatting and content of UG
  * Developer Guide:
    * Added implementation and testing guide for add leave and list leave feature
    * Created UML diagrams to illustrate implementations
    * Updated the user stories
    * Check through overall formatting and content of DG


* **Tools**: Java, JavaFX, IntelliJ, Github, Sourcetree, PlantUML
