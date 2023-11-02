# KeepInTouch User Guide

KeepInTouch is a **desktop app for managing contacts for people in the working industry (recruiters, engineers, etc.) as well as events for career purposes.
KeepInTouch is optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, KeepInTouch can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Viewing help : `help`](#viewing-help--help)
  * [Listing contacts: `list contact`](#listing-contacts--list-contact)
  * [Adding a contact: `add contact`](#adding-a-contact--add-contact)
  * [Deleting a contact: `delete contact`](#deleting-a-contact--delete-contact)
  * [Finding a contact: `find`](#finding-a-contact--find)
  * [Adding tags: `add tag`](#adding-tags--add-tag)
  * [Deleting tags: `delete tag`](#deleting-tags--delete-tag)
  * [Adding a note: `add note`](#adding-notes-to-a-contact--add-note)
  * [Deleting a note: `delete note`](#deleting-a-note--delete-note)
  * [Listing events: `list events`](#listing-events--list-events)
  * [Adding an event: `add event`](#adding-events--add-event)
  * [Deleting an event: `delete event`](#deleting-an-event--delete-event)
  * [Exit: `exit`](#exiting-the-program--exit)
  * [Other Features: _coming soon..._](#other-features)
* [FAQ](#faq)
* [Known Issues](#known-issues)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `keepintouch.jar` from [here](https://github.com/AY2324S1-CS2103T-W16-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your KeepInTouch app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar keepintouch.jar` command to run the application.
[//]: # (   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `delete contact CONTACT_ID`, `CONTACT_ID` is a parameter which can be used as `delete contact 1`.

* Items in square brackets are optional.<br>
  e.g `add contact -n NAME [-p PHONE_NUMBER] [-a ADDRESS] [-e EMAIL] [-t TAGNAME...]` can be used as `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com -t Frontend` or as `add contact -n Aaron -e aaron123@gmail.com`.

* `CONTACT_ID` is the number that is on the left of the contact's name in each contact card.

* Items with `…`​ after them can be used multiple times.<br>
  e.g. `[-t TAGNAME...]` can be used as `-t Frontend`, `-t Frontend -t Java` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -t NOTE_TITLE`, `-t NOTE_TITLE -n NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list contact`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a list of commands with the functionalities.

Format: `help`

### Listing contacts : `list contact`

Shows a list of all contacts in the contact list if tags not specified. 
Otherwise, shows a list of contacts which contains one of the specified tags. 

Format: `list contact [-t TAGNAME...]`

* Lists all contacts if no tags passed. 
* If tags argument passed, lists only contacts that contain one of the tags.

Examples:
* `list contact` to show all contacts.
* `list contact -t Recruiter` to show all contacts which have a recruiter tag.

### Adding a contact : `add contact`

Adds a contact to the contact list.

Format: `add contact -n NAME -p PHONE_NUMBER -a ADDRESS -e EMAIL [-t TAGNAME...]`

Examples:
* `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com`

### Deleting a contact : `delete contact`

Deletes the specified contact from the contact list.

Format: `delete contact CONTACT_ID`

Examples:
* `delete contact 1` deletes the first contact in the contact list.

### Finding a contact : `find`

Finds a contact by their name by matching keywords with the contact's name. Keywords are **case sensitive**.

Format: `find KEYWORD [OTHER_KEYWORDS...]`

Examples:
* `find Alex`

### Adding tags : `add tag`

Adds one or more tags to a contact.

Format: `add tag -id CONTACT_ID -t TAGNAME...`

* Adds one or more tags to a contact.
* Duplicates are accepted but only unique tags will be added.

Requirements:
* `TAGNAME` must be alphanumeric, with no spaces.

Examples:
* `add tag -id 1 -t Frontend` adds a tag with tag name "Frontend" to the first contact in the contact list.
* `add tag -id 1 -t Frontend -t Java` adds two tags with tag name "Frontend" and "Java" to the first contact in the contact list.


### Deleting tags : `delete tag`

Deletes one or more tags to a contact. 

Format: `delete tag -id CONTACT_ID -t TAGNAME...`

* Deletes one or more tags to a contact, regardless if the tag exists in the contact or not.
* Duplicates are accepted but only unique tags will be added.

Requirements:
* `TAGNAME` must be alphanumeric, with no spaces.

Examples:
* `delete tag -id 1 -t Frontend` deletes a tag with tag name "Frontend" from the first contact in the contact list.
* `add tag -id 1 -t Frontend -t Java` deletes two tags with tag name "Frontend" and "Java" from the first contact in the contact list.

### Adding notes to a contact : `add note`

Adds a note to a contact from the contact list.

Format: `add note -id CONTACT_ID -tit NOTE_TITLE -con NOTE_CONTENT`

Examples:
* `add note -id 1 -tit Meeting Topics -con The topic is about the framework design of the project`
* `add note -id 2 -tit Open Position -con Applications for SWE full-time positions will open soon`

### Deleting a note : `delete note`

Deletes the specified note from the contact list.

Format: `delete note -id CONTACT_ID -nid NOTE_ID`

* Deletes the note with the id `NOTE_ID` from the contact with id `CONTACT_ID`.

Examples:
* `delete note -id 1 -nid 1` deletes the first note from the first contact in the contact list.

### Listing events : `list events`

Shows a list of all events or events within a specified time interval.

Format: `list events [-descending] [-st filter_start_time] [-et filter_end_time]` (start time and end time are inclusive)

Arguments `-st` and `-et` must both present or both not present.
  - If both are not present, all events will be listed.
  - If both present, events within the time interval wil listed.

By default, the list of events are sorted by the start time in ascending order (i.e. from earlier to latest). If you want to use descending order, add `-descending` to the command.

Examples
* `list events`
* `list events -st 2023-11-01 -et 2023-11-02`
* `list events -descending -st 2023-11-01 -et 2023-11-02`

### Adding events : `add event`

Adds an event to a contact. The added event should not have clashes in timing with other existing events in the contact list.

Format: `add event -id CONTACT_ID -en EVENT_NAME -st START_TIME [-et END_TIME] [-loc LOCATION] [-info INFORMATION]`

Date-Time Format:
 - You can use one of the following formats for `START_TIME` and `END_TIME`:
    - Both date and time: `yyyy-MM-dd HH:mm[:ss]`
      - Example: `2023-10-12 20:05`, `2023-10-12 20:05:30`
   - Only date (Time will be defaulted to 00:00): `yyyy-MM-dd`
       - Example: `2023-10-12`
    - Only time (Date will be defaulted to the current date): `HH:mm[:ss]`
      - Example: `00:10`, `05:01:45`

Examples:
* `add event -id 1 -en Meeting with professor -st 12:00 -et 13:00 -loc COM 1 Basement -info Discuss the project implementation with the professor`
* `add event -id 2 -en Chat with TikTok recruiter -st 17:00`

### Deleting an event : `delete event`

Deletes the specified event from a contact.

Format: `delete event -id CONTACT_ID -eid EVENT_ID`

* Deletes the event with the id `EVENT_ID` from the contact `CONTACT_ID`.
* Note that `EVENT_ID` is the number that are in the left of the event line under each contact card.

Examples:
* `delete event -id 1 -eid 2` deletes the second event from the first contact in the contact list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

KeepInTouch data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Other Features

_More features coming coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: Why can't I run the app?<br>
**A**: Make sure you have Java `11` or above installed before running the app.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous KeepInTouch home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

_No known issues at the moment_

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action             | Format, Examples
-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**List Contact**   | `list contact [-t TAGNAME...]` <br> e.g., `list contact -t Recruiter`
**Add Contact**    | `add contact -n NAME -p PHONE_NUMBER -a ADDRESS -e EMAIL [-t TAGNAME...]` <br> e.g., `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com -t Frontend`
**Delete Contact** | `delete contact CONTACT_ID`<br> e.g., `delete contact 1`
**Find Contact**   | `find KEYWORD [OTHER_KEYWORDS...]`<br> e.g., `find Alex`
**Add Tag**        | `add tag -id CONTACT_ID -t TAGNAME...` <br> eg., `add tag -id 1 -t Frontend`
**Delete Tag**        | `delete tag -id CONTACT_ID -t TAGNAME...` <br> eg., `delete tag -id 1 -t Frontend`
**Add Note**       | `add note -id CONTACT_ID -t NOTE_TITLE -c NOTE_CONTENT` <br> e.g., `add note -id 2 -tit Open Position -con Applications for SWE full-time positions will open soon`
**Delete Note**    | `delete note -id CONTACT_ID -t NOTE_TITLE`<br> e.g., `delete note -id 2 -t Meeting Topics`
**List Events**    | `list events [-descending] [-st filter_start_time] [-et filter_end_time]`<br> e.g., `list events -descending -st 2023-11-01 -et 2023-11-02`
**Add Event**      | `add event -id CONTACT_ID -en EVENT_NAME -st START_TIME [-et END_TIME] [-loc LOCATION] [-info INFORMATION]` <br> e.g., `add event -id 1 -en Meeting with professor -st 12:00 -et 13:00 -loc COM 1 Basement -info Discuss the project implementation with the professor`
**Delete Event**   | `delete event -id CONTACT_ID -eid EVENT_ID`<br> e.g., `delete event -id 1 -eid 1`
**Help**           | `help`
