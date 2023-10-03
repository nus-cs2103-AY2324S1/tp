---
  layout: default.md 
  title: "Project Portfolio Page"
---

# Project: Staff-Snap

> ***Craigton Lian's*** *Project Portfolio Page*<br>

### Overview<hr>
Staff-Snap is a desktop HR management application used for managing employees. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about <span style="color:red">**[TO BE UPDATED]**</span> 10 kLoC.

### Summary of Contributions<hr>

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=craigtonlian&breakdown=true)

* **Project management**:
  * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * <span style="color:red">**[TO BE UPDATED]**</span> Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * <span style="color:red">**[TO BE UPDATED]**</span> Added implementation details of the `delete` feature.

* **Community**:
  * <span style="color:red">**[TO BE UPDATED]**</span> PRs reviewed (with non-trivial review comments): 
      [\#31](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/31), 
      [\#32](), 
      [\#19](), 
      [\#42]()
  * Contributed to forum discussions (examples: 
      [\#2](https://github.com/nus-cs2103-AY2324S1/forum/issues/2), 
      [\#121](https://github.com/nus-cs2103-AY2324S1/forum/issues/121), 
      [\#158](https://github.com/nus-cs2103-AY2324S1/forum/issues/158), 
      [\#228](https://github.com/nus-cs2103-AY2324S1/forum/issues/228))
  * <span style="color:red">**[TO BE UPDATED]**</span> Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo
