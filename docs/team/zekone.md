---
  layout: zekone.md
  title: "Khee Wei's Project Portfolio Page"
---

### Project: KeepInTouch

KeepInTouch is a desktop app for managing contacts for job-seekers. It can also help job-seekers to manage events for career purposes.

* **New Feature**: Delete and add tags to existing contacts ([\#63](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/63))
  * What it does: It allows users to add/delete one or more tags from any existing contacts with one simple command.
  * Justification: This feature improves the product significantly because the original product can only change the tags when adding a new contact. Changing tags will require deleting a contact and add it again with the new tags which is a huge inconvenience.

* **Existing Feature**: Adapt the `storage` in our architecture. ([\#32](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/32))
  * What it does: It allows `storage` to work with newly added feature such as `note` and `event`.
  * Justification: Without this adaptation, our product will not be able to store and retrieve data correctly.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=zekone&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Managed `1.3.trial` release.
  * Created 12 labels for better tracking of issues.
  * Created milestones `v1.2` and `v1.3`.
 
* **Fixed bugs reported in PE-D**:
  * Added tag related help message to help command ([\#147](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/147))
  * Fixed help and error messages ([\#148](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/148), [\#155](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/155))
  * Deleted edit feature ([\#149](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/149))
  * Fixed UG bugs ([\#150](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/150), [\#157](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/157))
  * Fixed behaviour when deleting non-exiting tags ([\#154](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/154))
  * Added bugs that cannot be fixed due to feature-freeze to the DG under planned enhancements. ([\#159](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/159), [\#164](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/164))
  * Changed jar filename in gradle.build ([\#163](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/163))
  * Fixed error message and UG when a large index is causing integer overflow ([\#166](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/166))

* **Documentation**:
  * User Guide:
    * Added section for details and requirements for user input parameters ([\#157](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/157))
    * Added section for add and delete tag feature ([\#63](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/63))
    * Added section for find feature ([\#81](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/81))
    * Added section for clear feature ([\#150](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/150))
  * Developer Guide:
    * Added planned enhancement section ([\#159](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/159))
    * Added implementation section for add and delete tag feature([\#181](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/181))
    * Added use cases for adding and deleting tag ([\#63](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/63))
    * Updated storage component ([\#54](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/54))
    * Added section for manual testing of adding and deleting tag ([\#186](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/186))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#33](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/33)
