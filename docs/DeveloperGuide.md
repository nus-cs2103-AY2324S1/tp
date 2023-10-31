---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# CampusConnect Developer Guide

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

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
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
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


### Notifications feature

#### Implementation

The notifications feature is centered around `Event` instances.
`Event`s can represent any type of event with a specific date and time.
This could be a birthday, an upcoming meeting or a deadline.
`Event`s also encapsulate timings where a reminder should be created.

On startup, `EventFactory#createEvents(model)` is used to generate the `Event` instances from the initial state of the model.
Any future events can be added to the data model as well during runtime.

Three public methods for `Event` are important for its usage
* `Event#addMember(Person)` — Adds a `Person` as associated with this event.
* `Event#addReminder(Duration)` — Sets a reminder for the event one `Duration` before the time of the actual event. If `Duration` is set to a day, the reminder will be a day in advance.
* `Event#getNotificationAtTime(LocalDateTime)` — Check if any notifications should be generated based on a specific time, usually the current time should be passed as the parameter.

Below is the class diagram for the `Event` class and it's interactions with the other classes.

<puml src="diagrams/notification-system/ClassDiagram.puml" alt="NotificationClassDiagram" />

The startup sequence for creating initial events is given below as well.

<puml src="diagrams/notification-system/InitEventsSequenceDiagram.puml" alt="InitEventsSequenceDiagram" />

Based on the `Event` instances present in the data model, you can call `Model#getLatestNotifications(LocalDateTime)`, passing in the current datetime, in order to get a list of `Notification` instances representing notifications that should be displayed to the user.
The UI system will make use of this API to check if any notifications should be displayed to the user at startup.

The flow for the startup notifications is described by the following sequence diagram.

<puml src="diagrams/notification-system/StartupNotificationsSequenceDiagram.puml" alt="StartupNotificationsSequenceDiagram" />

#### Design considerations:

**Aspect: Generic design**

A generic event system was created, even though CampusConnect only requires a specific Birthday notification system at the moment.

* **Alternative 1 (current choice):** Generic event system.
  * Pros: Extensible for future types of Events and Notifications beyond birthdays.
  * Cons: More code required, and more indirection in code because of the generic nature.

* **Alternative 2:** Birthday notification system.
  * Pros: Simpler to implement, code will be more straightforward to understand as well.
  * Cons: Any future ideas to implement new notifications and events will not benefit from the existing implementation of the birthday notification system.


### AddAlt feature

#### Implementation Details

The `addalt` feature involves creating a new `Person` object with additional contact details to replace the previous `Person` object.
This is done using the `AddAltPersonDescriptor` class; `AddAltPersonDescriptor` object stores the additional contact information to be added to the previous `Person` object.

As a result, the existing `Person` class in AB3's implementation is enhanced to have the capacity of containing more attributes.
Below is a class diagram on the `Person` class and the classes related to its attributes:

<puml src="diagrams/PersonClassDiagram.puml" alt="PersonClassDiagram" />

The `Person` object is now composed of the following additional attributes due to the `addalt` feature:

* `Email`: The secondary email address of the contact.
* `Linkedin`: The linkedin of the contact.
* `Telegram`: The telegram handle of the contact.
* `Birthday`: The birthday of the contact.

The [**`java.util.Optional<T>`**](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Optional.html) class is utilised to encapsulate the optional logic of the above attributes.

To add these additional attributes into a `Person` object, an `INDEX` parameter, followed by the [prefixes](#prefix-summary) that represent the attributes needs to be specified for the `addalt` command.
`INDEX` represents the index number of the contact to be edited in the contact list.

<box type="info">

While all the fields are optional, at least one needs to be given to the `addalt` command.
</box>

The flow for the `addalt` command is described by the following sequence diagram:

<puml src="diagrams/AddAltSequenceDiagram.puml" alt="AddAltSequenceDiagram" />

#### Feature details
1. The application will validate the arguments supplied by the user; whether the `INDEX` provided is valid and each of the additional attribute input follows the pre-determined formats defined in their respective classes.
2. If any of the inputs fails the validation check, an error message is provided with details of the input error and prompts the user for a corrected input.
3. If all of the inputs pass the validation check, a new `Person` objected with the updated attributes is created and stored in the `AddressBook`.

#### Design Considerations

**Aspect: Generic design**

The additional attributes to be added into a `Person` object on top of the original AB3 attributes are encapsulated into their respective classes: `Linkedin`, `Telegram` and `Birthday`. These classes are implemented similarly to the other existing attributes of `Person`, but they are modified according to the respective input patterns that model the real world.
As these attributes are additional information for a `Person` object, every attribute has been made optional in the case when user only keys in several, but not all of the additional attributes into the command. However, the purpose of using this command only exists when users would like to add additional attributes to a `Person` in the contact list. Thus, the `addalt` command is designed to ensure that the user adds at least one of the additional attributes aforementioned.

As this command merely adds additional attributes to a `Person` object, this can be done by enhancing the `add` command.

* **Alternative 1 (current choice):** Create a separate command, `addalt`.
    * Pros:
        * Many cases of empty/*null* inputs in the optional fields need not be accounted for when saving the data and testing when a new `Person` is added by the `add` command.
    * Cons:
        * Inconveniences users as users have to key in two separate commands in order to add additional attributes of a `Person`.
* **Alternative 2:** Enhance the current `add` command.
    * Pros:
        * Improves the user's convenience by allowing them to add both compulsory and optional attributes to a new `Person` entry.
    * Cons:
        * Many cases of empty/*null* inputs in the optional fields have to be accounted for when saving the data and testing.

### Edit Feature

#### Implementation Details

The `edit` feature is similar to the implementation of [**`addalt`**](#addalt-feature); it involves creating a new `Person` object with edited contact details to replace the previous `Person` object.
This is done using the `EditPersonDescriptor` class; `EditPersonDescriptor` object stores the contact information to be updated to the previous `Person` object.

The `edit` command has similar input fields to the [**`addalt`**](#addalt-feature) command with the difference being that it is able to edit all the attributes of a `Person` object except:

* `Note`: The notes of the contact. Read [**`Notes feature`**](#notes-feature) for more details.
* `Avatar`: The profile picture of the contact. Read [**`Update photo feature`**](#update-photo-feature) for more details.

<box type="info">

While all the fields are optional, at least one needs to be given to the `edit` command.
</box>

#### Feature details
1. Similar to [`addalt`](#addalt-feature), the application will validate the arguments supplied by the user; whether the `INDEX` provided is valid and each of `Person` attribute input follows the pre-determined formats defined in their respective classes. However, it also checks that the `edit` command does not update any empty additional attributes of `Person` and update `Person` object to have same primary and secondary email.
2. If an input fails the validation check, an error message is provided which details the error and prompts the user the course of action.
3. If the input passes the validation check, the application checks if the corresponding `Person` and the new `Person` object with the edited attributes is the same.
4. If the check fails, user will be prompted that current `edit` command does not update the `Person` object.
5. Otherwise, a new `Person` objected with the updated attributes is created and stored in the `AddressBook`.

The following activity diagram shows the logic of a user using the `edit` command:

<puml src="diagrams/EditActivityDiagram.puml" alt="EditActivityDiagram" />

The flow for the `edit` command is described by the following sequence diagram:

<puml src="diagrams/EditSequenceDiagram.puml" alt="EditSequenceDiagram" />

#### General Design Considerations
Since `edit` command updates attributes of a `Person` object, setting the values directly to the `Person` object could be another viable option.

* **Alternative 1 (Current choice):** Create a new `Person` object.
    * Pros:
        * Guarantees immutability of `Person` class, reducing possible bugs.
    * Cons:
        * Performance overhead; a `Person` object is always created even if for instance, only one attribute of `Person` object is changed.

* **Alternative 2:** Set the edited attributes to the existing `Person` object.
    * Pros:
        * Minimal performance overhead; less memory usage.
    * Cons:
        * More bug prone.

### Find feature

The find command feature allows users to locate specific Person instances within the application based on keywords provided. Users can input specific terms, and the system will filter and present Person instances that match the given keywords in either their name, address, email, phone number, or tags.

The find command is not just a simple string matching utility within our system. It is an advanced search mechanism that employs tokenization, parsing, and supports the evaluation of complex boolean expressions. This makes it an invaluable tool for users who want to execute intricate searches and filter results based on a combination of criteria.

#### Implementation

##### `FindCommand` and `FindCommandParser`

The heart of the Find command system is the combination of `FindCommand` and `FindCommandParser`. The latter is responsible for processing raw user input, tokenizing the search criteria using the `FindFilterStringTokenizer`, and subsequently parsing it with the `FindExpressionParser`. The end product is a `FindCommand` object that executes the search based on a `Predicate<Person>` that checks all relevant fields of a `Person`.

Sequence Diagram for FindCommandParser:

<puml src="diagrams/find-command/FindCommandParserSequenceDiagram.puml" alt="FindCommandParserSequenceDiagram" />

##### `FindFilterStringTokenizer`

The `FindFilterStringTokenizer` is tailored to break down the user's search criteria, a `String` into meaningful tokens which can later be parsed into a final `Predicate<Person>`. This process includes recognizing individual terms (in the form of conditions of the form `PREFIX/KEYWORD`), Boolean operators (including `!`, `&&`, and `||`), and parentheses (`(` and `)`).

Class Diagram for FindFilterStringTokenizer:

<puml src="diagrams/find-command/FindFilterStringTokenizerClassDiagram.puml" alt="FindFilterStringTokenizerClassDiagram" />

##### `FindExpressionParser`

The `FindExpressionParser` ingests the tokens produced by the `FindFilterStringTokenizer` and interprets them, creating a singular complete `Predicate<Person>` that's applied on the PersonList.

Class Diagram for FindExpressionParser:

<puml src="diagrams/find-command/FindExpressionParserClassDiagram.puml" alt="FindExpressionParserClassDiagram" />

FindExpressionParser uses the recursive gradient descent algorithm to parse the tokens into a `Predicate<Person>`.

Sequence Diagram for FindExpressionParser showing how a sample input is parsed using the recursive gradient descent algorithm:

<puml src="diagrams/find-command/FindExpressionParserSequenceDiagram.puml" alt="FindExpressionParserSequenceDiagram" />

#### Design considerations:

**Aspect: Command Flexibility vs. Complexity**

* **Alternative 1 (current choice)**: Support boolean operations in `FindCommand`.
  * **Pros**: Provides powerful search capabilities.
  * **Cons**: Increases code complexity and potential for user input errors.

* **Alternative 2**: Only allow simple keyword-based searches.
  * **Pros**: Easier to implement and use.
  * **Cons**: Limits user's searching abilities.


**Aspect: Tokenizer library**

* **Alternative 1 (current choice):** Custom-built tokenizer.
  * **Pros:** Allows for more flexibility in terms of the syntax of the search criteria. Can handle our custom-defined terms, operators, and grouping symbols explicitly.
  * **Cons**: More code required, requires regular maintenance to adapt to new features or changes.

* **Alternative 2:** Use a third-party library.
  * **Pros:** Less code required, might be more robust and have additional features.
  * **Cons**: Less flexibility in terms of the syntax of the search criteria. May not be able to handle our custom-defined terms, operators, and grouping symbols explicitly. Might not cater explicitly to the specific requirements of the Find command. Requires integration efforts.

**Aspect: Tokenization Strategy**

* **Alternative 1 (current choice)**: Custom tokenizer.
  * **Pros**: Greater control and flexibility.
  * **Cons**: More complex to implement and maintain.

* **Alternative 2**: Regular expression-based tokenizer.
  * **Pros**: Can be more concise.
  * **Cons**: May not handle all edge cases or complexities.


**Aspect: Supported Logical Operators**

* **Alternative 1 (current choice):** Use standard boolean operators (`&&`, `||`, `!`).
  * **Pros:** Universally recognized and understood.
  * **Cons**: Limited to boolean logic.

* **Alternative 2:** Support more advanced operators or functions (e.g., nearness search, regex patterns).
  * **Pros:** Provides more power and flexibility to users.
  * **Cons**: Significantly complicates parsing and understanding for users.

**Aspect: Handling Invalid Inputs**

* **Alternative 1 (current choice)**: Throw an exception and inform the user.
  * **Pros**: User is made aware of mistakes immediately.
  * **Cons**: Stops the command processing.
* **Alternative 2**: Silently ignore or correct invalid inputs.
  * **Pros**: Smoother user experience.
  * **Cons**: User might not realize they made a mistake.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

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

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

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

### Notes feature

#### 1. NoteCommand
**Purpose:**
The NoteCommand is used to add a note to a specific person in the Address Book.

**Class Description:**
- `NoteCommand`: This class extends `Command` and is responsible for the logic of adding a note.
- `Index`: This is used to store the index of the person in the filtered person list to whom the note will be added.
- `Note`: This is used to store the content of the note to be added.

**Logic Flow:**
1. The user inputs a command to add a note, specifying the index of the person and the content of the note.
2. The `NoteCommandParser` parses the user input and creates a new `NoteCommand` object.
3. The `execute` method of `NoteCommand` is called.
4. The method retrieves the list of all persons and checks if the provided index is valid.
5. If the index is valid, it retrieves the person at the specified index and adds the note to this person.
6. Finally, a `CommandResult` is returned, indicating that the note has been successfully added.

**Key Methods:**
- `NoteCommand(Index index, Note note)`: Constructor to initialize the command with the specified index and note.
- `execute(Model model)`: Adds the note to the person at the specified index in the model's filtered person list.

#### 2. RemoveNoteCommand
**Purpose:**
The RemoveNoteCommand is used to remove a note from a specific person in the Address Book based on the index of the person and the index of the note.

**Class Description:**
- `RemoveNoteCommand`: This class extends `Command` and is responsible for the logic of removing a note.
- `Index`: The first index refers to the person in the filtered person list, and the second index refers to the note to be removed.

**Logic Flow:**
1. The user inputs a command to remove a note, specifying the index of the person and the index of the note.
2. The `RemoveNoteCommandParser` parses the user input and creates a new `RemoveNoteCommand` object.
3. The `execute` method of `RemoveNoteCommand` is called.
4. The method retrieves the list of all persons and checks if the provided indexes are valid.
5. If the indexes are valid, it retrieves the person and note at the specified indexes and removes the note from this person.
6. Finally, a `CommandResult` is returned, indicating that the note has been successfully removed.

**Key Methods:**
- `RemoveNoteCommand(Index indexPerson, Index indexNote)`: Constructor to initialize the command with the specified indexes.
- `execute(Model model)`: Removes the note from the person at the specified index in the model's filtered person list.

#### 3. Person
**Key Modifications:**
- `List<Note> notes`: A list to store notes associated with the person.
- `addNote(Note note)`: Adds a note to the person.
- `removeNote(int idx)`: Removes a note from the person at the specified index.

### User Interface Implementation for Notes Feature

The Notes feature in the application is implemented using a combination of JavaFX components. Below is a detailed explanation of the development and testing of the UI components relevant to this feature.

#### 1. `PersonCard` UI Component

The `PersonCard` UI component is responsible for displaying individual person's details. It is implemented using FXML and its associated controller class. The relevant parts for the Notes feature are:

- **FXML**: The layout of the `PersonCard` includes a Button component (`notesButton`) for accessing the notes of a person. This button is placed in a VBox in the second column of a GridPane.

  ```xml
  <VBox alignment="CENTER" GridPane.columnIndex="1" GridPane.vgrow="ALWAYS">
    <Region VBox.vgrow="ALWAYS" />
    <Button fx:id="notesButton" onAction="#handleNotesButtonClick" text="Notes (0)" />
  </VBox>
  ```

- **Controller**: In the `PersonCard` controller, there is a method `handleNotesButtonClick()`, which is called when the Notes button is clicked. This method creates a new instance of `NotesWindow` and shows it.

  ```java
  @FXML
  public void handleNotesButtonClick() {
      try {
          NotesWindow notesWindow = new NotesWindow(person);
          notesWindow.show();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  ```

#### 2. `NotesWindow` UI Component

The `NotesWindow` UI component is used to display the notes of a person in a new window.

- **FXML**: It consists of a `ListView` (`notesListView`) to list the notes and a Button to close the window.

  ```xml
  <ListView fx:id="notesListView" />
  <Button text="Close" onAction="#handleClose" />
  ```

- **Controller**: The controller class manages the population of the `ListView` with the notes from the person object and handles the closing of the window.

  ```java
  public class NotesWindow extends UiPart<Stage> {
      //...
      private void populateListView(List<Note> notes) {
          ObservableList<String> notesObservableList = FXCollections.observableArrayList();
          for (Note note : notes) {
              notesObservableList.add(note.toString());
          }
          notesListView.setItems(notesObservableList);
      }
  
      @FXML
      void handleClose() {
          Stage stage = (Stage) notesListView.getScene().getWindow();
          stage.close();
      }
      //...
  }
  ```

#### Testing

For testing the UI components, we use TestFX.

- `NotesWindowTest`: This test class ensures that the `NotesWindow` UI component is functioning as expected.

  ```java
  public class NotesWindowTest extends ApplicationTest {
      //...
      @Test
      public void testNotesDisplay() {
          List<String> expectedNotes = Arrays.asList("Likes to swim", "Likes to run", "Is a chad");
          verifyThat("#notesListView", hasItems(3));
          assertTrue(notesWindow.getNotesListView().getItems().containsAll(expectedNotes));
      }

      @Test
      public void testIsShowing() {
          assertTrue(notesWindow.isShowing());
          interact(() -> notesWindow.hide());
          assertFalse(notesWindow.isShowing());
      }

      @Test
      public void testHandleClose() {
          interact(() -> notesWindow.handleClose());
          assertFalse(notesWindow.isShowing());
      }
      //...
  }
  ```

In the tests, we verify that the `NotesWindow` displays the correct number of notes, that it reacts correctly to interactions, and that it properly closes when the close button is clicked.

Ensure that you replace the actual code and XML content if it changes in the future to keep the documentation up to date.


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

* is an NUS student staying on campus
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                          | I want to …​                                                                                                                        | So that I can…​                                                 |
|----------|----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| `* * *`  | user                             | add new contacts with basic contact information                                                                                     | keep track of the people I know.                                |
| `* * *`  | user                             | add alternative contact information such as telegram, email and linkedin, which are preferred communication channels of my contacts | stay better connected to them                                   |
| `* * *`  | user                             | delete contacts                                                                                                                     | reduce clutter and keep my contact list organised.              |
| `* * *`  | forgetful user                   | upload photos for my contacts                                                                                                       | visually remember them.                                         |
| `* * *`  | user                             | update photos for my contacts                                                                                                       | visually recognize them.                                        |
| `* * *`  | forgetful user                   | add the birthday of my contact                                                                                                      | keep track and remember my contacts’ birthdays.                 |
| `* * *`  | forgetful user                   | receive a notification when it is my contact’s actual birthday                                                                      | remember to celebrate his/her birthday.                         |
| `* * *`  | user                             | opt out of receiving notifications                                                                                                  | keep myself from being distracted by the notifications.         |
| `* * *`  | user                             | search through my contacts based on their respective contact information                                                            | quickly access the information required.                        |
| `* * *`  | user                             | search my contacts by name                                                                                                          | quickly find a person without scrolling through my entire list. |
| `* * *`  | user                             | search my contacts by phone number                                                                                                  | identify who is calling me from an unfamiliar number.           |
| `* * *`  | international in-campus resident | add Singapore’s emergency contact details                                                                                           | access them quickly in urgent situations.                       |
| `* * *`  | in-campus resident               | add campus specific information to my contacts, in particular, a tag called RA (Residential Assistant) and SO (Security Officer)    | quickly reach out to them when required.                        |
| `* * *`  | on-campus student                | quickly list the emergency contacts I have previously registered                                                                    | contact them in an emergency.                                   |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `CampusConnect` and the **Actor** is a `NUS student who stays in campus`, unless specified otherwise)

**Use case: UC1- Opt out notifications**

**MSS**
1. User requests to opt out from receiving notifications.
2. System requests for confirmation.
3. User confirms.
4. System opts out the user from receiving notifications. <br>
Use case ends.

**Extensions**
   * 1a. System detects an error in data entered.
     * 1a1. System shows the correct format for request.
     * 1a2. User enters a new opt out request.

     Steps 1a1-1a2 are repeated until the data entered are correct. <br>
     Use case resumes from step 4.

**Use case: UC2 - List contacts**

**MSS**
1. User requests list all contacts.
2. System shows a list of all contacts. <br>
Use case ends.

**Use case: UC3 - Delete emergency contact**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index to delete contact as an emergency contact.
3. System deletes contact from the emergency contact list. <br>
Use case ends.

**Extensions**
* 1a. System shows an empty contact list.
Use case ends.
* 2a. System detects an invalid index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new delete request. <br>
  Steps 2a1- 2a2 are repeated until the data entered are correct. <br>
  Use case resumes from step 3.

**Use case: UC4 - Delete contact**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index to delete a contact from the system.
3. System deletes contact inside its system. <br>
Use case ends.

**Extensions**
* 1a. System shows an empty contact list.
Use case ends.
* 2a. System detects an invalid index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new delete request. <br>
  Steps 2a1- 2a2 are repeated until the data entered are correct.<br>
  Use case resumes from step 3.

**Use Case UC5: Add emergency contact**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User adds a contact to the emergency contact list.
3. System adds the contact into the emergency contact list. <br>
Use case ends.

**Extensions**
* 1a. System detects that the tag given to the contact is invalid.
  * 1a1. System shows the valid tags for input.
  * 1a2. User enters a new add emergency contact request. <br>
  Steps 1a1- 1a2 are repeated until the data entered are correct. <br>
  Use case resumes from step 3.
* 1b. System detects an invalid index entered.
  * 1b1. System shows an error message.
  * 1b2. User enters a new delete request. <br>
  Steps 1b1- 1b2 are repeated until the data entered are correct. <br>
  Use case resumes from step 3.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Able to hold up to 1000 contacts without a compromise in performance.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should respond within 1 second for any command the user inputs
5. Should be easy to use and navigate for the users.
6. Should be able to accommodate growth and expansion. It should be easy to add new features and functionalities as needed. 
7. Should be easy to maintain and update through a clear and well-documented architecture, and it should be easy to troubleshoot and fix problems should they arise. 
8. Data stored should be persistent until removal by the user, and all contact details should be secure. 
9. The code should be well-organised and well-documented to ensure ease of maintenance and debugging. 
10. Should provide clear and easily accessible help and documentation, including FAQs and tutorials, to assist the user in using the platform effectively. 
11. Should be designed to prevent errors and provide clear, actionable error messages if errors occur, so that users can correct any issues.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

### Prefix summary

| Prefix | Meaning                        | Example                          |
|--------|--------------------------------|----------------------------------|
| n/     | Name of contact                | `n/John Doe`                     |
| p/     | Phone number of contact        | `p/98765432`                     |
| e/     | Email of contact               | `e/johndoe@gmail.com`            |
| a/     | Address of contact             | `a/16 Bukit Timah Road, S156213` |
| t/     | Tags of contact                | 'friend'                         |
| li/    | Linkedin of contact            | `li/john-doe`                    |
| tg/    | Telegram handle of contact     | `tg/@johndoe`                    |
| e2/    | Secondary email of contact     | `e2/johndoe@hotmail.com`         |
| b/     | Birthday of contact            | `b/23/10`                        |

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
