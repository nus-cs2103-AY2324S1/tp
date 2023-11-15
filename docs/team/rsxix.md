---
layout: page
title: Rohan Sapra's Project Portfolio Page
---

### Project: InsureIQ

InsureIQ is for car insurance agents who type fast over CLI and have to keep track of multiple customers' vehicles with the car insurance policies.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to `sort` the client list.
    * What it does: Allows the user to `sort` clients based on their policy expiry date, from those expiring earliest to those without policy data.
    * Justification: The feature is useful to car insurance agents who will want to know which policies expiry fast so they can respond appropiately and not risk losing their business.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=RSXIX&breakdown=true)

* **Enhancements to existing features**:
    * Throw exceptions for incomplete `add` and `edit` features, specifying the missing parameters.
    * Throw exceptions for `delete` feature with erroneous index number.
    * Add checks in the `Policy` class so that policy expiry date must fall after issue date.
    * Allow duplicate names in `Person` class as long as there is another attribute that does not resemble the existing list of persons in the client list.
    * Disallow policy numbers from being reused in the `Policy` class.
    * Change `edit` for complete policies to be reverted to default policy if any policy parameter is made to the default value.
    * Added tests for DeleteCommand, DeleteCommandParser, SortCommand, SortCommandParser, Person, Policy and PolicyExpirationDateComparator classes.

* **Documentation**:
    * User Guide:
        * Added documentation for the `sort` feature.
    * Developer Guide:
        * Added implementation for the `sort` feature.
        * Added manual testing guidance.

* **Project management**:
    * Added issues for exceptions in the `add` and `edit` features.
    * Assigned a few issues in v1.3.

* **Community**:
    * PRs reviewed (with non-trivial comments) : [\#106](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/106)
    * Had the responsibility "Testing" in the team, and helped implement and oversee error handling.


