---
layout: page
title: Lam Jin Heng Braydon's Project Portfolio Page
---

### Project: TuitionConnect

to be added soon

Given below are my contributions to the project.

* **New Feature**: `ListByDayCommand`
  * What it does: Shows the current list of tutees who have lessons on a specified day
  * Justification: This feature allows private tutors to be reminded if they have any classes on that particular day with a single command
    * Highlights: This feature extends the `ListCommand` class and uses the same command syntax `list` followed by an optional `[DAY]` parameter. If the user does not specify the `[DAY]` parameter, it will be parsed as a `ListCommand` and shows all tutees in the list. If the `[DAY]`
    parameter is included, it will be parsed as a `ListByDayCommand`

* **New Feature**: `Undo`
  * What it does: Undo commands that alter the data of tutees such as `delete`, `add` and `edit`
  * Justification: This feature allows private tutors to efficiently undo commands if they made a mistake.
    * Highlights: This feature saves the state of the `VersionedAddressBook` after every command that alters the data of tutees into  temporary memory which will be cleared everytime the application is closed

* **New Feature**: `Redo`
  * What it does: Redo undo commands that alter the data of tutees such as `delete`, `add` and `edit`
  * Justification: This feature allows private tutors to efficiently redo commands if they made a mistake.

* **New Feature**: Created a `ScheduleList` that is displayed on the UI as a `ScheduleListPanel` which allows tutor to view upcoming lessons.
  *  What it does: Sorts a private tutor's lessons according the day and time so in an `ObservableList`.
  *  Justification: This feature allows private tutors to easily glance their schedule for the week.
  *  Highlights: This features requires a `Lesson` Class that encapsulates the `Day`, `Begin` and `End` fields in an `add` command. `Lesson` objects are than sorted using a `LessonComparator`

* **Code contributed**:
    * [Reposense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lambraydon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=lambraydon&tabRepo=AY2324S1-CS2103T-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Set up codecov for team repo
  * Created team organisation on github
  * Created initial UI mockup in v1.2

* **Enhancements to existing features**:
    * Updated GUI by modifying UI of list tiles
    * Refactored `Person#isSamePerson` such that a person is considered a duplicate if they have the `Name` and `Phone`

* **Documentation**:
    * to be added soon

* **Community**:
    * to be added soon

* **Tools**:
    * to be added soon
