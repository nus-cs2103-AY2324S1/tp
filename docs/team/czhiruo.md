---
layout: page
title: Chen Zhiruo's Project Portfolio Page
---
# Project: JobFindr

This portfolio provides an overview of my contributions to JobFindr.

## Overview
JobFindr is a desktop-based job application management application.

The inspiration for JobFindr came from the shared challenge faced by all members of our team - the daunting task of organising and keeping track of the numerous job applications we submit. Recognising that this is a common experience among NUS students, we felt a strong urge to develop a software solution that would make this process more manageable for students like us.

JobFindr was born out of this necessity, offering a one-stop solution for recording and managing your job applications effortlessly.

## Summary of Contributions

### Enhancements

1. Repurposed AB3 to fit the context of JobFindr
  * **What it does**: Instead of an address book containing a list of `Person` objects with fields such as `Name`, `Phone`
    and `Email`, the application was changed to contain a list of `Job` objects with the basic fields of `Company` and
    `Role`.
  * **Justification**: Since we intended to repurpose the application to cater to NUS fresh graduates who are searching
    for jobs, the current fields were irrelevant.
  * **Highlights**: This was a tedious process as it required many adjustments throughout the whole code. Not only did the
    classes representing the fields have to change, the command and parser classes had to be changed to interact with
    the fields differently. Sample data and test cases had to be changed as well.
    <br><br>
2. Improved the `find` command
  * **What it does**: The `find` command now allows for searching keywords by specific fields when a user provides a
    prefix. Users can also choose not to provide a prefix if they want to search in all fields. Multiple keywords can
    be specified, and multiple prefixes can be searched for at the same time.
  * **Justification**: Users might want to search in fields other than company name. By providing flexibility in the
    search process, users can find their job applications more efficiently.
  * **Highlights**: Implementing this functionality required changing the `NameContainsKeywordsPredicate` class to a
    more general `FieldContainsKeywordsPredicate`. The challenging aspect was combining multiple conditions together,
    especially in the case when no prefix is provided as it would mean that the keywords could appear in any field.
    <br><br>
3. Added the `sort` command
  * **What it does**: The `sort` command takes in a prefix and sorts the application list by the field specified by
    the prefix.
  * **Justification**: This allows our users to organise their application list by their preferred field, improving
    organisation.
  * **Highlights**: This was a rather simple feature to implement, but the use of a `FieldComparator` class made this
    function extendable should more fields be added later on.

### Code Contributed
My code contributions can be found here: [Reposense Report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=JeremyYong128&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

### Other Contributions
* **Project management**:
  * Actively assisted peers by providing technical advise and reviewing and approving PRs.
  * Conducted multiple checks on code quality and refactored messy code.
* **Documentation**:
  * Contributed extensively to both the User Guide (UG) and Developer Guide (DG).
* **Bug Fixes**:
  * Fixed bugs reported during PE dry run. (PR [#171](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/171))
* **Community**:
  * PRs reviewed (with non-trivial review comments): PR [#180](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/180)

## Contributions to the User Guide
*Given below are sections I contributed to the User Guide. They showcase my ability to write documentation targeting
end-users.*

## Contributions to the Developer Guide
*Given below are sections I contributed to the Developer Guide. They showcase my ability to write technical
documentation and the technical depth of my contributions to the project.*
