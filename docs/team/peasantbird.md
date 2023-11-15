---
  layout: default.md
  title: "Bertrand's Project Portfolio Page"
---

### Project: RealtorTrackerPlusMax

RealtorTrackerPlusMax is a desktop application made for usage by real estate agents to keep track of their contacts, 
such as buyers and sellers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. 
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the `buyer` command.
  * What it does: Allows users to add a new buyer.
  * Justification: A realtor would need to manage separate lists of buyers and sellers, and refer to each list
    individually depending on his objectives at a certain point in time. Having the ability to add a buyer and maintain
    a list of buyers is crucial for his contact management.
  * Credits: Inspired by the command to add person.


* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. 
  * Credits: The undo & redo feature and its DG implementation details were reused from
    [Address Book (Level4)](https://github.com/se-edu/addressbook-level4) with minor modifications.


* **New Feature**: Added the ability to sort the buyer and seller lists with `bsort` and `ssort`.
  * What it does: The user can sort the buyer/seller list by specifying a `prefix` and a `direction` to sort the list
    by, or sort by the default input order by leaving the `prefix` and `direction` blank. The currently supported 
    prefixes are `n` for name, `ah` for home address, `i` for house info and `prio` for priority, and the supported 
    directions are`a` for ascending and `d` for descending.
  * Justification: A realtor needs to organize his client contact list to get the most out of it. An unorganized list
    would mean potentially missing out on valuable opportunities to maintain important relationships and warm leads.
  * Highlights: This enhancement required an in-depth OOP design. The implementation was challenging as it required
    implementing many new methods and classes, including `SortOrder` and `BuyerComparator`/`SellerComparator`, to 
    abstract out various logic like parsing the sort order input, creating the comparator, then passing the comparator 
    into a `SortedList` which wraps around the original buyer and seller list. This definitely made me think a lot on
    what classes I need to handle which parts of the logic, and how they invoke the methods of each other without 
    causing too much dependency.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=peasantbird&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)


* **Project management**:
  * Team lead for 1.2b iteration
    * Led the product direction for 1.2b, helping the team organise features to implement based on what was most needed
    * Organised timely team meetings to discuss the product direction and delegation of roles
  * Managed releases `v1.2` and `v1.3` (2 releases) on GitHub, including manual tests on the product to ensure 
    correctness before release


* **Enhancements to existing features**:
  * Updated the GUI for the buyer and seller boxes, making them look more sleek and rounding the edges
    (Pull request [\#167](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/167))
  * Wrote additional tests for existing features to increase coverage from 72.56% to 78.34% (Pull requests [\#220](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/220) [\#237](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/237))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort`, `undo/redo`, `help` and `exit` (Pull requests 
      [\#227](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/227),
      [\#223](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/223))
  * Developer Guide:
    * Added implementation details of the `sort` and `undo/redo` features
    * Added test cases in the manual testing section for the `sort` feature
    * Added proposed better error handling for the `sort` feature in planned enhancements
    * Did cosmetic tweaks to existing documentation of planned enhancements: [\#239](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/239)


* **Community**:
  * Timely review and approval of PRs, resulting in faster iterations
  * Contributed to forum discussions ([\#438](https://github.com/nus-cs2103-AY2324S1/forum/issues/438))
