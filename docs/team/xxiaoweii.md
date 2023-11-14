---
layout: default.md
title: "Ong Xiao Wei's Project Portfolio Page"
---

### Project: NUSearch

NUSearch is a desktop address book application used to consolidate the profiles of NUS professors, 
teaching assistants (TAs) and students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java.

Given below are my contributions to the project.

#### Feature: Add feature
* What it does: This feature allows the user to add in a new profile into the list, with only one compulsory field. 
* Justification: This feature facilitates the rapid addition of a new profile by requiring only one mandatory field.

#### Enhancements to existing features: 
* `help` feature: Update the help link from the AB3 address book user guide link to NUSearch user guide link.
* `Course` class: Change the acceptable course value to follow the NUS course code format instead of allowing all
all types of input.
* UI: Add role tag to a person's person card in the UI to make the role of each person more easily seen by the user. 

#### Bug fixes
* Fixed a bug where when multiple inputs for `contacts`, `courses` and `tutorials` are added, they are being listed as
one contact, one course and one tutorial instead of separate entries
* Fixed a bug where when tutorials are being added, they are not added to the tutorial list but to the course list. 
* Fixed a bug where missing `name` field in the user input does not show error message.
* Make `Course` input value case-insensitive so same course code with different case will be treated the same.
* Make `Name` input value case-insensitive so no duplicate name will be added twice. 
* Fix the acceptable value for valid `tutorial` input so that no symbols, other than hyphen, will be accepted.

#### Code contributed: 
[Ong Xiao Wei's RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=xxiaoweii&breakdown=true)

#### Project management:
* Added issues on GitHub to keep track of each member's job each week after our weekly meeting.

#### Documentation:
  * User Guide:
    * Added the Motivation section
    * Added and updated the `add` feature under the Feature section
    * Updated the `help` feature under the Feature section
  * Developer Guide:
    * Added a list of glossary to the Glossary section
    * Added the Add feature under Implementation section
  
#### Team Tasks:
  * Updated AboutUs
  * Updated UserGuide
  * Updated DeveloperGuide

#### Community:
PRs reviewed:
  * team member's PRs in v1.1
  * team member's PRs in v1.2
  * team member's PRs in v1.3
  * team member's PRs in v1.3b
  * team member's PRs in v1.4
