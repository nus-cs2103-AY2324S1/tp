---
layout: page
title: Ferdinand Halim's Project Portfolio Page
---

### Project: PropertyMatch

PropertyMatch is a desktop tool for property agents who want to organise their client profiles with their corresponding properties. Property agents can boost their efficiency by seamlessly matching their clients with their desired properties.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=FerdiHS&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=FerdiHS&tabRepo=AY2324S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**:
  * Added `addcust` commmand and tests
    * [#56](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/56)
    * [#64](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/64)
    * What it does: It takes in a command in the format `addcust n/NAME p/PHONE e/EMAIL b/BUDGET [c/CHARACTERISTIC]…​` and add that customer to the database.
    Where `NAME` represents the name, `PHONE` represents the phone number, `EMAIL` represents the email, `BUDGET` represents the budget, and `CHARACTERISTIC` represent the properties' characteristic wanted by the customer.
  * Added `filtercust` command and tests
    * [#102](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/102)
    * [#117](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/117)
    * What it does: It takes a command in the format `filtercust [b/BUDGET] [c/CHARACTERISTIC]…​` and returns the customers who satisfy the predicate.
    Where `BUDGET` represents the minimum budget of the filtered customers and `CHARACTERISTIC` represents the minimum set of characteristics the filtered customers wanted.
  * Added `filterprop` command and tests
    * [#107](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/107)
    * [#102](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/102)
    * What it does: It takes a command in the format `filterprop [pr/PRICE] [c/CHARACTERISTIC]…​` and returns the properties who satisfy the predicate.
      Where `PRICE` represents the minimum price of the filtered properties and `CHARACTERISTIC` represents the minimum set of characteristics the filtered properties wanted.

* **Contribution to the UG**:
  * [#26](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/26)
  * [#102](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/102)
  * Added and updated `addcust`, `filtercust`, and `filterprop` description.

* **Contribution to the DG**:
  * [#102](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/102)
  * [#25](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/25)
  * Added user stories.
  * Added and updated description and sequence diagram for `filtercust` and `filterprop` command.

* **Review/mentoring contributions**:
  * Reviewed 16 PRs (Links to some PR reviews below)
    * [#98](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/98)
    * [#110](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/110)
    * [#120](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/120)
  * Consistently answered queries from team members in the team chat

* **Contributions beyond the project team**:
  * Did the product pitch for CS2101
  * Reported 10 bugs for other team during PED
