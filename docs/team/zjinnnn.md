---
layout: page
title: Zong Jin's Project Portfolio Page
---

# Project: TAvigator

### Overview

**TAvigator** streamlines the teaching process for TAs around the world. Our software understands the various roles a TA has to play, from meticulous record-keeping to fostering connections with students.

Tavigator is optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TAvigator can get your lesson management tasks done faster than traditional GUI apps.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark student attendance and participation in lessons via `markAttendanceCommand`.
    * What it does: Allows users to mark or modify the attendance status of students for lessons on particular weeks. 
    * Justification: This feature streamlines the attendance tracking process. It reduces the administrative burden on users by automating the process of marking attendance, handling corrections, and maintaining accurate records over time.
    * Highlights: This feature affects existing commands and commands that will be implemented in the future. The implementation was relatively straightforward, but required creation of several new classes and modifications to existing classes. Implementation required multiple rounds of refactoring in order to adhere to software design principles of SLAP.
  
* **New Enhancement**: Improved `markAttendanceCommand`to handle multiple students' names or ID.
    * What it does: Allows the user to mark attendance for multiple students at once, passing all invalid names/IDs, marking all valid ones.
    * Justification: This feature improves the product significantly because it allows the user to mark attendance for multiple students at once, saving time and effort.
    * Highlights: The implementation of the command was relatively straightforward as it only involved modifying the existing code to handle multiple names/IDs and a simple logic check for the validity of names/IDs.

* **New Enhancement**: Improved `markAttendanceCommand`to intelligently updates existing attendance records if they fall within the same week.
    * What it does: Ensures users that attendance data is current and accurate without duplication.
    * Justification: This feature improves the product substantially because it allows the user to mark attendance for students who have already been marked for the same week, without creating duplicate records. Additionally, it allows for user to modify attendance records for the same week.

* **Test Coverage**: Wrote test cases to increase overall cumulative coverage by 3.15% (PRs: [#74](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/74), [#80](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/80), [#97](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/97))

* **Code contributed**: [Zjinnnn's tP Dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=zjinnnn&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Contribution to team-based tasks**:
  * Managed issues on issue tracker 
  * Updated `README.md` with product logo and appropriate description (PR: [#38](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/38))

* **Documentation**:
    * User Guide:
        * Re-organized sections in User Guide for better flow and readability (PR: [#153](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/153), [#166](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/166))
          * Added `About this User Guide` , `Prefix Summary` section
          * Categorized features for better navigation
          * Added links to relevant sections for better navigation
        * Added details for `markAttendanceCommand` 
        * Standardised User Guide format
    * Developer Guide:
        * Added implementation details for `markAttendanceCommand`
        * Added use cases
        * Added future enhancements
      
* **Review/ mentoring contributions**:
  * Reviewed 7 PRs with non-trivial review comments (PR: [#78](https://github.com/AY2324S1-CS2103T-T09-4/tp/pull/78))

* **Contributions beyond project team**:
  * Reported bugs other teams with comprehensive detailing and suggestions. (Bug Reports: [1](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/191), [2](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/196), [3](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/198), [4](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/208), [5](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/211), [6](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/219), [7](https://github.com/AY2324S1-CS2103T-T08-2/tp/issues/220))
