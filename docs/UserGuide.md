---
layout: page
title: User Guide
---

**ConText** is a **desktop app designed for managing contacts of NUS SoC students.** ConText leverages a Command Line Interface (CLI) combined with a modern Graphical User Interface (GUI) to offer speedy contact management. If you're familiar with typing commands, you'll find ConText incredibly efficient.

* Table of Contents
  {:toc}

---

## Getting Started

Upon launching ConText, you'll be presented with a clean interface. At the top is the command input box, and below it, you'll find a list of your contacts. The bottom of the window will display feedback from the commands you input.

### Entering text commands

1. Click on the command input box at the top of the application.
2. Type your command.
3. Press `Enter` to run the command.

Feedback will appear at the bottom. If there's an error in your command, it will be highlighted in red in the input box. Simply modify and retry.

---

### Adding a Contact: `add`

Easily add a new contact to your list with the `add` command.

**Format:**   
`add /name FULL_NAME /phone PHONE_NUMBER`

**Examples:**
- `add /name John Doe /phone 98765432`

**Acceptable values:**
- `FULL_NAME`: Names can contain spaces.
- `PHONE_NUMBER`: Adheres to the format /\+?\d+/u (optional '+' followed by digits)

**Expected outputs:**
- Success: `<FULL_NAME>’s contact is successfully added.`
- Error: `Given input is not valid.`

---

### Viewing All Contacts: `list`

To view all your stored contacts.

**Format:**   
`list`

**Expected output:**  
Your contacts will appear in the central panel of the GUI, each showing their name, phone number, and any associated tags.

---

### Deleting a Contact: `delete`

If you need to remove a contact, use the `delete` command.

**Format:**   
`delete /name FULL_NAME`

**Examples:**
- `delete /name John Doe`

**Expected outputs:**
- Success: `<FULL_NAME>’s contact is successfully deleted.`
- Error: `Contact <FULL_NAME> not found.`

---

Future updates will introduce more features and improvements to enhance your contact management experience with ConText.
