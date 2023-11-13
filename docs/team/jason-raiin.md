---
layout: page
title: Jason's Project Portfolio Page
---

### Project: OutBook

OutBook is a desktop data management application for freelance insurance agents to manage their numerous contacts and meeting schedule. Users can link their contacts to their meetings, to keep track of the personnel attending these meetings.

My contributions to the project are listed below.

- **New Feature**: Remove contact from meeting command (Pull Request [#51](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/51))
  - What it does: Allows the user to remove an Attendee from the meeting.
  - Justification: Basic functionality for meetings

- **New Feature**: Add `Tag`s to meetings (Pull Request [#63](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/63))
  - What it does: Meetings can have tags associated to them
  - Justification: Enables searching and filtering, similar to Persons

- **New Features**: Add `Status` field to meetings (Pull Request [#105](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/105))
  - What it does: Meetings can be marked as complete
  - Justification: Users want to see which meetings have already been done

- **New Feature**: Mark meeting command (Pull Request [#105](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/105))
  - What it does: Allows the user to mark a meeting as complete, and updates the last contacted time of all attendees
  - Justification: Automates the updating of last contacted time for attendees

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jason-raiin&breakdown=true)

- **Project management**:
  - Contributed issues and bugs
  - Reviewed PRs

- **Enhancements to existing features**:
  - Used `LinkedHashSet` for attendees instead of `HashSet` (Pull Request [#51](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/51))
    - Justification: Some commands require indexing of the Attendee set, so a `LinkedHashSet` is more appropriate to ensure that the indexing is consistent
  - Convert `Tag` to factory class (Pull Request [#64](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/64))
    - Justification: Eliminate duplicate objects to reduce memory usage
  - `ParseException`: Improved throwing, handling, and display of parsing exceptions (Pull Request [#155](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/155))
    - Justification: Allows the user to see the root cause when an error is encountered.
  - Duplicate contacts: added checks for duplicate emails and phones (Pull Request [#161](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/161))
    - Justification: Prevent duplicate entries
  - Email format: Improved Regex for email field (Pull Request [#161](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/161))
    - Justification: Narrowed the check to only accept common email formats.
 

- **Documentation**:

  - User Guide
    - `rmmc` command guide
    - `mark` command guide
    - Proofreading and copywriting

  - Developer Guide
    - `rmmc` documentation and diagrams
    - `mark` documentation and diagrams
    - User profile
    - Value proposition
    - User stories
    - Use cases

- **Tools**:
  - Added util method `parseIndexes` (Pull Request [#51](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/51))
    - What it does: Parses multiple indexes in a command and checks that the correct number of index arguments are provided
    - Justification: `addmc` and `rmmc` commands take in multiple index arguments
  - Improved methods for `typicalMeetings` and `typicalAddressBook` (Pull Request [#105](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/105))
    - Justification: Increase ease of testing
  - Added `DateTimeUtil` for parsing and formatting dates (Pull Request [#161](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/161))
    - Justification: Abstract frequeuntly used parse and format methods into a common util
