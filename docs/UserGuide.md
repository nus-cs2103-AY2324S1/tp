---
layout: page
title: OutBook User Guide
---

## About OutBook
OutBook is a desktop application designed for freelance insurance agents, enabling them to effectively organize and oversee their extensive contacts and meeting schedules. It is optimised for Command Line Interface (CLI) and aims to significantly reduce the time needed for organizational tasks.

This guide aims to help you explore its features and learn how to use them.

## Key Features
To help you simplify and streamline the process of organizing your contacts and meeting schedule, our application provides these easy-to-use and efficient features:
1. **Client Information Management**: Easily store, access and modify client details such as contact number, email and last contacted time. Add statuses, custom tags and remarks to organize your clients according to your preferences.
2. **Meeting Schedule and Information Management**: Keep track of where and when your meetings are, and who you are meeting, to ensure you will be prepared for every appointment.

Managing clients and a busy schedule has never been easier. With OutBook, all you need is a keyboard and a few short seconds typing out simple commands!
<div style="page-break-after: always;"></div>

# Table of Contents

- [Quick Start](#quick-start)
- [Navigating OutBook](#navigating-outbook)
- [Features](#features)
  - [Contact Commands](#contact-commands)
    - [Adding a contact: `addc`](#adding-a-contact--addc)
    - [Listing all contacts: `listc`](#listing-all-contacts--listc)
    - [Deleting a contact: `deletec`](#deleting-a-contact--deletec)
    - [Editing a contact: `editc`](#editing-a-contact--editc)
    - [Viewing detailed contact information: `viewc`](#viewing-detailed-contact-information--viewc)
    - [Finding contacts using contact fields: `findc`](#search-for-contacts-using-contact-fields--findc)
  - [Meeting Commands](#meeting-commands)
    - [Adding a meeting: `addm`](#adding-a-meeting--addm)
    - [Listing all meetings: `listm`](#listing-all-meetings--listm)
    - [Deleting a meeting: `deletem`](#deleting-a-meeting--deletem)
    - [Editing a meeting: `editm`](#editing-a-meeting--editm)
    - [Viewing detailed meeting information: `viewm`](#viewing-detailed-meeting-information--viewm)
    - [Finding meetings using meeting fields: `findm`](#search-for-meetings-using-meeting-fields--findm)
    - [Adding contact to meeting: `addmc`](#add-contact-to-meeting--addmc)
    - [Removing contact from meeting: `rmmc`](#remove-contact-from-meeting--rmmc)
    - [Marking a meeting as complete : `mark`](#marking-a-meeting-as-complete--mark)
  - [Miscellaneous Commands](#miscellaneous-commands)
    - [Viewing help: `help`](#viewing-help--help)
    - [Clearing all entries: `clear`](#clearing-all-entries--clear)
    - [Exiting the program: `exit`](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [Command Summary](#command-summary)
- [FAQ](#faq)
- [Known issues](#known-issues)

---

<div style="page-break-after: always;"></div>

# Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `OutBook.jar` [here](https://github.com/AY2324S1-CS2103T-F12-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for OutBook.

4. Open a command terminal, navigate to the _home folder_ using `cd`, and use the `java -jar OutBook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app comes pre-loaded with some sample data.<br>

![Ui](images/Ui.png)
<br><br><br><br><br><br>
5. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `listc` : Lists all contacts.

   - `addc n/John Doe p/98765432 e/johnd@example.com lc/10.10.2023 1000` : Adds a contact named `John Doe` to OutBook.

   - `deletec 3` : Deletes the 3rd contact shown in the contact list.

   - `deletem 1` : Deletes the 1st meeting shown in the meeting list.

   - `clear` : Deletes all contacts and meetings.

   - `exit` : Exits the app.

6. You can refer to the [Features](#features) section below for details of each command. 

[Click here to return to the Table of Contents](#table-of-contents)
___

<div style="page-break-after: always;"></div>

# Navigating OutBook
![User Interface for OutBook](images/UserInterface.png)

| Name          | Function                                                                                                                                                                                                                                              |
|---------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Command Box   | Allows you to enter your commands here.                                                                                                                                                                                                               |
| Status Box    | Displays the results after a command is executed.                                                                                                                                                                                                     |
| Contacts List | Displays your contacts along with a summary of their details.                                                                                                                                                                                         |
| Meetings List | Displays meetings that are scheduled along with a summary of their details.                                                                                                                                                                           |
| Details List  | Displays all the fields of a contact and/or meeting in full detail, complete with labels. Use [viewc](#viewing-detailed-contact-information--viewc)/[viewm](#viewing-detailed-meeting-information--viewm) to specify the contact/meeting to display.  |

[Click here to return to the Table of Contents](#table-of-contents)

---

<div style="page-break-after: always;"></div>

# Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Every word in uppercase represents a field you can supply.<br>
  e.g. in `addc n/NAME`, `NAME` is a parameter that can be substituted for `John Doe`, as in `addc n/John Doe`.

- Fields contained in square brackets are optional.<br>
  e.g. when provided with the fields `n/NAME [t/TAG]`, you can submit `n/John Doe t/friend` if you want to mark this contact as a `friend`, or `n/John Doe` otherwise.

- Fields with `…`​ after them can be submitted any number of times, including zero.<br>
  e.g. when provided with the field `[t/TAG]…​`, you can leave the field blank, or submit `t/friend`, `t/friend t/family`, etc.

- You can submit fields in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, submitting in the format `p/PHONE_NUMBER n/NAME`, such as in `p/91472381 n/John Doe`, is also acceptable.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines. This is to avoid omission of space characters surrounding line-breaks when lengthy text is copied over to the application.
</div>

<div style="page-break-after: always;"></div>

## Contact Commands

### Adding a contact: `addc`

Adds a contact to OutBook.

Format: `addc n/NAME p/PHONE_NUMBER e/EMAIL [lc/LAST_CONTACTED_TIME] [s/STATUS] [r/REMARK] [t/TAG]…​`

- `NAME`, `PHONE_NUMBER`, `EMAIL` are compulsory fields. `LAST_CONTACTED_TIME`, `STATUS`, `REMARK` and `TAG` are optional.
- `PHONE_NUMBER` must contain only numbers, and be at least 3 digits long.
- `EMAIL` must be of the format local-part@domain and adhere to the following constraints:
  1. The local-part should only contain alphanumeric characters and the following special characters `+ _ . -`.
  2. The local-part may not start or end with any special characters.
  3. The domain name is made up of domain labels separated by periods.
     The domain name must:
     - end with a domain label at least 2 characters long
     - have each domain label start and end with alphanumeric characters
     - have each domain label consist of alphanumeric characters, separated only by hyphen
- `LAST_CONTACTED_TIME` must contain both date and time and adhere to the `DD.MM.YYYY HHMM` format.
  -  e.g. 1st October 2023, 10:00am will be written as `01.10.2023 1000`.
- STATUS, if included, must be one of `NIL`, `Prospective`, `Active`, `Inactive`, `Claimant`, `Renewal`, or blank.
- This command may change the contact that is currently displayed in the details list via the `viewc` command, as detailed in the description for `viewc`.


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can put any number of tags (including 0) on a contact.
</div>

Examples:
- `addc n/John Doe p/98765432 e/johnd@example.com`
- `addc n/Betsy Crowe t/friend e/betsycrowe@example.com p/1234567 lc/01.01.2023 0100 t/Professor`

<br/><br/>

### Listing all contacts: `listc`

Shows an unfiltered list of all contacts in OutBook in the contacts list. Contacts are sorted by their `LAST_CONTACTED_TIME`.

Format: `listc`

* This command may change the contact that is currently displayed in the details list via the `viewc` command, as detailed in the description for `viewc`.


### Deleting a contact: `deletec`

Deletes a contact from OutBook.

Format: `deletec INDEX`

- Deletes the contact at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed contacts list.
- The `INDEX` **must be a positive integer** such as 1, 2, 3, …​
- This command clears the contact that is currently displayed via the `viewc` command.

Examples:
- `listc` followed by `delete 2` deletes the 2nd contact in the results of the `listc` command.
- `findc Betsy` followed by `delete 1` deletes the 1st contact in the results of the `findc` command.

<div style="page-break-after: always;"></div>

### Editing a contact : `editc`

Edits an existing contact in OutBook.

Format: `editc INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [lc/LAST_CONTACTED_TIME] [s/STATUS] [r/REMARK] [t/TAG]…​`

- Edits the contact at the specified `INDEX`. The `INDEX` refers to the index number shown in the displayed contacts list. The `INDEX` **must be a positive integer** such as 1, 2, 3, …​
- All fields are optional, but at least one must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the contact will be removed i.e addition of tags is not cumulative.
  - You can therefore remove all of a contact’s tags by typing `t/` without specifying any tags after it.
- This command may change the contact that is currently displayed via the `viewc` command in the details list, as detailed in the description for `viewc`.

Examples:
- `editc 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st contact to be `91234567` and `johndoe@example.com` respectively.
- `editc 2 n/Betsy Crower t/` edits the name of the 2nd contact to be `Betsy Crower`, and clears all existing tags.


### Viewing detailed contact information : `viewc`

Displays detailed information of a contact in OutBook's details list.

Format: `viewc INDEX`

- Displays detailed information of the contact at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed contacts list.
- The `INDEX` **must be a positive integer** such as 1, 2, 3, …​
- The displayed details include a contact's Name, Phone, Email, Last Contacted Time, Status, Remarks and Tags.
- As this command displays the contact at the specified `INDEX` in the contacts list, the contact displayed in the details list may change due to a modification in list order through the use of `addc`, `editc` or `listc` commands. This is intentional.
- The displayed contact is cleared from the details list when the `deletec` and `findc` commands are used.

Examples:
- `viewc 2` Displays detailed information related to the 2nd contact on the list.

<div style="page-break-after: always;"></div>

### Finding contacts using contact fields: `findc`
Displays in the contacts list only those contacts whose details match the keywords specified in these fields: `NAME`, `PHONE_NUMBER`, `EMAIL`, `LAST_CONTACTED_TIME`, `STATUS`, `TAG`.

Format: `findc [n/KEYWORDS] [p/KEYWORDS] [e/KEYWORDS] [lc/DATETIME] [s/KEYWORDS] [t/KEYWORDS]`

* All fields are optional. If none are provided, all contacts will be displayed in the contacts list.
* The search is case-insensitive.
  - e.g `shop` will match `SHOP`.
* The order of the keywords does not matter. 
  - e.g. `Shop Meet` will match `Meet Shop`.
* For `NAME`, `STATUS` and `TAG`, only full words will be matched.
  - e.g. `Meet` will not match `Meeting`.
* For `EMAIL`, any characters, whether alphanumeric or not, will be matched.
  - e.g. `_` will match `m_e@gmail.com`.
* For `PHONE_NUMBER`, the entire length of the input digits will be matched.
  - e.g. `913` will match `90091300` but not `90103000`.
* For `LAST_CONTACTED_TIME`, the input must adhere to the `DD.MM.YYYY HHMM` format.
  - e.g. 9th October 2023, 10.30am will be written as `09.10.2023 1030`.
* Contacts must match at least one keyword in a particular field to be returned.
  - e.g. `John Doe` will return `John Lee`, `James Doe`.
* If multiple fields are provided, contacts must match at least one keyword in all fields to be returned.
  - e.g. `n/John Doe s/active` will return `Name: John Lee, Status: Active` but not `Name: James Doe, Status: Claimant`.
* This command clears the contact that is currently displayed via the `viewc` command.

<div style="page-break-after: always;"></div>

Examples:
- `findc n/alice` returns `Alice` and `alice tan`
- `findc p/51` returns `95163890` and `40351`
- `findc e/_@GMAIL` returns `alice_@gmail.com`
- `findc p/9 s/inactive claimant t/friend` returns contacts with a `9` in their phone number, whose status is either `inactive` or `claimant`, and has a `friend` tag.
  ![result for 'findContact'](images/findContactResult.png)

<div style="page-break-after: always;"></div>

## Meeting Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about meetings:**<br>

- Meetings in OutBook are sorted by their start time.

- Meetings are allowed to overlap and multiple meetings can be occuring at the same time.

</div>

### Adding a meeting: `addm`

Adds a meeting to OutBook.

Format: `addm m/TITLE a/LOCATION s/START e/END [t/TAG]…​`

* `TITLE`, `LOCATION`, `START` and `END` are compulsory fields. `TAG` is optional.
* `START` and `END` must contain both date and time and adhere to the `DD.MM.YYYY HHMM` format.
  -  eg. 1st October 2023, 10:00am will be written as `01.10.2023 1000`.
* This command may change the meeting that is currently displayed in the details list via the `viewm` command, as detailed in the description for `viewm`.

Examples:
- `addm m/Lunch a/Cafeteria s/20.09.2023 1200 e/20.09.2023 1300`
- `addm m/CS2103T meeting a/Zoom call url s/20.09.2023 1000 e/20.09.2023 1200`

### Listing all meetings : `listm`

Shows an unfiltered list of all meetings in OutBook in the meetings list. Meetings are sorted by their `START` time.

Format: `listm`

* This command may change the meeting that is currently displayed in the details list via the `viewm` command, as detailed in the description for `viewm`.

<div style="page-break-after: always;"></div>

### Deleting a meeting : `deletem`

Deletes a meeting from OutBook.

Format: `deletem INDEX`

- Deletes the meeting at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed meeting list.
- The `INDEX` **must be a positive integer** such as 1, 2, 3, …​
- This command clears the meeting that is currently displayed via the `viewm` command.

Examples:
- `listm` followed by `deletem 2` deletes the 2nd meeting in the results of the `listm` command.
- `findm m/Project` followed by `deletem 1` deletes the 1st meeting in the results of the `findm` command.

### Editing a meeting : `editm`

Edits an existing meeting in OutBook.

Format: `editm INDEX [m/TITLE] [a/LOCATION] [s/START] [e/END] [t/TAG]…​`

- Edits the meeting at the specified `INDEX`. The `INDEX` refers to the index number shown in the displayed meeting list. The `INDEX` **must be a positive integer** such as 1, 2, 3, …​
- All fields are optional, but at least one must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the meeting will be removed i.e adding of tags is not cumulative.
  - You can therefore remove all of a meeting’s tags by typing `t/` without specifying any tags after it.
- This command may change the meeting that is currently displayed via the `viewm` command in the details list, as detailed in the description for `viewm`.

Examples:
- `editm 1 a/Hawker Centre s/15.09.2023 1500` edits the location and start of the 1st meeting to be `Hawker Centre` and `15.09.2023 1500` respectively.
- `editm 2 m/Zoom meeting t/` edits the title of the 2nd meeting to be `Zoom meeting`, and clears all existing tags.

<div style="page-break-after: always;"></div>

### Viewing detailed meeting information : `viewm`

Displays detailed information of a meeting in OutBook's details list.

Format: `viewm INDEX`

- Displays detailed information of the meeting at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed meeting list.
- The `INDEX` **must be a positive integer** such as 1, 2, 3, …​
- The displayed details include a meeting's Title, Location, Start and End time, Attendees and Tags.
- As this command displays the contact at the specified `Index` in the meetings list, the meeting displayed in the details list may change due to a modification in list order through the use of `addm`, `editm` and `listm` commands. This is intentional.
- The displayed meeting is cleared from the details list when the `deletem` and `findm` commands are used.

Examples:
- `viewm 2` Displays detailed information related to the 2nd meeting on the list, including current attendees.


<div style="page-break-after: always;"></div>


### Finding meetings using meeting fields: `findm`

Displays in the meetings list only those meetings whose details match the keywords specified in these fields: `TITLE`, `LOCATION`, `ATTENDEE_NAME`, `TAG`, or fall within the `START` and `END` times you provide.

Format: `findm [m/KEYWORDS] [a/KEYWORDS] [t/KEYWORDS] [n/ATTENDEE_NAME] [s/START e/END]`

- All fields are optional. If none are provided, all meetings will be displayed in the meetings list.
- The search is case-insensitive.
  - e.g `shop` will match `SHOP`.
- The order of the keywords does not matter.
  - e.g. `Shop Meet` will match `Meet Shop`.
- For `TITLE`, `LOCATION`, `TAG` and `ATTENDEE_NAME`, only full words will be matched.
  - e.g. `Meet` will not match `Meeting`.
- If searching for a meeting by time, both `START` and `END` times must be provided. Only meetings that begin after and end before the provided `START` and `END` times respectively will be returned.
- Meetings must match at least one keyword in a particular field to be returned.
  - e.g. `m/Shop Meet` will return `Meeting: Shop at mall`, `Meeting: Meet client`.
- If multiple fields are provided, meetings must match at least one keyword in all fields to be returned.
  - e.g. `m/Shop Meet a/Mall` will return `Meeting: Shop at mall, Location: Mall` but not `Meeting: Meet client, Location: Park`.
- This command clears the meeting that is currently displayed via the `viewm` command.

Examples:
- `findm m/project` returns `project` and `Project work`.
- `findm m/zoom meeting` returns `Zoom session`, `Zoom meeting`, `Meeting advisor`.
- `findm s/09.09.2023 0000 e/09.10.2023 0000` returns all meetings between 09.09.2023 0000 and 09.10.2023 0000.
- `findm m/Meeting s/18.10.2023 0000 e/18.10.2023 2359 n/John` returns `Meeting with friends`, as it starts after 18.10.2023 0000 and ends before 18.10.2023 2359, and is attended by John.

<div style="page-break-after: always;"></div>

![result for 'findJohnMeeting'](images/findJohnMeetingResult.png)

<div style="page-break-after: always;"></div>

### Add contact to meeting: `addmc`

Adds a contact to a meeting as an attendee.

Format: `addmc MEETING_INDEX CONTACT_INDEX`

- Adds the contact you specified with `CONTACT_INDEX` to a meeting specified with `MEETING_INDEX`.
- `MEETING_INDEX` refers to the index number shown in the displayed meeting list.
- `CONTACT_INDEX` refers to the index number shown in the displayed contact list.
- The indexes **must be positive integers** such as 1, 2, 3, …​
- Both `MEETING_INDEX` & `CONTACT_INDEX` must refer to the index of an existing meeting and contact respectively.
- The name of the added contact will thereafter be listed in the detailed description of the meeting in the details list, when `viewm` is used on that meeting.

Examples:
- `addmc 3 1` adds the 1st contact as an attendee to the 3rd meeting in OutBook.

### Remove contact from meeting: `rmmc`

Removes a contact from a meeting.

Format: `rmmc MEETING_INDEX ATTENDEE_INDEX`

- Removes a contact at the specified `ATTENDEE_INDEX` from the meeting at the specified `MEETING_INDEX`.
- `MEETING_INDEX` refers to the index number shown in the displayed meeting list.
- `ATTENDEE_INDEX` refers to the index number of the attendee listed in the detailed description of the meeting in the details list, when `viewm` is used on that meeting.
- The indexes **must be positive integers** such as 1, 2, 3, …​
- Both `MEETING_INDEX` & `ATTENDEE_INDEX` must refer to the index of an existing meeting and attendee respectively.

Examples:
- `rmmc 3 2` removes the 2nd attendee from the 3rd meeting in OutBook.

<div style="page-break-after: always;"></div>

### Marking a meeting as complete : `mark`

Marks a meeting in OutBook as complete. All attendees of the meeting will have their `LAST_CONTACTED_TIME` field updated to the end time of the meeting.

Format: `mark INDEX`

- Marks the meeting at the specified `INDEX` as complete.
- The `INDEX` refers to the index number shown in the displayed meeting list.
- The `INDEX` **must be a positive integer** such as 1, 2, 3, …​

Examples:
- `listm` followed by `mark 2` marks the 2nd meeting in the results of the `listm` command.

## Miscellaneous Commands

### Viewing help : `help`

Displays a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Clearing all entries : `clear`

Clears all entries from the contacts, meetings and details list in OutBook, effectively wiping all data from OutBook.

Format: `clear`

### Exiting the program : `exit`

Closes and exits OutBook.

Format: `exit`

## Saving the data

OutBook data is saved in the hard disk automatically after any command that modifies the data. There is no need to save manually.

### Editing the data file

OutBook data is saved as a JSON file `[JAR file location]/data/outbook.json`. If you are an experienced user, you may attempt to update data directly by editing this data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file render its format invalid, OutBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to make a backup of the file before editing it.
</div>  

[Click here to return to the Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

## Command summary

| Action                          | Format, Example                                                                                                                                                                         |
|---------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add contact**                 | `addc n/NAME p/PHONE_NUMBER e/EMAIL [lc/LAST_CONTACTED_TIME] [r/REMARK] [t/TAG]…​` <br> e.g., `addc n/James Ho p/22224444 e/jamesho@example.com lc/09.09.2023 0000 t/friend t/colleague` |
| **Add contact to meeting**      | `addmc MEETING_INDEX CONTACT_INDEX` <br> e.g., `addmc 2 1`                                                                                                                               |
| **Add meeting**                 | `addm m/TITLE a/LOCATION s/START e/END [t/TAG]…​` <br> e.g., `addm m/Lunch a/Cafeteria s/20.09.2023 1200 e/20.09.2023 1300`                                                              |
| **Clear**                       | `clear`                                                                                                                                                                                  |
| **Delete contact**              | `deletec INDEX` <br> e.g., `deletec 3`                                                                                                                                                   |
| **Delete meeting**              | `deletem INDEX` <br> e.g., `deletem 3`                                                                                                                                                   |
| **Edit contact**                | `editc INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [lc/LAST_CONTACTED_TIME] [r/REMARK] [t/TAG]…​`<br> e.g.,`editc 2 n/James Lee e/jameslee@example.com`                                    |
| **Edit meeting**                | `editm INDEX [m/TITLE] [a/LOCATION] [s/START] [e/END] [t/TAG]…​`<br> e.g.,`editm 1 a/Hawker Centre s/15.09.2023 1500`                                                                    |
| **Find contact**                | `findc [n/KEYWORDS] [p/KEYWORDS] [e/KEYWORDS] [lc/LAST_CONTACTED_TIME] [s/KEYWORDS] [t/KEYWORDS]` <br> e.g., `findc n/James Jake s/active claimant`                                      |
| **Find meeting**                | `findm [m/KEYWORDS] [a/KEYWORDS] [t/KEYWORDS] [n/ATTENDEE_NAME] [s/START e/END]` <br> e.g., `findm m/Zoom Meet s/09.09.2023 0000 e/09.10.2023 0000`                                      |
| **Help**                        | `help`                                                                                                                                                                                   |
| **List contacts**               | `listc`                                                                                                                                                                                  |
| **List meetings**               | `listm`                                                                                                                                                                                  |
| **Remove contact from meeting** | `rmmc MEETING_INDEX ATTENDEE_INDEX` <br> e.g., `rmmc 2 2`                                                                                                                                |
| **View contact details**        | `viewc INDEX` <br> e.g., `viewc 4`                                                                                                                                                       |
| **View meeting details**        | `viewm INDEX` <br> e.g., `viewm 4`                                                                                                                                                       |
| **Exit**                        | `exit`                                                                                                                                                                                   |

[Click here to return to the Table of Contents](#table-of-contents)

---

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Follow these steps:
1. Install OutBook on the other computer following the same instructions as in the [Quick Start Guide](#quick-start). An empty data file will be created in the same folder as the application. 
2. Replace this empty data file with the file that contains the data from your previous OutBook installation.
3. Your transferred data should now be visible upon launching OutBook.

[Click here to return to the Table of Contents](#table-of-contents)

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. You can solve this by deleting the `preferences.json` file created by the application, before running the application again.

[Click here to return to the Table of Contents](#table-of-contents)

---
