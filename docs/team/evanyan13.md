---
layout: page
title: Yan Weidong(Evan)'s Project Portfolio Page
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

## Summary of Contributions

### Enhancements

1. Programmed the implementation the `Deadline` field

* **What it does**: Implemented the `deadline` field in the job application using the `LocalDateTime` parser, enhancing
  flexibility in date and time format entry. The month component is made case-sensitive to accommodate various input
  styles. Additionally, the time format's accuracy is improved by converting 2400 to 0000, a valid format that is not
  recognized by Java's `LocalDateTime`.
* **Justification**: Users might be unfamiliar with the constraints of Java's `LocalDateTime` format, potentially
  leading to difficulties in entering the deadline field. Our goal is to maximize the flexibility of the deadline format
  to ensure a seamless user experience.
* **Highlights**: To achieve these functionalities, we had to format the input deadline before initializing
  the `Deadline`instance. A key consideration was maintaining the abstraction of this implementation within
  the `ParserUtil` class.
  <br><br>

2. Revamped the User Interface Design for JobFindr

* **What It Does**: This update involves a comprehensive redesign of the visual elements of each JavaFX component in
  JobFindr, alongside a reformatting of the application layout to enhance user experience. Additional UI components were
  introduced to complement the new features of our application, such as the `JobDetailsPanel`, which was introduced to
  the
  right half of the screen in the v1.3 iteration.
* **Justification**: Being a CLI-based desktop application, our aim is to develop a user-friendly GUI for our users.
  From repurposing the color scheme to reformatting the layouts, this GUI update shares the same objective of improving
  user experience.
* **Highlights**: This task was particularly challenging due to the steep learning curve associated with JavaFX and
  FXML. It required a complete overhaul of the CSS styling and reorganization of each component's styleClass. I also had
  to familiarize myself with the various parameters of FXML components. Integrating Java components with FXML and CSS
  presented a novel and engaging challenge.
  <br><br>

3. Improved Real-Time GUI Responsiveness in JobFindr by enhancing the MVC Architecture

* **What It Does**: Implemented real-time synchronization between the GUI elements such as `JobListPanel` and
  `JobDetailsPanel`, and the backend processes. This ensures immediate GUI updates in response to user commands,
  eliminating the need for manual refresh.
* **Justification**: Critical for user interface efficiency, this feature provides instant feedback on command
  execution, enhancing user experience by keeping them informed of the latest state changes.
* **Highlights**: This marked my initial foray into applying the MVC (Model-View-Controller) architecture,
  requiring extensive custom coding to achieve seamless integration and dynamic updates.
  <br><br>

### Code Contributed

My code contributions can be found
here: [Reposense Report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=evanyan13&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

### Other Contributions

* **Project management**:
    * Lead the team in fulfilling weekly deliverables and coordinated the general direction of the project.
    * Actively assisted peers by providing technical advise and reviewing and approving PRs.
* **Documentation**:
    * Contributed extensively to both the User Guide (UG) and Developer Guide (DG).
    * Focused on providing graphical design support in the documentations.
* **Bug Fixes**:
    * Fixed bugs reported during PE dry run.
        * PR [#81](https://github.com/AY2324S1-CS2103T-W12-3/tp/issues/81)
        * PR [#93](https://github.com/AY2324S1-CS2103T-W12-3/tp/issues/93)
        * PR [#146](https://github.com/AY2324S1-CS2103T-W12-3/tp/issues/146)
* **Community**:
    * PRs reviewed (with non-trivial review comments):
        * PR [#65](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/65)
* **Tools**:

## Contributions to the User Guide

*Given below are sections I contributed to the User Guide. They showcase my ability to write documentation targeting
end-users.*

## Contributions to the Developer Guide

*Given below are sections I contributed to the Developer Guide. They showcase my ability to write technical
documentation and the technical depth of my contributions to the project.*
