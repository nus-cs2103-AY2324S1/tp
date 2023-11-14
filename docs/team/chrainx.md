---
layout: page
title: Fredy Lawrence's Project Portfolio Page
---

### Project: PropertyMatch

PropertyMatch is a desktop tool for property agents who want to organise their client profiles with their corresponding properties. Property agents can boost their efficiency by seamlessly matching their clients with their desired properties.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about [NUMBER] kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=chrainx&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=Chrainx&tabRepo=AY2324S1-CS2103T-W11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Added `addprop` commmand
    * [#59](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/59)
    * What it does: It takes in a command in the format `addprop n/NAME a/ADDRESS p/PHONE pr/PRICE [c/TAG]…​` and add that property to the database.
      Where `NAME` represents the name, 'ADDRESS' represents the address, `PHONE` represents the phone number, `PRICE` represents the price, and `TAG` represent the properties' characteristic.
  * Added `matchcust` command and tests
    * [#108](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/108)
    * What it does: It takes a command in the format `matchcust INDEX` and returns the properties which satisfy at least one characteristic of the selected customer (If any) and the price is affordable by the customer's budget.
      Where `INDEX` represents number displayed in the UI besides the customer.
  * Added `macthprop` command and tests
    * [#108](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/108)
    * What it does: It takes a command in the format `matchprop INDEX` and returns the customers which searching at least one characteristic of the selected properties (If any) and the price is affordable by the customer's budget.
      Where `INDEX` represents number displayed in the UI besides the property.

* **Contribution to the UG**:
  * [#36](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/36)
  * [#122](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/122)
  * Added and updated `addprop`, `matchcust`, and `matchprop` description.
  * Adjusted the command summary
  * Reordering the UG

* **Contribution to the DG**:
  * [#37](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/37)
  * [#99](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/99)
  * Added user stories.
  * Added and updated description and sequence diagram for `matchcust` and `matchprop` command.

* **Review/mentoring contributions**:
  * Reviewed 16 PRs (Links to some PR reviews below)
    * [#23](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/23)
    * [#68](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/68)
    * [#113](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/113)
  * Consistently answered queries from team members in the team chat

* **Contributions beyond the project team**:
  * Did the product pitch for CS2101
  * Reported 8 bugs for other team during PED
