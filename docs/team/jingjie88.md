---
layout: page
title: Jing Jie's Project Portfolio Page
---

### Project: InterviewHub

InterviewHub is a desktop application that helps engineering hiring managers to manage applicants and interviews. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 18 kLoC.

Given below are my contributions to the project.

* **New Feature**: `mark` command (PR: [#56](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/56))
  * What it does: Allows the user to mark an interview as done.
  * Justification: Users need a way to keep track of which interviews are done.

* **New Feature**: `list-i-done` command (PR: [#109](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/109))
  * What it does: Allows users to list all interviews that are done.
  * Justification: This is useful in the post-interview reviewing process.

* **New Feature**: `list-i-not-done` command (PR: [#109](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/109))
  * What it does: Allows users to list all interviews that are not done.
  * Justification: This is useful for users to see upcoming interviews or reschedule interviews that are not yet done.

* **Enhancement**: `find-a` command (PR: [#90](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/90))
  * What it does: Allows users to search by any applicant field (name, phone, email, address, tag).
  * Justification: A more powerful find feature helps users filter applicants more accurately.

* **Enhancement**: Interview clash checks (PR: [#126](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/126))
  * What it does: Checks if an interview clashes with existing interviews when adding or editing an interview.
  * Justification: Prevents the user from accidentally adding overlapping interviews which greatly improves the interview scheduling process.

* Renamed and changed product icon (PR: [#137](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/137))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jingjie88&breakdown=true)

* **Project management**:
  * Documented v1.2 features demo in project notes
  * Released v1.3.2 jar file 
  * Created some issues

* **Documentation**:
  * User Guide:
    * Added documentation for `add-a`, `edit-a`, `delete-a`, `find-a`, `list-a`, `list-i-today` commands.
    * Contributed to GUI summary section.
    * Proofread and fixed typos across all sections
  * Developer Guide:
    * Added documentation for `find-a` and `list-i-today` commands.
    * Added sequence diagram and activity diagram for `find-a` command.
    * Added sequence diagram for `list-i-today` command.
    * Added test cases for `add-a`, `edit-a`, `delete-a`, `find-a`, `list-a`, `list-i-today` commands.
    * Added glossary

* **Community**:
  * 35 team pull requests reviewed.
  * Helped manually test all features for bugs and fixed bugs.
  * Reported [16](https://github.com/jingjie88/ped/issues) bugs for other team during PE-D.
