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

CampusConnect is adapted from the [AddressBook Level-3](https://se-education.org/addressbook-level3/) created by the [SE-EDU initiative](https://se-education.org).

Below are the Java libraries used in this project:

* [Jackson](https://github.com/FasterXML/jackson) for Storage
* [JavaFX](https://openjfx.io/) for UI
* [JUnit5](https://github.com/junit-team/junit5) and [TestFX](https://github.com/TestFX/TestFX) for Testing

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

Here's a class diagram of the `Model` component:

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

<br> Below is a class diagram on the `Person` class and the classes related to its attributes:

<puml src="diagrams/PersonClassDiagram.puml" alt="PersonClassDiagram" />

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
`Event` can represent any type of event with a specific date and time.
This could be a birthday, an upcoming meeting or a deadline.
`Event` also encapsulates timings where a reminder should be created.

On startup, `EventFactory#createEvents(model)` is used to generate `Event` instances from the initial state of the model.
Any future events can be added to the data model as well during runtime.

Three public methods for `Event` are important for its usage
* `Event#addMember(Person)` — Adds a `Person` as associated with this event.
* `Event#addReminder(Duration)` — Sets a reminder for the event one `Duration` before the time of the actual event. For example, if `Duration` is set to a day, the reminder will be a day in advance.
* `Event#getNotificationAtTime(LocalDateTime)` — Check if any notifications should be generated based on a specific time, usually the current time should be passed as the parameter.

Below is the class diagram for the `Event` class and it's interactions with the other classes.

<puml src="diagrams/notification-system/ClassDiagram.puml" alt="NotificationClassDiagram" />

The startup sequence for creating initial events is given below as well.
On a high level, the `MainApp#initEvents()` will use `EventFactory#createEvents(model)` to generate `Event` instances from the intial state of the model, then add all of these events to the model.

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

As a result, the existing `Person` class in AB3's implementation is enhanced to have the capacity of containing more attributes. The `Person` object is now composed of the following additional attributes due to the `addalt` feature on top of the existing attributes from AB3's implementation:

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
1. The application will validate the arguments supplied by the user; whether the `INDEX` provided is valid and each of the additional attribute input follows the pre-determined formats defined in their respective classes. It also checks that the added secondary email does not result in the contact to have duplicate emails.
2. If any of the inputs fails the validation check, an error message is provided with details of the input error and prompts the user for a corrected input.
3. If all of the inputs pass the validation check, a new `Person` objected with the updated attributes is created and stored in the `AddressBook`.

#### Design Considerations

**Aspect: Generic design**

The additional attributes to be added into a `Person` object on top of the original AB3 attributes are encapsulated into their respective classes: `Linkedin`, `Telegram` and `Birthday`. These classes are implemented similarly to the other existing attributes of `Person`, but they are modified according to the respective input patterns that model the real world.<br>

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

### Edit feature

#### Implementation Details

The `edit` feature is similar to the implementation of [**`addalt`**](#addalt-feature); it involves creating a new `Person` object with edited contact details to replace the previous `Person` object.
This is done using the `EditPersonDescriptor` class; `EditPersonDescriptor` object stores the contact information to be updated to the previous `Person` object.

The `edit` command has similar input fields to the [**`addalt`**](#addalt-feature) command with the difference being that it is able to edit all the attributes of a `Person` object except:

* `Note`: The notes of the contact. Read [**`Notes feature`**](#notes-feature) for more details.
* `Avatar`: The profile picture of the contact. Read [**`Update photo feature`**](#update-photo-feature) for more details.
* `Balance`: The amount that the contact owes. Read [**`Payments feature`**](#payments-feature) for more details.

<box type="info">

While all the fields are optional, at least one needs to be given to the `edit` command. Users who wishes to `edit` empty additional attributes of `Person` object should use [`addalt`](#addalt-feature) instead.
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

#### Design Considerations
Since `edit` command updates attributes of a `Person` object, setting the values directly to the `Person` object could be another viable option.

* **Alternative 1 (current choice):** Create a new `Person` object.
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


##### `FindFilterStringTokenizer`

The `FindFilterStringTokenizer` is tailored to break down the user's search criteria, a `String` into meaningful tokens which can later be parsed into a final `Predicate<Person>`. This process includes recognizing individual terms (in the form of conditions of the form `PREFIX/KEYWORD`), Boolean operators (including `!`, `&&`, and `||`), and parentheses (`(` and `)`).


##### `FindExpressionParser`

The `FindExpressionParser` ingests the tokens produced by the `FindFilterStringTokenizer` and interprets them, creating a singular complete `Predicate<Person>` that's applied on the PersonList.

FindExpressionParser uses the recursive gradient descent algorithm to parse the tokens into a `Predicate<Person>`.

Sequence Diagram for FindExpressionParser showing how a sample input is parsed using the recursive gradient descent algorithm:


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

### Update photo feature
#### Implementation
##### Commmand logic
The `UpdatePhotoCommand` feature allows users to update photo of a specific contact. This functionality is essential for forgetful users who want to store photos of contacts to remember them easier.

Two key classes are involved in this implementation:
- `UpdatePhotoCommand`: Handles the logic for updating photo.
- `Avatar`: Represents the photo of a contact.

Photos are stored as an `Avatar` within the Person model.

The `Avatar` class contains a `String` representing the path to the chosen photo.

When a user inputs a command to update photo of a contact, the `UpdatePhotoCommandParser` parses the user input and creates a new `UpdatePhotoCommand` object. This object is then executed, which results in the update of the contact's photo.


The process can be summarized in the following logic flow:
<puml src="diagrams/updatephoto-command/UpdatePhotoCommandSequenceDiagram.puml" />

1. Parse user input to create a `UpdatePhotoCommand` object.
2. Execute the `UpdatePhotoCommand`, which involves:
    - Retrieving the list of all persons.
    - Validating the provided index.
    - Creating an exact copy of the person at the provided index, except for the `Avatar`.
    - Replacing the specified person with their copy.
3. Return a `CommandResult` indicating the outcome.

Key methods in this implementation include:
- `UpdatePhotoCommand(int index, String path)`: Constructor to initialize the command.
- `execute(Model model)`: Updates the photo as well as the model.

### Payments feature

#### Implementation

The Payments feature allows users to keep track of the money they owe to and are owed by their contacts. This feature is useful for users who wish to keep track of their financial transactions with their contacts.

Money is represented with a `Balance` class, which is effectively a wrapper around an `int` value representing a monetary value in cents. Validation, including ensuring that values of balances cannot exceed the transaction limit of CampusConnect (currently set at $10,000), is handled by the `Balance` class.

#### Design considerations

**Aspect: Using integers to represent money value**

* **Alternative 1 (current choice):** Use `int` to represent money value in cents.
  * **Pros**: Simple and straightforward.
  * **Cons**: Requires more complex processing when converting to human-friendly string representations.
* **Alternative 2:** Use `double` to represent money value in dollars.
  * **Pros**: More human-friendly.
  * **Cons**: Vulnerable to floating-point precision errors.


### Notes feature
#### Implementation
##### Commmand logic
The `AddNoteCommand` feature allows users to add personalized notes to a specific contact in the Address Book. This functionality is essential for users who wish to record additional information about their contacts.

Three key classes are involved in this implementation:
- `AddNoteCommand`: Handles the logic for adding the note.
- `Index`: Represents the index of the person in the filtered person list to whom the note will be added.
- `Note`: Represents the content of the note to be added.

Notes are stored as an `ObservableList<Note>` within the Person model. This is done to simplify storage and retrieval of notes, as well as to enable the use of JavaFX components to display the notes with the `Observer` pattern.

The `Note` class is a simple wrapper class that contains a `String` representing the content of the note.

When a user inputs a command to add a note, the `NoteCommandParser` parses the user input and creates a new `AddNoteCommand` object. This object is then executed, which results in the addition of the specified note to the targeted contact.


The process can be summarized in the following logic flow:
<puml src="diagrams/notes/NotesCommandSequenceDiagram.puml" />

1. Parse user input to create a `AddNoteCommand` object.
2. Execute the `AddNoteCommand`, which involves:
    - Retrieving the list of all persons.
    - Validating the provided index.
    - Adding the note to the person at the specified index.
3. Return a `CommandResult` indicating the outcome.

Key methods in this implementation include:
- `AddNoteCommand(Index index, Note note)`: Constructor to initialize the command.
- `execute(Model model)`: Adds the note and updates the model.




##### UI Logic
The Notes feature also includes a user interface component to allow users to interactively add, view, and remove notes from contacts.

The user interface for the Notes feature is implemented using JavaFX components. The main components include:
- `PersonCard`: Displays individual person's details and includes a button for accessing notes.
- `NotesWindow`: A pop-up window that displays all notes associated with a person.

The `PersonCard` component includes a button (`notesButton`) that, when clicked, triggers the `handleNotesButtonClick` method in the controller. This method creates and shows a new `NotesWindow`.

The `NotesWindow` is responsible for displaying the list of notes and is populated using a `ListView` component. The controller for `NotesWindow` handles the population of this list and the closing of the window. `NotesWindow` has a button (`closeButton`) that, when clicked, triggers the `handleCloseButtonClick` method in the controller. This method closes the window. The `NotesWindow` is also closed when the user presses the `ESC` key.

#### Design considerations

**Aspect: Integration with Existing Data Model**
* **Alternative 1 (current choice):** Leverage existing `Person` class and `Model` interface.
    * Pros: Utilizes existing data structures and methods, ensuring consistency and potentially reducing the likelihood of bugs.
    * Cons: The `Person` class becomes more complex as more features are added.
* **Alternative 2:** Create a separate system for managing notes.
    * Pros: Keeps the `Person` class simpler and more focused on contact information.
    * Cons: May result in duplication of effort and increased complexity in ensuring consistency between the contact and note systems.

Choosing alternative 1 aligns with the principle of maximizing code reuse and maintaining consistency across the application, even though it slightly increases the complexity of the `Person` class.

**Aspect: User Interaction and Experience**
* **Alternative 1 (current choice):** Pop-up window for notes.
    * Pros: Provides a clear and focused view for user to manage notes.
    * Cons: Additional window management required; may be less convenient for quick interactions.
* **Alternative 2:** Inline editing within the `PersonCard`.
    * Pros: Potentially more convenient for quick additions or edits.
    * Cons: Could clutter the interface and distract from the main contact information.

Choosing alternative 1 provides a balance between functionality and user interface simplicity, ensuring that the notes feature does not overwhelm the main contact viewing experience.

#### Testing

The UI components are tested using TestFX to ensure that they behave as expected. Test cases include verifying the display of notes, interaction responses, and the proper functioning of the close button. Ensuring thorough testing is vital for maintaining the reliability and user-friendliness of the application.

Take note that UI tests have to be run on the `JavaFX` thread, so UI tests have to extend `ApplicationTest` from `TestFX` to run properly.

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## Planned Enhancements / Known Issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **When executing `add`/`edit` command**, if you try to add/edit a new/existing contact with the same [properties](#properties-of-contact) of another saved contact **(Note: 2 names are considered the same if both of them have the same casing and whitespaces)** in your contact list, CampusConnect allows you to do so. We plan to enhance the `add`/`edit` command such that it **takes into account what makes a contact unique in your contact list.**
3. **When executing `addalt` command**, if you input other prefixes that are not accepted by the command format, the error message shown does not prompt you to remove those prefixes and adhere strictly to the command format. We will be working on this in the future to **improve the specificity of error messages.**
4. **When executing commands with `PERSON_INDEX`**, if you did not input an appropriate index, the error message shown is generic; CampusConnect informs you the format of the command you should adhere to instead of prompting you to input a positive index. We will be working on this in the future to **improve the specificity of error messages.**
5. **When executing `updatephoto` command**, if the `PERSON_INDEX` contains characters besides `0-9`, CampusConnect will be unresponsive as we assume that you will input a valid integer for `PERSON_INDEX`. Moreover, successful execution of the same `updatephoto` command with the same image will still result in `Photo updated` even though the photo is not updated. In addition, you can input multiple valid paths and the command will update your contact profile with the last image. We will be working on **handling more errors and improving the specificity of messages in the future.**
6. **When executing all commands**, CampusConnect only accepts printable ASCII characters. Additionally, certain commands may only accept alphanumeric input (such as name field in `add` not accepting slashes). We plan to improve our internationalization support in the future, allowing for Unicode characters to be used throughout the app since users could have contacts with names including diacritics or non-alphabetic characters (e.g. Tamil, Arabic or Chinese names).
7. **When executing the `find` command**, if you input a `FIND_EXPRESSION` that is not accepted by the command format, the error message shows fairly general error messages. We will be working on this in the future to **improve the specificity of error messages.**
8. **When executing the `find` command**, it is impossible to search for keywords that include the double-quote character (`"`) under any circumstance. We will be working on this in the future to **support searching for the double-quote character**, which could appear in fields such as notes.
9. **When executing the `find` command**, the behavior of the `bal` field is not intuitive, especially for users who do not read the User Guide in-depth. We will be working on this in the future to **improve the ergonomics of `bal` field**, by implementing `>` and `<` operators so users can search for balance amounts below or above the keywords.

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
| `* * *`  | user                             | add alternative contact information such as telegram, email and linkedin | connect with friends through my preferred channels |
| `* * *`  | user                             | delete contacts                                                                                                                     | reduce clutter and keep my contact list organised.              |
| `* * *`  | user                             | add notes associated with my contacts | remember important information regarding my contacts. |
| `* * *`  | user                             | delete notes associated with my contacts | remove unwanted information. |
| `* *`  | forgetful user                   | update photos for my contacts                                                                                                       | visually remember them.                                         |
| `* * *`  | forgetful user                   | add the birthday of my contact                                                                                                      | keep track and remember my contacts’ birthdays.                 |
| `* * *`  | forgetful user                   | receive a notification when it is the day before my contact’s birthday                                                                      | remember to celebrate his/her birthday.                         |
| `* * *`  | user                             | opt out of receiving notifications                                                                                                  | keep myself from being distracted by the notifications.         |
| `* * *`  | user                             | record money owed to a contact | remember to settle the debt.         |
| `* * *`  | user                             | record money owed by a contact | remember to collect the money.         |
| `* * *`  | user                             | search through my contacts based on their respective contact information                                                            | quickly access the information required.                        |
| `* * *`  | user                             | search my contacts by name                                                                                                          | quickly find a person without scrolling through my entire list. |
| `* * *`  | user                             | search my contacts by phone number                                                                                                  | identify who is calling me from an unfamiliar number.           |
| `* * *`  | international in-campus resident | add Singapore’s emergency contact details                                                                                           | access them quickly in urgent situations.                       |
| `* * *`  | in-campus resident               | add campus specific information to my contacts, in particular, a tag called RA (Residential Assistant) and SOS (Security Officer)   | quickly reach out to them when required.                        |
| `* * *`  | on-campus student                | quickly list the emergency contacts I have previously registered                                                                    | contact them in an emergency.                                   |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `CampusConnect` and the **Actor** is a `NUS student who stays in campus`, unless specified otherwise)


**Use case: UC1 - Add new contact**

**MSS**
1. User enters information of the new contact to be added.
2. System adds the new contact into its system. <br>
   Use case ends.

**Extensions**
* 1a. System detects an error in the entered data.
    * 1a1. System shows an error message.
    * 1a2. User enters a new add request. <br>
      Steps 1a1- 1a2 are repeated until the data entered is correct.<br>
      Use case resumes from step 2. <br>

**Use case: UC2 - List all contacts**

**MSS**
1. User requests list all contacts.
2. System shows a list of all contacts. <br>
Use case ends.

**Extensions**
* 1a. System shows an empty contact list. <br>
  Use case ends. <br>

**Use case: UC3 - Add alternative information to existing contact**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index with alternative information to be added for an existing contact in the system.
3. System adds the alternative information to the specified contact inside its system. <br>
   Use case ends.

**Extensions**
* 1a. System shows an empty contact list. <br>
  Use case ends.
* 2a. System detects an error in the entered data.
    * 2a1. System shows an error message.
    * 2a2. User enters a new addalt request. <br>
      Steps 2a1- 2a2 are repeated until the data entered is correct.<br>
      Use case resumes from step 3.
* 3a. System detects that the field of the specified contact is non-empty.
  * 3a1. System shows an error message and prompts users to use edit command. <br>
    Use case ends. <br>

**Use case: UC4 - Edit information of existing contact**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index and information to be edited for an existing contact in the system.
3. System edits the information of the specified contact inside its system. <br>
   Use case ends.

**Extensions**
* 1a. System shows an empty contact list. <br>
  Use case ends.
* 2a. System detects an error in the entered data.
    * 2a1. System shows an error message.
    * 2a2. User enters a new edit request. <br>
      Steps 2a1- 2a2 are repeated until the data entered is correct.<br>
      Use case resumes from step 3.
* 3a. System detects that the additional fields of the specified contact is empty.
    * 3a1. System shows an error message and prompts users to use addalt command. <br>
      Use case ends. <br>

**Use case: UC5 - Delete contact**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index to delete a contact from the system.
3. System deletes contact inside its system. <br>
Use case ends.

**Extensions**
* 1a. System shows an empty contact list. <br>
Use case ends.
* 2a. System detects an invalid index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new delete request. <br>
  Steps 2a1- 2a2 are repeated until the data entered is correct.<br>
  Use case resumes from step 3. <br>


**Use Case UC6 - Add note**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index of the specified contact alongside the note to be added.
3. System adds the note to the contact.<br>
Use case ends.

**Extensions**
* 2a. System detects an invalid index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new add note request.<br>
  Steps 2a1- 2a2 are repeated until the data entered is correct. <br>
  Use case resumes from step 3.

**Use Case UC7 - Remove note**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index of the specified contact alongside the index of the note to be removed.
3. System removes the note from the contact.<br>
Use case ends.

**Extensions**
* 2a. System detects an invalid contact or note index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new remove note request.<br>
  Steps 2a1- 2a2 are repeated until the data entered is correct. <br>
  Use case resumes from step 3.

**Use Case UC8 - Record payment**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index of the specified contact alongside the amount of money paid/owed to that contact.
3. System records money paid/owed to the contact.
4. System displays the contact with an indication of the money owed.<br>
Use case ends.

**Extensions**
* 2a. System detects an invalid index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new payment request.<br>
  Steps 2a1- 2a2 are repeated until the data entered is correct. <br>
  Use case resumes from step 3.
* 2b. System detects an invalid payment amount entered.
  * 2b1. System shows an error message.
  * 2b2. User enters a new payment request.<br>
  Steps 2b1- 2b2 are repeated until the data entered is correct. <br>
  Use case resumes from step 3.

**Use Case UC9 - Search for contact**

**MSS**
1. User enters a search criteria to find a contact.
2. System displays all contacts that match the criteria.<br>
Use case ends.

**Extensions**
* 1a. System detects an invalid search criteria.
  * 1a1. System shows an error message.
  * 1a2. User enters a new search request.<br>
  Steps 1a1 - 1a2 are repeated until the data entered is correct.<br>
  Use case resumes from step 2.

**Use Case UC10 - Update contact photo**

**MSS**
1. User <ins>lists all contacts (UC2).</ins>
2. User enters an index of the specified contact alongside the file path of the new contact photo.
3. System updates the contact's photo to the specified image.<br>
Use case ends.

**Extensions**
* 2a. System detects an invalid index entered.
  * 2a1. System shows an error message.
  * 2a2. User enters a new update photo request.<br>
  Steps 2a1- 2a2 are repeated until the data entered is correct. <br>
  Use case resumes from step 3.
* 2b. System detects an invalid file path entered.
  * 2b1. System shows an error message.
  * 2b2. User enters a new update photo request.<br>
  Steps 2b1- 2b2 are repeated until the data entered is correct. <br>
  Use case resumes from step 3.

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
| t/     | Tags of contact                | `t/friend`                       |
| li/    | Linkedin of contact            | `li/john-doe`                    |
| tg/    | Telegram handle of contact     | `tg/@johndoe`                    |
| e2/    | Secondary email of contact     | `e2/johndoe@hotmail.com`         |
| b/     | Birthday of contact            | `b/23/10`                        |
| path/  | Path to the photo of contact   | `path/D:/images/john-doe.png`    |

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the `CampusConnect.jar` file and copy into an empty folder

   2. Double-click the `CampusConnect.jar` file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimal.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Adding a person

1. Adding a `Person`:

    1. **Test case:** `add n/John Doe p/98765432 e/johndoe@gmail.com a/John street, block 123, #01-01`<br>
       **Expected:** A new `Person` is successfully created with name "John Doe", phone "98765432", email "johndoe@gmail.com" and address "John street, block 123, #01-01". Success details shown in the status message. Moreover, this new `Person` is visible in CampusConnect.

    2. **Test case:** `add n/John Doe p/98765432 e/johndoe@gmail.com a/`<br>
       **Expected:** No new `Person` is created. Error details shown in the status message.
   
   3. **Test case:** `add n/John Doe p/98765432 e/johndoe@gmail.com`<br>
      **Expected:** No new `Person` is created. Error details shown in the status message. 
   
2. To see if the `Person` is added, use the `list` command and verify the last `Person` in CampusConnect.

### Adding alternative contact to a person

1. Adding alternative contact to a `Person`:

   1. **Prerequisites:** List all `Persons` in CampusConnect using the `list` command.
   
   2. **Test case:** `addalt 1 tg/@johndoe_123 e2/johndoe@hotmail.com li/john-doe-b9a38128a b/31/10`<br>
       **Expected:** The first `Person` in the list is added with the following alternative contact information: telegram "@johndoe_123", secondary email "johndoe@hotmail.com", linkedin "john-doe-b9a38128a" and birthday "31/10". Success details shown in the status message. The aforementioned happens only if the fields are initially empty. Otherwise, error details will be shown in status message. 

   3. **Test case:** `addalt 1 tg/@johndoe_123 e2/`<br>
       **Expected:** The first `Person` in the list will not be added with the following alternative contact information: telegram "@johndoe_123". Error details shown in the status message.

   4. **Test case:** `addalt 1 tg/@johndoe_123 tg/@johnjohn`<br>
      **Expected:** The first `Person` in the list will not be added with the following alternative contact information: telegram "@johndoe_123" or "@johnjohn". Error details shown in the status message.

2. To see if the `Person` is added with alternative contact information, use the [find command](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#contact-search) to search for the `Person` and verify the details.

3. To know exactly what are the alternative details of a `Person` that can be added, see [this](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#add-alternative-information-to-contact-addalt).

### Editing a person

1. Editing a `Person`:

    1. **Prerequisites:** List all `Persons` in CampusConnect using the `list` command. 

    2. **Test case:** `edit 1 p/98765411 e/johndoe@gmail.com`<br>
       **Expected:** The first `Person` in the list will have phone edited to "98765411" and email edited to "johndoe@gmail.com". Success details shown in the status message. 
   
    3. **Test case:** `edit 1 tg/@johndoe e2/johndoe@gmail.com`<br>
       **Expected:** The first `Person` in the list will have telegram edited to "@johndoe" and secondary email edited to "johndoe@gmail.com" only if the fields were not initially empty; success details shown in the status message. Otherwise, error details will be shown in status message.

    4. **Test case:** `edit 1 tg/@johndoe_123 tg/@johnjohn`<br>
       **Expected:** The first `Person` in the list will not have telegram edited. Error details shown in status message.

2. To see if the `Person`'s details are edited, use the [find command](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#contact-search) to search for the `Person` and verify the details.

3. To know exactly what are the details of a `Person` that can be edited, see [this](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#edit-information-of-contact-edit).
   
### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Updating photo of a person

1. Updating photo of a  `Person`:

    1. **Prerequisites:** List all `Persons` in CampusConnect using the `list` command.

    2. **Test case:** `updatephoto 1 path/docs/images/johndoe.png`<br>
       **Expected:** The first `Person` in the list will have profile photo updated to the picture "johndoe.png". Success details shown in the status message.

    3. **Test case:** `updatephoto 1 path/C:/documents/test.docx`<br>
       **Expected:** The first `Person` in the list will not have profile photo updated. Error details shown in the status message.

2. To see if the `Person`'s profile photo is updated, use the [find command](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#contact-search) to search for the `Person` and verify the profile photo.

### Adding note to a person

1. Adding note to a `Person`:

    1. **Prerequisites:** List all `Persons` in CampusConnect using the `list` command.

    2. **Test case:** `addnote 1 This is a sample note for the person.`<br>
       **Expected:** The first `Person` in the list will have a new note stating "This is a sample note for the person.". Success details shown in the status message.

    3. **Test case:** `addnote 1 This person is very funny! 😀`<br>
       **Expected:** The first `Person` in the list will not have a new note stating "This person is very funny! 😀". Error details shown in the status message.

2. To see if the new note is added to `Person`, use the [viewnotes command](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#view-notes-of-contact-viewnotes) and verify the details of the notes.

### Paying money to a person

1. Paying money to a `Person`:

    1. **Prerequisites:** List all `Persons` in CampusConnect using the `list` command.

    2. **Test case:** `pay 1 10`<br>
       **Expected:** The first `Person` in the list will be paid $10. Success details shown in the status message.

    3. **Test case:** `pay 1 10.555`<br>
       **Expected:** The first `Person` in the list will not be paid $10.555. Error details shown in the status message.

   4.  **Test case:** `pay 1 50000`<br>
       **Expected:** The first `Person` in the list will not be paid $50000. Error details shown in the status message.

2. To see if the money is paid to `Person`, use the [find command](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#view-notes-of-contact-viewnotes) to search for the `Person` and verify the money paid.


### Owing money to a person

1. Owing money to a `Person`:

    1. **Prerequisites:** List all `Persons` in CampusConnect using the `list` command.

    2. **Test case:** `owe 1 10`<br>
       **Expected:** You owe the first `Person` in the list $10. Success details shown in the status message.

    3. **Test case:** `owe 1 10.555`<br>
       **Expected:** You will not owe the first `Person` in the list $10.555. Error details shown in the status message.

   4.  **Test case:** `owe 1 50000`<br>
       **Expected:** You will not owe the first `Person` in the list $50000. Error details shown in the status message.

2. To see if the money the `Person` owes is recorded, use the [find command](https://ay2324s1-cs2103t-t13-2.github.io/tp/UserGuide.html#view-notes-of-contact-viewnotes) to search for the `Person` and verify the money owed.


### Finding a person

1. Finding a `Person` while all persons are being shown:

    1. **Test case:** `find n/do`<br>
       **Expected:** CampusConnect displays a list of `Person` that has name that contains the substring `do`.

    2. **Test case:** `find t/friends`<br>
       **Expected:** CampusConnect displays a list of `Person` that has tag that is equal to the tag `friends`.

    3. **Test case:** `find a/John street, block 123, #01-01`<br>
       **Expected:** CampusConnect will not display any `Person`. Error details shown in the status message.
   
    4. **Test case:** `find a/"John street, block 123, #01-01"`<br>
       **Expected:** CampusConnect will display a list of `Person` with address `John street, block 123, #01-01`.

