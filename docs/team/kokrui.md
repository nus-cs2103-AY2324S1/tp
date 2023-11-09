---
  layout: default.md
  title: "Wong Kok Rui's Project Portfolio Page"
---

# Project: CampusConnect

**CampusConnect** is a desktop application built for **NUS students** living on campus to help them **stay organised**, **stay connected**, and **make the on-campus experience at NUS stress-free**. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

# Summary of Contributions

## Code Contributed

Refer to this [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kokrui&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=kokrui&tabRepo=AY2324S1-CS2103T-T13-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Enhancements Implemented

- **Enhancement to Existing Feature**: Revamped the `find` command to support expressive filtering of contacts (Pull Requests: [#51](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/51), [#56](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/56), [#115](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/115))
  - What it does: Allows users to search for contacts by constructing arbitrarily-complex boolean search queries (AND, OR, NOT, parentheses) for all available Person attributes. This is supported by a custom tokenizer and custom recursive descent parser written from scratch.
  - Justification: The existing `find` command filters only by name. The target user will want to search by other important fields, and advanced users of our product may want to search by many fields combined by conjunctons, disjunctions, and negations.
- **New Feature**: Added the `pay` and `owe` command to track money loaned to / from contacts (Pull Request: [#89](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/89))
  - What it does: Allows users to track money loaned to / from contacts, and view the total amount of money owed to / by each contact.
  - Justification: The target user will want to track money loaned to / from contacts, and view the total amount of money owed to / by each contact without manually calculating the net money owed.
- **Enhancement to Existing Feature**: Added the ability to use quoted strings as search keywords in `find` command (Pull Request: [#117](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/117))
  - What it does: Allows users to use search keywords that may contain spaces or special characters using quoted strings.
  - Justification: The existing `find` command filters only on a word-level basis. The target user will also want to search for contacts by using keywords that may contain spaces or special characters, such as parts of addresses.
- **Minor Enhancements**: Updated birthday field string representation to human-readable form (Pull Request: [#118](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/118))

## Contributions to the User Guide

- Wrote UG section for revamped `find` command, established use of personal voice adopted in all subsequent UG PRs (Pull Request: [#101](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/101))
- Established and managed MarkBind visual component usage guide (Pull Requests: [#122](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/122), [#132](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/132))
- Added the "Using this Guide" section (Pull Request: [#122](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/122))
- Established new header level structure across the UG (Pull Request: [#133](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/133))
- Wrote UG section for `pay` and `owe` commands (Pull Request: [#150](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/150))

## Contributions to the Developer Guide

- Wrote DG section for revamped `find` command (Pull Request: [#159](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/159))
- Wrote DG section for `pay` and `owe` commands (Pull Request: [#159](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/159))

## Contributions to Team-Based Tasks

- Set up GitHub organization, repository, issue tracker and labels, all milestones, and Codecov test coverage integration
- Initialized MarkBind site configuration for Product Website / UG / DG
- Wrote initial update for project README (Pull Request: [#14](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/14))
- Replaced initial mentions of AB3 with CampusConnect (Pull Request: [#29](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/29))
- Created PPP skeleton templates with all required sections (Pull Request: [#35](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/35))
- Managed releases [v1.2 early wrap-up](https://github.com/AY2324S1-CS2103T-T13-2/tp/releases/tag/v1.2) and [v1.3](https://github.com/AY2324S1-CS2103T-T13-2/tp/releases/tag/v1.3)
- Enabled assertions in repository (Pull Request: [#98](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/98))
- Requested for external TestFX library approval (Forum Issue: [#261](https://github.com/nus-cs2103-AY2324S1/forum/issues/261)), updated CI workflow and Gradle build script to ensure it runs on GitHub CI Runners (Commits: [136e933](https://github.com/AY2324S1-CS2103T-T13-2/tp/commit/136e933b8c8ef5347fdbd74aec72efae7cb09268), [970f579](https://github.com/AY2324S1-CS2103T-T13-2/tp/commit/970f579e3afba6e23e48049e622d51731f3f93ad))



## Review / Mentoring Contributions

- Pull Requests reviewed: 37 [(GitHub)](https://github.com/AY2324S1-CS2103T-T13-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Akokrui), including non-trivial comments on PRs such as: [#95](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/95), [#43](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/43), [#114](https://github.com/AY2324S1-CS2103T-T13-2/tp/pull/114)
- Guided teammates on release management for [v1.3.trial](https://github.com/AY2324S1-CS2103T-T13-2/tp/releases/tag/v1.3.trial) and on MarkBind usage

## Contributions Beyond the Project Team

- Found 13 bugs for team [CS2103T-T17-2](https://github.com/AY2324S1-CS2103T-T17-2/tp) during PE-D
- Occasionally posted ([#261](https://github.com/nus-cs2103-AY2324S1/forum/issues/261), [#273](https://github.com/nus-cs2103-AY2324S1/forum/issues/273), [#341](https://github.com/nus-cs2103-AY2324S1/forum/issues/341), [#374](https://github.com/nus-cs2103-AY2324S1/forum/issues/374)) and answered questions ([#32](https://github.com/nus-cs2103-AY2324S1/forum/issues/32), [#284](https://github.com/nus-cs2103-AY2324S1/forum/issues/284), [#298](https://github.com/nus-cs2103-AY2324S1/forum/issues/298)) on course forum
- In particular, tips on supporting automated UI tests ([1](https://github.com/nus-cs2103-AY2324S1/forum/issues/261), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/284#issuecomment-1780439553)) and maintaining background colors on Markbind PDFs ([1](https://github.com/nus-cs2103-AY2324S1/forum/issues/341)) were referenced multiple times in issues / PRs / commits by other users
