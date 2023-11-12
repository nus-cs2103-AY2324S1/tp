---
layout: page
style: guide
title: User Guide
---
## CCACommander Ultra Promax Xtra 9000PLUS - User Guide

![Logo](images/logo_removebg.png)

## Introduction
As a Co-curricular Activity (CCA) head, do you find yourself struggling to manage your CCA members’ attendance and record the array of events that your CCA has?
Are the heaps of spreadsheets you are using to manage them making you feel overwhelmed? Well, fear not, **CCACommander Ultra ProMax Xtra 9000PLUS** is here to save the day!

CCACommander is a desktop application that helps CCA leaders record details of their members and events, whilst tracking every member’s attendance. 
It helps to organise members and events into easy-to-view compartments under a singular Graphical User Interface (GUI) while providing granular control to you via its in-built Command Line Interface (CLI). 
If you can type quickly, CCACommander can help you complete your administrative tasks much more efficiently than traditional GUI applications.

## About
This user guide provides documentation on how you can install and use **CCACommander Ultra ProMax Xtra 9000PLUS**. Descriptions of CCACommander’s features and how to use them have been carefully organised into key sections for your convenience.
To get started, take a look at our [quick start](#quick-start) guide!

This guide uses the following features to make it easier for you to navigate around:
* Words that look like [this](#about) can be clicked to jump to the related section.
* Words that look like `this` refer to commands which you can type into the command box of CCACommander.
* Words that look like <kbd>this</kbd> refer to keyboard keys that you can press.

<div markdown="block" class="alert alert-warning">
:warning: Boxes with the :warning: icon contain important information that you should read.
</div>

<div markdown="block" class="alert alert-info">
:information_source: Boxes with the :information_source: icon contain additional useful information.
</div>

<div markdown="block" class="alert alert-primary">
:bulb: Boxes with the :bulb: icon contain additional tips and tricks to help you get the most out of CCACommander.
</div>


{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start
<div markdown="block" class="alert alert-warning">

:warning: We assume that you have some basic knowledge of terminal commands. You can learn how to use the terminal with [this guide](https://www.freecodecamp.org/news/command-line-for-beginners/)

</div>

1. Ensure you have Java `11` or above installed in your computer by following [this guide](https://www.freecodecamp.org/news/command-line-for-beginners/).

2. Download the latest `ccacommander.jar` from [here](https://github.com/AY2324S1-CS2103T-F11-1/tp/releases).

3. Put the downloaded file into a folder of your choice (preferably a new empty folder).

4. Open a command terminal in the folder you put the CCACommander file in, and use the `java -jar ccacommander.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br> e.g. typing `help` and pressing <kbd> Enter </kbd> will open the help window. <br>
   Some example commands you can try:

    * `list` : Lists all members and events.

    * `createMember n/Lim Jun Jie g/Male p/98765432 e/limjunjie@gmail.com a/19 Kent Ridge Crescent, Singapore 119278`: Creates a member named Lim Jun Jie in CCACommander.

    * `deleteMember 3` : Deletes the 3rd member shown in the current list.

6. You can refer to the [Features](#features) section below for the details of each command.

<div markdown="block" class="alert alert-primary">

:bulb: You can use the `clear` command to remove the sample data.
</div>


--------------------------------------------------------------------------------------------------------------------

## Navigating User Interface
![UI_breakdown](images/ui_breakdown.png)
Here are the important components of CCACommander that you will use and interact with.

**Menu**: The CCACommander menu is located at the top-left corner of your screen. Click the sub-menu items to execute the following
1. File: Exit the program
2. Theme: Toggle between light and dark themes

Light | Dark
--------|--------
![LightTheme](images/light_theme.png) | ![DarkTheme](images/dark_theme.png)

3. Help: Access our user guide

**Command Box**: Type in your text commands. <br>
**Result Display**: View messages from CCACommander regarding your commands. <br>
**Member List**: View the members stored in CCACommander. <br>
**Event List**: View the events stored in CCACommander. <br>
**Storage Location**: Locate where your CCACommander data is stored in your computer.

### Command History
You can quickly recall previously entered commands for convenience as CCACommander stores almost all (see the info card below to see what commands are stored) commands entered by the user. <br/>
Upon selecting the command box, you can use the <kbd>↑</kbd> to <kbd>↓</kbd> key to navigate through the commands entered .

Example:
1. You have just marked attendance for the first member in the member list using the editEnrolment m/1 e/1 r/Present command.
2. You can then use <kbd>↑</kbd> to get editEnrolment m/1 e/1 r/Present
3. You can edit the member index to get editEnrolment m/2 e/1 r/Present to mark the second member as present.

<div markdown="block" class="alert alert-info">:information_source: CCACommander will not store 2 of the same commands if they were entered twice in a row. Likewise, "commands" containing exclusively of blank spaces will not be stored as well.

Upon entering the <kbd>⌫ Backspace</kbd> or the <kbd>↵ Enter</kbd> key, users will have to re-navigate from the most recent command as it will be treated as an edit or entry of command.
</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `createMember n/NAME`, `NAME` is a parameter which can be used as `createMember n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any given order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Some parameters of certain commands have constraints on the acceptable inputs. <br>
  You can refer to the [List of acceptable values](#list-of-acceptable-values) section below for more details.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

<hr class="feature-class-separator">

### Member commands

#### Create a Member: `createMember`
Creates a new member with accompanying personal details (name, gender, phone number, email address, home address, tag).

Format: `createMember n/MEMBER_NAME g/GENDER [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* Acceptable values for `GENDER`: `Male`, `Female`, `Others`.
* Acceptable values for `EMAIL`: A string with an email extension (e.g. `@gmail.com`).

Examples:
* `createMember n/CHU WEI RONG g/Male p/98765432 e/chuweirongrocks@gmail.com a/19 Kent Ridge Crescent, Singapore 119278 t/Leader` creates a member `CHU WEI RONG` in CCACommander.

<hr class="command-separator">

#### Delete a Member : `deleteMember`

Deletes the member at the specified index.

Format: `deleteMember MEMBER_INDEX`

* Deletes the member at the specified `MEMBER_INDEX`.
* The index refers to the index number shown in the **currently displayed** member list.
* The index **must be a positive integer** that is within the range of the length of the member list.

Examples:
* `deleteMember 1` deletes the 1st member in the member list.
* `deleteMember 10` deletes the 10th member in the member list.

<hr class="command-separator">

#### Edit a Member: `editMember`
Edits the member at the specified index with the specified fields.

Format: `editMember MEMBER_INDEX [n/MEMBER_NAME] [g/GENDER] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]...`

* The index refers to the index number shown in the **currently displayed** member list.
* The index **must be a positive integer** that is within the range of the length of the member list.
* At least one field to edit must be provided.
* Acceptable values for `GENDER`: `Male`, `Female`, `Others`.
* Acceptable values for `EMAIL`: A string with an email extension (e.g. `@gmail.com`).

Examples:
* `editMember 1 a/RH t/Musician` edits the address and the tag fields of the 1st member in the member list.

<hr class="feature-class-separator">

### Event commands

#### Create an Event : `createEvent`

Creates a new event with accompanying details (name, location, date, tag).

Format: `createEvent n/EVENT_NAME l/LOCATION d/DATE [t/TAG]...`

* Acceptable values for `DATE`: Dates in the format of `YYYY-MM-DD`.

Examples:
* `createEvent n/Party l/Raffles Hall d/2023-09-16` creates an event `Party` in CCACommander.

<hr class="command-separator">

#### Delete an Event: `deleteEvent`

Deletes the event at the specified index.

Format: `deleteEvent EVENT_INDEX`

* Deletes the event at the specified `EVENT_INDEX`.
* The index refers to the index number shown in the **currently displayed** event list.
* The index **must be a positive integer** that is within the range of the length of the event list.

Examples:
* `deleteEvent 1` deletes the 1st event in the event list.
* `deleteEvent 10` deletes the 10th event in the event list.

<hr class="command-separator">

#### Edit an Event : `editEvent`

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

<hr class="feature-class-separator">

### Enrolment commands

#### Enrol a Member to an Event: `enrol`

Enrols a member to an event.

Format: `enrol m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]`

* Enrols the member at the specified `MEMBER_INDEX` to the event at the specified `EVENT_INDEX` with `NUMBER_OF_HOURS` specifying the number of hours that the member contributed and `REMARK` stating extra remarks about the member and event.
* The `MEMBER_INDEX`/`EVENT_INDEX` refers to the index number shown in the **currently displayed** member/event list.
* The `MEMBER_INDEX`/`EVENT_INDEX` **must be a positive integer** that is within the range of the length of the member/event list.
* The `NUMBER_OF_HOURS` **must be a positive integer** and **must be less than or equal to 2147483647**.

Examples:
* `enrol m/1 e/5 h/3 r/did planning` enrols the 1st member in the member list to the 5th event in the event list, where the member had 3 hours of contributions to that event and has a remark stating that the member "did planning".
* `enrol m/5 e/1` enrols the 5th member in the member list to the 1st event in the event list.

<hr class="command-separator">

#### Unenrol a Member from an Event: `unenrol`

Unenrol a member from an event.

Format: `unenrol m/MEMBER_INDEX e/EVENT_INDEX`

* Unenrol the member at the specified `MEMBER_INDEX` from the event at the specified `EVENT_INDEX`.
* The member at `MEMBER_INDEX` must be a part of the event at `EVENT_INDEX`.
* The `MEMBER_INDEX`/`EVENT_INDEX` refers to the index number shown in the **currently displayed** member/event list.
* The `MEMBER_INDEX`/`EVENT_INDEX` **must be a positive integer** that is within the range of the length of the member/event list.


Examples:
* `unenrol m/1 e/5` unenrols the 1st member in the member list from the 5th event in the event list.
* `unenrol m/5 e/1` unenrols the 5th member in the member list from the 1st event in the event list.

<hr class="command-separator">

#### Edit an enrolment: `editEnrolment`
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

<hr class="feature-class-separator">

### View commands

#### List all Members and all Events : `list`

List all members and all events in the CCA in two separate columns.

Format: `list`

A GUI similar to the one below will be shown after entering the command.
![list](images/list.png)

<hr class="command-separator">

#### View Events of Member : `viewMember`

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

<hr class="command-separator">

#### View Members of Event : `viewEvent`

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

<hr class="command-separator">

#### Find Member in member list : `findMember`

Finds and lists member(s) whose name(s) contain the provided `KEYWORD`.

Format: `findMember KEYWORD [MORE_KEYWORDS]`
* Finds and lists member(s) whose name(s) contain the specified `KEYWORD`.
* More than 1 `KEYWORD` can be provided to find more members.
* The `KEYWORD` must match minimally one of the words in the name of the member to be found, where capitalisation does not matter.

Examples:
* `findMember alice` displays the member(s) whose name(s) contain 'alice'
* `findMember alice bob charlie` displays the member(s) whose name(s) contain 'alice', 'bob' and/or 'charlie'.

<hr class="command-separator">

#### Find Event in event list : `findEvent`

Finds and lists event(s) which name(s) contain the provided `KEYWORD`.

Format: `findEvent KEYWORD [MORE_KEYWORDS]`
* Finds and lists event(s) which name(s) contain the specified `KEYWORD`.
* More than 1 `KEYWORD` can be provided to find more events.
* The `KEYWORD` must match minimally one of the words in the name of the event to be found, where capitalisation does not matter.

Examples:
* `findEvent party` displays the event(s) which name(s) contain 'party'
* `findEvent party marathon gaming` displays the event(s) which name(s) contain 'party', 'marathon' and/or 'gaming'.

<hr class="feature-class-separator">

### Utility commands

#### Undoing a command: `undo`

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

<hr class="command-separator">

#### Redoing a command: `redo`

Redoes a command that the user has undone previously.

Format: `redo`

<hr class="command-separator">

#### Clear all Members and Events: `clear`
Clears all Member and Event entries from CCACommander.

Format: `clear`

<hr class="command-separator">

#### Help: `help`

Displays a pop-out window that shows a link to this User Guide.

Format: `help`

<hr class="command-separator">

#### Exit: `exit`

Closes CCACommander and its display window.

Format: `exit`

<hr class="command-separator">

#### Recall previous commands

CCA Leaders can quickly recall previously entered commands for convenience. Example use cases include marking attendance for a group of members, editing slightly wrong details and more.

CCACommander stores almost all (see the info card below to see what commands are stored) commands entered by the user. Users can use the <kbd>↑</kbd> to <kbd>↓</kbd> key to navigate through the commands entered upon selecting the command box.

Example:
1. A CCA Leader has just marked attendance for the first member in the member list using the `editEnrolment m/1 e/1 r/Present` command.
2. The CCA Leader can then use <kbd>↑</kbd> to get `editEnrolment m/1 e/1 r/Present`
3. The CCA Leader can edit the member index to get `editEnrolment m/2 e/1 r/Present` to mark the second member as present.

<div markdown="block" class="alert alert-info">:information_source: CCACommander will not store 2 of the same commands if they were entered twice in a row. Likewise, "commands" containing exclusively of blank spaces will not be stored as well. 

Upon entering the <kbd>⌫ Backspace</kbd> or the <kbd>↵ Enter</kbd> key, users will have to re-navigate from the most recent command as it was treated as an edit or entry of command.
</div>

<hr class="command-separator">

#### Saving the data

CCACommander data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

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

## List of acceptable values

<table class="tg">
<thead>
  <tr>
    <th class="tg-0pky"><span>Command</span></th>
    <th class="tg-0pky"><span>Field</span></th>
    <th class="tg-0pky"><span>Acceptable values</span></th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0pky command" rowspan="6"><code>createMember</code> / <code>editMember</code></td>
    <td class="tg-0pky">MEMBER_NAME</td>
    <td class="tg-0pky">Only contain alphanumeric characters and spaces, and should not be blank</td>
  </tr>
  <tr>
    <td class="tg-0pky">GENDER</td>
    <td class="tg-0pky"><code>Male</code>, <code>Female</code>, <code>Others</code></td>
  </tr>
  <tr>
    <td class="tg-0pky">PHONE_NUMBER</td>
    <td class="tg-0pky">Only contain numbers, and <span>at least 3 digits long</span></td>
  </tr>
  <tr>
    <td class="tg-0pky">EMAIL</td>
    <td class="tg-0pky">An email with a valid extension (e.g. <code>@gmail.com</code>)</td>
  </tr>
  <tr>
    <td class="tg-0pky">ADDRESS</td>
    <td class="tg-0pky">Any non-blank values except the following prefixes: <code>n/</code>, <code>g/</code>, <code>p/</code>, <code>e/</code>, <code>a/</code>, <code>t/</code></td>
  </tr>
  <tr>
    <td class="tg-0pky">TAG</td>
    <td class="tg-0pky">Only contain alphanumeric characters with no spaces in between, but can be blank</td>
  </tr>
  <tr>
    <td class="tg-0pky command" rowspan="4"><code>createEvent</code> / <code>editEvent</code></td>
    <td class="tg-0pky">EVENT_NAME</td>
    <td class="tg-0pky">Only contain alphanumeric characters and spaces, and should not be blank</td>
  </tr>
  <tr>
    <td class="tg-0pky">LOCATION</td>
    <td class="tg-0pky"><span>Must not be blank </span>and can take in any values, except the following prefixes:<code>e/</code>, <code>l/</code>, <code>d/</code>, <code>t/</code></td>
  </tr>
  <tr>
    <td class="tg-0pky">DATE</td>
    <td class="tg-0pky">Must be a <span>valid date </span>in the format of <span>YYYY-MM-DD</span></td>
  </tr>
  <tr>
    <td class="tg-0pky">TAG</td>
    <td class="tg-0pky">Only contain alphanumeric characters with no spaces in between, but can be blank</td>
  </tr>
  <tr>
    <td class="tg-0pky command"><code>deleteMember</code> / <code>deleteEvent</code></td>
    <td class="tg-0pky">MEMBER_INDEX / EVENT_INDEX</td>
    <td class="tg-0pky">Must be a<span> positive integer</span> that is within the range of the length of the <span>currently displayed</span> member/event list</td>
  </tr>
  <tr>
    <td class="tg-0pky command" rowspan="3"><code>enrol</code> / <code>editEnrolment</code></td>
    <td class="tg-0pky">MEMBER_INDEX / EVENT_INDEX</td>
    <td class="tg-0pky">Must be a <span>positive integer</span> that is within the range of the length of the <span>currently displayed</span> member/event list</td>
  </tr>
  <tr>
    <td class="tg-0pky">NUMBER_OF_HOURS</td>
    <td class="tg-0pky">Must be a<span> positive integer </span>and must be <span>less than or equal to 2147483647</span></td>
  </tr>
  <tr>
    <td class="tg-0pky">REMARK</td>
    <td class="tg-0pky"><span>Must not be blank </span>and can take in any values, except the following prefixes:<code>m/</code>, <code>e/</code>, <code>h/</code>, <code>r/</code></td>
  </tr>
  <tr>
    <td class="tg-0pky command"><code>viewMember</code> / <code>viewEvent</code></td>
    <td class="tg-0pky">MEMBER_INDEX / EVENT_INDEX</td>
    <td class="tg-0pky">Must be a <span>positive integer</span> that is within the range of the length of the <span>currently displayed</span> member/event list</td>
  </tr>
  <tr>
    <td class="tg-0pky command"><code>findMember</code> / <code>findEvent</code></td>
    <td class="tg-0pky">KEYWORD</td>
    <td class="tg-0pky">Any non-blank values</td>
  </tr>
</tbody>
</table>
