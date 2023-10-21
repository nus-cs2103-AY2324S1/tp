---
layout: page
title: User Guide
---

NetworkBook is a **desktop contact book application**. You can use it to network with other computing students and professionals from NUS.

## Features

### <u>Category 1 - Add contact information</u>

### Create new contact: `create /name [/optional fields]`

You can use the create command to create a new contact. When creating a contact, you must provide the name field, and it's optional to provide other fields which will be added to the new contact.

Format: `create /name [name] [/phone /email /link /grad /course /spec /priority /tag]`

Example usage:
* `create /name Oreki`
* `create /name Oreki /phone +12345678 /grad AY2526-S2`

Parameters:
* `[name]`  is the name of the contact you wish to add.
* `[optional fields]` are the non-mandatory fields you can associate with the contact at the point of creation. The fields can also be added using the add command.

Valid command calls:
* `create /name Oreki`

Invalid command calls:
* Missing name: `create /name`

When adding contacts, if there is a contact with the same name, the program will inform the user that another contact with the same name already exists (not case sensitive)
The program will then give the user the option of either creating a new contact (so there are 2 contacts with the same name), deleting the old contact and adding the new one, or aborting the current add operation

Example usage:
* `Hey! We noticed another contact with the same name below:`
  * `Contact name`
  * `Phone(s) [if it exists]`
  * `Email(s) [if it exists]`
* `Would you like to:`
  * `1. Delete the old contact and add the new contact`
  * `2. Add the new contact and keep the old contact`
  * `3. Don’t add the new contact`

When the command succeeds:
* `Old contact deleted. New contact added.`
* `New contact added`
* `New contact discarded`

![remove_duplicate](images/add-remark/remove-duplicate.png)

When the command fails:
* `Invalid response, please type ‘1, 2, 3’ only`

### Add phone number to contact: `add [index] /phone`

You can add a phone number to an existing contact. A new phone number will be added to the contact's list of phone numbers, and no new contact will be created.

Format: `add [index] /phone [phone]`

Example usage:
* `add 1 /phone +6591234567`
* `add 2 /phone +11234567890`

Parameters:
* `[index]` is the index of the contact in the list.
* `[phone]` is a valid phone number (should be all numeric characters).

Valid command calls:
* `add 1 /phone 91234567`

Invalid command calls:
* Missing index: `add /phone 91234567`
* Non-numeric characters: `add 1 /phone 912a4567`

### Add email to a contact: `add [index] /email`

You can add an email to an existing contact.
A new email will be added to the contact's list of emails,
and no new contact will be created.

Format: `add [index] /email [email]`

Example usage:
* `add 1 /email nknguyentdn@gmail.com`
* `add 2 /email test@example.com`

Parameters:
* `[index]` is the index of the contact in the list.
* `[email]` is a valid email (`@` (at sign) must be present, 
and `.` (period) must be present after `@` (at sign)).

Valid command calls:
* `add 1 /email nknguyentdn@gmail.com`
* `add 2 /email test@example.com`

![add email success](images/add-remark/add-email.png)

Invalid command calls:
* Invalid email: `add 1 /email nknguyentdn@gmail`
* Missing index: `add /email nknguyentdn@gmail.com`

### Add link to a contact: `add [index] /link`

You can add a social link to an existing contact.
A new link will be added to the contact's list of links,
and no new contact will be created.

Format: `add [index] /link [link]`

Example usage:
* `add 1 /link https://nknguyenhc.github.io/`
* `add 2 /link https://www.linkedin.com/in/nguyen-khoi-nguyen-6279341a8/`

Parameters:
* `[index]` is the index of the contact in the list.
* `[link]` is a valid URL linking to a contact’s social media page.

Valid command calls:
* `add 1 /link https://nknguyenhc.github.io/`
* `add 2 /link https://www.linkedin.com/in/nguyen-khoi-nguyen-6279341a8/`

![add link success](images/add-remark/add-link.png)

Invalid command calls:
* Missing index: `add /link https://nknguyenhc.github.io/`
* Invalid link: `add /link https://nknguyenhc`

### Add course to a contact: `add [index] /course /date`

You can add a course of study to an existing contact.  A new course will be added to the contact's list of courses, and no new contact will be created.

Format: `add [index] /course [course] /date [start date] [end date]`

Example usage:
* `add 1 /course CS1101S /date 01-08-2022 07-12-2022`
* `add 2 /course CS2030S /date 02-01-2023`

Parameters:
* `index` is the index of the contact in the list.
* `course` is the contact's course of study.
* `start date` is when the contact started taking this course.
* `end date` is when the contact finished taking this course, optional (not finished reading the course).

Valid command calls:
* `add 1 /course Computer Science /date 01-08-2022 07-12-2022`

![add priority success](images/add-remark/add-course.png)

Invalid command calls:
* Invalid start date: `add 1 /course Computer Science /date 1`
* `add 1 /course Information Systems /date 30-02-2022`
* Missing start date: `add /course Computer Science`
* Invalid index: `add 20000 /course Computer Engineering /date 01-08-2022 07-12-2022`

### Add specialisation: `add [index] /spec`

You can add a specialisation to an existing contact.  A new specialisation will be added to the contact's list of specialisations, and no new contact will be created.
Specialisations are displayed in the order they are added.

Format: `add [index] /spec [specialisation]`

Example usage:
* `add 1 /spec Robotics & AI`

Parameters:
* `index` is the index of the contact.
* `spec` is the specialisation that contact is taking.

Valid command calls:
* `add 1 /spec Robotics & AI`

Invalid command calls:
* Missing specialisation: `add 1 /spec`
* Invalid index: `add 20000 /spec Robotics & AI`
* Missing index: `add /spec Robotics & AI`

### Assign priority levels: `add [index] /priority` 

You can set the priority level of a contact, 

so that you can easily filter them by priority for future reference.

Format: `add [index] /priority [priority level]`

Example usage:

- `add 1 /priority high`
- `add 10 /priority L`
- `add 23 /priority m`

Parameters:
- `[index]` is the index of the person in the list
- `[priority level]` is a word or letter representing the priority level to be assigned to the contact.

  There are three priority levels, **high, medium and low**, 

  represented by either the word itself (e.g. "high") or the first letter ("h"), 

  and they are not case-sensitive.


Valid command calls:
- `add 3 /priority high`

![add priority success](images/add-remark/add-priority.png)

Invalid command calls:

- Invalid priority: `add 1 /priority hhhhh`
- Invalid index: `add 100000 /priority h`

### Add tag to a contact: `add [index] /tag`

You can use the `tag` command to associate a custom category with a contact, 

so that you can filter them by unique criteria for easier searching.

Format: `add [index] /tag [tag name]`

Example usage:

- `add 1 /tag data analytics`
- `add 18 /tag internship`

Parameters:
- `[index]` is the index of the person in the list
- `[tag name]` is the name of the tag to associate the contact with

Valid command calls:
- `add 1 /tag data analytics`

Invalid command calls:

- Missing index: `add /tag internship`
- Invalid index: `add 100000 /tag internship`

### Add graduation date to contact: `add [index] /grad`

You can set the graduation date (to the nearest semester) of an existing contact. No new contact will be created.

Format: `add [index] /grad [grad]`

Example usage:
* `add /grad AY2223-S1 /index 1`
* `add /grad AY2627-S2 /index 2`

Parameters:
* `[index]` is the index of the contact in the list.
* `[grad]` is a valid graduation date, in the format `AYxxxx-Sy`.
    * `xxxx` is the 4-digit representation of the 2 calendar years, in the academic year e.g. `2223` for Academic Year 20`22`/20`23`. Academic year must be between AY1970/1971 to AY2069/2070 (inclusive).
    * `y` is either `1` for Semester 1, or `2` for Semester 2.

Valid command calls:
* `add /grad AY2223-S1 /index 1`

Invalid command calls:
* Missing index: `add /grad AY2223-S1`
* Invalid graduation date: `add 1 /grad 2022`


### <u>Category 2 - Edit contact details</u>

### Edit contact detail : `edit [index] /field`

You can edit contact details of existing contacts in your book.

Format: `edit [index] /[parameter name] [new parameter value]`

Example usage:
* `edit 1 /name nkn`
* `edit 1 /link https://nknguyenhc.github.io/`

Parameters:

* `[parameter name]` refers to the name of the parameters. Names can be:
  * `name`
  * `phone`
  * `course`
  * `specialisation`
  * `email`
  * `link`
  * `grad`
  * `priority`
  * `tag`
* `[index]` is the index of the contact in the list.
* `[new parameter value]` is the new value of the parameter. The new value must follow the formatting of the parameter.


Valid command calls:
* `edit 1 /name nkn`
* `edit 1 /link https://nknguyenhc.github.io/`

If the contact has multiple links:
```
There are multiple links to the contact at index 1. Which one do you wish to update?
https://nknguyenhc.github.io/ip
https://www.linkedin.com/in/nguyen-khoi-nguyen-6279341a8/
```

Assume that you input 1:

`OK, a link of the contact at index 1 (Nguyen) has been updated
from https://nknguyenhc.github.io/ip to https://nknguyenhc.github.io/.`

![update success](images/edit/edit.png)

Invalid command inputs:

* Invalid email: `update 1 /email nknguyentdn@gmail`
* Missing index: `update /email nknguyentdn@gmail.com`

### Delete a contact: `delete` 

You can remove a contact from your NetworkBook using the `delete` command, 

so that your book only contains contact details of those relevant.

Format: `delete [index]`

Example usage:

- `delete 1`
- `delete 16`

Parameters:

- `[index]` is the index of the contact in the list

Valid command inputs:
- `delete 1`

Invalid command inputs:
- Missing index: `delete`
- Invalid index: `delete 100000`


### <u>Category 3 - Find contacts</u>

### Find a contact: `find [name]`

You can use the `find` command to search for contacts by their name if you wish to quickly reference a particular contact’s details.

Format: `find [name]`

Example usage:
* `find Jack`
* `find Kai Jie`

Parameters:
* `[name]` is a term found in the names of the contacts you wish to find.

Valid command inputs:
* `find Jack`

![search success](images/find/search-success.png)

Invalid command inputs:
* Missing search term: `find`


### Sort contacts list: `sort /by /order`

You can use the `sort` command to sort your list of contacts.

Format: `sort /by [field] /order [order]`

Example usage:

* `sort /by grad /order asc`
* `sort /by name /order descending`
* `sort /by name`

Parameters

* `[field]` is the information to sort by.

    List of options:
    * `name` - Sort alphabetically by contact name
    * `grad` - Sort chronologically by graduation year
    * `priority` - Sort by priority
    * `none` - Return to default sorting

* `[order]` (optional) is the order to sort in. If not specified, defaults to ascending.

    List of options:
    * `asc`/`ascending` - Sort in ascending order
    * `desc`/`descending` - Sort in descending order

Valid command inputs:
* `sort /by grad`
* `sort /by name /order desc`

Invalid command inputs:
* Field not specified: `sort`
* Invalid field: `sort /by nickname`
* Invalid order: `sort /by name /order normal`


## Command summary

| Category | Format, Examples                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**  | `create /name [name] [/phone /email /link /grad /course /spec /priority /tag]` <br> e.g., `create /name Oreki /phone +6598765432 /grad AY2526-S2`<br><br>`add [index] /phone [phone]` <br> e.g., `add 1 /phone +6591234567`<br><br>`add [index] /email [email]` <br> e.g., `add 2 /email test@example.com`<br><br>`add [index] /link [link] [note]`<br>e.g., `add 1 /link https://nknguyenhc.github.io/ website`<br><br>`add [index] /course [course of study] /date [start date] [end date]`<br>e.g., `add 1 /course Computer Science /date 01-08-2022 07-12-2022`<br><br>`add [index] /spec [specialisation]`<br>e.g., `add 1 /spec Robotics & AI`<br><br>`add [index] /priority [priority level]`<br>e.g., `add 1 /priority high`<br><br>`add [index] /tag [tag name]`<br>e.g., `add 1 /tag friend` |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |  
| **Edit** | `update [index] /[parameter name] [new parameter value]`<br> e.g.,`update 1 /name nkn`<br><br>`delete [index]`<br>e.g., `delete 1`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Find** | `find [name]` <br> e.g., `find Ness`<br><br>`sort /by [field] /order [order]`<br>e.g., `sort /by name /order asc`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |  
