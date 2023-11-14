---
layout: page
title: Bing Heng's Project Portfolio Page
---

### Project: ManageHR

ManageHR is a desktop contact/task scheduling/storage application used by HRs to organize essential data. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**:
  * Developed the CRUD feature of departments [\#73]()
    * Provides the ability for users to group employee contacts together
    * Synergy with the supervisor-subordinate relationship feature we developed: future integration will provide users the ability to compartmentalize and provide roles of members
    * **Credit**: Referenced some components in AB3.
    * **Highlights**: Implementation was challenging, many components such as names, unit test case, previous data structures have to be refactored as previously departments were simply passed as a String and loosly passed around

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=starry&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Participated in weekly discussion with team members in preparation for milestones

* **Enhancements to existing features**:
  * Improved the UI Layout to match the mock-up layout
    * Reorganized & Simplified the layout of the individual components [\#49](),[\#73](),[\#81]()
      * Addition of a left sidebar for future development
      * Centralized the list of names and removed excessive details such as phone number & tags
      * Created a component to display micro details of the employee contact instead of displaying them in the list
    * Added svg assets to represent certain parameters instead of words (eg. Salary is represented with a dollar sign)
    * **Credit**: Icons were taken from thenounproject, design is original
    * **Highlights**: Allows current features to be improved with ease (eg. Displaying departments as a separate tabs as a future update), Implementation was challenging as component properties interact with one another in a unique way, much trial and error
  * Refactored the class Name into EmployeeName & DepartmentName [\#73]()
    * Allows distinguishing the different constraints between different type of names (eg. DepartmentName can contain certain ascii characters, but not EmployeeName)
    * Simplifies the relationship between employees and department data: employees will be able to contain department names and vice versa without cyclic dependencies
    * **Credit**: Referenced AB3's `Name` in the employee module.
    * **Highlights**: Implementation was tedious as many lines of codes used the previously implemented name, refactoring took a little too long
  * Wrote unit test cases for Department and the refactored Names

* **Documentation**:
  * GUI Mock-Up: Redesigned the GUI to fit the theme of project [\#12]()
    * Added icons to profiles, contacts, logo, command bar etc
    * Redesigned components: CLI, Displayed infomation about stored people's profile
    * Added a sidebar for navigation and display of important infomation
    * Modified color scheme to give it a more vibrant profile
  * User Guide & Developer Guide [\#141]()
    * Added documentation for the feature `department`, `edit` and `exit`
* **Community**:
  * PRs reviewed : [\#48](), [\#26]()

