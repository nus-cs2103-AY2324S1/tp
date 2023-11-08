---
layout: page
title: Wang Cheng's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to start and end review sessions.
    * What it does: allow users to manage review sessions where they can review their flashcards to improve their memory retention.
    * Justification:
    * Highlights: The actual implementation is facilitated by a new class called `SessionManager`, which conforms to the **Singleton Design Pattern**,
      making sure that there is only one instance of the `SessionManager` throughout the application's lifecycle. 
      This class efficiently manages the creation and destruction of user sessions, ensuring an organized approach to session handling within the system.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=wangcheng0116&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos#/)

* **Enhancements to existing features**:
    *

* **Documentation**:
    * User Guide:
        * Added documentation for the features `yes` `no` `start` and `stop`
        * Drafted UI for main and `list` feature
    * Developer Guide:
        *

* **Contributions to team-based tasks**:
    * Milestone v1.1 creation and issues assignment
* **Community**:
    * PRs reviewed (with non-trivial review comments):

* **Tools**:
    *

