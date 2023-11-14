---
layout: page
title: Ho Hong Wei's Project Portfolio Page
---

### Project: FriendBook

FriendBook - is a desktop app for managing friend contacts and plan details,
optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI) created with JavaFX.
If you can type fast, FriendBook can get your plans management done faster than traditional GUI apps.
It is written in Java, and has about 17kLoC.

Given below are my contributions to the project.

* **New Feature**: Refactored ModelManager to take into account new methods to modify/display/access the newly created Plan List
  * What it does: The backend Model will now have access to new Plan Methods such as add plan, delete plan and inner helper methods such as
    contains and set plan to handle the new functionalities of FriendBook involving plans.
  * Justification: These methods work in tandem to support key functionality of FriendBook which is to add and manipulate plans.

  * Highlights:
    * Extensive planning required to determine which functions of plans would be required and which inner helper methods would be required to
      allow the other methods to function as required.

* **New Feature**: Refactored AddressBook methods and creating support for Plan classes.
  * What it does: Augments current AddressBook with a UniquePlanList to allow Friendbook to now store plan information.
  * Justification: This feature is essential as part of morphing AB3 to our FriendBook.
  * Highlights:
    * Once again, extensive planning to understand which methods are essential for the plans and how I need to represent them in the addressbook
      for the most object-oriented design.
    * Deciding between 2 separate address and plan books, and augmenting a planlist into the addressbook. Settled on the former to take full advantage of the
      structure of AB3.


* **New Feature**: Added the UniquePlanList class.
  * What it does: A structure created to store Plans inside FriendBook, similar to a UniquePersonList.
  * Justification: The plans stored within can be modified based on the commands given.

* **Enhance Existing Storage Class**: Modified `JsonAddressBookStorage.java` to save plans together with friends upon exit.
  * What it does: Allows all plans and friends to be saved when exiting the application.
  * Justification: Simply an extension of a current feature in place to save Persons.
  * Highlights: Had to tradeoff between readability of toString() command for Plans for logging in UI and for ease of writing
    data to a file with json formats. Settled on a toString() method for external use, and a toStringRaw() method for internal use
    of writing data to a json file.

* **New Feature**: Added `JsonAdaptedPlan.java` class to aid with the Json encoding to storage.
  * What it does: Similar to `JsonAdaptedPerson.java`, provides a Json format to encode and decode Plans to/from.
  * Justification: This feature is essential to support our storage feature.

* **Testing**: Tested all the new classes/methods added.
  * What it does: Ensures optimal coverage for new methods.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=w16&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=zoom&zA=redtailedfox&zR=AY2324S1-CS2103T-W16-4%2Ftp%5Bmaster%5D&zACS=184.76271186440678&zS=2023-09-22&zFS=w16&zU=2023-11-07&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)


* **Project management**:
  * Documented triage of bugs raised in PE-D.


* **Documentation**:
  * User Guide:
    * Added documentation for `delete-plan` and `list-plan` commands
    * Refactor the details to the exact implementation of the commands for issues like unexpected output error messages
  * Developer Guide:
    * Added implementation details of the `delete-plan` feature including activity and sequence diagrams.


* **Review / Mentoring Contributions**:
  * Briefed teammates on the new structure of AddressBook augmented with plans and the major changes to key features
    such as storage
  * Reviewing and helping to fix bugs brought up in PE-D


* **Community**:
  * Advised a team outside of class how to abstract their fields for better object-oriented design.
  * Advised a team outside of class how to reduce arrowhead code design in their methods.
