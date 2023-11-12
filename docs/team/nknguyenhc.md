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
The link can be the person's social page or personal website.
The link regex accommodates for links that consist of domain, and optionally pathname and query parameters, while ensuring that most of the URL standards are not violated (which suggests the URL's invalidity).
The command adds the new link to the target person's current list of links.
Pull request: [#88](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/88)

* **Enhancement to an existing feature**: Edit a person's details.
Any contact detalls editable.
The edit feature makes effective use of the `UniqueList` class to enforce the uniqueness constraint on person's name within the contact list, and field values within the same list (e.g. unique phone numbers within a contact's phone list).
The edit feature also ensures the new value adheres to the constraint(s) of the field type.
The edit feature makes effective use of the `UniqueList` class to ensure the validity of the indices.
For e.g. `edit 1 /phone 12345678 /index 2` assigns the value of `12345678` to the phone number at index 2 in the phone list of contact at index 1.
Pull request: [#122](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/122)

* **New Feature**: Open a contact's link from the app.
The feature allows the user to easily to open a contact link with the  `open` command.
The code used to pop up the browser had to be customised for each of the three OS'es, Windows, Mac OS and Ubuntu.
The command pops up the user's default browser directed to the link.
Pull request: [#152](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/152)

* **New Feature**: Open a window for a new email to a contact's email address.
The feature allows the user to easily start sending an email to a contact with the `email` command.
The code used to pop up the browser had to be customised for each of the three OS'es, Windows, Mac OS and Ubuntu.
The command pops up the user's default mailbox application, with a new email draft created and the recipient prefilled with the contact's email address.
Pull request: [#161](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/161)

* **Design implementation**: Generalised `UniquePersonList` to `UniqueList`, which is also used in the implementation of many fields of the `Person` class, including phone, email, link, course, specialisation and tag.
The `UniqueList` class is a generic class that allows identity checking of the generic type `T`, by enforcing that `T` implements the `Identifiable<T>` interface.
Pull request: [#79](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/79)

* **Code refactoring**: Refactored the entire code base, changing the package name from `seedu.address` to `networkbook`.
This involves exhaustive changes in class names and test case names.
Pull request: [#77](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/77)

* **Bug fix**: Ensured that any kind of malformed JSON and lack of write permission in the data file, preference file, config file and log file do not cause the application to crash upon launch.
This involves a null check on objects of classes that are convertible from JSON,
and a duplicate check on any list in the data file that are supposed to conform to the uniqueness constraint.
Pull requests: [#235](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/235) [#237](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/237)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=nknguyenhc&tabRepo=AY2324S1-CS2103T-T08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed all releases `v1.1`, `v1.2`, `v1.3`, `v1.4` on GitHub.
  * Created all milestones and sub-milestones, `v1.1`, `v1.2`, `v1.2b`, `v1.3`, `v1.3b`, `v1.4`, `v1.4b`.

* **Documentation**:
  * User Guide:
    * Added documentation for the following features (pull request: [#57](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/57)):
      * Added an email to a person
      * Added a link to a person
      * Edited details of a person
    * Ensured consistency in the feature section. Pull request: [#182](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/182)
  * Developer Guide:
    * Added user stories, use cases and non-functional requirements
    for features related to accessibility. Pull request: [#24](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/24)
    * Added a glossary to define relevant terms used in the Developer Guide.
    * Added documentation on the implementation the edit feature.
    This includes the sequence diagrams on the edit command and explanation on the implementation of the edit command.
    This also includes the use cases related to edit command.
    Pull request: [#138](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/138) and the one below
    * Refined documentation on the model package. Pull request: [#238](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/238)
    * Update planned enhancements. Pull request: [#248](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/248)

* **Community**:
  * Conducted team weekly meetings.
  * Did task division to ensure equal share of workload and team's progress.
  * Ensured that team members follow the internal deadlines.
    * Conducted thorough review of team's dynamics in `v1.2` postmortem meeting to ensure everyone is on track, upon late work submissions in `v1.2`.
  * Reviews on major PRs (with changes in functional code):
    * Add `AddCommand` class: [#82](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/82)
    * Add `Priority` class and functionality: [#85](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/85)
    * Update attributes of `Person` class: [#87](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/87)
    * Add contact sorting feature: [#94](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/94)
    * Edit functionalities of `Tag` class: [#97](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/97)
    * Add feature of filtering by various fields: [#141](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/141) [#167](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/167)
    * Enhancement of GUI: [#142](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/142) [#166](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/166)
    * Add undo and redo commands: [#145](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/145) [#178](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/178)
    * Delete command: [#146](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/146)
    * Add keyboard shortcut functionalities: [#160](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/160)
    * Enhance course-related features: [#165](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/165)
  * Completed teammate's task when he could not finish in time.
    * Add functionalities related to specialisation and course attributes of a contact: [#95](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/95)
  * Produce bug reports for PE-D: [#256](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/256) [#258](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/258) [#272](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/272) [#276](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/276) [#282](https://github.com/AY2324S1-CS2103T-T17-3/tp/issues/282)

* **Tools**:
  * Set up team member's privileges in the repository.
  * Set up github pages for pages deployment.
  * Added JavaCI and Codecov for pull request checks. Added branch protection rules.
