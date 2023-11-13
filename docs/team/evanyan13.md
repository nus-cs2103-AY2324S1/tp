---
layout: page
title: Yan Weidong's Project Portfolio Page
---

# Project: JobFindr

This portfolio provides an overview of my contributions to JobFindr.

## Overview

JobFindr is a desktop-based job application management application.

The inspiration for JobFindr came from the shared challenge faced by all members of our team - the daunting task of
organising and keeping track of the numerous job applications we submit. Recognising that this is a common experience
among NUS students, we felt a strong urge to develop a software solution that would make this process more manageable
for students like us.

JobFindr was born out of this necessity, offering a one-stop solution for recording and managing your job applications
effortlessly.

## Implementation Contribution

1. Programmed the implementation the `Deadline` field

* **What it does**: Implemented the `deadline` field in the job application using the `LocalDateTime` parser, enhancing
  flexibility in date and time format entry.
* **Justification**: Users might be unfamiliar with the constraints of Java's `LocalDateTime` format, potentially
  leading to difficulties in entering the deadline field.
* **Highlights**: We had to format the input deadline before initializing the `Deadline`instance. A key consideration
  was maintaining the abstraction of this implementation within the `ParserUtil` class.
  <br><br>

2. Revamped the User Interface Design for JobFindr

* **What It Does**: This update involves a comprehensive redesign of the visual elements of each JavaFX component in
  JobFindr, alongside a reformatting and addition of new components to the application's window.
* **Justification**: Being a CLI-based desktop application, our aim is to develop a user-friendly GUI for our users.
* **Highlights**: This task was particularly challenging due to the steep learning curve associated with JavaFX and
  FXML. It required a complete overhaul of the CSS styling and reorganization of each component's styleClass.
  <br><br>

3. Improved Real-Time GUI Responsiveness in JobFindr by enhancing the MVC Architecture

* **What It Does**: Implemented immediate GUI updates in response to user commands, eliminating the need for manual
  refresh.
* **Justification**: Critical for user interface efficiency, this feature provides instant feedback on command
  execution.
* **Highlights**: This marked my initial foray into applying the MVC (Model-View-Controller) architecture.
  <br>

### Other Contributions

* **Code Contributed**:
    * My code contributions can be found
      here: [Reposense Report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=evanyan13&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)
* **Project management**:
    * Lead the team in fulfilling weekly deliverables and coordinated the general direction of the project.
    * Actively assisted peers by providing technical advise and reviewing and approving PRs.
* **Documentation**:
    * User Guide (UG):
        * Added description for `delete`, `help` and `exit` commands.
        * Added UI mockups to illustrate command results.
        * Added Introduction, Guide to User Guide and Quick Tutorial sections.
    * Developer Guide (DG): Added sections for `edit` command and `Ui` component.
* **Bug Fixes**:
    * Fixed bugs reported during PE dry run: PR [#126](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/126),
      [#112](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/112),
      [#172](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/172)
* **Community**:
    * PRs reviewed (with non-trivial review comments): PR [#65](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/65)
