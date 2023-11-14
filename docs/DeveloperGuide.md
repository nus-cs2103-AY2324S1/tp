---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# NUSearch Developer Guide

## Table of Contents
<box>
    <ol>
        <li> <a href="#table-of-contents">Table of Contents</a> </li>
        <li> <a href="#acknowledgements">Acknowledgement</a> </li>
        <li> <a href="#setting-up-getting-started">Setting up, getting started</a> </li>
        <li> <a href="#design">Design</a> </li>
        <ol>
            <li> <a href="#architecture">Architecture</a> </li>
            <li> <a href="#ui-component">UI component</a> </li>
            <li> <a href="#logic-component">Logic component</a> </li>
            <li> <a href="#model-component">Model component</a> </li>
            <li> <a href="#storage-component">Storage component</a> </li>
            <li> <a href="#common-classes">Common classes</a> </li>
        </ol>
        <li> <a href="#implementation">Implementation</a> </li>
        <ol>
            <li> <a href="#add-feature">Add feature</a> </li>
            <li> <a href="#list-feature">List feature</a> </li>
            <li> <a href="#delete-feature">Delete feature</a> </li>
            <li> <a href="#favourite-feature">Favourite feature</a> </li>
            <li> <a href="#unfavourite-feature">Unfavourite feature</a> </li>
            <li> <a href="#favourite-list-feature">Favourite List feature</a> </li>
            <li> <a href="#search-feature">Search feature</a> </li>
            <li> <a href="#autocomplete-feature">Autocomplete feature</a> </li>
            <li> <a href="#proposed-undo-redo-feature">[Proposed] Undo/redo feature</a> </li>
            <li> <a href="#proposed-edit-feature">[Proposed] Edit feature</a> </li>
        </ol>
        <li> <a href="#documentation-logging-testing-configuration-dev-ops">Documentation, logging, testing, configuration, dev-ops</a> </li>
        <li> <a href="#appendix-requirements">Appendix: Requirements</a> </li>    
        <ol>
            <li> <a href="#product-scope">Product scope</a> </li>
            <li> <a href="#user-stories">User stories</a> </li>
            <li> <a href="#use-cases">Use cases</a> </li>
            <li> <a href="#non-functional-requirements">Non-Functional Requirements</a> </li>
            <li> <a href="#glossary">Glossary</a> </li>
        </ol>
        <li> <a href="#appendix-instructions-for-manual-testing">Appendix: Instructions for manual testing</a> </li>
        <ol>
            <li> <a href="#launch-and-shutdown">Launch and shutdown</a> </li>
            <li> <a href="#deleting-a-person">Deleting a person</a> </li>
            <li> <a href="#saving-data">Saving data</a> </li>
        </ol>
    </ol>
</box>

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--- {.dotted .thick-1 .border-primary}

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

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
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. <br>

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

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Feature
#### Implementation details 

The `add` feature allows user to add up to five different details of a person, including the name, role the person,
contact details, the course the person is taking, as well as the respective tutorial classes.

#### Implementation:

- When adding a profile, the only compulsory field that user has to include is the `name` field. 
- All the other fields `role`, `contact` and `course` are optional.
- The fields can be added in any order. 
- Users can have more than one input for each of the optional field.
- When adding a `course`, users can also choose to include or not to include the tutorial classes of the course.

#### Design Considerations
<ol>
    <li>
        <b>User Expectations:</b> 
        <md>
            Users have a common expectation that their favorite persons should be identifiable in the UI. Hence, a role
            tag is being added for different roles. 
        </md>
    </li>
    <li>
        <b>Ease of Use: </b>
        <md>
            Having only one compulsory field when adding a person makes it faster and easier for users to add someone
            into the list, instead of having to type include every single field, some of which might be unknown to the
            users. 
        </md>
    </li>
</ol>

### List Feature 
#### Implementation details

the `list`feature allows user to get the information (name, role,
contact details, courses, tutorial classes) of all the people stored.

#### Implementation:
- the list feature is executed using the `list` command

<div style="page-break-after: always;"></div>

### Delete feature

#### Implementation:
The delete feature allows the user to delete a person that they don't want to view anymore or a person with fields that 
have to be edited (such that they can add the person with the same name but with edited fields after deleting the original).
It identifies the person based on its displayed index in the person list.

* `DeleteCommandParser#parse(String args)` -- Parses the user input and creates a `DeleteCommand`object.
* `DeleteCommand#execute(Model model)` -- Executes the command to delete a person identified by the index in the person list
  and returns a `CommandResult` object.

Given below is an example usage scenario and how the delete feature behaves at each step:

1. The user wants to delete a person that they want to view on a frequent basis.
2. The user executes `delete` command with the person index. For instance, `delete 1` will favourite the person at index 1.
3. The command is parsed in `AddressBookParser`. `DeleteCommandParser` object then is created.
4. `DeleteCommandParser` object parses the user input and creates an `DeleteCommand` object with the given `INDEX` which
   represents the index of the `Person` to be deleted in the person list.
5. The `DeleteCommand#execute(Model model)` calls `Model::getFilteredPersonList` and gets the specified `Person` from the filtered person list using the index.
6. The `execute` method then calls the `deletePerson` method in the `ModelManager` with the specified `Person` to be deleted.
7. The result of the `execute` method is returned as a `CommandResult` object, which is returned back to the `LogicManager`.

The following sequence diagram shows how the delete operation works:

<puml src="diagrams/DeletePersonSequenceDiagram.puml" alt="DeletePersonSequenceDiagram" />

<box type="info" header="**Note**" >
<md>
  The lifeline for `DeleteCommandParser` should end at the destroy marker [X].
</md>
</box>

<div style="page-break-after: always;"></div>

### Favourite feature

#### Implementation:
The favourite feature allows the user to favourite persons that they want to view on a frequent basis.
It identifies the person based on its displayed index in the person list.

* `FavouriteCommandParser#parse(String args)` -- Parses the user input and creates a `FavouriteCommand`object.
* `FavouriteCommand#execute(Model model)` -- Executes the command to favourite a person identified by the index in the person list
  and returns a `CommandResult` object.

Given below is an example usage scenario and how the favourite feature behaves at each step:

1. The user wants to favourite a person that they want to view on a frequent basis.
2. The user executes `fav` command with the person index. For instance, `fav 1` will favourite the person at index 1.
3. The command is parsed in `AddressBookParser`. `FavouriteCommandParser` object then is created.
4. `FavouriteCommandParser` object parses the user input and creates an `FavouriteCommand` object with the given `INDEX` which
   represents the index of the `Person` to be favourited in the person list.
5. The `FavouriteCommand#execute(Model model)` calls `Model::getFilteredPersonList` and gets the specified `Person` from the filtered person list using the index.
6. The `execute` method then calls the `favouritePerson` method in the `ModelManager` with the specified `Person` to be favourited.
7. After that, the `execute` method will call the `setPerson` method in the `ModelManager` to set the current `Person` to
   a new `Person` with the same fields as the current `Person`, except that the `isFavourite` field is set to `true`.
8. The result of the `execute` method is returned as a `CommandResult` object, which is returned back to the `LogicManager`.

The following sequence diagram shows how the unfavourite operation works:

<puml src="diagrams/FavouritePersonSequenceDiagram.puml" alt="FavouritePersonSequenceDiagram" />

<box type="info" header="**Note**">
<md>
  The lifeline for `FavouriteCommandParser` should end at the destroy marker [X].
</md>
</box>

#### Design considerations
<ol>
    <li>
        <b>User Expectations:</b> 
        <md>
            Users have a common expectation that their favorite persons should be identifiable in the UI. Hence, a 
            yellow tag is added to the UI to indicate that the person is a favourite.
        </md>
    </li>
</ol>

<div style="page-break-after: always;"></div>

### Unfavourite feature

#### Implementation:
The unfavourite feature allows the user to unfavourite favourited persons that they do not want to view on a frequent basis anymore.
It identifies the person based on its displayed index in the person list.

* `UnfavouriteCommandParser#parse(String args)` -- Parses the user input and creates a `UnfavouriteCommand`object.
* `UnfavouriteCommand#execute(Model model)` -- Executes the command to unfavourite a person identified by the index in the person list
and returns a `CommandResult` object.

Given below is an example usage scenario and how the unfavourite feature behaves at each step: 

1. The user wants to unfavourite a person that they do not want to view on a frequent basis anymore.
2. The user executes `unfav` command with the person index. For instance, `unfav 1` will unfavourite the person at index 1.
3. The command is parsed in `AddressBookParser`. `UnfavouriteCommandParser` object then is created.
4. `UnfavouriteCommandParser` object parses the user input and creates an `UnfavouriteCommand` object with the given `INDEX` which
represents the index of the `Person` to be unfavourited in the person list.
5. The `UnfavouriteCommand#execute(Model model)` calls `Model::getFilteredPersonList` and gets the specified `Person` from the filtered person list using the index.
6. The `execute` method then calls the `unfavouritePerson` method in the `ModelManager` with the specified `Person` to be unfavourited.
7. After that, the `execute` method will call the `setPerson` method in the `ModelManager` to set the current `Person` to 
a new `Person` with the same fields as the current `Person`, except that the `isFavourite` field is set to `false`.
8. The result of the `execute` method is returned as a `CommandResult` object, which is returned back to the `LogicManager`.

The following sequence diagram shows how the unfavourite operation works:

<puml src="diagrams/UnfavouritePersonSequenceDiagram.puml" alt="UnfavouritePersonSequenceDiagram" />

<box type="info" header="**Note**">
<md>
  The lifeline for `UnfavouriteCommandParser` should end at the destroy marker [X].
</md>
</box>

<div style="page-break-after: always;"></div>

### Favourite List feature 
#### Implementation Details & Philosophy
High Level Description: 

The favourite list feature is executed using the `favlist` command. 

**FavList Command**

A FavListCommand Java class that extends the parent Command class will be created. This base class
will represent the `favlist` command

**Command Word**

A constant COMMAND_WORD = "favlist" is instantiated. 

**Usage Message**

A usage message constant MESSAGE_USAGE will be created to explain to the users how to interact and 
use the `favlist` command

**Integration with Model**
To ensure that the FavListCommand class interacts with the application's model 
to perform actions related to the favorite list. This will be done through the 
`model.updateFilteredPersonList(predicate)` method. 

In essence, the sequence of events is illustrated by the following activity diagram:

<puml src="diagrams/FavListActivityDiagram.puml" width="250" />

#### Design considerations 
<ol>
    <li>
        <b>User Expectations:</b> 
        <md>
            Users have a common expectation that their favorited people should be accessible
            and manageable in a straightforward manner. Hence, this is fulfilled using a simple and intuitive 
            `favlist` command.
        </md>
    </li>
</ol>

<div style="page-break-after: always;"></div>

### Search feature
#### Implementation Details & Philosophy
Description:

The search feature contains 4 different sub commands namely : `search`, `searchrole`,
`searchcourse`, `searchtutorial` .

1. `search` allows the user to search for a person through their name
2. `searchrole` allows the user to search for a list of people with the same role
3. `searchcourse` allows the user to search for a list of people taking a particular course
4. `searchtutorial` allows the user to search for a list of people taking a particular tutorial

Implementation:

`search`
<p>
A `search` class has a `NameContainsKeywordPredicate` field that describes the search criteria and 
filters the list of persons.`NameContainsKeywordPredicate` implements the Predicate interface for Person
object. It is used to filter a collection of Person objects based on whether their names contain 
a certain keyword.

In essence, the sequence of events is illustrated by the following activity diagram:

<puml src="diagrams/SearchActivityDiagram.puml" width="250" />
</p>

`searchrole`
<p>
A `searchrole` class has a `RoleContainsKeywordPredicate` field that describes the search criteria and 
filters the list of persons. `RoleContainsKeywordPredicate` implements the Predicate interface for Person
object. It is used to filter a collection of Person objects based on whether their role contain 
a certain keyword. 

In essence, the sequence of events is illustrated by the following activity diagram:

<puml src="diagrams/SearchRoleActivityDiagram.puml" width="250" />
</p>

`searchcourse`
<p>
A `searchcourse` class has a `CourseContainsKeywordPredicate` field that describes the search criteria and 
filters the list of persons. `CourseContainsKeywordPredicate` implements the Predicate interface for Person
object. It is used to filter a collection of Person objects based on whether their courses contain 
a certain keyword. 

In essence, the sequence of events is illustrated by the following activity diagram:

<puml src="diagrams/SearchCourseActivityDiagram.puml" width="250" />
</p>

`searchtutorial`
<p>
A `searchtutorial` class has a `TutorialContainsKeywordPredicate` field that describes the search criteria and 
filters the list of persons. `TutorialContainsKeywordPredicate` implements the Predicate interface for Person
object. It is used to filter a collection of Person objects based on whether their tutorials contain 
a certain keyword. 

In essence, the sequence of events is illustrated by the following activity diagram:

<puml src="diagrams/SearchTutorialActivityDiagram.puml" width="250" />
</p>

#### Design considerations
<ol>
1. Clarity and Ease of Use 
<p>
Having distinct search commands for different purposes instead of a single search 
command that searches all four makes it clear to the user what each command is used for. Hence, 
this will make the interface more user-friendly as it reduces the likelihood of the users getting 
confused about the commands.
</p>
2. Scalability
<p>
If the search feature were to be expanded or modified in the future, it would be easier
to add or change specific commands and their functions without affecting the whole search system. 

Additionally, if the search feature was to modified to allow multiple searches in a single command,
If a single search command was used, users may need to use complex syntax to specify what they
are searching for. Hence, having 4 different search commands will reduce ambiguity and hence
make it more scalable.
</p>
3. Improved Error Handling
<p>
With a specific search command for each search, it is easier to give specific and targeted error 
messages. For a single search command that handles multiple types of searches, providing relevant 
feedback can be more challenging. Hence, the error messages for a single search command might not be 
specific, making the application less user-friendly. 
</p>

</ol>

<div style="page-break-after: always;"></div>

### Autocomplete Feature

#### Implementation
The autocomplete feature relies on the `Map` from Command Words to `CheckedFunction` in the `AddressBookParser.java` named `wordToCommandMap`. On instantiation, this `Map` is populated with each Command Word as keys, and their respective values as lambda `CheckedFunction`s taking in the `String` userInput as arguments and returning a parsed version of their respective function. For example, a short excerpt is shown below;

```java
/**
 * Initialize the word to command map. Remember each command word maps to a lambda function
 * that will return the parsed Command object. This command object will then be executed.
 */
public AddressBookParser() {
    // Command taking in arguments
    wordToCommandMap.put(AddCommand.COMMAND_WORD, (arguments) -> new AddCommandParser().parse(arguments));
    // Command taking in no arguments; no need to parse via parser
    wordToCommandMap.put(ClearCommand.COMMAND_WORD, (arguments) -> new ClearCommand());
    // ...
}
```

Once all the command words are stored in the map, the autocomplete feature will, on key pressed in the command box, check for any command that starts with the `String` input supplied by the user, and iterate through them one by one. In essence, the sequence of events is illustrated by the following activity diagram:

<puml src="diagrams/AutocompleteActivityDiagram.puml" width="250" />

#### Design Considerations
<ol>
    <li>
        <b>Performance and Scalability:</b> 
        <md>
            In order for autocomplete to be used with many commands, a `HashMap` implementation was chosen for `AddressBookParser`'s command word storage data structure as lookup for command words can be done in approximately `O(1)` time.
        </md>
    </li>
    <li>
        <b>Consistency:</b>
        <md>
            To maintain consistency with users' expectations of how Autocomplete features work, the exact mechanics (ie pressing Tab to cycle through command suggestions) are inspired by some traditional terminals' command autocomplete features, which are similar (for instance, Powershell's default autocomplete works similarly). Thus, users do not have to learn a new mental model for how they would expect the autocomplete to function.
        </md>
    </li>
</ol>

<div style="page-break-after: always;"></div>


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

Step 3. The user executes `add --name David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

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

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

### \[Proposed\] Edit feature

#### Proposed Implementation

The edit feature allows users to edit specific fields in a person they want to edit. It identifies the person based on
its displayed index in the person list. 

* `EditCommandParser#parse(String args)` -- Parses the user input and creates a `EditCommand`object.
* `EditCommand#execute(Model model)` -- Executes the command to edit a person identified by the index in the person list 
and returns a `CommandResult` object.

Given below is an example usage scenario and how the edit feature behaves at each step.

1. The user wants to edit some fields that may be typed wrongly in the person fields.
2. The user executes `edit` command with the person index and the specifiers of the attribute to be edited. For instance, 
`edit 1 --name new name` will edit the name of the person at index 1 to `new name`.
3. The command is parsed in `AddressBookParser`. `EditCommandParser` object is created, which creates an `EditPersonDescriptor` object.
This `EditPersonDescriptor` object contains the new fields which is to be in the new edited `Person`. 
4. An `EditCommand` object is then constructed with this `EditPersonDescriptor` object and the index of the person to be edited.
5. The `EditCommand` object gets the `Person` to be edited from the filtered person list using the index. 
6. `EditCommand` object then creates an edited `Person` from the specified `Person` and the`EditPersonDescriptor` object.
7. `EditCommand` object then calls the `setPerson` method in `ModelManager` with the edited `Person`. This sets the `Person` specified by the index
in the model to be that edited `Person`. 
8. `EditCommand` object updates the person list to then display the edited `Person` in the UI.

The following sequence diagram illustrates the above steps for editing a `Person`.

<puml src="diagrams/EditPersonSequenceDiagram.puml" alt="EditPersonSequenceDiagram" />

<box type="info" header="**Note**">
<md>
  The lifeline for `EditCommandParser` should end at the destroy marker [X].
</md>
</box>

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--- {.dotted .thick-1 .border-primary}

## **Appendix: Requirements**

### Product scope

**Target user profile**:

NUS students:
* who wants to consolidate and access profiles of professors, teaching assistants (TAs), and their fellow classmates easily
* who are lazy to navigate to numerous NUS websites for academic resources
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

* Helps students to consolidate profiles of professors, teaching assistants (TAs), and their fellow classmates, within a single platform
* Compact and easy to navigate
* Students can save time and energy that would otherwise be spent searching for scattered and hard-to-access essential college information
* Features an intuitive and user-friendly interface, making it convenient for users to quickly find the information they need

<div style="page-break-after: always;"></div>

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​     | I want to …​                                                                                                                   | So that I can…​                                                        |
|---------|-------------|--------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| `* * *` | new user    | see usage instructions                                                                                                         | refer to instructions when I forget how to use the App                 |
| `* * *` | NUS student | add my classmates, professors and teaching assistants profiles in                                                              | easily keep track of my peers and mentors in NUS                       |
| `* * *` | NUS student | my classmates, professors or teaching assistants profiles                                                                      | remove entries that I no longer need                                   |
| `* * *` | NUS student | search a profile by name                                                                                                       | locate details of a person without having to go through the entire list |
| `* *`   | NUS student | view the list of profiles added                                                                                                |                                                                        |
| `*`     | NUS student | favourite profiles I would view often                                                                                          | easily view their profiles when using the app                          |
| `* * *` | NUS student | close the app when I am done using it                                                                                          |                                                                        |
| `* * `  | NUS student | save my favourite professors, teaching assistants, and classmates in a personal contact list within NUSearch |  reach out to them easily in the future                           |
| `* * `  | Professor   | include my don’t disturb timings I can reach out to them easily in the future                           | so that I can have better work life balance                          |
| `* * `  | NUS student   | delete my classmates, professors or teaching assistants profiles                           | I can remove entries I no longer need                         |
| `*  `   | NUS student   | find the direction to my tutorial / lecture classrooms                         | I will not get lost on campus.                       |

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is `NUSearch` and the **Actor** is the `User`, unless specified otherwise)

**System: NUSearch**
**Use case: UC1 - Add a new profile**
**Actor: User**

**MSS**
1.  User input a new profile
2.  NUSearch adds the new profile to the list
    Use case ends

**Extensions**
* 1a. NUSearch detects an error in the input data
*  * 1a1. NUSearch requests for the correct data
* Use case ends

**System: NUSearch**
**Use case: UC2 - Asking for help**
**Actor: User**
**MSS**
1. User requests for help
2. NUSearch sends the help

**System: NUSearch**
**Use case: UC3 - Asking for List**
**Actor: User**
**MSS**
1. User requests for the list of profiles
2. NUSearch sends the list of profiles

**Extensions**
* 1a. NUSearch detects no data in the list
*  * 1a1. NUSearch tells the user the list is empty
* Use case ends

**System: NUSearch**
**Use case: UC4 - Exit**
**Actor: User**
**MSS**
1. User requests for an exit
2. NUSSearch exits

**System: NUSearch**
**Use case: UC5 - Add favorite**
**Actor: User**
**MSS**
1. User favorites a profile
2. NUSearch favorites the profile

**Extensions**
* 1b. The profile does not exists
* * 1b1. NUSearch tells the user the profile does not exists
*  Use case ends

**System: NUSearch**
**Use case: UC6 - Search for a profile**
**Actor: User**
**MSS**
1. User requests for a profile
2. NUSearch shows the profile

**Extensions**
* 1b. The profile does not exists
* * 1b1. NUSearch tells the user the profile does not exists
*  Use case ends

*{More to be added}*

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should store data in a human-editable text file, in a human-readable format.
5.  The system should work without requiring an installer.
6.  The system should not depend on a remote server.
7.  The GUI should not cause any resolution-related inconveniences for a user for standard screen resolutions 1920x1080 or higher.
8.  The GUI should not cause any resolution-related inconveniences for a user at screen scales 100% or 125%.
9.  The GUI should be usable at standard screen resolutions 1280x720 or higher.
10. The GUI should be usable at screen scale 150%.
11. The system should be packaged into a single JAR file.
12. The JAR file containing the system should not exceed 100MB in size.
13. The system should not take more than 2 seconds to process any given command.
14. The GUI should be easy to navigate for an experienced user of the system.
15. The system should generally follow the object-oriented paradigm.
16. It should be easy to incorporate new commands, attributes or fields into the system.


### Glossary

1. **API**: Application Programming Interface, a set of rules and protocols that allow different software applications to communicate with each other
2. **Architecture Diagram**: Visual representation that illustrates how various components of the software project are structured and interconnected
3. **JavaFX UI**: A user interface framework in Java for creating interactive and visually appealing desktop applications
4. **Mainstream OS**: Windows, Linux, Unix, OS-X
5. **PlantUML**: A text-based tool for creating diagrams using simple text descriptions
6. **Sequence Diagram**: A visual tool showing the order of actions between system components or objects
7. **Object-oriented paradigm**:A programming paradigm
8. **Profile**: Information or details of a person, including their name, role, contact details, courses, tutorial classes, etc.
9. **Manual testing**: The process of testing the application through manual execution of specific test cases.
10. **Logic Component**: The component responsible for executing commands and coordinating interactions between different parts of the application. 
11. **Model Component**: The part of the application that stores and manages the data, including the address book and user preferences. 
12. **Storage Component**: The part of the application that handles reading and writing data.

--- {.dotted .thick-1 .border-primary}

<div style="page-break-after: always;"></div>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

2. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_


