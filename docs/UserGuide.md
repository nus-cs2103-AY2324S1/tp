---
layout: page
title: User Guide
---
# Tra$act User Guide

Welcome to **Tran$act**, your solution for effortless transaction recording and management.
Transact is a desktop application designed to cater the needs of startup or small scale
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

1. Ensure you have Java `11` or above installed in your Computer.<br><br>

2. Download the latest `transact.jar` from [here](https://github.com/AY2324S1-CS2103T-W13-3/tp/releases).<br><br>

3. Copy the file to the folder you want to use as the _home folder_ for Tran$act.<br><br>

4. Double click `transact.jar` to start the app. A GUI similar to the below should appear in a few seconds.<br><br>
   ![Ui](images/Ui.png)

>**❗If the above does not work, try this method:**
>
>Open a command terminal, `cd` into the folder you put the jar file in, type `java -jar transact.jar` and press Enter to run the application.<br>

5. The app contains some sample data. Type `view s` to switch to the _staff list_.

>Alternatively, you can click on the Staff List/Transaction tab at the top of the window to switch to the staff/transaction
> view respectively.

6. Let's add a new staff member: type `addstaff n/Isaac p/92345678 e/isaac@gmail.com a/Blk 456, Pasir Ris St 32 t/marketing` 
   and press Enter.<br/><br/>

   This creates a new staff member called Isaac with the respective phone number, email and address, and adds an
   optional tag with the name 'marketing'<br><br>
 
7. Let's add a new transaction: type `add ty/E d/Flyer printing amt/100 on/23/10/2023 s/7` and press Enter.<br><br>

   Notice that
   the app automatically switches to the _transaction list_, and your newly added transaction appears at the bottom of
   the list.

>**Wondering where the s/7 came from?**
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

* \<?...>: Optional field
* \<description>: Any string
* \<type>: [R (for Revenue), E (for Expense)]
* \<amount>: Any number
* \<date>: In dd/mm/yy format
* \<staff>: ID of staff, an integer
* []: Multiple entries allowed, separated by a space
</div>


### Adding transaction : `add`

Format: `add ty/<type> d/<description> a/<amount> on/<date> s/<?staff>`

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

### Viewing transactions : `view t`

Switches UI to transaction tab, which shows the full list of transactions

Format: `view t` or `view transaction`

### Adding staff : `addstaff`

Format: `addstaff n/<name> p/<phone no> e/<email> a/<address> [t/<tag>]`

Success/Fail Output:

- Added staff
- Error: <Error Message>

### Removing staff: `delstaff`

Format: `delstaff <staff id>`

Example: `delstaff 1`

Success/Fail Output:

- Removed staff
- Error: <Error Message>

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

transact data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


## Navigating the GUI

Tran$act's GUI is simple and gives you access to all of its features with just one click.

This should be what you see when you open Transact for the first time:
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

| Action                  | Format, Examples                                                  |
|-------------------------|-------------------------------------------------------------------|
| **Add transaction**     | `add ty/<type> d/<description> a/<amount> on/<date> s/<?staff>`   |
| **Remove transaction**  | `del <id>`                                                        |
| **View transaction**    | `view t` or `view transaction`                                    |
| **Add staff**           | `addstaff n/<name> p/<phone no> e/<email> a/<address> [t/<?tag>]` |
| **Remove staff**        | `delstaff <staff id>`                                             |
| **View staff**          | `view s` or `view staff`                                          |
| **View overview**       | `view o` or `view overview`                                       |
| **Clear output**        | `clear`                                                           |
| **Exit**                | `exit`                                                            |

## Glossary

| Term        | Definition                                             |
| ----------- | ------------------------------------------------------ |
| Transaction | An exchange of money (e.g. Income / Expense)           |
| Transactor  | Person associated with the transaction                 |
| Income      | Money received (e.g. Product Sale)                     |
| Expense     | Costs incurred (e.g. Staff salary, cost of production) |
| Command     | An input into the text box to carry out actions        |
