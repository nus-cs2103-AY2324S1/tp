---
title: Nabonita's Project Portfolio Page
---

## Project: TAfinder

TAfinder is a desktop resource management application used by NUS SOC professors to choose TAs from a large pool of applicants. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to bookmark/unbookmark users.
    * What it does: allows the user to bookmark/unbookmark specific applicants of their choice, and list all bookmarked applicants. (List function later enhanced by Amy)
    * Justification: This feature improves the product significantly because a user may want to take special note of certain applicants or may require a way to sigal which applicants have already been chosen to be a TA.
    * Highlights: This feature's development involved creating new data structures for bookmarking, extending the user interface, and ensuring seamless integration with existing application features.


* **New Feature**: Added an initial view command to display a singular applicant.
    * What it does: allows the user to view a singular applicant.
    * Justification: This feature improves the product significantly because a user may want to view and evaluate just one applicant's details instead of the whole list.
    * Credits: Later updated by Ravern to display applicant on a separate panel.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nabonitasen&breakdown=true)

* **Enhancements to existing features**:
    * Updated the GUI design of applicant list and colour scheme.
    * Initial refactoring of address field to GPA field.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `bookmark/unbookmark` and `view`.
        * Did tweaks to existing documentation of features `clear` and `exit`.
    * Developer Guide:
        * Added implementation details of the `bookmark/unbookmark` and `view` feature.
    * About Us Page:
        * Updated the About Us page with the information of the team.

* **Community**:
    * Reviewed PRs for team members.
    * Reported bugs and suggestions for other teams in the class during PE-D.

