---
layout: page
title: Poh Say Keong's Project Portfolio Page
---

### Project: TAManager

TAManager is a desktop address book application made for professors to manage their teaching assistants (TA) under their charge. It provides fast access to TAs' contact details and their availabilities for tutorials/labs. You can track teaching and claimable hours among your TAs and find relief TAs for impromptu events or unforeseen circumstances.

Given below are my contributions to the project.

* **New Feature**: Added the ability to assign the availability of a TA.
  * What it does: Allows the user to assign the availability of a TA to a specific day and time.
  * Justification: This feature improves the product significantly because it allows the user to keep track of the availability of the TA. This is important because the user can then assign the TA to a tutorial/lab session that does not clash with the TA's availability.
  * Highlights: This enhancement required me to think about the way to represent a block of time in an OOP manner. In the end I decided to represent availability as a collection of time intervals. Subsequently, I had to add this to the Person model and change the various classes that interacted with the Person model.

* **New Feature**: Added the ability to view course information.
  * What it does: Allows the user to view the course information of a specific course.
  * Justification: This is important because the user can then view information such as the different timings for the course that he is interested in.
  * Highlights: Since we were not going to add features that allow users to edit the course information, I had to think about how to represent the course information in an immutable manner.

* **New Feature**: Added the ability to reset the user's default course.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=pohsaykeong&breakdown=true)

* **Project management**:
    * Managed milestones `v1.2b` - `v1.3` and issues on GitHub
    * Managed team's pull requests and code reviews on GitHub
    * Forecasted weekly tasks and delegated tasks to team members

* **Documentation for Developer Guide**:
  * Added class diagram on the implementation of `FreeTime` class [\#47](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/47)
  * Came up with initial draft of user stories and use cases [\#9](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/9)

* **Documentation for User Guide**:
  * Added documentation for the features `course` and `clearteach` [\#61](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/61), [\#71](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/71)
  * Fixed various formatting issues

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#62](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/62), [\#74](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/74), [\#36](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/36)
