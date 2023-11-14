---
layout: page
title: Armando Jovan Kusuma's Project Portfolio Page
---

### Project: TuitionConnect
TuitionConnect is a **desktop app** built for tutors and tutoring businesses to simplify the process of
administration and finance management, optimized for use via a **Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI).


**Code contributed:** Click here for [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jovkusuma&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=jovkusuma&tabRepo=AY2324S1-CS2103T-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).


**Enhancements implemented:**

* Refactored the `add` and `edit` command.
  * Remove tags as parameter for both `add` and `edit` command.
  * (Pull Requests [#90](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/90))

* Enhance the `find` command
  * Allow users to find tutees using partial keywords.
  * Allow users to find by name and/or subject to find for tutees.
  * (Pull Requests [#68](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/68), [#127](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/127))

* Enforce that `begin` time should strictly be before `end` time.
  * Restrict users to only input `begin` times which are before `end` times. 
  * (Pull Requests [#111](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/111))

* Write and fixing test cases:
  * Fixed the test cases for `StringUtilTest`, `AddressBookParserTest`, `FindCommandTest`, `FindCommandParserTest`, `NameContainsKeywordsPredicateTest`
    to support refactored `find` command (Pull Requests [#68](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/68),[#127](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/127)).
  * Fixed the test cases for `AddCommandTest`, `EditCommandTest` to support the enforcement of begin time less than end time (Pull Requests [#111](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/111)).
  * Wrote test cases for `SubjectContainsKeywordsPredicateTest`, `NameSubjectPredicateTest`
    to test the `find` Command (Pull Requests [#127](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/127), [#199](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/199)).

**Contributions to the UG:**

* Added `Using this guide` and `Glossary` sections (Pull Requests [#209](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/209)).
* Wrote features and descriptions for `help`, `find`, `edit` and `delete` (Pull Requests [#235](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/235)).


**Contributions to the DG:**

* Added use cases for `add`, `edit` and `find` commands (Pull Requests [#102](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/102), [#210](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/210)).
* Added description and sequence diagram for `find` and `edit` commands (Pull Requests [#197](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/197)).
* Wrote manual test cases for `find` command (Pull requests [#234](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/234)).
* Wrote planned enhancements (Pull Requests [#210](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/210)).


**Contributions to team-based tasks**:


* Updating config.yml (Pull Requests [#53](https://github.com/AY2324S1-CS2103T-F10-4/tp/pull/53)).


**Review/Mentoring contributions:**

* Reviewed a total of 31 PRs.
* Reviewed and offered suggestions for team member's pull requests.


**Contributions beyond the team project:**

* Assisted other teams and reported 9 bugs during the [Practical Exam Dry Run](https://github.com/jovkusuma/ped/issues).
