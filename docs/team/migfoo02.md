---
layout: page
title: Miguel's Project Portfolio Page
---

# Project: JobFindr

This portfolio provides an overview of my contributions to JobFindr.
## Overview

JobFindr is a desktop-based job application management application.

The inspiration for JobFindr came from the shared challenge faced by all members of our team - the daunting task of
organising and keeping track of the numerous job applications we submit. Recognising that this is a common experience
among NUS students, we felt a strong urge to develop a software solution that would make this process more manageable
for students like us.

JobFindr was born out of this necessity, offering a one-stop solution for recording and managing your job applications
effortlessly.

## Summary of Contributions

### Enhancements

1. Changed main field from `Role` to `Company`
    * **What it does**: Instead of a `Job` displaying the `Role` as the main field in a `JobCard`, it displays the
      `Company` instead.
    * **Justification**: After `v1.1` we noticed that the `Role` field was not as important as the `Company` field as
      users may apply to multiple roles that are similar in nature, but for different companies.
    * **Highlights**: This was my first time working with JavaFX, so it was a challenge to understand how the GUI changes based on the changes made in JavaFX. It did not take me long to pick up the basics of JavaFX and implement the necessary changes in `JobCard` as this was mainly a cosmetic change.


2. Added `JobType` field to `Job`
    * **What it does**: `JobType` is a field that represents a fixed list of job types that a user can choose from. Examples of job types include `internship`, `full-time`, and `contract`. As this is an optional field, users can choose to leave it blank.
    * **Justification**: This was a valuable addition to `Job` as it allows users to categorise and filter their jobs based on the type of job they are applying for.
    * **Highlights**: Implementing this functionality required changing the `Job` class to include the `JobType` field, and changing the `JobCard` class to display the `JobType` field. The challenging aspect was ensuring that the `JobType` field was displayed correctly in the `JobCard` class, as well as ensuring that the `JobType` field was saved and loaded correctly in the `Storage` class. 


3. Added the `Interview` class and its fields
   * **What it does**: The `Interview` class is a class that represents an interview that a user has been invited to. It contains the fields `InterviewDateTime`, `InterviewAddress`, and `InterviewType` 
   * **Justification**: This was a valuable addition to `Job` as it allows users to note down their interview details in a structured manner. 
   * **Highlights**: Implementing this functionality required creating the `Interview` class and its fields, and changing the `Job` class to include the `Interview` class. I referred to similar fields from `Job` when implementing the interview fields.
 

4. Added `InterviewListPanel` to the UI
   * **What it does**: The `InterviewListPanel` is a panel that displays the list of interviews associated with a job application. It is displayed when a user clicks on a job application in the `JobListPanel`.
   * **Justification**: This was a valuable addition to the UI as it allows users to view the interviews associated with a job application. 
   * **Highlights**: Implementing this functionality required creating the `InterviewListPanel` class and its associated FXML file. The challenging aspect was ensuring that the `InterviewListPanel` updates whenever an `interview` command is executed. As such, I modified the `CommandResult` class to track the type of command that was executed to update the `InterviewListPanel` accordingly.

### Contributions

* **Project management**:
  * Participated actively in meetings and discussions to provide feedback and suggestions on the project.
  * Helped to ensure that the team was on track to meet the deadlines for each milestone.
* **Documentation**:
    * User Guide (UG)
      * Added successful and failed examples for `add` and `edit` commands. (PR [#97](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/97))
      * Updated UI Mockups for all commands to reflect the latest UI changes. (PR [#123](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/123))
    * Developer Guide (DG)
      * Made major revisions to the current DG, adding important appendices for manual testing, requirements, effort and proposed enhancements. (Pr [#182](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/182))
      * Added documentation for the architecture and `add` command sections. Added descriptions for 7 proposed enhancements. (PR [#206](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/206))
* **Bug Fixes**:
    * Fixed bugs reported during PE dry run.
* **Community**:
    * PRs reviewed (with non-trivial review comments): [#55](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/55), [#58](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/58), [#103](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/103), [#126](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/126)

### Code Contributed

My code contributions can be found
here: [Reposense Report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=migfoo02&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=migfoo02&tabRepo=AY2324S1-CS2103T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)