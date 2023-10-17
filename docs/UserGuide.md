---
layout: page
title: User Guide
---

DoConnek Pro is a **desktop app** that helps **General Practitioner Clinic Management Staff** manage their **contact information for patients and specialists**. It is optimized for **Command Line Interface (CLI) users** while having a **Graphical User Interface (GUI)**. This allows frequent tasks to be completed faster by typing in commands.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `DocConnekPro.jar` from [here](https://github.com/AY2324S1-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your DocConnek Pro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar DocConnekPro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command input box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list -pa` : Lists all patients.

   * `add -pa n/John p/12345678 a/21 m/Osteoporosis m/Rheumatoid arthritis` : Adds a patient named `John` to the list.

   * `delete 3` : Deletes the 3rd person shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Commands acting on the address book must contain the `-pa` (for patient) and the `-sp` (for specialist) tag to specify which subset they 
would like the command to operate on.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -pa n/NAME`, `NAME` is a parameter which can be used as `add -pa n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [m/MEDICAL_HISTORY]` can be used as `n/John Doe m/Osteoporosis` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[m/MEDICAL_HISTORY]…​` can be used as ` ` (i.e. 0 times), `m/Osteoporosis`, `m/Osteoporosis m/Asthma` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient or specialist: `add`

Adds a patient or specialist to the address book.

Format (for patients): `add -pa n/NAME p/PHONE_NUMBER a/AGE [m/MEDICAL_HISTORY]...​`<br>
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of medical histories (including 0)
</div>

Format (for specialists): `add -sp n/NAME p/PHONE_NUMBER s/SPECIALISATION l/LOCATION`


Examples:
* `add -pa n/John p/12345678 a/21 m/Osteoporosis m/Rheumatoid arthritis`
* `add -sp n/Jane p/73331515 s/Dermatologist l/Ang Mo Kio`

### Listing patient or specialist records: `list`

Shows a list of all patients or specialists in stored records.

Format: `list -PERSON_TYPE`

Examples:
* `list -pa` Lists all patients in records.
* `list -sp` Lists all specialists in records.

### Locating persons by their attributes: `find`

Finds persons whose attributes contain any of the given keywords. 
Multiple attributes can be searched at once, the result will display any person
with all attributes containing any of the corresponding keywords in the command.

Format: `find -PERSON_TYPE [PREFIX/KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find -pa n/John` returns the patient `john` and the patient `John Doe`
* `find -sp n/alex david` returns the specialists `Alex Yeoh` and `David Li` 
* `find -sp n/Alex s/Orthopaedic` returns any specialists with the name `Alex` who has the `Orthopaedic` specialty
<br>

### Deleting a patient or specialist : `delete`

Deletes the specified patient or specialist from the stored records.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​ with a maximum value of the list size.

Examples:
* `list -pa` followed by `delete 2` deletes the 2nd patient in the listed patients. 
* `find -sp n/Betsy` followed by `delete 1` deletes the 1st specialist from the specialists listed in the `find` command.

### Clearing all entries : `clear`

Clears all entries from the stored records.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Save and Load Data

The patient and specialist data will automatically be saved to the device’s harddrive every time the data is updated, and will automatically be loaded when the user starts the application. The user does not need to manually save any data.

### Editing the data file

DoConnek Pro data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, DoConnek Pro will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### UI mock-up :

![UI mock-up](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add (patient)** | `add -pa n/NAME p/PHONE_NUMBER a/AGE [m/MEDICAL_HISTORY]...` <br> e.g., `add -pa n/John p/12345678 a/21 m/Osteoporosis m/Rheumatoid arthritis`
**Add (specialist)** | `add -sp n/NAME p/PHONE_NUMBER s/SPECIALISATION l/LOCATION` <br> e.g., `add -sp n/Jane p/73331515 s/Dermatologist l/Ang Mo Kio`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find -PERSON_TYPE KEYWORD [MORE_KEYWORDS]`<br> e.g., `find -pa n/James Jake p/73281193`
**List** | `list -pa`
**Help** | `help`
