---
  layout: default.md
  title: "Conrad Soon's Project Portfolio Page"
---

# Project: CampusConnect
**CampusConnect** is a desktop application built for **NUS students** living on campus to help them **stay organised**, **stay connected**, and **make the on-campus experience at NUS stress-free**. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

# Summary of Contributions

## Code Contributed

Refer to this [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=conradsoon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=zoom&zA=conradsoon&zR=AY2324S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&zACS=201.41666666666666&zS=2023-09-22&zFS=conradsoon&zU=2023-11-11&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

## Enhancements Implemented

* **New Notes Feature**: Implemented the Notes feature for CampusConnect (Pull requests [#69](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/69), [#200](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/200)).
  * What it does: The notes feature is used for storing quick notes about a contact
  * Details: The notes feature implements a new GUI feature in the form of the Notes window, but also allows users to continue using the CLI to add notes. The notes feature also allows users to add notes to a contact, view all notes for a contact, and delete notes for a contact.
* **Added UI Test Coverage**: Implemented UI testing from scratch with TestFX for the Notes feature (Pull requests [#69](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/69))
  * Details: We were unable to reliably hit test coverage requirements due to the lack of UI testing. I implemented UI testing from scratch with TestFX for the Notes feature, and this allowed us to hit the test coverage requirements.
* **Bug Fixing**: Fixed PE-D bugs for the `notes` command (Pull requests [#206](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/206))
  * Details: During the PE-D, some teams found several bugs with the `note` command. I worked on resolving several of these bugs, having to change various UI components and some core logic in order to do so.

## Contributions to the User Guide

* Managed User Guide section for the notes system (Pull requests [#99](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/99), [#138](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/138), [#151](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/151))

## Contributions to the Developer Guide

* Managed Developer Guide section for the notes system implementation (Pull request [#99](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/99), [#128](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/128))

## Contributions to Team-Based Tasks

* Set up UI testing with TestFX to increase test coverage by testing UI elements as well
* Helped reduce some security risks by implementing ASCII-only for the entire project ([#148](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/148))
* Proactively searched for edge-cases and raised them up as issues for future discussions (e.g. [#145](https://github.com/AY2324S1-CS2103T-T13-2/tp/issues/145), [#144](https://github.com/AY2324S1-CS2103T-T13-2/tp/issues/144), [#147](https://github.com/AY2324S1-CS2103T-T13-2/tp/issues/147))
* Debugged PE-D bugs ([#206](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/206), [part of #213](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/213))

## Review / Mentoring Contributions

* Pull requests reviewed: 23 [(Github)](https://github.com/AY2324S1-CS2103T-T13-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aconradsoon+)
* Reviews where I found bugs
  * [#75](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/75): Here, I found a bug where the `edit` command failed even though the user input was valid. I found the source of the bug, and suggested a fix, and possible test cases to test the fix.
  * [#89](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/89): Here, I found a bug where valid payment amounts were not being accepted. I found the source of the bug, and suggested a fix, and possible test cases to test the fix.
* Reviews where I found documentation errors
  * [#159](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/159): Here, I found a unindented line which would have caused our markdown to render incorrectly.

## Contributions Beyond the Project Team

* Wrote a [TMMOAI-Reviewer](https://github.com/conradsoon/tmmoai-reviewer), a quick script to evaluate Java code in a repo for code quality, coding style violations, etc, with GPT3.5. It might be useful for future iterations of CS2103T!
* Submitted 14 bug reports to team [CS2103T-W16-3](https://github.com/AY2324S1-CS2103T-W16-3/tp/issues?q=is%3Aissue+is%3Aclosed+Tester-C), where I also suggested the fix/better ways of structuring code!
