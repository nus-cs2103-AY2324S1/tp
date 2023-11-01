---
layout: page
title: User Guide
---

# Welcome to Connectify!

![Ui](images/Ui.png)

Connectify is a **desktop app for managing clients, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Connectify can get your client management tasks done faster than traditional GUI apps.

Connectify helps salespersons manage their clients so that that can build customer relationships!

In this user guide, you will find instructions on how to install Connectify and use its many features to manage your clients.

Choose a topic from the table of contents below to find out how to manage your clients using Connectify!

# Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# Quick start

1. Ensure that you have Java 11 installed on your Computer.

2. Download the latest `connectify.jar` from our [releases](https://github.com/AY2324S1-CS2103T-F11-4/tp/releases/).

3. Copy the file over to the folder you would want to use as the home folder for Connectify. An option is to create a folder named `Connectify` in your`Desktop` and place the file there.

4. Open the command terminal.
   
    a. For Windows, you can open `Command Prompt` from your list of installed applications.

    b. For macOS and other operating systems, open the `Terminal` app.

5. Change the directory of the terminal by using the `cd` command.

    a. For example, if I saved my file at `C:\Users\John\Desktop\Connectify\connectify.jar`, run the command `cd C:\Users\John\Desktop\Connectify` in your terminal.

6. Run `java -jar connectify.jar` to start the application.

7. A window similar to the one below should appear in a few seconds, containing sample data.

   <figure>
       <img src="images/StartupGUI.png" alt="Connectify GUI during start-up">
       <figcaption align="center">
           <em>The GUI you see on start-up may be slightly different due to differences in data.</em>
       </figcaption>
   </figure>
   <br>
   
8. Type the command in the command box and press `Enter` to execute it.
   
   Some examples you can try:

   - `list`: Lists all client profiles.
   
   - `create n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`: Creates a client named `John Doe` in the client list.
   
   - `delete 3`: Deletes the 3rd contact currently shown in the displayed client list.
   
   - `exit`: Exits the app.

9. Refer to [Features](#features) below for more commands and details for each command.

[↑ Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Features [coming soon]

### Viewing help [coming soon]

### Adding a client profile: `create profile` [to be implemented]

Creates a new client profile with various details.

Format
```text
create profile <client_name> --profession <profession> --email <email>
--telegram <telegram_handle> --phone <phone_number> --income <income>
--details <additional_details>
```

Example
```text
create profile John Doe --profession Sales --email john@example.com
--telegram @john_doe --phone +1234567890 --income $50,000
--details "Birthday: 01/15, Family: Spouse, 2 children"
```

Acceptable values
- <client_name>: Alphanumeric, up to 50 characters, has to be unique
- <profession>: Alphanumeric, up to 50 characters
- <email>: Valid email address format
- <telegram_handle>: Alphanumeric, up to 50 characters, starting with '@'
- <phone_number>: Numeric, valid phone number format
- <income>: Numeric, representing annual income
- <additional_details>: Alphanumeric, additional client details

Expected Output (Success)
- The new client profile is created and added to the address book
- The GUI should reflect the newly added client profile

Expected Output (Failure)
- Invalid email, telegram handle, or phone number format: "Invalid email/telegram/phone format."
- Missing required parameters: "Missing parameters. Please provide all required details."

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
