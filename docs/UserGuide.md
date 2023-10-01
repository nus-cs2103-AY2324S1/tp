# JobFindr User Guide

---

Jobfindr is a **Contact Book app** for NUS fresh graduates who are looking for jobs. 
It simplifies _contact management_, provides _reminders_ and enhances _organisation_, helping users _stay competitive_ in the job market.
The project simulates an ongoing software project for a desktop application (called _JobFindr_) used for managing job applications.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

---

### Adding an application : `add`

Adds an application to a company to the list.

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS​`

* Users must input a company `NAME`
* Details of the company such as `PHONE_NUMBER`, `EMAIL` and `ADDRESS` are optional

**Successful command:**
print “Application to (Company name) has been added.”

**Failed command:**
print “Error: ” and error message for:
* `NAME` is not in the command: “Name of company was not included in the command.”

**Examples:**
* `add n/Microsoft p/98765432 e/microsoft@gmail.com a/182 Cecil St, #13-01, Singapore 069547`
  Adds a company called microsoft, adds their phone number, email and address 
* `add n/The Coca-Cola Company e/cocacola@yahoo.com`
  Adds a company called The Coca Cola Company, adds their email

---

### Deleting an application : `delete`

Deletes the specified application from the list.

**Format:** `delete INDEX`

* Deletes the application to the company at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed application list.
* The `INDEX` must be a _positive integer_ 1, 2, 3, …​

**Successful command:**
print “Application to (Company name) has been deleted.”

**Failed command:**
print “Error: ” and error message for:
* `INDEX` is not a positive integer: “Index must be a positive integer.”
* `INDEX` is larger than list size: “No such company at index (`INDEX`).”

**Examples:**
* `list` followed by `delete 2`
  Deletes the 2nd application in the list.

---

### Listing all applications : `list`

Shows a list of all applications in the list.

**Format:** `list`

**Successful command:**
List out all applications to companies in the list with index.

**Failed command:**
print “Error: ” and error message for:
* List is empty: “List is empty.”
* Arguments passed after the list command: “Unexpected arguments.”

**UI mockup:**
(insert photo)

---

### Marking the status of an application : `mark`

Marks the status of an existing application in the list.

**Format:** `mark INDEX s/STATUS`

* Marks the status of the application at the specified `INDEX` as submitted, pending, accepted, or rejected.
* The `INDEX` refers to the index number shown in the displayed application list.
* The `INDEX` must be a _positive integer_ 1, 2, 3, …​

**Successful command:**
print “Application to (Company name) has been marked as `STATUS`.”

**Failed command:**
print “Error: ” and error message for:
* `INDEX` is not a positive integer: “Index must be a positive integer.”
* `INDEX` is larger than list size: “No such company at index (`INDEX`).”

**Examples:**
* `mark 1 submitted` 
  Marks the status of the company application at index 1 as submitted.
* `mark 2 rejected` 
  Marks the status of the company application at index 1 as rejected.

---

### Setting the deadline for an application submission : `set deadline`

Sets submission deadline for an existing application in the list.

**Format:** `set deadline INDEX d/DEADLINE`

* Sets deadline for the existing application at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed application list.
* The `INDEX` must be a _positive integer_ 1, 2, 3, …​
* `DEADLINE` must be provided in the accepted `DATETIME` Format as listed below:
  - Nov 12 2022 1200 
  - 12 Nov 2022 1200 
  - 2022-11-12 1200 
  - 12/11/2022 1200

**Successful command:**
print “(Company Name)’s application deadline has been set to `DEADLINE`.”

**Failed command:**
print “Error: ” and error message:
* `INDEX` is not a positive integer: “Index must be a positive integer.”
* `INDEX` is larger than list size: “No such company at index (index).”

**Examples:**
* `setdeadline 1 d/Nov 12 2022 1200` 
  Sets deadline for application at index 1 to be Nov 12 2022 1200.

---

# [coming soon]

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
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

### Clearing all entries : `clear`
Clears all entries from the address book.

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

### Archiving data files `[coming in v2.0]`
_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------
## Command summary

Action | Format
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`
**Delete** | `delete INDEX`
**List** | `list`
**Mark** | `mark INDEX s/STATUS`
**SetDeadline** | `set deadline INDEX d/DEADLINE`

### [coming soon]
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Help** | `help`
**Clear** | `clear`
