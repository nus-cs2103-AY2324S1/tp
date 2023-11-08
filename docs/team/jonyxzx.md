---
layout: page
title: Jony's Project Portfolio Page
---

### Project: InterviewHub

InterviewHub is a desktop application that helps engineering hiring managers to manage applicants and interviews. The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about 18 kLoC.

Given below are my contributions to the project.

* **New Feature**: `find-i` command (PR: [#51](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/51))
  * What it does: Allows the user to search for the target interviews based on the input job description with the command `find-i`.
  * Justification: This feature improves the product significantly because a user can conveniently search for all the interviews based on the attached job role. This would assist the users to reduce the time taken to search interviews manually.
  * Highlights: This feature requires in-depth understanding of how the list is being handled in the program such as ObservableList and FilteredList. The feature emphasises on filtering through the list without modifying the main interview list stored in the program. Therefore, preventing accidental modification or removal.
  
* **New Feature**: `rate` command (PR: [#86](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/86))
  * What it does: Allows the user to rate an interview after it has been completed to record the applicant's performance.
  * Justification: This feature is a core feature to support post interview process to keep a record of each applicant rating in InterviewHub.
  * Highlights: The rating is a standalone class which allows easy extension in the future. Moreover, the rate command itself is self editing, which removes the need for delete/edit rating.

* **New Feature**: `sort-rate` command (PR: [#108](https://github.com/AY2324S1-CS2103T-T11-2/tp/pull/108))
  * What it does: Allows the user to sort the interview list in descending order based on the rating of the interview.
  * Justification: This feature completes the post interview process to view the top candidates in the entire interview list.
  * Highlights: The sorting requires in-depth understanding of the comparator and comparable interface. To maintain the integrity of the data, the sorting is being implemented in the UniqueInterviewList. The feature also maintains its extendability by keeping the sorting function general, allowing new comparator class to being pass in to sort the list.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jonyxzx&breakdown=true)

* **Project management**:
  * Managed the release for v1.2 jar file.
  * Managed the release for v1.3(trial) jar file.
  * Managed the release for v1.3.1 jar file.
  * Documented v1.3 demo into the project google documents.

* **Documentation**:
  * User Guide:
    * Added the full table of contents.
    * Added the glossary section.
    * Added the how to use the user guide section.
    * Rearrange and added the entire command summary section.
    * Handled the formatting of the User Guide.
    * Modified the image for help feature.
    * Added documentation for the features `find-i`, `sort-rate` and `rate`
  * Developer Guide:
    * Added the user stories.
    * Added documentation for the features sort, rate, and find interview.
    * Formatted the structure of the Developer Guide.

* **Community**:
  * 41 team pull requests reviewed.
  * Reported [10](https://github.com/Jonyxzx/ped/issues) bugs for other team during PE-D.
  * Helped in finding bugs in teammates' features.
