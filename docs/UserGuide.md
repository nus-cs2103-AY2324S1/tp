---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# JABPro User Guide

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
* [Product Overview](#product-overview)
* [Key Definitions](#key-definitions)
* [Features Overview](#overview-of-main-features)
* [Quick Start Guide](#quick-start)
* [Features](#features)
  * [Viewing help](#viewing-help-help)
  * [Command Failures](#command-failure)
  * [Managing Tags](#1-managing-tags)
    * [Creating tags](#creating-tags-create)
    * [Listing all tags](#listing-all-tags-listt)
  * [Managing Applicants](#2-managing-applicants)
    * [Adding an applicant](#adding-an-applicant-add)
    * [Adding a remark to an applicant](#adding-a-remark-to-an-applicant-remark)
    * [Adding applicant's LinkedIn/GitHub](#adding-linkedingithub-username-for-a-user-addl-or-addg)
    * [Opening applicant's LinkedIn/GitHub](#opening-user-linkedin-or-github-account-linkedin-or-github)
    * [Setting an applicant's status](#setting-an-applicants-status-set)
    * [Viewing an applicant's details](#viewing-a-applicants-details-view)
    * [Editing an applicant's detail](#editing-a-applicant-edit)
    * [Deleting an applicant](#deleting-job-applicants-delete)
  * [Filtering and Listing Applicants](#3-filtering-and-listing-applicants)
    * [Searching for applicants](#searching-job-applicants-by-category-search)
    * [Filtering applicants](#filter-job-applicants-by-statistics-filter)
    * [Listing all applicants](#listing-all-applicant-list)
  * [Managing Events](#4-managing-events)
    * [Adding an event](#adding-an-event-event)
    * [Viewing all events](#viewing-events-schedule)
  * [Others](#5-others)
    * [Exporting all entries](#exporting-the-existing-data-to-csv-export)
    * [Clear all entries](#clearing-all-entries-clear)
    * [Exiting the program](#exiting-the-program-exit)
* [Additional Information](#additional-information)
  * [Summary Statistics](#summary-statistics)
  * [Saving Data](#saving-the-data)
  * [Editing Data](#editing-the-data-file)
* [FAQ](#faq)
* [Command Summary](#command-summary)
* [Prefix Summary](#prefix-summary)
* [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------

## Product Overview

Are you a Hiring Manager who's tired of managing applicant applications through cumbersome spreadsheets? 

Upgrade your hiring process with **JABPro (JobApplicationsBook Pro)**, a CLI based desktop app that allows you to:

* easily manage job applicants' contact details,
* schedule interviews,
* and gain valuable insights on their interview performance! <br>

From interns to full-time roles, software to marketing, JABPro’s versatile interface allows you to keep track of all kinds of job applicants in various industries. 

**If you are a Hiring Manager, looking to improve your applications management workflow, JABPro is the tool for you!**

Access our self-curated user guide below to learn more on how you can integrate various JABPro’s functions into your workflow.

[Jump back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
## Overview of Main Features

While **JABPro** offers a whole range of features, we believe that the following features are likely to be the most useful to you:

**Viewing details of applicants:**
* Viewing applicant's information: `view`

**Tag colouring and categorisation:**
* Creating a tag with a specified category: `create`
* Listing all tags: `listT`
* Adding and editing an applicant's tags and tag scores: `edit`

**Event management and Scheduling:** 
* Adding an event: `event`
* Viewing events: `schedule`

**Comparing and filtering applicants:**
* Filtering applicants by statistics: `filter`

These features address the complications that Hiring Managers face when managing applicants: 
1. **Visual Noise and Clutter** from using other applicant management software like Excel

`Viewing details of applicants` and `Tag colouring and categorisation` address this by creating an organized and intuitive way to view applicants and their details. This is done through colour coding and minimalistic design.

2. **Toggling between different software** to manage applicants and schedule events

`Event Management and Scheduling` address this by allowing you to schedule events and view them in the same software. This means that you do not have to toggle between different software to manage applicants and schedule events.

3. Having to manually calculate summary statistics and compare applicants
`Comparing and filtering applicants` address this by allowing you to filter applicants by statistics and compare them. This means that you do not have to manually calculate summary statistics and compare applicants.

--------------------------------------------------------------------------------------------------------------------
Go to the [Table of Contents](#table-of-contents) to navigate to the feature that you are interested in 

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Heads up!
Our user guide takes into consideration your level of expertise in JABPro.

You are a **beginner** user if ...

1. You are new to JABPro (used JABPro less than 5 times) *and*,
2. you wish to fully rely on the JABPro interface.

ALl the **notes** and **tips** mentioned in this user guide are directed towards beginners unless otherwise stated.

You are an **advanced** user if ...

1. You have used JABPro multiple times now *and*,
2. you use JABPro's search and summary statistics extensively for comparison *or*
3. you would like to challenge yourself to go beyond the JABPro user interface and manually edit files.

**Notes** and **tips** curated for you are labeled by the following box!

<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>

<br>

**Also, take note of the following icons and their meanings.**

<box type="warning" seamless>

This is a warning. Watch out for these!
</box>

<box type="info" seamless>

This refers to highlighted information that you should take note of!
</box>

<box type="tip" seamless>

This is a tip. It's good to know but not a must-have!
</box>

[Jump back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `jabpro.jar` from [here](https://github.com/AY2324S1-CS2103T-W09-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your JabPro.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar jabpro.jar` command to run the application. <br> 
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

    ![Ui](images/main.png)  

<box type="warning" seamless>

**Caution:** ensure your JABPro contains some sample data. If it starts off empty then there might be some issues with the launch which might result in 
some commands to not run properly. See below to learn how to troubleshoot this problem!

</box> 

<box type="tip" seamless>

**My app does not contain any sample data!**
Not to worry, here are some steps you can take to fix this:
1. On your command terminal, `cd` into the folder where you put the jar file in.
2. Run `cd Data` to navigate to the `Data` folder. This is the folder where JABPro stores the application data on your computer.
3. Run `ls` to view all the files stored in this folder.
4. You should be able to see a file titled `addressbook.json`. Run `rm addressbook.json` to delete this file.
5. Run `cd ..` to navigate back to the folder you were in before.
6. Run `java -jar jabpro.jar` to relaunch the application. You should be able to see a GUI similar to the one above.

</box>

5. If your UI looks **compressed and words are being cut off such as that seen below**, you should **resize** the window to a larger size by dragging the corners of the application window. The UI should now look like the example given above.
   <br>
  
    ![Ui](images/UICompressed.png)

    <div style="page-break-after: always;"></div>

    **Here's what each part of the GUI signifies:** <br>

    ![UiBreakdown](images/uibreak.png)


| Colour                                                   | Component                   | Description                                                                                                                                      |
|----------------------------------------------------------|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| <img src="images/red_1.png" width="10px" height="10px">  | Menu Bar                    | Provides buttons for exiting, opening Help window, and opening Events window.                                                                    |
| <img src="images/orange.png" width="10px" height="10px"> | Command Box                 | Allows you to enter a command.                                                                                                                   |
| <img src="images/yellow.png" width="10px" height="10px"> | Result Display              | Displays the result of the command execution.                                                                                                    |
| <img src="images/green.png" width="10px" height="10px">  | Applicant List Panel        | Displays a list of all applicants in JABPro.                                                                                                     |
| <img src="images/brown.png" width="10px" height="10px">  | Applicant Card              | Displays certain details of an applicant for quick view, such as name, address, phone, email, tags, LinkedIn/GitHub username.                    |
| <img src="images/blue.png" width="10px" height="10px">   | Applicant Information Panel | Displays a detailed view of an applicant, providing information of status and remarks, in addition to the basic information about the applicant. |
| <img src="images/purple.png" width="10px" height="10px"> | Summary Statistics Panel    | Displays summary statistics for a particular applicant pertaining to a specific tag.                                                             |

In addition, there are windows such as:
   * Help Window [accessed by the `help` command, or through Menu Bar].
   * Events Window [accessed by the `schedule` command, or through Menu Bar].
   * TagList window [accessed through the `listT` command].

   Details for each have been provided with the respective commands. <br>

6. Type the command in the command box and press Enter to execute it. Go to our [command summary](#command-summary) to see
   some example commands you can try.

[Jump back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Command Failure
<box type="warning" seamless>

**How to know if your command has failed?**
1. You will see the command that you have entered being highlighted in red.
2. The command will not be cleared from the command box.
3. The error message will be shown in the result display panel.
4. The UI below will not be updated if your command has failed.
   </box>

**The example below shows a command failure for `view`:**
![CommandFailure](images/view-command-failure.png)
<br>

[Jump back to Table of Contents](#table-of-contents)

## Viewing help: `help`
<a name="viewing-help-help"></a>

Opens the `Help window` that leads you to the User Guide for assistance on working with JABPro.

Format: `help`

<box type="tip" seamless>

**Tip:** The `Help window` can also be accessed by clicking `Help > Help F1` in the menu bar, located at the top of the window.

Additionally, pressing the `F1` key also opens the `Help Window`.

</box>

<img src="images/helptip.png">

**Example of successful execution of the `help` command:**

1. Enter the command `help`
2. This is the result of the successful `help` command:

![Help](images/helpwindow-mh.png)

The `Help Window` opens up as follows:

![HelpWindow](images/hw.png)

[Jump back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>
--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* When using parentheses ( ) with items separated by the slash symbol /, at least one item must be included. <br>
  e.g. in the command `search (n/KEYWORD [MORE KEYWORDS]  st/KEYWORD [MORE KEYWORDS]  t/KEYWORD [MORE KEYWORDS])`, it is necessary to specify at least one search category.

* Items with `…`​ after them can be used multiple times including zero times.<br>
   e.g. `t/TAGNAME…​` can be used as ` ` (i.e. 0 times), `t/swe t/intern` for `add` commands or `t/swe intern` for `search` and `delete` commands.

* Parameters can be in any order.<br>
   e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listT`, `exit` and `clear`) will be ignored.<br>
   e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

[Jump back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### 1. Managing Tags
<a name="1-managing-tags"></a>
Tags are meant to help you easily remember applicants details by appending different colour coding to different types of information.

#### Creating tags: `create`
<a name="creating-tags-create"></a>

Creates a tag and categorises it to the specified category.

Format: `create t/CATEGORY TAGNAME…​`

| Type      | Field                | Constraints                                                                                              |
|-----------|----------------------|----------------------------------------------------------------------------------------------------------|
| Mandatory | `t/CATEGORY TAGNAME` | `TAGNAME` must be alphanumeric (letters and numbers, no spaces and symbols allowed such as `/`, `,` ...) |

**Note:**
* JABPro offers 3 predefined tag categories namely `employment`, `role`, and `dept`. However, you can define up to 3 more tag categories of your own!
* The tags created using this command can be used to tag applicants using the `add` or `edit` command. Tagging
  applicant without previously creating the tags using `create` would still work but the tags would be *uncategorised*.
* `create` only allows tags to be categorised at creation meaning tags that have already been created, cannot be categorised further i.e. cannot edit tag categories of tags.
* The field `t/CATEGORY TAGNAME` must strictly contain only two words hence it is advisable for you to keep the `TAGNAME` alphanumerical (contains no spaces). Any other word
  that comes after `t/CATEGORY TAGNAME` that is not preceded by a `t/` prefix would be ignored and the tag for the first valid tag is created.
  <br>
  Example: `create t/role software developer` would create the tag **software** and ignore the word developer.

<box type="tip" seamless>

**Tip:**
* You can create multiple tags at once i.e. `create t/dept marketing t/role developer ...`
* Use this command for frequently used tags for better efficiency in tagging applicants.
* You can view all of your tags by keying in the `listT` command.
  </box>

**An example of the `create` command being successfully executed:**
1. Enter the command `create t/dept marketing t/role developer`
2. This is what you should see upon successful execution of command.

   ![create-success](images/create-success.png)

3. View your newly created tags using the `listT` command.

   ![listT-create](images/listT-create-success.png)

**Failed to create tags? Here are some possible reasons why**

| Reason for Error                                                                                                        | Error Message                                | Remedy / Suggested course of action                                                                                        |
|-------------------------------------------------------------------------------------------------------------------------|----------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| Missing create keyword: `create`                                                                                        | Unknown command                              | Follow the command format of `create t/CATEGORY TAGNAME…​` closely                                                         |
| Missing mandatory field e.g. `create`                                                                                   | Invalid command format!                      | Ensure that you specify at least one tag category and tag name of the tag you would like to create.                        |
| Incomplete field e.g. `create t/test`                                                                                   | Invalid command format!                      | Ensure that both parts of the field are included i.e. specify both tag category and tag name.                              |
| Invalid tag name e.g. `create t/developer@`                                                                             | Tags names should be alphanumeric.           | Ensure that the tag name does not contain any non-alphanumeric characters i.e. no symbols and whitespaces.                 |
| Tag already exists                                                                                                      | This tag already exists in the address book! | Since the tag already exists, there is no need for you to create a new one. You can reuse this same tag to tag applicants! |
| Using commas as delimiters of different prefixes instead of whitespaces e.g. `create t/dept software, t/role marketing` | Invalid command format!                      | Remove the comma(s) e.g. `create t/dept software, t/role marketing`                                                        |                                                                                                  |

[Jump back to Table of Contents](#table-of-contents)

#### Listing all tags: `listT`
<a name="listing-all-tags-listt"></a>

Shows a list of all tags in JABPro

Format: `listT`

* The `listT` command does not require any additional parameters or arguments.
* Tags listed by the `listT` command are unique and do not repeat.

**Example:**
* `listT` Shows a list of all tags.

![ListT](images/listT.png)

[Jump back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>
--------------------------------------------------------------------------------------------------------------------

### 2. Managing Applicants
<a name="2-managing-applicants"></a>

#### Adding an applicant: `add`
<a name="adding-an-applicant-add"></a>

Adds an applicant to JABPro.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/[CATEGORY] TAGNAME]…​`

| Type      | Field                  | Constraints                                                                                                                 |
|-----------|------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Mandatory | `n/NAME`               | `NAME` must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...).                                 |
| Mandatory | `p/PHONE_NUMBER`       | `PHONE_NUMBER` must contain numbers only and should be at-least 3 digits long.                                              |
| Mandatory | `e/EMAIL`              | `EMAIL` must be the standard email address format (There must be an email-prefix followed by  `@` symbol and email domain). |
| Mandatory | `a/ADDRESS`            | `ADDRESS` can be any value, including special characters such as `#`, `,` ...                                               |
| Optional  | `t/[CATEGORY] TAGNAME` | `[CATEGORY]` is optional. TAGNAME` must be alphanumeric with no spaces. Any details after the space will be ignored.        |

**Notes regarding additional constraint on `add` command:**
* The uniqueness of the applicant is determined by the name only. This means that you cannot have 2 applicants with the same name in the application book.
* All other fields other than name can be identical between different people in JABPro.
* Applicants added using the `add` command will be added to the end of the overall list of applicants (i.e. the list that you would get when you do `list`).

**Notes on adding tags:**
* If you would like to tag a user with a tag that has not been categorised yet using the `create` command,
  you can specify the category that you would like it to be categorised to in the `add` command. e.g. `...t/role swe`
* If you are using a tag that has not been categorised yet, and you did not specify its category in the `add` command,
  the tag would still be saved, but it would be _uncategorized_ by default.
* If you have multiple tags in different categories with the same name, you must specify the category when you want to
  add one of these tags to the applicant you are adding.

<box type="tip" seamless>

**Tip:**
* An applicant can have any number of tags (including 0)!
</box>

**An example of the `add` command being successfully executed:**
1. Enter the command `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/dept finance`
2. This is the result of the successful `add` command (Take note that command entered will not be shown in the result):
   
    ![Add-Success](images/add-command-success.png)
<br>

**Failed to add applicants? Here are some possible reasons why**

| Reason for Error                              | Error Message                                                                             | Remedy / Suggested course of action                                                                                                              |
|-----------------------------------------------|-------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| Missing add keyword: `add`                    | Unknown command                                                                           | Follow the command format of `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAGNAME]…​` closely                                                 |
| Missing mandatory fields                      | Invalid command format!                                                                   | Ensure that all mandatory fields are filled up.                                                                                                  | 
| Duplicate name                                | This person already exists in the address book                                            | Ensure that the name of the applicant is unique. That is you cannot add the same name twice. Use some form of extra identification like a number |
| Invalid phone number                          | Phone numbers should only contain numbers, and it should be at least 3 digits long        | Ensure that the phone number only contains number and should be at least 3 digits long                                                           |
| Invalid email                                 | Emails should be of the format local-part@domain and adhere to the following constraints: | Ensure that the prefix and domain of the email is correct following the constraints stated by the error                                          |                                                                                                                                                
| Invalid tag name                              | Tag names should only contain alphanumeric characters and should not be blank             | Ensure that the tag name only contains alphanumeric characters and should not be blank                                                           |
| Multiple prefixes of the same type being used | Multiple values specified for the following single-valued field(s): `prefix/`             | Remove the duplicate prefix. The command should only have 1 of every prefix except for `t/`                                                      |

[Jump back to Table of Contents](#table-of-contents)

#### Adding a remark to an applicant: `remark`
<a name="adding-a-remark-to-an-applicant-remark"></a>

Edits a remark of an existing applicant in JABPro.
Format: `remark INDEX r/REMARK`

| Type      | Field         | Constraints                                                                                                                                 |
|-----------|---------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Mandatory | `INDEX`       | `INDEX` must be an existing index in the displayed applicant list and it must not be greater than the total number of applicants in JABPro. |
| Optional  | `r/ [REMARK]` | `REMARK` can be any value, including special characters such as `#`, `,` ...                                                                |

**Notes regarding `remark` command:**
* The previous remark is not saved, and instead is replaced by the inputted remark. The command does not add to the existing remark.
* You can empty out a remark by inputting `r/` without any text after it or by omitting the `r/` prefix.
* You can get the remark previously inputted by using the `**REMARK**` keyword. It will be replaced with the previous remark (see example below). The keyword `**REMARK**` is case-sensitive. This means that `remark 1 r/**remark**` will just replace the remark with the word `**remark**`.
* You can use multiple prefix for `remark` but only the last prefix will be used. This means that `remark 1 r/remark r/remark2` will just replace the remark with `remark2`.

<div style="page-break-after: always;"></div>

**An example of the `remark` command being successfully executed:**
1. Enter the command `remark 1 r/Great attitude, hardworking`
2. This is the result of the successful `remark` command (Take note that command entered will not be shown in the result):
   ![Remark-Success](images/remark-command-success.png)
<br> 

<div style="page-break-after: always;"></div>

**An example of the `remark` command being successfully executed with the **REMARK** keyword:**
1. Enter the command `remark 1 r/**REMARK** furthermore he is great at teamwork`
2. This is the result of the successful `remark` command (Take note that command entered will not be shown in the result):
  ![Remark-Success](images/remark-command-enhanced-success.png)
<br>

**Failed to add remark to an applicant? Here are some possible reasons why**

| Reason for Error                 | Error Message                        | Remedy / Suggested course of action                                                                       |
|----------------------------------|--------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Missing remark keyword: `remark` | Unknown command                      | Follow the command format of `remark INDEX r/[REMARK]` closely                                            |
| Missing Index                    | Invalid command format!              | Ensure that the index is filled up.                                                                       |
| Invalid Index                    | The person index provided is invalid | Ensure that the index is valid. That is it is a number that is on the displayed applicant list.           |
 | Negative or 0 Index              | Invalid command format!              | Ensure that the index is a positive integer and is also a number that is on the displayed applicant list. |

**Additional Examples:**
*  `remark 1` Empties the remark of the 1st applicant. It is equivalent to `remark 1 r/`.
  
[Jump back to Table of Contents](#table-of-contents)

#### Adding LinkedIn/GitHub username for a user: `addL` or `addG`
<a name="adding-linkedingithub-username-for-a-user-addl-or-addg"></a>

Adds the username for their social profile [LinkedIn/GitHub] to the existing contact details of applicants.

Format: `addL INDEX u/USERNAME` or `addG INDEX u/USERNAME`

| Type      | Field        | Constraints                                                                                                           |
|-----------|--------------|-----------------------------------------------------------------------------------------------------------------------|
| Mandatory | `INDEX`      | `INDEX` must be a non-zero unsigned integer and it must not be greater than the total number of applicants in JABPro. |
| Mandatory | `u/USERNAME` | `USERNAME` must be a string value. Only the prefix (i.e. `u/`) is mandatory.                                          |

**Notes regarding `addL` and `addG` command:**

* Any set of characters entered after the prefix `u/` is taken to be the username, except if the prefix `u/` occurs multiple times.
* You may provide the username multiple times with the prefix `u/`, however, JABPro only considers the set of characters entered after the last occurring instance of `u/` as the username.
* You are expected to ensure that `USERNAME` is a valid username for the respective social profile. If it is not a valid username, user will be redirected to the error page of the corresponding social profile when `linkedin` or `github` command is invoked. JABPro does not perform checks for the validity of the username for the corresponding social profile.
* Invoking the `addL` or `addG` command for an applicant for whom a username has already been added, will simply overwrite the existing username with the new one.
* You may run the command `addL INDEX u/` or `addG INDEX u/`, i.e. providing no username, or simply providing blanks for the username. Such inputs are accepted by JABPro. However, it will prove to be erroneous when `linkedin` or `github` command is invoked.

**Example of successful execution of the `addL` command:**

1. Enter the command `addL 1 u/alexyeoh`
2. This is the result of the successful `addL` command

![AddL](images/addL.png)

**`addG` command is invoked in the same way.**

**Failed to add LinkedIn/Github username? Here are some possible reasons why**

| Reason for Error                 | Error Message                        | Remedy / Suggested course of action                                                                       |
|----------------------------------|--------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Missing `addL` or `addG` keyword | Unknown command                      | Follow the command format of `addL INDEX u/USERNAME` or `addG INDEX u/USERNAME` closely                   |
| Missing Index                    | Invalid command format!              | Ensure that the index is filled up.                                                                       |
| Invalid Index                    | The person index provided is invalid | Ensure that the index is valid. That is it is a number that is on the displayed applicant list.           |
| Negative or 0 Index              | Invalid command format!              | Ensure that the index is a positive integer and is also a number that is on the displayed applicant list. |
| Missing username                 | Invalid command format!              | Ensure that the username is filled up                                                                     |

[Jump back to Table of Contents](#table-of-contents)

#### Opening user LinkedIn or GitHub account: `linkedin` or `github`
<a name="opening-user-linkedin-or-github-account-linkedin-or-github"></a>

Redirects user to applicant's LinkedIn or GitHub account.

Format: `linkedin INDEX` or `github INDEX`

| Type      | Field   | Constraints                                                                                                           |
|-----------|---------|-----------------------------------------------------------------------------------------------------------------------|
| Mandatory | `INDEX` | `INDEX` must be a non-zero unsigned integer and it must not be greater than the total number of applicants in JABPro. |

**Notes regarding `LinkedIn` and `GitHub` commands:**

* User is expected to enter `INDEX` for an applicant for whom username [that is not blank, or does not comprise of only spaces] has been added previously.
* User is redirected to the page of the social profile regardless of the validity of the username for that particular social profile (i.e. whether the social profile exists)

**Example of successful execution of `github` command:**

1. Enter the command `github 1`
2. This is the result of the successful `github` command [It is assumed an applicant exists in JABPro, with Github username previously added]:

![Github](images/linkedin.png)

The GitHub window opens as follows, displaying the profile with the specified username, or error page in case profile with that username does not exist:

![GithubProfile](images/github.png)

`linkedin` command is invoked in the same manner.

**Failed to redirect to social profile? Here are some possible reasons why**

| Reason for Error                       | Error Message                                                                                                  | Remedy / Suggested course of action                                                                       |
|----------------------------------------|----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Missing `linkedin` or `github` keyword | Unknown command                                                                                                | Follow the command format of `linkedin INDEX` or `github INDEX` closely                                   |
| Missing Index                          | Invalid command format!                                                                                        | Ensure that the index is filled up.                                                                       |
| Invalid Index                          | The person index provided is invalid                                                                           | Ensure that the index is valid. That is it is a number that is on the displayed applicant list.           |
| Negative or 0 Index                    | Invalid command format!                                                                                        | Ensure that the index is a positive integer and is also a number that is on the displayed applicant list. |
| Missing account for provided Index     | No LinkedIn account has been added for this candidate. or No Github account has been added for this candidate. | Ensure that username has been previously added to the specified candidate                                 |

[Jump back to Table of Contents](#table-of-contents)

#### Setting an applicant's status: `set`
<a name="setting-an-applicants-status-set"></a>

Sets the applicant to a specific status ("Preliminary"/ "Interviewed"/ "Rejected"/ "Offered").

Format: `set INDEX STATUS`

| Type      | Field    | Constraints                                                                                                                                                                   |
|-----------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Mandatory | `INDEX`  | `INDEX` must be an existing index in the displayed applicant list and it must not be greater than the total number of applicant in JABPro. It must also be a positive integer |
| Mandatory | `STATUS` | `STATUS` must be one of the following - "Preliminary", "Interviewed", "Rejected", "Accepted". It is case-insensitive.                                                         |

**Examples:**
* `list` followed by `set 2 Interviewed` sets the 2nd applicant in the address book to "Interviewed".

[Jump back to Table of Contents](#table-of-contents)

#### Viewing an applicant's details: `view`
<a name="viewing-a-applicants-details-view"></a>

Creates a complete view for details of an applicant in the applicant information panel and summary statistics (if applicable) of an applicant in the summary statistics panel.

Format: `view INDEX`

| Type      | Field   | Constraints                                                                                                                                                                    |
|-----------|---------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Mandatory | `INDEX` | `INDEX` must be an existing index in the displayed applicant list and it must not be greater than the total number of applicant in JABPro. It must also be a positive integer. |

**Notes regarding `view` command:**
* Refer to the [Summary Statistics](#summary-statistics) section for more details on the summary statistics.

<box type="tip" seamless>

**Tip:** Other operations that affect user's data will trigger a refresh of the view.
These include `add`, `edit`, `set`, `remark`, `addL`, `addG`.
This means that the view will be updated to reflect the latest changes to the data for that particular applicant.

</box>

**An example of the `view` command being successfully executed:**
1. Enter the command `view 3`
2. This is the result of the successful `view` command (Take note that command entered will not be shown in the result):
   
   ![View-Success](images/view-command-success.png)
<br>

**An example of the `view` command being successfully executed for applicant with tags and score:**
1. Enter the command `view 2` (**Applicant with tags and score**)
2. This is the result of the successful `view` command (Take note that command entered will not be shown in the result):
   
    ![View-Success](images/view-command-with-stats-success.png)
<br>


**Failed to execute `view` command? Here are some possible reasons why**

| Reason for Error             | Error Message                        | Remedy / Suggested course of action                                                             |
|------------------------------|--------------------------------------|-------------------------------------------------------------------------------------------------|
| Missing view keyword: `view` | Unknown command                      | Follow the command format of `view INDEX` closely                                               |
| Missing Index                | Invalid command format!              | Ensure that the index is filled up.                                                             |
| Invalid Index                | The person index provided is invalid | Ensure that the index is valid. That is it is a number that is on the displayed applicant list. |

[Jump back to Table of Contents](#table-of-contents)

#### Editing an applicant: `edit`
<a name="editing-a-applicant-edit"></a>

Edits an existing applicant's detail in JABPro. It also includes the functionality to add scores to a specific applicant.

Format: `edit INDEX ([n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAGNAME]…​ [sc/TAGNAME SCORE])`

| Type       | Field                  | Constraints                                                                                                                 |
|------------|------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| Mandatory  | `INDEX`                | `INDEX` must be a non-zero unsigned integer and must also not be greater than the total number of applicants in JABPro.     |
| Mandatory* | `n/NAME`               | `NAME` must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...).                                 |
| Mandatory* | `p/PHONE_NUMBER`       | `PHONE_NUMBER` must contain numbers only and should be at-least 3 digits long.                                              |
| Mandatory* | `e/EMAIL`              | `EMAIL` must be the standard email address format (There must be an email-prefix followed by  `@` symbol and email domain). |
| Mandatory* | `a/ADDRESS`            | `ADDRESS` can be any value, including special characters such as `#`, `,` ...                                               |
| Mandatory* | `t/[CATEGORY] TAGNAME` | `TAGNAME` must be alphanumeric with no spaces. Any details after the space will be ignored.                                 |
| Mandatory* | `sc/TAGNAME SCORE`     | `TAGNAME` a tag that is being created or already exist for that applicant. `SCORE` must be a non-negative integer.          |

*it is mandatory if and only if it is the only field used in the command.

**Notes regarding `edit` command:**
* At least one of the optional fields must be provided.
* Existing attributes will be updated to the parameters.
* There is a way to edit tags to an existing applicant and their categories at the same time. Look at the notes for editing tags with categories `t/[CATEGORY] TAGNAME` for more details.
  
<box type="tip" seamless>

**Tip:** Editing an applicant's details will trigger a refresh of the view. This means that the view will be updated to reflect the latest changes to the data for that particular applicant.
</box>

**Editing tags of an applicant? Take note of the following**:
* When editing the tags of a specific applicant, the existing tags of the applicant will be removed i.e. adding of tags is **not** cumulative. 
  You will have to re-tag the applicant with the existing tags and the new tags.
* Doing `edit INDEX /t` removes the tags of the applicant at that index.
* If you used a tag that has not been created using `create` in an `edit` command, the tag would still be added to the applicant, but it would be _uncategorized_.
* If you have **multiple tags in different categories with the same name**, you must **specify the category** when you want to tag the specified applicant with one of these tags e.g. `edit INDEX t/CATEGORY DUPLICATETAGNAME`

<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>

* If you still would like to tag an applicant that has **not** been created, but you do not want this tag to be _uncategorized_, you can do so by doing 
`edit INDEX t/CATEGORY TAGNAME`.

<box type="warning" seamless>

**Caution:**
If you tag a person with a tag that has not been categorised yet without specifying the category i.e. `edit 1 t/UNCATEGORIZEDTAG`, and then you created that same tag with a specific category using `create`, 
you need to re-tag the person with the same tag name again in order to overwrite the previous uncategorized tag with the categorized tag.

This is because, `create` only creates **new** categorized tags. It does **not** categorize existing tags when you do `create t/CATEGORY EXISTINGTAGNAME`
</box>

**Editing the score of an applicant? Take note of the following**:
* The `TAG` in `sc/TAGNAME SCORE` must be a tag of the category `assessment`. You cannot use the `sc/TAG SCORE` field for tags that are not of the `assessment` category.
* The `SCORE` in `sc/TAG SCORE` is non-negative, that is `SCORE` must be more than or equal to 0.
* To clear a tag's score, just re-tag it with the same tag name, but without using the `sc/TAG SCORE` field e.g. `edit 1 t/SCORETAG`
* You can only edit the score of an applicant i.e. `edit INDEX sc/TAGNAME SCORE` if they have been tagged with the assessment-related `TAGNAME`.


<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>

* You can also tag the applicant and edit their score **at the same time** by doing `edit INDEX t/TAGNAME sc/TAGNAME SCORE`. 
* Note that the tag has to have already been created using `create` with the category `assessment`. You **cannot** do this with a tag that hasn't been created even if you specified its category _assessment_ i.e. `edit INDEX t/assessment interview1 sc/interview1 100` does **NOT**
work. This is because, you cannot create tag and edit score at the same time as editing score relies on the fact that the tag has already existed.

<box type="tip" seamless>

**How is creating tags using `edit` different from `create`?**
<br>
Both will create categorized tags and add them to the tag list but `create` serves the singular purpose of creating categorized tags, while the main purpose of `edit`
is to _edit_ the details of an applicant which includes tags.
<br>
The reason why we've allowed you to create tags in the event you use `edit` with a tag that 
hasn't been created is for the sake of convenience (i.e. if you had forgotten to create the tag, it would still be added to the applicant you were editing).
<br>
Nonetheless, we **strongly recommend you to use `create` to create categorized tags** if your only intention is to _create tags_. 
</box>

<div style="page-break-after: always;"></div>

**An example of the `edit` command being successfully executed:**
1. Enter the command `edit 1 n/Alex Ho p/91234567` (**edits name and phone number**)
2. This is the result of the successful `edit` command (Take note that command entered will not be shown in the result):
   
   ![Edit-Success](images/edit-command-success.png)
<br>

<div style="page-break-after: always;"></div>

**An example of the `edit` command being successfully executed with tags and score:**
1. Ensure that you have created a tag `Interview` under the `assessment` category using the `create` command. Enter the command `create t/assessment Interview`
2. Enter the command `edit 1 t/Interview sc/Interview 80` (**edits tag and score**)
3. This is the result of the successful `edit` command (Take note that command entered will not be shown in the result):
   
   ![Edit-Success](images/edit-command-with-stats-success.png)
<br>

<div style="page-break-after: always;"></div>

**An example of the `edit` command being successfully executed to clear a tags and score:**
1. Enter the command `edit 1 t/` (**Clear all tags**)
2. This is the result of the successful `edit` command (Take note that command entered will not be shown in the result):
   
   ![Edit-Success](images/edit-command-clear-tags-success.png)
<br>

**Failed to edit an applicant's details? Here are some possible reasons why**

| Reason for Error                                                                 | Error Message                                                                                                                  | Remedy / Suggested course of action                                                                                                                                                                                                                                           |
|----------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Missing edit keyword: `edit`                                                     | Unknown command                                                                                                                | Follow the command format of `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAGNAME]…​ [sc/TAGNAME SCORE]` closely                                                                                                                                                   |
| Missing Index                                                                    | Invalid command format!                                                                                                        | Ensure that the index is filled up.                                                                                                                                                                                                                                           |
| Invalid Index                                                                    | The person index provided is invalid                                                                                           | Ensure that the index is a valid number on the displayed applicant list.                                                                                                                                                                                                      |
| Missing at least one of the field                                                | At least one field to edit must be provided.                                                                                   | Ensure that at least one of the field is filled up and to be changed.                                                                                                                                                                                                         |
| Duplicate name                                                                   | This person already exists in the address book                                                                                 | Ensure that the name of the applicant is unique. That is, you cannot add the same name twice. Use some form of extra identification like a number                                                                                                                             |
| Invalid phone number                                                             | Phone numbers should only contain numbers, and it should be at least 3 digits long                                             | Ensure that the phone number only contains numbers and should be at least 3 digits long                                                                                                                                                                                       |
| Invalid email                                                                    | Emails should be of the format local-part@domain and adhere to the following constraints: ...                                  | Ensure that the prefix and domain of the email is correct, following the constraints stated by the error                                                                                                                                                                      |                                                                                                                                                
| Invalid tag name                                                                 | Tag names should only contain alphanumeric characters and should not be blank                                                  | Ensure that the tag name only contains alphanumeric characters and should not be blank                                                                                                                                                                                        |
| Multiple prefixes of the same type being used (does not apply to tag prefix `t`) | Multiple values specified for the following single-valued field(s): `prefix/`                                                  | Remove the duplicate prefix. The command should only have 1 of every prefix except for `t/`                                                                                                                                                                                   |
| Missing score for tag                                                            | Invalid score, score must be non-negative integer.                                                                             | Ensure that the score is filled up and is separated from the `TAGNAME` by a whitespace (NOT commas).                                                                                                                                                                          | 
| Invalid tag to attach score                                                      | Invalid score tag, tag must a tag of the category assessment and must exist on the applicant                                   | Ensure that the tag is of the category assessment and it exists on the applicant. If it's the wrong category, use `create` i.e. `create t/assessment TAGNAME` to firstly create the categorized tag. If it is not tagged to the person use `edit` i.e. `edit INDEX t/TAGNAME` |
| Missing valid score-tag on applicant                                             | The tag does not exist, cannot attach a score to it                                                                            | Ensure that the applicant has the tag and it is of  category `assessment`, this is what is considered a valid score-tag. This is done by creating an `assessment` category for the tag name using `create` and update tag using `edit INDEX t/TAGNAME ...`                    | 
| Tag ambiguity                                                                    | Multiple tags exists with the same name! Specify the category of the tag when adding it to a person e.g. edit 1 t/experience 3 | Specify the category of the tag you want to add in the field e.g.`edit INDEX t/CATEGORY DUPLICATETAGNAME`                                                                                                                                                                     |


[Jump back to Table of Contents](#table-of-contents)  

#### Deleting job applicants: `delete`
<a name="deleting-job-applicants-delete"></a>

Deletes the specified job applicants from the address book.

Format: `delete INDEX` 

| Type      | Field   | Constraints                                                                                                             |
|-----------|---------|-------------------------------------------------------------------------------------------------------------------------|
| Mandatory | `INDEX` | `INDEX` must be a non-zero unsigned integer and must also not be greater than the total number of applicants in JABPro. |

Format: `delete (t/TAGNAME... st/STATUS...)`

| Type       | Field       | Constraints                                                                  |
|------------|-------------|------------------------------------------------------------------------------|
| Mandatory* | `t/TAGNAME` | `TAGNAME` must be alphanumeric and contains no spaces.                       |
| Mandatory* | `st/STATUS` | `STATUS` must either be `preliminary`, `interviewed`, `offered`, `rejected`. |

*it is mandatory if and only if it is the only field used in the command.

**Note:**
* User **cannot** delete by index and delete by tags & status in a single command.<br>
  E.g. `delete 1 t/hardworking` is **not** allowed.
* `delete` by tags & status requires at least ONE delete parameter i.e. `st/STATUS`, or `t/TAGNAME`.
* Each prefix can only be used at most once.
* Multiple delete parameters for a specific category are divided by spaces (not commas!) i.e. `delete st/preliminary interviewed`
* Delete parameters are case-insensitive i.e. doing `delete t/interN` is the same as `delete t/intern`

<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>


**Note:**
* You can combine multiple delete categories in a single `delete` command e.g. `delete st/interviewed t/intern`

**An example of **delete by index** command being successfully executed:**
1. Enter the command `delete Bernice`

   ![delete-by-index-pre](images/delete-by-index-pre.png)    

2. Enter the command `delete 1`

   ![delete-by-index-success](images/delete-by-index-success.png)

Similarly, here are some examples of **delete by tags & status** command being successfully executed:

Assuming this as the data after executing `list`:
![delete-by-tags-status-pre](images/delete-by-tags-status-pre.png)

1. `delete t/marketing software`

   ![delete-by-tags-status-pre](images/delete-by-tags-status-1.png)

   The above `delete` command deleted all applicants whose tags match ANY of the given keywords. This is because
   `delete` does an `OR` search **within a specific category**. <br>

2. `delete st/interviewed t/software`

   ![delete-by-tags-status-pre](images/delete-by-tags-status-2.png)

   The above `delete` command only deleted Bernice because `delete` does an `AND` search **across multiple categories**.<br>

<box type="tip" seamless>

**What does it mean to do an `OR` delete within a single category and an `AND` delete across multiple categories?**
<br>
It's best to explain this by breaking down an example `delete` command! <br>
`delete st/interviewed rejected t/intern manager` will delete applicants whose:
* status is either **interviewed** `OR` **rejected**
* `AND` has a tag `intern` or `manager`.

</box>

**Failed to execute the `delete` command? Here are some possible reasons why**

| Reason for Error                                                                                            | Error Message                                                                                                                                                                                        | Remedy / Suggested course of action                                                                               |
|-------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| Missing delete keyword                                                                                      | Unknown command!                                                                                                                                                                                     | Follow the command format `delete INDEX` OR `delete (st/STATUS... t/TAGNAME...)`                                  |
| Missing delete categories e.g. `delete`                                                                     | Invalid command format!                                                                                                                                                                              | Make sure you include **at least one** of the delete categories i.e. `/st`, `/t`.                                 |
| Invalid INDEX e.g. `delete 0`, `delete -1`                                                                  | Invalid command format!                                                                                                                                                                              | Make sure `INDEX` is a positive integer.                                                                          |
| Invalid status e.g. `delete st/in`, `delete st/`                                                            | Status should be either one of the following: 'Preliminary','Interviewed', 'Offered', 'Rejected' and it should not be blank.                                                                         | Check that the `status` is one of the following: `preliminary`, `interviewed`, `rejected`, `offered`.             |
| Invalid tag e.g. `delete t/intern@`, `delete t/`                                                            | Tags names should be alphanumeric.                                                                                                                                                                   | Ensure that `tag` does not contain any non alphanumeric characters such as &, $, @, -, %, *, _, empty space, etc. |
| Multiple prefixes of the same category being used e.g. `delete t/intern t/manager`                          | Multiple values specified for the following single-valued field(s): `prefix/`.                                                                                                                       | Remove the duplicate prefix. The command should only have at most **one** of every prefix.                        |
| Using commas as delimiters of different parameters instead of spaces e.g. `delete t/intern, manager`        | Status should be either one of the following: 'Preliminary','Interviewed', 'Offered', 'Rejected' and it should not be blank<br/>Tags names should be alphanumeric.                                   | Remove the comma(s) e.g. `delete t/intern manager`                                                                |
| Using commas as delimiters of different parameters instead of spaces e.g. `delete st/interviewed, t/intern` | Should display the error message for either invalid `status` or `tag` depending on the first prefix because it will consider the comma and anything that comes after it as part of the first prefix. | Remove the comma(s) e.g. `delete n/alex t/intern`                                                                 |

[Jump back to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
### 3. Filtering and Listing Applicants
<a name="3-filtering-and-listing-applicants"></a>

#### Searching job applicants by category: `search`
<a name="searching-job-applicants-by-category-search"></a>

Finds job applicants whose profiles match the specified categories' keywords. The search categories are: name, status, tag.

Format: `search (n/NAME...  st/STATUS...  t/TAGNAME...)`

| Type       | Field       | Constraints                                                                                 |
|------------|-------------|---------------------------------------------------------------------------------------------|
| Mandatory* | `n/NAME`    | `NAME` must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...). |
| Mandatory* | `st/STATUS` | `STATUS` must either be `preliminary`, `interviewed`, `offered`, `rejected`.                |
| Mandatory* | `t/TAGNAME` | `TAGNAME` must be alphanumeric and contains no spaces.                                      |

*it is mandatory if and only if it is the only field used in the command.

**Note**:
* `search` requires at least ONE search parameter i.e. `n/NAME`, `st/STATUS`, or `t/TAGNAME`.
* Each prefix can only be used at most once.
* Multiple search parameters for a specific category are divided by spaces (not commas!) i.e. `search st/preliminary interviewed`
* Search parameters are case-insensitive i.e. doing `search n/aLeX` is the same as `search n/alex`

<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>


**Note:**
* You can combine multiple search categories in a single `search` command e.g. `search n/alex st/interviewed t/intern`

**Here are some examples of **search** command being successfully executed:**
1. Enter `search n/alex bernice`

   ![search-success-1](images/search-success-1.png)

   The above `search` command displayed all applicants whose name match ANY of the given keywords. This is because
   `search` does an `OR` search **within a specific category**. <br>

2. Enter `search n/alex bernice st/interviewed t/intern`

   ![search-success](images/search-success.png)

   Notice how the above `search` command did not display "Alex" despite his profile matching
   the `name` and `tag` categories. This is because `search` does an `AND` search **across multiple categories**.<br>

<box type="tip" seamless>

**What does it mean to do an `OR` search within a single category and an `AND` search across multiple categories?** <br>
It's best to explain this by breaking down an example `search` command!
<br>
`search n/alex bernice st/interviewed t/intern` will output applicants whose:
* names contain either Alex `OR` Bernice
* `AND` status is either interviewed
* `AND` has a tag `intern`

</box>

**Failed to execute the `search` command? Here are some possible reasons why**

| Reason for Error                                                                                    | Error Message                                                                                                                                                                                                                                                   | Remedy / Suggested course of action                                                                                                                    |
|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| Missing search keyword                                                                              | Unknown command!                                                                                                                                                                                                                                                | Follow the command format `search (n/NAME... st/STATUS... t/TAGNAME...)`                                                                               |
| Missing search categories e.g. `search`                                                             | Invalid command format!                                                                                                                                                                                                                                         | Make sure you include **at least one** of the search categories i.e. `/n`, `/st`, `/t`.                                                                |
| Invalid name e.g. `search n/alex@`, `search n/`                                                     | Names should only contain alphanumeric characters and spaces, and it should not be blank.                                                                                                                                                                       | Ensure that `name` does not contain any non-alphanumeric characters such as &, $, @, -, %, *, _, etc.                                                  |
| Invalid status e.g. `search st/in`, `search st/`                                                    | Status should be either one of the following: 'Preliminary','Interviewed', 'Offered', 'Rejected' and it should not be blank.                                                                                                                                    | Check that the `status` is one of the following: `preliminary`, `interviewed`, `rejected`, `offered`. Enter the command again with any of the 4 metric |
| Invalid tag e.g. `search t/intern@`, `search t/`                                                    | Tags names should be alphanumeric.                                                                                                                                                                                                                              | Ensure that `tag` does not contain any non alphanumeric characters such as &, $, @, -, %, *, _, empty space, etc.                                      |
| Multiple prefixes of the same category being used e.g. `search n/alex n/bernice`                    | Multiple values specified for the following single-valued field(s): `prefix/`.                                                                                                                                                                                  | Remove the duplicate prefix. The command should only have at most **one** of every prefix.                                                             |
| Using commas as delimiters of different parameters instead of spaces e.g. `search n/alex, bernice`  | Names should only contain alphanumeric characters and spaces, and it should not be blank<br/>Status should be either one of the following: 'Preliminary','Interviewed', 'Offered', 'Rejected' and it should not be blank<br/>Tags names should be alphanumeric. | Remove the comma(s) e.g. `search n/alex yeoh`                                                                                                          |
| Using commas as delimiters of different parameters instead of spaces e.g. `search n/alex, t/intern` | Should display the error message for either invalid `name`, `status`, or `tag` depending on the first prefix because it will consider the comma and anything that comes after it as part of the first prefix.                                                   | Remove the comma(s) e.g. `search n/alex t/intern`                                                                                                      |

[Jump back to Table of Contents](#table-of-contents)

#### Filter job applicants by statistics: `filter`
<a name="filter-job-applicants-by-statistics-filter"></a>

Filters and display applicants in the current displayed applicant list using statistical metrics and values.
<br>
In essence, this allows you to find job applicants whose performance rating is above a certain percentile, score or mean/median score for that tag.  
<br>
Ideally, this feature can then be used to find the best applicants easily and quickly without having to manually look through the list of applicants.

Format:`filter t/TAGNAME met/METRIC val/VALUE` or `filter t/TAGNAME met/METRIC`

| Type       | Field              | Constraints                                                                                       |
|------------|--------------------|---------------------------------------------------------------------------------------------------|
| Mandatory  | `t/TAGNAME`        | `TAGNAME` must be a tag that is of the category `assessment`.                                     |
| Mandatory  | `met/METRIC`       | `METRIC` must be either `score`, `percentile`, `mean`, `median`.                                  |
| Mandatory* | `val/VALUE`        | `VALUE` must be a non-negative integer and is a mandatory field only for `score` and `percentile` |

*`val/VALUE` is an **optional** field for `mean` and `median`

<box type="info" seamless>

* For `METRIC` that is `score` or `percentile`, `filter` displays job applicants whose **value** is **greater than or equal** to the specified value for the specified statistic metric.
* For `METRIC` that is `mean` or `median`, the `VALUE` is optional. Specifying a `VALUE` here will be ignored accordingly. `filter t/TAGNAME met/METRIC` is equivalent to `filter t/TAGNAME met/METRIC val/X` where `X` is any positive integer.
* Do look at the [Summary Statistics](#summary-statistics) section for more details on the summary statistics metrics.
</box>

**Notes:**
* Filter works only on the **current list of job applicants displayed**. It is **essential** that you enter `list` before using `filter` to ensure that you are filtering the correct list of job applicants.
* Filter does not edit, update or in any way change the data of the job applicants. It only filters and displays the job applicants.
* Filter does not trigger view, that is your view panels represent the previous applicant you viewed before filtering.
* To get back the **original list with all the applicants**, simply type `list` again.

<box type="tip" seamless>

You should use `filter` after you have tagged most of the job applicants with a tag that has a score.   
This is because some metrics such as `percentile`, `mean` and `median` require a certain number of scores to be considered meaningful.
Read more about this in the [Summary Statistics](#summary-statistics) section.

</box>

<div style="page-break-after: always;"></div>

Set up for examples when you first start JABPro with default data:
1. `list`
2. `create t/assessment interview` to create a tag `interview` under the `assessment` category.
3. `edit 1 t/interview sc/interview 80`
4. `edit 2 t/interview sc/interview 90`
5. `edit 3 t/interview sc/interview 70`
6. The result of the above commands should look like this:

   ![Filter-Setup](images/filter-setup.png)
<br>

<div style="page-break-after: always;"></div>

**An example of the `filter` command being successfully executed:**
1. Enter the command `list`
2. Enter the command `filter t/interview met/percentile val/80` (**Filter by percentile**)
3. This is the result of the successful `filter` command (Take note that command entered will not be shown in the result):

   ![Filter-Success](images/filter-command-success.png)
<br>

<div style="page-break-after: always;"></div>

**An example of the `filter` command being successfully executed with `median`:**
1. Enter the command `list`
2. Enter the command `filter t/interview met/median` (**Filter by median**)
3. This is the result of the successful `filter` command (Take note that command entered will not be shown in the result):
   
   ![Filter-Success](images/filter-command-median-success.png)
<br>

**Failed to execute the `filter` command? Here are some possible reasons why:**

| Reason for Error                              | Error Message                                                                              | Remedy / Suggested course of action                                                                                                                                                                      |
|-----------------------------------------------|--------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Missing filter keyword: `filter`              | Unknown command!                                                                           | Follow the command format strictly of `filter t/TAGNAME met/METRIC val/VALUE` for score and percentile or `filter t/TAGNAME met/METRIC` for mean or median.                                              | 
| Missing parameters                            | Incomplete parameter inputs. t/TAG and met/SCORE are compulsory fields.                    | Enter the command again with the correct parameters.                                                                                                                                                     |
| Tag missing on applicants                     | Tag does not exist!                                                                        | Check that the tag is used on at least one applicant and that the tag is a `assessment` tag also. Add the tag to the applicants using `edit`                                                             |
| Invalid tag name                              | Invalid tag provided. Needs to be non-empty name                                           | Check that the tag name is not empty and is alphanumeric (a valid tag name) and does not contain space. Additionally, the tag must already have a category of `assessment`, ensure this by using `listT` |                                                                  |                                                                                           | |                                                                  |                                                                                           |
| Invalid metric                                | Invalid metric provided. Needs to be one of: score, mean, median, percentile               | Check that the metric is one of the following: `score`, `mean`, `median`, `percentile` and that it is spelt correctly. Enter the command again with any of the 4 metric                                  |
 | Invalid value                                 | Invalid value provided. Needs to be a non negative integer that is more than or equal to 0 | Check that the value is a non-negative integer that is more than or equal to 0. Enter the command again with the correct value.                                                                          |
 | Missing value                                 | val/VALUE is missing, it is compulsory.                                                    | Enter a value for `val/VALUE` since the metric requires it.                                                                                                                                              |
| Multiple prefixes of the same type being used | Multiple values specified for the following single-valued field(s): `prefix/`              | Remove the duplicate prefix. The command should only have 1 of every prefix                                                                                                                              |

[Jump back to Table of Contents](#table-of-contents)  


#### Listing all applicant: `list`
<a name="listing-all-applicant-list"></a>

Shows a list of all applicants in JABPro

Format: 
`list [so/ATTRIBUTE]`

| Type     | Field          | Constraints                                   |
|----------|----------------|-----------------------------------------------|
| Optional | `so/ATTRIBUTE` | `ATTRIBUTE` must either be `name` or `email`. |

**Note:**
* Attribute is case-insensitive: `list so/NAME` and `list so/name` return the same result.
* The result will be sorted in **ascending** order.
* The sorting algorithm is case-sensitive, which means it will treat uppercase and
  lowercase letters as distinct. This may result in names being sorted as A B C a b c, rather than A a B b C c.

[Jump back to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### 4. Managing Events
<a name="4-managing-events"></a>

#### Adding an Event: `event`
<a name="adding-an-event-event"></a>

Adds an event, associated with an applicant, to JABPro.

Format: `event INDEX d/DESCRIPTION bt/BEGIN_TIME et/END_TIME`

| Type       | Field           | Constraints                                                                                                         |
|------------|-----------------|---------------------------------------------------------------------------------------------------------------------|
| Mandatory  | `INDEX`         | `INDEX` must be a non-zero unsigned integer and it must not be greater than the total number of applicant in JABPro |
| Mandatory  | `d/DESCRIPTION` | `DESCRIPTION` must be a string value. Only the prefix (i.e. `d/`) is mandatory.                                     |
| Mandatory  | `bt/BEGIN_TIME` | `BEGIN_TIME` must be a valid date-time, in the format `yyyy-MM-dd HH:mm`                                            |
| Mandatory  | `et/END_TIME`   | `END_TIME` must be a valid date-time, in the format `yyyy-MM-dd HH:mm`, and after the `BEGIN_TIME`                  |

**Note:**

* JABPro allows the addition of multiple events associated with the same applicant, having the same description. It is up to the user to provided detailed descriptions to distinguish events from one another.
* Events added to JABPro can also be found in the `data/eventbook.json` file. Existing events are also read from the file when JABPro starts up.
* Events with empty `DESCRIPTION`s can also be added. However, the prefix `d/` must still be present.

**Example of successful execution of `event` command:**

1. Enter the command `event 1 d/Interview bt/2023-11-12 10:00 et/2023-11-12 12:00`
2. This is the result of the successful `event` command [It is assumed an applicant called Alex Yeoh exists in JABPro]:

![Event](images/event.png)

The changes in UI take place in the `Events Window`. Please find more details in [Viewing Events](UserGuide.md#viewing-events-schedule).

**Failed to execute teh `event` command? Here are some possible reasons why:**

| Reason for Error                          | Error Message                        | Remedy / Suggested course of action                                                                       |
|-------------------------------------------|--------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Missing event keyword: `event`            | Unknown command                      | Follow the command format of `event INDEX d/DESCRIPTION bt/START_TIME et/END_TIME` closely                |
| Missing Index                             | Invalid command format!              | Ensure that the index is filled up.                                                                       |
| Invalid Index                             | The person index provided is invalid | Ensure that the index is valid. That is it is a number that is on the displayed applicant list.           |
| Negative or 0 Index                       | Invalid command format!              | Ensure that the index is a positive integer and is also a number that is on the displayed applicant list. |   
| Missing description                       | Invalid command format!              | Ensure that the description is filled up                                                                  |
| Missing start time                        | Invalid command format!              | Ensure that the start time is filled up                                                                   |
| Missing end time                          | Invalid command format!              | Ensure that the end time is filled up                                                                     |
| Start time/End time not in correct format | Date is not in correct format!       | Ensure that the start time/end time is in yyyy-MM-dd format                                               |
| Start time/End time not a valid date-time | Date entered is invalid!             | Ensure that the start time/end time is a semantically valid date                                          |
| End time before or same as start time     | End time must be after start time!   | Ensure that the end time is after the start time                                                          |                                                                 |                                                                                           |

[Jump back to Table of Contents](#table-of-contents)

#### Viewing events: `schedule`
<a name="viewing-events-schedule"></a>

Displays all events that have been added to JABPro.

Format: `schedule`

**Note:**

* `schedule` command will open the `Events` window regardless of whether there are events in JABPro or not.
* Any set of characters added after the `schedule` keyword will be ignored. E.g.: `schedule a1b2c3`

<box type="tip" seamless>

**Tip:** The `Events` window can also be accessed by clicking `Events > Events` in the menu bar, located at the top of the window.
        <img src="images/eventstab.png">
</box>

**Example of successful execution of `schedule` command:**

1. Enter the command `schedule`
2. This is the result of the successful `schedule` command [It is assumed that an event was previously added using the command `event 1 d/Interview bt/2023-11-12 10:00 et/2023-11-12 12:00]:

![Schedule](images/schedule.png)

The `Events` window opens up:

![EventWindow](images/eventwin.png)

There is no possibility of a "failed" execution of the `schedule` command.

[Jump back to Table of Contents](#table-of-contents)
<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
### 5. Others
<a name="5-others"></a>

#### Exporting the existing data to csv: `export`
<a name="exporting-all-entries-export"></a>

Format: `export`

Exports the entries into a .csv file located in the current directory as (/data/export.csv)

**Note:**
* Export only exports the following: Name, Phone, Email, Address, Tags, Linkedin, Github, Remark, Status.
* JABPro must have write permissions, this means that if the .csv file is open, 
  exporting again will not be possible.
    
**Examples:**
* `export` exports the data to /data/export.csv

[Jump back to Table of Contents](#table-of-contents)

#### Clearing all entries: `clear`
<a name="clearing-all-entries-clear"></a>

Clears all entries from JABPro - including applicants and events.

Format: `clear`

**Note:**

* Usage of the `clear` command empties all records, not only in the current running instance of JABPro, but from the `json` files as well where the data is written to/read from. Hence, a subsequent launch of JABPro will display an empty application.
* `clear` command can be invoked on an already empty instance of JABPro as well, without any errors being raised. There is no effect of running this command.

**Example of the successful execution of the `clear` command:**

![Clear](images/clear.png)

[Jump back to Table of Contents](#table-of-contents)

#### Exiting the program: `exit`
<a name="exiting-the-program-exit"></a>

Exits the program.

Format: `exit`

[Jump back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## Additional information
<a name="additional-information"></a>

<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>


### Summary Statistics

Summary Statistics is a table generated by JABPro that displays the following information about an applicant:
It is generated for tags that are categorised under the `assessment` category.

| Statistic / Metric | Description                                   |
|--------------------|-----------------------------------------------|
| `score`            | The score of the applicant for the tag.       |
| `mean`             | The mean score of applicant with that tag.    |
| `median`           | The median score of applicant with that tag.  |
| `minimum`          | The minimum score of applicant with that tag. |
| `maximum`          | The maximum score of applicant with that tag. |
| `percentile`       | The percentile of the applicant for that tag. |

<box type="warning" seamless>

You should ensure that you have **sufficient candidates of more than 20** with a score for the tag you are interested in, before using the summary statistics to make comparisons.
</box>

**Notes on why you should have sufficient applicants with a score for the tag you are interested in:**
* This is due to the fact that these summary statistics rely on concepts such as mean, median and percentile, which are statistical concepts that require a sufficient sample size to be meaningful.
* For example, if you have only assigned 5 out of 100 applicants, the summary statistics will not be representative of the actual mean, median and percentile for that tag.
* In this case, you should assign more applicants with a score for that tag, before using the summary statistics to make comparisons.
* If you have assigned a sufficient number of applicants with a score for that tag, you can use the summary statistics to make comparisons. For example, you want to check if an applicant's score for a tag is more than or equal to half of all the applicant who have a score for that tag, you can use the median to make this comparison.
* A **sufficient number** could be deemed as **any number that is more than 20**, but this is not a hard and fast rule. You should use your own discretion to determine if the number of applicant with a score for that tag is sufficient.

<box type="tip" seamless>

**Tip:**
1. Use mostly `median` and `percentile` to make your judgement on the performance of an applicant.
2. `median` to find applicants who are the better performing half
3. `percentile` as where this applicant stands among all other applicants (treat it like a ranking system, the higher the percentile, the better the applicant is performing)
   </box>

<div style="display: flex; justify-content: center; align-items:center;  background-color: #FF0000; padding: 10px; border-radius: 30px; width: 100px; height: 50px; text-align: center;">
    <p style="color: #FFFFFF; font-size: 15px; font-weight: bold;">Advanced</p>
</div>


**Note**:
* Understand that `percentile` has limited functionality in some context. This is because if two applicants have the same score, they are `rank` the same. This means that the percentile of both applicants will be the same.
    * If all applicants have the same score, their percentile will all be 0.0. This is because they are all `rank` the same.
    * Additionally, when the spread of scores is small, the percentile will not be able to differentiate between applicants with similar scores.

**Formula used to calculate the summary statistics:**
* **mean** is calculated by using the formula `sum of all scores with that tag / number of applicants with that tag`
* **median** is calculated by using the formula `middle score of all scores with that tag`
* **minimum** is calculated by using the formula `lowest score of all scores with that tag`
* **maximum** is calculated by using the formula `highest score of all scores with that tag`
* **percentile** is calculated by using the formula `number of applicants with a score strictly lower than the applicant / total number of applicants with that tag`

[Jump back to Table of Contents](#table-of-contents)

### Saving the data

JABPro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

JABPro data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, JABPro will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

[Jump back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: What would happen if I used JABPro for the first time without the sample data? Would the app break?
**A**: Not necessarily, we've tested out the JABPro launch without any sample data and basic functionalities are working fine. However, 
we do not recommend this because there could be unexpected behaviours that we might have not accounted for. It is always advisable to follow
the [quickstart instructions](#quick-start) and launch JABPro with the sample data!

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous JABPro home folder.

[Jump back to Table of Contents](#table-of-contents)


<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                   | Format, Examples                                                                                                                                                                                |
|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                  | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/[CATEGORY] TAGNAME]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/developer t/intern`            |
| **Remark**               | `remark r/REMARK` <br> e.g., `remark 1 r/Great attitude, hardworking`                                                                                                                           |
| **Add LinkedIn/Github**  | `addL INDEX u/USERNAME` or `addG INDEX u/USERNAME` e.g., `addL 1 u/alex-yeoh`, `addG 2 u/bernicesanders123`                                                                                     |
| **Open LinkedIn/Github** | `linkedin INDEX` or `github INDEX` e.g., `linkedin 1`, `github 2`                                                                                                                               |
| **Set**                  | `set INDEX STATUS`<br> e.g., `set 2 Interviewed`                                                                                                                                                |
| **View**                 | `view INDEX` <br> e.g., `view 1`                                                                                                                                                                |
| **Edit**                 | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAGNAME]​ [sc/TAGNAME SCORE]`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com t\MarketingInterview sc\MarketingInterview 50` |
| **Delete**               | `delete INDEX` or `delete (t/TAGNAME... st/STATUS...)` <br> e.g., `delete 3`, `delete t/intern st/rejected`                                                                                     |
| **Create**               | `create t/CATEGORY NAME…​` <br> e.g. `create t/dept software`                                                                                                                                   |
| **ListT**                | `listT`                                                                                                                                                                                         |
| **Search**               | `search (n/NAME... st/STATUS... t/TAGNAME...)` <br> e.g., `search n/alex`                                                                                                                       |
| **Filter**               | `filter t/TAGNAME met/METRIC val/VALUE` <br> e.g., `filter t/interview met/score val/80`                                                                                                        |
| **List**                 | `list so/ATTRIBUTE` <br> e.g. `list so/name`                                                                                                                                                    |
| **Event**                | `event INDEX d/DESCRIPTION bt/START_TIME et/END_TIME`                                                                                                                                           |
| **Schedule**             | `schedule`                                                                                                                                                                                      |
| **Help**                 | `help`                                                                                                                                                                                          |
| **Export**               | `export`                                                                                                                                                                                        |
| **Clear**                | `clear`                                                                                                                                                                                         |

[Jump back to Table of Contents](#table-of-contents)

## Prefix Summary

| Prefix | Description  | Commands                          | Constraint                                                                                                        |
|--------|--------------|-----------------------------------|-------------------------------------------------------------------------------------------------------------------|
| `n/`   | Name         | Add, Edit, Search                 | Must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...).                              |
| `p/`   | Phone Number | Add, Edit                         | Must contain numbers only and should be at-least 3 digits long.                                                   |
| `e/`   | Email        | Add, Edit                         | Must be the standard email address format (There must be an email-prefix followed by  @ symbol and email domain). |
| `a/`   | Address      | Add, Edit                         | can be any value, including special characters such as `#`, `,` ...                                               |
| `t/`   | Tag          | Add, Edit, Search, Create, Delete | Must be alphanumeric with no spaces. Any details after the space will be ignored.                                 |
| `sc/`  | Score        | Edit                              | Must be a non-negative integer.                                                                                   |
| `st/`  | Status       | Search, Delete, Set               | Must either be `preliminary`, `interviewed`, `offered`, `rejected`.                                               |
| `r/`   | Remark       | Remark                            | can be any value, including special characters such as `#`, `,` ...                                               |
| `u/`   | Username     | Add Github/LinkedIn               | Must be a string value.                                                                                           |
| `met/` | Metric       | Filter                            | Must be either `score`, `percentile`, `mean`, `median`.                                                           |
| `val/` | Value        | Filter                            | Optional only for mean and median. Otherwise, must be a non-negative integer.                                     |
| `so/`  | Sort         | List                              | Must be either `name` or `email`.                                                                                 |
| `d/`   | Description  | Event                             | Must be a string value.                                                                                           |
| `bt/`  | Begin Time   | Event                             | Must be a valid date-time, in the format `yyyy-MM-dd HH:mm`.                                                      |
| `et/`  | End Time     | Event                             | Must be a valid date-time, in the format `yyyy-MM-dd HH:mm`.                                                      |

[Jump back to Table of Contents](#table-of-contents)

## Glossary
| Keyword      | Definition                                                                              |
|--------------|-----------------------------------------------------------------------------------------|
| Alphanumeric | Letters and numbers. Should NOT contain symbols, or whitespaces.                        |
| Index        | The index of the applicant of the currently displayed list in the applicant list panel. |
| Parameter    | Details about the job applicant that will be included in the command.                   |
| Command      | Instructions that `JABPro` will execute.                                                |
| JAR          | Compressed file of `JABPro` that is in the form of a Java Archive.                      |

[Jump back to Table of Contents](#table-of-contents)
