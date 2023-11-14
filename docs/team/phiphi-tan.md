---
layout: page
title: Phi Phi's Project Portfolio Page
---

### Project: CodeSphere

**CodeSphere** is a desktop **student management app, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI).
It is an app targeted at Teaching Assistants (TAs) in the National University of Singapore (NUS) School of Computing (SoC).

Given below are my contributions to the project.
* **New Feature**: Remark Command (PR [#63](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/63)).
  * What it does: The user can add text as a Remark to a Student, which will be displayed on the student's list entry
  * Justification: This feature allows the user to store additional information regarding students,
    which benefits their student management significantly

* **New Feature**: Command History (PRs [#158](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/158), [#184](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/184)).
  * What it does: Through utilising the *UP* and *DOWN* arrows on the keyboard,
    users can access commands that were previously inputted into the application.
    Commands that result in an error will be shown in red.
  * Justification: This feature allows the user to be able to access and edit erroneous commands or repeat previous actions
    without having to re-type the entire command again

* **UI Feature**: Display Panel (PR [#120](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/120))
  * What it does: Attached to the 'Main Window' as part of the application's UI, this display panel provides an easy way
    to toggle between 3 different views (Splash Screen, Course Panel, Combined Panel).
  * Justification: Due to commands working in different 'stages' of our application, the user interface should
    be updated accordingly as well to reflect these different stages. By attaching view panels to a single 'display panel',
    what is displayed to the user can easily be changed and updated according to commands and logic flows.
  * Highlights: Upon application start-up, a splash screen of the CodeSphere logo will be displayed to the user, which
    gives the app a more 'polished' feel and improves the general user experience

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=phiphi-tan&breakdown=true)
<div style="page-break-after: always;"></div>

* **Enhancements to existing features**:
  * **UI Enhancements**: Compared to AB3, making the following UI enhancements greatly improves the UI/UX of our application.
      Implementing them requires a comprehensive understanding of JavaFX, FXML and CSS, as well an understanding of
      the project's observer patterns and when to update UI elements.
      The UI changes were constantly iterated and updated with peer feedback, and requires an extensive knowledge of user design.
    * Course list panel (PR [#82](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/82))
      * With the enhancements made over the AB3 codebase, the User Interface had to be updated respectively
        to fit with the addition of the `Course` class. This UI change enables the user to view all the courses initially.
    * Combined panel sidebar (PR [#96](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/96))
      * Provides a sidebar of all courses alongside the view of the particular student list of a selected course
        The selected course in the side panel is highlighted while the other courses are faded.
      * The combined panel serves as a direct upgrade to the previous 'Student List' panel
        by creating a 'sidebar' that enables the user to maintain a view of the summary statistics of other courses while working on a separate course,
        greatly increasing the overall user experience.
    * Help window and header panel (PR [#140](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/140))
      * Help window now contains additional button that directly opens the link in the user's default web browser.
        Header 'help' option now contains links to the User Guide, Developer Guide and Team GitHub Repository that will automatically
        open in the default browsers, and can be selected with keyboard shortcuts *F1*, *F2* and *F3* respectively.
    * Miscellaneous CodeSphere UI Upgrades (PR [#136](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/136))
      * User Interface was updated from AB3 to better fit our CodeSphere application (fonts, colours, team logo, icons etc.)
      * Resizing application window will have UI items adjust and text wrap accordingly
        (PRs [#150](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/150), [#301](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/301))
  * Modified existing test cases and added new tests (PRs [#249](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/249),
    [#264](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/264), [#265](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/265),
    [#274](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/274)).
<div style="page-break-after: always;"></div>

* **Documentation**:
  * User Guide:
    * Added in a Beginner's Tutorial that walked new users through navigating the application and
      how to use some of the more basic functions in CodeSphere (PR [#259](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/259)).
  * Developer Guide:
    * Updated the DG regarding Storage as CodeSphere introduced a new 'InputStorage' class (PR [#184](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/184/files))
    * Updated and rewrote the portion on User Interface (UI) with new class diagrams as well as in-depth
      explanations regarding the different UI components that were added in
      (PR [#118](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/118)).
    * Added in the *Appendix: Effort* that details an estimation on the total project effort
      (PR [#382](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/382)).
    * Added in the *Appendix: Planned Enhancements* that lists fixes that we propose to add in the near future.
      (PR [#382](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/382)).

* **Community**:
  * Reviewed PRs, and suggested improvements for project management such as adhering to Javadocs requirements and code quality.
  * Reported bugs for team *CS2103T-W13-3* during PE-D and suggested possible areas of improvements
