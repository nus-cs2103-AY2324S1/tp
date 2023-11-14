---
layout: default.md
title: Peng Victor M X's Project Portfolio Page
---

### Project: Connectify

Connectify is a networking platform designed to help professionals efficiently manage their connections and enhance their networking experiences.

Given below are my contributions to the project.

* **New Feature**: Implemented `deleteCompany` command [\#54](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/54)
  * Implemented the `deleteCompany` command, enabling users to delete companies from the Connectify database.
  * This includes adding the parser and predicate classes associated with the command, as well as relevant test cases.
  * Significance: This feature improves the product significantly because users are now able to remove erroneous or outdated data from the database.

* **New Feature**: Added ability to view the list of persons associated with a company in the company card. [\#71](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/71)
  * Edited the UI component, enabling users to view which persons are associated with a specific company.
  * This includes changes to the UI and structure of the CompanyCard class.
  * Significance: This feature is important as it saves time for the user that would otherwise be used to search for each person individually.

* **New Feature**: Implemented `rank` command. [\#107](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/107)
  * Implemented the `rank` command, enabling users to rank persons by their priority value.
  * This includes changing existing files to support the Comparator<Person> interface in the Person class, and adding relevant test cases.
  * Significance: This feature enables users to view persons deemed more important at the top of the person list so that they can access their details more easily.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=victorpengmx&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=victorpengmx&tabRepo=AY2324S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed deliverables consistently from `v1.1` to `v1.4`.
  * Contributed to pull request reviews.
  * Identified bugs in code and informed team members.

* **Enhancements to existing features**: Fixed bugs in `listAll`, `listPeople`, `rank` commands. [\#118](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/118)
  * Revamped list commands to list entities in a consistent order
  * Previously, the `rank` command would permanently alter the displayed results of running the `listAll` and `listPeople` commands.
  * After fixing, the `listAll` and `listPeople` commands display results in a specific order that will not be affected by the `rank` command.
  * This includes the adding of several comparator classes and modifying existing classes to support the new ordering.
  * Significance: This enhancement ensures that display results of the list commands are consistent.

* **Documentation**:
    * User Guide:
      * Added descriptions for the deleteCompany command. [\#90](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/90)
      * Add to Known Issues section to address bugs. [\#191](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/191)
      * Added sections on keyboard shortcuts and advanced data management features. [\#203](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/203) [\#214](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/214) [\#218](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/218)
      * Added terms to the glossary. [\#222](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/222)
        
    * Developer Guide:
      * Added sequence diagram for list commands. [\#87](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/87)
      * Added Table of Contents, Introduction and Acknowledgements. [\#125](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/125)
      * Updated user stories and use cases for all commands. [\#125](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/125)

* **Team-Based Tasks**:
  * **Enhanced Code Robustness through Gradle Assertions**: Implemented assertions in Gradle to bolster code robustness, contributing to the software's overall reliability.

* **Community**:
  * PRs reviewed (with non-trivial review comments).
  * Reported bugs and suggestions for other teams in the class.

* **Tools**:
  * **Managed SourceTree, Git, and GitHub for Version Control**: Utilized SourceTree, Git, and GitHub for revision control and code management, ensuring efficient development workflows.
  * **Used PlantUML for Diagram Creation**: Utilized PlantUML to generate diagrams for the Developer Guide so that it is more visually informative.
