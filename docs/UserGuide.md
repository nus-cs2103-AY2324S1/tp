---
layout: default.md
title: "User Guide"
pageNav: 3
---


# Welcome to ProjectPRO's User Guide

--------------------------------------------------------------------------------------------------------------------


## Introductions

Achieve simplicity while optimizing efficiency.


ProjectPRO is a desktop application designed to help university students organize their projects. Here is how ProjectPRO can streamline your project management process.


- Categorize your contacts into various project groups.
- Track key information from your projects.
- Find free time for your group meetings.


ProjectPRO is optimized for use via a Command Line Interface (CLI) while preserving the advantages of maintaining an attractive user interface. ProjectPRO utilizes simple and easy-to-remember commands to execute different tasks, enhancing our user's experience. For instance, adding a contact is executed with a simple `add` command, as listed in our Features Section. Unlock more time in your day, store your information the smart way.

--------------------------------------------------------------------------------------------------------------------


**New here?** View our instructions for first-time users [here](#first-time-user).


**Used ProjectPRO before?** Click [here](#experienced-user) to recall what features we have!


--------------------------------------------------------------------------------------------------------------------


## Table of Contents
- [1. Introductions](#introductions)
- [2. Table of Contents](#table-of-contents)
- [3. Using Our Guide](#using-our-guide)
- [4. Quick Start](#quick-start)
- [5. Glossary](#glossary)
- [6. Features](#features)
- [7. Commands to Manage Contacts](#commands-to-manage-contacts)
  - [7.1 Adding a Contact `add`](#adding-a-contact-add)
  - [7.2 Deleting a Contact `delete`](#deleting-a-contact-delete)
  - [7.3 Finding a Contact `find`](#finding-a-contact-find)
  - [7.4 Listing all Contacts `list`](#listing-contacts-list)
- [8. Commands to Manage Groups](#commands-to-manage-groups)
  - [8.1 Adding a Group `new`](#adding-a-group-new)
  - [8.2 Deleting a Group `delete`](#deleting-a-group-delete)
  - [8.3 Adding remarks to a Group `remark`](#adding-remarks-to-a-group-remark)
  - [8.4 Finding a Group `find`](#finding-a-group-find)
  - [8.5 Listing a Group `list`](#listing-a-group-listgroup)
  - [8.6 Grouping a Contact `group`](#grouping-a-contact-group)
  - [8.7 Ungrouping a Contact `ungroup`](#ungrouping-a-contact-ungroup)
- [9. Commands to Manage Time](#commands-to-manage-time)
  - [9.1 Adding Time to a Contact `addtime`](#adding-time-to-a-contact-addtime)
  - [9.2 Removing Time from a Contact `deletetime`](#removing-time-from-a-contact-deletetime)
  - [9.3 Listing Time from a Contact `listtime`](#listing-time-from-a-contact-listtime)
  - [9.4 Add Meeting to Group `addmeeting`](#add-meeting-to-a-group-addmeeting)
  - [9.5 Remove Meeting Time from a Group `deletetime`](#remove-meeting-time-from-a-group-deletetime)
  - [9.6 Listing Meeting Time from a Group `listtime`](#listing-meeting-time-from-a-group-listtime)
  - [9.7 Finding Free Time of a Group `findfreetime`](#finding-free-time-of-a-group-findfreetime)
- [10. General Commands](#general-commands)
  - [10.1 Viewing Help `help`](#viewing-help--help)
  - [10.2 Clearing All Data `clear`](#clearing-all-data--clear)
  - [10.3 Exit ProjectPRO `exit`](#exit-projectpro--exit)


--------------------------------------------------------------------------------------------------------------------


## Using Our Guide

--------------------------------------------------------------------------------------------------------------------


This guide explains how you can use ProjectPRO to add contacts and manage your groups of contacts effectively by using our commands.

Here are some annotations used in this guide:




<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;"> User Input</code>
    Description of what you are allowed to type.</p>
</div>
<p></p>
<div style="border-left: 4px solid #d9534f; background-color: #FFD4D4; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">:exclamation: Warning!</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;"> inline code</code>
    Pay attention to these points as they could lead to unexpected issues.</p>
</div>

--------------------------------------------------------------------------------------------------------------------

#### First Time User

--------------------------------------------------------------------------------------------------------------------


We are happy to have you on board as a first-time user of ProjectPRO! To get started, visit our [Quick Start](#quick-start) to set up ProjectPRO. Once it's done, you can dive into the Features section to explore the full range of commands and learn more about what ProjectPRO has to offer. Refer to the image below to get acquainted with our user interface.


![Image 1: ProjectPRO's user interface](images/UG/Overview_of_GUI.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">ProjectPRO user interface</p>
</div>

--------------------------------------------------------------------------------------------------------------------


#### Experienced User

--------------------------------------------------------------------------------------------------------------------


Welcome back to ProjectPRO. Simply head over to our Features section to gain insights into our commands. If you are looking for a quick reference, click [here](#table-of-contents) for our table of contents.

--------------------------------------------------------------------------------------------------------------------

## Quick Start

--------------------------------------------------------------------------------------------------------------------

1. Ensure you have Java 11 or above installed on your computer.
   - Simply go to your terminal and type `java -version`. ![here](images/UG/QuickStart1.png)

2. Download the latest ProjectPRO.jar from [here](https://github.com/AY2324S1-CS2103T-T10-3/tp/releases).
   - ![here](images/UG/QUICKSTART2.png)

3. Move the JAR file to a folder where you want to store your project details. For example, create a folder named ProjectPRO and place it on your desktop.
   - For Mac Users:

     - ![MACGUIDE](images/UG/QUICKSTART3.png)
   - For Windows Users:
   
     - ![WINDOWSGUIDE](images/UG/QUICKSTART4.png)

4. Launch ProjectPRO.
   1. Open a command terminal
   2. type `cd Desktop` , followed by `cd ProjectPro`
   3. type `java -jar ProjectPRO.jar` to run the application.

5. Start using ProjectPRO!

--------------------------------------------------------------------------------------------------------------------


## Glossary

--------------------------------------------------------------------------------------------------------------------


**Definitions**


Written below are the definitions of the terminology used throughout the User Guide:


| Term      | Definition                                                                                 |
|-----------|--------------------------------------------------------------------------------------------|
| Parameter | Parameter indicates the type of information required for a particular command.             |
| Command   | A special word used together with parameters to execute a particular action. E.g., `find`. |
| GUI       | Graphical User Interface, the visual display of ProjectPRO.                                |


**Parameter Information**


Written below are some commonly used parameters, what they represent, and examples:


| Parameter | Description                      | Constraints                                                                                                                                             | Valid Examples                           | Invalid Examples                                |
|-----------|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------|-------------------------------------------------|
| `n/`      | Contact name of the student      | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                        | John Doe, David Li 2                     | Kishen s/o Kasinathan, ナルト, அசிங்கமான           |
| `p/`      | Phone number of the student      | Positive integer with 3 or more digits                                                                                                                  | 999, 98765432, 18003569377               | 1-800-356-9377, 0, -1, 98431234.5               |
| `e/`      | Email of the student             | Email prefix: Alphanumeric characters (a to z, A to Z, 0 to 9), @, Email Domain                                                                         | example@gmail.com, example@moe.edu.sg    | example@!.com, example@moed.edu.s               |
| `g/`      | Name of the group                | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                        | CS2103T, Group 3                         | Group 3!, 1                                     |
| `r/`      | Group remark                     | N/A                                                                                                                                                     | Zoom link: CS2101.zoom, 123!@#$#@        | N/A                                             |
| `t/`      | Time interval of student / group | timings are written with the first 3 letters of the day and time in 24 hour format, with a `-` between the timings. Start time cannot be after end time | mon 1300 - mon 1400, sat 1000 - sun 1300 | monday 1300 - tuesday 1200, wed 1300 - wed 1000 |

**Command format**

Written below are some extra information regarding our command formats:

| Format                      | Explanation                                      | Examples                                                                                                                                              |
|-----------------------------|--------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| Words in `UPPER_CASE`       | These are values written by you                  | `new g/GROUP_NAME` can be written as new `g/CS2103T`                                                                                                  |
| Items in `SQUARE_BRACKETS`  | These are optional values that can be left empty | `add n/NAME p/PHONE e/EMAIL [g/GROUP_NAME]` can be used as `add n/John p/999 e/example@gmail.com` and `add n/John p/999 e/example@gmail.com g/CS2103` |
| Items with `...` after them | These are values that can be used multiple times | `addtime n/NAME t/time...` can be used as `addtime n/John t/mon 1200 - mon 1300 t/tue 1400 - tue 1500`                                                |

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h3 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Notes</h3>
  <li> All user input is case-sensitive, unless stated otherwise.</li>
  <li> Command prefixes can be in any order.</li>
</div>

--------------------------------------------------------------------------------------------------------------------


## Features

--------------------------------------------------------------------------------------------------------------------

## Commands to Manage Contacts

--------------------------------------------------------------------------------------------------------------------


### Adding a contact `add`
You can create a contact in your contact list.


**Format:** `add n/NAME p/PHONE e/EMAIL [g/GROUP_NAME]`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">NAME</code>
    must be alphanumeric, cannot be blank and must not exist in the contact list.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">PHONE</code>
    must be a positive integer with at least 3 digits and must not exist in the contact list.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">EMAIL</code>
    must be alphanumeric with a @ domain, end with a domain label at least 2 characters long and must not exist in the contact list.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric. This is an optional parameter.</p>
</div>


**Example(s):**
- `add n/John Doe p/98765432 e/johnd@example.com g/CS2103T`
  This creates a contact in the contact list, named John Doe, who is in group CS2103T.
- `add n/John Doe p/98765432 e/johnd@example.com`
  This creates a contact in the contact list, named John Doe, who is not in any group.

![Manage Contacts: Add](images/features/Managecontacts_add.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have added John Doe to your contacts, at contact number 7</p>
</div>

**Potential error(s):**
- Incorrect format (e.g., no prefix, duplicate prefixes).
- The contact you are trying to add already exists in your contact list: `This person already exists in your contact list`.

--------------------------------------------------------------------------------------------------------------------

### Deleting a contact `delete`
You can delete a contact from your contact list.


**Format:** `delete n/NAME`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size: 13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">NAME</code>
    must be alphanumeric and cannot be blank.</p>
  <p style="font-size: 13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">NAME</code>
    must be an existing contact in the contact list.</p>
</div>


**Example(s):**
- `delete n/John Doe`
  This deletes John Doe from the contact list.

![Manage Contacts: Delete](images/features/Managecontacts_delete.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have deleted John Doe, previously contact number 7, from your contacts</p>
</div>

**Potential error(s):**
- Invalid format (e.g., no prefix, duplicate prefixes).
- The contact you are trying to delete does not exist in your contact list: `No person with such name found. Please provide the person's full name as in the existing contact list`.

--------------------------------------------------------------------------------------------------------------------

### Finding a contact `find`
You can find all the contacts from your contact list with the matching keywords.


**Format:** `find n/KEYWORDS_IN_NAME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size: 13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">KEYWORDS_IN_NAME</code>
    must be alphanumeric and cannot be blank.</p>
  <p style="font-size: 13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">KEYWORDS_IN_NAME</code>
    is not case-sensitive.</p>
</div>


**Example(s):**
- `find n/alex bernice`
  This displays all the contacts with names containing Alex or Bernice.

![Manage Contacts: Find](images/features/Managecontacts_find.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have found all your contacts with the name Alex or Bernice</p>
</div>

**Potential error(s):**
- Invalid format (e.g., no prefix, duplicate prefixes).

--------------------------------------------------------------------------------------------------------------------


### Listing contacts `list`
You can list all the contacts in your contact list.


**Format:** `list`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size: 13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">User Input</code>
    No additional input required.</p>
</div>

**Example(s):**
- `list`
  This displays all the contacts in the contact list.

![Manage Contacts: List](images/features/Managecontacts_list.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You can view all the contacts in your contact list</p>
</div>


**Potential error(s):**
- Extra inputs detected.

--------------------------------------------------------------------------------------------------------------------


## Commands to Manage Groups

--------------------------------------------------------------------------------------------------------------------


### Adding a group `new`
You can create a new group in your contact list.


**Format:** `new g/GROUP_NAME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size: 13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric, cannot be blank, and must not exist in the contact list.</p>
</div>


**Example(s):**
- `new g/CS2103T tp` This creates a new group named "CS2103T tp".

![Manage Groups: New](images/features/Managegroup_new.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have created a new group called 'CS2103T tp'</p>
</div>

**Potential error(s):**
- Invalid command format (e.g., no prefix, duplicate prefixes).
- The group you are trying to add already exists in your contact list: `This group already exists in the contact list`.

--------------------------------------------------------------------------------------------------------------------


### Deleting a group `delete`
You can delete a group in your contact list.


**Format:** `delete g/GROUP_NAME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric, cannot be blank and must exist in the contact list.</p>
</div>


**Example(s):**
- `delete g/CS2103T tp`
  This deletes the group "CS2103T tp" from your contact list, given "CS2103T tp" is an existing group in your contact list.

![Manage Group: Delete](images/features/Managegroup_delete.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have deleted the group "CS2103T tp" from your contact list</p>
</div>


**Potential error(s):**
- Incorrect format (e.g., no prefix, duplicate prefixes).
- The group you are trying to delete does not exist in your contact list: `No group with such name found. Please provide the group's full name as in the existing contact list`.

--------------------------------------------------------------------------------------------------------------------


### Adding remarks to a group `remark`

You can add remarks to a group in your contact list.


**Format:** `remark g/GROUP_NAME r/REMARK`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric, cannot be blank and must exist in the contact list.</p>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">REMARK</code>
    can take on any value.</p>
</div>


**Example:**
- `remark g/CS2103T r/quiz tomorrow`
  This adds the remark "quiz tomorrow" to the existing "CS2103T" group in your contact list.

![Manage Group: Remark](images/features/Managegroup_remark.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have added the group remark "quiz tomorrow" to the group "CS2103T" in your contact list</p>
</div>

**Potential errors(s):**
- Incorrect format (e.g. no prefix, duplicate prefixes).
- The group you entered does not exist in your contact list: `No group with such name found. Please provide the group's full name as in the existing contact list.`

--------------------------------------------------------------------------------------------------------------------


### Finding a group `find`
You can find a group in your contact list. This allows you to view the group's members and remarks.


**Format:** `find g/GROUP_NAME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric, cannot be blank and must exist in the contact list.</p>
</div>

**Example(s):**
- `find g/CS2103T`
  This returns the members and remarks of the existing "CS2103T" group in your contact list.

![Manage Group: Find](images/features/Managegroup_find.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You are now able to view all details regarding the group "CS2103T" in your contact list</p>
</div>


**Potential error(s):**
- Incorrect format (e.g. no prefix, duplicate prefixes).


- The group you are trying to find does not exist in your contact list: `No group with such name found. Please provide the group's full name as in the existing contact list`.


### Listing a group `listgroup`
You can list all the groups in your contact list.


**Format:** `listgroup`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
   No additional parameters.
</div>


**Example(s):**
- `listgroup`
  This lists all the groups in your contact list.

![Manage Group: List Group](images/features/Managegroup_listgroup.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have listed all the groups in your contact list</p>
</div>


**Potential error(s):**
- Extra inputs detected.

--------------------------------------------------------------------------------------------------------------------


### Grouping a Contact `group`
You can add an existing contact to an existing group.


**Format:** `group n/NAME g/GROUP_NAME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">NAME</code>
    must be alphanumeric, cannot be blank, must be an existing contact in your contact list, and must not be a member of GROUP_NAME</code>.</p>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric, cannot be blank, and must be an existing group in your contact list.</p>
</div>


**Example(s):**
- `group n/Bernice Yu g/CS2103T`
  This adds your contact "Bernice Yu" into the group "CS2103T".

![Manage Group: Group](images/features/Managegroup_group.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have added "Bernice Yu" to the group "CS2103T" in your contact list</p>
</div>


**Potential error(s):**
- Incorrect format (e.g., no prefix, duplicate prefixes).
- The contact you are trying to add is already a member of the group: `Alex Yeoh is already in this group: CS2103T`.

--------------------------------------------------------------------------------------------------------------------

### Ungrouping a Contact `ungroup`

You can remove a contact from a group.


**Format:** `ungroup n/NAME g/GROUP_NAME`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">NAME</code>
    must be alphanumeric, cannot be blank, must be an existing contact in your contact list, and must be a member of GROUP_NAME</code>.</p>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric, cannot be blank, and must be an existing group in your contact list.</p>
</div>


**Example(s):**
- `ungroup n/Alex Yeoh g/CS2103T`
  This removes your contact "Alex Yeoh" from the group "CS2103T".

![Manage Group: Ungroup](images/features/Managegroup_ungroup.png)
<div align="center">
  <p style="font-size: 11px; font-style: italic;">You have removed "Alex Yeoh" from the group "CS2103T" in your contact list</p>
</div>


**Potential error(s):**
- Incorrect format (e.g., no prefix, duplicate prefixes).
- The contact you are trying to remove is not a member of the group: `Charlotte Oliveiro is not in this group: CS2103T`.

--------------------------------------------------------------------------------------------------------------------

## Commands to Manage Time

--------------------------------------------------------------------------------------------------------------------

### Adding Time to a Contact `addtime`
You can add time slots when your contacts are available.


**Format:** `addtime n/NAME t/FREE_TIME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">    NAME</code>
     must be alphanumeric and cannot be blank.
  </p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">FREE_TIME</code>
  must be a time slot within the current weekly schedule. Eg. <code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">Sat 1000 - Mon 1000</code> is not allowed as the monday here refers to next week, violating the current weekly schedule pattern.</p>
</div>

<p></p>
<div style="border-left: 4px solid #d9534f; background-color: #FFD4D4; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">:exclaimation: Warning!</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">NAME</code>
  ensure the contact exists in your contact list.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">FREE_TIME</code>
  must not clash with existing time slot to the contact being added to.</p>
 <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">FREE_TIME</code>
  if more than one time slot is added into the input, it cannot clash with one another.</p>
</div>

**Example(s):**
- `addtime n/Alex Yeoh  t/mon 1400 - mon 1600`
This adds a time slot when Alex Yeoh is available in your contact list.

![](images/features/Managetime_addtime.png)

<div align="center">
  <p style="font-size: 11px; font-style: italic;">You just added MON 1400 - MON 1600 to Alex Yeoh to indicate that he is free during that time</p>
</div>

**Potential error(s):**
- Contact does not exist in the contact list.
- The time slot you are trying to add is not valid.


### Removing Time from a Contact `deletetime`
You can remove available time slots of your contacts.


**Format:** `deletetime n/NAME t/FREE_TIME`

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;"> :heavycheckmark: Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">    NAME</code>
     must be alphanumeric and cannot be blank.
  </p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">FREE_TIME</code>
  must match to the contact's existing time.</p>
</div>

<p></p>
<div style="border-left: 4px solid #d9534f; background-color: #FFD4D4; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">:exclaimation: Warning!</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">NAME</code>
  ensure the contact exists in your contact list.</p>
 <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">FREE_TIME</code>
  if more than one time slot is added into the input, it cannot clash with one another.</p>
</div>

**Example(s):**
- `deletetime n/Alex Yeoh  t/mon 1400 - mon 1600`
  This removes a time slot when Alex Yeoh is available from your contact list.

![](images/features/Managetime_deletetimecontact.png)

<div align="center">
  <p style="font-size: 11px; font-style: italic;">You just deleted MON 1400 - MON 1600 time slot from Alex Yeoh to indicate that he is not free during that time anymore</p>
</div>

**Potential error(s):**
- Contact does not exist in the contact list.
- Time slot does not exist for the contact.
- Invalid time slot format.

--------------------------------------------------------------------------------------------------------------------

### Listing Time from a Contact `listtime`
You list all available time slots of your contacts.


**Format:** `listtime n/NAME`
- Provide the full name of the contact using the `n/` prefix.
<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">NAME</code> 
    must be alphanumeric and cannot be blank.</p>
</div>

<p></p>


**Example(s):**
- `listtime n/Alex Yeoh`
  This lists all time slots when Alex Yeoh is available from your contact list.

![](images/features/Managetime_listtimecontact.png)

<div align="center">
  <p style="font-size: 13px; font-style: italic;">We see that Alex Yeoh is free on Mondays from 1400 - 1600</p>
</div>

**Potential error(s):**
- Contact does not exist in the contact list.

--------------------------------------------------------------------------------------------------------------------

### Add Meeting to a Group `addmeeting`
You can add a meeting time slot for your group.


**Format:** `addmeeting g/GROUP_NAME t/MEETING_TIME`
- Provide the full name of the group using the `g/` prefix.
- Provide the time slot of the meeting using the `t/` prefix.
- Time slot is with respect to the weekly schedule.

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric and cannot be blank.</p>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">MEETING_TIME</code>
    must be a time slot within a weekly schedule.</p>
  <p style="color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">MEETING_TIME</code>
    must not be a time slot already added to the group.</p>
</div>
<p></p>
<div style="border-left: 4px solid #d9534f; background-color: #FFD4D4; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Warning!</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">MEETING_TIME</code>
    can be added even if members are not available. Do coordinate with your group members on the most suitable time slots.</p>
</div>

**Example(s):**
- `addmeeting g/CS2103T  t/mon 1400 - mon 1600`
  This adds a meeting for your group CS2103T.

![](images/features/Managetime_addmeeting.png)

<div align="center">
  <p style="font-size: 13px; font-style: italic;">You have added a CS2103T meeting on Mondays from 1400 - 1600</p>
</div>

**Potential error(s):**
- Group does not exist in the contact list.
- Invalid time slot format.

--------------------------------------------------------------------------------------------------------------------

### Remove Meeting Time from a Group `deletetime`
You can remove meeting times from your groups.


**Format:** `deletetime g/GROUP_NAME t/MEETING_TIME`
- Provide the full name of the group using the `g/` prefix.
- Provide the time slot of the meeting using the `t/` prefix.
- Time slot is with respect to the weekly schedule.

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric and cannot be blank.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">MEETING_TIME</code>
    must be a time slot within a weekly schedule.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">MEETING_TIME</code>
    must be a time slot already added to the group.</p>
</div>
<p></p>

**Example(s):**
- `deletetime g/CS2103T t/mon 1400 - mon 1600`
  This removes a meeting time slot from the group CS2103T in your contact list.

![](images/features/Managetime_deletetimegroup.png)

<div align="center">
  <p style="font-size: 13px; font-style: italic;">You have deleted the CS2103T meeting on Mondays from 1400 - 1600</p>
</div>

**Potential error(s):**
- Group does not exist in the contact list.
- Group does not contain meeting time.
- Invalid time slot format.

--------------------------------------------------------------------------------------------------------------------

### Listing Meeting Time from a Group `listtime`
You list meeting time for your groups.


**Format:** `listtime g/GROUP_NAME`
- Provide the full name of the group using the `g/` prefix.

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric and cannot be blank.</p>
</div>
<p></p>


**Example(s):**
- `listtime g/CS2103T`
  This lists all meeting times of the CS2100 group.

![](images/features/Managetime_listtimegroup.png)

<div align="center">
  <p style="font-size: 11px; font-style: italic;">You can see all the meetings you have for CS2103T/p>
</div>

**Potential error(s):**
- Group does not exist in the contact list.

--------------------------------------------------------------------------------------------------------------------

### Finding Free Time of a Group `findfreetime`
You can find a meeting time slot for your group where everyone is available.


**Format:** `findfreetime g/GROUP_NAME d/DURATION`
- Provide the full name of the group using the `g/` prefix.
- Provide the duration of the meeting using the `d/` prefix.

<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must be alphanumeric and cannot be blank.</p>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">DURATION</code>
    must be a whole number representing the meeting duration in minutes.</p>
</div>
<p></p>
<div style="border-left: 4px solid #d9534f; background-color: #FFD4D4; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Warning!</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #FB9A9A; padding: 2px; border-radius: 2px;">GROUP_NAME</code>
    must contain contacts with their free time slots. </p>
</div>



**Example(s):**
- `findfreetime g/CS2100 d/60`
  This finds a common meeting time of 60 minutes for your CS2103T group.

![](images/features/Managetime_findfreetime.png)

<div align="center">
  <p style="font-size: 13px; font-style: italic;">You can coordinate meetings easily</p>
</div>


**Potential error(s):**
- Group does not exist in the contact list.
- Contacts in the group did not add their available time slots.

--------------------------------------------------------------------------------------------------------------------

## General Commands

--------------------------------------------------------------------------------------------------------------------

### Viewing Help: `help`
You can view the link to our UserGuide.


**Format:** `help`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">User Input</code>
    No additional input required.</p>
</div>
<p></p>

**Example(s):**
- `help`

![](images/features/Generalcommand_help.png)

<div align="center">
  <p style="font-size: 13px; font-style: italic;">You can follow the link to get help!</p>
</div>


**Potential error(s):**
- Extra inputs detected.

--------------------------------------------------------------------------------------------------------------------


### Clearing All Data: `clear`
Clears all entries from the contact list.


**Format:** `clear`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">User Input</code>
    No additional input required.</p>
</div>
<p></p>


**Example(s):**
- `clear`

![](images/features/Generalcommand_clear.png)

<div align="center">
  <p style="font-size: 11px; font-style: italic;">You can remove all information at once</p>
</div>



**Potential error(s):**
- Extra inputs detected.


### Exit ProjectPRO: `exit`
You can exit ProjectPRO.


**Format:** `exit`


<p></p>
<div style="border-left: 4px solid #56494C; background-color: #F1DEC6; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">Acceptable values</h5>
  <p style="font-size:13px; color: #000; margin: 8px 0;"><code style="color: #555; background-color: #F0C481; padding: 2px; border-radius: 2px;">User Input</code>
    No additional input required.</p>
</div>
<p></p>


**Potential error(s):**
- Extra inputs detected.

--------------------------------------------------------------------------------------------------------------------

### Saving the data
ProjectPRO saves automatically after any command changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

### Editing the data file
ProjectPRO's data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.
<div style="border-left: 4px solid #d9534f; background-color: #FFD4D4; color: #000; padding: 10px; margin: 5px 0; border-radius: 6px;">
  <h5 style="color: #56494C; margin: 0 0 5px 0; padding: 0;">:exclamation: Warning!</h5>
    If your changes to the data file makes its format invalid, ProjectPRO will discard all data and start with a sample data file. Hence, it is recommended to take a backup of the file before editing it.
</div>
