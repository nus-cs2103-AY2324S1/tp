---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# HealthSync User Guide

> "HealthSync will be your patient’s best friend, the frontdesk’s right hand, and time’s biggest foe." - Yi Chee, Developer, HealthSync

HealthSync is a **powerful desktop application designed specifically for clinic assistants in small private clinics.** It offers a unique combination of a Command Line Interface (CLI) and a Graphical User Interface (GUI) to efficiently manage and organize patient details. If you're a fast typist, HealthSync can streamline your workflow and help you handle patient information more effectively than traditional GUI apps.

## Why HealthSync?  

1. **Tailored for Front Desk Workers**: HealthSync is built with the needs of front desk workers in mind. It provides a user-friendly interface that simplifies patient management tasks, allowing you to focus on providing excellent service to patients.

2. **Fast and Efficient**: With HealthSync's CLI, you can quickly navigate through commands and perform actions without the need to rely solely on a mouse. This saves you valuable time and makes patient data management faster and more efficient.

3. **Comprehensive Patient Details**: HealthSync enables you to store and access comprehensive patient details, including personal information, medical history, appointments, and more. All the essential information you need is organized in one centralized location.

4. **Intuitive GUI Experience**: HealthSync's GUI complements the CLI by providing a visual representation of patient data. The GUI is intuitive and user-friendly, making it easy to view and update patient information with just a few keys.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## [Quick start](#quick-start)

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `healthsync.jar` from [here](https://github.com/AY2324S1-CS2103T-T14-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your HealthSync.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar healthsync.jar`
   command to run the application.<br>


   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui_v1.3.1.jpg)

5. Type the command in the command box and press Enter to execute it.
   e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients.

   * `add n/John Doe id/S8765432A p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 ap/17-10-2023 11:00 13:00 m/hypochondriac` : Adds a patient named `John Doe` with the relevant field details to HealthSync.

   * `delete n/Alex Yeoh` : Deletes Alex Yeoh's details from the current list.

   * `clear` : Deletes all patients.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## [Features](#features)

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* The word `or` indicates that at least one of the fields has to be supplied by the user.
  The output `or` will return the above field that was supplied by the user.
* `[field]` are optional tags that can be added after a command.

* `[field] …` indicate that multiple fields can be supplied by the user.

* `[field] …` can be in any order.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Common Shared Fields


The 2 identifying parameters of a patient are given below:

| Tag   | Representative Value         | Example Usage  | General Form in Commands |
|-------|------------------------------|----------------|--------------------------|
| `n/`  | Name                         | `n/Alex`       | `n/NAME`                 |
| `id/` | Identification Number (NRIC) | `id/S2345678A` | `id/IC_NUMBER`           |

1 or more identifying parameters must be specified in each command, unless stated otherwise.

`[field]` are common fields that can be specified behind commands. The common fields are:

| Tag    | Representative Value | Example Usage              | General Form in Commands | Remarks                                      |
|--------|----------------------|----------------------------|--------------------------|----------------------------------------------|
| `p/`   | Phone Number         | `p/91234567`               | `p/PHONE_NUMBER`         |                                              |
| `e/`   | Email Address        | `e/example@a.com`          | `e/EMAIL`                |                                              |
| `a/`   | Address              | `a/Location, Here Rd`      | `a/ADDRESS`              |                                              |
| `m/`   | Medical History      | `m/Asthmatic`              | `m/MEDICAL_HISTORY`      | Can have multiple of this field, including 0 |
| `ap/`  | Appointment          | `ap/2022-2-11 11:00 12:00` | `ap/APPT `               | Optional field                               |

### Auto Save

HealthSync data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.


### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `add`

Adds a patient into the program, with the given patient information.

* A patient's name and IC Number are required when creating a new patient entry.

Format: `add n/NAME id/IC_NUMBER [field] ...`

Example commands:
 * `add n/Aaron Tan Jun Jie id/S8943782H p/98114839 e/example@mailhere a/Serangoon HDB 123`


![result for 'add n/Aaron Tan Jun Jie id/S8943782H p/98114839 e/example@mailhere a/Serangoon HDB 123'](images/addResult.jpg)

Expected outputs when the command succeeds:
 * `Patient Aaron Tan Jun Jie has been added with the fields: id/S8943782H
    p/98114839 e/example@mailhere a/Serangoon HDB 123`

Expected outputs when the command fails:
 * `Unable to add the patient to the database: Patient already exists.`
 * `Unable to add the patient to the database: IC required.`

Tips:
* Use the shortcut `a` for faster data entry
* Double-check patient data to prevent unnecessary errors later on


### Listing all persons: `list`

Shows a list of all persons in the address book.

Format: `list`


![result for 'list'](images/listResult.jpg)


### Editing a patient's details: `edit`

Edits an existing patient's details in the address book.

 * Edits the person with the specified name or id.
 * If an invalid name or IC Number is passed, an error message will be logged.
 * At least one of the optional fields must be provided.
 * Existing fields will be updated to the input values.
 * If the fields do not exist, the corresponding field with details will be added.

Format: `edit n/NAME or id/IC_NUMBER [field] ...`

Example commands:
 * `edit n/Alex Yeoh p/91234567 e/alexyeoh@example.com`


![result for 'edit n/Alex Yeoh p/91234567 e/alexyeoh@example.com'](images/editResult.jpg)

Expected outputs when the command succeeds:
* `Patient Alex Yeoh has been updated with the fields: p/91234567 e/alexyeoh@example.com`

Expected outputs when command fails:
* `Unable to edit the patient: Patient identification does not exist.`

Tips:
* Use the shortcut `e` for faster data editing
* Update multiple fields in a single edit command to save time


### Undoing a command: `undo`

Undoes an undo-able command within the address book.

* An undo-able command includes an edit command, add commmand or delete command
* The command allows you to undo the last command or to undo a specific number of previous commands

Format:
* `undo` or `undo [number]`

Example commands:
*  `undo`
*  `undo 2`


![result for 'undo'](images/undoResult.jpg)

Expected outputs when the command succeeds:
* `The last command has been undone`
* `The last 2 commands have been undone`

Expected outputs when command fails:
* `There is no valid command to be undone`

Tips:
* Use the shortcut `u` for faster command undoing
* Undo multiple commands at once to save time


### Locating persons by name or IC Number: `find`

Searches the patient list for all patients matching the name or IC Number and returns their related information.

 * The search is case-insensitive. e.g `hans` will match `Hans`.
 * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
 * Only the name or IC number is searched.
 * Only full words will be matched e.g. `Han` will not match `Hans`.
 * For the name, only persons matching at least one keyword will be returned (i.e. `OR` search).
   e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`.

Format: `find n/NAME` *or* `id/IC_NUMBER`

Example commands:
* `find n/John` returns `john` and `John Doe`
* `find id/T0123456F` returns `Alex Yeoh`, with IC number `T0123456F` <br>


![result for 'find id/T0123456F'](images/findidT0123456FResult.jpg)

Expected outputs when the command succeeds:
 * `Patient n/NAME or id/IC_NUMBER: [field] …`

Expected output when the command fails:
 * `Unable to find the patient. Check if the patient’s information is correct.`

Tips:
* Use the shortcut `f` for faster patient-finding


### Preserving a `find` command result: `log`

Logs the result of the find command to the logger tab, which can be viewed at all times.

* Saving to the logger tab only works for results of the `find` command.
* The entire result will be saved.
* The result will be saved in the same order and format.
* Saving a new result clears the current saved result from the logger tab and replaces it.

Format: `log`

Example Command: `log` (after entering a FindCommand)


![result for 'log'](images/logResult.jpg)

Expected outputs when the command succeeds:
* `Results of the FindCommand have been saved to the logger tab.`

Expected output when the command fails:
* `There are no FindCommand results. There is nothing to be saved to the logger tab.`

Tips:
* Use the shortcut `lo` for faster patient-logging
* Use `log` command to save data you want to continue referring to
* `log` overwrites the data currently in the logger tab, so you do not need to perform clearing prior


### Adding a new `find` command result to the log: `alog`

Appends the new results of the most recent find command to the current data in the logger tab, which can be viewed at all times.

* Adding to the logger tab only works for results of the `find` command.
* The previously-saved result will remain the same.
* The entire new result will be saved below the previously-saved result.
* The result will be saved in the same order and format.

Format: `alog`

Example Command: `alog` (after entering a FindCommand)


![result for 'alog'](images/alogResult.jpg)

Expected outputs when the command succeeds:
* `Results of the FindCommand have been appended to the logger tab.`

Expected output when the command fails:
* `There are no FindCommand results. There is nothing to be saved to the logger tab.`


Tips:
* Use the shortcut `al` for faster log-appending
* Use `alog` command to save data you want to continue referring to, on top of some others
* `alog` does not overwrite the data and instead adds on to it, so you do not have to keep performing `log` to save more data


### Clearing data from the log: `clog`

Clears all current data in the logger tab.

Format: `clog`

Example Command: `clog`


![result for 'clog'](images/clogResult.jpg)

Expected output:
* `Logger tab has been cleared!`


Tips:
* Use the shortcut `cl` for faster log-clearing
* Use `clog` command if you do not need the data in the current logger tab anymore


### Deleting a person or field: `delete`

Deletes the specified person or the fields for the person from HealthSync.

* Deletes the person with the specified `n/NAME or id/IC_NUMBER`.
* The name or IC number must be a valid input.
* To delete a specified field only instead of the entire person, we indicate the field behind of the identification.

Format: `delete n/NAME or id/IC_NUMBER [field]`

Fields that can be deleted:
* Appointment: Include `ap/` behind delete command
* Medical History: Include `m/` behind delete command. Can delete specific Medical History. e.g `m/diabetes`

Example commands:
* `delete id/S9987362H` deletes all the details of the person with the specified IC number from HealthSync.

* `delete n/Alex Yeoh` deletes all the details of Alex Yeoh from HealthSync.
* `delete n/Alex Yeoh m/` deletes all Medical Histories of Alex Yeoh from his profile.

![result for 'delete n/Alex Yeoh'](images/deleteResult.jpg)

Expected outputs when the command succeeds:
 * `Deleted Patient:`  
 `Name: Alex Yeoh; NRIC: T0123456F; Phone: 87438807; Email: alexyeoh@example.com; Address: Blk 30 Geylang Street 29, #06-40 Appointment: 08-Aug-2023, 10:00 to 12:00; Medical Histories: [pneumonia][HIV][high blood pressure][AIDS][tachycardia][diabetes]; Tags: [friends]`  

 * `Deleted Patient's field:`  
 `Name: Bernice Yu; NRIC: S0123452F; Phone: 99272758; Email: berniceyu@example.com; Address: Blk 30 Lorong 3 Serangoon Gardens #07-18; Appointment: null; Medical Histories: [Diabetes]; Tags: [colleagues][friends]`

Tips:
* Use the shortcut `d` for faster patient-deleting
* Specify the medical history to be deleted using m/ if it's only the medical history data that is to be deleted

Expected outputs when the command fails:
 * `The given combination of Name and NRIC does not match any patient in the patients list. `
 * `Patient does not have an appointment to delete.`
 * `Patient does not have any medical histories to delete.`
 * `Patient does not have the medical histories specified.`

### Delete all patients: `clear`

Deletes all patients from the program.

Format: `clear`


![result for 'clear'](images/clearResult.jpg)


### Exiting the program: `exit`

Exits the program.

Format: `exit`


### Editing the data file

HealthSync data are saved automatically as a JSON file `[JAR file location]/data/healthsync.json`.
Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, HealthSync will discard all data and start with an empty
data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

--------------------------------------------------------------------------------------------------------------------

## [FAQ](#faq)

**Q**: What operating systems does HealthSync support?<br>
**A**: HealthSync is a cross-platform application and supports Windows, macOS, and Linux. Simply follow the
[installation guide](#quick-start) and download the jar file to use the application.

**Q**: What are the advantages of using a CLI interface in HealthSync over traditional GUI apps?<br>
**A**: HealthSync's CLI interface offers faster data entry and navigation for users who are comfortable with typing.
It streamlines tasks and provides a more efficient way to manage patient details.

**Q**: Can multiple users access Healthysync simultaneously, and how do I set up user accounts?<br>
**A**: HealthSync is currently an application for a single user. Hence, it cannot be accessed simultaneously
by different users.

**Q**: How do I import patient data from external sources into HealthSync?<br>
**A**: Datafile storing current patient data will be stored in `data/addressbook.json` by default under the same folder.
You may import patient data and store into that file. However, do adhere to the data format present in the current file.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
       the data of your previous HealthSync home folder.

**Q**: Can I use HealthSync offline, or does it require an internet connection?<br>
**A**: HealthSync is designed to work offline, ensuring you can access and update patient data even when you don't have
an internet connection.

**Q**: What happens if there is a system crash or power outage while I'm using HealthSync?<br>
**A**: HealthSync includes auto-save functionality to minimize data loss in case of unexpected events.
The app will attempt to recover your work upon restart.


--------------------------------------------------------------------------------------------------------------------

## [Known issues](#known-issues)

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only
   the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by
   the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## [Command summary](#command-summary)

| Action         | Shortcut | Format, Examples                                                                                                           |
|----------------|----------|----------------------------------------------------------------------------------------------------------------------------|
| **Add**        | `a`      | `add n/NAME id/IC_NUMBER [field] ...` <br> e.g., `add n/James Ho id/SXXXX123D p/91234567 a/A Estate, Clementi Rd, 1234665` |
| **Clear**      | `c`      | `clear`                                                                                                                    |
| **Delete**     | `d`      | `delete n/NAME [field]` *or* `delete id/IC_NUMBER [field]`<br> e.g., `delete n/John Doe ap/`                                |
| **Edit**       | `e`      | `edit n/NAME [field]` *or* `edit id/IC_NUMBER [field] ... `<br> e.g.,`edit n/James Lee e/jameslee@example.com`             |
| **Find**       | `f`      | `find n/NAME [field]` *or* `find id/IC_NUMBER [field]`<br> e.g., `find n/James Jake` *or* `find id/S872D`                  |
| **Help**       | `h`      | `help`                                                                                                                     |
| **List**       | `li`     | `list`                                                                                                                     |
| **Log**        | `lo`     | `log`                                                                                                                      |
| **Append Log** | `al`     | `alog`                                                                                                                     |
| **Clear Log**  | `cl`     | `clog`                                                                                                                     |
| **Exit**       | `ex`     | `exit`                                                                                                                     |
