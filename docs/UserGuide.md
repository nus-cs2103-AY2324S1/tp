---
layout: page
title: User Guide
---

Foster Family is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Foster Family can get foster family management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

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

### Viewing Help for Commands : `help`

Shows the purpose and format of the different commands.

Format: `help COMMAND`

Parameter:
* `COMMAND`: One of the available commands

Examples:
* `help add` Displays the function and the format of how to use the `add` command

Expected output(success):
```agsl
Adds a fosterer to the address book.
Format: add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL_FOSTERED/WANTED t/TAG
```

Expected output (fail):
```agsl
Oops! There seems to be an error, please check the format of your command again.
```

### Adding a fosterer: `add`

Adds a fosterer to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL_FOSTERED/WANTED t/TAG…`

Parameters:
* `NAME`: Name of fosterer
* `PHONE_NUMBER`: Phone number of fosterer
* `EMAIL`: Email of fosterer
* `ADDRESS`: Address of foster family
* `HOUSING_TYPE` (case-sensitive) : HDB / Condo / Landed / nil
* `AVAILABILITY` (case-sensitive) : Available / NotAvailable / nil
* `ANIMAL_NAME`
  * `ANIMAL_NAME` (if `availability/NotAvailable`) : Name of animal fostered
  * `ANIMAL_NAME` (if `availability/nil`) : nil
  * `ANIMAL_NAME` (if `availability/Available`) : nil
* `TYPE_OF_ANIMAL_FOSTERED/WANTED` (case-sensitive) :
  * `TYPE_OF_ANIMAL_FOSTERED` (if `availability/NotAvailable`) : current.Dog / current.Cat / nil
  * `TYPE_OF_ANIMAL_WANTED` (if `availability/Available`) : able.Dog / able.Cat / nil
  * `TYPE_OF_ANIMAL_FOSTERED/WANTED` (if `availability/nil`) : nil

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
 A person can have any number of tags (including 0). ‘nil’ can be indicated for HOUSING_TYPE, AVAILABILITY, ANIMAL_NAME and TYPE_OF_ANIMAL_FOSTERED/WANTED if information is not currently available.
</div>

Examples:
* `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Cat t/Urgent`
adds a fosterer named Jerry Tan with the phone number 98765412 and email address jerry123@example.com; his address is Baker street, block 5, #27-01, housing type is HDB and he is fostering a cat named Dexter. An urgent visit is required.
* `add n/Tom Lee p/98123456 e/tom@example.com a/Happy street, block 123, #01-01 t/Available t/HDB t/able.Dog`
adds a fosterer named Pete Tay with the phone number 98765411 and email address pete@example.com; his address is Happy street, block 5, #01-01, housing type is Condo and currently he is not fostering any animal but looking to foster a cat.
* In the case where duplicates are given, second/last one will be chosen:
  * `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB housing/Condo availability/Available availability/NotAvailable animal/Dexter animal/Happy animalType/able.Dog animalType/current.Cat t/Urgent`
    * Outcome will be: `New fosterer added: Jerry Tan; Phone: 98765412; Email: jerry123@example.com; Address: Baker street, block 5, #27-01; Housing: Condo; Availability: NotAvailable; Animal name: Happy; Animal type: current.Cat; Tags: [Urgent]`

Expected output (success):
```agsl
New fosterer added: Jerry Tan; Phone: 98765412; Email: jerry123@example.com; Address: Baker street, block 5, #27-01; Housing: HDB; Availability: NotAvailable; Animal name: Dexter; Animal type: current.Cat; Tags: [Urgent]
```

Expected output (fail):

| Scenario                                                                                                               | Error Message                                            |
|------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|
| Without `n/`, `p/`,`e/`, `a/`                                                                                          | `Invalid command format! add: Adds a person to the address book. Parameters: n/NAME p/PHONE e/EMAIL a/ADDRESS housing/HOUSING availability/AVAILABILITY animal/ANIMAL_NAME animalType/ANIMAL_TYPE [t/TAG]... Example: add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Dog t/Urgent t/goodWithDogs`                                                                  |
| Without `housing/`                                                                                                     | `Housing is required. Please indicate as 'nil' if information is not available.` |
| Without `availability/`                                                                                                | `Availability is required. Please indicate as 'nil' if information is not available.` |
| Without `animal/`                                                                                                      | `Animal name is required. Please indicate as 'nil' if information is not available.` |
| Without `animalType/`                                                                                                  | `Animal type is required. Please indicate as 'nil' if information is not available.` |
| `availability/nil` but `animal/` is not 'nil'                                                                          | `Animal name should be 'nil' when availability is 'nil'.` |
| `availability/nil` but `animalType/` is not 'nil'                                                                      | `Animal type should be 'nil' when availability is 'nil'.` |
| `availability/Available` but `animal/` is not 'nil'                                                                    | `Availability cannot be 'Available' when an animal name is provided; animal name should be 'nil'.` |
| `housing/` with values other than ‘HDB’, ‘Condo’, ‘Landed’, ‘nil’                                                      | `Housing type should be either 'HDB', 'Condo', 'Landed' or 'nil'`   |
| `availability/` with values other than ‘Available’, ‘NotAvailable’, ‘nil’                                              | `Availability should be either 'Available', 'NotAvailable' or 'nil'` |
| `availability/Available` with `animalType/` values set to other values which are NOT ‘able.Cat’ or ‘able.Dog’          | `If fosterer is available, animal type should be 'able.Dog' / 'able.Cat'. If fosterer is NOT available, animal type should be 'current.Dog' / 'current.Cat'. If animal type information is not available, it should be inputted as 'nil'.`                                                                     |
| `availability/NotAvailable` with `animalType/` values set to other values which are NOT ‘current.Cat’ or ‘current.Dog' | `If fosterer is available, animal type should be 'able.Dog' / 'able.Cat'. If fosterer is NOT available, animal type should be 'current.Dog' / 'current.Cat'. If animal type information is not available, it should be inputted as 'nil'.`                                                                     |

### Editing a fosterer's detail : `edit`

Edits the details of a fosterer stored in the address book.

**Format**: `edit INDEX`

**Alias: `view`**

**Parameter**: `INDEX`

* Index of a fosterer to be edited is shown in the list obtained by the find/list command.

**Examples**:
*  `list` followed by `edit 3` or `view 3` generates the edit-window for the 3rd fosterer in the address book.

**How to Edit**:
1. Type in `list` command to see the list of fosterers in the address book.
2. Type `edit INDEX` to edit the details of the fosterer with the corresponding INDEX in the list. 
3. Type in the command line to search the list of fields, and press enter to edit the first match. This creates a text field around the detail corresponding to the chosen field.
4. Edit the content of the detail and press enter to confirm the changes, or the esc key to cancel them. 
5. Repeat step 4-5 until you made all your edits. 
6. While the command line is empty, press enter to save all applied changes to all fields, or the esc key to revert them to before the edit command. Both will send you back to the home window.

**Expected Output (success)**:
```
Edit Success! 
```

**Expected Output (failure)**:
1. Compulsory fields are not filled in
```
Edit failed: Compulsory fields are not filled in!
```
2. System Error
```
Edit failed: There seems to be an error, please check your fields again.
```
### Listing fosterers: `list`

Lists fosterers that match a particular description or search, or all fosterers if the search is blank.

Format: `list *KEYWORDS`
Alias: `find`

* The keywords are case-insensitive.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* All fields are searched (including tags).
* Keywords can match as parts of words. e.g. `john` will match `Johnny`.
* Keywords can overlap. e.g. `samm my` will match `Sammy`
* Fosters must match all keywords (i.e. `AND` search).
  e.g. `Hans Bo` will return `Hansbo Grahm`, but not `Hans Duo`

Examples:
* `list` lists all fosterers in the address book
* `list john doe` matches "John Doe", "Doe John", "Johnny Doe", and "Mary" who lives on "John Doe Street"
* `list john john doe` is redundant and gives the same result as `list john doe`

Expected output (success):
```agsl
Fosterers matching query are listed.
```
UI also updates with a list of fosterers matching the query.

Expected output (fail):
```agsl
Oops! Invalid search expression, please check again.
```

### Deleting a fosterer : `delete`

Deletes the data entry at the index-th position of the currently displayed list.

Format: `delete INDEX...`

Parameter: `INDEX...`
* Index of a fosterer is shown in the list obtained by the `find/list` command.
* Must be a positive integer 1, 2, 3, …
* Multiple indexes are allowed for mass deletion,  each index separated by a white space.
* `delete` without an index is valid if and only if there exists only one data entry in the current list.

Examples:
* `list` followed by `delete 2` deletes the 2nd fosterer in the address book
* `find Jerry` followed by `delete 1` deletes the 1st fosterer in the results of the find command
* `list` followed by `delete 1 3 7` deletes the 1st, 3rd and 7th fosterers in the address book

Expected output (success):
```agsl
Fosterers Jerry Chee, John Doe, and Mary Ann are successfully deleted!
```

Expected output (fail):
```agsl
Oops! Invalid fosterer index provided, please check again.
```
### Clearing all entries : `reset`

Clears all entries from the address book.

Format: `reset`

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

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                     |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**  | `clear`                                                                                                                                                              |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                  |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                           |
| **List**   | `list`                                                                                                                                                               |
| **Help**   | `help`                                                                                                                                                               |
