---
layout: page
title: Chan Rui Jia's Project Portfolio Page
---

### Project: ClubMembersContacts

ClubMembersContacts is an application that allows users to keep track of their club members' contact information.

Given below are my contributions to the project.

* Code Contributed: [link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=matochichap&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* Enhancements Implemented:
  * Major refactoring of code
    * Made `Person` class abstract and removed redundant commands and test cases related to `Person`
    * Wrote outline of methods and TODOs to make it easier for team members to implement the relevant classes and methods they are responsible for.
    * Committed over 3.5k lines of code initially to help team members to adapt `Person` class to `Applicant` and `Member` classes for functional code and test cases
  * Created Add Applicants feature.
    * User is able to add a new applicant to the applicant list.
    * Wrote new classes for this feature : `Applicant`, `AddApplicantCommand`, `AddApplicantCommandParser`, `JsonAdaptedApplicant`
    * Edited other related classes like `ModelManager`, `AddressBook`, `JsonSerializbleAddressBook`, `UniquePersonList`, `LogicManager`
  * Created View All Tags feature.
    * User is able to view all tags that all members are tagged with.
    * Wrote new classes for this feature : `TagListPanel`
    * Edited other related classes like `ModelManager`, `AddressBook`, `MainWindow`, `LogicManager`
    * Edited Help Window to open UG in their browser.
  * User is able to open the UG in their browser by clicking a button.
    * Edited other related classes like `HelpWindow`
    * Wrote comprehensive test cases to achieve over 90% coverage for all functional code written by me, excluding functional code related to UI.
* Contributions to the UG:
  * Wrote the section for adding Applicants and viewing all tags.
* Contributions to the DG:
  * Wrote the section for explaining `Model`, adding `Applicants` and viewing all `Tags`.
  * Created UML diagrams using PlantUML.
  * Used class, sequence and activity diagram to explain the above sections.
* Contributions to team-based tasks:
  * Regularly created new issues and assigned them to respective members.
  * Maintained issue tracker by closing finished issues.
  * Wrote notes in code to provide additional information for team members.
* Review/mentoring contributions:
  * Reviewed members PRs and gave comments about their code when needed.
  * Helped team members with some of the problems they encountered with their code.
  * Here are the PRs that I have reviewed :
    * [Pull Request #31](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/31)
    * [Pull Request #32](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/32)
    * [Pull Request #44](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/44)
    * [Pull Request #60](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/60)
    * [Pull Request #100](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/100)
    * [Pull Request #109](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/109)
    * [Pull Request #125](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/125)
    * [Pull Request #135](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/135)
    * [Pull Request #142](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/142)
    * [Pull Request #150](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/150)
* Contributions beyond the project team:
  * Arranged meetings for the group weekly and when there was a need to meet for discussions.
