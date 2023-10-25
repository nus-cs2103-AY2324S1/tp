---
layout: page
title: JasonLCY-Temp Project Portfolio Page
---

### Project: InterviewHub

InterviewHub is a CLI-focused desktop application for Tech/Engineering hiring managers to assist them
in keeping track of their pool of interviewees, job openings and related information.
It has a GUI created with JavaFX. It is written in Java, and has >10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Interview
  * What it does: Allows the user to perform CRUD operations for an Interview object
  * Justification: The basic CRUD features of `Interview` serves as the crucial foundation of our product,
alongside AB3's Person which was adapted into Applicant by Jing Jie. Most of the code for Interview was implemented by me,
adapting the frame of AB3's Person class CRUD classes. Implementing it was a lot more challenging than initially expected
due to how many additional classes (~20 associated classes had to be edited, some a lot more heavily than others, 
to handle the `Interview` object properly through all levels of abstraction - From the stored Json datafile to the ObservableList
used to display the data on the UI).

* **New Feature**: PLACEHOLDER
  * What it does: PLACEHOLDER
  * Justification: PLACEHOLDER


* **Code contributed**: [tP Code Dashboard summary](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=JasonLCY-Temp&tabRepo=AY2324S1-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
  * Created the organization and team on GitHub
  * Created the team repo on GitHub (including setting up `CodeCov` CI)
  * Kept track of deadlines (internal and external) and performed issue tracking & scheduling for milestone `v1.1 and v1.2`
  * Performed standardization for GitHub issues (Task Tracker) for `v1.1 - v1.3`
  * Enabled assertions for the team repo
  * Updated codecov global YAML to be more permissive with test case coverage decrease


* **Documentation**:
  * User Guide:
    * PLACEHOLDER
  * Developer Guide:
    * Use Cases for v1.1
  * Removed most traces of AB3 from the markdown pages, UG and DG in `v1.1`


* **PRs reviewed**:
  * Git conventions and GitHub usage tips/advice: 
    [#19](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/19)
    [#20](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/20)

  * Coding standards tips/advice:
    [#64](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/64)
    [#77](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/77)

  * General quality checks: 
    [#27](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/27)


* **Community Contribution**:
  * Point out (potentially) confusing instructions for clarification 
    [#236](https://github.com/nus-cs2103-AY2324S1/forum/issues/236)