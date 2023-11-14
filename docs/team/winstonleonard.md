---
layout: page
title: Winston Leonard Prayonggo's Project Portfolio Page
---

### Project: TuitionConnect
TuitionConnect is a **desktop app** built for tutors and tutoring businesses to simplify the process of
administration and finance management, optimized for use via a **Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI).


**Code contributed:** Click here for [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=winstonleonard&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=WinstonLeonard&tabRepo=AY2324S1-CS2103T-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


**Enhancements implemented:**
  

  * Refactored the `add` and `edit` command.
    * Allowed tutees to take in `Subject`, `Day`, `Begin`, `End` fields. 
    * (Pull Requests [#72](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/72))


  * Added clashing schedules mechanism
    * Prevents the `add` and `edit` command from adding or editing tutees that will result in clashing schedules.
    * (Pull Requests [#93](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/93))
    

  * Implemented the `freeTime` command 
    * Command that finds free time in the user's schedule.
    * (Pull Requests [#121](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/121))


  * Write and fixing test cases:
      * Fixed the test cases for `AddCommandTest`, `EditCommandTest`, `AddCommandParserTest`, `EditCommandParserTest`, `AddressBookParserTest`. `PersonBuilder`, `PersonUtil`, `TypicalPerson`
      to support refactored `add` and `edit` command. (Pull Requests [#73](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/73))
      * Wrote test cases for `DurationTest`, `IntervalTest`, `IntervalBeginTest`, `IntervalEndTest`, `IntervalDayTest`, `TimeSlotTest`, `FreeTimeCommandTest`, `FreeTimeCommandParserTest` 
        to test the `freeTime` Command. (Pull Requests [#121](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/121))
      * Wrote test cases for clashing schedules for `add` and `edit` command (Pull Requests [#198](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/198))
  

**Contributions to the UG:**


  * Added Welcome Section, Introduction Section, Symbol Syntax Table. (Pull Requests [#207](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/207))
  * Added the Layout Section (Pull Requests [#216](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/216))
  * Wrote descriptions for `add` and `freeTime` (Pull Requests [#221](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/221))


**Contributions to the DG:**


* Modified Model Class Diagram. (Pull Requests [#192](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/192))
* Added description and Sequence Diagram for `freeTime` command (Pull Requests [#192](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/192))
* Added Activity Diagrams for `freeTime` and `edit` command (Pull Requests [#211](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/211))
* Wrote manual test cases for `add`, `freeTime`, `edit`, and also added a glossary. (Pull requests [#244](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/224))
* Wrote planned enhancements (Pull Requests [#232](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/232))


**Contributions to team-based tasks**:


* Enabling assertions (Pull Requests [#104](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/104))


**Review/Mentoring contributions:**


* Reviewed and offered suggestions for team member's pull requests.


**Contributions beyond the team project:**


* Assisted other teams in finding bugs during the [Practical Exam Dry Run](https://github.com/WinstonLeonard/ped/issues)
