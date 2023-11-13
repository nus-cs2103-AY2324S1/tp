---
layout: page
title: Nicholas Lee's Project Portfolio Page
---

### Project: UniMate

UniMate is a desktop address book and calendar infused in one application intended for National University of Singapore (NUS) students to save group mate's contacts and sync calendars to schedule classes and group project meetings. The user interacts with it using primarily the Command Line Interface (CLI), but can choose to interact with the GUI using a mouse. Its GUI created with JavaFX, and the entire project is written in Java, and has about 25 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added the ability to sort address book contacts by a specified attribute

  - What it does: allows the user to sort address book contacts by a specified attribute in a certain order
  - Justification: This feature improves the product significantly because a user may have many contacts in the address book and sorting it by either ascending or descending order will enable the user to find the contact more conveniently.
  - Highlights: This enhancement does not affect existing commands and commands to be added to the future. However, the function inherently changes the way the address book data is being stored, and design alternatives may be required should the storage system change. The implementation was challenging as working around the way the address book is designed and we cannot alter the observable list directly.

- **New Feature**: Added the ability to edit contact's calendar events

  - What it does: allows the user to sort address book contacts by a specified attribute in either
  - Justification: This feature improves the product significantly because a user may want to reschedule a meeting with a contact and the edit function serves as a convenient way for the user to change the details of the event without having to delete and add a new event.
  - Highlights: This enhancement affects existing attributes to be added to contact's events in the future. It requires an in-depth analysis of design alternatives should the design of contact's events be altered. The implementation too was challenging as it required an additional temporary class to store all the edited changes.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nicrandomlee&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

- **Project management**:

  - Contributed to the releases `v1.2` - `v1.4` (3 releases) on GitHub

- **Enhancements to existing features**:

  - Added two new features (Pull requests [\#40](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/40), [\#51](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/51), [\#78](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/78))

- **Documentation**:

  - User Guide:
    - Added documentation for the features `sort` and `editContactEvents` [\#66](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/66)
  - Developer Guide:
    - Added implementation details and sequence diagrams for `sort` and `editContactEvents` [\#167](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/167)

- **Community**:

  - PRs reviewed (with non-trivial review comments): [\#152](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/152), [\#161](https://github.com/AY2324S1-CS2103-F13-4/tp/pull/161)
  - Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/nicrandomlee/ped/issues/1), [2](https://github.com/nicrandomlee/ped/issues/2), [3](https://github.com/nicrandomlee/ped/issues/3), [4](https://github.com/nicrandomlee/ped/issues/4), [5](https://github.com/nicrandomlee/ped/issues/5), [6](https://github.com/nicrandomlee/ped/issues/6), [7](https://github.com/nicrandomlee/ped/issues/7), [8](https://github.com/nicrandomlee/ped/issues/8), [9](https://github.com/nicrandomlee/ped/issues/9), [10](https://github.com/nicrandomlee/ped/issues/10))
  - Posted in forum discussion ([1](https://github.com/nus-cs2103-AY2324S1/forum/issues/217))

