---
layout: page
title: Nicole's Project Portfolio Page 
---

### Project: Foster Family
This is a desktop application for **managing animal foster families**. Our target audience are the foster managers of animal shelters who currently do not have a good logistical workflow to keep track of their fosterers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=butteredyakiimo&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos) 


* **New Feature**: Added the ability to view various statistics about fosterers.
  * What it does: Performs calculations and summarises different types of information for the foster manager. The information available are about the available fosterers(those available to foster an animal), current fosterers (those currently fostering) and the different housing types of fosterers. 
  * Justification: This feature improves the product significantly, because the foster manager may have a large number of fosterers in the address book, and will require a quick and effective way to gain insights on the different types of fosterer information.
  * Highlights: This features requires access to the different fields of a fosterer, which may have resulted in slightly tedious code to adhere to the SLAP principle. The implementation was also challenging, as it was built on top of new fields, requiring changes to existing classes. However, this feature can be easily extended in the future to account for new information that the foster manager wants to know about.
  * Pull requests: [#86](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/86), [#96](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/96)


* **Enhancement to existing feature**: Allow for mass deletions
  * What it does: Allows the foster manager to enter more than one fosterer to delete. Extra blank spaces and duplicate indices will be ignored. 
  * Justification: In the previous implementation, the foster manager can either delete one fosterer at a time, or completely clear the address book of all data. Hence, implementing a multiple delete feature will allow the foster manager to delete more than one fosterer using just a single command, making deletes faster. 
  * Highlights: This feature requires tweaks to the existing `DeleteCommand`, and also a new class to encapsulate the multiple indices inputted.
  * Pull requests: [#59](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/59), [#80](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/80)


* **Other enhancements to existing features**:
  * Designed the draft UI
  * Updated main window GUI to follow draft UI, except tags (Pull requests: [#66](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/66))
  * Updated sample persons data for testing (Pull requests: [#86](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/86))

  
* **Documentation**:
    * User Guide: 
        * Added documentation for the feature `delete`
        * Added documentation for the feature `stats`
        * Added useful notations for better readability
        * Modified quick-start section to fit current project context and for better clarity
        * Did cosmetic tweaks to existing documentation
      
    * Developer Guide: 
        * Added implementation details of the `delete` feature
        * Added sequence diagram for multiple `delete` feature
        * Added implementation details of the `stats` feature
        * Added `StatsCommand` class diagram
        * Added sequence diagram for `stats` feature
        * Updated `Model` class diagram
        * Added user stories and use cases related to `stats` and `delete`
        * Added planned enhancements for:
          * Shorter command formats
          * Reducing coupling between availability and animal type
          * Supporting more animal types
          * Allowing one fosterer to care for more than one animal
          * Case-sensitivity of inputs
          * Allowing symbols in name
  * Pull requests: [#24](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/24), [#51](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/51), [#80](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/80), [#139](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/139)
  


* **Project management**:
    * Kept track of deliverables and deadlines
    * Helped to maintain the issue tracker for milestones
    * Managed releases `v1.2.1` and `v1.3.1` on GitHub


* **Community**:
  * PRs reviewed (with non-trivial review comments): [#63](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/63), [#73](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/73), [#82](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/82)
