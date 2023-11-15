---
  layout: default.md
  title: "Calvin Fong's Project Portfolio Page"
---

# Project: CampusConnect

**CampusConnect** is a desktop application built for **NUS students** living on campus to help them **stay organised**, **stay connected**, and **make the on-campus experience at NUS stress-free**. The user interacts with it using a CLI, and it has a GUI created with JavaFX. 

Given below are my contributions to the project.

# Summary of Contributions

## Code Contributed

Refer to this [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lordidiot&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lordidiot&tabRepo=AY2324S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Enhancements Implemented

* **New Feature**: Implemented the Notification system for CampusConnect (Pull requests [#43](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/43), [#200](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/200)).
  * What it does: The notification system is used for our birthday notifications system that will prompt the user when a contact's birthday is nearing
  * Details: It's written in a generic way so that future notifications and events (other than birthdays) can be added to CampusConnect without much effort in the future
* **Test Coverage**: Implemented tests for the new features added during v1.2 (Pull requests [#67](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/67), [#68](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/68))
  * Details: Given the rush of the v1.2 deadline, we pushed some features without enough tests, this affected our test coverage and therefore reduced the potential reliability of our features. In v1.2b I wrote tests for the *addalt command* and the *emergency tag system*, fixing the bugs found by the new tests as well.
* **Bug Fixing**: Fixed PE-D bugs for the `updatephoto` command (Pull requests [#207](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/207), [#213](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/213))
  * Details: During the PE-D, there were various bugs identified due to the current implementation of our `uploadphoto` command. I worked on resolving these bugs, and changed some of the existing implementation to align with the design principles for CampusConnect.

## Contributions to the User Guide

* Managed User Guide section for the notification system (Pull requests [#87](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/87), [#135](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/135))
* Worked on styling and table of contents (Pull request [#137](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/137))
* Fixed PE-D User Guide errors (Pull requests [#201](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/201), [#204](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/204))

## Contributions to the Developer Guide

* Managed Developer Guide section for the notification system implementation (Pull request [#85](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/85))
* Fix errors (Pull request [#126](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/126))
* Added usecases (Pull request [#157](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/157))

## Contributions to Team-Based Tasks

* Designed initial mock-up of CampusConnect (Pull request [#18](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/18))
* Managed [v1.2b release](https://github.com/AY2324S1-CS2103T-T13-2/tp/releases/tag/v1.2b)

## Review / Mentoring Contributions

* Pull requests reviewed: 29 [(Github)](https://github.com/AY2324S1-CS2103T-T13-2/tp/pulls?q=is%3Apr+reviewed-by%3Alordidiot+)
  * Selected PRs reviewed with non-trivial comments: [#56](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/56), [#65](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/65), [#69](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/69), [#77](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/77), [#140](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/140)

## Contributions Beyond the Project Team

* Found and reported 5 high quality bug reports to team [CS2103T-F10-1](https://github.com/AY2324S1-CS2103T-F10-1/tp/issues?q=is%3Aissue+%5BPE-D%5D%5BTester+B%5D)
