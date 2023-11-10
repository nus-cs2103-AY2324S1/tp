---
layout: page
title: Brandon's Project Portfolio Page 
---

### Project: Foster Family

This is a desktop application for **managing animal foster families**. Our target audience are the foster managers of animal shelters who currently do not have a good logistical workflow to keep track of their fosterers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:

  1. Added the ability to view a fosterer's profile page
     * What it does: Displays the chosen fosterer's details in full screen.
     * Justification: There are many attributes added to a fosterer, such as AnimalName or Availability. Lengthy attributes can disrupt user experience, as users may forget the attribute names or waste time figuring out mistakes. Adding a profile page can show users which parameters they have forgotten, and easily spot input errors. 
     * Highlights: Implementing the GUI changing required a certain level of understanding of JavaFX. The feature took inspiration from how `HelpCommand` is implemented from the original addressbook. 
     * Pull requests: [#58](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/58)
  2. Added the ability of adding a new fosterer and edit a fosterer's details from a fosterer's profile page and save the changes.
     * What it does: Using the profile UI and handlers implemented by my teammate Zhi Hong, user can edit a fosterer's detail, save the changes, and exit from the profile page. The users are also reminded of saving the changes if they attempt to exit without saving.
     * Justification: Saving is essential part of using our profile UI. After the user is done editing or filling in the details of the new fosterer, only after entering save will the changes be reflected in the storage.
     * Highlights: Implementing adding a new fosterer and saving changes required a thorough understanding of the overall structure as well as how other features are being implemented. For example, preventing adding an invalid fosterer detail required the understanding of checking validity of a fosterer's details, adding and editing a fosterer.
     * Pull requests: [#88](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/88)
* **Documentation**:
    * User Guide:
        * Added documentation for the feature `edit`.
        * Added documentation for the feature `view`. 
        * Added documentation for the feature `save`. 
        * Added a documentation for the feature `add` without any parameter.
        * Added a note for the feature `exit` which behaves differently in the two different windows.
        * Added a section of User Interface that explains the different windows we have.
    * Developer Guide:
        * Added implementation details of the `edit` feature.
        * Added implementation details of the `view` feature. 
        * Added implementation details of the `save` feature. 
        * Added implementation details of the `exit` feature. 
* **Project Mangement**: 
* 
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=brandon-nam&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Community**:

* **Tools**:
