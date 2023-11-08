---
layout: page
title: Tan Kerway's portfolio
---

### Project: InterviewHub

InterviewHub - A CLI app for engineering managers to manage interviews and interviewees

Given below are my contributions to the project.

* **Accept multiple time formats**: Added the ability to accept multiple time formats.
    * What it does: Allows the user to enter a time in different formats
    * Justification: Different individuals have different ways of entering dates
    * Highlights: This feature increases the flexibility of the command,
      allowing the user to type in inputs without the input being rejected, leading to better UX.


* **New Feature**: list-freetime `DATE`
  * Key terms: `DATE`
    * Accepted date formats
      * `dd/mm/yyyy`
      * `dd-mm-yyyy`
  * What it does: Allows the user to list all the blocks of time from 9am to 5pm on a given date. 
  * Justification: The user might want to see, in a single command, when exactly they are free on a particular day, so that they can schedule other activities.
  * Highlights: This feature allows the user to see, in 1 command, when they are free, so that they can schedule more interviews or schedule other work activities (e.g. team meetings)


* **New Feature**: list-i-today
  * What it does: Allows the user to list all the interviews that they have scheduled on the day the command is executed
  * Justification: The user might want to see all the interviews that are scheduled on the day that the command was executed
  * Highlights: This feature will allow the user to see upcoming interviews on the day that the command was executed in 1 command, thus eliminating the need for the user to consult a potentially messy calendar to attend to upcoming interviews


* **New Feature**: sort-time
  * What it does: Allows the user to list all the interviews that they have scheduled so far, in chronological ascending order
  * Justification: The user might want to see all the interviews that scheduled and are coming soon, so that they can prepare for them accordingly
  * Highlights: This feature will allow the user to see the list of interviews ordered in ascending order of start time in 1 command, thus eliminating the need for the user to check a potentially messy calendar for upcoming interviews


* **Code contributed**: [RepoSense link]()

* **Project management**: `to be added soon`

* **Documentation**:
    * User Guide:
        * Added documentation for the features `accepted time formats`
  * Developer Guide:
      * Added implementation details of the `delete` feature.
