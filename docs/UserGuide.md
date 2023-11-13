---
layout: page
title: User Guide
---

<!-- Inject styles for custom table widths -->

<style>
.field-table th:nth-child(1) {
    width: 23%;
}

.field-table th:nth-child(2) {
    width: 14%;
}

.field-table th:nth-child(3) {
    width: 10%;
}

.field-table th:nth-child(4) {
    width: 53%;
}

.errors-table th:nth-child(1) {
    width: 50%;
}

.errors-table th:nth-child(2) {
    width: 50%;
}
</style>

Are you a School of Computing (SoC) Computing Club event planner juggling numerous tasks and contacts for your upcoming events?

Managing everything efficiently just got easier with CoordiMate!

CoordiMate is your go-to **desktop app** designed specifically for **SoC Computing Club event planners** to help you **manage your contacts and tasks** for your events, so that you can focus on the event itself.

And here's the best part – while it's perfect for members of SoC Computing Club, **event planners of all kinds** can benefit from CoordiMate's powerful features too!

<h4>Why choose CoordiMate?</h4>

1. **User-Friendly [Command Line Interface (CLI)](#command-line-interface)**: CoordiMate is optimized for use via a CLI, combining all the benefits of a [Graphical User Interface (GUI)](#graphical-user-interface) with the efficiency of a CLI.

2. **Speedy Navigation**: If you're a fast typist, CoordiMate will help you complete your contact and tasks management faster than traditional GUI apps, maximising your productivity.

Don't let the stress of contact and task management hinder your event planning creativity.

Let **CoordiMate** take care of the details, while you craft extraordinary events with confidence.

## Using This Guide

This document will guide you on the various features of CoordiMate and how to use them.

If you are a new user, we recommend that you read this guide starting from the [Quick Start](#quick-start) section.

If you are an experienced user, you can use the [Table of Contents](#table-of-contents) below to quickly locate the relevant section. Alternatively, you can jump to the [Command Summary](#command-summary) section for an overview of the command syntax.

Here are some annotations used in this guide:

{% include admonition.html type="warning" title="Warning" body="

Take note of these as they can cause unexpected behaviour or data loss when using CoordiMate.

" %}

{% include admonition.html type="note" title="Note" body="

Contains useful information to help you use CoordiMate better.

" %}

{% include admonition.html type="question" title="Question" body="

Answers to frequently asked questions about CoordiMate.

" %}

{% include admonition.html type="bug" title="Known Issue" body="

Known issues that you may face when using CoordiMate and how to resolve them.

" %}

Throughout this guide, you will see different text styles that are used to highlight important information.

- <a href="javascript: void(0)">Text in blue</a> are links which you can click on to jump to the relevant section.
- `Text with light blue background` are commands or file names which are used for CoordiMate.

---

<div style="page-break-after: always;"></div>

## Table of Contents

- Table of Contents
{:toc}

---

<div style="page-break-after: always;"></div>

## Features

[Back to Table of Contents](#table-of-contents)

  1. **Easy Management of People**:
      - Seamlessly create, update, and delete your contacts, ensuring your contact list is always up-to-date.<br><br>

  2. **Effortless Task Management**:
      - CoordiMate records down your tasks and their completion status, allowing you to stay on top of your to-do list.<br><br>

  3. **Simplified Searching**:
      - Easily locate contacts or tasks based on your criteria. No more scrolling through endless lists!<br><br>

  4. **Hassle-Free Data Management**:
      - CoordiMate automatically saves your data on every change, so you never have to worry about losing your data.
      - When CoordiMate starts, your existing data is automatically loaded in, allowing you to pick up where you left off.
      - For advanced users, CoordiMate offers you complete data control by saving your data in a human-readable format. <br>Locate the data file at `[JAR file location]/data/addressbook.json` to edit CoordiMate's data directly!

{% include admonition.html type="warning" title="Warning" body="

If your changes to the data file makes its format invalid, CoordiMate will not be able to load the data file, resulting in the contact and task lists being empty. <br>

Always make a backup before you edit!

" %}

---

<div style="page-break-after: always;"></div>

## Quick Start

[Back to Table of Contents](#table-of-contents)

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `CoordiMate.jar` from [here](https://github.com/AY2324S1-CS2103T-T10-2/tp/releases).

3. Copy `CoordiMate.jar` to the folder you want to use as the _home folder_ for CoordiMate. This folder will be used by CoordiMate to store its data.

4. Open a command terminal, `cd` into the folder you put `CoordiMate.jar` in, and use the `java -jar CoordiMate.jar` command to start CoordiMate.<br>

   A GUI similar to the below screenshot should appear in a few seconds. Note how the app contains some sample data.<br><br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br><br>

   Some example commands you can try:

   - `listPerson` : Lists all persons.

   - `addPerson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a new contact named `John Doe` with the specified details.

   - `deletePerson 3` : Deletes the 3rd contact shown in the current contact list.

   - `findTask caterer` : Finds all tasks with the word `caterer` in their title or note.

   - `listTask` : Lists all tasks.

   - `addTask T/Get Flowers n/for Finale Night` : Adds a task titled `Get Flowers` with note `for Finale Night` to the task list.

   - `markTask 1` : Marks the 1st task shown in the current task list as done.

   - `exit` : Exits the app.

6. Refer to the [Usage](#usage) section below for details of each command.

---

<div style="page-break-after: always;"></div>

## Usage

[Back to Table of Contents](#table-of-contents)

The commands are split into 4 main sections:

1. [Commands to Manage Persons](#1-commands-to-manage-persons)
2. [Commands to Manage Tasks](#2-commands-to-manage-tasks)
3. [Commands to Manage Tags](#3-commands-to-manage-tags)
4. [General commands](#4-general-commands)

{% include admonition.html type="note" title="Note" body="

<ul>
  <li>
    <p>
      Words in <code>UPPER_CASE</code> are the fields to be supplied by the user.<br>
      e.g. in <code>addPerson n/NAME</code>, <code>NAME</code> is a field which can be used as <code>addPerson n/John Doe</code>, where <code>John Doe</code> is the value of the field <code>NAME</code>. <br>
    </p>
  </li>

  <li>
    <p>
      Fields in square brackets are optional.<br>
      e.g <code>n/NAME [t/TAG]</code> can be used as <code>n/John Doe t/friend</code> or as <code>n/John Doe</code>.<br>
      Note that the square brackets (<code>[</code> and <code>]</code>) are not part of the syntax.
    </p>
  </li>

  <li>
    <p>
      Fields in square brackets with <code>…</code> after them can be used multiple times, including zero times.<br>
      e.g. <code>[t/TAG]…​</code> can be used as <code> </code> (i.e. 0 times), <code>t/friend</code>, <code>t/friend t/family</code> etc.
    </p>
  </li>

  <li>
    <p>
      Fields can be in any order.<br>
      e.g. if the command specifies <code>n/NAME p/PHONE_NUMBER</code>, <code>p/PHONE_NUMBER n/NAME</code> is also acceptable.
    </p>
  </li>

  <li>
    <p>
      Extraneous fields for commands that do not take in fields (such as <code>help</code>, <code>listPerson</code>, <code>deleteAllPerson</code>, <code>listTask</code>, <code>deleteAllTask</code> and <code>exit</code>) will be ignored.<br>
      e.g. if the command specifies <code>help 123</code>, it will be interpreted as <code>help</code>.
    </p>
  </li>
</ul>

" %}

{% include admonition.html type="warning" title="Warning" body="

If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

" %}

<div style="page-break-after: always;"></div>

### Command Aliases

[Back to Table of Contents](#table-of-contents)

CoordiMate supports predefined aliases for commands, so that you can get things done faster!<br>

**Example 1: Adding a person**

Instead of typing:

<div class="highlight">
  <pre class="highlight"><code><b>addPerson</b> n/John Doe p/12345678 e/john@example.com a/1 John Street t/friend</code></pre>
</div>

you can simply type:

<div class="highlight">
  <pre class="highlight"><code><b>ap</b> n/John Doe p/12345678 e/john@example.com a/1 John Street t/friend</code></pre>
</div>

Both commands will add a person named `John Doe` with phone number `12345678`, email `john@example.com`, address `1 John Street`, and tag `friend` to your contact list.<br><br>

**Example 2: Listing all persons**

Instead of typing:

<div class="highlight">
  <pre class="highlight"><code><b>listPerson</b></code></pre>
</div>

you can simply type:

<div class="highlight">
  <pre class="highlight"><code><b>lp</b></code></pre>
</div>

Both commands will show a full list of all the contacts in your contact list.<br>

A summary of valid aliases is shown in the [Command Summary](#command-summary) section below.

---

<div style="page-break-after: always;"></div>

### Understanding Our GUI

[Back to Table of Contents](#table-of-contents)

![Ui](images/Ui_explanation.png)

1. Menu Bar: Quick access to File and Help features.
2. Command Line: Type your commands here.
3. Command Result: View command results or error messages through here.
4. Contact List: View and manage contacts here.
5. Task List: View and manage tasks here.
6. Data Storage: Displays the path where your data is stored.

{% include admonition.html type="note" title="Note" body="

Here's a handy trick – you can easily resize sections like Command Result, Contact List, and Task List. Just give the sides a drag in or out to customize the view according to your preference.

" %}

---

<div style="page-break-after: always;"></div>

### 1. Commands to Manage Persons

#### 1.1. Adding a person: `addPerson`

[Back to Table of Contents](#table-of-contents)

You can add new persons to your contact list, so that you can remember details of new people you meet.

<h4>Format:</h4>

```
addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…
```

<h4>Alias:</h4>

```
ap
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `NAME` | `n/` | <img width=30px src='assets/svg/ug/required.svg'> | Full name of the individual.|
| `PHONE_NUMBER` | `p/` | <img width=30px src='assets/svg/ug/required.svg'> | Phone number of the individual.|
| `EMAIL` | `e/` | <img width=30px src='assets/svg/ug/required.svg'> | Email address of the individual.|
| `ADDRESS` | `a/` | <img width=30px src='assets/svg/ug/required.svg'> | Physical address of the individual.|
| `TAG` | `t/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Tag(s) for the individual.|
{: .field-table}

- A person is uniquely identified by their `NAME`. This field is case sensitive.

<h4>Example:</h4>

- `addPerson n/Charlotte Oliveiro p/93210283 e/charlotteo@example.com a/Blk 11 Ang Mo Kio Street 74, #11-04 t/flowers`<br><br>
  ![addPerson success](images/output/addPerson_success.png)

  *<center>CoordiMate adds a new contact with the corresponding details.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the name, phone number, email address and address are specified.
`Names should only contain alphanumeric characters and spaces, and it should not be blank` | Ensure that the name specified is not blank and contain only alphanumeric characters. Whitespaces at the start and end are trimmed.
`Phone numbers should only contain numbers, and it should be at least 3 digits long` | Ensure that the phone number specified is not blank, contain only numbers and at least 3 digits long. Whitespaces at the start and end are trimmed.
`Addresses can take any values, and it should not be blank` | Ensure that the address specified is not blank. Whitespaces at the start and end are trimmed.
`Emails should be of the format local-part@domain and adhere to the following constraints: …` | Ensure that the email specified is not blank and adheres to the constraints specified. Whitespaces at the start and end are trimmed.
`This person already exists in the address book.` | Ensure that the new name specified does not match an existing person.
`Multiple values specified for the following single-valued field(s): …` | Ensure that there are no duplicate fields for name, email, address and phone number.

<div style="page-break-after: always;"></div>

#### 1.2. Listing all persons: `listPerson`

[Back to Table of Contents](#table-of-contents)

You can view your entire contact list, so that you can quickly access and manage your connections.

<h4>Format:</h4>

```
listPerson
```

<h4>Alias:</h4>

```
lp
```

<h4>Example:</h4>

- `listPerson`<br><br>
  ![listPerson success](images/output/listPerson_success.png)

  *<center>CoordiMate shows you the full contact list.</center>*

<div style="page-break-after: always;"></div>

#### 1.3. Editing a person: `editPerson`

[Back to Table of Contents](#table-of-contents)

You can change the details of an existing person in your contact list, so that you can keep information in your contact list constantly up-to-date.

<h4>Format:</h4>

```
editPerson INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…
```

<h4>Alias:</h4>

```
ep
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The person at this index in the displayed person list is edited. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
| `NAME` | `n/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Full name of the individual.|
| `PHONE_NUMBER` | `p/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Phone number of the individual.|
| `EMAIL` | `e/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Email address of the individual.|
| `ADDRESS` | `a/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Physical address of the individual.|
| `TAG` | `t/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Tag(s) for the individual.|
{: .field-table}

- Existing values will be updated to the input values.
- At least one of the optional fields must be provided for the command to be valid.
- A person is uniquely identified by their `NAME`. This field is case sensitive.
- You may add multiple tags to a person by specifying the `t/` prefix multiple times.
  - Note that this will replace all existing tags of the task. If you wish to add to the existing tags of the task, use the [`addTagPerson` command](#31-adding-tags-to-a-person-addtagperson) instead.
  - Specifying `t/` without any tags will clear all existing tags of the task.

<h4>Example:</h4>

- `editPerson 1 p/91234567 e/johndoe@example.com`<br><br>
  ![editPerson success](images/output/editPerson_success.png)

  *<center>CoordiMate edits the phone number and email of the 1st person in the contact list.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer.
`At least one field to edit must be provided.` | Ensure that at least one of name, phone, email, address or tag is specified.
`The person index provided is invalid` | Ensure that the index provided is within the valid range.
`Names should only contain alphanumeric characters and spaces, and it should not be blank` | Ensure that the name specified is not blank and contain only alphanumeric characters. Whitespaces at the start and end are trimmed.
`Phone numbers should only contain numbers, and it should be at least 3 digits long` | Ensure that the phone number specified is not blank, contain only numbers and at least 3 digits long. Whitespaces at the start and end are trimmed.
`Addresses can take any values, and it should not be blank` | Ensure that the address specified is not blank. Whitespaces at the start and end are trimmed.
`Emails should be of the format local-part@domain and adhere to the following constraints: …` | Ensure that the email specified is not blank and adheres to the constraints specified. Whitespaces at the start and end are trimmed.
`This person already exists in the address book.` | Ensure that the new name specified does not match an existing person.
`Multiple values specified for the following single-valued field(s): …` | Ensure that there are no duplicate fields for name, email, address and phone number.

<div style="page-break-after: always;"></div>

#### 1.4. Finding a specific person: `findPerson`

[Back to Table of Contents](#table-of-contents)

You can find a person by their name, so that you can quickly locate their contact details.

<h4>Format:</h4>

```
findPerson KEYWORD [MORE_KEYWORDS]…
```

<h4>Alias:</h4>

```
fp
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `KEYWORD` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The keyword to search for. |
| `MORE_KEYWORDS` | No prefix | <img width=33px src='assets/svg/ug/not_required.svg'> | Additional keywords to search for. |
{: .field-table}

- Persons matching at least one `KEYWORD` in their names will be shown. This field is case insensitive and the order of keywords does not matter.
- Only full words will be matched, e.g. the keyword `Alex` will not match the name `Alexis`.

{% include admonition.html type="note" title="Note" body="

This command hides all persons that do not match the search criteria. <br>
(i.e. If no persons match the search criteria, the list will be empty.)<br><br>
To reset the persons view, simply run the <code>listPerson</code> command to list all persons.

" %}

<h4>Example:</h4>

- `findPerson alex yu`<br><br>
  ![findPerson success with a list](images/output/findPerson_success.png)

  *<center>CoordiMate finds all persons whose names contains either <code>alex</code> or <code>yu</code>.</center>*

<h4>Potential Error:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that a keyword is provided.
{: .errors-table}

<div style="page-break-after: always;"></div>

#### 1.5. Deleting a person entry: `deletePerson`

[Back to Table of Contents](#table-of-contents)

You can remove a person from your contact list, so that you can remove contacts that you no longer need.

<h4>Format:</h4>

```
deletePerson INDEX
```

<h4>Alias:</h4>

```
dp
```

<h4>Fields:</h4>

| Fields  | Prefix | Required | Remarks |
|---------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The person at this index in the displayed person list is deleted. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
{: .field-table}

{% include admonition.html type="warning" title="Warning" body="

This person will be removed from your contact list immediately. This action is irreversible.

" %}

<h4>Example:</h4>

- `listPerson` followed by `deletePerson 2`.<br><br>
  ![deletePerson_success_with_listPerson](images/output/deletePerson_success.png)

  *<center>CoordiMate deletes the person at the index <code>2</code>.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer.
`The person index provided is invalid` | Ensure that the index provided is within the valid range.

<div style="page-break-after: always;"></div>

#### 1.6. Deleting all person entries: `deleteAllPerson`

[Back to Table of Contents](#table-of-contents)

You can delete all persons in your contact list, so that you can restart easily with a clean slate and an empty contact list.

<h4>Format:</h4>

```
deleteAllPerson
```

<h4>Alias:</h4>

```
dap
```

{% include admonition.html type="warning" title="Warning" body="

All persons will be removed from your contact list immediately. This action is irreversible.

" %}

<h4>Example:</h4>

- `deleteAllPerson`<br><br>
  ![deleteAllPerson success](images/output/deleteAllPerson_success.png)

  *<center>CoordiMate deletes all persons in your contact list.</center>*

---

<div style="page-break-after: always;"></div>

### 2. Commands to Manage Tasks

#### 2.1. Adding a task: `addTask`

[Back to Table of Contents](#table-of-contents)

You can add a task to your task list, so that you can keep track of important things to do.

<h4>Format:</h4>

```
addTask T/TITLE n/NOTE [t/TAG]…
```

<h4>Alias:</h4>

```
at
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `TITLE` | `T/` | <img width=30px src='assets/svg/ug/required.svg'> | Title describing the task. |
| `NOTE` | `n/` | <img width=30px src='assets/svg/ug/required.svg'> | Note providing details about the task. |
| `TAG` | `t/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Tag(s) for the task.|
{: .field-table}

- A task is uniquely identified by the combination of its `TITLE` and `NOTE`. These fields are case sensitive.
- You may add multiple tags to a task by specifying the `t/` prefix multiple times.

<h4></h4>

<h4>Example:</h4>

- `addTask T/Book rooms n/For day 2 t/orientation t/bookings`<br><br>
  ![addTask_success](images/output/addTask_success.png)

  *<center>CoordiMate adds a new task with the corresponding details.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the title and note are specified.
`Titles/Notes can take any value, as long as it is not blank and does not start with a whitespace` | Ensure that the title and note specified are not blank. Whitespaces at the start and end are trimmed.
`Multiple values specified for the following single-valued field(s): …` | Ensure that there are no duplicate fields for title and note.

<div style="page-break-after: always;"></div>

#### 2.2. Listing all tasks: `listTask`

[Back to Table of Contents](#table-of-contents)

You can view your entire task list, so that you can stay organized and on top of your responsibilities.

<h4>Format:</h4>

```
listTask
```

<h4>Alias:</h4>

```
lt
```

<h4>Example:</h4>

- `listTask`<br><br>
  ![listTask success](images/output/listTask_success.png)

  *<center>CoordiMate shows you the full task list.</center>*

<div style="page-break-after: always;"></div>

#### 2.3. Editing a task: `editTask`

[Back to Table of Contents](#table-of-contents)

You can edit a task in your task list, so that you can ensure task details are up-to-date with the latest information.

<h4>Format:</h4>

```
editTask INDEX [T/TITLE] [n/NOTE] [t/TAG]…
```

<h4>Alias:</h4>

```
et
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The task at this index in the displayed task list is edited. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
| `TITLE` | `T/` | <img width=30px src='assets/svg/ug/not_required.svg'> | Title describing the task. |
| `NOTE` | `n/` | <img width=30px src='assets/svg/ug/not_required.svg'> | Note providing details about the task. |
| `TAG` | `t/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Tag(s) for the task.|
{: .field-table}

- At least one of the optional fields must be provided for the command to be valid.
- Fields that are not specified will preserve their existing values.
- You may edit the task to have multiple tags by specifying the `t/` prefix multiple times.
- If you specify at least one tag with this command, all old tags will be replaced.
  - If you wish to add to the existing tags of the task, use the [`addTagTask` command](#32-adding-tags-to-a-task-addtagtask) instead.
  - Specifying `t/` without any tags will clear all existing tags of the task.

<h4>Example:</h4>

- `editTask 2 T/Book room n/By Friday t/orientation`<br><br>
  ![editTask_success](images/output/editTask_success.png)

  *<center>CoordiMate edits the title, note, and tags of the 2nd task in the task list.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer.
`At least one field to edit must be provided.` | Ensure that at least one of title, note, or tag is specified.
`Titles/Notes can take any value, as long as it is not blank and does not start with a whitespace` | Ensure that the title and/or note specified are not blank. Whitespaces at the start and end are trimmed.
`This task already exists in the address book.` | Ensure that the new title and note specified do not match an existing task.
`Multiple values specified for the following single-valued field(s): …` | Ensure that there are no duplicate fields for title and note.

<div style="page-break-after: always;"></div>

#### 2.4. Finding a specific task: `findTask`

[Back to Table of Contents](#table-of-contents)

You can quickly locate tasks that contains your specified keywords in their title and/or note.

<h4>Format:</h4>

```
findTask KEYWORD [MORE_KEYWORDS]…
```

<h4>Alias:</h4>

```
ft
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `KEYWORD` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The keyword to search for. |
| `MORE_KEYWORDS` | No prefix | <img width=33px src='assets/svg/ug/not_required.svg'> | Additional keywords to search for. |
{: .field-table}

- Tasks matching at least one `KEYWORD` in their title or note will be shown. This field is case insensitive and the order of keywords does not matter.
- Only full words will be matched, e.g. the keyword `Photo` will not match the title `Photography`.

{% include admonition.html type="note" title="Note" body="

This command hides all tasks that do not match the search criteria. <br>
(i.e. If no tasks match the search criteria, the list will be empty.)<br><br>
To reset the tasks view, simply run the <code>listTask</code> command to list all tasks.

" %}

<h4>Example:</h4>

- `findTask Find Finale`<br><br>
  ![findTask_success](images/output/findTask_success.png)

  *<center>CoordiMate finds all tasks whose titles or notes contain either <code>Find</code> or <code>Finale</code>.</center>*

<h4>Potential Error:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that a keyword is provided.

<div style="page-break-after: always;"></div>

#### 2.5 Deleting a task entry: `deleteTask`

[Back to Table of Contents](#table-of-contents)

You can remove a task from your task list, so that you can remove tasks that you no longer need.

<h4>Format:</h4>

```
deleteTask INDEX
```

<h4>Alias:</h4>

```
dt
```

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the task list currently displayed.
- The index **_must be a positive integer_** 1, 2, 3, … , 2147483647.

<h4>Fields:</h4>

| Fields  | Prefix | Required | Remarks |
|---------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The task at this index in the displayed task list is deleted. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
{: .field-table}

{% include admonition.html type="warning" title="Warning" body="

The task will be removed from your task list immediately. This action is irreversible.

" %}

<h4>Example:</h4>

- `listTask` followed by `deleteTask 2`<br><br>
  ![deleteTask_success](images/output/deleteTask_success.png)

  *<center>CoordiMate deletes the task at the index <code>2</code>.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer.
`The task index provided is invalid` | Ensure that the index provided is within the valid range.

<div style="page-break-after: always;"></div>

#### 2.6. Deleting all task entries: `deleteAllTask`

[Back to Table of Contents](#table-of-contents)

You can remove all tasks from your task list, so that you can restart easily with a clean slate and an empty task list.

<h4>Format:</h4>

```
deleteAllTask
```

<h4>Alias:</h4>

```
dat
```

{% include admonition.html type="warning" title="Warning" body="

All tasks will be removed from your task list immediately. This action is irreversible.

" %}

<h4>Example:</h4>

- `deleteAllTask`<br><br>
  ![deleteAllTask success](images/output/deleteAllTask_success.png)

  *<center>CoordiMate deletes all tasks in the task list.</center>*

<div style="page-break-after: always;"></div>

#### 2.7. Marking a task as done: `markTask`

[Back to Table of Contents](#table-of-contents)

You can indicate that a specific task as completed, so that you can keep track of task progress and identify tasks that are done.

<h4>Format:</h4>

```
markTask INDEX
```

<h4>Alias:</h4>

```
mt
```

<h4>Fields:</h4>

| Fields  | Prefix | Required | Remarks |
|---------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The task at this index in the displayed task list is marked as done. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
{: .field-table}

- Tasks are marked as not done by default.

<h4>Example:</h4>

- `listTask` followed by `markTask 1`<br><br>
  ![markTask_success](images/output/markTask_success.png)

  *<center>CoordiMate marks the task at the index <code>1</code> in the task list as done.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer.
`The task index provided is invalid` | Ensure that the index provided is within the valid range.
`This task is already marked as done in the task list.` | Ensure that the task specified for marking is not already done in the task list.

<div style="page-break-after: always;"></div>

#### 2.8. Marking a task as not done: `unmarkTask`

[Back to Table of Contents](#table-of-contents)

You can indicate that a specific task as not completed, so that you can keep track of task progress and identify tasks that are pending further action or completion.

<h4>Format:</h4>

```
unmarkTask INDEX
```

<h4>Alias:</h4>

```
ut
```

<h4>Fields:</h4>

| Fields  | Prefix | Required | Remarks |
|---------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The task at this index in the displayed task list is marked as not done. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
{: .field-table}

- Tasks are marked as not done by default.

<h4>Example:</h4>

- `listTask` followed by `unmarkTask 1`<br><br>
  ![unmarkTask_success](images/output/unmarkTask_success.png)

  *<center>CoordiMate marks the task at the index <code>1</code> in the task list as not done.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer.
`The task index provided is invalid` | Ensure that the index provided is within the valid range.
`This task is already marked as not done in the task list.` | Ensure that the specified task is not already marked as not done in the task list.

<div style="page-break-after: always;"></div>

#### 2.9. Finding all tasks that are done: `findDone`

[Back to Table of Contents](#table-of-contents)

You can filter the task list to locate all completed tasks, so that you can keep track of task progress and the number of completed tasks with ease.

<h4>Format:</h4>

```
findDone
```

<h4>Alias:</h4>

```
fd
```

{% include admonition.html type="note" title="Note" body="

This command hides all Tasks that are not done. <br>
(i.e. If no Tasks are not done, the list will be empty.)<br><br>
To reset the Tasks view, simply run the <code>listTask</code> command to list all Tasks.

" %}

<h4>Example:</h4>

- `findDone`<br><br>
  ![findDone_success](images/output/findDone_success.png)

  *<center>CoordiMate finds all tasks whose status is done.</center>*

<div style="page-break-after: always;"></div>

#### 2.10. Finding all tasks that are not done: `findNotDone`

[Back to Table of Contents](#table-of-contents)

You can filter the task list to locate all not completed tasks, so that you can keep track of task progress and the number of not completed tasks with ease.

<h4>Format:</h4>

```
findNotDone
```

<h4>Alias:</h4>

```
fnd
```

{% include admonition.html type="note" title="Note" body="

This command hides all Tasks that are done. <br>
(i.e. If no Tasks are done, the list will be empty.)<br><br>
To reset the Tasks view, simply run the <code>listTask</code> command to list all Tasks.

" %}

<h4>Example:</h4>

- `findNotDone`<br><br>
  ![findNotDone_success](images/output/findNotDone_success.png)

  *<center>CoordiMate finds all tasks whose status is not done.</center>*

<div style="page-break-after: always;"></div>

#### 2.11. Deleting all tasks that are done: `deleteAllDone`

[Back to Table of Contents](#table-of-contents)

You can remove all completed tasks in the task list, so that you can focus on not completed tasks and maintain an organized and clutter-free task list.

<h4>Format:</h4>

```
deleteAllDone
```

<h4>Alias:</h4>

```
dad
```

- After completing the deletion of completed tasks, the task list will automatically revert back to displaying all tasks, ensuring you have a comprehensive overview of your remaining to-dos.

{% include admonition.html type="warning" title="Warning" body="

All completed tasks will be removed from your task list immediately. This action is irreversible.

" %}

<h4>Example:</h4>

- `deleteAllDone`<br><br>
  ![deleteAllDone_success](images/output/deleteAllDone_success.png)

  *<center>CoordiMate deletes all tasks whose status is done.</center>*

<h4>Potential Error:</h4>

 Error message | How to resolve
---------------|---------------
`No Done tasks found in task list` | Ensure that there are tasks marked as done in your task list with [`findDone` command](#29-finding-all-tasks-that-are-done-finddone).

---

<div style="page-break-after: always;"></div>

### 3. Commands to Manage Tags

#### 3.1. Adding tag(s) to a person: `addTagPerson`

[Back to Table of Contents](#table-of-contents)

You can add tag(s) to a person, so that you can simply add to the existing list of tags of the indexed person.

<h4>Format:</h4>

```
addTagPerson INDEX t/TAG [t/MORE_TAGS]…
```

<h4>Alias:</h4>

```
atagp
```

<h4>Fields:</h4>

| Fields  | Prefix | Required | Remarks |
|---------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The person at this index in the displayed person list is edited. The index must be a positive integer 1, 2, 3, … , 2147483647. |
| `TAG` | `t/` | <img width=30px src='assets/svg/ug/required.svg'> | The tag to be added to the person. |
| `MORE_TAGS` | `t/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Additional tag(s) to be added to the person. |
{: .field-table}

- A tag is uniquely identified by the `TAG`. This field is case sensitive.

<h4>Example:</h4>

- `addTagPerson 1 t/friends t/expensive`.<br><br>
  ![addTagPerson success](images/output/addTagPerson_success.png)

  *<center>CoordiMate adds tag <code>expensive</code> to the person at the index <code>1</code>, while <code>friends</code> is not added because it already exists in the list of tags.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index and tag are both specified. The index should be correctly specfiied as an integer.
`Tags names should be alphanumeric` | Ensure that the tag is specified and is alphanumeric.
`The person index provided is invalid` | Ensure that the index provided is within the valid range.

<div style="page-break-after: always;"></div>

#### 3.2. Adding tag(s) to a task: `addTagTask`

[Back to Table of Contents](#table-of-contents)

You can add tag(s) to a task, so that you can simply add to the existing list of tags of the indexed task.

<h4>Format:</h4>

```
addTagTask INDEX t/TAG [t/MORE_TAGS]…
```

<h4>Alias:</h4>

```
atagt
```

<h4>Fields:</h4>

| Fields  | Prefix | Required | Remarks |
|---------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The task at this index in the displayed task list is edited. The index must be a positive integer 1, 2, 3, … , 2147483647. |
| `TAG` | `t/` | <img width=30px src='assets/svg/ug/required.svg'> | The tag to be added to the task. |
| `MORE_TAGS` | `t/` | <img width=33px src='assets/svg/ug/not_required.svg'> | Additional tag(s) to be added to the task. |
{: .field-table}

- A tag is uniquely identified by the `TAG`. This field is case sensitive.

<h4>Example:</h4>

- `addTagTask 1 t/day1 t/day2`.<br><br>
  ![addTagTask success](images/output/addTagTask_success.png)

  *<center>CoordiMate adds tag <code>day2</code> to the task at the index <code>1</code>, while <code>day1</code> is not added because it already exists in the list of tags.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index and tag are both specified. The index should be correctly specified as an integer.
`Tags names should be alphanumeric` | Ensure that the tag is specified and is alphanumeric.
`The task index provided is invalid` | Ensure that the index provided is within the valid range.

<div style="page-break-after: always;"></div>

#### 3.3. Listing all tags: `listTag`

[Back to Table of Contents](#table-of-contents)

You can view the tags in your contact list and task list and their frequencies, so that you can keep track of the tags you have used.

<h4>Format:</h4>

```
listTag
```

<h4>Alias:</h4>

```
ltag
```

- The list is sorted by frequency of each tag in descending order.

- If two tags have the same frequency, the tags are sorted in the order as defined in the [American Standard Code for Information Interchange (ASCII)](#american-standard-code-for-information-interchange-ascii).

<h4>Example:</h4>

- `listTag`<br><br>
  ![listTag success](images/output/listTag_success.png)

  *<center>CoordiMate shows you the list of all tags used and their frequencies.</center>*

<div style="page-break-after: always;"></div>

#### 3.4. Finding persons and tasks with any matching tag(s): `findTag`

[Back to Table of Contents](#table-of-contents)

You can extend your search with keywords, so that you can locate any person or task with at least one matching tag.

<h4>Format:</h4>

```
findTag KEYWORD [MORE_KEYWORDS]…
```

<h4>Alias:</h4>

```
ftag
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `KEYWORD` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The keyword to search for tags.|
| `MORE_KEYWORDS` | No prefix | <img width=33px src='assets/svg/ug/not_required.svg'> | Additional keyword(s) to search for tags.|
{: .field-table}

- Persons and tasks matching at least one of `KEYWORD` in their tag(s) will be returned (i.e. OR search). This field is case insensitive and the order of keywords does not matter.
- Only full words will be matched, e.g. `findTag catering` will not match persons and tasks which tag(s) contain only `foodcatering`.

{% include admonition.html type="note" title="Note" body="

This command hides all persons and tasks that do not match the search criteria. <br>
(i.e. If no persons or tasks match the search criteria, the list will be empty.)<br><br>
To reset the Persons and Tasks view, simply run the <code>listAll</code> command to list all persons and tasks.

" %}

<h4>Example:</h4>

- `findTag catering orientation`<br><br>
  ![findTag_success](images/output/findTag_success.png)

  *<center>CoordiMate finds all persons and tasks matching any of <code>catering</code> or <code>orientation</code> tags.</center>*

<h4>Potential Error:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that a keyword is provided.

<div style="page-break-after: always;"></div>

#### 3.5. Finding persons and tasks with all matching tag(s): `findAllTag`

[Back to Table of Contents](#table-of-contents)

You can narrow your search using multiple keywords, so that you can find only those contacts and tasks that include every tag you need.

<h4>Format:</h4>

```
findAllTag KEYWORD [MORE_KEYWORDS]…
```

<h4>Alias:</h4>

```
fatag
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `KEYWORD` | No prefix | <img width=30px src='assets/svg/ug/required.svg'>  | The keyword to search for tags.|
| `MORE_KEYWORDS` | No prefix | <img width=33px src='assets/svg/ug/not_required.svg'> | Additional keyword(s) to search for tags.|
{: .field-table}

- Persons and tasks matching all `KEYWORD` in their tag(s) will be returned (i.e. AND search). This field is case insensitive and the order of keywords does not matter.
- Only full words will be matched, e.g. `findAllTag catering` will not match persons and tasks which tag(s) contain only `foodcatering`.

{% include admonition.html type="note" title="Note" body="

This command hides all persons and tasks that do not match the search criteria. <br>
(i.e. If no persons or tasks match the search criteria, the list will be empty.)<br><br>
To reset the Persons and Tasks view, simply run the <code>listAll</code> command to list all persons and tasks.

" %}

<h4>Example:</h4>

- `findAllTag catering orientation`<br><br>
  ![findAllTag_success](images/output/findAllTag_success.png)

  *<center>CoordiMate finds persons and tasks matching all of <code>catering</code> and <code>orientation</code> in their tag(s).</center>*

<h4>Potential Error:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that a keyword is provided.

<div style="page-break-after: always;"></div>

#### 3.6. Deleting tag(s) from a person: `deleteTagPerson`

[Back to Table of Contents](#table-of-contents)

You can delete tag(s) from a person in your contacts list, so that you can remove unwanted or irrelevant tags.

<h4>Format:</h4>

```
deleteTagPerson INDEX t/TAG [t/MORE_TAGS]…
```

<h4>Alias:</h4>

```
dtagp
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The person at this index in the displayed person list is edited. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
| `TAG` | `t/` | <img width=33px src='assets/svg/ug/required.svg'> | Tag(s) to be deleted for the person.|
{: .field-table}

- You may delete multiple tags from a person by specifying the `t/` prefix multiple times.
- Tags that do not belong to the person will be ignored.

<h4>Example:</h4>

- `deleteTagPerson 2 t/EragonSounds t/soundSystems t/catering`<br><br>
  ![deleteTagPerson_success](images/output/deleteTagPerson_success.png)

  *<center>CoordiMate deletes <code>EragonSounds</code> and <code>soundSystems</code> tags from the 2nd person, and ignores the <code>catering</code> tag.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer. At least one tag must be specified.
`The person index provided is invalid` | Ensure that the index specified is in the range of the displayed number of persons.
`Tags names should be alphanumeric` | Ensure that the tags specified are alphanumeric and not empty. Whitespaces are trimmed, but spaces between characters are not allowed.

<div style="page-break-after: always;"></div>

#### 3.7. Deleting tag(s) from a task: `deleteTagTask`

[Back to Table of Contents](#table-of-contents)

You can delete tag(s) from a task in your task list, so that you can remove unwanted or irrelevant tags.

<h4>Format:</h4>

```
deleteTagTask INDEX t/TAG [t/MORE_TAGS]…
```

<h4>Alias:</h4>

```
dtagt
```

<h4>Fields:</h4>

| Fields | Prefix | Required | Remarks |
|--------|--------|:--------:|---------|
| `INDEX` | No prefix | <img width=30px src='assets/svg/ug/required.svg'> | The task at this index in the displayed task list is edited. The index must be a positive integer (i.e. 1, 2, 3, … , 2147483647). |
| `TAG` | `t/` | <img width=33px src='assets/svg/ug/required.svg'> | Tag(s) to be deleted for the task.|
{: .field-table}

- You may delete multiple tags from a task by specifying the `t/` prefix multiple times.
- Tags that do not belong to the task will be ignored.

<h4>Example:</h4>

- `deleteTagTask 2 t/finance t/orientation t/caterer`<br><br>
  ![deleteTagTask_success](images/output/deleteTagTask_success.png)

  *<center>CoordiMate deletes <code>finance</code> and <code>orientation</code> tags from the 2nd task, and ignores the <code>caterer</code> tag.</center>*

<h4>Potential Errors:</h4>

 Error message | How to resolve
---------------|---------------
`Invalid command format!…` | Ensure that the index is correctly specified as an integer. At least one tag must be specified.
`The task index provided is invalid` | Ensure that the index specified is in the range of the displayed number of tasks.
`Tags names should be alphanumeric` | Ensure that the tags specified are alphanumeric and not empty. Whitespaces are trimmed, but spaces between characters are not allowed.

---

<div style="page-break-after: always;"></div>

### 4. General Commands

#### 4.1. Viewing help: `help`

[Back to Table of Contents](#table-of-contents)

You can view a link to access the user guide at any time, so that you can refer to documentation to understand the existing features.

<h4>Format:</h4>

```
help
```

<h4>Alias:</h4>

```
h
```

<h4>Example:</h4>

- `help`<br><br>
  ![help message](images/output/help_success.png)

  *<center>CoordiMate opens a new window with a link to the user guide.</center>*

<div style="page-break-after: always;"></div>

#### 4.2. Listing all persons and tasks: `listAll`

[Back to Table of Contents](#table-of-contents)

You can list all persons and tasks in your contact list and task list at the same time.

<h4>Format:</h4>

```
listAll
```

<h4>Alias:</h4>

```
la
```

<h4>Example:</h4>

- `listAll`<br><br>
  ![listAll success](images/output/listAll_success.png)

  *<center>CoordiMate shows all the persons and tasks in your contact list and task list.</center>*

<div style="page-break-after: always;"></div>

#### 4.3. Exiting the program: `exit`

[Back to Table of Contents](#table-of-contents)

You can exit the application, so that you can close the application window and stop the program.

<h4>Format:</h4>

```
exit
```

<h4>Alias:</h4>

```
e
```

<h4>Example:</h4>

- `exit`

<br><br>

---

<div style="page-break-after: always;"></div>

## FAQ

[Back to Table of Contents](#table-of-contents)

{% include admonition.html type="question" title="Question" body="

<b>Q: How do I backup/restore my data?</b><br>

<b>A: </b>To make a backup:<br>

1. Locate the data file at <code>[CoordiMate JAR file location]/data/addressbook.json</code> on your current computer. <br>
2. Copy the data file to a safe location.<br><br>

To restore from a backup data file:<br>

1. Copy the data file to <code>[CoordiMate JAR file location]/data/addressbook.json</code>.<br>
2. Launch CoordiMate, and your data will be automatically imported.

" %}

<br><br>

---

<div style="page-break-after: always;"></div>

## Glossary

[Back to Table of Contents](#table-of-contents)

### Alphanumeric

A combination of letters and numbers only, no spaces or special characters.

For more information, see <a href='https://en.wikipedia.org/wiki/Alphanumericals' rel='noopener noreferrer' target='_blank'>Alphanumericals</a> on Wikipedia.

Example: For tags, entries like `day1` or `day2` are valid, while entries like `day 1` or `day-2` are invalid.

### American Standard Code for Information Interchange (ASCII)

The American Standard Code for Information Interchange (ASCII) is a character encoding standard that is well understood by computers. <br>
In ASCII ordering, uppercase letters come before lowercase letters (<code>A</code> comes before <code>a</code>), unlike in alphabetical ordering where case does not matter. <br>

Example: After a `listTag` command, `Finale` tag is listed before `finale` in the command result.

For more information, see <a href='https://en.wikipedia.org/wiki/ASCII' rel='noopener noreferrer' target='_blank'>ASCII</a> on Wikipedia.

### Command Line Interface

A text-based interface used to interact with a computer or software by entering commands into a terminal or command prompt.

Example: In order to delete a task, user types `deleteTask 1` and pressing Enter. The task specified is deleted.

For more information on CoordiMate's commands, read more about it [here](#usage).

### Graphical User Interface

A visual interface that allows users to interact with software using graphical elements like icons and windows, making it more user-friendly.

Example: After `listAll` command, users can scroll the contact list and task list to view information in both lists and see different icons for done and not done tasks respectively.

For more information on CoordiMate's GUI, read more about it [here](#understanding-our-gui).

### Integer

A whole number without fractions or decimals, commonly used in CoordiMate to denote the position of an item in a collection or list.

The valid range for accepted integers is 1, 2, 3, … , 2147483647.

Example: In a list of 3 alphabets containing (“a”, “b”, “c”) in that order,
  - Index 1 refers to “a”
  - Index 2 refers to “b”
  - Index 3 refers to “c”

<br><br>

---

<div style="page-break-after: always;"></div>

## Known Issues

[Back to Table of Contents](#table-of-contents)

{% include admonition.html type="bug" title="Known Issue" body="

<b>Issue:</b> CoordiMate does not open on the correct screen. <br><br>

<b>Workaround:</b> To resolve this issue, you can simply delete the <code>preferences.json</code> file that is in the same folder as the CoordiMate JAR file.

"%}

{% include admonition.html type="bug" title="Known Issue" body="

<b>Issue:</b> The application becomes laggy after I enter a very long string.<br><br>

<b>Workaround:</b> As a workaround, we recommend that you keep inputs (such as a person's name or a task's title) to less than 200 characters each.

"%}

{% include admonition.html type="bug" title="Known Issue" body="

<b>Issue:</b> I am unable to input a name that contains special characters (e.g. <code>Ravi s/o Veegan</code>).<br><br>

<b>Workaround:</b> As a workaround, we suggest that you omit the special characters in the name. (e.g. <code>Ravi so Veegan</code>)

"%}

<br><br>

---

<div style="page-break-after: always;"></div>

## Command Summary

### Managing Persons

[Back to Table of Contents](#table-of-contents)

 Action | Format | Example | Alias
--------|--------|---------|------
[**Add Person**](#11-adding-a-person-addperson)| `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…` | `addPerson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` | `ap`
[**List All Person**](#12-listing-all-persons-listperson) | `listPerson` | `listPerson` | `lp`
[**Edit Person**](#13-editing-a-person-editperson) | `editPerson INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` | `editPerson 1 p/91234567 e/johndoe@example.com` | `ep`
[**Find Person**](#14-finding-a-specific-person-findperson) | `findPerson KEYWORD [MORE_KEYWORDS]…` | `findPerson John` | `fp`
[**Delete Person**](#15-deleting-a-person-entry-deleteperson) | `deletePerson INDEX` | `deletePerson 1` | `dp`
[**Delete All Person**](#16-deleting-all-person-entries-deleteallperson) | `deleteAllPerson` | `deleteAllPerson` | `dap`

<div style="page-break-after: always;"></div>

### Managing Tasks

[Back to Table of Contents](#table-of-contents)

 Action | Format | Example | Alias
--------|--------|---------|------
[**Add Task**](#21-adding-a-task-addtask) | `addTask T/TITLE n/NOTE [t/TAG]` | `addTask T/Get Flowers n/Wedding Anniversary` | `at`
[**List All Task**](#22-listing-all-tasks-listtask) | `listTask` | `listTask` | `lt`
[**Edit Task**](#23-editing-a-task-edittask) | `editTask INDEX [T/TITLE] [n/NOTE] [t/TAG]` | `editTask 1 T/Call Caterer n/Wedding Dinner` | `et`
[**Find Task**](#24-finding-a-specific-task-findtask) | `findTask KEYWORD [MORE_KEYWORDS]…` | `findTask Call Wedding` | `ft`
[**Delete Task**](#25-deleting-a-task-entry-deletetask) | `deleteTask INDEX` | `deleteTask 1` | `dt`
[**Delete All Task**](#26-deleting-all-task-entries-deletealltask) | `deleteAllTask` | `deleteAllTask` | `dat`
[**Mark Task**](#27-marking-a-task-as-done-marktask) | `markTask INDEX` | `markTask 1` | `mt`
[**Unmark Task**](#28-marking-a-task-as-not-done-unmarktask) | `unmarkTask INDEX` | `unmarkTask 1` | `ut`
[**Find Done Task**](#29-finding-all-tasks-that-are-done-finddone) | `findDone` | `findDone` | `fd`
[**Find Not Done Task**](#210-finding-all-tasks-that-are-not-done-findnotdone) | `findNotDone` | `findNotDone` | `fnd`
[**Delete All Done Task**](#211-deleting-all-tasks-that-are-done-deletealldone) | `deleteAllDone` | `deleteAllDone` | `dad`

<div style="page-break-after: always;"></div>

### Managing Tags

[Back to Table of Contents](#table-of-contents)

 Action | Format | Example | Alias
--------|--------|---------|------
[**Add Tag(s) to a Person**](#31-adding-tags-to-a-person-addtagperson) | `addTagPerson INDEX [t/TAG]…` | `addTagPerson 1 t/friends` | `atagp`
[**Add Tag(s) to a Task**](#32-adding-tags-to-a-task-addtagtask) | `addTagTask INDEX [t/TAG]…` | `addTagTask 1 t/day1` | `atagt`
[**List All Tags**](#33-listing-all-tags-listtag) | `listTag` | `listTag` | `ltag`
[**Find Tags**](#34-finding-persons-and-tasks-with-any-matching-tags-findtag) | `findTag KEYWORD [MORE_KEYWORDS]…` | `findTag orientation` | `ftag`
[**Find All Tags**](#35-finding-persons-and-tasks-with-all-matching-tags-findalltag) | `findAllTag KEYWORD [MORE_KEYWORDS]…` | `findAllTag orientation` | `fatag`
[**Delete Tag(s) from Person**](#36-deleting-tags-from-a-person-deletetagperson) | `deleteTagPerson INDEX t/TAG [t/MORE_TAGS]…` | `deleteTagPerson 1 t/catering` | `dtagp`
[**Delete Tag(s) from Task**](#37-deleting-tags-from-a-task-deletetagtask) | `deleteTagTask INDEX t/TAG [t/MORE_TAGS]…` | `deleteTagTask 1 t/catering` | `dtagt`

<div style="page-break-after: always;"></div>

### General

[Back to Table of Contents](#table-of-contents)

 Action | Format | Example | Alias
--------|--------|---------|------
[**View Help**](#41-viewing-help-help) | `help` | `help` | `h`
[**List All Person and Task**](#42-listing-all-persons-and-tasks-listall) | `listAll` | `listAll` | `la`
[**Exit**](#43-exiting-the-program-exit) | `exit` | `exit` | `e`
