---
layout: page
title: UNOFAS User Guide
---

UNOFAS (UNO: One FAS: Financial Advisors app) is a **desktop app for Financial Advisors to manage client's contacts,
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a
Graphical User Interface (GUI). If you can type fast, UNOFAS can help you manage and retrieve client's information
better than traditional GUI apps.

This guide provides you with comprehensive instructions on utilizing UNOFAS. It also serves as a mode of referral to
help learn the various commands required to effectively learn and be able to integrate the application to your daily
use as financial advisors. To help you make the most of this resource, utilize the Table of Contents provided below.
Click on the relevant links to easily navigate through the guide and access the information you need.

## Table of Contents
* [Quick Start](#quick-start)
* [UI Components](#ui-components)
  * [General UI Information](#general-ui-information)
  * [Contact Card](#contact-card)
  * [Appointment Card](#appointment-card)
* [Features](#features)
  * [Help](#viewing-help--help)
  * [Add](#adding-a-person--add)
  * [List](#listing-all-persons--list)
  * [Edit](#editing-a-person--edit)
  * [Find](#locating-persons-by-name-financial-plan-andor-tag--find)
  * [Gather](#gathering-emails-of-matching-persons--gather)
  * [Schedule](#scheduling-an-appointment--schedule)
  * [Complete](#completing-an-appointment--complete)
  * [Delete](#deleting-a-person--delete)
  * [Clear](#clearing-all-entries--clear)
  * [Sort](#sorting-of-data--sort)
  * [Exit](#exiting-the-program--exit)
* [Saving the data](#saving-the-data)
* [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Known Issues](#known-issues)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `unofas.jar` from [here](https://github.com/AY2324S1-CS2103T-F12-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your UNOFAS.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar unofas.jar` command
   to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`
   to the Contact Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## UI Components

![generalUi](images/generalUi.png)

### General UI information

| Component            | Purpose                                                |
|----------------------|--------------------------------------------------------|
| **Navigation Bar**   | Allows you to exit UNOFAS or view help                 |
| **Command Line**     | Location to enter commands                             |
| **Result Line**      | Displays the result after a command is entered         |
| **Contact List**     | Displays clients                                       |
| **Appointment List** | Displays appointments                                  |
| **Contact Card**     | Displays detailed information about a patient          |
| **Appointment Card** | Displays detailed information about an appointment     |
| **Save Location**    | Displays the location where your UNOFAS data is stored |

### Contact Card

![contactCardUi](images/contactCardUi.png)

* Shows the client's name, phone number, financial plan(s) (if any), address, email address, next-of-kin name, next-of-kin phone number, appointment (if any) and tag(s) (if any).

### Appointment Card

![appointmentCardUi](images/appointmentCardUi.png)

* Shows the appointment name, client allocated to the appointment and the appointment date and time.

--------------------------------------------------------------------------------------------------------------------

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Unless explicitly allowed, blank inputs or inputting any number of spaces as an argument for a field is invalid.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Any input parameters that do not adhere to the accepted values will result in the command
  **failing and not executing**.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will
  be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple
  lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</div>

--------------------------------------------------------------------------------------------------------------------
# Argument Summary

Below is a table summarising common arguments used in `add`, `edit`, `find`, `schedule` etc. Refer to the table below
to view the arguments' prefix, and their acceptable values. Unless specified, having only space characters i.e. an empty
value, is not an acceptable value and will result in a warning.

| Prefix | Argument              | Acceptable Values                                                      |
|--------|-----------------------|------------------------------------------------------------------------|
| -      | INDEX                 | Number (1 to current size of the contact book)                         |
| `n/`   | NAME                  | Alphabets, numbers, and space characters only                          |
| `p/`   | PHONE_NUMBER          | Numbers only and at least 3 digits long                                |
| `e/`   | EMAIL                 | Alphabets, numbers, and symbols only in a valid email format           |
| `a/`   | ADDRESS               | Any value is possible                                                  |
| `nk/`  | NEXT_KIN              | Alphabets, numbers, and space characters only                          |
| `nkp/` | NEXT_KIN_PHONE        | Numbers only and at least 3 digits long                                |
| `fp/`  | FINANCIAL_PLAN        | Alphabets, numbers, and space characters only. Empty value is accepted |
| `t/`   | TAG                   | Alphabets and numbers only. Empty value is accepted                    |
| `ap/`  | APPOINTMENT_NAME      | Alphabets, numbers, and space characters only                          |
| `d/`   | APPOINTMENT_DATE      | Format: dd-MM-yyyy (e.g., 31-12-2023)                                  |
| `d/`   | APPOINTMENT_DATE_TIME | Format: dd-MM-yyyy HH:mm (e.g., 31-12-2023 14:30)                      |
| -      | KEYWORD               | `name` or `appointment`                                                |

-----------------------
### Viewing help : `help`

Shows a message explaining how to access the help page, as well as a list of available keywords.

![help message](images/helpMessage.png)

Format: `help`

---------------------------
### Adding a person : `add`

Add a client’s contacts to contact book (name, phone number, email, home address, next-of-kin name, next-of-kin phone
number) into contact book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS nk/NEXT_KIN nkp/NEXT_KIN_PHONE [fp/FINANCIAL_PLAN]… [t/TAG]…​`

* Adding a person with the exact same name (case-sensitive) as a person currently in the contact book counts as a
    duplicate and will cause the command to fail. Duplicate information in other ways does not count as a duplicate
    person.
* To prevent accidentally adding duplicates, you can use [Find](#locating-persons-by-name-financial-plan-andor-tag--find)
    to check if you have already added the person already.
* After performing an add, the list displayed will be reset to display all clients.

Acceptable Values: Refer to [Argument Summary](#argument-summary).

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of Financial Plans (including 0).
A person can have any number of tags (including 0).
</div>

Examples:
* `add n/John p/80101010 e/johndoe@gmail.com a/Punggol Central Blk 444 #15-32 820123 nk/Brennan nkp/82020202 t/80yo fp/Financial Plan C`

Successful Output: `New person added: John;
Phone: 80101010;
Email: johndoe@gmail.com;
Address: Punggol Central Blk 444 #15-32 820123;
Next-of-kin Name: Brennan;
Next-of-kin Phone: 82020202;
Appointment: No Appointment made!;
Financial Plans: [Financial Plan C];
Tags: [80yo]`

![result for 'add n/John p/80101010 e/johndoe@gmail.com a/Punggol Central Blk 444 #15-32 820123 nk/Brennan nkp/82020202 t/80yo fp/Financial Plan C'](images/addUi.png)

<div markdown="span" class="alert alert-primary">:information_source:
**Do note** that it is possible to add a client's contact with multiple tags by duplicating the `t/` prefix. The same can be done for financial plans with the `fp/` prefix.
</div>

------------------
### Listing all persons : `list`

Display a list of all the clients and their contact details that are currently stored in the address book. 

Format: `list`

Successful Output: `Listed all persons`

![result for 'list'](images/ListUi.png)

--------------------------------
### Editing a person : `edit`

Edit clients contact fields using an index followed by the updated details.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [nk/NEXT_KIN] [nkp/NEXT_KIN_PHONE] [fp/FINANCIAL_PLAN]…​ [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* Editing the name of a person to be the exact same name as another person currently in the contact book
(case-sensitive) counts as a duplicate and will cause the command to fail. Duplicate information in other ways does
not count as a duplicate person.
* When editing financial plans or tags, the existing financial plans or tags of the person will be removed i.e. adding
of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* You can remove all the person’s financial plans by typing `fp/` without
  specifying any financial plans after it.
* A person's appointment cannot be edited in this manner. Refer to [Schedule](#scheduling-an-appointment--schedule).
* After performing an edit, the list displayed will be reset to display all clients.

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Examples:
*  `edit 4 n/john doe a/23 woodlands ave 123` Edits the name and address of the 1st person to be `john doe` and `woodlands ave 123` respectively.

Successful Output:
`Edited Person: john doe;
Phone: 80101010;
Email: johndoe@gmail.com;
Address: 23 woodlands ave 123;
Next-of-kin Name: Brennan;
Next-of-kin Phone: 82020202;
Appointment: No Appointment made!;
Financial Plans: ;
Tags:`

![result for 'edit 4 n/john doe a/23 woodlands ave 123'](images/editUi.png)

<div markdown="span" class="alert alert-primary">:information_source:
**Do note** that it is possible to add a client's contact with multiple tags by duplicating the `t/` prefix. The same can be done with for financial plans with the `fp/` prefix.
</div>

---------------
### Locating persons by name, financial plan, and/or tag : `find`

Finds persons whose names, tags or financial plans contain any of the specified keywords.

Format: `find [n/NAME]…​ [fp/FINANCIAL_PLAN]…​ [t/TAG]…​`

* At least one of the optional fields must be provided.
* This command will ignore other prefixes. Using them anyway can cause undefined behaviour.
* The search is case-insensitive. e.g `hans` will match `Hans`.
* For names, only full words will be matched e.g. `Han` will not match `Hans`.
* Calling this command on a sorted list will retain the sorted quality of the list.
* For financial plans and tags, any substring will be matched e.g. `Senior` will match `SuperSenior`.
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `n/Hans n/Bo` will return `Hans Gruber`, `Bo Yang`.
* Upon successful execution of the command, only the relevant contact details will be reflected in the **Contacts list**. The relevant appointment details will also be updated in the **Appointments list**.

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Examples:
* `find n/john n/charlie` returns `Charlie`, `john doe`<br>

  ![result for 'find john charlie'](images/findJohnCharlieResult.png)

### Gathering emails of matching persons : `gather`

Gathers all the emails of persons with a desired financial plan or tag.

Format: `gather fp/FINANCIAL PLAN` or `gather t/TAG`

* Generates a list of emails separated by semicolons, making it convenient for copying and pasting into the recipient input of an email application. 
  This function currently works for gmail and outlook but might not work for all email services.
* Either **Financial Plan or Tag** can be searched at once, but **not both**.
* The search is case-insensitive e.g. `financial` will match `FINANCIAL` or `Financial`.
* A person's email will be gathered if the prompt matches a substring of their financial plan or tag.

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Examples:
* `gather t/Elderly`
* `gather fp/Financial Plan A`

Successful Output:
`davidmiller@gmail.com; bob@example.com;`

![result for`gather fp/Financial Plan A'](images/gatherUi.png)

------------
### Deleting a person : `delete`

Deletes the client contact from the contact book by their index.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Examples:
* `list` followed by `delete 1` deletes the 1st person in the contact book.

Successful Output:
`Deleted Person: David;
Phone: 93234567;
Email: davidmiller@gmail.com;
Address: Bishan Blk 999 #08-15 569874;
Next-of-kin Name: Olivia;
Next-of-kin Phone: 56981234;
Appointment: Meeting, 15-12-2023 17:30;
Financial Plans: [Financial Plan A][Financial Plan B];
Tags: `

----------
### Scheduling an Appointment : `schedule`

Schedules an appointment for a client using an index followed by the appointment details.

Format: `schedule INDEX ap/APPOINTMENT_NAME d/APPOINTMENT_DATE_TIME`

- Schedules appointment with the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
- **Both appointment name and date-time** must be provided.
- Upon successful execution of the command, the scheduled appointment details will be updated in the **Contacts list**. The appointment details will also be updated in the **Appointments list**.

<div markdown="span" class="alert alert-primary">:information_source:
If there is an existing appointment with the person when the command is executed, you can replace it with a new appointment by **clicking confirm** or **pressing the enter key** when the prompt is given.
</div>

  ![schedule prompt](images/schedulePrompt.png)

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Example:
- `schedule 1 ap/Annual review of financial goals d/20-11-2023 15:00`

Successful Output: \
For overridden appointment: `Appointment updated!`\
For new appointment: `New appointment added: David; Phone: 93234567; Email: davidmiller@gmail.com; Address: Bishan Blk 999 #08-15 569874; Next-of-kin Name: Olivia; Next-of-kin Phone: 56981234; Appointment: Annual review of financial goals, 20-11-2023 15:00; Financial Plans: [Financial Plan A][Financial Plan B]; Tags:` 

![result for`schedule 1 ap/Annual review of financial goals d/20-11-2023 15:00'](images/scheduleUi.png)

<div markdown="span" class="alert alert-primary">:information_source:
Upon triggering the overriding prompt, until confirmation or cancellation of command on the prompt, usage of the application
is not allowed (including trying to exit the program). 
</div>

----------
### Completing an Appointment : `complete`

Completes an appointment either with the person at the specified `INDEX` or complete all appointments with matching `APPOINTMENT_DATE`.

Format: `complete [INDEX] [d/APPOINTMENT_DATE]`

- Either an **index or appointment date** must be provided for command to execute, but **not both**.
- If user inputs an `INDEX`, command will complete appointment with the person at the specified `INDEX`. The index refers to the index number shown in the **Contacts list**.
- If user inputs an `APPOINTMENT_DATE`, the command will complete all appointments in address book that have a date
matching the one input by user. This can allow the user to clear all his/her appointments finished throughout the
entire day quickly.

<div markdown="span" class="alert alert-primary">:information_source:
**Note** that an appointment's date is considered to be a match with user's input `APPOINTMENT_DATE` if the year, month and day are the same. Time of the appointment does not matter in this command.
</div>

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Examples:
- `complete 1`
- `complete d/01-05-2023`

Successful Output: `Appointment(s) Completed!`

![result for 'complete 1'](images/completeUi.png)

----------
### Clearing all entries : `clear`

Clears all entries from the contact book. UNOFAS will ask for confirmation first to ensure it is not a mistake. Click
the clear button to confirm.

Format: `clear`

Example:
* `clear`

![confirm clear window](images/confirmClear.png)

<div markdown="span" class="alert alert-primary">:information_source:
Upon entering the `clear` command, until confirmation or cancellation of command on the prompt, usage of the application
is not allowed (including trying to exit the program). 
</div>

----------------------------
### Sorting of data : `sort`

Sorts all the entries with predefined sorting functionalities. After sorting the list, the ordering of the entries
will be changed. As a result, performing any operations that require indexing (such as delete, add or edit), will reference the new ordering that is currently displayed on the screen.

**Here are the current predefined sorting functions that have been implemented**

* `name` : sorts list by lexicographical ordering of name (case-insensitive).
* `appointment`: sorts list by appointment timing in order of the earliest appointment first. If no appointment is found, the remaining persons without an appointment will be displayed in an arbitrary order, based on the reordering of the previous sorting functions applied.

Format: `sort KEYWORD`

* Calling this command after a Find command will preserve the results filtered by the Find command.

Acceptable Values: Refer to [Argument Summary](#argument-summary).

Example: `sort name` performs sorting by lexicographical ordering

Successful Output: `4 persons listed!`

![result for`sort name'](images/sortUi.png)

------------
### Exiting the program : `exit`

Exits the program.

Format: `exit`

------------
### Saving the data

UNOFAS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

------------
### Editing the data file

UNOFAS data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning"> :exclamation: **Caution:**
If your changes to the data file makes its format invalid, UNOFAS will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous UNOFAS home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **When sorting the list**, we have chosen to not implement returning sorted list to original ordering due to the lack of necessity. However, due to feedback, we will implement this in the next release to enable users to return list to original order should they wish to.
3. **It is possible to add appointments with dates** before the current date and time.
4. **On macOS Systems**, you have to use the cursor to manually click the confirm or cancel buttons for the overriding and clear prompts. In contrast, Windows users can choose to hit enter to confirm execution of command.
5. **No current method to de-conflict clashing appointments**. Users should be advised to check the appointment sidebar to ensure appointments do not clash with each other. The appointment sidebar may display appointments with the same date and time in a different order after adding a new appointment and subsequently reopening the app. This will be resolved when fixing issue 6a.
6. **The appointment sidebar** may display appointments with the same date and time in a different order after adding a new appointment and subsequently reopening the app. This will be resolved when fixing issue 6.
7. **Checking for duplicate persons** is done by checking their full name, case-sensitive. The future plan is to do this by checking of phone number as it is less likely 2 people share the same phone number than compared to name.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format, Examples                                                                                                                                                                                                                    |
|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS nk/NEXT_KIN nkp/NEXT_KIN_PHONE [fp/FINANCIAL_PLAN]…​ [t/TAG]…​` <br> e.g., `add n/John p/80101010 e/johndoe@gmail.com a/Punggol Central Blk 444 #15-32 820123 nk/Brennan nkp/82020202` |
| **Clear**    | `clear`                                                                                                                                                                                                                             |
| **Delete**   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                 |
| **Edit**     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [nk/NEXT_KIN] [nkp/NEXT_KIN_PHONE] [fp/FINANCIAL_PLAN]…​ [t/TAG]…​`<br> e.g.,`edit 1 n/john doe a/23 woodlands ave 123`                                                 |
| **Find**     | `find [n/NAME]…​ [fp/FINANCIAL_PLAN]…​ [t/TAG]…​`<br> e.g., `find n/James n/Jake`                                                                                                                                                   |
| **Gather**   | `gather [fp/FINANCIAL PLAN]` or `gather [t/TAG]` <br> e.g., `gather fp/Basic Insurance Plan`                                                                                                                                        |
| **Schedule** | `schedule INDEX ap/APPOINTMENT_NAME d/APPOINTMENT_DATE_TIME`<br> e.g. `schedule 1 ap/Annual review of financial goals d/20-11-2023 15:00`                                                                                           |
| **Complete** | `complete [INDEX] [d/APPOINTMENT_DATE]` <br> e.g `complete 1` <br> e.g `complete d/01-05-2023`                                                                                                                                      |                                                                                                                                                                         |
| **List**     | `list`                                                                                                                                                                                                                              |
| **Help**     | `help`                                                                                                                                                                                                                              |
| **Sort**     | `sort KEYWORD` <br> e.g., `sort appointment`                                                                                                                                                                                        |

