## Get Started with ToothTracker
Welcome to ToothTracker! We're thrilled to assist you in streamlining your dental practice effortlessly.
This quick start guide will guide you through the setup and basic operations of ToothTracker, enabling you to start optimizing
your clinic's workflow immediately.

### Installing ToothTracker

If you haven't installed ToothTracker yet, follow our easy step-by-step guide below to have ToothTracker up and running in no time.
Our software is compatible with Windows, macOS, and Linux to ensure optimal performance on your machine.


1. ToothTracker requires **Java 11 or above** installed in your Computer to run.
    - If you don't have Java installed, no worries — it's completely free! Find detailed installation instructions [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).

    <div markdown="span" class="alert alert-success">
      :bulb: **Tip:** Unsure about your Java version? Our [troubleshooting](#troubleshooting) section has you covered with a quick guide.
    </div>

1. Grab the latest version of ToothTracker by downloading the `toothtracker.jar` file from our [releases page](https://github.com/AY2324S1-CS2103T-W10-3/tp/releases).

1. Create an empty folder in your computer where you would like to use as the _home folder_ for ToothTracker.

1. Place the downloaded ToothTracker file (`toothtracker.jar`) into the folder.

Installation complete! You're now ready to launch ToothTracker.

{% include page-break.html %}

### Launching ToothTracker
Great! Now that you have downloaded ToothTracker (if not, refer to the [Installation Instructions](#installing-toothtracker)), let's get it up and running.

1. Open a command terminal.
2. Enter `cd` and change your working directory into the folder you put the `toothtracker.jar` file in.
3. Enter the `java -jar toothtracker.jar` command to run the application.

A user-friendly GUI will pop up shortly, preloaded with some sample data to get you started.<br>
![Ui](images/Ui.png){: .centered-image-full-size }

### Familiarising with ToothTracker's Interface
Now that you have ToothTracker up and running, let's get you familiarized with ToothTracker's user interface!

ToothTracker has two windows: a [Main Window](#main-window) and a [Calendar Window](#calendar-window).
Let's take a closer look at each window individually.

{% include page-break.html %}

{: .no_toc}
#### Main Window
Upon launching ToothTracker for the first time, the Main Window will open by default.
It's designed to give you a comprehensive overview of all your patient, dentist, and appointment-related information at a glance.
Let's learn what each of these components does!


![ToothTracker UI](images/UiAnnotated.png){: .centered-image-full-size }

|   | Component                   | Description                                                                                                                                                                                                            |
|---|-----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | Menu Bar                    | The menu bar contains menu options to [exit]( #exiting-the-program--exit) ToothTracker and access [help](#viewing-help--help) resources.                                                                                 |
| 2 | Command Input               | The command input is where you can enter your commands to instruct ToothTracker on the tasks that you want to get done. To learn more about ToothTracker's CLI, refer to the [next section](#using-toothtrackers-cli). |
| 3 | Result Display              | The result display area shows you the outcomes or feedback from your entered commands.                                                                                                                                 |
| 4 | Patient List                | This panel lists all patients currently registered in your clinic. It includes vital information such as names, contact details, and other fields.                                                                     |
| 5 | Dentist List                | Here, you'll find a roster of dentists associated with your clinic, complete with their specializations and contact information.                                                                                       |
| 6 | Appointment List            | Displays the schedule, status, and details of all clinic appointments.                                                                                                                                                 |
| 7 | [Quick Notes](#quick-notes) | A dedicated section at the bottom right corner of the Main Window for on-the-fly notes, reminders, or observations that you need to keep handy.                                                                        |
| 8 | Saved Data File             | For advanced users, the file path displayed here is where ToothTracker stores your saved data (which you can [edit](#editing-the-data-file)).                                                                          |

{: .no_toc}
#### Quick Notes
Quick Notes is designed to enhance your efficiency by providing a space for immediate note-taking within the application.
Use this notes section that we created to help you capture important reminders or information swiftly without having to navigate away from the main window.

{: .no_toc}
##### How to use Quick Notes<br>

**1. Adding Notes**:
Click into the Quick Notes Box and type your notes.
Remember to hit the `Save` button to keep your notes for future reference.
Unsaved notes will be lost when the main window is closed.
<div markdown="span" class="alert alert-info">
   <span id="text">
**:information_source: Notes about Saving:** <br>
The Quick Notes box will turn green upon saving latest changes.
![Quick Notes](images/ug/QuickNotesSaved.png){: .centered-image-minimal-margin }
   </span>
</div>

**2. Editing Notes**:
Click into the Quick Notes Box and make your changes. You can select text to copy, cut, or delete as needed.

**3. Clearing Notes**:
To clear the contents of your Quick Notes and start from a clean slate, simply click the `Clear` button.
This will remove all text until you decide to save new notes.


{: .no_toc}
#### Calendar Window
ToothTracker's Calendar Window provides a visual representation of your clinic's schedule. This view becomes available after executing a [Calendar Command](#viewing-calendar--view-calendar).
It allows you to easily understand your clinic's appointments and availability at a glance.

![Calendar Window](images/ug/CalendarWindowAnnotated.png){: .centered-image-full-size }

|   | Component           | Description                                                                                                                                                 |
|---|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | View Toggle         | The View Toggle allows you to switch between different calendar views: Day, Week, Month, or Year to get a more precise or broader overview of appointments. |
| 2 | Navigation Buttons  | The navigation arrows enable you to move to the previous or next time periods, depending on the currently selected view (day, week, month, or year).        |
| 3 | Appointment Entries | This section displays the specifics of each appointment, including the Appointment ID, patient name, dentist name and appointment time.                     |

{% include page-break.html %}

### Using ToothTracker's CLI
ToothTracker is optimized for use via a CLI, allowing you to accomplish tasks using text inputs, otherwise known as commands.

Currently, ToothTracker recognizes specific commands (the complete list is available in the Features section),
and these commands must be formatted in a specific way. Let's delve into how you can effectively communicate your needs to ToothTracker!

{: .no_toc}
#### How to Input Commands
Think of the [Command Input](#main-window) as ToothTracker’s ‘ears’ — always ready to listen to your instructions.

1. Click on the Command Input box.
2. Type in your command.
3. Press ‘Enter’ on your keyboard (or ‘return’ if you're using a Mac).

That's it! ToothTracker has just executed your command. Yes, it's as simple as that!

{: .no_toc}
#### Understanding Command Format
Every command you input follows a simple structure to help ToothTracker understand your needs:

* **Command Word:** This is the first word of your command. It serves as a unique keyword that tells ToothTracker precisely the command that you want to execute.
* **Index:** The index is a numerical identifier assigned to each patient, dentist, or appointment, serving to uniquely distinguish them. When issuing a command related to a specific patient, dentist, or appointment, you'll need to use the corresponding index.
* **Flags:** These serve as markers to differentiate between inputs. Typically, a flag is followed by a placeholder.
* **Placeholders:** These are temporary labels that you'll replace with actual data. For instance, replace 'TREATMENT' in `tr/TREATMENT` with the actual name of a treatment, like `tr/Braces`.

{% include page-break.html %}

### Trying out your first commands
Can't wait to get started with ToothTracker?
Let's go through some straightforward commands for you to get a feel on how easy managing your clinic's data can be.

<div markdown="span" class="alert alert-info">
   <span id="text">
      **:information_source:** Remember, the images in this section are just **examples** to guide you.
         Your ToothTracker will show information specific to your own clinic.
   </span>
</div>


Let's start with adding and managing your clinic's dental team:

**Welcoming a new dentist**:
Suppose Dr. Jonathan Goh is the latest addition to your dental team. Let's add him into ToothTracker. <br>
Type `add-dentist n/Jonathan Goh p/92095568 e/jonathan.goh@gmail.com y/3 s/Paediatric Dentistry` and press Enter.
   - Just like that, Dr. Jonathan Goh is part of your digital roster!

   ![trying-command-add-dentist-example](images/ug/add-dentist-example.png){: .centered-image-full-size }

{% include page-break.html %}

**Saying farewell**: If Dr. Alex Yeoh is leaving your clinic, you can keep your records up-to-date by removing his details. <br>
   Simply run `delete-dentist 1`.
   - After executing the command, Dr. Alex Yeoh's information is now deleted from ToothTracker.

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** Always ensure the `DENTIST_ID` matches the identifier on the dentist's card within ToothTracker.
   </span>
</div>

   ![trying-command-delete-dentist-example](images/ug/delete-dentist-example.png){: .centered-image-full-size }

{% include page-break.html %}

Now let's see how easy it is to manage patient information.

**Registering a new patient**: Suppose you have a new patient, named Jean, onboarding your clinic. <br>
Enter `add-patient n/Jean p/95339212 b/14-09-2001 g/F` and press enter.
   - Notice that you've created a new patient, Jean!

   ![trying-command-add-patient-example](images/ug/add-patient-example.png){: .centered-image-full-size }

{% include page-break.html %}

**Updating patient info**: Now, suppose that Alex wants to update his phone number. Easy! Just type in `edit-patient 1 p/82019452`.
   - Done! Alex's phone number has been updated!

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** Always ensure the `PATIENT_ID` matches the identifier on the patient's card within ToothTracker.
   </span>
</div>

   ![trying-command-edit-patient-example](images/ug/edit-patient-example.png){: .centered-image-full-size }

Awesome! Now that you've gotten familiar with ToothTracker's commands, feel free to explore more features in the
[Features](#features) section to fully leverage on ToothTracker's capabilities.
