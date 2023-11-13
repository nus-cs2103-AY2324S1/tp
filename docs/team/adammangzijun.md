---
layout: default.md
title: "Adam's Project Portfolio Page"
---

### Project: MedBook

## Overview:

MedBook - Medbook streamlines patient management for healthcare professionals.
It simplifies the process of accessing patients' contact information and medical records, making it easier for healthcare providers to efficiently coordinate care.

## Summary of Contributions

- Code contributed: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=adammangzijun&breakdown=true)
- Enhancements implemented:

  - **Edit Patient Details Feature**:
    Updated the edit functionality within MedBook, enabling users to edit the additional details of a certain patients as well.

  - **Edit Record Details Feature**:
    Developed a new functionality within MedBook, enabling users edit details of patient records.

- Contributions to the User Guide (UG):

  - Added new sections explaining the usage of the `editpatient` and `editrecord` commands I've implemented.
  - Touched up on the feature summary table.
  - Tidied, sorted and double-checked UG for PE-D, ensuring readability.
  - Reviewed the UG together as a group for final submission

- Contributions to the Developer Guide (DG):
  - Added implementation details for `editpatient` command.
  - Added implementation details for `editrecord` command.
  - Added `editpatient` and `editrecord` sequence diagram.
  - Reviewed the DG together as a group for final submission

## Contributions to Team-Based Tasks

- Review/mentoring contributions:
  - Some links to PRs reviewed. [#26](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/26) , [#36](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/36), [#64](https://github.com/AY2324S1-CS2103T-T12-4/tp/pull/64)
  - Helped to identify potential bugs through questions prompting. For instance, I've successfully identified a bug in the file attachment command when my teammate first implemented it. Provided some possible suggestions to resolve the issue.
  - Assisted in the explanation of certain parts of the code such as how to enforce non-duplication of prefixes in the command.

## Contributions Beyond the Project Team

- Evidence of helping others:
  - Bugs reported in other team's products.
  - Raised technical issues in the forum which may apply to others as well.
- Evidence of technical leadership:
  - Sharing useful information in the forum.

## Project Effort Explanation
- Depth of Enhancements:
  - The enhancements I implemented required a deep understanding of MedBook’s existing architecture. For example, the Edit Patient Details Feature not only involved interfacing with the GUI components but also necessitated intricate handling of data validation and error-checking to maintain data integrity. It was essential to ensure that edited patient details were consistent across all instances of the application, requiring careful consideration of concurrent data access and potential race conditions.

- Complexity of Implementation:
  - The Edit Record Details Feature introduced a new layer of complexity as it required manipulating nested data structures. It was not just about updating fields but also about ensuring that related records remained in sync. Crafting this feature demanded a thorough understanding of how records were interrelated and the potential cascading effects of any changes.

- Completion Criteria:
  - Enhancements are considered complete when they have been rigorously tested, reviewed by peers, and all identified bugs have been resolved. For instance, the edit functionalities underwent multiple cycles of code reviews and were subjected to both unit and integration tests. They have been stress-tested to handle edge cases, ensuring the application's robustness.
- Impact of Enhancements:
  - The enhancements have streamlined the workflow for end-users, reducing the number of steps needed to update patient and record details significantly. By refining the user journey, healthcare providers can now access and modify data with reduced effort and increased accuracy, ultimately facilitating better patient management.
