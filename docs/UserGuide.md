---
layout: page
title: User Guide
---

## **Introduction - What is TimetaBRO?**

Welcome to TimetaBRO, your ultimate companion for managing your university life, social interactions, and schedules. It is dedicated to NUS students who have many things on their plate and have trouble arranging meetings with friends and teammates.

In the past, NUS students had to tediously save their friend’s timetables, and compare them to their own. Most of the time, students do not even have the time to compare timetables and have to go through the hassle of coordination through messaging apps.

However, with TimetaBRO, you can now save your friends’ timetables, and ask it when your friends are free! Make scheduling meetups and meetings slick and easy, and while you’re busy scheduling the best dates, you can also save important details about your friends! No more forgetting birthdays or favourite foods, be the best friend you can be!

## **About the user guide**
This comprehensive user guide will walk you through all the exciting features TimetaBRO has to offer. New to TimetaBRO? Fret not! This guide will walk you through a quickstart to start using TimetaBRO.

This user guide will also provide information about its amazing functionalities in the features section, optimising your use of TimetaBRO even further. Included is a command summary for your perusal.

Additionally, we included FAQs and a glossary in case you have any additional questions after reading this user guide.

--------------------------------------------------------------------------------------------------------------------
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **1. Quickstart**
Before we jump into it, let's make sure that your TimetaBRO is working properly!
1. Ensure you have Java 11 installed on your computer. This is important!
    * To check if Java 11 is currently installed, you may follow this [short guide](https://www.baeldung.com/java-check-is-installed).
    * If Java 11 is not installed, you may follow the installation instructions over [here](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).
2. Next, download the latest 'timetabro.jar' from [here](https://github.com/AY2324S1-CS2103T-W12-4/tp).
3. Copy the file to the folder you want to use as a home folder for TimetaBRO.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. You should see a pop-up. That is your reminder for the events and birthdays for the day!

![Ui-labelled](images/ui-startup.png)
<br><center><ins>Image: User interface layout of TimetaBRO upon startup with sample data</ins></center>

Here are some commands you can try:

  * `add n/Owen p/91792309 b/ 2001-12-26`:
  adds a friend named Owen, with phone number 91792309 and birthday on 26 December 2001.
  Say hello to your new friend!

  * `addschedule 1 type/module en/CS2101 h/Monday 1200 1400`:
  adds a module CS2101 into the schedule of the first person in your friends list that occurs on Monday 12pm to 2pm. If you did not change anything else, index 1 should refer to Alex Yeoh. He now has CS2101 in his timetable!

  * `cft Friday 1200 1400`:
  Filters your friend list to people who are free on these timings. Alex and Owen should both be free!

  * `delete 1`:
  Deletes a person off your list based on their index. It's time to say goodbye to Alex.

Nice! Now you know the basic commands and have launched TimetaBRO, lets get into the finer details.

## **2. TimetaBRO User Interface**

![Ui-labelled](images/ui-breakdown.png)
<br><center><ins>Image: User interface layout breakdown of TimetaBRO</ins></center>

### 2.1. Toolbar

Provides functionality related to accessing the help pop-up or exiting TimetaBRO. Currently, we only have two buttons for you! They are:
* `File`: Clicking on it will open a dropdown menu, where you can click on `Exit` to exit the application!
* `Help`: Clicking on it will open another dropdown menu, where you can click on `Help F1` to get a help pop-up! See 2.5 for more info about it.

### 2.2. Command input box

This is where you can input our cool commands to perform actions on TimetaBRO.

### 2.3. Command feedback box

This displays information related to your entered input in the command input box. <br>
Errors and success messages all will be here, and they will guide you to use TimetaBRO more effectively, so keep an eye out for them over here!

### 2.4. Friends list

This section displays all your friends in your list by default, and may be filtered display certain friends based on the executed command.

### 2.5 Help pop-up

![Ui-labelled](images/HelpPopUp.png)
<br><center><ins>Image: Help pop-up</ins></center>

This pop-up provides a link to this user guide should you lose the link! There is also a handy `Copy URL` button for you to copy the URL onto your clipboard!

### 2.6. Daily reminder pop-up

![Ui-labelled](images/ui-reminder-popup.png)
<br><center><ins>Image: User interface layout breakdown of TimetaBRO</ins></center>

This reminder pop-up appears when you open the app.

#### 2.6.1. Birthday reminders

This section displays the names of friends whose birthday falls on the date of the day you open TimetaBRO. Remember to wish them happy birthday!

#### 2.6.2. Event reminders

This section displays the non-recurring events you have for the day. They can be hangouts with friends, meetings, exams, and other important events you need reminders for!

### 2.7 Profile display

![Ui-labelled](images/ui-profile-display.png)
<br><center><ins>Image: TimetaBRO profile display breakdown</ins></center>

#### 2.7.1. User display

At all times, this section displays the user profile. This consists of all the user information and the user schedule. Now, you can see your schedule at a glance!

#### 2.7.2. Friend Display

This section is blank by default.
It displays the profile of whichever friend is selected from the list by clicking on it.<br>
Upon selection, the list cell of that friend will turn blue, and you can see your friend's details and schedule for quick comparisons.

#### 2.7.3. Display features

##### 2.7.3.1 Timetable layout

The timetable will be displayed under all the profile information, and each profile section is scrollable.

The days of the week are arranged from Monday to Sunday, and the timeslots are arranged from earliest to latest. Hope you like how it looks!

##### 2.7.3.2 Color coding

The event timeslots are color coded according to their type and vibe.

Blue for module (recurring), red for cca (recurring), and green for dated (non-recurring) events. Let TimetaBRO brighten your day up!

##### 2.7.3.3 Event block formation

Each event block consists of the event name on the first line, and the start and end times on the second line. Simple yet elegant!

## **3. Features**

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be written by you!<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order you like!<br>
  e.g. if the command specifies `n/NAME p/PHONE`, `p/PHONE n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines! Space characters surrounding line-breaks may be omitted when copied over to the application.

* Clicking on your friend's name will display their timetable on the bottom half of the right hand side of TimetaBRO.

* Only times in 30-minute intervals are allowed. <br>
  e.g. `1430` and `1500` will be excepted, `1445` and `2359` will not be valid! If your timings do not fit nicely into 30-minute chunks, try your best to round up/down, and use tags to remind yourself that it's actually at another time.<br> E.g. t/[insert Event Name] 1445

</div>

### 3.1 User Commands

#### 3.1.1 Editing User Information: `user`

Personalise your profile information, such as your birthday and tags!

Format: `user n/NAME p/PHONE e/EMAIL a/ADDRESS b/BIRTHDAY [t/TAG]...​`

* Click [here](#4-parameters) to find out more about the parameter constraints.

<div markdown="span" class="alert alert-primary">Tip:
You can have any number of tags (including 0). Go crazy!
</div>

**Successful Command:**
```
Edited Your Details: NAME, Phone: PHONE, Email: EMAIL; Address: ADDRESS; Birthday:
BIRTHDAY; Tags: [TAG]...​
```

**Unsuccessful Command:**\
No changes:
````
No changes to user.
````

#### 3.1.2 Adding recurring event to user: `addschedule user`

This command adds a recurring event to your schedule, such as a class or CCA. You might find this command useful if you want to keep track of your weekly commitments!

Format: `addschedule user type/EVENT_TYPE en/EVENT_NAME h/DAY_TIME`

- Adds an event titled `EVENT_NAME`
- `EVENT_TYPE` is a prefix that can either be `module` or `CCA`.
- Event date and time will be equal to `DAY_TIME`<br>
where `DAY_TIME` must be entered in the format `Day HHMM HHMM`.<br>
`Day` is any day of the week fully spelt out and is case-insensitive, `HHMM` is a 24H time format to indicate the start time and end time!
- Event names will be changed to all upper case regardless of whether it was keyed it in lower case or upper case.
- Click [here](#4-parameters) to find out more about the parameter constraints.


**Successful Command:**\
Input:
```
addschedule user type/CCA en/Basketball h/Tuesday 1500 1600
```
Output:
```
New event added:
CCA:
BASKETBALL Tuesday 1500 1600 to [Your Name]
```

**Unsuccessful Command:**\
If you use the wrong format (i.e. missing prefix, wrong event type),
this error message will be shown:
```
[error message]
Message Usage:
addschedule: Adds a schedule to the specified contact.
Parameters: INDEX type/EVENT_TYPE en/EVENT_NAME h/[DAY_OF_WEEK START_TIME [HHMM]
END_TIME [HHMM]]
Example: addschedule 1 type/cca en/Basketball h/Monday 1400 1600
NOTE: If you want to add a cca/module to yourself, use addschedule user
Example: addschedule user type/cca en/Basketball h/Monday 1400 1600
```

The error message at the top of the command feedback above will specify what needs to be changed!

Input:
```
addschedule user
```
Output:
```
Missing prefix(es) for en/ type/ h/ !
```

<div markdown="block" class="alert alert-info">

:information_source: Notes about the `addschedule` command:

* Timeslots added of type `module` will be colored blue, while those of type `CCA` will be colored red.
* To add an event that lasts until the end of the day (midnight), set the end timing as `2400`. We know it isn't ideal, but bear with us!
* You will not be allowed to add an event that clashes with any current or future events. We are making sure you don't double book!

</div>

#### 3.1.3 Adding non-recurring event to user: `addevent user`

This command adds a non-recurring event to your schedule. Use it to keep track of meetings, hangouts, and exams!

Format: `addevent user en/EVENT NAME h/DAY_TIME r/REMINDER`

- Adds an event titled `EVENT_NAME`
- Event date and time will be equal to `DATE_TIME` <br>
where `DATE_TIME` must be entered in the format `DATE [YYYY-MM-DD] START_TIME [HHMM] END_TIME [HHMM]`. Date must be a valid date!
- You can set whether you want to enable reminders for this event by inputting `y/n` under `[REMINDER]` so you don't forget them!
- Event names will be changed to all upper case regardless of whether it was keyed it in lower case or upper case. TimetaBRO is hyping you up!
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful Command:**\
Input:
```
addevent user en/Final Submission h/2023-10-17 1500 1600 r/y
```

Output:
```
New event added:
Dated Event:
FINAL SUBMISSION 2023-10-17 1500 1600 to [Your Name]
```

**Unsuccessful Command:**\
If you use the wrong format (i.e. missing prefix, wrong event type),
this error message will be shown:
```
[error message]
Message Usage:
addevent: Adds a non-recurring event to the calendar.
Parameters: INDEX en/EVENT_NAME h/[Date [YYYY-MM-DD] StartTime (HHMM) EndTime (HHMM)]
r/[REMINDER: y/n]
Example: addevent 1 en/CS2103T Lecture h/2020-03-02 1400 1600 r/y
Note: Index should be the index of the friend you are adding the dated event to or
'user' if you would like to add the event to yourself
```

The error message at the top of the command feedback above will specify what needs to be rectified.\
e.g.\
Input:
```
addevent user
```

Output:
```
Missing prefix(es) for en/ h/ r/ !
```


<div markdown="span" class="alert alert-warning">Caution:
Events added outside the current week are not visible! The application is streamlined for you to see what's ahead in the week, and not anything more.
</div>

<div markdown="block" class="alert alert-info">

:information_source: Notes about the `addevent` command:

* Timeslots added with this command will be green.
* To add an event that lasts until the end of the day (midnight), set the end timing as `2400`.
* You will not be allowed to add an event that clashes with any current or future events. Keep those clashes in mind!

</div>

#### 3.1.4 Deleting recurring event from user: `rmschedule user`

Removes the specified recurring item from your schedule. Useful for schedule changes!
<br>If there are multiple recurring events with the same names, this command will the instance of the event that was added the earliest!</br>

Format: `rmschedule user type/EVENT_TYPE en/EVENT_NAME`

- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful commands:**\
Input:
```
rmschedule user type/CCA en/Basketball
```

Output:
```
BASKETBALL has been removed from [Your Name]!
```

**Unsuccessful commands:**\
If you use the wrong format (i.e. missing prefix),
this error message will be shown:
```
[error message]
Message Usage:
rmschedule: Removes an event from the specified contact's calendar.
Parameters: INDEX type/EVENT_TYPE en/EVENT_NAME
Example: rmschedule 1 type/cca en/Basketball
NOTE: If you want to remove an event from yourself, use index user
Example: rmschedule user type/cca en/Basketball
```

The error message at the top of the command feedback above will specify what needs to be rectified.\
e.g.\
Input:
```
rmschedule user
```

Output:
```
Missing prefix(es) for en/ type/ !
```

If you input an invalid event type, the following error message will be shown:
```
Invalid event type!
Event type must either be 'cca' or 'module'!
```

<div markdown="block" class="alert alert-info">

:information_source: Notes about the `rmschedule` and `rmevent` command:

If there are multiple time slots with the same names, the command will remove time slots in the chronological order they were added.

</div>

#### 3.1.5 Deleting non-recurring event from user: `rmevent user`

Removes the specified event from your schedule. You can use it when your plans changes!
<br>If there are multiple non-recurring events with the same names, this command will the instance of the event that was added the earliest!</br>

Format: `rmevent user en/EVENT_NAME`

- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful commands:**\
Input:
```
rmevent user en/CS2103T exam
```

Output:
```
Dated event 'CS2103T EXAM' deleted from your calendar!
```

**Unsuccessful commands:**\
If you use the wrong format (i.e. missing prefix),
this error message will be shown:
```
Missing prefix(es) for en/ !
Message Usage:
rmevent: Removes an event from the specified contact's calendar.
Parameters: INDEX en/EVENT_NAME
Example: rmevent 1 en/CS2103T Final Exam
NOTE: If you want to remove an event from your calendar, use rmevent user.
Example: rmevent user en/CS2103T Final Exam
```

### 3.2 Friend Commands

#### 3.2.1 Adding a friend: `add`

Adds a person to your TimetaBRO friend list. Use it to keep track of the schedules and details of your closest friends or even new acquaintances. Watch as your list grows!

Format: `add n/NAME p/PHONE e/EMAIL a/ADDRESS b/BIRTHDAY [t/TAG]...​`

* Adds a person to your friend list to the last index!
* You can't add someone with the same birthday and same name, because it's likely that they are the same person! (We know it's not necessarily true, but it's the best way we can do it without collecting your NRIC...)
* You can't have duplicate phone numbers or emails.
* Names can be alphanumeric. For Elon Musk's child's sake!
* Birthdays must be a valid date. We do check the calendars!
* Click [here](#4-parameters) to find out more about the parameter constraints.

<div markdown="span" class="alert alert-primary">Tip:
Your friend can have any number of tags (including 0). Go crazy!
</div>

**Successful Command:**\
Output:
```
New Person Added: {NAME}, Phone: {PHONE}, Email: {EMAIL}, Address: {ADDRESS},
Birthday: {BIRTHDAY}, tags: [{TAG}]
```

**Unsuccessful Command:**\
Output:
```
Invalid command format!
add: Adds a person to the address book. Parameters: n/NAME p/PHONE e/EMAIL a/ADDRESS
b/BIRTHDAY [t/TAG]...
Example: add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25
b/2000-01-01 t/friends t/owesMoney
```

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com b/2001-12-12 a/Downtown t/police`
* `add n/Betsy Crowe e/betsycrowe@example.com p/1234567 b/2002-04-19 a/Upurs Street t/criminal`

#### 3.2.2 Editing friend information: `edit`

Edits a friend's details in TimetaBRO. Use it when they change addresses or phone numbers!

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed friend list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Same constraints as add! Meaning no duplicate names. Birthdays also need to be valid dates.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative. It's not ideal, but we're working on it!
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.
* Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful Command:**

Changes the specified fields of specified friend’s profile.

Output:
```
Edited Person {NAME}; Phone: {PHONE}; Email: {EMAIL}; Address: {ADDRESS}; Birthday:
{BIRTHDAY}; tags: [{TAG}]
```

**Unsuccessful Command:**\
Displays an error message:
```
Invalid command format!
edit: Edits the details of the person identified by the index number used in the
displayed person list. Existing values will be overwritten by the input values.
Parameters: INDEX (must be a positive integer) [n/NAME] [p/PHONE] [e/EMAIL]
[a/ADDRESS] [b/BIRTHDAY] [t/TAG]...
Example: edit 1 p/91234567 e/johndoe@example.com
```
If index given is not in the list, the following error message will be returned:
```
The person index provided is invalid
```

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com`: Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower`: Edits the name of the 2nd person to be `Betsy Crower`.

#### 3.2.3 Deleting a friend's profile: `delete`

Deletes the specified friend from your TimetaBRO friends list. You may it when your friends leave for SEP and you don't want your friends' list being so cluttered!

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`, in the event they're no longer your friend.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer**, e.g. 1, 2, 3, …​

**Successful Command:**

Output:
```
[Friend's Name] deleted.
```

The friend should be removed from the friend's list. Goodbye!

**Unsuccessful Command:**\
If index is unspecified or not a positive integer, the following error message will be returned:
```
Invalid command format!
delete: Deletes the person identified by the index number used in the displayed
person list.
Parameters: INDEX (must be a positive integer)
Example: delete 1
```

If index given is not in the list, the following error message will be returned:
```
The person index provided is invalid
```

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

#### 3.2.4 Listing all friends: `list`

Shows a list of all your added friends! You could use it after you use the `find` or `cft` command, which alters your friends' list. Your list now returns to its original state!

Format: `list`

#### 3.2.5 Viewing friend's profile

View your friend's timetable. Now, you can use it compare both your timetables!

![Ui-labelled](images/ui-view-friend.png)
<br><center><ins>Image: Friend profile selection on TimetaBRO</ins></center>

**How to use:**
1. Scroll down your list of friends, until you locate the friend's timetable you want to see.
2. Click on the friend's profile. You should see their details appear, on the bottom right panel.

#### 3.2.6 Adding recurring event to friend: `addschedule`

This command adds a weekly recurring event to a friend's schedule, like their modules and CCAs. Use it to keep track of their lesson timings, and CCAs. You could maybe even join in if you're free!

Format: `addschedule INDEX type/TYPE en/EVENT_NAME h/DAY_TIME`

- Adds an event titled `EVENT_NAME` to the specified friend at `INDEX`
- `TYPE` is a prefix that can either be `module` or `CCA`. Pretty straightforward!
- Event date and time will be equal to `DAY_TIME`
  where `DAY_TIME` must be entered in the format `[monday/tuesday/wednesday/thursday/friday/saturday/sunday] HHMM [start time] HHMM [end time]`
- Event names will be changed to all upper case regardless of whether it was keyed it in lower case or upper case. Imagine TimetaBRO hyping you up!
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful Command:**\
Input:
```
addschedule 1 type/module en/CS2030 h/Monday 1000 1400
```
Output:
```
New event added:
Module:
CS2030 Monday 1000 1400 to [Friend Name]
```

**Unsuccessful Command:**\
If you do not put an index or the index is not a positive integer,
this error message will be shown:
```
Invalid index!
Index can only be 'user' or a positive integer!
```

If you use an index that is larger than the list,
this error message will be shown:
```
The person index provided is invalid
Index can be max [list size]!
```

If you use the wrong format (i.e. missing prefix, wrong event type),
this error message will be shown:
```
[error message]
Message Usage:
addschedule: Adds a schedule to the specified contact.
Parameters: INDEX type/EVENT_TYPE en/EVENT_NAME h/[DAY_OF_WEEK START_TIME [HHMM]
END_TIME [HHMM]]
Example: addschedule 1 type/cca en/Basketball h/Monday 1400 1600
NOTE: If you want to add a cca/module to yourself, use addschedule user
Example: addschedule user type/cca en/Basketball h/Monday 1400 1600
```

The error message at the top of the command feedback above will specify what needs to be changed to help you correct any errors you might have made!\
e.g.\
Input:
```
addschedule
```

Output:
```
Missing prefix(es) for en/ type/ h/ !
```

<div markdown="block" class="alert alert-info">

:information_source: Notes about the `addschedule` command:

* Timeslots added of type `module` will be colored blue, while those of type `CCA` will be colored red.
* To add an event that lasts until the end of the day (midnight), set the end timing as `2400`.
* You will not be allowed to add an event that clashes with any current or future events.

</div>

#### 3.2.7 Adding non-recurring event to friend: `addevent`

This command adds a dated, non-recurring event to you or your friend's schedule. You could use it to keep track of your friends' important events!

Format: `addevent INDEX en/EVENT_NAME h/DATE_TIME r/REMINDER`

- Adds an event titled `EVENT_NAME` to the specified friend at `INDEX`
- Event date and time will be equal to `DATE`
  where `DATE` must be entered in the format `YYYY-MM-DD HHMM [start time] HHMM [end time]`. Date must be a real date too!
- The reminder feature only works for events in your schedule so far. We're working on it! So for now using `y/n` does not matter for events added to your friends' schedules but it is still necessary!
- Event names will be changed to all upper case regardless of whether it was keyed it in lower case or upper case.
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful Command:**\
Input:
```
addevent 1 en/CS2030 Finals h/2023-10-31 1000 1400 r/y
```

Output:
```
New event added:
Dated Event:
CS2030 FINALS 2023-10-31 1000 1400 to [Friend Name]
```

**Unsuccessful Command:**

If you do not put an index or the index is not a positive integer,
this error message will be shown:
```
Invalid index!
Index can only be 'user' or a positive integer!
```

If you use an index that is larger than the list,
this error message will be shown:
```
The person index provided is invalid
Index can be max [list size]!
```

If you use the wrong format (i.e. missing prefix),
this error message will be shown
```
[error message]
Message Usage:
addevent: Adds a non-recurring event to the calendar.
Parameters: INDEX en/EVENT_NAME h/[Date [YYYY-MM-DD] START_TIME [HHMM] END_TIME
[HHMM]] r/[REMINDER: y/n]
Example: addevent 1 en/CS2103T Final Exam h/2020-03-02 1400 1600 r/y
Note: Index should be the index of the friend you are adding the dated event to or
'user' if you would like to add the event to yourself
```

The error message at the top of the command feedback above will specify what needs to be rectified.\
e.g.\
Input:
```
addevent 1
```

Output:
```
Missing prefix(es) for en/ h/ r/ !
```

<div markdown="block" class="alert alert-info">

:information_source: Notes about the `addevent` command:

* Timeslots added with this command will be green.
* To add an event that lasts until the end of the day (midnight), set the end timing as `2400`.
* You will not be allowed to add an event that clashes with any current or future events. Tell your friends if they have clashes!

</div>

#### 3.2.8 Deleting recurring event from friend: `rmschedule`

Removes the specified recurring item from the specified Person's schedule. You might find it useful when they have a change in schedule!<br>If there are multiple recurring events with the same names, this command will the instance of the event that was added the earliest!<br>

Format: `rmschedule INDEX type/EVENT_TYPE en/EVENT_NAME`

- Removes an event titled `EVENT_NAME` from the specified friend at `INDEX`
- `EVENT_TYPE` is a prefix that can either be `module` or `CCA`. Pretty straightforward!
- `EVENT_NAME` must exist in the friend's schedule.
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful commands:**\
Input:
```
rmschedule 1 type/CCA en/Basketball
```

Output:
```
BASKETBALL has been removed from [Friend's Name]!
```

**Unsuccessful commands:**\
If you do not put an index or the index is not a positive integer,
this error message will be shown:
```
Invalid index!
Index can only be 'user' or a positive integer!
```

If you use an index that is larger than the list,
this error message will be shown:
```
The person index provided is invalid
Index can be max [list size]!
```

If given event does not exist, this error message will be shown:
```
[TYPE] [EVENT_NAME] does not exist!
Please check that you have entered the correct [TYPE] name!
```

If wrong command format is used (i.e. missing prefixes):
```
[error message]
Message Usage:
rmschedule: Removes an event from the specified contact's calendar.
Parameters: INDEX type/EVENT_TYPE en/EVENT_NAME
Example: rmschedule 1 type/cca en/Basketball
NOTE: If you want to remove an event from yourself, use index user
Example: rmschedule user type/cca en/Basketball
```

The error message at the top of the command feedback above will specify what needs to be rectified.\
e.g.\
Input:
```
rmschedule 1
```

Output:
```
Missing prefix(es) for en/ type/ !
```

<div markdown="block" class="alert alert-info">

:information_source: Notes about the `rmschedule` and `rmevent` command:

If there are multiple time slots with the same names, the command will remove time slots in the chronological order they were added.

</div>

#### 3.2.9 Deleting non-recurring event from friend: `rmevent`

Removes the specified event from the specified Person. Use this if there are a change in anyone's plans!<br>
If there are multiple non-recurring events with the same names, this command will the instance of the event that was added the earliest!<br>

Format: `rmevent INDEX en/EVENT_NAME`

- Removes an event titled `EVENT_NAME` from the specified friend at `INDEX`
- `EVENT_NAME` must exist in the friend's schedule.
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful commands:**\
Input:
```
rmevent 1 en/CS2103T Final Exam
```

Output:
```
Dated event 'CS2103T FINAL EXAM' deleted from [Friend's Name]'s calendar!
```

**Unsuccessful commands:**\
If you do not put an index or the index is not a positive integer,
this error message will be shown:
```
Invalid index!
Index can only be 'user' or a positive integer!
```

If you use an index that is larger than the list,
this error message will be shown:
```
The person index provided is invalid
Index can be max [list size]!
```

If given event does not exist, this error message will be shown:
```
Event [EVENT_NAME] does not exist!
Please check that you have entered the correct event name!
```

If wrong command format is used (i.e. missing prefix):
```
Missing prefix(es) for en/ !
Message Usage:
rmevent: Removes an event from the specified contact's calendar.
Parameters: INDEX en/EVENT_NAME
Example: rmevent 1 en/CS2103T Final Exam
NOTE: If you want to remove an event from yourself, use rmevent user
Example: rmevent user en/CS2103T Final Exam
```

#### 3.2.10 Finding common free times with your friend(s): `cft`

Finds friend(s) with the same free times as you, or returns a message if no friends are free. This command is for you to look for lunch buddies or to see what time you can bug them!

Format:

**Finding common free times with a specific friend**: `cft INDEX`

* Finds common free times with friend of the specified `INDEX`.

**Finding common free times with entire friends list**: `cft`

* Finds common free times with friend of the specified `INDEX`. Use it with your best friend!
* Finds common free times with **all friends** in the list if `INDEX` is not included. Now find someone to bug!

**Successful Command:**\
Input:
```
cft
```

Output:\
Displays all the common free times you have with all your friends in your friend list.

```
Here are the contacts with the same free time as you:
You and Bernice Yu have no common free time!
You and Charlotte have no common free time!
You have common free times with Alex Yeoh at:
[Monday 0000 0800]
[Monday 2000 2400]
[Tuesday 0000 1300]
[Wednesday 0000 1200]
[Wednesday 1800 2400]
[Thursday 0000 2400]
[Friday 0000 2400]
[Saturday 0000 2400]
[Sunday 0000 2400]
```

Input:
```
cft 1
```

Output:
```
You have common free times with Alex Yeoh at:
[Monday 0000 0800]
[Monday 2000 2400]
[Tuesday 0000 1300]
[Wednesday 0000 1200]
[Wednesday 1800 2400]
[Thursday 0000 2400]
[Friday 0000 2400]
[Saturday 0000 2400]
[Sunday 0000 2400]
```

If your contact is a hustler, and you have no common free times with a contact, it will display:
```
You and [Friend's Name] have no common free time!
```

If you're super busy and your entire timetable is full for the whole week, it will return:
```
You have no free time!
```

If you execute `cft` and you have no common free times with any contact, it will return:
```
You have no contacts with the same free time as you!
```

**Unsuccessful Command:**

If you input invalid command format, the app will display:
```
Invalid command format!
cft: Finds all contacts with the same free time as the User.
Example: cft
```

If you input an index that does not exist in the list, the app will display:
```
The person index provided is invalid
```

Examples:
* `cft` lists all friends .
* `cft 1` finds the 1st person in the TimetaBRO friend list and displays the common free times you have with the person.

### 3.3 Reminder Commands

#### 3.3.1 Set reminder for non-recurring events: `setReminder`

Sets a reminder for a **dated, non-recurring event** in your schedule. Use it when you forgot to set reminders when adding it, or decide that an event is important enough for a reminder afterwards. You'll never forget it now!

Format: `setReminder EVENT_NAME`
- Set reminder for an event titled `EVENT_NAME` in your schedule!
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful command:**\
Input:
```
setReminder CS2103T Final Exam
```

Output:
```
Reminder set for following event:
CS2103T FINAL EXAM
```

**Unsuccessful command:**

If an invalid event name is used, it will return:
```
No such event exists!
```

#### 3.3.2 Remove reminder for non-recurring events: `rmReminder`

Removes a reminder for a dated event from your schedule. You may find this command great when you accidentally set a reminder, or decided that it's not that important!

Format: `rmReminder EVENT_NAME`
- Remove reminder for an event titled `EVENT_NAME` in your schedule.
- Click [here](#4-parameters) to find out more about the parameter constraints.

**Successful command:**\
Input:
```
rmReminder CS2103T Final Exam
```

Output:
```
Reminder removed for following event:
CS2103T FINAL EXAM
```

**Unsuccessful command:**\
If an invalid event name is used, it will return:
```
No such event exists!
```

### 3.4 Other Commands

#### 3.4.1 Viewing help: `help`

Shows a message explaining how to access the help page. We promise it's helpful.

Format: `help`

The app should provide you with a pop-up as shown below!
![Ui-Labelled](images/HelpPopUp.png)

#### 3.4.2 Clearing all entries: `clear`

Clears all entries from TimetaBRO. You can use it to clear the sample data we provided, or if you find it too cluttered. Be careful not to delete everything by accident!

Format: `clear`

**Successful Command:**\
Output:
```
All friends have been deleted.
```

The entire friend list should be emptied. Start afresh! 

#### 3.4.3 Exiting the program: `exit`

Exits the program. Use it instead of the traditional button if you're already typing!

Format: `exit`

The app closes after saving all data.

--------------------------------------------------------------------------------------------------------------------

## **4. Parameters**

<div markdown="span" class="alert alert-warning">Caution:
Command parameter inputs have constraints to ensure their use is streamlined.
</div>

| Parameter               | Constraints                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **NAME**                | Alphanumeric, any length.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **PHONE**               | 8 digits.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **INDEX**               | A number present on the list of friends in the address book.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **ADDRESS**             | No constraints.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **EMAIL**               | Emails should be of the format `local-part@domain` and adhere to the following constraints: <br>1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters. <br>2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods. <br>The domain name must: <br>-  end with a domain label at least 2 characters long <br>- have each domain label start and end with alphanumeric characters <br>- have each domain label consist of alphanumeric characters, separated only by hyphens, if any. |
| **BIRTHDAY**            | `YYYY-MM-DD`, a valid date.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| **DAY_TIME**            | `DAY HHMM HHMM` <br>`DAY` is any day of the week fully spelt out (case insensitive), <br> the first `HHMM` is the start time of the time block and the second is the end time, <br> `HHMM` is a 24 hour time format of half hour intervals, from `0000` to `2400`. <br> Example: `mOndaY 2330 2400`                                                                                                                                                                                                                                                                                                                                                                                        |
| **DATE_TIME**           | `YYYY-MM-DD HHMM HHMM`<br>`YYYY-MM-DD` is the date of the event which has to be valid, <br> the first `HHMM` is the start time of the time block and the second is the end time, <br> `HHMM` is a 24 hour time format of half hour intervals, from `0000` to `2400`. <br> Example: `2023-11-08 2330 2400`                                                                                                                                                                                                                                                                                                                                                                                  |
| **EVENT_NAME (MODULE)** | Accepts any NUS module code. Example: `CS2103T`/`UTC2113`/`MA1521`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| **EVENT_NAME (CCA)**    | Alphanumeric, any length.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **EVENT_NAME**          | Alphanumeric, any length.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **EVENT_TYPE**          | `cca` or `module` only.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **REMINDER**            | `y` to turn reminder on. <br>`n` to turn reminder off.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |


--------------------------------------------------------------------------------------------------------------------

## **5. Saving the data**

TimetaBRO data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually!

## **6. Editing the data file**

TimetaBRO data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file!

<div markdown="span" class="alert alert-warning">Caution:
If your changes to the data file makes its format invalid, TimetaBRO will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

--------------------------------------------------------------------------------------------------------------------

## **7. FAQ**

**How do I transfer my data to another Computer?**<br>
Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TimetaBRO home folder.

**Is TimetaBRO compatible with Mac and Linux operating systems, or is it Windows-specific?**<br>
TimetaBRO is compatible with multiple operating systems, including Windows, Mac, and Linux! It runs on systems that support Java 11, so you can use it on your preferred platform.

**Can I import my friend's schedule from a different calendar application into TimetaBRO?**<br>
Nope! TimetaBRO does not offer a direct import feature for schedules from other calendar applications. You'll need to manually add your friends' schedules to TimetaBRO using the add command. Tedious, but we're on it!

**Is there a way to set recurring events for specific dates or weekdays, like every Tuesday, without manually adding them one by one?**<br>
Yes! You can add recurring events for specific weekdays in TimetaBRO using the `addschedule` command. This feature allows you to set events for particular days of the week, making it easier to input recurring events.

**What happens if I accidentally delete a friend or event in TimetaBRO? Is there a way to recover deleted data?**<br>
Sorry! TimetaBRO does not have a built-in data recovery feature. When you delete a friend or event, the data is permanently removed from the application. Do double-check before deleting something, or clearing your app!


**How can I customize the reminder settings for events added to TimetaBRO?**<br>
You can customize the reminder settings for events when adding them using the `addevent` command. The r/y or r/n option allows you to enable or disable reminders for specific events.

**Is there a way to share my TimetaBRO schedule with others or export it to a different format, such as a calendar file?**<br>
Nope! TimetaBRO currently does not support sharing schedules with others or exporting them to external formats. It primarily functions as a personal scheduling tool.

**Can I remove a specific event or schedule from my timetable or my friends' timetable?**<br>
Not yet! However, for now, you may set slightly different names for events with the same name, such as 'Meetup with Jason (1)' and 'Meetup with Jason (2)' so that you can select the specific event to delete later. If not, when you try to remove an event from the schedule, it will remove the one that you added first!

**If I have two events that overlap, can I add both of them to the calendar?**<br>
Nope! As TimetaBRO was designed to be a timetable management app, we would not be allowing overlapping events to be added into the timetable. You don't wanna double book your own time!

**Why do my event names automatically change to all capitalised letters?**<br>
We want to standardize the format of all event names to prevent users from accidentally adding multiple events wit the same names but in different formats! (i.e. only first letter is capitalised vs only last letter is capitalised)

**Why can't I put 0000 instead of 2400 when my event ends at 12AM?**<br>
This is because it makes it hard for TimetaBRO to understand that it ends at 12AM the next day! Hence, 2400 is a workaround for this. Stay tuned for enhancements!

--------------------------------------------------------------------------------------------------------------------

## **8. Known issues**

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen :( But don't worry! The remedy is to delete the `preferences.json` file created by the application before running the application again! Simple!

2. **The timetable only supports timings in 30-minute gaps** because this is an app made for NUS students! Since NUS timetables are set in 30-minute gaps, we have adapted this to better fit the NUS timetable style.

3.  While you **can't select a specific event to delete**, don't sweat it. We've got you covered. The events are removed in the order they were added, following a first-in-first-out basis. So, the earliest added event will be the first to bid farewell! If you have any more questions, feel free to ask!

--------------------------------------------------------------------------------------------------------------------

## **9. Command Summary**
For you TLDR-ers!

| Action                                      | Format and Examples                                                                                                                                                                  |
|---------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add a Friend**                            | `add n/NAME p/PHONE e/EMAIL a/ADDRESS b/BIRTHDAY [t/TAG]…​`<br> Example: `add n/John Doe p/98765432 e/johnd@example.com a/1 Hon Sui Sen Dr, Singapore 117588 b/2023-11-05 t/police`  |
| **Add a Dated Event to friend**             | `addevent INDEX en/EVENT NAME h/DATE r/REMINDER`<br> Example: `addevent 1 en/CS2030 Finals h/2023-10-31 1000 1400 r/y`                                                               |
| **Add a Dated Event to user**               | `addevent user en/EVENT NAME h/DATE r/REMINDER`<br> Example: `addevent user en/CS2030 Finals h/2023-10-31 1000 1400 r/y`                                                             |
| **Remove an Event from friend**             | `rmevent INDEX en/EVENT NAME`<br> Example: `rmevent 1 en/CS2103T Lecture`                                                                                                            |
| **Remove an Event from user**               | `rmevent user en/EVENT NAME`<br> Example: `rmevent user en/CS2103T Lecture`                                                                                                          |
| **Add a Recurring Event to friend**         | `addschedule INDEX type/EVENT_TYPE en/EVENT_NAME h/DAY_TIME`<br> Example: `addschedule 1 type/module en/CS2030 h/Monday 1000 1400`                                                   |
| **Add a Recurring Event to user**           | `addschedule user type/EVENT_TYPE en/EVENT_NAME h/DAY_TIME`<br> Example: `addschedule 1 type/module en/CS2030 h/Monday 1000 1400`                                                    |
| **Remove a Recurring Event from friend**    | `rmschedule INDEX type/EVENT_TYPE en/EVENT_NAME`<br> Example: `rmschedule 1 type/CCA en/CS2103T Lecture`                                                                             |
| **Remove a Recurring Event from user**      | `rmschedule user type/EVENT_TYPE en/EVENT_NAME`<br> Example: `rmschedule 1 type/CCA en/CS2103T Lecture`                                                                              |
| **Remove a Reminder**                       | `rmReminder EVENT_NAME`<br> Example: `rmReminder CS2103T Lecture`                                                                                                                    |
| **Set a Reminder**                          | `setReminder EVENT_NAME`<br> Example: `setReminder CS2103T Lecture`                                                                                                                  |
| **Clear All Entries**                       | `clear`                                                                                                                                                                              |
| **Delete a Friend**                         | `delete INDEX`<br> Example: `delete 3`                                                                                                                                               |
| **Edit Friend Info**                        | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`<br> Example: `edit 2 n/James Lee e/jameslee@example.com`                                                |
| **Edit User Info**                          | `user [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [t/TAG]…​`<br> Example: `user n/James Lee e/jameslee@example.com`                                                        |
| **List All Friends**                        | `list`                                                                                                                                                                               |
| **View Help**                               | `help`                                                                                                                                                                               |
| **Find Common Free Times with friend**      | `cft INDEX` <br> Example: `cft 1`                                                                                                                                                    |
| **Find Common Free Times with all friends** | `cft`<br>                                                                                                                                                                            |
| **Exit**                                    | `exit`                                                                                                                                                                               |
