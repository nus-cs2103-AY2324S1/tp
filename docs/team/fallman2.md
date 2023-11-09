---
layout: page
title: Andre Sim's Project Portfolio Page
---

### Project: UniMate

UniMate is a desktop address book application used for managing contacts and calendars. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: `to be added soon,`
    * What it does: `to be added soon,`
    * Justification: `to be added soon,`
    * Highlights: `to be added soon,`
    * Credits: `to be added soon,`


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

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * `to be added soon,`
    * `to be added soon,`

* **Documentation**:
    * User Guide:
        * Added documentation for the features `filter`, `addEvent` and `deleteEvent` [\#72]()
    * Developer Guide:
        * `to be added soon,`

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#2](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/2).
    * Contributed to forum discussions (examples: `to be added soon,`)
    * Reported bugs and suggestions for other teams in the class (examples: `to be added soon,`)

* **Tools**:
    * `to be added soon,`
    * `to be added soon,`
