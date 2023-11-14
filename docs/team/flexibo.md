---
  layout: default.md
  title: "Debbie's Project Portfolio Page"
---

### Project: FreelanceBuddy

**Overview:** FreelanceBuddy is a **powerful and efficient Command Line Interface (CLI)
optimised app designed for freelancers**.
It's a one-stop solution for freelancers, helping them manage multiple clients, track project statuses,
and stay on top of their financial reports.

Given below are my contributions to the project.

* **New Feature**: Filter by Company
  * What it does: Filters the contacts by company instead of name of contacts
  * Justification: In case freelancers forget the name of client, but they remember the company name
  * Highlights: Similar to filter by name

* **New Feature**: Edit events
  * What it does: Edit events using any parameter
  * Justification: In case freelancers need to reschedule or adjust event details
  * Highlights: Allows editing set of clients (that exists in contacts), but once it edits, it no longer validates whether clients exist or not.

* **New Feature**: Edit finance
  * What it does: Edit finance using any parameter
  * Justification: In case freelancers made a mistake manually and need to readjust the values/client linked
  * Highlights: Edit is able to differentiate between commission and expense (i.e., you can remove a client from expense but not commission with edit command)

* **New Feature**: Tabs
  * What it does: Allows users to navigate between tabs
  * Justification: Collaborating all the 3 different components into one app for easy access for freelancers. 
    * Looks cleaner than putting all 3 sections in one screen.
  * Highlights: Allows using the `tab` command implemented by @dloh2236 to navigate the tabs instead of clicking them.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=flexibo&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Check for code quality throughout the project
  * Managed releases `v1.2`, `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * For the edit method in finance, able to edit for both commissions and expense even though they have different compulsory requirements
  * Refactor the tab command to make use of a Tab object
  * Implemented 2 new books, `financebook.json` and `eventsbook.json` besides the `addressbook.json`.

* **Documentation**:
  * User Guide:
    * `Edit` commands for events and finance
    * `filter-n` and` filter-c` commands for contacts
    * Adding hyperlinks
    * Standardize formatting and error messages for the whole UG as much as possible
    * Images for all `filter` commands
    * Data storage
  * Developer Guide:
    * Add tab change implementation
      * Add Sequence Diagram
    * Update storage UML diagram 
    * Add test cases for storage
    * Add instruction for manual testing for Storage

* **Community**:
  * [PR reviews for group mates](https://github.com/AY2324S1-CS2103T-W09-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aflexibo)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/417), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/277), [3](https://github.com/nus-cs2103-AY2324S1/forum/issues/191))
  * Helping group mates
    * Every week or so I checked the whole code to see if theres anything that can be improved or needs refactoring.
    * Communicate often with my team to clarify any doubts or issues 

* **Tools**:
  * Intellij
  * GitHub
  * PlantUML
  * MarkBind
