---
layout: page
title: Yu Jiali's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature 1**: Added a `load` command that allows users to load their words and translations from Excel files to Flashlingo.
    * What it does: Reads from an Excel file, loads the data and creates new `FlashCard` objects in the app.
    * Justification: This feature significantly improves the product to make it more functional to our target users. It allows them to load customized flash cards conveniently into the app and start the review session immediately, which is more efficient than adding them one by one.
    * Highlights: As the data is stored in a complex structure, any exceptions or invalid data need to be handled thoroughly. Moreover, duplicated data should be avoided.
    * Credits: Third-party library "apache-poi-ooxml" is used to read data from Excel Workbook.

* **New Feature 2**: Added a `switch` command that allows users to switch between different color themes.
    * What it does: Switches between light and dark themes of the GUI and saves the preference in `preferences.json`.
    * Justification: This feature allows users to choose their preferred color theme. Enabling users to switch between different themes could potentially motivate them to use the application more frequently and learn the words more often.
    * Highlights: This new feature requires a comprehensive design of CSS for different themes. In-depth analysis of logic design for interacting with UI, Storage, Logic and Model components is also carefully considered, which leads to this final choice to follow the single-responsibility principle.
    * Credits: The logic flow of the `switch` command is similar to the GUI size settings in AB-3.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=a1waysd&breakdown=true)

* **Enhancements to existing features**:
    *

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list` and `stop`
        * Drafted UI for main and `list` feature
    * Developer Guide:
      *

* **Contributions to team-based tasks**:
    * Milestone v1.1, v1.2, v1.2b and v1.3b creation, issues assignment
    * Documenting group meeting notes, task assignment
    * Morphing the product into a different product, refactoring code from AB-3 to Flashlingo
    * Documenting FAQ in User Guide
    * v1.2 and v1.3 features demo
* **Community**:
    * PRs reviewed (with non-trivial review comments):

* **Tools**:
    *

