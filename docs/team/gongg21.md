---
layout: page
title: Gallen Ong's Project Portfolio Page
---

### Project: CodeSphere

**CodeSphere** is a **desktop contact management app, optimised for use via a Command Line Interface (CLI)**
while still having the benefits of a Graphical User Interface (GUI).
It is an app targeted at enabling Teaching Assistants in NUS School of Computing to better manage their students.

Given below are my contributions to the project.

* **New Feature**: EditCourse Command
  * What it does: allows users to edit the course name of an existing course on the home page.
  * Justification: in the case where the user has a change in course name they wish to make to an existing course, this is the command to use.
  * Highlights: this enhancement had to be implemented carefully to align with the updated Model component of the app.

* **New Feature**: DeleteCourse Command
  * What it does: allows users to delete an existing course on the home page.
  * Justification: in the case where the user wishes to delete an existing course, this is the command to use.
  * Highlights: this enhancement had to be implemented carefully to align with the updated Model component of the app.

* **New Feature**: Select Command
  * What it does: allows users to select a course to view into, changing the screen to the course view.
  * Justification: each course contains a list of students and to view into it to manage them, the user has to be able to select into one.
  * Highlights: as our app was updated to rely on two stages, Home and Course, this enhancement had to be properly implemented to switch correctly.


* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=gongg21&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Enhancements to existing features**:
    * Updated Storage component for the app ([#37](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/78))
    * Updated Model component for the app ([#37](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/78))
    * Updated Logic component for the app ([#37](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/78))
    * Added and updated test files for several classes and existing features to align with the app's intended functionality ([#37](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/78), [#268](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/268/files#diff-1ec9267e3dde0569a3b06f8201aef7d83b34bcde507697075f92a4758e7e041d))

* **Project management**:
    * Generated and managed issues for the progress of the app for `v1.3`-`v1.4` on GitHub.

* **Documentation**:
    * User Guide:
        * Reworked structure of the User Guide ([#123](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/123/files), [#125](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/125/files))
        * Added documentation for all sections except _Tutorials for Beginner_ ([#123](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/123/files), [#130](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/130/files))
        * Added documentation for `add`, `edit`, `delete`, `select`, `sort`, `find`, and `clear` ([#123](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/123/files), [#130](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/130/files), [#193](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/193/files), [#194](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/194/files))
        * Improved navigability of User Guide ([#132](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/132/files))
    * Developer Guide:
        * Created UML diagrams for Architecture, Logic, Model, and Storage for the app ([#110](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/110/files))
        * Added documentation for all main components except UI for the app ([#110](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/110/files))
        * Added implementation details and diagrams for `add`, `edit`, and `delete` course commands ([#345](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/345), [#356](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/356))

* **Community**:
    * PRs reviewed (with non-trivial review comments) ([#77](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/77), [#97](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/113), [#112](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/112), [#113](https://github.com/AY2324S1-CS2103T-W15-4/tp/pull/113))
    * Reported bugs for team CS2103T-F08-3 during PE-D and suggested possible improvements [here](https://github.com/gongg21/ped)
