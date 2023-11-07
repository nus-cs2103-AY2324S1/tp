---
  layout: default.md
  title: "Lim Jun Xian's Project Portfolio Page"
---

# Project: CampusConnect

**CampusConnect** is a desktop application built for **NUS students** living on campus to help them **stay organised**, **stay connected**, and **make the on-campus experience at NUS stress-free**. The user interacts with it using a CLI, and it has a GUI created with JavaFX. 

Given below are my contributions to the project.

# Summary of Contributions

## Code Contributed

Refer to this [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=limjunxian1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=limjunxian1&tabRepo=AY2324S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Enhancements Implemented

* **New Feature**: Implemented the `addalt` command; it supports the addition of alternative information of existing contacts in CampusConnect. (Pull request [#46](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/46))
  * What it does: It allows the user to add extra information to existing contacts, in particular telegram, linkedin, secondary email and birthday.
  * Justification: Basic contact information when a contact is first added in CampusConnect may not be sufficient as with the advent of technology, people are engaged in different platforms to communicate with others besides messaging through contact number or email and mailing letters using address of contact. This feature helps the users to store more contact information, in particular, their telegram and linkedin usernames (or even another email) so that they can stay better connected to their contacts through these communication platforms.
  * Credits: Existing AB3 implementation for `edit` command gave me the inspiration of how I can move forward to implement this feature.
  
* **Enhancements to existing features**: Improved the `edit` command; it supports the editing of other `Person` attributes added in CampusConnect. (Pull requests [#75](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/75), [#130](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/130))
  * What it does: It allows the user to edit information of existing contacts only if user
    1. Is not editing an empty field of alternative information (otherwise, it defeats the purpose of having the `addalt` command feature).
    2. Is not editing the 2 existing emails such that they become the same after the edit.
    3. Makes edits that results in at least a change in one of the contact's attributes.
  * Justification: This feature enables users to correctly utilize the `edit` command as per the definition of edit in layman terms; it should result in a change after editing existing values.
  
* **Enhancement to existing features**: Improved the GUI of `PersonCard`. (Pull request [#114](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/114))
  * Added labels to each of the attributes in the `PersonCard` so that users are able to distinguish what the values are referring to.

* **Bug fixing**: Updated specificity of error messages displayed for commands that has index as a parameter and disallowing `tag` to have duplicate values. (Pull request [#208](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/208))
  
## Contributions to the User Guide
  * Added introduction. (Pull request [#22](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/22))
  * Added documentation for the `add`, `addalt`, `delete` and `list` commands. (Pull request [#140](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/140))
  * Updated planned enhancements section and front matters (quick start and start of features section). (Pull request [#140](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/140))
  * Updated command summary. (Pull requests [#140](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/140), [#156](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/156))
  * Cleaned up errors before PED (Pull requests [#156](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/156), [#158](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/158))
  * Improved based on suggestion from PED, standardization of Table of Contents to have parallel language and summarized visual components as per CS2101 tutor feedback (Pull request [#209](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/209))

## Contributions to the Developer Guide
  * Updated NFRs and User Stories (Pull request [#33](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/33))
  * Added implementation details for `addalt` and `edit` command. (Pull request [#105](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/105))
  * Added prefix summary (Pull request [#105](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/105))
  * Updated Use Cases (Pull requests [#33](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/33), [#143](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/143))
  * Updated Person Model (Pull request [#143](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/143))

## Contributions to Team-Based Tasks
  * Added introduction in UserGuide.md (Pull request [#22](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/22))
  * Updated NFRs and User Stories in DeveloperGuide.md (Pull request [#33](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/33))
  * Update structure of README.md for better clarity (Pull request [#105](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/105))
  * Managed release [v1.3.trial](https://github.com/AY2324S1-CS2103T-T13-2/tp/releases/tag/v1.3.trial)

## Review / Mentoring Contributions
  * Pull Requests reviewed: 23 [(GitHub)](https://github.com/AY2324S1-CS2103T-T13-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  * Pull Request reviewed with non-trivial comments: [#56](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/56), [#79](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/79), [#128](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/128), [#150](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/150), [#152](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/152)

## Contributions Beyond the Project Team

Found 13 bugs in team [CS2103T-W10-4](https://github.com/AY2324S1-CS2103T-W11-2/tp) during PED.


