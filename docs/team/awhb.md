---
layout: page
title: Hang Bin's Project Portfolio Page
---

### Project: NetworkBook

NetworkBook is a desktop contact book application that aims to help computing students and professionals from NUS to network with each other. The user interacts with it using a command-line-interface (CLI), and it has a graphical user interface (GUI) created with JavaFX.

#### Summary of Contributions

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=awhb&breakdown=true)


* **New feature**: Added the ability to undo/redo previous commands that altered contact data stored in the app and/or contacts displayed by the app (Pull requests [#145](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/145), [#169](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/169)).
  * What it does: Allows the user to undo all previous commands affecting contact data and/or displayed contacts one at a time. Preceding undo commands can be reversed by calling the redo command. 
  * Justification: This feature improves the product significantly because the user can make mistakes in commands and the app should provide a convenient way to rectify them. In addition, for commands that have altered the contacts displayed by the app, the undo/redo function offers a convenient alternative to revert the changes to filtering and sorting applied on the list of contacts displayed. 
  * Highlights: This new feature affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes not only to existing commands, but also the existing implementation of both the Model and NetworkBook classes, also demanding an in-depth analysis of current design implementation as well as design alternatives.
  * Credits: AB-3 Developer Guide, section for proposed implementation of undo/redo feature for the idea of facilitating the undo/redo mechanism through calling commands on a subclass of NetworkBook named VersionedNetworkBook. 

* **New feature**: Added the ability to add phone numbers to an existing contact (Pull request [#91](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/91)).
  * What it does: Allows the user to add another phone number to an existing contact that stores a list of phone numbers.
  * Justification: This feature improves the product significantly because the user can actually assign multiple phone numbers to a single contact. Not only that, they can do so incrementally, because the user can add phone numbers to an existing contact outside of creating the contact or modifying the entire contact, greatly increasing convenience.
  * Highlights: This new feature has a pre-requisite of enabling Person to possess multiple Phone values. Hence, refactoring Person to accept multiple Phone values, as well as modifying the testing to account for this, took up a considerable amount of time during the implementation.


* **Enhancements to existing features**:
  * Updated attributes of a Person to those relevant to NetworkBook (Pull request [#87](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/87)).
  * Modify CreateCommand to only require contact name during execution (Pull request [#89](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/89)).
  * Added testcases for FindCommand (Pull request [#99](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/99)).
  * Modify FindCommand to enable searching by a fragment of contact's name (Pull request [#102](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/102)).


* **Contributions to the UG**:
  * Added documentation for `create` and `add [/phone]` commands (Pull request [#34](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/34)).
  * Added documentation for `undo` and `redo` commands (Pull request [#169](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/169)).

* **Contributions to the DG**:
  * Updated the architecture subsection of the developer guide design section.
  * Added implementation details of the `create` feature (Pull request [#151](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/151)). 
  * Updated implementation details of the `undo/redo` feature.


* **Contributions to team-based tasks**:
  * Reformatted user guide headings (Pull request [#124](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/124)).
  * Write product overview for user guide (Pull request [#128](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/128)).
  * Reviewed and updated user guide to match command implementations (Pull request [#96](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/96)).

* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [#79](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/79), [#86](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/86), [#240](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/240)

* **Contributions beyond the project team**:
  * Bugs found in other team's products: [1](https://github.com/awhb/ped/issues/4), [2](https://github.com/awhb/ped/issues/6), [3](https://github.com/awhb/ped/issues/7), [4](https://github.com/awhb/ped/issues/9)
