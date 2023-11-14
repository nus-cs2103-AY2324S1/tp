---
layout: page
title: Nicole Ng's Project Portfolio Page
---

### Project: PropertyMatch

PropertyMatch is a desktop tool for property agents who want to organise their client profiles with their corresponding properties. Property agents can boost their efficiency by seamlessly matching their clients with their desired properties.

Given below are my contributions to the project.

* **Enhancements implemented**:
  * Added `delprop` command and tests
    * [#67](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/67)
    * What it does: It takes in a command in the format `delprop INDEX` where `INDEX` refers to the index number shown in the displayed property list

  * Added `listcust` command and tests
    * [#69](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/69)
    * [#86](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/86)
    * What it does: Updates the Customer List to show all customers in the database

  * Added `findcust` command and tests
    * [#112](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/112)

    * What it does: It takes in a command in the format `findcust NAME` where `NAME` represents the substring to search for. When executed, the findcust commands scans the customer address book for customers whose names begins with the `NAME` substring at any position within their name. The results are then returned.

    * Justification: The `findcust` feature enhances user convenience and improve customer management within the application. It provides a convenient way to quickly locate specific customers based on a partial name match.

    * Highlights:
      * Flexible Substring Matching: It allows for substring matching at any position within the customer's name, offering flexibility in identifying customers with diverse name structures.

  * Added `findprop` command and tests
      * [#113](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/113)

      * What it does: It takes in a command in the format `findprop NAME` where `NAME` represents the substring to search for. When executed, the `findprop` commands scans the property address book for properties whose names begins with the `NAME` substring at any position within their name. The results are then returned.

      * Justification: The `findprop` feature enhances user convenience and improve property management within the application. It provides a convenient way to quickly locate specific properties based on a partial name match.

      * Highlights:
        * Flexible Substring Matching: It allows for substring matching at any position within the property's name, offering flexibility in identifying properties with diverse name structures.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nicolengk&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Contributions to the UG**
  * Added `findcust` and `findprop` description [#101](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/101)
  * Update overall ug format [#206](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/206)

* **Contributions to the DG**
  * [#101](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/101)
  * Updated description for `delprop`, `delcust`, `findcust` and `findprop`
  * Added sequence diagram for `findcust`

* **Contributions to team-based tasks**:
  * Added UI design [#16](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/16)
  * Added use cases for DG [#41](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/41)
  * Cleaning up UG bugs aft PED

* **Review/mentoring contributions**:
  * Reviewed 14 PRs (some reviewed PR links)
  * [#59](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/59)
  * [#102](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/102)
  * [#114](https://github.com/AY2324S1-CS2103T-W11-2/tp/pull/114)

