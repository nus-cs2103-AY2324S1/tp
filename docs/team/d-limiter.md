---
layout: page
title: Taanish Bhardwaj's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a `stats` that allows users to learn more about their learning stt=atisitics
    * What it does: * This command provides users with detailed learning statistics. It showcases the total count of flashcards, the number of flashcards successfully remembered, and the user's overall success rate.
    * Justification: * This feature is specifically designed to motivate novice language learners, our target user group, by visually representing their learning progress. The success rate metric serves as a continuous source of inspiration, encouraging users to improve                          their language skills.
    * Highlights: *  The development of this feature required an in-depth understanding of Flashlingo's model and its flashcard list management. Key challenges involved accessing and processing the entire flashcard list to accurately calculate the success rate. This                            necessitated intricate integration with the existing system architecture, ensuring that the calculations are accurate and reflect real-time learning progress. The implementation not only provided users with valuable insights but also demonstrated a                         sophisticated application of data analysis within the Flashlingo environment.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=a1waysd&breakdown=true)

* **Enhancements to existing features**:
  * Enhanced the functionality for storing words in flashcards by creating a unified `Word` class.  #104
  * Implemented language parameters for each word in their respective flashcards to support multilingual functionality. #103
  * Conducted extensive and thorough unit testing for each command to ensure of their accuracy and correctness.  #220
* **Documentation**:
    * User Guide:
        * Drafted the documentation for `add` and `delete` commands
        * Conducted a thorough review and correction of grammatical errors across various sections of the User Guide to enhance readability and clarity.
        * Authored the comprehensive documentation for `list`, `review`, `stats`, and `exit` commands. #323 
    * Developer Guide:
        * Developed and integrated comprehensive use cases for various commands within the project. 
        * Authored documentation for the proposed enhancement of integrating `frequency of usage tags`.
        * Added documentation for the potential enhancement for adding graphical representations for the `stats` command.

* **Contributions to team-based tasks**:
    * Ensured code quality is maintained throughout the project
    * Actively contributed to version-specific milestones, playing a key role in meeting project deadlines and achieving set benchmarks efficiently.
* **Community**:
    * PRs reviewed (with non-trivial review comments): #179, #159
    * Contributed to the bug finding for other teams as well, reported bugs can be found [here](https://github.com/D-Limiter/ped/issues)
