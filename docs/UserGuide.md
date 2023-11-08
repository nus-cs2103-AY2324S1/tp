---
layout: page
title: User Guide
show-toc: true
---

ClubMembersContacts (CMC) is an application designed to empower EXCO members of the School of Computing's CCAs in
efficiently managing the contacts of their members and applicants.

In the fast-paced world of CCA leadership, time is a precious resource, and effective contact management is crucial.
ClubMembersContacts has been tailored to cater to your specific needs, ensuring that you can streamline your
contact-related responsibilities seamlessly. It provides a swift and convenient yet powerful
solution through a Command Line Interface (CLI) aimed at optimizing the speed and effectiveness of your contact
management tasks.

Here are some possible ways you can integrate CMC into your CCA:

- You can add tags to different members to delegate them roles, or any additional information.
- You can track tasks assigned to each member to track their completion.
- You can schedule interview times with your applicants.
- You can find members or applicants easily through the `find` commands.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

If this is your first time using CMC, head over to [How to use CMC's User Guide](#1-how-to-use-cmcs-user-guide)
to start keeping track of all your members and applicants!

</div>

--------------------------------------------------------------------------------------------------------------------

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. How to Use CMC's User Guide

Thank you for choosing to use CMC. We are sure that CMC will be your trusty companion throughout your
management journey. This user guide will provide you a comprehensive documentation of CMC's features.

If you are a first time user of CMC, make sure you have already downloaded the application. Head over to
[Quick Start](#2-quick-start) to learn how to install the application.

If you would like to have a brief introduction of the application's interface,
head over to our [Interface Walkthrough](#3-interface-walkthrough) to better learn about CMC.

If you are already a seasoned user of CMC, you can refer to [Command Summary](#6-command-summary) for a
quick reference to all the commands available in CMC.

## 2. Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest ClubMembersContact.jar from [here](https://github.com/AY2324S1-CS2103T-W15-3/tp/releases).

3. Locate your jar file in your computer and double-click on it to run the application. Alternatively, you can run the
   jar file from the command line using the java -jar ClubMembersContact.jar command.

4. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.
   Here are some example commands you can try:

    - `help`: Opens up the help window.
    - `addm /name Alicia Teng /phone 91126291 /email alicia_teng@gmail.com /tele @alicia_teng`: Adds a member
      named `Alicia Teng` to the list of members. See how to [add member](#411-adding-a-member-addmember)
      for more.
    - `adda /name Chan Rui Jia /phone 97777117 /interview 10/12/2023 1400`: Adds an applicant named `Chan Rui Jia` to
      the list. See how to [add applicant](#421-adding-an-applicant-addapplicant-or-adda) for more.
    - `deltm 1`: Deletes the first member in the list of members.
    - `finda Win Sheng`: Searches for all applicants with `Win Sheng` in their contact details. See how
      to [find an applicant](#422-finding-applicants-findapplicant-or-finda) for more.
    - `editm 1 /tag Design`: Edits the tag of the first member in the list of members. See how
      to [edit a member](#414-editing-a-member-editmember-or-editm) for more.
    - `exit`: Exits the application.

        <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
        Follow each step in order to see how a normal workflow would feel like with CMC!
        </div>

6. Refer to the [Features](#4-features) below for details of each command.

<div markdown="span" class="alert alert-warning">:exclamation: **CAUTION:**
If you are using a PDF version of this User Guide, be careful when copy-pasting the commands that span beyond several lines. 
</div>
--------------------------------------------------------------------------------------------------------------------

## 3. Interface Walkthrough

This is the expected interface of the GUI when you launch CMC. Please note that this application has been populated
with sample data.

The key windows are labeled as follows:

1. File button: Opens an option to exit the application.
2. Help button: Opens a menu with a link to the User Guide.
3. Command Box: Enter your command here.
4. Result Display: Displays the result of your command.
5. Tag Display: Shows all the tags assigned to all members thus far.
6. Member List Display: Displays a list of all the members and their contact information.
7. Task List Display: Displays the task list assigned to a specific member.
8. Applicant List Display: Displays a list of all the applicants and their contact information.

![Interface](images/Interface_Guide.png)

The "Help" window will look like as follows:

![Help_Window](images/helpMessage.png)

--------------------------------------------------------------------------------------------------------------------

## 4. Features

This section of the User Guide will explain about each feature in detail.

<div markdown="block" class="alert alert-primary">

:information_source: **Notes about the inputs:**

* Commands are **case-insensitive**.

* Words in UPPER_CASE are input parameters that you need to provide for that specific field.  
  For example, in `addm /name NAME`, `NAME` would be the input parameter for the member's name.

* Fields in square brackets `[ ]` are optional fields.

* Parameters can be in any order.  
  For example, `adda /name Taylor Swift /phone 91961969` and `adda /phone 91691969 /name Taylor Swift` will result in
  the same applicant added.

* Items with … after them can be used multiple times including zero times.  
  For example, [/tag TAG]… can be used as (i.e. 0 times), /tag SWE, /tag UIUX /tag Product etc.

* See [examples of fields](#442-examples-of-fields) for more examples of valid and invalid fields.

</div>

<div markdown="block" class="alert alert-info">

:bulb: **Aliases**

Many of the commands below have _aliases_, or short-form versions that make them easier to type (e.g. `addm`
for `addmember`).

The aliases are documented below alongside the full command. For a more efficient experience, use the aliases instead of
the full command word!

</div>

### 4.1 Member Features

These are all the features in regard to a **member** in CMC.

#### 4.1.1 Adding a member: `addmember` or `addm`

Adds a member to the list of members.

#### Format:

`addmember /name MEMBER_NAME /phone PHONE_NUMBER /email EMAIL /tele TELEGRAM_HANDLE [/tag TAG]...`<br/>
`addm /name MEMBER_NAME /phone PHONE_NUMBER /email EMAIL /tele TELEGRAM_HANDLE [/tag TAG]...`

<div markdown="block" class="alert alert-primary">

:information_source: **Notes about input parameter:**
<br/>

- `MEMBER_NAME`: Only alphabetical characters, spaces, @, (), /, are allowed. Should not be blank.
  <br/>
- `PHONE_NUMBER`: Only numbers are allowed. At least 3 digits are required.
  <br/>
- `EMAIL`: See [email format](#441-email-format) for more details.
  <br/>
- `TELEGRAM_HANDLE`: Starting character of @ is required. Only alphanumeric characters and underscore are allowed.
  Minimum of 5 and maximum of 32 characters are allowed.
  <br/>
- `TAG`: Only alphanumeric characters are allowed. Minimum of 1 and maximum of 15 characters are allowed.

</div>

<div markdown="block" class="alert alert-primary">

:information_source: **Notes about duplicate members:**

<br/>
Duplicate members are not allowed to be added into the list of members.
<br/>
A member is considered a duplicate if the member's name, phone number, email **or** telegram handle matches that of an existing member.
<br/>
This means that only a member with a unique name, phone number, email and telegram handle can be added into the list of members.

</div>

#### Example of usage:

`addmember /name Taylor Swift /phone 91691969 /email taylorswift@era.tour /tele @tswift /tag Admin`<br/>
`addm /name Taylor Swift /phone 91691969 /email taylorswift@era.tour /tele @tswift /tag Admin`<br/>
This adds a new member named `Taylor Swift` with phone number `91691969`, email `taylorswift@era.tour`, telegram
handle `tswift` and tag `Admin` to the list of members.

![Add_Member](images/addMember.png)

#### 4.1.2 Finding members: `findmember` or `findm`

Find and generate a list of all existing member(s) whose information contain any of the specified keyword(s).

##### Format:

`findmember KEYWORD...`<br/>
`findm KEYWORD...`

<div markdown="span" class="alert alert-primary">
:information_source: **Notes about the command format:** KEYWORDs have to be separated by a space.
</div>

##### Example of usage:

`findmember Alicia`<br/>
`findm Alicia`<br/>
This generates a list of all members whose details contain `Alicia`.

![Find_Member](images/findMember.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

If you would like to go back to the whole list of members, use `viewmember`.
You may check out the command [here](#413-viewing-members-viewmembers-or-view).

</div>

#### 4.1.3 Viewing members: `viewmembers` or `viewm`

Generates a list of all existing member(s). An example of where you might want to use this command is if
you want to go back to viewing all members after a search.
with [`findmember`](#412-finding-members-findmember-or-findm).

##### Format:

`viewmembers`<br/>
`viewm`

##### Example of usage:

`viewmembers`<br/>
Generates a list of all existing member(s).

![View_Member](images/viewMember.png)

#### 4.1.4 Editing a member: `editmember` or `editm`

The member at the specified index will have his/her specified field(s) edited.

#### Format:

`editmember INDEX [/name MEMBER_NAME] [/phone PHONE_NUMBER] [/email EMAIL] [/tele TELEGRAM_HANDLE] [/tag TAG]...`<br/>
`editm INDEX [/name MEMBER_NAME] [/phone PHONE_NUMBER] [/email EMAIL] [/tele TELEGRAM_HANDLE] [/tag TAG]...`

<div markdown="span" class="alert alert-warning">:exclamation: **CAUTION:**
When editing a member's tag, the new tag(s) will replace any old existing tag(s). 
If you want to add a new tag to a member, you will have to include **both** the old tag(s)
and the new tag in the `tag` field of the command.
</div>

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:**
<br/>

- `INDEX`: Only positive integers are allowed.
  <br/>
- `MEMBER_NAME`: Only alphabetical characters, spaces, @, (), / are allowed. Should not be blank.
  <br/>
- `PHONE_NUMBER`: Only numbers are allowed. At least 3 digits are required.
  <br/>
- `EMAIL`: See [email format](#441-email-format) for more details.
  <br/>
- `TELEGRAM_HANDLE`: Starting character of @ is required. Only alphanumeric characters and underscore are allowed.
  Minimum of 5 and maximum of 32 characters are allowed.
  <br/>
- `TAG`: Only alphanumeric characters are allowed. Minimum of 1 and maximum of 15 characters are allowed.

</div>

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about duplicate members:**
<br/>
Duplicate members are not allowed to be the list of members.
<br/>
This means that you cannot edit a member to have the same name, phone number, email **or** telegram handle as another
existing member.

</div>

#### Example of usage:

`editmember 1 /name John Wick /email johnwick@the.movie /tele @johnwick`<br/>
`editm 1 /name John Wick /email johnwick@the.movie /tele @johnwick`<br/>
This edits the particulars of the first member in the list of members, changing his/her name to `John Wick`, email
to `johnwick@the.movie`, and telegram handle to `@johnwick`.

#### 4.1.5 Deleting a member: `deletemember` or `delm`

The member at the specified index will be deleted from the list of members.

#### Format:

`deletemember INDEX`<br/>
`delm INDEX`

<div markdown="span" class="alert alert-warning">:exclamation: **CAUTION:**
This command is DESTRUCTIVE! This command cannot be undone. Deleted members will have to be re-added into the list of members via
the `addmember` command. **Proceed with caution!**
</div>

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:**
<br/>

- `INDEX`: Only positive integers are allowed.

</div>

#### Example of usage:

`deletemember 1`<br/>
`delm 1`<br/>
This deletes the member at index 1 in the list of members.

#### 4.1.6 Copying a member's details: `copymember` or `cpm`

Copies the details of the member at the specified index to the clipboard.

##### Format:

`copyMember INDEX`<br/>
`cpm INDEX`

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:** The input parameter will only take positive integers.
If the index is negative or 0, or the member does not exist in the list, this command will throw an error.

</div>

##### Example of usage:

`copymember 1`<br/>
`cpm 1`<br/>
This copies the details of the member at index 1 to the clipboard.

![Copy_Member](images/copyMember.jpg)

The copied details will be as follows:

```
Name: Taylor Swift
Phone: 91691969
Email: taylorswift@era.tour
Telegram: @tswift
Tags: [Singer]
Tasks:
```

#### 4.1.7 Allocating a task to a member: `addtask` or `addt`

#### 4.1.8 Viewing all tasks allocated to a member: `viewtask` or `viewt`

#### 4.1.9 Deleting a task allocated to a member: `deletetask` or `delt`

Adds an applicant to the list of applicants.

#### Format:

`deletetask MEMBER_INDEX /task TASK_INDEX`<br/>
`delt1 MEMBER_INDEX /task TASK_INDEX`

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:**

- `MEMBER_INDEX`: Only positive integers are allowed.<br/>
- `TASK_INDEX`: Only positive integers are allowed.

#### Example of usage:

`deletetask 1 /task 2`<br/>
`delt 1 /task 2`<br/>
This deletes the second task of the first member in the list of members.

### 4.2 Applicant Features

These are all the features in regard to an **applicant** in CMC.

#### 4.2.1 Adding an applicant: `addapplicant` or `adda`

Adds an applicant to the list of applicants.

#### Format:

`addapplicant /name APPLICANT_NAME /phone PHONE_NUMBER`<br/>
`adda /name APPLICANT_NAME /phone PHONE_NUMBER`

<div markdown="block" class="alert alert-primary">

:information_source: **Notes about input parameter:**

- `APPLICANT_NAME`: Only alphabetical characters, spaces, @, (), / are allowed. Should not be blank.<br/>
- `PHONE_NUMBER`: Only numbers are allowed. At least 3 digits are required.

</div>

<div markdown="block" class="alert alert-primary">

:information_source: **Notes about duplicate applicants:**

* Duplicate applicants are not allowed to be added into the list of applicants.

* An applicant is considered a duplicate if the applicant's name **or** phone number matches that of an existing
  applicant.

* This means that only an applicant with a unique name and phone number can be added into the list of applicants.

</div>

#### Example of usage:

`addapplicant /name Lady Gaga /phone 99129969`<br/>
`adda /name Lady Gaga /phone 99129969`

This adds a new applicant named `Lady Gaga` with phone number `99129969` to the list of applicants.

![Add_Applicant](images/addApplicant.png)


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

If you would like **schedule an interview** with a specific applicant, use `editapplicant`.
You may check out the command [here](#424-editing-an-applicant-editapplicant-or-edita).

</div>

#### 4.2.2 Finding applicants: `findapplicant` or `finda`

Find and generate a list of all existing applicants(s) whose information contain any of the specified keyword(s).

##### Format:

`findapplicant KEYWORD...`  
`finda KEYWORD...`

<div markdown="span" class="alert alert-primary">
:information_source: **Notes about the input parameter:** KEYWORDs have to be separated by a space.
</div>

##### Example of usage:

`findapplicant Rui`<br/>
`finda Rui`<br/>
This generates a list of all members whose details contain `Rui`.

![Find_Applicant](images/findApplicant.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

If you would like to go back to the whole list of applicants, use `viewapplicants`.
You may check out the command [here](#423-viewing-applicants-viewapplicants-or-viewa).

</div>

#### 4.2.3 Viewing applicants: `viewapplicants` or `viewa`

Generates a list of all existing applicant(s). An example of where you might want to use this command is if
you want to go back to viewing all members after a search.
with [`findapplicant`](#422-finding-applicants-findapplicant-or-finda).

##### Format:

`viewapplicants`<br/>
`viewa`

##### Example of usage:

`viewa`<br/>
Generates a list of all existing applicant(s).

![View_Applicant](images/viewApplicants.png)

#### 4.2.4 Editing an applicant: `editapplicant` or `edita`

The applicant at the specified index will have his/her specified field(s) edited.

#### Format:

`editapplicant INDEX [/name APPLICANT_NAME] [/phone PHONE_NUMBER] [/interview INTERVIEW_TIME]`<br/>
`edita INDEX [/name APPLICANT_NAME] [/phone PHONE_NUMBER] [/interview INTERVIEW_TIME]`

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:**
<br/>

- `INDEX`: Only positive integers are allowed.
  <br/>
- `APPLICANT_NAME`: Only alphabetical characters, spaces, @, (), / are allowed. Should not be blank.
  <br/>
- `PHONE_NUMBER`: Only numbers are allowed. At least 3 digits are required.
  <br/>
- `INTERVIEW_TIME`: Only dates in the format of “DD/MM/YYYY HhMm” are allowed. To remove an interview time from an
  applicant, ‘cancel’ is also allowed.

</div>

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about duplicate applicants:**
<br/>
Duplicate applicants are not allowed to be the list of applicants.
<br/>
This means that you cannot edit an applicant to have the same name **or** phone number as another existing applicant.

</div>

#### Example of usage:

`editapplicant 1 /name John Wick /interview 01/01/2024 0100`<br/>
`edita 1 /name John Wick /interview 01/01/2024 0100`<br/>
This edits the name of the first applicant in the list of applicants, changing his/her name to `John Wick`.
An interview date and time is also scheduled (or changed to if already scheduled) for the first applicant
at `01/01/2024`, `0100` hrs.

#### 4.2.5 Deleting an applicant: `deleteapplicant` or `dela`

The applicant at the specified index will be deleted from the list of applicants.

##### Format:

`deleteapplicant INDEX`<br/>
`dela INDEX`

<div markdown="span" class="alert alert-warning">:exclamation: **CAUTION:**
This command is DESTRUCTIVE! This command cannot be undone. Deleted applicants will have to be re-added into the list of applicants via
the `addapplicant` command. **Proceed with caution!**
</div>

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:** The input parameter will only take positive integers.
If the index is negative or 0, or the applicant does not exist in the list, this command will throw an error.
</div>

##### Example of usage:

`deleteapplicant 3`<br/>
`dela 3`<br/>
This deletes the applicant at index 1 in the list of applicants.

![Delete_Applicant](images/deleteApplicant.png)

#### 4.2.6 Copying an applicant's details: `copyapplicant` or `cpa`

Copies the details of the applicant at the specified index to the clipboard.

##### Format:

`copyapplicant INDEX`<br/>
`cpa INDEX`

<div markdown="span" class="alert alert-primary">

:information_source: **Notes about input parameter:** The input parameter will only take positive integers.
If the index is negative or 0, or the applicant does not exist in the list, this command will throw an error.
</div>

##### Example of usage:

`copyapplicant 1`<br/>
`cpa 1`<br/>
This copies the details of the applicant at index 1 to the clipboard.

![Copy_Applicant](images/copyApplicant.jpg)

The copied details will be as follows:

```
Name: Lady Gaga
Phone: 99129969
Interview Time: Interview time has not been set
```

### 4.3 General Features

#### 4.3.1 Accessing the user guide: `help`

Opens a window with a link to the user guide.
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

This window can also be opened from accessing the `Help` button.  
Check out the [Interface Walkthrough](#3-interface-walkthrough) to locate where the button is.

</div>

#### 4.3.2 Exiting the application: `exit`

Exits the application.

<div markdown="span" class="alert alert-warning">:exclamation: **CAUTION:**
There will be no prompt to confirm this action.
</div>

### 4.4 Field Constraints

#### 4.4.1 `EMAIL` format

`EMAIL` field should be of the format **local-part@domain**.

- local-part:
    - Only alphanumerical and special characters "+_.-" are allowed.
    - It cannot start or end with any special characters.
- domain:
    - Made up of 1 **or** 2 domain labels (separated by periods ".").
    - Each domain label can only contain alphanumerical characters and hyphens "-", if any.
    - Each domain label must start and end with an alphanumerical character.
    - The last domain label must contain at least 2 characters.

#### 4.4.2 Examples of fields

| Field               | Valid                                                    | Invalid                                                                                                   |
|---------------------|----------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| **INDEX**           | `1` `2` `3`                                              | `0` `-1`                                                                                                  |
| **NAME**            | `Rui Jia (MR)` `J@mes` `James s/o John` `Jon4s`          | `Sally-Ong` `Dark_Knight`                                                                                 |
| **PHONE_NUMBER**    | `123` `91223294` `88299188282839`                        | `1` `12` `9239189a` `9838@-_.`                                                                            |
| **EMAIL**           | `a@x.yz` `a@xy` `John_Lim@gmail.com` `Alicia@g-mail.com` | `a@x` `a@x.y` `_Zann@gmail.com` `Alfred@-xy.com` `Win Sheng@xy` `Rui_Jia@hotmail-.com` `DarkKnight!@xyz`  |
| **TELEGRAM_HANDLE** | `@Win_Sheng` `@Jon4s` `@1234` `@abc_`                    | `Win_Sheng` `@123` `@Jon4s!` `@Rui Jia` <br/> `@this_is_32_characters_long_abcd`                          |
| **TAG**             | `Friend` `Friend123`                                     | `Friend!@!` `16characterslong`                                                                            |
| **INTERVIEW_TIME**  | `01/12/2023 1430` `15/05/2022 0915` `cancel`             | `2023-12-01 14:30` `01/12/23 1430` `01/Dec/2023 1430` `01/12/2023 3:30 PM` `15/05/2022` `Cancel` `CANCEL` |

<div markdown="span" class="alert alert-primary">

:information_source: Click [here](#4-features) to see all the features of CMC.
</div>

## 5. FAQ

Q: How do I see tasks of a different member?  
A: You have to use the `viewt` or `viewtask` method to view the tasks allocated to another member.  
For example, if your window is currently on another member's task, use `viewt 3` to view the tasks of the member at
index 3.

Q: How do I transfer my data to another person or computer?  
A: This application saves the data of your applicants and members in the ./data/addressbook.json file. You can send the
file to the other
person or computer, and overwrite the empty addressbook.json with addressbook.json file of your choice.

<div markdown="span" class="alert alert-warning">:exclamation: **CAUTION:**

If the addressbook.json file is invalid or has any formatting errors, the program might not start up. Please make sure
that the addressbook.json file used is **correct and accurate.**

</div>


--------------------------------------------------------------------------------------------------------------------

## 6. Command summary

| Action                      | Format                                                                                                                                                                                                               | Example(s)                                                                                                                                                                        |
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Member**              | `addMember /name NAME /phone PHONE_NUMBER /email EMAIL /tele TELEGRAM_HANDLE [/tag TAG]...`  <br/> <br/>  `addm /name NAME /phone PHONE_NUMBER /email EMAIL /tele TELEGRAM_HANDLE [/tag TAG]...`                     | `addm /name Alicia /phone 92345678 /email alicia@gmail.com /tele @Alicia`  <br/> <br/> `addMember /name Jonas /phone 9993 3325 /email jonas@outlook.com /tele @jonasong /tag SWE` |
| **Delete Member**           | `deleteMember INDEX` <br/> <br/> `delm INDEX`                                                                                                                                                                        | `deleteMember 1` <br/> <br/> `delm 1`                                                                                                                                             |
| **Edit Member**             | `editMember INDEX [/name NAME] [/phone PHONE_NUMBER] [/email EMAIL] [/tele TELEGRAM_HANDLE] [/tag TAG]...` <br/> <br/>`editm INDEX [/name NAME] [/phone PHONE] [/email EMAIL] [/tele TELEGRAM_HANDLE] [/tag TAG]...` | `editm 1 /name Alicia /phone 99126291`  <br/> <br/> `editm 1 /email aliciateng@gmail.com`                                                                                         |
| **Find Member(s)**          | `findMember KEYWORD` <br/> <br/> `findm KEYWORD`                                                                                                                                                                     | `findm Alicia` <br/> <br/> `findm John 92345678` <br/> <br/> `findMember Designer`                                                                                                |
| **View Member(s)**          | `viewMembers` <br/> <br/> `viewm`                                                                                                                                                                                    | `viewMembers` <br/> <br/> `viewm`                                                                                                                                                 |
| **Add Applicant**           | `addApplicant /name [NAME] /phone PHONE_NUMBER` <br/> <br/> `adda /name NAME /phone PHONE_NUMBER`                                                                                                                    | `addApplicant /name Jonas /phone 91238932` <br/> <br/> `adda /name Alicia /phone 92345678`                                                                                        |
| **Add Task to Member**      | `addTask [MEMBER_INDEX] /task [TASK_DESCRIPTION]` <br/> <br/> `addt [MEMBER_INDEX] /task [TASK_DESCRIPTION]`                                                                                                         | `addTask 2 /task Design Poster` <br/> <br/> `addt 3 /task Meet Product Team`                                                                                                      |
| **View Task(s) of Member**  | `viewTask [MEMBER_INDEX]` <br/> <br/> `viewt [MEMBER_INDEX]`                                                                                                                                                         | `viewTask 2` <br/> <br/> `viewt 5`                                                                                                                                                |
| **Delete Task from Member** | `deleteTask MEMBER_INDEX /task TASK_INDEX` <br/> <br/> `delt MEMBER_INDEX /task TASK_INDEX`                                                                                                                          | `deleteTask 2 /task 4` <br/> <br/> `delt 1 /task 10`                                                                                                                              |
| **Delete Applicant**        | `deleteApplicant INDEX` <br/> <br/> `dela INDEX`                                                                                                                                                                     | `deleteApplicant 3` <br/> <br/> `dela 1`                                                                                                                                          |
| **Edit Applicant**          | `editApplicant INDEX [/name NAME] [/phone PHONE_NUMBER] [/interview INTERVIEW_TIME` <br/> <br/>  `edita INDEX [/name NAME] [/phone PHONE_NUMBER] [/interview INTERVIEW_TIME`                                         | `editApplicant 1 /name John` <br/> <br/> `edita 1 /interview 07/01/2003 1500` <br/><br/> `edita 1 /name Aliciaa /phone 12345678 /interview 10/12/2023 1150`                       |
| **Find Applicant(s)**       | `findApplicant KEYWORD...` <br/><br/> `finda KEYWORD...`                                                                                                                                                             | `finda Alicia` <br/><br/> `finda John 92345678`                                                                                                                                   |
| **View Applicant(s)**       | `viewApplicants` <br/><br/> `viewa`                                                                                                                                                                                  | `viewApplicants` <br/><br/> `viewa`                                                                                                                                               |
