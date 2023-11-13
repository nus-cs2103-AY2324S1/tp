---
layout: page
title: Chen Zhiruo's Project Portfolio Page
---
# Project: JobFindr

This portfolio provides an overview of my contributions to JobFindr.

## Overview

JobFindr is a desktop-based job application management application.

The inspiration for JobFindr came from the shared challenge faced by all members of our team

- the daunting task of organising and keeping track of the numerous job applications we submit. Recognising that this is
  a common experience among NUS students, we felt a strong urge to develop a software solution that would make this
  process more manageable for students like us.

JobFindr was born out of this necessity, offering a one-stop solution for recording and managing your job applications
effortlessly.

## Summary of Contributions
1. Kept track of the test codes and test code coverage
2. Updated User Guide
3. Updated Developer Guide

### Enhancements

1. **Add `Industry` field**:
  * What it does: Users can add in `industry` as a field for their job application. This field is optional.
  * Justification: To cater to users who may want to categorise their applications by industry for better organisation.
  * Highlights: The addition of a new field to `Job` as a `industry` class required updating a lot of the previous
methods and classes associated with `Job` which can be tedious and hard to debug.

2. **Implement `interview delete` command**:
* What it does: Users can delete an interview from a job application.
* Justification: Since users may want to delete interviews after they are over to prevent clutter in their application,
it is significant for them to have the option to delete their interview.
* Highlights: When first implementing this command, it took some time to decide the formatting of the command that would
make the most sense for users since it required a job and interview index.

3. **Implement `interview edit` command**:
* What it does: Users can edit any field(s) of an interview from a job application.
* Justification: For users to easily update any modification in the interview if they keyed in fields wrongly or there
is a change to the original interview.
* Highlights: Implementing this command required implementing a nested class `EditInterviewDescriptor` which made it
more complicated to carry out testing for the EditCommand.

### Other Contributions

* **Project management**:
  * Actively assisted peers by providing technical advice and reviewing and approving PRs.
  * Conducted multiple checks on test cases to improve test code coverage.
* **Documentation**:
  * Contributed to the User Guide (UG)
  * Contributed to the Developer Guide (DG).
* **Bug Fixes**:
  * Fixed bugs reported during PE dry run. 
    * Update delete interview error message (PR [#176](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/176))
    * Update help command window message (PR [#167](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/167))
* **Community**:
  * PRs reviewed (with non-trivial review comments): (PR [#189](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/189_) [#190](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/190))

### Code Contributed

My code contributions can be found
here: [Reposense Report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=czhiruo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)