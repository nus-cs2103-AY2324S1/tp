---
  layout: default.md
  title: "Ian Tsai's Project Portfolio Page"
---

### Project: RealtorTrackerPlusMax

RealtorTrackerPlusMax is a desktop application made for usage by real estate agents to keep track of their
contacts, such as buyers and sellers.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link]()
* **New Feature**: `bedit` and `sedit` command
  * What it does: Allows users to edit the details of buyers and sellers
  * Justification: Without the edit commands, users would need to delete the user they want to edit,
  then add in a new person with the updated information
  * Credits: AB3 source code
* **New Feature**: `seller` command
  * What it does: Allows users to add a new seller
  * Justification: Allow a user to add someone of type "seller" instead of buyer
  * Credits: Inspired by the command to add person


* **Project management**:
  * Managed 1.2 iteration
* **Enhancements to existing features**:
  * Added tests for various commands
  * Wrote Test Utils to help build sellers and buyer for testing.
  Had to refactor code in multiple places to make it work. (Pull request #78, #82, #85)

* **Documentation**:
    * User Guide:
      * Ensure `add` and `edit` command writeups are correct
    * Developer Guide:
      * Wrote use cases and user stories

* **Contributions to team-based tasks**:
  * Lead the 1.2a iteration
    * Decided on issues for the iterations
    * Assigned issues for the iteration to team members
    * Ensured all issues were completed and closed on time
  * Assisted with dividing workload for the first iteration where we updated the DG and UG

* **Community**:
  * Timely review and approval of PRs, resulting in faster iterations

