---
layout: page
title: Mingyu's Project Portfolio Page
---

### Project: CodeContact

CodeContact is a desktop address book solution for Software Engineering companies. The user interacts with it using a
CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: `Undo` n `Redo`
  * What it does: <br> Allows users to undo and redo their command easily.
  * Justification: <br> This feature enhances user experience by allowing effortless reversal of previous actions,
  providing convenience in scenarios where unintended changes are made.
  * Highlights: <br> The undo and redo feature is unlimited, allowing users to revert all the way to their first or last command using
  the simple `undo` or `redo` command, ensuring ease of use.
  * Credits: <br> Inspired by the AB-3 developer guide, this feature employs a pointer to change the address book at each undo or redo command.
  Additional validation checks were added for CodeContact's complexity.
<br>

* **New Feature 2**: `Adding roles `
    * What it does: <br> Allows users to add different roles.
    * Justification: <br> This feature minimizes risks associated with incorrect role assignments by allowing explicit
definition and addition of developer or client roles. It ensures a standardized set of role names,
reducing the chance of mislabeling and improving searchability within CodeContact.
    * Highlights: <br> The feature provides separate commands (add-developer-role and add-client-role) for adding developers and clients,
adding clarity to the role assignment process. A pre-added list of roles for clients and developers facilitates user adoption, and validation checks prevent the addition of repeated roles.
    * Credits: N.A.
<br>

* **New Feature 3**: `Deleting roles `
    * What it does: <br> Allows users to delete different roles.
    * Justification: <br> This CodeContact feature is crucial for user data management, enabling users to refine and update the list of roles.
  Deleting roles is essential for maintaining a clean and relevant role structure, empowering users to adapt roles to evolving needs and ensuring accuracy in the address book.
    * Highlights: <br> Separate commands (delete-developer-role and delete-client-role) cater to the unique needs of deleting developers and clients.
  To prevent accidental deletion when roles are in use, the feature checks for active developers and clients before deletion. Users cannot delete pre-added roles from CodeContact.
    * Credits: N.A.
<br>

* **Enhancements to existing features**:
    * Modified list feature to work with our new tab and fields (Pull requests [\#113](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/113))
    * Fixed parts of the test cases to pass again (Pull requests [\#225](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/225))
    * Increased code coverage from `32.28%` to `38%` and from `48.9%` to `54.39%`.(Pull requests [\#250](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/250)
      [\#241](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/241))
<br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=mingyu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=zoom&zA=mingyu-wan&zR=AY2324S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&zACS=241.3&zS=2023-09-22&zFS=mingyu&zU=2023-11-10&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)
<br>

* **Project management(team-based tasks contributions)**:
    * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub
    * Managed issues creating and some assigning throughout the milestones
    * Managed the collaboration document and product demo submissions for tutorial
<br>

* **Documentation Contributions**:
    * User Guide Contributions:
        * Removed traces of AB-3: [\#50](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/50)
        * Added documentation for the features `undo`,`redo`,`add-role` and `delete-role`: [\#160](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/160)
        * Did cosmetic tweaks to existing documentation of features `list`: [\#160](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/160)
        * Wrote the content for `tutorial`: [\#162](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/162)
        * Modified and content and UI pictures for `Quick Start`: [\#162](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/162)
        * Wrote the content for `Navigating the GUI` and `Format`: [\#242](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/242)
    * Developer Guide Contributions:
        * Added use case (abandoned due to a shift in project): [\#43](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/43)
        * Reformatted entire DG format: [\#244](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/244)
        * Wrote user stories and use case: [\#246](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/246) [\#247](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/247)
        * Wrote implementations, sequence and activity diagrams for features i implemented: [\#246](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/246)
        * Wrote in appendix: planned enhancements: [\#247](https://github.com/AY2324S1-CS2103T-T09-2/tp/pull/246)
<br>

* **Community (Review/mentoring contributions)**:
    * Reported bugs and suggestions for other teams in the cohort
<br>
