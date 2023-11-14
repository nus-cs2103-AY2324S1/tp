---
layout: page
title: Loh Jian Rong's Project Portfolio Page
---

### Project: PropertyMatch

PropertyMatch is a desktop tool for property agents who want to organise their client profiles with their corresponding properties. Property agents can boost their efficiency by seamlessly matching their clients with their desired properties.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jianrong7&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=jianrong7&tabRepo=AY2324S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Enhancements implemented**:
  * Added `listprop` command and tests ([#66](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/66), [#84](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/84))
    * What it does: Takes in command `listprop` and updates the Property List to show all properties in the database.
  * Added goodbye message to `exit` command ([#58](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/58))
    * What it does: Takes in command `exit` and displays a goodbye message. Delays the exit by 3 seconds to give the user sufficient time to read the message.
  * Added `editcust` command and tests ([#110](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/110))
    * What it does: Takes in command `editcust`. Allows the user to edit an existing customer. However, the user cannot edit the customer such that it has a duplicate phone number.
  * Added `clear` command and tests ([#120](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/120))
    * What it does: Takes in command `clear` and removes all existing data, both customer list and property list, in the application.
  * Removed traces of AB3 ([#61](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/61))
  * Fix CI/CD pipeline errors ([#94](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/94))
  * Added guardrails for phone numbers, budgets and prices ([#187](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/187), [#188](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/188))
    * What it does: Ensures that phone numbers, budgets and prices are valid. Phone numbers must start with 6, 8, or 9 and must be 8 digits. Budget and prices must be integers of at least 5 digits and at most 12 digits. It cannot start with a 0.


* **Contributions to the UG**:
  * Make UG more friendly to property agents using our application in these PRs ([#121](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/121), [#167](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/167), [#174](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/174))


* **Contributions to the DG**:
  * Added sequence diagram for edit command in [#97](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/97)
  * Massive change to DG in v1.4 [#229](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/229)
    * Added object diagrams for Customer and Property
    * Added activity diagrams for `addcust` and `addprop` commands
    * Added Appendix B: Instructions for manual testing
    * Added some proposed enhancements in Appendix C
    * Added Appendix D: Effort


* **Project management**:
  * Led weekly group meetings
  * Managed milestones `v1.2` - `v1.4` (4 milestones) on GitHub
  * Managed releases `v1.2` - `v1.4` (4 releases) on GitHub


* **Contributions to team-based tasks**:
  * Setting up the GitHub team org and repo
  * Maintaining the issue tracker
  * Release management
  * Updating user guide that are not specific to feature
  * Updating developer guide that are not specific to feature
  * Set up CI for the team repository
  * Enabled assertions in Gradle
  * Filmed product demo for v1.2 and v1.3


* **Review/mentoring contributions**:
  * Reviewed 16 PRs (Links to PR reviews with non-trivial comments below)
    * [#56](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/56), [#64](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/64), [#87](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/87), [#93](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/93), [#122](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/122), [#173](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/173)
  * Consistently answered queries from team members in the team chat


* **Contributions beyond the project team**:
  * Did the product pitch for CS2101
  * Reported 10 bugs for other team during PED
  * Answered queries in the CS2103T forum (Links to answers)
    * [#1](https://github.com/nus-cs2103-AY2324S1/forum/issues/107#issuecomment-1706281857)
    * [#2](https://github.com/nus-cs2103-AY2324S1/forum/issues/92#issuecomment-1704647357)
