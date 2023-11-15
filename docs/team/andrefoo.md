---
layout: page
title: Andre Foo's Project Portfolio Page
---
## Overview
TimetaBRO is a scheduling app designed to help users organize their own calendars and those of their friends. It comes
with functionalities like adding or deleting friends, scheduling or canceling events, setting up reminders, and
identifying mutual availability among friends. The app works across various operating systems and automatically backs up
data in a JSON format. For more tech-savvy users, there's an option to edit the data file manually. TimetaBRO features a
user-friendly command-line interface and includes a summary of commands for quick reference.
This application is aims to strengthen social ties and streamline event coordination.

## Summary of Contributions

[**Code contributed**](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=andrefoo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=andrefoo&tabRepo=AY2324S1-CS2103T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Enhancements implemented:**

- v1.2 (unimplemented) enhancements: FreeTime attribute added to Person class in Model. Prototyped the common free time and implemented cascading changes in storage, creating JsonAdaptedFreeTime and incorporating it into the rest of Model.
- Create timetable model for person:
  - Added `FreeTime` class to encapsulate timeslots for overlapping free times between persons
  - Added `TimeBlock` abstract class as a superclass for timeslots to be represented in the timetable
  - Added `Module` class with implemented validation regex to restrict module codes to legitimate formats in NUS
  - Added `Cca` class to represent Cca timeslots
  - Added `DatedEvent` class with Date to represent non-recurring events
  - Added `Schedule` facade class for timetable to encapsulate the schedule of a person
    - Implement methods to calculate overlapping free times
    - Implement methods to retrieve, add and sort lists of modules for easier implementation of commands

**Contributions to the User Guide (UG):**

* Add Parameters constraints table
* Finalised Command Summary table
* Add Caution blocks and Warnings for beginner users
* Formatting command blocks

**Contributions to the Developer Guide (DG):**

* Drafted skeleton for the Developer Guide to delegate tasks to the team
* Describe functionality and component breakdown of `Model`
* Add all use cases
* Add design considerations for implementing `timetable` in `Model` component

**Contributions to team-based tasks:**

* Delegate tasks for documentation-related tasks
* Reviewed, approved and resolved conflicts for team pull requests
* Bug fixing for commands and documentation flaws

**Contributions beyond the project team:**

* Bug catcher stress test participation
* Took part in PE-D
