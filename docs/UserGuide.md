---
layout: page
title: User Guide
---
# BandConnect++ User Guide
BandConnect++ is a powerful desktop app that helps musicians manage contact with other musicians to easily form a band. You can use it to manage musician contacts, select band members, and form a band of your preference, all within a few clicks or a few seconds of typing!

--------------------------------------------------------------------------------------------------------------------
## About this guide
Welcome to the *BandConnect++ User Guide*! This user guide provides an in-depth documentation on everything about *BandConnect++*, including installation, set up, features, common FAQ and troubleshooting recommendations. 

For first-time users, please go to the [Quick start](#quick-start) section below to start an end-to-end tutorial that gets you onboard. Should you encounter any difficulty understanding the terminology, don't forget to refer to the [Glossary](#glossary)!

### Table of Contents
* [Glossary](#glossary)
* [Quick start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)
* [Troubleshooting](#troubleshooting)


## Glossary
To be added.


## Quick start
To be added.

--------------------------------------------------------------------------------------------------------------------

## Features

### Formatting Convention

### Adding a musician contact: `add`

Adds a musician to the contact book. Name, phone number, email, and instrument can be recorded.

**Format:** `add n/NAME p/PHONE_NUMBER e/EMAIL i/INSTRUMENT`

**Examples:**
* `add n/John Doe p/98765432 e/johnd@example.com i/Violin`
* `add n/Betsy Crowe e/pianistbetsy@smtp.com p/87988039 i/Piano`

**Upon success:**

You will see a message indicating successful addition of the musician like below:
![img.png](images/addJohnDoe.png)

**Upon failure:**

Should you input a musician which is already in your contact book(ie. have the same phone number or email as an existing contact), you will see an error message showing the possible error, please input a different phone/email for the current contact or modify the original contact's relevant details.


--------------------------------------------------------------------------------------------------------------------

## Troubleshooting

--------------------------------------------------------------------------------------------------------------------


## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL i/INSTRUMENT [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com i/violin t/roommate`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX n/NAME p/PHONE_NUMBER e/EMAIL i/INSTRUMENT [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
