# LoveBook User Guide

LoveBook, is a dating-focused application, revolving around providing users with a convenient
and enjoyable tool to enhance their dating experiences. Featuring user profile management, date organization,
compatibility ranking, and customizable filtering options, LoveBook enhances the efficiency and effectiveness of your
online dating journey.

<!-- * Table of Contents -->
<page-nav-print />

- [Quick Start](#1-quick-start)
- [Features](#2-features)
    - [Creation of new date: `add`](#creation-of-new-date)
    - [Getting a recommended date: `bestMatch`](#getting-a-best-match)
    - [Blind Date Generator: `blindDate`](#blind-date-generator)
    - [Clear all dates: `clear`](#clear-all-dates)
    - [Deletion of dates: `delete`](#deletion-of-dates-delete)
    - [Edit existing dates: `edit`](#edit-existing-date)
    - [Exit the program: `exit`](#exit-the-program)
    - [Filter by Metric: `filter`](#filter-by-metric-filter)
    - [Find a date: `find`](#find-a-date)
    - [Help: `help`](#help)
    - [Listing current dates : `list`](#list-all-dates)
    - [Setting the matching algorithm: `setP`](#setting-the-date-pref)
    - [Show Date Preferences: `showP`](#show-date-preferences)
    - [Sorting list of dates by Metric: `sort`](#sorting-date-list)
    - [Star a date: `star`](#star-a-date)
    - [Unstar a date: `unstar`](#unstar-a-date)
- [FAQ](#3-faq)
- [Summary](#4-summary)
- [Glossary](#5-glossary)

--------------------------------------------------------------------------------------------------------------------

# 1 [Quick start](#1-quick-start)

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `LoveBook.jar` from [here](https://github.com/AY2324S1-CS2103T-F10-2/tp).

3. Double-click the file to start the app. The window that appears will be similar to the below should appear in a few
   seconds. Note how
   the app contains some sample information.<br>
   ![Ui](images/Ui.png)
   *Figure 1: A view of LoveBook at startup*

4. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing `Enter` to
   open the help window.<br>
   Some commands you can try:

    * `list` : Lists all dates and their associated details

    * `exit` : Exits the app

5. Refer to the features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# 2 [Features](#2-features)

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `edit INDEX METRIC/NEW_ARG`, `INDEX`, `NEW_ARG` and `METRIC` are parameters which can be used as
  `edit 2 income/3000`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
  will be ignored.<br> e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.

<box type="info">
    * `name` should be a non-empty string.
    * `age` should be a positive integer between 18 and 150 (inclusive).
    * `gender` should be either M or F.
    * `horoscope` should be a valid zodiac sign.
    * `income` (per month) should be a positive integer in SGD.
    * `height` should be a positive integer in cm between 100cm and 250cm (inclusive).
</box>

### [Creation of new date: `add`](#creation-of-new-date)

Adds a date to the LoveBook.

Format: `add name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME`

<box type="tip" seamless>
    Parameters in the add command can be in any order.
</box>

Example: `add name/Cleon age/22 gender/F height/176 horoscope/Taurus income/3000`

Expected output: `New date added: Cleon; Age: 22; Gender: F; Height: 176; Income: 3000; Horoscope: TAURUS`

Output if error: `Invalid command format! add: Adds a date to the LoveBook. Parameters: name/NAME age/AGE gender/GENDER
height/HEIGHT income/INCOME horoscope/HOROSCOPE Example: add name/John Doe age/21 gender/M height/23124 income/3000
horoscope/Libra`

### [Getting a recommended Date: `bestMatch`](#getting-a-best-match)

Filters out the most compatible date based on the set preferences.

Format: `bestMatch`

Expected Output: `List the most compatible date`

Output if error: `No dates found!`

### [Blind Date Generator: `blindDate`](#blind-date-generator)

Outputs a blind date from the LoveBook.

Format: `blindDate`

Expected Output: `A random Date entry is printed`

Output if error: `No dates in list!`

### [Clear all dates: `clear`](#clear-all-dates)

Clears all dates in the LoveBook.

Format: `clear`

Expected output: `LoveBook has been cleared!`

### [List all dates: `list`](#list-all-dates)

Shows a list of all dates in the LoveBook.

Format: `list`

Expected output: `Listed all Dates`

### [Deletion of dates: `delete`](#deletion-of-dates-delete)

Deletes the specified date from the LoveBook.

Format: `delete INDEX`

Parameter constraints:

- The index must be a positive integer, and be within the range of the recorded dates.

Example: `delete 1`

Expected output: `Deleted Date: John Doe; Age: 21; Gender: M; Height: 123; Income: 3000; Horoscope: LIBRA`

Output if error : `The date index provided is invalid`

### [Edit existing dates: `edit`](#edit-existing-date)

Edits a date in the specified index in the LoveBook.

Format: `edit INDEX METRIC/NEW ARG`

Parameter constraints:

- The index must be a positive integer, and be within the range of the recorded dates
- Metric is limited to `gender, age, horoscope, name, height, income` only
- New argument replaces the existing argument for that metric
- User can edit up to n number of metrics in one command line, where n refers to the number of metrics available

Example:

- `edit 3 name/Cleon` (editing 1 metric)
- `edit 3 name/Cleon horoscope/Cancer` (editing 2 metrics)
- `edit 3 horoscope/Cancer name/Cleon` (sequence doesn't matter)

Expected Output: `Edited Date: Cleon Tan; Age: 12; Gender: M; Height: 123; Income: 3000; Horoscope: CANCER`

### [Exit the program: `exit`](#exit-the-program)

Exits the program.

Format: `exit`

Expected output: `Exiting LoveBook...`

### [Filter by Metric: `filter`](#filter-by-metric-filter)

Filters the dates in the LoveBook by a specific metric.

Format: `filter METRIC/ARG`

* Parameter constraints:
* Filter must be from list of metrics
* Metric is limited to `gender, age, horoscope, name, income, height` only

Example:

- `filter name/Cleon`
- `filter gender/M`
- `filter gender/M name/Cleon`

Expected Output: `Lists the dates with the metric specified`

Output if error: `No dates found!`

### [Find a date: `find`](#find-a-date)

Finds a date in the LoveBook by a specific name(s)

Format: `find NAME [MORE_NAMES]`

Parameter constraints:

- Name should be a non non-empty string.
- More names can be added to the command line, and the search will be conducted for all names provided

Example:

- `find Cleon`
- `find Cleon John`

Expected Output: `Lists the dates with the name(s) specified`

Output if error: `0 dates listed!`

### [Help: `help`](#help)

Shows a modal to bring user to the LoveBook User Guide.

Format: `help`

Expected output: `Opened help window.`

### [Setting the matching algorithm: `setP`](#setting-the-date-pref)

Sets the user's preferences for the matching algorithm.

Format: `setP gender/GENDER age/AGE height/HEIGHT income/INCOME`

Example: `setP gender/M age/22 height/180 income/2000`

Expected output: `Updated Preferences: Age: 22; Gender: M; Height: 180; Income: 2000; Horoscope: TAURUS`

Output if error:
`At least one field to edit must be provided. Please try the following command format: setP: Sets Date Preference. Parameters: age/AGE gender/GENDER height/HEIGHT income/INCOME horoscope/HOROSCOPE Example: setP age/21 gender/M height/23124 income/3000 horoscope/Scorpio`

### [Show Date Preferences: `showP`](#show-date-preferences)

Shows the User's Date Preferences.

Format: `showP`

Example: `showP`

Expected output: `Here are your preferences: Age: 22; Gender: M; Height: 180; Income: 2000; Horoscope: TAURUS`

### [Sorting list of dates by Metric: `sort`](#sorting-date-list)

Sorts the dates in the LoveBook by a specific metric.

Format: `sort METRIC/ORDER`

Parameter constraints:

- Sort must be from list of metrics
- Metric is limited to `age, horoscope, name, income, height` only
- ORDER must take on either the value of 'increasing', or 'decreasing'

Example:

- `sort name/increasing`
- `sort horoscope/decreasing`
- `filter income/ height/increasing`

Expected Output: `Lists the dates in the order specified`

Output if error: ```No dates found!```

### [Star a date: `star`](#star-a-date)

Stars a date in the LoveBook.

Format: `star INDEX`

Example: `star 1`

Expected output: `Starred Date: John Doe; Age: 21; Gender: F; Height: 245; Income: 3000; Horoscope: LIBRA`

Output if error:
`The date index provided is invalid`

<box type="info">
* If Date is already starred, it will display the output `Date has already been starred`
</box>

### [Unstar a date: `unstar`](#unstar-a-date)

Unstars a date in the LoveBook.

Format: `unstar INDEX`

Example: `unstar 1`

Expected output: `Unstarred Date: John Doe; Age: 21; Gender: M; Height: 123; Income: 3000; Horoscope: LIBRA`

Output if error:
`The date index provided is invalid`

<box type="info">
    If Date is already unstarred, it will display the output `Date has already been unstarred`
</box>

--------------------------------------------------------------------------------------------------------------------

# 3 [FAQ](#3-faq)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file with the file that
contains the data of your previous LoveBook home folder.

**Q**: Is my data stored in the cloud? Will I be open to data breaches?<br>
**A**: No, all your data is stored locally, no need to fear a potential data hack.

**Q**: Do I have to save before exiting the application for my data to safely backed up on my computer? <br>
**A**: All your data is saved on your computer as soon as you enter in the command. There is no need to manually save
your data. In the event of a power outage, all your data will be safe.

**Q**: Where do I go if I have issues with LoveBook? <br>
**A**: You may leave your issues [here](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues).

--------------------------------------------------------------------------------------------------------------------

# 4 [Summary](#4-summary)

| Action                         | Format                                                                                | Examples                                                               |
|--------------------------------|---------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| Listing current dates          | `list`                                                                                | `list`                                                                 |
| Deletion of dates              | `delete INDEX`                                                                        | `delete 2`                                                             |
| Creation of new date           | `add name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME` | `add name/John age/25 gender/M height/175 horoscope/Aries income/5000` |
| Edit existing dates            | `edit INDEX METRIC/NEW ARG`                                                           | `edit 3 name/Cleon`                                                    |
| Blind Date Generator           | `blindDate`                                                                           | `random`                                                               |
| Filter by Metric               | `filter METRIC/ARG`                                                                   | `filter name/Cleon`                                                    |
| Sorting list of dates          | `sort METRIC/ORDER`                                                                   | `sort name/increasing`                                                 |
| Getting a recommended date     | `bestMatch`                                                                           | `match`                                                                |
| Setting the matching algorithm | `setP gender/GENDER age/AGE height/HEIGHT income/INCOME`                              | `setP gender/M age/22 height/180 income/2000`                          |
| Star a date                    | `star INDEX`                                                                          | `star 1`                                                               |
| Unstar a date                  | `unstar INDEX`                                                                        | `unstar 1`                                                             |

--------------------------------------------------------------------------------------------------------------------

# 5 [Glossary](#5-glossary)

### Mainstream OS: Windows, Linux, Unix, OS-X.
