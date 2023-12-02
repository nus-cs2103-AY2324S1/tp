---
layout: page
title: Glenda Chong Rui Ting's Project Portfolio Page
---

# Project: LinkMeIn

LinkMeIn is a desktop address book application for NUS Computer Science students to track their internship applications.
The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX. It is written in Java, and has about
~10 kLoC.

## Summary of Contributions

Given below are my contributions to the project.

* **Code Contributed:** The code that I have contributed can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=GlendaChong&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22).

* **New Feature: Filter Companies**
  * **What it does:** Filters the list of companies by application status.
  * **Justifications:** Users may wish to only view internship applications of a particular status. For example, they may wish to put more attention into applications that are pending interviews, so that they can prepare for the interviews.
  * **Highlights:** The `filter` command relies on the new predicate, `ApplicationStatusPredicate`, to test if the company's application status matches the application status given by the user. The `filter` command also uses the updated `CompanyListPanel` to display the filtered list of companies.

* **New Feature: Update GUI for `CompanyListPanel` and `CompanyDetailPanel`**
  * **What it does:** `CompanyListPanel` and `CompanyDetailPanel` are of a similar colour theme. All the fields displayed will not be truncated, and will be soft-wrapped instead.
  * **Justifications:** This update will improve the user experience of the GUI, and allow them to view the full information of the company.

* **Enhancement to Existing Features:**
  * **Enhance Error Messages for Fields in Company Model**
    * **What it does:** For each field, there will be 2 main error messages, 1 for when the field is empty and 1 for when the field does not follow the valid regex.
    * **Justifications:** The user needs to know exactly what is wrong with the input, and be able to correct it easily.
    * **Highlights:** While the change was not difficult to implement, it was very tedious due to the large number of fields in the company model. It was very crucial to also not break the existing code base.
  * **Enhance Existing `add` Command**
    * **What it does:** The `add` command now accepts all the new fields added to the company model, including `Role`, `RecruiterName`, `ApplicationStatus` and `Deadline`. The error messages for the `add` command now also differentiates between empty inputs, invalid fields, or duplicate fields.
    * **Justifications:** The `add` command is one of the most important commands in LinkMeIn. By adding more fields to the `add` command, it allows the user to add more information about the company. More specific error messages also allow the user to know exactly what is wrong with the input, and be able to correct it easily.

* **Contributions to the UG**
  * **Navigating the User Guide:** Add the annotations and icons using bootstrap alert icons.
  * **Quick Start:** Improve on this section with step-by-step instructions, including adding more details and screenshots.
  * **Features:** Add a company (`add`),  List all companies (`list`), Filter companies by application status (`filter`)
    * Include description of command, format, notes, tips, examples and possible errors.
  * **Glossary:** Add definitions of key terms, parameters description, application status parameter description and email format description sections.
  * **Command Summary:** Add the command summary table.

* **Contributions to the DG**
  * Add use cases.
  * Add 3 planned enhancements.
  * Add non-functional requirements.
  * Add implementation details for `filter` and `add` commands.
  * Add instructions for manual testing section.

* **Contributions to Team-Based Tasks**
  * Set up CodeCov for the project.
  * Update landing page for the project website and AboutUs page.
  * Manage milestones, change their due dates, and wrap-up milestones.
  * Review PRs for the team regularly.
    * Non-trivial PRs reviewed: [#271](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/271), [#261](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/261), [#226](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/226), [#156](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/156), [#169](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/169), [#142](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/142), [#135](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/135), [#109](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/109), [#104](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/104).

* **Contributions beyond the Project Team**
  * Extensive number of bugs reported for the other team's product during PE-D (28 bug reports)
