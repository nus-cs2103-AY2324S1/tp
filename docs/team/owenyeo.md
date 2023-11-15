---
layout: page
title: Owen Yeo's Project Portfolio Page
---

## Project: TimetaBRO

### Overview

TimetaBRO, your ultimate companion for managing your university life, social interactions, and schedules. It allows for easy storage and perusal of your own schedule and your friends! Arrange meetings with ease, and never forget anything!

### Summary of Contributions

**Code contributed:**
[Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=owenyeo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

**Enhancements implemented:**
* **New Feature**: Added the ability to store user data, such as birthdays and schedules. It is stored in a Jackson file `userdata.json`
    * What it does: Allows the user to store their own data and schedules in TimetaBRO across sessions.
    * Justification: This feature allows users to quickly access their data and schedules should they need it, and allows for schedule comparisons to occur in other commands. Users also do not need to re-key their information every time they use the app.
    * Highlights: This enhancement required knowledge about Jackson files, and how to convert models into JSON adapted versions to save on a hard disk. It also required the implementation of a user class. It took me quite long for this.
* **New Feature**: Added ability to edit user data stored in the app itself.
    * What it does: Allows user to edit saved data straight from the app's CLI.
    * Justification: This makes the user experience a lot more smooth as the user does not need to access the `userdata.json` file to update their details.
    * Highlights: This enhancement required knowledge and tracing of the EditCommand to implement. This was relatively easier as we implemented a command in the codebase tutorial.
* **JsonAdapted classes**: Added JsonAdapted classes to translate models made by my team to Json friendly formats, and vice versa.
    * What it does: Allows user to store friends' and their own schedules and data in Json files.
    * Justification: This allows our new model, the schedule, and its related models to be able to be stored in a data.
    * Created the follow Json related classes:
        * JsonAdaptedCca
        * JsonAdaptedDatedEvent
        * JsonAdaptedModule
        * JsonAdaptedSchedule
        * JsonAdaptedUser
        * JsonUserDataStorage
        * JsonSerializableUserData
* Wrote testcases for storage
* **Modify person and AddressBook**: Disallowed duplicate phone numbers and emails. Changed notion of equality of same birthday AND same full name.
    * Justification: It is impossible for 2 different people to have the same phone number or the same email. It is also possible for people to have the same first name. Thus, adding birthday would mitigate this to a higher degree.


**Contributions to the User Guide (UG):**
* Wrote the introduction, giving the user a broad overview of the functionality of TimetaBRO in a friendly manner.
* Wrote the About section, giving the user a broad overview of the purpose of the UG.
* Wrote the Quickstart, giving example commands and links to aid the user in the use of the product.
* Updated tone throughout the UG.
* Proofread and ensured quality of UG.

**Contributions to the Developer Guide (DG):**
* Updated the diagrams in Storage and Model
* Added details about UserDataStorage
* Added implementation of EditUserCommand
* Created sequence diagrams for EditUserCommand's execution.
* Added some extensions in use cases.
* Proofread and ensured quality of DG.

**Contributions to team-based tasks:**
* Delegated work to teammates
* Created issues and assigned them to teammates
* Kept track of code deadlines
* Reviewed, approved, and merged pull requests from other tea members. E.g. #93 where I requested change due to code quality transgressions.
* Created releases for v1.4 and v1.2
* Ensured labels were correct in PRs I reviewed.

**Contributions beyond the project team:**
* Took part in Bug CATcher stress test
* Took part in PE-D
