---
layout: page
title: Wang Hejin's Project Portfolio Page
---

### Project: BandConnect++
BandConnect++ helps musicians manage contact with other musicians to easily form a band of their preference. It is optimised for CLI users so that frequent tasks can be done faster by typing in commands.

Given below are my contributions to the project.

* **New Feature: List band members [\#107](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/107), [\#109](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/109)**
  * **What it does:** it allows the user to view the band members information
  * **Justification:** it is a common use case for music producers to check who are the existing band members for a band
  * **Highlights:** this enhancement involves major modifications to the GUI, since we must ensure the musician panel are consistent to whatever the band panel is showing. Many UI modifications are done to other relevant features to enhance the consistency and intuitiveness for users.
  * **Credits:** My teammate did the initial implementation of this feature which supports listing band members by index of the band. I tweaked it so that now it takes in a band name as input since it is more likely for producers to remember the band name instead of band index. I have also done extensive testing to ensure the feature work properly and does not affect other operations.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&chartGroupIndex=76&chartIndex=4)


* **Enhancements to existing features**:
  * Enhanced GUI to include an extra band panel [\#84](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/84)
  * Modified `ModelManager` and relevant commands so as to synchronize musician and band panels and make UI more intuitive for users.
  * Update test utility classes to include new entity `Band`
  * Enforced constraints on phone and email to ensure integrity of data regardless of operations executed (`add`, `delete`, `edit` etc.) [\#151](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/151) [\#192](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/192)
  * Wrote unit test of `findb` command [\#109](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/109)
  * Enhanced unit tests of `add` and `edit` commands [\#193](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/193)


* **Documentation**:
  * User Guide: [\#13](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/13) [\#43](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/43) [\#134](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/134) [\#146](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/146)
    * Introduction
    * About
    * Quick Start
    * Table of Contents
    * Features:
      * Help
      * List
      * Add Musician
      * Delete Musician
      * List Band Members
      * Clear
      * Exit
      * Save
  * Developer Guide: [\#44](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/44) [\#112](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/112)
    * Updated existing UML diagrams to conform to current project [\#204](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/204) 
    * Created 1 new activity diagram for edit musician feature [\#205](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/205)
    * Features:
      * List Band Members
      * Add Musician
      * Edit Musician


* **Team-based tasks**:
  * Set up the GitHub team org and repository
  * Set up CodeCov
  * Maintained the issue tracker
  * Reviewed PRs and gave constructive feedback
  * Helped improve code quality for the whole project
  * Made one jar release
  * Structured the whole UG
  * Changed the product icon and renamed the application
  * Refactored the old AB3 classes for BandConnect++


* **Community**:
  * Participated in PE dry run and reported five bugs for team w15-4

