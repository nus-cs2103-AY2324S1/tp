---
layout: page
title: Cloud7050's Project Portfolio Page
---

<!-- NOTE After PDF conversion, overview + summary must not exceed 2 pages! -->

## Overview

**ConText** is a desktop app that allows for managing contacts quickly via text commands, with useful features relevant to NUS SoC students.
While it has a Graphical User Interface, most of its user interactions happen via an in-app Command Line Interface.

## Summary of contributions

### Code contributed

[TP RepoSense Code Dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=cloud7050&breakdown=true)

### Enhancements implemented

//TODO

### Contributions to the UG

- Merged AB3 UG contents back into the draft UG taken from our internal project document, to flesh it out, as the draft was a short one written from scratch before we were familiar with the documentation and code AB3 already came with.
- Made a full pass over the UG to add and update contents to match our project, enhancing it along the way.
- Proofread and fixed/filed miscellaneous (formatting) issues after the pages finished building. This is harder to notice and check for just based on PRs, as the pages only get built after merging, by which point the review process has already ended, and the author is not usually the one to perform the merge so as to be around to check whether the page is displaying as intended.

Wrote sections:

- Introductory description
- Quick start
- Features
  - About command format
  - Add, edit, delete, find, filter
  - Clear, list, help, exit
  - Automatic sorting
  - Automatic saving
- Known limitations

Some of the aforementioned sections may have been initially drafted by others, or may have since been edited or added on to by others.

### Contributions to the DG

<!-- TODO may do a full pass over DG -->
<!-- TODO use cases -->
<!-- TODO appendix: instructions for manual testing -->
<!-- TODO appendix: effort -->
<!-- TODO appendix: planned enhancements -->

Wrote sections:

- Implementation
  - Maintaining sorting while supporting filtering
- Acknowledgements (merged/updated from `README.md` and `index.md`)
- Appendix: Requirements
  - Product scope (target user profile, value proposition)
  - Non-functional requirements

Some of the aforementioned sections may have been initially drafted by others, or may have since been edited or added on to by others.

### Contributions to team-based tasks

- Set up GitHub team org with permissions and invites. Set up repo with custom labels and user stories project board. Edited branch protections over time.
- Made team PR in main course repo with product description.
- Large amount of refactoring to help rename classes, methods, fields, variables, tests etc. to match our product.
- Renamed packages to match our product.
- Gradle:
  - Managed Gradle configs, editing them as needed (e.g. fixing config features deprecated in Gradle V8.3, config simplification, improving `clean` task, removing temporary workarounds, enabling assertions).
  - Updated and tested some dependencies.
  - Upgraded Gradle wrapper.
- Set up GitHub Pages Jekyll deployment.
- Set up Codecov.
- Extensively defined most tasks in the [issue tracker](https://github.com/AY2324S1-CS2103-W14-3/tp/issues?q=is%3Aissue+author%3ACloud7050) over time. Managed milestones (some of which were split), labelled and kept track of work items, edited/assigned tasks, and kept the issue tracker up-to-date and tidy overall. Was very active in [issue comments](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/tp-comments.html#16-joel-leow-cloud7050-60-comments), such as adding details or meeting notes and linking relevant items.
- Helped perform some milestone wrapups before deadlines, following defined checklists.
- Helped tag, build, and create some releases as part of milestone wrapups or weekly project tasks.
- Helped keep track of meeting agendas and which tasks were pending assignment or further discussion. Helped create and link forum posts to seek clarifications from Prof.
- Helped track deliverables and ensure they were accomplished before their deadlines.
- Worked on sections of the UG/DG not about my own features or specific to anybody's features (described [above](#contributions-to-the-ug)).
- Updated `README.md`, `Ui.png`.
- Set up and maintained PR template to aid in reviewer and milestone assignment.
- Added and configured GitHub bot to automatically assign specific reviewers, with support for ignoring PRs under certain conditions.
- Performed some week-specific team tasks, e.g. triaging PE-D bugs, collating issue statistics into tutor's Teams message.

### Review/mentoring contributions

- Helped review a good portion of [team PRs](https://github.com/AY2324S1-CS2103-W14-3/tp/pulls?q=is%3Apr). I tried to be [quite thorough](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/tp-comments.html#16-joel-leow-cloud7050-60-comments) and give many relevant comments.\
Even if I was not one of the members who did the final approvals and merging of a PR, I would check out the merged PRs' changes and reviews to keep myself informed, and see if there was anything missed, opening various followup issues as needed ([example 1](https://github.com/AY2324S1-CS2103-W14-3/tp/issues/53) [example 2](https://github.com/AY2324S1-CS2103-W14-3/tp/issues/80) [example 3](https://github.com/AY2324S1-CS2103-W14-3/tp/issues/122)).
- Pointed out miscellaneous things that may be notable, e.g. whether the [right git details were configured for the grading scipts](https://github.com/AY2324S1-CS2103-W14-3/tp/commits/master?after=7575f07d444894d96d1849ee81bbb0bdfcd05802+454&branch=master&qualified_name=refs%2Fheads%2Fmaster#:~:text=Remove%20rubbish-,DESKTOP%2DITF4GUD%5C94866,-committed%20on%20Oct), whether everyone was watching the repo as instructed by the course website, about the [use of automation](https://github.com/Cloud7050/js-canvastransfer) to save time with quizzes.
- Contributed to GitHub/Telegram/Zoom discussions to help clarify/answer questions and add my thoughts. Discussed steps for how to accomplish certain tasks or areas that could be enhanced.

### Contributions beyond the project team

- Put a good amount of effort into [finding HouR bugs during the PE-D](https://github.com/AY2324S1-CS2103T-W12-1/tp/issues?q=is%3Aissue+c%5D). Kept an eye on the transferred CATcher issues for comments in case there were things I could help clarify after the fact.
