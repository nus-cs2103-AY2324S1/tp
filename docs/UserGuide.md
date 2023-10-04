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
- Tracks debts owed by you to your friends or owed to you by your friends

This user guide shows you how to get started with using CampusConnect -- it has easy-to-understand explanations and examples to help you grasp its various features.

For a more technical and comprehensive overview of CampusConnect's codebase, please refer to our [Developer Guide](DeveloperGuide.md).

# Table of Contents
1. Quick Start
2. Features 
   - Request help [Coming Soon]
   - Add normal contact
   - Add emergency contact
   - Add alternative information to contact
   - Upload contact's photo
   - Update contact's photo
   - Search contact
   - List all contacts
   - List emergency contacts
   - Delete normal contact
   - Delete emergency contact
   - Undo last action [Coming Soon]
   - Receive actual birthday notification 
   - Receive upcoming birthday notification [Coming Soon]
   - Opt out notification
   - Track payment [Coming Soon]
   - Change language [Coming Soon]
3. Troubleshooting / FAQ

--------------------------------------------------------------------------------------------------------------------

## Quick start

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

   * `addemer 3 RA` : Marks the 3rd contact in the list as an RA (Residential Assistant).

   * `find name/Doe` : Finds all contacts who have "Doe" in their name.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional, ___with `addalt` as an exception. (Refer to [addalt](#addalt) under Features)___<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Request help [Coming Soon]
### Add normal contact

### Add emergency contact

Adds an existing contact as an emergency contact.

Format: `addemer INDEX [tag/TAG]`
* `tag/TAG` Optional tag indicating the type of emergency contact, e.g. RA (residential assistant) or SO (security officer)

Examples:
* `addemer 1 RA`
  * Indicates that the contact at index 1 is the residential assistant (RA) for contact during emergencies

###  Add alternative information to contact
###  Upload contact's photo
###  Update contact's photo
###  Search contact
1. Search name
2. Search phone number
3. Search email
4. Search address
5. Search tag

6. Search multiple fields
###  List all contacts

###  List emergency contacts

Lists all emergency contacts that have been registered.

Format: `listemer [tag/TAG]`
* `tag/TAG` Optional tag to filter for emergency contacts of a certain type, e.g. RA (residential assistant) or SO (security officer)

Examples:
* `listemer`
* `listemer tag/RA`
  * Only lists emergency contacts tagged as residential assistants (RA)

###  Delete normal contact
###  Delete emergency contact

Removes contact as an emergency contact.

Note: Contact will not be deleted, only unmarked as an emergency contact.

Format: `delemer INDEX`

Examples:
* `delemer 1`
  * Indicates that contact at index 1 is no longer an emergency contact

###  Undo last action [Coming Soon]
###  Receive actual birthday notification
###  Receive upcoming birthday notification [Coming Soon]
###  Opt out notification
###  Track payment [Coming Soon]
###  Change language [Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CampusConnect home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action        | Format, Examples
--------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**       | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Addemer**   | `addemer INDEX [tag/TAG]`
**Delemer**   | `delemer INDEX`
**Listemer**  | `listemer [tag/TAG]`
