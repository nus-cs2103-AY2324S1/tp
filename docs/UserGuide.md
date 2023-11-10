---
layout: page
title: User Guide
---
# CheckMate User Guide
CheckMate is a **desktop app for streamlining the process of room bookings for hotel employees, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, you can maximise real-time room searching, service scheduling, and amenity management. Optimized for administrative roles, it ensures that room allocation and guest needs are seamlessly addressed.

## Table of Contents

* Table of Contents
{:toc}

---
<details open>
<summary><strong>Quick start</strong></summary>
<div markdown="1">

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `checkmate.jar` from [here](https://github.com/AY2324S1-CS2103T-F10-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your CheckMate.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar checkmate.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![LandingGUI](images/Ui.png)

5. Clicking on a booking will allow you to see its details.
   ![ClickBooking.png](images%2FClickBooking.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all bookings.

    * `add r/1 d/2023-11-03 08:00 to 2023-11-04 11:00 n/Aikenot Dueet p/98765432 e/aikenotdueet@gmail.com` : Adds a booking for the room number `1` to the Bookings Book.

    * `delete 3` : Deletes the 3rd booking shown in the current list.

    * `clear` : Deletes all booking.

    * `undo` : Undoes the most recent deletion

    * `exit` : Exits CheckMate and closes the application.

6. Refer to the [Commands](#commands) below for details of each command.

</div>
</details>

---
<details open>
<summary><strong>Formats</strong></summary>
<div markdown="1">

## Input Formats

<div markdown="block" class="alert alert-info">

### Command Format

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [rm/REMARK]` can be used as `n/John Doe rm/Extra Pillows` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `r/ROOM d/BOOKING_PERIOD`, `d/BOOKING_PERIOD r/ROOM` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `undo` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Parameter Format

* `r/ROOM`: `ROOM` can be any integer from 1 to 500 inclusive.
* `d/BOOKING_PERIOD`: `BOOKING_PERIOD` is in the format `YYYY-MM-dd HH:mm to YYYY-M-dd HH:mm` where the end to the period must come after the start.
* `n/NAME`: `NAME` can be any String within 50 characters, and it should not be blank.
* `p/PHONE_NUMBER`: `PHONE_NUMBER` can be any positive integer between 3 and 15 digits in length (inclusive).
* `e/EMAIL`: `EMAIL` is in the format `local-part@domain` with a 50-character limit. The local-part should only contain
  alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start
  or end with any special characters. This is followed by a '@' and then a domain name. The domain name is made up of
  domain labels separated by periods. The domain name must end with a domain label that is supported:
    * gmail, yahoo, outlook, hotmail, icloud.
* `rm/REMARK`: `REMARK` can be any String less than or equal to 50 characters in length.

</div>
</details>

---
<details open>
<summary><strong>Commands</strong></summary>
<div markdown="1">

## Commands

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a booking: `add`

Adds a booking to the bookings book.

Format: `add r/ROOM d/BOOKING_PERIOD n/NAME p/PHONE_NUMBER e/EMAIL rm/REMARK`
![AddBooking.png](images%2FAddBooking.png)

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
The room number is used to assign the room type.
</div>

* Image above shows result of command `add r/11 d/2023-01-01 08:00 to 2023-01-02 12:00 n/John Doe p/98765432 e/johnd@gmail.com`

Examples:
* `add r/1 d/2023-01-01 08:00 to 2023-01-02 12:00 n/John Doe p/98765432 e/johnd@gmail.com`
* `add r/256 d/2023-02-01 15:00 to 2023-02-02 23:59 n/Betsy Crowe p/99990000 e/betsycrowe@gmail.com`

### Listing all bookings : `list`

Shows a list of all bookings in the bookings book.

Format: `list`

![ListCommand.png](images%2FListCommand.png)

* Example image above shows result of command `list`

### Editing a booking : `edit`

Edits an existing booking in the bookings book.

Format: `edit INDEX [r/ROOM] [d/BOOKING_PERIOD] [n/NAME] [p/PHONE] [e/EMAIL]`

![EditBooking.png](images%2FEditBooking.png)

* Example image above shows result of command `edit 1 p/91234567 e/johndoe@gmail.com`
* Edits the booking at the specified `INDEX`. The index refers to the index number shown in the displayed booking list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* The Room number is used to map to the Room type; there is no way to directly edit the room type without changing the Room number.

Examples:
*  `edit 1 p/91234567 e/johndoe@gmail.com` Edits the phone number and email of the 1st booking to be `91234567` and `johndoe@gmail.com` respectively.

### Locating bookings by name or room: `find`

Finds bookings whose bookings contain the room, name, or both.

Format: `find KEYWORD [MORE_KEYWORDS]`

![FindCommand.png](images%2FFindCommand.png)

* Example image above shows result of command `find 1`
* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name and room is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find 1 john` returns the booking which contains the room `1` and the name `john`.
* `find 1` returns the booking which contains the room `1`.
* `find john` returns the booking(s) where the name is `john`.

### Deleting a booking : `delete`

Deletes the specified booking(s) from the bookings book.

Format: `delete INDEX ...`

![DeleteBooking.png](images%2FDeleteBooking.png)

* Example image above shows result of command `delete 2`
* Deletes the booking at the specified `INDEX`.
* The index refers to the index number shown in the displayed booking list.
* The index **must be a positive integer** 1, 2, 3, …
* Multiple entries can be deleted at once by including their list index i.e. `delete 1 2 3 ...`
* Incorrect indices will not be deleted instead they will be flagged out to the user

Examples:
* `list` followed by `delete 2` deletes the 2nd booking in the bookingPeriod book.
* `find Betsy` followed by `delete 1` deletes the 1st booking in the results of the `find` command.

### Undo a deletion : `undo`

Adds the most recently deleted booking back to the system.

Format: `undo`

![UndoBooking.png](images%2FUndoBooking.png)

* Example image above shows the result of command `undo`
* If the deleted item was manually added back, then undo was performed, the deleted booking will not be added as it 
* already exists in CheckMate which compares two bookings using their room number and booking period.

### Clearing all entries : `clear`

Clears all entries from the bookingPeriod book.

Format: `clear`

![ClearCommand.png](images%2FClearCommand.png)

* Example image above shows the result of command `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

* The window will close automatically on command `exit`

### Saving the data

CheckMate data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

CheckMate data are saved automatically as a JSON file `[JAR file location]/data/checkmate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CheckMate will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Prefix Completion

Pressing `tab` while inputting a command will autocomplete relevant command prefix with examples. If no prefixes are found, your input will blink red.

Example:

* `add` followed by `space` and `tab` results in `add r/1`.
* `edit INDEX` followed by `space` and `tab` 
  * gives the unused prefix with the actual information for the booking at the index.

Prefix completions works with `add` and `edit` command and does not validate your input format.

</div>
</details>
--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CheckMate home folder.

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Commands Summary

| Action     | Format, Examples                                                                                                                                                                            |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add r/ROOM d/BOOKING_PERIOD n/NAME p/PHONE_NUMBER e/EMAIL rm/REMARK` <br> e.g., `add r/1 d/2023-01-01 08:00 to 2023-01-02 11:00 n/James Ho p/22224444 e/jamesho@gmail.com rm/Extra Towels` |
| **Clear**  | `clear`                                                                                                                                                                                     |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                         |
| **Undo**   | `undo`                                                                                                                                                                                      |
| **Edit**   | `edit INDEX [r/ROOM] [d/BOOKING _PERIOD] [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [rm/REMARK]`<br> e.g.,`edit 2 r/2 d/2023-01-01 08:00 to 2023-01-02 11:00 n/James Lee e/jameslee@gmail.com`     |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find 1`                                                                                                                                           |
| **List**   | `list`                                                                                                                                                                                      |
| **Help**   | `help`                                                                                                                                                                                      |
| **Exit**   | `exit`                                                                                                                                                                                      |
