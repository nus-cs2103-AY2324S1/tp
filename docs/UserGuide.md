---
layout: page
title: FoodNotes User Guide
---

<a name="introduction"></a>
## 1. Introduction

Welcome to the User Guide of FoodNotes!


Ever find yourself forgetting the delightful dishes you savored? Hungry for a way to recall your favorite food experiences? FoodNotes has the solution.

FoodNotes is a quick, simple and beautiful food diary made specifically for you, NUS student foodies. With FoodNotes, you can add stalls that you want to keep track of and manage your reviews for them.

This application is optimised for use via a Command Line Interface (CLI). This means that you operate the application by typing commands into a _Command Box_. If you are fast at typing, FoodNotes can help you manage your food reviews faster than other Graphical User Interface (GUI) applications; GUI applications allow users to interact with the application through graphical icons such as buttons.

FoodNotes is available for the Linux, Unix, Windows and Mac OS operating systems.

<div style="page-break-after: always;"></div>

## Table of Contents
1. [Introduction](#introduction)
2. [About the User Guide](#about-the-user-guide) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;2.1 [Navigating the User Guide](#navigating-the-user-guide) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;2.2 [Reading the User Guide](#reading-the-user-guide) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.1 [Terminology related to the GUI](#terminology-related-to-the-gui) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.2 [Icons](#icons) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.2.3 [Command Syntax and Usage](#command-syntax-and-usage) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;2.3 [Input parameters](#input-parameters) <br>
3. [Quick Start](#quick-start) <br>
4. [Features](#features) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;4.1 [General Features](#general-features) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.1 [Viewing help `help`](#viewing-help) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.2 [Exiting the application `exit`](#exiting-the-application) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.1.3 [Clearing the database `clear`](#clearing-the-database) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;4.2 [Stall Management](#stall-management) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.1 [Listing all stalls `list`](#listing-all-stalls) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.2 [Viewing a stall `view-stall`](#viewing-a-stall) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.3 [Adding a stall `add-stall`](#adding-a-stall) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.4 [Deleting a stall `delete-stall`](#deleting-a-stall) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.5 [Editing a stall `edit-stall`](#editing-a-stall) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.6 [Reviewing a stall `review-stall`](#reviewing-a-stall) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.2.7 [Deleting a stall review `delete-stall-review`](#deleting-a-stall-review) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;4.3 [Stall Sorting](#stall-sorting) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.1 [Sorting stalls by location `sort-stalls-location`](#sorting-stalls-by-location) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.2 [Sorting stalls by rating `sort-stalls-rating`](#sorting-stalls-by-rating) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.3.3 [Sorting stalls by price `sort-stalls-price`](#sorting-stalls-by-price) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;4.4 [Stall Finding](#stall-finding) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.1 [Finding stalls by name `find-by-name`](#finding-stalls-by-name) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.2 [Finding stalls by location `find-by-location`](#finding-stalls-by-location) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.4.3 [Finding stalls by item `find-by-item`](#finding-stalls-by-item) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;4.5 [Item Management](#item-management) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.1 [Viewing an item `view-item`](#viewing-an-item) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.2 [Adding an item `add-item`](#adding-an-item) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.3 [Deleting an item `delete-item`](#deleting-an-item) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.4 [Editing an item `edit-item`](#editing-an-item) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.5 [Reviewing an item `review-item`](#reviewing-an-item) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.6 [Deleting an item review `delete-item-review`](#deleting-an-item-review) <br>
5. [Command Summary](#command-summary) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;5.1 [General Commands](#general-commands) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;5.2 [Stall Management Commands](#stall-management-commands) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;5.3 [Stall Sorting Commands](#stall-sorting-commands) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;5.4 [Stall Finding Commands](#stall-finding-commands) <br>
   &nbsp;&nbsp;&nbsp;&nbsp;5.5 [Item Management Commands](#item-management-commands) <br>
6. [FAQ](#faq) <br>

---

<div style="page-break-after: always;"></div>

## 2. About the user guide <a id="about-the-user-guide"></a>
This section is your guide to the user guide! It gives you all the information you need to easily navigate and get the most out of the user guide.
### 2.1 Navigating the user guide <a id="navigating-the-user-guide"></a>

**If you are a first time user:**

1. The [Quick Start](#quick-start) section provides instructions for you on how to get started.
2. Once you have set up FoodNotes, you can check out the [Layout Screen](#terminology-related-to-the-gui) section to get familiar
with the different components of FoodNotes. To learn the basics of using FoodNotes, head over to the [Features](#features) section.

**If you are an experienced user:**

You can refer to the [Command summary](#command-summary) section for an overview of FoodNotes' commands.


### 2.2 Reading the User Guide <a id="reading-the-user-guide"></a>
Before you jump into the upcoming sections, take a moment to familiarise yourself with the technical terms, icons, and syntax used throughout this document. This subsection contains all the essential information you need to understand the content better.

#### 2.2.1 Terminology related to the Graphical User Interface (GUI) <a id="terminology-related-to-the-gui"></a>
Let's introduce you to FoodNotes' user interface! The following image showcases the different components and their respective functions in FoodNotes.

<p align="center">
<img src="images/userGuide/Frame.png" alt="Layout of GUI">
</p>
What the main components do:

* _Command Box_: This is where you type your commands.

* _Result Display_: This is where FoodNotes displays the result of your commands.

* _Left and Right Display_: This is where FoodNotes displays the information of the page that you are viewing.

<div style="page-break-after: always;"></div>

#### 2.2.2 Icons <a id="icons"></a>

This section will run you through the icons used in this guide.

| Icon                 | Meaning                                                                                                                        |
|----------------------|--------------------------------------------------------------------------------------------------------------------------------|
| :information_source: | An information icon signals that the enclosed text contains notes about how to use FoodNotes.                                  |
| :bulb:               | A light bulb icon signals that the enclosed text contains tips that you might find useful.                                     |
| :warning:            | A warning icon signals that the enclosed text contains warnings about potentially dangerous actions you should be cautious of. |


#### 2.2.3 Command Syntax and Usage <a id="command-syntax-and-usage"></a>
Since FoodNotes is a CLI application, knowing how to use commands is crucial. The following subsection will guide you on how to read and use commands in FoodNotes. All commands in FoodNotes follow similar formats as illustrated in the table below and examples will be provided to help you understand their usage.


|**Technical Term** | **Meaning**                                                                                                                                          |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------------------|
| Command word | The first word of a command. It determines the action that FoodNotes should perform.                                                                 |
| Prefix | The prefix precedes a parameter. It is used to identify the type of the parameter that is being input. |
| Parameter | The information following the prefix. This information is then used to update FoodNotes.                                                             |


<div markdown="block" class="alert alert-success">

:bulb: **Tip:**<br>
Here is a simple visualisation of the command format.
<img src="images/userGuide/commandIns.png">

</div>

<div style="page-break-after: always;"></div>

### 2.3 Input parameters <a id="input-parameters"></a>

This section provides a summary of the parameters used when inputting commands into the application.

| Prefix | Parameter           | Meaning                                | Input                                             |
|--------|---------------------|----------------------------------------|---------------------------------------------------|
| `n/`   | `STALL_NAME`        | Name of the stall                      | 1 or more characters                              |
| `s/`   | `STALL_INDEX`       | Index of the stall in FoodNotes        | Positive integer from 1 to 2147483647 (inclusive) |
| `l/`   | `STALL_LOCATION`    | Location of the stall                  | 1 or more characters                              |
| `n/`   | `ITEM_NAME`         | Name of the item                       | 1 or more characters                              |
| `i/`   | `ITEM_INDEX`        | Index of the item in the menu          | Positive integer from 1 to 2147483647 (inclusive) |
| `p/`   | `ITEM_PRICE`        | Price of the item                      | Non-negative number with 2 decimal places         |
| `r/`   | `STALL_RATING`      | Rating of the stall                    | Positive integer from 1 to 5 (inclusive)          |
| `r/`   | `ITEM_RATING`       | Rating of the item                     | Positive integer from 1 to 5 (inclusive)          |
| `d/`   | `STALL_DESCRIPTION` | Descriptive review for the stall       | 1 or more characters                              |
| `d/`   | `ITEM_DESCRIPTION`  | Descriptive review for the item        | 1 or more characters                              |
| N/A    | `NAME_KEYWORD`      | Keyword of the stall name              | 1 or more characters                              |
| N/A    | `LOCATION_KEYWORD`  | Keyword of the stall location          | 1 or more characters                              |
| N/A    | `ITEM_KEYWORD`      | Keyword of the menu items in the stall | 1 or more characters                              |

<div markdown="block" class="alert alert-info">

:information_source: **Info:**<br>
Character refers to any letter, number, symbol or space e.g. `a`, `A`, `1`, `!`, `@`, etc.
Positive integers are whole numbers greater than zero, e.g. 1, 2, 3, 4, 5, etc.
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 3. Quick Start <a id="quick-start"></a>

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `FoodNotes.jar` from [here](https://github.com/AY2324S1-CS2103T-W10-4/tp/releases/download/v1.3.1/FoodNotes.jar).

3. Copy the file to the folder you want to use as the _home folder_ for your FoodNotes.
<div markdown="block" class="alert alert-info">

:information_source: **Info:**<br>
The illustration shown below is for Mac OS. The procedure for Windows is similar and the steps apply.

</div> 

   ![steps](images/userGuide/steps.png)
4. Enter your _home-folder_ and right-click on the downloaded jar file as shown.
5. Select on the "New iTerm2 Tab here" option, (or any other terminal option you have) to open a terminal window in the folder.
   ![run](images/userGuide/run.png)
6. Run the command `java -jar FoodNotes.jar` in the terminal window.
7. FoodNotes will now launch and show a list of preloaded stalls.
   ![preloadStalls](images/userGuide/preloadStalls.png)
8. You are now ready to use FoodNotes!
9. Type your commands in the _Command Box_ and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * `add-stall n/Chicken Rice l/Deck` : Adds a stall named `Chicken Rice` located at `Deck` to the list of stalls.

   * `view-stall s/1` : Shows the 1st stall shown in the current list.

   * `list` : Lists all stalls.
10. Refer to the [Features](#features) below for more details of each command.

<div markdown="block" class="alert alert-success">

:bulb: **Tip:**<br>

If double-clicking `FoodNotes.jar` does not work,

1. Search for "Command Prompt" or "Terminal" on your computer.
2. Navigate to the location where `FoodNotes.jar` is saved via the terminal. (_Unsure how to navigate?_ You can try running this in your terminal: `cd Downloads`.)
3. Run the following in the terminal: `java -jar FoodNotes.jar`.

</div>


--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4. Features <a id="features"></a>
Now that you have started up FoodNotes, you are ready to start using its features! This section unveils the array of features FoodNotes has to offer.

### 4.1 General Features <a id="general-features"></a>

The commands in this section allow you to perform operations on the entire system. They include:
- Viewing help
- Exiting from the application
- Clearing the FoodNotes database

<a id="viewing-help"></a>
#### 4.1.1 Viewing help : `help`

You can use this command to view the online user guide, which contains information about all the instructions in FoodNotes.

<div markdown="block" class="alert alert-info">

:information_source: **Info:**<br>
Ensure that you have a stable internet connection and have a web browser on your computer in order to access the user guide.

</div>

**Format:**

`help`

**Example:**
<div markdown="block" class="alert alert-white">

You want to view the user guide of FoodNotes.

1. Type `help` into the _Command Box_ and press `Enter` to execute.
2. A new window will appear as shown below. Click on the `Copy URL` button to copy the URL containing the FoodNotes user guide.

   ![HelpOutcome](images/userGuide/helpCommandOutcome.png)

3. Open your preferred web browser on your computer and paste the link into the address bar.
4. Press `Enter` to load the website.

Outcome:

1. Your browser will now show the user guide of FoodNotes.
2. You can now access information about all commands in FoodNotes via the user guide.

</div>

<a id="exiting-the-application"></a>
#### 4.1.2 Exiting from the application: `exit`

You can use this command to exit FoodNotes.

<div markdown="block" class="alert alert-info">

:information_source: **Info:**<br>
This command will exit the application immediately. All data is saved automatically and will be reloaded upon restart of the application.

</div>

**Format:**

`exit`

**Example:**
<div markdown="block" class="alert alert-white">

You want to exit FoodNotes.

Type `exit` into the _Command Box_ and press `Enter` to execute.

Outcome:

The FoodNotes application window is now closed.
</div>

#### 4.1.3 Clearing all the stalls in FoodNotes: `clear` <a id="clearing-the-database"></a>

You can use this command to clear all existing stalls in FoodNotes.

<div markdown="block" class="alert alert-danger">

:warning: **Warning:**<br>

This command will permanently delete all data stored in FoodNotes. You should make a backup of `addressbook.json` if you still want to keep your old data.

</div>

**Format:**

`clear`

**Example:**

<div markdown="block" class="alert alert-white">

You want to clear all data stored in FoodNotes.

Type `clear` into the _Command Box_ and press `Enter` to execute.

Outcome:

The FoodNotes database is now empty.

![ClearOutcome](images/userGuide/clearCommandOutcome.png)

</div>

----


### 4.2 Stall Management <a id="stall-management"></a>

Explore FoodNotes' stall management features designed to simplify your experience. The stall management features include:
- Listing all stalls
- Viewing a stall
- Adding a stall
- Deleting a stall
- Editing a stall
- Reviewing a stall
- Deleting a stall review

#### 4.2.1 Listing all stalls : `list` <a id="listing-all-stalls"></a>

You can use this one-word command to view the list of all the stalls in FoodNotes..

**Format:**

`list`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to view all the stalls in FoodNotes.
1. Type `list` into the _Command Box_ and press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. The _Left Display_ will show you the list of stalls.
</div>

![ListOutcome](images/userGuide/listCommandOutcome.png)

#### 4.2.2 Viewing a specific stall : `view-stall` <a id="viewing-a-stall"></a>

You can use this command to view details from a specific stall from FoodNotes and take a look at its menu items and review of the stall.

**Format:**

`view-stall s/STALL_INDEX`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to view more details of the Japanese stall, which is the first stall in the list.

1. Type `view-stall s/1` into the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. The _Left Display_ will show you the list of menu items from the stall.
3. The _Right Display_ will show you the details of the stall.

</div>

![ListOutcome](images/userGuide/viewStallOutcome.png)

#### 4.2.3 Adding a stall : `add-stall` <a id="adding-a-stall"></a>

You can use this command to add a new stall to FoodNotes.

**Format:**

`add-stall n/STALL_NAME l/STALL_LOCATION`

**Example:**

<div markdown="block" class="alert alert-white">

You just visited the newly opened Subway at Utown and are eager to add it to FoodNotes.

1. Type `add-stall n/Subway l/Utown` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now added a new stall with the specified name and location.
2. The _Left Display_ will show you the updated list of stalls.
</div>

![ListOutcome](images/userGuide/addStallOutcome.png)

<div markdown="block" class="alert alert-info">

:information_source: **Info:**<br>

* Let's keep it unique! Avoid adding duplicate stalls with the same name and location (case-insensitive) to FoodNotes.


</div>


#### 4.2.4 Deleting a stall : `delete-stall` <a id="deleting-a-stall"></a>

You can use this command to delete a stall from FoodNotes, making sure that your food diary is always up-to-date.

**Format:**

`delete-stall s/STALL_INDEX`

**Example:**


<div markdown="block" class="alert alert-white">

Western Stall has closed down, and you want to delete it from FoodNotes.

1. Type `delete-stall s/3` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now deleted the third stall from the list.
2. The _Left Display_ will show you the updated list of stalls.
</div>

![ListOutcome](images/userGuide/deleteStallOutcome.png)

#### 4.2.5 Editing a specific stall : `edit-stall` <a id="editing-a-stall"></a>

You can use this command to edit an existing stall, and update its name and/or location. If the stall has a review,
you may edit its rating and/or description.

**Format:**

`edit-stall s/STALL_INDEX [n/STALL_NAME] [l/STALL_LOCATION] [r/STALL_RATING] [d/STALL_DESCRIPTION] `

<div markdown="block" class="alert alert-info">
:information_source: **Info:**<br>
Parameters in the square brackets are optional, but you must specify at least one of them.
In addition, you can only edit ratings and descriptions if the stall already has an existing review.
</div>

**Example:**

<div markdown="block" class="alert alert-white">

You wish to change the location of the Western Stall in the list, as the stall has relocated from Utown to Deck.


1. Type `edit-stall s/1 l/Deck` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now edited the first stall from the list, and the list of stalls will be updated.
2. The _Left and Right Display_ will show you the stall's updated information.
</div>

![ListOutcome](images/userGuide/editStallOutcome.png)

#### 4.2.6 Reviewing a stall : `review-stall` <a id="reviewing-a-stall"></a>

You can use this command to add some personal touch to your food diary! Add star rating and even some text descriptions for the stall.

**Format:**

`review-stall s/STALL_INDEX r/STALL_RATING d/STALL_DESCRIPTION`

**Example:**

<div markdown="block" class="alert alert-white">
You want to give the Japanese Stall a five-star rating as you love their cutlets.

1. Type `review-stall s/2 r/5 d/Their cutlets are amazing and crispy.` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now reviewed the second stall in the list.
2. The _Right Display_ will show you the star rating and detailed review of the stall.
</div>

![ListOutcome](images/userGuide/stallReviewOutcome.png)

#### 4.2.7 Deleting a stall review: `delete-stall-review` <a id="deleting-a-stall-review"></a>

You can use this command to delete a review of an existing stall.

**Format:**

`delete-stall-review s/STALL_INDEX`

**Example:**

<div markdown="block" class="alert alert-white">

You decide to delete the review for the Western Stall as you accidentally reviewed the wrong stall.


1. Type `delete-stall-review s/1` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now deleted the review of the first stall in the list.
2. The _Right Display_ will show you that the review has been deleted.
</div>

![ListOutcome](images/userGuide/delete-stall-review.jpeg)


---------------------------------------------------------------------------------------------------------------------

### 4.3 Stall Sorting <a id="stall-sorting"></a>
These features allow you to sort stalls in FoodNotes. The sorting features include:
- Sorting stalls by location
- Sorting stalls by rating
- Sorting stalls by price

#### 4.3.1 Sorting stalls by location: `sort-stalls-location` <a id="sorting-stalls-by-location"></a>

You can use this command to sort the stalls by their location in alphabetical order.

**Format:**

`sort-stalls-location`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to sort the stalls by their location in alphabetical order.

1. Type `sort-stalls-location` in the _Command Box_.
2. press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. You will now see that the stalls are sorted by location in alphabetical order in the _Left Display_.

</div>

![ListOutcome](images/userGuide/sort-stalls-location.png)

#### 4.3.2 Sorting stalls by rating: `sort-stalls-rating` <a id="sorting-stalls-by-rating"></a>

You can use this command to sort the stalls by their rating in descending order, so the stalls with the highest ratings will appear at the top!

**Format:**

`sort-stalls-rating`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to sort the stalls by their rating in descending order.

1. Type `sort-stalls-rating` in the _Command Box_.
2. press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. You will now see that the stalls are sorted by rating in descending order in the _Left Display_.
</div>

![ListOutcome](images/userGuide/sort-stalls-rating.png)

#### 4.3.3 Sorting stalls by price: `sort-stalls-price` <a id="sorting-stalls-by-price"></a>

You can use this command to sort the stalls by the average price of its menu items in ascending order, so the cheapest stalls will appear at the top!

**Format:**

`sort-stalls-price`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to sort the stalls by their average price in ascending order.


1. Type `sort-stalls-price` in the _Command Box_.
2. press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. You will now see that the stalls are sorted by average price in ascending order in the _Left Display_.
</div>

![ListOutcome](images/userGuide/sort-stalls-price.png)



--------------------------------------------------------------------------------------------------------------------

### 4.4 Stall Finding <a id="stall-finding"></a>

These features allow you to find stalls in FoodNotes. The finding features include:
- Finding stalls by name
- Finding stalls by location
- Finding stalls by item

#### 4.4.1 Finding stalls by name: `find-by-name` <a name="finding-stalls-by-name"></a>
You can use this command to quickly locate specific food stalls based on their names. Even if you can only remember part of the stall name, this command can help you narrow down your choices effectively.

**Format:**

`find-by-name NAME_KEYWORD`

<div markdown="block" class="alert alert-info">


:information_source: **Info:**<br>
If you have a "Japanese Stall" in FoodNotes, you can find it using `find-by-name japanese`
as the `find` commands are case-insensitive.
However, partial or incomplete matches won't count as a match, thus`find-by-name japan` will not return any results as "japan" is not an exact match for "japanese".
</div>

**Example:**

<div markdown="block" class="alert alert-white">

You wish to find stalls that have 'Japanese' or 'Western' or 'Noodles' in their names.

1. Type `find-by-name Japanese Western Noodles` in the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message indicating the number of matches found.
2. You will now see the matching stalls in the _Left Display_.
</div>

![ListOutcome](images/userGuide/find-by-name.png)

<div markdown="block" class="alert alert-success">

:bulb: **Tip:**<br>

* You can enter more than one keyword, separated by a space, and all the stalls containing any of the keywords will be listed!

</div>

---
#### 4.4.2 Finding stalls by location: `find-by-location` <a id="finding-stalls-by-location"></a>
You can use this command to quickly locate specific food stalls based on their locations. If you want to find the nearest food options, this command can help you narrow down your choices effectively.

**Format:**

`find-by-location LOCATION_KEYWORD`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to find stalls that are located at the Deck.

1. Type `find-by-location deck` in the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message indicating the number of matches found.
2. You will now see the matching stalls in the _Left Display_.
</div>

![ListOutcome](images/userGuide/find-by-location.png)

<div markdown="block" class="alert alert-success">

:bulb: **Tip:**<br>

* You can enter more than one keyword, separated by a space, and all the stalls containing any of the keywords will be listed!

</div>

---
#### 4.4.3 Finding stalls by item: `find-by-item` <a id="finding-stalls-by-item"></a>
You can use this command to quickly locate specific food stalls based on the items on their menu. If you are craving for a specific dish, this command can help you narrow down your choices effectively.

**Format:**

`find-by-item ITEM_KEYWORD`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to find stalls that have chicken in their menu.

1. Type `find-by-item chicken` in the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message indicating the number of matches found.
2. You will now see the matching stalls in the _Left Display_.
</div>

![ListOutcome](images/userGuide/find-by-item.png)


<div markdown="block" class="alert alert-success">

:bulb: **Tip:**<br>

* You can enter more than one keyword, separated by a space, and all the stalls containing any of the keywords will be listed!
</div>



-----

<div style="page-break-after: always;"></div>

### 4.5 Item Management <a id="item-management"></a>
These features allow you to manage information about the items in FoodNotes. The item management features include:

- Viewing an item
- Adding an item
- Deleting an item
- Editing an item
- Reviewing an item
- Deleting an item review

#### 4.5.1 Viewing an item : `view-item` <a id="viewing-an-item"></a>

You can use this command to view details of a specific item from a specific stall in FoodNotes,
which includes the price, rating and description of the item.

**Format:**

`view-item s/STALL_INDEX i/ITEM_INDEX`

**Example:**

<div markdown="block" class="alert alert-white">

You want to view more about Chicken Katsu Curry from the Japanese stall.

1. Type `view-item s/1 i/1` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. The _Right Display_ will show you the details of item.
</div>

![ListOutcome](images/userGuide/viewItemCommandOutcome.png)

#### 4.5.2 Adding an item : `add-item` <a id="adding-an-item"></a>

You can use this command to add your new favourite food item to your favourite stall in FoodNotes.

**Format:**

`add-item s/STALL_INDEX n/ITEM_NAME p/ITEM_PRICE`

**Example:**

<div markdown="block" class="alert alert-white">

You want to add a new menu item named 'Pork Chop' with a price of $5.50 to the menu of the Japanese stall.

1. Type `add-item s/1 n/Pork Chop p/5.50` into the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. You have now added a new item with the specified name and price.
3. The _Left Display_ will show you the updated list of items from the stall.
4. The _Right Display_ will show you the new item.
</div>

![ListOutcome](images/userGuide/add-item.png)

<div markdown="block" class="alert alert-info">

:information_source: **Info:**<br>

* You are not allowed to add duplicate items of the same name (case-insensitive) to a stall.

</div>

#### 4.5.3 Deleting an item : `delete-item` <a id="deleting-an-item"></a>

You can use this command to delete an item from the database.

**Format:**

`delete-item s/STALL_INDEX i/ITEM_INDEX`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to delete item "mochi" from Western Stall.

1. Type `delete-item s/1 i/1` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now deleted the first menu item of the first stall.
2. The _Left Display_ will show you the updated list of items from the stall.
</div>

![ListOutcome](images/userGuide/delete-item.jpeg)



#### 4.5.4 Editing an item : `edit-item` <a id="editing-an-item"></a>

You can use this command to edit an existing item, and update its name, location, rating and/or description.

**Format:**

`edit-item s/STALL_INDEX i/ITEM_INDEX [n/ITEM_NAME] [p/ITEM_PRICE] [r/ITEM_RATING] [d/ITEM_DESCRIPTION] `

<div markdown="block" class="alert alert-info">
:information_source: **Info:**<br>
Parameters in the square brackets are optional, but you must specify at least one of them.
In addition, you can only edit ratings and descriptions if the item already has an existing review.
</div>

**Example:**

<div markdown="block" class="alert alert-white">

You wish to edit the price of tiramisu from the Western Stall as the price has increased.

1. Type `edit-item s/1 i/1 p/20.10` into the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. You have now edited the first menu item of the first stall.
3. The _Right Display_ will show you the item with the updated information.
</div>

![ListOutcome](images/userGuide/edit-item.jpeg)


#### 4.5.5 Reviewing an item : `review-item` <a id="reviewing-an-item"></a>

You can use this command to review an existing item, and add your own star rating and text description for your experience at the stall.

**Format:**

`review-item s/STALL_INDEX i/ITEM_INDEX r/ITEM_RATING d/ITEM_DESCRIPTION`

**Example:**

<div markdown="block" class="alert alert-white">

You wish to review the chicken cutlet from the Japanese Stall as it was delicious.

1. Type `review-item s/1 i/1 r/4 d/Very Flavourful` into the _Command Box_.
1. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
1. You have now reviewed the first menu item of the first stall.
2. The _Right Display_ will show you the star rating and detailed review of the item.
</div>

![ListOutcome](images/userGuide/reviewItemOutcome.png)


#### 4.5.6 Deleting an item review: `delete-item-review` <a id="deleting-an-item-review"></a>

You can use this command to delete a review of an existing item.

**Format:**

`delete-item-review s/STALL_INDEX i/ITEM_INDEX`

**Example:**

<div markdown="block" class="alert alert-white">

You decide to delete the review for tiramisu from Western Stall as you accidentally reviewed the wrong item.

1. Type `delete-item-review s/1 i/1` into the _Command Box_.
2. Press `Enter` to execute.

Outcome:

1. The _Result Display_ will show you a success message.
2. You have now deleted the review of the first menu item of the first stall, and the menu will be updated.
3. The _Right Display_ will show you that the review has been deleted.
</div>

![ListOutcome](images/userGuide/delete-item-review.png)


--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 5. Command summary <a id="command-summary"></a>

### 5.1 General Commands <a id="general-commands"></a>

| Features  | Format, Examples |
|-----------|------------------|
| **Help**  | `help`           |
| **Exit**  | `exit`           |
| **Clear** | `clear`          |

### 5.2 Stall Management Commands <a id="stall-management-commands"></a>


| Features                | Format, Examples                                                                                                                         |
|-------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| **List**                | `list` <br> e.g. `list`                                                                                                                  |
| **View stall**          | `view-stall s/STALL_INDEX` <br> e.g. `view-stall` s/1                                                                                    |
| **Add stall**           | `add-stall n/STALL_NAME l/STALL_LOCATION` <br> e.g.`add-stall` n/Japanese Stall l/Deck                                                   |
| **Delete stall**        | `delete-stall s/STALL_INDEX` <br> e.g. `delete-stall` s/1                                                                                |
| **Edit stall**          | `edit-stall s/STALL_INDEX [n/STALL_NAME] [l/STALL_LOCATION] [r/STALL_RATING] [d/STALL_DESCRIPTION]` <br> e.g. `edit-stall` s/1 l/Terrace |
| **Review stall**        | `review-stall s/STALL_INDEX r/STALL_RATING d/DESCRIPTION` <br> e.g. `review-stall` s/1 r/5 d/Good food and service                       |
| **Delete stall review** | `delete-stall-review s/STALL_INDEX` <br> e.g. `delete-stall-review` s/1                                                                  |


### 5.3 Stall Sorting Commands <a id="stall-sorting-commands"></a>

| Features                    | Format, Examples                                        |
|-----------------------------|---------------------------------------------------------|
| **Sort stalls by location** | `sort-stalls-location` <br> e.g. `sort-stalls-location` |
| **Sort stalls by rating**   | `sort-stalls-rating` <br> e.g. `sort-stalls-rating`     |
| **Sort stalls by price**    | `sort-stalls-price` <br> e.g. `sort-stalls-price`       |

### 5.4 Stall Finding Commands <a id="stall-finding-commands"></a>

| Features                    | Format, Examples                                             |
|-----------------------------|--------------------------------------------------------------|
| **Find stalls by name**     | `find-by-name` <br> e.g. `find-by-name` western japanese     |
| **Find stalls by location** | `find-by-location` <br> e.g. `find-by-location` deck terrace |
| **Find stalls by item**     | `find-by-item` <br> e.g. `find-by-item` chicken              |


<div style="page-break-after: always;"></div>


### 5.5 Item Management Commands <a id="item-management-commands"></a>


| Features               | Format, Examples                                                                                                                              |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| **View item**          | `view-item s/STALL_INDEX i/ITEM_INDEX` <br> e.g. `view-item` s/1 i/1                                                                          |
| **Add item**           | `add-item s/STALL_INDEX n/ITEM_NAME p/ITEM_PRICE` <br> e.g.`add-item` s/1 n/Chicken Rice p/4.50                                               |
| **Delete item**        | `delete-item s/STALL_INDEX i/ITEM_INDEX` <br> e.g. `delete-item` s/1 i/1                                                                      |
| **Edit item**          | `edit-item s/STALL_INDEX i/ITEM_INDEX [n/ITEM_NAME] [p/ITEM_PRICE] [r/ITEM_RATING] [d/ITEM_DESCRIPTION]` <br> e.g. `edit-item` s/1 i/1 p/5.00 |
| **Review item**        | `review-item s/STALL_INDEX i/ITEM_INDEX r/ITEM_RATING d/ITEM_DESCRIPTION` <br> e.g. `review-item` s/1 i/1 r/5 d/Flavorful                     |
| **Delete item review** | `delete-item-review s/STALL_INDEX i/ITEM_INDEX` <br> e.g. `delete-item-review` s/1 i/1                                                        |

<div style="page-break-after: always;"></div>

## 6. FAQ<a id="faq"></a>

**Q:** **Can I use FoodNotes without prior experience with CLI (Command Line Interface) applications?** <br>
**A:** Yes, FoodNotes is designed to be user-friendly, even for those unfamiliar with CLI applications. The user guide includes explanations of all commands and their syntax, making it accessible for beginners. 

**Q:** **How do I transfer my data to another computer?** <br>
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FoodNotes home folder.

**Q:** **How can I export or backup my FoodNotes data?** <br>
**A:** Currently, FoodNotes does not support direct data export or backup. We recommend manually backing up the database file from the application directory.

**Q:** **Do I need to manually save my data?** <br>
**A:** No, FoodNotes automatically saves your data after any command that changes the data. There is no need for you to save manually.

**Q:** **Can I run FoodNotes on my mobile device?** <br>
**A:** No, FoodNotes is currently only available for Windows, Mac and Linux operating systems.

--------------------------------------------------------------------------------------------------------------------
