---
  layout: default.md
    title: " Project Portfolio Page"
---

# Project: Staff-Snap

> ### Ivan Lee's Project Portfolio Page!
Author: Ivan Lee @ivanleekk

### What is Staff-Snap?

---

Staff-Snap is a desktop HR management application used for managing employees. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 9 kLoC

### What have I done?

___

Given below are my contributions to the project.

* **Sorting feature**: Added the ability to sort the applicant list by various comparisons using the `sort` command.
    * **What it does**: allows the user to choose to sort the applicant list by name, phone, email, position applied
      for, or score. This can be in ascending or descending order.
    * **Justification**: This feature improves the product significantly because a user may want to sort the list via
      different methods throughout the use of Staff-Snap, such as by score in descending order to find the best
      applicants, or in alphabetical order by name when handling admin activities.
    * **Highlights**: The sort command has been designed in a modular manner, such that future enhancements are quick
      and easy to implement. As all descriptors used for comparison link to a field in the Applicant class, additional
      sorting criteria can be added quickly as and when they are needed.
    * **Credits**: My team for helping me find bugs and reviewing my PRs whilst I was working on this feature.

<br>

* **Filter feature**: Added the ability to filter the applicant list by one or more fields using the `filter` command.
    * **What it does**: allows the user to choose to filter the applicant list by name, phone, email, position applied
      for, or if it is less than or greater than the score specified, or by status.
    * **Justification**: This feature improves the product significantly because a user may want to filter the list of
      applicants down to contain only relevant applicants. For example, they might only want to see applicants who
      applied for a Testing Engineer role and got a score more than 7.
    * **Highlights**: The filter command was designed with the needs of a hiring manager in mind, and how they would use
      such a feature. As such, we included all relevant fields that a hiring manager might use in the hiring process,
      such as filtering applicants based on the scores they received.
    * **Credits**: My team for helping me find bugs and reviewing my PRs whilst I was working on this feature. As well
      as coming up with the base Applicant class and Score class which I used to help filter the applicants.

<br>

* **Status feature**: Added the ability to give applicants a status of __Undecided/Offered/Rejected__ using `status`
  command.
    * **What it does**: allows the user to choose to give applicants a status for their job application.
    * **Justification**: Hiring managers have to make the decision for who to hire and prune out unworthy candidates
      throughout the process. A status tracker will help them check who they have reviewed, who has been rejected, who
      has been offered, and who is still undecided. This reduces administrative headaches when a wrong message was sent
      to an applicant who may not be in the process anymore.
    * **Highlights**: The status command was designed to be extended if more specific tags are required, few changes
      have to be made to the files in order for it to work well. As part of making the command quicker to use,
      short-forms of the three valid statuses can be used in the command.
    * **Credits**: My team for helping me find bugs and reviewing my PRs whilst I was working on this feature. And for
      coming up with the idea to implement the status tracking.

<br>

* **Code contributed**: [Ivan Lee's RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=ivanleekk&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=ivanleekk&tabRepo=AY2324S1-CS2103T-W08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

<br>

* **Project management**:
    * Complete features required for releases.
    * Facilitate ad hoc meetings with the team to discuss future plans for the week.

<br>

* **Enhancements to existing features**:
    * Wrote additional tests to improve code coverage from 70.24% to 74.22% [#100](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/100)

<br>

* **Documentation**:
    * User Guide:
        * Added documentation for the features `filter`
          and `sort` [#142](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/142)
    * Developer Guide:
        * Added implementation details of the `sort` and `delete` features (Pull
          requests [#109](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/109), [#114](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/114))
        * Added Activity Diagrams for `sort` and `delete` features (Pull
          requests [#109](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/109), [#114](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/114))

<br>

* **Community**:
    * PRs reviewed (with non-trivial review
      comments): [\#108](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/108), [\#129](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/129), [\#210](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/210)
    * Reformatted User Guide from Google Docs for planned features to use
      GFMD instead [#32](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/32)
