---
layout: page
title: User Guide
---
# CheckMate User Guide
CheckMate is a **desktop app for streamlining the process of room bookings for hotel employees, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, you can maximise real-time room searching, service scheduling, and amenity management. Optimized for administrative roles, it ensures that room allocation and guest needs are seamlessly addressed.

## Table of Contents

1. [Quick Start](#quick-start)
2. [Input Formats](#input-formats)
   1. [`Command Format`](#command-format)
   2. [`Paramter Format`](#parameter-format)
3. [Commands](#commands)
   1. [`help`](#viewing-help--help)
   2. [`add`](#adding-a-booking-add)
   3. [`list`](#listing-all-bookings--list)
   4. [`edit`](#editing-a-booking--edit)
   5. [`find`](#locating-bookings-by-name-or-room-find)
   6. [`delete`](#deleting-a-booking--delete)
   7. [`clear`](#clearing-all-entries--clear)
   8. [`exit`](#exiting-the-program--exit)
3. [FAQ](#faq)
4. [Known Issues](#known-issues)
5. [Commands Summary](#commands-summary)
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `checkmate.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all bookings.

    * `add r/1 d/2023-01-01 to 2023-01-02 n/Aikenot Dueet p/995 e/aikenotduet@gmail.com` : Adds a booking for the room number `1` to the Bookings Book.

    * `delete 3` : Deletes the 3rd booking shown in the current list.

    * `clear` : Deletes all booking.

    * `exit` : Exits CheckMate and closes the application.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Input Formats

<div markdown="block" class="alert alert-info">

### Command Format

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `r/ROOM d/BOOKING_PERIOD`, `d/BOOKING_PERIOD r/ROOM` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Parameter Format

* `r/ROOM`: `ROOM` can be any integer from 1 to 500 inclusive.
* `d/BOOKING_PERIOD`: `BOOKING_PERIOD` is in the format `YYYY-MM-dd to YYYY-M-dd`.
* `n/NAME`: `NAME` can be any String.
* `p/PHONE_NUMBER`: `PHONE_NUMBER` can be any integer.
* `e/EMAIL`: `EMAIL` can be any String as long as it contains `@` inside the String.
* `t/TAG`: `TAG` can be any String.

--------------------------------------------------------------------------------------------------------------------

## Commands

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a booking: `add`

Adds a booking to the bookings book.

Format: `add r/ROOM d/BOOKING_PERIOD n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A booking can have any number of tags (including 0)
</div>

Examples:
* `add r/1 d/2023-01-01 to 2023-01-02 n/John Doe p/98765432 e/johnd@example.com`
* `add r/2 d/2023-02-01 to 2023-02-02 n/Betsy Crowe p/99990000 e/betsycrowe@example.com`

### Listing all bookings : `list`

Shows a list of all bookings in the bookings book.

Format: `list`

### Editing a booking : `edit`

Edits an existing booking in the bookings book.

Format: `edit INDEX [r/ROOM] [d/BOOKING_PERIOD] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the booking at the specified `INDEX`. The index refers to the index number shown in the displayed booking list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the booking will be removed i.e adding of tags is not cumulative.
* You can remove all the booking’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email bookingPeriod of the 1st booking to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd booking to be `Betsy Crower` and clears all existing tags.


### Locating bookings by name or room: `find`

Finds bookings whose bookings contain the room, name, or both.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find 1 john` returns the booking which contains the room `1` and the name `john`.
* `find 1` returns the booking which contains the room `1`.
* `find john` returns the booking(s) where the name is `john`.

### Deleting a booking : `delete`

Deletes the specified booking from the bookings book.

Format: `delete INDEX`

* Deletes the booking at the specified `INDEX`.
* The index refers to the index number shown in the displayed booking list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd booking in the bookingPeriod book.
* `find Betsy` followed by `delete 1` deletes the 1st booking in the results of the `find` command.


### Clearing all entries : `clear`

Clears all entries from the bookingPeriod book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Commands Summary

Action | Format, Examples
--------|------------------
**Add** | `add r/ROOM d/BOOKING_PERIOD n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​` <br> e.g., `add r/1 d/2023-01-01 to 2023-01-02 n/James Ho p/22224444 e/jamesho@example.com t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [r/ROOM] [d/BOOKING _PERIOD] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 r/2 d/2023-01-01 to 2023-01-02 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find 1`
**List** | `list`
**Help** | `help`
**Exit** | `exit`
