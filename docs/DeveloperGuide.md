---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

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

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

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

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

This approach is also use for `FinancialPlan`. The multiple `Tag` and `FinancialPlan` objects are stored in their respective `HashSet` where each object in the `HashSet` will have a corresponding hashcode.

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
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

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
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

**Target user profile**: Financial Advisors
* has a need to manage a significant number of client contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: 
This tool functions as a digital address book suited to the needs of financial advisors. 
It allows them to track, update, and manage their clients’ information efficiently. 
This is facilitated through the use of a command line interface for efficient and effective querying and 
modifying of clients’ data.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                                   | I want to …​                                                                                                          | So that I can…​                                                                                                                      |
|----------|---------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| `* * *`  | financial advisor who often works with numerous clients                   | have a central repository for my clients’ contacts details                                                            | effectively manage the intricate details of each of my clients.                                                                      |
| `* * *`  | financial advisor                                                         | add clients' contacts to the contact book                                                                             | accumulate contacts for future purposes.                                                                                             |
| `* * *`  | financial advisor                                                         | remove clients contacts from the contact book                                                                         | keep my contact book compact and relevant.                                                                                           |
| `* * *`  | financial advisor                                                         | edit clients’ contacts in the contact book                                                                            | keep my information updated.                                                                                                         |
| `*`      | busy financial advisor                                                    | streamline administrative tasks like tracking my clients contacts                                                     | focus most of my time on giving personalised financial advice and services to my clients.                                            |
| `*`      | financial advisor managing a substantial client portfolio                 | follow a standardised format to collect my clients’ information                                                       | manage data consistency among my clients.                                                                                            |
| `*`      | financial advisor                                                         | search for specific client details                                                                                    | quickly contact my clients.                                                                                                          |
| `*`      | user who values both my clients' time and the quality of our interactions | set reminders for follow-up sessions with clients                                                                     | ensure I never miss an important meeting.                                                                                            |
| `*`      | financial advisor                                                         | efficiently track referral sources for my clients                                                                     | manage their relationships.                                                                                                          |
| `*`      | financial advisor                                                         | sort my clients in certain orders including alphabetical order portfolio value in both ascending and descending order | view my clients in a more systematic manner.                                                                                         |
| `*`      | financial advisor                                                         | filter my clients based on certain metrics like financial products purchased and minimum portfolio value              | choose clients.                                                                                                                      |
| `*`      | financial advisor                                                         | record appointments with clients with the application                                                                 | keep track of when my last meeting with each client is.                                                                              |
| `*`      | financial advisor                                                         | export my contact data and client data in a readable format                                                           | use it for backup purposes or to run data processing with other software tools.                                                      |
| `*`      | financial advisor                                                         | have a dashboard 	                                                                                                    | obtain insights into my clientele base including metrics like client acquisition, retention rates and revenue I generate each month. |
| `*`      | financial advisor	                                                        | categorise contacts based on their financial status (high net worth regular) 	                                        | prioritise my client interactions.                                                                                                   |
| `*`      | financial advisor                                                         | update my profile information (name, contact, details, company)                                                       | ensure my personal information is always accurate.                                                                                   |
| `*`      | an experienced user                                                       | edit the data file directly  	                                                                                        | be more efficient.                                                                                                                   |
| `*`      | user                                                                      | undo actions                                                                                                          | recover from my mistakes.                                                                                                            |
| `*`      | busy financial advisor                                                    | quickly add incomplete details of a client and be reminded about it                                                   | fill the rest in later.                                                                                                              |
| `*`      | financial advisor                                                         | check the appointments scheduled today                                                                                | 	 not forget to meet a client                                                                                                        |
| `*`      | financial advisor                                                         | view contacts of all my clients I am meeting for the day                                                              | 	efficiently search for their contacts.                                                                                              |
| `*`      | financial advisor                                                         | view all the insurance plans my client has purchased easily                                                           | make planning during an appointment easier.                                                                                          |
| `*`      | financial advisor                                                         | filter the plans of my client                                                                                         | to make it focus on certain plans during my appointment                                                                              |
| `*`      | financial advisor                                                         | make updates to their plans on the app easily	                                                                        | keep track of changes to the clients plans                                                                                           |
| `*`      | financial advisor                                                         | check which client has been under me the longest                                                                      | 	plan welfare to retain them as long term customers.                                                                                 |
| `*`      | financial advisor                                                         | add notes on clients                                                                                                  | 	excess them when needed                                                                                                             |
| `*`      | financial advisor                                                         | display data from the address book into a excel file                                                                  | 	more easily present to clients                                                                                                      |
| `*`      | financial advisor                                                         | add tags to customers                                                                                                 | 	collate and notify people with the same plan should there be a new change                                                           |
| `*`      | manager                                                                   | retrieve data on the types of plans purchased                                                                         | 	better understand the products my team member is selling                                                                            |
| `*`      | manager                                                                   | monitor expiring insurance plans                                                                                      | 	advice trainees on time management                                                                                                  |
| `*`      | financial advisor	                                                        | import a file                                                                                                         | 	easily transfer client information when client leaves                                                                               |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `UNOFAS` and the **Actor** is the `financial advisor`, unless specified otherwise)



**U1: Add a client** \
**Precondition:** NIL

**MSS**

1.  User request to add a client to the list 
2.  System adds the client

    Use case ends.

**Extensions**

* 1a. Client details are invalid.
    * 1a1. System shows an error message.

      Use case resumes at step 1.

**U2: View list of clients** \
**Precondition:** NIL

**MSS**

1.  User requests to list clients
2.  System shows a list of clients

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**U3: Edit a client's contacts** \
**Precondition:** NIL

**MSS**

1. User request to edit client’s contacts from the list 
2. System changes the client’s contacts

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. User enters the wrong details.

    * 1b1. System shows an error message.

      Use case resumes at step 1.

**U4: Delete a client** \
**Precondition:** NIL

**MSS**

1. User requests to delete a specific client in the list
2. System deletes the client

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. The specified client is non-existent.

    * 1b1. System shows an error message.

      Use case resumes at step 1.

**U5: Find a client** \
**Precondition:** NIL

**MSS**

1.  User requests to find client
2.  System shows a list of clients which match search query

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with typing speed of above 80WPM for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  A user should be able to have up to 2000 clients.
5.  The product is offered as a free offline service.
6.  The codebase should be well-documented to aid in future maintenance and updates.
7.  Should continue working despite invalid commands and error messages should be shown to the user.
8.  All features added to the code should be tested.
9.  All commands should be able to be executed by a financial advisor with little technical knowledge.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **API**: Application Programming Interface that enables application to use capabilities or data from another application
* **Financial Advisor**: A person who provides financial advice to clients
* **Financial Products**: A product connected with the way a person manages or uses money(eg. Insurance)
* **Client**: A person whos financial products are being managed by a financial advisor
* **Portfolio value**: The intrinsic value of all financial products being held under a clients name
* **Central Repository**: A centralised storage location for all user data
* **Contact details**: Name, email, phone number, next-of-kin name, next-of-kin phone number and home address of a client
* **Manager**: A person who is a superior in charge of managing and mentoring a group of financial advisors.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

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
