---
layout: page
title: Brendan's Project Portfolio Page
---

### Project: InsureIQ

InsureIQ is for car insurance agents who type fast over CLI and have to keep track of multiple customers' vehicles 
with the car insurance policies. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `remind` agents of upcoming policy expiry dates
  * What it does: Filters and displays the client list according to the specified number of days remaining until each 
  client's policy expiry date, as determined by the agent from the current date
  * Justification: This feature will be able to quickly narrow down the list of clients with approaching policy expiry 
  date in 1 command
  * Highlights:
    * The initial implementation does not allow the agents to enter any parameters, so it will only show the clients 
    with policy expiry date within the next 30 days
    * However, we decided to allow more flexibility for the agents and allow them to specify the number of days 
    remaining until each client's policy expiry date
    * Hence, the feature can be used with `remind NUMBER_OF_DAYS` where `NUMBER_OF_DAYS` can only range from 0 to 7305 
    (equivalent to 20 years, the maximum lifespan of a car in Singapore)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=brendanneojw&breakdown=true)

* **Project management**:
  * Team lead role
    * Take charge of agendas during group meetings
    * Delegate tasks for the week to the team members
    * Oversee overall team progress for each week's tasks
    * Resolve any disagreements or conflicts within the team
  * Maintained issue tracker in the team's GitHub repository
  * Maintained milestones `v1.2` to `v1.4` (5 milestones) on GitHub
  * Managed releases `v1.3(trial)` to `v1.3.1` (2 releases) on GitHub


* **Enhancements to existing features**:
  * Updated the implementation of `find` feature where it previously only find clients by their names
    * `find` now requires the prefix to be given to find for different fields
      * `n/` for Name field
      * `i/` for NRIC field
      * `p/` for Contact Number field
      * `l/` for Licence Plate field
      * `e/` for Email field
      * `t/` for Tag field
      * `c/` for Company field
      * `pn/` for Policy Number field
      * `pi/` for Policy Issue Date field
      * `pe/` for Policy Expiry Date field
    * Added 9 more predicates, total of 10 predicates to support this feature
  * Updated `find` command such that the values given for each prefix is treated as 1 value rather than separate values
    * For example, `find n/Hans Bo` no longer returns clients with names `Hans` or `Bo`, but only clients with names 
    `Hans Bo` in it
  * Updated `find` command to support partial matching of values
    * For example, `find n/Ha` will return all clients with names that has the characters `Ha` in it
  * Updated the storage file name from `addressbook.json` to `insureiq.json`
  * Updated the URL given in the help window from the `help` feature to reflect the team's 
  [User Guide](https://ay2324s1-cs2103t-w16-3.github.io/tp/UserGuide.html)
  * Updated the display of `PersonCard` for the policy details
  * Constantly updates the version number in `MainApp.java` to correctly reflect the version of `insureiq.jar`


* **Documentation**:
  * User Guide:
    * Replaced all occurrences of `person` to `client` to better suit our target users
    * Replaced all occurrences of `addressbook.jar` to `insureiq.jar`
    * Added documentation for the features `find` and `remind`
  * Developer Guide:
    * Added implementation details of the `remind` feature
    * Added sequence diagram and activity to illustrate the flow for `remind` feature
    * Added in _User Stories_ in **Appendix: Requirements** section


* **Community**:
  * PRs reviewed (with non-trivial comments): [\#67](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/67), [\#75](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/75), 
  [\#81](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/81), [\#89](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/89), [\#92](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/92),
  [\#98](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/98), [\#110](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/110), [\#129](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/129),
  [\#143](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/143), [\#199](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/199), [\#204](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/204)
  * Contributed to verbal discussions during tutorial sessions
  * Reported 12 [bugs and suggestions](https://github.com/brendanneojw/ped/issues) for another team during the PE dry run


* **Tools**:
  * JavaFX for design of InsureIQ GUI
