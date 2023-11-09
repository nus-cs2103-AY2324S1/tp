---
layout: default.md
  title: " Project Portfolio Page"
---

### Project: Staff-Snap
Author: Wang Jingting @jingting1412

Staff-Snap is a desktop HR management application used for managing employees. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Find Feature**: Enhanced the `find` command to find applicants by name using partial match search.

  * What it does: 
    The improved find feature allows the user to find an applicant by name using partial match search (i.e. `find ed` will result in both `Eddy Yang` and `Ed Lee`)
  
  * Justification: 
  Changing the find command from the original implementation of exact match search to partial match search improves the 
  convenience and efficiency of the find command. The original implementation, which only returns applicants if their name
  contains the exact keyword, is prone to user error and may miss relevant results if the input isn't spelled or formatted exactly as in the data.
  Changing the implementation to a partial match search allows for users to search for users more quickly and
  is more user-friendly as it accounts for variations or common misspellings.
  
  * Highlights: 
  This enhancement required an in-depth analysis of design alternatives and evaluation of their pros and cons. 
  The implementation too was challenging as it required changes to existing commands.


* **Displaying the overall score of an applicant**: Added a component that shows the overall score of an applicant.

  * What it does:
  Shows a graphical representation of the overall score of an applicant, with different colours for different scores.
  This display is responsive and will change whenever the overall score of an applicant changes.
  
  * Justification
  This display is important for the overall UI/UX of our application as it allows our users to immediately see the performance
  of an applicant. 

  * Highlights:
  The implementation of this feature is quite tricky as it has to be responsive to changes in the applicant's overall score.
  Care has to be put in to ensure the arcs of the circular graph has the correct length and colour for different scores.
  Much testing also has to be done to the component to ensure that all data is displayed correctly and updates correctly.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jingting1412&breakdown=true)

<div style="page-break-after: always;"></div>

* **Project management**:
  * Completed tasks for each milestone.
  * Facilitated meetings within the team.
  * Oversaw the preparation of documentation.


* **Enhancements to existing features**:
  * Updated current UI to increase viewing experience. (Pull requests [#132](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/132))
  * Wrote additional tests for existing features to ensure correctness. 
  (Pull requests [#70](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/70), [#203](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/203))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` and `save` 
    * Added additional resources such as a table of contents, glossary, definitions, diagrams, etc. to add more clarity 
    to our user guide and our application. (Pull request [#136](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/136))

  * Developer Guide:
    * Added implementation details of the `find` feature. (Pull request [#123](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/123))
    * Added the sections on non-functional requirements and glossary. (Pull request [#42](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/42))


* **Community**:
  * PRs reviewed (with non-trivial review comments): [#31](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/31), 
  [#211](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/211#pullrequestreview-1720643976)


* **Tools**:
  * Integrated a third party library (TestFX) to the project (Pull request [#132](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/132))
