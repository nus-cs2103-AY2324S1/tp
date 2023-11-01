---
layout: page
title: User Guide
---

**ConText** is a desktop app that allows for managing contacts quickly via text commands.
It is optimised for use via an in-app Command Line Interface (CLI), while still having the benefits of a Graphical User Interface (GUI).

It has useful features relevant to NUS SoC students:

- Tagging contacts by category: You could tag all your professors and classmates with custom tags such as "Professor", "Tutorial mate", "CS2103" etc., then filter by tag.
- Storing different ways to reach people: By adding alternate contact details, you could have local phone number, overseas phone number, Telegram, Discord etc. all in the same contact.

If you can type fast, prefer typing, and are reasonably comfortable with CLI inputs, ConText can let you manage contacts faster than traditional GUI apps.

- Table of Contents
{:toc}

---

## Quick start

1. Ensure you have [Java 11](https://openjdk.org/) or above installed.

1. Download the latest `context.jar` [here](https://github.com/AY2324S1-CS2103-W14-3/tp/releases).

1. Place the JAR file in the folder you want to use as the app's home folder.

1. Open a command terminal, `cd` into the folder you put the JAR file in, and use the `java -jar context.jar` command to run the application.\
A window should open with a GUI similar to the one below. Note how the app starts off with some sample data.\
![Ui](images/Ui.png)

1. The text box at the top of the window should be automatically selected. This is where you can type your text commands. Press <kbd>Enter</kbd> to execute them.\
The feedback from each command's execution will be displayed below the text box, and the currently displayed list of contacts is at the bottom.

1. Refer to the [Features](#Features) section below to find out about the various commands.

<div markdown="span" class="alert alert-primary">
    :bulb: **Tip:**
    You could test out some commands on the sample data.
    Once you are familiarised, feel free to use the `clear` command to delete all the sample data, and start adding your own contacts!
</div>

---

## Features

<div markdown="block" class="alert alert-info">
    **:information_source: Notes about the command format:**\

    - Some commands take in parameters.
    e.g. in `add n/NAME`, the `add` command takes in an `n/` parameter.

    - Words in `UPPER_CASE` are placeholders for values to be specified for a parameter.\
    e.g. in `n/NAME`, you should specify a `NAME` such as `n/John Doe`.

    - Parameters in square brackets are optional.\
    e.g in `n/NAME [o/NOTE]`, you could specify `n/John Doe o/Good at SE.`, or just `n/John Doe`.

    - Parameters with `…`​ after them can be specified multiple times.\
    e.g. in `[t/TAG]…​`, which is optional but can also be specified multiple times, you could specify ` ` (none specified), `t/NUS`, `t/NUS t/CS2103 course` etc.

    - Parameters can be specified in any order.\
    e.g. in `n/NAME p/PHONE_NUMBER`, the order `p/PHONE_NUMBER n/NAME` also works.

    - Extra words for commands that do not take any parameters (such as `list` or `help`) will be ignored.\
    e.g. `list 10 n/John Doe z/Extra` will be interpreted as just `list`.

    - If you are using the PDF version of this user guide, be careful when copy-pasting commands that span multiple lines. Spaces surrounding line breaks may get omitted when copied over to the app.
</div>

### Adding a Contact: `add`

Easily add a new contact to your list with the `add` command.

**Format:**
`add n/FULL_NAME p/PHONE_NUMBER e/EMAIL [o/NOTE] [t/TAG]... [a/ALTERNATE_CONTACT]...`

* A contact can have 0 or 1 note
* A contact can have any number of tags (including 0)
* A contact can have any number of alternate contacts (including 0)

**Examples:**
- `add n/John Doe p/98765432 e/john.doe@email.com`
- `add n/John Doe p/98765432 e/john.doe@email.com o/CS2103 Prof. t/NUS t/CS2103 course a/Telegram@JohnDoe`

---

### Viewing All Contacts: `list`

Display all your stored contacts.

**Format:**
`list`

---

### Deleting Contacts: `delete`

Removes one or more contacts based on their indices. The indices refer to the index numbers shown in the displayed contact list.

**Format:**
`delete INDEX [INDEX]...`

- You can delete multiple contacts at once by specifying multiple indices separated by spaces.
- Indices **must be positive integers** like 1, 2, 3, …​
- Duplicated indices are only count once.
- Invalid indices will cause abortion of the delete command.

**Examples:**
- `delete 1`
  Deletes the contact at index 1.

- `delete 1 3 5`
  Deletes the contacts at the specified indices: 1, 3, and 5.

---

### Viewing Help: `help`

Click the help button on the top to navigate to a comprehensive help manual.

---

### Locating Contacts by Name: `find`

Search and display contacts with names containing any of the given keywords.

**Format:**
`find [KEYWORD]...`

**Example:**
`find John Alice`

---

### Clearing All Entries: `clear`

Remove all contacts from your list. Apply with caution!

**Format:**
`clear`

---

### Exiting the Program: `exit`

Close the ConText application.

**Format:**
`exit`

---

### Editing a Contact: `edit`

Modify an existing contact's details in your list.

**Format:**
`edit INDEX n/FULL_NAME p/PHONE_NUMBER e/EMAIL`

**Example:**
`edit 2 n/John Doe p/98765432 e/john.doe@email.com`

---

Remember, whenever in doubt, you can always navigate to the help webpage via the `help` tab at the top.
