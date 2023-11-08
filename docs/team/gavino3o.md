---
layout: page
title: Gavin Chiam Xiang Zhe Project Portfolio Page
---

## Project: DoConnek Pro

### Overview:

DoConnek Pro offers General Practitioner Clinic Management Staff an efficient desktop application for organizing patient and specialist contact details.
Designed to cater to Command Line Interface (CLI) enthusiasts while also featuring an intuitive Graphical User Interface (GUI), it enables swift execution of routine tasks.

### Summary of Contributions

- **Code Contributed:** 
  - [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=Gavino3o&breakdown=true)
- **Enhancements implemented:**
  - Added a new `find` command 
    - What it does: Allows the user to search for patient or specialist records by their unique or common attributes.
    - Justification: This feature allows users to find patient or specialist records quickly without the need of relying on the scroll bar of the GUI. This can greatly enhance the user experience and speed up user workflow.
    - Highlights: This feature required detailed and careful analysis for proper solution architecture. Numerous edge cases were needed to be considered and handled accordingly. The initial design choice was later modified in a later pull request which allowed for easier extension and testing.
    - Credits: Assisted by team member Alvin Lim in the design and implementation of the solution.
  - Modified `delete` command to allow for mass deletion
    - What it does: The newly enhanced `delete` command can take in multiple indexes and delete multiple patient or specialist records at once. The previous implementation only allowed for deletion of records by a single index. 
    - Justification: This feature aids users in organising their patient or specialist records by allowing them to delete multiple invalid or redundant records at once, instead of deleting one by one. Greatly enhancing user experience.
    - Highlights: Various edge cases were considered as improper implementation can result in incorrect deletion of records and wrong command result messages.
  - Modified `edit` command to edit records in view panel
    - What it does: The new `edit` command edits records in the view panel instead of editing records by index in the persons list.
    - Justification: With the addition of the new view panel, users can now view patient or specialist records in more detail in another panel. Editing records in the view panel is more intuitive for overall user workflow.
    - Highlights: Refactored old `view` command code implementation for better code quality and extensibility.
- **Contributions to Documentation:** 
  - **User Guide**:
    - Added documentation for the `list`, `find`, `delete` and `edit` commands.
    - Fixed and tidied wrong user guide information throughout document.
    - Updated images for newly implemented UI and URLs.
  - **Developer Guide:** 
    - Added developer documentation for the new `find` command implementation.
    - Created all UML diagrams used to document the new `find` command implementation.
- **Contributions to team-based tasks:** 
  - Managed and wrapped up the release for v1.3.
  - Helped maintain the issue and PR trackers.
  - Helped in manual testing of newly implemented enhancements and features developed by team members.
  - Update index.md.
- **Review/mentoring contributions:** 
  - Pull Request reviewed (with non-trivial review comments):
    - https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/159
    - https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/111
    - https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/93
    - https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/88
    - https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/76
    - https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/62

