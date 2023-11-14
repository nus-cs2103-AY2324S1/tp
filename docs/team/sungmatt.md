---
layout: page
title: Matthew Sung's Project Portfolio Page
---

### Project: ManageHR

ManageHR is your all-in-one solution for seamless Human Resources management. Say goodbye to paperwork, spreadsheets, and administrative headaches. Our cutting-edge software empowers HR professionals to focus on what truly matters – your people.

Given below are my contributions to the project.

* **Enhanced Feature**: Added the ability to serve help pages for each command.
  * What it does: Allows users to clarify and identify syntax required for each command. The help function displays syntax for each command, and provides an example to allow users to easily copy and modify to suit their needs.
  * Justification: This feature improves the product significantly because a user is now able to use ManageHR in an offline environment, without requiring the need to refer to the online User Guide. This also allows users to quickly identify the syntax required for commands, and streamlines the learning and quick reference process.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required a thorough analysis and re-designing of the Help Window, as well as how it is to be displayed to prevent UI scaling issues. At the same time, this provides a canvas for future commands to follow, with regards to good user-centric design and structure.


* **Enhanced Feature**: Added a field to ManageHR to track leave days available.
  * What it does: Allows users to track the leave allowances of employees. This is then fed into the UI, providing a quick display of their compensation package, and liabilities to the company.
  * Justification: This feature allows HR Managers to easily see the leave allowances of employees, and identify if certain employees are costing large amounts due to these additional compensation packages they are provided.
  * Collaboration: Together with the filter command, we are able to track employees with leave days above a certain amount, and easily identify the high-cost emplyees.
  * Highlights: The addition of a new field presented a lot of changes to both our sample datasets, as well as the cohesiveness of the project. Standard CRUD functions had to be worked with, in order to provide support for this new field. This meant a large amount of updating for each individual command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=SungMatt&tabRepo=AY2324S1-CS2103-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Primary manager of the team repo, provides guidance to teammates on the proper use of Git
  * Git management and workflow compliance -- allows for good oversight and cross-checking of PRs, along with good SE practices.

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage from 70% to 75% (Pull requests [\#135](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/135), [\#138](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/138),  [\#66](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/66))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `help` [\#74](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/74)z
    * Modified and beautified command summary, and add outcome tables for commands [\#124](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/124)
    * Added quick start sample references [\#139](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/139), as well as fix issues regarding the samples [\#132](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/132).
  * Developer Guide:
    * Added implementation details of the `help` feature.
    * Added implementation details of the `delete` feature.

* **Contributions to Team Tasks**:
  * Primary manager of the team repo. Set up the repo for use for the entire team, including the configuring and launch of the website.
  * Setting up other tools, see [tools](#Tools) section below.
  * Maintenance of the group GitHub issue tracker, ensuring milestones are properly tagged and closed.
  
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#136](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/136), [\#12](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/12#issuecomment-1747115616), [\#45](https://github.com/AY2324S1-CS2103-T16-1/tp/issues/45#issuecomment-1770368541)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/36), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/148), [3](https://github.com/nus-cs2103-AY2324S1/forum/issues/200), [4](https://github.com/nus-cs2103-AY2324S1/forum/issues/183))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S1-CS2103T-T08-3/tp/issues/215), [2](https://github.com/AY2324S1-CS2103T-T08-3/tp/issues/210), [3](https://github.com/AY2324S1-CS2103T-T08-3/tp/issues/214))

* **Tools**:
  * Set up Jekyll for the team website([\#7](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/7)
  * Integrated a new Github plugin (CircleCI) to the team repo
