---
layout: page
title: Zhu Xihua's Project Portfolio Page
---

### Project: ManageHR

ManageHR is your all-in-one solution for seamless Human Resources management. Say goodbye to paperwork, spreadsheets,
and administrative headaches. Our cutting-edge software empowers HR professionals to focus on what truly matters – your people.

Given below are my contributions to the project.

* **New Feature**: Implemented Manager-subordinate Relationship feature.
  * **What it does**: It allows users to set hierarchical structure amongst the employees. 
  * **Justification**:
    * ManageHR allows user to mimic real life hierarchical structure of all employees since for HR managers, their 
    responsible can include assigning employees to be under the charge of another. Hence, failing to implement this feature
    can result in the app to be less useful since the HR manager could not easily determine who an employee can be assigned under.
  * **Highlights**:
    * `Manager` Employees needs to exist prior to being able to be in charge of other employees.
    * `Manager` Employees needs to have no more employees under their charge before they can be deleted to prevent those said
    employees to have no one in charge of them.
    *  **Credit** nil

* **New Feature**: Improvement to the `filter` feature.
  * **What it does**: It allows users to filter employees using all the common prefixes beside only the `department` prefix.
  * **Justification**:
    * This feature improvement allows for user to be able to filter on a wider set of keys, thereby allowing user to 
      to get query results that are much more relevant to them.
  * **Highlights**:
    * This feature's improvement required a new subclass of `Hashset`, which behaves differently from normally `Hashset`
      when the `contains` method is called.
    * Multiple prefixes can be utilised simultaneously in combination with one another, which can allow user to determine
    the level of specificity the wish to achieve. 

  * **Credit**: Slightly referenced AB3's `find` and `edit` feature's design as well as to Kenvyn Kwek's prior implementation of feature.
* **Refactor**: Refactored all instances of `AddressBook3` to `manageHr`
  * **What it does**: No changes to features.
  * **Justification**: It is pivotal to do so as failing so could make future implementations more difficult to understand. 
  * **Highlights**: Refactor implementation was hard due to the many dependencies that exists between each class.
  * **Credit**: nil


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=XihuaZ&tabRepo=AY2324S1-CS2103-T16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Assist in merging PRs
* 
* **Documentation**:
    * User Guide:
        * Added documentation for the features `list` and `Employee` class [\#57](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/57)
    * Developer Guide:
        * Added use cases and NFRs to Developer Guide [\#9](https://github.com/AY2324S1-CS2103-T16-1/tp/pull/9) 


