---
layout: page
title: Nicholas Chia Zhi Jie's Project Portfolio Page
---

### Overview

UNOFAS is a desktop app for Financial Advisors (FA) to manage client’s contacts, optimized for use via a Command Line
Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The app also includes features
such as sorting, scheduling and other commands to query information quickly required by the FA.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added appointment list that shows upcoming appointments of clients in chronological order and wrote tests.
    * Justification: Financial advisors may want to view all upcoming appointments easily in chronological order so that it is easier for them to plan their timetables.
    * Highlights: This enhancement creates another UI element to show various other appointment-specific details in the future. It requires an understanding of ObservableList interface and the way the list is being tracked by JavaFX.
    * Pull request [#110](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/110)


* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nikele2001&breakdown=true)


* **Enhancements Implemented**:
    * Added `FinancialPlan` field and wrote tests.
      * Justification: As a financial advisor, it would be convenient to have a person's current financial plans available for business purposes.
      * Pull request [#69](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/69)

* **Contributions to the UG**:
  * Added introduction to UG.
  * Added and updated all UI images.
  * Added general UI information table.


* **Contributions to the DG**:
  * Added use cases for `schedule` command, `sort` command, `complete` command and adding financial plans via the `fp/` prefix.
  * Updated user stories.
  * Updated UML class diagrams for `Model` and `UI` components.
  * Added sequence diagram for the `setPerson()` method in the `ScheduleCommand#execute()` method to illustrate how the appointment list is being updated.
  * Added activity diagram for the `gather` command.
  * Contributed to `Instructions for manual testing` in DG.
  * Added effort section.


* **Community**:
  * PRs reviewed (with non-trivial commments):
    [#72](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/72),
    [#108](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/108)


* **Contributions to team-based tasks**:
  * Set up team organisation.
  * Set up TP repository.
  * Set up Jekyll.
