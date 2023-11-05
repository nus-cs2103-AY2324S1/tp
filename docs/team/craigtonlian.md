---
  layout: default.md
  title: "Project Portfolio Page"
---

# Project: Staff-Snap

> ***Craigton Lian's*** *Project Portfolio Page*<br>

## Overview
Staff-Snap is a desktop hiring management application used for managing applicants during the recruitment cycle. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9k LoC.

## Summary of Contributions
* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=craigtonlian&breakdown=true)

* **Project management**:
  * Managed [releases](https://github.com/AY2324S1-CS2103T-W08-1/tp/releases) `v1.2` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the logo for Staff-Snap <a href="https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/74" class="badge rounded-pill bg-success text-dark">\#74</a>
  * Updated the Applicant Card to display icons <a href="https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/105" class="badge rounded-pill bg-success text-dark">\#105</a>
  * Updated the GUI to display Interview Cards <a href="https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/111" class="badge rounded-pill bg-success text-dark">\#111</a>

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addi`, `editi`, `deletei` and `import` [[\#80](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/80), [\#90](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/90), [\#117](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/117), [\#140](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/140)]
    * Did cosmetic tweaks to existing documentation of features `help`, `edit`: [\#67](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/67), [\#138](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/138), [\#145](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/145)
  * Developer Guide:
    * <span style="color:red">**[TO BE UPDATED]**</span> Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments):
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
