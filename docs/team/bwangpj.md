---
layout: page
title: Bryan's Project Portfolio Page
---

<!-- Not complete yet, just to meet the dashboard for today -->


## Overview

**ConText** is a desktop app that allows for managing contacts quickly via text commands, with useful features relevant to NUS SoC students.
While it has a Graphical User Interface, most of its user interactions happen via an in-app Command Line Interface.

## Summary of contributions

### Code contributed

[TP RepoSense Code Dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=bwangpj&breakdown=true)

### Enhancements implemented

1. Filter command to allow users to filter contacts by Tag, and associated tests
2. Store command history and allow navigating between previous commands using up/down arrow keys, similar to an actual CLI, and associated tests 

### Contributions to the UG

- Describe useful features relevant to NUS SoC students at the start of the UG, i.e. filtering and navigating between commands using up/down arrow keys
- Include labelled UI diagram of the various information that is stored and displayed in the UI
- Include documentation for `filter` command
- Explain and emphasise that indexing is 1-based and not 0-based
- Explain and emphasise how duplicate contacts are handled - only by name, and only with an exact match of name
- Clarify how input commands with empty fields are handled

### Contributions to the DG

- Include initial list of all user stories brainstormed
- Describe implementation of:
  - `edit` command
  - `filter` command
  - navigation between command history using up/down arrow keys; including the UI component as well as description of how the feature is implemented
  
  and their associated UML diagrams.
- Add Planned Enhancements appendix to the DG

### Contributions to team-based tasks

- Rename objects in the code to remove traces of AB3, and the associated big refactor
- Remove traces of AB3 from Jekyll
- Remove tutorials and images used in tutorials from the docs
- Release of v1.3 (trial)
- Triage and collate the PE-D bugs for Planned Enhancements
- Help with miscellaneous checkstyle corrections to pass checkstyle

### Review/mentoring contributions

- Review PRs to catch things that may have been missed. 
E.g. https://github.com/AY2324S1-CS2103-W14-3/tp/pull/71#discussion_r1361430312
- Contribute to discussions on GitHub, Telegram and Zoom.

### Contributions beyond the project team

- Put a good amount of effort into [finding bugs during the PE-D](https://github.com/AY2324S1-CS2103T-T17-4/tp/issues?q=is%3Aissue+c%5D). 
