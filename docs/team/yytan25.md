---
layout: page
title: Tan Yang Yi's Project Portfolio Page
---

### Project: BandConnect++
BandConnect++ is a desktop application that helps music producers seeking talented musicians to manage their musician contacts and form a band of their preferences.
It is optimised for CLI users so that frequent tasks can be done faster by typing in commands.

Given below are my contributions to the project.

* **New Feature 1: Delete a band**
  * What it does: Allows users to delete a band using the index of the band.
  * Justification: It is a common use case for music producers to remove bands that are disbanded or otherwise no longer needed.
  * Highlights: This enhancement involves updating the `FilteredBandList` to remove the band that is deleted.
  In addition, the GUI will update the band list to reflect the removal of the band.
  * Credits: I took inspiration from the `DeleteCommand` of the original AB3 and applied the same logic to the `FilteredBandList`.

* **New Feature 2: Remove a musician from a band**
  * What it does: Allows users to remove a musician from a band using the indices of the musician and the band.
  * Justification: It is a common use case for music producers to manage their bands and delete musicians who are no longer present.
  * Highlights: This enhancement involves a new operation in the `UniqueBandList` to access the target `Band` and `Musician` objects,
  and then removing the musician from the band. A new method, `updateFilteredBandMusicianList`, was also implemented to reflect the
  updated band members in the GUI.
  * Credits: I took reference from the implementation of `AddMusicianFromBandCommand` and applied the logic in reverse for this feature.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=yytan25&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Enhancements to existing features**:
  * Organised the Commands and corresponding Parsers to sub-packages `band`, `musician` and `general`

* **Team tasks contributions**:
  * Reviewed PRs of team members and gave constructive feedback
  * Initiated the morphing of the product to include the `Band` entity, which involved:
    1. Implementing the `Band`, `UniqueBandList`, `BandName` classes
    2. Implementing all test classes in `seedu.address.model.band`
    3. Implementing the initial version of `FindBandCommand`
    4. General enhancements in `ModelManager` and `AddressBook`
  * Did regular inspection of the `Band`-related sections of the code and ensured consistency across all methods
  * Enhanced the UG to cater to the target audience of the product

* **Project management**:
  * Reviewed the code and made GitHub releases for the `v1.1`, `v1.2` and `v1.3` milestones.
  * Wrapped up the `v1.1`, `v1.2` and `v1.3` milestones.
  * Ensured that all issues were accounted for and managed the issue tracker for each milestone.

* **Documentation**:
  * User Guide:
    * to be added soon
  * Developer Guide:
    * Added user stories

* **Community**:
  * to be added soon
