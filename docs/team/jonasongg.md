---
layout: page
title: Jonas's Project Portfolio Page
---

### Project: ClubMembersContacts

ClubMembersContacts is an application that allows users to keep track of their club members' contact information.

Below is a summary of my contributions to the project.

* [Code Contributed](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jonasongg&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)
* Enhancements Implemented:
    * Added and adapted the view member and applicant features for our app
        * Created classes including `ViewMembersCommand`
        * Ensured existing automated tests covered the new commands
    * Added the copy member and applicant features
        * Allows users to copy a member or applicant's information to the clipboard for easy pasting elsewhere
        * Created classes including `CopyMemberCommand`, `CopyMemberCommandParser`, etc.
        * Added new tests for the new commands and ensured existing automated tests (such as for `AddressBookParser`)
          covered the new commands
    * Updated the UI
        * To match our app concept of splitting the member and applicant features, added the separate `Member`
          and `Applicant` columns to the UI
        * Among other UI enhancements, made the command result box wrap text to make it easier for users to read
    * Added command aliases for every command (such as `addm` for `addMember`) to make it easier for users to use the
      app
    * Made commands case-insensitive to make it easier for users to type them
* Contributions to the UG:
    * Wrote the section on viewing all member(s)/applicant(s)
    * Wrote the section on copying a member and copying an applicant
    * Improved the formatting of the UG
        * Increased the width of the page so that the code snippets are not cut off
        * Adjusted the size of images so that they are not too big and flowed better with the document
        * Added callout boxes to highlight important information
        * Ensured formatting was consistent throughout the document
    * Ensured the UG was consistent with the app's features, and that the UG was up-to-date with the latest changes
      made to the app
* Contributions to the DG:
  * Updated the DG and its diagrams and explanations to reflect the changes made to `Person`, `Member`, `Applicant`,
    `DeleteMemberCommand` classes and more
  * Wrote sections and added UML diagrams created with PlantUML explaining the view and copy commands and their
    processes
* Contributions to team-based tasks:
    * Created the team repo and added the team members to it
    * Set up the project website for the team
    * Regularly created issues for problems that we discussed about and assigned them to the appropriate team members
    * Updated the issue tracker regularly by closing issues that were resolved by a PR I reviewed
* Review/mentoring contributions:
    * Helped in allowing users to add an interview time at the same time when adding an applicant
    * Helped in the add task feature, especially in terms of creating the UI view for displaying an individual member's
      tasks
    * Offered advice in Git commands
    * Regularly reviewed team members' PRs and issues and offered advice where necessary
    * PRs I reviewed include:
        * [Pull Request #87](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/87)
        * [Pull Request #109](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/109)
        * [Pull Request #125](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/125)
        * [Pull Request #142](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/142)
        * [Pull Request #143](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/143)
        * [Pull Request #147](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/147)
        * [Pull Request #149](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/149)
        * [Pull Request #153](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/153)
        * [Pull Request #215](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/215)
        * [Pull Request #230](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/230)
        * [Pull Request #232](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/232)
        * [Pull Request #243](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/243)
        * [Pull Request #246](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/246)
        * [Pull Request #270](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/270)
        * [Pull Request #271](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/271)
* Contributions beyond the project team:
    * to be added soon
