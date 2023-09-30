[![Java CI](https://github.com/AY2324S1-CS2103T-T14-3/tp/actions/workflows/gradle.yml/badge.svg)](https://github.com/AY2324S1-CS2103T-T14-3/tp/actions/workflows/gradle.yml)
![Ui](docs/images/Ui.png)

# HealthSync

HealthSync is a desktop app for managing patient details, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HealthSync can help you organize and manage patient details faster than traditional GUI apps.


## Features

1. **Auto-Save Feature** - Saves data in the hard disk automatically after any changes have been made. There is no need to save manually.


2. **Data Creation (Add Function)** - Enables front desk workers to create a new patient object and new fields to store patient information. Adding patient information can be done using the `add` command after providing one of the patient’s identification (either name or id).
- Patient information includes:
    - Patient's Contacts
    - Patient's Medical History
    - Patient's Ward Information
    - Patient's Upcoming Appointment Time(s)


3. **Data Retrieval (Find Function)** - Enables front desk workers to quickly retrieve patient information based on command executed. If no fields are provided, the function returns all the patient’s related information. Else, only the details relating to the field are provided.


4. **Data Deletion (Delete Function)** - Enables front desk workers to remove certain patient information / all patient information when they are no longer needed. If no fields are provided, the function deletes the patient. Else, only the patient information within the specified field is deleted.


5. **Edit Data (Edit Function)** - Enables front desk workers to update patient information fields as necessary. Editing patient information can be done using the `edit` command after providing one of the patient's identification (either name or id).


6. **User-Friendly CLI Interface** - CLI interface that is easy to navigate for users.


For in-depth usage and a full list of commands, refer to the [User Guide](https://ay2324s1-cs2103t-t14-3.github.io/tp/UserGuide.html#quick-start).


## Acknowledgments

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).