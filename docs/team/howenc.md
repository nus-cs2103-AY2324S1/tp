---
layout: page
title: Howen's Project Portfolio Page
---

### Project: OutBook

OutBook is a desktop data management application for freelance insurance agents to manage their numerous contacts and meeting schedule. Users can link their contacts to their meetings, to keep track of the personnel attending these meetings.

My contributions to the project are listed below.

- **New Feature**: Add Meetings (Pull Request [#16](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/16))
  - What it does: Allows the user to add meetings into OutBook. This meeting allows the user to set the Meeting title, time, location and tags.
  - Highlights: This new feature is the foundation of half our other features as it is one of the base functions of our program.
  - Credits: It was built in a similar manner to add person to have similar structure.

- **New Feature**: Add UI to show meeting instances (Pull Request [#54](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/54))
  - What it does: Allows the user to see the meetings that are in OutBook. This will show the meeting title, time, location and tags that it has.

- **New Feature**: Find Meetings (Pull Request [#70](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/70))
  - What it does: Allows the user to find meetings using all the different fields on the meeting, title, time, location, tags, attendees.
  - Justification: This feature enables the user to quickly find the meeting that they want instead of scrolling through the entire list of meetings, improving the quality of life and efficiency of our product.
  - Highlights: The design process proved quite hard as in order to maintain SLAP (Single Level of Abstraction Principle) many predicate classes had to be made which increase coupling and decreased cohesion. In order to maintain similar level of coupling as before, a general predicate class needed to be created to handle all other predicates.

- **New Feature**: Made meetings to be sorted by start time (Pull Request [#83](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/83))
  - What it does: Meetings are sorted by start time
  - Justification: This feature would always put the earliest meeting the user has at the top of the list so that they do not have to search the entire list to find the meeting to prepare for next.
  - Highlights: The original implementation wanted to enable the user to specify the type of sort that they want, with the default being by start time. However, upon further dicussion with the team, this feature was reduced to sort by start time to keep things simple.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=howenc&breakdown=true)

- **Project management**:

  - Organising tasks and team meetings
  - Forking workflow
    - Review and merge pull requests
  - Ensuring deliverables are done on time
  - Managed [releases](https://github.com/AY2324S1-CS2103T-F12-4/tp/releases) `v1.2`-`v1.3(final)` 

- **Enhancements to existing features**:

  - Configured the model and storage to support Meetings. (Pull Request [#42](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/42))
    - What it does: This was the groundwork needed for the rest of our project to happen.
    - Highlights: The saving of the Attendees using Jackson proved quite difficult. The original implementation wanted the Person to be saved within the meeting. However, this would not be feasible as it would take up too much space and have a non-unique instance of a Person. This was resolved by using a string to represent the Person, as there cannot be 2 persons with the same name.

- **Documentation**:

  - User Guide:
    - Added documentation for `editm`, `findm`
  - Developer Guide:
    - Added implementation for `findm`
    - Updated diagram for UI, Model, Storage and Appendix: Instructions for Manual Testing
  - Updated README

- **Community**:

- **Tools**:

  - Umlet for UML modeling
