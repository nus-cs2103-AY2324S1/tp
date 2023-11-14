---
layout: page
title: Tiong Jjyi's Project Portfolio Page
---

### Project: CodeSphere

**CodeSphere** is a **desktop contact management app, optimised for use via a Command Line Interface (CLI)**
while still having the benefits of a Graphical User Interface (GUI).
It is an app targeted at enabling Teaching Assistants in NUS School of Computing to better manage their students.

Given below are my contributions to the project.

* **New Feature**: Implement Adding Pending Question Command (PR [#88](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/88))
    * What it does: Adds a pending question to a student.
    * Justification: This feature allows TAs to keep track and manage questions from their students more effectively. When a student approaches with a question, the TA can use this command to record the question under the student.
    * Highlights: Pending question is optional to a student. This feature allows TAs to decide whether to include pending questions when recording a student's information in the system. We have also enhanced the command to ensure that every pending question must be non-empty.

* **New Feature**: Implement Remove Command to Remove Remark and Pending Question (PR [#113](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/113))
    * What it does: This feature enables TAs to efficiently manage and maintain student records by removing remarks and pending questions associated with a student's profile.
    * Justification:  With the ability to remove remarks and pending questions, TAs can maintain a concise and focused record for each student, making it easier to access important information and provide more effective support.
    * Highlights: Theis feature offers versatile data management, allowing TAs to conveniently remove pending questions, remarks, or **both** **at the same time**.

* **New Feature**: Finding Course in Home Page. (PR [#146](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/146))
    * What it does: This feature allows user to quickly find course(s) directly from the home page.
    * Justification: TAs often juggle multiple classes simultaneously. This feature simplifies their access to specific courses by allowing keyword-based searches. It also enhances the overall user experience and fosters personalization.
    * Highlights: This feature enables simultaneous searching of multiple courses using keywords or even substrings of course names, enhance efficiency and precision in course discovery.

* **New Feature**: Implement Reset Command in Home Page (PR [#146](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/146))
    * What it does: This feature enables TAs to reset the course list to its original state to view the entire course list after `find` command.
    * Justification: After executing `find` command in Home Page, the user might want to view the entire list of course, hence a `reset` is essential to achieve that.
    * Highlights: The command is short and easy to use.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=tiongjjyi&tabRepo=AY2324S1-CS2103T-W15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Create milestone v1.1 within our team's GitHub organization. This milestone represents a significant step in our project's progress, enabling us to effectively track and manage our development goals.
    * Delete existing labels and add all the issue type labels, priority labels and bug severity labels.

* **Enhancements to existing features**:
    * Improve the `RemarkCommand` from AB3 by incorporating a check for an empty string and display an error message if such a string is provided.(PR [#179](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/179))
    * Enhance the tagging functionality from AB3 by constraining the tag types to only include GOOD, AVERAGE, and POOR. Implement this restriction by creating an enum class for the tag.(PR [#53](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/53))
    * Fix several error message to increase user experience.(PR [#179](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/179), [#190](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/190))
    * Add new tests and modified existing tests. (PR [#239](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/239), [#246](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/246), [#254](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/254), [#263](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/263), [#283](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/283))

* **Documentation**:
    * User Guide:
        * Update first version of UserGuide after project refinement.(PR [#55](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/55), [#57](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/57))
        * Add all the command success screenshots in User Guide to help users understand the features and functionality of our app. (PR [#344](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/344))
        * Annotate and add the screenshots to Tutorial for Beginners.(PR [#365](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/365))
    * Developer Guide:
        * Added use case for `PendingQuestionCommand`.(PR [#117](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/117))
        * Described implementation for `PendingQuestionCommand`. (PR [#124](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/124))
        * Created the sequence and activity diagrams for the `pq` command. (PR [#348](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/348), [#343](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/343), )
        * Added instructions for manual testing for `RemarkCommand`(PR [#137](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/317))

* **Community**:
    * Reported 25 bugs for team CCS2103T-T09-1 during PE-D and suggested possible improvements.
    * Reviewed PRs and provided suggestions for improvement.
