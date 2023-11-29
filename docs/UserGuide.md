---
layout: page
title: User Guide
show-toc: true
---

## About ToothTracker

ToothTracker is a **Dental Clinic Administrative Management System** designed for **dental clinic administration assistants**.
From adding new patient and dentist profiles to seamlessly creating appointment schedules, ToothTracker is
engineered to simplify every aspect of your dental clinic administrative responsibilities.

{: .no_toc}
#### Why ToothTracker? Because Your Time Matters.

In your bustling dental clinic, time is the rarest commodity. That is why we designed ToothTracker to match the speed of the services
that you provide, enabling you to **manage patient and dentist records efficiently**, **schedule appointments without any overlaps**
and ensure that your clinic operates like a well-oiled machine — all through a versatile Command Line Interface (CLI)
that responds to your speedy typing, along with a Graphical User Interface (GUI) that includes a user-friendly calendar.

{: .no_toc}
#### ToothTracker's Core Features Designed with You in Mind

- **Digital Record Keeping:** Say goodbye to cumbersome physical files — store and manage patients, dentists and appointments digitally.
- **Effortless Retrieval:** Use intuitive `search` and `filter` commands to access and retrieve patient and dentist records.
- **Tagging System:** Add tags to each patient and dentist to highlight key information about them.
- **Smart Scheduling:** Eliminate double-bookings and optimize your clinic's operations with appointment management.
- **Custom Treatments:** Customize ToothTracker to reflect the treatments that are provided by your clinic.
- **Visual Calendar:** Get a clear overview of your day-to-day operations with our comprehensive calendar view.


Head over to [How to use ToothTracker's User Guide](#how-to-use-toothtrackers-user-guide) to start tracking your dental clinic records!

{% include page-break.html %}

{% include toc.md header=true show-in-toc=true ordered=true %}

{% include page-break.html %}

## How to use ToothTracker's User Guide
Thank you for choosing ToothTracker! We are delighted to have you as a user!
This user guide provides a comprehensive documentation on the various commands supported by ToothTracker.

<div markdown="span" class="alert alert-success">
  <span id="text">
    :bulb: **Tip:** Our user guide is designed for **ease of navigation**. Simply click on any item in the Table of Contents
    to jump directly to that section. Within each section, clicking on the **headings** will return you to the Table of Contents,
    so it's easy to navigate between sections!
  </span>
</div>

{: .no_toc}
#### First-Time Setup
If you are new to ToothTracker, let's begin by setting up the application. Visit the [Installation](#installing-toothtracker)
section for a straightforward guide to get ToothTracker up and running smoothly on your system.

{: .no_toc}
#### Basic Operations
Already installed ToothTracker? Great! Let's head over to the [Familiarising with ToothTracker's Interface](#familiarising-with-toothtrackers-interface)
section which covers the basics of using ToothTracker.

{: .no_toc}
#### Quick Reference
Experienced users can refer to the [Command Summary](#command-summary), a concise reference guide that encapsulates all ToothTracker
commands for quick reference.

{: .no_toc}
#### Support and Troubleshooting
If you come across an issue or have a question, our [Troubleshooting](#troubleshooting) and [FAQ](#faq) sections are here to swiftly
assist you in troubleshooting common problems and providing answers.

{% include page-break.html %}

### Conventions used in this guide

{: .no_toc}
#### Formatting conventions

| Formatting          | Description                                                                                                    |
|---------------------|----------------------------------------------------------------------------------------------------------------|
| `code_snippets`     | Code snippets are used to show ToothTracker commands or inputs.                                                |
| **bold**            | Text in bold are important details you should look out for or headers to distinguish from the rest of the text. |
| [blue hyperlink](#) | Text in blue are hyperlinks that will direct you to the relevant section of the page or to other websites.     |

{: .no_toc}
#### Call-out text box conventions

Throughout this guide, you'll encounter various call-out text boxes.
These contain helpful tips and cautionary advice that you should keep in mind to ensure a smooth experience using ToothTracker.

**Note Box**
<div markdown="span" class="alert alert-info">
   <span id="text">
      :information_source: **Note:** Call-outs like this contains information you should keep in mind.
   </span>
</div>

**Tip Box**
<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** Call-outs like this contains tips that you might find useful.
   </span>
</div>

**Caution Box**
<div markdown="span" class="alert alert-warning">
   <span id="text">
      :exclamation: **Caution:** Call-outs like this contains warnings that you should be careful about.
   </span>
</div>


{% include page-break.html %}

{% include quickstart.md %}

{% include page-break.html %}

## Features
This section shares with you more about how to use each feature in detail.

Similar features are grouped into the individual subsections:
* [Dentist-related commands](#dentist-features)
* [Patient-related commands](#patient-features)
* [Appointment-related commands](#appointment-features)
* [Treatment-related commands](#treatment-features)
* [Calendar-related command](#calendar-feature)
* [General commands](#general-features)

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are input parameters that you need to provide.<br>
  e.g. in `add-dentist n/NAME`, `NAME` is an input parameter which can be used as `add-dentist n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/Professional` or `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Input parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE s/SPECIALIZATION `, `p/PHONE s/SPECIALIZATION n/NAME` is also acceptable.

* Extraneous input parameters for commands that do not take in input parameters (such as `help`, `list-patient`,
  `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

{% include page-break.html %}

### Dentist Features

#### Adding a dentist : `add-dentist`

Adds a dentist to the list of dentists in ToothTracker. This is useful when:
* You are using ToothTracker for the first time and have to add your dentists' particulars.
* You are adding a new dentist who has joined your dental clinic.

**Format:** `add-dentist n/NAME p/PHONE s/SPECIALIZATION y/YOE [e/EMAIL] [h/ADDRESS] [t/TAG]…​`

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** A dentist can have any number of `TAG`s (including zero tags).
      `EMAIL` and `ADDRESS` are optional to include. You may use the `edit-dentist` command to update them in future.
   </span>
</div>


<div markdown="block" class="alert alert-info">
**:information_source: Notes about Specializations:**<br>
By default, ToothTracker only accepts a predefined set of recognized dental specializations. They are: <br>
  1. Endodontics<br>
  2. Dental Public Health<br>
  3. Oral and Maxillofacial Surgery<br>
  4. Orthodontics<br>
  5. Paediatric Dentistry<br>
  6. Periodontics<br>
  7. Prosthodontics<br>

This is in accordance to the
[List of Recognised Specializations](https://www.healthprofessionals.gov.sg/dsab/specialist-training/list-of-recognised-specialties)
from The Ministry of Health. Please consult the developers if you need to change the dental specializations!
</div>

<div markdown="block" class="alert alert-info">
**:information_source: Notes about adding a dentist without email and address:**<br>
ToothTracker will set the <code>EMAIL</code> as "NoEmailProvided@ToBeAdded.com"
and the <code>ADDRESS</code> as "No Address Provided." by default.

If you wish to update them at a later time, you can use the <code>edit-dentist</code> command to make changes.
</div>

<div markdown="block" class="alert alert-info">
**:information_source: Notes about adding dentists with the same name:**<br>
Each dentist must have a unique name. ToothTracker does not allow multiple dentists with identical names.
We recommend that if multiple dentists have the same name, add a unique suffix to their name to differentiate them (e.g., last 3 digits of their NRIC) — John Tan 789H.
</div>


<div markdown="block" class="alert alert-info">
**:information_source: Notes about dentist ID value:**<br>
The dentist ID value is auto-generated by ToothTracker and would always increase by 1 for each new dentist added.
</div>

**Examples:**
* `add-dentist n/Xavier Roald p/99773311 s/Orthodontics y/1 e/roaldxavier@hotmail.com
h/Yishun Street 72, Blk 742, #03-354, Singapore 512742 t/Trainee`<br>
This adds a dentist named 'Xavier Roald' with the phone number '99773311', specialising in 'Orthodontics' with '1 year of experience',
email 'roaldxavier@hotmail.com', address 'Yishun Street 72, Blk 742, #03-354, Singapore 512742', who is a 'Trainee' into
the list of dentists in ToothTracker.

![add-dentist-example-1](images/ug/dentist/AddDentistExample1.png){: .centered-image-full-size }

* `add-dentist n/Barbara Noel p/93349795 e/barbaranoel@gmail.com y/6 s/Paediatric Dentistry`<br>
This adds a dentist named 'Barbara Noel' with the phone number '93349795' and email 'barbaranoel@gmail.com',
who has '6 years of experience' and specializing in 'Paediatric Dentistry' into the list of dentists in ToothTracker.

![add-dentist-example-2](images/ug/dentist/AddDentistExample2.png){: .centered-image-full-size }

{% include page-break.html %}

#### Listing all dentists : `list-dentist`

Shows a list of all dentists in ToothTracker. This is useful when:
* You want to retrieve the information of all dentists.
* You want to verify that a dentist has been added successfully in ToothTracker.
* You want to verify that a dentist has been updated successfully in ToothTracker.

**Format:** `list-dentist` (No extra parameters required)

![list-dentist](images/ug/dentist/ListDentistExample.png){: .centered-image }

{% include page-break.html %}

#### Deleting a dentist : `delete-dentist`

Deletes the dentist with the specified `DENTIST_ID` from ToothTracker.

**Format:** `delete-dentist DENTIST_ID`

<div markdown="span" class="alert alert-warning">
   <span id="text">
      :exclamation: **CAUTION:** This command is DESTRUCTIVE! The records of all deleted dentists will be removed.
      **Proceed with caution!**
   </span>
</div>

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** To check the `DENTIST_ID` of a dentist, you can simply enter the command `list-dentist`.
   </span>
</div>


**Example:**
* `delete-dentist 2` <br> This deletes the dentist with the `DENTIST_ID` 2.
![delete-dentist-example-1](images/ug/dentist/DeleteDentistExample1.png){: .centered-image-full-size }

{% include page-break.html %}

#### Searching for dentists by `DENTIST_ID` : `search-dentist`

Searches for a dentist by their `DENTIST_ID` in ToothTracker.
This command helps you find a unique dentist based on the provided `DENTIST_ID`. This is useful when:
* You want to confirm the identity of a specific dentist before using further commands.
* You need quick access to specific details about a dentist.

**Format:** `search-dentist DENTIST_ID`

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** The `DENTIST_ID` refers to the ID shown in the displayed list of dentists. <br>
      To check the `DENTIST_ID` of a dentist, you can simply enter the command `list-dentist`.
   </span>
</div>


**Example:**
* `search-dentist 5` <br> This searches for the dentist with `DENTIST_ID` 5.
![search-dentist-id-example-1](images/ug/dentist/SearchDentistIdExample.png){: .centered-image-full-size }

{% include page-break.html %}

#### Searching for dentists by keywords : `search-dentist`

Searches for dentists by matching names with a keyword.
This command helps you find dentists that match your search criteria. This is useful when:
* You need to find dentists whose names match your search criteria.
* You forgot the `DENTIST_ID` of a dentist and want to search using the dentist's name instead.

**Format:** `search-dentist KEYWORD`

<div markdown="block" class="alert alert-info">
**:information_source: Notes about searching dentist by keywords:**<br>
  <ul>
    <li>The search is performed only on the dentist's name.</li>
    <li>The search is case-insensitive, meaning that it will match both uppercase and lowercase characters. For example, searching for `James` will match both `James` and `james`.</li>
    <li>The order of the keywords in the name does not matter. For instance, searching for `Mike Lim` will match `Lim Mike` as well.</li>
    <li>Sub-strings will be matched. For example, if you search for `Fred`, it will match `Frederick`.</li>
    <li>More than one dentist result might be returned when searching by keywords, especially if multiple dentists match your search criteria.</li>
  </ul>
</div>

{% include page-break.html %}

**Example:**
* `search-dentist John` <br> This searches for dentists with names containing the keyword `John`.
![search-dentist-keyword-example-1](images/ug/dentist/SearchDentistKeywordExample.png){: .centered-image-full-size }

{% include page-break.html %}

#### Filtering dentists : `filter-dentist`

Narrows down your search for dentists based on a specified attribute.
When this command is used, there might be more than one result that matches your search criteria. This is useful when:
* You want to find dentists with specific attributes.
* You want to confirm the identity of a dentist before using further commands.

**Format:** `filter-dentist a/ATTRIBUTE k/KEYWORDS`

<div markdown="span" class="alert alert-info">
   <span id="text">
      **:information_source: Notes about filtering dentists using keywords:**<br>
      The `filter-dentist` command only accepts filtering using 1 attribute and 1 set of keywords. <br>
      For example, `filter-dentist` a/Address k/Serangoon k/Bishan will not work.
   </span>
</div>

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** To check the attributes that you can filter by, you can simply enter the command `list-dentist` to
      view the attributes which will be displayed in the Dentist card.
      Alternatively, a message will be shown in the text box regarding the attributes that you can filter by if you entered an invalid attribute. <br>
      We have set the attributes that can be filtered to be: `name`, `phone`, `specialization`, `experience`, `email`, `address`, `tags`.
   </span>
</div>

{% include page-break.html %}

**Examples:**
* `filter-dentist a/phone k/93526527` <br> This filters dentists with the phone number 93526527.
![filter-dentist-example-1](images/ug/dentist/FilterDentistExample1.png){: .centered-image-full-size }

* `filter-dentist a/specialization k/Orthodontics` <br> This filters dentists with the specialization Orthodontics.
![filter-dentist-example-2](images/ug/dentist/FilterDentistExample2.png){: .centered-image-full-size }

{% include page-break.html %}

#### Editing a dentist : `edit-dentist`

Edits one or more attributes of the dentist with the specified `DENTIST_ID`. This is useful when:
* The particulars of a dentist need to be updated.
* Optional dentist particulars were not provided previously.
* You accidentally entered incorrect information about a dentist into ToothTracker.

**Format:** `edit-dentist DENTIST_ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/SPECIALIZATION] [y/YOE] [t/TAG]…​`

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** You can edit the particulars in any order and edit more than one particular of the dentist with a single `edit-dentist` command.
   </span>
</div>

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** To check the `DENTIST_ID` of a dentist, you can simply enter the command `list-dentist`.
   </span>
</div>

<div markdown="block" class="alert alert-info">
**:information_source: Notes about editing tags:**<br>
When editing tags for a dentist, ensure to include all existing tags to avoid their removal. For instance, if a dentist has 2 tags, use the <code>edit-dentist</code> command with 2 tags (e.g., t/TAG1 t/TAG2). 
Replace TAG1 and TAG2 with the desired tags. Note that tags cannot contain spaces. To remove all tags, use t/ in the <code>edit-dentist</code> command.
</div>

{% include page-break.html %}

**Examples:**

* `edit-dentist 1 p/98987676 e/bobjuly@gmail.com` <br> This modifies the phone number and email of the dentist with
`DENTIST_ID` 1 to '98987676' and 'bobjuly@gmail.com' respectively.
![edit-dentist-example-1](images/ug/dentist/EditDentistExample1.png){: .centered-image-full-size }

* `edit-dentist 5 n/Emmanuel Alexandra t/` <br>
This modifies the name of the dentist with `DENTIST_ID` 5 to ‘Emmanuel Alexandra’ and removes all tags associated with the dentist.
![edit-dentist-example-2](images/ug/dentist/EditDentistExample2.png){: .centered-image-full-size }


{% include page-break.html %}

### Patient Features

#### Adding a patient : `add-patient`

Adds a patient to the list of patients in ToothTracker. This is useful when:
* You are using ToothTracker for the first time and have to add your patients' particulars.
* A new patient has joined your clinic.

**Format:** `add-patient n/NAME p/PHONE b/BIRTHDATE g/GENDER [r/REMARK] [tr/TREATMENT] [e/EMAIL] [h/ADDRESS] [t/TAG]…​`

<div markdown="span" class="alert alert-success">
   <span id="text">
      :bulb: **Tip:** A patient can have any number of `TAG`s (including zero tags).
      `EMAIL`, `ADDRESS`, `REMARK` and `TREATMENT` are optional to be put. You may use the `edit-patient` command to update them in the future.
   </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about adding a patient without a remark and treatment:**<br>
ToothTracker will set the <code>REMARK</code> and <code>TREATMENT</code> as `NIL` by default. <br>

If you wish to update them at a later time, you can use <code>edit-patient</code> to edit them.
  </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about adding a patient without an email and address:**<br>
ToothTracker will set the <code>EMAIL</code> as "NoEmailProvided@ToBeAdded.com"
and <code>ADDRESS</code> as "No Address Provided." by default. <br>

If you wish to update them at a later time, you can use <code>edit-patient</code> to edit them.
  </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about adding Treatments:**<br>
The treatment associated with a patient must be a valid treatment in ToothTracker. To add a new treatment for your clinic, use the command `add-treatment`.
To view the list of treatments, you can use the command `list-treatment`.
  </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about adding patients with the same name:**<br>
Each patient must have a unique name. ToothTracker does not allow multiple patients with identical names.
We recommend that if multiple patients have the same name, add a unique suffix to their name to differentiate them (e.g., last 3 digits of their NRIC) — Mike Lim 976B.
  </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about patient ID value:**<br>
The patient ID value is auto-generated by ToothTracker and would always increase by 1 for each new patient added.
  </span>
</div>

**Examples:**

* `add-patient n/John p/90676622 b/26-06-1998 g/M r/Allergic to Peanuts tr/Braces e/johntan@gmail.com h/60 Harvey Avenue t/Urgent` <br>
This adds a new patient named ‘John’, with the phone number ‘90676622’, birthdate of '26 June 1998', gender 'M' (Male), remark that he is 'allergic to peanuts',
requesting for 'Braces' treatment, email of ‘johntan@gmail.com’, with an address at '60 Harvey Avenue', and a tag 'Urgent'.
![add-patient-example-1](images/ug/patient/AddPatientExample1.png){: .centered-image-full-size }

{% include page-break.html %}

* `add-patient n/Jean p/95339212 b/14-09-2001 g/F` <br>
This adds a new patient named 'Jean', with the phone number '95339212', birthdate of '14 September 2001', gender 'F' (Female).
![add-patient-example-2](images/ug/patient/AddPatientExample2.png){: .centered-image-full-size }


#### Listing all patients : `list-patient`

Shows a list of all patients in ToothTracker. This is useful when:
* You want to retrieve the information of all patients.
* You want to verify that a patient has been added successfully in ToothTracker.
* You want to verify that a patient has been updated successfully in ToothTracker.

Format: `list-patient` (No extra parameters required)
![list-patient-example](images/ug/patient/ListPatientExample.png){: .centered-image }

#### Deleting a patient : `delete-patient`

Deletes the patient with the specified `PATIENT_ID` from ToothTracker.

**Format:** `delete-patient PATIENT_ID`

<div markdown="span" class="alert alert-warning">
  <span id="text">
    :exclamation: **CAUTION:** This command is DESTRUCTIVE! The records of all deleted dentists will be removed.
    **Proceed with caution!**
  </span>
</div>

<div markdown="span" class="alert alert-success">
  <span id="text">
    :bulb: **Tip:**
    To check the `PATIENT_ID` of a patient, you can simply enter the command `list-patient`.
  </span>
</div>

Example:
* `delete-patient 5` <br> This deletes the patient with the `PATIENT_ID` 5.
![delete-patient-example-1](images/ug/patient/DeletePatientExample1.png){: .centered-image-full-size }

{% include page-break.html %}

#### Searching for patients by `PATIENT_ID` : `search-patient`

Searches for a patient by their `PATIENT_ID` in ToothTracker.
This command helps you find a unique patient based on the provided `PATIENT_ID`. This is useful when:
* You want to confirm the identity of a specific patient before using further commands.
* You need quick access to a specific patient's particulars.

**Format:** `search-patient PATIENT_ID`

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
The `PATIENT_ID` refers to the ID shown in the displayed list of patients. <br>
To check the `PATIENT_ID` of a patient, you can simply enter the command `list-patient`.
  </span>
</div>

**Example:**
* `search-patient 6` <br> This searches for the patient with `PATIENT_ID` 6.
![search-patient-id-example](images/ug/patient/SearchPatientIdExample1.png){: .centered-image-full-size }

{% include page-break.html %}

#### Searching for patients by keywords : `search-patient`

Searches for patients by matching names with a keyword.
This command helps you find patients that match your search criteria. This is useful when:
* You need to find patients whose names match your search criteria.
* You forgot the `PATIENT_ID` of a dentist and want to search using the patient's name instead.

**Format:** `search-patient KEYWORD`

<div markdown="block" class="alert alert-info">
**:information_source: Notes about searching patient by keywords:**<br>
  <ul>
    <li>The search is performed only on the patient's name.</li>
    <li>The search is case-insensitive, meaning that it will match both uppercase and lowercase characters. For example, searching for `James` will match both `James` and `james`.</li>
    <li>The order of the keywords in the name does not matter. For instance, searching for `Mike Lim` will match `Lim Mike` as well.</li>
    <li>Sub-strings will be matched. For example, if you search for `Fred`, it will match `Frederick`.</li>
    <li>More than one patient result might be returned when searching by keywords, especially if multiple patients match your search criteria.</li>
  </ul>
</div>

{% include page-break.html %}

**Example:**
* `search-patient John` <br> This searches for patients with names containing the keyword `John`.
![search-patient-keyword-example-1](images/ug/patient/SearchPatientKeywordExample1.png){: .centered-image-full-size }

{% include page-break.html %}

#### Filtering patients : `filter-patient`

Narrows down your search for patients based on a specified attribute.
When this command is used, there might be more than one result that matches your search criteria. This is useful when:
* You want to find patients with specific attributes.
* You want to confirm the identity of a patient before using further commands.

**Format:** `filter-patient a/ATTRIBUTE k/KEYWORDS`

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about filtering dentists using keywords:**<br>
The `filter-patient` command only accepts filtering using 1 attribute and 1 set of keywords. For example, `filter-patient` a/Treatment k/Cleaning k/Braces will not work.
  </span>
</div>

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:** To check the attributes that you can filter by, you can simply enter the command `list-patient` to view
the attributes which will be displayed in the Patient card.
Alternatively, a message will be shown in the text box regarding the attributes that you can filter by if you entered an invalid attribute. <br>
We have set the attributes that can be filtered to be: `name`, `phone`, `address`, `email`, `gender`, `birthday`, `remark`, `tags` and `treatment`.
  </span>
</div>

{% include page-break.html %}

**Examples:**
* `filter-patient a/phone k/82713092` <br> This filters for patients with the phone number '82713092'.
![filter-patient-example-1](images/ug/patient/FilterPatientExample1.png){: .centered-image-full-size }

* `filter-patient a/treatment k/Braces` <br> This filters for patients with the treatment 'Braces'.
![filter-patient-example-2](images/ug/patient/FilterPatientExample2.png){: .centered-image-full-size }

{% include page-break.html %}

#### Editing a patient : `edit-patient`

Edits one or more attributes of the patient with the specified `PATIENT_ID`. This is useful when:
* The particulars of a patient need to be updated.
* Optional patient particulars were not provided previously.
* You accidentally entered incorrect information about a patient into ToothTracker.

**Format:** `edit-patient PATIENT_ID [n/NAME] [p/PHONE] [b/BIRTHDATE] [g/GENDER] [r/REMARK] [tr/TREATMENT] [e/EMAIL] [h/ADDRESS] [t/TAG]`

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
You can edit the particulars in any order and edit more than one particular of the patient with a single `edit-patient` command.
  </span>
</div>

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
To check the `PATIENT_ID` of a patient, you can simply enter the command `list-patient`.
  </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about editing tags:**<br>
When editing tags for a patient, ensure to include all existing tags to avoid their removal. For instance, if a patient has 2 tags, use the <code>edit-patient</code> command with 2 tags (e.g., t/TAG1 t/TAG2). 
Replace TAG1 and TAG2 with the desired tags. Note that tags cannot contain spaces. To remove all tags, use t/ in the <code>edit-patient</code> command.
  </span>
</div>

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about editing Treatments:**<br>
The edited treatment must be a valid treatment in ToothTracker. For a list of valid treatments, you can use the command `list-treatment`.
  </span>
</div>

{% include page-break.html %}

**Examples:**
* `edit-patient 1 p/91234567 e/johndoe@example.com r/Allergic to Peanuts`<br>
This edits the phone number, email, and remarks of the patient with `PATIENT_ID` 1 to
'91234567', 'johndoe@example.com', and 'Allergic to Peanuts' respectively.
![edit-patient-example-1](images/ug/patient/EditPatientExample1.png){: .centered-image-full-size }

* `edit-patient 2 n/John Tan t/` <br>
This edits the name of the patient with `PATIENT_ID` 2 to 'John Tan' and removes all tags associated with the patient.
![edit-patient-example-2](images/ug/patient/EditPatientExample2.png){: .centered-image-full-size }

{% include page-break.html %}

### Appointment Features

#### Adding an appointment : `add-appointment`

Adds a dental appointment to ToothTracker. This is useful when:
* Scheduling future dental appointments for patients with specific dentists at designated times.
* Checking for clashes with existing appointments.
* Adding past appointments for record-keeping purposes.

**Format:** `add-appointment dentist/DENTIST_ID patient/PATIENT_ID start/START_DATE_TIME tr/TREATMENT`

<div markdown="block" class="alert alert-info">
**:information_source: Notes about adding Appointments:**<br>
  <ul>
    <li>ToothTracker will not allow the addition of appointments that clashes with existing appointments
        with the same dentist or patient.</li>
    <li>ToothTracker only allows the addition of appointments from the year 2000 and onwards.</li>
    <li>When adding an appointment, the list of appointments shown may get filtered, resulting in the new appointment not showing up in the appointment list.
        Please use <code>list-appointment</code> if you want the new appointment to be displayed.</li>
    <li>After adding an appointment, the details of the appointment cannot be changed regardless of the changes made to related attributes (e.g. treatment duration, dentist's name).
        Please delete the appointment and add a new appointment if you wish to update the changes.</li>
  </ul>
</div>

{% include page-break.html %}

**Example:**
* `add-appointment dentist/1 patient/1 start/2023-10-12 16:00 tr/Braces` <br>
This command adds an appointment with the `PATIENT_ID` 1 and the `DENTIST_ID` 1.
The appointment starts on '12 October 2023, 4pm'. The treatment provided during the appointment is 'Braces'.
The duration of the appointment is automatically set based on the selected treatment in ToothTracker.
![add-appointment-example](images/ug/appointment/AddAppointmentExample.png){: .centered-image-full-size }

{% include page-break.html %}

#### Listing all appointments : `list-appointment`

Shows a list of all appointments in ToothTracker. This is useful when:
* You want to retrieve the information of all appointments.
* You want to verify that an appointment has been successfully added to ToothTracker.

**Format:** `list-appointment` (No extra parameters required)
![list-appointment-example](images/ug/appointment/ListAppointmentExample.png){: .centered-image }

{% include page-break.html %}

#### Deleting an appointment : `delete-appointment`

Deletes the appointment with the specified `APPOINTMENT_ID` from ToothTracker.

**Format:** `delete-appointment APPOINTMENT_ID`

<div markdown="span" class="alert alert-warning">
  <span id="text">
:exclamation: **CAUTION:** This command is DESTRUCTIVE! The records of all deleted appointments will be removed.
**Proceed with caution!**
  </span>
</div>

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
To check the `APPOINTMENT_ID` of an appointment, you can simply enter the command `list-appointment`.
  </span>
</div>

**Example:**
* `delete-appointment 2` <br>
This deletes the appointment with `APPOINTMENT_ID` 2 from ToothTracker.
![delete-appointment-example](images/ug/appointment/DeleteAppointmentExample.png){: .centered-image-full-size }

{% include page-break.html %}

#### Filtering appointments by `DENTIST_ID` : `filter-appointment dentist`

Narrows down your search for appointments by filtering the appointment list to
show the list of appointments under the dentist with the specified `DENTIST_ID`.
This is useful when:
* You want to view the list of appointments under a specific dentist.

**Format:** `filter-appointment dentist DENTIST_ID`

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
`DENTIST_ID` refers to the ID shown in the displayed list of dentists. <br>
You may use `list-dentist` to check out the `DENTIST_ID` first.
  </span>
</div>

**Example:**
* `filter-appointment dentist 1` <br>
This shows you all the appointments under the dentist with `DENTIST_ID` 1.
![filter-appointment-dentist-example](images/ug/appointment/FilterAppointmentDentistExample.png){: .centered-image-full-size }

{% include page-break.html %}

#### Filtering appointments by `PATIENT_ID` : `filter-appointment patient`

Narrows down your search for appointments by filtering the appointment list to
show the list of appointments under the patient with the specified `PATIENT_ID`.
This is useful when:
* You want to view the list of appointments under a specific patient.

**Format:** `filter-appointment patient PATIENT_ID`

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
`PATIENT_ID` refers to the index number shown in the displayed list of patients. <br>
You may use `list-patient` to check the `PATIENT_ID` first.
  </span>
</div>

**Example:**
* `filter-appointment patient 1` <br>
This shows you all the appointments under the patient with the `PATIENT_ID` 1.
![filter-appointment-patient-example](images/ug/appointment/FilterAppointmentPatientExample.png){: .centered-image-full-size }


{% include page-break.html %}

### Treatment Features

#### Adding a treatment : `add-treatment`

Adds a dental treatment to the ToothTracker system.
This command allows you to specify the treatment name, the cost of treatment, and how long the treatment would take.

**Format:** `add-treatment tr/NAME cs/PRICE ti/DURATION`

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about adding treatments with the same name:**<br>
Each treatment must have a unique name. ToothTracker does not allow multiple treatments with identical names.
  </span>
</div>

**Example:**
* `add-treatment tr/Tooth Extraction cs/1080 ti/01:30` <br>
This adds a 'Tooth Extraction' treatment to ToothTracker with a cost of '$1080 SGD', and duration of '1 hour and 30 minutes'.
![add-treatment-example](images/ug/treatment/AddTreatmentExample.png){: .centered-image-full-size }

{% include page-break.html %}

#### Listing all treatments : `list-treatment`

Shows a list of all treatments in ToothTracker. This is useful when:
* You want to view the currently available treatments stored in ToothTracker.

Format: `list-treatment` (No extra parameters required)
![list-treatment-example](images/ug/treatment/ListTreatmentExample.png){: .centered-image }

{% include page-break.html %}

#### Deleting a treatment : `delete-treatment`

Deletes the treatment with the specified `TREATMENT_NAME` from ToothTracker.

**Format:** `delete-treatment TREATMENT_NAME`

<div markdown="span" class="alert alert-warning">
  <span id="text">
:exclamation: **CAUTION:**
This command is DESTRUCTIVE! The records of all deleted treatments will be removed.
**Proceed with caution!**
  </span>
</div>

<div markdown="span" class="alert alert-warning">
  <span id="text">
:exclamation: **CAUTION:**
Before deleting a treatment in ToothTracker, confirm that it's no longer linked to any patients to maintain functionality.
  </span>
</div>

<div markdown="span" class="alert alert-success">
  <span id="text">
:bulb: **Tip:**
`TREATMENT_NAME` must be an **exact** match (case-sensitive) with the name of the treatment to be deleted.
  </span>
</div>

**Example:**
* `delete-treatment Braces` <br> This deletes the treatment `Braces` from ToothTracker.
![delete-treatment-example](images/ug/treatment/DeleteTreatmentExample.png){: .centered-image-full-size }



{% include page-break.html %}

### Calendar Feature

#### Viewing calendar : `view-calendar`

Shows a calendar filled with all scheduled appointments in ToothTracker. This is useful when:
* You want a visual summary of all appointments in your clinic.
* You need to plan ahead for upcoming weeks or months.

Format: `view-calendar` (No extra parameters required)

<div markdown="span" class="alert alert-info">
  <span id="text">
**:information_source: Notes about viewing the calendar:**<br>
The appointments displayed in the calendar depends on the appointments listed in the main list.
If the main appointment list is filtered, the appointments displayed in the calendar would be filtered too. <br>
Please use <code>list-appointment</code> before <code>view-calendar</code> if you want to view all appointments.
  </span>
</div>

![view-calendar-example](images/ug/ViewCalendarExample.png){: .centered-image-full-size }

{% include page-break.html %}

### General Features

#### Viewing help : `help`

Shows a message explaining how to access the help page.

**Format**: `help`

![help message](images/ug/HelpWindow.png){: .centered-image-size-75 }



#### Clearing all data : `clear`

Clears all data from ToothTracker. **PROCEED WITH CAUTION!**

<div markdown="span" class="alert alert-warning">
  <span id="text">
:exclamation: **CAUTION:** Data cleared cannot be recovered.
  </span>
</div>

**Format:** `clear`


#### Exiting the program : `exit`

Exits ToothTracker app.

**Format:** `exit`

{% include page-break.html %}

#### Saving the data
ToothTracker data is saved in the hard disk automatically after any command that changes the data. There is no need to
save manually.


#### Editing the data file
ToothTracker data is saved automatically as a JSON file `[JAR file location]/data/toothtracker.json`. Advanced users are
welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">
  <span id="text">
:exclamation: **CAUTION:**
If your changes to the data file makes its format invalid, ToothTracker will discard all data and start with an empty
data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
  </span>
</div>


{% include page-break.html %}

## Troubleshooting

{: .no_toc}
#### Checking your system's Java version
Check that your system has the correct Java version (**Java 11 and above**) installed to run ToothTracker. Follow the steps below:

- For Windows Users:
  1. Press `Win + S` to open search.
  2. Type in `Terminal` and open the terminal.
  3. Key in `java --version` and press `Enter` to check your Java version.

- For macOS Users:
  1. Press `Cmd + Space` to launch Spotlight.
  2. Type in `Terminal` and open the terminal.
  3. Key in `java --version` and press `Return` to check your Java version.

- For Linux Users:
  1. Press `Ctrl + Alt + T` to open the Terminal.
  2. Key in `java --version` and press `Enter` to check your Java version.


The following image shows an example of what will show up on macOS. You can expect a similar result if you’re using another operating system.
![System Java Version](images/ug/SystemJavaVersion.png){: .centered-image-full-size }

The number in the red box highlights the Java version your system currently has.
In the example, the system is running Java 11.0.19, which meets the requirement for ToothTracker.

If you hava an earlier version of Java installed, head over to
[Oracle's Java download page](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) to install the correct version of Java.

{% include page-break.html %}

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous ToothTracker home folder.

**Q**: I don't have Java 11, how do I install it on my computer?<br>
**A**: You can find detailed installation instructions [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
and install Java 11 according to your platform.

**Q**: Why do I get an error while trying to edit an existing dentist/patient?<br>
**A**: The dentist/patient to be edited may not be shown in ToothTracker.
To display the to-be-edited dentist/patient, you may use these commands:
1. `list-dentist` or `list-patient`
2. `search-dentist` or `search-patient`

Thereafter, you may edit the particulars using `edit-dentist` or `edit-patient` with their `DENTIST_ID` or `PATIENT_ID`
respectively.

**Q**: Why are some appointments missing from the calendar view?<br>
**A**: You might have used `view-calendar` after filtering the appointment list. To display all appointments,
you may use `list-appointment` before using `view-calendar`.


{% include page-break.html %}

## Known issues


### Multi-screen Display

- **Issue:** When moving the application from a secondary screen to the primary screen, the GUI may open off-screen.
- **Remedy:** Delete the `preferences.json` file before relaunching the application.

### List Treatment Command

- **Issue:** Extra comma displayed at the end when using the `list-treatment` command.

### Incomplete Treatment Information

- **Issue:** The `list-treatment` command only displays the names of treatments without additional information such as the cost of treatment and duration, which were fields requested during the initial addition of treatments into ToothTracker.

### Phone Number Validation

- **Issue:** Validation checks for phone numbers in `add-patient` and `add-dentist` commands are currently based on 8 digits and do not verify if the phone number starts with an 8 or 9.

### Filter Command Validation

- **Issue:** The `filter-patient` and `filter-dentist` commands only perform validation checks on the attribute to filter by and not the keywords. For example, `filter-dentist a/phone k/myphone` will still execute the command.

### Tag Error in Edit Commands

- **Issue:** When using non-alphanumeric characters in tags on the `edit-dentist` or `edit-patient` commands, an "Unknown command" error is sometimes displayed.

### Limited Tag Editing

- **Issue:** If a patient was initially added to ToothTracker with 3 tags, only 3 tags can be edited afterward—no more, no less. Additional tags cannot be added, and attempting to edit with a different number of tags results in an error.
- **Remedy:** To modify tags, the number must exactly match the tags initially assigned during patient creation via `add-patient`. If additional tags are required or if tags need removal, the patient or dentist must be deleted and added again. 
Resetting tags to empty using `edit-patient [PATIENT_ID] t/` is possible, but subsequent tag editing must adhere to the original number of tags.

### Editing Patient Without Tags or Editing Patient previously deleted:

- **Issue:** Attempting to edit a patient record that originally had no tags and adding tags during the edit or editing a patient record that was previously deleted to have tags will result in a "Multiple values specified for the following single-valued field(s): t/" error.
When editing a patient record that initially had no tags, adding tags during the editing process triggers an error related to the `t/` (tags) field. The system recognizes this as an attempt to assign multiple values to a field that is intended to be single-valued.
- **Remedy:** To avoid this error, ensure that the patient record has at least one tag during the initial creation. If tags need to be added later, consider deleting the patient and adding them again with the desired tags.

{% include page-break.html %}

## Fields summary

| Field       | Description                                                               | Examples                                         |
|-------------|---------------------------------------------------------------------------|--------------------------------------------------|
| **ti**      | Represents the total duration of the `treatment` in hours and minutes.    | `ti/10:30` `tr/09:59`                            |
| **cs**      | Represents the total cost of the `treatment` in dollars.                  | `cs/3000` `cs/500`                               |
| **tr**      | Represents the name of the `treatment`                                    | `tr/Whitening` `tr/Braces`                       |
| **start**   | Represents the start date time of a `appointment` in `yyyy-mm-dd hh:mm`   | `start/2023-01-01 09:05` `start/2021-10-12 16:00` |
| **patient** | Represents the unique ID of the patient when adding a `appointment`       | `patient/1` `patient/45`                         |
| **dentist** | Represents the unique ID of the dentist when adding a `appointment`       | `dentist/1` `dentist/45`                         |
| **y**       | Represents the years of experience of the dentist when adding a `dentist` | `y/5` `y/10`                                     |
| **s**       | Represents the specialization of the dentist when adding a `dentist`      | `s/Orthodontics` `s/DENTAL_PUBLIC_HEALTH`        |
| **r**       | Represents a optional `remark` when adding a `patient`                    | `r/Peanut Allergy` `r/Soya Allergy`              |
| **g**       | Represents  gender.                                                       | `g/M` `g/F`                                      |
| **b**       | Represents the birthday of a `patient` in `dd-mm-yyyy`                    | `b/06-06-1998` `b/12-11-2002`                    | 
| **t**       | Represents  an optional tag for a `patient` or `dentist`                  | `t/urgent` `t/NotUrgent`                         | 
| **e**       | Represents  an optional email address for a `patient` or `dentist`        | `e/freddy@gmail.com` `e/john@youmail.com`        | 
| **h**       | Represents  an optional home address for a `patient` or `dentist`         | `h/123, Clementi Ave 3, #12-34` `h/8 Farrer Road` |
| **p**       | Represents a phone number                                                 | `p/97826376` `p/92837263`                        |
| **n**       | Represents a name                                                         | `n/John` `n/Tom`                                 |


{% include page-break.html %}

## Command summary

### Dentist Commands

| Action                           | Format                                                                                                     | Example                                                                                                                               |
|----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Dentist**                  | `add-dentist n/NAME p/PHONE s/SPECIALIZATION y/YOE [e/EMAIL] [h/ADDRESS] [t/TAG]…​`                     | `add-dentist n/Bob p/12345678 e/bobjune@gmail.com y/6 s/braces`                                                                       |
| **List Dentist**                 | `list-dentist`                                                                                             | `list-dentist`                                                                                                                        |
| **Delete Dentist**               | `delete-dentist DENTIST_ID`                                                                                | `delete-dentist 3`                                                                                                                    |
| **Search Dentist by Dentist ID** | `search-dentist DENTIST_ID`                                                                                | `search-dentist 2`                                                                                                                    |
| **Search Dentist by Keyword**    | `search-dentist KEYWORD`                                                                                   | `search-dentist Tom`                                                                                                                  |
| **Filter Dentist**               | `filter-dentist a/ATTRIBUTE k/KEYWORDS`                                                                    | `filter-dentist a/phone k/90182211`                                                                                                   |
| **Edit Dentist**                 | `edit-dentist DENTIST_ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/SPECIALIZATION] [y/YOE] [t/TAG]…​` | `edit-dentist 1 p/98987676 e/bobjuly@gmail.com`                                                                                       |


### Patient Commands

| Action                           | Format                                                                                                                | Example                                                                                                                               |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| **Add Patient**                  | `add-patient n/NAME p/PHONE b/BIRTHDATE g/GENDER [r/REMARK] [tr/TREATMENT] [e/EMAIL] [h/ADDRESS] [t/TAG]…​`           | `add-patient n/John p/90676622 b/06-06-1998 g/M r/Allergic to Peanuts tr/Braces e/johntan@gmail.com h/60 Harvey Avenue t/Urgent`   |
| **List Patient**                 | `list-patient`                                                                                                        | `list-patient`                                                                                                                        |
| **Delete Patient**               | `delete-patient PATIENT_ID`                                                                                           | `delete-patient 3`                                                                                                                    |
| **Search Patient by Patient ID** | `search-patient PATIENT_ID`                                                                                           | `search-patient 3`                                                                                                                    |
| **Search Patient by Keyword**    | `search-patient KEYWORD`                                                                                              | `search-patient John`                                                                                                                 |
| **Filter Patient**               | `filter-patient a/ATTRIBUTE k/KEYWORDS`                                                                               | `filter-patient a/phone k/98776211`                                                                                                   |
| **Editing Patient**              | `edit-patient PATIENT_ID [n/NAME] [p/PHONE] [b/BIRTHDATE] [g/GENDER] [r/REMARK] [tr/TREATMENT] [e/EMAIL] [h/ADDRESS] [t/TAG]…​` | `edit-patient 1 p/91234567 e/johndoe@example.com r/Allergic to Peanuts`                                                  |


### Appointment Commands

| Action                           | Format                                                                                                     | Example                                                                                                                       |
|----------------------------------|------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|
| **Add Appointment**              | `add-appointment dentist/DENTIST_ID patient/PATIENT_ID start/START_TIME tr/TREATMENT`  | `add-appointment dentist/1 patient/1 start/2023-10-12 16:00 tr/Braces`                                                        |
| **List Appointment**             | `list-appointment`                                                                     | `list-appointment`                                                                                                            |
| **Delete Appointment**           | `delete-appointment APPOINTMENT_ID`                                                    | `delete-appointment 3`                                                                                                        |
| **Filter Appointment by Dentist ID** | `filter-appointment dentist DENTIST_ID`                                            | `filter-appointment dentist 1`                                                                                                |
| **Filter Appointment by Patient ID** | `filter-appointment patient PATIENT_ID`                                            | `filter-appointment patient 1`                                                                                                |


### Treatment Commands

| Action                           | Format                                       | Example                                             |
|----------------------------------|----------------------------------------------|-----------------------------------------------------|
| **Add Treatment**                | `add-treatment tr/NAME cs/PRICE ti/DURATION` | `add-treatment tr/Tooth Extraction cs/150 ti/01:00` |
| **List Treatment**               | `list-treatment`                             | `list-treatment`                                    |
| **Delete Treatment**             | `delete-treatment NAME`                      | `delete-treatment Braces`                           |


### Calendar Commands

| Action            | Format          | Example         |
|-------------------|-----------------|-----------------|
| **View calendar** | `view-calendar` | `view-calendar` |


### General Commands

| Action                | Format           | Example |
|-----------------------|------------------|---------|
| **Help**              | `help`           | `help`  |
| **Clear all data**    | `clear`          | `clear` |
| **Exit ToothTracker** | `exit`           | `exit`  |


## Acknowledgements
* Java dependencies:
  * [JavaFX](https://openjfx.io/) for GUI
  * [Jackson](https://github.com/FasterXML/jackson) for JSON-related operations
  * [JUnit5](https://github.com/junit-team/junit5) for testing
  * [CalendarFX](https://github.com/dlsc-software-consulting-gmbh/CalendarFX) for CalendarView
* User Guide drew inspiration from:
  * [FoodRem](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/2a34fab143ace403adeb4b923258468140b66200/docs/UserGuide.md)
  * [ArtBuddy](https://github.com/AY2223S1-CS2103T-W11-3/tp/blob/master/docs/UserGuide.md)
* Developer Guide drew inspiration from:
  * [FoodRem](https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/2a34fab143ace403adeb4b923258468140b66200/docs/UserGuide.md)
* Code Acknowledgements:
  * [Pure liquid HTML table-of-contents](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/docs/_includes/toc.html) from [@allejo on GitHub](https://github.com/allejo/jekyll-toc)
  * [Pure CSS auto-incrementing heading counters](https://github.com/AY2324S1-CS2103T-W10-3/tp/blob/master/docs/_sass/toc.scss) adapted from [@gvgramazio on StackOverflow](https://stackoverflow.com/a/51007932/9311854)
