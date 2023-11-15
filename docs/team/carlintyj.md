---
layout: page
title: Carlin Tan's Project Portfolio Page
---

### Project: NUSCoursemates

NUSCoursemates is a desktop address book application used for keeping track of the courses your contacts are taking.
The user interacts with it using a Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created with JavaFX.
It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add student's course in AddCommand. (Pull Request: [#57](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/57))
  * What it does: It allows the user to indicate the course that the student's contact is taking.
  * Justification: This feature is inline with our project specification of tracking the courses.
  * Highlights: This improvement affects both existing commands and commands that will be implemented in the future. It necessitated a thorough examination of design choices. The implementation was also difficult because it required adjustments to existing commands.
  * Credits: *seedu AB3 code* was being referenced.

* **New Feature**: Added course validation. (Pull Request: [#136](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/136))
  * What it does: It passes the course object through CourseUtil which will check the validity of said course.
  * Justification: This feature enhances the user's experience by only allowing valid existing courses to be added to the address book.
  * Highlights: This feature requires a list generated from NUSMods API which we integrated into our product. In addition, every command that deals with the course object will have to go through the CourseUtil to ensure that the user is dealing with a valid course. 

* **New Feature**: UI Enhancement. (Pull Request: [#99](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/99))
  * What it does: The UI has received a makeover, now featuring both dark and light modes that can be effortlessly toggled from the menu bar. Furthermore, a user-friendly summary of courses and tags is conveniently positioned on the right side of the person list panel. 
  * Justification: This enhancement significantly elevates the user experience, offering theme customization and quick access to course and tag information directly within the UI.
  * Highlights: Implementing this feature necessitated modifications to the CSS file and extensive adjustments to the `MainWindow.fxml`. Additionally, various JavaFX components had to be harmonized to seamlessly integrate these improvements into the UI.

* **New Feature**: Theme Customization via Command Line. (Pull Request: [#164](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/164))
  * What it does: Users can switch between two visually distinct themes: Dark and Light, tailoring the UI to their preferences.
  * Justification: We understand that every user has unique aesthetic preferences, and this feature empowers them to personalize their experience. Whether you prefer a sleek, dark interface or a bright, light one, the choice is yours. To switch to the Dark theme, simply use the command `theme dark`, while `theme light` will illuminate your UI. It's never been easier to make your UI truly yours.
  * Highlights: Implementing this feature required enhancing the command-line interface to support theme selection.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=carlintyj&breakdown=true)

* **Project management**:
  * Released v1.3.1 jar file.
  * Updated `index.md` (Pull Request: [#126](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/126))
  * Updated `readme.md` (Pull Request: [#126](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/126))

* **Documentation**:
  * User Guide:
    * Added the introduction to the user guide. (Pull Request: [#132](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/132))
    * Added the Using this Guide section. (Pull Request: [#132](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/132))
    * Added the User Interface section. (Pull Request: [#144](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/144))
    * Updated the Adding a student feature. (Pull Request: [#144](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/144))
    * Updated the list feature. (Pull Request: [#144](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/144))
    * Add Glossary section. (Pull Request: [#156](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/156))
  * Developer Guide:
    * Added Use Cases. (Pull Request: [#60](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/60))
    * Updated UI Class diagram. (Pull Request: [#126](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/126))
    * Added AddCommand feature implementation. (Pull Request: [#126](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/126))
    * Added Planned Enhancements. (Pull Request: [#232](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/232))
    * Added Ui panel list. (Pull Request: [#274](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/274))
    * Added Theme feature implementation (Pull Request: [#254](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/254))
    * Added List Panel feature implementation (Pull Request: [#294](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/294))

* **Community**:
  * Reviewed [44](https://github.com/AY2324S1-CS2103T-T17-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Acarlintyj) Pull Requests
  * Reviewed PRs: [#75](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/75), [#82](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/82), [#94](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/94), [#95](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/95), [#97](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/97), [#104](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/104), [#106](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/106), [#123](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/123), [#125](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/125), [#133](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/133), [#137](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/137), [#159](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/159), [#162](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/162), [#163](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/163), [#165](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/165), [#166](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/166), [#224](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/224), [#225](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/225), [#230](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/230), [#233](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/233), [#235](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/235)


