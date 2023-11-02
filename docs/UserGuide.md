# LoveBook User Guide

LoveBook, is a dating-focused application, revolving around providing users with a convenient
and enjoyable tool to enhance their dating experiences. Featuring user profile management, date organization,
compatibility ranking, and customizable filtering options, LoveBook enhances the efficiency and effectiveness of your
online dating journey.

<!-- * Table of Contents -->
<page-nav-print />

- [Quick Start](#1-quick-start)
- [Features](#2-features)
   - [Listing current dates : `list`](#list-all-dates-list)
   - [Deletion of dates: `delete`](#deletion-of-dates-delete)
   - [Creation of new date: `add`](#creation-of-new-date-add)
   - [Edit existing dates: `edit`](#edit-existing-dates-edit)
   - [Random Date Generator: `random`](#random-date-generator-random)
   - [Filter by Metric: `filter`](#filter-by-metric-filter)
   - [Sorting list of dates by Metric: `sort`](#sorting-list-of-dates-by-metric-sort)
   - [Getting a recommended date: `match`](#getting-a-recommended-date-match)
   - [Setting the matching algorithm: `setPreference`](#setting-the-matching-algorithm-setPreference)
   - [Star a date: `star`](#star-a-date-star)
   - [Unstar a date: `unstar`](#unstar-a-date-unstar)
- [FAQ](#3-faq)
- [Summary](#4-summary)
- [Glossary](#5-glossary)

--------------------------------------------------------------------------------------------------------------------

# 1 [Quick start](#1-quick-start)

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `LoveBook.jar` from [here](https://github.com/AY2324S1-CS2103T-F10-2/tp).

3. Double-click the file to start the app. The window that appears will be similar to the below should appear in a few seconds. Note how
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

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.



### [List all dates: `list`](#list-all-dates-list)

Shows a list of all dates in the lovebook.

Format: `list`

Expected output: `Lists all dates and their associated details`

### [Deletion of dates: `delete`](#deletion-of-dates-delete)

Deletes the specified date from the lovebook.

Format: `delete INDEX`

Parameter constraints:
- The index must be a positive integer, and be within the range of the recorded dates.

Example: `delete 2`

Expected output: `Deletes the date at the specified INDEX.`

Output if error : `The index you have provided is out of bounds of your current list of dates`

### [Creation of new date: `add`](#creation-of-new-date-add)

Adds a date to the lovebook.

Format: `add name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME`

Parameter constraints:
- Name should be a non non-empty string.
- Age should be a positive integer.
- Gender should be either M or F.
- Horoscope should be a valid zodiac sign.
- Income (per month) should be a positive integer in SGD.
- Height should be a positive integer in cm.

Example: `add name/Cleon age/22 gender/F height/176 horoscope/Taurus income/3000`

Expected output: `New date added: Cleon; Age: 22; Gender: F; Height: 176; Income: 3000; Horoscope: TAURUS`

Output if error: `Invalid command format! add: Adds a date to the LoveBook. Parameters: name/NAME age/AGE gender/GENDER
height/HEIGHT income/INCOME horoscope/HOROSCOPE Example: add name/John Doe age/21 gender/M height/23124 income/3000
horoscope/Libra`

### [Edit existing dates: `edit`](#edit-existing-dates-edit)

Edits a date in the specified index in the lovebook.

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

### [Random Date Generator: `random`](#random-date-generator-random)

Filters out a random date.

Format: `random`

Expected Output: `A random date entry is printed`

Output if error: ```No dates in list!```

### [Filter by Metric: `filter`](#filter-by-metric-filter)

Filters the dates in the lovebook by a specific metric.

Format: `filter METRIC/ARG`

Parameter constraints:
- Filter must be from list of metrics
- Metric is limited to `gender, age, horoscope, name, income, height` only

Example:
- `filter name/Cleon`
- `filter gender/M`
- `filter gender/M name/Cleon`

Expected Output: `Lists the dates with the metric specified`

Output if error: ```No dates found!```

### [Sorting list of dates by Metric: `sort`](#sorting-list-of-dates-by-metric-sort)

Sorts the dates in the lovebook by a specific metric.

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

### [Getting a recommended date: `match`](#getting-a-recommended-date-match)

Filters out the most compatible date based on the set preferences.

Format: `match`

Expected Output: `List the most compatible date`

Output if error: `No dates found!`

### [Setting the matching algorithm: `setPreference`](#setting-the-matching-algorithm-setPreference)

Sets the user's preferences for the matching algorithm. 

Format: `setPreference gender/GENDER age/AGE height/HEIGHT income/INCOME`

Example: `setPreference gender/M age/22 height/180 income/2000`

Expected output: `Preferences have been updated!`

Output if error:
`Please follow the required format to add a new date (setPreference gender/M age/22 height/180 income/2000)`

### [Star a date: `star`](#star-a-date-star)
Format: `star INDEX`

Expected output: `NAME has been starred!`

Output if error:
`The index you have provided is out of bounds of your current list of dates`

### [Unstar a date: `unstar`](#unstar-a-date-unstar)
Format: `unstar INDEX`

Expected output: `NAME has been unstarred!`

Output if error:
`The index you have provided is out of bounds of your current list of dates`
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

| Action                 | Format                                                                               | Examples                                                            |
|------------------------|--------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| Listing current dates  | `list`                                                                               | `list`                                                              |
| Deletion of dates      | `delete INDEX`                                                                       | `delete 2`                                                          |
| Creation of new date   | `add name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME` | `add name/John age/25 gender/M height/175 horoscope/Aries income/5000` |
| Edit existing dates    | `edit INDEX METRIC/NEW ARG`                                                          | `edit 3 name/Cleon`                                                 |
| Random Date Generator  | `random`                                                                             | `random`                                                            |
| Filter by Metric       | `filter METRIC/ARG`                                                                  | `filter name/Cleon`                                                 |
| Sorting list of dates  | `sort METRIC/ORDER`                                                                  | `sort name/increasing`                                              |
| Getting a recommended date | `match`                                                                              | `match`                                                             |
| Setting the matching algorithm | `setPreference gender/GENDER age/AGE height/HEIGHT income/INCOME`                                | `setPreference gender/M age/22 height/180 income/2000`                 |
 | Star a date            | `star INDEX`                                                                         | `star 1`                                                            |
 | Unstar a date          | `unstar INDEX`                                                                       | `unstar 1`                                                          |
--------------------------------------------------------------------------------------------------------------------

# 5 [Glossary](#5-glossary)

### Mainstream OS: Windows, Linux, Unix, OS-X.
