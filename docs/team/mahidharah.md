---
layout: page
title: Mahidharah's Project Portfolio Page
---

### Project: CodeContact

CodeContact is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, CodeContact can get your contact management tasks done faster than traditional GUI apps.

Our product helps seamlessly integrate contact, client, and project management, simplifying access to coding-related contacts, facilitating collaboration, and offering command-line efficiency for project managers.

Given below are my contributions to the project.


* **New Feature 1**: `add-developer`, `add-client` & `add-project`
    * What it does: Adds a developer/client/project to the address book, making sure input parameters are valid and potential duplicates are found, highlighted to the user and rejected.
    * Justification: Decided to separate these as different commands instead of having a single `add` command to allow for more flexibility with parameters changes to these features in the future (from basic OOP principles). This also allows for more specific error messages to be displayed to users when they input invalid parameters, utilising polymorphism.
    * Highlights: Allows specific optional fields (i.e Project deadline). Automatically switches tabs according to command.



* **New Feature 2**: `delete-developer` & `delete-client`
    * What it does: Deletes a developer/client from the address book.
    * Justification: Decided to separate these as different commands instead of having a single `delete` command to allow for more flexibility with parameters changes to these features in the future (from basic OOP principles). This also allows for more specific error messages to be displayed to users when they input invalid parameters, utilising polymorphism.
    * Highlights: Automatically switches tabs according to command.

<div style="page-break-after: always;"></div>

* **New Feature 3**: `delete-project`
    * What it does: Deletes a project from the address book and accordingly removes it from the set of projects for all developers and clients.
    * Justification: I decided to implement the feature such that it triggers updates on developers and clients with hopes that this feature will be a major quality of life improvement for project managers, enabling them to remove projects easily without having to edit the information for relevant clients and developers to maintain integrity of the data.
    * Highlights: Automatically switches tabs according to command. This feature uses the iterator java class and its for each remaining method effectively to simplify code and ensure efficiency.


* **Enhancements to existing features**:
    * Contributed to the GUI component of CodeContact by creating relevant classes and FXML files for developer and client cards (Pull request [\#79](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/79))
    * Ensured functional improvements use developer and client classes instead of person class (Pull request [\#79](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/79))
    * Edited SampleData to include sample developers, clients and projects for easy testing for teammates (Pull request [\#79](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/79))


* **Testing**:
  * Enabled test files to work for other team members and contributed greatly to testcode
  * Wrote testcases for model, logic and storage, and edited necessary test utility files to account for functional developments of CodeContact, speciafically developer and client features (Pull request [\#216](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/216), [\#222](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/222), [\#236](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/236))
  * Wrote testcases for my functional contributions to Codecontact. (Pull request [\#216](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/216), [\#236](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/222), [\#236](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/236))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Mahidharah&tabRepo=AY2324S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management(team-based tasks contributions)**:
    * Responsible for the timely pivot of our team project in v1.2 after checking in with tutors and professors
    * Spearheaded the pivot by suggesting a new idea and directing the team to a new project which effectively carried over previous functional contributions
    * Actively contributed during team meetings and reminded team members of upcoming deadlines

<div style="page-break-after: always;"></div>

* **Documentation Contributions**:
    * User Guide Contributions:
        * Added documentation for the features `delete-developer`, `delete-client`, `delete-project`, `add-developer`, `add-client`, `add-project`
        * Did cosmetic tweaks to existing documentation of feature list table
    * Developer Guide Contributions:
        * Added implementation details of the `delete` and `add` features
        * Added PUML sequence diagrams for these implementations


* **Community (Review/mentoring contributions)**:
    * PRs reviewed (with non-trivial review comments): [\#155](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/155), [\##219](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/219)
    * Reported bugs and suggested ideal implementations for features for teammates
    * Some parts of the delete-project feature I added was adopted by team members and classmates, for instance the following implementation of validation checks for projects


* **Tools**:
    * Capitalised on GitHub copilot autocomplete features in creating testcases, enabling me to cover the most amount of functional code in testcases
