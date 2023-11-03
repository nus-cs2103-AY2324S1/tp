---
layout: page
title: User Guide
---
# BandConnect++ User Guide
Unlock your musical potential with **_BandConnect++_** ! 

**_BandConnect++_** is a powerful desktop app that helps music producers manage their musician contacts and form bands with ease. It is created with a convenient and intuitive Command Line Interface (CLI) that speeds up your work routine.

**_BandConnect++_** is developed by a driven team of musicians/programmers who strives to make life easier for musicians and music producers.  Discover your perfect musical match and create your next big hit now!

--------------------------------------------------------------------------------------------------------------------
## About this guide
Welcome to the *BandConnect++ User Guide*! This user guide walks you through everything about *BandConnect++*. Whether you are new to our app or a seasoned user, you can always find something useful in this guide. 

For first-time users, please go to the [Quick start](#quick-start) section below to start an end-to-end tutorial that gets you onboard. Should you encounter any difficulty understanding the terminology, don't forget to refer to the [Glossary](#glossary)!

For experienced users, if you need help in remembering a particular command, please see [Command Summary](#command-summary). For common troubleshooting, please see [Troubleshooting](#troubleshooting).

## Table of Contents
* [Glossary](#glossary)
* [Quick start](#quick-start)
* [Features](#features)
  * [Command Format](#command-format)
  * [Get help](#get-help--help)
  * [List all](#list-all-musicians-and-bands--list)
  * [Features for musicians](#features-for-managing-musicians)
    * [Add musician](#add-musician--add)
    * [Remove musician](#remove-musician--remove)
    * [Edit musician](#edit-musician--edit)
    * [Tag musician](#tag-musician)
    * [Find musicians](#find-musicians--find)
  * [Features for bands](#features-for-managing-bands)
    * [Create band](#create-band--addb)
    * [Add musician to band](#add-musician-to-band--addm)
    * [List band members](#list-all-members-in-band--findb)
  * [Clear data](#clear-data--clear)
  * [Exit app](#exit-app--exit)
  * [Save data](#save-data)
* [Command Summary](#command-summary)
* [Troubleshooting](#troubleshooting)

## Glossary
To be added.
CLI, GUI, Java
--------------------------------------------------------------------------------------------------------------------

# Quick start
1. Ensure you have Java 11 or above installed in your Computer. If you have never downloaded it before, download from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).


2. Download the latest version of [BandConnect++](https://github.com/AY2324S1-CS2103T-W11-3/tp/releases/latest).


3. Move the file to the home folder you want to access the app. 

     Recommended: Drag the downloaded file to your desktop so that you can access it from there.


4. From the home folder, open "Terminal" on MacOS or "Command Prompt" in Windows, and type `java -jar BandConnect++.jar` to run the application. A GUI similar to the below should appear in a few seconds.
   ![to be updated with components labelled](images/tobeupdated.png)
Note how the app contains some sample data.
Type the command in the command box and press Enter to execute it. 


5. Some example commands you can try:
   * `help` : Open the help window.
   * `list` : Lists all contacts. 
   * `add n/John Doe p/98765432 e/johnd@example.com i/bass g/rock`: Adds rock bassist John Doe.
   * `delete 3` : Deletes the 3rd musician shown in the current list.
   * `clear` : Deletes all contacts. 
   * `exit` : Exits the app.
   
Please refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# Features

## Command Format
to be updated.

## Get help: `help`
Access a link to our user guide.

**Format:** `help`

You will see a window like below, click `Copy URL`, paste the link in any web browser to view this user guide.

![images/help/helpWindow.png](images/help/helpWindow.png)

## List all musicians and bands: `list`
View all musicians and bands in their separate panels.

**Format:** `list`


## Features for managing musicians
### Add musician: `add`

Adds one musician to the contact book. 

Name, phone number, email, tag, instrument, genre about the musician can all be included.

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]... [i/INSTRUMENT]... [g/GENRE]...`

**Examples:**
* `add n/John Doe p/98765432 e/johnd@example.com t/bestman i/violin g/classical`
* `add n/Betsy Crowe e/pianistbetsy@smtp.com p/87988039 i/piano g/pop g/rock`

**Upon success:**
A success message like below will be displayed.

```
New musician added: John Doe; Phone: 98765432; Email: johnd@example.com; Tags: [bestman]; Instruments: [violin]; Genres: [classical]
```

**Upon failure:**

If you input a musician which is already in your contact book (ie. have the same phone number or email as an existing contact). You will be shown an error message like below. Please re-enter the correct information.
```
This musician already exists in your contact list
```

### Remove musician: `remove`

Removes a musician from your contact list.
From the current `My Musicians` panel, find the index of the musician to be deleted.

**Format:** `delete INDEX`

**Examples:**
* `delete 1`

**Upon success:**

You will see a message indicating successful removal of the first musician contact in the musician panel like below:

```
Deleted Musician: John Doe; Phone: 98765432; Email: johnd@example.com; Tags: [bestman]; Instruments: [violin]; Genres: [classical]
```

**Upon failure:**

Should you input an index out of the range of the current `My Musicians` list, you will see the error message below:

```
The musician index provided is invalid
```

Please verify that the index is correct and try again.

### Edit musician: `edit`

### Tag musician
[combine tag i and g]
with instruments: `tag instrument`

Tags a musician with one or more instruments he/she is proficient in.

**Format:** `tag instrument INDEX i/INSTRUMENT…`

**Constraints:**
* `INDEX` must be a positive integer 1, 2, 3, …​
* The instrument tag must be *non-empty*.
* You have to supply *at least one instrument tag* to the musician you are tagging.

**Examples:**
* `tag instrument 1 i/Piano i/Violin`
* `tag instrument 2 i/Drums`

**Upon success:**

You will see a message indicating successful addition of instruments like below:
[insert image]

**Upon failure:**

Should you try to tag a musician with zero instrument tags or empty tags, i.e., `tag instrument 1 i/` or
`tag instrument 1`, you will see a message like below:
[insert image]

Tag musician with genres: `tag genre`

Tags a musician with one or more genres he/she is proficient in.

**Format:** `tag genre INDEX g/GENRE…`

**Constraints:**
* `INDEX` must be a positive integer 1, 2, 3, …​
* The genre tag must be *non-empty*.
* You have to supply *at least one genre tag* to the musician you are tagging.

**Examples:**
* `tag genre 1 g/rock g/pop`
* `tag genre 2 g/jazz`

**Upon success:**

You will see a message indicating successful addition of tags like below:
[insert image]

**Upon failure:**

Should you try to tag a musician with zero genre tags or empty tags, i.e., `tag genre 1 g/` or `tag genre 1`,
you will see a message like below:
[insert image]

### Find musicians: `find`

Finds all musicians whose names contain any of the given keywords.

**Format:** `find KEYWORD`

Examples:
* `find John` returns `john` and `John Doe`

**Upon success:**

You will see a list of musicians as follows:
[insert image]

**Upon failure:**

Should you input `find` without any keyword, you will see a message like below:
[insert image]

## Features for managing bands
### Create band: `addb`

Creates a band with the specified band name.

**Format:** `addb n/BANDNAME`

Examples:
* `addb n/My Garage Band`

**Upon success:**

You will see a message indicating successful addition of the band like below:
[insert image]

**Upon failure:**

Should you input a band which is already in your contact book (ie. have the same name as an 
existing band), you will see an error message showing the possible error. Please input a different name for the new 
band or change the name of the existing band.
[insert image]

### Add musician to band: `addm`

Adds a musician to a specified band.

**Format:** `addm bin/BANDINDEX min/MUSICIANINDEX`

Examples:
* `addm bin/1 min/2` adds the second musician in the contact list to the first band in the list of bands.

**Upon success:**

You will see a message indicating successful addition of the musician into the band like below:
[insert image]

**Upon failure:**

Should you input an index that does not exist, you will see an error message showing the possible error. 
Please input a different index as pointed out by the error message.
[insert image]

### List all members in band: `findb`
List the band members of a selected band. From `My Bands` panel, find the complete band name of the band.

[warning box]Run `list` before running this command!

**Format:** `findb BANDNAME`

**Examples:**
* `findb theory X` 

**Upon success:**
* Before: From `list` state
    ![findb_before.png](images%2Fband-features%2Ffindb_before.png)
* After: On the left, `My Musicians` panel will display all musicians in the band. On the right, `My Bands` panel will display the band of interest.
![findb_after.png](images%2Fband-features%2Ffindb_after.png)

**Upon failure:**
If you input an invalid band name, an error message `Band does not exist!` will be displayed. Please input a valid band name and enter the command again.

## Clear data: `clear`

## Exit app : `exit`

Exits the program.

Format: `exit`

## Save data

BandConnect++ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

Congratulations! You are now ready to use **_BandConnect++_**!

If you wish, please refer to the below section for a more succinct [command summary](#command-summary).

--------------------------------------------------------------------------------------------------------------------
## Command summary


| Action             | Format, Examples                                                                                               |
|--------------------|----------------------------------------------------------------------------------------------------------------|
| **Add**            | `add n/NAME p/PHONE_NUMBER e/EMAIL i/INSTRUMENT`<br> e.g., `add n/John Doe p/98928479 e/johndpiano@xmail.com i/Piano` |
| **Remove**         | `remove INDEX`<br> e.g., `remove 1`                                                                            |
| **Tag Instrument** | `tag instrument INDEX i/INSTRUMENT…​`<br> e.g.,`tag instrument 1 i/piano i/guitar`                             |
| **Tag Genre**      | `tag genre INDEX g/GENRE…​`<br> e.g., `tag genre 1 g/rock g/pop`                                               |
| **Find**           | `find KEYWORD`                                                                                                 |



--------------------------------------------------------------------------------------------------------------------
## Troubleshooting

[to be added soon]

