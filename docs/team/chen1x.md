---
layout: page
title: Xin-An's Project Portfolio Page
---

### Project: InterviewHub

InterviewHub is a desktop application that helps engineering hiring managers to manage applicants and interviews.
The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created
with JavaFX. It is written in Java, and has about 18 kLoC.

I am responsible for the User Interface (UI) component of the project.
Given below are my contributions to the project.

* **New Feature**: Interview List Panel UI component
  * What it does: Allows the user to view Interview objects in a list format
  * Justification: This feature is the core of the UI component of InterviewHub. Creating this feature allows the user to view Interviews concurrently with applicants, facilitating the interview scheduling process.

* **New Feature**: Interview List Card color coded border based on interview status
  * What it does: Allows the user to view the interview status of all interviews at a glance, with red border signifying an incomplete interview and green border signifying a completed interview
  * Justification: This feature is essential to the functionality of InterviewHub. It facilitates mark interview as done command as well as sort command based on interview status. So that the user can see at a glance, all upcoming and incomplete interviews scheduled. As well as all completed and rated interviews to facilitate the post interview reviewing process.

* **New Feature**: Applicant List Card color coded border based on whether applicant has interview scheduled
  * What it does: Allows the user to view all applicant's status at a glance, with red border signifying no interview is scheduled and green border signifying an interview has been scheduled
  * Justification: This feature is essential to the functionality of InterviewHub. It allows the user to quickly identify which applicant has no interview scheduled yet and thereby proceed to schedule an interview for the applicant.

* **Code contributed**: [tP Code Dashboard summary](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=Chen1x&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=Chen1x&tabRepo=AY2324S1-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Drew early iterations of the UI design and class diagram for setting the general direction of this project
  * Gave ideas for multiple potential enhancements and features
  * Written multiple user stories
  * Created multiple issues based on user stories
  * Dedicated bug hunter, found and reported multiple bugs


* **Documentation**:
  * User Guide:
    * Added welcome message to set the target audience and value proposition
    * Enhanced the introduction section to better explain the product
    * Enhanced How to use this user guide section to better explain the user guide
    * Enhanced Quickstart section to include more details and accuracy in the steps
    * Enhanced GUI Summary section to include annotation for the GUI image
    * Enhanced the GUI image for better clarity
    * Added FAQs to guide users to install Java 11
    * Ensured overall standardisation of formats across all sections
    * Rigorous documentation bug hunting and fixing
    * Other misc. enhancements to the UG
  * Developer Guide:
    * Updated all hyperlinks to link to our team repo instead of AB3's repo
    * Updated the Architecture section to reflect our product's architecture
    * Updated the UI component section to reflect our product's UI component
    * Updated the Logic component section to reflect our product's Logic component
    * Updated the Model component section to reflect our product's Model component
    * Updated the Storage component section to reflect our product's Storage component
    * Rigorous documentation bug hunting and fixing
  * Proofread and removed all remaining traces of AB3 from the project code, markdown pages, UG and DG in `v1.2`


* **Review contributions**:
  * Examples of team PRs reviewed: [#257](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/257), [#56](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/56)
