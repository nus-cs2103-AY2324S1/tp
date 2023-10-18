---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Staff-Snap Developer Guide

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

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
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

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the applicant book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both applicant book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current applicant book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous applicant book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone applicant book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial applicant book state, and the `currentStatePointer` pointing to that single applicant book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the applicant book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the applicant book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted applicant book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified applicant book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the applicant book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous applicant book state, and restores the applicant book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the applicant book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest applicant book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the applicant book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all applicant book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire applicant book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
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

* has a need to manage a significant number of applicants
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: introduces organisation to applicant management, recruitment processes and 
streamlines hiring decisions

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                      | So that I can…​                                |
|----------|---------|-----------------------------------|------------------------------------------------|
| `* * *`  | user    | view all the available commands   | I know how to use the app                      |
| `* * *`  | user    | add a new applicant               | track the the progress of all applicants       |
| `* * *`  | user    | edit an applicant descriptor      | maintain an updated database of all applicants |
| `* * *`  | user    | view the full list of applicants  | access important applicant information         |
| `* * *`  | user    | delete an applicant entry         | only track valid applicants                    |
| `* * *`  | user    | add an interview for an applicant | plan screenings                                |
| `* * *`  | user    | store data locally                | use it on a daily basis consistently           |
| `* *`    | user    | find a specific applicant         | access the applicant's information quickly     |
| `* *`    | user    | sort applicants by a descriptor   | find relevant applicants quickly               |
| `* *`    | user    | filter applicants by a descriptor | find relevant applicants quickly               |
| `* *`    | user    | purge all existing data           | remove sample data and populate real data      |


### Use cases

(For all use cases below, the **System** is `Staff-Snap` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add an applicant**

Guarantees: The new applicant will be added to the list of applicants.

**MSS**

1.  User inputs the command to add an applicant.
2.  Staff-Snap adds the new applicant to the list and displays the updated list.
   
    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends

**Use case: UC02 - Edit applicant's information**

Guarantees: The applicant's information will be updated.

**MSS**

1.  User inputs the command to edit an applicant's information.
2.  Staff-Snap updates the applicant list with the updated applicant information.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC03 - List all applicants**

Guarantees: All applicants will be listed.

**MSS**

1.  User inputs the command to view the list of all applicants.
2.  Staff-Snap displays the list of all applicants.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.
* 1b. Applicant list is empty.

    * 1b1. Staff-Snap shows an empty applicant list.

      Use case ends.

**Use case: UC04 - Delete an applicant**

Guarantees: The applicant will be removed from the list of applicants.

**MSS**

1.  User inputs the command to delete an applicant.
2.  Staff-Snap removes the applicant from the list of applicants.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC05 - Find an applicant by name**

Guarantees: The applicants with name matching the search will be listed.

**MSS**

1.  User inputs the command to to find an applicant by name.
2.  Staff-Snap displays the list of all applicants that match the search.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.
* 1b. No applicant found.

    * 1b1. Staff-Snap shows an empty applicant list.

      Use case ends.

**Use case: UC06 - Sort applicants**

Guarantees: The list of applicants will be sorted by the descriptor.

**MSS**

1.  User inputs the command to sort the applicants by a particular descriptor.
2.  Staff-Snap displays the list of applicants sorted by the descriptor.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC07 - Filter applicants**

Guarantees: Only applicants that satisfies the specified criterion will be listed.

**MSS**

1.  User inputs the command to filter the list of applicants by a specified criterion.
2.  Staff-Snap displays the list of all applicants that satisfies the specified criterion.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC08 - List all commands**

Guarantees: The list of all available commands will be made accessible.

**MSS**

1.  User inputs the command to view the list of all available commands.
2.  Staff-Snap opens the user guide in the default browser.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC09 - Exit the program**

Guarantees: Staff-Snap exits.

**MSS**

1.  User inputs the command to exit the program.
2.  Staff-Snap exits and closes.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. User closes the application window.

    Use case resumes at step 2.

**Use case: UC10 - Clear list of applicants**

Guarantees: The list of applicants will be cleared.

**MSS**

1.  User inputs the command to clear the list of applicants.
2.  Staff-Snap prompts for confirmation
3.  User confirms the action.
4.  Staff-Snap clears the list of applicants and displays an empty list.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 2a. User rejects the confirmation.

    * 2a1. Staff-Snap clears confirmation message.

      Use case ends.

**Use case: UC11 - Add an interview to an applicant**

Guarantees: A new interview will be added to the applicant.

**MSS**

1.  User inputs the command to add an interview to an applicant.
2.  Staff-Snap updates the applicant information with the new interview.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. The entire software should be able to be packaged into a single _JAR file_ for users to download.
3. The file size of the JAR file should not exceed 100MB.
4. A user who can type fast should be able to accomplish most tasks faster via a _command line interface (CLI)_, 
compared to a hypothetical GUI-only version of the app.
5. The product is for single-users. The application should not be running in a shared computer and with
   different people using it at different times. 
6. The software should respond to user input within 2 seconds under normal load conditions.
7. There should be no shared file storage mechanism. The data file created by one user should not be accessed by
   another user during regular operations.
8. The data should be stored locally and should be in a _human editable text file_.
9. The software should work without requiring an installer.
10. The software should not depend on a remote server so that anyone can use the app at anytime.
11. The _GUI_ should not cause any resolution-related inconveniences to the user for
    standard screen resolutions 1920x1080 and higher, and for screen scales 100% and 125%.
12. All functions can be used via the GUI, even if the user experience is not optimal, for resolutions 1280x720 and higher,
    and for screen scales 150%.
13. The software should provide error messages which clearly states the error and provides guidance on correcting the error.
14. The software should provide easily accessible help in the form of documentation for users unfamiliar with the commands.
15. The software should include automated tests to ensure correctness and reliability.

### Glossary

* **Mainstream OS**: Windows, Linux, MacOS
* **JAR file**: A package file format that bundles all the components of a Java application into a single file for distribution.
* **Command Line Interface (CLI)**: A means for users to interact with a software by inputting commands
* **Human editable text file**: A text file that can be viewed and modified using a standard text editor by a user. 
(e.g. a `.txt` file)
* **Graphical User Interface (GUI)**: A type of user interface that allows users to interact with software through 
graphical icons and visual indicators.

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

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
