---
layout: page
title: User Guide
show-sticky-toc: true
---

## Overview

Introducing **TAfinder - the one-stop solution for all your Teaching Assistant (TA) selection needs**.

As an NUS School Of Computing (SOC) professor, with TAfinder, you can easily:
1. **Manage** troves of TA applicants.
2. Make more **informed decisions** with the help of our **compare** and **sort** functions.
3. **Import** data to the TAfinder application.

TAfinder utilises a Command Line Interface (CLI), while still enjoying the benefits of a Graphical User Interface (GUI),
for a more efficient user experience.

So say goodbye to the days of manually sifting through hundreds of TA applications. Enrich your TA selection process with
the power of TAfinder today!

## About This Guide

This guide shows you the relevant information for setting up and using TAfinder to manage your TA applications.

You can click on any of the links below to navigate to the respective sections for more information.

{% include toc.md header=true show-in-toc=true ordered=true %}

## Quick start

### Prerequisites

#### Java
Ensure you have [Java `11`](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
or above installed. Java is the language that your computer uses to understand TAfinder.

<div markdown="block" class="alert alert-secondary">

**:bulb: Not sure how to check your Java version?**

**Step 1.** Open up **Command Prompt** (Windows) or **Terminal** (Mac and Linux).

**Step 2.** Type and run the command `java -version`.

**Step 3.** Check the version number provided (`xxx`) is at least `11`.

An example is shown below:
  ```
  > java -version
  java version "xxx" <Other information>
  ```

</div>

<div style="page-break-after: always;"></div>

#### Glossary

| Words/Abbreviations | Explanation                                                                                                                      |
|---------------------|----------------------------------------------------------------------------------------------------------------------------------|
| **TA**              | Teaching Assistant.                                                                                                              |
| **Applicant**       | Applicant refers to a student who has applied as a TA.                                                                           |
| **Mainstream OS**   | Windows, Linux or Mac.                                                                                                           |
| **CLI**             | Command-Line Interface.                                                                                                          |
| **GUI**             | Graphical User Interface.                                                                                                        |
| **Toast**           | A popup alert to inform users about certain information.                                                                         |
| **Tag**             | Tags are associated with applicants, users can tag applicants with any keyword they want, the number of tags are not restricted. |

### Installation

**Step 1.** Download the latest `tafinder.jar` file from [here](https://github.com/AY2324S1-CS2103T-W10-1/tp/releases/latest).

**Step 2.** Copy the file to the folder you want to use as the _home folder_ for your TAfinder.

**Step 3.** Double-click on the `tafinder.jar` file to start the TAfinder app.

<div markdown="block" class="alert alert-secondary">

**:bulb: TAfinder does not open?**

**Step 1.** Open a command terminal.

**Step 2.** Navigate to the location of the `tafinder.jar` file.

**Step 3.** Type in `java -jar ` (Keep in mind of the space at the end).

**Step 4.** Drag and drop `tafinder.jar` into the command terminal.

**Step 5.** Press enter and execute the command.

An example of the final command is displayed below:

  ```
  > java -jar xxxx/xxxx/tafinder.jar
  ```

</div>

The GUI similar to the below should appear in a few seconds: 
![Ui](images/startupUI.png)

### Utilisation

**Step 1:** Type the command in the Command Input Box and press Enter to execute it. e.g. typing **`help`** and
pressing Enter will open the help window.<br>
Some example commands you can try:

- `list` : Lists all applicants.

- `add s/A0251647W n/Harry Lee p/89064678 e/harry@example.com g/4.3 pg/A` : Adds an applicant named "Harry Lee" to the list.

- `delete 3` : Deletes the 3rd applicant shown in the current list.

- `clear` : Deletes all applicants.

- `exit` : Exits the app.

**Step 2:** Observe the changes to the application.<br>

<div markdown="block" class="alert alert-secondary">

**:bulb: Not sure what the commands above do?**

* Find all about the usable commands [here](#features).
* Look at a summary of all the usable commands [here](#command-summary).
* Return to the [Table of Contents](#toc-heading) to find your desired command.

</div>

### Navigating the User Interface

![UI with shaded areas](images/navigationUI.png)

The UI has the following areas:
- **Navigation Bar**
  - This is where you can navigate to the `File` and `Help` menus.
- **Command Input Box**
  - This is where commands are typed.
  - Press `Enter` to execute it.
- **Command Result Screen**
  - This is where the result of entering a command is displayed.
- **Applicant List**
  - This is where the list of applicants is displayed.
- **Applicant Viewing Box**
  - This is where the details of the selected applicant is displayed after the command `view` is executed.

<div markdown="block" class="alert alert-secondary">

**:bulb: Not sure what `view` does?**

* Find out more about `view` [here](#viewing-the-details-of-a-single-applicant-view).

</div>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  **e.g.** `add n/NAME`,<br>
  `NAME` is a parameter which can be used as `add n/John Doe`.


* Items in square brackets are optional.<br>
  **e.g.** `n/NAME [t/TAG]` ,<br>
  can be used as `n/John Doe t/friend` or as `n/John Doe`.


* Items with `…`​ after them can be used zero or more times.<br>
  **e.g.** `[t/TAG]…​`,<br>
  can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.


* Parameters can be in any order.<br>
  **e.g.** if the command specifies `n/NAME p/PHONE_NUMBER`,<br>
  `p/PHONE_NUMBER n/NAME` is also acceptable.


* Extraneous parameters will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.


* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple
  lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</div>

---

### Basic applicant management

#### Adding an applicant: `add`


**Format:**

<div markdown="block" class="alert alert-info">

**`add s/STUDENT_NUMBER n/NAME p/PHONE e/EMAIL g/GPA [pg/PREVIOUS_GRADE] [is/INTERVIEW_SCORE] [c/COMMENT] [t/TAG]…`**

- **`s/STUDENT_NUMBER`**: Student number of the applicant.
- **`n/NAME`**: Name of the applicant.
- **`p/PHONE`**: Phone number of the applicant.
- **`e/EMAIL`**: Email address of the applicant.
- **`g/GPA`**: GPA of the applicant.
- **`pg/PREVIOUS_GRADE`**: Previous grade of the applicant.
- **`[is/INTERVIEW_SCORE]`**: Interview score of the applicant.
- **`[c/COMMENT]`**: Comments for the applicant.
- **`[t/TAG]`**: Tags of the applicant.

</div>

<div markdown="block" class="alert alert-primary">

:bulb: You can add more than 1 tag to an applicant by adding more `t/TAG` parameters.

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`add s/A0251647W n/amanda p/89064678 e/amanda@example.com g/4.3 pg/A`**
- Adds an applicant with the following information:
  - **Student number**: A0251647W
  - **Name**: Amanda
  - **Phone number**: 89064678
  - **Email address**: amanda@example.com
  - **GPA**: 4.3
  - **Previous grade**: A

</div>

<div markdown="block" class="alert alert-secondary">

**`add s/A0269357C n/john doe p/91234567 e/johndoe@example.com g/5.0 pg/A- is/8.9 c/Hardworking t/pastTA t/deanslist`**
- Adds an applicant with the following information:
  - **Student number**: A0269357C
  - **Name**: John Doe
  - **Phone number**: 91234567
  - **Email address**: johndoe@example.com
  - **GPA**: 5.0
  - **Previous grade**: A-
  - **Interview score**: 8.9
  - **Comment**: Hardworking
  - **Tags**: pastTA, deanslist

</div>

Following the example above, if you entered everything correctly, you should see the following:

![add.jpg](images/add_afterUI.png)


**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully added an applicant.
- Confirmation message:<br>
**`New applicant added: Student number: <student number>; Name: <name>; Phone: <phone>;
Email: <email>; GPA: <gpa>; Previous grade: <previous grade>; [Interview score: <interview score>];
[Comment: <comment>]; [Tags: <tags>].`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Invalid command format:<br>
**`Invalid command format!`<br>
`add: Adds an applicant to the list. Parameters: s/STUDENT NUMBER n/NAME p/PHONE e/EMAIL g/GPA pg/PREV GRADE
[is/INTERVIEW SCORE] [c/COMMENT] [t/TAG]...`<br>
`Example: add s/A0343434C n/John Doe p/98765432 e/johnd@example.com g/4.9 pg/A is/9.1 c/Hardworking and diligent t/pastTA`**

</div>

<div markdown="block" class="alert alert-danger">

Repeated applicant:<br>
**`This applicant already exists in the applicant list.`**

</div>

---

#### Editing an applicant: `edit`

**Format:**

<div markdown="block" class="alert alert-info">

**`edit INDEX [s/STUDENT NUMBER] [n/NAME] [p/PHONE] [e/EMAIL] [g/GPA] [pg/PREVIOUS_GRADE] [is/INTERVIEW_SCORE] [c/COMMENT] [t/TAG]…`**

- **`INDEX`**: The index of the applicant to edit. The index must be a positive integer (e.g., 1, 2, 3…).

- `[optional fields]`: **At least one** of the following optional fields must be provided for editing:
  - **`s/STUDENT NUMBER`**: Student number of the applicant.
  - **`n/NAME`**: Name of the applicant.
  - **`p/PHONE`**: Phone number of the applicant.
  - **`e/EMAIL`**: Email address of the applicant.
  - **`g/GPA`**: GPA of the applicant.
  - **`pg/PREVIOUS_GRADE`**: Previous grade of the applicant.
  - **`c/comment`**: Comment of the applicant.
  - **`is/INTERVIEW_SCORE`**: Interview score of the applicant.
  - **`t/TAG`**: Tags of the applicant. Note that editing tags will replace existing tags; it is not cumulative.
  - To remove all existing tags, use **`t/`** without specifying any tags after it.

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`edit 1 p/91234567 e/johndoe@example.com`**
- Edits the following fields of the first person in the list:
  - **Phone number**: **`91234567`**
  - **Email address**: **`johndoe@example.com`**

</div>

<div markdown="block" class="alert alert-secondary">

**`edit 2 n/Alex Yeoh t/`**
- Edits the following fields of the second person in the list:
  - **Name**: **`Betsy Crower`**
  - Clears all existing tags

</div>

Following the example above, if you entered everything correctly, you should see the following:

![edit function UI](images/edit_afterUI.png)

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully edited an applicant.
- Confirmation message:<br>
**`Edited applicant: Student number: <student number>; Name: <name>; Phone: <phone>; Email: <email>; GPA: <gpa>; Comment: <comment>; Tags: <tags>`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Invalid command format:<br>
`Invalid command format!`<br>
`edit: Edits the details of the applicant identified by the index number used in the displayed applicant list. Existing values will be overwritten by the input values.`<br>
`Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL] [g/GPA] [c/COMMENT] [t/TAG]...`<br>
`Example: edit 1 p/91234567 e/johndoe@example.com`

</div>

<div markdown="block" class="alert alert-danger">

Index out of range:<br>
**`Error: Invalid index. Please enter an index within range.`**

</div>

---

#### Deleting an applicant: `delete`


![delete function UI](images/delete_afterUI.png)

**Format:**

<div markdown="block" class="alert alert-info">

**`delete INDEX`**

**`INDEX`**: The index corresponding to the applicant to be deleted. The index must be a positive integer (e.g., 1, 2, 3…).

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`delete 3`**
- Deletes the third applicant in the list.

</div>

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully deleted an applicant.
- Confirmation message:<br>
**`Deleted applicant: Student number: <student number>; Name: <name>; Phone: <phone>; Email: <email>; GPA: <gpa>; Comment: <comment> ; Tags: <tags>.`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Missing index: <br>
**`Invalid command format!`<br>
`delete: Displays the applicant identified by the index number used in the displayed applicant list.`<br>
`Parameters: INDEX (must be a positive integer)`<br>
`Example: delete 1`**

</div>

<div markdown="block" class="alert alert-danger">

Index out of range:<br>
**`Error: Invalid index. Please enter an index within range.`**

</div>

---

#### Listing all applicant: `list`


**Format:**

<div markdown="block" class="alert alert-info">

**`list`** / **`list hidden`** / **`list bookmarked`**

</div>

<div markdown="block" class="alert alert-info">

**`list`**
- Shows a list of all applicants.

</div>

![listUI](images/listUI.png)

<div markdown="block" class="alert alert-info">

**`list hidden`**
- Shows a list of all hidden applicants.

</div>

![listhiddenUI](images/listhiddenUI.png)

<div markdown="block" class="alert alert-info">

**`list bookmarked`**
- Shows a list of all bookmarked applicants.

</div>

![listbookmarkedUI](images/listbookmarkedUI.png)

---

#### Viewing the details of a single applicant: `view`

Displays the details of a specified applicant in the details panel.

**Format:**

<div markdown="block" class="alert alert-info">

**`view INDEX`**

**`INDEX`**: The index corresponding to the applicant you want to be displayed. The index must be a positive integer (e.g., 1, 2, 3…).

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`view 3`**
- Displays the following details about the third applicant.
  - Name
  - Tags
  - Phone number
  - Email Address
  - GPA
  - Previous Grade
  - Interview Score
  - Comments
  - Attachments
</div>

Following the example above, if you entered everything correctly, you should see the following:

![view function UI](images/viewUI.png)

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

Sample display of the details of an applicant:
- **`Name: John Doe`**
- **`Tags: pastTA, deansList`**
- **`Phone Number: 91234567`**
- **`Email Address: johndoe@example.come`**
- **`GPA: 5.0`**
- **`Previous Grade: A`**
- **`Interview Scorw (optional): 8`**
- **`Comment (optional): Good fit, has teaching experience`**
- **`Attachments: Resume.pdf`**

Confirmation message:<br>
**`Displaying: APPLICANT_NAME.`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Missing index: <br>
**`Invalid command format!`<br>
`view: Displays the applicant identified by the index number used in the displayed applicant list.`<br>
`Parameters: INDEX (must be a positive integer)`<br>
`Example: view 1`**

</div>

<div markdown="block" class="alert alert-danger">

Index out of range:<br>
**`"Error: Invalid index. Please enter an index within range."`**

</div>

---

#### Hiding an applicant from list: `hide`/`unhide`

Hides/unhides an applicant from the list of applicants.

**Format:**

<div markdown="block" class="alert alert-info">

`hide INDEX` / `unhide INDEX` / `unhide-all`

</div>

<div markdown="block" class="alert alert-info">

`hide INDEX`
- Hides the applicant at the specified INDEX from all future lists. The index refers to the number shown in the displayed person list.</br>
  Index must be a positive integer (e.g. 1, 2, 3...).

</div>

![hideUI](images/hide_afterUI.png)

<div markdown="block" class="alert alert-info">

`unhide INDEX`
- Unhides the applicant at the specified INDEX from all future lists. The index refers to the number shown in the displayed person list. </br>
  Index must be a positive integer (e.g. 1, 2, 3...).

</div>

![unhideUI](images/unhide_afterUI.png)

<div markdown="block" class="alert alert-info">

`unhide-all`
- Unhides all applicants that were previously hidden.

</div>

![unhideallUI](images/unhideall_afterUI.png)

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`hide 2`**
- Hides the applicant at index 2.

</div>

<div markdown="block" class="alert alert-secondary">

**`list hidden` followed by `unhide 2`**
- Lists all hidden applicants, then unhides the applicant at index 2 in the list of hidden applicants.

</div>

<div markdown="block" class="alert alert-secondary">

**`unhide-all`**
- Unhides all applicants.

</div>

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully hidden an applicant.
- Confirmation message:<br>
**`Applicant <name>; Phone: <phone>; Email: <email>; GPA: <gpa>; Comment: <comment>; Tags: <tags> hidden from lists.`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Missing index:<br>
**`Invalid command format!`<br>
`hide: Hides an applicant, identified by the index number used in the last list, from all future lists of applicants.`<br>
`Parameter: INDEX (must be a positive integer)`<br>
`Example: hide 1`**

</div>

<div markdown="block" class="alert alert-danger">

Index out of range:<br>
**`Error: Invalid index. Please enter an index within range.`**

</div>

---

#### Clearing all applicants: `clear`

Clears your entire list of applicants.

**Format:**

<div markdown="block" class="alert alert-info">

**`clear`**

</div>

Following the example above, if you entered everything correctly, you should see the following:


|                     Before                      |                     After                      |
|:-----------------------------------------------:|:----------------------------------------------:|
| ![clear function UI](images/clear_beforeUI.png) | ![clear function UI](images/clear_afterUI.png) |

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully cleared all applicants.
- Confirmation message:<br>
**`Applicant list has been cleared!`**

</div>


---

#### Exiting the application: `exit`

Exits the application, while ensuring all your changes are saved.

**Format:**

<div markdown="block" class="alert alert-info">

**`exit`**

</div>

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Application closes.

</div>

---

### Applicant evaluation & comparison

#### Sorting applicants by GPA: `sort`

Sorts applicants by a designated field.

|              Before                |               After                |
|:----------------------------------:|:----------------------------------:|
| ![sortUI](images/sort_afterUI.png) | ![sortUI](images/sort_afterUI.png) |

**Format:**

<div markdown="block" class="alert alert-info">

**`sort FIELD`**

**`FIELD`**: The field that applicants are sorted by. <br>
* Valid fields: `name`, `studentNo`, `gpa`, `previousGrade`, `interviewScore`, `comment`, `phone`, `email`, `tags` <br>

</div>

**Expected Output:**

<div markdown="block" class="alert alert-success">

- A sorted list of applicants.

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Empty list:<br>
**`No applicants to sort.`**

</div>

---

#### Comparing 2 applicants: `compare`

Compares two applicants side by side to make informed decisions.


|          TAfinder window           |                  Popup window                  |
|:----------------------------------:|:----------------------------------------------:|
| ![compareUI](images/compareUI.png) | ![compare_popupUI](images/compare_popupUI.png) |


**Format:**

<div markdown="block" class="alert alert-info">

**`compare INDEX1 INDEX2`**

**`INDEX1`**: The index of the first applicant to compare.

**`INDEX2`**: The index of the second applicant to compare.<br>

Both indices must be a positive integer (e.g., 1, 2, 3…), and should not be the same.

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`compare 1 2`**
- Compares the first and second applicants.

</div>

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully compare two applicants.
- Confirmation message:<br>
  **`Comparison successful!`**
- A side-by-side comparison of the two applicants is displayed in a user-friendly format.
- This comparison window will include:
  - Student number
  - Name
  - Various TA selection criteria such as:
    - GPA
    - Interview Score
    - Module Grade
    - Comments
- The system also highlights the differences between the two applicants, making it easy to see variations in their profiles.

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Applicant not found:<br>
**`Error: One or both of the specified applicants were not found in the list.`**

</div>

<div markdown="block" class="alert alert-danger">

Comparing the same applicant:<br>
**`Error: Please provide distinct indices. You cannot compare the same applicant.`**

</div>

<div markdown="block" class="alert alert-danger">

Comparing more than 2 applicants:<br>
**`Invalid command format!`<br>
`Please follow the format: compare INDEX1 INDEX2.`<br>
`Parameters: INDEX (must be positive integers)`**

</div>

---

#### Bookmarking applicants: `bookmark`/`unbookmark`

Bookmarks/Unbookmarks a specific applicant.

**Format:**

<div markdown="block" class="alert alert-info">

**`bookmark INDEX` / `unbookmark INDEX`**

**`INDEX`**: The index corresponding to the applicant you want to bookmark/unbookmark. The index must be a positive integer (e.g., 1, 2, 3…).

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`bookmark 2`**
- Bookmarks the second applicant.

</div>

Following the example above, if you entered everything correctly, you should see the following:

|                        Before                         |               After                |
|:-----------------------------------------------------:|:----------------------------------:|
| ![bookmark function UI](images/bookmark_beforeUI.png) | ![bookmark function UI](images/bookmark_afterUI.png) |

<div markdown="block" class="alert alert-secondary">

**`unbookmark 2`**
- Unbookmarks the second applicant.

</div>

Following the example above, if you entered everything correctly, you should see the following:

|                          Before                           |                          After                           |
|:---------------------------------------------------------:|:--------------------------------------------------------:|
| ![unbookmark function UI](images/unbookmark_beforeUI.png) | ![unbookmark function UI](images/unbookmark_afterUI.png) |


**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully bookmarked/unbookmarked applicant at the given index.
- Confirmation message:<br>
  **`Applicant at index INDEX has been successfully bookmarked/unbookmarked.`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Missing index:<br>
**`Invalid command format!`<br>
`bookmark: Bookmarks an applicant, identified by the index number used in the last list, from all future lists of applicants.`<br>
`Parameter: INDEX (must be a positive integer)`<br>
`Example: bookmark 1`**

</div>

<div markdown="block" class="alert alert-danger">

Index out of range:<br>
**`Error: Invalid index. Please enter an index within range.`**

</div>

---

#### Commenting on applicant: `comment`

One of yours TAs is unable to make it for the interview.
You want to make a comment on the applicant to remind yourself to follow up with him/her.
Lets find out how to do use the comment feature to do so.

**Format:**

<div markdown="block" class="alert alert-info">

**`comment INDEX c/COMMENT`**

**`INDEX`**: The index corresponding to the applicant to be commented. The index must be a positive integer (e.g., 1, 2, 3…).

The index of the applicant is the number beside the applicant's name in the list of applicants.

</div>

**Example:**

<div markdown="block" class="alert alert-secondary">

**`comment 3 c/Unable to make it for interview`**
- Comments on the third applicant with the comment: "Unable to make it for interview"

</div>

Following the example above, if you entered everything correctly, you should see the following:

![commentUI.jpg](images/comment_afterUI.png)

You have now successfully commented on the applicant. As you comment on more applicants in the list,
watch out for the expected and erroneous messages below that could be displayed in the command result screen!

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully commented on the applicant at the given index.
- Confirmation message:<br>
**`Applicant has been successfully commented on.`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Invalid command format:<br>
**`Invalid command format!"`<br>
`"comment: Edits the comment of the person identified by the index number used in the last person listing. Existing comment will be overwritten by the input."`<br>
`"Parameters: INDEX (must be a positive integer) c/ [COMMENT]"`<br>
`"Example: comment 1 c/ Hardworking student`**

</div>

<div markdown="block" class="alert alert-danger">

Index out of range:<br>
**`Error: Invalid index. Please enter an index within range.`**

</div>


---

### Files and data management

#### Importing applicants from spreadsheet: `import`

Imports an entire list of applicants along with their details from a CSV file. If applicants with the same student number already exist, they will be skipped.

|            Sample .csv             |                After import                |
|:----------------------------------:|:------------------------------------------:|
| ![sampleCSV](images/samplecsv.png) |   ![importUI](images/import_afterUI.png)   |

**Format:**

<div markdown="block" class="alert alert-info">

**`import FILENAME`**

**`FILENAME`**: The desired filename of the CSV file to import from (including the file extension).

</div>

<div markdown="block" class="alert alert-warning">

The first line of the CSV file should contain the column names e.g. `studentNo`, `name` in any order. It must contain all the column names as specified in the example below. The following rows should contain the data for each applicant in the order specified by the header row.

```csv
studentNo,name,phone,email,gpa,previousGrade,tags
A0123486A,Jasmine David,98472983,jasmine_david@u.nus.edu,4.3,B+,deansList;pastTA
A0456123A,Sandeep Kopparthi,86753746,sandeep@u.nus.edu,5.0,B+,pastTA
A0775848D,Lim Boon Kong,97777777,boonkong@u.nus.edu,3.5,C,deansList
A0483910A,Mohammed Taufiq bin Rozaini,85535252,taufiq@u.nus.edu,4.2,A+,
```

*Note: The CSV format supported is only a **subset** of the [RFC 4180 CSV standard](https://www.loc.gov/preservation/digital/formats/fdd/fdd000323.shtml). Only text, commas and newlines are respected. No quoting or escaping is recognised by the parser.*

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`import ta-applicants.csv`**
- Imports a entire list of applicants, from a file in the CSV format called `ta-applicants.csv` in the same directory as the JAR file, into TAfinder.

</div>

**Expected Output:**

<div markdown="block" class="alert alert-success">

- Successfully attached a file to the applicant at the corresponding index.
- Confirmation message:<br>
  **`Imported `i` applicants successfully!`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Missing file permissions or invalid file path:<br>
**`Failed to open and load applicant file.`**

</div>

---

#### Attaching file to applicant profiles: `attach`

Attaches local files to the profiles of applicants to provide even more richness and insight into their applications.

![attach function UI](images/attach_afterUI.png)

**Format:**

<div markdown="block" class="alert alert-info">

**`attach INDEX f/FILEPATH`**

**`INDEX`**: The index of the applicant to edit. The index must be a positive integer (e.g., 1, 2, 3…).

**`FILEPATH`**: The desired path of the file to attach to the applicant’s profile. This is relative to the path of the JAR file unless either `/` or `C:\` is at the start of the path, then the path will be treated as an absolute path.

</div>

**Examples:**

<div markdown="block" class="alert alert-secondary">

**`attach 2 f/john-resume.pdf`**
- Attaches the file called `john-resume.pdf` in the same directory as the `tafinder.jar` file to the second applicant in the applicant list.

</div>

<div markdown="block" class="alert alert-secondary">

**`attach 78 f//home/jennifer/resumes/benson-resume.pdf`**
- Attaches the file called `benson-resume.pdf` in the directory `/home/jennifer/resumes` to the 78th applicant in the applicant list.

</div>

**Expected Outputs:**

<div markdown="block" class="alert alert-success">

- Successfully attached a file to the applicant at the corresponding index.
- Confirmation message:<br>
  **`Attached `i` attachments to `name`!`**

</div>

**Erroneous Outputs:**

<div markdown="block" class="alert alert-danger">

Invalid file path or corrupted data:<br>
**`Failed to copy attachment.`**

</div>

<div markdown="block" class="alert alert-danger">

Any other unexpected error:<br>
**`Error: Unknown error. Please contact the app developer at contact@email.com`**

</div>

---
## Data Management
### Saving the data

TAfinder data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TAfinder data is saved automatically as a JSON file `[JAR file location]/data/tafinder.json`. Advanced users are welcome to update data directly by editing that data file.


<div markdown="block" class="alert alert-danger">

**Warning!:**

If your changes to the data file makes its format invalid, TAfinder will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.

</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------
## Summary
### Prefix Summary

| Parameter       | Prefix | Rules                                                                                                                                                                                                                |
|-----------------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Student Number  | s/     | - Should be in the format `CdddddddC`, <br/>where `d` represents digit and `C` represents capital letters.                                                                                                           |
| Name            | n/     | - Should only contain alphanumeric characters and spaces.                                                                                                                                                            |
| Phone           | p/     | - Should only contain digits.<br/>- Should have at least 3 digits.                                                                                                                                                   |
| Email           | e/     | - Should only be of the form `local@domain` and only accept alphanumeric characters.<br/>- `local` allows for special characters `+`, `_`, `.` and `-` as well.<br/>- `domain` must be at least 2 letters long.<br/> |
| GPA             | g/     | - Should be in the range of 0.00 to 5.00 inclusive.<br/>- Can be given in 0, 1 or 2 decimal places.                                                                                                                  |
| Previous Grade  | pg/    | - Should be one of the following: A+, A, A-, B+, B, B-, C+, C, D+, D, F.                                                                                                                                             |
| Interview Score | is/    | - Should be in the range of 0.00 to 10.00 inclusive.<br/>- Can be given in 0, 1 or 2 decimal places.                                                                                                                 |
| Comment         | c/     | - No restrictions.                                                                                                                                                                                                   |
| Tag             | t/     | - Should only contain alphanumeric characters.<br/>- Should not contain spaces.                                                                                                                                      |


### Command Summary
#### Basic applicant management

| Action          | Format, Examples                                                                                                                                                                      |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | - `add s/STUDENT_NUMBER n/NAME p/PHONE e/EMAIL g/GPA pg/PREVIOUS_GRADE [is/INTERVIEW_SCORE] [c/COMMENT] [t/TAG]…` <br> - e.g., `add s/A0269357C n/john doe p/91234567 e/johndoe@example.com g/5.0 pg/A+ t/pastTA t/deanslist` |
| **Edit**        | - `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> - e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                       |
| **Delete**      | - `delete INDEX`<br> - e.g., `delete 3`                                                                                                                                               |
| **List**        | - `list [FIELD]`                                                                                                                                                                              |
| **View**        | - `view INDEX`<br> - e.g., `view 3`                                                                                                                                                   |
| **Hide/Unhide** | - `hide INDEX` / `unhide INDEX`<br/> - e.g., `hide 3` / `unhide 3`                                                                                                                    |

#### Applicant comparison and evaluation

| Action                  | Format, Examples                                                                  |
|-------------------------|-----------------------------------------------------------------------------------|
| **Sort**                | - `sort FIELD`<br> - e.g., `sort gpa`, `sort name`                                |
| **Compare**             | - `compare INDEX1 INDEX2`<br> - e.g.,`compare 1 2`                                |
| **Bookmark/Unbookmark** | - `bookmark INDEX` / `unbookmark INDEX`<br> - e.g., `bookmark 3` / `unbookmark 3` |
| **Comment**             | - `comment INDEX c/COMMENT`<br/> - e.g., `comment 3 c/Hardworking`                |

#### Data import and management

| Action                  | Format, Examples                                                |
|-------------------------|-----------------------------------------------------------------|
| **Import**              | - `import FILENAME`<br/> - e.g., `import ta-applicants.csv`     |
| **Attach**              | - `attach INDEX FILEPATH`<br> - e.g.,`attach 2 john-resume.pdf` |
