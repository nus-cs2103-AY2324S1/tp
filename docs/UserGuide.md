---
layout: default.md
title: "User Guide for LinkTree"
pageNav: 3
---

# LinkTree User Guide

LinkTree is a **desktop app optimized for developers engaged in startups** who require organized and streamlined 
methods for contact management. With LinkTree, swiftly connect with the right stakeholder, ensuring smooth 
project execution and superior collaboration.

This user guide is designed to help our users to understand and make the most of out of LinkTree. This guide is to 
provide clear and comprehensive instructions, to assist our users in understanding the core features and functions of
LinkTree application. It is your **companion** for unlocking the full potential of LinkTree to effectively manage your
teams.

**Value Proposition**:
LinkTree is the top contact solution for software professionals. Using our unique tag-based system and tree structure,
you can access contacts by roles and responsibilities instantly.

* Table of Contents
{:toc}

<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Introduction

### Target User Profile:
- Developers engaged in medium to large-scale software projects.
- Collaborates frequently with multiple teams or departments.
- Requires quick access to contact details of other team members based on their roles and responsibilities.
- Prefers an organized and streamlined method for contact management.
- Tech-savvy and open to adopting new tools for enhancing productivity.

### How to use this Guide?

**Navigating the Document**: This guide is divided into sections to help you quickly find the information you need. You
can use the table of contents to jump from one section to another section. Additionally, headings and subheadings are
used for easy navigation.

**Formatting and Icons**: Throughout the guide, we use bold text and colors to emphasize key points and important terms.
Icons are used to draw your attention to noteworthy tips and additional information.


--------------------------------------------------------------------------------------------------------------------

## Getting Started

### Installation

1. Ensure you have Java `11` or above installed on your Computer.
2. LinkTree is compatible with all major operating systems, including Windows, macOS and Linux.
3. Download the latest `LinkTree.jar` from [here](https://github.com/AY2324S1-CS2103T-W11-4/tp/releases).
4. Copy the file to the folder you wish to use as the _home folder_ for your smart contact book.

### Initial Setup

1. Open a command terminal, `cd` into the folder where you placed the jar file, and use the `java -jar addressbook.jar` command to run the application. A GUI similar to the one below should appear in a few seconds, containing some sample data.
   ![Ui](images/Ui.png)

2. Type a command into the command box and press Enter to execute it. For example. type `help` in the command box and press Enter.
It will open a new help window, and shows a command summary in the result box.

3. Refer to the [Basic Features](#basic-features) and [Advanced Features](#advanced-features) sections for details of each command.


### Understanding the GUI
Will update soon...

### Tutorial on using our Commands
Will update soon...

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
  - Command: `edit INDEX(must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [r/REMARK] [t/TAG]...`
  - Example: `edit 1 n/John Wick e/new.email@example.com`
  - Result: The person of index `1` now has a new name `John Wick` and a new
  email address `new.email@example.com`.
  - Note: You **do not need to provide** the arguments that you do not want to edit. In the
  given example above, I only want to edit this person's name and email address and nothing else,
  so I only provided this person's index `1`, new name `n/John Wick` 
  and new email address `e/new.email@example.com`.


* To edit team name:
  - Command: `editTeamName [tn/TEAMNAME(original team name)] [tn/TEAMNAME(new team name)]`
  - Example: `editTeamName tn/Alpha Team tn/Beta Team`
  - Result: Now, the team with original team name `Alpha Team` changed its team name
  to `Beta Team`.


* To edit team leader:
  - Command: `editTeamLeader [tn/TEAMNAME] [tl/TEAMLEADER]`
  - Example: `editTeamLeader tn/Alpha Team tl/Bob`
  - Result: Now, the team with team name `Alpha Team` changed its team leader to be `Bob`.

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

