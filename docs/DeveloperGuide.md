---
layout: default.md
title: "Developer Guide"
---

# LoveBook Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

- Code reused from here to disable click feature of `ListView`:
  https://stackoverflow.com/questions/20621752/javafx-make-listview-not-selectable-via-mouse
- Result Display FXML code inspired from here:
  https://github.com/AY1920S2-CS2103T-F09-3/main

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/MainApp.java)) is
in charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `DateListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Date` object residing in the `Model`.

### Logic component

**API
** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of
PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `LoveBookParser` object which in turn creates a
   parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a date).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600" />

How the parsing works:

* When called upon to parse a user command, the `LoveBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `LoveBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API
** : [`Model.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the LoveBook data i.e., all `Date` objects (which are contained in a `UniqueDateList` object).
* stores the currently 'selected' `Date` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Date>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPrefs` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPrefs` objects.
* stores a `DatePrefs` object that represents the user’s preferences for dates. This is exposed to the outside as
  a `ReadOnlyDatePrefs` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

### Storage component

**API
** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save both LoveBook data, Date preferences data and user preference data in JSON format, and read them back into
  corresponding objects.
* inherits from both `LoveBookStorage`, `UserPrefsStorage` and `DatePrefsStorage`, which means it can be treated as
  either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.LoveBook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section aims to describe the implementation details of the features in LoveBook.

There are 12 main features in that you came up with for LoveBook.

1. Add Dates
2. Delete Dates
3. Edit Dates
4. List Dates
5. Set preferences
6. Get most compatible match
7. Get random date
8. Filter dates
9. Sort dates
10. Get best match
11. Star dates
12. Unstar dates

### Add Dates
The add dates feature is implemented using the `AddCommand` class. The `AddCommand` class takes in a `Date` object as a parameter. The `Date` object is used to add the `Date` object to the `Model` component. The `AddCommand` class then returns a `CommandResult` object that contains the added `Date` object.
The Activity Diagram below summarises what happens after the user enters an add command.

<puml src="diagrams/AddActivityDiagram.puml" width="450" />

### Delete Dates
The delete dates feature is implemented using the `DeleteCommand` class. The `DeleteCommand` class takes in a an 'Index' object as a parameter. The 'Index' object is used to identify the `Date` object in the `Model` component to be deleted. The `DeleteCommand` class then returns a `CommandResult` object that contains the deleted `Date` object
 

### Edit Dates
The edit dates feature is implemented using the `EditCommand` class. The `EditCommand` class takes in a an 'Index' object as a parameter. The 'Index' object is used to identify the `Date` object in the `Model` component to be edited. The `EditCommand` class then returns a `CommandResult` object that contains the edited `Date` object
The Sequence Diagram below shows how the components interact with each other for the scenario where the user issues the command `edit 1 name/Cleon`

<puml src="diagrams/EditSequenceDiagram.puml" width="450" />

### List Dates
The list dates feature is implemented using the `ListCommand` class. The `ListCommand` class takes in a `Predicate` object as a parameter. The `Predicate` object is used to filter the `Date` objects in the `Model` component. The `ListCommand` class then returns a `CommandResult` object that contains the filtered `Date` objects.

### Filter dates
The filter feature is implemented using the `FilterCommand` class. The `FilterCommand` class takes in a `Predicate`
object as a parameter. The `Predicate` object is used to filter the `Date` objects in the `Model` component.
The `FilterCommand` class then returns a `CommandResult` object that contains the filtered `Date` objects.

The _Activity_ diagram summarises what happens after the user enters a filter command.

<puml src="diagrams/FilterActivity.puml" width="450" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `filter name/ John`

<puml src="diagrams/FilterSequence.puml" width="450" />

### Sort dates

The sort feature is implemented using the `SortCommand` class. The `SortCommand` class takes in a `Comparator`
object as a parameter. The `Comparator` object is used to sort the `Date` objects in the `Model` component.
The `SortCommand` class then returns a `CommandResult` object that contains the sorted `Date` objects.

The _Activity_ diagram summarises what happens after the user enters a sort command.

<puml src="diagrams/SortActivity.puml" width="450" />


The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `sort name/ increasing`

<puml src="diagrams/SortSequence.puml" width="450" />

### Get Blind Date

The random date feature is implemented using the 'RandomCommand' class. The 'RandomCommand' class calls a
getRandomPerson() method from the model class. Within the getRandomPerson method, a 'Predicate' object is created and
used to filter the 'Date' objects in the 'Model component'. The 'RandomCommand' class then returns a 'CommandResult'
object that contains the random 'Date' object.

### Get best match

The best match feature is implemented using the `BestMatchCommand` class. The `BestMatchCommand` class iterates
through the list of Dates, and calls `GetScore` to get the score of the date based on height, age, horoscope and
income. Each metric will be scored upon 10, and when it deviates from the user's preferences, the score is reduced.
The maximum score is 40.

<puml src="diagrams/BestMatchSequence.puml" width="450" />

### Set preferences

The set preferences feature is implemented using the `SetPrefCommand` class. The `SetPrefCommand` class takes in a
`DatePref` object as a parameter. The `DatePref` object is used to set the `DatePref` object in the `Model` component.
The `SetPrefCommand` class then returns a `CommandResult` object that contains the `DatePref` object.

The _Activity_ diagram summarises what happens after the user enters a set preferences command.
<puml src="diagrams/SetPrefActivity.puml" width="550" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
<puml src="diagrams/SetPrefSequence.puml" width="550" />

### Star dates

The star feature is implemented using the `StarCommand` class. The `StarCommand` class takes in a an 'Index'
object as a parameter. The 'Index' object is used to identify the `Date` object in the `Model` component to be
starred. The `StarCommand` class then returns a `CommandResult` object that contains the starred `Date` object

The _Activity_ diagram summarises what happens after the user enters a star command.

<puml src="diagrams/StarActivity.puml" width="450" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `star 1`

<puml src="diagrams/StarSequence.puml" width="450" />

### Unstar dates

The unstar feature is implemented using the `UnstarCommand` class. The `UnstarCommand` class takes in a an 'Index'
object as a parameter. The 'Index' object is used to identify the `Date` object in the `Model` component to be
unstarred. The `UnstarCommand` class then returns a `CommandResult` object that contains the unstarred `Date` object

The _Activity_ diagram summarises what happens after the user enters a star command.

<puml src="diagrams/UnstarActivity.puml" width="450" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `unstar 1`

<puml src="diagrams/UnstarSequence.puml" width="450" />


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* active online dater
* has a need to manage a number of dates
* prefer desktop apps over other types
* can find, filter and organize their dates for better compatibility
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
LoveBook simplifies the process of storing information of dates and assessing compatibility between user and his/her
dates by taking into account the user’s preferences and profile, thereby enhancing the efficiency and effectiveness of
finding the perfect match.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| As a …​      | I want to …​                                                                                    | So that I can…​                                                                             |
|--------------|-------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| new user     | be greeted with a welcome message when i launch the app                                         | so that I feel welcome                                                                      |
| new user     | be able to key in my height in my preferences                                                   | so that the algorithm can recommend me a suitable date based on our height compatibility    |
| new user     | be able to key in my horoscope in my preferences                                                | so that the algorithm can recommend me a suitable date based on our horoscope compatibility |
| new user     | be able to key in my own income in my preferences                                               | so that the algorithm can recommend me a suitable date based on our income compatibility    |
| new user     | to be able to key in my own gender in my preferences                                            | so that the algorithm can match me to the appropriate gender                                |
| serial dater | be able to pull up a list of my previous dates                                                  | so that I can keep track of who I have dated in the past                                    |
| serial dater | be able to delete dates from my list                                                            | so that my dating list is only limited to those who I am still interested in                |
| serial dater | to be able create a new date entry with his/her gender, name, income, height, horoscope and age | so that I can keep my list growing                                                          |
| serial dater | to be able to edit the details of my date                                                       | so that I can keep my dates details up to date                                              |
| serial dater | to be able to be recommended a complete random date                                             | so that I can have an exciting surprise date that day                                       |
| serial dater | to be able to filter my past dates based on a particular metric                                 | so that I can find dates that I am interested in amidst my long and ever growing list       |
| serial dater | to be able to be recommended the most compatible date for me                                    | so that I optimize my chance of finding my one true love                                    |
| serial dater | to be able to star dates                                                                        | so that I can keep track of outstanding dates                                               |
| serial dater | to be able to unstar dates                                                                      | so that I can keep focused on people who are still outstanding to me                        |
*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `LoveBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use Case: Add a Date

**Main Success Scenario (MSS):**

1. User requests to add a new date to the LoveBook.
2. LoveBook prompts the user to provide the date's details, including name, age number, and gender.
3. User enters the required details.
4. LoveBook validates the input.
5. LoveBook adds the new date to the LoveBook.
6. LoveBook displays a confirmation message.

**Extensions:**

2a. User cancels the operation.

- Use case ends.

4a. The input is invalid (e.g., missing name or an invalid gender height).

- LoveBook shows an error message.
- User is prompted to re-enter the details.
- Use case resumes at step 3.

#### Use Case: Search for a Date

**Main Success Scenario (MSS):**

1. User requests to search for a date in the LoveBook.
2. LoveBook prompts the user to enter a search query (e.g., name or age number).
3. User enters the search query.
4. LoveBook performs a search based on the query.
5. LoveBook displays a list of dates matching the search query.

**Extensions:**

4a. No dates match the search query.

- LoveBook displays a message indicating that no matching dates were found.

#### Use Case: Edit Date Details

**Main Success Scenario (MSS):**

1. User requests to edit the details of a specific date.
2. LoveBook shows a list of dates.
3. User selects the date they want to edit from the list.
4. LoveBook prompts the user to provide the updated details for the selected date.
5. User enters the updated details.
6. LoveBook validates the input.
7. LoveBook updates the date's details with the new information.
8. LoveBook displays a confirmation message.

**Extensions:**

2a. The list is empty.

- LoveBook displays a message indicating that there are no dates to edit.
- Use case ends.

3a. The selected date does not exist.

- LoveBook displays an error message.
- User is prompted to select a valid date.
- Use case resumes at step 3.

6a. The input is invalid (e.g., missing name or an invalid age number).

- LoveBook shows an error message.
- User is prompted to re-enter the details.
- Use case resumes at step 5.

#### Use Case: View Date Details

**Main Success Scenario (MSS):**

1. User requests to view the details of a specific date.
2. LoveBook shows a list of dates.
3. User selects the date they want to view from the list.
4. LoveBook displays the date's details, including name, age number, and gender.

**Extensions:**

2a. The list is empty.

- LoveBook displays a message indicating that there are no dates to view.
- Use case ends.

3a. The selected date does not exist.

- LoveBook displays an error message.
- User is prompted to select a valid date.
- Use case resumes at step 3.

#### Use Case: Delete a Date

**Main Success Scenario (MSS):**

1. User requests to delete a specific date from the LoveBook.
2. LoveBook shows a list of dates.
3. User selects the date they want to delete from the list.
4. LoveBook confirms the deletion with the user.
5. User confirms the deletion.
6. LoveBook deletes the date from the LoveBook.
7. LoveBook displays a confirmation message.

**Extensions:**

2a. The list is empty.

- LoveBook displays a message indicating that there are no dates to delete.
- Use case ends.

3a. The selected date does not exist.

- LoveBook displays an error message.
- User is prompted to select a valid date.
- Use case resumes at step 3.

5a. User cancels the deletion.

- Use case ends.

#### Use Case: Get Best Match

**Main Success Scenario (MSS):**

1. User requests his/her best match.

**Extensions:**
1a. The list is empty.

- LoveBook displays a message indicating that there are no dates.
- Use case ends.

#### Use Case: View Preferences for Dates

**Main Success Scenario (MSS):**

1. User requests to edit the details of a specific date.
2. LoveBook shows the user's preferences.
3. LoveBook prompts the user to provide the updated details for the selected preference.
4. User enters the updated details.
5. LoveBook validates the input.
6. LoveBook updates the preference's details with the new information.
7. LoveBook displays a confirmation message.

**Extensions:**

2a. User did not set a preference.

- LoveBook displays a message indicating a default preference.
- Use case ends.

5a. The input is invalid (e.g., missing date or an invalid age number).

- LoveBook shows an error message.
- User is prompted to re-enter the details.
- Use case resumes at step 5.

### Non-Functional Requirements

1. Usability and Accessibility: The application should provide clear and user-friendly CLI prompts and menus.
   It should support keyboard shortcuts for navigation to enhance accessibility.
2. Scalability: The LoveBook should be capable of storing at least 10,000 contacts without a significant decrease in
   performance.
3. Reliability and Availability: The application should have a 99.9% uptime, ensuring that users can access their
   contacts reliably.
   It should automatically back up LoveBook data daily to prevent data loss.
4. Portability: The CLI application should be compatible with multiple operating systems, including Windows, macOS, and
   Linux.

*{More to be added}*

### Glossary

| Term                       | Definition                                                                                                        |
|----------------------------|-------------------------------------------------------------------------------------------------------------------|
| Date                       | A person that the user is interested in and is currently seeing.                                                  |
| Metric | A certain characteristic of a date. (e.g. Gender, Height)                                                         |
| Command | Text that the user types into the application to perform an action.                                               |
| Parameter | A value that the user provides to the application when executing a command. (e.g. in `gender/M` M is a parameter) |
| GUI | Graphical User Interface                                                                                          |
| CLI | Command Line Interface                                                                                            |
| Mainstream OS |  Windows, Linux, Unix, OS-X |

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.
You are recommended to start with an empty LoveBook and follow the instructions sequentially
in order for the example commands provided to be relevant.
You can refer to the user guide for more details on the features.

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

### Launch

1. Initial Launch
    * Download the jar file and copy it into an empty folder.
    * Double-click the jar file.
    * Expected: The program runs and shows the GUI. Note that the window size may not be optimum.

### Viewing Help

1. Opening the Help window
    * Type the following help command into the text field.<br>
      `help`
    * Press enter.
    * Expected: The GUI shows a popup with a message and a link to the User Guide.

### Adding Dates

1. Adding an Date
    * Type the following add command into the text field.<br>
      `add name/Cleon age/22 gender/F height/176 horoscope/Taurus income/3000`
    * Press enter.
    * Expected: The GUI shows the added Date in the LoveBook.

### Best Match Dates

1. Getting the best match using your Date Preferences
    * Type the following best match command into the text field.<br>
      `bestMatch`
    * Press enter.
    * Expected: The GUI shows the best-matched Date in the LoveBook.

### Clearing Dates in LoveBook

1. Clearing all Dates
    * Type the following clear command into the text field.<br>
      `clear`
    * Press enter.
    * Expected: The GUI shows no Dates in the LoveBook.

### Deleting Dates

1. Delete a Date by index
    * Type the following delete command into the text field.<br>
      `delete 1`
    * Press enter.
    * Expected: The date the specified Index is removed from LoveBook in the GUI.

### Editing Dates

1. Editing a Date by index
    * Type the following edit elderly command into the text field.<br>
      `edit 3 horoscope/Cancer name/Cleon`
    * Press enter.
    * Expected: The GUI shows the new fields for the Date at the specified index. (Sequence doesn't matter)

### Finding Dates

1. Finding Dates by name
    * Type the following find command into the text field.<br>
      `find John`
    * Press enter.
    * Expected: The Date with name in the find command.

1. Finding Dates by multiple names
    * Type the following find command into the text field.<br>
      `find John Cleon`
    * Press enter.
    * Expected: The Dates with names in the find command.

1. Listing all Dates
    * Type the following list command into the text field.<br>
      `list`
    * Press enter.
    * Expected: The GUI shows all Dates in LoveBook.

### Filtering Dates

1. Filtering Dates by metric (e.g. name, age, height)
    * Type the following filter command into the text field.<br>
      `filter age/John`
    * Press enter.
    * Expected: The GUI shows the Dates with age in the filter command.

### Finding a Blind Date

1. Finding a Blind Date
    * Type the following random command into the text field.<br>
      `blindDate`
    * Press enter.
    * Expected: The GUI shows a blind date in LoveBook.

### Exit

1. Exiting the app
    * Use the `exit` command or click the 'X' button in the top right corner.<br>
    * Expected: The app closes.

### Saving

1. Saving window preferences
    * Resize the window to an optimum size, preferably full screen. Close the window.
    * Re-launch the app by double-clicking the jar file.
    * Expected: The most recent window size and location is retained.
    * Note: The window looks best under 1920 x 1080 resolution, 125% scale.

2. Saving data
    * Launch the app by double-clicking the jar file.
    * Execute an add command to add a `Date` in the database.
    * Close the app.
    * Expected: A `data` folder is created under the current repository where the jar file is located.

## **Appendix: Planned Enhancements**

1. Improve the command parser to be more robust. Some examples include:
   - Gender entered while adding a new date is case-sensitive. We are planning to make it case-insensitive.
   - Adding dates with the same name is currently not allowed. We are planning to allow this.
   - Allowing some fields to be optional if user doesn't have access to the information. For example, if the user does
     not know the income of the date, he/she can leave it blank. Currently, this is not allowed. We are planning to
     allow this.

2. Improve the income field to be more robust
   - Currently, the income field is unable to accept a range of values, which might not be inclusive of people who
have careers with variable income.
   - Additionally, right now the income field does not accept a value of 0. This is not inclusive towards students 
who may not have any income. Hence, we plan to modify the field to accept a value of 0, on top of supporting a range 
of values

3. Improve issues regarding sorting stability
   - Currently, you are able to sort the dates by a specific field. However, if we sort by income for instance, and two
dates have the same value, then when one of those two dates are modified by operations such as star or edit, the order 
of the two of them can change. The dates are still sorted in order, just that the stability is disrupted. We intend
to improve upon our star and edit commands such that they do not disrupt the stability in the future.

4. Improve the filter feature to be more robust. Some examples include:
   - Currently, the filter feature only allows the user to filter by metrics limited to name, age, gender and height. We
     are planning to allow the user to filter by other metrics such as horoscope and income.
   - Currently, the filter feature only allows the user to filter by a single keyword for a single metric. We are planning to allow the user
     to filter by multiple keywords.

5. Improve the error message to be more comprehensive
   - Currently, the error message for user that key in multiple invalid keywords only spots the first invalid keyword. We
     are planning to allow the user to know all the invalid keywords that he/she has keyed in.
