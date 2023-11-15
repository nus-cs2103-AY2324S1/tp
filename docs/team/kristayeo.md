---
layout: page
title: Krista Yeo's Project Portfolio Page
---

# Project Portfolio Page: TimetaBRO

## Overview
TimetaBRO is an app made for NUS students to better keep track of all their friends' information and schedules.
It facilitates easy visual comparison between the user and a friend's timetable, and quickly finds common free times with friends.

## Summary of Contributions

**Code contributed:**
https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kristayeo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22

**Enhancements implemented:**

* Added right section of the app that consists of the user profile display and the selected friend profile display.
* Added a timetable for each display profile
* Displayed time blocks sorted according to time under their respective day
* Color coded the time blocks according to their type:
  * Blue for module (weekly recurring)
  * Red for CCA (weekly recurring)
  * Green for dated events (non-recurring)
* Displayed friend profile upon selection by mouse click from the friend list
* Refresh display profiles upon relevant command executions (`user`, `edit`, `addschedule`, `addevent`, `rmschedule`, `rmevent`)
* Created the following UI related classes:
  * ListCellSelectedEvent.java
  * SelectedFriendCard.java
  * UserCard.java
  * SelectedFriendCard.fxml
  * UserCard.fxml
* Redesigned the help pop-up

**Contributions to the User Guide (UG):**

* Wrote the TimetaBRO User Interface
* Wrote about `addschedule`, `addevent`, `rmschedule`, `rmevent` for both user and friend.
* Contributed to the documentation of `edit`, `delete`, Viewing friend's profile, `cft`.
* Ensured all the outputs for their respective features were correct.
* Organised the UG, ensured that all the features followed the same format.
* Added necessary additional notes and proofread the UG, cleaned up mistakes.

**Contributions to the Developer Guide (DG):**

* UI component of the Design
* Implementation: Click to View Friend Timetable Feature
* Product Scope: Target User Profile and Value Proposition
* Contributed to the Glossary
* Did formatting, organising of the code 

**Contributions to team-based tasks:**

* Approved and merged pull requests.
* Wrote README.md and index.md

**Contributions beyond the project team:**

* Took part in Bug CATcher stress test
* Took part in PE-D
