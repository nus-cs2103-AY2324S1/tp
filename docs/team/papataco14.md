---
layout: page
title: Ramu Rithik Vijay's Project Portfolio Page
---

#### Project: LinkMeIn

LinkMeIn - Is a desktop address book application made for NUS Computer Science students to track their internship 
applications. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, 
and has about ~10 kLoC.

The features in LinkMeIn can be categorised into 3 main categories:

1.  **General features:** Navigating the application. Includes, help, exit and clear commands.
2.  **Company management features:** Manage an entry of a company. Includes, add, delete, edit, view, remark and unremark commands.
3.  **Company list features:** Manage the list of companies added. Includes, list, find, sort and filter commands.

Given below are my contributions to the project.
**Code contributed**:

The code that I have contributed can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=papataco&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

#### Enhancements implemented:

**1. Implemented the sort command.**

**What it does:**

The `sort` command organizes the list of companies by their respective deadlines, in either ascending or descending order.

**Justification:**

This feature was implemented to assist users in efficiently managing their application deadlines. It addresses the need for quick prioritization and helps prevent missing important deadlines by keeping the closest deadlines at the forefront.

**Highlights:**

-   **Ease of Use**: Case-insensitive input and a default to the most commonly used sorting order (ascending). Also allow various other `SORT-ORDER` inputs such as `asc` and `desc` for fast typists.
-   **Robust Error Handling**: Informs users of incorrect input and guides them towards correct usage.
-   **Thorough Testing**: Ensured reliability and performance across diverse scenarios and data sets.

**2. Implemented the find command.**

**What it does:**

The `find` command enables users to search for companies by keywords, with case-insensitive and partial matching capabilities.

**Justification:**

Implemented to enhance navigability and accessibility, this command allows users to locate companies quickly without exact name recall, significantly improving user experience.

**Highlights:**

-   **Flexible Search**: Supports partial keyword matching for broader search results.
-   **Case-Insensitive Logic**: Ensures user convenience by eliminating the need for precise casing.
-   **Predicate Enhancement**: Modified `NameContainsKeywordsPredicate` for substring matching, boosting the command's functionality.
-   **User-Centric Design**: Selected design alternatives aim for the optimal balance between flexibility and precision, prioritizing ease of use.

**3. Modified the Deadline class** 
Previously, the deadline field in the Deadline class was a string. I have modified this to be a java LocalDate object for better date time management. I also modified the validation regex and error messages to distinguish between invalid date formats and invalid dates. For implementation of the sorting functionality, instead of having the basis of comparison between deadlines being defined in the sort method, I have modified the Deadline class to implement the comparable interface and defined the basis of comparison within the Deadline class itself. This allows for simpler code and better abstraction.

**4. Refactor person to company** 
Changed the person model to company model for better OOP at the start of the project.

#### Contribution to the UG:

The following sections from the UG were contributed by me:

1.  Features:
    -   Sort command: Added description, examples and possible errors for the sort command.
    -   Find a company: Added description, examples and possible error for the find command.
2.  Added known issues
3.  Added FAQ:
    -   How do I load data from another computer?
    -   How do I open up my terminal?

#### Contribution to the DG:

-   Updated the models to use Company instead of Person
-   Added a sequence diagram for the find command implementation discussion
-   Added uses cases for sorting by deadline
-   Reordered the user stories table to be in order of priority

#### Contribution to team-based tasks:

-   Created the developers team in our team organization and linked the team repo to it.
-   Created additional labels for bug triaging for the project.
-   Created a release template for the project and managed two releases, v1.2.0 and v1.3.0.
-   Created a pull request template for the project.
-   Maintained the GitHub issues page to remove duplicates and close issues that are no longer relevant.

#### Review/mentoring contributions:

-   PRs reviewed:
    -   [#107](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/107), [#92](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/92), [#140](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/140), [#167](https://github.com/AY2324S1-CS2103T-T17-2/tp/pull/167)

#### Contributions beyond the project team:

-   Bugs reported for the other team during PE-D: [link to issues](https://github.com/papataco14/ped/issues)
-   Helped others on the forum with smoke testing on Windows [link to forum post](https://github.com/nus-cs2103-AY2324S1/forum/issues/218)
-   Suggested a fix for better code in course textbook website [link to issue](https://github.com/nus-cs2103-AY2324S1/website/issues/2)
