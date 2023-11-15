---
  layout: default.md
  title: "Jia Hao's Project Portfolio Page"
---

### Project: RealtorTrackerPlusMax

RealtorTrackerPlusMax is a CLI-based desktop application made for real estate agents to better keep track of the
contact information of their clients, namely buyers and sellers.
It is written in Java with about 10 kLoC, and uses a JavaFX GUI.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=j-hta-n&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)


* **New Feature**: Separated the seller and buyer list in the address book and GUI
  * What it does: Allows users to view and modify buyers and sellers separately using commands which are prefixed
  with either a `b` for buyers or `s` for sellers. The GUI also shows the two lists side-by-side.
  * Justification: Buyers and sellers have different attributes and purposes, so separating them makes it easier
    for the user to differentiate and manage their clients.
  * Credits: JavaFX documentation for modifying the GUI.

* **New Feature**: Added the `bprio` and `sprio` command
  * What it does: Allows users to tag their clients with a particular priority level
  * Justification: Certain clients may be of higher priority due to urgent needs or important
  deals, hence, this feature, in conjunction with sort, allows the user to better focus on clients
  with higher priorities.
  * Highlights: This feature adds a new `priority` field to all clients in the addressbook,
  which affects the add (`buyer` & `seller`) and edit (`bedit` & `sedit`) commands, which required
  significant refactoring across multiple files and test cases, emphasizing the need for small
  and incremental changes to be made for this to work.
  * Credits: The mechanism for changing the priority level of clients is inspired from that of the edit command.


* **Project management**:
  * Led the team up till the v1.1 iteration
    * Allocated different sections of the UG & DG for the team in v1.1
    * Delegated team roles and responsibilities for the duration of the project
    * Created a workflow guideline for the team to agree on early project matters (such as Issue/PR/branch 
    naming conventions, and PR merging/rectifying merge conflicts)
    * Organised the team's project notes / drive files and created the first few team meeting agendas
    * Assigned planned features to members for v1.2


* **Enhancements to existing features**:
  * Changed the initial GUI appearance for v1.2 (Pull request #73)
  * Wrote additional tests to increase coverage from 73.39% to 76.49% (Pull request #120)

<div style="page-break-after: always;"></div>

* **Documentation**:
    * User Guide:
      * Added documentation for the `bprio` and `sprio` commands and fixed typos in the command summary
    * Developer Guide:
      * Added implementation details for the `priority` feature
      * Added proposed plan for implementing optional fields


* **Community**:
  * Contributed to forum discussions (#118, #238)
  * Reviewed team PRs promptly, allowing for quicker rectification of issues during the project iterations
