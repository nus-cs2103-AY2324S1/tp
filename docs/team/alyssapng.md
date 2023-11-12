---
layout: page
title: Alyssa Png Kai Wen's Project Portfolio Page
---

### Overview

UNOFAS is a desktop app for Financial Advisors (FA) to manage client’s contacts, optimized for use via a Command Line
Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The app also includes features
such as sorting, scheduling and other commands to query information quickly required by the FA.

### Summary of Contributions

Given below are my contributions to the project.
* **New feature**: Added the ability to gather email of clients by Financial plan and Tag
    * What it does: It takes a prompt as input and retrieves emails of clients where the prompt matches a substring of their Financial Plan or Tag names. 
    * Justification: This feature significantly improves the product's efficiency by allowing financial advisors to consolidate emails effectively. It simplifies communication, enabling advisors to update multiple clients about changes in their financial plans or schedule crucial meetings promptly. 
    * Highlights:
      * Provides a foundation for future email gathering implementations, paving the way for potential expansions into other fields. 
      * Required a deep understanding of interfaces and integration with existing codebase.
    * (Pull request [#72](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/72))

* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=alyssapng&breakdown=true)

* **Enhancements Implemented**:
  * Gather command to be able to gather emails by tags
    * (Pull request [#109](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/109))
  * Enhancing the UI design for UNOFAS
    * Justification: Provide financial advisors with a more intuitive, visually appealing interface, optimizing content visibility to reduce the likelihood of overlooking crucial details.
    * (Pull request [#151](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/151)

* **Bug Fixes** :
  * Handling incorrect date for complete and schedule command 
    * What it does: Handles cases where users enter invalid days (e.g. 31-02-2023) are input in the date fields.
    * Justification: To prevent financial advisors from missing appointments, it is crucial to ensure the accurate entry of dates and deter any errors.
    * (Pull request [#256](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/256))

* **Contributions to the UG**:
  * Updated Title and Introduction
  * Added Argument Summary
  * Added documentation for the features `gather`

* **Contributions to the DG**:
  * Added target user profile, value proposition, user stories and user cases.
  * Added implementation details for gather.
  * Added planned enhancement.

* **Contributions to team-based tasks**:
  * Release v1.2 JAR file.
  * Added screenshots into project notes document for v1.2 demo.

* **Review/mentoring contributions**:
* PRs reviewed (with non-trivial commments):
[#108](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/108))
[#90](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/90))
