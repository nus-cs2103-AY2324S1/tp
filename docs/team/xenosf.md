---
layout: page
title: Xenos' Project Portfolio Page
---

### Project: NetworkBook

NetworkBook is a desktop contact book application that aims to help computing students and professionals from NUS network with each other. The user can interact with the application using a command-line-interface (CLI), as well as a graphical user interface (GUI) created with JavaFX.

Given below are my contributions to the project:

* **New Feature**: Graduation semester field
    * **What it does:** Allows users to record a contact's graduation academic year (AY) and semester.
    * **Justification:** This feature allows users to record when their contacts graduate, facilitating easier selection of relevant contacts when needed. For example, if finding members for a school project, the user can easily see whether a given contact is still studying.
    * **Highlights:** This feature required analysis of design alternatives regarding how best to model the data. The implementation was challenging as it required the creation of the model class, the corresponding parser, and integration into the existing Person model.
* **New Feature**: Sorting of contacts
    * **What it does:** Allows users to sort contacts by graduation semester, priority, or name in ascending or descending order.
    * **Justification:** This feature allows users to easily find relevant contacts when needed.
    * **Highlights:** This feature required the creation and implementation of a new command class and corresponding parser. It also required implementation of several different comparators for each field/order pair, as well as modifying the overall model class to display the sorted list.
    * **Credits:** The sorting uses JavaFX's `SortedList`, which sorts its items based on a given comparator.
* **New Feature**: Filter/sort display in status bar
    * **What it does:** Displays the current sorting and filtering status in the bottom status bar.
    * **Justification:** This feature allows users to know at a glance, the current status, reducing confusion that may arise if users forget to reset the filter and cannot see all of their contacts.
    * **Highlights:** This feature required analysis of design alternatives regarding how best to retrieve the current sorting and filtering status. The implementation was challenging as it required changes to the existing filter and sort commands.
* **New Feature**: Mouse interaction
    * **What it does:** Allows users to click on certain elements in the GUI to execute `open`, `email`, and `filter` commands without typing.
    * **Justification:** The mouse interaction feature is not intended to replace the CLI-based interaction, but to complement it. This allows the user to click on the desired element right way without the extra step of typing the command (useful where actions are performed in quick succession, such as when opening multiple links of a given contact).
    * **Highlights:** The implementation of this feature was challenging as it required creating a way to execute commands programmatically. This could only be done in the main window class, which introduced the additional challenge of capturing the click event and allowing it to execute an action in a parent class.
* **Enhancement to existing features**: Improved GUI (PRs [#166](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/166), [#178](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/178)):
    * Designed and implemented enhanced contact display GUI loosely based on existing GUI and Edwin's initial mockup
    * Improved styling of contact fields (particularly multi-value fields) for readability and aesthetic appeal
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=xenosf&breakdown=true)
* **Documentation**:
    * User Guide:
        * Added initial documentation for [sorting command](../UserGuide.md#sort-contacts-list-sort-by-order).
        * Wrote [Getting Started section](../UserGuide.md#getting-started).
        * Added documentation for previously undocumented commands: `list`, `clear`, `help`, `exit`
        * Cosmetic changes to introduction section and overall formatting
    * Developer Guide:
        * Wrote use cases for sorting and filtering feature
        * Updated documentation and diagrams for UI
        * Documented sorting feature implementation
        * Documented status bar feature design and implementation
* **Community**:
    * PRs reviewed (with non-trivial review comments): [PR #93](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/93), [PR #101](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/101), [PR #255](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/255), [PR #256](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/256)
* **Contributions to team-based tasks**:
    * Wrote summary section of [NetworkBook README](../../README.md)
    * Updated [landing page](../index.md)
    * Took screenshots for the [User Guide](../UserGuide.md)
    * Smoke test on MacOS
    * Review code quality
* **Contributions outside the project**:
    * Found and reported 13 bugs in another team's project ([Bug reports](https://github.com/xenosf/ped/issues))
