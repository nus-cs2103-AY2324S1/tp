---
title: Amy's Project Portfolio Page
---

## Project: TAfinder

TAfinder is a desktop resource management application used by NUS SOC professors to choose TAs from a large pool of applicants. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ylyma&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByAuthors&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=ylyma&tabRepo=AY2324S1-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Added the 'hide', 'unhide' and 'unhide-all' commands to the app.
  * What it does: Allows the user to hide and unhide applicants from the list of applicants in the app.
  * Justification: This feature is useful for the user to hide applicants that are not suitable for the job, and unhide them when they are suitable, helping to declutter the list of applicants.
  * Highlights: This feature required a modification of several models to include new fields. I had to take care to preserve the immutability of the classes while doing so. I also had to make sure this command is applied to all future lists.

* **New Feature**: Added a 'sort' command
  * What it does: Allows the user to sort applicants by a specified field.
  * Justification: This feature allows the most suitable applicants to be listed in order based on the user's criteria, helping them to make a decision more efficiently.
  * Highlights: To allow flexibility of command when more fields are added in the future, I had to make sure the class is easily extendable to include new fields, while ensuring I stuck to the OOP principles. I also had to deliberate on how each field should be sorted.

* **Enhancement to existing feature**: Enhanced 'list' command
  * What it does: Allow the 'list' command to filter applicants based on a specified field, in addition to listing all applicants.
  * Justification: This feature allows the user to see a filtered list of applicants based on what they are looking for (e.g. bookmarked applicants), making their search for the right applicant more organised.
  * Highlights: I had to make this feature easily extendable to new fields as well. I also had to modify some existing classes.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `hide`, `unhide`, `unhide-all`, `sort` and `list`
    * Added the corresponding screenshots for each command
  * Developer Guide:
    * Added the hide command section under implementation

* **Contributions to team-based tasks**:
  * Reviewed PRs for team members

