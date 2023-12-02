---
layout: page
title: Lim En Tian's Project Portfolio Page
---

# Project: LinkMeIn

## Overview

LinkMeIn is a desktop address book application used to track computer science student's job applications to companies.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
~10 kLoC.

The features in LinkMeIn can be categorised into 3 main categories:
1. **General features:** Navigating the application. Includes, help, exit and clear commands.
2. **Company management features:** Manage an entry of a company. Includes, add, delete, edit, view, remark and
   unremark commands.
3. **Company list features:** Manage the list of companies added. Includes, list, find, sort and filter commands.

## Summary of Contributions

Given below are my contributions to the project.

### Code contributed
You can find all the code I have contributed in [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=alientian&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos).

### Enhancements implemented

#### 1. Implemented display of application details in the GUI
This enhancement adds a horizontal SplitPane to add a panel to show application details of all the fields input by user.
Users change the company viewed by clicking on the list of companies on the right panel.
This is later on enhanced by my team member, Ru Xin, to be controlled by the `view` command to adhere to our CLI app.
Edited `MainWinfow.fxml` to restructure the GUI and created `CompanyDetailCard.fxml` file to define structure of the panel added and its Java class to render the UI.

#### 2. Implemented new feature: Remark feature
This allows users to write additional details to a specific application. 
Created the `Remark` class to add a remark to the `Company` model.
`RemarkCommandParser` and `UnremarkCommandParser` classes are created to add and remove remarks respectively.
`RemarkCommand` class is also created so that both parser extends from `Parser<RemarkCommand>`.
Test cases were added for all the classes created. 

#### 3. Modified the edit command
Edited the code for `edit` command so that the command can edit all the fields that we have added to the `Company` model. 
The `edit` command should allow users to edit all the fields for more convenience and better user experience.
Added more checks in `EditCommandParser` to provide more specific error message for invalid inputs.
Test cases were edited to test for fields and error messages added.

### **Contributions to the UG**

- **CLI Description** : 
Added an introduction to CLI for users to learn how to write commands.

- **Features** :
Added description, examples, possible errors and images for the following features. 
Tips, notes and warnings are also added if applicable.
    - **Edit** 
    - **Remark**
    - **Unremark**
    - **Clear**
    - **Exit**

### **Contributions to the DG**

- Added NFRs 
- Added planned enhancements
- Added how Edit Command and Remark Command works under Implementation
- Created a UML sequence diagram to explain Edit Command
- Created a UML activity diagram to explain Remark Command

### **Contributions to team-based tasks**

- Created new issues under the issue tracker

### **Review/Mentoring contributions**

- Non-trivial PRs reviewed:
[\#145](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/145),
[\#232](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/232),
[\#274](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/274)


### **Contributions beyond the project team**

- [Bugs reported during PE-D](https://github.com/alientian/ped/issues)
