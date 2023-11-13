---
layout: default.md
title: "Lauren Lim Yi-Xing's Project Portfolio Page"
---

### Project: Tutorium

#### Overview:
Tutorium is a desktop application for tuition centre administrative staff to conduct data analysis for the planning of marketing strategies. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

#### Summary of Contributions:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=laurenlim2112&breakdown=true)

* **Enhancements implemented**:
    * Updated the search feature to use the `search` command.
    * Changed the implementation of the search feature to treat user input as 1 keyword, rather than multiple separate keywords.
    * Added a filter feature to the app.
      * This feature enables tuition centre administrative staff to filter student data based on several conditions, making it easier for them to organise and subsequently analyse student data.
    * Added support for adding and editing enrol dates of students in the add, edit and import features.
      * Each enrol date had to be associated with a specific subject for each student, as students may enrol in tuition centres on different dates for different subjects (e.g. enrolling in English first, before deciding to enrol in Chinese as well 3 months later.)
      * The enrol date field was also essential for subsequent enhancements of generating tables and charts based on the enrol date of students.

* **Documentation**:
    * User Guide:
        * Updated the target user profile for our app.
        * Updated the section on the search feature to reflect its current logic.
        * Added a new section on the filter feature.
        * Fixed typo errors and grammatical mistakes throughout the User Guide.
    * Developer Guide:
        * Updated the Model class diagram and the Storage class diagram.
        * Added activity diagrams and sequence diagrams for the filter feature.
        * Provided details on alternative implementations of the filter feature.
        * Updated user stories and use cases in Appendix: Requirements.
        * Added manual testing instructions to Appendix: Instructions for Manual Testing.
        * Conducted final check for Developer Guide.

<div style="page-break-after: always;"></div>

* **Team-based tasks**:
    * Set up milestones v1.2, v1.2b and v1.3 in GitHub.
    * Wrote and improved JUnit tests for enhancements implemented.

* **Review/mentoring contributions**:
    * Reviewed PRs created by Kam Jia Yue and other team members, providing feedback where appropriate.
    * PRs merged and reviewed include: 82, 100, 104, 107, 108, 110, 120, 121 and 122.

* **Contributions beyond the project team**:
    * Reported bugs found in other teams' JAR files and User Guide during the PE Dry Run.
