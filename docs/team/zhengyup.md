---
layout: default.md
title: "Zheng Yu's Project Portfolio Page"
---

### Project: LetsGetHired

LetsGetHired is a desktop application designed to assist in efficiently tracking
and managing internship
applications and their progress. The user interacts with it using a CLI, and it
has a GUI created with JavaFX.
This project is a collaborative effort built with Agile methodology and
comprehensive testing.

Given below are my contributions to the project.

* New Feature: View : Added the ability to select and view a specific internship
  application.
    * What it does : Allows the user to select a specific intern application
      from the application list through
      either the CLI or GUI, and view the in-depth details of the application in
      the select view.
    * Justification : This feature improves the product significantly because it
      allows the user to have a
      clear and organised view of an application's details without having to
      perform any formatting. This is
      a significant user experience improvement from viewing details from the
      list view, and facilitates
      other core features like adding notes to an application, which requires
      more screen space to render.
    * Highlights : This enhancement required consideration of the layout of the
      user interface.
* New Feature : Undo : Added the ability to undo previous commands.
    * What it does : allows the user to undo all previous commands one at a
      time, up to the time the
      app was started.
    * Justification : This feature improves the product significantly because a
      user can make mistakes
      in commands and the app should provide a convenient way to rectify them.
      This makes the app more
      user-friendly as it reduces the destructive nature of some commands such
      as delete and clear.
    * This enhancement affects existing commands and commands to be added in the
      future. It required an
      in-depth analysis of design alternatives.
    * Credits : Sampled the proposed implementation of the undo/redo in AB3,
      which suggested the creation of
      a Versioned extension of the InternTracker class which allows committing
      and undoing changes, but
      decided against adopting the proposed data structure to manage version
      control.

* **Code contributed**:
* [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=zhengyup&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)
  summarizes my
  contributions to the codebase
* [PRs authored by me](https://github.com/AY2324S1-CS2103T-W17-2/tp/pulls?q=is%3Apr+author%3Azhengyup) -
  over
  15+ PRs authored and merged

* **Enhancements to existing features**:
    * Revamped the GUI color scheme from a dark theme to a light
      theme [#101](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/101)
    * Updated the coloring of status tags to create better distinction and
      improve user
      experience [#101](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/101)
    * Introduced more possible inputs for the status field that would allow
      users to better track the
      progress of an
      application [#101](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/101)

* **Documentation**:
    * User Guide:
        * Removed all previous references to AB3 and replaced with appropriate
          fields from
          LetsGetHired [#44](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/44)
        * Provided the outlines for the View and Undo commands
    * Developer Guide:
        * Added "useful" user stories and their associated use
          cases [#26](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/26)
        * Refined and revised use cases after the
          tutorial [#43](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/43)
        * Added Appendix: Effort section which highlights the difficulty,
          challenges faced and
          achievements of our group in the development of
          LetsGetHired [#197](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/197)
        * Added section on "Redo" under planned
          enhancements [#209](https://github.com/AY2324S1-CS2103T-W17-2/tp/pull/209)

* **Community**:
    * [PRs reviewed by me](https://github.com/AY2324S1-CS2103T-W17-2/tp/pulls?q=is%3Apr+reviewed-by%3Azhengyup)
      over 10+ PRs reviewed



