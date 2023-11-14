---
layout: default.md
title: "Yong Ning's Project Portfolio Page"
---

## Project: Flagship

Flagship is a desktop application designed to assist aspiring students in tracking their internship applications. Interaction with the application is primarily through a CLI, complemented by a GUI built using JavaFX. It is developed in Java, with approximately 9 kLoC.

## Summary of contributions

* **Code contributed**:
  * Modified the initial template, which was intended for AddressBook, to suit Flagship, by creating the following classes:
    * InternshipMainApp.java
    * InternshipLogic.java
    * InternshipLogicManager.java
    * InternshipCommand.java
    * InternshipBookParser.java
    * InternshipParser.java
    * InternshipBook.java
    * InternshipModel.java
    * InternshipModelManager.java
    * InternshipUserPrefs.java
    * ReadOnlyInternshipBook.java
    * ReadOnlyInternshipUserPrefs.java
    * InternshipSampleDataUtil.java
    * InternshipBookStorage.java
    * InternshipStorage.java
    * InternshipStorageManager.java
    * InternshipUserPrefsStorage.java
    * JsonSerializableInternshipBook.java
    * InternshipCard.java
    * InternshipListPanel.java
    * InternshipMainWindow.java
    * InternshipUIManager.java
    * InternshipListCard.fxml
    * InternshipListPanel.fxml

  * Led the development of the Modify Command to allow users to edit existing internship entries:
    * ModifyCommandParser.java
    * ModifyCommand.java

  * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=Yong%20Ning&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=yongning0310&tabRepo=AY2324S1-CS2103T-W17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Modify command
  * What it does: Allows users to **modify** an existing internship entry within Flagship.
  * Justification: This feature is crucial for maintaining up-to-date internship information.
  * Highlights:
    * Streamlines the modification process, requiring minimal input (e.g., `modify 1 ro/SWE`).
    * Validates date inputs to prevent logical inconsistencies, such as a deadline after the start date.
  * Contributions: (Pull requests [\#59](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/59), [\#203](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/203))

* **Documentation**:
  * User Guide:
    * Oversaw the **Modify command** section of the User Guide.
    * Implemented a user-friendly navigation feature by adding a "Table of Contents" button on the top right corner of each page, enhancing the usability of the UG and allowing for quick access to the main page without extensive scrolling. 
    * Improved the PDF layout of the UG, ensuring a clean presentation by meticulously organizing content to prevent random page breaks, facilitating a smoother reading experience.
    * Contributions: (Pull requests [\#80](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/80), [\#82](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/82), [\#200](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/200), [\#240](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/240))
  * Developer Guide:
    * Managed the **modify command** and **logic** sections of the Developer Guide.
    * Managed the **Testing Appendix** sections of Developer Guide.
    * Improved the PDF layout of the DG, ensuring a clean presentation by meticulously organizing content to prevent random page breaks, facilitating a smoother reading experience.
    * Contributions: (Pull requests [\#203](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/203), [\#221](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/221))

* **Project management**:
  * Constructed the skeleton for Flagship, enabling concurrent contributions from the team.
  * Ensured that the initial draft of the User Guide was consistent with Flagship's design philosophy.

* **Community**:
  * Reviewed several non-trivial pull requests with comprehensive feedback:
    [\#79](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/79),
    [\#84](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/84),
    [\#97](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/97),
    [\#201](https://github.com/AY2324S1-CS2103T-W17-1/tp/pull/201)
  * Actively participated in forum discussions, providing insights and solutions.

