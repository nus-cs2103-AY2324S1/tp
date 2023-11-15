---
layout: page
title: Joel Foo's Project Portfolio Page
---
### Project: NUSCourseMates

NUSCourseMates is a desktop address book application used for keeping track of the courses your friends are taking. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Incorporated the ability to modify the courses of a student in the address book into the `edit` function. 
  * What it does: Allows the user to change the courses of a particular student in the address book. 
    * With `edit 1 c/add-MA1521`, you can add MA1521 to student 1 in the address book. 
    * With `edit 1 c/del-MA1521`, assuming student 1 in the address book has MA1521 originally, you can remove MA1521 from student 1.
    * With `edit 1 c/MA1521-CS2030`, assuming student 1 in the address book has MA1521 originally, you can change student 1's MA1521 to CS2030. This is a convenience method that combines the power of both `add` and `delete` in one expression. 
  * Justification: This feature improves the product significantly because a friend in the address book can decide to take different courses at any point in time and the app should provide a convenient way for the user to update them.
  * Highlights: 
    * It is possible to chain any amount of each type of edit in a single command, providing unparalleled flexibility. 
      * For example, you can perform `edit 1 c/add-MA1521 c/del-CS2030 c/add-ST2334 c/CS2103T-MA2001`, and the changes will be performed in the listed order, from left to right. 
    * Implemented course validation, so that you can only add or delete a valid course.
  * Effort: 
    * The effort required for expanding this feature is essentially equivalent to the work needed for creating three 
      entirely new commands - `addcourse`, `deletecourse` and `editcourse`, but to make it more user-friendly and 
      convenient for the end user, I decided to make them utilise the common `edit` interface using the `c/add-` and 
      `c-del` sub-prefixes. The result of this innovation is unparalleled flexibility and ease of use for the end user. 
    * The effort required to validate the courses was non-trivial as well, as there were many edge cases we had to 
      consider, given we allowed the user to supply not just one, but a list of modifications that will be applied sequentially.

* **New Feature**: Added a `clear-courses` command that clears the courses of a specified student in the address book. 
  * What it does: Allows the user to remove all courses of a particular student in the address book.
  * Justification: This feature is extremely useful from a user perspective, as when a new semester begins, you would want to keep your friends, but want to remove all their courses. The alternative would be to manually remove courses for each student (by calling the `edit` function for each person in the address book), but that would be too cumbersome.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=joel-foo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=AY2324S1-CS2103-T16-1%2Ftp%5Bmaster%5D&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=joel-foo&tabRepo=AY2324S1-CS2103T-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=true&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Set up the Github team org and repo
  * Created and managed all the project milestones on Github
  * Set up codecov for the repo and updated the project `README.md`
  * Managed releases `v1.3` and `v1.3trial`

* **Documentation**:
  * User Guide:
    * Added documentation for the features `edit` and `clear-courses` ([\#125](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/125), [\#149](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/149))
    * Added documentation in the form of tables that listed the requirements for what constitutes a valid input for 
      the `add` and `edit` commands ([\#236](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/236))
  * Developer Guide:
    * Added implementation details of the `edit` feature ([\#125](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/125)) as well as created the sequence and activity diagrams pertaining to 
      the `edit` feature

* **Review / Mentoring Contributions**:
  * Helped resolve critical bugs that were outside the scope of my assigned features:
    * Resolved a critical bug where if you update the address book data JSON file to have more than two emergency 
      contacts, you can still get the address book up and running ([\#279](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/279))
    * Resolved a critical bug where the address book does not accept a valid course as input to the `add` and `edit` 
      commands ([\#255](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/255))
    * Resolved a critical bug where you are not permitted to edit any fields of a person once the emergency tag limit 
      has been reached ([\#107](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/107))

* **Community**:
  * PRs reviewed with constructive feedback provided ([\#52](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/52), [\#82](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/82), [\#94](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/94))
  * Contributed to forum discussions [\#86](https://github.com/nus-cs2103-AY2324S1/forum/issues/86)

* **Contributions beyond the project team:**
  * [Bugs reported during PE-D](https://github.com/joel-foo/ped/issues)
