---
    layout: default.md
    title: "Wang Zhi Wren's Project Portfolio Page"
---

### Project: HealthSync

HealthSync is an application designed to streamline the workflow of healthcare center frontdesk staff
by allowing them to efficiently register and access patient information within 2-3 minutes.
It offers a user-friendly platform that enhances patient management, appointment scheduling,
and health record retrieval, ultimately improving care delivery and saving valuable time for healthcare professionals.

Given below are my contributions to the project.

* **New Feature**:
  * `Appointment` field implementation, including the parsing and handling logic of
    temporal data.
    * What it does: allows the user to store appointment data directly in a verifiable way,
      without needing them to verify their input themselves.
    * Justification: Temporal data like appointments are very commonly handled by our target
      audience. By allowing them to flexibly input their time, and storing it in a particular manner
      that the engine can understand, we can more easily make use of this field to improve our user's
      experience.
    * Highlights: This feature required an understanding of how temporal data is parsed by the Java engine,
      and how it can be made more flexible to parse and interpret on the backend.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T14-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=WZWren&tabRepo=AY2324S1-CS2103T-T14-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Enhanced `add` feature to allow for addition of new fields added by the team.
  * Expanded `find` feature to allow for search by name, IC and Appointment.
  * Expanded `find` feature to compose multiple search fields through the use of
    the `Predicate` functional interface class.
    * What it does: allows the user to customize their search terms when using the `find` command.
    * Justification: `find` should generally be as flexible as possible to allow users to filter through their
      search data easier. Implementing this feature in this way allows us to scale this feature up when we want to
      expand our search terms.
    * Highlights: Creating `Predicate` classes as concrete classes allow us to more accurately compare 2 differently
      composed search terms and manage equalities easier. In particular, a `CompositePredicate` class was created
      through the use of `Sets`, which required an understanding of how hashing works for a `Predicate` interface and
      how it is interpreted for lambdas.

* **Documentation for UG**:
    * Added Glossary Section for UG.
    * Hyperlinked relevant sections in UG to the Glossary.

* **Documentation for DG**:
    * Added implementation details for `Appointment`, `Predicate` and `Find` features.

* **Project management**:
  * Managed milestones `v1.2` and `v1.3` to keep it close to the deadline.
  * Performed troubleshooting in workflow for projects. Examples include:
    * [Troubleshooting CodeCov](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/72)
    * [Troubleshooting jackson json library with @WeeeHung](https://github.com/nus-cs2103-AY2324S1/forum/issues/253)
  * Performed significant in-depth code review on PRs of my peers. Example PRs include:
    * [Edit Command Enhancement by @kanna-1](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/77)
    * [Delete Command Initial Enhancement by @nubnubyas](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/75)
    * [Delete Command Refactoring by @nubnubyas](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/129)
    * [Log Command Implementation by @hyc17003](https://github.com/AY2324S1-CS2103T-T14-3/tp/pull/117)

* **Community**:
    * Occasionally helped other members of the course in responding in the forum.
      Examples include:
      * [JavaFX Config Errors by @JasonRay168](https://github.com/nus-cs2103-AY2324S1/forum/issues/122)
      * [Relative Path Issues by @zacwong2151](https://github.com/nus-cs2103-AY2324S1/forum/issues/153)
