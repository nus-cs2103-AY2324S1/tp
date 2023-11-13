---
layout: page
title: Tyrus's Project Portfolio Page 
---

### Project: Foster Family

This is a desktop application for **managing animal foster families**. Our target audience are the foster managers of animal shelters who currently do not have a good logistical workflow to keep track of their fosterers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tyruslye&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)


* **New Feature**: Added the ability to undo the last command that was executed
        * What it does: Undoes the last command that was executed.
        * Justification: Accidental edits and deletes can happen and having a means of reversing any unwanted edits allows users more leniency when making changes.
        * Highlights: Through experimenting with many methods of implementing an undo function, and due to the nature of the features of the application, the simplest method of state saving was used.
        * Pull requests: [#94](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/94)


* **Enhancements to existing feature**: Made the `reset` command require confirmation before execution
    * What it does: Instead of entering reset to erase the entire contents of the Address Book, the user is now required to enter `reset` followed by `reset confirm` to execute the command.
    * Justification: Due to its highly catastrophic consequence in the event of a accidental execution of this command, it was decided that the user needed to be prompted to confirm their decision before the command is executed to prevent accidental deletion of the entire Addressbook.
    * Highlights: This feature high-jacks and overrides an existing method to ensure state consistency between different command execution instances.
    * Pull requests: [#143](https://github.com/AY2324S1-CS2103T-T13-4/tp/pull/143)


* **Other enhancements to existing features**:
    * Changed the `clear` command word to `reset`.
    * Made the commands `list` and `find` synonymous.


* **Documentation**:
    * User Guide:
        * Added documentation for the feature `reset`.
        * Added documentation for the feature `help`.
        * Added documentation for the feature `exit`.
        * Added documentation for the feature `undo`.
    * Developer Guide:
        * Added implementation details of the `reset` feature.
        * Updated the class diagram for `reset` feature.
        * Added the activity diagram for `reset` feature.
        * Added implementation details of the `help` feature.
        * Added implementation details of the `exit` feature.
        * Added implementation details of the `undo` feature.
        * Updated the class diagram for `undo` feature.
        * Added the activity diagram for `undo` feature.

