---
  layout: nixx162.md
  title: "Nicholas Jimmy Alden's Project Portfolio Page"
---

### Project: KeepInTouch

KeepInTouch is a desktop app for managing contacts for people in the working industry (recruiters, engineers, etc.) as well as events for career purposes.

Given below are my contributions to the team:

* **New Feature**: to be added soon

* **New Feature**: to be added soon

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nixx162&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22) 

* **Project management**:
    * Went through all the issues in GitHub to create a summary of what bugs there are (eliminating duplicate reports), whether they can be fixed, and recommended ideas of fixing it.  

* **Enhancements to existing features**:
    * Improved help feature to better accomodate users. The old help command only opens a window containing a link to the user guide. The improvements I implemented are:
        * Calling the help command now display the list of all available commands
        * Calling the help command with the relevant command word will display the uses of that command word
        * Help command can recognize small typos in the additional argument and can suggest a correction
    * Improved UI of the original contact list. Since initially all fields in a person instance is shown vertically, this leaves a lot of weight space in the right part of the window. Additionally, the density of information can make it hard to read. Therefore, I modified the display container to be a table instead.
    * Improved UX when calling `list events`. Previously, the events are displayed as a text shown in the `ResultDisplay` container, which:

        1. Can only show a small amount of rows, forcing the user to scroll
        2. Is information-dense and unpleasant to read

        Therefore, similar to the previous solution, I displayed the result, using a popup window, in a table as well.

* **Documentation**:
    * User Guide:
        * to be added soon
    * Developer Guide:
        * to be added soon

* **Community**:
    * to be added soon

* **Tools**:
    * to be added soon
