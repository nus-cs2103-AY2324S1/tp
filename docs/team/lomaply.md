---
layout: page
title: Linus' Project Portfolio Page
---

### Project: OutBook

OutBook is a desktop personal secretary application used for saving contacts and scheduling meetings. Users can link their contacts to their meetings, to keep track of the personnel attending these meetings.

My contributions to the project are listed below.

- **New Feature**: View contact command (Pull Request [#53](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/53))
  - What it does: Allows the user to view all details of a contact.
  - Highlights:
    - This command required the first modification to the default GUI to make space for the viewed items display.
    - Finding a way to send the viewed contact from the Command to the UI without unnecessarily increasing coupling was quite tricky. This was achieved by having the command store the viewed contact in the Model, and having the UI access it via the Logic Manager.
  - Justification: This feature allows the user to see details of a contact, such as remarks, that may not be shown in the contact list due to space constraints.

- **New Feature**: View meeting command (Pull Request [#68](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/68))
  - What it does: Allows the user to view all details of a meeting, similar to View contact command.
  - Justification: This feature allows the user to see details of a meeting, such as the list of attendees, that may not be shown in the meeting list due to space constraints.

- **New Feature**: Edit meeting command (Pull Request [#80](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/80))
  - What it does: Allows the user to edit all meeting details apart from the list of attendees, which is separately handles by `addmc` and `rmmc`.
  - Highlights: While the implementation is quite similar to the existing edit contact command, some changes were required due to meetings having different data fields, with one of them (Attendees list) that should not be modifiable by this command.
  - Justification: This feature allows users to edit incorrect/outdated meeting information, instead of having to delete and create a new meeting.

  
- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lomaply&breakdown=true)


- **Project management**:

  - Maintain Issue Tracker
  - Forking workflow
    - Review and merge pull requests

- **Enhancements to existing features**:

  - Split original UI (contact list) into 3 separate panes (Pull Request [#53](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/53))
    - Justification: Makes space for the Meeting Schedule and Display panel for the view commands.
  - Contact list automatically sorted by LastContactedTime (Pull Request [#99](https://github.com/AY2324S1-CS2103T-F12-4/tp/pull/99))
    - Justification: Ensures that Outbook users will easily stay in touch with their contacts by pushing contacts with the oldest LastContactedTime (with respect to the current date) to the top of the contact list.

- **Documentation**:

  - User Guide
    - First draft of User Guide with initial plans for v1.2
    - Add section in User Guide for `viewm`, `viewc`, `editm`
    - General polishing and error checking
  - Developer Guide
    - Add Implementation notes on `viewm`, `viewc`, `editc` and `editm`
    - Add several Sequence & Class diagrams

- **Community**:

- **Tools**:
