---
layout: page
title: Mathan's Project Portfolio Page
---

### Project: CodeContact

CodeContact is a desktop address book solution for Software Engineering companies. The user interacts with it using a
CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: `import-developer` and `import-client`
    * What it does: <br>Gives users the ability to import developers and clients from a CSV file.
    * Justification: <br>Allows users to easily import a large number of developers and clients into the address book. This
      is especially useful for companies that have a large number of employees and those who are new to CodeContact porting over from other address book applications.
    * Highlights: <br> The feature is able to handle a large number of developers and clients, and is able to handle
      errors such as invalid CSV files and invalid data in the CSV file.


* **New Feature 2**: `lock` and `unlock`
    * What it does: <br>Gives users the ability to lock and unlock the application.
    * Justification: <br> Allows users to lock the application when they are away from their computer, preventing
      unauthorised access to the application. This is especially useful since the information stored on the application contains personal data of developers and clients, that has to be kept secure.
  * Highlights: <br> The password is stored in a hashed format, preventing anyone from accessing the password file to view it directly. The application is also locked when the user closes the application.


* **New Feature 3**: `change-password`
    * What it does: <br>Gives users the ability to change their password.
    * Justification: <br> Allows users to change their password when they feel that their password has been compromised. This is especially useful since the information stored on the application contains personal data of developers and clients, that has to be kept secure.
    * Highlights: <br> The password is only changed if the current password is correct, new password follows the password requirements and the new password is different from the current password.
    * Credits:[Password requirements] (https://support.kaspersky.com/KPC/1.0/en-US/183862.htm)

* **New Feature 4**: `find-deadline`
    * What it does: <br>Gives users the ability to filter deadlines by date and priority.
    * Justification: <br> Allows users to easily find deadlines that are due before or on a certain date, or deadlines that are of a certain priority. This is especially useful for project managers who need to keep track of deadlines.
    * Highlights: <br> The feature is able to handle a large number of deadlines, and is able to handle errors such as invalid dates and invalid priority.
    * Credits: Followed a similar structure as storing filtered lists in `ModelManager` but one level lower at the individual project level to store filtered deadlines.




* **Enhancements to existing features**:
    * Update Logic and Model for Person to Developer, Client and Project (Pull request [\#92](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/92))
    * Implemented Tab Functionality (Pull request [\#96](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/96))
    * Updated the GUI color scheme from Dark Theme to Light Theme(Pull requests [\#110](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/110))
    * Updated User Guide (Pull request [\#144](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/144), [\#161](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/161), [\#243](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/243), [\#254](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/254), [\#278](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/278))
    * Updated Developer Guide (Pull Request [\#254](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/254), [\#261](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/261), [\#278](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/278))
    * Add javadocs and fixed checkstyle errors (Pull request [\#219](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/219))
    * Fixed bugs (Pull request [\#226](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/226), [\#243](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/243))
    * Wrote additional tests for existing features to increase coverage from 41% to 43% (Pull requests [\#243](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/243))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=ncmathan&tabRepo=AY2324S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management(team-based tasks contributions)**:
    * Managed releases `v1.2`-`v1.3` (2 JAR releases) on GitHub
    * Managed issues creating and some assigning throughout the milestones
    * Managed the collaboration document and product demo submissions for tutorial
    * Sorted the PE-D bugs into relevant catgegories


* **Documentation Contributions**:
    * User Guide Contributions:
        * Added documentation for the features `import-developer`, `import-client`, `lock`, `unlock`, `change-password` and `find-deadline`
        * Did cosmetic tweaks to existing documentation including adding a Table of Contents for features
    * Developer Guide Contributions:
        * Added implementation details of the `import`, `GUI` features.
        * Updated the architecture diagrams and sequence diagrams in the design section to reflect changes in CodeContact
        * Added Glossary section
        * Added Effort section


* **Community (Review/mentoring contributions)**:
    * PRs reviewed (with non-trivial review comments): [\#219](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/219), [\#216](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/216))

* **Contributions beyond the project team:**:
    * Provided suggestions to other teams on how to improve their project


* **Tools**:
    * Integrated a third party library (ControlsFX) to the project
