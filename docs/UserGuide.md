---
layout: default.md
title: "User Guide"
pageNav: 4
---

# LoveBook User Guide

## **Overview**
LoveBook, is a **dating-focused** application, revolving around providing **serial daters** with a **convenient**
and **enjoyable** tool to enhance their dating experiences. Featuring **user preferences management**, **date
organization**,
**customizable filtering options** and **best match algorithms**, LoveBook enhances the **efficiency** and 
**effectiveness** of your online dating journey.

## **Table of Contents**

* [Glossary](#glossary)
    * [Common Symbols](#common-symbols)
* [Quick Start](#quick-start)
    * [Download and Installation](#download-and-installation)
    * [Navigating the GUI](#navigating-the-gui)
    * [LoveBook Tutorial: Getting started](#lovebook-tutorial-getting-started)
* [Features](#features)
    * [Managing Dates](#managing-dates)
        * [Adding a new date: `add`](#adding-a-new-date-add)
        * [Editing a date: `edit`](#editing-a-date-edit)
        * [Deleting a date: `delete`](#deleting-a-date-delete)
        * [Listing all dates: `list`](#listing-all-dates-list)
        * [Clearing all dates: `clear`](#clearing-all-dates-clear)
        * [Starring a date: `star`](#starring-a-date-star)
        * [Unstarring a date: `unstar`](#unstarring-a-date-unstar)
    * [Sorting, Finding dates](#sorting-finding-dates)
        * [Finding a date: `find`](#finding-a-date-find)
        * [Filtering dates by a certain metric: `filter`](#filtering-dates-by-a-certain-metric-filter)
        * [Sorting dates by a certain metric: `sort`](#sorting-dates-by-a-certain-metric-sort)
    * [Managing Preferences and Getting Matches](#managing-preferences-and-getting-matches)
        * [Showing your date preferences: `showP`](#showing-your-date-preferences-showp)
        * [Setting your date preferences: `setP`](#setting-your-date-preferences-setp)
        * [Getting a recommended date: `bestMatch`](#getting-a-recommended-date-bestmatch)
        * [Getting a blind date: `blindDate`](#getting-a-blind-date-blinddate)
    * [Other Commands](#other-commands)
        * [Exiting the application: `exit`](#exiting-the-application-exit)
        * [Getting help: `help`](#getting-help-help)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## **Glossary**

| Term                         | Definition                                                                                                                             |
|------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| Date                         | A person that the user is interested in and is currently seeing.                                                                       |
| Metric                       | A certain characteristic of a date. (e.g. Gender, Height)                                                                              |
| Command                      | Text that the user types into the application to perform an action.                                                                    |
| Parameter                    | A value that the user provides to the application when executing a command. (e.g. in`gender/M` M is a parameter)                       |
| GUI                          | Graphical User Interface                                                                                                               |
| CLI                          | Command Line Interface                                                                                                                 |
| Mainstream OS                | Windows, Linux, Unix, OS-X                                                                                                             |
| <box type="tip"></box>       | To provide you with relevant suggestions on how to use the commands as well as address potential confusions when using these commands. |
| <box type="important"></box> | To inform you of some unintended or unexpected consequences that may occur when you use the commands.                                  |

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Common Symbols**

As you navigate through the application, you may come across certain symbols depicting your date's gender and horoscope.
Here's a quick guide to help you understand what they mean.

#### Gender Symbols

| Gender | Symbol                                                                                                                                 |
|--------|----------------------------------------------------------------------------------------------------------------------------------------|
| Male   | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/genders/male.png?raw=true" height="50">   |
| Female | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/genders/female.png?raw=true" height="70"> |

#### Horoscope Symbols

| Horoscope   | Symbol                                                                                                                                         | Birthday Range            |
|-------------|------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------|
| Aries       | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/aries.png?raw=true" height="50">       | March 21 - April 19       |
| Taurus      | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/taurus.png?raw=true" height="50">      | April 20 - May 20         |
| Gemini      | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/gemini.png?raw=true" height="50">      | May 21 - June 20          |
| Cancer      | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/cancer.png?raw=true" height="50">      | June 21 - July 22         |
| Leo         | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/leo.png?raw=true" height="50">         | July 23 - August 22       |
| Virgo       | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/virgo.png?raw=true" height="50">       | August 23 - September 22  |
| Libra       | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/libra.png?raw=true" height="50">       | September 23 - October 22 |
| Scorpio     | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/scorpio.png?raw=true" height="50">     | October 23 - November 21  |
| Sagittarius | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/sagittarius.png?raw=true" height="50"> | November 22 - December 21 |
| Capricorn   | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/capricorn.png?raw=true" height="50">   | December 22 - January 19  |
| Aquarius    | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/aquarius.png?raw=true" height="50">    | January 20 - February 18  |
| Pisces      | <img src="https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/resources/images/horoscopes/pisces.png?raw=true" height="50">      | February 19 - March 20    |

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

---

## **Quick Start**

### **Download and Installation**

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `LoveBook.jar` from [here](https://github.com/AY2324S1-CS2103T-F10-2/tp).
3. Double-click the file to start the app. The window that appears will be similar to the one below. <br>
    ![startScreen](images/emptyLoveBook.png) <br>
4. Follow the tutorial below to get started.

### **Navigating the GUI**

If you're new to this program, the initial screen might appear a bit daunting. Don't fret; let's explore the various
elements of the LoveBook interface together.
We suggest enlarging the program window for a clearer perspective, allowing you to view more content.

Here's the detailed breakdown of the LoveBook screen: <br>
![GUI](images/navigation.png)

- **Menu Bar** - This is where you can find the `File` and `Help` options. Click on `File` to access the `Exit` option.
  Click on `Help` to access the `Help` option.
- **Command Box** - This is where you type in your commands. Press `Enter` to execute the command.
- **Presets Bar** - This is where you can find the preset commands. Click on a preset command to populate your command
  box.
- **Result Display** - This is where the results of your commands are displayed.
- **Date List Panel** - This is where the list of dates are displayed. The list is scrollable, and you can scroll up and
  down to view the entire list.

### **LoveBook Tutorial: Getting started**

Now that you are familiar with the layout of LoveBook, we recommend you try the following steps to develop a feel for
the core features of LoveBook.

#### 1. Adding a date

So you've just started dating. How do you begin using this app?

Let's say you went out on a date with John. Throughout your date you find out key things about him, he's 123cm tall,
earns 3k a month, his horoscope is Libra.
Here you can add John to LoveBook using the add command:

In the command box, enter `add name/John Doe age/21 gender/M height/123 income/3000 horoscope/Libra` and press
the `enter` key.
This will add John to LoveBook. <br>

![addCommandExample](images/user-commands/addCommandExample.png) <br>

Let's imagine you've added a couple of dates to the LoveBook already. It should look something like this now: <br>

![addCommandExample2](images/user-commands/addCommandExample2.png) <br>

#### 2. Starring a date

With so many dates you might want to start keeping tabs on dates that are perhaps more exceptional. Fret not, star dates
as you go along
in your journey. Here you can star John for instance, using the star command:
Enter `star 2`.
This will star John. <br>

![starCommandExample](images/user-commands/starCommandExample.png) <br>

#### 3. Filtering dates

With so many dates, you might want to start filtering down to the metrics that matter. Perhaps you seek someone younger,
simply filter for
a specific age. Here you can filter for an age of 21 for instance, using the filter command:
Enter `filter age/21`.
This will filter for John. <br>

![filterCommandExample](images/user-commands/filterCommandExample.png) <br>

#### 4. Set Preferences

Well, maybe you are indecisive as a serial dater, and you want a third party opinion. Fret not, simply begin by keying
in your preferences using the
setPreferences command:
Enter `setP age/22 height/130 income/3200 horoscope/Libra`
This will set your preferences like so: <br>

![setPreferencesCommandExample](images/user-commands/setPreferencesCommandExample.png) <br>

#### 5. Best Match

And here it is, to get the match of your dreams, simply use the bestMatch command:
Enter `bestMatch` <br>

![bestMatchCommandExample](images/user-commands/bestMatchCommandExample.png) <br>

### Congrats! You have completed the tutorial!

The tutorial shows you what a typical workflow might look like on LoveBook. Do continue to peruse the commands section
which contains
other commands that, while not worth covering in a quick start tutorial, are certainly important to using LoveBook as a
whole.
Here's to you embarking on your LoveBook journey!

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

---

## **Features**

<box type="tip">

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `edit INDEX METRIC/NEW_ARG`, `INDEX`, `NEW_ARG` and `METRIC` are parameters which can be used as
  `edit 2 income/3000`.
* Items in square brackets are optional.<br>
  e.g `age/AGE [horoscope/HOROSCOPE]` can be used as `age/22 horoscope/ARIES` or as `age/22`.
* Parameters can be in any order.<br>
  e.g. if the command specifies `name/NAME age/AGE`, `age/AGE name/NAME` is also acceptable.
* For commands that accept a positive integer, the integer cannot be preceded with a leading zero.
  For instance, `star 9` is valid, but `star 09` is invalid
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br> e.g. if the command specifies `help 123`, it will be interpreted as `help`.
* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>

<box type="tip">
    
The following table shows the list of metrics and their respective constraints.

| Metric    | Prefix       | Constraints                                                                             |
|-----------|--------------|-----------------------------------------------------------------------------------------|
| Name      | `name/`      | Name can only contain letters, numbers and spaces. It must not be left blank.           |
| Age       | `age/`       | Age should be a positive integer between 18 and 150 (inclusive).                        |
| Gender    | `gender/`    | Gender should be either M or F. It is case sensitive.                                   |
| Height    | `height/`    | Height should be a positive integer in cm between 100cm and 250cm (inclusive).          |
| Income    | `income/`    | Income (per month) should be a positive integer in SGD less than or equal to a million. |
| Horoscope | `horoscope/` | Horoscope should be a valid zodiac sign. It is case insensitive.                        |

</box>

---

### **Managing Dates**

#### **Adding a new date: `add`**

Adds a date to the LoveBook.

<box type="tip">

All parameters are compulsory.

</box>

<box type="important">

The date added must have a unique name that is not already in the LoveBook.

</box>

Format: `add name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME`

Example: `add name/John Doe age/21 gender/M height/123 income/3000 horoscope/Libra`

![addCommandExample](images/user-commands/addCommandExample.png)

Expected output: `New date added: John Doe; Age: 21; Gender: M; Height: 123; Income: 3000; Horoscope: LIBRA`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

#### **Editing a date: `edit`**

Edits a date in the specified index in the LoveBook.

Format: `edit INDEX [name/NAME] [age/AGE] [gender/GENDER] [height/HEIGHT] [income/INCOME] [horoscope/HOROSCOPE]`

<box type="tip">

* Metric is limited to `gender, age, horoscope, name, height, income` only.
* New value replaces the existing value.
* User can edit up to n number of metrics in one command line, where n refers to the number of metrics available.
* The index must be a positive integer, and be within the range of the recorded dates. For example, if there are 3 Dates in LoveBook, the indices are 1, 2 and 3.

</box>

Examples:

- `edit 1 name/John` (editing 1 metric)
- `edit 1 name/John horoscope/Cancer` (editing 2 metrics)
- `edit 1 horoscope/Cancer name/John` (sequence doesn't matter) 

<br>

![editCommandExample](images/user-commands/editCommandExample.png) <br>

Expected Output: `Edited Date: John; Age: 21; Gender: M; Height: 123; Income: 3000; Horoscope: LIBRA`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

#### **Deleting a date: `delete`**

Deletes the specified date from the LoveBook.

Format: `delete INDEX`

<box type="tip">

The index must be a positive integer, and be within the range of the recorded dates. For example, if there are 3 Dates in LoveBook, the indices are 1, 2 and 3.

</box>

Example: `delete 1` <br> 

![deleteCommandExample](images/user-commands/deleteCommandExample.png) <br>

Expected output: `Deleted Date: Ben Brown; Age: 21; Gender: F; Height: 194; Income: 6000; Horoscope: GEMINI`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

#### **Listing all dates: `list`**

Shows a list of all dates in the LoveBook.

Format: `list` <br>

![listCommandExample](images/user-commands/listCommandExample.png) <br>

Expected output: `Listed all Dates`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

#### **Clearing all dates: `clear`**

Clears all dates in the LoveBook.

<box type="important">

* This command is irreversible.
* All data will be lost. Please use this command with caution.

</box>

Format: `clear`

![clearCommandExample-1](images/user-commands/clearCommandExample1.png)

![clearCommandExample-2](images/user-commands/clearCommandExample2.png)

Expected output: `LoveBook has been cleared!`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

#### **Starring a date: `star`**

Stars a date in the LoveBook.

Format: `star INDEX`

<box type="tip">

* The index must be a positive integer, and be within the range of the recorded dates.
* For example, if there are 3 Dates in LoveBook, the indices are 1, 2 and 3.

</box>

Example: `star 1`

![starCommandExample-1](images/user-commands/starCommandExample1.png)

Expected output: `Starred Date: John Doe; Age: 21; Gender: F; Height: 245; Income: 3000; Horoscope: LIBRA`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

#### **Unstarring a date: `unstar`**

Unstars a date in the LoveBook.

Format: `unstar INDEX`

<box type="tip">

The index must be a positive integer, and be within the range of the recorded dates. For example, if there are 3 Dates in LoveBook, the indices are 1, 2 and 3.

</box>

Example: `unstar 1` <br>

![unstarCommandExample](images/user-commands/unstarCommandExample.png) <br>

Expected output: `Unstarred Date: John Doe; Age: 21; Gender: M; Height: 123; Income: 3000; Horoscope: LIBRA`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

### **Sorting, Finding dates**

#### **Finding a date: `find`**

Finds a date in the LoveBook by a specific name(s)

Format: `find NAME [MORE_NAMES]`

<box type="tip">

More names can be added to the command line, and the search will be conducted for all names provided.

</box>

Example:

- `find Cleon`
- `find Cleon John`

Expected Output: `Lists the dates with the name(s) specified`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

#### **Filtering dates by a certain metric: `filter`**

Filters the dates in the LoveBook by a specific metric.

Format: `filter METRIC/ARG`

<box type="tip">

* Metric is limited to `gender, age, name, height` only
* Arguments should be non-empty and adhere to the constraints of the metric
* Multiple valid METRIC/ARG pairs can be added to the command line, and the search will be conducted for all pairs
  provided. e.g. `filter gender/M age/21`
* Filter format is valid as long as there is one valid METRIC/ARG pair. e.g. `filter xx gender/M` works as well, where xx is not a metric

</box>

<box type="important">

When filtering dates by a valid metric and an invalid metric, the valid METRIC/ARG pair will be used to filter the dates. 
Note that the invalid metric will be ignored and there will be no checking of the validity of the invalid metric value.

</box>

Example:

- `filter name/Cleon`
- `filter gender/M`
- `filter gender/M name/Cleon`
- `filter hi gender/M` (provides flexibility to the user in the case they mistype a metric)
- `filter income/20 name/Cleon` (this only returns dates with name Cleon since income is an invalid metric)

Expected Output: `Lists the dates with the valid metric values specified`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

#### **Sorting dates by a certain metric: `sort`**

Sorts the dates in the LoveBook by a specific metric.

Format: `sort METRIC/ORDER`

<box type="tip">

* Metrics are limited to `name/`, `age/`, `height/`, `income/` and `horoscope/` only.
* Order is limited to `increasing` or `decreasing` only and is case-sensitive.
* Sort command is valid as long as there is only one valid METRIC/ORDER pair and no other metrics are provided.

</box>

<box type="important">

* When sorting dates by a field where both have an equal value, say Kevin and Wayne are sorted by income and both have
an income of 2000, applying commands such as edit or star, can affect the stability of the sort. However, the overall
sorting order by value is preserved.
             
</box>

Example:

- `sort name/increasing`
- `sort horoscope/decreasing`
- `sort xx income/decreasing`, where xx is not a metric (provides flexibility to the user in the case they mistype a metric)

Expected Output: `Lists the dates in the order specified`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Managing Preferences and Getting Matches**

<box type="tip">

* By default, Date Preference is set to:
    * AGE: 21
    * HEIGHT: 170
    * INCOME: 10000
    * HOROSCOPE: ARIES
* If you wish to change your Date Preference, please use the [`setP`](#setting-your-date-preferences-setp) command.
* The [`bestMatch`](#getting-a-recommended-date-bestmatch) command will use the Date Preference set by the user.

</box>

#### **Showing your date preferences: `showP`**

Shows the User's Date Preference. This can be edited using the [`setP`](#setting-your-date-preferences-setp) command.

Format: `showP`

Expected output: `Here are your preferences: Age: 22; Height: 180; Income: 2000; Horoscope: TAURUS`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

#### **Setting your date preferences: `setP`**

Sets the user's preferences for the matching algorithm.

<box type="tip">

* At least one field to edit must be provided.
* The user's preferences will be used in the [`bestMatch`](#getting-a-recommended-date-bestmatch) command.
* Gender is not taken into account here
* The relevant fields are
    * `age/AGE`
    * `height/HEIGHT`
    * `income/INCOME`
    * `horoscope/HOROSCOPE`

</box>

Format: `setP [age/AGE] [height/HEIGHT] [income/INCOME] [horoscope/HOROSCOPE]`

Example: `setP age/22 height/180 income/2000 horoscope/TAURUS` <br>

![setPCommandExample](images/user-commands/setPCommandExample.png) <br>

Expected output: `Updated Preferences: Age: 22; Height: 180; Income: 2000; Horoscope: TAURUS`


[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

#### **Getting a recommended date: `bestMatch`**

Filters out the most compatible date based on the set preferences.

Format: `bestMatch` <br>

![bestMatchCommandExample-1](images/user-commands/bestMatchCommandExample1.png) <br>

![bestMatchCommandExample-2](images/user-commands/bestMatchCommandExample2.png) <br>

Expected Output: `Here's your best match!`

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

#### **Getting a blind date: `blindDate`**

Outputs a random date from the LoveBook.

Format: `blindDate` <br>

![blindDateCommandExample-1](images/user-commands/blindDateCommandExample1.png) <br>

![blindDateCommandExample-2](images/user-commands/blindDateCommandExample2.png) <br>

Expected Output: `Here's a blind date for you!`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

### **Other Commands**

#### **Exiting the application: `exit`**

Exits the program.

Format: `exit`

Expected output: `Exiting LoveBook...`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

#### **Getting help: `help`**

Shows a window to bring user to the LoveBook User Guide.

Format: `help` <br>

![helpCommandExample](images/user-commands/helpCommandExample.png) <br>

Expected output: `Opened help window.`

[Scroll back to *Table of Contents*](#table-of-contents)

<div style="page-break-after: always;"></div>

---

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file with the file that
contains the data of your previous LoveBook home folder.

**Q**: Is my data stored in the cloud? Will I be open to data breaches?<br>
**A**: No, all your data is stored locally, no need to fear a potential data hack.

**Q**: Do I have to save before exiting the application for my data to safely backed up on my computer? <br>
**A**: All your data is saved on your computer as soon as you enter the command in. There is no need to manually save
your data. In the event of a power outage, all your data will be safe.

**Q**: Where do I go if I have issues with LoveBook? <br>
**A**: You may leave your issues [here](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues).

**Q**: What happens if I enter invalid data into the stored data file? <br>
**A**: LoveBook will clear all the existing data and reset to as it was when you installed it. It is highly discouraged
to manually edit the data file.

**Q**: How do I change my date's avatar? <br>
**A**: Currently, the avatars are randomly selected from a pool of 9 avatars. We are working on a feature to allow users
to choose their own avatars.

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

---

## **Command Summary**

<box type="tip">

Commands are sorted by alphabetical order.

</box>

| Action                                                                             | Format                                                                                                   | Examples                                                               |
|------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| [Adding a new date](#adding-a-new-date-add)                                        | `add name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME`                    | `add name/John age/25 gender/M height/175 horoscope/Aries income/5000` |
| [Getting a recommended date](#getting-a-recommended-date-bestmatch)                | `bestMatch`                                                                                              | `bestMatch`                                                            |
| [Getting a blind date](#getting-a-blind-date-blinddate)                            | `blindDate`                                                                                              | `blindDate`                                                            |
| [Clearing all dates](#clearing-all-dates-clear)                                    | `clear`                                                                                                  | `clear`                                                                |
| [Deleting a date](#deleting-a-date-delete)                                         | `delete INDEX`                                                                                           | `delete 2`                                                             |
| [Editing a date](#editing-a-date-edit)                                             | `edit INDEX [name/NAME] [age/AGE] [gender/GENDER] [height/HEIGHT] [income/INCOME] [horoscope/HOROSCOPE]` | `edit 3 name/Cleon`                                                    |
| [Exiting the application](#exiting-the-application-exit)                           | `exit`                                                                                                   | `exit`                                                                 |
| [Filtering dates by a certain metric](#filtering-dates-by-a-certain-metric-filter) | `filter METRIC/ARG`                                                                                      | `filter name/Cleon`                                                    |
| [Finding a date](#finding-a-date-find)                                             | `find NAME [MORE_NAMES]`                                                                                 | `find Cleon`                                                           |
| [Getting help](#getting-help-help)                                                 | `help`                                                                                                   | `help`                                                                 |
| [Listing all dates](#listing-all-dates-list)                                       | `list`                                                                                                   | `list`                                                                 |
| [Setting your date preferences](#setting-your-date-preferences-setp)               | `setP [age/AGE] [height/HEIGHT] [income/INCOME] [horoscope/HOROSCOPE]`                                   | `setP age/22 height/180 income/2000 horoscope/TAURUS`                  |
| [Showing your date preferences](#showing-your-date-preferences-showp)              | `showP`                                                                                                  | `showP`                                                                |
| [Sorting dates by a certain metric](#sorting-dates-by-a-certain-metric-sort)       | `sort METRIC/ORDER`                                                                                      | `sort name/increasing`                                                 |
| [Starring a date](#starring-a-date-star)                                           | `star INDEX`                                                                                             | `star 1`                                                               |
| [Unstarring a date](#unstarring-a-date-unstar)                                     | `unstar INDEX`                                                                                           | `unstar 1`                                                             |

[Scroll back to *Table of Contents*](#table-of-contents)
<div style="page-break-after: always;"></div>

---
