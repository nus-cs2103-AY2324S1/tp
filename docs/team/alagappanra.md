---
layout: page
title: Alagappan Ramanathan's Project Portfolio Page
---

### Project: LinkMeIn
LinkMeIn - Is a desktop address book application
used to track student's job applications to companies.
The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java, and has about
~10 kLoC.

Given below are my contributions to the project.

## **Code contributed**:
The code that I have contributed can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=AlagappanRa&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

## Enhancements implemented:
**Duplicate checking algorithm for the add and edit command**

**What it does:**<br>
The duplicate checking algorithm checks if the company that the user is trying to add or edit is already present in the
company list. If the company is already present in the company list, a specific error message will be raised to the 
user, informing them of the corrective action they need to take. 

**Justification:**<br>
This feature was implemented to inform the user of an accidental duplicate company that they 
tried to add or edit. The associated error message covers 4 different cases, each of which 
guides the user towards a specific corrective action. These cases are filtered list view, 
original list view, partial match and complete match of fields.

**Input validation method for all fields**

**What it does:**<br> 
- Company name, recruiter name, role, phone number, email, application status and priority fields are validated to 
ensure that they are not empty and that they follow the correct regex.
- Implemented specific REGEX to ensure that the phone number and email fields are in the correct format.

**Justification:**<br>
Input validation allows us to validate duplicates, while allowing the user to see whatever they typed. This allows
for flexibility in the user interface, while ensuring that the user does not enter invalid inputs.

**Test cases for the duplicate checking algorithm**<br>
Added close to 990 lines of test cases for the duplicate checking algorithm and the input validation fields
to ensure behaviour was as expected.

## General
**Bug Fixes**:
* Fixed a [bug](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/232) where the duplicate detection algorithm gave a wrong output
* Fixed a bug where `Software Engineer 2` and `Software Engineer` were considered equals
* Sequence diagrams and Activity Diagrams [bug](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/322) fixes

**Team Contributions**:
* PR's reviewed non-trivially
  [#325](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/325),
  [#287](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/287),
  [#236](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/236)

**Forum Enthusiast**
* Resolved other classmate's code related issues (examples:
  [#104](https://github.com/nus-cs2103-AY2324S1/forum/issues/104#issuecomment-1706219015),
  [#154](https://github.com/nus-cs2103-AY2324S1/forum/issues/154#issuecomment-1726817514),
  [#170](https://github.com/nus-cs2103-AY2324S1/forum/issues/170#issuecomment-1730530807),
  [#249](https://github.com/nus-cs2103-AY2324S1/forum/issues/249#issuecomment-1759469324))
* Resolved a bug in the [JavaFX tutorial](https://se-education.org/guides/tutorials/javaFxPart1.html)
  regarding a JavaFX MAC-OS dependency issue. Recommended a modification to build gradle to solve the issue
  [#159](https://github.com/nus-cs2103-AY2324S1/forum/issues/159#issuecomment-1728708305)
* Questions asked in the forum (examples:
  [#434](https://github.com/nus-cs2103-AY2324S1/forum/issues/434),
  [#339](https://github.com/nus-cs2103-AY2324S1/forum/issues/339),
  [#411](https://github.com/nus-cs2103-AY2324S1/forum/issues/411),
  [#336](https://github.com/nus-cs2103-AY2324S1/forum/issues/336))

**PE-D Testing**
* Reported [10 bugs, of which 9 out of 10 were triaged](https://github.com/AY2324S1-CS2103T-T15-1/tp/issues?q=is%3Aissue+is%3Aclosed+AlagappanRa)
* Breakdown: 2 High, 2 Medium, 3 Low, 2 Very Low, 1 Rejected

### Contributions to the UG:
The following sections from the UG were contributed by me:
1. **Features**:
   - Duplicate detection algorithm: Added description, examples for the various errors that can arise 
2. **Added FAQ**:
   - Added questions 1 and 2 to the FAQ section.
3. **Introduction section**
   - Added a section on the motivation behind LinkMeIn
   - Quick summary of features
4. **Code block text size re-adjustment**
   - Adjusted the code block text size to be smaller for better readability.

### Contributions to the DG:
The following sections from the DG were contributed by me:
- Added activity & sequence diagrams for the duplicate checking algorithm
- Planned enhancements
  - Remove the alphanumeric checks for the company name, recruiter name and role fields
  - Expanding acceptance for phone number field
- Effort section


