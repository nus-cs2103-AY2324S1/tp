---
  layout: default.md
  title: "Yim Sohyun's Project Portfolio Page"
---

# Project: SEPlendid

**SEPlendid** is a versatile application used by prospective exchangers to plan their Student Exchange Programme (SEP)
study plans. Students from NUS Computing can use this CLI-based application, with a beautiful GUI, to view partner
universities and course mappings. The application is also bundled with many other useful features.

## Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: ```partnercourse```
  * Added `list`, `add` and `delete` commands for partnercourse.
  * Note: This data type is quite similar to local course (done by Lam Cheng Hou) but with additional parameter - 
  university.
  **Sub Feature 1**: ```list```
  * What it does:
    - Lists all partner courses, including relevant information of each partner course. 
  * Justification: This feature is useful for users to explore and understand the complete array of partner courses 
  available, facilitating informed decision-making.
  * PR:
    [\#80](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/80)
    [\#84](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/84)
  
  **Sub Feature 2**: ```add```
  * What it does:
    - Adds new partner course
  * Justification: This feature is useful for users to contribute to SEPlendid's database, ensuring that the partner 
  course catalogue remains comprehensive and up-to-date.
  * PR:
    [\#80](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/80)
    [\#84](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/84)

  **Sub Feature 3**: ```delete```
  * What it does:
    - Deletes partner course
  * Justification: This feature allows users to maintain the relevance of the partner course catalogue by removing
  partner courses that are no longer applicable.
  * PR:
    [\#80](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/80)
    [\#84](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/84)

* **New Feature**: ```sort```
  * What it does: 
    - Sorts localcourse/partnercourse/university list according to their attributes
  * Justification: This feature is essential for allowing users to view the data objects sorted in different attributes,
allowing quick and efficient retrieval of information.
  * PR: 
  [\#115](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/115)
  [\#137](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/137)

* **New Feature**: ```update partnercourse```
  * What it does:
    - Updates specified attribute in partnercourse.
  * Justification: This feature enhances SEPlendid's utility by allowing users to reflect real-time changes in partner 
  course information. This contributes to the overall accuracy and reliability of the data maintained
  by SEPlendid.
  * PR:
  [\#157](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/157)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=dlathyun&breakdown=true)

* **Project management**:
  * Made and linked issues to my PRs.
  * Did my fair share of reviewing and merging PRs.

* **Enhancements to existing features**:
  **```attribute```**
  * Added attribute enum class to store the attributes of different data types, and for different commands.
  * This provided a structured way to handle our many data types with different attributes.

* **Documentation**:
  * User Guide:
    * Added documentation for partner course related commands
    * Added documentation for sort command
  * Developer Guide:
    * Added documentation for delete, update and sort commands

* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [\#68](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/68)
    [\#88](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/88)
    [\#92](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/92)
    [\#110](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/110)
    [\#161](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/161)
    [\#165](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/165) 
    [\#169](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/169)
    [\#171](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/171)
    [\#173](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/173)
    [\#249](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/249)
  * Reported bugs for other teams: [PED repo](https://github.com/dlathyun/ped/issues)

