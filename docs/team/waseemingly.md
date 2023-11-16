---
layout: page
title: Waseem's Project Portfolio Page
---

### Project: CodeContact

CodeContact is a desktop address book solution for Software Engineering companies. The user interacts with it using a
CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Find
    * What it does: Project managers can find for developers, clients and projects based on the specified attributes.
    * Justification: This feature improves the product significantly as project managers might want to look out for
        certain attributes in a developer before adding them to a new project, or sort clients based on their
        organisation or even group projects according to their deadline.
    * Highlights: This implementation was challenging as it required creating new predicates for each of the attributes
        of the developers, clients and projects. The feature also allows for multi-level searching as project managers
        can find based on multiple attributes at the same time. This allows for even more detailed searching. The find
        feature then displayed the updated filtered list on the GUI. In addition, the find implementation was utilised
    in the implementation of the delete feature as well.

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage.


* **Code contributed
  **: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=waseemingly&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)


* **Project management(team-based tasks contributions)**:
    * Set up GitHub team repository and organisation and added team members to the developers team.
    * Set up the project website
    * Set up CodeCov to generate code coverage data and provides more information about coverage of our tests.
    * Created and maintained issue tracker.
    * Update AboutUs and Index pages.


* **Documentation Contributions**:
    * User Guide Contributions:
        * Added documentation for the features `find` [\#164]()
        * Added the table for list of acceptable parameters.
        * Added UI screenshots of the app to display examples of certain features.
    * Developer Guide Contributions:
        * Added implementation details of the `find` feature.
        * Added planned enhancements for filter results and autocorrect search.
        * Added manual testing cases for `find` and `mark project deadlines` features.
    * README Contributions:
        * Maintained README page of repository


* **Community (Review/mentoring contributions)**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()

* **Contributions beyond the project team:**:
    * Reported bugs and suggestions for other teams in the cohort.

* **Contributions to the Developer Guide (Extracts)**:

### Find Feature

#### Implementation

The find feature is facilitated by a map-based strategy, associating specific prefixes (e.g., "find-developer n/" or "
find-client r/") with corresponding predicates, allowing dynamic generation of filtering criteria based on user input.

Implemented operations include:

- `FindCommandParser#parse()`: Interprets the user's input and generates the appropriate predicate to filter the list of
  developers or clients.
- `Model#updateFilteredPersonList()`: Updates the list displayed in the UI based on the provided predicate.

Given below is an example usage scenario and how the find mechanism behaves at each step:

**Step 1.** The user launches the application. The list of developers and clients are displayed.

**Step 2.** To filter developers by name, the user executes the command `find-developer n/ alice bob`. The application
recognizes the "developer n/" prefix and uses the `NameContainsKeywordsPredicate` to generate a filtering criteria. The
list in the UI is updated to only display developers named Alice or Bob.

**Step 3.** Next, the user wants to find clients from a specific organisation. They use the
command `find-client o/ Google`. The "find-client o/" prefix maps to the `OrganisationContainsKeywordsPredicate` and
filters clients associated with Google.

**Step 4.** If the user provides an unrecognized prefix, e.g., `find-developer z/ alice`, an error message is displayed
informing them of the correct command format.

> :information_source: **Note:** While the user can search by multiple keywords, each keyword maps to an entire word in
> the attributes. For example, searching for "Ali" will not return "Alice".

The following sequence diagram provides an overview of how the find operation is executed:

[Diagram would be inserted here illustrating the parsing of the command, identification of the appropriate predicate, and subsequent filtering of the list.]

### Design Considerations:

**Aspect:** Implementation of the predicate map:

**Alternative 1 (current choice):**

- Use a long chain of `if-else` conditions for each attribute.
    - **Pros:** Explicit parsing logic for each attribute.
    - **Cons:** Code becomes lengthy and hard to maintain. Adding a new attribute involves modifying the parsing logic,
      increasing the risk of errors.

**Alternative 2:**

- Use a map linking prefixes to their corresponding predicate constructors.
    - **Pros:** Simplifies the parsing process. Adding a new attribute to search becomes as simple as adding a new entry
      in the map.
    - **Cons:** A potential mismatch between the prefix and its predicate can lead to wrong results.

Given the benefits of a more maintainable and scalable codebase, we've decided to go with the first alternative. Future
enhancements might include fuzzy search.


* **Contributions to the User Guide (Extracts)**:

### Parameter Information

Within the tables below, you can find out more about the parameters that CodeContact supports. These parameters come in
handy when crafting commands in CodeContact.

Here are some notes about these parameters.

* Each parameter comes with **constraints**. These constraints detail the specific formats of text that
  each parameter accepts as valid user input.
    * Not following these constraints will **result in an error** when entering the command.
      *Nonetheless, CodeContact will not stop working. Rather, a message will be provided to you on
      how to correct your command.

#### Common Parameters

| Parameter | Description                              | Constraints                                                    | Valid Examples              | Invalid Examples                            |
|-----------|------------------------------------------|----------------------------------------------------------------|-----------------------------|---------------------------------------------|
| `n/`      | name of developer/client/project         | alphanumeric characters and spaces, and it should not be blank | Tom Hanks, Elizabeth 2      | 成龍, 潔 いさぎ 世 よ 一 いち, Ganesh s/o Ravichandran |
| `p/`      | phone number of developer/client/project | numeric characters, and it should not be blank                 | 94566835                    |                                             |
| `e/`      | email of developer/client/project        | alphanumeric characters, and it should not be blank            | amy@gmail.com               |                                             |
| `a/`      | address of developer/client/project      | alphanumeric characters and spaces, and it should not be blank | 311, Clementi Ave 2, #02-25 |                                             |
| `r/`      | role of developer/client/project         | alphabetical characters and spaces, and it should not be blank | Developer                   |                                             |
| `pr/`     | project name of developer/client/project | alphanumeric characters and spaces, and it should not be blank | CS2103T                     |                                             |

#### Developer Parameters

| Parameter | Description                  | Constraints                                                                    | Valid Examples | Invalid Examples     |
|-----------|------------------------------|--------------------------------------------------------------------------------|----------------|----------------------|
| `g/`      | github username of developer | alphanumeric characters, and it should not be blank                            | johng, amy123  |                      |
| `d/`      | date joined of developer     | numeric characters in DD-MM-YYYY format, and it should not be blank            | 19-11-2023     | 19/11/2023, 1/1/2023 |
| `s/`      | salary of developer          | numeric characters, and it should not be blank                                 | 5000           |                      |
| `rt/`     | rating of developer          | numeric characters with 1 decimal place and spaces, and it should not be blank | 5.0, 3.5       | 5,3                  |

#### Client Parameters

| Parameter | Description                 | Constraints                                                    | Valid Examples | Invalid Examples |
|-----------|-----------------------------|----------------------------------------------------------------|----------------|------------------|
| `o/`      | organisation name of client | alphanumeric characters and spaces, and it should not be blank | Google         |                  |
| `do/`     | document name of client     | alphanumeric characters and spaces, and it should not be blank | google.com     |                  |

#### Project Parameters

| Parameter | Description | Constraints | Valid Examples | Invalid Examples |
|-----------|------------------------|---|---|--|
| `dr/`     | description of project | alphanumeric characters and spaces, and it should not be blank | App to allow for
different juices to be ordered | |
| `dl/`      | deadline of project | alphanumeric characters and spaces, and it should not be blank| 19-12-2023,Design
backend,HIGH,0 | |


-----------------------------------------------------------------------------------------------

### Find

#### Find developer details

Finds the details of an existing developer in the address book.

Format: `find-developer [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DATE_JOINED] [r/ROLE] [s/SALARY] [pr/PROJECT_NAME] [gh/GITHUB_ID] [ra/RATING]`

* Finds for developers based on the attributes provided.
* At least one of the optional fields must be provided.
* Existing values will be compared to the input values, and the results will include any items that match the provided
  criteria.
* You can combine multiple attributes for a more specific search.
* The search is case-insensitive, so you can use any case for the search criteria.

Example of usage: `find-developer pr/2103T rt/5.0`

* Prints developers in 2103/T project with a 5-star rating.

When command succeeds, CLI shows:

```
This is the one developer with matching information
```
followed by the matching developer's details in the GUI.

#### Find client details

Finds the details of an existing client in the address book.

Format: `find-client [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [o/ORGANISATION] [pr/PROJECT] [d/DOCUMENT]`

* Finds for clients based on the attributes provided.
* At least one of the optional fields must be provided.
* Existing values will be compared to the input values, and the results will include any items that match the provided
  criteria.
* You can combine multiple attributes for a more specific search.
* The search is case-insensitive, so you can use any case for the search criteria.

Example of usage: `find-client o/Google r/Senior developer`

* Prints clients from Google of the senior developer role

When command succeeds, CLI shows:

```
These are the 2 clients with matching information
```
followed by the matching clients' details in the GUI.

#### Find project details

Finds the details of an existing project in the address book.

Format: `find-project [pr/PROJECT_NAME] [d/DESCRIPTION] [dl/DEADLINE]`

* Finds for projects based on the attributes provided.
* At least one of the optional fields must be provided.
* Existing values will be compared to the input values, and the results will include any items that match the provided
  criteria.
* You can combine multiple attributes for a more specific search.
* The search is case-insensitive, so you can use any case for the search criteria.

Example of usage: `find-project pr/JuiceApp`

* Print projects with the name 2103T project.

When command succeeds, CLI shows:

```
This is the one project with matching information
```
followed by the matching project's details in the GUI.
