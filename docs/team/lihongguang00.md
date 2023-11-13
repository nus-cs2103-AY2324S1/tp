---
  layout: default.md
  title: "Hongguang's Project Portfolio Page"
---

### Project: UniMate

UniMate is a desktop address book and calendar infused in one application intended for National University of Singapore (NUS) students to save group mate's contacts and sync calendars to schedule classes and group project meetings. The user interacts with it using primarily the Command Line Interface (CLI), but can choose to interact with the GUI using a mouse. Its GUI created with JavaFX, and the entire project is written in Java, and has about 25 kLoC.

Given below are my contributions to the project.

* **New Feature**: Calendar System (Model)
  * What it does: A back-end model of the calendar system that supports the calendar UI used in UniMate
  * Justification: Skeleton for the calendar system in UniMate
  * Highlights: The model was highly reusable, being able to be utilised to create both user calendars and contact calendars.

* **New Feature**: AddEvent command
  * What it does: Allows the user to add events to the calendar system through CLI.

* **New Feature**: CompareCalendars/CompareGroupCalendars command
  * What it does: Allows the user to compare calendars with contacts of interest.
  * Highlights: Creates a pop-up of the comparison calendar.

* **New Feature**: Calendar System GUI (User Calendar, Contact Calendar, Comparison Calendar)
  * What it does: Allows the user to view their personal events/their contact's events for the week
  * Justification: Able to see and visualize the timetable would improve the user experience, as well as help the user to identify any mistakes in the events that they have added for the week.
  * Highlights: Was able to make the GUI "adaptive", which means that by default, it would only show 8am to 6pm in the calendar, unless events exceeding the default time period were added, which the GUI will then display a longer time period all in real time. This decreased the amount of clutter in the GUI as unnecessary time periods were not shown.

* **New Feature**: Calendar Import (UNSUCCESSFUL ATTEMPT)
  * What it does: Allows the user to import .ics format files into the application
  * Justification: NUSMODS allows users to export their timetable as .ics format file, so we wanted to allow our application to import it into our application
  * Highlights: Using an external open-source library [ical4j](https://www.ical4j.org/) for parsing .ics files, the documentation of the ical4j library was messy and many methods deprecated. Eventually was unable to integrate into our application effectively, because the format that the library expects was different from the NUSMODS exported format.
  * Credits: [ical4j](https://www.ical4j.org/)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=lihongguang00&tabRepo=AY2324S1-CS2103-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Assigning of suitable group member to resolve issues raised during PE-D
  * Tagging of PRs with suitable assignees, labels and milestone

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addEvent`, `compareCalendars` and `compareGroupCalendars` [#84](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/84)
    * Reformatted UG and added screenshots for all commands: [#148](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/148) and [#152](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/152)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#7](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/7), [#9](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/9), [#11](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/11), [#25](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/25), [#28](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/28), [#31](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/31), [#36](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/36), [#65](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/65), [#66](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/66), [#72](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/72), [#86](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/86), [#87](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/87)
  * Contributed to forum discussions (examples: [#267](https://github.com/nus-cs2103-AY2324S1/forum/issues/267))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/lihongguang00/ped/issues/1), [2](https://github.com/lihongguang00/ped/issues/2), [3](https://github.com/lihongguang00/ped/issues/3), [4](https://github.com/lihongguang00/ped/issues/4), [5](https://github.com/lihongguang00/ped/issues/5), [6](https://github.com/lihongguang00/ped/issues/6), [7](https://github.com/lihongguang00/ped/issues/7))
