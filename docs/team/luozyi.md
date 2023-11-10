---
layout: page
title: Luo Zhongyi's Project Portfolio Page
---

### Project: TAManager

TAManager is a desktop address book application made for professors to manage their teaching assistants (TA) under their charge. It provides fast access to TAs' contact details and their availabilities for tutorials/labs. You can track teaching and claimable hours among your TAs and find relief TAs for impromptu events or unforeseen circumstances.

Given below are my contributions to the project.

* **New Feature**: Added the command to update teaching hours to all TAs in the current list.
    * What it does: Allows the user to update the teaching hours to all TAs in the current list.
    * Justification: A professor will be managing multiple TAs and requires a command to update in batches.
    * Highlights: This feature requires me to fetch the current list from the given model and update every TA in the current list only if all updates are valid.

* **New Feature**: Added edit free time command for a specified TA.
    * What it does: Allows the user to update the free time interval for a specific day for a specified user.
    * Justification: TAs may have different free time for each day, so users can update for specified days only.
    * Highlights: I reused the editPersonDescriptor in the Edit Command class and modify it to fit the edit free time command.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=luozyi&breakdown=true)
* 


* **Project management**:
    * Participated discussions in features, UG and DG.
    * Coordinated team workflow on the DG and UG.

* **Documentation for Developer Guide**:
    * Updated DG for hour command [\#65](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/65)

* **Documentation for User Guide**:
    * Updated ug according to PE-D suggestions for Hour command description, edit free time prefix groups, and command summary [\#134](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/134)
    * Initial set up for UG for our group [\#14](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/14)

* **Community**:
    * PRs reviewed: [\#30](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/30), [\#39](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/39), [\#51](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/51), [\#55](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/55)
