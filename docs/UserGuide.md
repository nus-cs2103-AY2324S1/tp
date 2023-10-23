---
layout: page
title: User Guide
---

**Tran$act** is a simple and easy to use transaction recorder and tracker built in Java.

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

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**

</div>
* \<?...>: Optional field
* \<description>: Any string
* \<type>: [R (for Revenue), E (for Expense)]
* \<amount>: Any number
* \<date>: In dd/mm/yy format
* \<staff>: ID of staff, an integer


### Adding transaction : `add`

Format: `add <type> <description> a/<amount> <date> <?staff>`

Examples:

- `add R Sold 1 Mug a/10 10/10/23 John`
- `add E “Paid Manufacturer” 100 10/11/23`

Success/Fail Output:

- Added revenue (Toast)
- Added expenditure (Toast)
- Error: <Error Message> (Toast)

### Removing transaction: `del`

Format: `del <id>`

Example:`del 1`

Success/Fail Output:

- Removed transaction (Toast)
- Error: <Error Message> (Toast)

### Viewing transactions : `view t`

Switches UI to transaction tabs, which shows the full list of transactions

Format: `view t` or `view transaction`

### Adding staff : `addstaf`

Format: `addstaff n/<name> p/<phone no> e/<email> a/<address> [t/<tag>]`

Success/Fail Output:

- Added staff (Toast)
- Error: <Error Message> (Toast)

### Removing staff: `delstaff`

Format: `delstaff <staff id>`

Example: `delstaff 1`

Success/Fail Output:

- Removed staff (Toast)
- Error: <Error Message> (Toast)

### Viewing staff : `view s`

Switches UI to staff tabs, which shows the full list of staff

Format: `view s` or `view staff`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

transact data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---

## FAQ

---

## Known issues

---

## Command summary

| Action                 | Format, Examples                                                 |
| ---------------------- | ---------------------------------------------------------------- |
| **Add transaction**    | `add <type> <description> a/<amount> <date> <?staff>`            |
| **Remove transaction** | `del <id>`                                                       |
| **View transaction**   | `view t` or `view transaction`                                   |
| **Add staff**          | `addstaff n/<name> p/<phone no> e/<email> a/<address> [t/<tag>]` |
| **Remove staff**       | `delstaff <staff id>`                                            |
| **View staff**         | `view s` or `view staff`                                         |

## Glossary

| Term        | Definition                                             |
| ----------- | ------------------------------------------------------ |
| Transaction | An exchange of money (e.g. Income / Expense)           |
| Transactor  | Person associated with the transaction                 |
| Income      | Money received (e.g. Product Sale)                     |
| Expense     | Costs incurred (e.g. Staff salary, cost of production) |
| Command     | An input into the text box to carry out actions        |
