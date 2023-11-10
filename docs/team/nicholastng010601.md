---
  layout: default.md
  title: "Nicholas Tng's Project Portfolio Page"
---

### Project: ProjectPRO

ProjectPRO is a desktop application designed to help university students organize their projects. ProjectPRO is optimized for use via a Command Line Interface (CLI) while preserving the advantages of maintaining an attractive user interface. Utilizing simple and easy-to-remember commands to execute different tasks, it helps users streamline their project management.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add a group.
  * What it does: allows the user to add various groups into the address book so that the user can group contacts into the various project groups they are in.
  * Justification: This is a core features which improves the product significantly because it will help our target audience, university students, group their contacts into their various project groups so that they do not mix up contacts with similar names. It is especially relevant as university students tend to have many projects.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design principles. Since it involved creating a new class of objects, it required careful planning and utilising of OOP principles.

* **New Feature**: Added the ability to delete time.
  * What it does: allows the user to delete time which had been added to a contact or group.
  * Justification: This feature improves the product significantly because a user can make mistakes in adding free time to contacts and this function provides a convenient way for users to remove the error.
  * Highlights: This enhancement required an in-depth analysis of parser and various conditions whereby the command would be carried out. For example, the time keyed in must exist within the contact. Various designs were also considered, such as allowing multiple time slots to be keyed in at once to improve the user experience. In such a case, we also wanted to allow time that was keyed in correctly to be deleted, even if the command had certain time slots which were wrong. The implementation too was difficult as it required the involvement of our time interval class and comparing the various time slots to come up with a error-free yet efficient implementation.

* **Improved Feature**: Added the ability to save not only contacts, but groups and time intervals.
  * What it does: allows the user's application to save groups and time intervals in the form of JSON, so that when the user reboots their application, their data will be saved and loaded up properly.
  * Justification: This feature is a core feature as without it, users would have to retype all their groups and times everytime they on their application.
  * Highlights: This enhancement required the in-depth analysis of the existing process of saving and loading of the JSON file. It required meticulousness and attention to detail as it involved complex logical processes such as adding a person to the group he is in, while also adding the group to the person's own internal list, whereby any error would result in unexpected results. It also required careful considerations regarding the important variables that had to be stored for the save function to fully capture all the details and allow the user to load the application just as he or she stored it.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&chartGroupIndex=36&chartIndex=3)

* **Project management**:
  * Managed releases `v1.3.0` - `v1.3.3` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#82](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/82), [\#83](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/83), [\#85](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/85))
  * Wrote additional tests for existing features (Pull requests [\#132](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/132), [\#139](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/139), [\#141](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/141))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find`, `list`, `group`, `ungroup` and `addtime`. [\#145](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/145)
  * Developer Guide:
    * Added implementation details of the `deletetime` [\#112](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/112/files) and `addgroup` [\#226](https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/226/files) feature.
    * Added activity and sequence diagrams for the above functions. This includes for delete person time command, shown here.
    * <puml src="diagrams/DeletePersonTimeDiagram.puml" alt="DeletePersonTimeDiagram"/>
    * <puml src="diagrams/DeletePersonTimeActivityDiagram.puml" alt="DeletePersonTimeActivityDiagram"/>
* **Contributions beyond project team**
    * Meticulously review other groups work and provided feedback on potential bugs and how they could improve their product [here](https://github.com/nicholastng010601/ped/tree/main/files).


[//]: # (* **Community**:)
[//]: # (  * PRs reviewed &#40;with non-trivial review comments&#41;: [\#89]&#40;https://github.com/AY2324S1-CS2103T-T10-3/tp/pull/89&#41;)

[//]: # (  * Reported bugs and suggestions for other teams in the class &#40;examples: [1]&#40;&#41;, [2]&#40;&#41;, [3]&#40;&#41;&#41;)
[//]: # (  * Some parts of the history feature I added was adopted by several other class mates &#40;[1]&#40;&#41;, [2]&#40;&#41;&#41;)


