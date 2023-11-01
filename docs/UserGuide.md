---
layout: page
title: User Guide
---

Connectify is a **desktop app for managing clients, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Connectify can get your client management tasks done faster than traditional GUI apps.

![Ui Markup](images/Ui.png)

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start [coming soon]

--------------------------------------------------------------------------------------------------------------------
## Reading the examples in this user guide

The examples in this guide are formatted with the following conventions:
* **Command** - The command to be typed into the command box.
* **Command word(s)** - Words that specify the type of command to be executed. Written in ***bold italics***, always at the start of a line.
* **Flags** - Indicators to differentiate various parts of the command. Always take the format `$/` followed by a **Parameter**. The `$` varies depending on the type of flag. <br/> E.g., `o/` in ***interaction*** command specifies the **Outcome** of the interaction, while `l/` in ***edit*** command specifies the **Lead** of the client.
* **Parameters** - Component of the command that usually follows a **Flag**. A parameter might be written without a flag for some commands where it is clear what the parameter is referring to. <br/> E.g., `INDEX` following an ***edit*** command specifies the index of the client to be edited.

[↑ Back to table of contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features
### Quick notes about the command format
- Everything related to the command is case-sensitive unless otherwise stated.
- Words in `UPPER_CASE` are the parameters to be supplied by the user. <br/> E.g., in `n/NAME`, `NAME` is a parameter which can be used as `n/John Doe`.
- Parameters that are optional are indicated with square brackets `[OPTIONAL]`. <br/> E.g., in `[tg/TELEGRAM]`, `TELEGRAM` is an optional parameter which can be used as `tg/@john_doe` or omitted.
- Parameters specified in the command can be written in any order. <br/> E.g., `n/John Doe tg/@john_doe` is equivalent to `tg/@john_doe n/John Doe`.
- Optional parameters with `...` after the square bracket can be repeated any number of times, including zero. <br/> E.g., in `[tag/TAG]...`, multiple tags can be supplied as `tag/important tag/urgent` or omitted.

[↑ Back to table of contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Viewing help [coming soon]

### Adding a client profile: ***create***

Your job as a salesperson starts with adding a client profile to Connectify. This is a one-time process for each client that has been made simple for you.
Use the ***create*** command to add a client profile to Connectify with the following format:

Format: `create n/CLIENT_NAME p/PROFESSION e/EMAIL a/ADDRESS [t/TAG]... [tg/TELEGRAM] [i/INCOME] [d/DETAILS]`

Examples: 
```
create n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney tg/meowies pf/student i/1000 d/Likes to play games 
```
```
create n/Chemmy Lee p/98765432 e/chemmy@gmail.com a/311, Clementi Ave 2, #02-25 tg/meowies`
```
You should directly see the client profile added to the list of clients in the application window. After adding a client profile, you can now perform various operations on the client profile as specified in the next few sections!

### Marking a client as Cold, Warm, or Hot Leads [to be implemented]

Tag a client as a "hot lead", "warm lead", or "cold lead" to prioritise your interactions.

Format
```text
lead <lead_category> <client_name>
```

Example
```text
lead hot John Doe
```

Acceptable Values
- <lead_category>: "hot", "warm" or "cold"
- <client_name>: Alphanumeric, the name of an existing client

Expected Output (Success)
- The specified client is marked with the chosen lead category
- The GUI should display the updated lead category for the client

Expected Output (Failure)
- Invalid lead category: "Invalid lead category. Please choose 'hot,' 'warm,' or 'cold.'"
- Client not found: "Client not found in the address book."

### Delete a client profile [to be implemented]

Delete a client's profile from the address book.

Format
```text
delete profile <client_name>
```

Example
```text
delete profile John Doe
```

Acceptable Values:
- <client_name>: Alphanumeric, the name of an existing client

Expected Output (Success)
- The specified client profile is deleted from the address book
- The GUI should reflect the removal of the client profile

- Expected Output (Failure)
- Client not found: "Client not found in the address book."
- Missing client name parameter: "Please enter the client name."

### Viewing a client profile [to be implemented]

View the full details of a client profile.

Format
```text
view <index>
```

```text
view 1
```

Acceptable Values
- <index>: Number, the index of the client to view in the list displayed.

Expected Output (Success)
- The full details of the selected client profile are displayed in the GUI
 
Expected Output (Failure)
- Invalid index: "The person index provided is invalid"

### Create a client interaction [to be implemented]

Create an interaction that is tagged to a client.

Format
```text
log <client_name> <interaction>
```

Example 
```text
log John Doe “Discussed financial plans”
```

Acceptable Values
- <client_name>: Alphanumeric, the name of an existing client
- <interaction>: Alphanumeric, details of interaction with the client

Expected Output (Success)
- The client profile is shown
- The interaction is added to the client profile

Expected Output (Failure)
- Client not found: "Client not found in the address book."
- Missing client name parameter: "Please enter the client name."
- Missing interaction parameter: “Please enter the client interaction.”

--------------------------------------------------------------------------------------------------------------------

## FAQ [coming soon]


--------------------------------------------------------------------------------------------------------------------

## Known issues [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary [coming soon]
