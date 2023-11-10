---
layout: page
title: Tan Yiming's Project Portfolio Page
---

### Project: BandConnect++

BandConnect++ is a desktop application that helps music producers seeking talented musicians to manage their musician contacts and form a band of their preferences. It is optimised for CLI users so that frequent tasks can be done faster by typing in commands.

Given below are my contributions to the project.

* **New Feature 1: Tagging the musicians with their instruments and genres [\#73](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/73), [\#83](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/83)**
  * **What it does:** it allows the user to tag the musicians with their instruments and genres while checking the input against a list of valid instruments and genres
  * **Justification:** they are more specialised and less error-prone than AB3's general tags, catering to the needs of the target users (music producers)
  * **Highlights:** this enhancement involves changing all layers of the application from `UI` to `Storage` and is the basis of many other features like find musicians.
  * **Credits:** I took the inspiration from the `tag` feature in AB3 and extended it to suit the needs of the target users.


* **New Feature 2: Adding musicians to a band [\#137](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/137), [\#195](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/195)**
  * **What it does:** it allows the user to add multiple musicians to a band using the indices of the musicians and the band
  * **Justification:** it is a common use case for music producers to add multiple musicians to a band at once and checking their suitability for the band
  * **Highlights:** this enhancement involves integrating the existing (refactored from `Person`) `Musician` list with the newly created `Band` list and requires the knowledge of both components.
  * **Credits:** My teammate did the initial implementation of this feature which supports adding one musician at a time. I extended it to support adding multiple musicians at once. I also refactored the code and enhanced its error handling.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tanyyyming&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=tanyyyming&tabRepo=AY2324S1-CS2103T-W11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&since=2023-09-22)


* **Enhancements to existing features**:
  * Enhanced the `find` feature to allow the user to find musicians by their tags, instruments, and genres on top of their names [\#94](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/94), [\#148](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/148)


* **Team tasks contributions**:
  * Set up the product website and did site-wide settings [\#51](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/51)
  * Reviewed PRs of team members and gave constructive feedback
  * Structured the UG and DG to ensure consistency across the team members [\#200](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/200)
  * Fix bugs of other features [\#144](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/144), [\#194](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/194)
  * Product-wide modifications, like GUI [\#140](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/140)
  * Conducted post-PE-D bugs triage, classify bugs and close duplicate and irrelevant issues


* **Documentation**:
  * User Guide [\#154](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/154), [\#200](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/200):
    * Product introduction and overview
    * How to use the guide
    * Features: `edit`, `tags`, `find`, `addm` 
    * Command Summary
    * Glossary
  * Developer Guide [\#203](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/203), [\#208](https://github.com/AY2324S1-CS2103T-W11-3/tp/pull/208):
    * Features: `tags`, `find`, `addm`
    * Add two activity diagrams (`find` and `addm`), one sequence diagram (`find`), and one class diagram (`tags`)
    * Use cases
    * User stories
