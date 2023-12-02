---
layout: page
title: Tay Ru Xin's Project Portfolio Page
---

# Project: LinkMeIn

LinkMeIn - Is a desktop address book application made for computer science to 
their internship application.
The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java, and has about
~10 kLoC.

The features in LinkMeIn can be categorised into 3 main categories:
1. **General features:** Navigating the application. Includes, help, exit and clear commands.
2. **Company management features:** Manage an entry of a company. Includes, add, delete, edit, view, remark and unremark commands.
3. **Company list features:** Manage the list of companies added. Includes, list, find, sort and filter commands.

Given below are my contributions to the project.

## **Code contributed**:
The code that I have contributed can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tayruxin&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

## Enhancements implemented: 

**1. Implemented the view command.**

Created a new command class `ViewCommand` to handle the view command. A new parser class `ViewCommandParser` was created to handle the parsing of the view command. 
Furthermore, a new UniqueCompanyList was created in `AddressBook` to store the 
filtered company list which will contain the company that the user wish to view. Test cases were added to test the view command.

**2. Enhance error message for invalid index**

Previously, when checking for invalid index, the error message is the same regardless of whether the index is out of bounds or the index is not a positive integer.

I have enhanced the error message to be more specific to the error. I have added new methods in `String.util` to check if the index passed is an integer with the 
`isInteger` method.

**3. Added the company detail panel and company detail card to the GUI.**

Previously, the GUI only shows the list of companies that the user have. I have added a `CompanyDetailPanel` which extends `UiPart<Region>` to the GUI. 
Furthermore, I have added `CompanyDetailPanel.fxml` to the GUI. 


**4. Added the priority field to company model.**

A new priority field was added to the company model. I have made the priority filed optional. When the user adds a company 
without indicated the priority, the priority will be set to `none`.

**5. Added the application status field to company model.** 

A new application status field was added to the company model. The application status is an enums with 5 possible values. 

**6. Delete address field from the company model.**

The address field was deleted from the company model as the address field was not used in any of the commands. Address field was also removed in all the various 
test cases.

## Contribution to the UG: 

The following sections from the UG were contributed by me:
1. **Introducing LinkMeIn's User Interface:** Added an intro to LinkMeIn's GUI. 
2. **Navigating the User Guide:** Added a section on how to navigate the UG, which includes symbols used and how the user guide is catered for all levels of users. 
3. **Quick Start:** Added this section to teach user how to download and set-up LinkMeIn.
4. **Introducing LinkMeIn: A quick tour:** Added this section to teach first-time users how to use the application. Included features like 
add, view, delete, edit and clear. 
5. Features: 
   - View full company information: Added description, examples and possible errors for the view command.
   - Delete a company: Added description, examples and possible error for the delete command.
6. Added FAQ:
   - How do I check if I have Java 11 installed?
   - How do I open up my terminal? 
   - What is an invalid index?

## Contribution to the DG:
- Added use cases into the DG.
- Added planned enhancements into the DG. 
- Added a UML sequence diagram for the view command into the DG.
- Added a UML Activity diagram for the view command into the DG.
- Elaborated on how the View command works in the DG.

## Contribution to team-based tasks:
- Set up the team repo and organisation on GitHub.
- Created the labels that are required for the project.
- Set up the project board in GitHub for user stories.
- Help to enable assertion in the team repo (in `build.gradle`).

## Review/mentoring contributions:
- PRs reviewed: 
  - [\#135](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/135), [\#111](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/111),
[\#89](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/89), [\#110](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/110),
[\#147](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/147),
[\#105](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/105)

## Contributions beyond the project team:
- Bugs reported for the other team during PE-D: [link to issues](https://github.com/tayruxin/ped/issues)

