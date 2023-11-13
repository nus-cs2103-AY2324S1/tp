---
layout: page
title: Kum Chai Yin's Project Portfolio Page
---

### Project: InsureIQ

InsureIQ is for car insurance agents who type fast over CLI and have to keep track of multiple customers' vehicles 
with the car insurance policies. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `batchdelete` clients with given condition.
  * What it does: Allows the agent to batch delete clients whose policy expiry date in a specific month or policy purchased from a specific company  
  * Justification: This feature will be useful for a car insurance agent to manage client list in the following scenario:
    * The agent want to delete all clients whose policy expiry date in a specific month. 
      For example, clients whose policy expiry date is 6 months; this indicates that they chose not to have this 
      agent assist them in renewing their car insurance.
    * The agent no longer work for a specific insurance company.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=KumChaiYin&breakdown=true)

* **Project management**:
  * Documentation role
    * Ensured the quality of various project documents
    * Standardised the format and structure of project documents
  * Did the UI mockup of our intended final product
  * Took the initiative to modify sequence diagram in DG
    * Used ref frame to break complicated sequence diagram into two parts
  * Prepared pdf files of UG, DG, PPP and ensure their quality.

* **Enhancements to existing features**:
  * Extended the current `add` command to deal with the updated `Person` fields as listed below.
    * New attributes in `Person` class: NRIC, Licence plate, Policy
    * New `Policy` class: Company, Policy number, Policy date (encapsulates issue and expiry date)
  * Introduced flexible `add` command, user can choose to:
    * Add a client's details without a policy or with complete policy details
  * Extended the Model interface to include an abstract method `batchDeleteWithPredicate`:
    * This method is designed to delete all clients from the list who satisfy the give predicate
    * The `ModelManager` class implements this method

* **Documentation**:
  * User Guide:
    * Updated documentation for `add` command.
    * Added documentation for `batchdelete` command.
  * Developer Guide:
    * Added implementation for `batchdelete` command.
    * Updated **Use cases** section.
      * Updated UC1 - 5
      * Add UC6 - 10

* **Community**:
  * PRs reviewed (with non-trivial comments): [#81](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/81), [#122](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/122)
  * Had the role of a "Documentation" in the team, maintained the quality of various project documents.
