---
layout: page
title: Lim Jun Han, Alvin Project Portfolio Page
---

## Project: DoConnek Pro

### Overview:

DoConnek Pro offers General Practitioner Clinic Management Staff an efficient desktop application for organizing patient and specialist contact details. 
Designed to cater to Command Line Interface (CLI) enthusiasts while also featuring an intuitive Graphical User Interface (GUI), it enables swift execution of routine tasks.

### Summary of Contributions

- **Code Contributed:**
  [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=alvinlim277&breakdown=true)
- **New Feature: Added functionality allowing users to define their own shortcuts for command keywords**
  - What it does: allows users to define shortcut aliases for any existing command offered by DoConnek Pro.
  - Justification: this feature improves the user experience by allowing them to personalise the application to suit their needs, especially if they are used to other command keywords from other CLI interfaces they might be familiar with.
  - Highlights: this feature risked introducing unecessary dependencies so some consideration of alternatives had to be done to pick the most non-intrusive implementation.
- **Enhancements implemented:**
    - Did the initial separation of display for `Patients` and `Specialists` (Pull request [#62](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/62))
    - Made `location` field specific to `Specialists` (Pull request [#73](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/73))
    - Made `find` command work with substring search (Pull request [#88](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/88))
- **Contributions to Documentation:**
    - **User Guide:** 
      - Added documentation for the `addsc` and `delsc` commands.
      - Updated documentation for new `find` command behaviour.
    - **Developer Guide:**
      - Added developer documentation for the shortcuts feature.
      - Created all the UML diagrams used to document the shortcut feature.
- **Contributions to team-based tasks:**
    - Updated the README and site-wide settings.
    - Created milestone for v1.1.
    - Helped maintain the issue tracker.
- **Review/mentoring contributions:**
    - Helped to devise implementation for `find` command [(link to branch)](https://github.com/alvinlim277/tp/tree/feature-FindCommandUsage),
  which was later adapted for the project (Pull Request [#66](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/66)).
    - Pull Requests reviewed (with non-trivial comments): [#52](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/52), [#58](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/58), 
  [#64](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/64), [#66](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/66), [#72](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/72),
  [#75](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/75), [#95](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/95), [#102](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/102),
  [#105](https://github.com/AY2324S1-CS2103T-W13-1/tp/pull/105)
