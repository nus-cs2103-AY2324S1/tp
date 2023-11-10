---
layout: page
title: User Guide
---
## CCACommander Ultra Promax Xtra 9000PLUS - User Guide
CCACommander Ultra Promax Xtra 9000PLUS is the one-stop app for CCA Heads to manage CCA members and events, optimised for CCA Heads who prefer to use command line interface.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ccacommander.jar` from [here](https://github.com/AY2324S1-CS2103T-F11-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CCACommander application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ccacommander.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all members and events.

    * `createMember n/CHU WEI RONG g/Male p/98765432 e/chuweirongrocks@gmail.com a/19 Kent Ridge Crescent, Singapore 119278` : Creates a member named `CHU WEI RONG` in CCACommander.

    * `deleteMember 3` : Deletes the 3rd member shown in the current list.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `createMember n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any given order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Clear all Members and Events: `clear`
Clears all Member and Event entries from CCACommander.

Format: `clear`

### Create a Member: `createMember`
Creates a new member with accompanying personal details (name, gender, phone number, email address, home address, tag).

Format: `createMember n/MEMBER_NAME g/GENDER [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* Acceptable values for `GENDER`: `Male`, `Female`, `Others`.
* Acceptable values for `EMAIL`: A string with an email extension (e.g. `@gmail.com`).

Examples:
* `createMember n/CHU WEI RONG g/Male p/98765432 e/chuweirongrocks@gmail.com a/19 Kent Ridge Crescent, Singapore 119278 t/Leader` creates a member `CHU WEI RONG` in CCACommander.

### Delete a Member : `deleteMember`

Deletes the member at the specified index.

Format: `deleteMember MEMBER_INDEX`

* Deletes the member at the specified `MEMBER_INDEX`.
* The index refers to the index number shown in the **currently displayed** member list.
* The index **must be a positive integer** that is within the range of the length of the member list.

Examples:
* `deleteMember 1 ` deletes the 1st member in the member list.
* `deleteMember 10 ` deletes the 10th member in the member list.

### Edit a Member: `editMember`
Edits the member at the specified index with the specified fields.

Format: `editMember MEMBER_INDEX [n/MEMBER_NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* The index refers to the index number shown in the **currently displayed** member list.
* The index **must be a positive integer** that is within the range of the length of the member list.
* At least one field to edit must be provided.
* Acceptable values for `GENDER`: `Male`, `Female`, `Others`.
* Acceptable values for `EMAIL`: A string with an email extension (e.g. `@gmail.com`).

Examples:
* `editMember 1 a/RH t/Musician` edits the address and the tag fields of the 1st member in the member list.

### Create an Event : `createEvent`

Creates a new event with accompanying details (name, location, date, tag).

Format: `createEvent n/EVENT_NAME l/LOCATION d/DATE [t/TAG]...`

* Acceptable values for `DATE`: Dates in the format of `YYYY-MM-DD`.

Examples:
* `createEvent n/Party l/Raffles Hall d/2023-09-16` creates an event `Party` in CCACommander.

### Delete an Event: `deleteEvent`

Deletes the event at the specified index.

Format: `deleteEvent EVENT_INDEX`

* Deletes the event at the specified `EVENT_INDEX`.
* The index refers to the index number shown in the **currently displayed** event list.
* The index **must be a positive integer** that is within the range of the length of the event list.

Examples:
* `deleteEvent 1` deletes the 1st event in the event list.
* `deleteEvent 10` deletes the 10th event in the event list.

### Edits an Event : `editEvent`

Edits the event at the specified index with the specified attributes.

Format: `editEvent EVENT_INDEX [n/EVENT_NAME] [l/LOCATION] [d/DATE] [t/TAG]...`

* The index refers to the index number shown in the **currently displayed** event list.
* The index **must be a positive integer** that is within the range of the length of the member list.
* At least one field to edit must be provided.
* `EVENT_NAME` **must only contain** Alphanumeric Characters and spaces, and it should not be blank
* `LOCATION` **must not** be blank and can take in any values.
* `DATE` **must be a valid date** in the format of **YYYY-MM-DD**.
* `TAG` **must only contain** Alphanumeric Characters with no space in between.

Examples:
* `editEvent 5 n/Halloween Surprise Party l/UTR d/2023-10-31 t/sem1` edits the 5th event in the event list to change the name to `Halloween
  Surprise Party`, the location to `UTR`, the date to `2023-10-31` and the tag to `sem1`.
* `editEvent 3 l/UCC Theater` edits the 3rd event in the event list to change the location to `UCC Theater`.

### Enrol a Member to an Event: `enrol`

Enrols a member to an event.

Format: `enrol m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]`

* Enrols the member at the specified `MEMBER_INDEX` to the event at the specified `EVENT_INDEX` with `NUMBER_OF_HOURS` specifying the number of hours that the member contributed and `REMARK` stating extra remarks about the member and event.
* The `MEMBER_INDEX`/`EVENT_INDEX` refers to the index number shown in the **currently displayed** member/event list.
* The `MEMBER_INDEX`/`EVENT_INDEX` **must be a positive integer** that is within the range of the length of the member/event list.
* The `NUMBER_OF_HOURS` **must be a positive integer** and **must be less than or equal to 2147483647**.

Examples:
* `enrol m/1 e/5 h/3 r/did planning` enrols the 1st member in the member list to the 5th event in the event list, where the member had 3 hours of contributions to that event and has a remark stating that the member "did planning".
* `enrol m/5 e/1` enrols the 5th member in the member list to the 1st event in the event list.

### Unenrol a Member from an Event: `unenrol`

Unenrol a member from an event.

Format: `unenrol m/MEMBER_INDEX e/EVENT_INDEX`

* Unenrol the member at the specified `MEMBER_INDEX` from the event at the specified `EVENT_INDEX`.
* The member at `MEMBER_INDEX` must be a part of the event at `EVENT_INDEX`.
* The `MEMBER_INDEX`/`EVENT_INDEX` refers to the index number shown in the **currently displayed** member/event list.
* The `MEMBER_INDEX`/`EVENT_INDEX` **must be a positive integer** that is within the range of the length of the member/event list.


Examples:
* `unenrol m/1 e/5` unenrols the 1st member in the member list from the 5th event in the event list.
* `unenrol m/5 e/1` unenrols the 5th member in the member list from the 1st event in the event list.

### Edit an enrolment: `editEnrolment`
Edits the enrolment details of a specified member at a specified event with the specified attributes.

Format: `editEnrolment m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]`

* Edits the specified `MEMBER_INDEX`'s enrolment of the event at the specified `EVENT_INDEX` with `NUMBER_OF_HOURS` specifying the number of hours that the member contributed and `REMARK` stating extra remarks about the member and event.
* The `MEMBER_INDEX`/`EVENT_INDEX` refers to the index number shown in the **currently displayed** member/event list.
* The `MEMBER_INDEX`/`EVENT_INDEX` **must be a positive integer** that is within the range of the length of the member/event list.
* At least one field to edit must be provided.
* The `NUMBER_OF_HOURS` **must be a positive integer** and **must be less than or equal to 2147483647**.
* `REMARK` can take any value, but should not be blank.

Examples:
* `editEnrolment m/1 e/1 h/0 r/Absent due to Covid` edits the enrolment of the 1st member in the member list for the 1st event of the event list to be `0` hours and have a remark `Absent due to Covid`.

### List all Members and all Events : `list`

List all members and all events in the CCA in two separate columns.

Format: `list`

A GUI similar to the one below will be shown after entering the command.
![list](images/list.png)

### View Events of Member : `viewMember`

Lists all the events of a specified member index.

Format: `viewMember MEMBER_INDEX`

* Views the events of the member at the specified `MEMBER_INDEX`.
* The index refers to the index number shown in the **currently displayed** member list.
* The index **must be a positive integer** that is within the range of the length of the member list.

Examples:
* `viewMember 1` displays events of the 1st member in the member list.
* `viewMember 10` displays events of the 10th member in the member list.

A GUI similar to the one below will be shown after entering the command if the member is enrolled in events.
The hours and remarks for each event is reflected here.
![viewMember](images/viewMember.png)

### View Members of Event : `viewEvent`

Lists all the members of a specified event index.

Format: `viewEvent EVENT_INDEX`
* Views the members of the event at the specified `EVENT_INDEX`.
* The index refers to the index number shown in the **currently displayed** event list.
* The index **must be a positive integer** that is within the range of the length of the event list.

Examples:
* `viewEvent 1` displays members of the 1st event in the event list.
* `viewEvent 10` displays members of the 10th event in the event list.

A GUI similar to the one below will be shown after entering the command if the event has members enrolled in it.
The hours and remarks for each member is reflected here.
![viewEvent](images/viewEvent.png)

### Find Member in member list : `findMember`

Finds and lists member(s) whose name(s) contain the provided `KEYWORD`.

Format: `findMember KEYWORD [MORE_KEYWORDS]`
* Finds and lists member(s) whose name(s) contain the specified `KEYWORD`.
* More than 1 `KEYWORD` can be provided to find more members.
* The `KEYWORD` must match minimally one of the words in the name of the member to be found, where capitalisation does not matter.

Examples:
* `findMember alice` displays the member(s) whose name(s) contain 'alice'
* `findMember alice bob charlie` displays the member(s) whose name(s) contain 'alice', 'bob' and/or 'charlie'.

### Find Event in event list : `findEvent`

Finds and lists event(s) which name(s) contain the provided `KEYWORD`.

Format: `findEvent KEYWORD [MORE_KEYWORDS]`
* Finds and lists event(s) which name(s) contain the specified `KEYWORD`.
* More than 1 `KEYWORD` can be provided to find more events.
* The `KEYWORD` must match minimally one of the words in the name of the event to be found, where capitalisation does not matter.

Examples:
* `findEvent party` displays the event(s) which name(s) contain 'party'
* `findEvent party marathon gaming` displays the event(s) which name(s) contain 'party', 'marathon' and/or 'gaming'.

### Undoing a command: `undo`

Undoes the previous command that the user has entered, which has changed the data within CCACommander.

Format: `undo`

List of commands that can be undone:
* `clear`
* `createMember`
* `deleteMember`
* `editMember`
* `createEvent`
* `deleteEvent`
* `editEvent`
* `enrol`
* `unenrol`
* `editEnrolment`

### Redoing a command: `redo`

Redoes a command that the user has undone previously.

Format: `redo`

### Help: `help`

Displays a pop-out window that shows a link to this User Guide.

Format: `help`

### Exit: `exit`

Closes CCACommander and its display window.

Format: `exit`

### Recall previous commands

CCA Leaders can quickly recall previously entered commands for convenience. Example use cases include marking attendance for a group of members, editing slightly wrong details and more.

CCACommander stores almost all (see the info card below to see what commands are stored) commands entered by the user. Users can use the <kbd>↑</kbd> to <kbd>↓</kbd> key to navigate through the commands entered upon selecting the command box.

Example:
1. A CCA Leader has just marked attendance for the first member in the member list using the `editEnrolment m/1 e/1 r/Present` command.
2. The CCA Leader can then use <kbd>↑</kbd> to get `editEnrolment m/1 e/1 r/Present`
3. The CCA Leader can edit the member index to get `editEnrolment m/2 e/1 r/Present` to mark the second member as present.

<div markdown="block" class="alert alert-info">:information_source: CCACommander will not store 2 of the same commands if they were entered twice in a row. Likewise, "commands" containing exclusively of blank spaces will not be stored as well. 

Upon entering the <kbd>⌫ Backspace</kbd> or the <kbd>↵ Enter</kbd> key, users will have to re-navigate from the most recent command as it was treated as an edit or entry of command.
</div>

### Saving the data

CCACommander data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CCACommander data are saved automatically as a JSON file `[JAR file location]/data/ccacommander.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CCACommander will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CCACommander home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Clear all members and events** | `clear`
**Create a member** | `createMember n/MEMBER_NAME g/GENDER [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...` <br> e.g. `createMember n/CHU WEI RONG g/Male p/98765432 e/chuweirongrocks@gmail.com a/19 Kent Ridge Crescent, Singapore 119278 t/Leader`
**Delete a member** | `deleteMember MEMBER_INDEX` <br> e.g.`deleteMember 1`
**Edit a member** | `editMember MEMBER_INDEX [n/MEMBER_NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...` <br> e.g.`editMember 1 a/One North`
**Create an event** | `createEvent n/EVENT_NAME [l/LOCATION] [d/DATE] [t/TAG]...` <br> e.g.`createEvent n/Party l/Raffles Hall d/2023-09-16 t/Fun`
**Delete an event** | `deleteEvent EVENT_INDEX` <br> e.g.`deleteEvent 1`
**Edit an event** | `editEvent EVENT_INDEX [n/EVENT_NAME] [l/LOCATION] [d/DATE] [t/TAG]...` <br> e.g. `editEvent 5 n/Halloween Surprise Party l/UTR d/2023-10-31 t/sem1`
**Add member to an event** | `enrol m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]` <br> e.g.`enrol m/1 e/5 h/3 r/did planning`
**Delete member from an event** | `unenrol m/MEMBER_INDEX e/EVENT_INDEX` <br> e.g.`unenrol m/1 e/5`
**Edit an enrolment** | `editEnrolment m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]` <br> e.g. `editEnrolment m/1 e/1 h/0 r/Absent due to Covid`
**List all members and all events** | `list`
**View members of event** | `viewEvent EVENT_INDEX` <br> e.g.`viewEvent 1`
**View events of member** | `viewMember MEMBER_INDEX` <br> e.g.`viewMember 1`
**Find member in list** | `findMember KEYWORD [MORE_KEYWORDS]` <br> e.g.`findMember alice`
**Find event in list** | `findEvent KEYWORD [MORE_KEYWORDS]` <br> e.g.`findEvent party`
**Redo** | `redo`
**Undo** | `undo`
**Help** | `help`
**Exit** | `exit`
