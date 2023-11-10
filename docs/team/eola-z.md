---
layout: page
title: Eola-Z's Project Portfolio Page
---

### Project: NetworkBook

NetworkBook is a desktop address book application 
that School of Computing students can use to track and
network with peers. The user interacts with it using
a CLI, and has a GUI created with JavaFX. It is written
in Java, and has about 20 kLoC.

Here are my contributions to the project.

* **New Features**:
  * Added checks for duplicate contact names. The application ensures that
  multiple contacts with the same name cannot be created. 
  * Added specialisations and courses to contact details. Courses, in particular, have start and
  end dates to indicate when a contact has started/finished taking a specific course. These optional fields
  also enable the user to further refine their search. 
  * Added filter commands for courses, specialisations, graduation year, and tags. Each
  filter command is parsed through a global parser which then disseminates into specific parsers. Each filter also
  has its own set of predicates to filter against each person.
* **Enhancements**: 
  * Improve command result messages to give better feed back to the user. So now, users
  can better understand that they're formatting the command incorrectly, or they've left a required field blank,
  and so on.
* **Code contributed**: [tP Dashboard Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=eola-z&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=Eola-Z&tabRepo=AY2324S1-CS2103T-T08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Bug fixes**: Conducted bug fixes for:
  * Json file storage not properly storing course dates
  * Correcting behavior of several commands to match UG specification
  * Fix a runtime error caused by `ArgumentMultiMap`
* **Documentation**:
    * User Guide:
        * Added documentation for the features `remove duplicates`,
        `course` and `specialisation`
        * Added an `About` section, giving an overview of how to start using the
      application and documented new features per version.
        * Provided documentation for `filter` command, specifying the various parameters
      that the command accepts, as well as providing detailed instructions on how
      to use the `filter` by course command with the `taken` parameter.
    * Developer Guide
        * Added documentation for the `Logic` component of the DG to further explain the classes used in the
      application, such as `ArgumentMultiMap` and `CliSyntax`
        * Designed UML Diagrams to demonstrate the logical flow of command parser and the internal workings of each command parser
          * An Activity Diagram is used to illustrate the inner workings of
          `CommandParser`. An input string is passed to `CommandParser` which deciphers which parser
          the string should be passed onto to generate a specific class of `Command`, based on the identifying prefix
          of the command. If the prefix of the input string doesn't exist, throw an error.
* **Team-based tasks**:
  * Attended weekly meetings punctually
  * Reviews for major PRs:
    * Update edit sequence: [#138](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/138)
    * Add undo/redo to displayed contacts: [#169](https://github.com/AY2324S1-CS2103T-T08-2/tp/pull/169)
* **Community tasks**:
  * Produced bug reports for PE-D: [#157](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/157)
  [#158](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/158)
  [#162](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/162)
  [#167](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/167)
  [#172](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/172)
  [#174](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/174)
  [#181](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/181)
  [#191](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/191)
  [#194](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/194)
  [#202](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/202)
  [#205](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/205)
  [#213](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/213)
  [#215](https://github.com/AY2324S1-CS2103T-F10-2/tp/issues/215)

Here are some extracts from my contributions to the 
user guide:

* Description of `specialisation`:
  * > You can add a specialisation to an existing contact.  
  A new specialisation will be added to the contact's list 
  of specialisations, and no new contact will be created.
  Specialisations are displayed in the order they are added.
  * Format: `add /spec [specialisation] /index [index]`
* Description of `filter`:
  * > You can use the `filter` command to filter your list of conatacts,
    temporarily hiding contacts that don't contain certain keywords
    for easy viewing. If the list is currently sorted (using [`sort`](#sort-contacts-list-sort-by-field-name-order-ascdesc)), the filtered list will be sorted.
  * Format: `filter /by [field] /with [term] /taken true/false`
* UI mockups:
  * Created the UI mockups in the user guide to emulate
  the application's behavior in `1.1`.
