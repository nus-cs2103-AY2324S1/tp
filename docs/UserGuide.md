---
layout: page
title: User Guide
---

# <span style="color:green">MediFlowR</span>

## Welcome to MediFlowR

Too many patients to handle? Mixing up your patient appointments?

Don't worry, we got you! Taking control of your patient records management has never been easier. All you need is a 
desktop and **MediFlowR** will handle the rest!

### About MediFlowR:

MediFlowR is a desktop application built for medical practitioners to manage their day-to-day operations. It is a 
user-friendly and efficient patient records management system designed to help you streamline your patient management 
processes. In the fast-paced world of healthcare, we understand the importance of keeping patient information organised 
and appointments on track. MediFlowR is your solution!

### Key Features:
To simplify your administrative tasks, our application provides a range of features, but not limited to:

1. **Patient Information Management:** Easily store, access and edit patient data, making it simple to track patient's vital information and history.
2. **Appointment Scheduling:** Efficiently manage patient appointments and eliminate the risk of scheduling conflicting appointments. 
3. **Integration:** Seamlessly integrate patient records across multiple departments by allowing users to update patient information, allowing for the doctor to follow up and ensuring continuity of care.


Here at MediFlowR, we believe in keeping things simple and efficient. 

_**Simple:**_  
Navigating MediFlowR is a breeze. Our intuitive user interface just displays essential information allowing
you to quickly access the information you need. Moreover, this user guide together with the in-application guidance 
minimises the learning curve for your administrative staff. 

_**Efficient:**_  
On top of these functionalities, we believe that patient management must be efficient.
Therefore, MediFlowR is optimised for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). By simply typing, MediFlowR can get your
hospital management tasks done faster than current GUI apps in the industry.




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

- **Congratulations!** You have completed the tutorial. You are now ready to use MediFlowR.

--------------------------------------------------------------------------------------------------------------------

## Glossary
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `CAPITAL_LETTERS` are the parameters to be supplied by the user.<br>
e.g. in `find NAME`, `NAME` is a parameter which can be used as `find David`.

* Words between square brackets `[]` are the optional parameters that are not necessary for the command to run.<br>
e.g. in `add name=NAME gender=GENDER birthdate=BIRTHDATE phone=PHONE email=EMAIL address=ADDRESS [illness=ILLNESS]`,
`[ilness=ILLNESS]` means that it does not have to be specified.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name=[name] birthdate=[birthdate]`, `birthdate=[birthdate] name=[name]` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

--------------------------------------------------------------------------------------------------------------------

## Commands summary

| Action                                                                       | Format, Examples                                                                                                                                                                                                                                                     |
|------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [**Adding a new patient**](#adding-a-new-patient-add)                        | `add name=NAME gender=GENDER birthdate=BIRTHDATE phone=PHONE email=EMAIL address=ADDRESS [illness=ILLNESS]` <br> e.g., `add name=John Doe gender=MALE birthdate=2000/10/20 phone=98765432 email=johnd@example.com address=311, Clementi Ave 2, #02-25 illness=fever` |
| [**Updating a patient's details**](#updating-a-patients-details-edit)        | `edit INDEX [name=NAME] [gender=GENDER] [birthdate=BIRTHDATE] [phone=PHONE] [email=EMAIL] [address=ADDRESS]` <br> e.g., `edit 1 birthdate=2001/12/14 phone=93842738`                                                                                                 |
| [**Removing a patient**](#removing-a-patient-delete)                         | `delete INDEX` <br> e.g., `delete 1`                                                                                                                                                                                                                                 |
| [**Displaying all patients**](#displaying-all-patients-list)                 | `list`                                                                                                                                                                                                                                                               |
| [**Finding patients by name**](#finding-patients-by-name-find)               | `find NAME`<br> e.g., `search name=James Jake`                                                                                                                                                                                                                       |
| [**Scheduling a new appointment**](#scheduling-a-new-appointment-schedule)   | `schedule patient=PATIENT start=START end=END description=DESCRIPTION` <br> e.g., `schedule patient=Alex Yeoh start=2023/10/20 12:00 end=2023/10/20 13:00 description=Follow up on Chest X-Ray`                                                                      |
| [**Rescheduling an appointment**](#rescheduling-an-appointment-reschedule)   | `reschedule INDEX start=START end=END`<br> e.g., `reschedule 1 start=2023/05/02 09:00 end=2023/05/02 11:00`                                                                                                                                                          |
| [**Cancelling an appointment**](#cancelling-an-appointment-cancel)           | `cancel INDEX`<br> e.g., `cancel 3`                                                                                                                                                                                                                                  |
| [**Displaying all appointments**](#displaying-all-appointments-appointments) | `appointments`                                                                                                                                                                                                                                                       |
| [**Clearing all patient records**](#clearing-all-patient-records-clear)      | `clear`                                                                                                                                                                                                                                                              |
| [**Exiting the program**](#exiting-the-program-exit)                         | `exit`                                                                                                                                                                                                                                                               |
| [**Viewing help**](#viewing-help-help)                                       | `help`                                                                                                                                                                                                                                                               |

--------------------------------------------------------------------------------------------------------------------

## Features

MediFlowR provides a variety of commands for you to effectively manage your patient records and appointments. This section
will clearly guide you on how to use the commands so that you can streamline your workflow and take full advantage of the
features present in this application.

## Patient commands

### Adding a new patient: `add`

This command creates a new patient to be added to the patient records, along with the necessary personal information
and contact details about the patient. It is *optional* to include what illness the patient is currently inflicted with
when entering the patient details.

Format: `add name=NAME gender=GENDER birthdate=BIRTHDATE phone=PHONE email=EMAIL address=ADDRESS [illness=ILLNESS]`

Example: `add name=John Doe gender=MALE birthdate=2000/10/20 phone=98765432 email=johnd@example.com address=311, Clementi Ave 2, #02-25 illness=fever`

The example command will add a *male* patient called *John Doe*, with birthdate on *20 October 2000*, phone number at *98765432*,
email at *johnd@example.com* and address at *311, Clementi Ave 2, #02-25*, who is currently down with *fever*.

### Updating a patient's details: `edit`

This command updates a patient's personal information and contact details. It will update the details of the patient at
the specified `INDEX` shown in the patients records. You must edit *at least one* detail when using the command.

Format: `edit INDEX [name=NAME] [gender=GENDER] [birthdate=BIRTHDATE] [phone=PHONE] [email=EMAIL] [address=ADDRESS]`

Example: `edit 1 birthdate=2001/12/14 phone=93842738`

This example command will update the patient with index 1 in the patient records (i.e. the first patient) and will change
the patient's birthdate to *2001/12/14* and phone number to *93842738*.

### Removing a patient: `delete`

This commands removes the specified patient from the patient list. It will remove the patient at
the specified `INDEX` shown in the patients records.

Format: `delete INDEX`

Example: `delete 1`

This example command will remove the patient with index 1 in the patient records (i.e. the first patient).

### Displaying all patients: `list`

This command shows a list of all patients currently recorded in the MediFlowR application under the patients section.

Format: `list`

### Finding patients by name: `find`

This command finds patients whose names contain any of the keywords that you specified.

The search is case-insensitive, meaning that finding patients with the keyword `John`
will return the same results as the keyword `john`. Only patients with names matching the full words of the keywords
will be displayed, meaning that `Tom` will find patients with names that contain the full `Tom` but will not find patients
with names such as `Tommy`.

Format: `find NAME`

Example: `find name=alex david`

This example command will find all patients with names that contain either `alex` or `david`, e.g. `Alex Yeoh` and `David Li`.

## Appointment Commands

### Scheduling a new appointment: `schedule`

This command schedules an appointment for an existing patient in the patient records. It will schedule an appointment
for the patient with the name `PATIENT`. The start date time should be before the end date time.

Format: `schedule patient=PATIENT start=START end=END description=DESCRIPTION`

Example: `schedule patient=Alex Yeoh start=2023/10/20 12:00 end=2023/10/20 13:00 description=Follow up on Chest X-Ray`

This example command will schedule a new appointment for the patient *Alex Yeoh* on *20 October 2023* from *12pm* to *1pm*
for his *follow-up appointment on his chest X-Ray*.

### Rescheduling an appointment: `reschedule`

This command reschedules an existing appointment to another timeslot. It will reschedule the appointment at the specified
`INDEX` shown in the appointments list.

Format: `reschedule INDEX start=START end=END`

Example: `reschedule 1 start=2023/05/02 09:00 end=2023/05/02 11:00`

This example command will reschedule the appointment with index 1 in the appointments list (i.e. the first appointment) to
*2 May 2023*, from *9am* to *11am*.

### Cancelling an appointment: `cancel`

This command cancels an existing appointment. It will cancel the appointment at the specified
`INDEX` shown in the appointments list.

Format: `cancel INDEX`

Example: `cancel 3`

This example command will cancel the appointment with index 1 in the appointments list (i.e. the first appointment).

### Displaying all appointments: `appointments`

This command shows a list of all appointments currently scheduled.

Format: `appointments`

## Miscellaneous commands

### Clearing all patient records: `clear`

This command clears all patient records from the application.

Format: `clear`

### Exiting the program: `exit`

This command exits the program.

Format: `exit`

### Viewing help: `help`

Shows a message explaining how to access the help page.

![help message](images/userguide/helpMessage.png)

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: You can install MediFlowR on that other computer, then simply transfer over the `mediflowr.json` file located in the
data folder to the data folder on that other computer. The data folder is located at the same place as your MediFlowR application.

**Q**: How do I save my data after any changes I have made?<br>
**A**: The data is saved automatically after any command that changes the data. There is no need to save manually.

**Q** Can I edit the data file used by the application directly?<br>
**A**: MediFlowR data are saved automatically as a JSON file `[JAR file location]/data/mediflowr.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
