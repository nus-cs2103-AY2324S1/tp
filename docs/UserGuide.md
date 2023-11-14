---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# BayMeds v.2103 User Guide

## Welcome to the BayMeds User Guide!

This guide provides a step-by-step documentation on how to use BayMeds' features and how to tackle commonly encountered errors. In addition, the [quick start guide](#quick-start) shows you how to get started in a matter of minutes!

## What is BayMeds?

BayMeds v.2103 (a.k.a BayMeds) is your go-to prescription management solution, catering to chronically ill patients or caregivers seeking a streamlined and effective way to track prescriptions. Ideal for those with busy lives and complex medication regimens, BayMeds offer reminders for staying on top of prescription schedules and provides a platform to track prescription consumptions. With a focus on user-friendliness, BayMeds aims to promote medication adherence and reduce misusage.

## What can I do with BayMeds?

#### Track consumption of existing prescriptions

BayMeds filters and shows you your prescriptions to be consumed each day. By marking prescriptions you have consumed, BayMeds will differentiate and show you the prescriptions that are completed and those that have yet to be completed for the day.

#### Store important details of existing prescriptions

BayMeds allows you to store details crucial to the consumption of the prescription, such as the end and expiry date of the prescription, as well as the current stock of pills available. You may also store specific requirements that a prescription may have, such as restricted consumption to only after heavy meals.

#### Get warnings for conflicting drugs

BayMeds informs you when you have drugs that conflicts with each other, e.g. drugs that react with other drugs or affect the efficacy of other drugs. This is especially useful for patients with multiple prescriptions, as it helps to prevent the consumption of conflicting drugs.

#### Track past prescriptions

BayMeds enables storage of past prescriptions, i.e. prescriptions that you have completed in the past. This provides ease of access to such information when required, such as during a Doctor's visit, or when tracking the quantity of medication remaining.

--------------------------------------------------------------------------------------------------------------------

## Table of Contents
1. [How to use this guide](#how-to-use-this-guide)
2. [Quick start](#quick-start)
3. [Graphical User Interface](#graphical-user-interface)
4. [Features](#features)
    * [How to add a prescription : `add`](#how-to-add-a-prescription-add)
    * [How to list all current prescriptions : `list`](#how-to-list-all-current-prescriptions-list)
    * [How to list today's prescriptions : `listToday`](#how-to-list-today-s-prescriptions-listtoday)
    * [How to list completed prescriptions : `listCompleted`](#how-to-list-completed-prescriptions-listcompleted)
    * [How to edit a prescription : `edit`](#how-to-edit-a-prescription-edit)
    * [How to find prescriptions by name : `find`](#how-to-find-prescriptions-by-name-find)
    * [How to delete a prescription : `delete`](#how-to-delete-a-prescription-delete)
    * [How to take a medication : `take`](#how-to-take-a-medication-take)
    * [How to untake a medication : `untake`](#how-to-untake-a-medication-untake)
    * [How to list medications that are about to expire or low in stock : `reminder`](#how-to-list-medications-that-are-about-to-expire-or-low-in-stock-reminder)
    * [How to list a prescription's conflicting drugs : `listConflicts`](#how-to-list-a-prescription-s-conflicting-drugs-listconflicts)
    * [How to list all conflicting drugs : `listAllConflicts`](#how-to-list-all-conflicting-drugs-listallconflicts)
    * [How to view help : `help`](#how-to-view-help-help)
    * [How to exit BayMeds : `exit`](#how-to-exit-baymeds-exit)
5. [FAQ](#faq)
6. [Known issues](#known-issues)
7. [Command summary](#command-summary)

## How to use this guide

Navigate to the start of this guide anytime by clicking the button in the bottom right corner of your screen.
The blue underlined text in this guide are hyperlinks that will bring you to the relevant section when clicked. <br>
The ```code``` text in this guide are commands that you can type into the application. <br>
To see all available commands, refer to the [Command Summary](#command-summary) section. <br>
If you are an experienced user, you can refer to the [Features](#features) section for a quick summary of the commands available. <br>
If you are new to BayMeds, we recommend you to start with the [Quick Start](#quick-start) section. <br>

Also, here are some common icons you may encounter in this guide.
<div style="width: 100%;">

| Icon                                                          | Description            |
|---------------------------------------------------------------|------------------------|
| <box type="definition" />                                     | Examples               |
| <box type ="tip" />                                           | Useful tips            |
| <box type="info" />                                           | Additional information |
| <box type="warning" icon=":fa-solid-triangle-exclamation:" /> | Warning                |
| <box type="wrong" />                                          | Errors                 |

</div>

--------------------------------------------------------------------------------------------------------------------

## Quick start

<box type="tip">

If you are experienced in using JAR applications, simply download the latest version [here](https://github.com/AY2324S1-CS2103T-T15-2/tp/releases) and start the application.

</box>

1. Ensure you have Java `11` or above installed in your computer.

1. Download the latest `BayMeds.jar` from [here](https://github.com/AY2324S1-CS2103T-T15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for BayMeds.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar BayMeds.jar` command to start the application.<br>


   A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br><br>
   ![Ui](images/Ui.png)

1. Type commands in the command box and press Enter to execute it. <br> e.g. typing the command **`help`** and pressing Enter will open the help window.<br>

   Some example commands you can try:

   * `list` : Shows all your prescriptions.

   * `add mn/Doxazosin f/Daily` : Adds a prescription `Doxazosin` to the list.

   * `delete 2` : Deletes the second prescription in the list.


1. Refer to the [features](#features) below for details of each command.

<br></br>

--------------------------------------------------------------------------------------------------------------------

## Graphical User Interface

BayMeds allows you to manage your prescriptions using only a single user interface! Hover over the circles to find out more about the different parts!

<annotate src="images/Ui.png" alt="Ui">
  <a-point x="4%" y="3%" content="You can access various menu functions here." label="1" header="Menu Bar" opacity="0.8" trigger="hover focus" />
  <a-point x="20%" y="24.5%" content="This is where you will input your commands." label="2" header="Command Box" opacity="0.8" trigger="hover focus" />
  <a-point x="24%" y="50%" content="BayMeds will show you messages through this box."  label="3" header="Result Display" opacity="0.8" trigger="hover focus" />
  <a-point x="75%" y="40%" content="This is where you can view your list of prescriptions."  label="4" header="List Display" opacity="0.8" trigger="hover focus" />
</annotate>

<div><br></div>

##### 1: Menu Bar

You can access various menu functions here.

##### 2: Command Box

This is where you will input your commands.

##### 3: Result Display

BayMeds will show you messages through this box.

##### 4: List Display

This is where you can view your list of prescriptions.
<br></br>


--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" header="**Notes about the command format**">

* Words in `<>` are parameters you can enter.
  <box type="definition" header="Example" seamless>

  For `add mn/<medication_name>`, `<medication_name>` is a parameter which can be used as `add mn/Aspirin`.
  </box>

* Items in `[]` are optional.
  <box type="definition" header="Example" seamless>

  `<medication_name> [sd/<start_date>]` can be used as `mn/Aspirin sd/25/10/2023` or as `mn/Aspirin`.
  </box>

* Parameters can be in any order.
  <box type="definition" header="Example" seamless>

  For `mn/<medication_name> f/<frequency>`, `f/<frequency> mn/<medication_name>` is also acceptable.
  </box>

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

</box>
<br></br>


### How to add a prescription : `add`

Upon getting a new prescription from the doctor, BayMeds allows you to add the prescription to the list.

To add the prescription, type the following command.

Format:
```
add
  mn/<medication_name>
  [d/<dosage>]
  [f/<frequency>]
  [sd/<start_date>]
  [ed/<end_date>]
  [exp/<expiry_date>]
  [ts/<total_stock>]
  [cfdg/<conflicting_drugs>]
  [n/<note>]
```

The prescription will then be added and shown in the list.

<box type="info" header="Notes">

* `<medication_name>` refers to the name of the medication.
  <box type="definition" header="Example" seamless>

  Aspirin, Doxazosin, Propranolol
  </box>

* `<dosage>` refers to the number of pills to be taken. It only accepts a numeric value.
  <box type="definition" header="Example" seamless>

  For 1 pill, type 1.
  </box>

* `<frequency>` refers to the interval to consume the prescription. It only accepts the inputs `Daily`, `Weekly` or `Monthly`.

* `<start_date>`, `<end_date>` and `<expiry_date>` should be in <span style="color:red">**dd/mm/yyyy format**</span>. In addition, `<expiry_date>` cannot be earlier than the `<end_date>`, and similarly, `<end_date>` cannot be earlier than the `<start_date>`.
  <box type="definition" header="Example" seamless>

  For 20th September 2023, type `20/09/2023`.
  </box>

* `<total_stock>` refers to the total number of pills you have. It only accepts a numeric value.
  <box type="definition" header="Example" seamless>

  For 100 pills, type 100.
  </box>

* `<conflicting_drugs>` refers to the drugs that conflict with your prescription. You may add in multiple conflicting drugs by separating them with a space.
  <box type="definition" header="Example" seamless>

  If you wish to add Paracetamol and Aspirin as conflicting drugs to your prescription, type `cfdg/Paracetamol Aspirin`.
  </box>

* `<note>` refers to any important or special information you would like to include.
  <box type="definition" header="Example" seamless>

  "To be taken after meals".
  "Take after food".
  </box>

</box>

<box type="definition" header="#### Example 1">

```
add mn/Isotretinoin d/1 f/Weekly sd/01/08/2023 ed/25/12/2023 exd/01/01/2024 ts/100 n/Take only after dinner
```
By typing this, you will add a new prescription with the following information.

* Medication name: Isotretinoin
* Dosage: 1
* Frequency: Weekly
* Start date: 01/08/2023
* End date: 25/12/2023
* Expiry date: 01/01/2024
* Total stock: 100
* Note: Take only after dinner

After successfully adding the prescription, you will get the following result: <br>

![result for 'Example output of add command'](images/ui/Ui-add.png)

</box>

<box type="definition" header="#### Example 2">

```
add mn/Doxazosin
```
By typing this, you will add a new prescription with the following information.

* Medication name: Doxazosin

After successfully adding the prescription, you will get the following result: <br>

![result for 'Example output of add command'](images/ui/Ui-add-2.png)

<box type="info">

As shown in this example, the date of which this entry was entered (e.g. 27/10/2023) will be used as the default start date if no start date was given.
</box>

</box>
<br></br>


### How to list all current prescriptions : `list`

BayMeds allows you to list all prescriptions you are **currently** taking.

To list all **current** prescriptions, type the following command.

Format:
```
list
```

You will then be able to see the relevant prescriptions in the list.

<box type="info" header="Notes">

* If you would like to only view prescriptions that you have to consume **for the day**, use [listToday](#how-to-list-today-s-prescriptions-listtoday) instead.

* If you would like to view prescriptions that you have consumed **in the past**, use [listCompleted](#how-to-list-completed-prescriptions-listcompleted) instead.

</box>

<box type="definition" header="#### Example">

By typing `list`, you will see your **current** prescriptions displayed in the list on the right.

![result for 'Example output of list command'](images/ui/Ui-list.png)

</box>
<br></br>


### How to list today's prescriptions : `listToday`

BayMeds allows you to list all prescriptions that you need to consume **for the day**.

To list these prescriptions, type the following command.

Format:
```
listToday
```

You will then be able to see the relevant prescriptions in the list.

<box type="info" header="Notes">

* Only the medications to be taken for the day will be displayed.

* Medications to be taken are determined by their frequency and start dates.
  <box type="definition" header="Example" seamless>

  If the `<frequency>` of a particular prescription is `Daily`, it will appear in this list everyday.

  If the `<frequency>` of a particular prescription is `Weekly`, and the `<start_date>` falls on a Wednesday, it will appear in this list only on Wednesdays.
  </box>

* In this list, prescriptions will have an indicator, on the top right, to show the number of pills consumed and whether or not it has been completed for the day. In the event that `<dosage>` was not provided, the indicator will instead display the amount you have consumed for the day.

* As `<frequency>` is an optional input, prescriptions that do not have a frequency will continue to appear in this list. This is so that in the event you are unaware of the frequency, or if the frequency is irregular, you may continue to track the prescription consumption if you wish to take it on that day.

* If you would like to view prescriptions that you are **currently** taking, use [list](#how-to-list-all-current-prescriptions-list) instead.

* If you would like to view prescriptions that you have consumed **in the past**, use [listCompleted](#how-to-list-completed-prescriptions-listcompleted) instead.
</box>

<box type="definition" header="#### Example">

By typing `listToday`, you will see your prescriptions **for the day** displayed in the list on the right.

![result for 'Example output of list today command'](images/ui/Ui-listToday.png)

</box>
<br></br>

### How to list completed prescriptions : `listCompleted`

BayMeds allows you to list prescriptions that you have consumed **in the past**.

To list these prescriptions, type the following command.

Format:
```
listCompleted
```

You will then be able to see the relevant prescriptions in the list.

<box type="info" header="Notes">

* If you would like to view prescriptions that you are **currently** taking, use [list](#how-to-list-all-current-prescriptions-list) instead.

* If you would like to only view prescriptions that you have to consume **for the day**, use [listToday](#how-to-list-today-s-prescriptions-listtoday) instead.

<box type="warning" icon=":fa-solid-triangle-exclamation:" header="Important">

  The list of prescriptions shown after using this command will **not** respond to other commands such as `add`, `edit`, `take` etc. This is because this list is meant to store your consumption history for record-keeping purposes. Thus, apart from viewing these prescriptions, other interactions with this list are intentionally disabled.
  <box type="definition" header="Example" seamless>

  If you use `edit` while the list of completed prescriptions is displayed, `edit` will function based on the most recently displayed list from either [list](#how-to-list-all-current-prescriptions-list) or [listToday](#how-to-list-today-s-prescriptions-listtoday).
  </box>
</box>

</box>

<box type="definition" header="#### Example">

By typing `listCompleted`, you will see your **completed** prescriptions in the list on the right.

![result for 'Example output of list completed command'](images/ui/Ui-listCompleted.png)

</box>
<br></br>

### How to edit a prescription : `edit`

You may wish to update any of the fields of the prescription for a variety of reasons, such as a change in dosage, or an increase in stock. BayMeds allows you to edit such fields in the prescription.

To edit a prescription, type the following command.

Format:
```
edit
  <index>
  [mn/<medication_name>]
  [d/<dosage>]
  [f/<frequency>]
  [sd/<start_date>]
  [ed/<end_date>]
  [exd/<expiry_date>]
  [ts/<total_stock>]
  [n/<note>]
```

Based on the fields you specify, the corresponding fields of the index-identified prescription will be updated with the new value. Fields that are not specified will continue to remain the same.

<box type="info" header="Notes">

* The `<index>` specifies the prescription to be edited.
  <box type="definition" header="Example" seamless>

    To edit the first prescription in the list (currently being displayed), specify the `<index>` as 1.
  </box>

* At least one of the optional fields must be provided.

* Input values cannot be empty.

<box type="warning" icon=":fa-solid-triangle-exclamation:" header="Important">

If the currently displayed list shows your completed prescriptions (i.e. prescriptions that you have consumed in the past), [edit](#how-to-edit-a-prescription-edit) will not update these prescriptions. Instead, it will update based on the most recently displayed list from either [list](#how-to-list-all-current-prescriptions-list) or [listToday](#how-to-list-today-s-prescriptions-listtoday).
</box>

</box>

<box type="definition" header="#### Example 1">

```
edit 1 d/2 f/Daily
```

By typing this, you will edit the dosage and frequency of the first prescription on the list to be `2` and `Daily` respectively.

![result for 'Example output of edit command'](images/ui/Ui-edit.png)

</box>

<box type="definition" header="#### Example 2">

```
edit 2 mn/Creatine n/Red Pill
```

By typing this, you will edit the name and note of the second prescription on the list to be `Creatine` and `Red Pill` respectively.

</box>
<br></br>

### How to find prescriptions by name : `find`

With a complex medication regimen, BayMeds allows you to easily filter and find your prescriptions with names that contain the specified keyword.

To find matching prescriptions, type the following command.

Format:
```
find <keyword>...
```
<box type="info" header="Notes">

* The `...` indicates that you can include multiple `<keywords>`.
  <Box type="definition" header="Example" seamless>

  You can indicate `find Ketoconazole Shampoo`.
  </Box>

* The search is case-insensitive.
  <Box type="definition" header="Example" seamless>

  `find paracetamol` will also match `Paracetamol`.
  </Box>

* The order of the keywords does not matter.
  <Box type="definition" header="Example" seamless>

  `find Ketoconazole Shampoo` will match `Shampoo Ketoconazole`.
  </Box>

* Only the medication name is searched and matched.

* Substrings will be matched.
  <Box type="definition" header="Example" seamless>

  `find Para` will match `Paracetamol`.
  </Box>

* Prescriptions will be matched as long as any part of it matches the `<keyword>`.
  <Box type="definition" header="Example" seamless>

  `Ketorolac ophthalmic` will return both `Ketotifen ophthalmic` and `Ketorolac Tromethamine`.
  </Box>

</box>

<box type="definition" header="#### Example">

```
find Ketorolac ophthalmic
```

By typing this, the list will display both `Ketotifen ophthalmic` and `Ketorolac Tromethamine`.<br>

![result for 'find Ketorolac ophthalmic'](images/ui/Ui-find.png)

</box>
<br></br>

### How to delete a prescription : `delete`

Should you need to remove your prescriptions, BayMeds allows you to **permanently** delete a specified prescription from the list (currently being displayed).

To delete a prescription, type the following command.

Format:
```
delete <index>
```

This will cause the prescription at that index to be removed from the list (currently being displayed) and **permanently** deleted.

<box type="info" header="Notes">

* The `<index>` specifies the prescription to be deleted.
  <box type="definition" header="Example" seamless>

    To delete the first prescription in the list (currently being displayed), specify the `<index>` as 1.
  </box>
* The prescription that will be removed will be the prescription at the specified index of the currently displayed list (list or listToday).  This command will not affect the completed prescription list.

<box type="warning" icon=":fa-solid-triangle-exclamation:" header="Important">

If the currently displayed list shows your completed prescriptions (i.e. prescriptions that you have consumed in the past), [delete](#how-to-delete-a-prescription-delete) will not remove these prescriptions. Instead, it will delete based on the most recently displayed list from either [list](#how-to-list-all-current-prescriptions-list) or [listToday](#how-to-list-today-s-prescriptions-listtoday).
</box>

</box>

<box type="definition" header="#### Example">

```
delete 3
```

By typing this, you will delete the third prescription from the list.

![result for 'Example output of delete command'](images/ui/Ui-delete.png)

</box>
<br></br>

### How to take a medication : `take`

Upon consuming a medication, you may wish to indicate that you have taken 1 / 4 pills, or that you have completed your dosage for the day. BayMeds allows you to update prescriptions with the amount of dosage you have taken.

In order to do so, type the following command.

Format:
```
take
  <index>
  [d/<dosage>]
```

This will update the index-identified prescription with the specified amount of `<dosage>`.

<box type="info" header="Notes">

* The `<index>` specifies the prescription to be marked as taken.
  <box type="definition" header="Example" seamless>

    To update the first prescription in the list (currently being displayed), specify the `<index>` as 1.
  </box>

* `<dosage>` refers to the number of pills you have taken. It only accepts a numeric value.
  <box type="definition" header="Example" seamless>

  If you have taken 2 pills, specify the `<dosage>` as 2.
  </box>

* Since `<dosage>` is an optional input, it will be set as 1 if no input is given.

* The amount consumed will be increased by the specified `<dosage>`.

* The stock field of the prescription will be decreased by the specified `<dosage>`.

* If you would like to see the updated consumption count and stock, use [listToday](#how-to-list-today-s-prescriptions-listtoday).

<box type="warning" icon=":fa-solid-triangle-exclamation:" header="Important">

If the currently displayed list shows your completed prescriptions (i.e. prescriptions that you have consumed in the past), [take](#how-to-take-a-medication-take) will not update these prescriptions. Instead, it will update based on the most recently displayed list from either [list](#how-to-list-all-current-prescriptions-list) or [listToday](#how-to-list-today-s-prescriptions-listtoday).
</box>

</box>

<box type="definition" header="#### Example 1">

```
take 1
```
By typing this, you will indicate that you have taken 1 dose from the first prescription.

![result for 'Example output of take command'](images/ui/Ui-take1.png)

As shown in this example, the dosage taken is set as 1 if dosage is not specified.

</box>

<box type="definition" header="#### Example 2">

```
take 2 d/2
```

By typing this, you will take 2 doses from the second prescription.

![result for 'Example output of take command'](images/ui/Ui-take2.png)

</box>
<br></br>

### How to untake a medication : `untake`

If you have accidentally indicated that you have taken a medication, and you wish to undo this, Baymeds allows you untake the medication similar to the [take](#how-to-take-a-medication-take) feature.

Note that this feature is meant to revert errors made by the user, updating changes to the stock and consumption count.

In order to do so, type the following command.

Format:
```
untake
  <index>
  [d/<dosage>]
```

This will update the index-identified prescription with the specified amount of `<dosage>` to be removed.

<box type="info" header="Notes">

* The `<index>` specifies the prescription to be marked as taken.
  <box type="definition" header="Example" seamless>

    To update the first prescription in the list (currently being displayed), specify the `<index>` as 1.
  </box>

* `<dosage>` refers to the number of pills you want to mark as not consumed. It only accepts a numeric value.
  <box type="definition" header="Example" seamless>

  If you wish to mark 2 pills of the prescription as not consumed, specify the `<dosage>` as 2.
  </box>

* Since `<dosage>` is an optional input, it will be set as 1 if no input is given.

* The amount consumed will be decreased by the specified `<dosage>`.

* The stock field of the prescription will be increased by the specified `<dosage>`.

* If you would like to see the updated consumption count and stock, use [listToday](#how-to-list-today-s-prescriptions-listtoday).

<box type="warning" icon=":fa-solid-triangle-exclamation:" header="Important">

If the currently displayed list shows your completed prescriptions (i.e. prescriptions that you have consumed in the past), [untake](#how-to-untake-a-medication-untake) will not update these prescriptions. Instead, it will update based on the most recently displayed list from either [list](#how-to-list-all-current-prescriptions-list) or [listToday](#how-to-list-today-s-prescriptions-listtoday).
</box>

</box>

<box type="definition" header="#### Example 1">

```
untake 1
```
By typing this, you will update 1 dose from the first prescription as not consumed.

Using the same prescription list as shown in [take](#how-to-take-a-medication-take), you will get the following result:

![result for 'Example output of untake command'](images/ui/Ui-untake1.png)

As shown in this example, the dosage to untake is set as 1 if not dosage is specified.
</box>

<box type="definition" header="#### Example 2">

```
untake 2 d/2
```
By typing this, you will update 2 doses from the second prescription as not consumed.

Using the same prescription list as shown in [take](#how-to-take-a-medication-take), you will get the following result:

![result for 'Example output of untake command'](images/ui/Ui-untake2.png)

</box>
<br></br>

### How to list medications that are about to expire or low in stock : `reminder`

To reduce your risk of running out of medications or consuming expired medications, BayMeds allows you to filter and view prescriptions that are either **expiring** or **low in stock**.

To list these prescriptions, type the following command.

Format:
```
reminder
```

You will then be able to see the relevant prescriptions.

<box type="info" header="Notes">

*  Medications that are expiring within the next 7 days will be shown.

*  Medications that either have less than 10 tabs left or have 7 dosages worth left will be shown.

</box>

<box type="definition" header="#### Example">

By typing `reminder`, you will see the prescriptions that are **expiring** or **low in stock** in the list on the right.

![result for 'Example output of reminder command'](images/ui/Ui-reminder.png)

</box>
<br></br>

### How to list a prescription's conflicting drugs : `listConflicts`

As you can store drugs that conflict with the prescription, BayMeds allows you to view this list of conflicting drugs.

To view the drugs that conflict with a specific prescription, type the following command.

Format:
```
listConflicts <index>
```

You will then be able to see the conflicting drugs in the text box on the left.

<box type="info" header="Notes">

* The `<index>` specifies the prescription to show.
  <box type="definition" header="Example" seamless>

    To show the conflicting drugs of the first prescription in the list (currently being displayed), specify the `<index>` as 1.
  </box>

* This differs from the [listAllConflicts](#how-to-list-all-conflicting-drugs-listallconflicts) command. While `listAllConflicts` lists drugs that conflict with **all** your current prescriptions, `listConflicts` will only list drugs that conflict with the specified prescription.

<box type="warning" icon=":fa-solid-triangle-exclamation:" header="Important">

If the currently displayed list shows your completed prescriptions (i.e. prescriptions that you have consumed in the past), [listConflicts](#how-to-list-a-prescription-s-conflicting-drugs-listconflicts) will not follow this list. Instead, it will use the list based on the most recently displayed list from either [list](#how-to-list-all-current-prescriptions-list) or [listToday](#how-to-list-today-s-prescriptions-listtoday).
</box>

</box>

<box type="definition" header="#### Example">

```
listConflicts 2
```

By typing this, the drugs that conflict with the second prescription, Paracetamol, in the list will be shown in the text box on the left.

![result for 'Example output of listConflicts command'](images/ui/Ui-listConflicts.png)

</box>
<br></br>

### How to list all conflicting drugs : `listAllConflicts`

Should you need to see **all** the drugs that conflict with all your current prescriptions, BayMeds allows you to view all these drugs in a single list.

In order to list **all** conflicting drugs, type the following command.

Format:
```
listAllConflicts
```

You will then be able to see all conflicting drugs in the text box on the left.

<box type="info" header="Notes">

* This differs from the [listConflicts](#how-to-list-a-prescription-s-conflicting-drugs-listconflicts) command. `listAllConflicts` lists drugs that conflict with **all** your current prescriptions, while `listConflicts` will only list drugs that conflict with a specified prescription.

</box>

<box type="definition" header="#### Example">

By typing `listAllConflicts`, all drugs that conflict with **all** your current prescriptions will be shown in the text box on the left.

![result for 'Example output of listAllConflicts command'](images/ui/Ui-listAllConflicts.png)

</box>
<br></br>

### How to view help : `help`

If you need more information about how to use BayMeds, BayMeds allows you to view our [User Guide](#welcome-to-the-baymeds-user-guide).

To open the help window, type the following command.

Format:
```
help
```

You will then see a help window with the link to our [User Guide](#welcome-to-the-baymeds-user-guide) pop up.
<br></br>

### How to exit BayMeds : `exit`

To exit BayMeds, type the following command.

Format:
```
exit
```
<br></br>


--------------------------------------------------------------------------------------------------------------------

## FAQ

<box type="info" icon=":fa-solid-q:" light header="How do I save my data?">

BayMeds data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
</box>

<box type="info" icon=":fa-solid-q:" light header="How do I edit the data file?">

BayMeds data are saved automatically as a JSON file `[JAR file location]/data/prescriptionList.json and completedPrescriptionList.json`. Advanced users are welcome to update data directly by editing that data file.
<box type="warning" icon=":fa-solid-triangle-exclamation:">

**Caution:**
If your changes are in an invalid format, BayMeds will discard all data and start with an empty data file at the next run. Hence, it is recommended to make a backup of the file before editing it.
</box>
</box>

<box type="info" icon=":fa-solid-q:" light header="How do I store the size and colour of the pill?">

You may add it as a `note` when adding the prescription.
</box>

<box type="info" icon=":fa-solid-q:" light header="How do I know if I have taken the medication for the day?">

Use `listToday` to list out all medications to be taken for the day.<br>
Medications with a green label will indicate that they have been taken while those with a red label will indicate otherwise.
</box>

<box type="info" icon=":fa-solid-q:" light header="What should I do if I accidentally **add** a prescription with an end date to be in the past?">

By doing this, the prescription will now be stored as a completed prescription. Thus, it will not be shown in your list of current prescriptions.
</box>

<box type="info" icon=":fa-solid-q:" light header="What should I do if I accidentally **set** the end date of a prescription to be in the past?">

By doing this, the prescription will now be stored as a completed prescription. As mentioned in [listCompleted](#how-to-list-completed-prescriptions-listcompleted), interacting with this list is disabled by design. Thus, you may add a new prescription with the same details but corrected end date using the [add](#how-to-add-a-prescription-add) command.
</box>

<box type="info" icon=":fa-solid-q:" light header="Is my data shared with other software or organisations?">

Data is stored locally in the computer and is not stored in any external database nor shared with other third parties.<br>
We recommend locking your device before leaving it unattended to prevent others from accessing your prescription records and consumption history.
</box>

<box type="info" icon=":fa-solid-q:" light header="How do I transfer my data to another computer?">

Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BayMeds home folder.
</box>

<box type="info" icon=":fa-solid-q:" light header="What if I eat extra medication by accident?">

The application allows you to take extra dosages even if it differs from the intended frequency and dosage.<br>
This allows you to correctly and effectively track dosages that you may have mistakenly taken.
</box>

<box type="info" icon=":fa-solid-q:" light header="Can I manually reset the completion status of a medication?">

No, the completion status of a medication is automatically reset upon the beginning of a new day.<br>
If you have wrongly inputted the taking of a medication, use the [untake](#how-to-untake-a-medication-untake) command.
</box>

<box type="info" icon=":fa-solid-q:" light header="What happens if my drug has no valid details but I take it?">

You will still be allowed to take the drug, allowing you to keep track of the drugs that you have taken.
</box>
<br></br>

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
1. **When adding conflicting drugs**, if you are handling complex drugs, drugs with names longer than a word (such as "Ascorbic acid") cannot be added as drug names are space separated. We will implement a fix for this in the future.
1. **When adding conflicting drugs**, if you leave the /cfdg field empty or without alphanumeric characters, the error message will be the same as if you left the /mn parameter empty. We will implement a fix for this in the future.
1. **When adding conflicting drugs**, if you add drugs in a different case to the /cfdg field (e.g. ASPIRIN, aspirin, AspIrIN), BayMeds will add each of them as a new conflicting drug. BayMeds will not show a warning if the conflicting drug to be added does not exactly match the case of the existing prescriptions. We will implement a fix for this in the future.
1. **When taking prescriptions**, if you overdose and `take` more than the dosage stored in BayMeds, there will not be an error message to warn you. We will implement a fix for this in the future.
1. **When adding prescriptions**, if you name your prescriptions with numbers "1", "234" or "4 5 6", BayMeds will add this prescription as per normal. As these names do not properly identify the exact prescription stored in BayMeds, we will implement a fix for this in the future.
1. **When taking prescriptions**, we will implement a feature that automatically reminds you when it is time to eat your medication.
   <br></br>

--------------------------------------------------------------------------------------------------------------------

## Glossary

| Term                                 | Definition                                                                                                                                                                                                                                                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Medication**                       | A drug identified by a name.                                                                                                                                                                                                         |
| **Prescription**                     | Uniquely identified by a medication, other fields are optional.                                                                                                                                                                                                                                                                                                          |
| **Conflicting drugs**                | Drugs that should not be taken together, e.g. drugs that react with other drugs or affect the efficacy of other drugs                                                                                                                                                                                                                                                                                                     |
| **Command**                          | One of the commands in the command summary below, each command has a unique format.                                                                                                                                                                                                                                                                                    |
| **Parameters**                       | Additional information supplied to a command. Some parameters are compulsory while others are optional, please refer to the command summary below.                                                                                                                                                                                                                                                                                                |
| **Index**                            | A number indicating the position of a prescription in the list of prescriptions. The first prescription in the list has an index of 1.                                                                                                                                                                                                                                    |

--------------------------------------------------------------------------------------------------------------------

## Command summary

| How to ...                                   | Format, Example                                                                                                                                                                                                                                                                                                 |
|----------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add a prescription**                       | `add mn/<medication_name> [d/<dosage>] [f/<frequency>]  [sd/<start_date>] [ed/<end_date>] [exd/<expiry_date>] [ts/<total_stock>] [n/<note>] [cfdg/<conflicting_drugs>]`, <br> e.g. `add mn/Aspirin d/1 f/Daily sd/20/09/2023 ed/03/10/2024 exp/04/10/2024 ts/100 n/Take during meals cfdg/Panadol`              |
| **List all prescriptions**                   | `list`                                                                                                                                                                                                                                                                                                          |
| **List today's prescriptions**               | `listToday`                                                                                                                                                                                                                                                                                                     |
| **List completed prescriptions**             | `listCompleted`                                                                                                                                                                                                                                                                                                 |
| **Edit prescriptions**                       | `edit <index> [mn/<medication_name>] [d/<dosage>] [f/<frequency>] [sd/<start_date>] [ed/<end_date>] [exd/<expiry_date>] [ts/<total_stock>] [n/<note>] [cfdg/<conflicting_drugs>]`, <br> e.g. `edit 1 mn/Aspirin d/1 f/Daily sd/20/09/2023 ed/03/10/2024 exp/04/10/2024 ts/100 n/Take during meals cfdg/Panadol` |
| **Find prescriptions**                       | `find <keyword>`, <br> e.g. `find Aspirin`                                                                                                                                                                                                                                                                      |
| **Delete prescriptions**                     | `delete <index>`, <br> e.g. `delete 2`                                                                                                                                                                                                                                                                          |
| **Take prescriptions**                       | `take <index> [d/<dosage>]`, <br> e.g. `take 1 d/1`                                                                                                                                                                                                                                                             |
| **Untake prescriptions**                     | `untake <index> [d/<dosage>]`, <br> e.g. `untake 1 d/1`                                                                                                                                                                                                                                                         |
| **View expiring/low in stock prescriptions** | `reminder`                                                                                                                                                                                                                                                                                                      |
| **List conflicting prescriptions**           | `listConflicts <index>`, <br> e.g. `listConflicts 1`                                                                                                                                                                                                                                                            |
| **List all conflicting prescriptions**       | `listAllConflicts`                                                                                                                                                                                                                                                                                              |
| **Show help**                                | `help`                                                                                                                                                                                                                                                                                                          |
| **Exit the application**                     | `exit`                                                                                                                                                                                                                                                                                                          |
