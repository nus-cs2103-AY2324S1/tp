---
layout: page
title: Yu Jiali's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a `load` command that allows users to load their words and translations from Excel files to Flashlingo.
    * What it does: Reads data from file, creates and stores new `FlashCard` objects.
    * Justification: This feature significantly improves the product to make it more functional to our target users. It allows them to load customized flash cards conveniently and start to learn them immediately, which is more efficient than adding them one by one.
    * Highlights: As the data is stored in a complex structure, any exceptions or invalid data need to be handled thoroughly. Moreover, duplicated data should be avoided.
    * Credits: Third-party library _apache-poi-ooxml_ is used to read data from Excel.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=a1waysd&breakdown=true)

* **Enhancements to existing features**:
    * Improved CSS styling of GUI, such as font and layout
    * Introduced dark theme, enabled theme preference saving and loading
    * Refactored `storage`, `model` and `logic` components to fit with Flashlingo and flash cards
    * Added more defensive coding in `storage` to prevent data and app corruption

* **Documentation**:
    * User Guide:
        * Drafted UI, `list` and `stop` commands, refactored title and layout
        * Added documentation for `switch`, `load` and `help` commands
        * Wrote FAQ section in detail
        * Updated and cropped all images, improved overall word usage and format
    * Developer Guide:
      * Drafted project scope, NFRs and manual testing
      * Adapted the storage design from AB-3 to Flashlingo
      * Added documentation for `load` and `switch` commands implementation
      * Wrote planned enhancement section and updated NFRs

* **Contributions to team-based tasks**:
    * Milestone v1.1, v1.2, v1.2b and v1.3b creation, issues assignment
    * In charge of [project docs](https://docs.google.com/document/d/1I7AeOjEK5tkLxLRfuDXVB0-02TU7aJH4cUiQuE5mahE/edit#heading=h.mgdo413wsg9o), including v1.2, v1.3 features demo and postmortem
    * Task assignment, in-team coordination, task presenter including PE-D bug fix report and tester feedback
    * Effective [Refactor of code](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/75) from AB-3 to Flashlingo
    * Documented FAQ in User Guide, project scope and NFRs in Developer Guide
* **Community**:
    * PRs reviewed (with non-trivial review comments): [#185](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/185), [#232](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/232), [#311](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/311)
    * Contributed to [forum discussions](https://github.com/nus-cs2103-AY2324S1/forum/issues/145)
    * Reported bugs in other team's products: [PE-D](https://github.com/A1WAYSD/ped/issues)

