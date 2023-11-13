---
  layout: default.md
  title: "Goh Ler Xuan's Project Portfolio Page"
---

### Project: ProjectPRO

ProjectPRO is a desktop application designed to help university students organize their projects. ProjectPRO is optimized for use via a Command Line Interface (CLI) while preserving the advantages of maintaining an attractive user interface. Utilizing simple and easy-to-remember commands to execute different tasks, it helps users streamline their project management.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete added contacts.
  * What it does: allows the user to delete contacts added into the address book.
  * Justification: This is a core feature that improves the product significantly because a user may want to remove contacts that they no longer want to keep track of.
  * Highlights: This enhancement affected existing commands (deleting contacts needed to remove them from the groups they were part of as well) and commands to be added in future (deleting added groups). It required utilising of OOP principles and the creation of an abstract class, as the 'delete' command word had to be shared between commands.

* **New Feature**: Added the ability to delete added groups.
  * What it does: allows the user to delete groups added into the address book, removing any members from those groups as well.
  * Justification: This is a core feature that improves the product significantly because a user may want to remove groups or projects that they no longer want to keep track of.
  * Highlights: Aside from the creation of an abstract class, as the 'delete' command word had to be shared between commands, this enhancement required the utilising of OOP principles as it involved the accessing and sharing of data between the group and person classes.

* **New Feature**: Added the ability to find groups.
  * What it does: allows the user to view members of the specified group, as well as the group remarks.
  * Justification: This is a feature that improves the product significantly because a user can then filter the address book to view the information of only the relevant contacts. They can also see the remarks that they had added into the groups beforehand.
  * Highlights: This enhancement affected existing commands (finding contacts by keywords). It required utilising of OOP principles and the creation of an abstract class, as the 'find' command word had to be shared between commands.

* **New Feature**: Added the ability to list all groups.
  * What it does: allows the user to view all the groups currently added into the address book, to keep better track of them.
  * Justification: This is a feature that improves the product because a user can then better track and manage the groups or projects that they have.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&chartGroupIndex=36&chartIndex=0)

* **Project management**:
  * Managed releases `v1.3.0` - `v1.3.3` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Modified the general commands so no additional inputs can be accepted (Pull request [\#143](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/143))
  * Modified the find person command to inherit from an abstract class and share the same command word as the 'find group' command (Pull request [\#103](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/103))

* **Documentation**:
  * User Guide:
    * Added app introductions and quick-start instructions, as well as the glossary and table of contents.[\#147](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/147)
    * Added documentation for the features `add contact` and `delete contact`. [\#147](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/147)
    * Resolved inconsistencies in existing documentation in multiple features, including `delete contact`, `find contact` and `list`: [\#228](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/228)
  * Developer Guide:
    * Added implementation details of the `delete`, `find`, `list` [\#265](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/265) and `listgroup` [\#268](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/268) features.

* **Contributions beyond project team**
  * Meticulously reviewed other groups' works and provided feedback on potential bugs [here](https://github.com/lerxuann/ped).

