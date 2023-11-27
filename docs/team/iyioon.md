---
layout: page
title: Moon Ji Hoon Project Portfolio Page
---


### Project: CheckMate


CheckMate is designed to streamline the process of room bookings by hotel employees. With a Graphical User Interface (GUI) that provides the necessary information needed at a glance, and the application
being optimised for use via a Command Line Interface (CLI), we have combined elegance and efficiency when it comes to
the process of room booking by hotel receptionists.

Given below are my contributions to the project.

* **New Feature**: Added autocomplete functionality to the command input field. (Pull request [#114](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/145))
  * What it does: Allows the user to quickly complete commands by pressing the TAB key. It provides suggestions based on the prefixes of commands, enhancing the user's efficiency.
  * Justification: This feature greatly enhances user experience. Users no longer need to type in full commands, which not only saves time but also reduces the chance of typos or command errors. Moreover, users are given an example parameter along with prefix completion.
  * Highlights: Implementing this feature required an understanding of both the UI components and the logic behind command parsing. It involved integrating the PrefixCompletion class with the CommandBox UI component and handling key events effectively. Moreover, I ensured that the prefix suggestions were in the correct order and spaced appropriately for optimal usability.

* **Code contributed**: 
  * [Reposense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=iyioon&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Managed release `v1.2`
  * Created and assigned some issues to teammates.
  * Created tasks for each iteration.

* **Enhancements to existing features**:
  * Provided initial skeleton consisting of booking and room model (Pull request [#99](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/99))
  * Updated `find` command to support room searching (Pull request [#114](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/114))
  * Added booking period for check in/out time for bookings (Pull request [#120](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/120))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `prefix complete`.
  * Developer Guide:
    * Added implementation details of the `prefix complete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#176](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/176), [\#182](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/182), [#185](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/185)
  * Contributed to forum discussions (examples: [1](https://github.com/AY2324S1-CS2103T-F10-1/tp/issues/136), [2](https://github.com/AY2324S1-CS2103T-F10-1/tp/issues/181))
  * Some parts of the booking period feature I added was adopted by a classmate ([1](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/123))
