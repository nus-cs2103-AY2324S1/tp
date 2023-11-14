---
layout: page
title: Nguyen's Project Portfolio Page
---

### Project: NetworkBook

NetworkBook is a desktop contact book application. It aims to help computing students and professionals from NUS to network with each other. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Add an email to a person.
The command adds the new email to the target person's current list of emails.
The email regex ensures that `@` is present, and at least one `.` is present in the domain part.
Pull request: [#86](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/86)

* **New Feature**: Add a link to a person.
The link regex accommodates for links that consist of domain, and optionally pathname and query parameters, while ensuring that the provided link conforms to most of the URL standards (which suggests the URL's invalidity).
Pull request: [#88](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/88)

* **Enhancement to an existing feature**: Edit a person's details.
Any contact details are editable.
The edit feature makes effective use of the `UniqueList` class to ensure the validity of the indices and for duplicate checking.
Pull request: [#122](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/122)

* **New Feature**: Open a contact's link from the app.
The feature allows the user to easily to open a contact link with the  `open` command.
Pull request: [#152](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/152)

* **New Feature**: Open a window for a new email to a contact's email address.
The feature allows the user to easily start sending an email to a contact with the `email` command.
Pull request: [#161](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/161)

* **Design implementation**: Generalised `UniquePersonList` to `UniqueList`, which is also used in the implementation of many fields of the `Person` class, including phone, email, link, course, specialisation and tag.
Pull request: [#79](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/79)

* **Code refactoring**: Refactored the entire code base, changing the package name from `seedu.address` to `networkbook`.
This involves exhaustive changes in class names and test case names.
Pull request: [#77](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/77)

* **Bug fix**: Ensured that any kind of malformed JSON and lack of write permission in the data file, preference file, config file and log file do not cause the application to crash upon launch.
Pull requests: [#235](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/235) [#237](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/237)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=nknguyenhc&tabRepo=AY2324S1-CS2103T-T08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed all releases `v1.1`, `v1.2`, `v1.3`, `v1.4` on GitHub.
  * Created all milestones and sub-milestones, `v1.1`, `v1.2`, `v1.2b`, `v1.3`, `v1.3b`, `v1.4`, `v1.4b`.

* **Documentation**:
  * User Guide:
    * Added documentation for the following features (pull request: [#57](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/57)): add email, add link, edit email
    * Updated table for common fields used. Pull request: [#284](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/284)
  * Developer Guide:
    * Added user stories, use cases and non-functional requirements
    for features related to accessibility. Pull request: [#24](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/24)
    * Added a glossary to define relevant terms used in the Developer Guide.
    * Added documentation on the implementation the edit feature. Pull request: [#138](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/138) and the one below
    * Refined documentation on the model package. Pull request: [#238](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/238)
    * Added documentation on the implementation of filter feature. Pull request: [#297](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/297)
    * Updated planned enhancements. Pull requests: [#248](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/248), [#289](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/289)
    * Updated all use cases to follow current implementation. Pull request: [#287](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/287)
    * Updated manual testing section. Pull request: [#293](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/293)

* **Community**:
  * Conducted team weekly meetings.
  * Ensured that team members follow the internal deadlines.
    * Conducted thorough review of team's dynamics in `v1.2` postmortem meeting to ensure everyone is on track, upon late work submissions in `v1.2`.
  * Reviews on major PRs (with changes in functional code): [#82](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/82), [#85](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/85), [#87](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/87), [#94](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/94), [#97](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/97), [#141](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/141), [#167](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/167), [#142](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/142), [#166](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/166), [#145](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/145), [#178](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/178), [#146](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/146), [#160](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/160), [#165](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/165), [#269](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/269)
  * Completed teammate's task when he could not finish in time: [#95](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/95)
  * Produce bug reports for PE-D: [#256](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/256) [#258](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/258) [#272](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/272) [#276](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/276) [#282](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/282)
  * Produce bug reports for team T08-1: [repo link](https://github.com/nknguyenhc/Medi-Contrived/issues)

* **Tools**:
  * Set up team member's privileges in the repository.
  * Set up github pages for pages deployment.
  * Added JavaCI and Codecov for pull request checks. Added branch protection rules.
