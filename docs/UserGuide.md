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

### Add Features
#### Adding a client profile: ***create***

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

#### Adding a client interaction: ***interaction***

After adding a client profile, you can now log your interactions with the client. 
Use the ***interaction*** command to add an interaction to a client profile with the following format:

Format: `interaction INDEX o/OUTCOME [DETAILS]`
Where INDEX refers to the index of the client profile in the displayed list of clients.

Examples:
```
interaction 1 o/Meeting with client d/Client is interested in our products
```
```
interaction 1 o/Meeting with client
```

You should directly see the interaction added to the client profile in the application window.

[↑ Back to table of contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### View Features

Sometimes, you may want to view the details of a client profile or the interactions you have had with a client to quickly catch up on the client's profile before contacting them.
Or, you might just want to view the list of clients you have added to Connectify.
<br/>
This section contains multiple commands that allow you to view various details of your clients and collate them in a single place for your convenience.

#### Viewing the dashboard: ***dashboard***

The ***dashboard*** command allows you to view a summarized information of all your clients and their interactions with you in a single place.
Use the ***dashboard*** command with the following format:

Format: `dashboard`

Example:
```
dashboard
```
You should see a dashboard as shown below:

#### Viewing the list of clients: ***list***

You might want to go back to the list of clients you have added to Connectify to view the details of a particular client.
Use the ***list*** command with the following format:

Format: `list`

You should then see the list of clients in the application window.

#### Finding a client by name: ***find***

Managing a large number of clients can be difficult. Some of our commands use indexes to refer to a client profile. This might be difficult to remember if you have a large number of clients.
Don't worry, though, we have a solution for you! You can use the ***find*** command to search for a client by name and get their index.

Format: `find CLIENT_NAME`
<br/>
You don't need to type the full name of the client. You can type a part of the name and the command will return the index of the first client whose name contains the search term.

Example:
Finding a client with the name "John Doe"
```
find John Doe
```

Example:
Finding a client with the name "Chemmy Lee". Notice that you don't need to type the full name of the client.
```
find Chemmy
```

#### Viewing the full details of a client: ***view***

Catching up on the details of a client before contacting them is important. Most of the time, remembering the details of previous interactions with a client is difficult as the data is scattered everywhere.
Well, not anymore! With Connectify, you can view the full details of a client in a single place using the ***view*** command.

Format: `view INDEX`
<br/>
Where INDEX refers to the index of the client profile in the displayed list of clients.

Example:
```
view 1
```

[↑ Back to table of contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Edit Feature

#### Editing a client profile: ***edit***

Each different client may be marked with different lead (e.g. hot, warm, cold) that may change over time.
Not only that, you might find yourself needing to update the details of a client profile sometimes.

Connectify got you covered with the ***edit*** command.

Format: `edit INDEX [n/CLIENT_NAME] [p/PROFESSION] [e/EMAIL] [a/ADDRESS] [t/TAG]... [tg/TELEGRAM] [i/INCOME] [d/DETAILS] [l/LEAD]`
<br/>
Where INDEX refers to the index of the client profile in the displayed list of clients.

Note that at least one of the optional parameters must be provided.

Example of editing a name:
```
edit 1 n/Kamili
```

Example of editing a lead:
```
edit 1 l/warm
``` 

Example of editing multiple fields:
```
edit 1 n/Chemmy Lee p/98765432 l/hot t/friends
```

[↑ Back to table of contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Delete Feature

#### Deleting a client profile: ***delete***

Keep the list of clients in Connectify clean by deleting client profiles that are no longer relevant.

Deleting a client profile is easy with the ***delete*** command.

Format: `delete INDEX`

Example:
```
delete 1
```

[↑ Back to table of contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## FAQ [coming soon]


--------------------------------------------------------------------------------------------------------------------

## Known issues [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary
### Add
* Add a client profile: ***create***
* Add a client interaction: ***interaction***

### View
* View the dashboard: ***dashboard***
* View the list of clients: ***list***
* Find a client by name: ***find***
* View the full details of a client: ***view***

### Edit
* Edit a client profile: ***edit***

### Delete
* Delete a client profile: ***delete***

### Exit
* Exit the application: ***exit***

[↑ Back to table of contents](#table-of-contents)
