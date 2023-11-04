---
layout: page
title: User Guide
---

# ClubMemberContacts

ClubMembersContacts (CMC) is an application designed to empower EXCO members of the School of Computing's CCAs in 
efficiently managing the contacts of their members and applicants. 

In the fast-paced world of CCA leadership, time is a precious resource, and effective contact management is crucial. 
ClubMembersContacts has been tailored to cater to your specific needs, ensuring that you can streamline your 
contact-related responsibilities seamlessly. It provides a swift and convenient yet powerful
solution through a Command Line Interface (CLI) aimed at optimizing the speed and effectiveness of your contact 
management tasks. 

Here are some possible ways you can integrate CMC into your CCA:
- You can add tags to different members to delegate them roles, or any additional information.
- You can track tasks assigned to each member to track their completion.
- You can schedule interview times with your applicants.
- You can find members or applicants easily through the `find` commands. 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If this is your first time using CMC, head over to [How to use CMC's User Guide](#how-to-use-cmcs-user-guide)
to start keeping track of all your members and applicants!
</div>

* Table of Contents
  <!-- TOC -->
* [ClubMemberContacts](#clubmembercontacts)
  * [Quick start](#quick-start)
  * [Features](#features)
    * [Add Member/Applicant](#add-memberapplicant)
      * [Usage:](#usage-)
        * [Adding member:](#adding-member-)
        * [Adding applicant:](#adding-applicant-)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters-)
      * [Example of usage:](#example-of-usage-)
    * [Delete Member](#delete-member)
      * [Usage:](#usage--1)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--1)
      * [Example of usage:](#example-of-usage--1)
      * [Expected Outcome:](#expected-outcome-)
      * [If index is out of range:](#if-index-is-out-of-range-)
      * [If there are no members:](#if-there-are-no-members-)
    * [Edit Member](#edit-member)
      * [Usage:](#usage--2)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--2)
      * [Example of usage:](#example-of-usage--2)
      * [Expected Outcome:](#expected-outcome--1)
      * [If name is invalid:](#if-name-is-invalid-)
      * [If phone number is invalid:](#if-phone-number-is-invalid-)
      * [If telegram handle is invalid:](#if-telegram-handle-is-invalid-)
      * [If tag is invalid:](#if-tag-is-invalid-)
    * [Find Member(s)](#find-member--s-)
      * [Usage:](#usage--3)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--3)
      * [Example of usage:](#example-of-usage--3)
      * [Expected Outcome:](#expected-outcome--2)
      * [If unable to find member(s) with matching keyword(s)](#if-unable-to-find-member--s--with-matching-keyword--s-)
    * [View Members/Applicants](#view-membersapplicants)
      * [Usage:](#usage--4)
        * [Viewing all members:](#viewing-all-members-)
        * [Viewing all applicants:](#viewing-all-applicants-)
    * [Delete Applicant](#delete-applicant)
      * [Usage:](#usage--5)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--4)
      * [Example of usage:](#example-of-usage--4)
      * [Expected Outcome:](#expected-outcome--3)
      * [If index is out of range:](#if-index-is-out-of-range--1)
      * [If there are no applicants:](#if-there-are-no-applicants-)
    * [Edit Applicant](#edit-applicant)
      * [Usage:](#usage--6)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--5)
      * [Example of usage:](#example-of-usage--5)
      * [Expected Outcome:](#expected-outcome--4)
      * [If name is invalid:](#if-name-is-invalid--1)
      * [If phone number is invalid:](#if-phone-number-is-invalid--1)
      * [If interview date is invalid:](#if-interview-date-is-invalid-)
    * [Find Applicant(s)](#find-applicant--s-)
      * [Usage:](#usage--7)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--6)
      * [Example of usage:](#example-of-usage--6)
      * [Expected Outcome:](#expected-outcome--5)
      * [If unable to find applicant(s) with matching keyword(s)](#if-unable-to-find-applicant--s--with-matching-keyword--s-)
    * [Copy Member/Applicant](#copy-memberapplicant)
      * [Usage:](#usage--8)
        * [Copying member:](#copying-member-)
        * [Copying applicant:](#copying-applicant-)
      * [Acceptable values for parameters:](#acceptable-values-for-parameters--7)
      * [Example of usage:](#example-of-usage--7)
      * [Expected Outcome:](#expected-outcome--6)
      * [If index is out of range:](#if-index-is-out-of-range--2)
  * [Command summary](#command-summary)
<!-- TOC -->

--------------------------------------------------------------------------------------------------------------------

## How to Use CMC's User Guide

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest ClubMembersContact.jar from [here](https://github.com/AY2324S1-CS2103T-W15-3/tp/releases).

3. Locate your jar file in your computer and double-click on it to run the application. Alternatively, you can run the
   jar file from the command line using the java -jar ClubMembersContact.jar command.

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

| Action                | Format                                                                                                   | Example(s)                                                                                                                           |
|-----------------------|----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| **Add Member**        | `addm /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}`          | `addm /name Alicia /phone 92345678 /email Alicia@xyz.com /tele @Alicia`                                                              |
| **Delete Member**     | `delm {index}`                                                                                           | `delm 1`                                                                                                                             |
| **Edit Member**       | `editm {index} /name {memberName} /phone {phoneNumber} /email {email} /tele {telegramHandle} /tag {tag}` | `editm 1 /name Aliciaa /phone 12345678`  <br/>`editm 1 /email Aliciaa@xyz.com`                                                       |
| **Find Member(s)**    | `findm {keyword}`                                                                                        | `findm Alicia`<br/>`findm John 92345678`                                                                                             |
| **View Member(s)**    | `viewm`                                                                                                  | `viewm`                                                                                                                              |
| **Add Applicant**     | `adda /name {applicantName} /phone {phoneNumber}`                                                        | `adda /name Alicia /phone 92345678`                                                                                                  |
| **Delete Applicant**  | `dela {index}`                                                                                           | `dela 1`                                                                                                                             |
| **Edit Applicant**    | `edita {index} /name {applicantName} /phone {phoneNumber} /interview {interviewTime}`                    | `edita 1 /name John`<br/>`edita 1 /interview 07/01/2003 1500`<br/>`edita 1 /name Aliciaa /phone 12345678 /interview 10/12/2023 1150` |
| **Find Applicant(s)** | `finda {keyword}`                                                                                        | `finda Alicia`<br/>`finda John 92345678`                                                                                             |
| **View Applicant(s)** | `viewa`                                                                                                  | `viewa`                                                                                                                              |
