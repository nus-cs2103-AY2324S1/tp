---
layout: page
title: Aaron Tay's Project Portfolio Page
---

### Overview

UNOFAS is a desktop app for **Financial Advisors (FA)** to manage client’s contacts, optimized for use via a **Command Line Interface (CLI)** while still having the benefits of a **Graphical User Interface (GUI)**. The app also includes features such as sorting, scheduling and other commands to query information quickly required by the FA.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the `ScheduleItem` class as a new field to Person.
  * Justification: This feature provides users the ability to associate appointments with their clients.
  * Pull Request [#70](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/70)
  
  <br>
  
* **New Feature**: Added the ability to `schedule` appointments. 
  * What it does: Allows the user to create an appointment associated with the specified client.
  * Justification: This feature provides users the ability to schedule and keep track of appointments with their clients.
  * Pull Request [#70](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/70)

  <br>

* **New Feature**: Added the ability to complete appointments. 
  * What it does: Allows users to clear appointments completed from the contact book. 
  * Justification: With this feature, user can keep track of appointments completed.
  * Highlights: This command allows for more flexibility, giving users the choice to clear appointments by user's index or by a date. 
  * Pull Request [#133](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/133)
  

* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kb-tay&breakdown=true)


* **Enhancements Implemented**: 
  * Added testing for implemented features
  * Refactored `ScheduleItem` class to adhere to LSP. [#108](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/108) 
  * Refactored `CompleteCommand` to better adhere to the command pattern. [#217](https://github.com/AY2324S1-CS2103T-F12-1/tp/issues/217)
  

* **Contributions to the UG**:
  * Added documentation for `schedule` command and `complete` under feature section. [#137](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/137) [#135](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/137) [#210](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/210)
  

* **Contributions to the DG**:
  * Document the overview of how `Command` is executed.
  * Added documentation, class diagram and sequence diagram for `schedule` feature. 
  * Added documentation on design considerations for `Appointment` field. [#137](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/137)
  * Added sequence and activity diagram for `complete` feature. [#209](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/209/files) 
  * Added documentation on implementation and design considerations for `complete` feature.


* **Community**:
  * Review PRs of teammates. [#109](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/109) [#205](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/205) [#110](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/110) [#256](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/256) 
  * Review DG and suggested bug fixes for teammates. [#285](https://github.com/AY2324S1-CS2103T-F12-1/tp/issues/285) [#278](https://github.com/AY2324S1-CS2103T-F12-1/tp/issues/278)


* **Contributions to team-based tasks**
  * Update Developer Guide - Update diagram for `Model` and `Storage`, checked for overall correctness. [#128](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/128)
  * Handled PE-D bug triaging.
