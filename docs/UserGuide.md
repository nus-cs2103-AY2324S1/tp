---
layout: page
title: User Guide
---
# Tran$act User Guide

Welcome to **Tran$act**, your solution for effortless transaction recording and management.
Tran$act is a desktop application designed to cater the needs of startup or small scale
retailers looking for a cheap and efficient way to record daily transactions.

Here's an overview of our main features:
- **Transaction Management** : Add, Edit and Delete Income and Expenses
- **Comprehensive Dashboard**: An instant snapshot of your monthly financial situation 
- **Data Integration**: Export/Import financial statements in CSV format
- **Address Book**: Add people to your address book and link them to transactions 

In addition, Tran$act's **Command Line Interface (CLI)** functionality will allow you to unlock
greater efficiency as you type faster, while still having the benefits of a Graphical User Interface (GUI)

This user guide will walk you through how to get started with our app and dive into our features
to help you streamline your accounting 

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `transact.jar` from [here](https://github.com/AY2324S1-CS2103T-W13-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Tran$act.

4. Double click `transact.jar` to start the app. A GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)

>**❗If the above does not work, try this method:**
>
>Open a command terminal, `cd` into the folder you put the jar file in, type `java -jar transact.jar` and press Enter to run the application.<br>

5. The app contains some sample data. Type `view s` to switch to the _staff list_.

>Alternatively, you can click on the Staff List/Transaction tab at the top of the window to switch to the staff/transaction
> view respectively.

6. Let's add a new staff member: type `addstaff n/Isaac p/92345678 e/isaac@gmail.com a/Blk 456, Pasir Ris St 32 t/marketing` 
   and press Enter.

   This creates a new staff member called Isaac with the respective phone number, email and address, and adds an
   optional tag with the name 'marketing'.
 
7. Let's add a new transaction: type `add ty/E d/Flyer printing amt/100 on/23/10/2023 s/7` and press Enter.

   Notice that the app automatically switches to the _transaction list_, and your newly added transaction appears
   at the bottom of the list.

>**Wondering where the _s/7_ came from?**
>
> In step 6, we added a new staff member Isaac, and the app automatically assigned them with an ID of 7, which you can
> see in the staff list.

8. Congratulations, you have successfully added a staff member and a transaction! Refer to the [Features](#features) 
   below for the full details of each command. Enjoy transacting with Tran$act!

---

## Features

Tran$act offers a range of features designed to make transaction recording and management a seamless process. These features are tailored to the needs of accountants and finance professionals in small businesses, allowing for efficient data entry and financial reporting. Here's an overview of the key features:


**1. Adding Transactions**

Tran$act allows you to add transactions with ease. Specify the transaction type, description, amount, date, and optionally, the associated person. The system validates your input data and records the transaction in the database.

**2. Removing Transactions**

Remove transactions from your records with ease. Simply select the transaction you wish to delete, and the system will confirm the removal before removing it from the database.

**3. Viewing All Transactions**

View a comprehensive list of all recorded transactions for reference. This list provides an overview of all financial activities in one place.

**4. Editing Transactions**

Need to make corrections or updates to transaction records? Tran$act allows you to edit transaction details, ensuring your records are accurate.

**5. Filtering, Sorting Transactions**

Need to find transactions meeting a specific criteria, and order them? Tran$act allows you to filter transactions by date, amount and person, and sort transactions by date and amount.

**5. Dashboard Display**

Upon opening the app, you'll be greeted with a clear and concise dashboard. The dashboard displays essential financial information, including total income, total expenses, net profit for the selected period.

**6. Export of transactions in csv format**

For those who need to share data with stakeholders, Tran$act allows you to generate CSV report.

**7. Address Book**

Import staff lists into the address book for quick access to contact information. You can also add, edit, and remove people from the address book as needed.

---
## Usage

<div markdown="block" class="alert alert-info">

**Notes about the command format:**

* []: Optional field
* \<description>: Any string
* \<type>: R (for Revenue), or E (for Expense)
* \<amount>: Any number
* \<date>: In dd/mm/yy format
* \<staff ID>: ID of staff, an integer
* \<sort type>: asc (ascending) or desc (descending)
* ...: Multiple entries allowed, separated by a space
</div>


### Adding transaction : `add`

Format: `add ty/<type> d/<description> a/<amount> on/<date> [s/<staff ID>]`

Examples:

- `add ty/R d/Sold 1 Mug a/10 on/10/10/23 s/1`
- `add ty/E d/Paid Manufacturer a/100 on/10/11/23`

Success/Fail Output:

- Added revenue
- Added expenditure
- Error: <Error Message>

### Removing transaction: `del`

Format: `del <id>`

Example:`del 1`

Success/Fail Output:

- Removed transaction
- Error: <Error Message>

### Sorting transactions : `sort`

Format: `sort [date/<sort type>] [amount/<sort type>]`

* At least one parameter must be present
* If both parameters are present, the order matters
  * Transactions will be sorted by the first parameter; if there are ties, the second parameter is used to tiebreak

### Filtering transactions : `filter`

Format: `filter [has/<keywords...>] [after/<date>] [before/<date>] [more/<amount>] [less/<amount>] [by/<staff ID>]`

* At least one parameter must be present
* If multiple parameters are present, only transactions matching all parameters will be shown
* The order of parameters does not matter
* `has` will match descriptions containing the keyword(s)
  * The search is case-insensitive. e.g `hans` will match `Hans`
  * The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
  * Only the description is searched.
  * Only full words will be matched e.g. `Han` will not match `Hans`
* `after` will match dates on or after the given date
* `before` will match dates on or before the given date
* `more` will match amounts greater than or equal to the given amount
* `less` will match amounts less than or equal to the given amount
* `by` will match transactions with the given staff ID

### Viewing transactions : `view t`

Switches UI to transaction tab, which shows the full list of transactions

Format: `view t` or `view transaction`

### Adding staff : `addstaff`

Format: `addstaff n/<name> p/<phone no> e/<email> a/<address> [t/<tag>...]`

Success/Fail Output:

- Added staff
- Error: <Error Message>

### Removing staff: `delstaff`

Format: `delstaff <staff id>`

Example: `delstaff 1`

Success/Fail Output:

- Removed staff
- Error: <Error Message>

### Locating staff by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find keyword [keywords...]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`

### Viewing staff : `view s`

Switches UI to staff tab, which shows the full list of staff

Format: `view s` or `view staff`

### Viewing overview : `view o`

Switches UI to overview tab, which shows the overview of transactions

Format: `view o` or `view overview`

### Clearing the output : `clear`

Clears the output of the previous command.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Tran$act's data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


## Navigating the GUI

Tran$act's GUI is simple and gives you access to all of its features with just one click.

This should be what you see when you open Tran$act for the first time:
![Ui](images/Transact_UI.png)

The upper menu bar contains two tabs:
1. File — This tab contains actions like saving data and loading data.
2. Help — This tab contains more information about Tran$act like, for example, this User Guide.

The lower menu bar contains three tabs:
1. Overview — This tab shows you a monthly summary of your finances based on thr transactions you have entered. Although it's empty right now, when you have added a few transactions it will begin to show graphs.
2. Transactions — This tab shows you the transactions you have entered in a row-by-row format.
3. Addressbook — This tab shows you the persons or parties you have entered in a list format.

The input field below this with the grey text `Enter command here...` is where you can enter your commands.

## FAQ
1. How to check my Java Version?
* Open a Terminal/ Command Prompt and type java --version. If you do not have Java installed, you can check the instructions [here](https://nus-cs2103-ay2223s2.github.io/website/admin/programmingLanguages.html)
2. Can I do further analysis with the transaction data
* The purpose of Tran$act is to keep accounting and analysis simple to improve efficiency. Nonetheless, you can export the data as a CSV file and use the data in third party apps such as excel and do further analysis if needed.
3. How can I launch Tran$act  if clicking on the JAR file does not work?
* Open a command terminal, `cd` into the folder you put the jar file in, type `java -jar transact.jar` and press Enter to run the application. 
---


## Command summary

| Action                  | Format, Examples                                                                                            |
|-------------------------|-------------------------------------------------------------------------------------------------------------|
| **Add transaction**     | `add ty/<type> d/<description> a/<amount> on/<date> [s/<staff ID>]`                                         |
| **Remove transaction**  | `del <id>`                                                                                                  |
| **Sort transactions**   | `sort [date/<sort type>] [amount/<sort type>]`                                                              |
| **Filter transactions** | `filter [has/<keywords...>] [after/<date>] [before/<date>] [more/<amount>] [less/<amount>] [by/<staff ID>]` |
| **View transaction**    | `view t` or `view transaction`                                                                              |
| **Add staff**           | `addstaff n/<name> p/<phone no> e/<email> a/<address> [t/<tag>...]`                                         |
| **Remove staff**        | `delstaff <staff ID>`                                                                                       |
| **Find staff**          | `find <keyword> [more keywords...]`                                                                         |
| **View staff**          | `view s` or `view staff`                                                                                    |
| **View overview**       | `view o` or `view overview`                                                                                 |
| **Clear output**        | `clear`                                                                                                     |
| **Exit**                | `exit`                                                                                                      |

## Glossary

| Term        | Definition                                             |
| ----------- | ------------------------------------------------------ |
| Transaction | An exchange of money (e.g. Income / Expense)           |
| Transactor  | Person associated with the transaction                 |
| Income      | Money received (e.g. Product Sale)                     |
| Expense     | Costs incurred (e.g. Staff salary, cost of production) |
| Command     | An input into the text box to carry out actions        |
