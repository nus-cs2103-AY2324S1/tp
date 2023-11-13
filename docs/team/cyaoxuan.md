---
layout: page
title: Yao Xuan's Project Portfolio Page
---

### Project: InsureIQ

InsureIQ is for car insurance agents who type fast over CLI and have to keep track of multiple customers' vehicles with the car insurance policies. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add remarks to person
  * What it does: Allows the user to add comments / remarks to a client to remember special notes related to them
  * Justification: This feature is useful to car insurance agents who have a large client base as they can better personalise their client engagement based on certain characteristics clients have (e.g. "prefers policies with longer coverage")

* **Enhancements to existing features**:
  * Extended the current `Model` to include more information to model the car insurance context
    * New attributes in `Person` class: NRIC, licence plate, remark, policy
    * New `Policy` class: company, policy number, policy date (encapsulates issue and expiry date)
  * Handled exceptions for `find` command that came from functionality changes to search by other fields

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=cyaoxuan&breakdown=true)

* **Documentation**:
  * User Guide:
    * Added documentation for `remark` feature
    * Checked through format and content
  * Developer Guide:
    * Added implementation for `remark` feature
    * Extended documentation for the `Model` component on the new attributes added and design considerations
    * Add use cases for features in MVP (UC1 - 5)
    * Extend glossary
    * Add Appendix: Effort section
    * Checked through format and content

* **Project management**:
  * Assigned issues to the team and manage milestones in `v1.1` and `v1.4`

* **Community**:
  * PRs reviewed (with non-trivial comments): [\#74](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/74), [\#77](https://github.com/AY2324S1-CS2103T-W16-3/tp/pull/77)
  * Had the role of a "Git expert" in the team, and guided the team on how to use the Git CLI and forking workflow 
