---
layout: page
title: User Guide
---

MediFlowR is a **desktop app for managing patient records and appointments, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MediFlowR can get your patient management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `mediflowr.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your patient records.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar mediflowr.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients.

   * `add name=John birthdate=30/09/2001 gender=Male illness=Fever` : Adds a patient named `John` to the records.

   * `delete id=3` : Deletes the patient with id 3 in the current list.

   * `clear` : Deletes all patients.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Tutorial for new users

If this is your first time using MediFlowR, do not worry as this is a tutorial for you to get familiar with the features
of MediFlowR. Please follow the instructions [here](#quick-start) first to set up the application before proceeding with the tutorial. 

- Launch the MediFlowR application.

  - Note: When the application is first launched, it will contain some default patient records. 

- Let us try **adding a patient** into our patient records. Enter the command `add name=John Doe gender=MALE birthdate=2000/10/20 phone=98765432 email=johnd@example.com address=311, Clementi Ave 2, #02-25 illness=Fever` in the command box.

![Command result for adding a patient](images/userguide/add-patient.png)

- You should get this result screen. Try adding a few more patients into the patient records with the same format to familiarise yourself with the command.

- Let us try **editing the patient** that we have just added. Enter the command `edit 1 phone=91234567 birthdate=2000/09/19` in the command box.

![Command result for editing a patient](images/userguide/edit-patient.png)
- You should get this result screen. This will change the phone of the patient to 91234567 and the birthdate of the patient to 2000/09/19.

- Let us try **scheduling an appointment** now that we have a patient in our records. Enter the command `schedule patient=John Doe start=2023/10/20 12:00 end=2023/10/20 13:00 description=Follow up on Chest X-Ray` in the command box.

![Command result for scheduling an appointment](images/userguide/schedule-appointment.png)

- You should get this result screen. This will schedule a new appointment for the patient with the name John Doe.

- Let us try **rescheduling the appointment** that we have just scheduled. Enter the command `reschedule 1 start=2023/05/02 09:00 end=2023/05/02 11:00` in the command box.

![Command result for rescheduling the appointment](images/userguide/reschedule-appointment.png)

- You should get this result screen. This will reschedule the appointment to start on 2023/05/02 09:00 and end on 2023/05/02 11:00.

- Let us try **deleting a patient** from our patient records. Enter the command `delete 1`.

![Command result for deleting a patient](images/userguide/delete-patient.png)

- You should get this result screen. This will delete the first patient in the patient records.

- Let us try **cancelling an appointment** from our appointment list. Enter the command `cancel 1`.

![Command result for cancelling an appointment](images/userguide/cancel-appointment.png)

- You should get this result screen. This will cancel the first appointment in the appointment list.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in between square brackets `[]` are the parameters to be supplied by the user.<br>
  e.g. in `search name=[name]`, `[name]` is a parameter which can be used as `add name=John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name=[name] birthdate=[birthdate]`, `birthdate=[birthdate] name=[name]` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a new patient: `add`

Creates a new patient profile to be added to the patient records. 

Format: `add name=[name] birthdate=[birthdate] gender=[gender]
illness=[illness]​`

Examples:
*  `add name=John birthdate=30/09/2001 gender=Male illness=Fever`

* `add name=Cena birthdate=30/12/1993 gender=Male illness=Schizophrenia`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Updating Patient Treatment History: `update`

Updates a patient's treatment history with illness.

Format: `update id=[patient-id] illness=[illness]`

* Updates the illness of the patient with id `patient-id`.
* Existing illness will be changed to the `illness`.

Examples:
*  `update id=12345 illness=Fever` updates the illness of the patient with patient ID `12345` to `Fever`.

### Locating patients by name: `search`

Finds patients whose names contain any of the given keywords.

Format: `search name=[name]`

* The search is case-insensitive. e.g. `tianrun` will match `Tianrun`
* The order of the keywords does not matter. e.g. `Lebron James` will match `James Lebron`
* Only the name is searched.
* Only full words will be matched e.g. `Curr` will not match `Curry`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Chris Paul` will return `Chris Bumstead`, `Logan Paul`

Examples:
* `search name=James` returns `james` and `James Harden`
* `search name=alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified patient from the patient list.

Format: `delete id=[patient-id]`

* Deletes the patient with the specified `patient-id`.
* The patient id refers to the id of the patient shown in the displayed patient list.
* The patient id **must be a positive integer** 1, 2, 3, …​

Examples:
`delete id=12345` deletes the patient with id **12345** in the patient list.

### Clearing all patient records : `clear`

Clears all patient records from the system.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Scheduling a new appointment: `schedule`

Creates an appointment to be added to the appointment records.

Format: `schedule patient=PATIENT start=START end=END description=DESCRIPTION`

Examples:
*  `schedule patient=Alex Yeoh start=2023/10/20 12:00 end=2023/10/20 13:00 description=Follow up on Chest X-Ray `

### Rescheduling an existing appointment: `reschedule`

Reschedules an appointment in the appointment records to another timeslot.

Format: `reschedule INDEX start=START end=END`

Examples:
*  `reschedule 1 start=2023/05/02 09:00 end=2023/05/02 11:00`

### Cancelling an existing appointment: `cancel`

Cancels an appointment in the appointment records.

Format: `cancel INDEX`

Examples:
*  `cancel 3`

### Listing all appointments : `appointments`

Shows a list of all appointments in the address book.

Format: `appointments`


### Saving the data

MediFlowR data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MediFlowR data are saved automatically as a JSON file `[JAR file location]/data/mediflowr.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MediFlowR home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action           | Format, Examples                                                                                                                                                                                |
|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**          | `add name=[name] birthdate=[birthdate] gender=[gender] illness=[illness]` <br> e.g., `add name=John birthdate=30/09/2001 gender=Male illness=Fever`                                             |
| **Clear**        | `clear`                                                                                                                                                                                         |
| **Delete**       | `delete id=[patient-id]`<br> e.g., `delete id=3`                                                                                                                                                |
| **Update**       | `update id=[patient-id] illness=[illness]`<br> e.g.,`update id=12345 illness=Fever`                                                                                                             |
| **Search**       | `search name=[name]`<br> e.g., `search name=James Jake`                                                                                                                                         |
| **List**         | `list`                                                                                                                                                                                          |
| **Help**         | `help`                                                                                                                                                                                          |
| **Schedule**     | `schedule patient=PATIENT start=START end=END description=DESCRIPTION` <br> e.g., `schedule patient=Alex Yeoh start=2023/10/20 12:00 end=2023/10/20 13:00 description=Follow up on Chest X-Ray` |
| **Reschedule**   | `reschedule INDEX start=START end=END`<br> e.g., `reschedule 1 start=2023/05/02 09:00 end=2023/05/02 11:00`                                                                                     |
| **Cancel**       | `cancel INDEX`<br> e.g., `cancel 3`                                                                                                                                                             |
| **Appointments** | `appointments`                                                                                                                                                                                  |