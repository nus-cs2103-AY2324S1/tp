---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# Pawfection Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

- [Test logging plugin](https://github.com/radarsh/gradle-test-logger-plugin) used to retrieve more detailed test logs.

- Referenced and modified use of `java.utils.Optionals` when retrieving `CommandResult` from an executed `Command` from
[Team W17-2](https://github.com/AY2324S1-CS2103T-W17-2/tp),
[MainWindow.java](https://github.com/AY2324S1-CS2103T-W17-2/tp/blob/master/src/main/java/seedu/letsgethired/ui/MainWindow.java)
lines 191 - 194.

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

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/java/seedu/address/MainApp.java))
is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`AnimalLogic`**](#logic-component): The command executor.
* [**`AnimalModel`**](#model-component): Holds the data of the App in memory.
* [**`AnimalStorage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API
`interface` mentioned in the previous point.

For example, the `AnimalLogic` component defines its API in the [AnimalLogic.java](https://github.com/AY2324S1-CS2103T-F08-3/tp/blob/master/src/main/java/seedu/address/logic/AnimalLogic.java)
interface and implements its functionality using the [AnimalLogicManager.java](https://github.com/AY2324S1-CS2103T-F08-3/tp/blob/master/src/main/java/seedu/address/logic/AnimalLogicManager.java)
class which follows the `AnimalLogic` interface. Other components interact with a given component through its interface
rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a
component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-F08-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts. Some core components include: `CommandBox`, `ResultDisplay`,
`AnimalListPanel`, `AnimalDetailPanel`, and `AnimalCard`, along with other components. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.

Generally, the UI consists of the command box for the user to input commands, a result display to give an output based on the command given, and a main animal display panel consisting of 2 parts:
1. `AnimalListPanel` which consists of a list of `AnimalCard` objects
2. `AnimalDetailPanel` which is a detailed view of `Animal` objects.


The layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/resources/view/HelpWindow.fxml)

The `UI` component does the following actions:

* executes user commands using the 'Logic' component, and this is done through the `CommandBox` class of the UI where
users can input a command.
* listens for changes to `Model` data so that the UI can be updated with the modified data. For example, when an animal
is deleted or added to the catalog, the UI is updated through the `AnimalListPanel` class, where the list that is
referenced is an `Observable<Animal>` list that is updated live.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Animal` object residing in the `Model`.

### Logic component

**API** : [`AnimalLogic.java`](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/java/seedu/address/logic/AnimalLogic.java)

Here's a (partial) class diagram of the `AnimalLogic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `AnimalLogic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteAnimalCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `AnimalLogic` component works:

1. When `AnimalLogic` is called upon to execute a command, it is passed to an `AnimalCatalogParser` object which in
turn creates a parser that matches the command (e.g., `DeleteAnimalCommandParser`) and uses it to parse the command.
1. This results in a `AnimalCommand` object (more precisely, an object of one of its subclasses e.g., `DeleteAnimalCommand`) which is executed by the `AnimalLogicManager`.
1. The command can communicate with the `AnimalModel` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `AnimalLogic`.

Here are the other classes in `AnimalLogic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AnimalCatalogParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddAnimalCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddAnimalCommand`) which the `AnimalCatalogParser` returns back as a `AnimalCommand` object.
* All `XYZCommandParser` classes (e.g., `AddAnimalCommandParser`, `DeleteAnimalCommandParser`, ...) inherit from the `AnimalParser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`AnimalModel.java`](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/java/seedu/address/model/AnimalModel.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

The `AnimalModel` component,

* stores the animal catalog data i.e., all `Animal` objects (which are contained in a `UniqueAnimalList` object).
* stores the currently 'selected' `Animal` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Animal>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `AnimalUserPref` object that represents the user’s preferences. This is exposed to the outside as a `AnimalReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `AnimalModel` represents data entities of the domain, they should make sense on their own without depending on other components)


<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `PetId` list in the `AnimalCatalog`, which `Animal` can reference. This allows `AnimalCatalog` to only require `PetId` object, instead of each `Animal` needing their own `PetId` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

### Storage component

**API** : [`AnimalStorage.java`](../src/main/java/seedu/address/storage/AnimalStorage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

<box type="info" seamless>
<md>
`AnimalStorage` and `Storage` are used synonymously.
</md>
</box>

The `AnimalStorage` component,
* can save both animal catalog data and user preference data in JSON format, and read them back into corresponding
objects.

* inherits from both `AnimalCatalogStorage` and `AnimalUserPrefStorage`, which means it can be treated as either
one (if only the functionality of only one is needed). This also allows AnimalStorage to act as a unified facade that
exposes the relevant APIs for other classes to interact with the Storage.
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
that belong to the `Model`). Specifically, it depends on the `Animal` model. To decrease the amount of coupling,
`JsonAdaptedAnimal` is used, which acts as an adapter for `Animal` to be serialized/deserialized by Jackson. As such, it
is important for developers to take extra caution when modifying `Animal` and its attributes, and update
`JsonAdaptedAnimal` accordingly.

### Common classes

Classes used by multiple components are in the [commons](https://github.com/AY2324S1-CS2103T-F08-3/tp/tree/master/src/main/java/seedu/address/commons) package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

## `HelpAnimalCommand` Feature

### Feature

The `HelpAnimalCommand` is a command designed to display useful help messages to the User, without having them refer to
the User Guide constantly, which could be a cumbersome and frustrating user experience.

Here's a brief outline of the `HelpAnimalCommand`'s operations and attributes:

- `HelpAnimalCommandParser#parse` - Parses the user input to create a `HelpAnimalCommand` that may or may not contain
an argument.
- `HelpAnimalCommand#execute(AnimalModel model)` Executes the command to display a help message to the User.

Given below is an example usage scenario of the `HelpAnimalCommand`:

1. The User types in the `help` command to find out how to use the App. The user can optionally include a known command's
name as argument, e.g. `help add` to find out how to use the `AddAnimalCommand`.
2. The command verifies the validity of the syntax. If characters other than alphabets and whitespaces are used, a
`CommandException` is thrown. Otherwise, if the syntax is valid, the `help` command displays 1 of 2 possible outcomes:
   1. No arguments supplied to the command: A general help message is shown to guide the user, along with a pop-up
   containing the link to our User Guide.
   2. A valid command name is supplied to the command: A specific help message that teaches the user how to use the
   command is shown.

The following activity diagram shows how the `HelpAnimalCommand` works:

<puml src="diagrams/HelpActivityDiagram.puml" alt="HelpActivityDiagram" />

### Design considerations:

**Aspect: How the help is implemented**

- **Alternative 1 (current choice):** `help` command searches for the specified command in a `CommandEnum` class.
  - Pros: Better maintainability and separation of concerns. `CommandEnum` encapsulates the commands' name and help
          message, rather than hard-coding and maintaining a Map of commandName: helpMessage. If the help message were
          to be updated, developers only have to update the `getHelp` method in the respective commands, and it would
          automatically be refllected by `help`.
  - Cons: Increased Developer overhead. When implementing a new command, developers have to remember to add in their
          new command's name and help message into `CommandEnum` for `help` command to display the relevant help message.
- **Alternative 2:** Use Java Reflections to return all implemented `Command` classes at run-time.
  - Pros: Less developer overhead. Developers implementing new commands do not have to remember to add the new command
          to the `CommandEnum` class for the `help` command to display the newly added command's help message.
  - Cons: More complex to implement, test and debug.

--------------------------------------------------------------------------------------------------------------------

## `AddAnimalCommand` Feature

### Implementation

The `AddAnimalCommand` is a specific command designed to add an animal to the animal catalog. It identifies the animal to
be added with attributes such as its name, ID, sex, species, breed, age, date of birth and date of admission.

Here's a brief outline of its operations and attributes:

- `AddAnimalCommandParser#parse` — Parses the user input to create an `AddAnimalCommand` with the various
attributes of the Animal to be added.
- `AddAnimalCommand#execute(AnimalModel model)` — Executes the command to add the specified animal to the model.

Given below is an example usage scenario of the `AddAnimalCommand`:

1. The user types in the `add` command with the proper CLI syntax, giving a compulsory input field for each attribute.
Note that the position of the cli syntax can be in any order.
2. The Command verifies the validity of the syntax. If any of the mandatory prefixes are missing, or if the arguments to
the prefixes are invalid, a `CommandException` describing the error is thrown. Otherwise, it adds the animal with its
inputted attributes to the model and returns a successful command result.

The following sequence diagrams shows how the `AddAnimalCommand` works:

**In the logic component:**
<puml src="diagrams/AddLogicSequenceDiagram.puml" alt="AddLogicSequenceDiagram" />

<box type="info" seamless>
<md>
All commands (except `list`, `search` and `reset`) have similar logic sequence diagrams, and hence only [`AddAnimalCommand`](#addanimalcommand-feature) will be explained in detail.
</md>
</box>

**In the model component:**
<puml src="diagrams/AddModelSequenceDiagram.puml" alt="AddModelSequenceDiagram" />

### Design considerations:

**Aspect: How the addition is handled**

- **Alternative 1 (current choice):** Add the animal directly with ALL the attributes specified.
    - Pros: Straightforward for the user, since all attributes of the animals are handled at the start. The user does
            not have to remember to manually add in the attributes for the animal after adding the entry.
    - Cons: Requires many error handling in case of an invalid syntax, wrong format, or missing attributes. All the
            crucial information about the animal must be known to add the animal.
- **Alternative 2:** Add the animal with mandatory attributes such as name and id but optional attributes for the rest.
    - Pros: Can be easier for the user if the user does not know all the information of the animal at the moment
            of adding.
    - Cons: Have to create an optional field for all the other attributes. This can involve additional development
            work to allow users to input optional data as needed. This can also mean that important information that are
            not mandatory at time of adding the animal can be unintentionally left out.

--------------------------------------------------------------------------------------------------------------------

## `DeleteAnimalCommand` Feature

### Implementation

The `DeleteAnimalCommand` is a specific command designed to remove an animal from the animal catalog. It identifies the animal to be deleted based on its displayed index in the animal list.

Here's a brief outline of its operations and attributes:

- `DeleteAnimalCommand#execute(AnimalModel model)` — Executes the command to delete a specified animal.
- `targetIndex` — An attribute that holds the index of the animal to be deleted.

Given below is an example usage scenario of the `DeleteAnimalCommand`:

1. The user views the list of animals in the animal catalog. Note that this can be a filtered list, such as after using the `find` command.
2. The user decides to delete a specific animal and executes the `delete` command, providing the index of the animal to be deleted. For instance, `delete 3` would attempt to delete the third animal on the list.
3. The command verifies the validity of the index. If the index is out of bounds, it throws a `CommandException`. Otherwise, it retrieves the animal corresponding to the index, removes it from the model, and returns a successful command result.

<box type="info" seamless>
<md>
Refer to [`AddAnimalCommand`](#addanimalcommand-feature) for the sequence diagrams of the logic and model components of `DeleteAnimalCommand` as they are largely similar.
</md>
</box>

### Design considerations:

**Aspect: How the deletion is handled**

- **Alternative 1 (current choice):** Use an index to specify which animal to delete.
    - Pros: Straightforward for the user, especially if the list of animals is displayed.
    - Cons: Requires error handling in case of an invalid index.
- **Alternative 2:** Delete by specifying the animal's unique identifier or name.
    - Pros: Can be more intuitive if the user knows the specific animal's details.
    - Cons: Might lead to errors if multiple animals have similar names or if the user misspells the identifier.

--------------------------------------------------------------------------------------------------------------------

## `EditAnimalCommand` Feature

The `EditAnimalCommand` is a specific command designed to edit the field(s) of a selected animal from the animal catalog. It identifies the animal to be edited based on its displayed index in the animal list.

- `EditAnimalCommandParser#parse(String args)` — Parses the user input to create a `EditAnimalCommand` object with the given animal index.
- `EditAnimalCommand#execute(AnimalModel model)` — Executes the command to edit an animal's attribute(s) for an animal in the animal catalog.

Given below is an example usage scenario of the `EditAnimalCommand`:
1. The user wants to update erratas in the animal detail of an animal.
2. The user executes the `edit` command with the animal index and the prefixes of the attribute to be edited. For instance, `edit 1 n/Boo s/Cat` will edit the first animal's name to "Boo" and the species to "Cat".
3. The command verifies the validity of the prefixes. If any of the prefixes are invalid, it throws a `CommandException`. Otherwise, it proceeds to edit the attributes of the selected animal, and returns a successful command result.

<box type="info" seamless>
<md>
Refer to [`AddAnimalCommand`](#addanimalcommand-feature) for the sequence diagrams of the logic and model components of `EditAnimalCommand` as they are largely similar.
</md>
</box>

### Design considerations:

**Aspect: Choices of attributes to edit**

- **Alternative 1 (current choice):** Restrict the editing of animal `ID`.
    - Pros: Primary key constraint is maintained, and animals can be uniquely identified by their `ID`.
    - Cons: If user inputs the wrong `ID` when adding animal, user has to delete the entry and add again.
- **Alternative 2:** Allow all attributes to be edited.
    - Pros: Animal `ID` can be edited, user does not have to delete the entry and add a new animal.
    - Cons: Might bring about cases where 2 entries for animal share exact same fields, and it becomes impossible to identify which is which animal.

--------------------------------------------------------------------------------------------------------------------

## `ListAnimalCommand` Feature

### Implementation

The `ListAnimalCommand` is a command designed to list all animals in the animal catalog. It does not take in any arguments.

Here's a brief outline of its operations and attributes:
- `ListAnimalCommand#execute(AnimalModel model)` — Executes the command to list all animals in the animal catalog.

Given below is an example usage scenario of the `ListAnimalCommand`:
1. The user wants to view all animals that are in the catalog on the same page.
2. The user executes the `list` command.
3. The command retrieves all animals from the model and returns a successful command result.

The following sequence diagram shows how the `ListAnimalCommand` works:

**In the logic component:**
<puml src="diagrams/ListSequenceDiagram.puml" alt="ListSequenceDiagram" />

### Design considerations:

**Aspect: How the listing is handled**

- **Alternative 1 (current choice):** Use a command to list all animals in the animal catalog.
    - Pros: Straightforward for the user.
    - Cons: None.
- **Alternative 2:** Display all animals in the animal catalog by default.
    - Pros: None.
    - Cons: Might be confusing for the user if the list is long.

--------------------------------------------------------------------------------------------------------------------

## `SearchAnimalCommand` Feature

### Implementation

The `SearchAnimalCommand` is a command designed to search for animals in the animal catalog. It takes in at least one prefix as an argument.

Here's a brief outline of its operations and attributes:
- `SearchAnimalCommandParser#parse(String args)` — Parses the user input to create a `SearchAnimalCommand` object with the given predicates based on the prefixes.
- `SearchAnimalCommand#execute(AnimalModel model)` — Executes the command to filter animals in the animal catalog based on the predicate.

Given below is an example usage scenario of the `SearchAnimalCommand`:
1. The user wants to search for animals of breed Poodle in the animal catalog.
2. The user executes the `search` command with the prefixes of the attributes to be searched. For instance, `search b/Poodle` would search for animals of breed Poodle.
3. The command verifies the validity of the prefixes. If all the prefixes are invalid, it throws a `CommandException`. Otherwise, it retrieves the animals that match the valid prefixes, and returns a successful command result.

<box type="info" seamless>
<md>
Refer to [`ListAnimalCommand`](#listanimalcommand-feature) for the sequence diagrams of the model component of `SearchAnimalCommand` as they are largely similar, except for the predicate passed into updateFilteredAnimalList.
</md>
</box>

### Design considerations:

**Aspect: How the search is handled**

- **Alternative 1 (current choice):** Searches for animals based on complete matches of prefixes.
    - Pros: Easy to find specific animals
    - Cons: Might be harder for users to find animals if they do not know the exact prefixes.
- **Alternative 2:** Searches for animals based on partial matches of prefixes.
    - Pros: Easy to find animals even if the user does not know the exact prefixes.
    - Cons: Might be harder for users to find specific animals.

--------------------------------------------------------------------------------------------------------------------

## `AddTaskCommand` Feature

The `AddTaskCommand` is a specific command designed to add a task to the task list of an animal. It identifies the animal to be selected based on its index in the current animal list.

Here's a brief outline of its operations and attributes:

- `AddTaskCommand#execute(AnimalModel model)` — Executes the command to add a task to the task list of a specified animal.
- `targetIndex` — An attribute that holds the index of the specified animal.
- `task` — An attribute that holds the task that is to be added.

Given below is an example usage scenario of the `AddTaskCommand`:

1. The user views the list of animals in the animal catalog. Note that this can be a filtered list, such as after using the `find` command.
2. The user decides to add a task to a specified animal and executes the `addtask` command, providing the index of the specified animal and a task description of "Feed dog". For instance, `addtask 3 Feed dog` will add the task with task description "Feed dog" to the third animal in the list.
3. The command verifies the validity of the index. If the index is out of bounds, it throws a `CommandException`. Otherwise, it retrieves the animal corresponding to the index, adds the task to the task list of that animal, and returns a successful command result.

The following sequence diagram shows how the `AddTaskCommand` works:

<box type="info" seamless>
<md>
Refer to [`AddAnimalCommand`](#addanimalcommand-feature) for the sequence diagrams of the logic and model components of `AddTaskCommand` as they are largely similar.
</md>
</box>

### Design considerations:

**Aspect: Multiplicity of tasks to be added and animals to be modified**

- **Alternative 1 (current choice):** Allows for only one task to be added to one animal in each command.
    - Pros: Easy to keep track of the task to be added to the specified animal.
    - Cons: Users will not be able to add multiple tasks with just one command and have to use the `addtask` command multiple times to add multiple tasks.
- **Alternative 2:** Allows for multiple tasks to be added to multiple animals in one command.
    - Pros: Users can add all tasks to all animals in one command.
    - Cons: Command can get too long and user may lose track of the animals and tasks to be added.

--------------------------------------------------------------------------------------------------------------------

## `DeleteTaskCommand` Feature

The `DeleteTaskCommand` is a specific command designed to delete a task to the task list of an animal. It identifies the animal to be selected based on its index in the current animal list and the task to be deleted based on its index in the task list of the selected animal.

Here's a brief outline of its operations and attributes:

- `DeleteTaskCommand#execute(AnimalModel model)` — Executes the command to delete a specified task of a specified animal.
- `targetAnimalIndex` — An attribute that holds the index of the specified animal.
- `targetTaskIndex` — An attribute that holds the index of the task to be deleted.

Given below is an example usage scenario of the `DeleteTaskCommand`:

1. The user views the list of animals in the animal catalog. Note that this can be a filtered list, such as after using the `find` command.
2. The user decides to delete a task of a specified animal and executes the `deletetask` command, providing the index of the specified animal and the index of the task to be deleted. For instance, `deletetask 3 1` will delete the first task in the task list of the third animal in the displayed animal list.
3. The command verifies the validity of the indices. If either index is out of bounds, it throws a `CommandException`. Otherwise, it retrieves the animal corresponding to the animal index, deletes the task corresponding to the task index of the task list of that animal, and returns a successful command result.

<box type="info" seamless>
<md>
Refer to [`AddAnimalCommand`](#addanimalcommand-feature) for the sequence diagrams of the logic and model components of `DeleteTaskCommand` as they are largely similar.
</md>
</box>

**Aspect: Multiplicity of tasks to be deleted and animals to be modified**

- **Alternative 1 (current choice):** Allows for only one task to be deleted from one animal in each command.
    - Pros: Easy to keep track of the task to be deleted from the specified animal.
    - Cons: Users will not be able to delete multiple tasks with just one command and have to use the `deletetask` command multiple times to delete multiple tasks.
- **Alternative 2:** Allows for multiple tasks to be deleted from multiple animals in one command.
    - Pros: Users can delete all specified tasks from all specified animals in one command.
    - Cons: Command can get too long and user may lose track of the animals and tasks to be deleted.

--------------------------------------------------------------------------------------------------------------------

## `MarkTaskCommand` and `UnmarkTaskCommand` Feature

### Implementation

The `MarkTaskCommand` is a command designed to mark task(s) as done for an animal in the animal catalog.
The `UnmarkTaskCommand` is a command designed to mark task(s) as uncompleted for an animal in the animal catalog.

Both commands take in one animal index and at least one task index as arguments.

<box type="info" seamless>
<md>
`MarkTaskCommand` and `UnmarkTaskCommand` have very similar implementations, hence only `MarkTaskCommand` will be explained in detail.
</md>
</box>

Here's a brief outline of its operations and attributes:
- `MarkTaskCommandParser#parse(String args)` — Parses the user input to create a `MarkTaskCommand` object with the given animal index and task index(s).
- `MarkTaskCommand#execute(AnimalModel model)` — Executes the command to mark task(s) as done for an animal in the animal catalog.

Given below is an example usage scenario of the `MarkTaskCommand`:
1. The user wants to mark a few tasks as done for an animal in the animal catalog.
2. The user executes the `mark` command with the animal index and task indexes. For instance, `mark 1 2 3` would mark tasks 2 and 3 as done for the animal at index 1.
3. The command verifies the validity of the indexes. If at least one index is invalid, it throws a `CommandException`. Otherwise, it retrieves the animal and tasks that match its respective indexes, marks it as done, and returns a successful command result.

The following sequence diagrams shows how the `MarkTaskCommand` works:

**In the logic component:**
<puml src="diagrams/MarkLogicSequenceDiagram.puml" alt="MarkLogicSequenceDiagram" />

**In the model component:**
<puml src="diagrams/MarkModelSequenceDiagram.puml" alt="MarkModelSequenceDiagram" />

<box type="info" seamless>
<md>
`UnmarkTaskCommand` works similarly to `MarkTaskCommand`, except that `updateTask` takes in `false` instead of `true`.
</md>
</box>

### Design considerations:

**Aspect: How the marking is handled**

- **Alternative 1 (current choice):** Marks tasks as done only when all indexes provided are valid.
    - Pros: Easy to keep track which tasks are marked as done.
    - Cons: Users have to ensure that all indexes provided are valid.
- **Alternative 2:** Ignore invalid task indexes and mark the rest of the tasks as done.
    - Pros: Easy for users to mark tasks as done.
    - Cons: Users might not be fully aware of which tasks are marked as done.

--------------------------------------------------------------------------------------------------------------------

## `ResetTaskCommand` Feature

### Implementation

The `ResetTaskCommand` is a command designed to reset all tasks as uncompleted for all animals in the animal catalog. It does not take in any arguments.

Here's a brief outline of its operations and attributes:
- `ResetTaskCommand#execute(AnimalModel model)` — Executes the command to reset all tasks as uncompleted for all animals in the animal catalog.

Given below is an example usage scenario of the `ResetTaskCommand`:
1. The user wants to reset all tasks as uncompleted for all animals in the animal catalog.
2. The user executes the `reset` command.
3. The command retrieves all animals and tasks from the model, marks all tasks as uncompleted, and returns a successful command result.

The following sequence diagram shows how the `ResetTaskCommand` works:

**In the logic component:**
<puml src="diagrams/ResetLogicSequenceDiagram.puml" alt="ResetLogicSequenceDiagram" />

**In the model component:**
<puml src="diagrams/ResetModelSequenceDiagram.puml" alt="ResetModelSequenceDiagram" />

### Design considerations:

**Aspect: How the resetting is handled**

- **Alternative 1 (current choice):** Resets all tasks as uncompleted for all animals in the animal catalog.
    - Pros: Easy to reset all tasks as uncompleted.
    - Cons: None.
- **Alternative 2:** Resets all tasks as uncompleted for only one animal in the animal catalog.
    - Pros: Easy to reset all tasks as uncompleted for a specific animal. Users have finer control.
    - Cons: Might be harder for users to reset all tasks as uncompleted for all animals. Users have to manually reset
            tasks for each of their animal.

--------------------------------------------------------------------------------------------------------------------

## `Detailed View` Feature

### Implementation

The detailed view is a feature that shows the details of the animals not seen in the entries of the animal list. These include
Date of Birth(DOB), Date of Admission(DOA), Age, and Tasks that are added to the animal. The detailed view is handled by the `AnimalDetailPanel` class.

Here's a brief outline of its operations:
- `AnimalDetailPanel#updateDetails(Animal animal)` — Updates the detail panel of the application with the input animal's details.

`AnimalDetailPanel` is either updated by the user by clicking on an animal entry cell, or using animal-specific commands. The following activity diagram shows how the detailed view works:

<puml src="diagrams/ViewDetailActivityDiagram.puml" alt="ViewDetailActivityDiagram" />

For update of `AnimalDetailPanel` by clicking, this is handled in the `AnimalListPanel` class using a `Listener`. The `Listener` will listen to changes in selected cell, and update the animal details to show the selected animal.

For update of `AnimalDetailPanel` by animal-specific commands, animal-specific commands that automatically update the `Detailed View` with the targeted animal include `add`, `edit`, `addtask`,
`deletetask`, `mark`, and `unmark`. All other commands are considered non-animal-specific. For such animal-specific commands, the `CommandResult` will accept an additional argument `Animal`,
and this is retrieved by the `AnimalDetailView` in the `MainWindow` class. To differentiate between `CommandResult` that contains an `Animal` (animal-specific commands) and non-animal-specific commands, Optional<Animal> was used as the Type stored in the `CommandResult` class.

### Design considerations:

**Aspect: How the detailed view is displayed**

- **Alternative 1 (current choice):** Integrate detailed view with the main window of the application.
    - Pros: Easy to see the details of the animals, better accessibility of the detailed view.
    - Cons: None
- **Alternative 2:** Make the detailed view a Pop-up
    - Pros: Application window size can be smaller and more minimal.
    - Cons: User has to close the pop-up whenever user is done seeing the details of animal.


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

**Target user profile**: Volunteers at animal shelters.

**Value proposition**: Our application aims to provide a centralized platform to ensure efficient storage and access to critical details, enhancing the volunteer experience in animal shelters.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| **Priority** | **As a...** | **I want to...**                          | **So that I can...**                                       |
|--------------|-------------|-------------------------------------------|------------------------------------------------------------|
| `***`        | volunteer   | view specific information about each pet. | understand their unique needs.                             |
| `***`        | volunteer   | be able to access a user manual.          | easily learn how to navigate the app.                      |
| `***`        | volunteer   | view all animals in a single page.        | -                                                          |
| `***`        | volunteer   | add new animal entries                    | -                                                          |
| `***`        | volunteer   | update existing animal profiles           | -                                                          |
| `**`         | volunteer   | add tasks to each animal                  | know each animal's needs.                                  |
| `**`         | volunteer   | mark tasks as done                        | keep track of which animal's needs has been taken care of. |




### Use cases

(For all use cases below, the **System** is the `AnimalCatalog` and the **Actor** is the `user`, unless specified
otherwise)

---

**Use Case: UC01 - Help on Animal Commands**

**MSS:**
1. User requests help on a specific command or a list of commands related to animal handling.
2. AnimalCatalog retrieves the usage instructions for the command(s).
3. AnimalCatalog displays the help instructions to the user.
    - Use case ends.

**Extensions:**
- 1a. The user does not specify any command
    - 1a1. AnimalCatalog detects empty command.
    - 1a2. Returns a pop-up with a link to the user guide.
    - Use case ends.
- 1b. The user specifies a command that does not exist or is misspelled.
    - 1b1. AnimalCatalog displays a message indicating the command was not recognized.
    - Use case ends.

---

**Use Case: UC02 - Add Animal to Catalog**

**MSS:**
1. User requests to add an animal to the catalog by entering required details for the new animal.
2. AnimalCatalog adds the animal to the catalog and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The animal already exists in the catalog.
  - 1a1. AnimalCatalog displays a message indicating a duplicate entry.
  - Use case ends.
- 1b. The user enters incomplete or invalid details.
  - 1b. AnimalCatalog prompts the user to enter the correct and complete details.
  - Use case ends.

---

**Use Case: UC03 - Delete Animal from Catalog**

**MSS:**
1. User requests to delete an animal from the catalog by specifying the animal's index.
2. AnimalCatalog deletes the animal from the catalog and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The specified index is invalid.
    - 1a1. AnimalCatalog displays an error message indicating an invalid index.
    - Use case ends.

---

**Use Case: UC04 - Edit Animal Details in Catalog**

**MSS:**
1. User requests to edit the details of an existing animal in the catalog by specifying the animal's index and the new details.
2AnimalCatalog updates the animal's details with the new information provided and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The user attempts to change the animal's ID.
    - 1a1. AnimalCatalog displays an error message indicating the ID cannot be changed.
    - Use case ends.
- 1b. No new details are provided for the update.
    - 1b1. AnimalCatalog prompts the user to enter at least one field to be updated.
    - Use case ends.
- 1c. The specified index is invalid.
    - 1c1. AnimalCatalog displays an error message indicating an invalid index.
    - Use case ends.

---

**Use Case: UC05 - List All Animals in Catalog**

**MSS:**
1. User requests to list all animals in the catalog.
2. AnimalCatalog displays the list of all animals.
    - Use case ends.

---

**Use Case: UC06 - Search for Animals in Catalog**

**MSS:**
1. User requests to search for animals in the catalog using a specific keyword.
2. AnimalCatalog displays a list of animals that match the keyword.
    - Use case ends.

**Extensions:**
- 1a. No animals match the search keyword.
    - 1a1. AnimalCatalog displays a message indicating no results found.
    - Use case ends.
- 1b. The keyword provided is invalid or empty.
    - 1b1. AnimalCatalog display a message for the user to enter a valid keyword.
    - Use case ends.

---

**Use Case: UC07 - Add Task to Animal's Task List**

**MSS:**
1. User requests to add a task to the task list of an animal by specifying the animal's index and the task details.
2. AnimalCatalog adds the task to the animal's task list and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The task details provided are invalid or incomplete.
    - 1a1. AnimalCatalog displays a message for the user to enter valid task details.
    - Use case ends.
- 1b. The specified index is invalid.
    - 1b1. AnimalCatalog displays an error message indicating an invalid index.
    - Use case ends.

---

**Use Case: UC08 - Delete Task from Animal's Task List**

**MSS:**
1. User requests to delete a task from the task list of an animal by specifying the animal's index and the task index.
2. AnimalCatalog deletes the task from the animal's task list and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The specified animal index is invalid.
    - 1a1. AnimalCatalog displays an error message indicating an invalid animal index.
    - Use case ends.
- 1b. The specified task index is invalid.
    - 1b1. AnimalCatalog displays an error message indicating an invalid task index.
    - Use case ends.

---

**Use Case: UC09 - Mark Task as Done in Animal's Task List**

**MSS:**
1. User requests to mark a task as done in the task list of an animal by specifying the animal's index and the task index(es).
2. AnimalCatalog marks the task(s) as done and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The specified animal index is invalid.
    - 1a1. AnimalCatalog displays an error message indicating an invalid animal index.
    - Use case ends.
- 1b. The specified task index(es) are invalid or exceed the number of tasks in the task list.
    - 1b1. AnimalCatalog displays an error message indicating invalid or excessive task index(es).
    - Use case ends.
- 1c. One or more of the specified task index(es) do not correspond to an existing task.
    - 1c1. AnimalCatalog displays an error message indicating the task does not exist.
    - Use case ends.

---

**Use Case: UC10 - Unmark Task as Uncompleted in Animal's Task List**

**MSS:**
1. User requests to unmark a task as uncompleted in the task list of an animal by specifying the animal's index and the task index(es).
2. AnimalCatalog unmarks the task(s) as uncompleted and displays a success message.
    - Use case ends.

**Extensions:**
- 1a. The specified animal index is invalid.
    - 1a1. AnimalCatalog displays an error message indicating an invalid animal index.
    - Use case ends.
- 1b. The specified task index(es) are invalid or exceed the number of tasks in the task list.
    - 1b1. AnimalCatalog displays an error message indicating invalid or excessive task index(es).
    - Use case ends
- 1c. One or more of the specified task index(es) do not correspond to an existing task.
    - 1c1. AnimalCatalog displays an error message indicating the task does not exist.
    - Use case ends.

---

### Non-Functional Requirements

1. The application should respond to user interactions (e.g., searches, edits) within 2 seconds.
2. The application should handle up to 200 contacts efficiently
3. The application should load GUI components and data within 5 seconds upon startup.
4. The application should adapt to various screen sizes and orientations to provide an optimal user experience on different devices
5. The application should be able to scale to accommodate an increasing number of users without significant changes to the system architecture.
6. The application user interface should be intuitive, requiring minimal training for new users.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Animal-Specific Command**: Certain commands that target an animal, which are `add`, `edit`, `addtask`, `deletetask`, `mark`, `unmark`.

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

## **Appendix: Planned Enhancements** ##
## Planned Enhancements

1. Implement more detailed error messages for `search` command. The current error message for invalid inputs for the `search` command is not very accurate. It will be improved in the future by providing more accurate error messages. e.g. `search nil/other` will return an error message saying that the prefix provided is invalid, instead of saying that the search input is empty since it is not recognised.


2. Include `INDEX` numbers of tasks in the detailedView. Currently, tasks are listed in the detailedView without the `INDEX`. While usable, it can get inconvenient to figure out which `INDEX` a task is when the task list gets longer, as users have to manually count up/down. It will be improved by displaying the `INDEX` of the task beside each task, e.g <br>1. [ ] Task 1 <br>2. [ ] Task 2 <br>3. [X] Task 3<br>


3. Add a `view` command, to view details of animals. Currently, to view the details of an animal, users have two approaches: <br> <br> 3.1. Click on an animal entry in the animal list to see the detailed view. <br> <br>3.2. Most animal-specific commands that involve specifying an `INDEX` of animal will automatically show the details of the animal in the detailed view. Commands include: `add`, `edit`, `addtask`, `deletetask`, `mark`, `unmark`. <br><br>While intuitive, it was an oversight to not include a `view` animal command to make it more CLI-friendly. We plan on adding a `view INDEX` command in the future, where the detailed view will show the details of the animal at the specified `INDEX` of the `view INDEX` command.


4. Implement persistent `search` status. Currently, the `search` command filters the animals based on the tags passed in. If the user follows up with any command other than `delete`, the filtered list will reset to show all animals. <br><br>For example, `search s/Dog` will show all animals whose species is Dog. If user uses the following command (`add`, `edit`, `addtask`, `deletetask`, `mark`, `unmark`, `reset`) right after the `search` command, the list will reset and show all animals. <br><br>To implement the persistent `search` status, we plan to keep track of the last `FilteredAnimalList` and display the animals within the last `FilteredAnimalList` after the update.


5. Accommodate extreme length inputs for Animal `name` and Task `description`. Currently, when a user inputs an animal `name` and task `description` of extreme length, the text will be cut off in the UI. While it's highly unlikely to have such extreme length `name` and task `description`, it will still be good to include a horizontal scrollbar in the JavaFX `AnimalDetailPanel` component to accommodate such scenarios.


6. Implement more detailed error messages for `add` command. Currently, our AnimalCatalog prevents adding of identical animals, and this identity is tracked using the primary key `ID` of the animal. When a user tries to add an animal and specifies an `ID` that already exists in the AnimalCatalog, the error message states that `This animal already exists in the Catalog`. While performing as expected, it can be further enhanced with the animal `ID`, such as `Animal with ID: 1111 already exists in the Catalog`.


7. Allow for multiple task creations for single animal and creations of the same tasks for multiple animals. Currently, we only allow for one task creation for one animal per command. This can be implemented by allowing a user to specify if they are adding a single task to multiple animals with an additional `single` keyword or multiple tasks to a single animal with an additional `multiple` keyword.

## **Appendix: Effort**

Our groups faced challenges and required to put great effort for this project. These included:
1. An additional UI panel (Detailed View) that displays the details of an animal (if selected). These panel also had to be updated live with
any commands that causes changes to the Animal attributes. For example, if a task of the selected animal in the detailed view is marked as complete,
the UI needs to reflect the changes live for it to be accurate.
2. Addition of multiple new attribute classes like `PetId`, `AdmissionDate`, `DateOfBirth`, `Sex`, `Task`, and many more to fit the target audience. These meant a lot more test cases and error handling for all inputs for these classes.

