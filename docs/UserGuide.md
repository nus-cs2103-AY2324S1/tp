---
layout: page
title: User Guide
---

FumbleLog is a **productivity desktop app for managing contacts and tracking events. It is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `fumblelog.jar` from [here](https://github.com/AY2324S1-CS2103T-T12-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your FumbleLog.

4. Open a command terminal, `cd` into the folder you put the fumblelog.jar file in, and use the `java -jar fumblelog.jar`
   command to run the application. A GUI similar to the below should appear in a few seconds.<br>
   Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type commands into the command box and press `Enter` to execute it. e.g. typing **`help`** and pressing `Enter` will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all persons.

   * `add n/John Doe` : Adds a person named `John Doe` to the FumbleLog persons list.

   * `delete 3` : Deletes the 3rd person shown in the current persons list.

   * `exit` : Exits the FumbleLog.

6. Refer to the [Features](#features) below for more details of FumbleLog's command.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [g/GROUP]` can be used as `n/John Doe g/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[g/GROUP]…​` can be used as ` ` (i.e. 0 times), `g/friend`, `g/friend g/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the user guide for help.

Format: `help`

## Commands for Persons

### Adding a person: `add`

Adds a person to the FumbleLog.

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [g/GROUP]…​`

<div markdown="span" class="alert alert-primary">
    :bulb: **Tip:**
    A person can have any number of groups (including 0)
</div>

<div markdown="span" class="alert alert-secondary">
    :bulb: **Tip:**
    The parameters are optional, but at least the name must be provided.
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/2023-09-30 g/friend g/partner`
* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567`
* `add n/Jonathan`

Acceptable values for each parameter:
* `n/NAME`: Name of the person (Compulsory)
* `p/PHONE_NUMBER`: A valid phone number (Optional)
* `e/EMAIL`: A valid email address (Optional)
* `a/ADDRESS`: Address of the person (Optional) 
* `b/BIRTHDAY`: A valid date in the format `yyyy-MM-dd` (Optional)
* `g/GROUP`: A group for the person to be categorised into (Optional)

Expected output when a command succeeds:
* Input: `add n/james p/999 e/example@gmail.com a/1 computing drive b/2001-09-20`
* Output: `New person added: james; Phone: 999; Email: example@gmail.com; Address: 1 computing drive; Birthday: Sep 09 2001; groups:; `

Expected output when the command fails
* `Invalid command format! add: Adds a person to the FumbleLog. Parameters: n/NAME [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [g/GROUP]…​`

### Editing a person : `edit`

Edits an existing person in the FumbleLog.

Format: `edit PERSON_INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [g/GROUP]…​`

* **At least one of the optional parameters must be provided.**
* Existing values will be updated to the input values.
* When editing groups, the existing groups of the person will be removed i.e adding of groups is not cumulative.
* You can remove all the person’s groups by typing `g/` without specifying any groups after it.
* When you edit a person's name, the person's name will be updated in all [events](#commands-for-events) that the person is assigned to.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower g/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing groups. Any events that Betsy Crower is assigned to is also updated with this new name.
*  `edit 3 n/Betsy Crower b/2023-09-29` Edits the name of the 3rd person to be `Betsy Crower` and changes the birthday to 29th Sep 2023. Any events that Betsy Crower is assigned to is also updated with this new name.

Acceptable values for each parameter:
* `PERSON_INDEX`: A positive integer
* `n/NAME`: Name of the person (Optional)
* `p/PHONE`: A valid phone number (Optional)
* `e/EMAIL`: A valid email address (Optional)
* `a/ADDRESS`: Address of the person (Optional)
* `b/BIRTHDAY`: A valid date in the format `yyyy-MM-dd` (Optional)
* `g/GROUP`: Text for the tag of the person (Optional)

Expected output when a command succeeds:
* Input: `edit 1 n/Alexa Yeoh`
* Output: `Edited Person: Alexa Yeoh; Phone: 87438807; Email: alexyeoh@example.com; Address: Blk 30 Geylang Street 29, #06-40; groups: [friends]`

Expected output when the command fails:
* `Invalid command format! edit: Edits the details of the person identified by the index number used in the displayed person list. Existing values will be overwritten by the input values. Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [g/GROUP]…​`

### Listing all persons : `list`

Shows a list of all persons in the FumbleLog.

Format: `list`

Expected output when a command succeeds:
* You should see a list of all persons under the persons column.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from FumbleLog.
When a person is deleted, any [events](#commands-for-events) that the person is assigned to will also be updated, i.e. the person will be unassigned from the event.

Format: `delete PERSON_INDEX`

* Deletes the person at the specified `PERSON_INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the FumbleLog.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command. i.e Any person named `Betsy` at index `1` will be deleted.

Acceptable values for each parameter:
* `PERSON_INDEX`: A positive integer

Expected output when a command succeeds:
* Input: `delete 1`
* Output: `Deleted Person: Roy Balakrishnan; Phone: 92624417; Email: royb@example.com; Address: Blk 45 Aljunied Street 85, #11-31; groups: [colleagues]`

Expected output when the command fails:
* `Invalid command format! delete: Deletes the person identified by the index number used in the displayed person list. Parameters: INDEX (must be a positive integer)Example: delete 1`

## Commands for Events

### Adding an event : `add_event`

Add an event to the events list in FumbleLog.

Format: `add_event m/EVENT_NAME d/DATE [s/START_TIME] [e/END_TIME] [n/PERSON_NAME]... [g/GROUP]...`

- `START_TIME` and `END_TIME` are optional.
- `PERSON_NAME` is optional and multiple persons can be added at once, however only persons that exist can be added.
- `GROUP` is optional, however only groups that exist can be added.
- The given `DATE`, `START_TIME` and `END_TIME` cannot be a time in the past.
- The given `START_TIME` must be before the given `END_TIME`.
- If the meeting is added successfully, it will automatically be sorted by date and time with the earliest meeting at the top of the list.
- All dates are to be in the format `yyyy-MM-dd`. i.e. 2023-10-05 for 5th Oct 2023
- All time are to be in the format `HHmm`. i.e. 1400 for 2pm

Example: 
* `add_event m/FumbleLog meeting d/2023-10-05 s/1500 e/1700 n/Ken g/CS2103T g/CS2101`
* `add_event m/FumbleLog presentation d/2023-10-30 g/Team2`

Acceptable values for each parameter:
* `m/EVENT_DETAILS`: Details of the event.
* `d/DATE`: A valid date in the format `yyyy-MM-dd`.
* `[s/START_TIME]`: A valid time in the format `HHmm`.
* `[e/END_TIME]`: A valid time in the format `HHmm`.
* `[n/PERSON_NAME]`: Name of the person to be assigned.
* `[g/GROUP]`: Name of the group to be assigned.

Expected output when the command succeeds:
* Input: `add_event m/FumbleLog meeting d/2023-10-05 s/1500 e/1700 n/Ken g/CS2103T g/CS2101`
* Output: `New event added: FumbleLog meeting; Date: 05 Oct 2023; Start Time: 15:00; End Time: 17:00; Persons involved: Ken; Groups involved: [CS2103T], [CS2101];`

Expected output when the command fails:
* `Invalid command format!
  add_event: Adds an event to the address book.
  Parameters: m/EVENT_NAME d/DATE [s/START_TIME] [e/END_TIME] [n/NAME]... [g/GROUP]...
  Example: add_event m/FumbleLog Meeting d/2020-10-30 s/1000 e/1200 n/Ken n/Yuheng g/Team2 `
* * `You cannot enter a time that is before the current time!` - When the given `DATE`, `START_TIME` and `END_TIME` is before the current time.
* `You cannot enter an end time that is before the start time!` - When the given `START_TIME` is after the given `END_TIME`.

### Editing an event : `edit_event`

Edits an existing event in FumbleLog.

Format: `edit_event EVENT_INDEX [m/MEETING_DETAILS] [d/DATE] [s/START_TIME] [e/END_TIME] [n/PERSON_NAME]... [u/PERSON_NAME]... [g/GROUP]... [ug/GROUP]...`

* **At least one of the optional parameters required.**
* `START_TIME` must be coupled with `END_TIME`.
* The input values will replace the existing values, except for `PERSON` AND `GROUP`.
* `PERSON` and `GROUP` edits are cumulative and will add to the current list of persons and groups.
  Use the unassign commands, i.e. `u/PERSON`, if you would like to unassign any person or group.
* If there are any changes to the meeting date and time, the meeting will be automatically sorted by date and time with the earliest meeting at the top of the list.
* All dates are to be in the format `yyyy-MM-dd`. i.e. 2023-10-05 for 5th Oct 2023
* All time are to be in the format `HHmm`. i.e. 1400 for 2pm

Examples:
* `edit_event 1 m/FumbleLog meeting d/2023-10-05 s/1500 e/1700`
* `edit_event 1 g/CS2103T g/CS2101`: Adds the groups CS2103T and CS2101 to the event.
* `edit_event 1 u/Ken`: Unassigns the person `Ken` from the event.

Acceptable values for each parameter:
* `EVENT_INDEX`: The index position of the event in the displayed event list.
* `[n/EVENT_DETAILS]`: Details of the event to be changed.
* `[d/DATE]`: A valid date in the format `yyyy-MM-dd`
* `[s/START_TIME]`: A valid time in the format `HHmm`
* `[e/END_TIME]`: A valid time in the format `HHmm`
* `[n/PERSON_NAME]`: Name of the person(s) to be assigned.
* `[u/PERSON_NAME]`: Name of the person(s) to be unassigned.
* `[g/GROUP]`: Name of the group(s) to be assigned.
* `[ug/GROUP]`: Name of the group(s) to be unassigned.

Expected output when the command succeeds:
* Input: `edit_event 1 m/tP week 3 meeting d/2023-10-05 s/1500 e/1700`
* Output: `Edited event: tP week 3 meeting; Date: 05 Oct 2023; Start Time: 15:00; End Time: 17:00; `

Expected output when the command fails:
* `Invalid command format!
  edit_event: Edits the details of the event identified by the index number used in the displayed event list.
  Existing values will be overwritten by the input values, except for the list of assigned persons and the list of assigned groups
  Parameters: INDEX (must be a positive integer) [m/EVENT_DETAILS] [d/DATE] [s/START_TIME] [e/END_TIME] [n/NAME]... [u/NAME]... [g/GROUP]... [ug/GROUP]...
  Example: edit_event 1 m/FumbleLog Meeting d/2023-10-13 n/Ken g/Team2 `
* * `You cannot enter a time that is before the current time!` - When the given `DATE`, `START_TIME` and `END_TIME` is before the current time.
* `You cannot enter an end time that is before the start time!` - When the given `START_TIME` is after the given `END_TIME`.

### Deleting an event : `delete_event`

Deletes a specified event from the FumbleLog.

Format: `delete_event EVENT_INDEX`

* Deletes the meeting at the specified `EVENT_INDEX`.

Examples:
* `delete_event 1`: Deletes the 1st event in the event list.

Acceptable values for each parameter:
* `EVENT_INDEX`: The index position of the event in the displayed event list.

Expected output when the command succeeds:
* Input: `delete_event 1`
* Output: `Deleted Event: tP week 3 meeting; Date: 05 Oct 2023; Start Time: 15:00; End Time: 17:00; Groups involved: [Team1];`

Expected output when the command fails:
* `Invalid command format!
  delete_event: Deletes the event identified by the index number used in the displayed event list.
  Parameters: INDEX (must be a positive integer)
  Example: delete_event 1`

## General commands

### Clearing all entries : `clear`

Clears all entries from the FumbleLog. Be very sure before using this command.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

# How we manage your data

### Saving the data

FumbleLog data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

FumbleLog data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FumbleLog will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FumbleLog home folder.

--------------------------------------------------------------------------------------------------------------------

# Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

# Command summary

### Commands for Persons
Action | Format, Examples
--------|------------------
**Add Person** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [g/GROUP]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 g/friend g/colleague`
**Edit Person** | `edit PERSON_INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GROUP]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Delete Person** | `delete PERSON_INDEX`<br> e.g., `delete 3`
**List Persons** | `list`
**Find Person** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`

### Commands for Events
Action | Format, Examples
--------|------------------
**Add Event** | `add_event m/EVENT_NAME d/DATE [s/START_TIME] [e/END_TIME] [n/PERSON_NAME]... [g/GROUP]...`<br> e.g., `add_event m/FumbleLog meeting d/2023-10-05 s/1500 e/1700 n/Ken g/CS2103T g/CS2101`
**Edit Event** | `edit_event EVENT_INDEX [m/MEETING_DETAILS] [d/DATE] [s/START_TIME] [e/END_TIME] [n/PERSON_NAME]... [u/PERSON_NAME]... [g/GROUP]... [ug/GROUP]...`<br> e.g., `edit_event 1 m/tP week 3 meeting d/2023-10-05 s/1500 e/1700 n/Ken g/CS2103T g/CS2101`
**Delete Event** | `delete_event EVENT_INDEX`<br> e.g., `delete_event 1`

### General commands
Action | Format, Examples
--------|------------------
**Clear** | `clear`
**Exit** | `exit`
**Help** | `help`
