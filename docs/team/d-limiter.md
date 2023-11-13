---
layout: page
title: Taanish Bhardwaj's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added `stats` command for comprehensive learning analysis
    * What it does: This command provides users with detailed learning statistics. It showcases the total count of flashcards, the number of flashcards successfully remembered, and the user's overall success rate.
    * Justification: This feature is specifically designed to motivate novice language learners, our target user group, by visually representing their learning progress. The success rate metric serves as a continuous source of inspiration, encouraging users to improve                          their language skills.
    * Highlights: The development of this feature required an in-depth understanding of Flashlingo's model and its flashcard list management. Key challenges involved accessing and processing the entire flashcard list to accurately calculate the success rate. This                            necessitated intricate integration with the existing system architecture, ensuring that the calculations are accurate and reflect real-time learning progress. The implementation not only provided users with valuable insights but also demonstrated a                         sophisticated application of data analysis within the Flashlingo environment.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=D-Limiter&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=false)
* **Enhancements to existing features**:
  * Enhanced the functionality for storing words in flashcards by creating a unified `Word` class. [#104](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/104)
  * Implemented language parameters for each word in their respective flashcards to support multilingual functionality. [#103](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/103)
  * Conducted extensive and thorough unit testing for each command to ensure of their accuracy and correctness. [#220](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/220)
* **Documentation**:
    * User Guide:
        * Drafted the documentation for `add` and `delete` commands
        * Conducted a thorough review and correction of grammatical errors across various sections of the User Guide to enhance readability and clarity.
        * Authored the comprehensive documentation for `list`, `review`, `stats`, and `exit` commands. [#323](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/323)
    * Developer Guide:
        * Developed and integrated comprehensive use cases for various commands within the project.[#33](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/33) 
        * Authored documentation for the proposed enhancement of integrating `frequency of usage tags`.[#349](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/349)
        * Added documentation for the potential enhancement for adding graphical representations for the `stats` command.[#349](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/349)

* **Contributions to team-based tasks**:
    * Ensured code quality is maintained throughout the project
    * Actively contributed to version-specific milestones, playing a key role in meeting project deadlines and achieving set benchmarks efficiently.
* **Community**:
    * PRs reviewed (with non-trivial review comments): [#179](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/179), [#159](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/159)
    * Contributed to the bug finding for other teams as well, reported bugs can be found [here](https://github.com/D-Limiter/ped/issues)
