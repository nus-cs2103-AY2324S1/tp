---
layout: page
title: Chen Peiran's Project Portfolio Page
---

# Project: JobFindr

This portfolio provides an overview of my contributions to JobFindr.

## Overview

JobFindr is a desktop-based job application management application.

The inspiration for JobFindr came from the shared challenge faced by all members of our team - the daunting task of
organising and keeping track of the numerous job applications we submit. Recognising that this is a common experience
among NUS students, we felt a strong urge to develop a software solution that would make this process more manageable
for students like us.

JobFindr was born out of this necessity, offering a one-stop solution for recording and managing your job applications
effortlessly.

## Summary of Contributions

### Enhancements

1. Added `status` field
  * **What it does**: Users can indicate whether the application is `PENDING`, `APPROVED`, `REJECTED`.
  * **Justification**: Users should be able to add the status so that they can better track and decide on the next 
  course of action of the applications.
  * **Highlights**: Relevant classes such as Status class had to be added and relevant test cases such as StatusTest 
  had to be implemented.
  <br><br>
2. Added `interview add` command
  * **What it does**: The feature enables users to add interviews to a job application.
  * **Justification**: Since interviews are almost always the mandatory next step after applying for a job, 
    it is significant that users are able to add interview details to their applications.
  * **Highlights**: Implementing the command from scratch posed many issues. For instance, Storage had to be modified to 
    save interviews. This was challenging as previous commands did not require us to look into the Storage section, 
    hence a lot of time was spent scrutinising the section in order to modify it.  
    Files such as TypicalInterviews also had to be added for testing purposes.
    <br><br>
3. Improved `sort` command to sort by `status`
  * **What it does**: The `sort` command is now able to take in the status prefix and sort the application list by status.
  * **Justification**: This allows our users to organise their application list by the status, improving organisation.
  * **Highlights**: Implementing sorting by status was not difficult, but writing testcases for the entire 
  `sort` command was an enriching experience as the `sort` command requires extensive testing.

* **Code contributed**:
    * My code contributions can be found here: [Reposense Report](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=peiran18&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)
* **Project management**:
    * Actively assisted peers by providing technical advice and reviewing and approving PRs.
    * Created and tracked milestones and issues on GitHub.
* **Documentation**:
    * User Guide (UG): Added sections for `interview add`, `list`, and `clear` commands as well as command summary and 
    overall formatting.
    * Developer Guide (DG): Added sections for `delete` and `list` commands, and `Model` component.
* **Bug Fixes**:
    * Fixed bugs reported during PE dry run. (PR [#173](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/173))
* **Community**:
    * PRs reviewed (with non-trivial review comments): PR [#66](https://github.com/AY2324S1-CS2103T-W12-3/tp/pull/66)

