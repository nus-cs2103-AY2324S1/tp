---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# CampusConnect User Guide

___Empowering connections in the NUS community is just a few keystrokes away!___

**CampusConnect** is a desktop application built for **NUS students** living on campus to help them **stay organised**, **stay connected**, and **make the on-campus experience at NUS stress-free**. It is **optimised for use** via **Command Line Interface (CLI)** while benefiting from a **Graphical User Interface (GUI)**.

Here is an **overview** of how CampusConnect can enhance your campus experience:

- Store and edit contact profiles of your friends
- Receive timely notifications to celebrate your friends' birthdays
- Tracks payments between you and your friends

## Using this Guide

This user guide shows you how to get started with using CampusConnect -- it has easy-to-understand explanations and examples to help you grasp its various features. 

### Who is this guide for?

Our guide is catered to CampusConnect users of all experiences! Here's a quick overview of who this guide is for, and how you can best use the guide:

| If you are... | You should... |
| :-----------: | :-----------: |
| New to CampusConnect | Read the **[Quick Start](#quick-start)** below! |
| An existing user | Skip to the **[Command Summary](#command-summary)** for a quick reference, or read through our **[Features](#features)** for a deeper look at each feature! |
| Interested in comprehensive technical details | Read our **[Developer Guide](DeveloperGuide.md)**, which is separate from this User Guide! |

### Visual Components

Our guide uses **visual components** to highlight key information for your convenience. Here's a quick overview of what each component means:

<div style="display: grid; grid-template-columns: 25% 75%; grid-template-rows: repeat(5, auto);">
    <div style="font-size: 1.5em; display: flex; align-items: center;">
       <ol start="1">
        <li>Examples</li>
      </ol>
    </div>
    <div>
        <box theme="info" icon=":fa-solid-magnifying-glass:">

**Example usages** of a feature will be contained in a **light blue box** with this **magnifying glass icon** :fa-solid-magnifying-glass:. These examples will show you how a feature works, and will include sample commands that you can try out yourself!
        </box>
    </div>
    <div style="font-size: 1.5em; display: flex; align-items: center;">
        <ol start="2">
            <li>Command Syntax Tables</li>
        </ol>
    </div>
    <div>
        <br>
        <panel header=":fa-solid-book: **Command Parameter / Syntax Tables**" type="secondary" expanded no-close>
Large tables describing the **syntax or parameters** of **parameter-heavy commands**, or the **attributes of large models**, will be contained in a **collapsible grey panel** like this one with a **book icon** :fa-solid-book:.

| Sample | Table |
| ------ | ----- |
| Sample | Table Data |
| Sample 2 | Table Data 2 |

These tables will provide a quick reference on the meanings and usage of each parameter in commands which support many such parameters, or the attributes of large and complex models, such as our [Person model](#person-model).

PDF Users will always see these panels expanded, but users of the web version of this guide can click on the panel header to collapse (and then re-expand) the panel if the information is no longer necessary.
        </panel>
        <br>
    </div>
    <div style="font-size: 1.5em; display: flex; align-items: center;">
        <ol start="3">
            <li>Warnings / Error Cases</li>
        </ol>
    </div>
    <div>
        <box type="warning">
**Warnings / Error Cases** for features will be shown in a **yellow box** with this **exclamation mark icon** :fa-solid-exclamation: . These clarify cases which will are expected to display errors to users, or explain enhancements that will be implemented in future releases of CampusConnect.

All planned enhancements will also be listed in the [Planned Enhancements / Known Issues](#planned-enhancements-known-issues) section near the end of the guide.
        </box>
        <br>
    </div>
    <div style="font-size: 1.5em; display: flex; align-items: center;">
        <ol start="4">
            <li>Tips</li>
        </ol>
    </div>
    <div>
        <box theme="primary" icon=":fa-solid-lightbulb:">
**Tips** which provide useful additional information about a feature will be contained in a **darker blue box** with this **lightbulb icon** :fa-solid-lightbulb:. These tips aren't required to get through basic functionality of our features, but will help you get the most out of CampusConnect!
        </box>
    </div>
</div>




<br>



<br>



<br>



---

# Table of Contents
1. Quick Start
2. Features
   - Request help [Coming Soon]
   - Add normal contact
   - Add alternative information to contact
   - Edit contact information
   - Upload contact's photo
   - Update contact's photo
   - Search contact
   - List all contacts
   - Delete normal contact
   - Undo last action [Coming Soon]
   - Receive upcoming birthday notifications
   - Opt out notification [Coming soon]
   - Track payment [Coming Soon]
   - Change language [Coming Soon]
3. Troubleshooting / FAQ

--------------------------------------------------------------------------------------------------------------------

# Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `CampusConnect.jar` from [here](https://github.com/AY2324S1-CS2103T-T13-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for the application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar CampusConnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to CampusConnect.

   * `add n/Jane Doe p/98765433 e/janed@example.com a/Jane street, block 123, #01-01 t/floorball t/suitemate` : Adds a contact named `Jane Doe` to CampusConnect who is tagged with the `floorball` and `suitemate` tags.

   * `optout notify birthdays` : Opts out from birthday notifications.

   * `find name/Doe` : Finds all contacts who have "Doe" in their name.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional, ___with `addalt` as an exception. (Refer to [Add alternative information to contact](#add-alternative-information-to-contact) under Features)___<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

## Manage Contacts

### Add normal contact

Add a new contact with basic details like name, phone number, email, and address.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]`
<box type="info">

All the fields must be provided except `TAG`. The fields you enter should follow the following format:

| Field          | Format                                                                                                                  | Example                         |
|----------------|-------------------------------------------------------------------------------------------------------------------------|---------------------------------|
| `NAME`         | Use `a-z`, `A-Z`, `0-9` and whitespaces only                                                                            | John Doe                        |
| `PHONE_NUMBER` | Use `0-9` only and should be 3 digits long                                                                              | 98765432                        |
| `EMAIL`        | Have the format of `local-part@domain`                                                                                  | johndoe@gmail.com               |
| `ADDRESS`      | Use any characters                                                                                                      | John street, block 123, #01-01  |
| `TAG`          | Use `a-z`, `A-Z` and `0-9` only. Alternatively, use `RA` or `SOS` which are predefined emergency tags for your contact  | friend                          |
</box>

<box type="info">

Certain tags such as `RA` and `SOS` are tags to indicate important contacts such as emergency contacts. They are displayed first, and with a red background to indicate their importance. 
</box>

Examples
* `add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend`
    * Adds a contact named "John Doe" with the phone number "98765432", email "johndoe@gmail.com", address "John street, block 123, #01-01", and a tag "friend"
* `add n/Betsy Crowe e/betsycrowe@example.com a/Newgate Prison p/1234567 t/friend t/colleague`
    * Adds a contact named "Betsy Crowe" with the email "betsycrowe@example.com", address "Newgate Prison", phone "1234567", and two tags "friend" and "colleague"




###  Add alternative information to contact

Adds alternative contact information to an existing contact.

Format: `addalt INDEX [tg/TELEGRAM] [e2/SECONDARY_EMAIL] [li/LINKEDIN] [b/BIRTHDAY]`

<box type="info">
At least one of the optional fields must be provided. The fields you enter should follow the following format:

| Field             | Format                                                                                                        | Example               |
|-------------------|---------------------------------------------------------------------------------------------------------------|-----------------------|
| `TELEGRAM`        | Start with the `@` symbol, no whitespace with a minimum length of 5 characters. Use `a-z`, `0-9` and `_` only | @johndoe              |
| `SECONDARY_EMAIL` | Have the format of `local-part@domain`                                                                        | johndoe@hotmail.com   |
| `LINKEDIN`        | Use `a-z`, `A-Z`, `0-9`, `_` and `-` only                                                                     | john-doe-b9a38128a    |
| `BIRTHDAY`        | Have the format of `DD/MM`                                                                                    | 31/10                 |

</box>

Examples:
* `addalt 1 tg/@johndoe e2/johndoe@hotmail.com`
    * Adds John Doe's telegram "@johndoe" and secondary email "johndoe@hotmail.com"
* `addalt 1 li/john-doe-b9a38128a`
    * Adds John Doe's linkedin "john-doe-b9a38128a"

###  Edit contact information

Edits contact information of an existing contact.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [tg/TELEGRAM] [e2/SECONDARY_EMAIL] [li/LINKEDIN] [b/BIRTHDAY]`

<box type="info">
At least one of the optional fields must be provided. The fields you enter should follow the following format:

| Field             | Format                                                                                                                 | Example                        |
|-------------------|------------------------------------------------------------------------------------------------------------------------|--------------------------------|
| `NAME`            | Use `a-z`, `A-Z`, `0-9` and whitespaces only                                                                           | John Doe                       |
| `PHONE_NUMBER`    | Use `0-9` only and should be 3 digits long                                                                             | 98765432                       |
| `EMAIL`           | Have the format of `local-part@domain`                                                                                 | johndoe@gmail.com              |
| `ADDRESS`         | Use any characters                                                                                                     | John street, block 123, #01-01 |
| `TAG`             | Use `a-z`, `A-Z` and `0-9` only. Alternatively, use `RA` or `SOS` which are predefined emergency tags for your contact | friend                         |
| `TELEGRAM`        | Start with the `@` symbol, no whitespace with a minimum length of 5 characters. Use `a-z`, `0-9` and `_` only          | @johndoe                       |
| `SECONDARY_EMAIL` | Have the format of `local-part@domain`                                                                                 | johndoe@hotmail.com            |
| `LINKEDIN`        | Use `a-z`, `A-Z`, `0-9`, `_` and `-` only                                                                              | john-doe-b9a38128a             |
| `BIRTHDAY`        | Have the format of `DD/MM`                                                                                             | 31/10                          |
</box>

Examples:
* `edit 1 tg/@johndoe e2/johndoe@hotmail.com`
    * Edits the first person in your contact list with existing telegram to "@johndoe" and existing secondary email to "johndoe@hotmail.com"
* `edit 2 n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01 t/friend`
    * Edits the second person in your contact list with existing name to "John Doe", existing phone number to "98765432", existing email to "johndoe@gmail.com", existing address to "John street, block 123, #01-01", and a existing tag to "friend"

<box type="warning">

You are not allowed to edit any alternative contact information, i.e. `TELEGRAM`, `SECONDARY_EMAIL`, `LINKEDIN`, `BIRTHDAY` if any of these fields are empty. You will receive an error message that directs you to use [**`addalt`**](#add-alternative-information-to-contact) command.
</box>

###  Update contact's photo

Updates the photo of an existing contact.

Format: `updatephoto INDEX path/NEW_PHOTO_PATH`

Examples:
* `updatephoto 1 path/C:/photos/new_johndoe.jpg`
  * Updates the photo for the 1st person with a new image from the specified path.
* `updatephoto 2 path/D:/images/new_betsycrowe.png`
  * Updates the photo for the 2nd person with a new image from the specified path.

<box type="warning">

You are required to input the absolute path to the photo for this command. If you use an invalid path, a default photo will be chosen for your specified contact.
</box>

###  List all contacts

Shows a list of all contacts.

Format: `list`

###  Delete normal contact

Deletes an existing contact from the address book.

Format: `delete INDEX`

Examples:
* `delete 1`
  * Deletes the 1st person from the list
* `delete 2`
  * Deletes the 2nd person from the list

## Notes

<div style="display:flex; justify-content:space-around; align-items:center;">
  <img src="images/notes/noteswindow2.png" alt="Window with Notes" style="height:400px; margin:10px;">
</div>

Allows you to add notes to a person and remove notes from a person.

You can add notes to a person with the `addnote` command, remove notes from them with the `removenotes` command.

You can view notes by one of two ways: by using the `viewnotes` command, or by clicking on the `Notes` button in the person's information window.

Format: `addnote PERSON_INDEX NOTE_CONTENT`, `removenote PERSON_INDEX NOTE_INDEX` and `viewnotes PERSON_INDEX`

<box type="warning">

Always make sure the indices provided are valid and within the bounds of the list. Invalid indices will result in an error.

</box>

<panel header=":fa-solid-book: **Command Parameter / Syntax Tables**" type="secondary" expanded no-close>
The fields you enter should follow the following format:

| Parameter     | Description                                                                                                 |
|---------------|-------------------------------------------------------------------------------------------------------------|
| `PERSON_INDEX`| The position of the person in the list you want to add a note to. This should be a positive integer, and should be within the bounds of the list. |
| `NOTE_INDEX`  | The position of the note in the person's list of notes you want to remove. This should be a positive integer, and should be within the bounds of the list. |
| `NOTE_CONTENT`| The content of the note you want to add. It has to be non-empty, and can contain any character.              |

</panel>

<br>

<box type="info" icon=":fa-solid-magnifying-glass:">
To manage notes for a person in a list, use the following commands:

| Action                                  | Command                                           | Description                                             |
|-----------------------------------------|---------------------------------------------------|---------------------------------------------------------|
| Add a note to a person                  | `addnote 1 This is a sample note for the person.` | Adds a note to the person at index 1.                   |
| Remove a specific note from a person    | `removenote 1 2`                                  | Removes the 2nd note from the person at index 1.        |
| View all notes of a person              | `viewnotes 1`                                     | Displays all notes of the person at index 1.            |

To add a note, use the `addnote` command followed by the position number and the note text. To remove a note, use the `removenote` command followed by the position number and the note index. To view all notes, use the `viewnotes` command followed by the position number. Closing the notes window can be done via the "Close" button or by pressing ESC.
</box>

## Birthday Notifications

###  Receive upcoming birthday notifications

Receives a pop-up notification for each contact in CampusConnect whose birthday is within a day.

Upon launching the application, if any of your contacts’ birthday in CampusConnect is coming within a day, you should see the following pop-up notification: <br>

![birthdayNotification](images/birthdayNotification.png)

The notification will contain the names of the birthday individuals saved in CampusConnect.

###  Opt out notification [Coming soon]

Opts you out from receiving birthday related notifications, such as turning off actual birthday notification feature.

Format: `optout NOTIFICATION_DESCRIPTION`

- `NOTIFICATION_DESCRIPTION` Mandatory field to enter which only includes the following and are not case-sensitive:
    - `Notify Birthdays`
    - `All`

Examples:
- `optout notify birthdays`
- `optout Notify Birthdays`
- `optout NOTIFY BIRTHDAYS`
    - Requests to opt out from receiving actual birthday notifications in the future.
- `optout all`
    - Requests to turn off all kinds of notifications CampusConnect will send.

Upon request to opt out notification, you should see the following pop-up message: <br>

![optOutNotification](images/optOutNotification.png)

Select `OK` to opt out notifications or `Cancel` to cancel the request.

Below shows some examples of ___invalid usage___ of the command and the response that CampusConnect will provide.

Invalid Input Example | Application Output
---|---
**optout notifications** | Invalid `NOTIFICATION_DESCRIPTION` (refer to aforementioned for the list of `NOTIFICATION_DESCRIPTION` to enter).
**optout** | `NOTIFICATION_DESCRIPTION` cannot be empty.


## Payments
## Find Contacts

Another feature of CampusConnect is the ability to search for contacts based on a variety of criteria. This is useful for quickly finding contacts whose details you may only partially remember, or for finding contacts who match a certain criteria.

This feature involves only 1 command: `find`, which list contacts whose fields match the specified find expression.


Format: `find FIND_EXPRESSION`

Find expressions have a low barrier to entry that allows for simple filtering by field. This basic filtering for contacts is likely sufficient for most of your use cases. We recommend that you first read the [basic filtering](#find-contacts-basic-filtering) section to learn how to perform simple filtering by a single field.

If you then find that the basic filtering is insufficient for your use case, you can read the [advanced filtering](#find-contacts-advanced-filtering) section to learn how to perform more complex filtering.



<panel header="**Supported Fields**" type="primary" id="find-fields-table" expanded no-close>
Across both basic and advanced filtering, the following fields are supported:<br><br>

| Field | Prefix | Description |
| ----- | ------ | ----------- |
| Name  | `n`   | Finds contacts whose names **contain** the given keyword. |
| Phone | `p`   | Finds contacts whose phone numbers **contain** the given digits. |
| Email | `e`   | Finds contacts whose email addresses **contain** the given keyword. |
| Address | `a` | Finds contacts whose addresses **contain** the given keyword. |
| Tag   | `t`   | Finds contacts who have **any tag** that **exactly matches** the given keyword. |
| Birthday | `b` | Finds contacts whose birthdays (in DD-MM format) **contain** the given substring. |
| LinkedIn | `li` | Finds contacts whose LinkedIn usernames **contain** the given keyword. |
| Secondary Email | `e2` | Finds contacts whose secondary email addresses **contain** the given keyword. |
| Telegram | `tg` | Finds contacts whose telegram handles **contain** the given keyword. |
| Note | `nt` | Finds contacts who have **any note** that **contains** the given keyword. |
| Balance | `bal` | Keyword **must be valid balance** (see: [Payments feature format](#tbd)). Finds contacts who **owe user** an amount **more than or equal** to given balance. <br><br> Keyword **may start with a negative sign**, in which case finds contacts who **user owes** more than or equal to given balance. |

</panel>

<br>

<box type="info">

Note that in all cases, the search is case-insensitive for alphabetic characters. For example, `n/Joe` will match contacts who have the name `Joe`, `joE`, `Ajoeia`, `BobJOe Lee`, etc., and `t/friend` will match `friend`, `FriENd`, `FRIEND`, etc.
</box>


<box type="warning">

For now, search keywords cannot contain spaces. For example, `n/John Doe` will not work as expected. Functionality to search for keywords which spaces like `"John Doe"` will be added in a future release.
</box>

### Basic Filtering

Contacts can be filtered by a single field by typing:
- the **prefix** of the field you're searching through, followed by
- a **slash** (`/`), followed by
- the **keyword** you're looking for.

Such a search will return all contacts whose field matches the keyword based on the behavior specified in the [supported fields table](#find-fields-table).

We call this basic block of filtering a **find condition**, which is the smallest unit that act as a valid [**`FIND_EXPRESSION`**](#find-contacts).


<box>

For example, given the following contacts (some fields omitted for brevity):

| Name | Tags |
| ---- | ---- |
| John Doe | neighbor, colleague |
| Jane Doe | neighbor, friend |
| Alex Yeoh | friend |
| Yervis Alexis | girlfriend |


`n/do` is a **find condition** that will return all contacts whose names contain the substring `"do"`, in this case `"John Doe"` and `"Jane Doe"`.

Similarly, `t/friend` is a **find condition** that will return all contacts who have the `"friend"` tag, in this case `"Jane Doe"` and `"Alex Yeoh"` (and **not** `"Yervis Alexis"`, since [supported fields table](#find-fields-table) requires an exact tag match).

Since `n/do` and `t/friend` are both **find conditions**, they can constitute a **`FIND_EXPRESSION`**. The complete commands in each case would be:

- `find n/do`
- `find t/friend`

</box>

### Advanced Filtering

While basic filtering is sufficient for most use cases, you may find that you need to perform more complex filtering. For example, you may want to find all contacts who have the tag `"friend"` *and* whose names contain the substring `"do"`. Or you may want to find all contacts whose addresses contain the substring `"street"` *or* whose names *do not* contain the substring `"ye"`.

You can accomplish this and more using our powerful advanced filtering syntax, which supports arbitrarily-complex **`FIND_EXPRESSIONs`**, which can be composed of many **find conditions** combined or transformed by **logical operators**.

<panel header="**Supported Logical Operators**" type="primary" id="find-logical-operators-table" expanded no-close>

The following logical operators are supported, and are listed in order of precedence (from highest to lowest):

| Operator | Description | Usage |
| -------- | ----------- | ------- |
| `(` and `)` | Parentheses for grouping | `(FIND_EXPRESSION)`
| `!`     | Logical NOT | `!FIND_EXPRESSION`
| `&&`    | Logical AND | `FIND_EXPRESSION && FIND_EXPRESSION`
| <code>&#124;&#124;</code> | Logical OR | <code>FIND_EXPRESSION &#124;&#124; FIND_EXPRESSION</code>

</panel>

<br>

Note that the smallest possible **find expressions** is simply a **find condition**.

**Find expressions** can be nested arbitrarily deeply, and that parentheses can be used to group  **find expressions** together to specify the order of evaluation.

<br>

<box>

For example, given the following contacts (some fields omitted for brevity):

| Name | Tags |
| ---- | ---- |
| John Doe | neighbor, colleague |
| Jane Doe | neighbor, friend |
| Alex Yeoh | friend |
| Yervis Alexis | girlfriend |

The following are valid **`FIND_EXPRESSIONs`**:

- `!n/do` will return all contacts whose names do **not** contain the substring `"do"`, in this case `"Alex Yeoh"` and `"Yervis Alexis"
- `n/do && t/friend` will return all contacts whose names contain the substring `"do"` **and** who have the `"friend"` tag, in this case `"Jane Doe"`.
- `n/do || t/friend` will return all contacts whose names contain the substring `"do"` **or** who have the `"friend"` tag, in this case `"John Doe"`, `"Jane Doe"`, and `"Alex Yeoh"`.
- `n/do && (t/friend || t/colleague)` will return all contacts whose names contain the substring `"do"` **and** who have either the `"friend"` or `"colleague"` tag, in this case `"John Doe"`, `"Jane Doe"`, and `"Alex Yeoh"`.

Note that the last example is **not equivalent** to `n/do && t/friend || t/colleague`. Due to the higher precedence of `&&` compared to `||`, this will return all contacts whose names contain the substring `"do"` **and** who have the `"friend"` tag, **or** who have the `"colleague"` tag, in this case `"Jane Doe"` and `"Alex Yeoh"`.

</box>

--------------------------------------------------------------------------------------------------------------------

# FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CampusConnect home folder.

--------------------------------------------------------------------------------------------------------------------

# Planned Enhancements / Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

# Command summary

Action        | Format, Examples
--------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**       | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Addalt**    | `addalt INDEX [tg/TELEGRAM] [e2/SECONDARY_EMAIL] [li/LINKEDIN]` <br> e.g., `addalt 1 tg/johndoe_telegram e2/johndoe2@example.com`
**Find**      | `find FIELD/KEYWORD [FIELD/KEYWORD]`

