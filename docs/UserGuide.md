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
  * [Adding a person: `add contact`](#adding-a-person--add-contact)
  * [Listing all persons: `list contact`](#listing-all-persons--list-contact)
  * [Deleting a person: `delete contact`](#deleting-a-person--delete-contact)
  * [Adding a note: `add note`](#adding-notes-to-a-contact--add-note)
  * [Listing all notes: `list notes`](#listing-all-notes--list-notes)
  * [Deleting a note: `delete note`](#deleting-a-note--delete-note)
  * [Adding an event: `add event`](#adding-events--add-event)
  * [Listing all events: `list events`](#listing-all-events--list-events)
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
   Some example commands you can try:

   * `list contact` : Lists all contacts.

   * `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com` : Adds a contact named `Aaron` along with some contact details.

   * `delete contact Aaron` : Deletes a contact with the name Aaron.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `delete contact NAME`, `NAME` is a parameter which can be used as `delete contact Aaron`.

* Items in square brackets are optional.<br>
  e.g `add contact -n NAME [-p PHONE_NUMBER] [-a ADDRESS] [-e EMAIL]` can be used as `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com` or as `add contact -n Aaron -e aaron123@gmail.com`.

* `CONTACT_ID` is the number that is on the left of the person's name in each person card.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -t NOTE_TITLE`, `-t NOTE_TITLE -n NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list contact`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a list of commands with the functionalities.

Format: `help`


### Adding a person: `add contact`

Adds a person to the contact list.

Format: `add contact -n NAME -p PHONE_NUMBER -a ADDRESS -e EMAIL`

<box type="tip" seamless>

</box>

Examples:
* `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com`

### Listing all persons : `list contact`

Shows a list of all persons in the contact list.

Format: `list contact`

### Deleting a person : `delete contact`

Deletes the specified person from the contact list.

Format: `delete contact NAME`

* Deletes the person with the name `NAME`.

Examples:
* `list contact` followed by `delete contact Aaron` deletes the contact with the name Aaron.

### Adding notes to a contact: `add note`

Adds a note to a contact from the contact list.

Format: `add note -id CONTACT_ID -tit NOTE_TITLE -con NOTE_CONTENT`

Examples:
* `add note -id 1 -tit Meeting Topics -con The topic is about the framework design of the project`
* `add note -id 2 -tit Open Position -con Applications for SWE full-time positions will open soon`

### Listing all notes : `list notes`

Shows a list of all notes from all contacts in the contact list.

Format: `list notes`

### Deleting a note : `delete note`

Deletes the specified note from the contact list.

Format: `delete note -id CONTACT_ID -nid NOTE_ID`

* Deletes the note with the id `NOTE_ID` from the contact with id `CONTACT_ID`.

Examples:
* `list note` followed by `delete note -id 1 -nid 1` deletes the note with title Meeting Topics from the contact with name Aaron.

### Adding events: `add event`

Adds an event to a contact.

Format: `add event -id CONTACT_ID -en EVENT_NAME -st START_TIME [-et END_TIME] [-loc LOCATION] [-info INFORMATION]`

Date-Time Format:
 - You can use one of the following formats for start time and end time:
    - Date and time: `yyyy-MM-dd HH:mm[:ss]`
      - Example: `2023-10-12 20:05`, `2023-10-12 20:05:30`
   - Only date (Time will then refers to 00:00): `yyyy-MM-dd`
       - Example: `2023-10-12`
    - Only time (Date will then refers to the current date): `HH:mm[:ss]`
      - Example: `00:10`, `05:01:45`

Examples:
* `add event -id 1 -en Meeting with professor -st 12:00 -et 01:00 -loc COM 1 Basement -info Discuss the project implementation with the professor`
* `add event -id 2 -en Chat with TikTok recruiter -st 17:00`

### Listing all events : `list events`

Shows a list of all events.

Format: `list events`

### Deleting an event : `delete event`

Deletes the specified event from a contact.

Format: `delete event -id CONTACT_ID -eid EVENT_ID`

* Deletes the event with the id `EVENT_ID` from the contact `CONTACT_ID`.
* Note that `EVENT_ID` is the number that are in the left of the event line under each person card.

Examples:
* `list events` followed by `delete event -id 1 -eid 2` deletes the second event under contact that has id `1`.

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
**Add Contact**    | `add contact -n NAME -p PHONE_NUMBER -a ADDRESS -e EMAIL` <br> e.g., `add contact -n Aaron -p 12345678 -a Baker Street 12 -e aaron123@gmail.com`
**Delete Contact** | `delete contact NAME`<br> e.g., `delete contact Aaron`
**List Contact**   | `list contact`
**Add Note**       | `add note -n NAME -t NOTE_TITLE -c NOTE_CONTENT` <br> e.g., `add note -n Daniel -t Open Position -e Applications for SWE full-time positions will open soon`
**Delete Note**    | `delete note -n NAME -t NOTE_TITLE`<br> e.g., `delete note -n Aaron -t Meeting Topics`
**List Notes**     | `list notes`
**Add Event**      | `add event -id CONTACT_ID -en EVENT_NAME -st START_TIME [-et END_TIME] [-loc LOCATION] [-info INFORMATION]` <br> e.g., `add event -id 1 -en Meeting with professor -st 12:00 -et 01:00 -loc COM 1 Basement -info Discuss the project implementation with the professor`
**Delete Event**   | `delete event -id CONTACT_ID -eid EVENT_ID`<br> e.g., `delete event -id 1 -eid 1`
**List Events**    | `list events`
**Help**           | `help`
