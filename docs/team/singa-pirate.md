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
  * Implemented `ctrl-Z/Y` shortcuts to undo or redo previous commands
  * Implemented `ctrl-W` shortcut to exit program
  * Implemented `ctrl-S` shortcut to manually save data

* **Enhancements to existing features**:

  - Made the `index` prefix optional when editing or deleting multi-valued fields. If not specified, the default index is 1. (Pull request [#174](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/174))

* **Bugs fixed**:

  * Fixed issue where commands not affecting data storage cannot be executed when the app has no write permission to data file. (Pull request [#160](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/160)) 
  * Fixed issue where keyboard shortcuts do not work properly on macOS (Pull request [#254](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/254))

* **Code contributed**: [RepoSense report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=singa-pirate&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Singa-pirate&tabRepo=AY2324S1-CS2103T-T08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:

  * Managed release `v1.3` on GitHub

* **Documentation**:

  * User Guide:
    *  [#26](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/26) Refactored from AB3 descriptions and drafted documentation for v1.2 features
    *  [#132](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/132) Added command summary and FAQ sections in the user guide for v1.2
    *  [#175](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/175) Drafted documentation for v1.3 features
    *  [#244](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/244) Made the user guide more concise by formatting explanations of common terms

  - Developer Guide:
    - [#29](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/29/commits/1cb161408bbdcb1e941b21987bdc6a9759657ca4) Added user stories and non-functional requirements for v1.2 features
    - [#58](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/58) Added use cases and glossary for `add` command
    - [#105](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/105) Conducted quality assurance on the developer guide for v1.2
    - [#263](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/263) Added use cases for v1.3 features and updated class diagram for `Storage`

* **Community**:

  * PRs reviewed (with non-trivial review comments): [#34](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/34), [#88](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/88), [#91](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/91), [#92](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/92), [#122](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/122), [#152](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/152), [#161](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/161), [#162](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/162), [#164](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/164), [#173](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/173), [#235](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/235), [#237](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/237), [#245](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/245).
  * Reported bugs and suggestions for other teams in the class (examples: [bug report 1](https://github.com/Singa-pirate/ped/issues/2), [bug report 2](https://github.com/Singa-pirate/ped/issues/3), [bug report 3](https://github.com/Singa-pirate/ped/issues/5), [bug report 4](https://github.com/Singa-pirate/ped/issues/8))

