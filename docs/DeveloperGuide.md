---
layout: page
title: Developer Guide
---

<style>
.diagram {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 10px;
}
</style>

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* [JavaFX](https://openjfx.io/) was used to develop a Graphical User Interface (GUI).
* [Jackson](https://github.com/FasterXML/jackson) was used for JSON local storage of data.
* [JUnit5](https://github.com/junit-team/junit5) was used for automated unit and integration testing.
* [TestFx](https://github.com/TestFX/TestFX) was used for automated JavaFX GUI testing.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

<!-- @@author awhb -->

### Architecture

<div class="diagram">
  <img src="images/ArchitectureDiagram.png" width="280" />
</div>

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

<div class="diagram">
  <img src="images/ArchitectureSequenceDiagram.png" width="580" />
</div>

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<div class="diagram">
  <img src="images/ComponentManagers.png" width="310" />
</div>

The sections below give more details of each component.

<!-- @@author -->

<!-- @@author xenosf -->

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T08-2/tp/blob/master/src/main/java/networkbook/ui/Ui.java)

<div class="diagram">
  <img src="images/UiClassDiagram.png" width="980" />
</div>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

These parts may use custom component classes such as `FieldLabel` and `FieldHyperlink` that
inherit from default JavaFX components. These subclasses can provide
reasonable defaults or part-specific behavior to simplify code.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T08-2/tp/blob/master/src/main/java/networkbook/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T08-2/tp/blob/master/src/main/resources/view/MainWindow.fxml).

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<!-- @@author -->

<!-- @@author Eola-Z -->

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T08-2/tp/blob/master/src/main/java/networkbook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<div class="diagram">
  <img src="images/LogicClassDiagram.png" width="570" />
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to a `NetworkBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePersonCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<div class="diagram">
  <img src="images/ParserClasses.png" width="670" />
</div>

How the parsing works:

* When called upon to parse a user command, the `NetworkBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `NetworkBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

Here is an overview of what the other classes in `Logic` do:

* `ArgumentMultiMap` and `ArgumentTokeniser` are used to map the parameters of 
the user's input into key-value pairs, where the keys are specified using `ArgumentTokeniser`
* `CliSyntax` is where command-specific keywords are stored. It is used as the arguments for `ArgumentTokeniser` to process the user input into: `{keyword : parameter}` pairs.
  * Example usage: The text `1 /name John Doe /phone 98765432` when
    mapped using `ArgumentTokeniser` with the keywords `/name`
    and `/phone` produces:
    * `{1}, {/name : John Doe}, {/phone : 98765432}`
    * Any text that appears before the first possible keyword is stored
      in its own entry.
  * `ArgumentMultiMap` can then perform specific operations, including
    but not limited to:
    * Retrieve all values/only a specific value from the set.
    * Check that a certain key only appears once, or exactly once.
* `ParserUtil` contains useful commands for parsing text such as removing
  leading/trailing whitespace from text, verifying that there are no 
  duplicate entries in the text, and so on.

#### Type Inference Within NetworkBookParser

The activity diagram below describes the workflow of `NetworkBookParser`
when determining which `Parser` to use:

<div class="diagram">
  <img src="images/AddressBookParser.png" width="1130" />
</div>

#### Type Inference Within FilterCommandParser

The sequence diagram illustrates the interactions within `FilterCommandParser` to infer
which type of `FilterCommand` to return, using `ArgumentMultiMap` to extract the field the user
wishes to filter, based on input.

<div class="diagram">
  <img src="images/FilterCommandFindParseCommand.png" width="1250"/>
</div>

The full implementation of `FilterCommandParser` can be found in the [Filter Command Implementation.](#filter-contact-list)


<!-- @@author -->

<!-- @@author nknguyenhc -->

### Model component

**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T08-2/tp/blob/master/src/main/java/networkbook/model/Model.java)

<div class="diagram">
  <img src="images/ModelClassDiagram.png" width="1320" />
</div>

The `Model` component,

* stores the networkbook data, which in turn is all `Person` objects (which are contained in a `UniqueList<Person>` object).
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* exposes the current list of displayed person as a `Observable<Person>` obtained from the `VersionedNetworkBook`.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

The `VersionedNetworkBook` component,

* stores the currently filtered `Person` objects (the result of a filter) as a separate _filtered_ list. This list is used as the intermediate list between the original `UniqueList<Person>` and the _sorted_ list.
* stores the sorted list `Person` objects (the result of a filter and a sort) as a separate _sorted_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a list of states that can be backtracked/forwarded to. This list is extensively used in undo and redo command.

The `UserPrefs` component,

* stores the GUI settings of window size and position.
* stores the path of the data file.

The `UniqueList<T>` class,

* is a generic class that ensures that items within the list conforms to the unique constraint. In other words, each pair of objects within the list must have a different identity. `UniqueList<T>` enforces `T` to implement `Identifiable<T>`, which has the method `isSame(T)` to check for identity against another object.
* The identity is determined by the class that `T` binds to.
  * For `Person`, the identity is the name. Two names are equal if there string values are equal.
  * For `Phone`, the identity is the literal string value of the phone.
  * For `Email`, the identity is the literal string value of the email.
  * For `Link`, the identity is the literal string value of the link.
  * For `Course`, the identity is the literal string value of the course name.
  If two courses are of the same name but of different start and end dates, they are considered having the same identity.
  * For `Specialisation`, the identity is the literal string value of the specialisation.
  * For `Tag`, the identity is the literal string value of the tag.
* `UniqueList<T>` does an identity check upon adding every object to the list. It throws an `AssertionError` if duplicates are found.
  * Any actor that wants to add an object to the list must ensure that an identity check has been done before the add method is called.
* `UniqueList<T>` supports supplying the object at the specified index to a consumer through the method `consumeItem(int index, ThrowingIoExceptionConsumer<T> consumer)`. The method takes the item at `index` and passes it into the `consumer`.
* `UniqueList<T>` supports supplying the object at the specified index to a consumer, at the same time applying a function on the same object to produce a value through the method `consumerAndComputeItem(int index, ThrowingIoExceptionConsumer<T> consumer, Function<T, U> function)`. The method works the same as above, and does an extra step of applying `function` on the object and return the computed value of type `U`.

<!-- @@author -->

<!-- @@author Singa-Pirate -->


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T08-2/tp/blob/master/src/main/java/networkbook/storage/Storage.java)

<div class="diagram">
  <img src="images/StorageClassDiagram.png" width="930" />
</div>

The `Storage` component,

* can save both NetworkBook data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `NetworkBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

<!-- @@author -->

### Common classes

Classes used by multiple components are in the `networkbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

<!-- @@author awhb -->
### Create new contact

The implementation of the create command follows the convention of a normal command,
where `CreateCommandParser` is responsible for parsing the user input string
into an executable command.

<div class="diagram">
  <img src="images/create/CreateDiagram.png" width="1800" />
</div>

`CreateCommandParser` first obtains the values corresponding to the prefixes
`/name`, `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority` and `/tag`.
`CreateCommandParser` ensures that:

* There is no preamble text between the `create` keyword and the prefixes.
* One and only one of the prefix `/name` is present.
* Each of `/grad` and `/priority` prefixes appears at most once.
* All values corresponding to the prefixes `/name`, `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority` and `/tag` are valid.

If any of the above constraints are violated, `CreateCommandParser` throws a `ParseException`.
Otherwise, it creates a new instance of `CreateCommand` that corresponds to the user input.

`CreateCommand` comprises of the person to be added, which is an instance of `Person`.

Upon execution, `CreateCommand` first queries the supplied model if it contains a person with an identical name.
If no such person exists, `CreateCommand` then calls on `model::addPerson` to add the person into the networkBook data.

We have considered the following alternative implementations:

* Implement `CreateCommandParser` to parse the arguments using regular expressions.
  This is not optimal for our use case as having a regex expression to parse the field values would be more complicated to scale and debug.

<!-- @@author -->

<!-- @@author Singa-Pirate -->

### Add details

The implementation of the add command follows the convention of a normal command, where `AddCommandParser` is responsible for parsing the user input string into an executable command.

<div class="diagram">
  <img src="images/add-remark/AddDiagram.png" width="1430" />
</div>

`AddCommandParser` first obtains the values corresponding to the prefixes
`/name`, `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority` and `/tag`.

It ensures that each of `/name`, `/grad` and `/priority` prefixes appears at most once. If not, it throws a `ParseException`.

Otherwise, it attempts to generate an `AddPersonDescriptor` with the details provided. It checks the following:

* `/name` is not provided, since all contacts already have a name, and `edit` command should be used to update a contact's name
* Apart from `/name`, at least one field to add information has been specified

If the details do not fulfil these requirements, it throws a `ParseException`. Otherwise, it produces an `AddCommand` with the index of contact and the descriptor.

Upon execution, the `AddCommand` will first obtain the `Person` at the specified index in the displayed contact list, and then attempts to add the information in the descriptor to the `Person`. In the process, it checks that:

* There is a corresponding person at the specified index
* If the descriptor contains `/grad` or `/priority`, the original person should not contain the corresponding field, as `edit` command should have been used

These incorrect usages will throw a `CommandException`. Otherwise, the `AddCommand` will proceed to replace the original person with the new person after adding information.

We have considered the following alternative implementation:

* Let `AddCommandParser` generate `EditPersonDescriptor`, and when executing `AddCommand`, treat the information in the edit descriptor as information to add.

  This implementation was not chosen because it creates unnecessary coupling between add and edit commands. Our final implementation of `EditPersonDescriptor` contains optional indices to specify the entry of a multi-valued field to edit, which is much different from `AddPersonDescriptor` and thus they are implemented separately.

<!-- @@author -->

<!-- @@author nknguyenhc -->

### Edit details

The implementation of the edit command follows the convention of a normal command,
where `EditCommandParser` is responsible for parsing the user input string
into an executable command.

<div class="diagram">
  <img src="images/edit/EditDiagram.png" width="1160" />
</div>

`EditCommandParser` first obtains the values corresponding to the prefixes 
`/name`, `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority`, `/tag` and `/index`.
`EditCommandParser` ensures that:

* One and only one of `/name`, `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority`, `/tag` is present.
* If `/name`, `/priority` or `/grad` is present, then `/index` is not present.
* If `/phone`, `/email`, `/link`, `/course`, `/spec` or `/tag` is present, then at most one prefix `/index` is present.

If any of the above constraints are violated, `EditCommandParser` throws a `ParseException`. 
Otherwise, it creates a new instance of `EditCommand` that corresponds to the user input.

`EditCommand` makes use of `EditPersonDescriptor`, which is an editable version of `Person` class.
Most importantly,

* `EditPersonDescriptor` constructor copies the details of the person.
* `EditPersonDescriptor` has setter methods to allow changing the details.
* `EditPersonDescriptor` has a `toPerson` method that returns a new instance of `Person` that matches the current details.

`EditCommand` comprises of the `index` of the person to edit, and `editAction` as an instance of `EditAction`. 
Each `EditAction` implements `edit(EditPersonDescriptor)`,
which mutates the input instance of `EditPersonDescriptor`.
`EditAction` is an interface that has implementing concrete classes corresponding to each type of action
(i.e. `EditPhoneAction` for editing phone, `EditEmailAction` for editing email, etc).

Upon execution, `EditCommand` first obtains the `Person` at the index `index` in the model.
`EditCommand` then creates a new instance of `EditPersonDescriptor` that matches the details of the `Person`.
`EditCommand` then calls on `editAction::edit` to mutate the created `EditPersonDescriptor`.

`EditCommand` then converts the current `EditPersonDescriptor` into a new `Person`.
`EditCommand` then asks the `model` to update the original `Person` with the edited `Person`.

We have considered the following alternative implementations:

* Implement `EditCommand` with only `EditPersonDescriptor` and without `EditAction`,
and `EditCommandParser` generates the instance of `EditPersonDescriptor` directly.
`EditCommandParser` then must know the details of the person editing
in order to generate the correct instance of `EditPersonDescriptor`.
This is not optimal for object-oriented programming,
as the parser should not need to know how the current model looks like.
* Use a different class for each type of edit command 
(i.e. editing phone with `EditPhoneCommand`, editing email with `EditEmailCommand`, etc).
This design has the advantage that the parser does not need to know how the current model looks like.
However, to keep `Command` classes consistent in design, 
we decide to only have one `EditCommand` class and practice inheritance with `EditAction`.
* Implement `EditCommand` such that `EditAction` edits the `Person` object directly.
This means that `Person` class must be mutable, which breaks the defensiveness of the current code and has the potential of introducing more bug.
Moreover, the `Person` class being immutable also accommodates for the `undo` and `redo` command,
in which the `VersionedNetworkBook` only creates a shallow copy of the current list of `Person` objects
and hence any mutation of the `Person` object might introduce bugs.

### Filter contact list

The implementation of the filter command follows the convention of a normal command, where `FilterCommandParser` is responsible for parsing the user input string into an executable `FilterCommand`.

<div class="diagram">
  <img src="images/filter/FilterCommandParser.png" width="1110" />
</div>

`FilterCommandParser` first obtains the values corresponding to the prefixes `/by` and `/with`, and ensures that each prefix is indicated once and only once.
If the value corresponding to `/by` is `course`, the value of the tag `/taken` is obtained from the value of the prefix `/with`.

A new filter command is then created with the `Predicate<Person>` that corresponds to the values of `/by` and `/with`, and `/taken` if any.

Upon execution, `FilterCommand` passes the instance of `Predicate<Person>` to the model through the method `model::updateDisplayedPersonList`. The model then uses the predicate internally to update the displayed list of contacts.

The details of how the parser infers which type of `FilterCommand` is to be returned can be found in [`ref`](#type-inference-within-filtercommandparser)

<!-- @@author -->

<!-- @@author Singa-Pirate -->

### Delete contact

<div markdown="span" class="alert alert-info">:information_source: **Note:** The implementation of the delete command varies a little from other commands, where `DeleteCommandParser` parses user input string into either a `DeletePersonCommand`, or a `DeleteFieldCommand`.
</div>

The command responsible for removing an entire contact is `DeletePersonCommand`. Below illustrates the process of parsing and executing a `DeletePersonCommand`.

<div class="diagram">
  <img src="images/delete/DeletePersonDiagram.png" width="1110" />
</div>

`NetworkBookParser` will instantiate a `DeleteCommandParser` when it detects the `delete` preamble. The `DeleteCommandParser` then checks if any field prefix is present in the command string (including `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority` and `/tag`).

If none of the field prefix is present, it is expected to be a `DeletePersonCommand`. The parser then checks if the `/index` prefix has been specified by mistake (as it is used to specify which entry of a multi-valued field to delete). If yes, it throws a `ParseException`. Otherwise, it proceeds to generate a `DeletePersonCommand` with the specified index of contact.

Upon execution, the `DeletePersonCommand` will first obtain the person at the corresponding index in the displayed contact list. It throws a `CommandException` if there's no person at the specified index. Else, it removes all information about the person from the model.

### Delete details

The command responsible for removing some details of a contact is `DeleteFieldCommand`. This command is generated by `DeleteCommandParser` if a field prefix is present in the `delete` command string (including `/phone`, `/email`, `/link`, `/grad`, `/course`, `/spec`, `/priority` and `/tag`).

The process of parsing the field to delete and execution is the same as `edit` command [above](#edit-details). The sequence diagram is the same after replacing original class names with `DeleteCommandParser`, `DeleteAction`, `DeleteFieldCommand` and`DeletePersonDescriptor`. Some noteworthy differences are listed below:

* `DeleteCommandParser` checks if the `/name` prefix is specified. If yes, it throws a `ParseException` as the name of a contact cannot be deleted.
* The public setter methods in `DeletePersonDescriptor` simply remove the corresponding field entry from the descriptor.

We have considered the following alternative implementation:

* Implement two different command preambles for `DeletePersonCommand` and `DeleteFieldCommand`, such as `delete` and `remove`, or `deletePerson` and `deleteField`.

  The intention of this alternative is to differentiate the two commands better, so that users would not be confused about their usages. After discussion, it has been noticed that the first suggestion (`delete` and `remove`) does not provide more clarity, and the second suggestion creates unnecessary trouble for advanced users. The current way to differentiate these commands (by the presence or absence of field prefix) is sufficiently intuitive for new users, and can be easily remembered and applied by advanced users.

<!-- @@author -->

<!-- @@author nknguyenhc -->

### Open link/email

The implementation of the opening link/email command follows the convention of normal command, where `OpenEmailCommandParser`/`OpenLinkCommandParser` is responsible for parsing the user input string into an executable command. Below illustrates the process for open link command. The process of opening email is similar, where the reader can simply replace `link` with `email` to get the process for opening email.

<div class="diagram">
  <img src="images/open/OpenDiagram.png" width="1020" />
</div>

`OpenLinkCommandParser` first obtains the values corresponding to the preamble and the prefix `/index`, and return an object of class `OpenLinkCommand`.

* If there are multiple `/index` prefixes, `OpenLinkCommandParser` throws a `ParseException`.
* If there is no `/index` prefix, the link index takes the default value of `1`.

`OpenLinkCommand` then executes on the `Model` to open the link at `personIndex` (index of contact) and `linkIndex` (index of contact). The `Model` calls on the `NetworkBook`, which then calls on the `Person` at the correct index to open the link at `linkIndex`.

The `Person` opens the link by first detects which OS the application is running on.

* On Windows, the `Person` executes `Desktop::browse(URI)`, where [`Desktop`](https://docs.oracle.com/javase/8/docs/api/java/awt/Desktop.html) and [`URI`](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/net/URI.html) are java classes from default packages.
* On Mac OS, the `Person` executes the `open` command in the terminal through the [`Runtime`](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/Runtime.html) class, which is a built-in class in java language. The `open` command in the terminal opens the computer's default browser if the `URI` supplied is correctly formatted to be a web link.
* On Ubuntu, the `Person` executes the `xdg-open` command in the terminal through the [`Runtime`](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/Runtime.html) class.

<!-- @@author -->

<!-- @@author awhb -->
### Undo/redo

The undo/redo mechanism is facilitated by `VersionedNetworkBook`. It extends `NetworkBook` with an undo/redo history of its state (encompassing list of all contacts and displayed list of contacts), stored internally as an `networkBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedNetworkBook::commit` — Saves the current NetworkBook state in its history.
* `VersionedNetworkBook::undo` — Restores the previous NetworkBook state from its history.
* `VersionedNetworkBook::redo` — Restores a previously undone NetworkBook state from its history.

These operations are called in functions of the `Model` interface:

* `VersionedNetworkBook::undo` is called in `Model::undoNetworkBook`.
* `VersionedNetworkBook::redo` is called in `Model::redoNetworkBook`.
* `VersionedNetworkBook::commit` is called in `Model::setPerson`, `Model::addPerson`, `Model::deletePerson` and `Model::updateDisplayedPersonList`.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedNetworkBook` will be initialized with the initial NetworkBook state, and the `currentStatePointer` pointing to that single state.

<div class="diagram">
  <img src="images/UndoRedoState0.png" width="480" />
</div>

Step 2. The user executes `delete 5` command to delete the 5th person displayed in NetworkBook. The `delete` command calls `Model::deletePerson` which in turn calls `VersionedNetworkBook::commit`, causing the modified state of the NetworkBook after the `delete 5` command executes to be saved in the `networkBookStateList`, and the `currentStatePointer` is shifted to the newly inserted NetworkBook state.

<div class="diagram">
  <img src="images/UndoRedoState1.png" width="480" />
</div>

Step 3. The user executes `create /name David …​` to add a new person. The `create` command also calls `Model::addPerson` which also in turn calls `VersionedNetworkBook::commit`, causing another modified NetworkBook state to be saved into the `networkBookStateList`.

<div class="diagram">
   <img src="images/UndoRedoState2.png" width="480" />
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `VersionedNetworkBook::commit`, so the NetworkBook state will not be saved into the `networkBookStateList`.


</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model::undoNetworkBook`, which will shift the `currentStatePointer` once to the left, pointing it to the previous NetworkBook state, and restores the NetworkBook to that state.

<div class="diagram">
  <img src="images/UndoRedoState3.png" width="480" />
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial state of NetworkBook when the user began the current session, then there are no previous NetworkBook states to restore. The `undo` command uses `Model::canUndoNetworkBook` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.


</div>

The following sequence diagram shows how the undo operation works:

<div class="diagram">
  <img src="images/UndoSequenceDiagram.png" width="1010" />
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model::redoNetworkBook`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the NetworkBook to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `networkBookStateList.size() - 1`, pointing to the latest NetworkBook state, then there are no undone NetworkBook states to restore. The `redo` command uses `Model::canRedoNetworkBook` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.


</div>

Step 5. The user then decides to execute the command `open 1 /index 1`. Commands that do not modify NetworkBook's list of all contacts or displayed contacts, such as `open 1 /index 1`, will usually not call `Model::undoNetworkBook`, `Model::redoNetworkBook`, or any `Model` commands that call `VersionedNetworkBook::commit`. Thus, the `networkBookStateList` remains unchanged.

<div class="diagram">
  <img src="images/UndoRedoState4.png" width="480" />
</div>

Step 6. The user executes `clear`, which calls `Model::setNetworkBook` which in turn calls `VersionedNetworkBook::commit`. Since the `currentStatePointer` is not pointing at the end of the `networkBookStateList`, all NetworkBook states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<div class="diagram">
  <img src="images/UndoRedoState5.png" width="550" />
</div>

The following activity diagram summarizes what happens when a user executes a new command:

<div class="diagram">
  <img src="images/CommitActivityDiagram.png" width="320" />
</div>

**Some design considerations:**

* **Alternative 1 (current choice):** Saves the entire NetworkBook state.

  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.

  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: Additional implementation of each individual command requires more time and effort spent on achieving undo/redo behaviour for said command.

### Sorting displayed contacts

The `NetworkBook` class wraps around the data displayed to the user.
The sorting feature builds on the filter feature present in `NetworkBook`, which uses JavaFX's `FilteredList`, an implementation of the `ObservableList` interface.

The sort feature makes use of JavaFX's `SortedList`, another implementation of the `ObservableList` interface.
`SortedList` takes a predicate which it then uses to sort the list.

To implement the sort feature, a new `SortedList` was added to `NetworkBook`, wrapping around the existing `FilteredList`.
This sorted list is the list that is displayed to the user.

The sort command updates the predicate of the `SortedList` to a `PersonSortComparator`.
`PersonSortComparator` extends `Comparator<Person>`, adding in a few extra methods specific to sorting persons:

* `parseSortField()` parses a given string into a value of the `SortField` enumeration. This value is then used later to determine the predicate implementation.
* `parseSortOrder()` parses a given string into a value of the `SortOrder` enumeration. This value is then used later to determine the predicate implementation.
* `generateXXComparator()` methods. These methods return comparators which compare based on XX field of Person in either ascending or descending order.
* `generateComparator()` takes in a `SortField` and `SortOrder` and calls the relevant `generateXXComparator()` based on the given sort order and field.

Given below is an example usage scenario and how the sorting mechanism behaves at each step.

**Step 1.** The user launches the app. The rendered list is sorted by name in ascending order by default.

Current displayed contacts:

| Sorting         | Filter |
|-----------------|--------|
| name, ascending | none   |

**Step 2.** The user executes `find al` command to filter contacts by name. This updates the predicate of the `FilteredList` to only show contacts with names matching "al".
The `SortedList` predicate remains unchanged.

Current displayed contacts:

| Sorting         | Filter               |
|-----------------|----------------------|
| name, ascending | name containing "al" |

**Step 3.** The user enters `sort /by name /order desc` to sort the filtered list by name in descending order.
The NetworkBook parser parses this into a sort command using a sort command parser.
The sort command parser constructs a new `PersonSortComparator`, which uses the static method `generateComparator()` to generate the appropriate comparator.

<div class="diagram">
  <img src="images/SortingParsingSequenceDiagram.png" width="1000" />
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `SortCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>
**Step 4.** The sort command is executed. It calls `updateDisplayedPersonList()` of the model, updating the predicate of the `SortedList`.
This newly sorted list is then rendered in the main UI upon updating of the model.

Current displayed contacts:

| Sorting          | Filter               |
|------------------|----------------------|
| name, descending | name containing "al" |

**Step 5.** A `SortCommandResult` is also returned by the command, which is then passed to `MainWindow`.
The main window then updates the sorting status displayed in the status bar.

**Step 6.** The user uses `filter` to filter by tag "friends". The list of contacts is filtered and the sorting remains the same.

Current displayed contacts:

| Sorting          | Filter                  |
|------------------|-------------------------|
| name, descending | tag containing "friend" |

The following sequence diagram shows how the sort operation works:

<div class="diagram">
  <img src="images/SortingSequenceDiagram.png" width="1330" />
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `SortCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

### Sort and filter status bar display

The existing implementation has a status bar displaying the save file path.
To display the current sort and filter status, another label was added to the status bar.

When a command that changes sorting (e.g. `sort`) is run, it returns a `SortCommandResult` which is used to update the status bar.
Likewise, when a command that changes filtering (e.g. `filter`, `find`, `list`) is run, it returns a `FilterCommandResult` which is used to update the status bar.

The following activity diagram summarizes what happens when a command is executed:

<div class="diagram">
  <img src="images/StatusBarActivityDiagram.png" width="520" />
</div>

**Some design considerations:**

* **Alternative 1 (current choice):** Return subclass of `CommandResult` upon execution.
    * After command execution, the command result is returned to `MainWindow`, which has access to the status bar element.
    * By using polymorphism, additional information can be added to the command results of relevant commands.
    * As `MainWindow` already receives the command results in order to update the text result box, the simplest way of implementing the status bar update would be to use the command results to update the status bar as well.

* **Alternative 2:** Directly read status of model.
    * The sorting and filtering is controlled by a `NetworkBook` instance.
    * The status bar could read the sorting and filtering predicate/comparator directly.
    * However, this increases coupling between model and UI which is undesirable. Hence, this alternative was not chosen.

* **Alternative 3:** Implement observer pattern to allow UI updates on model status change.
    * The sorting and filtering is controlled by a `NetworkBook` instance.
    * The observer design pattern could be implemented to update the status bar when the filtering predicate/comparator updates, without increasing coupling.
    * However, this introduces some complexity as there needs to be new observer and observable interfaces, as well as their corresponding method implementations.
    * Since commands are currently the only way to change the sort or filter status, alternative 1 is a simpler way to implement the feature.

<!-- @@author -->

<!-- @@author awhb -->

### Find contacts by name

The implementation of the command to find contacts by their names follows the convention of a normal command, where `FindCommandParser` class is responsible for parsing the user input string into an executable command. 

<div class="diagram">
  <img src="images/find/FindDiagram.png" width="1690" />
</div>

`FindCommandParser` first obtains the values input by the user after the keyword `find`.
`FindCommandParser` ensures that there is at least one key term value after trimming the user input for space characters.
If the above constraint is violated, `FindCommandParser` throws a `ParseException`.
Otherwise, it creates a new instance of `FindCommand` that corresponds to the user input.

`FindCommand` stores an instance of `NameContainsKeyTermsPredicate`, which represents a predicate that checks if a person's name contains at least one of the space-separated key terms the user has input. (Note: contains is defined here as a case-insensitive, antisymmetric relation; that is, A may contain B even if B does not contain A).

Examples:
* "Ben" is contained in "Ben Leong".
* "al" is contained in both "Alex Yeoh" and "Jiale".
* "BenL" is not contained in "Ben Leong" because the 'space' character between the words in the name invalidates the contains relationship.

Upon execution, `FindCommand` passes the instance of `NameContainsKeyTermsPredicate` to the model through the method `model::updateDisplayedPersonList`. The model then uses the predicate internally to update the displayed list of contacts. 

We have considered the following alternative implementations:
* Do not implement a separate command to find contact by name i.e. use the `filter` command where the `name` field is passed to the `/by` prefix instead as the way to find contacts by their name.
  * This is not optimal for our use case as the `find` command is a command we expect users to use often to search for contacts via their name. Hence simplifying the command call usage would make the experience more efficient for users. 

<!-- @@author -->

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

* is an NUS computing student or computing professional
* has a need to manage a significant number of contacts
* has a need to manage a lot of details for each contact
* is looking into networking with other computing students and professionals
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps


**Value proposition**: As computing students and professionals network with alumni to expand their career prospects, our app keeps a list of contacts of people that each user networks with.

* Offline, with a static online page that contains user manual and download link
* Sort contacts by name, priority, graduation year
* Filter contacts by details, e.g. courses taken, graduation year, specialisations, tags.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                 | I want to …​                                                                               | So that I can…​                                                          |
|---------|-------------------------|--------------------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| `* * *` | user                | see usage instructions in the app                                                          | refer to instructions when I forget how to use the app                |
| `* * *` | user                    | create a new contact                                                                       | keep a record of individuals in my network                              |
| `* * *` | user                    | add more details about an existing contact                                                 | store information about my contacts for future reference                |
| `* * *` | user                    | edit or delete details of a contact                                                        | replace outdated details with more accurate information                 |
| `* * *` | user                    | delete a contact                                                                           | remove individuals I no longer keep contact with |
| `* *`   | user                    | find a contact by name                                                                     | locate details of contacts without having to go through the entire list |
| `* *`   | user with many contacts | sort contacts by their details                                                             | locate contacts with special characteristics that I am looking for      |
| `*`     | user with many contacts | filter contacts based on their details                                                     | locate contacts who fulfil certain conditions that I am looking for     |
| `* *`   | new user                | use commonly-available keyboard shortcuts (e.g. ctrl-c for copy, ctrl-v for paste)         | provide input more efficiently with shortcuts I am accustomed to |
| `* *`   | user                    | use simple and easy-to-press shortcuts                                                     | remember and execute the shortcuts more easily |
| `* *`   | user                    | open my email app by clicking on my contact's email                                        | send emails to my contacts more efficiently |
| `* *`   | user                    | open the relevant website by clicking on my contact's social link                          | conveniently access their social links when needed |
| `* *`   | user                    | undo and redo my commands                                                                  | revert my changes when I make a mistake |
| `* *`   | new user                | have a quick-start guide                                                                   | start using the basic functionality of the app as soon as possible |
| `* *`   | user                    | visit an online page containing the complete user manual                                   | refer to the full set of instructions when needed            |
| `*`     | user                    | navigate to the relevant section of the online manual directly from the catalogue | quickly find instructions on the feature I want to use       |
| `*`     | user                    | export my contacts in the form of readable text                                            | easily share my contacts with others                                    |
| `*`     | user with many devices  | import data from my exported contacts                                                      | sync my contact details across different devices |

### Use cases

<!-- @@author awhb -->
**Use case: Create a new contact**

**MSS**

1. User requests to create a new contact with a name.

2. NetworkBook creates a new contact with the name.

   Use case ends.

**Extensions**

* 1a. User does not include the contact's name in the request.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. User includes more details about the contact in the request.

  * 1ba. All the details provided are in the correct format.

    * 1ba1. NetworkBook creates a contact with all included details.

      Use case ends.

  * 1bb. Some of the details provided are not correctly formatted.

    * 1bb1. NetworkBook shows an error message.

      Use case ends.
  
  * 1bc. Some of the details are provided twice.

    * 1bc1. NetworkBook shows an error message.

      Use case ends.

* 1c. The name is not unique.

  * 1c1. NetworkBook shows an error message.

    Use case ends.

<!-- @@author -->

<!-- @@author Singa-Pirate -->

**Use case: Add phone numbers to contact**

This use case is also applicable to adding **email, link, course, specialisation, tag** to a contact. For each contact, each of these fields is recorded by a list, and new entries for a field will be appended to the field's list.

**MSS**

1. User requests to add phone numbers to a specific contact in the displayed list.

2. NetworkBook adds the new phone numbers to the contact's list of phone numbers.

3. NetworkBook updates the displayed contact card with the new phone numbers.

   Use case ends.

**Extensions**

* 1a. The given index is invalid.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. User does not input any field.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. One of the given phone numbers is in an invalid format.

  * 1c1. NetworkBook shows an error message.

    Use case ends.

* 1d. One of the given phone numbers is already present in the contact's list of phone numbers.

  * 1d1. NetworkBook ignores the present phone number, and adds the other phone numbers to the contact's list of phone numbers.

    Use case resumes at step 3.

* 1e. User provides the same phone number more than once.

  * 1e1. NetworkBook shows an error message.

    Use case ends.

**Use case: Add graduation year to a contact**

This use case is also applicable to adding **priority** to a contact. For each contact, each of these fields is a single value instead of a list. They cannot be added if the value is already present.

**MSS**

1. User requests to add graduation year to a specific contact in the list.

2. NetworkBook adds the graduation year to the contact.

3. NetworkBook informs user of the contact's new graduation year.

   Use case ends.

**Extensions**

* 1a. The given index is invalid.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. User does not input any field.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. The given graduation year is in an invalid format.

  * 1c1. NetworkBook shows an error message.

    Use case ends.

* 1d. The contact already has a graduation year.

  * 1d1. NetworkBook shows an error message.

    Use case ends.

<!-- @@author -->

<!-- @@author nknguyenhc -->

**Use case: Edit the name of a contact**

This use case is also applicable to editing **graduation, priority** of a contact, except for extension 1e. which is specific to the **name** field.

**MSS**

1. User requests to edit the name of a specific contact in the list.

2. NetworkBook updates the contact.

3. NetworkBook updates the displayed contact card of the person.

    Use case ends.

**Extensions**

* 1a. The given index is invalid.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. The name provided is not correctly formatted.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. The user does not provide any field to edit.

  * 1c1. NetworkBook shows an error message.

    Use case ends.
  
* 1d. The user provides two name fields.

  * 1d1. NetworkBook shows an error message.

    Use case ends.

* 1e. The name provided is taken by another contact of the user

  * 1e1. NetworkBook shows an error message.

    Use case ends.

**Use case: Edit a phone number of a contact**

This use case is also applicable to editing **email, link, course, specialisation, tag** of a contact.

**MSS**

1. User requests to edit a phone of a specific contact in the list at the specific index in the contact's phone number list.

2. NetworkBook updates the contact.

3. NetworkBook updates the displayed contact card of the person.

    Use case ends.

**Extensions**

* 1a. The given contact index is invalid.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. The phone number provided is not correctly formatted.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. The user does not provide any field to edit.

  * 1c1. NetworkBook shows an error message.

    Use case ends.
  
* 1d. The user provides two phone fields.

  * 1d1. NetworkBook shows an error message.

    Use case ends.

* 1e. The user provides an invalid phone number index.

  * 1e1. NetworkBook shows an error message.

    Use case ends.

* 1f. The user provides the phone number index twice.

  * 1f1. NetworkBook shows an error message.

    Use case ends.

* 1g. The user does not provide the phone number index.

  * 1g1. The phone number index takes the default value of 1.

    * 1g1a. The phone number index of 1 is invalid.

      Use case resumes at step 1e1.
    
    * 1g1b. The phone number index of 1 is valid.

      Use case resumes at step 2.

<!-- @@author -->

<!-- @@author Singa-Pirate -->

**Use case: Delete a contact**

**MSS**

1. User requests to delete a specific contact in the list.

2. NetworkBook deletes the contact.

3. NetworkBook updates the displayed list of contact.

    Use case ends.

**Extensions**

* 1a. The given index is invalid.

    * 1a1. NetworkBook shows an error message that the index is invalid.

      Use case ends.

**Use case: Delete a single-valued field of a contact**

This use case is applicable to deleting **graduation, priority** of a contact.

**MSS**

1. User specifies index of contact and a single-valued field to delete.

2. NetworkBook updates the contact by deleting the field.

3. NetworkBook updates the displayed contact card of the person.

   Use case ends.

**Extensions**

* 1a. The given index of contact is invalid.

  * 1a1. NetworkBook shows an error message.

    Use case ends.


* 1b. User provides multiple fields to delete.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. User provides an index field after the single-valued field to delete.

  * 1c1. NetworkBook shows an error message.

    Use case ends.

* 1d. The single-valued field of the contact is empty.

  * 1d1. NetworkBook shows an error message.

    Use case ends.

**Use case: Delete a multi-valued field of a contact**

This use case is applicable to deleting a **phone, email, link, course, specialisation, tag** of a contact.

**MSS**

1. User specifies index of contact, a multi-valued field, and the index of entry to delete.

2. NetworkBook updates the contact by deleting the entry from the field's list.

2. NetworkBook updates the displayed contact card of the person.

   Use case ends.

**Extensions**

* 1a. The given index of contact is invalid.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. User provides multiple fields to delete.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. The given index of entry is invalid.

  * 1c1. NetworkBook shows an error message.

    Use case ends.

* 1d. User provides multiple indexes of entry to delete.

  * 1d1. NetworkBook shows an error message.

    Use case ends.

* 1e. User does not provide an index of entry.

  * 1e1. The index of entry is default to 1.

    * 1e1a. The contact's field does not have an entry at index 1.

      Use case resumes at step 1c1.

    * 1e1b. The contact's field has an entry at index 1.

      Use case resumes at step 2.

<!-- @@author -->

<!-- @@author Eola-Z -->

**Use case: Filter contacts**

This user story applies to filtering contacts by **course, specialisation, graduation year, tag**.

**MSS**

1. User requests to filter by a field with a specified value.

2. NetworkBook displays the contacts with the field that matches the specified value.

    Use case ends.

**Extensions**

* 1a. Field is not specified or value is not specified.

  * 1a1. NetworkBook shows an error message.

    Use case ends.
  
* 1b. Field specified is invalid.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. Value specified is invalid.

  * 1c1. NetworkBook shows an error message.

    Use case ends.

<!-- @@author -->

<!-- @@author xenosf -->

**Use case: Sort contacts**

**MSS**

1.  User chooses to sort based on a field (e.g. name, graduation year, priority) and an order (ascending or descending).

2.  NetworkBook shows list of user’s contacts, sorted by the specified field and in the specified order.

    Use case ends.

**Extensions**

* 1a. User has no contacts to sort.

    * 1a1. NetworkBook shows an empty contact list.

      Use case ends.

* 1b. User does not specify a field.

    * 1b1. NetworkBook shows an error message.

      Use case ends.

* 1c. The specified field is invalid.

    * 1c1. NetworkBook shows an error message.

      Use case ends.

* 1d. User does not specify an order.

    * 1d1. Networkbook shows list of user's contacts, sorted by the specified field and ascending order by default.

        Use case ends.

* 1e. The specified sorting order is invalid.

    * 1e1. NetworkBook shows an error message.

      Use case ends.

* 1f. Certain contacts do not have any data in the specified field (e.g. no email address stored)

    * 1f1. NetworkBook shows sorted list of user's contacts who have data in the specified field. Any contacts without that field specified are put at the end of the list.

      Use case ends.

**Use case: Search contacts by name**

**MSS**

1.  User specifies the text they would like to search.

2.  NetworkBook shows list of user’s contacts with names containing the searched text.

    Use case ends.

**Extensions**

* 1a. The search text is not specified.

    * 1a1. NetworkBook shows an error message.

      Use case ends.

<!-- @@author -->

<!-- @@author awhb -->


**Use case: Open email app from NetworkBook**

**MSS**

1. User requests to email a specific contact in the list to the specific email address of the contact.

2.  NetworkBook loads the default email app of the user.

3.  NetworkBook pre-fills the contact's email in the recipient field.

    Use case ends.

**Extensions**

* 1a. The contact index is invalid.
  
  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. The email index is invalid.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. The email index is not provided.

  * 1c1. NetworkBook takes the default value of email index of 1.

    * 1c1a. The email index of 1 is invalid.

      Use case resumes at step 1b1.
    
    * 1c1b. The email index of 1 is valid.

      Use case resumes at step 2.

* 2a. The user has not logged in to his default email app.

  * 2a1. User will be taken to the sign-in page of his default email app.

    Use case ends.

**Use case: Open link from NetworkBook**

**MSS**

1. User requests to open a link of a specific contact.

2. NetworkBook loads the default browser app of the user with the link opened.

    Use case ends.

**Extensions**

* 1a. The contact index is invalid.
  
  * 1a1. NetworkBook shows an error message.

    Use case ends.

* 1b. The link index is invalid.

  * 1b1. NetworkBook shows an error message.

    Use case ends.

* 1c. The link index is not provided.

  * 1c1. NetworkBook takes the default value of link index of 1.

    * 1c1a. The link index of 1 is invalid.

      Use case resumes at step 1b1.
    
    * 1c1b. The link index of 1 is valid.

      Use case resumes at step 2.

* 2a. The domain of the link is not a registered domain.

    * 2a1. The user will see the error page displayed by the browser used to load the page link.

      Use case ends.

* 2b. The link is valid but the page fails to load.

    * 2b1. The user will see the error page displayed by the browser used to load the page link.

      Use case ends.

**Use case: Undo**

**MSS**

1. User requests to undo the previous command.

2. NetworkBook reverts back to the previous state.

    Use case ends.

**Extensions**

* 1a. User has not keyed in any command previously.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

**Use case: Redo**

**MSS**

1. User requests to redo the latest undo command.

2. NetworkBook reapplies the changes recalled by the undo command.

    Use case ends.

**Extensions**

* 1a. There are no more changes to redo.

  * 1a1. NetworkBook shows an error message.

    Use case ends.

**Use case: Access user manual on online page**

**MSS**

1.  User requests to visit the online page.
2. The online page renders.
3. User selects link to visit the user manual.
4. The user manual page renders.

   Use case ends.

**Extensions**

* 1a. The online page fails to load.

    * 1a1. The user will see the error page displayed by the browser used to load the page link.

      Use case ends.
  
* 4a. The user manual page fails to load.

    * 4a1. The user will see the error page displayed by the browser used to load the page link.

      Use case ends.

**Use case: Navigate from catalogue to relevant section of online manual**

**Preconditions:** User is on user manual page.

**MSS**

1. User chooses a manual section title within the catalogue.
2. Browser navigates to display relevant section of online manual. 

    Use case ends.

**Use case: Export contact in readable text**

**MSS**

1.  User requests to export a specific contact in the list.

2.  NetworkBook exports a text file storing user details in a readable format.

    Use case ends.

**Extensions**

* 1a. The contact index is invalid.

  * 1a1. NetworkBook shows an error message.

  Use case ends.

<!-- @@author -->

<!-- @@author Singa-Pirate -->

**Use case: Import data from exported contacts**

**MSS**

1. User requests to import contacts data.

2. NetworkBook loads the user's file explorer.

3. User selects a file containing exported contacts.

4. NetworkBook creates new contacts with details specified in the export file.

5. NetworkBook saves the path to the data file.

   Use case ends.

**Extensions**

* 2a. User exits the file explorer without selecting any file.

  * 2a1. NetworkBook shows an error message.

    Use case ends.

* 3a. The file chosen is not in the correct format.

  * 3a1. NetworkBook shows an error message.

    Use case ends.

* 3b. NetworkBook does not have permission to read the file.

  * 3b1. NetworkBook shows an error message.

    Use case ends.

* 3c. The file contains contacts with same names as some existing contacts.

  * 3c1. NetworkBook skips these contacts and only creates new contacts whose names are not present yet.

  * 3c2. NetworkBook shows the skipped contacts.

    Use case ends.

<!-- @@author -->


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1. Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
1. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. A new user should be able to familiarise him/herself with most of the basic features of the app upon finishing going through the quick-start guide.
1. A user should be able to use commonly available and easy-to-remember keyboard shorcuts
   * Common shortcuts to edit text, including Ctrl+C to copy, Ctrl+V to paste, Ctrl+A to select all, Ctrl+Z to undo text change, Ctrl+Y to redo text change, etc.
   * Ctrl+F: find a contact
   * Ctrl+N: create a new contact
   * Ctrl+G: edit a contact
   * Ctrl+Z: undo last command (when not editing text)
   * Ctrl+Y: redo last command undone (when not editing text)
   * Ctrl+W: exit the app
   * Ctrl+S: manually save data
   * Up/down arrow keys: navigate command history
1. A new user should be able to understand the meaning of a command just by looking at the keywords used in the command.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **Command**: a string keyed in by the user in the GUI text input that signifies an action to be done by the app.
* **Contact**: a contact of the user whose information is stored in the app, which includes name, phone numbers, emails, links, graduation year, courses taken, specialisations, priority level and tags of/associated with the person.
* **Field**: an attribute of a contact that describes information about the contact. Possible fields of a contact are elaborated in the **contact** term above.
* **Single-valued field**: a field that cannot hold many values, so that each contact can only have one value. These fields include name, graduation year and priority level.
* **Multi-valued field**: a field that can possibly hold many values, so that each contact has a list of values. These fields include phone numbers, emails, links, courses, specialisations and tags.
* **Course taken**: a module that a person has taken in university or outside (for e.g. CS2103T module in NUS).
* **Specialisation**: the specialisation a person can take in their computing degree in NUS (e.g. Software Engineering, Artificial Intelligence).
* **Graduation year**: the year and semester that a person will graduate / has graduated from NUS (e.g. AY2526-S2, meaning the second semester of the academic year spanning from 2025 to 2026).
* **Link**: a web link which directs to a contact's profile page on a social platform (e.g. LinkedIn, GitHub).
* **Tag**: an annotation to a person. This can be anything memorable of the person.
* **Priority**: the priority level of a contact set by the user. Its value can be either high, medium or low.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned enhancements**

Given below are the planned enhancements. The _current behaviour_ specifies how the application behaves as of `v1.4`, and the _enhanced behaviour_ specifies how the application should behave in a future milestone.

### Logic

* Better error handling on creating a duplicate contact (for `create` command).
  * Current behaviour: When the user attempts to create a new contact of the same name as an existing contact, the app displays an error message and prevents the user from creating that new contact.
  * Enhanced behaviour: When the user attempts to create a new contact of the same name as an existing contact, the app creates a new contact, but gives a warning message that another contact with the same name already exists. A sample warning message can be: `Noted, created new contact: Nguyen. Another contact with the name "Nguyen" already exists. If you created the new contact by accident, type "undo" to undo creating the contact.` This also means that person's uniqueness constraint should be enforced through a hidden ID and not the name.
  * Justification: This allows user to add two persons of exactly the same name to the contact list. The error message warns the user of a possible mistake.

* Handling duplicates of phone, email and link across different contacts.
  * Current behaviour: When the user attempts to add the same phone, email or link as another contact's phone, email or link respectively, the software adds the detail without any warning.
  * Enhanced behaviour: When the user attempts to add the same phone, email or link as nother contact's phone, email or link respectively, the software should still add the detail, but with a warning message. For example, suppose that the contact at index 2 has a phone number of `12345678`, the command `add 1 /phone 12345678` gives a warning message: `Added phone "12345678" to the contact at index 1. Phone number "12345678" already exists in the contact at index 2. If you added the phone number by accident, type "undo" to undo adding the phone number.`
  * Justification: Generally, phone numbers, emails and links are not shared by multiple contacts, hence adding the warning message warns the user of a possible mistake. However, the software should not prevent the user totally from doing so to accommodate the rare case that a phone number, email or link is shared by multiple contacts.

* Better command format for filtering course.
  * Current behaviour: The command `filter /by course /with CS2103T /taken true` means filtering in all contacts that have course `CS2103T` and are taking the course (i.e. current date is between the start and end date), while `filter /by course /with CS2103T /taken false` means filtering in all contacts that have course `CS2103T` regardless of whether the contact is taking the course. The latter is equivalent to `filter /by course /with CS2103T`, without the `/taken` tag.
  * Enhanced behaviour: The `/taken` prefix can be changed to `/taking`, to clearly suggest filtering in contacts that are taking the course. Furthermore, the presence of the prefix `/taking` should indicate filtering in contacts taking the course without the `true` value following the tag, while omitting the prefix means no filtering based on whether the contacts are taking the course. This means that `filter /by course /with CS2103T /taking` means filtering in all contacts that are taking `CS2103T`, while `filter /by course /with CS2103T` (without the `/taking` prefix) means filtering in all contacts that have course `CS2103T` regardless of whether they are taking the course.

* More user-friendly info message.
  * Current behaviour: Upon a successful command dealing with one contact, the info message shows all the information of the contact, which is rather verbose. For example, with command `create /name Nguyen /phone 12345678 /phone 87654321`, the info message is: `Noted, created new contact: Nguyen; Phones: [12345678, 87654321]; Emails: []; Links: []; Courses: []; Specialisations: []; Tags: `.
  * Enhanced behaviour: The info message can be shortened to show only information being add/edited/deleted. For example, with command `create /name Nguyen /phone 12345678 /phone`, the info message can be: `Noted, created new contact: Nguyen; Phones: [12345678, 87654321].`

### Model

* Better name handling.
  * Current behaviour: Name entered by user is kept as it is.
  * Enhanced behaviour: Name entered by user is standardised so that for each word, the first character is capitalised and the rest of the characters are not capitalised (non-alphabetical characters are kept as they are).
  * Justification: This standardises the display of contact names. Furthermore, this allows better sorting. As of `v1.4`, when sorting by name in ascending order, names starting with `a` are put behind names starting with `Z`. With the standardisation, there would be no name starting with `a`.

* Stricter phone uniqueness constraint.
  * Current behaviour: Two phone numbers are identified as having the same identity if the user input of the two phones are the same. This means that `+65 12345678` and `+6512345678` are identified as two different phone numbers.
  * Enhanced behaviour: Two phone numbers are identified as having the same identity if they have the same country code and number part. This means that `+65 12345678` and `+6512345678` should be identified as the same phone number.

* Stricter link uniqueness constraint.
  * Current behaviour: Two links are identified as having the same identity if the user input of the two links are the same. This means that `https://google.com`, `www.google.com`, `http://google.com` and `google.com` are identified as 4 different links.
  * Enhanced behaviour: Two links are identified as having the same identity if the link excluding the protocol are the same. This means that `https://google.com`, `www.google.com`, `http://google.com` and `google.com` should be identified as the same link, as without the protocol, they are all reduced to `google.com`.

* Better range of priority.
  * Current behaviour: Priority only has 3 possible values: `low`, `medium` and `high`.
  * Enhanced behaviour: Number of priority values can be specified by the user and saved to `preference.json`, or whichever JSON file that the user indicates as preference JSON file in `config.json`. Priority then can be represented by a number. There should be a cap of `10` levels of priority level as well, to prevent GUI display problems.
  * Justification: With the current range of priority, it may cause inconvenience for users that want to differentiate their contact in terms of priority in greater number of levels.
  Hence the app should allow the user to specify the number of priority levels.

### Storage

* Better course storage.
  * Current behaviour: Courses are stored as a command entered by the user. For example, the course of `CS2103T` starting on 10 Aug 2023 ending on 10 Dec 2023 is stored as a JSON string `"CS2103T /start 10-08-2023 /end 10-12-2023"`.
  * Enhanced behaviour: Courses can be stored as a JSON object with `name`, `start` and `end` fields. For example, the same course can be stored as

```json
{
  "name": "CS2103T",
  "start": "10-08-2023",
  "end": "10-12-2023"
}
```
while a course of `CS2103T` without a starting and ending date can be stored as
```json
{
  "name": "CS2103T",
  "start": null,
  "end": null
}
```

### UI

* Display course details in greater details.
  * Current behaviour: Each course tile only displays the title of the course, omitting the information on course start and end dates.
  * Enhanced behaviour: Each course tile can display 3 pieces of information: name, start date and end date. This can be done by expanding each course tile vertically to have 3 lines, with first line displaying name, second line displaying start date, third line displaying end date.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch.

   1. Download the jar file and copy into an empty folder.

   1. Run the jar file with the command in the terminal `java -jar networkbook.jar`.
   
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimal.

1. Saving window preferences.

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.
      
      Expected: The most recent window size and location is retained.

### Adding a person or details

1. Create a new contact

    1. Prerequisite: Before executing each of the following test cases, your contact list should not have contact with name `Test`. If there is, either edit/delete the contact, or conduct each test cases with another name not already present in your contact list.

    1. Test case: `create /name Test`

        Expected: A new contact with name `Test` is created, with no other field specified. If the command is run the second time, no new person is added, and NetworkBook shows an error message.
    
    1. Test case: `create /name Test /phone 12345678`

        Expected: A new contact with name `Test` and phone `12345678` is created, with no other field specified.
    
    1. Test case: `create /name Test /phone 12345678 /phone 87654321`

        Expected: A new contact with name `Test` and phones `12345678` and `87654321` is created, with no other field is specified.

1. Add details to a contact

    1. Prerequisite: your contact list has at least 1 contact, and your first contact does not have a phone number of `1234`. If there is, either edit/delete the phone number of the contact, or conduct the following test case with another phone that the first contact does not have.

    1. Test case: `add 1 /phone 1234`

        Expected: A new phone number of `1234` is added to the first contact. If the command is run the second time, no new details are added, and NetworkBook shows an error message.
    
    1. Test case: `add 0 /phone 1234`

        Expected: No new details are added. NetworkBook shows an error message.

### Editing a person

1. Editing a person's single-value field.
   
    1. Test case: `edit 1 /name Test`
    
        Expected: If there is another contact with name `Test`, NetworkBook shows an error message. Otherwise, name of the first contact should change to `Test`. NetworkBook shows the updated details.
    
    1. Test case: `edit 0 /name Test`

        Expected: No person is updated. NetworkBook shows an error message.
    
    1. _{ Likewise for `graduation`, `priority` }_

1. Editing a person's multi-value field.

    1. Prerequisite: Your contact at index 1 has at least 1 phone number.

    1. Test case: `edit 1 /phone 12345678 /index 1`

        Expected: First phone number of first contact should change to `12345678`. NetworkBook shows the updated details.

    1. Test case: `edit 1 /phone 12345678 /index 0`

        Expected: No person is updated. NetworkBook shows an error message.

    1. _{ Likewise for `email`, `course`, `specialisation`, `link`, `tag` }_

### Deleting a person

1. Deleting a person.

    1. Prerequisite: Your contact list has at least 1 contact.

    1. Test case: `delete 1`
      
        Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

    1. Test case: `delete 0`
      
        Expected: No person is deleted. NetworkBook shows an error message.

1. Deleting a field from a person

    1. Prerequisite: Your first contact in the list has at least 1 phone number and a graduation year.

    1. Test case: `delete 1 /phone /index 1`

        Expected: The first phone number of the first contact is deleted.
      
    1. Test case: `delete 1 /name`

        Expected: No detail is deleted. NetworkBook shows an error message.
    
    1. Test case: `delete 1 /grad`

        Expected: Graduation year of the first contact is deleted.

### Filtering contact list

1. Filter contact by name

    1. Test case: `find`

        Expected: No filtering is done. NetworkBook shows an error message.
    
    1. Test case: `find test`

        Expected: All contacts with `test` as substring (case-insensitive) are filtered in.

1. Filter contact by `course`, `tag`, `spec`, `grad`

    1. Test case: `filter /by course /with CS2103T`

        Expected: All contacts with courses with `CS2103T` being a substring are filtered in.
    
    1. _{Likewise for `specialisation`, `tag` }_
    
    1. Test case: `filter /by grad /with 2021`

        Expected: All contacts with graduation either AY2021-S2 or AY2122-S1 are filtered in.
    
    1. Test case: `filter /by course`

        Expected: No filtering is done. NetworkBook shows an error message.

### Sorting contact list

1. Sort contact list

    1. Test case: `sort /by name`, or `sort /by name /order asc`

        Expected: Contacts are sorted by name in ascending ASCII order.
    
    1. Test case: `sort /by name /order desc`

        Expected: Contacts are sorted by name in descending ASCII order.
    
    1. _{Likewise for `grad`, `priority`}_

### Undo/redo

1. Undo and redo for data-changing command

    1. Key in a successful command, e.g. `find test`, assuming that your contact list has at least another contact with name with no substring `test`.

        Expected: NetworkBook filters in only contacts with `test` as substring (case-insensitive).
    
    1. Test case: `undo`

        Expected: NetworkBook reverts to the previous state before `find test` command was executed.
    
    1. Test case: `redo`

        Expected: NetworkBook reapplies the changes done by `find test`, which means only contacts with `test` as substring (case-insensitive) is filtered in.

### Saving data

1. Dealing with missing data file

    1. Delete the data file. If you have not modified `preferences.json`, your data file should be located in `/data/networkbook.json`.

    1. Launch the app.

        Expected: The app populated with default sample data.

1. Dealing with corrupted data file

    1. Open the data file.

    1. Change an email to `"test@gmail"` or `null`.

    1. Launch the app.

        Expected: The app starts with an empty list of contacts.

1. Dealing with data file without write permission

    1. Make the data file read-only.

    1. Launch the app.

    1. Test case: `find test`

        Expected: All contacts with name containing `test` as substring (case-insensitive) are filtered in.
    
    1. _{Likewise for non-data-changing commands: `filter`, `sort`, `list`, `help`, `exit`}_

    1. Test case: `create /name test` (prerequisite: your contact list does not include any contact with name `test`)

        Expected: NetworkBook shows an error message.
    
    1. _{Likewise for data-changing commands: `add`, `edit`, `delete`, `undo`, `redo`}_

1. Save command

    1. Make the data file read-only.

    1. Launch the app.

    1. Key in a successful data-changing command, e.g. `create /name Test` if your contact list does not a contact with name `Test`.

        Expected: NetworkBook shows an error message, but a new contact card is still created in the GUI. However, data is not saved to the data file.
    
    1. Go to your OS' file system and add write permission to the data file.

    1. Key in command: `save`

        Expected: Nothing is changed in the GUI, but a new person object is created in the data file.

--------------------------------------------------------------------------------------------------------------------

## Appendix: Effort

The table below summarises the functional codes we have contributed to evolve the original AB3 project:

| Category of achievement                         | Efforts                                                      |
| ----------------------------------------------- | ------------------------------------------------------------ |
| More fields that are useful in networking       | Added `grad`, `priority`, `link`, `spec` and `course` <br/>Adapted `phone` and `email` to hold multiple values |
| More intuitive command words                    | Adapted `create` and `add` commands <br/>Changed field prefix format to be separate from field value |
| More powerful commands for efficient networking | `edit` and `delete` can be applied to a single item in a list <br/>`find` command can search by fragment of name <br/>`sort` and `filter` commands for efficient searching <br/>`undo` and `redo` commands to help user recover mistakes <br/>`open` and `email` commands to integrate with other apps |
| More comfortable UI/UX                          | New colour schemes, more spacious organisation, display of item indices in multi-valued fields <br/>Status bar to inform user of current filter and sort status <br/>Keyboard shortcuts and mouse interactions with GUI items |

Apart from these new or adapted features, other note-worthy efforts are listed below:

| Challenge                                                    | Efforts                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| AB3 only had 1 multi-valued field, `tag`, implemented using `Set`. <br/>The Json-friendly version was `JsonAdaptedTag`. <br/>We wanted to have many of such fields. | We implemented new generic `UniqueList` and `JsonAdaptedProperty` classes for these fields. <br/>`UniqueList` stores values in an internal `ArrayList`, and supports easier interaction with values in the list. <br/>`JsonAdaptedProperty` is similar to `JsonAdaptedTag` except that it works for any identifiable type. |
| AB3's use of `requireAllNonNull` may throw run-time `NullPointerException`. | We adapted this design by replacing all run-time exceptions with assertion statements. |
| AB3 does not have GUI testing, which affects code coverage for new UI codes. | We set up headless testing using [TestFX](https://github.com/TestFX/TestFX) library, using `FxRobot` to simulate user actions. <br/>We maintained our code coverage above 87%. |

