---
layout: page
title: Tan Kerway's portfolio
---

### Project: InterviewHub

InterviewHub is a desktop application that helps engineering hiring managers to manage applicants and interviews. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 18 kLoC.

#### Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kiwibang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=kiwibang&tabRepo=AY2324S1-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * New feature: Added the ability to accept multiple time formats. (PR: [#53](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/53))
      * What it does: Allows the user to enter a time in different formats
      * Justification: Different individuals have different ways of entering dates. This feature increases the flexibility of the command, allowing the user to type in inputs without the input being rejected, leading to better UX.
      * Highlights: Creating one's own time parser methods requires in-depth knowledge of the Java LocalDateTime class and its utility methods. Requires many iterations to get a fully functional parser which can accept multiple time strings in different formats.

  * New Feature: `list-freetime DATE` command (PR: [#121](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/121))
    * What it does: Allows the user to list all the blocks of time from 9am to 5pm on a given date. 
    * Justification: The user might want to see, in a single command, when exactly they are free on a particular work day, so that they can schedule other activities. This is much more concise than looking at a messy calendar to check when they are free manually.

  * New Feature: `list-i-today` command (PR: [#120](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/120))
    * What it does: Allows the user to list all the interviews that they have scheduled on the day the command is executed
    * Justification: This feature will allow the user to see upcoming interviews on the day that the command was executed in 1 command, thus eliminating the need for the user to consult a potentially messy calendar to attend to upcoming interviews

  * New Feature: `sort-time` command (PR: [#125](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/125))
    * What it does: Allows the user to list all the interviews that they have scheduled so far, in chronological ascending order
    * Justification: The user might want to see all the interviews that scheduled and are coming soon, so that they can prepare for them accordingly

  * Enhancement: Add `Time` class to represent parsed time strings (PR: [#149](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/149))
    * What it does: Place the LocalDateTime object into a wrapper class and move all related methods into it 
    * Justification: Improves encapsulation by wrapping all related fields and methods related to reading and writing time into 1 dedicated class
    * Highlights: Requires extensive modification of the existing codebase so that `Time` objects are used instead of `LocalDateTime` objects

* **Contributions to the UG**:
    * Created and added the `List of accepted date formats`
    * Added complete documentation for the following features: `list-freetime DATE`, `list-i-done`, `list-i-not-done`, `sort-time`
    * Added screenshots for the following features: `list-i-done`, `list-i-not-done`, `sort-time`
* **Contributions to the DG**:
    * Added description, implementation, sequence diagrams, execution process, and design considerations for the following sections: `parseDate API`, `List all free timing for a given day feature`, `List interviews done/not done feature`, `Sort interview feature`
    * Added skeleton for the section: `Appendix: Instructions for manual testing`
    * Added test cases for the following commands in the section `Appendix: Instructions for manual testing`: `Listing all free timing for the given day`, `Listing all interviews for today`, `Listing all completed interview`, `Listing all incomplete interview`, and `Sorting the interview list by start-time`
* **Contributions to team-based tasks**:
    * Maintain the issue tracker (e.g. assign priority levels to tasks, assign individuals to issues)
* **Review/mentoring contributions**:
    * Examples of team PRs reviewed: [#31](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/31), [#34](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/34), [#58](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/58)
    * Helped team members with testing and debugging their code.
* **Contributions beyond the project team**:
    * Other team PR reviewed: [CS2103T-W08-1 #42](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/42) 
    * Asked for help and then gave back to the community: [#159](https://github.com/nus-cs2103-AY2324S1/forum/issues/159), [#161](https://github.com/nus-cs2103-AY2324S1/forum/issues/161)
    * Contributed to forum discussions: [#120](https://github.com/nus-cs2103-AY2324S1/forum/issues/120#issuecomment-1710941098), [#285](https://github.com/nus-cs2103-AY2324S1/forum/issues/285#issuecomment-1774804526)
