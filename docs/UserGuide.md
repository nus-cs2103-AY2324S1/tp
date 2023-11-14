---
layout: default.md
title: "User Guide"
pageNav: 3
---

# NUSearch User Guide

NUSearch is a **desktop app for consolidating NUS professors, teaching assistants (TAs) and students’ profiles, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface (GUI)**. If you can type fast, NUSearch add and search for your NUS peers and mentors faster than traditional GUI apps.

--- {.dotted .thick-1 .border-primary}
## Motivation
We aim to simplify the process of accessing academic information by developing an efficient directory app. This app will help students to consolidate professors, teaching assistants (TAs) and their fellow classmates’ profile, improving the ease of accessing the details of individuals whom the students might need to contact for that semester.

--- {.dotted .thick-1 .border-primary}
## Unique Selling Point
The app helps students to consolidate important data, such as profiles of professors, teaching assistants (TAs), and fellow classmates, providing students with a single platform that is compact and easy to navigate. With this application, students can save time and energy that would otherwise be spent searching for scattered and hard-to-access essential academic contacts. The app features an intuitive and user-friendly interface, making it convenient for users to quickly find the information they need.

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## Table of Contents
<box>
    <ol>
        <li> <a href="#table-of-contents">Table of Contents</a> </li>
        <li> <a href="#quick-start">Quick Start</a> </li>
        <li> <a href="#features">Features</a> </li>
        <ol>
            <li> <a href="#help-page-help">Help</a> </li>
            <li> <a href="#adding-a-person-add">Add a Person</a> </li>
            <li> <a href="#listing-all-persons-list">List all Persons</a> </li>
            <li> <a href="#adding-persons-to-favourites-fav">Favourite a Person</a> </li>
            <li> <a href="#removing-a-person-from-favourite-unfav">Unfavourite a Person</a> </li>
            <li> <a href="#listing-all-favourite-persons-favlist">List all Favourites</a> </li>
            <li> <a href="#deleting-a-person-delete">Delete a Person</a> </li>
            <li> <a href="#searching-for-persons-by-name-search">Search by Name</a> </li>
            <li> <a href="#searching-for-persons-by-role-searchrole">Search by Role</a> </li>
            <li> <a href="#searching-for-persons-by-course-searchcourse">Search by Course</a> </li>
            <li> <a href="#searching-for-persons-by-tutorial-searchtutorial">Search by Tutorial</a> </li>
            <li> <a href="#clearing-the-person-list-clear">Clear Person List</a> </li>
            <li> <a href="#exiting-the-application-exit">Exit the Application</a> </li>
        </ol>
        <li> <a href="#faq">Frequently Asked Questions</a> </li>
        <li> <a href="#known-issues">Known Issues</a> </li>
        <li> <a href="#command-summary">Command Summary</a> </li>
    </ol>
</box>

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>



## Quick Start

1. Ensure you have Java 11 or above installed on your Computer.
<box type="info" header="**How do I install Java 11?**" dismissible light>
    First, let's check if Java 11 is installed already:
    <tabs>
        <tab header="**Windows**">
            <ol>
                <li> <md> Press Win+R. </md> </li>
                <li> <md> Type `cmd` and press Enter. </md> </li>
                <li> <md> Type `java --version` and press Enter. </md> </li>
                <li> <md> If Java is *not* installed on your computer, a red error message will pop up. In that case, see below on installing Java 11. </md> </li>
                <li> <md> If Java is installed on your computer, some lines of white text will appear. Among these, there should be a line reading `openjdk` followed by a number, which is the version of Java. If you do not have Java 11 or later, see below on installing Java 11. </md> </li>
            </ol>
        </tab>
        <tab header="**Mac**">
            <ol>
                <li> <md> Click the Search button on your device. </md> </li>
                <li> <md> Enter "Terminal" and open the app. </md> </li>
                <li> <md> Type `java --version` and press Enter. </md> </li>
                <li> <md> If Java is *not* installed on your computer, a red error message will pop up. In that case, see below on installing Java 11. </md> </li>
                <li> <md> If Java is installed on your computer, some lines of white text will appear. Among these, there should be a line reading `openjdk` followed by a number, which is the version of Java. If you do not have Java 11 or later, see below on installing Java 11. </md> </li>
            </ol>
        </tab>
        <tab header="**Linux**">
            <ol>
                <li> <md> Open the terminal. </md> </li>
                <li> <md> Enter the command `java --version`. </md> </li>
                <li> <md> Check if any error message appears. If there is, Java is not installed; see below on installing Java 11. </md> </li>
            </ol>
        </tab>
    </tabs>
    If Java 11 is not already installed, don't panic! Follow the instructions <a href="https://www.ibm.com/docs/en/oapi/1.3.6?topic=installation-installing-java-11">here</a> to install Java 11.
</box>

2. Make sure you place this app's JAR file in an empty folder before launching it for the first time.

3. Launch the JAR file by double-clicking it.
<box type="warning" header="**Help! I can't open the JAR file!**" dismissible light>
    If double clicking the JAR file to open it doesn't work, try the following steps:
    <tabs>
        <tab header="**Windows**">
            <ol>
                <li> <md> Right-click on the JAR file in the File Explorer, and click "Properties". </md> </li>
                <li> <md> Copy the entire *file path*, listed under Location in the menu that appears. </md> </li>
                <li> <md> Press Win+R. </md> </li>
                <li> <md> Type `cmd` and press Enter. </md> </li>
                <li> <md> Type `cd` and paste the file path copied in Step 2 by pressing Ctrl+Shift+V. </md> </li>
                <li> <md> Finally, type `java -jar NUSearch.jar` and press Enter. </md> </li>
            </ol>
        </tab>
        <tab header="**Mac**">
            <ol>
                <li> <md> Locate the JAR file, and right click it. </md> </li>
                <li> <md> Click "Get Info". </md> </li>
                <li> <md> Copy the location listed under "Where:". </md> </li>
                <li> <md> Open the Search, enter "Terminal" and open the app. </md> </li>
                <li> <md> Type `cd` and paste the file path copied in Step 3. </md> </li>
                <li> <md> Finally, type `java -jar NUSearch.jar` and press Enter. </md> </li>
            </ol>
        </tab>
        <tab header="**Linux**">
            <ol>
                <li> <md> Open the terminal. </md> </li>
                <li> <md> Enter `cd` and then the path of the directory in which the JAR file resides. </md> </li>
                <li> <md> Enter the command `java -jar NUSearch.jar`. </md> </li>
            </ol>
        </tab>
    </tabs>
</box>

4. The application should launch, resembling the UI shown below.

<img src="./images/Ui.png" width="100%" >

5. Refer to the [Features](#features) below for details of each command.

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>


## Features

### A guide to reading each feature
This section will guide you through how to interpret the description and the command format of each feature. 

##### The description of each feature will contain the following:

###### WHAT IT DOES:
Tells you the basic idea of what the command does.

###### FORMAT:
It specifies how the command should be formatted. You should follow the format specified to ensure that the command gives the desired output.

###### EXAMPLE COMMAND:
Gives you a few examples of how the command can be used for reference.

###### ACCEPTABLE VALUES:
Describes the accepted values used in a command field, specifying any restrictions. Values for the command must satisfy the restrictions for the command to be accepted.

###### EXPECTED OUTPUT ON SUCCESS:
Describes the desired output that you would see when the command is valid.

###### EXPECTED OUTPUT ON FAILURE:
Shows the error messages that will be shown to you if an invalid command is given.

##### How to interpret a command format:

###### COMMAND FORMAT
```
command --specifier INPUTFIELD [--specifier INPUTFIELD1, ...] 
[--specifier INPUTFIELD1/SUBFIELD1, ...]
```
<box type="warning">
    <md>
        Note that a command is case-sensitive; in other words, `add` is different from `ADD` and `Add`; be careful not to mix them up!
    </md>
</box>

###### EXAMPLE COMMAND FORMAT
```
add --name NAME [--role ROLE1, ...]  [--contact CONTACT1, ...] 
[--course COURSECODE1/CLASS1, ...] 
```

| Command Types |          Examples           | What they mean                                                                                              |
|:-------------:|:---------------------------:|-------------------------------------------------------------------------------------------------------------|
|   `command`   |            `add`            | The name of the command. It is in bold in the format.                                                       |
| `--specifier` |          `--name`           | The specifier of the field to indicate the field type.                                                      |
| `INPUTFIELD`  |           `NAME`            | The content of the INPUT FIELD the user wants to input.                                                     |
|     `...`      |       `CONTACT1, ...`       | Ellipses indicate that the field can accept multiple values.                                                |
| ` [ ] ` | `[--contact CONTACT1, ...]` | Square brackets indicate an optional field. The user can input these fields in the command if they want to. |
| `, ` |       `CONTACT1, ...`       | Comma separates the multiple INPUTFIELDs                                                                    |
| `INPUTFIELD/SUBFIELD` |     `COURSECODE/CLASS`      | Slash indicates that this INPUTFIELD can have a SUBFIELD. This SUBFIELD is optional.                        |

###### VALID SPECIFIERS

|  Specifier  | Purpose                                          |
|:-----------:|--------------------------------------------------|
|  `--name`   | The name of the person you are adding            |
|  `--role`   | The role of the person you are adding            |
| `--contact` | The contact details of the person you are adding |
| `--course`  | The course the person is taking                  |

<box type="info">
    <md>
        While no space is required between the specifier and the following field (in other words, `--courseCS2100` is equivalent to `--course CS2100`), it is recommended that a space be added for readability.
    </md>
</box>


<div style="page-break-after: always;"></div>

### Help page: `help`

Show the help page of the application

###### FORMAT:
`help`

###### EXAMPLE COMMAND:
`help`

###### ACCEPTABLE VALUES:
Command accepts parameters after the keyword `help`, i.e. `help im dying` but they will be ignored 
and the `help` command will still be executed.

###### EXPECTED OUTPUT ON SUCCESS:

```
Quick Guide: 
Adding a person: add --name NAME [--role ROLE1, ...]  
[--contact CONTACT1, ...] [--course COURSECODE1/CLASS1, ...]
Listing all persons: list
Deleting a person: delete INDEX
Search by name: search NAME
Search by role: searchrole ROLE
Search by course: searchcourse COURSECODE
Search by tutorial class: searchtutorial TUTORIAL
Adding persons to favourites: fav INDEX
Removing persons from favourites: unfav INDEX
Display all favourites: favlist
Clear all data: clear
Exit the application: exit
Refer to the User Guide for the detailed implementation.
```
A help window will pop out as shown: 

<img src="./images/HelpWindow.png" width="100%" >

###### EXPECTED OUTPUT ON FAILURE:

This command only recognises `help` as the keyword.

Any other command word such as `h`, `he` and `hel` will be seen as an invalid command with the following output:

`Unknown command`


<div style="page-break-after: always;"></div>

### Adding a person: `add`

Adds new persons in the person lists.

###### FORMAT:
`add --name NAME [--role ROLE1, ...]  [--contact CONTACT1, ...] [--course COURSECODE1/CLASS1, ...] `

<box type="tip" dismissible light>

**Tip:**


- The input for name is **case-sensitive** (i.e. `Aiken`, `AIKEN`, `AiKeN` and `aiken`
will be recognised as different inputs).

- Duplicate names (with same case) are not allowed.

- The input for role is **case-sensitive**.

- Contacts can be any type of contact: email address, telegram handle, phone number, etc.

- Courses can be any of the courses offered by NUS.

- Inputs for course are **case-sensitive** (i.e. `CS2100`, `cs2100` and `Cs2100`
will be recognised as different courses).

- Courses can be added without the tutorial class but tutorial class must be added with a course
  (see Example 2 below for more details).

- Multiple tutorial classes for the same course are to be added separately (i.e. To add T12 and Lab30 class for CS2100,
it has to be added like this: `CS2100/T12, CS2100/Lab30`).

- Input for tutorial is **case-sensitive** (i.e. `CS2100/T21 and CS2100/t21 will be recognised as different tutorials).

- Please use a comma (`,`) to separate the different roles, contacts and courses.

- The square brackets, (`[ ]`), are not needed when entering optional fields [see examples below for more details].
</box>

<box type="important" light>

**Important:**

- Specifiers must be preceded by a space.
- Invalid specifiers will NOT be recognised. It will be treated as an input for the previous specifier (if any), or it will be treated as an invalid add command format if there is no previous specifier. [See Examples 7 & 8 below for more details.]
    </md>
</box>

###### EXAMPLE COMMAND:

Example 1:

`add --name Aiken Dueet --role Student --contact @aikendueet, aikendueet@gmail.com
--course CS2103T/Tut8, CS2100/Lab40`


Example 2:

`add --name Charlie Dueet --role TA, Student --contact @charliee, charliee@gmail.com
--course GEA1000, QF2103`

Example 3:

`add --name Daycon Dueet`

###### ACCEPTABLE VALUES:
`NAME`: Any non-empty input of alphabetical characters.

`ROLE1`: Any three roles allowed here: Student, TA, Professor

`CONTACT1`: Any non-empty input of characters.

`COURSECODE1`: Starts with two or three letter prefix, follows by four digit, can end with or without a letter.

`CLASS1`: Any non-empty input of characters.

###### EXPECTED OUTPUT ON SUCCESS:

Example 1:

```
You have added a new person in : 
 Name: Aiken Dueet; Role: Student; Contacts: [@aikendueet], 
 [aikendueet@gmail.com]; Courses: CS2103T, CS2100; 
 Tutorials: CS2103T/Tut8, CS2100/Lab40
```

Example 2:

```
You have added a new person in : 
 Name: Charlie Dueet; Role: Student, TA; 
 Contacts: [@charliee], [charliee@gmail.com]; 
 Courses: GEA1000, QF2103; Tutorials: 
```

Example 3:

```
You have added a new person in :
Name: Daycon Dueet; Role: ; Contact: ; Course: ; Tutorials: 
```

###### EXPECTED OUTPUT ON FAILURE:

**For invalid `add` command:** 

Example 4.1: `add --`

```
Invalid command format! 
add: Adds a person to the address book. 
Parameters: --name NAME [--role ROLE1, ...] [--contact CONTACT1, ...] 
[--course COURSECODE1/CLASS1, ...]
Example: add --name John --role Student, TA --contact john@example.com, 98765432 
--course CS2103T/G06, CS2101/G06, CS2100/T24
```

Example 4.2: `add`
```
Invalid command format! 
Note: Compulsory name input is missing
Unable to add a person without name
```

**For wrong input value:**

Example 5.1: `add --name`

```
Names should only contain alphanumeric characters and spaces, 
and it should not be blank
```

Example 5.2: `add --name Charlie --role teacher`

```
A role must take one of the roleTypes: Student, TA, or Professor.
```

Example 5.3: `add --name Charlie --role TA --course CS21111`

```
INVALID COURSE FORMAT!
COURSE CODE SHOULD BE IN THE FOLLOWING FORMAT: 
 1. Starts with two- or three-letter prefix
 2. Follows by four digits, first of which indicates the level of the course
 3. Can end with a letter
 ```

Example 5.4: `add --name Charlie --role TA --course CS2100/         F09`

`Tutorials should be written in the format COURSECODE/TUTORIAL`

**For duplicate name:**

Example 6: `add --name Alex Yeoh` [Assuming Alex Yeoh already exists in the list] 

```
Note: A person with the same name already exists.
Please edit the existing person or change the name of this person to be added
```

**For invalid specifier:**

Example 7: `add --name alex yeoh -/-role TA` 

```
Names should only contain alphanumeric characters and spaces, 
and it should not be blank
```

Example 8: `add -/-name alex yeoh`

```
Invalid command format! 
add: Adds a person to the address book. 
Parameters: --name NAME [--role ROLE1, ...] [--contact CONTACT1, ...] 
[--course COURSECODE1/CLASS1, ...]
Example: add --name John --role Student, TA --contact john@example.com, 98765432 
--course CS2103T/G06, CS2101/G06, CS2100/T24
```

<div style="page-break-after: always;"></div>

### Listing all persons : `list`

List all the persons added by the user.

###### FORMAT:
`list`

###### EXAMPLE COMMAND:
`list`

###### ACCEPTABLE VALUES:
Command accepts parameters after the keyword `list`, i.e. `list everything` but they will be ignored 
and the `list` command will still be executed.

###### EXPECTED OUTPUT ON SUCCESS:
```

You have 2 persons in your list: 
1. Name: Aiken Dueet  
Role: STUDENT
Contact: @aikendueet, aikendueet@gmail.com
Course: CS2103T, CS2101, CS2100
Tutorials: CS2103T/Tut8 , CS2101/G06, CS2100/Lab40

2. Name: Eren Yeager
Role: TA
Contact: @ErenYeager@gmail.com
Course: CS1101S
Tutorials: CS1101S/Tut8

```

###### EXPECTED OUTPUT ON FAILURE:
This command only recognises `list` as the keyword. 

Any other command word such as `l`, `li` and `lis` will be seen as an invalid command with the following output:


<div style="page-break-after: always;"></div>

### Adding persons to favourites: `fav`

Favourite the persons in the user’s current person list.

###### FORMAT:
`fav INDEX`

###### EXAMPLE COMMAND:
`fav 2`

###### ACCEPTABLE VALUES:
`INDEX`: Any number representing a positive integer (i.e. 1, 2, 3, …),
less than or equal to the number of persons the user currently has.
The maximum `INDEX` allowed is `2147483647`.

> 📝Note:
>
> INDEX refers to the index of the person allocated to the specific person in the current person list.

###### EXPECTED OUTPUT ON SUCCESS:
```
Favourited Person: Name: Alex Yeoh; Role: Student; 
Contacts: [alexyeoh@example.com]; Courses: CS1101; Tutorials: CS1101/T03E
```

###### EXPECTED OUTPUT ON FAILURE:
**For invalid index:**

Example `fav -1`

```
Invalid command format! 
fav: Favourites the person identified by the index number used in the 
displayed person list. 
Parameters: INDEX (must be a positive integer)
Example: fav 1
 ```

**For index out of bound:**

Example: `fav 100` [Assuming the address book currently contains 10 persons]

`The person index provided is invalid`

<div style="page-break-after: always;"></div>

### Removing a person from favourite: `unfav`

Un-favourite a favourite person

###### FORMAT:
`unfav INDEX`

###### EXAMPLE COMMAND:
`unfav 2`

###### ACCEPTABLE VALUES:
`INDEX`: Any number representing a positive integer (i.e. 1, 2, 3, …),
less than or equal to the number of persons the user currently has.
The maximum `INDEX` allowed is `2147483647`.

> 📝Note:
>
> INDEX refers to the index of the person allocated to the specific person in the current person list.

###### EXPECTED OUTPUT ON SUCCESS:
```
Unfavourited Person: Name: Alex Yeoh; Role: Student; 
Contacts: [alexyeoh@example.com]; Courses: CS1101; Tutorials: CS1101/T03E
```

###### EXPECTED OUTPUT ON FAILURE:
**For invalid index:**

Example `unfav -1`

```
Invalid command format! 
unfav: Unfavourites the person identified by the index number used in the 
displayed person list. 
Parameters: INDEX (must be a positive integer)
Example: unfav 1
 ```

**For index out of bound:**

Example: `unfav 100` [Assuming the address book currently contains 10 persons]

`The person index provided is invalid`


<div style="page-break-after: always;"></div>

### Listing all favourite persons : `favlist`

List all the persons favourited by the user.

###### FORMAT:
`favlist`

###### EXAMPLE COMMAND:
`favlist`

###### ACCEPTABLE VALUES:
Command accepts parameters after the keyword `favlist`, i.e. `favlist hehe` but they will be ignored 
and the `favlist` command will still be executed.

###### EXPECTED OUTPUT ON SUCCESS:
*(if user has only favourited 1 person)*
```
You have 1 favourited person in your list. 
Name: Aiken Dueet  
Role: STUDENT
Contact: [[@aikendueet], [aikendueet@gmail.com]]
Course: CS2103T, CS2101, CS2100
Tutorials: CS2103T/Tut8 , CS2101/G06, CS2100/Lab40
```

###### EXPECTED OUTPUT ON FAILURE:
This command only recognises `favlist` as the keyword.

Any other command word such as `favl`, `favli` and `favlis` will be seen as an invalid command with the following output:

`Unknown command`


<div style="page-break-after: always;"></div>

### Deleting a person : `delete`

Delete the specific person based on the index allocated to the person.

###### FORMAT:
`delete INDEX`

###### EXAMPLE COMMAND:
`delete 1`

###### ACCEPTABLE VALUES:
`INDEX`: Any number representing a positive integer (i.e. 1, 2, 3, …),
less than or equal to the number of persons the user currently has.
The maximum `INDEX` allowed is `2147483647`.

> 📝Note:
>
> INDEX refers to the index of the person allocated to the specific person in the current person list.

###### EXPECTED OUTPUT ON SUCCESS:
```
Deleted person: Deleted Person: Name: Aiken Dueet; Role: Student; 
Contacts: [@aikendueet], [aikendueet@gmail.com]; 
Courses: CS2103T; Tutorials: CS2103T/Tut8
```

###### EXPECTED OUTPUT ON FAILURE:
**For invalid index:**

Example: `delete -1`

```
Invalid command format! 
delete: Deletes the person identified by the index number used in the 
displayed person list.
Parameters: INDEX (must be a positive integer)
Example: delete 1
```

**For out of bound index:**

Example: `delete 100` [Assuming the address book currently contains 10 persons]

`The person index provided is invalid`

<div style="page-break-after: always;"></div>

### Searching for persons by name: `search`

Search for persons using name.

Output persons which match the given name.

> Note: The input name is NOT case-sensitive.

###### FORMAT:
`search NAME`

###### EXAMPLE COMMAND:
`search Charlie`

###### ACCEPTABLE VALUES:
`NAME`: Any non-empty input of characters (not case-sensitive).

> 📝Note:
>
> `search` does not check for invalid `name` input so no error message will be shown for invalid input

###### EXPECTED OUTPUT ON SUCCESS:
```
1 persons found!
```

###### EXPECTED OUTPUT ON FAILURE:
**For incomplete command:** 

Example: `search   `

```
Invalid command format!
search: Finds all persons whose names contain any of the specified keywords 
(case-insensitive) and displays them as a list with index numbers.
Parameters: KEYWORD [MORE_KEYWORDS]...
Example: search alice bob charlie
```

<div style="page-break-after: always;"></div>

### Searching for persons by role: `searchrole`

Search for persons using role.

Output persons which match the given role.

> Note: The input role is NOT case-sensitive. In other words, `searchrole ta` is equivalent to `searchrole TA`, which will find all `TA` entries.

###### FORMAT:
`searchrole ROLE`

###### EXAMPLE COMMAND:
`searchrole TA`

###### ACCEPTABLE VALUES:
`ROLE`: Any non-empty input of characters (not case-sensitive).

> 📝Note:
>
> `searchrole` does not check for invalid `role` input so no error message will be shown for invalid input

###### EXPECTED OUTPUT ON SUCCESS:
```
0 persons found!
```

###### EXPECTED OUTPUT ON FAILURE:
**For incomplete command:**

Example 1: `searchrole    `

```
Invalid command format!
searchrole: Finds all persons whose roles contain any of the specified keywords 
(case-sensitive) and displays them as a list with index numbers.
Parameters: KEYWORD [MORE_KEYWORDS]...
Example: searchrole TA
```

<div style="page-break-after: always;"></div>

### Searching for persons by course: `searchcourse`

Search for persons using course.

Output persons which match the given course.

> Note: The input course is NOT case-sensitive. In other words, `searchcourse cs1101` is equivalent to `searchcourse CS1101`, which will match both `CS1101` and `cs1101`.

###### FORMAT:
`searchcourse COURSECODE`

###### EXAMPLE COMMAND:
`searchcourse CS2100`

###### ACCEPTABLE VALUES:
`COURSE`: Any non-empty input of characters (not case-sensitive).

> 📝Note:
>
> `searchcourse` does not check for invalid `course` input so no error message will be shown for invalid input

###### EXPECTED OUTPUT ON SUCCESS:
```
1 persons found!
```

###### EXPECTED OUTPUT ON FAILURE:
**For incomplete command:**

Example: `searchcourse    `

```
Invalid command format!
searchcourse: Finds all persons whose courses contain any of the 
specified keywords (case-insensitive) and displays them as a list 
with index numbers.
Parameters: KEYWORD [MORE_KEYWORDS]...
Example: searchcourse CS2100
```


<div style="page-break-after: always;"></div>

### Searching for persons by tutorial: `searchtutorial`

Search for persons using tutorial class.

Output persons which match the given tutorial class.

> Note: The input tutorial is NOT case-sensitive. In other words, `searchtutorial cs2100/t03` is equivalent to `searchtutorial CS2100/T03` which will match both `cs2100/t03` as well as `CS2100/T03`.

###### FORMAT:
`searchtutorial TUTORIAL`

###### EXAMPLE COMMAND:
`searchtutorial CS2100/Tut8`

###### ACCEPTABLE VALUES:
`TUTORIAL`: Any non-empty input of characters (not case-sensitive).

> 📝Note:
>
> `searchtutorial` does not check for invalid `tutorial` input so no error message will be shown for invalid input

###### EXPECTED OUTPUT ON SUCCESS:
```
0 persons found!
```

###### EXPECTED OUTPUT ON FAILURE:
**For incomplete command:**

Example: `searchtutorial    `

```
Invalid command format!
searchtutorial: Finds all persons whose tutorials contain any of the 
specified keywords (case-insensitive) and displays them as a list 
with index numbers.
Parameters: KEYWORD [MORE_KEYWORDS]...
Example: searchtutorial CS2100/G07
```

<div style="page-break-after: always;"></div>

### Clearing the person list: `clear`

Clears the address book.

###### FORMAT:
`clear`

###### EXAMPLE COMMAND:
`clear`

###### ACCEPTABLE VALUES:
Command accepts parameters after the keyword `clear`, i.e. `clear your mind` but they will be ignored 
and the `clear` command will still be executed. 

###### EXPECTED OUTPUT ON SUCCESS:
```
All persons have been cleared!
```

###### EXPECTED OUTPUT ON FAILURE:
This command only recognises `clear` as the keyword.

Any other command word such as `c`, `cl` and `clea` will be seen as an invalid command with the following output:

`Unknown command`

<div style="page-break-after: always;"></div>

### Exiting the application: `exit`

Closes and exits the application

###### FORMAT:
`exit`

###### EXAMPLE COMMAND:
`exit`

###### ACCEPTABLE VALUES:
Command accepts parameters after the keyword `exit`, i.e. `exit world` but they will be ignored and
the `exit` command will still be executed.

###### EXPECTED OUTPUT ON SUCCESS:
There will be no output 

The application will close

###### EXPECTED OUTPUT ON FAILURE:
This command only recognises `exit` as the keyword.

Any other command word such as `e`, `ex` and `exi` will be seen as an invalid command with the following output:

`Unknown command`

<div style="page-break-after: always;"></div>

### Autocomplete
What if you needed to quickly write a command? Well, Autocomplete feature is here to save you!

All you need to do is: type your command, and then press Tab for it to suggest the next command!

For example, pressing `f` and then `<Tab>` will let the program automatically suggest the `fav` command. You can continue pressing `<Tab>` to cycle through the list of commands. For instance, pressing `<Tab>` again after it suggests `fav` will cause it to autocomplete `favlist` instead. After cycling through all possible autocompletions, it will cycle back to your original input, if you want to amend it some more.

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## FAQ

<panel header="**Q**: Where is the save data file stored?">

**A**: 
By default, the data file is stored in the `root/data/addressbook.json` file, where `root` refers to the directory the executable JAR file is in.
<box type="info">
    <md>
        This data file is created when the app is launched **for the first time**. If the data file does not exist yet, try to launch the app and exit it (either via `exit` command or by closing with the X button), and then checking the directory again.
    </md>
</box>
<box type="warning">
    <md>
        Editing the save data file manually may result in **unexpected behaviour**. Only edit the data file if **you know what you are doing.** Otherwise, the data file may become corrupted and the app may unable to read or access your data.
    </md>
</box>
</panel>

<panel header="**Q**: How do I transfer my data to another Computer?">

**A**: 
1. Install the app in the other computer.
1. Launch the app for the first time in a new folder. (<a href="#quick-start">How?</a>) Then, close the app via `exit` command or clicking on the X button.
1. In the folder that the app was launched, there should be sub-folder called `data`.
1. Replace the json file in that folder, with the one (in the same location) from the first computer (the data you wish to transfer).
1. Reopen the app and the data should have been transferred!
</panel>



--- {.dotted .thick-1 .border-primary}

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## Command summary

|         Action         | Format                                                                                              | Example                                                                                                                             |
|:----------------------:|-----------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
|        **Help**        | `help`                                                                                              | `help`                                                                                                                              |                                                                                                                                  
|        **Add**         | `add --name NAME [--role ROLE1, ...]  [--contact CONTACT1, ...] [--course COURSECODE1/CLASS1, ...]` | `add --name Aiken Dueet --role Student --contact @aikendueet, aikendueet@gmail.com --course CS2103T/Tut8, CS2101/G06, CS2100/Lab40` |
|        **List**        | `list`                                                                                              | `list`                                                                                                                              |
|       **Delete**       | `delete INDEX`                                                                                      | `delete 3`                                                                                                                          |
|   **Search by Name**   | `search KEYWORD`                                                                                    | `search Alex`                                                                                                                       |
|   **Search by Role**   | `searchrole KEYWORD`                                                                                | `searchrole TA`                                                                                                                     |
|  **Search by Course**  | `searchcourse KEYWORD`                                                                              | `searchcourse CS2100`                                                                                                               |
| **Search by Tutorial** | `searchtutorial KEYWORD`                                                                            | `searchtutorial CS2100/G06`                                                                                                         |
|     **Favourite**      | `fav INDEX`                                                                                         | `fav 1`                                                                                                                             |
|    **Unfavourite**     | `unfav INDEX`                                                                                       | `unfav 1`                                                                                                                           |
|   **Clear the list**   | `clear`                                                                                             | `clear`                                                                                                                              |
|        **Exit**        | `exit`                                                                                              | `exit`                                                                                                                              | 
<scroll-top-button><scroll-top-button/>
