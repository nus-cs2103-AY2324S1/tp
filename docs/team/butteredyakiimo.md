---
layout: page
title: Nicole's Project Portfolio Page 
---

### Project: Foster Family
This is a desktop application for **managing animal foster families**. Our target audience are the foster managers of animal shelters who currently do not have a good logistical workflow to keep track of their fosterers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=butteredyakiimo&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos) <br>

* **New Feature**: Added the ability to view various statistics about fosterers.
  * What it does: Performs calculations and summarises different types of information for the foster manager. The information available are about the available fosterers(those available to foster an animal), current fosterers (those currently fostering) and the different housing types of fosterers. 
  * Justification: This feature improves the product significantly, because the foster manager may have a large number of fosterers in the address book, and will require a quick and effective way to gain insights on the different types of fosterer information.
  * Highlights: This features requires access to the different fields of a fosterer, which may have resulted in slightly tedious code to adhere to the SLAP principle. The implementation was also challenging, as it was built on top of new fields, requiring changes to existing classes. However, this feature can be easily extended in the future to account for new information that the foster manager wants to know about.

* **Enhancement to existing feature**: Allow for mass deletions
  * What it does: Allows the foster manager to enter more than one fosterer to delete. Extra blank spaces and duplicate indices will be ignored. 
  * Justification: In the previous implementation, the foster manager can either delete one fosterer at a time, or completely clear the address book of all data. Hence, implementing a multiple delete feature will allow the foster manager to delete more than one fosterer using just a single command, making deletes faster. 
  * Highlights: This feature requires tweaks to the existing `DeleteCommand`, and also a new class to encapsulate the multiple indices inputted.

* **Other enhancements to existing features**:
  * Designed the draft UI:
  * Updated main window GUI to follow mock up(except tags):
  * Enhanced existing sample data for testing: typical persons

* **Project management**:
  * Keeping track of deliverables and deadlines:
  * Maintaining issue tracker:
  * Release management: releases 1.3 etc

  
* **Documentation**:
    * User Guide:
        * Added documentation for the feature `delete`.
        * Added documentation for the feature `stats`.
        * Added useful notations for higher readability.
        * Modified quick-start section to current project context, and for more clarity
    * Developer Guide:
        * Added implementation details of the `delete` feature.
        * Added sequence diagram for multiple `delete` execution.
        * Added implementation details of the `stats` feature.
        * Added `StatsCommand` class diagram.
        * Added sequence diagram for `stats` command execution.
        * Updated `Model` class diagram.
        * Added user stories and use cases related to statistic and delete
        * Add planned enhancements for:
          * Shorter command formats
          * Reduce Coupling Between Availability and Animal Type
          * Supporting more Animal Types
          * Allowing one fosterer to care for more than one animal
          * Case-sensitivity of Inputs
          * Allowing symbols in name


* **Community**:
  * PRs reviewed: 

* **Tools**:
