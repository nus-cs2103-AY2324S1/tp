---
layout: page
style: guide
title: User Guide
---
## CCACommander Ultra Promax Xtra 9000PLUS - User Guide
CCACommander Ultra Promax Xtra 9000PLUS is the one-stop app for CCA Heads to manage CCA members and events, optimised for CCA Heads who prefer to use command line interface.

1. Table of Contents
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

### Event Commands

As a CCA head, keeping track of event details is a major responsibility. CCACommander allows you to do so with the event-related commands below:

#### Create an Event : `createEvent`

Creating a new event is no longer a hassle as it can be done with just one simple command - `createEvent`!

Here is how you can use it:<br>
`createEvent n/EVENT_NAME l/LOCATION d/DATE [t/TAG]...`

Here is an example command where we create an event **named** Party held at the **location** Raffles Hall on the
**date** 2023-09-16, with **tag** Dinner and **tag** Sem1:<br>
`createEvent n/Party l/Raffles Hall d/2023-09-16 t/Dinner t/Sem1`

<figure>
    <img src="images/CreateEventPostCommand.png"
         alt="createEvent Post Command">
    <figcaption>After executing the `createEvent` command</figcaption>
</figure>

<hr class="command-separator">

#### Edits an Event : `editEvent`

If you forgot to note any important details, or realise that you made a mistake in any field of the event created,
the `editEvent` command is here to help

Here is how you can use it:<br>
`editEvent EVENT_INDEX [n/EVENT_NAME] [l/LOCATION] [d/DATE] [t/TAG]...`

Here is an example command where we edit an **event** at index 1, changing the **location** to be MBS and the **date** to be 2023-10-20.<br>
`editEvent 1 l/MBS d/2023-10-20`

<hr class="command-separator">

#### Delete an Event: `deleteEvent`

Are the number of events piling up? To clear the clutter, you can delete any unwanted event from CCACommander with `deleteEvent`!

Here is how you can use it:<br>
`deleteEvent EVENT_INDEX`

Here is an example command where we delete the event at index 1:<br>
`deleteEvent 1`

<div markdown="block" class="alert alert-primary">:bulb: The `EVENT_INDEX` parameter refers to the index number shown in the **currently displayed** event list.
</div>

<hr class="feature-class-separator">

### Enrolment Commands

Enrolments are a way for you to manage your members’ involvement in your events. The enrolment commands below are the bread-and-butter for your participation tracking needs:

#### Enrol a Member to an Event: `enrol`

Now that you have added all your members and all your events, keeping track of your members’ attendance to any event is as simple as using the `enrol` command!

Here’s how you can use it:<br>
`enrol m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]`

Here is an example command where we want to enrol the **member** at index 1 to the **event** at index 1, where our member
has contributed 2 **hours**, and we want to make a **remark** on their role as a photographer:<br>
`enrol m/1 e/1 h/2 r/Role: Photographer`

<hr class="command-separator">

#### Edit an enrolment: `editEnrolment`
Accidentally mixed up two members, and gave them the wrong number of hours or wrong remarks for the event they are involved in?
You can fix this with no problem by using the `editEnrolment` command!

Here’s how you can use it:<br>
`editEnrolment m/MEMBER_INDEX e/EVENT_INDEX [h/NUMBER_OF_HOURS] [r/REMARK]`

Here is an example command where we want to edit the enrolment of the member at index 1 to the event at index 2,
changing the number of hours to 5 and the remark to reflect the member’s role as an exco member:<br>
`editEnroment m/1 e/2 h/5 r/Role: Exco`

<hr class="command-separator">

#### Unenrol a Member from an Event: `unenrol`
If your member is no longer involved in a particular event, you can easily remove them from the event by using the `unenrol` command!

Here’s how you can use it:<br>
`unenrol m/MEMBER_INDEX e/EVENT_INDEX`

Here is an example command where we want to unenrol the **member** at index 1 from the **event** at index 1:<br>
`unenrol m/1 e/1`

<div markdown="block" class="alert alert-primary">:bulb: The `MEMBER_INDEX`/`EVENT_INDEX` parameter refers to the index number shown in the **currently displayed** member/event list.
</div>
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
