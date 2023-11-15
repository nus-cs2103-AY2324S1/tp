---
layout: page
title: Edric Khiew's Project Portfolio Page
---

# Project: TimetaBRO

## Overview
TimetaBRO is a scheduling application that allows users to manage their own schedule as well as the schedules of their friends. It offers features such as adding and removing friends, adding and removing events, setting reminders, and finding common free times among friends. TimetaBRO is compatible with multiple operating systems and saves data automatically in a JSON file. It also allows advanced users to edit the data file directly. The application offers a command-line interface and provides a command summary for easy reference. TimetaBRO is a powerful tool for enhancing social connections and simplifying event planning.

## Summary of Contributions

**Code contributed:** \
https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=w12-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=lululwtv&tabRepo=AY2324S1-CS2103T-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false

**Enhancements implemented:**

- Changed `add` and `edit` commands to allow users to add friend's free times when adding the friend (past iteration) (feature has been removed)
- Created `addschedule`, `rmschedule`, `addevent`, `rmevent`, and `cft` commands
    - For each command class, created a corresponding parser class for the commands
    - This allows for each command to work better as they have a separate parser class that can help parse the specific inputs for the commands
- Created some test cases for the commands made by me
- Fixed seveeral bugs pertaining to `add`, `edit`, `addschedule`, `rmschedule`, `addevent`, `rmevent` commands

**Contributions to the User Guide (UG):**

- Added guides for commands `cft`, `rmevent`, `addevent`, `addschedule`, `rmschedule` commands
- Fixed UG documentation to align with newer commands
- Updated UG to include expected features that may not be expected by users, such as:
    - Informing users in the UG that event names will be changed to all caps even if they did not key it in caps
    - Add sample code for the commands I have added
- Wrote FAQ
- Wrote Known Issues
- Wrote Command Summary

**Contributions to the Developer Guide (DG):**

- Update DG on how addschedule command works
    - Created sequence diagram for addschedule command
- Updated how Logic package works
- Added implementation section on `addschedule` command
- Added User Stories

**Contributions to team-based tasks:**
- Fixed 20 bugs after PE-Dry run, which led to significant improvements in the code
- Kept track of code quality by fixing all checkstyle issues in gradle
- Kept track of team's submissions and deadlines, ensuring that all tasks are submitted on time by teammates

**Contributions beyond the project team:**

- Took part in bug-catcher
- Took part in PE-D
