---
layout: page
title: User Guide
---

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

JobFindr is a **Contact Book app** for NUS fresh graduates who are looking for jobs.

It simplifies _contact management_, provides _reminders_ and enhances _organisation_, helping users _stay competitive_
in the job market.
The project simulates an ongoing software project for a desktop application (called _JobFindr_) used for managing job
applications.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `jobfindr.jar` from [here](https://github.com/AY2324S1-CS2103T-W12-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar jobfindr.jar` command
   to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe`
      to the Address Book.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

</div>

---

### Adding an application : `add`

Adds an application to a company to the list.

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

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
  Adds a company called The Coca-Cola Company, adds their email

**UI mockup:**
![AddCommand](images/user-guide/AddCommand.png)
---

### Deleting an application : `delete`

Deletes the specified application from the list.

**Format:** `delete INDEX`

* Deletes the application to the company at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed application list.
* The `INDEX` must be a _positive integer_ 1, 2, 3, …

**Successful command:**
print “Application to (Company name) has been deleted.”

**Failed command:**
print “Error: ” and error message for:

* `INDEX` is not a positive integer: “Index must be a positive integer.”
* `INDEX` is larger than list size: “No such company at index (`INDEX`).”

**Examples:**

* `list` followed by `delete 2`
  Deletes the 2nd application in the list.

**UI mockup:**
![DeleteCommand](images/user-guide/DeleteCommand.png)
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
![ListCommand](images/user-guide/ListCommand.png)

---

### Marking the status of an application : `mark`

Marks the status of an existing application in the list.

**Format:** `mark INDEX s/STATUS`

* Marks the status of the application at the specified `INDEX` as submitted, pending, accepted, or rejected.
* The `INDEX` refers to the index number shown in the displayed application list.
* The `INDEX` must be a _positive integer_ 1, 2, 3, …

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

**UI mockup:**
![MarkCommand](images/user-guide/MarkCommand.png)

---

### Setting the deadline for an application submission : `deadline`

Sets submission deadline for an existing application in the list.

**Format:** `deadline INDEX d/DEADLINE`

* Sets deadline for the existing application at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed application list.
* The `INDEX` must be a _positive integer_ 1, 2, 3, …
* `DEADLINE` must be provided in the accepted `DATETIME` Format: Nov 12 2022 1200

**Successful command:**
print “(Company Name)’s application deadline has been set to `DEADLINE`.”

**Failed command:**
Prints the associated error messsage:

* No arguments provided: "Invalid command format!" and provides the command format.
* Invalid specifier: "Invalid command format!"
* `INDEX` is not a positive integer: “Index must be a positive integer.”
* `INDEX` is larger than list size: “No such company at index (index).”

**Examples:**

* `deadline 1 d/Nov 12 2022 1200`
  Sets deadline for application at index 1 to be Nov 12 2022 1200.

**UI mockup:**
![DeadlineCommand](images/user-guide/DeadlineCommand.png)

---

### Finding an application : `find`

Find applications by specifying a field and keywords.

**Format:** `find -FIELD [KEYWORDS]`

* Lists all applications whose field contains any of the provided keywords.
* Applications are filtered by the specified `FIELD`.
* The `FIELD` must be a valid specifier. `find` only supports searching by:
    * Company: `-c`
    * Role: `-r`

**Successful command:**
List is updated to contain applications meeting the search criteria.

**Failed command:**
Prints the associated error message.

* No arguments provided: “Invalid command format!” and provides the command format.
* Invalid specifier: "Invalid command format! Field specifier is invalid".

**Examples:**

* `deadline 1 d/Nov 12 2022 1200`
  Sets deadline for application at index 1 to be Nov 12 2022 1200.

### Asking for help: `help`

Shows a list of commands and how they can be used.

**Format:** `help`

**Successful command:**
A help window displaying the help message will pop up.

**Failed command:**
print “Error: ” and error message for:

* Arguments passed after the help command: “Unexpected arguments.”

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format                                        |
|--------------|-----------------------------------------------|
| **Add**      | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS` |
| **Delete**   | `delete INDEX`                                |
| **List**     | `list`                                        |
| **Mark**     | `mark INDEX s/STATUS`                         |
| **Deadline** | `deadline INDEX d/DEADLINE`                   |
| **Find**     | `find -FIELD [KEYWORDS]`                      |
| **Help**     | `help `                                       |
