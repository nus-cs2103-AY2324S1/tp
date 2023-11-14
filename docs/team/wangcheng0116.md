---
layout: page
title: Wang Cheng's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to start and end review sessions.
    * What it does: it allows users to manage review sessions where they can review their flashcards to improve their memory retention.
    * Justification: The implementation of 'start' and 'end' review session capabilities allows users to actively review words, thereby enhancing their memory retention.
    * Highlights: This upgrade impacts both current and future commands, demanding an in-depth analysis of design options. Integrating the Singleton pattern posed challenges, necessitating complex adjustments to existing commands.

* **New Feature**: Added the ability to reveal the translation of the particular flash card
    * What it does: It shows/hides the translation of the particular flash card.
    * Justification: The implementation of 'reveal' allows users to test their grasp of words, hence enhancing their memory retention.
    * Highlights: Unlike other commands, `reveal` command has default value when the parameter is omitted. In addition, in order to achieve the same effect of pressing buttons, it requires seamless coordination between command and UI.
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=wangcheng0116&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos#/)

* **Documentation**:
    * User Guide:
        * added documentation for the features `yes`, `no`, `start` and `stop`: [#185](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/185)
        * modified introduction section to briefly explain scientific basis of this application
        * included Application Features to introduce how the application can be used in a high-level manner: [#306](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/306)
        * added glossary section to explain the jargon used in the user guide: [#304](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/304/files)
    * Developer Guide:
        * added implementation details of the features `start` and `stop` commands: [#175](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/175)
        * wrote planned enhancement

* **Contributions to team-based tasks**:
    * Milestone v1.3 creation
    * Bug issues assignment
    * Ensuring coding standard is followed
  
* **Community**:
    * PRs reviewed (with non-trivial review comments): [#197](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/197), [#311](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/311)
    * Reported bugs and suggestions for other teams in the class: [here](https://github.com/WangCheng0116/ped/issues)

