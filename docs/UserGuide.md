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

## Table of Contents
* [Introduction](#introduction)
  * [Target User Profile](#target-user-profile)
  * [How to use this Guide?](#how-to-use-this-guide)
* [Getting Started](#getting-started)
  * [Installation](#installation)
  * [Initial Setup](#initial-setup)
  * [Understanding the GUI](#understanding-the-gui)
  * [Tutorial on using our Commands](#tutorial-on-using-our-commands)
* [Features](#features)
  * [Date & Time Command](#features)
  * [Add developer](#add-command)
  * [Remove developer](#remove-developer)
  * [Edit developer](#edit-developer)
  * [Find developer](#find-developer)
  * [List developers](#list-developers)
  * [Create teams](#create-teams)
  * [Add developer to team](#add-developers-to-team)
  * [Delete teams](#delete-team)
  * [Remove developer from team](#remove-developer)
  * [Edit team name](#edit-team-name)
  * [Edit team leader](#edit-team-leader)
  * [Find team](#find-team)
  * [List teams](#list-teams)
  * [Display tree](#display-tree)
  * [Help Command](#help-command)
  * [Reset the project](#reset-the-project)
  * [Exit the application](#exit-the-application)
* [FAQs](#faq)
* [Support](#support)
* [Known issues](#known-issues)
* [Command Summary](#command-summary)
* [Future Enhancement](#future-enhancements)
* [Glossary](#glossary)



--------------------------------------------------------------------------------------------------------------------

## Introduction

### Target User Profile
- Project managers and Developers who are engaged in startups.
- Collaborates frequently with multiple teams or departments.
- Requires quick access to contact details of other team members based on their roles and responsibilities.
- Prefers an organized and streamlined method for contact management.
- Tech-savvy and open to adopting new tools for enhancing productivity.

### How to use this Guide?

**Navigating the Document**: This guide is divided into sections to help you quickly find the information you need. You
can use the table of contents to jump from one section to another section. Additionally, headings and subheadings are
used for easy navigation.

* Click [here](#table-of-contents) to go back to contents.
* Click [here](#features) to view all detailed features of our app.
* Already know how to use this guide and can't wait to start? [Get started](#getting-started)!<br>
* Want an overview of all the commands? Click [here](#command-summary)!<br>
* Any further questions that are unresolved? Check out our [FAQ](#faq)!<br>
* Confused about the terms we have used? Check out our [Glossary](#glossary).<br>
* You may also contact us at [linktreeltd@gmail.com](mailto:linktreeltd@gmail.com) for further doubts!

**Formatting and Icons**: Throughout the guide, we use bold text and colors to emphasize key points and important terms.
Icons are used to draw your attention to noteworthy tips and additional information.
Here are some symbols you might encounter in our guide, and their respective meanings:

[//]: # (Table inspired by NUSCoursemates https://ay2324s1-cs2103t-t17-4.github.io/tp/UserGuide.html)

| Symbol               | Meaning                                                                     |
|----------------------|-----------------------------------------------------------------------------|
| :information_source: | Note. Provides additional information.                                      |
| :bulb:               | Tip. Information that might better your experience.                         |
| :exclamation:        | Warning. Cautionary statement to prevent you from undesirable consequences. |

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* These symbols (:information_source: :bulb: :exclamation:) will be encapsulated in a box as such.
</div>


--------------------------------------------------------------------------------------------------------------------

## Getting Started

### Installation

1. Ensure you have Java `11` or above installed on your Computer.
Check out our [FAQ](#faq) if you are not sure how to download Java `11`.
2. LinkTree is compatible with all major operating systems, including Windows, macOS and Linux.
3. Download the latest `linktree.jar` from [here](https://github.com/AY2324S1-CS2103T-W11-4/tp/releases).

### Initial Setup

1. Open a command terminal, navigate to the folder where you have stored the downloaded jar file. Type the command
`java -jar linktree.jar` to run the application. A GUI similar to the one below should appear in a few seconds, containing some sample data:
   ![Ui](images/MainUi.png)

2. Typing a command into the command box and hitting `Enter` will execute it. For example, try typing `help` into the command box and hit Enter.
It will open a new help window, that shows all the commands in linktree.

3. Refer to the [features](#features) section for details of each command.

<div style="page-break-after: always;"></div>


### Understanding the GUI

 ![Understanding GUI](images/UG_images/Understanding%20GUI.png)

Linktree's GUI has been designed to be simple and user-friendly. This guide will mention some of these UI elements:
1. **Command Box**: You'll have to type the commands in this box.
2. **Result Display**: Based on the inputs given, your result will be displayed in this box.
3. **Help Button**: Type `help` into the command box, and you will see a help window pop up, giving you the link to
this UserGuide. The Result Display will also show a summary of commands.
4. **File Button**: Click the `File` button and then you will see a `exit` button to close the application.
5. **Developers Panel**: Lists all the existing developers (or a filtered list of developers after the `find` command,
check out [find developer command](#find-developer) for more details).
6. **Teams Panel**: Lists all the teams (or a filtered list of developers after the `findteam` command,
check out [find team command](#find-team) for more details).

### Tutorial on using our Commands

| Commands                                    | Description                                                                                                                              |
|---------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| `add`, `delete`, `find`, etc.               | The main command keyword.                                                                                                                |
| `n/`<br/>`tn/`<br/>`tl`                     | n/ - Prefix for developer names<br/>tn/ - Prefix for team names.  <br/>tl/ -Prefix for team leader name.                                 |
| `p/`<br/>`e/`<br/> `a/`<br/> `r/`<br/> `t/` | p/ - Prefix for phone number<br/> e/ - Prefix for email<br/>a/ - Prefix for address<br/>r/ - Prefix for remark<br/>t/ - Prefix for tags. |                                                                  |
| `[DeveloperName]`, `[Phone]`, etc.          | Arguments that follow the command.                                                                                                       |
| `(optional)`                                | Optional elements like Remark and Tags can be omitted.                                                                                   |                   |
| `...`                                       | Ellipsis indicates that more arguments can follow.                                                                                       |


Example - `add n/[Developer Name] p/[Phone Number] e/[Email] a/[Address] (OPTIONAL r/[Remark] t/Tags)`

Click [here](#table-of-contents) to go back to contents.

<br/>

--------------------------------------------------------------------------------------------------------------------

## Features

Remember to check out how to use this guide [here](#how-to-use-this-guide) before continuing.

<br>

### Date & Time Command

#### What this command does:
* The "Hi" command displays a welcome message along with the current date and time.

#### Command format: `Hi`

#### Features:

- You can use this command to check the date and time! Type in `hi` and hit enter!
- You can now see "Welcome to Linktree, Current date and time: MM-DD-YYYY HH:MM:SS" displayed on the UI.

#### Example usage:

When you enter the command (Hi):
  ![Welcome](images/UG_images/WelcomeCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
- Please ensure that the first letter of the command is uppercase. For instance, use `Hi` instead of `hi` or `HI`.
- The command does not take any additional arguments or keywords.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>


--------------------------------------------------------------------------------------------------------------------
**The following commands are for managing developers.**
### Add command
#### What this command does:
* This command allows you to add a new developer to the addressbook.


#### Command format:
* `add n/[Developer Name] p/[Phone Number] e/[Email] a/[Address] (OPTIONAL r/[Remark] t/Tags)`

#### Example usage:
- For example, to add a new developer John to the addressboook, type
`add n/John p/89789678 e/John@gmail.com a/Singapore t/friend`.
  ![AddCommand](images/UG_images/AddCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* The `add` command allows for numbers and other special characters for the `name` parameter. This is to allow for more flexibility with naming.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### Remove developer

#### What this command does:
* Performs deletion of existing developers from the addressbook.

#### Command format:
* `delete [Index number]`

#### Example usage:
- For example, to delete developer **Jane Lim** from the list fo developers. First find the index number of **Jane Lim** from the list of developers.
Then type `delete 3` to delete that entry.
- In the following example, you can see that **Jane Lim** is removed from the list of developers.
  ![DeleteCommand](images/UG_images/DeleteCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Index must be a positive integer.
* You cannot delete a developer who is a **team leader** for any of the teams. To delete that developer, you will have to change the team leader using `editTeamLeader` command. And then you can remove that developer from the addressbook.
 ![DeleteTeamLeader](images/UG_images/DeleteTeamLeader.png)
</div>

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-danger">

**:exclamation: Warning:**<br>
* Deleting a developer who is not a team leader will remove that developer from all the teams and also from list
of developers.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>


### Edit developer
#### What this command does:
* Edits personal information of an existing developer.

#### Command format:
`edit [Index number] n/[Name] p/[Phone] e/[Email] a/[Address] r/[Remark] t/[Tag]...`

#### Example usage:
- For example, to edit a details of developer **John Wick**, firstly find the index number of **John Wick**. Then type
`edit 1 n/John Wick e/new.email@example.com` to change John's name and email address.
- In the following example, you can see that now index `1` has a new name `John Wick` and a new email address `new.email@example.com`.
  ![EditCommand](images/UG_images/EditCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Index must be a positive integer.
* The name of a person cannot be changed to one that already exists in the addressbook. This is to prevent the unintentional consequence of having duplicate names.
</div>

<div markdown="block" class="alert alert-warning">

**:bulb: Tips:**<br>
* You **do not need to provide** the arguments that you do not want to edit. In the
  given example above, I only want to edit this person's name and email address and nothing else,
  so I only provided this person's index `1`, new name `n/John Wick`
  and new email address `e/new.email@example.com`.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>


### Find developer
#### What this command does:
* Finds developers whose names satisfy the following parameter:
    * Any name, provided you can remember at least the first/last name.
    * Only full words will be matched e.g. Han will not match Hans
    * Multiple developer names can also be given as paramaters. The program will display the developers that have those names.

#### Command Format:
`find [Keyword1] ...`

#### Example Usage:
- For example, to find developers whose first or last name is **John**, type `find John`.
- In the following example, you can see that there are 3 developers with their first name **John** in the list of developers.
  ![FindCommandUsing1Keyword](images/UG_images/FindCommand1.png)
- You can use `find` command with multiple keywords. For example, if you type `find alex david` you get all the developers
with first or last name being **Alex** or **David**.
  ![FindCommandUsingMultipleKeyword](images/UG_images/FindCommand2.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
- The find command is designed to show only a list of developers. If you wish to view the team list again, you should use the `listt` command.
- To display the complete list of developers, use the `list` command. If you need to list both at the same time, you can use the `list` command twice or the `listt` command twice to revert to the default state.
- The search is case-insensitive. e.g hans will match Hans
- The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans

</div>

Click [here](#table-of-contents) to go back to contents.

<br>


### List developers
#### What this command does:
* Displays the list of all the developers in this project.

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Any additional words provided after the command will be ignored.
  (e.g. entering `list help delete 1 HAHAHA-1234` is equivalent to entering `list`)
</div>

#### Command format:
`list`

#### Example usage:
* For example, type `list` and you will see the list of developers in the system.
  ![ListCommand](images/UG_images/ListCommand.png)

<div markdown="block" class="alert alert-warning">

**:bulb: Tips:**<br><br>
**To return to the home page again**: <br>
Use the same command `list`. You will return to the home page.

**Example usage**: <br>
When you are at the main window, type `list` to show the developer list
and type `list` again when you want to go back to the main window.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

--------------------------------------------------------------------------------------------------------------------

**The following commands are for managing teams**

### Create teams
#### What this command does:
* Takes a team name and a team leader name. Creates a new team with these paramaters if the given team leader is already an existing developer in the project.

#### Command format:
* `newteam tn/[TeamName] tl/[TeamLeader]`

#### Example usage:
* For example, to add a new team with team name **Team Delta** and team leader **David Li**,
type `newteam tn/Team Delta tl/David Li`
* In the following example, you can see new team **Team Delta** is added to the team list.
  ![AddTeamCommand](images/UG_images/AddTeamCommand.png)

Click [here](#table-of-contents) to go back to contents.

<br>

### Add developers to team
#### What this command does:
* Helps to add existing developers in the addressbook to a team.

#### Command format:
* `dev2team tn/[TeamName] n/[Developer Name]`

#### Example usage:
* `dev2team tn/Team Delta n/Irfan Ibrahim`
  ![AddDeveloperToTeam](images/UG_images/AddDevToTeamCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* The specified developer and team must already exist in the project!
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### Delete team
#### What this command does:
* Deletes existing teams from the teambook.

#### Command format:
* `deleteteam tn/[TeamName]`

#### Example usage:
* For example, if you want to delete **Team Alpha** from the list of teams.
* You need to type `deleteteam tn/Team Alpha`, then the **Team Alpha** will be deleted from the list of teams.
  ![DeleteTeamCommand](images/UG_images/DeleteTeam.png)

Click [here](#table-of-contents) to go back to contents.

<br>

### Remove developer from team
#### What this command does:
* Performs deletion of existing developer from the given team.

#### Command format:
* `deletedev tn/[TeamName] n/[DeveloperName]`

#### Example usage:
* For example: if you want to delete developer **John Lim** from **Team Bravo**.
* You need to type `deletedev tn/Team Bravo n/John Lim`, then the developer
**John Lim** will be removed from **Team Bravo**.
  ![DeleteDevFromTeam](images/UG_images/DeleteDevFromTeam.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* You cannot delete a developer who is **team leader**. To delete that developer, you'll have to change the
  team leader of that team using `editTeamLeader` command. Only then you can remove that developer from the team.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### Edit team name
#### What this command does:
* Takes a team name and a new name for the team. Changes team name if it doesn't already exist in the teambook.

#### Command format:
* `editTeamName tn/[Existing team name] tn/[New team name]`

#### Example usage:
  * For example, if you want edit the team name **Team Alpha** to **Team Romeo**. Type `editTeamName tn/Team Alpha tn/Team Romeo`.
  * You will now see that `Team Alpha` has been changed to `Team Romeo` in the list of teams.
    ![EditTeamNameCommand](images/UG_images/EditTeamNameCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Remember to check that the new team name that you specify isn't already taken by another team in the teambook.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### Edit team leader
#### What this command does:
* Edits the team leader of a team.

#### Command format:
* `editTeamLeader tn/[Team name] tl/[New Team leader]`

#### Example usage:
- For example, if want to change the team leader of `Team Romeo` to `John Lim`, you need to type `editTeamLeader tn/Team Romeo tl/John Lim`.
- Now, you will see that the team leader for `Team Romeo` has changed to `John Lim`.
  ![EditTeamLeaderCommand](images/UG_images/EditTeamLeaderCommand.png)

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Remember to check that the new leader specified is a person that already exists in the addressbook.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### Find Team
#### What this command does:
- Finds matching teams whose names contain any of the given keywords.

#### Command Format:
* `findteam [Keyword1] ...`

#### Example usage:
- For example, type `findteam Alpha` to find all the teams with keyword **Alpha**.
- In the following example, you can see that the matching results are **Team Alpha** and **Alpha Squad**.
  ![FindTeamCommand](images/UG_images/FindTeamCommnd.png)
- You can also use multiple keywords, for example type `findteam Bravo Charlie` which will show the all the teams
with keywords Bravo or Charlie.


<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>
* The order of the keywords does not matter. e.g., "Alpha Bravo" will match "Bravo Alpha Team."
* Only the team name is searched.
* Only full words will be matched. e.g., "Alph" will not match "Alpha Team."
* Teams matching at least one keyword will be returned (i.e., OR search). e.g., "Alpha Bravo" will return "Team Alpha," "Bravo Team."
</div>

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* The findteam command is designed to show only a list of teams. If you wish to view the developer list again, you should use the `list` command. To display the complete list of teams, use the `listt` command. If you need to list both at the same time, you can use the `list` command twice or the `listt` command twice to revert to the default state.
*  The search is case-insensitive. e.g., "team" will match "Team Alpha."
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### List teams
#### What this command does:
* Displays the list of all the teams in this project.

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Any additional words provided after the command will be ignored.
  (e.g. entering `listt help delete 1 HAHAHA-1234` is equivalent to entering `listt`)
</div>

#### Command format:
* `listt`

#### Example usage:
* Type `listt` and you will see list of teams with team name, team leader and developers similar to the example shown below.
  ![ListTeamCommand](images/UG_images/ListTeam.png)

<div markdown="block" class="alert alert-warning">

**:bulb: Tips:**<br><br>
**To return to the home page again**: <br>
Use the same command `listt`. You will return to the home page.

**Example usage**: <br>
When you are at the main window, type `listt` to show the team list
and type `listt` again when you want to go back to the main window.
</div>

Click [here](#table-of-contents) to go back to contents.

<div style="page-break-after: always;"></div>

<br>

### Display tree
#### What this command does:
* Provides a tree visualisation of all the teams and members in the project.
<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Any additional words provided after the command will be ignored.
  (e.g. entering `tree list delete 1 HAHAHA-1234` is equivalent to entering `tree`)
* The project name **cannot be edited** because our application is designed to hold
    the contact information of all the developers in **one** software engineering project,
    the naming of this project is not important since there is only one project, and
    we do not need names to differentiate between different projects.
* The content in the tree **will not be updated in real time** because that requires
    too much computer resources. Instead, the tree is **updated every time you show it**.
    So, when you have some changes to the data, you should **hide the tree first if it is
    shown**, and call command `tree` again to show the tree. Then your changes will be
    reflected in the new tree. With that being said, if you enter command `tree` to
    show LinkTree and make some changes to the data, your changes will not be reflected
    in the current tree. You should hide the tree first and then call `tree` again to
    obtain a new tree which will reflect the changes you made.
* Team names and team leaders' names may not be shown fully if they are too long.
    You can refer back to the team list for their full names.
</div>

#### Command format:
* `tree`

#### Example usage:
* Type `tree` and you will see a tree similar to the one shown below.
![TreeResultExample](images/UG_images/TreeResultExample.png)

<div markdown="block" class="alert alert-warning">

**:bulb: Tips:**<br><br>
**To hide the tree**: <br>
Use the same command `tree`. You will return to the home page.

**Example usage**: <br>
When you are at the main window, type `tree` to show the tree graph
and type `tree` again when you want to hide the tree and go back to the main window.
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

--------------------------------------------------------------------------------------------------------------------

### Help Command
#### What this command does:
* Opens up a window which shows the link to this User Guide, which also comes with a button allowing copying the link;
* Display a summary of all commands of LinkTree, specifying the parameters needed.

<div markdown="block" class="alert alert-primary">

**:information_source: Note:**<br>
* Any additional words provided after the command will be ignored.
  (e.g. entering `help list delete 1 HAHAHA-1234` is equivalent to entering `help`)
</div>

#### Command format:
* `help`
* Or simply press F1

#### Example usage:
* Type `help` and you will see a pop up window and command summary as shown below.
  ![HelpWindow](images/UG_images/HelpWindow.png)
  ![HelpCommandResult](images/UG_images/HelpCommandResult.png)

Click [here](#table-of-contents) to go back to contents.

<br>

### Reset the project
#### What this command does:
* This command will erase **ALL THE DATA**. All the details of developers and teams will be cleared from storage.
This command can be used when you want to restart the project.

#### Command format:
* `clear`

#### Example usage:
* Type `clear` and you will see an empty list of developers and teams that is entire data will be erased.
  ![ClearCommand](images/UG_images/ClearCommand.png)

<div markdown="block" class="alert alert-danger">

**:exclamation: Warning:**<br>
* Using the clear command might result in unintended loss of data! Use it only when you intend to remove all of your data!
</div>

Click [here](#table-of-contents) to go back to contents.

<br>

### Exit the application
#### What this command does:
* You can use this command to close the application.

#### Command format:
* `exit`

#### Example usage:
* Type `exit` and the application will close automatically.

<br>

This is all the features that we have implementated in our application.

Click [here](#table-of-contents) to go back to contents.

--------------------------------------------------------------------------------------------------------------------

## FAQ
##### Q: How can I launch Linktree if clicking on the JAR file does not work?
**A:** Below is the CLI way to launch Linktree when encountering issues with the JAR file.
1. Open the Command Prompt.
2. Navigate to the directory where the JAR file is located using the following command:
3. Type the command below and press Enter: `cd [JAR file location]` followed by `java -jar linktree.jar`
4. Linktree should now launch.
- If this doesn’t work, check our GitHub to make sure you have the latest version of Tran$act downloaded.

##### Q: How can I transfer my Linktree data to another computer?
**A:** To transfer your Linktree data, install the jar file on the new computer and replace the empty data files(addressbook.json & teambook.json) with the two from your previous Linktree home directory (tp/data).

##### Q: What are the steps to install Java 11?
**A:**  To install Java 11, please refer to the [detailed download instructions available here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

##### Q: Is Linktree compatible with Mac and Linux operating systems?
**A:**  Linktree is compatible with a wide range of operating systems,
including Windows, Mac and Linux, and it runs on systems that support Java 11, so you can use it on your favorite platform.

##### Q: What should I do if a command isn't working?
**A:** Below are the 5 potential ways to solve your issue:
1. **Review the Command Syntax**:
  - Start by carefully reviewing the command you're trying to use. Ensure that you've entered it correctly, including all required components like command names, options, and arguments.

2. **Check for Typos**:
  - Typos are a common cause of command failures. Double-check the spelling of all command elements, including file paths and option names.

3. **Verify Input Requirements**:
  - Commands often require specific inputs, such as file paths, URLs, or values. Ensure that you've provided all the necessary inputs in the correct format.

4. **Refer to the user guide**:
  - Look for the section that explains the command's usage and syntax. It provide examples and detailed explanations.

5. **Ask for Help**:
  - If you've exhausted all other options and the command still isn't working, don't hesitate to seek help.
Reach out to your us in email or Github for assistance.
Describe the problem in detail, including the command you're using, the error messages received, and any relevant context.

##### Q: Why does Linktree not show all developers/teams in the database?
**A:** If not all customers or properties are appearing, it's likely due to certain commands like find, or findteam.
1. *If you wish to view the developer list, you should use the list command.*
2. *To display the complete list of teams, use the listt command.*
3. *If you need to list both at the same time, you can use the list command twice or the listt command twice to revert to the default state.*

##### Q: Is there a way to share my Linktree phonebook with others by exporting it to a different format such as a csv file?
**A:** Linktree does not currently support sharing your phonebook with others or exporting it to an external format.
Its main function is personal communication management for supervisors.

##### Q: I'm confused about some terms in the guide. Where can I find explanations?
**A:** You can check out the explanation of some of the complicated terms in our glossary

##### Q: I deleted my data file by mistake. Can I recover my lost data?
**A:** If you've accidentally deleted your data file, check your computer's trash or recycle bin.
Unfortunately, if it's not there, recovering lost data is not possible.

##### Q: How do I uninstall Linktree?
**A:** To uninstall Linktree, simply delete the folder that contains `linktree.jar`
- no installation on the hard drive means no complex uninstallation process.

##### Q: Do I need to be online to use Linktree?
**A:** Linktree doesn't require an internet connection for regular use, but you will need to be online to download the application initially.


Click [here](#table-of-contents) to go back to contents.


--------------------------------------------------------------------------------------------------------------------

## Support
If you encounter any issues or have questions about LinkTree, we're here to help. Please find the relevant support resources below:

##### Contact Information
For personalized assistance, you can reach out to our support team via email at [linktreeltd@gmail.com](mailto:linktreeltd@gmail.com). We aim to respond within [3 business days].

##### Frequently Asked Questions (FAQs)
Check our [FAQs section](#faq) for answers to common questions and solutions to known problems.

##### Software Updates

Stay informed about the latest releases, bug fixes, and new features by checking our [Releases](https://github.com/AY2324S1-CS2103T-W11-4/tp/releases) regularly.


##### Feedback and Suggestions

We value your feedback and suggestions. Share your ideas for improvement or new features through our feedback portal given below. Also, if you come across a bug or unexpected behavior, help us improve by reporting it in the feedback portal as well.
[Feedback Portal](https://docs.google.com/forms/d/e/1FAIpQLSc4rIrGgKWQBDKxmMKP7S0pmuI42OEoxZ00Qaxo0K2rvlVLag/viewform?usp=sf_link)


<div markdown="block" class="alert alert-warning">

**:bulb: Tips:**<br><br>
To resolve any issues even faster, try checking out our documentation and FAQs!
</div>

We appreciate your continued support and strive to provide the best user experience possible.


Click [here](#table-of-contents) to go back to contents.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running it again.

Click [here](#table-of-contents) to go back to contents.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Command            | Format and Example                                                                                                                                                          |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Hi`               | Format: `Hi`<br/>Example: `Hi`                                                                                                                                              |
| `add`              | Format: `add n/[Developer Name] p/[Phone] e/[Email] a/[Address] (OPTIONAL r/[Remark] t/[Tags])`<br/>Example: `add n/John p/89789678 e/John@gmail.com a/Singapore t/friend`  |
| `delete`           | Format: `delete [index number]`<br/> Example: `delete 7`                                                                                                                    |
| `edit`             | Format: `edit [Index number] (OPTIONAL n/[Developer Name] p/[Phone] e/[Email] a/[Address] r/[Remark] t/[Tag]...)`<br/>Example: `edit 1 n/John Wick e/new.email@example.com` |
| `find`             | Format: `find [Keyword]...`<br/>Example: `find John`                                                                                                                        |
| `list`             | Format: `list`<br/>Example: `list`                                                                                                                                          |
| `newteam`          | Format: `newteam tn/[TeamName] tl/[TeamLeader]`<br/> Example: `newteam tn/Team Delta tl/David Li`                                                                           |
| `dev2team`         | Format: `dev2team tn/[TeamName] n/[Developer Name]`<br/>Example: `dev2team tn/Team Delta n/Irfan Ibrahim`                                                                   |
| `deleteteam`       | Format: `deleteteam tn/[TeamName]`<br/>Example: `deleteteam tn/Team Alpha`                                                                                                  |
| `deletedev`        | Format: `deletedev tn/[TeamName] n/[Developer Name]`<br/>Example: `deletedev tn/Team Alpha n/Roy Balakrishnan`                                                              |
| `editTeamName`     | Format: `editTeamName tn/[Original Team Name] tn/[New Team Name]`<br/>Example: `editTeamName tn/Alpha Team tn/Beta Team`                                                    |
| `editTeamLeader`   | Format: `editTeamLeader tn/[TeamName] tl/[TeamLeader]`<br/>Example: `editTeamLeader tn/Alpha Team tl/Bob`                                                                   |
| `findteam`         | Format: `findteam [Keyword]...`<br/> Example: `findteam Alpha`                                                                                                              |
| `listt`            | Format: `listt`<br/>Example: `listt`                                                                                                                                        |
| `tree`             | Format: `tree`<br/>Example: `tree`                                                                                                                                          |
| `help` or Press F1 | Format: `help`<br/>Example: `help`                                                                                                                                          |
| `clear`            | Format: `clear`<br/>Example: `clear`                                                                                                                                        |
| `exit`             | Format: `exit`<br/>Example: `exit`                                                                                                                                          |

Click [here](#table-of-contents) to go back to contents.

--------------------------------------------------------------------------------------------------------------------

## Future Enhancements
**We have several feature enhancements and quality of life improvements in the pipeline.**
**Some of them are as follows:**

1. Currently, a new person can be added to the addressbook if they have the same phone number of email as another existing person. We plan to fix this in a future release to make sure that persons with the same phone number/email as an existing person cannot be added even if they have a different name.
2. Currently, when the tree is displayed, if there are not enough teams to display to fill the windows, a white background fills up the empty space by default. This will be patched in a future release so that the empty space will carry the same default colour as the UI.
3. Currently, when the tree is actively being displayed, it does not get refreshed when commands are run. For example, when a new developer is added to the addressbook, it does not reflect on the tree even though there is a feedback in the UI stating success. The tree gets refreshed only upon closing and reopening the tree. The auto-refresh feature will be implemented in a future release.
4. When `delete 0` command is run, the command error stating `invalid command format` is too general. Even though it specifies that the index has to be a positive integer, we can change it in the future to give better information to specify that the index number has to be positive. So when typing this command, the new error would be like `Index provided is incorrect. It has to be a positive integer. Please try again.`
5. Currently, the `help` command displays some of the most commonly used commands and their formats. Some commands like `findteam` and `hi` are not mentioned here. A future version of LinkTree will have a more comprehensive command summary page that will include all the valid commands and their formats.

<br>

Click [here](#table-of-contents) to go back to contents.

--------------------------------------------------------------------------------------------------------------------

## Glossary

| Key Terms   | Definition                                                                                                                                                                                           |
|-------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| CLI         | Command Line Interface - A text-based interface where users interact with the application by typing commands into a terminal or command prompt.                                                      |
| GUI         | Graphical User Interface - The visual interface that allows users to interact with the application.                                                                                                  |
| .jar        | Java Archive - A file format used to package Java class files, associated metadata, and resources into a single archive file. It is a common format for distributing Java applications or libraries. |
| Addressbook | A file that stores the list of developers in the application.                                                                                                                                        |
| Teambook    | A file that stores the list of teams in the application.                                                                                                                                             |
| Prefix      | A keyword used before certain elements in a command to specify its type or category.                                                                                                                 |
| Tag         | A label or keyword assigned to developers for categorization and easy identification.                                                                                                                |
| Tree        | A visual representation of the top-down project's structure, displaying teams and developers.                                                                                                        |
| Index       | A numerical identifier assigned to developers or teams for reference.                                                                                                                                |
| FAQ         | Frequently Asked Questions which users can check out if needed.                                                                                                                                      |

Click [here](#table-of-contents) to go back to contents.

--------------------------------------------------------------------------------------------------------------------
