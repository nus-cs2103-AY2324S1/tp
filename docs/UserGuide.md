---
layout: page
title: User Guide
---
**Foster Family** is a desktop application built for **foster managers of cat and dog shelters** to facilitate the **administrative management** of **foster families**. 

Here are some tasks which Foster Family can help you with: 
* **Store and update** the important details of animal fosterers (people who temporarily care for an animal in their own homes).
* **Search** for a fosterer using _any_ detail you can remember of them.
* **Gain insights** on the overall status of managed fosterers.

Foster Family is optimised for use via a **Command Line Interface (CLI)**. This means that you primarily interact with it by typing commands. It also retains the benefits of a Graphical User Interface (GUI), allowing you to interact with the application (app) through graphical components. If you can type fast, Foster Family can get things done faster than traditional GUI apps.

This document is a comprehensive guide to all the commands available to you, along with **step-by-step explanations** and **examples** to help you master the features Foster Family has to offer. If you are a _new user_, we recommend beginning your journey with [Quick Start](#quick-start). For those who are _already acquainted_, you can refer to the [Table of Contents](#table-of-contents) below to navigate to a section that interests you.

<div style="page-break-after: always;"></div>

## **Table of Contents**
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>


## **Quick Start**

Step 1. Ensure you have Java `11` or above installed in your computer. You can download it from the [Oracle website](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

Step 2. Download the latest `FosterFamily.jar` from [our GitHub Page](https://github.com/AY2324S1-CS2103T-T13-4/tp/releases).

Step 3. Copy the file to the folder you want to use as the _home folder_ for Foster Family.

Step 4. Open a command terminal.

Step 5. Navigate to the home folder you put the jar file in using the command<br>
`cd <path_to_home_folder>`, replacing `<path_to_home_folder>`  with your file path. 

Step 6. Use the `java -jar FosterFamily.jar` command to run the app.<br>

   The Foster Family GUI, similar to the image below, should appear on your screen. Note that the app contains some sample data the first time you launch it.<br>
   ![Ui](images/Ui.png) 

<div style="page-break-after: always;"></div>


Step 7. Type a command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>

   Some example commands you can try:

* `list` : Lists all fosterers.

* `add n/Jerry Tan p/98765412 e/jerry123@example.com a/61 Baker Street housing/nil availability/nil animal/nil animalType/nil` : Adds a fosterer named `"Jerry Tan"` to Foster Family.

* `delete 3` : Deletes the 3rd fosterer shown in the current list.

* `reset`, followed by `reset confirm` : Deletes all fosterers.

* `exit` : Exits Foster Family.<br>

Please refer to the [Features](#features) section for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Useful Notations**
These symbols highlight information you may find important.

| Symbol               | Meaning                                                                                       |
|----------------------|-----------------------------------------------------------------------------------------------|
| :information_source: | General notes about the command                                                               |
| :exclamation:        | Important notes about the command                                                             |
| :warning:            | Warnings about the command, especially when data loss or misinterpretation is likely to occur |
| :bulb:               | Tips to optimise the use of Foster Family                                                     |

--------------------------------------------------------------------------------------------------------------------

## **Technical Terms**
These are some technical terms you may come across in this user guide.

| Term                 | Definition                                                                                |
|----------------------|-------------------------------------------------------------------------------------------|
| Command              | Features of Foster Family, as well as to keywords that trigger those features             |
| Parameter / Argument | Information to be passed to commands as inputs                                            |
| Index                | The number to the left of each fosterer's name in the list shown in the main page         |
| Field                | Attributes associated with a fosterer entry in Foster Family, such as name and email, etc |

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **User Interface (UI)**

There are two different screens you will interact with in Foster Family. 

### The main page

![Ui](images/Ui.png)

This is the main view that welcomes you when you first start up Foster Family.

<div style="page-break-after: always;"></div>

### The profile page 
![ProfileExample](images/screenshots/EditExample.png)

This is the profile view that you can use to add a fosterer, or to edit the details of an existing fosterer. 

#### Opening the profile page
There are two ways you can use to navigate to the profile page. 
1. Enter <code>add</code> to view an _empty_ profile page to [add](#adding-a-fosterer-through-the-profile-page-add) a fosterer.
2. Enter either <code>edit INDEX</code> or <code>view INDEX</code> to [edit](#editing-a-fosterers-details-through-the-profile-page-edit) or [view](#viewing-a-fosterers-detail-view) the fosterer at index <code>INDEX</code> in Foster Family.

<div style="page-break-after: always;"></div>

Suppose you want to open the profile page of a fosterer named Alex Yeoh who is currently at index 1. 

<div style="text-align: center">
<img src="images/screenshots/ViewCommandExample.png" height="380" class="center"/>
</div>

<br>

To do so, enter <code>view 1</code> as shown in the image above. You will be directed to his profile page as shown in the image below.

<div style="text-align: center">
<img src="images/screenshots/EditExample.png" height="420" class="center"/>
</div>

<br>

<div style="page-break-after: always;"></div>

#### Navigating through fields

Entering the name, fully or partially, of the field you want to edit brings your cursor to the corresponding textbox.

<div style="text-align: center">
<img src="images/screenshots/BeforeEnteringName.png" class="center"/>
</div>

<br>

In the example above, entering <code>name</code>, or a part of `name` like <code>nam</code>, brings the focus to the name field.

<div style="text-align: center">
<img src="images/screenshots/NavigatingFields.png" class="center"/>
</div>

<div style="page-break-after: always;"></div>

After you are done editing, _press the Enter key_ on your keyboard to **confirm your changes**, and bring your cursor back to the command box. 

<div></div>

If you **wish to discard changes** while editing in the textbox, _press the Esc key_ to undo the changes and direct your cursor back to the command box.

<div style="text-align: center">

<img src="images/screenshots/CursorBackToCommandbox.png" class="center"/>

</div>

In the example above, after changing name from "Yeoh" to "Yeo", the Enter key was pressed. 

The same process can be applied to other fields.

<br>

#### Saving changes

Entering <code>save</code> saves the changes you have made into storage.

Suppose you want to save your changes after changing the name of the fosterer.

<div style="text-align: center">
<img src="images/screenshots/SaveCommandBefore.png" class="center"/>
</div>

<br>

Key in <code>save</code> and press Enter to save the changes. 

<br>

<div style="page-break-after: always;"></div>

<div style="text-align: center">
<img src="images/screenshots/SaveCommandAfter.png" class="center"/>
</div>

<br/>

The effect of a `save` depends on the command you used to enter the profile page.
* If this was an `edit` command, your changes to the fosterer will be saved.<br>
* If this was an `add` command,  the new fosterer with the inputted details will be added, and you will be redirected back to the main page.

<br>

#### Exiting the profile page

Entering <code>exit</code> closes the profile page and directs you back to the main page. Foster Family will warn you if you attempt to exit without saving your changes.

<br>

**1. Changes are saved**

Suppose you have already saved your changes.

![Exit command saved after](images/screenshots/ExitCommandSavedBefore.png)

<br>

<div style="page-break-after: always;"></div>

Key in <code>exit</code> and press Enter to close the profile page.

![Exit command saved after](images/screenshots/ExitCommandSavedAfter.png)

<br>

**2. Changes are not saved**

Suppose you entered <code>exit</code> without saving your latest changes. 

![Exit command not saved warning](images/screenshots/ExitCommandNotSavedWarning.png)

<br>

<div style="page-break-after: always;"></div>

If you _press the Enter key_ again, you will **discard your changes** and be redirected back to the main page.

<div></div>

If you _press the Esc key_, the **exit is cancelled**, and you can continue working on your changes in the profile page.

![Exit command not saved cancel](images/screenshots/ExitCommandNotSavedCancel.png)

The image above is the result of pressing the Esc key after the warning.

To learn more about **adding a new fosterer through the profile page**, refer to the section [Adding a fosterer through the profile page: add](#adding-a-fosterer-through-the-profile-page-add). <br>
To learn more about **editing a fosterer through the profile page**, refer to the section [Editing a fosterer's detail through the profile page: edit](#editing-a-fosterers-details-through-the-profile-page-edit).

<br>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Features**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are  parameters to be supplied by you.<br>
  e.g. in `n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/urgent` or `n/John Doe`.

* Items with `…`​ after them can be used multiple times, _**including zero times**_.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. zero times), `t/urgent`, `t/urgent t/experienced` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit`, `undo`, `sort` and `reset`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines, as space characters surrounding line-breaks may be omitted when copied over to the app.

* If the command you entered has an invalid format, our app will provide specific error messages to guide you to rectify the issue.

</div>

<br> 

### Viewing help for commands : `help`

Opens a pop-up window, providing you with the link to our User Guide for help.

Format: `help`

![Help](images/screenshots/HelpWindow.png)

<br> 

<div style="page-break-after: always;"></div>

### Adding a fosterer through the main page: `add`

Adds a fosterer to your address book, done through the main page.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL [t/TAG]…`

Parameters:

| Parameter        | About                                                                                                                                                             | Example                                                     |
|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------|
| `NAME`           | Name of the fosterer                                                                                                                                              | `Alice Tan`, `Harry Yeo`                                    |
| `PHONE_NUMBER`   | Phone number of the fosterer                                                                                                                                      | `93456778`, `89067547`                                      |
| `EMAIL`          | Email of the fosterer                                                                                                                                             | `thomas718@gmail.com`, `kate@yahoo.com.sg`                  |
| `ADDRESS`        | Address of the fosterer                                                                                                                                           | `Orchard road, Blk 8, #13-04`                               |
| `HOUSING_TYPE`   | - Housing type of the fosterer<br/> - Case-sensitive<br/> - Can only take the values shown in the example column                                                  | `HDB`, `Condo`, `Landed`, `nil`                             |
| `AVAILABILITY`   | - Availability of the fosterer<br/> - Case-sensitive<br/> - Can only take the values shown in the example column                                                  | `NotAvailable`, `Available`, `nil`                          |
| `ANIMAL_NAME`    | Name of the animal fostered                                                                                                                                       | `Fluffball`, `nil`                                          |
| `TYPE_OF_ANIMAL` | - Type of animal which the fosterer is currently fostering, or prefer to foster<br/> - Case-sensitive<br/> - Can only take the values shown in the example column | `current.Dog`, `current.Cat`, `able.Dog`, `able.Cat`, `nil` |
| `TAG`            | Tag to be associated with the fosterer                                                                                                                            | `experienced`, `urgent`                                     |

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>

* A person can have any number of tags (including 0).
* `nil` can be indicated for `HOUSING_TYPE`, `AVAILABILITY`, `ANIMAL_NAME` and `TYPE_OF_ANIMAL` if that specific information is not currently available.

</div>

<div style="page-break-after: always;"></div>

**Valid cases**:

| No. | Scenario                                                    | `AVAILABILITY` | `TYPE_OF_ANIMAL`     | `ANIMAL_NAME` |
|-----|-------------------------------------------------------------|-------------|-------------------|---------------|
| 1   | Not fostering, insufficient info collected                  | `nil`         | `nil`             | `nil`         |
| 2   | Not fostering, insufficient info collected                  | `Available`   | `nil`             | `nil`         |
| 3   | Not fostering, preference indicated                         | `Available`   | `able.Dog/Cat`    | `nil`         |
| 4   | Not fostering (e.g. overseas, currently not able to foster) | `NotAvailable`            | `nil`             | `nil`         |
| 5   | Fostering: ALL information must be present                  | `NotAvailable`            | `current.Dog/Cat` | NOT `nil`          |

For **invalid cases**, error messages will be shown when you enter the invalid commands. For example:
![Add](images/screenshots/AddErrorMessage.png)

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-danger">

**:exclamation: Important:**<br>

You cannot add duplicate fosterers. This is detected using the fosterer's name.<br>
e.g. <code>"Anne Tay"</code> is the same person as <code>"anne tay"</code> and <code>"anne  (multiple spaces)  tay"</code>.

</div>

Examples:
* `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Cat t/Urgent`
  * adds a fosterer named Jerry Tan with the following details:
    <br/><br/>
    ![Add](images/screenshots/AddJerry.png)
    <br/><br/>

* `add n/Pete Tay p/98765411 e/pete@example.com a/Happy street, block 5, #27-01 housing/Condo availability/Available animal/nil animalType/able.Cat`
  * adds a fosterer named Pete Tay with the following details:
    <br/><br/>
    ![Add](images/screenshots/AddPete.png)
  
<div style="page-break-after: always;"></div>

In the case where duplicate field descriptions or values for `HOUSING_TYPE`, `AVAILABILITY`, `ANIMAL_NAME` and `TYPE_OF_ANIMAL` are given, the last one will be chosen:
* `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB housing/Condo availability/Available availability/NotAvailable animal/Dexter animal/Happy animalType/able.Dog animalType/current.Cat t/Urgent`
  * adds a fosterer named Jerry Tan, who lives in a Condo and is fostering a cat named Happy.
    ![Add](images/screenshots/AddDuplicate.png)

<div style="page-break-after: always;"></div>

### Adding a fosterer through the profile page: `add`

Redirects you to an empty profile page with all the fields set to `nil`. In the profile page, you can key in the fosterer's details and save the 
information to add the fosterer to your address book.

Format: `add`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

*  The restrictions imposed on what makes a valid fosterer, as explained in the section [Adding a fosterer through the main page: add](#adding-a-fosterer-through-the-main-page-add), still applies in this alternative way of adding a fosterer.
</div>

Here is the profile page you will see after entering <code>add</code>: 

<div style="text-align: center">
<img src="images/screenshots/ProfilePage.png" height="480" class="center"/>
</div>

<div></div>

To learn more about the profile page, please refer to the section [User Interface: The profile page](#the-profile-page).  

<br> 

<div style="page-break-after: always;"></div>

### Listing fosterers: `list` (Alias: `find`)

Lists fosterers in your address book that match a particular description or search, or all fosterers if the search is blank.

Format: `list *KEYWORDS`

Alias: `find`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

* The keywords are case-insensitive.

* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.

* All fields are searched (including tags).
 
* Your keywords can overlap. e.g. `samm my` will match `Sammy`.

* Fosterers must match all keywords (i.e. `AND` search).<br>
  e.g. `Hans Bo` will return `Hansbo Grahm`, but not `Hans Duo`.

* You can use double quotes `"` for exact, case-sensitive, word-level match.<br>
  e.g. `"Tom"` matches "Tom", but not "Tommy".

* Symbols between keywords or sections will combine them according to the function of the symbol.<br>

| Symbol / Operator | Description              | Precedence |
|-------------------|--------------------------|------------|
| `&`               | Logical AND              | lowest     |
| `/`               | Logical OR               | low        |
| '` `' (space)     | Logical AND              | high       |
| `(` and `)`       | Parentheses for grouping | highest    |

e.g. `a & b / c d` is the same as `a & (b / (c & d))`.

</div>

<div style="page-break-after: always;"></div>

<div markdown="block" class="alert alert-danger">

**:exclamation: Important:**<br>

For most fields, your **keywords can match as parts of words**. <br>
e.g. `john` will match `Johnny`.<br>

However, for the `Housing` and `Availability` fields, as well as for tags, your **keywords must match the entire field**. <br>
e.g. `available` will not match `NotAvailable`.

</div>

Examples:
* `list`
    * lists all fosterers in your address book.
  
<div></div>

* `list john doe`
    * lists fosterers that match "John Doe", "Doe John", "Johnny Doe", and "Mary" who lives on "John Doe Street".
  
<div></div>

* `find john john doe`
    * is redundant and gives the same result as `find john doe`.
  
<div></div>

* `list "John" / zam & doe`
    * lists fosterers which match "John Doe" and "Doe Shazam", but not "John Grahm".

<br> 

<div style="page-break-after: always;"></div>

### Viewing a fosterer's detail: `view`

Redirects you to the profile page of the fosterer at the specified index of your currently displayed list. 

Format: `view INDEX`

Parameters:

| Parameter | About                                                                                                                                                                | Example |
|-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| `INDEX`     | - Index of a fosterer displayed in the list obtained from a `list`/`find` command <br/> - Index must be a positive integer | `1`, `2`, `3` |

<div markdown="block" class="alert alert-danger">

**:exclamation: Important:**<br>

Only the <code>save</code> and <code>exit</code> commands are available to you in the profile page.<br>

To learn more about profile page, please refer to the section [User Interface: The profile page](#the-profile-page).

</div> 

Examples:
* `list` followed by `view 2`
  * views the profile of the 2nd fosterer in your address book.

<br>

<div style="page-break-after: always;"></div>

### Editing a fosterer's details through the main page: `edit`

Edits the details of the fosterer at the specified index in your currently displayed list, done through the main page.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [housing/HOUSING_TYPE] [availability/AVAILABILITY] [animal/ANIMAL_NAME] [animalType/TYPE_OF_ANIMAL] [t/TAG…]`

Parameters:

| Parameter        | About                                                                                                                                                                     | Example                                                     |
|------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------|
| `INDEX`          | - The index of a fosterer displayed in the list obtained from a `list`/`find` command<br/> - Index must be a positive integer                                             | `1`, `2`, `3`                                               |
| `NAME`           | Updated name of the fosterer                                                                                                                                              | `Alice Tan`, `Harry Yeo`                                    |
| `PHONE_NUMBER`   | Updated Phone number of the fosterer                                                                                                                                      | `93456778`, `89067547`                                      |
| `EMAIL`          | Updated email of the fosterer                                                                                                                                             | `thomas718@gmail.com`, `kate@yahoo.com.sg`                  |
| `ADDRESS`        | Updated address of the fosterer                                                                                                                                           | `Orchard road, Blk 8, #13-04`                               |
| `HOUSING_TYPE`   | - Updated housing type of the fosterer<br/> - Case-sensitive<br/> - Can only take the values shown in the example column                                                  | `HDB`, `Condo`, `Landed`, `nil`                             |
| `AVAILABILITY`   | - Updated availability of the fosterer<br/> - Case-sensitive<br/> - Can only take the values shown in the example column                                                  | `NotAvailable`, `Available`, `nil`                          |
| `ANIMAL_NAME`    | Updated name of animal fostered                                                                                                                                           | `Fluffball`, `nil`                                          |
| `TYPE_OF_ANIMAL` | - Updated type of animal which the fosterer is currently fostering, or prefer to foster<br/> - Case-sensitive<br/> - Can only take the values shown in the example column | `current.Dog`, `current.Cat`, `able.Dog`, `able.Cat`, `nil` |
| `TAG`            | Tag to be associated with the fosterer                                                                                                                                    | `experienced`, `urgent`                                     |

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>

* The index of the fosterer has to be provided. However, the number of parameters to be edited can vary from zero to all fields.

</div>

<div markdown="block" class="alert alert-danger">

**:exclamation: Important:**<br>

* If the parameters are not provided, <code>edit INDEX</code> <b>operates the same way as</b> <code>view INDEX</code>, leading you to the profile page of the person at index <code>INDEX</code> in the address book. 
* If you run the **same** `edit` **command multiple times consecutively** (resulting in no visible change after the first run), the undo command _will not_ be able to revert the data back to the original state. <br>
  This is because `undo` can only undo the last command, _even if the command made no visible changes_.
</div>

<div style="page-break-after: always;"></div>

Examples:
*  `find` or `list` followed by `edit 3 n/John` 
   * edits the name of the 3rd fosterer in your address book to "John".

<div></div>

*  `find` or `list` followed by `edit 1 p/12345678 animal/Bobby` 
   * edits the phone number and animal name of the 1st fosterer in your address book to "12345678" and "Bobby" respectively.
   ![edit 1 example](images/screenshots/EditExample2.png) <br>

<div></div>

*  `find` or `list` followed by `edit 2` 
   * opens the profile page of the 2nd fosterer in your address book, since parameters are not provided.

<div markdown="block" class="alert alert-warning">

**:warning: Caution:**<br>

**Edit for tags** in the main page **overwrites existing tags**.<br>
e.g. If the first fosterer has 2 tags : `experienced` and `reliable`, entering `edit 1 t/goodWithCats` will mean fosterer 1 will lose the `experienced` and `reliable` tags. <br>

This is not a problem when editing using the [profile page](#editing-a-fosterers-details-through-the-profile-page-edit). <br>

</div>

<br>

<div style="page-break-after: always;"></div>

### Editing a fosterer's details through the profile page: `edit`

Edits the details of the fosterer at the specified index in your address book, by redirecting you to the profile page.

Format: `edit INDEX`

Parameters:

| Parameter | About                                                                                                                                                                    | Example |
|-----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| `INDEX`     | - The index of a fosterer displayed in the list obtained from a `list`/`find` command <br/> - Index must be a positive integer | `1`, `2`, `3` |


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

* Apart from the details added by the add command, we also provide an optional **notes feature** in the profile page for 
more flexibility.

<div></div>

  ![notes example](images/screenshots/Notes.png)

* You can use this to include _additional details_ such as: 
  * Health condition of the animal
  * Foster period of the animal
  * Identifiable physical traits of the animal
 
</div>

<div style="page-break-after: always;"></div>

<br/>

If you have at least one fosterer in your address book, here is an example of a profile page you will see after entering `view 1` or `edit 1`:

<div style="text-align: center">
<img src="images/screenshots/View1.png" height="480" class="center"/>
</div>

<div></div>

To learn more about the profile page, please refer to the section [User Interface: The profile page](#the-profile-page). 

<br> 

<div style="page-break-after: always;"></div>

### Saving changes in a fosterer's details: `save`

Saves changes to details of the fosterer which you have made in the profile page.

<div markdown="block" class="alert alert-danger">

**:exclamation: Important:**<br>

This command only works in the profile page, which you can navigate to by executing the `view` command.

</div>

Format: `save`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

*  Entering <code>save</code> in [the profile of a new fosterer](#adding-a-fosterer-through-the-profile-page-add) saves the new fosterer and exits the profile page.
*  Entering <code>save</code> in [the profile of an already existing fosterer](#editing-a-fosterers-details-through-the-profile-page-edit) saves the changes but does not exit the profile page, allowing you to edit more details if needed.
</div>

<br> 

<div style="page-break-after: always;"></div>

### Deleting a fosterer : `delete`

Deletes the fosterer at the specified index of your currently displayed list.

Format: `delete INDEX [INDEX...]`

<div markdown="block" class="alert alert-warning">

**:warning: Caution:**<br>

The **index** of a fosterer **is not fixed**. It is relative to the current list of fosterers you are handling.

</div>

Parameters:

| Parameter  | About                                                                                                                                                                      | Example       |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|
| `INDEX`    | - The index of a fosterer displayed in the list obtained from a `list`/`find` command <br/> - At least one index must be provided<br/> - Index must be a positive integer  | `1`, `2`, `3` |

<div markdown="block" class="alert alert-primary">

**:bulb: Tip:**<br>

You can **delete multiple fosterers at once**.<br> 

* Each index needs to be separated by a white space.<br>
* Any duplicates and extra white spaces will be ignored.

</div>

<div style="page-break-after: always;"></div>

Examples:
* `list` followed by `delete 2` 
  * deletes the 2nd fosterer in your address book.

<div></div>

* `find Jerry` or `list Jerry`, followed by `delete 1`
  * deletes the 1st fosterer in the result list of your `find` / `list` query

<div></div>

* `list` followed by `delete 1 3 7` 
  * deletes the 1st, 3rd and 7th fosterers in your address book.
  <div></div>

    ![Delete](images/screenshots/Delete.png)
In the example above, Alex, Bernice and Charlotte are the fosterers deleted. 
  
<div></div>

* `list` followed by `delete 3 3 3 3` 
  * deletes the 3rd fosterer in your address book.

<br> 

<div style="page-break-after: always;"></div>

### Sorting fosterers: `sort`

Sorts your list of fosterers alphabetically by name, where uppercase letters come before lowercase letters 
(i.e. `"annie tan"` will be sorted behind `"Jerry Tan"`).

Format: `sort`

![Sort](images/screenshots/Sort.png)

<br> 

<div markdown="block" class="alert alert-danger">

**:exclamation: Important:**<br>

Repeated `sort` commands will not result in additional changes to the address book, and `undo` will not be able to revert the data back to its original state. <br>
This is because `undo` can only revert the last command, _even if the command made no changes to the address book_.

</div> 

<div style="page-break-after: always;"></div>

### Viewing statistics of available fosterers : `stats avail`

Helps you calculate statistics about fosterers who are available to foster, and the animals they can foster. Percentages are calculated to 2 decimal places.

Format: `stats avail`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

* All statistic commands are **calculated based on the list currently displayed** in your address book.<br>
In the example below, `find available` was first entered, resulting in a list of 2 available fosterers. <br>
`stats avail` was then entered, and we see the resulting statistic reporting all listed fosterers as available. <br>

![Stats](images/screenshots/StatsAllAvail.png)<br>

Therefore, remember to use the `find` or `list` commands to get the list you want your statistics to be calculated from first, before using the statistic commands.<br>

</div>

<div style="page-break-after: always;"></div>

Examples:
* `list` followed by `stats avail` 
  * calculates statistics of available fosterers, taking into account all fosterers in your address book.
  
  <div></div>
  
  ![Stats](images/screenshots/StatsAvail.png)

  In the example above, you have 6 fosterers in your address book, and 3 of them are available to foster.

  <div></div>
  <div></div>


* `find cat` followed by `stats avail` 
  * calculates statistics of available fosterers, only taking into account fosterers who are either currently fostering a cat or are able to foster a cat.

<br> 

<div style="page-break-after: always;"></div>

### Viewing statistics of current fosterers : `stats current`
Helps you calculate statistics about fosterers who are currently fostering, and the type of animals they are fostering. Percentages are calculated to 2 decimal places.

Format: `stats current`

Examples:
* `list` followed by `stats current` 
  * calculates statistics of current fosterers, taking into account all fosterers in your address book.
  
  <div></div>

  ![Stats](images/screenshots/StatsCurrent.png)

  In the example above, you have 6 fosterers in your address book, and 2 of them are currently fostering.

  <div></div>
  <div></div>


* `find dog` followed by `stats current`
  * calculates statistics of current fosterers, only taking into account fosterers who are either currently fostering a dog,  or are able to foster a dog.

<br> 

<div style="page-break-after: always;"></div>

### Viewing statistics of housing types: `stats housing`
Helps you calculate statistics about the various housing types of fosterers. Percentages are calculated to 2 decimal places.

Format: `stats housing`

Examples:
* `list` followed by `stats housing`
  * calculates housing statistics, taking into account all fosterers in your address book.
  
  <div></div>
  
  ![Stats](images/screenshots/StatsHousing.png)

  In the example above, out of the 6 fosterers in your address book, 3 live in HDBs, 1 live in a Condo, and 2 live in Landed properties.

  <div></div>
  <div></div>


* `find available` followed by `stats housing` 
  * calculates housing statistics, only taking into account fosterers who are available.

<br> 

<div style="page-break-after: always;"></div>

### Undoing the previous command : `undo`

Undoes your previous command, given that the previous command successfully executed is either `add`, `delete`, `edit`, `sort` or a successful execution of the `reset` command.


Format: `undo`


<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

*  The `undo` command can only be executed **once** at a time, and it will undo your last successful command. When the `undo` command is executed consecutively more than once, an error message will be shown:
![Undo](images/screenshots/UndoError.png)

</div>

<br> 

<div style="page-break-after: always;"></div>

### Clearing all entries : `reset`, followed by `reset confirm`


Clears all your fosterer entries from the address book.

Format: `reset`, followed by `reset confirm`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

*  Upon entering `reset`, a confirmation message will be shown for the user to verify if he/she really wants to clear all the fosterer entries.
![Reset](images/screenshots/Reset.png)

   * User is prompted to enter `reset confirm` to confirm and execute the deletion of all fosterers.
![Reset](images/screenshots/ResetConfirm.png)

   * In the case where the user wishes to **cancel the reset**, he/she just has to proceed to type any other **valid command** in the command box.


</div>

<br> 

<div style="page-break-after: always;"></div>

### Exiting Foster Family : `exit`

Exits the app.

Format: `exit`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command:**<br>

On the profile page, entering <code>exit</code> leads you back to the main page, instead of exiting the app.

Please refer to [User Interface: The profile page: Exiting the profile page](#exiting-the-profile-page) for more information. 

</div>
 

### Saving data

In the **main page**, your Foster Family data is **saved in the hard disk automatically** after any command that changes the data, so no manual saving is needed. However, **edits made in the profile page have to be saved** via the `save` command. Else, changes will be discarded once you exit out of that fosterer's profile page.

<div style="page-break-after: always;"></div>

### Editing data file

* Your Foster Family data is saved automatically as a JSON file with the file path <br>
`[JAR file location]/data/addressbook.json`.<br>

* A JSON file stores fosterer details as key value pairs, making it more readable than a regular text file.<br>

  <img src="images/screenshots/JsonExample.png" height="400" class="center"/>
  
  In the example above, the "name" key is paired with a value "Alex Yeoh" for the first fosterer in your address book.<br>


* We strongly advise you to update the data file directly **only if you are an advanced user**.<br>

* Otherwise, we highly recommend you to perform edits using our user-friendly interface instead.<br>

<div></div>

<div markdown="block" class="alert alert-warning">

**:warning: Caution:**<br>

If your changes to the data file makes its **format invalid**, Foster Family will **discard all data** and start with an empty data file at the next run. Hence, it is recommended to make a **backup** of the file before editing it.<br>

</div>

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer, and overwrite the empty data file it creates with the file that contains the data of your previous Foster Family home folder.

**Q**: How do I know which version of Java I am using / have installed on my computer?<br>
**A**: Open a command terminal, type `java -version` and press Enter. The Java version in use will be displayed as a response message.

--------------------------------------------------------------------------------------------------------------------

## **Known Issues**

- If you are **using multiple screens** and you move the app to a secondary screen, a part of the GUI may appear "off-screen" if you later choose to switch back to your primary screen. <br>
  To resolve this, you can delete the `preferences.json` file that was created, before running the app again. This file is located in the same home folder as your jar file.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Command Summary**

| Action                                 | Format                                                                                                                                                                    | Examples                                                                                                                                                                |
|----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                               | `help`                                                                                                                                                                    | -                                                                                                                                                                       |
| **Add** from main page                 | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS housing/HOUSING_TYPE availability/AVAILABILITY animal/ANIMAL_NAME animalType/TYPE_OF_ANIMAL [t/TAG]…`                        | `add n/Jerry Tan p/98765412 e/jerry123@example.com a/Baker street, block 5, #27-01 housing/HDB availability/NotAvailable animal/Dexter animalType/current.Cat t/Urgent` |
| **Add** from profile page              | `add`                                                                                                                                                                     | -                                                                                                                                                                       |
| **List**  or **Find**                  | `list`, `find`                                                                                                                                                            | `list`, `find`,  `list available`, `find available`                                                                                                                     |
| **View Profile**                       | `view INDEX`                                                                                                                                                              | `view 1`                                                                                                                                                                |
| **Save updated fosterer details**      | `save`                                                                                                                                                                    | -                                                                                                                                                                       |
| **Edit** from main page                | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [housing/HOUSING_TYPE] [availability/AVAILABILITY] [animal/ANIMAL_NAME] [animalType/TYPE_OF_ANIMAL] [t/TAG…]` | `edit 2 n/James Lee e/jameslee@example.com`                                                                                                                             |
| **Edit** from profile page             | `edit INDEX`                                                                                                                                                              | `edit 1`                                                                                                                                                                |
| **Delete**                             | `delete INDEX [INDEX...]`                                                                                                                                                 | `delete 1 2 3`                                                                                                                                                          |
| **Sort**                               | `sort`                                                                                                                                                                    | -                                                                                                                                                                       |
| **View Available Fosterer Statistics** | `stats avail`                                                                                                                                                             | -                                                                                                                                                                       |
| **View Current Fosterer Statistics**   | `stats current`                                                                                                                                                           | -                                                                                                                                                                       |
| **View Housing Statistics**            | `stats housing`                                                                                                                                                           | -                                                                                                                                                                       |
| **Undo**                               | `undo`                                                                                                                                                                    | -                                                                                                                                                                       |
| **Clear all data entries**             | `reset`, followed by `reset confirm`                                                                                                                                      | -                                                                                                                                                                       |
| **Exit** from app / profile page       | `exit`                                                                                                                                                                    | -                                                                                                                                                                       |
