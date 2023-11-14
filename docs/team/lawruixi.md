---
  layout: default.md
  title: "Law Rui Xi's Project Portfolio Page"
---

### Project: NUSearch

NUSearch is a desktop application used for consolidating NUS professors, teaching assistants (TAs) and students’ profiles. The user interacts with it using a CLI, it has a GUI created with JavaFX, and is written in Java.

Given below are my contributions to the project.

##### **New Features**: 
- Added relevant classes to be used by the Person model:
 - `Contact`
 - `Role`
 - `Course`
 - `Tutorial`
- Updated the Person model, as well as all other places in the entire codebase, to use the aforementioned classes instead.
- Added an autocomplete feature;
  - Allows the user to press Tab to cycle through possible autocompletions.
  - Also changed the UI appropriately to support it.

##### **Bug Fixes**
- Fixed a bug that arose where new code was written based on outdating code on a newer branch, resulting in compilation failure Pull Requests.
- Fixed at least 27 bugs related to the User Guide.
    - Most of these are typos.
    - Some of these are grammar mistakes.
    - But a few are pretty large and required rewriting entire sections of the User Guide; see **Documentation** below.
- Fixed a bug where roles weren't being saved properly.
- Fixed a bug where data was not saved upon exiting the app.
- Fixed a bug with autocomplete where the user could not move the cursor in the text field.
- Fixed a bug where searchcourse produced the wrong error message if called with no arguments.

##### **Code contributed**: 
- [RepoSense link.](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lawruixi&breakdown=true)

##### **Project Management**:
- Added issues on GitHub to keep track of bugs whenever they appear.

<div style="page-break-after: always;"></div>

##### **Documentation**:
  * User Guide:
    * Added a mockup of the UI created using HTML / CSS.
    * Redesigned the User Guide using various features (boxes, HTML elements) provided by Markbind.
    * General beautification of the User Guide.
    * Ensured User Guide is consistent with the code output.
  * Developer Guide:
    * Added a list of Non-Functional Requirements to the Developer Guide.
    * Updated the UML Guide for Model section with the new attributes assigned to the Model class.
    * Added and formatted the Autocomplete section in the Developer Guide.
    * Added the UML Activity Diagram for Autocomplete section in the Developer Guide.

##### **Community**:
  * PRs reviewed (with non-trivial review comments):
    - Team Members' PRs in 1.2.
    - Team Members' PRs in 1.3.
    - Team Members' PRs in 1.4.
    - Some examples of PRs I've written comments for are:
        - [PR for implementing `add` and `search` features.](https://github.com/AY2324S1-CS2103T-F08-0/tp/pull/78)
        - [PR for implementing `favlist` feature.](https://github.com/AY2324S1-CS2103T-F08-0/tp/pull/152)
