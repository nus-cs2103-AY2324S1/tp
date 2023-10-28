---
layout: page
title: User Guide
---

# ClubMemberContacts

ClubMembersContacts is an app to help EXCO members of School of Computing's CCAs to manage the contacts of all their
members and applicants in a fast and convenient yet powerful way through a CLI or text-based interface for greater
speed.

* It is **written in OOP fashion**. It provides a **reasonably well-written** code base **bigger** (around 6 KLoC) than
  what students usually write in beginner-level SE modules, without being overwhelmingly big.
* It comes with a **reasonable level of user and developer documentation**.

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest clubmemberscontact.jar from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to
   run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.
   Try the following commands in order:

   1. `addm /name Alicia /phone 92345678 /email Alicia@xyz.com /tele @Alicia` : Adds a member named Alicia to the
      members list
   2. `addm /name John Doe /phone 92345677 /email Johndoe@xyz.com /tele @Johndoe` : Adds a member named John Doe to the
      members list
   3. `findm Alicia` : Only member Alicia will be listed under the members list
   4. `findm John Doe` : Only member John Doe will be listed under the members list
   5. `findm Alicia Johndoe@xyz.com` : Both members Alicia and John Doe will be listed under the members list
   6. `delm 2` : Member John Doe will be deleted from the members list


6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Add Member

Adds a new member to the members list. Tag field is optional.

#### Usage:

`addm /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

`addMember /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

#### Acceptable values for parameters:

- memberName: Only alphabetical characters, @, () are allowed
- phoneNumber: Only numbers are allowed
- email: Must follow the format of xyz@abc.wsd
- telegramHandle: Only alphanumeric characters and underscore are allowed
- tag: Only alphanumeric characters are allowed

#### Example of usage:

`addm /name Alicia /phone 92345678 /email Alicia@xyz.com /tele @Alicia`

`addMember /name Alicia /phone 92345678 /email Alicia@xyz.com /tele @Alicia`

#### Expected Outcome:

```
New member added: Alicia
```

#### If any field(s) not specified:

```
Invalid command format! 
```

#### If name is invalid:

```
Names should only contain alphanumeric characters and spaces, and it should not be blank
```

#### If phone number is invalid:

```
Phone numbers should only contain numbers, and it should be at least 3 digits long
```

#### If telegram handle is invalid:

```
Telegram handle should follow the format: @exampleHandle 
```

#### If tag is invalid:

```
Tags names should be alphanumeric
```

### Delete Member

The member at the specified index will be deleted from the members list.

#### Usage:

`delm {index}`

`deleteMember {index}`

#### Acceptable values for parameters:

- index: Only numbers are allowed, starting from 1

#### Example of usage:

`delm 1`

`deleteMember 1`

#### Expected Outcome:

```
Deleted member: Alicia
```

#### If index is out of range:

```
The member index provided is invalid
```

#### If there are no members:

```
The member index provided is invalid
```

### Edit Member

The member at the specified index will have its specified fields edited. At least 1 field to be specified, not all
fields have to be specified.

#### Usage:

`editm {index} /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

`editMember {index} /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

#### Acceptable values for parameters:

- index: Only numbers are allowed, starting from 1
- memberName: Only alphabetical characters, @, () are allowed
- phoneNumber: Only numbers are allowed
- email: Must follow the format of xyz@abc.wsd
- telegramHandle: Only alphanumeric characters and underscore are allowed
- tag: Only alphanumeric characters are allowed

#### Example of usage:

`editm 1 /name Aliciaa /phone 12345678`

`editMember 1 /name Aliciaa /phone 12345678`

#### Expected Outcome:

```
Edited member: Aliciaa
```

#### If name is invalid:

```
Names should only contain alphanumeric characters and spaces, and it should not be blank
```

#### If phone number is invalid:

```
Phone numbers should only contain numbers, and it should be at least 3 digits long
```

#### If telegram handle is invalid:

```
Telegram handle should follow the format: @exampleHandle 
```

#### If tag is invalid:

```
Tags names should be alphanumeric
```

### Find Member(s)

Find and generate a list of all existing member(s) whose information contain any of specified keyword(s). Keywords to be
separated by a spacing between 2 keywords.

#### Usage:

`findm {keyword(s)}`

`findMember {keyword(s)}`

#### Acceptable values for parameters:

- keyword(s): All alphanumerical characters are allowed

#### Example of usage:

`findm Alicia`

`findMember Alicia`

#### Expected Outcome:

```
1 member listed!
```

#### If unable to find member(s) with matching keyword(s)

```
0 members listed!
```

### View Member(s)

Generates a list of all existing member(s) in the members list.

#### Usage:

`viewm`

`viewMembers`

#### Expected Outcome:

```
Listed all members
```

### Add Applicant

Adds a new applicant to the applicants list.

#### Usage:

`adda /name {applicantName} /phone {phoneNumber}`

`addApplicant /name {applicantName} /phone {phoneNumber}`

#### Acceptable values for parameters:

- applicantName: Only alphabetical characters, @, () are allowed
- phoneNumber: Only numbers are allowed

#### Example of usage:

`adda /name Alicia /phone 92345678`

`addApplicant /name Alicia /phone 92345678`

#### Expected Outcome:

```
New applicant added: Alicia
```

#### If any field(s) not specified:

```
Invalid command format! 
```

#### If name is invalid:

```
Names should only contain alphanumeric characters and spaces, and it should not be blank
```

#### If phone number is invalid:

```
Phone numbers should only contain numbers, and it should be at least 3 digits long
```

### Delete Applicant

The applicant at the specified index will be deleted from the applicants list.

#### Usage:

`dela {index}`

`deleteApplicant {index}`

#### Acceptable values for parameters:

- index: Only numbers are allowed, starting from 1

#### Example of usage:

`dela 1`

`deleteApplicant 1`

#### Expected Outcome:

```
Deleted applicant: Alicia
```

#### If index is out of range:

```
The applicant index provided is invalid
```

#### If there are no applicants:

Generates a list of all existing applicants in the applicants list.

```
The applicant index provided is invalid
```

### Edit Applicant

The applicant at the specified index will have its specified fields edited. At least 1 field to be specified, not all
fields have to be specified.

#### Usage:

`edita {index} /name {applicantName} /phone {phoneNumber}`

`editApplicant {index} /name {applicantName} /phone {phoneNumber}`

#### Acceptable values for parameters:

- index: Only numbers are allowed, starting from 1
- applicantName: Only alphabetical characters, @, () are allowed
- phoneNumber: Only numbers are allowed

#### Example of usage:

`edita 1 /name Aliciaa /phone 12345678`

`editApplicant 1 /name Aliciaa /phone 12345678`

#### Expected Outcome:

```
Edited applicant: Aliciaa
```

#### If name is invalid:

```
Names should only contain alphanumeric characters and spaces, and it should not be blank
```

#### If phone number is invalid:

```
Phone numbers should only contain numbers, and it should be at least 3 digits long
```

### Find Applicant(s)

Find and generate a list of all existing applicant(s) whose information contain any of specified keyword(s). Keywords to
be separated by a spacing between 2 keywords.

#### Usage:

`finda {keyword(s)}`

`findApplicant {keyword(s)}`

#### Acceptable values for parameters:

- keyword(s): All alphanumerical characters are allowed

#### Example of usage:

`finda Alicia`

`findApplicant Alicia`

#### Expected Outcome:

```
1 applicant listed!
```

#### If unable to find applicant(s) with matching keyword(s)

```
0 applicants listed!
```

### View Applicant(s)

Generates a list of all existing applicant(s) in the applicants list.

#### Usage:

`viewa`

`viewApplicants`

#### Expected Outcome:

```
Listed all applicants
```

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                | Format                                                                                                   | Example(s)                                                                     |
|-----------------------|----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| **Add Member**        | `addm /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`          | `addm /name Alicia /phone 92345678 /email Alicia@xyz.com /tele @Alicia`        |
| **Delete Member**     | `delm {index}`                                                                                           | `delm 1`                                                                       |
| **Edit Member**       | `editm {index} /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}` | `editm 1 /name Aliciaa /phone 12345678`  <br/>`editm 1 /email Aliciaa@xyz.com` |
| **Find Member(s)**    | `findm {keyword}`                                                                                        | `findm Alicia`<br/>`findm John 92345678`                                       |
| **View Member(s)**    | `viewm`                                                                                                  | `viewm`                                                                        |
| **Add Applicant**     | `adda /name {applicantName} /phone {phoneNumber}`                                                        | `adda /name Alicia /phone 92345678`                                            |
| **Delete Applicant**  | `dela {index}`                                                                                           | `dela 1`                                                                       |
| **Edit Applicant**    | `edita {index} /name {applicantName} /phone {phoneNumber}`                                               | `edita 1 /name John`<br/>`edita 1 /name Aliciaa /phone 12345678`               |
| **Find Applicant(s)** | `finda {keyword}`                                                                                        | `finda Alicia`<br/>`finda John 92345678`                                       |
| **View Applicant(s)** | `viewa`                                                                                                  | `viewa`                                                                        |
