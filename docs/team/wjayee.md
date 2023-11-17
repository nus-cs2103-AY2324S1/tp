---
layout: default.md
title: "Ja Yee's Project Portfolio Page"
---

### Project: Pawfection

Pawfection is a desktop app for managing animals in a pet shelter, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI). It aims to provide a platform for volunteers to
store and retrieve information about animals in a shelter easily.

Given below are my contributions to the project:

* **Code contributed**: [Ja Yee's RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=wjayee&breakdown=true)

* **Project management**:
    * Managed releases for `v1.2` - `v1.4` on GitHub

* **New Features**:
    * Add `search` command for animals in the Catalog
        * What it does: Allows users to search for animals by its attributes
        * Justification: This feature allows users to search for animals easily by its attributes.
          This is especially useful when there are many animals in the catalog with the same attributes.
        * Highlights: This implementation will be affected if new attributes of animal is added in the future. This is
          because the search command will have to be modified to include the new attributes.
    * Add `mark` and `unmark` command for tasks
        * What it does: Allows users to mark and unmark tasks as done or uncompleted
        * Justification: This feature allows users to keep track of the tasks that they have completed or tasks that they have not completed.
    * Add `reset` command for all tasks
        * What it does: Allows users to reset all tasks of all animals to uncompleted
        * Justification: This feature allows users to reset all tasks of all animals to uncompleted at the end of the day. This
          allows for easy resetting of tasks that are repeated daily.

* **Enhancements to existing features**:
    * Modify `list` command: [#66](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/66)
    * Wrote tests for `list` and `search`: [#81](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/81), [#107](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/107)
    * Wrote tests for `mark`, `unmark` and `reset` commands and `mark` and `unmark` parsers to increase test coverage:
      [#183](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/183)
    * Wrote tests for `AnimalCatalog`, `Animal` and `UniqueAnimalList` classes to increase test coverage:
      [#78](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/78), [#82](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/82)

* **Documentation**:
    * User Guide:
      - Added documentation for the features `list`, `search`, `mark`, `unmark` and `reset`:
        [#89](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/89), [#116](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/116)
      - Added screenshots for the features `search`, `mark` and `unmark` to better illustrate the features
    * Developer Guide:
      - Added implementation details of following features: `list`, `search`, `mark`, `unmark` and `reset`:
           [#89](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/89), [#202](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/202)
      - Modified sequence UML diagrams: [#216](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/216)
      - Modified implementation details of the Architecture and Logic component: [#89](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/89)

* **Team Tasks**:
    * Gave feedback for PRs
    * Updated DG and UG
    * Refactored code for PawFection (Pull requests [#58](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/58), [#73](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/73))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#59](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/59), [#61](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/61), [#110](https://github.com/AY2324S1-CS2103T-F08-3/tp/pull/110)
