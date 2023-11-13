---
  layout: default.md
  title: "Huang Yixin's Project Portfolio Page"
---

### Project: ProjectPRO

ProjectPRO is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to remove a person from a group.
  * What it does: Allows the user to remove people from groups so that they can accurately keep track of any changes in their groupings. This function also provides a convenient way for users to remove members from a group should they add them by mistake.
  * Justification: This is a core feature which improves the product significantly because it will help our target audience, university students, keep track of their groups and members involved should there be any changes after creating the group.
  * Highlights: This feature required an in-depth analysis of design principles. Since it involved a new class of objects, it required careful planning and utilising of OOP principles.

* **New Feature**: Added the ability to add remarks to a group.
  * What it does: Allows the user to add remarks to groups.
  * Justification: This feature improves the product significantly because a user can keep track of specific details of each group to better manage their projects.
  * Highlights: This enhancement required an in-depth analysis of the existing classes and parser. As this function involved a new class of objects, and required the creation of a new class of object, it required the careful planning and utilising of OOP principles. We considered allowing the users to edit current remarks, but decided that it could be confusing to implement it in a user-friendly way, and could cause inconvenience if the remark is too long, or if they want to make extensive changes to the remark.

* **New Feature**: Added the ability to list the free times of a person or a group.
  * What it does: Allows the user to see the existing free times and meeting times of the people and groups in their contact list, so that they can better keep track of their contacts' and groups' schedules.
  * Justification: This feature improves the product significantly as without it, users would not be able to keep track of their group members' free times or their groups' meeting times.
  * Highlights: This enhancement required the in-depth analysis of the existing classes and storage formats. As this function is purely for users to see the time intervals, it required careful consideration to produce the most user-friendly and comprehensible output result. We considered many formats for showing the times, but ultimately settled on the current as it is the most readable and informative at the same time.

* **New Feature**: Added the ability to add meeting times to a group.
  * What it does: Allows the user to add meeting times to the groups in their contact list, so that they can record and track all meeting times of all groups in one place.
  * Justification: This feature improves the product significantly as without it, users would not be able to track all the meeting times of their groups.
  * Highlights: This enhancement required the in-depth analysis of the existing classes. We considered limiting the acceptable times to be ones where everyone is free, or strictly non-clashing, but decided against these as ultimately, we want this feature to be for the user's convenience and setting so many boundaries would inconvenience them instead.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=coderhuang559&tabRepo=AY2324S1-CS2103T-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&chartGroupIndex=36&chartIndex=1)

* **Project management**:
  * Managed releases `v1.3.0` - `v1.3.3` (4 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `remark` for contacts, `listtime` for contacts, `addmeeting` for groups and `deletetime` for groups. [\#144](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/145)
  * Developer Guide:
    * Added implementation details of the `remark` [\#270](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/270/files), `listtime` [\#270](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/270/files), and `addtime` [\#270](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/270/files) features.
    * Added activity and sequence diagrams for the above functions.

* **Contributions beyond project team**
  * Meticulously review other groups work and provided feedback on potential bugs and how they could improve their product [here](https://github.com/coderhuang559/ped/tree/main/files).
