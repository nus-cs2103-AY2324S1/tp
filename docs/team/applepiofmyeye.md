---
layout: page
title: Joey's Project Portfolio Page
---

### Project: ManaGease

ManaGease is a desktop Human Resource (HR) management system used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to mark employees absent, late or present.
  * What it does: allows the user to mark any of the employees in the database as absent, late or present for the current day. 
  * Justification: This feature is crucial to the effectiveness of the application because it sets the status of an employee to be late or absent, but is also the factor which other commands such as payroll calculation could make use of in future implementations as well.
  * Highlights: 
    * This enhancement affects future commands to be added. It required a designing of a new type of storage and storage methods by expanding on the already implemented JSON file writing and reading. 
    * As the attendance had a different relationship with a Person Model, the way the attendance was stored had to change as well. 


* **New Feature**: Added an attendance command to allow the user to view the full attendance report of an employee.
  * Justification: This feature is an aspect of employee performance that we currently measure. This feature allows for future implementations of signalling which employees have been away for long periods and need to be caught up, or which employees always seem to be coming late.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=applepiofmyeye&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)


* **Enhancements to existing features**:
    * Enhanced the `add` command to allow the user to add employees including their salary, bank accounts, annual leaves and join dates.

    
* **Documentation**:
    * User Guide:
      * Did initial mockups for User Guide UI
      * Added documentation for the features `mark` and `attendance`
      * Did tweaks for the User Guide from AB3 to our product [\#25](https://github.com/AY2324S1-CS2103T-W12-2/tp/pull/25/files)
      * Changed the styling of the User Guide slightly [\#25](https://github.com/AY2324S1-CS2103T-W12-2/tp/pull/25/files)
       
    * Developer Guide:
      * 

* **Team based Tasks**:
  * Morphed the initial code from AB3 to our product.


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#226](https://github.com/AY2324S1-CS2103T-W12-2/tp/pull/226)
    * Contributed to forum discussions (examples: [\#163](https://github.com/nus-cs2103-AY2324S1/forum/issues/163))

