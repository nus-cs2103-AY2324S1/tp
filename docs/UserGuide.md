# LoveBook User Guide Draft

LoveBook, is a dating-focused height book application, revolving around providing users with a convenient
and enjoyable tool to enhance their dating experiences. Featuring user profile management, date organization,
compatibility ranking, and customizable filtering options, LoveBook enhances the efficiency and effectiveness of your
online dating journey.

--------------------------------------------------------------------------------------------------------------------

# Wireframes

![Figma](/docs/images/user-guide/Figma.png)

# 1 Quick start (Ryann)

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `LoveBook.jar` from [here](https://github.com/AY2324S1-CS2103T-F10-2/tp).

3. Double-click the file to start the app. The window that appears will be similar to the below should appear in a few seconds. Note how
   the app contains some sample information.<br>
   ![Ui](images/Ui.png)
   *Figure 1: A view of LoveBook at startup*

4. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing `Enter` to
   open the help window.<br>
   Some commands you can try:

    * `list 1` : Lists all dtes and their associated details

    * `exit` : Exits the app

5. Refer to the features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

# 2 Features

### Listing current dates : `list`

Format: `list`

Expected output: `Lists all dates and their associated details`

### Deletion of dates: `delete`

Format: `delete INDEX`

Parameter constraints:
- The index must be a positive integer, and be within the range of the recorded dates

Example: `delete 2`

Expected output: `Deletes the task at the specified INDEX.`

Output if error : `The index you have provided is out of bounds of your current list of dates`

### Editing your profile `editP`

Format: `setP name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME`

Parameter constraints:
- Name should be non-empty string.
- Age should be positive integer.
- Gender should be a character (M/F).
- Horoscope should be a non-empty string.

Example: `setP name/Ryann age/22 gender/F height/1.76 horoscope/Taurus income/2000`

Expected output: `User profile has been successfully updated!`

Output if error: ```Please follow the required format (editP name/Ryann age/22 gender/F height/1.76 horoscope/Taurus
   income/2000)```

### Creation of new date
Format: `newD name/NAME age/AGE gender/GENDER height/HEIGHT horoscope/HOROSCOPE income/INCOME`
Parameter constraints:
- Name should be non non-empty string.
- Age should be a positive integer.
- Gender should be a character (M/F).
- Horoscope should be a non-empty string.

Example:
`newD name/Cleon age/22 gender/F height/1.76 horoscope/Taurus income/3000`

Expected output: `New date has been successfully added!`

Output if error:
Please follow the required format to add a new date (New date /name Adam /age 123 /gender F /horoscope Cancer)

### Edit existing dates
Format: `editD INDEX METRIC/NEW ARG`

Parameter constraints:
- The index must be a positive integer, and be within the range of the recorded dates
- Metric is limited to `gender, age, horoscope, name` only
- New arg replaces the existing argument for that metric
- User can edit up to n number of metrics in one command line, where n refers to the number of metrics available

Example:
- `editD 3 name/Cleon` (editing 1 metric)
- `editD 3 name/Cleon horoscope/Cancer` (editing 2 metrics)
- `editD 3 horoscope/Cancer name/Cleon` (sequence doesn't matter)

Expected Output: `The date information has been successfully updated!`

### Random Date Generator
Format: `random`

Expected Output: `A random date entry is printed`

Output if error: ```No dates in list!```

### Filter by Metric
Format: `filter METRIC/ARG`

Parameter constraints:
- Filter must be from list of metrics
- Metric is limited to `gender, age, horoscope, name` only

Example:
- `filter name/Cleon`
- `filter gender/M`

Expected Output: `Lists the dates with the metric specified`

Output if error: ```No dates found!```

### Getting a recommended date: `match`
Format: `match`

Expected Output: `List the most compatible date`

Output if error: `No dates found!`

### Setting the matching algorithm: `setPreference`
Format: `setPreference gender/M age/-3 height/-20 income/2000`

Expected output: `Preferences have been updated!`

Output if error:
`Please follow the required format to add a new date (setPreference /gender M /age -3 height/ -20 income/ 2000)`

--------------------------------------------------------------------------------------------------------------------

## 3 Use Cases

BOB


--------------------------------------------------------------------------------------------------------------------

# 3 FAQ (Ryann)

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

# 4 Summary

Action | Format, Examples | Scope: | `PROJECT_LIST` | `PERSON_LIST` | `PROJECT` | `PERSON` | `TASK` | `TEAMMATE`
--------|------------------|-------|---------------|---------------|-------------|--------|--------|------------

[//]: # (**Gets Help** | `help` |                                                                                                                                                                                                                                                          | √ | √ | √ | √ | √ | √)

[//]: # (**Exits application** | `exit` |                                                                                                                                                                                                                                                  | √ | √ | √ | √ | √ | √)

[//]: # (**Leaves a view** | `leave` |                                                                                                                                                                                                                                                     | √ | √ | √ | √ | √ | √)

[//]: # (**Shows all projects** | `listprojects` |                                                                                                                                                                                                                                         | √ | √ | √ |   | √ | √)

[//]: # (**Shows all dates** | `listdates` |                                                                                                                                                                                                                                           | √ | √ |   | √ |   |)

[//]: # (**Starts a project** | `startproject INDEX`<br> e.g., `startproject 3` |                                                                                                                                                                                                          | √ |   | √ |   |   |)

[//]: # (**Starts a date** | `startperson INDEX`<br> e.g., `startperson 3` |                                                                                                                                                                                                             |   | √ |   | √ |   |)

[//]: # (**Adds project** | `add n/PROJECT_NAME dl/DEADLINE ru/REPO_URL d/PROJECT_DESCRIPTION [tg/TAGS]... `   eg, `add n/Blair project dl/29-02-2020 00:00:00 ru/http://github.com/a/b.git d/Coding in Greenwich tg/challenging` |                                                        | √ |   | √ |   | √ | √)

[//]: # (**Deletes project** | `delete INDEX` <br> e.g. `delete 2` |                                                                                                                                                                                                                       | √ |   | √ |   | √ | √)

[//]: # (**Finds KEYWORD** | `find KEYWORD` <br> e.g. `find read` |                                                                                                                                                                                                                        | √ |   | √ |   | √ | √)

[//]: # (**Edits Project** | `edit [n/PROJECT NAME] [dl/DEADLINE] [ru/REPO URL] [d/PROJECT DESCRIPTION] [tg/TAGS...] ` eg, `edit n/Resident Evil project d/ new horror`|                                                                                                                   | √ |   | √ |   | √ | √)

[//]: # (**Adds Task** | `addtask tn/TASK_NAME td/TASK_DEADLINE [tp/TASK_PROGRESS] [d/TASK DESCRIPTION]` eg, `addtask tn/Do User Guide tp/30 td/29-02-2020 00:00:00` |                                                                                                                                             |   |   | √ |   | √ | √)

[//]: # (**Assigns A Task To A Teammate** | `assign INDEX NAME` <br> e.g. `assign 1 Niaaz` |                                                                                                                                                                                               |   |   | √ |   | √ | √)

[//]: # (**Edits task details** | `edittask INDEX [tn/TASK_NAME] [tp/TASK_PROGRESS] [td/TASK_DEADLINE] [d/TASK DESCRIPTION]` eg, `edittask 3 tn/Finish project` |                                                                                                                                   |   |   | √ |   | √ | √)

[//]: # (**Deletes a task** | `deletetask INDEX` <br>e.g. `deletetask 1` |                                                                                                                                                                                                                 |   |   | √ |   | √ | √)

[//]: # (**Filters tasks** | <code>filter &#40;ta/ASSIGNEE_GITHUB_USERNAME&#41;&#124;&#124;&#40;tn/KEYWORD [MORE_KEYWORDS]...&#41;&#124;&#124;&#40;td/DEADLINE&#41;&#124;&#124;&#40;start/START_DATE end/END_DATE&#41;&#124;&#124;&#40;tp/TASK_PROGRESS&#41;&#124;&#124;&#40;done/DONE_STATUS&#41;</code> <br>e.g. `filter tn/CS2103T` |   |   |   | √ |   | √ | √)

[//]: # (**Shows all the tasks** | `alltasks` |                                                                                                                                                                                                                                            |   |   | √ |   | √ | √)

[//]: # (**Sorts tasks** | <code>sort &#40;sa/&#41;&#124;&#124;&#40;sd/&#41; &#40;td/&#41;&#124;&#124;&#40;tp/&#41;&#124;&#124;&#40;tn/&#41;&#124;&#124;&#40;done/&#41;</code> <br>e.g. `sort sa/ td/` |                                                                                                                                   |   |   | √ |   | √ | √)

[//]: # (**Views Details of A Task** | `viewtask INDEX` <br> e.g. `viewtask 1` |                                                                                                                                                                                                           |   |   | √ |   | √ | √)

[//]: # (**Creates a new date** | `addperson mn/TEAMMATE_NAME mg/GITHUB_USERNAME mp/PHONE_NUMBER me/GENDER ma/HEIGHT` e.g. `addperson mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road` |                                                                         | √ | √ | √ | √ | √ | √)

[//]: # (**Adds a teammate to a project** | `addtoproject GITHUB_USERNAME` e.g. `addtoproject LucasTai98` |                                                                                                                                                                                |   |   | √ |   | √ | √)

[//]: # (**Removes a teammate from a project** | `deletefromproject GITHUB_USERNAME` e.g. `deletefromproject LucasTai98` |                                                                                                                                                                 |   |   | √ |   | √ | √)

[//]: # (**Edits teammate details** | `editteammate GITHUB_USERNAME [mn/TEAMMATE_NAME] [mp/PHONE_NUMBER] [me/] [ma/HEIGHT]` e.g. `editteammate Lucas98 tn/GeNiaaz ta/5 Hacker Way`|                                                                                                  |   |   | √ |   | √ | √)

[//]: # (**Views a teammate’s details** | `viewteammate GITHUB_USERNAME` e.g. `viewteammate Lucas98`|                                                                                                                                                                                      |   |   | √ |   | √ | √)

[//]: # (**Deletes a teammate** | `deleteperson GITHUB_USERNAME` e.g. `deleteperson Lucas98` |)

--------------------------------------------------------------------------------------------------------------------

# 5 Glossary

### Mainstream OS: Windows, Linux, Unix, OS-X.
