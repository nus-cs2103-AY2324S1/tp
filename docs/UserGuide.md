---
 layout: default.md
 title: "User Guide"
 pageNav: 3
---
<bottom-head>
    <link rel="stylesheet" href="stylesheets/userguide.css">
</bottom-head>

# SEPlendid User Guide

<br />

## About SEPlendid
SEPlendid is a **Course Mapping System** that allows NUS Computing students to seamlessly plan for
their overseas courses, for the highly coveted Student Exchange Programmes (SEP). As a student, you can view and find
course mappings in order to plan for your overseas studies without the hassle of creating complex Excel sheets.
Utilise SEPlendid's **course mapping** function in order to quickly find possible mappings for certain courses you want
to map. Finally, SEPlendid's **note-taking system** will assist you in organising your important information you
will need for planning for your courses.

This User Guide provides a comprehensive documentation on how you can streamline your process in your application for
SEP. It includes:
- **Step-by-step** instructions on how to launch SEPlendid
- SEPlendid's myriad of features and commands
- **Key parameters** to run SEPlendid smoothly

If you are a new user, please head over to  [How to navigate this User Guide](#2-how-to-navigate-this-user-guide) to
start planning for your study
guide!

<br/> 

--------------------------------------------------------------------------------------------------------------------

## Table of Contents

<div class= "toc">

1. [How to navigate this User Guide](#1-how-to-navigate-this-user-guide)
2. [Icons used in this User Guide](#2-icons-used-in-this-user-guide)
3. [Installation](#3-installation)
4. [Quick Reference Guide](#4-quick-reference-guide)
    - 4.1 [Graphical User Interface (GUI)](#4-1-graphical-user-interface-gui)
    - 4.2 [Command format](#4-2-command-format)
    - 4.3 [Try Out Your first command](#4-3-try-out-your-first-command)
5. [Commands](#5-commands)
    - 5.1 [Commands for local courses](#5-1-commands-for-local-courses)
        - 5.1.1 [List all local courses: `localcourse list`](#5-1-1-list-all-local-courses-localcourse-list)
        - 5.1.2 [Add a local course: `localcourse add`](#5-1-2-add-a-local-course-localcourse-add)
        - 5.1.3 [Delete a local course: `localcourse delete`](#5-1-3-delete-a-local-course-localcourse-delete)
        - 5.1.4 [Update a local course: `localcourse update`](#5-1-4-update-a-local-course-localcourse-update)
        - 5.1.5 [Search a local course by attributes: `localcourse search`](#5-1-5-search-a-local-course-by-attributes-localcourse-search)
        - 5.1.6 [Sort a local course by attributes: `localcourse sort`](#5-1-6-sort-local-courses-by-attributes-localcourse-sort)
    - 5.2 [Commands for partner courses](#5-2-commands-for-partner-courses)
        - 5.2.1 [List all partner courses: `partnercourse list`](#5-2-1-list-all-partner-courses-partnercourse-list)
        - 5.2.2 [Add a partner course: `partnercourse add`](#5-2-2-add-a-partner-course-partnercourse-add)
        - 5.2.3 [Delete a partner course: `partnercourse delete`](#5-2-3-delete-a-partner-course-partnercourse-delete)
        - 5.2.4 [Update a partner course: `partnercourse update`](#5-2-4-update-a-partner-course-partnercourse-update)
        - 5.2.5 [Search a partner course by attributes: `partnercourse search`](#5-2-5-search-a-partner-course-by-attributes-partnercourse-search)
        - 5.2.6 [Sort a partner course by attributes: `partnercourse sort`](#5-2-6-sort-partner-courses-by-attributes-partnercourse-sort)
    - 5.3 [Commands for universities](#5-3-commands-for-universities)
        - 5.3.1 [List all universities: `university list`](#5-3-1-list-all-universities-university-list)
        - 5.3.2 [Search a university by attributes: `university search`](#5-3-2-search-a-university-by-attributes-university-search)
        - 5.3.3 [Sort a university by attributes: `university sort`](#5-3-3-sort-universities-by-attributes-university-sort)
    - 5.4 [Commands for mappings](#5-4-commands-for-mappings)
        - 5.4.1 [List all mappings: `mapping list`](#5-4-1-list-all-mappings-mapping-list)
        - 5.4.2 [Add a mapping: `mapping add`](#5-4-2-add-a-mapping-mapping-add)
        - 5.4.3 [Delete a mapping: `mapping delete`](#5-4-3-delete-a-mapping-mapping-delete)
        - 5.4.4 [Search a mapping by attributes: `mapping search`](#5-4-4-search-a-mapping-by-attributes-mapping-search)
        - 5.4.5 [Sort a mapping by attributes: `mapping sort`](#5-4-5-sort-a-mapping-by-attributes-mapping-sort)
    - 5.5 [Commands for notes](#5-5-commands-for-notes)
        - 5.5.1 [List all notes: `note list`](#5-5-1-list-all-notes-note-list)
        - 5.5.2 [Add a note: `note add`](#5-5-2-add-a-note-note-add)
        - 5.5.3 [Delete a note: `note delete`](#5-5-3-delete-a-note-note-delete)
        - 5.5.4 [Update a note: `note update`](#5-5-4-update-a-note-note-update)
        - 5.5.5 [Tag a note: `note tag`](#5-5-5-tag-a-note-note-tag)
        - 5.5.6 [Clear tag a note: `note cleartag`](#5-5-6-clear-tags-of-a-note-note-cleartag)
        - 5.5.7 [Search notes by tag: `note search`](#5-5-7-search-notes-by-tag-note-search)
    - 5.6 [View help: `help`](#5-6-view-help-help)
    - 5.7 [Exit SEPlendid: `exit`](#5-7-exit-seplendid-exit)
    - 5.8 [Save the data](#5-8-save-the-data)
6. [FAQ](#6-faq)
7. [Command summary](#7-command-summary)

</div>

--------------------------------------------------------------------------------------------------------------------

## 1. How to navigate this User Guide

As a new user in SEPlendid, this user guide serves as an easy-to-follow guide in aiding you in executing your first 
commands in SEPlendid, utilising SEPlendid to it's fullest potential!

Here is a step-by-step instruction in navigating this user guide for **new** users:
1. Download SEPlendid by heading over to the [installation](#3-installation) section which provides a guided tour for you to get
   started with SEPlendid.
2. Get familiar with SEPlendid by heading over to the [Quick Reference Guide](#4-quick-reference-guide) section which shows you how to use 
navigate SEPlendid efficiently.

If you are an **experienced** user, you can head over to the [Command Summary](#7-command-summary) section for a well-curated overview
of the commands available in SEPlendid.

<br>
<br>

## 2. Icons used in this User Guide

Throughout this guide, icons are used to highlight important information, so do pay **special** attention to them:

| Icon                       | Meaning                                  |
|----------------------------|------------------------------------------|
| :information_source: Info  | Information you should keep in mind      | 
| :bulb: Tip                 | Information you might find useful        |
| :exclamation: Warning      | Information you should be cautious about | 

<br>
<br>

## 3. Installation

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `seplendid.jar` from [here](https://github.com/AY2324S1-CS2103T-W10-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for SEPlendid.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar seplendid.jar`
   command to run the application.<br>
   You would be able to view SEPlendid's GUI shown below in a few seconds. The app contains a large sample data of 
   courses so that you can start planning for SEP immediately.<br>
    <div class="centered-container">
      <img src="images/StartupWindowUI.png" alt="UI" class="resized-image">
    </div>
    <div class = "centered-content" >
      <p class = "image-caption"> Figure 1.1 Shows the SEPlendid's GUI upon opening.
    </div>

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

    * `university list` : Lists all of NUS' partner universities.

    * `mapping search [localcode] [CS2103]`: Searches and displays all NUS-to-partner university course mappings
      based on local NUS course code `CS2103`.

    * `exit` : Exits SEPlendid.

6. If you are an experienced user, refer to the [Commands](#4-commands) below for more details of each feature and 
   command.

--------------------------------------------------------------------------------------------------------------------

## 4. Quick Reference Guide

This section covers important information for you to utilise SEPlendid to its fullest capacity. You will learn how to 
navigate SEPlendid effectively and the commands section will cover on how you can run essential features on SEPlendid.

### 4.1 Graphical User Interface (GUI)

SEPlendid's GUI is designed to provide you with great visual feedback and user experience. SEPlendid's GUI allows you to
click and view the different courses which provides an in-depth insight about the course such as the course units and
description. Let's now take a look at the different components available in SEPlendid's GUI.

SEPlendid's GUI consists of these four main components:
1. Command Input Box
2. Command Result Box
3. List Panel for Local Courses, Partner Courses, Universities, Mappings or Notes
4. Display Panel for details of the selected item from the list panel.

Refer to the annotated diagram of SEPlendid's GUI which is numbered accordingly:

<div class="centered-container">
  <img src="images/AnnotatedUi.png" alt="Annotated GUI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 1.2 This shows SEPlendid's Starting GUI.
</div>

<br>

| Number | Component          | Meaning                                                                                                              |
|--------|--------------------|----------------------------------------------------------------------------------------------------------------------|
| 1      | Command Input Box  | This box is where you can type your commands you want to execute in SEPlendid.                                       |
| 2      | Command Result Box | This box is where you can view the successfully executed messages or error messages to guide you in using SEPlendid. | 
| 3      | List Panel         | This panel provides you with an overview of the sample data depending on the datatype.                               |
| 4      | Display Panel      | This panel provides you with an in-depth insight of the selected from the list panel.                                |

<br>
<br>

### 4.2 Command Format

We will be using SEPlendid's commands throughout this User Guide. This section will share with you more about 
SEPlendid's commands:
<br>

<box type="info" icon=":information_source:" header="#### Notes about SEPlendid's command format" icon-size="2x">

In SEPlendid, there are five data types: `localcourse`, `partnercourse`, `university`, `mapping` and `note`.
Each 'Command Word' begins a command, which can be coupled with an attribute, to narrow down to a specific 
functionality.
  <box type="tip" icon=":bulb:" header="You may find this annotated image on SEPlendis's command format useful!" >
      <img src="images/Command.png" alt="Command UI" class="resized-image">
  </box>
     
  <box type="tip" icon=":bulb:" header="Command words available in SEPlendid" >

| Command Word    | Description                                        |
|-----------------|----------------------------------------------------|
| `localcourse`   | Courses offered by NUS Computing                   |
| `partnercourse` | Courses offered by NUS’ partner universities       |
| `mapping`       | Mappings between local courses and partner courses |
| `university`    | NUS’ partner universities                          |
| `note`          | Your own notes                                     |
  </box>  
  <box type="tip" icon=":bulb:" header="Attributes for local course" >

| Attribute          | Description                          |
|--------------------|--------------------------------------|
| `localcode`        | Course code for the local course     |
| `localname`        | Name of the local course             |
| `localunit`        | Number of units  of the local course |
| `localdescription` | Description of the local course      |
  </box> 
  <box type="tip" icon=":bulb:" header="Attributes for partner course" >

| Attribute            | Description                                        |
|----------------------|----------------------------------------------------|
| `partnercode`        | Course code for the partner course                 |
| `partnername`        | Name of the partner course                         |
| `university`         | The partner university offering the partner course |
| `partnerunit`        | Number of units  of the partner course             |
| `partnerdescription` | Description of the partner course                  |
  </box>
  <box type="tip" icon=":bulb:" header="Attributes for mapping" >

| Attribute     | Description                                        |
|---------------|----------------------------------------------------|
| `localcode`   | Course code for the local course                   |
| `localname`   | Name of the local course                           |
| `partnercode` | Course code for the partner course                 |
| `partnername` | Name of the partner course                         |
| `university`  | The partner university offering the partner course |
  </box>
<box type="tip" icon=":bulb:" header="Attributes for notes" >

| Attribute | Description         |
|-----------|---------------------|
| `index`   | Index of the note   |
| `content` | Content of the note |
| `tag`     | Tag of the note     |
  </box>
</box>

**Extra Notes on our Command Format:**

* The command format is `command-word action-word [parameters]`. <br /> `action-word`s include `sort`, `search`, `add`,
  `delete`, `update`, `tag`, but not all `action-word`s can be used after each `command-word`. 
* Refer to the [Command Summary](#command-summary)for a quick overview of which `action-word`s can follow a `command-word`.

* `[parameters]` refer to any number of parameters which can follow an `action-word`. For instance, for the
  `localcourse delete` command, the full format is `localcourse delete [localcode]`, which signifies that we
  have a parameter (`localcode`) to fill. <br />

  An invocation of the command is exemplified by:
  > `localcourse delete [localcode]`.

* The following characters do not exist in our datasets and are not accepted in our input: `[` and `]`.

* Extraneous parameters for all commands will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.


### 4.3 Try Out Your first command

In order to get you started with SEPlendid's commands, we should try out a command together!

Let's start with the most basic command `add` command. `add` command allows you to add a local, partner course or note.

One of the available commands for `add` is the command to add a localcourse into your storage.


**Format:** `localcourse add [localcode] [localname] [unit] [localdescription]`

The first word of each command specifies the different core features with its own unique sets of functionalities.
- `localcourse` tells SEPlendid that this command word would execute actions only for local courses
- Attributes such as `localcode` and `localname` shows you what you should place in each portion of the command

<box type="tip" icon=":bulb:" >
    <b>Tip:</b> All the attributes for local course needs to be added.
</box>

Let's imagine this scenario, there is a newly offered course by NUS Computing, CS2105. Interested with this course, you 
want to add this localcourse in SEPlendid.

<br/> 

**So how can you add CS2105 to SEPlendid?**

Firstly, you would need to input this command: \
`localcourse add [CS2105] [Introduction to Computer Networks] [4.0] [Web and Web applications]`

Secondly, you would need to take note of the parameters that are required to be added:
- `localcode`: CS2105
- `localname`: Introduction to Computer Networks
- `localunit`: 4.0 
- `localdescription`: Web and Web applications

Lastly, you should take note of invalid formats:
- `localcourse add [CS2105]`
  The attributes such as local name and units are compulsory.
- `localcourse add CS2105 Introduction to Computer Networks 4.0`
  The brackets ("[ ]") wrapping each attribute is compulsory.
- `localcourse add`
  There is insufficient information on what local course to add.

<br>

<question type="checkbox">

##### Have you conducted these checks before executing commands in SEPlendid?

  <br>
  <q-option correct>
    I know the datatype that would like to query e.g. local course, partner course, university.
  </q-option>
  <q-option correct>
    I know the required parameters for each command.
  </q-option>
  <q-option correct>
    I know "[ ]" are compulsory for wrapping each attribute.
  </q-option>
</question>

Conducting these checks would prevent error messages and result in more efficient querying of results!

Now, you are equipped with the basics to start using SEPlendid!

<br>
<br>

## 5. Commands

This section provides an in-depth overview of each command SEPlendid offers.

Overview of SEPlendid's commands:
- Purpose of the command
- Command format
- Behaviour of the command (for both valid and invalid inputs)
- Examples of valid and invalid inputs

<br>
<br>

### 5.1 Commands for local courses

#### 5.1.1 List all local courses: `localcourse list`

Lists all possible local courses that can be mapped, offered by NUS Computing. This is useful when:

- you wish to retrieve all local courses' information.
- you wish to view  local courses in greater detail.
- you wish to verify that a local course has been added successfully. 
- you wish to verify that a local course has been deleted successfully.
- you wish to verify that a local course has been updated successfully.

**Format**: `localcourse list`
- This results in the displaying of all the available local courses provided by NUS Computing.

Refer to the Figure 5.1.1 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/LocalcourseListUi.png" alt="Localcourse List UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.1.1 This shows the entire list of local courses available in NUS Computing.
</div>

<br>
<br>

#### 5.1.2 Add a local course: `localcourse add`

Adds a local course using attributes such as localcode, localname, unit and description in this order. This is useful
when:
- you are adding a new local course offered by NUS Computing.
- you wish to add a local course that is not preloaded in SEPlendid.

**Format:** `localcourse add [localcode] [localname] [unit] [description]`

**Example**: `localcourse add [CS1234] [ProgrammingFun] [4.0] [fun mod]`
- This adds a local course with the course code 'CS1234' with the local name 'ProgrammingFun' which fulfils '4.0' units 
and has a description of a 'fun mod'.

Refer to the Figure 5.1.2 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/LocalcourseAddUi.png" alt="Localcourse Add UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.1.2 This shows the added local course with the course code: CS1234.
</div>

<br>
<br>

#### 5.1.3 Delete a local course: `localcourse delete`

Deletes local course with course code identified by `localcode`. This is useful when:
- you wish to remove a local course that is no longer offered by NUS Computing.

<box type="info" icon=":exclamation:" icon-color="red">
    Warning: You are unable to delete a local course if it exists in a mapping.
</box>

**Format:** `localcourse delete [localcode]`

**Example:** `localcourse delete [CS1234]`
- This deletes a local course with the course code 'CS1234'.

Refer to the Figure 5.1.3 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/LocalcourseDeleteUi.png" alt="Localcourse Delete UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.1.3 This shows the deleted local course with the course code: CS1234.
</div>

<br>
<br>

#### 5.1.4 Update a local course: `localcourse update`

Updates specified attributes of a local course, with course code identified by `localcode`.
These local course attributes include `localcode`, `localname`, `unit` and `localdescription`. This is useful when:
- you wish to update a localcourse if there are changes made by NUS Computing.

<box type="tip" icon=":bulb:" >
    Tip: You can check the updated localcourse using <code>localcourse list</code>
</box>

**Format:** `localcourse update [localcode] [localcourseattribute] [updatedValue]`

**Example:** `localcourse update [BT1101] [localcode] [BT1102]`
- This updates a local course with the course code 'BT1101' to 'BT1102'.

Refer to the Figure 5.1.4 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/LocalcourseUpdateUi.png" alt="Localcourse Update UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.1.4 This shows the updated local course BT1101 to BT1102.
</div>

<br />
<br />

#### 5.1.5 Search a local course by attributes: `localcourse search`

Searches local courses using specified attributes such as localcode, localname, unit, and localdescription.
This is useful when:
- you wish to find a specific local course you are interested in.
- you wish to find local courses that matches with your credits required.
- you wish to find local courses that matches with the course description you are interested in.

**Format:** `localcourse search [localcode] [keyword]`\
            `localcourse search [localname] [keyword]`

**Example:** `localcourse search [localcode] [BT2101]`
- This searches a local course with the local code 'BT2101'.

Refer to the Figure 5.1.5 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/LocalcourseSearchUi.png" alt="Localcourse Search UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.1.5 This shows the searched local course BT2102.
</div>

<br>
<br>

#### 5.1.6 Sort local courses by attributes: `localcourse sort`
Sorts local courses according to attributes such as `localname` and `localcode`. This is useful when:
- you wish to view local courses sorted alphabetically.
- you wish to view local courses in order.

**Format:** `localcourse sort [localcourseattribute]` 

**Example:** `localcourse sort [localname]`
- This sorts the local courses alphabetically according to its local name.

Refer to the Figure 5.1.6 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/LocalcourseSortUi.png" alt="Localcourse Sort UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.1.6 This shows the sorted local courses by localname.
</div>

<br>
<br>

### 5.2 Commands for partner courses

<br>

#### 5.2.1 List all partner courses: `partnercourse list`

Lists all available partner courses, offered by every partner university. This is useful when:
- you wish to retrieve all partner courses' information.
- you wish to view  partner courses in greater detail.
- you wish to verify that a partner course has been added successfully.
- you wish to verify that a partner course has been deleted successfully.
- you wish to verify that a partner course has been updated successfully.

**Format:** `partnercourse list`

**Expected Outcome:** SEPlendid's GUI will show the list of partner courses available.

<br>
<br>

#### 5.2.2 Add a partner course: `partnercourse add`

Adds a partner course with the specified partner course attributes. This is useful when:
- you are adding a new partner course offered by the particular partner university.
- you wish to add a partner course that is not preloaded in SEPlendid.

<box type="warning" icon=":exclamation:" icon-color="red">
    <b>Warning:</b> You are unable to add a partner course if the university does not exist in SEPlendid.
</box>

**Format**: `partnercourse add [university] [partnercode] [partnername] [unit] [description]`

**Example:** `partnercourse add [University of Toronto] [ROB311] [Artificial Intelligence] [5.0] 
[Introduction module to AI]`
- This adds a partner course with the course code 'ROB311' with the partner name 'Artificial Intelligence' which 
  fulfils '5.0' units and has a description of a 'Introduction module to AI' in the 'University of Toronto'.

**Expected Outcome:** SEPlendid's GUI will show you the added partner course.

<br>
<br>

#### 5.2.3 Delete a partner course: `partnercourse delete`

Deletes partner course with attributes such as university and partnercode respectively. This is useful when:
- you wish to remove a partner course that is no longer offered by the partner university.

<box type="warning" icon=":exclamation:" >
    <b>Warning:</b> You are unable to delete a partner course if it exists in a mapping.
</box>

**Format:** `partnercourse delete [university] [partnercode]`

**Example:** `partnercourse delete [University of Toronto] [ROB311]`
- This deletes a partner course with the course code 'ROB311' in the 'University of Toronto'.

**Expected Outcome:** SEPlendid's GUI will show the deleted partner course. 

<br>
<br>

#### 5.2.4 Update a partner course: `partnercourse update`

Updates specified attributes of a partner course, with the partner course identified by 
`universityname` and `partnercode`.
These partner course attributes include partnercode, partnername, unit and description. This is useful when:
- you wish to update a partner course if there are changes made by the partner university.

**Format**: `partnercourse update [universityname] [partnercode] [partnercourseattribute] [updatedValue]`

**Example:** `partnercourse update [University of Toronto] [ROB311] [unit] [10.0]`
- This updates a partner course's units to '10.0' units with the course code 'ROB311' in the 'University of Toronto'.

**Expected Outcome:** SEPlendid's GUI will show the updated partner course.

<br>
<br>

#### 5.2.5 Search a partner course by attributes: `partnercourse search`

Searches partner course using specified attributes such as partnercode, partnername, unit, and description.
This is useful when:
- you wish to find a specific partner course you are interested in.
- you wish to find partner courses that matches with your credits required.
- you wish to find partner courses that matches with the course description you are interested in.

**Format:** `partnercourse search [partnercode] [keyword]` \
`partnercourse search [partnername] [keyword]`

**Example:** `partnercourse search [partnercode] [CSE469]` 
- This searches a partner course with the partner code 'CSE469'.

**Expected Outcome:** SEPlendid's GUI will show you the searched partner course, CSE469.

<br>
<br>

#### 5.2.6 Sort partner courses by attributes: `partnercourse sort`
Sorts partner courses according to attributes such as partnername, partnercode and university. This is useful when:
- you wish to view partner courses alphabetically.
- you wish to view partner courses in order.

**Format:** `partnercourse sort [partnercourseattribute]`

**Example:** `partnercourse sort [partnername]`
- This sorts the partner courses alphabetically according to its partner name.

**Expected Outcome:** SEPlendid's GUI will show you the sorted partner courses according to partnercode. 

<br>
<br>

### 5.3 Commands for universities
<br>

#### 5.3.1 List all universities: `university list`

Lists all available partner universities that NUS Computing students are able to exchange at. This is useful when:
- you wish to retrieve all the universities available for you to exchange at.

**Format:** `university list`

**Example:**

Refer to the Figure 5.3.1 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/UniversityListUi.png" alt="University List UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.3.1 This shows the list of partner universities available for NUS Computing students.
</div>

<br>
<br>

#### 5.3.2 Search a university by attributes: `university search`

Searches universities that matches the keyword of the university name. This is useful when:
- you wish to find a specific university you are interested in.

**Format:** `university search [universityKeywords]`

**Example:** `university search [College]`
- This searches the universities with the name 'College'.

Refer to the Figure 5.3.2 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/UniversitySearchUi.png" alt="University Search UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.3.2 This shows the searched university, Imperial College of London.
</div>

<br>
<br>

#### 5.3.3 Sort universities by attributes: `university sort`

Sorts universities by the university name, alphabetically. This is useful when:
- you wish to view universities alphabetically.
- you wish to view universities in order.

**Format:** `university sort [universityname]`

**Example:** `university sort [universityname]` 
- This sorts the universities alphabetically according to its university name.

Refer to the Figure 5.3.3 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/UniversitySortUi.png" alt="University Sort UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.3.3 This shows the sorted university list.
</div>

<br>
<br>

### 5.4 Commands for mappings
<br />

#### 5.4.1 List all mappings: `mapping list`

Lists all available mappings from a local course offered by NUS Computing, to a partner course offered by a partner
university. This is useful when:

* you wish to retrieve all mappings' information.
* you wish to view mappings in greater detail.
* you wish to verify that a mapping has been added, deleted or updated successfully.

<box type="tip" icon=":bulb:">
    Click on a mapping in the list to bring up a detailed view.
</box>

**Format:** `mapping list`

- This results in the displaying of all available mappings stored in SEPlendid.

Refer to the figure below to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/MappingListUi.png" alt="Mapping List UI" class="resized-image">
</div>
<div class="centered-content" >
  <p class="image-caption"> Figure 3.1 Shows the entire list of mappings stored in SEPlendid.
</div>

<br />
<br />

#### 5.4.2 Add a mapping: `mapping add`

Adds mapping for local course identified by `localcode`, offered by partner `university`, has partner course with code
`partnercode` and has information `information`. This is useful when:

* you wish to add a mapping between a local course and a partner course.

<box type="info">
    You are only able to add a mapping consisting of existing local course, partner course and university.
</box>

**Format:** `mapping add  [localcode] [university] [partnercode] [information]`

**Example:** `mapping add [IS4231] [Lund University] [INFC40] [Sem 1 only.]`

- This adds a mapping of local course `IS4231` to `INFC40` offered by `Lund University`.

Refer to the figure below to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/MappingAddUi.png" alt="Mapping Add UI" class="resized-image">
</div>
<div class="centered-content" >
  <p class="image-caption"> Figure 3.2 Shows the added mapping of local course IS4231 to INFC40 offered by Lund University.
</div>

<br />
<br />

#### 5.4.3 Delete a mapping: `mapping delete`

Deletes mapping for local course identified by `localcode`, offered by partner `university`, and has partner course with
code `partnercode`. This is useful when:

* you wish to remove a mapping between a local course and a partner course.

**Format:** `mapping delete [localcode] [university] [partnercode]`

**Example:** `mapping delete [IS4231] [Lund University] [INFC40]`

- This deletes a mapping of local course `IS4231` to `INFC40` offered by `Lund University`.

Refer to the figure below to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/MappingDeleteUi.png" alt="Mapping Dekete UI" class="resized-image">
</div>
<div class="centered-content" >
  <p class="image-caption"> Figure 3.3 Shows the deletion of mapping of local course IS4231 to INFC40 offered by Lund 
  University.
</div>

<br />
<br />

#### 5.4.4 Search a mapping by attributes: `mapping search`

Searches for mappings using specified attribute such as `localcode`, `localname`, `partnercode`, `partnername`,
`university`, `information`. This is useful when:

* you wish to find a mapping which you are interested in, or have added.

**Format:** `mapping search [localcode/localname/partnercode/partnername/university/information] [query]`

**Example:** `mapping search [localcode] [CS3230]`

- This results in the displaying of all mappings of the local course `CS3230`.

Refer to the figure below to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/MappingSearchUi.png" alt="Mapping Search UI" class="resized-image">
</div>
<div class="centered-content" >
  <p class="image-caption"> Figure 3.4 This shows the search for mappings of local course CS3230.
</div>

<br />
<br />

#### 5.4.5 Sort a mapping by attributes: `mapping sort`

Sorts mappings according to attributes such as `localcode`, `localname`, `partnercode`, `partnername`, `university`,
`information`) in ascending order. This is useful when:

* you wish to view or find mappings alphabetically

**Format:**  `mapping sort [localcode/localname/partnercode/partnername/university/information]`

**Example:**
`mapping sort [localcode]`

- This results in the displaying of all mappings, sorted by their local course codes in ascending order.

Refer to the figure below to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/MappingSortUi.png" alt="Mapping Sort UI" class="resized-image">
</div>
<div class="centered-content" >
  <p class="image-caption"> Figure 5.4.5 This shows the sorting of mappings by their local course codes in ascending order.
</div>

<br />
<br />

### 5.5 Commands for notes
<br />

#### 5.5.1 List all notes: `note list`

Lists all notes that you have recorded in SEPlendid. This is useful when:
- you wish to retrieve all of your consolidated notes.
- you wish to verify that your note has been added successfully.
- you wish to verify that your note has been deleted successfully.
- you wish to verify that your note has been updated successfully.

<box type="tip" icon=":bulb:" >
    <b>Tip:</b> Click on a note in the list to bring up a detailed view.
</box>


**Format:** `note list`
* This results in the displaying of all notes, sorted by their index number in ascending order. 

Refer to Figure 5.5.1 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteListUi.png" alt="Note List UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.1 This shows the list of notes that you have.
</div>

<br />
<br />

#### 5.5.2 Add a note: `note add`

Adds a note with the content you wish to add and the tag you wish your note to have. This is useful when:
- you wish to record a course or mapping you would are interested in doing for exchange.
- you wish to record down important information.

**Format:** `note add [content] [tag]`


<box type="info" icon=":information_source:">
Tag must only be a single word with no whitespaces and only contain alphabets and numbers. You can only add 1 tag
for your note with this command. 
If you wish to have more tags for your note, you can do so with the command <code>note tag</code>.
You cannot have two notes of the same content.
</box>

**Example:** `note add [You can do this!] [motivation]`
- This adds a note with a content of "You can do this!" and tags the note with "motivation". 

Refer to Figure 5.5.2 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteAddUi.png" alt="Note Add UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.2 This shows the note you have added.
</div>

<br />
<br />

#### 5.5.3 Delete a note: `note delete`

Deletes specified index of the note. This is useful when:
- you wish to remove a note that contains outdated information.

**Format:** `note delete [index]`

<box type="warning" icon=":exclamation:" icon-color="red">
    <b>Warning:</b> This action is irreversible! Be careful when you want to delete your note. 
</box>

**Example:** `note delete [3]`
- This deletes the note with index 3.

Refer to Figure 5.5.3 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteDeleteUi.png" alt="Note Delete UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.3 This shows the note you have deleted and the updated note list.
</div>

<br />
<br />

#### 5.5.4 Update a note: `note update`

Updates specified note according to the index and updates the content of the note. This is useful when:
- you wish to edit to correct your mistakes made in the note.
- you wish to update the note with new information.

**Format:** `note update [index] [content]`

<box type="warning" icon=":exclamation:" icon-color="red">
    <b>Warning:</b> This command will overwrite your old content with the new content you specified. 
</box>

**Example:** `note update [1] [Exchange Application Deadline: 25 September 2023]`
- This updates the content of the note with index 1 to "Exchange Application Deadline: 25 September 2023". 

Refer to Figure 5.5.4 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteUpdateUi.png" alt="Note Update UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.4 Shows the note you updated and the updated note list.
</div>

<br>
<br>

#### 5.5.5 Tag a note: `note tag`

Adds a tag to the specified note according to the index. This is useful when:
- you wish to group your notes with specific words.
- you wish to easily view the different key points of the note.

**Format:** `note tag [index] [tag]`

<box type="warning" icon=":exclamation:" icon-color="red">
    <b>Warning:</b> Duplicate tags are not allowed. 
</box>

**Example:** `note tag [1] [important]`
- This adds "important" tag to the note with index 1. 

Refer to Figure 5.5.5 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteTagUi.png" alt="Note Tag UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.5 This shows the notes updated with a tag you have specified.
</div>

<br>
<br>

#### 5.5.6 Clear tags of a note: `note cleartag`

Clears all tags for the specified note according to its index. This is useful when:
- you wish to remove all the tags that are no longer relevant to your note.

**Format:** `note cleartag [index]`
<box type="warning" icon=":exclamation:" icon-color="red">
<b>Warning:</b> This command will remove all tags for your specified note. 
</box>


**Example:** `note cleartag [1]`
- This removes all the tags for the note with index 1.

Refer to Figure 5.5.6 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteClearTagUi.png" alt="Note Tag UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.6 This shows the note where all the tags have been removed.
</div>

<br>
<br>

#### 5.5.7 Search notes by tag: `note search`

Searches for notes which tag contains the value `tagKeyword`. This is useful when:
- you wish to filter your notes with the tags you are interested in. 

**Format:** `note search [tagKeyword]`

<box type="info" icon=":information_source:">
<code>tagKeyword</code> is case-insensitive.
</box>

<box type="warning" icon=":exclamation:" icon-color="red">
<b>Warning:</b> Search note tag cannot have white spaces. 
</box>

**Example:** `note search [plans]`
- This searches all notes with the tag that contains the word "plans".

Refer to Figure 5.5.7 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/NoteSearchUi.png" alt="Note Search UI" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.5.7 This shows the note with the tags that contains the tagKeyword.
</div>

<br>
<br>

### 5.6 View help: `help`

Results in a pop-up window for you to copy the URL into an external browser to view SEPlendid's user guide.

Format: `help`

Refer to Figure 5.6 to view the outcome of the execution of the command:

<div class="centered-container">
  <img src="images/Help.jpg" alt="Help" class="resized-image">
</div>
<div class = "centered-content" >
  <p class = "image-caption"> Figure 5.6 This shows the exit pop up window.
</div>

<br>
<br>

### 5.7 Exit SEPlendid: `exit`

Exits SEPlendid app.

Format: `exit`

<br />
<br />

### 5.8 Save the data

SEPlendid's data are saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.

#### Editing the data file

SEPlendid data are saved automatically as a JSON file `[JAR file location]/data/seplendid.json`. Advanced users are
welcome to update data directly by editing that data file.

<box type="warning" icon=":exclamation:" icon-color="red">
    <b>Warning:</b> If your changes to the data file makes its format invalid, SEPlendid will discard all data and start with an empty data 
file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>


--------------------------------------------------------------------------------------------------------------------

## 6. FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous SEPlendid home folder.

**Q**: Can I run SEPlendid without the need for internet connection? <br>
**A**: No, SEPlendid is designed as an offline application to enhance your convenience of using the app.

**Q**: Why do I get an error message when searching a local course by its description?<br>
**A**: The attributes used for local course search is restricted. To search the local course, you must use this command:
- `localcourse search [localdescription] [description_keyword]`

<br>
<br>

--------------------------------------------------------------------------------------------------------------------
## 7. Command summary

<table class="bordered-table">
    <tr>
        <td><code>command-word</code></td>
        <td><code>action-word</code></td>
        <td><code>parameters</code></td>
    </tr>
    <tr>
        <td rowspan="6"><code>localcourse</code></td>
        <td><code>list</code></td>
        <td> None.</td>
    </tr>
    <tr>
        <td><code>add</code></td>
        <td><code>[localcode] [localname] [unit] [description]</code></td>
    </tr>
    <tr>
        <td><code>delete</code></td>
        <td><code>[localcode]</code></td>
    </tr>
    <tr>
        <td><code>update</code></td>
        <td><code>[localcode] [attribute] [updatedValue]</code></td>
    </tr>
    <tr>
        <td><code>search</code></td>
        <td><code>[localcode] [localname]</code></td>
    </tr>
    <tr>
        <td><code>sort</code></td>
        <td><code>[localcode/localname]</code></td>
    </tr>   
    <tr>
        <td rowspan="6"><code>partnercourse</code></td>
        <td><code>list</code></td>
        <td> None.</td>
    </tr>
    <tr>
        <td><code>add</code></td>
        <td><code>[university] [partnercode] [partnername] [unit] [description]</code></td>
    </tr>
    <tr>
        <td><code>delete</code></td>
        <td><code>[university] [partnercode]</code></td>
    </tr>
    <tr>
        <td><code>update</code></td>
        <td><code>[university] [partnercode] [attribute] [updatedValue]</code></td>
    </tr>
    <tr>
        <td><code>search</code></td>
        <td><code>[partnercode] [partnername]</code></td>
    </tr>
    <tr>
        <td><code>sort</code></td>
        <td><code>[university/partnercode/partnername]</code></td>
    </tr>
    <tr>
        <td rowspan="5"><code>mapping</code></td>
        <td><code>list</code></td>
        <td> None.</td>
    </tr>
    <tr>
        <td><code>add</code></td>
        <td><code>[localcode] [university] [partnercode] [information]</code></td>
    </tr>
    <tr>
        <td><code>delete</code></td>
        <td><code>[localcode] [university] [partnercode]</code></td>
    </tr>
    <tr>
        <td><code>search</code></td>
        <td><code>[localcode/localname/partnercode/partnername/university/information] [query]</code></td>
    </tr>
    <tr>
        <td><code>sort</code></td>
        <td><code>[localcode/localname/partnercode/partnername/university/information]</code></td>
    </tr>
    <tr>
        <td rowspan="3"><code>university</code></td>
        <td><code>list</code></td>
        <td>None.</td>
    </tr>
    <tr>
        <td><code>search</code></td>
        <td><code>[universityname]</code></td>
    </tr>
    <tr>
        <td><code>sort</code></td>
        <td><code>[universityname]</code></td>
    </tr>
    <tr>
        <td rowspan="7"><code>note</code></td>
        <td><code>list</code></td>
        <td>None.</td>
    </tr>
    <tr>
        <td><code>add</code></td>
        <td><code>[content] [tag]</code></td>
    </tr>
    <tr>
        <td><code>delete</code></td>
        <td><code>[index]</code></td>
    </tr>
    <tr>
        <td><code>update</code></td>
        <td><code>[index] [content]</code></td>
    </tr>
    <tr>
        <td><code>tag</code></td>
        <td><code>[index] [tagname]</code></td>
    </tr>
    <tr>
        <td><code>cleartag</code></td>
        <td><code>[index]</code></td>
    </tr>
    <tr>
        <td><code>search</code></td>
        <td><code>[tagKeyword]</code></td>
    </tr>
</table>
