---
layout: page
title: Saezenn's (Alicia) Project Portfolio Page
---

### Project: ClubMembersContacts

ClubMembersContacts is an application that allows users to keep track of their club members' and applicants' 
contact information.

Below is a summary of my contributions to the project.

* Code Contributed: [link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=saezenn&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)
* Enhancements Implemented:
  * Added Interview Feature to Applicants.
    * Wrote new classes for this feature : `InterviewTime`
    * Edited other related classes, like `Applicant`, `EditApplicantCommandParser`, `JsonAdressBookStorage`.
    * User can now add an interview time to an applicant.
    * Interview time will be parsed into Day, Month, Year, 24h Time format automatically.
    * Can be removed or edited.
    * Wrote tests and fixed bugs for all things related to Interview Time.
  * Delete Applicant
    * Wrote new classes for this feature : `DeleteApplicantCommand` and `DeleteApplicantCommandParser`.
    * User will be able to delete any applicant from the applicant list.
    * Wrote tests and fixed bugs for all things related to Delete Applicant.
  * Find Applicant/Member
    * Wrote new classes for this feature : `FindApplicantCommand`, `FindMemberCommand`, `FindApplicantCommandParser`, `FindMemberCommandParser`, `ApplicantContainsKeywordPredicate`, `MemberContainsKeywordPredicate`.
    * User will be able to find an applicant or member based on any specific keyword(s).
    * The UI will show a list of all applicant(s)/member(s) that have the specified keyword.
    * Wrote tests and fixed bugs for all things related to Delete Applicant.
    
* Contributions to the UG:
  * Reformatted the entire UG.
  * Added an application interface section.
  * Made screenshots for all command examples.
  * Gave team members a format to follow.
  * Wrote the section for `findApplicant`, `findMember`, `deleteApplicant`, `exit`, `help`, `clear`.
  * Wrote the introduction section.
  * Wrote the command summary section.
* Contributions to the DG:
  * Wrote sections for delete member/applicant.
  * Wrote sections for find member/applicant.
  * Drew Activity Diagrams and Sequence Diagrams for both sections.
  * Wrote use cases (MSS and extensions) for delete and find commands.
* Contributions to team-based tasks:
  * Regularly created new issues and assigned them to respective members.
  * Maintained issue tracker by closing finished issues.
  * Sorting bugs received from PE-D by labelling and distributing.
* Review/mentoring contributions:
  * Reviewed members PRs and gave comments about their code when needed.
  * Here are the PRs that I have reviewed :
    * [Pull Request #58](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/58)
    * [Pull Request #90](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/90)
    * [Pull Request #100](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/100)
    * [Pull Request #108](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/108)
    * [Pull Request #109](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/109)
    * [Pull Request #122](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/122)
    * [Pull Request #123](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/123)
    * [Pull Request #146](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/146)
    * [Pull Request #148](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/148)
    * [Pull Request #157](https://github.com/AY2324S1-CS2103T-W15-3/tp/pull/157)
    * and more.
* Contributions beyond the project team:
  * Gave reminders for team members in regards to some deadlines. (i.e PE-D)

