---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

---

## **Acknowledgements**

- Libraries: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)
- App icon from http://www.mcdodesign.com/ by Susumu Yoshida
- Some code adapted from http://code.makery.ch/library/javafx-8-tutorial/ by Marco Jakob

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

## **Design**

<div markdown="span" class="alert alert-primary">
    :bulb: **Tip:** The `.puml` files used to create diagrams in this document are in the `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ContactListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Contact` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a contact).
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

* stores the address book data i.e., all `Contact` objects (which are contained in a `UniqueContactList` object).
* stores the currently 'selected' `Contact` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Contact>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Contact` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Contact` needing their own `Tag` objects.
    <br>
    <img src="images/BetterModelClassDiagram.png" width="450" />
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

---

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

Step 2. The user executes `delete 5` command to delete the 5th contact in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new contact. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.
</div>

Step 4. The user now decides that adding the contact was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the undo.
</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.
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
  * Pros: Will use less memory (e.g. for `delete`, just save the contact being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


---

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

+**Target user profile**: NUS SoC students, who
* have a need to label their contacts by categories (e.g. classmates in a certain course, professors)
* can type fast and prefers typing
* is reasonably comfortable with command-line inputs

**Value proposition**: Manage contacts quickly via text commands which computing students are familiar with,
and with useful features relevant to computing (and in particular, SoC) students


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                         | I want to …​                                                                         | So that I can…​                                                                 |
|----------|---------------------------------|--------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| `* * *`  | user                            | add contacts                                                                         | keep track of my friends and schoolmates                                        |
| `* * *`  | user                            | delete contacts                                                                      | remove my contact with that person                                              |
| `* * *`  | user                            | view my contacts                                                                     | know who I have as contacts                                                     |
| `* * `   | user                            | update contacts                                                                      | make changes to my contact info when they occur                                 |
| `* *`    | user                            | search for contacts                                                                  | find a specific contact directly and easily                                     |
| `* *`    | potential user                  | access an easy-to-follow tutorial                                                    | understand the app's core features                                              |
| `* *`    | user                            | add tags to contacts                                                                 | classify them based on contact type                                             |
| `* *`    | user                            | attach images to contacts such as a business card scan                               | store all additional or miscellaneous info about a contact                      |
| `* *`    | user                            | share a contact with other users or apps                                             | transfer my contact info easily                                                 |
| `* *`    | user                            | merge duplicate contacts                                                             | my contact list stays clean                                                     |
| `* *`    | user                            | sort contacts by certain criteria                                                    | find contacts satisfying a certain criteria easily                              |
| `* *`    | user of many communication apps | enter info about various platforms that my contacts use                              | keep track of all the various ways I can contact the same person                |
| `* *`    | user                            | indicate whether a contact is from NUS or not                                        | differentiate between NUS and non-NUS contacts                                  |
| `* *`    | user                            | enter names in a different format depending on contact type                          | maintain respect based on profession (e.g. prefix a professor's name with Prof) |
| `* *`    | user                            | see my contacts in different formats depending on their profession                   | easily differentiate them in a familiar way                                     |
| `* *`    | user                            | indicate where I met each contact                                                    | keep track of people I have various levels of familiarity with                  |
| `* *`    | user                            | view contacts by groups or type                                                      | more easily manage related contacts                                             |
| `* *`    | user                            | indicate where I met each contact                                                    | keep track of people I have various levels of familiarity with                  |
| `* *`    | new user                        | perform functions to a satisfying degree despite only using simple or basic commands | use the app without a steep learning curve                                      |
| `* *`    | long-time user                  | identify old and/or rarely-used contacts                                             | hide or delete them to reduce clutter                                           |
| `* *`    | user                            | export my contacts to an external file                                               | backup my contacts’ information                                                 |
| `* *`    | user                            | import my contacts from an external file                                             | quickly populate the app with my existing contacts                              |
| `*`      | user who prefers CLI            | use keyboard shortcuts                                                               | perform tasks more efficiently                                                  |
| `*`      | user                            | see a different background colour for each contact                                   | differentiate between contacts more easily                                      |
| `*`      | power user with many contacts   | use some scripting capabilities                                                      | automate tedious actions or achieve the exact results I specify                 |
| `*`      | busy user                       | use icons to denote certain contact information                                      | identify the information I want at a glance                                     |
| `*`      | user                            | see a log of past interactions with a contact                                        | know my history with my contacts                                                |
| `*`      | advanced user                   | search/filter by specific parts of contacts (e.g. containing certain words)          | narrow down contacts to exactly what I am looking for                           |
| `*`      | user who prefers CLI            | switch between previously entered commands in history                                | easily repeat previous commands                                                 |
| `*`      | infrequent user                 | view a “cheatsheet” or help dialog for the text commands                             | remember some basic commands I may have forgotten                               |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ConText` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add a contact**

**MSS**

1.  User request to add a contact
2.  ConText adds the contact

    Use case ends.

**Extensions**

* 1a. The given data is invalid.

  * 1a1. ConText shows an error message.

    Use case resumes at step 1.

**Use case: UC02 - Delete a contact**

**MSS**

1.  User requests to view the list of contacts (UC03)
2.  User requests to delete a specific contact in the list
3.  ConText deletes the contact

    Use case ends.

**Extensions**

* 2a. The given index is invalid.

    * 2a1. ConText shows an error message.

      Use case resumes at step 2.

**Use case: UC03 - List all contacts**

**MSS**

1. User requests to list contacts
2. ConText shows a list of contacts

**Extensions**

* 2a. The list is empty.

    Use case ends

*{More to be added}*

### Non-functional Requirements

1. "Brownfield" - Changes to the codebase must be done in small increments.
1. "Typing preferred" - The product must target users who can type fast and prefer CLI as their means of input.
1. "Single user" - The product must be designed for a single user.
1. "Incremental" - The product must be developed breadth-first as well as consistently each week.
1. "Human editable file" - Data must be stored locally in a human-editable text file format.
1. "No DBMS" - DataBase Management Systems must not be used.
1. "OO" - Software must mostly follow the object-oriented paradigm.
1. "Platform independent" - Software must work on Windows, Linux, and OSX. I.e., avoid OS-dependent libraries and OS-specific features.
1. "Java version" - Software must work on a computer that has Java version 11 installed.
1. "Portable" - Software must work without requiring an installer.
1. "No remote server" - Software must not depend on a remote server.
1. "External software" - Any 3rd party frameworks/libraries/services used must:
    1. Be free and open-source (except services), with permissive license terms (e.g. non-time limited trial).
    1. Not require installation by users. Services that require account creation on their 3rd party service are strongly discouraged.
    1. Not violate other project constraints.
    1. Be approved by the teaching team.
1. "Screen resolution" - GUI must work well for standard screen resolutions and scales, as specified in the admin info. GUI must still be usable if those factors are non-standard.
1. "Single file" - Software must all be packed into a single JAR file.
1. "File size" - Software must not exceed 100MB and must not be unnecessarily bloated. Documents must not exceed 15MB per file.
1. "PDF-friendly" - Developer and user guides must be PDF-friendly, without using expandable panels, embedded videos, animated GIFs etc.
1. "Minimal network" - Any public APIs used should have a fallback mechanism in the event that they are down. Any NUS data used should have the approval of NUS IT.
1. "Testability" - Features should not be hard to test or make the product hard to test, be the testing manual or automated.
1. "CLI first" - Users who can type fast should be able to accomplish most tasks faster via the CLI as compared to if they were to use a hypothetical GUI-only version of the product.
1. There must exist an image with the exact name and format `docs/images/Ui.png` depicting the final product, with similar proportions as the original AB3 image.
1. There must exist an `AboutUs` page that closely follows the original template, such that CS2103 grading scripts can understand it.
    1. Each team member must have an appropriately named lowercase PNG of their profile picture, as specified in the admin info.
1. There must exist Project Portfolio Pages (PPPs) in `docs/team/`, containing sections specified in the admin info.
    1. Each team member must have their own appropriately named lowercase page file, as specified in the admin info.
    1. The page must be written to account for paged PDF conversion.
1. Documentation must be built using Jekyll or MarkBind, then hosted via GitHub Pages, such that they are compatible with CS2103 grading scripts.
1. Branches must not be deleted after their associated PRs have been merged, so that CS2103 grading scripts can detect that the correct workflow was used.
1. The `README.md` must acknowledge SE-EDU's AB3, which this project is based on. It should also contain the `Ui.png`, as well as the repo's GitHub Actions build status badge.

### Glossary

* **Architecture Diagram**: A visual representation that depicts the high-level design and structure of the software application.

* **Component**: A modular part of the system with a distinct responsibility. The main components mentioned are UI, Logic, Model, and Storage.

* **Commons**: Classes or utilities used by multiple components of the application.

* **Sequence Diagram**: A type of diagram that visually represents how objects in the system interact with each other in a particular sequence.

* **API (Application Programming Interface)**: A set of rules that allows different software entities to communicate with each other.

* **JavaFx**: A Java library used to create desktop applications. It is the framework used for the UI component of the app.

* **JSON (JavaScript Object Notation)**: A lightweight data-interchange format that's easy to read and write for humans and easy to parse and generate for machines.

* **Brownfield**: A term used in software development to describe a project that has existing constraints, typically an existing system or codebase, as opposed to a greenfield project which starts from scratch.

* **CLI (Command Line Interface)**: A type of user interface that allows users to interact with software by typing in commands.

* **Platform independent**: Software that can run on any computer regardless of its operating system, such as Mac/Windows/Linux.

* **Human editable file**: A file format designed to be easily readable and editable by humans.

* **Portable**: Software that doesn't require installation and can be run from any location, such as from a USB stick.

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">
    :information_source: **Note:** These instructions only provide a starting point for testers to work on;
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

### Deleting a contact

1. Deleting a contact while all contacts are being shown

   1. Prerequisites: List all contacts using the `list` command. Multiple contacts in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No contact is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
