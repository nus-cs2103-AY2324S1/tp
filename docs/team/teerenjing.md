---
  layout: default.md
  title: "TeeRenJing's Project Portfolio Page"
---

### Project: BayMeds v.2103

BayMeds v.2103 is a desktop application used for tracking medications. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add conflicting drugs.
  * What it does: Allows the user to add multiple conflicting drugs to a prescription.
  * Justification: This feature allows a user to specify which drugs are conflicting to manage the possible clashes when planning the consumption schedule.
  * Highlights: This enhancement affects existing commands and commands to be added in the future.
    The implementation was also complex as the algorithm ensures that the conflicting relationship between drugs are bidirectional.
    If drug A conflicts with drug B, the algorithm ensures that drug B must also conflict with drug A.  
  * Future plans: Separate drug names by commas instead of spaces to allow for more complex drug names that might have multiple words. 
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=teerenjing&breakdown=true)

* **Enhancements implemented**:
  * Implemented substring matching for complex medication names (Pull requests [\#157]())
  * Wrote additional tests for existing features to increase coverage from 68% to 72% (Pull requests [\#208](), [\#209]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `listConflicts`, `listAllConflicts`, `find` (Pull requests [\#164](), [\#215]())
    * Did cosmetic tweaks to existing documentation of features `delete`, `help`, `exit` (Pull requests [\#81](), [\#82](), [\#83](), [\#87]())
    * Added Known Issues for BayMeds (Pull requests [\#242]())
  * Developer Guide:
    * Added OODM of the conflicting drugs feature.
    * Added Planned Enhancements for BayMeds (Pull requests [\#242]())

* **Team-based tasks**:
  * Minor admin matters such as setting up the GitHub organisation, tp repository and MarkBind for the product website.
  * Ran code inspection on entire codebase and removed commented out code (Pull request [\#241](), [\#243]())

* **Community**:
  * Reported more than 5 bugs and gave suggestions during the PE Dry Run.
