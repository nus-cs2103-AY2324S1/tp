---
layout: page
title: Andre Sim's Project Portfolio Page
---

### Project: UniMate

UniMate is a desktop address book and calendar infused in one application intended for National University of Singapore 
(NUS) students to save group mate's contacts and sync calendars to schedule classes and group project meetings. The user
interacts with it using primarily the Command Line Interface (CLI), but can choose to interact with the GUI using a 
mouse. Its GUI created with JavaFX, and the entire project is written in Java, and has about 25 kLoC.

Given below are my contributions to the project.


* **Filter Command**: `filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`
  * What it does: Filters contacts by the given fields. Contacts that match all fields are displayed.
  * Justification: Allows students to search for all students in their contacts that meet the specified fields, e.g. All
  students that study a specific module (and have the tag for that module) or all students that attend a particular CCA
    (and have the tag for that CCA) or all students that have a school email address (filter by email "@u.nus.edu").
  * Highlights: This command is meant to be different enough from the find command such that both commands fulfil
  different purposes. The filter command allows for finding groups of people while the find command allows for finding
  of one specific person given that you know their name specifically.
  * Credits: The structure of the execute method of this command is made to be similar to that of the edit command which
  similarly uses multiple fields that may or may not be present. Apart from this, all code for this command was original
  by me.

* **Delete Event Command**: `deleteEvent DATE_TIME`
  * What it does: Deletes an Event from the Calendar that is present at the specified Date and time.
  * Justification: For the Calendar to be functional, events have to be able to be added and deleted.
  * Highlights: Consists of methods that interact between different layers of abstraction to delete an event from the
  Calendar. Also takes in a Date and time that the event is during so that the specific start or end time of the event 
  does not need to be used. The event is deleted as long as the time indicated is between the start and end time of the
  event. Also shows the deleted event when the command is executed so that it is more easily re added if the wrong event
  is deleted.
  * Credits: The command uses some methods implemented by lihongguang00 for the Calendar class. Apart from that, all 
  code for this command was written by me.

* **Clear all events within a time range**: `clearEvent ts/START_DATE_TIME te/END_DATE_TIME [c/CONFIRMATION]`
  * What it does: Deletes all events from the Calendar that lie within a time range.
  * Justification: After comparing different Calendars to look for free time, users may have to delete events within a 
  time range to make time for a new event.
  * Highlights: Entering the command without any confirmation shows the user all events that are within the time range.
  The command can then be re-entered with the confirmation to confirm the deletion.
  * Credits: Code for this command was written by me.

* **Task List Feature**
  * What it does: Allows users to keep track of tasks. Tasks consist of a description and a deadline which is optional.
  * Justification: A task list allows students to keep track of activities that have to be completed and do not have a
  duration, thus not being appropriate for the Calendar. Examples of these include assignment submission deadlines.
  * Highlights: Allows of storage of tasks. Uses the same portion of the UI as the Event List.
  * Credits: The code for storing the data for the task list was written by Jun Hong and the rest of the code for the 
  task list functions was written by me.

* **Adding tasks to the task list**: `addTask d/DESCRIPTION [te/DEADLINE]`
  * What it does: Allows users to add tasks from the task list.
  * Justification: Users have to be able to add tasks to the task list to use it.
  * Highlights: Tasks with duplicate descriptions and deadlines are allowed but not both.
  * Credits: All code for this command was written by me.

* **Deleting tasks from the task list**: `deleteTask INDEX`
  * What it does: Allows users to delete tasks from the task list.
  * Justification: Users should be able to delete tasks from the task list when they are completed.
  * Highlights: Uses the index displayed in the task list to indicate which task should be deleted.
  * Credits: All code for this command was written by me.

* **Sorting tasks in the task list**: `sortTasks PARAMETER`
  * What it does: Allows users to sort tasks in the task list.
  * Justification: Tasks should be able to be sorted so that the user can see which tasks have to be completed by an
  earlier time or be able to find specific tasks.
  * Highlights: Allows for 2 preset options for ways to sort tasks, by deadline or by description.
  * Credits: All code for this command was written by me.

* **Switch the bottom list section of the UI between the event list and the task list**: `switchList`
  * What it does: Switches between the event list and the task list on the GUI.
  * Justification: Users should be able to view the task list.
  * Highlights: Swaps between the two lists on the bottom of the GUI so that the GUI does not become too cluttered.
  * Credits: All code for this command was written by me.

* **View the event list of someone in the address book**: `viewContactEvents INDEX`
  * What it does: Creates a pop-up to view the event list of someone in the address book.
  * Justification: Users should be able to view the event list of someone in the address book.
  * Credits: Code for this command was written by me, with assistance from lihongguang00.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=Fallman2&tabRepo=AY2324S1-CS2103-F13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Documentation**:
    * User Guide:
        * Added UG documentation for the features `filter`, `addEvent` and `deleteEvent` [\#86](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/86)
        * Added UG documentation for the features `clearEvents`, `addTasks`, `deleteTasks`, `sortTasks`, `switchList` 
      and `viewContactEvents` [\#86](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/86)
    * Developer Guide:
        * Added DG documentation of Use Cases 4 to 7 and UC 12.
        * Added DG documentation of Use Cases 15 to 19 [\#188](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/188).
        * Added DG documentation on the implementation of `TaskManager`, Adding Tasks, Deleting Tasks, Sorting Tasks [\#168](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/168/files), Filtering Persons and Deleting events [\#52](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/52).
        * Added DG sequence diagram for ClearEventsCommand and edited the class diagram for Model [\#176](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/176).
        * Wrote DG documentation on Manual Testing Instructions for all newly implemented features, except Storage related features [\#179](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/179).
        * Wrote 2 Planned Enhancements for DG [\#182](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/182).
        * Wrote on the implementation of TaskList and EventList in the Effort section of the DG [\#182](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/182/files).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#2](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/2), 
  [\#26](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/26), [\#42](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/42)
  , [\#44](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/44), [\#51](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/51)
  , [\#63](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/63), [\#70](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/70)
  , [\#75](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/75), [\#149](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/149), [\#167](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/167).
    * Reported bugs and suggestions for other teams in the class (examples: [\#1](https://github.com/Fallman2/ped/issues/1), 
  [\#2](https://github.com/Fallman2/ped/issues/2), [\#3](https://github.com/Fallman2/ped/issues/3), [\#4](https://github.com/Fallman2/ped/issues/4)
  , [\#5](https://github.com/Fallman2/ped/issues/5), [\#6](https://github.com/Fallman2/ped/issues/6), [\#7](https://github.com/Fallman2/ped/issues/7)
  , [\#8](https://github.com/Fallman2/ped/issues/8), [\#9](https://github.com/Fallman2/ped/issues/9)).

