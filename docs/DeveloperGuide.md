---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# LoveBook Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

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

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/Main.java) and [`MainApp`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `LoveBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a date).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `LoveBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `LoveBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the height book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `LoveBook`, which `Person` references. This allows `LoveBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/java/seedu/height/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both height book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `LoveBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.LoveBook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedLoveBook`. It extends `LoveBook` with an undo/redo history, stored internally as an `LoveBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedLoveBook#commit()` — Saves the current height book state in its history.
* `VersionedLoveBook#undo()` — Restores the previous height book state from its history.
* `VersionedLoveBook#redo()` — Restores a previously undone height book state from its history.

These operations are exposed in the `Model` interface as `Model#commitLoveBook()`, `Model#undoLoveBook()` and `Model#redoLoveBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedLoveBook` will be initialized with the initial height book state, and the `currentStatePointer` pointing to that single height book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th date in the height book. The `delete` command calls `Model#commitLoveBook()`, causing the modified state of the height book after the `delete 5` command executes to be saved in the `LoveBookStateList`, and the `currentStatePointer` is shifted to the newly inserted height book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new date. The `add` command also calls `Model#commitLoveBook()`, causing another modified height book state to be saved into the `LoveBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitLoveBook()`, so the height book state will not be saved into the `LoveBookStateList`.

</box>

Step 4. The user now decides that adding the date was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoLoveBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous height book state, and restores the height book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial LoveBook state, then there are no previous LoveBook states to restore. The `undo` command uses `Model#canUndoLoveBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoLoveBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the height book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `LoveBookStateList.size() - 1`, pointing to the latest height book state, then there are no undone LoveBook states to restore. The `redo` command uses `Model#canRedoLoveBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the height book, such as `list`, will usually not call `Model#commitLoveBook()`, `Model#undoLoveBook()` or `Model#redoLoveBook()`. Thus, the `LoveBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitLoveBook()`. Since the `currentStatePointer` is not pointing at the end of the `LoveBookStateList`, all height book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire height book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the date being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


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
* has a need to manage a significant number of dates
* prefer desktop apps over other types
* can find, filter and organize their dates for better compatibility
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:
LoveBook simplifies the process of storing information of dates and assessing compatibility betweenn user and his/ her dates by taking into account the user’s preferences and profile, thereby enhancing the efficiency and effectiveness of finding the perfect match.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

|  As a …​ | I want to …​                        | So that I can…​                                                                             |
|----------|-------------------------------------|---------------------------------------------------------------------------------------------|
| new user     | be greeted with a welcome message when i launch the app | so that I feel welcome                                                                      |
| new user     | see a brief introduction message of the app and its capabilities and instructions         | so that I know how to get started.                                                          |
| new user     | be able to key in my height in my profile                          | so that the algorithm can recommend me a suitable date based on our height compatibility    |
| new user     | be able to key in my horoscope in my profile           | so that the algorithm can recommend me a suitable date based on our horoscope compatibility |
| new user     | be able to key in my own income in my profile      | so that the algorithm can recommend me a suitable date based on our income compatibility    |
| new user     | to be able to key in my own gender in my own profile | so that the algorithm can match me to the appropriate gender                                |
| serial dater | be able to pull up a list of my previous dates         | so that I can keep track of who I have dated in the past                                    |
| serial dater | be able to delete dates from my list                   | so that my dating list is only limited to those who I am still interested in                |
| serial dater | to be able create a new date entry with his/her gender, name, income, height, horoscope and hobbies                   | so that I can keep my list growing                                                          |
| serial dater | to be able to edit the details of my date             | so that I can keep my dates details up to date                                              |
| serial dater | to be able to be recommended a complete random date            | so that I can have an exciting surprise date that day                                       |
| serial dater | to be able to filter my past dates based on a particular metric             | so that I can find dates that I am interested in amidst my long and ever growing list       |
| serial dater | to be able to be recommended the most compatible date for me            | so that I optimize my chance of finding my one true love                                    |
| serial dater | to be able to set my dating preferences            | so that the algorithm can recommend me a suitable match                                     |
*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `LoveBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use Case: Add a Person

**Main Success Scenario (MSS):**

1. User requests to add a new date to the height book.
2. LoveBook prompts the user to provide the date's details, including name, age number, and gender.
3. User enters the required details.
4. LoveBook validates the input.
5. LoveBook adds the new date to the height book.
6. LoveBook displays a confirmation message.

**Extensions:**

2a. User cancels the operation.
- Use case ends.

4a. The input is invalid (e.g., missing name or an invalid gender height).
- LoveBook shows an error message.
- User is prompted to re-enter the details.
- Use case resumes at step 3.

#### Use Case: Search for a Person

**Main Success Scenario (MSS):**

1. User requests to search for a date in the height book.
2. LoveBook prompts the user to enter a search query (e.g., name or age number).
3. User enters the search query.
4. LoveBook performs a search based on the query.
5. LoveBook displays a list of dates matching the search query.

**Extensions:**

4a. No dates match the search query.
- LoveBook displays a message indicating that no matching dates were found.

#### Use Case: Edit Person Details

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

#### Use Case: View Person Details

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

#### Use Case: Delete a Person

**Main Success Scenario (MSS):**

1. User requests to delete a specific date from the height book.
2. LoveBook shows a list of dates.
3. User selects the date they want to delete from the list.
4. LoveBook confirms the deletion with the user.
5. User confirms the deletion.
6. LoveBook deletes the date from the height book.
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


### Non-Functional Requirements

1.  Usability and Accessibility: The application should provide clear and user-friendly CLI prompts and menus.
It should support keyboard shortcuts for navigation to enhance accessibility.
2.  Scalability: The height book should be capable of storing at least 10,000 contacts without a significant decrease in performance.
3.  Reliability and Availability: The application should have a 99.9% uptime, ensuring that users can access their contacts reliably.
It should automatically back up height book data daily to prevent data loss.
4.  Portability: The CLI application should be compatible with multiple operating systems, including Windows, macOS, and Linux.

*{More to be added}*

### Glossary


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a date

1. Deleting a date while all dates are being shown

   1. Prerequisites: List all dates using the `list` command. Multiple dates in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No date is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
