---
layout: page
title: Luo Jiale's Project Portfolio Page
---

### Project: NetworkBook

NetworkBook is a desktop contact book application. It aims to help computing students and professionals from NUS to network with each other. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Command format improvements from AB3 project

  * Changed command prefix format (Pull request [#80](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/80))
  * Refactored `add` command to `create` command, in order to differentiate from our new `add` command (Pull request [#81](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/81))
  * Implemented new `add` command (Pull request [#82](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/82))

* **New Feature**: Assign priority level to a contact

  * Implemented `priority` field of contact that can be managed by user (Pull request [#85](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/85))

* **New Feature**: Attach tag to a contact

  * Implemented `tag` field of contact that can be assigned by user (Pull request [#97](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/97))

* **New Feature**: Delete some information about a contact

  * Implemented new `delete` command syntax to remove an entry from a field of information about a contact (Pull request [#146](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/146))

* **New Feature**: Keyboard shortcuts (Pull request [#160](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/160))

  * Implemented `ctrl-F/N/G/U/R` shortcuts to auto-fill command preamble
  * Implemented `Up/Down arrow key` shortcuts to navigate command history
  * Implemented `ctrl-Z/Y` shortcuts to undo or redo change to data
  * Implemented `ctrl-W` shortcut to exit program
  * Implemented `ctrl-S` shortcut to save data

* **Enhancements to existing features**:

  - Made the `index` prefix optional when editing or deleting multi-valued fields. If not specified, the default index is 1. (Pull request [#174](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/174))

* **Bugs fixed**:

  * Fixed issue where commands not affecting data storage cannot be executed when the app has no write permission to data file. (Pull request [#160](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/160)) 

* **Code contributed**: [RepoSense report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=singa-pirate&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Singa-pirate&tabRepo=AY2324S1-CS2103T-T08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:

  * Managed releases `v...`on GitHub

* **Documentation**:

  * User Guide:
    *  [\#..]()

  - Developer Guide:
    - [\#..]()

* **Community**:

  * PRs reviewed (with non-trivial review comments): [#34](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/34), [#88](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/88), [#91](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/91), [#92](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/92), [#122](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/122), [#152](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/152), [#161](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/161), [#162](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/162), [#164](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/164), [#173](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/173), [#235](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/235).
  * Reported bugs and suggestions for other teams in the class (examples: [bug report 1](https://github.com/Singa-pirate/ped/issues/2), [bug report 2](https://github.com/Singa-pirate/ped/issues/3), [bug report 3](https://github.com/Singa-pirate/ped/issues/5), [bug report 4](https://github.com/Singa-pirate/ped/issues/8))

