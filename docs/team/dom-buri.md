---
layout: page
title: Dominic's Project Portfolio Page
---

### Project: EventWise

EventWise is a event management desktop application used for managing contacts and details
specific to an event. It combines the flexibility of a Command Line Interface (CLI) and the
benefits of a Graphical User Interface (GUI). Designed for event planners, EventWise helps you
to easily track and access crucial information in a single place, simplifying event coordination
and communication. It is written in Java and has about 16 kLoc.

### My Contributions
Given below are my contributions to the project.

* **New Feature** Added `rsvp` command (PR [#84](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/84))
  * What it does: Allows users to set the RSVP status of a guest in a specific event
  * Justification: It is important as it allows the users to anticipate the number of attendees accurately. The users will then be able to determine venue size, catering needs, seating arrangements and logistics.
  * Highlights: This feature is a new association class that establishes a relationship between the `Person` and `Event` classes. In the GUI, the RSVP tag will be displayed in varying colors to indicate different RSVP statuses. Additionally, this feature includes validation checks to ensure that a `Person` must be added to the `Event` before they can RSVP. Lastly, when a duplicate RSVP is attempted, instead of throwing an error, EventWise will update the RSVP accordingly instead.
* **New Feature**: Added `event` command (PR [#43](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/43))
  * What it does: Allows users to add a new event, including details like the name, description, start and end dates, and a note
  * Justification: It is important as it enables users to create and maintain a record of events, including their details. This feature helps users keep track of their upcoming events, which is the key functionality of EventWise.
* **New Feature**: Added `editEvent` command (PR [#48](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/48))
  * What it does: Allows users to edit the details of an existing event like the name, description, start and end dates, and note
  * Justification: Enhances EventWise’s functionality by enabling users to edit and make changes to events. Users may need to modify event details for various reasons, such as correcting errors or change of plans.
* **New Feature**: Added `viewVenues` command (PR [#61](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/61))
  * What it does: Allows users to view a list of all venues created
  * Justification: Enhances EventWise’s functionality by providing users with a quick and convenient way to access an organized list of all the venues they have created.
* **New Feature** Added `clearGuests`, `clearEvents`, `clearVenues` commands (PR [#62](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/62))
  * What it does: Allows users to delete all `Person`, `Event`, `Venue` entries in EventWise
  * Justification: Enhances EventWise’s functionality by offering users a convenient and efficient way to reset EventWise storage. Over time, users may accumulate a large number of guest, event and venue entries, which can make the application's interface messy.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=dom-buri&breakdown=true)
* **Enhancements implemented**:
  * Updated `help` command (PR [#59](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/59))
    * to include URL to EventWise product website
  * Wrote additional test cases to increase coverage from 78% to 82% (PR [#206](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/206), PR [#209](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/209))
* **Documentation**:
  * User Guide:
    * Added documentation for `event`, `editEvent`, `rsvp`, `clear`, `clearEvents`, `clearGuests`, `clearVendors`, `clearVenues`, and `help` commands
    * Updated Command Summary
  * Developer Guide:
    * Updated class diagram for `Storage.java` (PR [#212](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/212))
    * Added implementation details and instructions for manual testing for `create`, `rsvp` and `clear` commands.
    * Added user stories (PR [#20](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/20))
* **Team-based tasks**:
  * Maintain issue tracker on GitHub
  * Set up Project Repository
  * Set up Project Website with Jekyll (PR [#22](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/22))
  * Integrated Codecov to the team repo
  * Create `Event` Class (PR [#40](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/40))
  * Create `Rsvp` Class (PR [#84](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/84))
  * Refactor `Person` Class (PR [#76](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/76), [#77](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/77))
  * Created Demo Video for [v1.3](https://drive.google.com/file/d/1FN2dsgVg_88JK2bkcxixUYlgjININqwR/view?usp=sharing)
  * Reviewed Pull Requests done by the team and check for correctness of the code.
  * Managed releases `v1.3.1`, `v1.3.2` (2 releases) on GitHub
* **Community**:
  * Reported bugs and suggestions for other teams in the PE Dry Run (Issue [#170](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/170), Issue [#172](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/172), Issue [#177](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/177), Issue [#179](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/179), Issue [#186](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/186), Issue [#194](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/194), Issue [#195](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/195), Issue [#197](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/197), Issue [#204](https://github.com/AY2324S1-CS2103T-F08-0/tp/issues/204))
  * PRs reviewed (PR [#204](https://github.com/AY2324S1-CS2103-F13-3/tp/pull/204))
