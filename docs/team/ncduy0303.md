---
layout: page
title: Nguyen Cao Duy's Project Portfolio Page
---

### Project: Spend n Split

Spend n Split (SnS) is a **desktop app for managing expense from contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SnS can get your contact expense management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added `Transaction` class ([\#66](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/66), [\#76](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/76), [\#79](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/79))
  * What it does: provides an internal representation of a transaction in the application, with necessary fields and methods to support the application's features.
  * Justification: This feature is foundational to support all the application's transaction-related features.
  * Highlights: This feature required an in-depth analysis of what fields should be included in the `Transaction` class, and how should it interacts with the `Person` class since each `Transaction` object is associated with some `Person` objects.
  * Credits: referenced from AddressBook Level 3's `Person` class.

* **New Feature**: Added `listTransaction` command ([\#81](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/81))
  * What it does: allows the user to view the transactions based on the name of people involved in the transaction.
  * Justification: user can store a lot of transactions with many people, and it is useful to be able to view specific transactions based on some given criteria.
  * Credits: referenced from AddressBook Level 3's `list` and `find` commands.

* **New Feature**: Added `deleteTransaction` command ([\#106](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/106))
  * What it does: allows the user to delete a transaction from the transaction list.
  * Justification: user can delete transactions that are no longer relevant or were created by mistake.
  * Credits: referenced from AddressBook Level 3's `delete` command.

* **New Feature**: Added `duplicateTransaction` command ([\#106](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/106))
  * What it does: allows the user to create a new transaction based on an existing transaction with optional changes.
  * Justification: user might have some recurring transactions or transactions with similar details, and it is convenient to be able to create a new transaction based on an existing transaction.
  * Credits: referenced from AddressBook Level 3's `add` and `edit` commands.

* **Code Refactoring**: Refactor `Descriptor` class ([\#304](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/304))
  * What it does: abstract a new `Descriptor` class that stores the parameters of a command, linking between some `Command` classes and their respective `Parser` classes.
  * Justification: improves code quality and maintainability.

* **Code Refactoring**: Refactor `listPerson` command ([\#192](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/192))
  * What it does: allows the user to view specific people based on keywords, or view all people in the list if no keywords are provided.
  * Justification: user can now have a single command to view people in the list, instead of having to use two separate commands `list` and `find`.
  * Credits: referenced from AddressBook Level 3's `list` and `find` commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ncduy0303&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Project management**:
  * Managed releases `v1.3` - `v1.4` (3 releases) on GitHub
  * Managed milestones `v1.1` - `v1.4` (6 milestones) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `deleteTransaction`, `listTransaction`, `listPerson` ([\#178](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/178))
    * Added screenshots for all features ([\#341](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/341))
  * Developer Guide:
    * Added implementation details of the all person-related features ([\#328](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/328))
    * Added implementation details of the Ui component and keyboard navigation feature ([\#355](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/355)) 

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#112](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/112), [\#155](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/155), [\#222](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/222), [\#329](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/329), [\#333](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/333), [\#334](https://github.com/AY2324S1-CS2103T-W17-3/tp/pull/334)

* **Tools**:
  * Set up team repo with access control, branch protection, and page deployment
  * Added Java CI and Codecov with GitHub Actions
