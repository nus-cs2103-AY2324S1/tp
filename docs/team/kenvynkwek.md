---
layout: page
title: Kenvyn Kwek's Project Portfolio Page
---

### Project: ManageHR

**ManageHR** is your all-in-one solution for seamless Human Resources management.
Say goodbye to paperwork, spreadsheets, and administrative headaches, our command line interface (CLI) based software
empowers HR professionals to focus on what truly matters – your people.

Given below are my contributions to the project.

* **New Feature**: Added the `filter` feature.
  * **What it does**: allows users to filter employees using the common prefixes.
  * **Justification**:
    * This feature improves the product significantly as over time, managing/searching for specific employees
amongst many becomes difficult.
    * Filtering allows the user to look for specific employees quickly, by name, by department, or even by salary.
    * It also enhances other features/commands, for example, filtering makes it easy to check a desired employee's
details before choosing to editing their details.
  * **Highlights**:
    * This feature's implementation was slightly challenging as it required using prefixes as the filter parameters
instead of a single keyword. 
    * Implementing a new feature/command also proved more challenging compared to an enhancement.
    * Many tests also had to be done to ensure every prefix can be filtered by correctly.
  * **Credit**: Slightly referenced AB3's `find` and `edit` feature's design.
* **Refactor**: Refactored instances of `AddressBook3` to `manageHr`
  * **What it does**: No changes to features.
  * **Justification**: tP instructions to treat each version as the final product. Not changing `AddressBook3` instances
would mean having non-descriptive package/class/method/variable names.
  * **Highlights**: Refactor implementation was challenging due to the many dependencies.
  * **Credit**: nil

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kenvynkwek&breakdown=true)

* **Project management**:
  * Lead weekly team meetings to discuss plans/tasks for the week
  * Maintained issue tracker of the team on Github
  * Managed team deliverables
    * v1.2 and v1.3 postmortem notes
    * jar releases
  * Primary project documents editor

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add`, `help` and `filter` [\#19](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/19), 
[\#75](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/75)
    * Added additional resources such as table of contents and description [\#19](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/19)
  * Developer Guide:
    * Added implementation details of the `filter` feature [\#58](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/58)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#63](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/63), 
[\#77](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/77), 
[\#128](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/128)
