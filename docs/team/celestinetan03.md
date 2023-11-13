---
  layout: default.md
  title: "Project Portfolio Page"
---

# Project: Staff-Snap
### Celestine Tan's Project Portfolio Page
Author: Celestine Tan @celestinetan03

### Overview
**Staff-Snap** is a desktop hiring management application used for managing applicants during the recruitment cycle. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 
9,000 lines of code.

### Summary of Contributions
Given below are my contributions to the project.

**New Feature**: Added the rating component for each interview.
* What it does: allows the user to rate an applicant's performance after an interview.
* Justification: This feature provides a convenient way for the user to take note and keep track of an applicant's performance.
* Highlights: This enhancement integrates with the current interview component and requires changes to the existing 
interview commands.

**New Feature**: Added a score component that keeps track of an applicant's overall average rating from all interviews.
* What it does: calculates the average rating of all the applicant's rated interviews and display it to the user.
* Justification: This feature provides a metric of comparison between applicants to aid user in the hiring process.
* Highlights: The implementation was challenging as only rated interviews are taken into account for the overall score.
Moreover, the score is updated using different logics in different situations - when an interview has a new rating, 
when an existing rating of an interview is edited, and when an interview is deleted.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=celestinetan03&breakdown=true)

**Project management**:
* Maintained the issue tracker of the team's GitHub repository. [Issues](https://github.com/AY2324S1-CS2103T-W08-1/tp/issues)

**Enhancements to existing features**:
* Wrote additional tests for existing features to increase coverage
    [\#88](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/88)
    [\#110](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/110)
    [\#121](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/121)

**Documentation**:
* User Guide:
  * Added documentation for the features `add` and `edit`
  * Updated documentation with the rating component for the features `addi` and `editi` [\#110](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/110)
  * Added the Frequently Asked Questions (FAQ) section and updated the glossary with more terms [\#144](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/144)
* Developer Guide:
  * Added implementation details of the `add` and `edit` component:
      [\#119](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/119)
      [\#211](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/211)
  * Added use cases and user stories:
      [\#47](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/47)
      [\#85](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/85)
      [\#211](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/211)
  * Added instructions for manual testing [\#211](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/211)
  * Added sequence and activity diagram for `add` command [\#229](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/229)

**Community**:
* PRs reviewed (with non-trivial review comments):
    [\#100](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/100)
    [\#109](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/109)
    [\#129](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/129)
