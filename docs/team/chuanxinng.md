---
layout: default.md
title: "Ng Chuan Xin's Project Portfolio Page"
---

### Project: Tutorium

#### Overview:
Tutorium is a desktop application for tuition centre staff to obtain data analysis to plan marketing strategies. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

#### Summary of Contributions:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=chuanxinng&breakdown=true)

* **Enhancements implemented**:
    * Added an import feature to the app.
        * This feature allows the user to import data from .csv files into Tutorium.
    * Added a display bar chart feature to the app.
        * This feature allows the user to visualise their data through a bar chart.

* **Documentation**:
    * User Guide:
        * Complete user guide for deleting data, importing data and visualising data through a bar chart.
        * Update Ui.png
        * Checking for other parts by other teammates and add additional information
    * Developer Guide:
        * Added activity diagrams and sequence diagrams for the import feature.
        * Provided details on alternative implementations of the import feature.

* **Team-based tasks**:
    * Set up milestones v1.4 in GitHub.
    * Wrote and improved JUnit tests for enhancements implemented.
    * Improve code quality by:
      * Abstracting similar code of EnrolDateBarChartCommandResult, EnrolDateTableCommandResult and TrendCommandResult into an abstract class EnrolDateCommandResult.
      * Create a OrganizeData class that encapsulates methods and data related to organizing and processing student data.

* **Review/mentoring contributions**:
    * Reviewed PRs created by Lin Chen Yu and other team members, providing feedback where appropriate.
    * PRs merged and reviewed: 88, 90, 92, 99, 109, 112, 115, 117, 186, 189, and 191.

* **Contributions beyond the project team**:
    * Reported bugs found in other teams' JAR files and User Guide during the PE Dry Run.
