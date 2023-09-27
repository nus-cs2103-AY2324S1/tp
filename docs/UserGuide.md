layout: default.md
title: "User Guide for LinkTree"
pageNav: 3
---

# LinkTree User Guide

LinkTree is a **desktop app optimized for developers engaged in medium to large-scale software projects** who require organized and streamlined methods for contact management. With LinkTree, swiftly connect with the right stakeholder, ensuring smooth project execution and superior collaboration.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Introduction

Target User Profile:
- Developers engaged in medium to large-scale software projects.
- Collaborates frequently with multiple teams or departments.
- Requires quick access to contact details of other team members based on their roles and responsibilities.
- Prefers an organized and streamlined method for contact management.
- Tech-savvy and open to adopting new tools for enhancing productivity.

Value Proposition:
LinkTree is the top contact solution for software professionals. Using our unique tag-based system, access contacts by roles and responsibilities instantly.

--------------------------------------------------------------------------------------------------------------------

## Getting Started

### Installation

1. Ensure you have Java `11` or above installed on your Computer.
2. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).
3. Copy the file to the folder you wish to use as the _home folder_ for your AddressBook.

### Initial Setup

1. Open a command terminal, `cd` into the folder where you placed the jar file, and use the `java -jar addressbook.jar` command to run the application. A GUI similar to the one below should appear in a few seconds, containing some sample data.
   ![Ui](images/Ui.png)

2. Type a command into the command box and press Enter to execute it.

3. Refer to the [Basic Features](#basic-features) and [Advanced Features](#advanced-features) sections for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Basic Features

### Adding Contacts

* To add contacts for individual developers:
  - Command: `Add id [contacts]`
  - Example: `Add 1 [80888888]`

### Searching Contacts

* To search for a user or contact based on their id:
  - Command: `search [role] [contact_name]`
  - Example: `search [developer] [Peter]`

### Editing Contacts

* To edit personal information:
  - Command: `edit- [id] [field] [new_field]`
  - Example: `edit 01 email new.email@example.com`

### Deleting Contacts

* To delete existing contacts for developers:
  - Command: `Delete id [contacts]`
  - Example: `Delete 2 [12345678]`

* To delete groups for a project:
  - Command: `Delete id [name]`
  - Example: `Delete 2 [name]`

### Show Graph

* Display all users in a tree diagram:
  - Command: `show linkTree`
  - Example: `show tree [team id]`

--------------------------------------------------------------------------------------------------------------------

## Advanced Features

### Importing/Exporting Contacts
- Details coming soon...

### Creating Groups
- Details coming soon...

--------------------------------------------------------------------------------------------------------------------

## Troubleshooting
- Details coming soon...

--------------------------------------------------------------------------------------------------------------------

## FAQs
- Details coming soon...

--------------------------------------------------------------------------------------------------------------------

## Support
- Details coming soon...

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running it again.

--------------------------------------------------------------------------------------------------------------------

## Command summary
- Details coming soon...

