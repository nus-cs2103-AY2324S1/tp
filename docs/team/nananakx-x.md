---
layout: page
title: Nanette's Project Portfolio Page 
---

### Project: Foster Family

This is a desktop application for **managing animal foster families**. Our target audience are the foster managers of animal shelters who currently do not have a good 
logistical workflow to keep track of their fosterers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 
kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nananakx-x&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **New Feature**: Added the ability to sort the fosterers list in the main window.
    * What it does: Sorts the list of fosterers alphabetically by name (uppercase letters come before lowercase letters).
    * Justification: This feature improves the product because it allows the foster manager to retrieve information efficiently at a glance, without having to type 
  the `find` command if the name of the fosterer is known.
    * Highlights: This feature requires the use of a comparator, specified based on the person's name. It can be easily extended in the future to account for new sorting criteria.
    * Pull requests: [#73](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/73)


* **Enhancements to existing features**: Allow for new fields like `housing`, `availability`, `animalName` and `animalType` to be added with a fosterer entry.
    * What it does: Allows the foster manager to enter more essential attributes related to a fosterer when the `add` command is executed. 
    * Justification: In the previous implementation, the foster manager can only add name, email, phone number and address which is not well-suited for the 
  needs of the target user since crucial information like housing type should also be taken into account, given the context of managing fosterers for cat and dog shelters.
      * Highlights: This feature requires tweaks to the existing `Person` class, and also new classes to encapsulate the new attributes. Furthermore, the implementation was also 
  challenging when coming up with methods to check if the combination of data / data inputted is valid are implemented since there were multiples cases to consider. Constant 
  changes were made to improve on the checks and usefulness of error messages.
    * Pull requests: [#63](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/63), [#73](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/73), 
  [#82](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/82) 


* **Other enhancements to existing features**:
    * Updated the UI for the person card such that the new fields are displayed according to the draft UI. (Pull requests: 
  [#63](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/63))
    * Implemented checks to ensure that the fosterer entries are valid before they can be added. (Pull requests: 
  [#63](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/63), [#73](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/73), 
  [#82](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/82))
    * Implemented error messages to guide the users on how to rectify the errors which resulted in an invalid fosterer entry. 
  (Pull requests: [#63](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/63), [#73](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/73), 
  [#82](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/82))
    * Improved on duplicate persons check such that it is now case-insensitive and multiple spaces between words will be ignored. 
  (Pull requests: [#91](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/91))


* **Documentation**:
    * User Guide:
        * Added documentation for the feature `add`, through both the main window and profile page
        * Added documentation for the feature `sort`
        * Added documentation for the feature `undo`, `reset` and `reset confirm`
        * Added technical terms section for better readability
        * Modified the `edit` section by separating it into two separate features - editing through the main window and editing through the profile page, for better 
      readability 
        * Updated and maintained the command summary
        * Did cosmetic tweaks to existing documentation

    * Developer Guide:
        * Added implementation details of the `add` feature.
        * Added sequence diagram for `add` feature
        * Added activity diagram for `add` feature
        * Added implementation details of the `sort` feature
        * Added sequence diagram for `sort` feature
        * Added user stories and use cases related to `add`, `sort` and `undo`
        * Added non-functional requirements and manual testing of `add` and `sort` 
        * Added planned enhancements for:
            * Phone number input
            * Guide Users on How To Rectify / Preventing the Corruption of Data File
            * Notes feature as a separate command
            * Specificity of error messages

  * Pull requests: [#30](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/30), [#48](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/48), 
  [#65](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/65), [#73](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/73), 
  [#82](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/82), [#91](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/91), 
  [#98](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/98), [#144](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/144),
  [#147](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/147), [#149](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/149),
  [#161](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/161)


* **Project management**:
    * Helped to set up the GitHub team org/repo, codecov and the project website
    * Kept track of deliverables and deadlines
    * Helped to maintain the issue tracker for milestones
    * Helped with demo video


* **Community**:
    * PRs reviewed (with non-trivial review comments): [#67](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/67), 
  [#80](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/80), [#84](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/84)

