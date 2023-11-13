---
layout: page
title: Low Jun Yu's Project Portfolio Page
---

### Overview

UNOFAS is a desktop app for Financial Advisors (FA) to manage client’s contacts, optimized for use via a Command Line
Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The app also includes features
such as sorting, scheduling and other commands to query information quickly required by the FA.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort list by appointment time and name by lexicographical order. [\#73](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/73) [\#90](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/90)
    * What it does: Allows the user to perform sorting of list by appointment time and lexicographical order of name.
    * Justification: This feature improves the product significantly because a user can more efficiently find clients by name and the proximity of their appointments to view upcoming appointments.
    * Credits: The feature was implemented by referencing JavaFX ObservableList documentation. 
  
<br>

* **New Feature**: Added confirm override window for `schedule` command if person already has a current appointment. [\#123](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/123)
  * What it does: Caution a user if he intends to schedule a new appointment when there is already one that is not yet complete.
  * Justification: Most people only arrange one appointment at a time. So the design prevents multiple appointments and also serves as a reminder of a previously set appointment.
  * Highlights: This feature causes the logic flow of the method to change if there is currently an appointment and results in breaking the execution into 2, getting a response from the user before deciding whether to continue the execution of the program.
  * Credits: The feature was implemented by referencing the help function from AB3. However, the main logic of the function was done by myself.
 
* **Code Contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jylow&breakdown=true)

* **Enhancements Implemented**: 
    * Fixed bugs identified during manual testing.
  
* **Contributions to the UG**:
    * Added documentation for the feature `sort`. [\#81](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/81)
    * Maintained known issues to be solved.
    * Proofreading of UG and making changes such as standardising terms used throughout the UG.

* **Contributions to the DG**:
    * Added documentation for sort function and improved documentation for `schedule` command.
    * Added sequence and activity diagram for sort function and update activity diagram for `schedule` command to include overriding.
    * Update schedule and clear command documentation to include the warning prompts.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#125](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/125) [\#133](https://github.com/AY2324S1-CS2103T-F12-1/tp/pull/133#pullrequestreview-1699166607)
  * Contributed to 1 forum discussion. (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/172#issuecomment-1730790631))
  * Reported bugs and suggestions for other teams in the class during PED.

* **Contributions to team-based tasks**: 
  * Released v1.3 and v1.4 of the application.
