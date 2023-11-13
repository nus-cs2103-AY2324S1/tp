---
layout: page
title: Xenos' Project Portfolio Page
---

### Project: NetworkBook

NetworkBook is a desktop contact book application that aims to help computing students and professionals from NUS network with each other. The user can interact with the application using a command-line-interface (CLI), as well as a graphical user interface (GUI) created with JavaFX.

Given below are my contributions to the project:

* **New Feature**: Graduation semester field
    * Implemented the graduation semester field for contacts, allowing users to record a contact's graduation academic year (AY) and semester
    * In doing so, implemented the corresponding model class and parser
    * Graduation semester is modelled as a combination of 2 calendar years (representing the AY) and a semester (either semester 1 or semester 2)
* **New Feature**: Sorting of contacts
    * Implemented the `sort` functionality, which allows for sorting of contacts by the following fields:
        * Graduation semester
        * Priority
        * Name
* **New Feature**: Filter/sort display in status bar
    * Added a text display to indicate the current filter and sorting status in the bottom status bar.
    * The filter status updates when `filter` or `find` (which akin to filter by name) is run.
    * The sort status updates when `sort` is run.
    * This allows users to know, at a glance, the current status, and reduces confusion that may arise if users forget their previous commands or if users do not know that sorting a filtered list does not reset the filter.
* **New Feature**: Mouse interaction
    * Implemented clickable elements in the GUI to allow for the following functionality:
        * Execution of `open` and `email` commands to open the clicked link/email
        * Execution of `filter` based on the clicked tag, course, specialization, or graduation semester
    * The mouse interaction feature is not intended to replace the CLI-based interaction, but to complement it.
        * Mouse interaction can be faster in some cases as the user can click on the desired element right way without the extra step of typing the command (useful where actions are performed in quick succession, such as when opening multiple links of a given contact).
* **Enhancement to existing features**: Improved contact fields display
    * Designed and implemented enhanced contact display GUI loosely based on existing GUI and Edwin's initial mockup
        * Initial GUI:
        * Initial mockup:
        * Enhanced GUI:
    * Improved visuals of general contact fields:
        * Added background colors, which are color-coded to distinguish between external hyperlinks, regular fields, and empty fields
        * Adjusted spacing to improve visual clarity
        * Added highlight on hover to indicate clickable elements
    * Improved readability and aesthetics of multi-value fields (e.g. courses, links):
        * Added separate background shapes to each list item to better visually distinguish them from each other
        * Added index numbering to each list item to allow users to easily run commands involving specific list items
    * Improved priority display for more clarity and faster visual identification:
        * Changed priority display to use symbols (1-3 stars) instead of text
        * Color-coded priority display based on priority value
        * Moved priority display next to contact name
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=xenosf&breakdown=true)
* **Project management**:
    * _To be added soon_
* **Documentation**:
    * User Guide:
        * Added initial documentation for [sorting command](../UserGuide.md#sort-contacts-list-sort-by-order).
        * Wrote [Getting Started section](../UserGuide.md#getting-started).
        * Added documentation for previously undocumented commands: `list`, `clear`, `help`, `exit`
        * _More to be added soon_
    * Developer Guide:
        * Updated documentation for UI
        * Added documentation for sorting feature
        * _More to be added soon_
* **Community**:
    * PRs reviewed (with non-trivial review comments):
        * [PR #93](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/93)
        * [PR #101](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/101)
        * [PR #255](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/255)
        * [PR #256](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/256)
* **Tools**:
    * _To be added soon_
* **Contributions to team-based tasks**:
    * Suggest changes to overall User Guide formatting (heading levels)
    * Write summary section of NetworkBook README
    * Took screenshots for the User Guide
    * Test keyboard shortcut feature on MacOS
    * Review Markdown code quality
    * _More to be added soon_
* **Contributions outside the project**:
    * Found and reported 13 bugs in another team's project ([Bug reports](https://github.com/xenosf/ped/issues))
