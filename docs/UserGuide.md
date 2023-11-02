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

3. Locate your jar file in your computer and double click on it to run the application. Alternatively, you can run the
   jar file from the command line using the java -jar clubmemberscontact.jar command.

4. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
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

<div markdown="span" class="alert alert-primary">

**Note:** Many of the commands below have _aliases_, or short-form versions that make them easier
to type. For example, the command `addMember` can be typed as `addm`. Usages of the aliases are documented below
alongside the full command word, and all aliases can be used interchangeably with the full command word.

</div>

### Add Member/Applicant

You can easily add a new member/applicant to the members/applicants list.

- Use `addMember` or `addm` to use add a member
- Use `addApplicant` or `adda` to use add an applicant

#### Usage:

##### Adding member:

`addMember /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

`addm /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

##### Adding applicant:

`addApplicant /name {applicantName} /phone {phoneNumber}`

`adda /name {applicantName} /phone {phoneNumber}`

#### Acceptable values for parameters:

- `name`: Only alphabetical characters, @, () are allowed
- `phoneNumber`: Only numbers are allowed
- `email`: Must follow the format of xyz@abc.wsd
- `telegramHandle`: Only alphanumeric characters and underscore are allowed
- `tag`: Only alphanumeric characters are allowed, tags are optional

#### Example of usage:

`addm /name John Doe /phone 92345678 /email johndoe@xyz.com /tele @johndoe /tag WelfareHead`

`adda /name John Doe /phone 92345678`

### Delete Member

The member at the specified index will be deleted from the members list.

#### Usage:

`deleteMember {index}`

`delm {index}`

#### Acceptable values for parameters:

- `index`: Only numbers are allowed, starting from 1

#### Example of usage:

`deleteMember 1`

`delm 1`

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

`editMember {index} /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

`editm {index} /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`

#### Acceptable values for parameters:

- `index`: Only numbers are allowed, starting from 1
- `memberName`: Only alphabetical characters, @, () are allowed
- `phoneNumber`: Only numbers are allowed
- `email`: Must follow the format of xyz@abc.wsd
- `telegramHandle`: Only alphanumeric characters and underscore are allowed
- `tag`: Only alphanumeric characters are allowed

#### Example of usage:

`editMember 1 /name Aliciaa /phone 12345678`

`editm 1 /name Aliciaa /phone 12345678`

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

`findMember {keyword(s)}`

`findm {keyword(s)}`

#### Acceptable values for parameters:

- `keyword(s)`: All alphanumerical characters are allowed

#### Example of usage:

`findMember Alicia`

`findm Alicia`

#### Expected Outcome:

```
1 member listed!
```

#### If unable to find member(s) with matching keyword(s)

```
0 members listed!
```

### View Members/Applicants

Generates a list of all existing member(s)/applicant(s). An example of where you might want to use this command is if
you want to go back to viewing all members after a search with `findMember`.

#### Usage:

##### Viewing all members:

`viewMembers`

`viewm`

##### Viewing all applicants:

`viewApplicants`

`viewa`

[//]: # (#### Expected Outcome:)

[//]: # ()

[//]: # (##### Members:)

[//]: # (```)

[//]: # (Listed all members)

[//]: # (```)

[//]: # (##### Applicants:)

[//]: # (```)

[//]: # (Listed all applicants)

[//]: # (```)

### Delete Applicant

The applicant at the specified index will be deleted from the applicants list.

#### Usage:

`deleteApplicant {index}`

`dela {index}`

#### Acceptable values for parameters:

- `index`: Only numbers are allowed, starting from 1

#### Example of usage:

`deleteApplicant 1`

`dela 1`

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

`editApplicant {index} /name {applicantName} /phone {phoneNumber} /interview {interviewTime}`

`edita {index} /name {applicantName} /phone {phoneNumber} /interview {interviewTime}`

#### Acceptable values for parameters:

- `index`: Only numbers are allowed, starting from 1
- `applicantName`: Only alphabetical characters, @, () are allowed
- `phoneNumber`: Only numbers are allowed
- `interviewTime` : Only dates in the format of "DD/MM/YYYY HhMm" are allowed. To remove an interview time from an 
applicant, 'cancel' is also allowed.

#### Example of usage:

`editApplicant 1 /name Alicia /phone 12345678 /interivew 12/10/2023 1400`

`edita 1 /name Alicia /phone 12345678 /interview 12/10/2023 1400`

#### Expected Outcome:

```
Edited applicant: Alicia
```

#### If name is invalid:

```
Names should only contain alphanumeric characters and spaces, and it should not be blank
```

#### If phone number is invalid:

```
Phone numbers should only contain numbers, and it should be at least 3 digits long
```

#### If interview date is invalid:

```
Interview time should be in the format of DD/MM/YYYY HHmm. 
To cancel the interview, enter 'cancel' (case sensitive)
```

### Find Applicant(s)

Find and generate a list of all existing applicant(s) whose information contain any of specified keyword(s). Keywords to
be separated by a spacing between 2 keywords.

#### Usage:

`findApplicant {keyword(s)}`

`finda {keyword(s)}`

#### Acceptable values for parameters:

- `keyword(s)`: All alphanumerical characters are allowed

#### Example of usage:

`findApplicant Alicia`

`finda Alicia`

#### Expected Outcome:

```
1 applicant listed!
```

#### If unable to find applicant(s) with matching keyword(s)

```
0 applicants listed!
```

### Copy Member/Applicant

Copies the details of the member/applicant at the specified index to the clipboard.

#### Usage:

##### Copying member:

`copyMember {index}`

`cpm {index}`

##### Copying applicant:

`copyApplicant {index}`

`cpa {index}`

#### Acceptable values for parameters:

- `index`: Only numbers are allowed, starting from 1

#### Example of usage:

`copyMember 1`

`cpm 1`

#### Expected Outcome:

```
Copied details of member to clipboard:
Name: Alex Yeoh
Phone: 87438807
Email: alexyeoh@example.com
Telegram: @alexyeoh
Tags: [Friend]
Tasks: [Finish Proposal]
```

#### If index is out of range:

```
The member index provided is invalid
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
