---
layout: page
title: Lim YingXuan's Project Portfolio Page
---

# Project: SOCareers

SOCareers is your ultimate companion to managing your professional connections and internship applications. Designed
with School of Computing (SoC) students in mind, it is optimised for use via a Command Line Interface (CLI), enabling
you to organise your information more efficiently than traditional Graphical User Interface (GUI) apps if
you can type fast.

I have contributed to the project in the following ways:

### Code
You can find a summary of all my code contributions in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=lunaroddity&tabRepo=AY2324S1-CS2103T-T10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)!
1. **Viewing a person's contact**
   * Implemented the abstract `ViewCommand` class to allow for further extension of the view command 
   * Implemented the concrete `ViewPersonCommand` class to allow users to easily view one person’s contact at a time
2. **Adding an internship to a company contact**
   * Implemented the `AddInternshipCommand` class to add an internship to a specified company
3. **Editing an existing internship in a company contact**
   * Implemented the `EditInternshipCommand` class to keep information on internships up-to-date
   * Improved the abstraction and code quality of the `EditCommandParser`
4. Created efficient and effective test code for the classes I have implemented to ensure they function according to
  specifications

### Documentation
**User Guide (UG)**
1. Added documentation for the view person, add internship and edit internship commands 
2. Added a prefix summary for easy reference to the constraints associated with each prefix 
3. Polished the language of the entire UG to be more enthusiastic and user-centric 
4. Standardised the formatting used throughout the entire UG to improve consistency and clarity 
5. Revised the UG to include more callouts to enhance its visual appeal and readability

**Developer Guide (DG)**<br><br>
Added implementation details, design considerations, alternatives considered and UML diagrams for ViewPersonCommand and EditPersonCommand.

### Team-based tasks
* Attended weekly meetings and contributed to the discussion of the implementation of features and other future plans actively 
* Came up with over 20 user stories

### Reviewing and mentoring
Reviewed more than 25 PRs and left members constructive feedback to help improve overall code quality and organisation
* Examples
  * [#94](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/94)
  * [#118](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/118)
  * [#132](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/132)
  * [#196](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/196)
  * [#202](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/202) (though it was closed, my review contributed to the changes seen in [#203](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/203))

### Beyond the project team
1. Reviewed the UG and v1.3 `.jar` release as part of the Practical Exam Dry Run and provided constructive feedback by opening issues under the name Tester D [CS2103T-T14-1](https://github.com/AY2324S1-CS2103T-T14-1/tp)
2. Organised an informal class-wide peer review for the class CS2103T-T10 
3. Reviewed the UG, DG and v1.4 .jar release for my classmates and provided constructive feedback on Google Docs
  * [CS2103T-T10-1](https://github.com/AY2324S1-CS2103T-T10-1/tp)
  * [CS2103T-T10-2](https://github.com/AY2324S1-CS2103T-T10-2/tp)
4. Opened several issues on the CS2103T forum and shared solutions to aforementioned issues
   * [#84](https://github.com/nus-cs2103-AY2324S1/forum/issues/84)
   * [#91](https://github.com/nus-cs2103-AY2324S1/forum/issues/91)
