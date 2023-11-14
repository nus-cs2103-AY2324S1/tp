---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# TutorMate Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the [AddressBook-Level3 project](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **Design**

### Architecture
<p style="text-align: center;">
<puml src="diagrams/ArchitectureDiagram.puml" width="280" />
</p>

The ***Architecture Diagram*** given above illustrates the high-level design of the App. 

You can see that the bulk of the app's works are done by the UI, Logic, Model and Storage components. Below are the general duties of each component and how they interact with each other.

#### Duties of each component
[**`UI`**](#ui-component) is responsible for
* Rendering visuals to the user.
* Handling and responding to user interactions.

[**`Logic`**](#logic-component) is responsible for 
* Interpreting the user command to find the corresponding strategies to execute.
* Parsing the user command to construct the corresponding parameters for the strategies.
* Executing the strategies to modify the data and state of the app.
* Updating the UI to reflect the changes in the data and state of the app.

[**`Model`**](#model-component) is responsible for
* Providing accurate representation of the current data and state of the app
* Providing APIs for other components to access and modify the data and state.

[**`Storage`**](#storage-component): is responsible for
* Loading the user data from the hard disk.
* Saving the user data to the hard disk.

#### How the architecture components interact with each other

[**`UI`**](#ui-component):
* Delegates the interpretation of user command to the `Logic` component. 
* Relies on the `Model` component to obtain the appropriate data to display.

[**`Logic`**](#logic-component):
* Informs `UI` what to display.
* Utilises `Model` to access and modify the current data and state <br>
* Instructs `Storage` to save data to the hard disk.

[**`Model`**](#model-component): 
* Provides APIs for `Logic` and `UI` to access and modify the data. 
* Depends on `Storage` to load data from the hard disk to initialise.

[**`Storage`**](#storage-component):
* Provides APIs for `Logic` to save data to the hard disk.
* Provides APIs for `Model` to load data from the hard disk to initialise.

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<p style="text-align: center;">
<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />
</p>

#### Other notes

1. Each of the four main components (also shown in the diagram above)
   * Defines its *API* in an `interface` with the same name as the Component.
   * Implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point. 
   
   For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class.
2. The team has decided to force all components to interact via interfaces rather than the concrete classes as illustrated in the (partial) class diagram below (reason: to prevent outside component's being coupled to the implementation of a component).
<p style="text-align: center;">
<puml src="diagrams/ComponentManagers.puml" width="300" />
</p>

If you are to contribute to this project, please align with this group decision.

3. There are other components that are not shown in the diagram above. These components are: 
    * `Commons` component: contains classes that are used by multiple other components.
    * `Entry Point` classes: `Main` and `MainApp` classes that are in charge of the app launch and shut down.
4. Please be reminded that, despite similar naming, our architecture is not the [Model-View-Controller (MVC)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) architecture. The major difference is that there is not a clear "Controller" component in our architecture. Instead, the role of the "Controller" is played by all the main components (UI, Logic, Model and Storage) working together.

The sections below give more details of each component.

## Components

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<p style="text-align: center;">
<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>
</p>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.
* consists of 2 sides: the left side being the lists section and the right side being the details section.
* depends on the `State` of the app currently to show the appropriate list panels.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

At a high level, the `Logic` component is responsible for making sense of the user inputs, and modifying storage and ui accordingly.
It acts like a controller to glue the other components together.

It interacts with the `UI` component by taking the user input from it, and setting the UI display accordingly.
It also calls APIs (addPerson, deleteLesson for example) from the `Model` component to modify the data representation, and call APIs
from the `Storage` component to save the data to local storage each time the data is modified.

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.
<p style="text-align: center;">
<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />
</p>

Inside the `Logic` component, there are 4 main components: `LogicManager`, `AddressBookParser`, `Parser` classes and `Command` classes.

The `Command` class will do the actual modification of the data, when executed by the LogicManager, and communicate its execution result via the CommandResult class with the Logic manager. 

Parser classes are responsible for parsing the user input and creating the corresponding command object. 

AddressBookParser is responsible for parsing the user input for finding the corresponding parser and returning the corresponding command object. 

LogicManager will perform the actual execution of the command, and update the ui and storage.

Here's a (partial) class diagram of the `Logic` component:
<p style="text-align: center;">
<puml src="diagrams/LogicClassDiagram.puml" width="550"/>
</p>

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:
<p style="text-align: center;">
<puml src="diagrams/ParserClasses.puml" width="400"/>
</p>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<p style="text-align: center;">
<puml src="diagrams/ModelClassDiagram.puml" />
</p>

The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the schedule data i.e., all `lesson` objects (which are contained in a `Schedule` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* stores the task data of all the lessons i.e., all `task` objects (which are contained in the `TaskList` objects of each `Lesson` object).
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components).
* links to the UI component to display the Show Details Panel in the UI (to reduce code complexity).
* stores a `State` object that represents the current state of the app. Currently, there are 3 main states: `STUDENT`, `SCHEDULE` and `TASK` state.

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<p style="text-align: center;">
<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />
</p>

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<p style="text-align: center;">
<puml src="diagrams/StorageClassDiagram.puml" />
</p>

The `Storage` component,
* can save both address book data, user preference data, and schedule data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage`, `UserPrefStorage` and `ScheduleStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Entry point classes
**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T11-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

### Common classes

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components. They are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### App State 

#### Background

TutorMate has 3 main "modes" each to cater to the main features. We refer to them as "states", where we implement it using the `State` enum.
The different states have their own set of Ui layout and commands, while some commands work for all states but has different behaviours between them.
The 3 main states are `STUDENT`, `SCHEDULE` and `TASK`.
* `STUDENT` state handles the student management system feature.
* `SCHEDULE` state handles lessons and schedules.
* `TASK` state handles tasks for lessons.

#### Implementation

The `ModelManager` class stores the current state of the app. The default state is `SCHEDULE`. It implements the following operations for the show command:

The `ModelManager#getState()` returns the current state of the app, `ModelManager#setState()` sets the app's state, and `ModelManager#sameState()` checks if the given state is the same as the app's state.


##### 1. Parser Overloading

States allows the same command word to be mapped to different commands based on the current app state. `AddressBookParser#parseCommand()` handles the parsing of user input into its respective parsers and commands.

For example, the "add" command word is overloaded, where using the `ModelManager#sameState()` method:
* If its in `STUDENT` state, it returns the `AddPersonCommandParser`, which returns the `AddPersonCommand` command class.
* If its in `SCHEDULE` state, it returns the `AddLessonCommandParser`, which returns the `AddLessonCommand` command class.

##### 2. Command Overloading

States allows the same command to have differing behaviours based on the current app state.
Commands can easily enable this by modifying its `execute` method and implement a switch statement that executes different functions based on the model state.

For example, the "find" command is overloaded, where using the `ModelManager#sameState()` method:
* If its in `STUDENT` state, it searches through the list of students.
* If its in `SCHEDULE` state, it searches through the list of lessons.

##### 3. Ui updates

When commands change the app state (i.e. model state), using the `ModelManager#setState()` method, it will automatically call the `UiManager` class to make necessary Ui changes.
The `UiManager` class calls the `MainWindow#changeLayout()` method to make necessary changes to the layout based on the new state:
* If the new state is `STUDENT` state, only show the `PersonList` (list for students) and `studentDetailList`.
* If the new state is `SCHEDULE` state, only show the `ScheduleList` and `lessonDetailList`.
* If the new state is `TASK` state, only show the `fullTaskList` and `taskDetailListPanel`.

#### Example execution
Given below is an example usage scenario of the `addPerson` command changes the model state and Ui layout.

Step 1. The user launches the application for the first time. The initial state of the Model will be set to `SCHEDULE`. The schedule list will be initialized with the initial schedule.

Step 2. User enters the command `addPerson -name Alice`. The `execute` method of the `AddPersonCommand` will be called by the logicManager. The `execute` command will call the `addPerson` method in the `ModelManager` class to add a new person to the app.

Step 3. `AddPersonCommand` will change the app state to `STUDENT` by calling the `ModelManager#setState()` method, which changes the model state to `STUDENT`.

Step 4. Since the current model state is `SCHEDULE`, the `ModelManager#setState()` method will call the `UiManager#changeLayout()` method, which calls the `MainWindow#changeLayout()` method.

Step 5. The `MainWindow#changeLayout()` method will hide all panels and only show the `PersonList` (list for students) and `studentDetailList`.

Step 6. `AddPersonCommand` will then call the `ModelManager#showPerson()` method, which will display the newly added person's details (not shown in diagram, described in next section).

The following sequence diagram shows how the flow of the example execution:

<p style="text-align: center;">
<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />
</p>

<box type="info" seamless>

**Note:** The lifeline for `AddPersonCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design considerations:

**Aspect: How to structure app to work with the 3 main modes:**

* **Alternative 1 (current choice):** Implement app state, where commands can check and modify the current app state.
    * Pros: 
      * Commands can be overloaded since they can have different behaviours based on app state, reducing number of commands for user to remember.
      * Commands do not need to handle changing of Ui layouts (specifying panels it requires), since similar features (e.g. managing students) all require the same panels. The `UiManager` will make required Ui changes based on desired state.
    * Cons: Increase complexity of the code.

* **Alternative 2:** Have a collection of related commands that are grouped to work with each main mode.
    * Pros: Easy to implement
    * Cons: 
      * Many commands for user to remember, although they have similar ideas (e.g. `addPerson`, `addLesson` both involving adding a new object, which the user could have used a generic `add` method.)
      * Commands have to manually call the Ui to specify which Ui panels to show.

<div style="page-break-after: always;"></div>

### EditPersonCommand and Parser

#### Background
To boost usability and support more functionalities, many commands in TutorMate support multiple forms of valid user input.

For example, when editing a person, the `INDEX` parameter can be conditionally omitted when editing the currently shown entry, the `edit` command word is overloaded for both `editLesson` and `editPerson` command, and for each of these two commands, many optional flags are available.

Given this flexibility and variety of valid user input, it is a challenge to parse all form of valid user input correctly and efficiently. 

The implementation of the `EditPersonCommand` and `EditPersonCommandParser` can serve as a good example to represent how we tackle this challenge at command and parser class level to achieve the desired flexibility and usability while keeping the code clean and maintainable.

#### Implementation
The parsing and execution process of the `EditPersonCommand` is achieved via the combination of four groups of classes: `AddressBookParser`, `TypeParsingUtil`, `EditPersonCommandParser` and `EditPersonCommand`, each of which is responsible for a different stage of the parsing process. 

Given a user input that is intended to be parsed into an `EditPersonCommand`, a high level description of the parsing process is as follows:

The first stage is to understand that user intends to invoke the `EditPersonCommand` and delegate the parsing to the specialised parser class, `EditPersonCommmandParser`. This step is done by the `AddressBookParser` class, which behaves like a "simple factory" via a giant "switch" statement. We will not discuss it in depth here.

The second stage is to parse each parameter and flags. This work is delegated to the `typeParsingUtil` class, which is a utility class that contains many static methods that are responsible for parsing different types of user input that is reused across different command parsers.

Then, the `EditPersonCommandParser` class is responsible for combining the results of the previous stage and constructing the `EditPersonCommand` object.

Finally, the `EditPersonCommand` class is responsible for executing the command and updating the model accordingly.

The following section will discuss the implementation of the later three groups of classes in more detail.

##### 1. ListEntry and ListEntryField class
In TutorMate, Lesson and Person extends `ListEntry` class as they are displayed as items of the ___STUDENTS list___, ___SCHEDULE list___ respectively.

Each `ListEntry` object contains a list of `ListEntryField` objects, which are the fields of the `ListEntry` object. For example, a `Person` object contains a list of `ListEntryField` objects, which are the fields of the `Person` object, such as name, phone number, email address, etc.

These two classes are used in multiple generic classes and methods to achieve the flexibility and robustness of the parsing process.

##### 2. Parsing of Flags and Parameters
The `TypeParsingUtil` class parses all kinds of flags (which are all `ListEntryField` ) via a single powerful generic method, `parseField`. The method signature is as follows:
```
 public static <T extends ListEntryField> T parseField(String flagName,
                                                          String input,
                                                          Of<T> of,
                                                          boolean isOptional) throws ParseException
```
The first parameter, `flagName`, is the name of the flag that is being parsed. It will also be used to generate the error message when the parsing fails.

The second parameter, `input`, is the user input that is intended to be parsed into the flag.

The third parameter, `of`, is a self-defined functional interface that is to be used as the "factory" to create the flag object. The `of` method signature is as follows:
```
public interface Of<T extends ListEntryField> {
    T apply(String str) throws IllegalArgumentException, ParseException;
}
```
The last parameter, `isOptional`, is a boolean flag that indicates whether the flag is optional or not. If the flag is not found in the user input, this method will throw a `ParseException` if the flag is not optional, but will return a `null` object if the flag is optional. It is overloaded to have a default value of `true` when omitted.


The `parseField` method is reused extensively in multiple parser classes to parse all the flags. The following is an example of how it can be used:
```
// Parse the name flag, which is compulsory
Name name = TypeParsingUtil.parseField("name", args, Name::of, false);

// Parse the phone flag, which is optional
Phone phone = TypeParsingUtil.parseField("phone", args, Phone::of);
```
##### 3. Parsing of the EditPersonCommand
The `EditPersonCommandParser` class will parse the user input into an `EditPersonCommand` object. 

This is achieved by combining the results of parsing flags in the previous stage and construct a `Person` object that contains the updated fields.

Each of the `ListEntryField` has a global and static singleton default instance. For example, the default `Phone` is a `Phone` object with message "To be added". Please also note that it is not possible to create a `Phone` object with such message via public constructor and factory method.

Each of the `ListEntry` also has a global and static singleton default instance whose fields are all the default `ListEntryField` objects. We can obtain a clone of the default `ListEntry` object via a static method in the class method.

The `EditPersonCommandParser` class will first obtain a clone of the default `Person` object via the `getDefault` method, and then update the fields of the `Person` object with the results of parsing flags in the previous stage when the corresponding flags are found in the user input.

The following is an example of how it can be used:
```
Person person = Person.getDefaultPerson();
            person.setNameIfNotDefault(parseField("name", args, Name::of, nameIsOptional));
            person.setPhoneIfNotDefault(parseField("phone", args, Phone::of));
            person.setEmailIfNotDefault(parseField("email", args, Email::of));
            person.setAddressIfNotDefault(parseField("address", args, Address::of));
            person.setSubjectsIfNotDefault(parseField("subject", args, Subjects::of));
            person.setTagsIfNotDefault(parseField("tag", args, Tags::of));
            person.setRemarkIfNotDefault(parseField("remark", args, Remark::of));
```
##### 4. EditPersonCommand Implementation
The `EditPersonCommand` class will execute the command and update the model accordingly.

Realising the commonality between all kinds of edit commands, we have created an abstract generic class, `AbstractEditCommand`, to serve as template class containing the logic of editing a `ListEntry` object.

The signature of the `AbstractEditCommand` class is as follows:
```
public abstract class AbstractEditCommand<T extends ListEntry<? extends T>> extends Command
```
And the concrete edit classes (`EditPersonCommand` class for example) can be simply implemented by "filling in the blank" and providing the correct parameters to the template class.

The template for the edit process is as follows:
```
initModelMethods();
init();
editFields();
updatePersonLessonMap();
validateEditedAndWriteBack();
showMethod.accept(edited);
return new CommandResult("Edit success.\n from: " + original.toString() + "\n to: " + edited.toString());
```
The `EditPersonCommand` class can be simply implemented by overriding the abstract methods in the `AbstractEditCommand` class.

The most important method to be overridden is the initModelMethods(), which looks like this in the `EditPersonCommand` class:
```
 @Override
    protected void initModelMethods() {
        currentShownEntry = model.getCurrentlyDisplayedPerson();
        list = model.getFilteredPersonList();
        hasClashWith = model::hasPersonClashWith;
        deleteMethod = model::deletePersonForEdit;
        addMethod = model::addPerson;
        getClashingEntry = model::getPersonClashWith;
        showMethod = model::showPerson;
    }
```

#### Design considerations:

**Aspect: How to keep the code clean and maintainable while providing the flexibility and variety of valid user input.

* **Alternative 1 (current choice):** Group the parsing and execution of a command into four groups of classes: `AddressBookParser`, `TypeParsingUtil`, `EditPersonCommandParser` and `EditPersonCommand`.
    * Pros:
        * Clear separation of concerns.
        * Easy to maintain, easy to extend as for each new `ListEntryField` and `ListEntry` class that is added and fulfill the contract of the `ListEntryField` and `ListEntry` class, the parsing and execution of the command will almost be automatically supported.
        * Good DRY principle. Only one method is needed to parse all kinds of flags, the knowledge of a given type of processing is encapsulated in a single place.
    * Cons: Less flexible. It is not easy to add flags that have special parsing logic not supported by the current template.

* **Alternative 2:** Having even stricter constraints on the `ListEntryField` and `ListEntry` class, so that we can use reflection feature of Java to automatically parse the flags.
    * Pros: Potentially better extensibility.
    * Cons:
        * Difficult to implement.
        * Even more difficult to debug.
        * Even less flexible.
        * Not easy to get the correct error message and type, as if the call via reflection throws exception, `ParseException` for example, the reflection call will always overwrite the original exception and throw an `InvocationTargetException` instead.
* **Alternative 3:** Writing a separate parser class for each command.
    * Pros: Flexible. Easy to implement.
    * Cons: 
        * Many redundant code.
        * Difficult to maintain as the code is not DRY.

### Show feature

#### Implementation

The show feature is facilitated by `ShowCommand` which extends the abstract `Command` class. The `ShowCommand` will check the current state of the Model (either `STUDENT`, `SCHEDULE`, `TASK` or `NONE`) when the `execute` method is called and see whether it is currently showing a ___STUDENTS list___, ___SCHEDULE list___ or a ___TASKS list___. 

Additionally, the `ModelManager` class implements the following operations for the show command:

`ModelManager#linkUi()` — Links the Ui of TutorMate to the Model to display the Show Panel
`ModelManager#showPerson()` — Shows the details of the specified person in the Ui.
`ModelManager#showLesson()` — Shows the details of the specified lesson in the Ui.
`ModelManager#showTask()` — Shows the details of the specified task in the Ui.

The Show Command has different behaviours based on the current state in the `Model`:
- The Show Command will show the Person Details if the current state is `STUDENT`
- The Show Command will show the Lesson Details if the current state is `SCHEDULE`
- The Show Command will show the Task Details if the current state is `TASK` 

The `execute` method of `ShowCommand` will be called by the logicManager when the `show` command is input.

Given below is an example usage scenario and how the show feature behaves at each step.

Step 1. The user launches the application for the first time. The initial state of the Model will be set to `SCHEDULE`. The schedule list will be initialized with the initial schedule.

Step 2. The user executes `show 5` command to show the details of the 5th lesson in the schedule list. The `execute` method of the `ShowCommand` will be called by the logicManager. The `execute` command will call the `showLesson` method in the ModelManager which displays the `LessonDetailListPanel` in the Ui.

Step 3. The user wants to display the details of a person and switches to the student list with the `list students` command in the Command Line Interface(CLI) Ui. The current list will display the students and the Model state will change to `STUDENT`.

Step 4. The user executes `show 1` command to show the details of the 1st person in the student list. The `execute` method of the `ShowCommand` will be called by the logicManager. The `execute` command will call the `showPerson` method in the ModelManager which displays the `StudentDetailListPanel` in the Ui.

The following sequence diagram shows how the show operation works for showing a person from the `STUDENT` list:

<p style="text-align: center;">
<puml src="diagrams/ShowSequenceDiagram.puml" alt="ShowSequenceDiagram" />
</p>

<box type="info" seamless>

**Note:** The lifeline for `ShowCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

#### Design considerations:

**Aspect: How the Show Command calls the Ui to display the Show Details Panel:**

* **Alternative 1 (current choice):** Link the Model with the Ui to display the Show Details Panel.
    * Pros: Easy to implement.
    * Cons: May increase coupling between the Model and Ui

* **Alternative 2:** Pass the Ui call from the Show Command around the currently linked files.
    * Pros: No additional coupling created.
    * Cons: Many files will have to be changed and will increase the complexity of the code.

### Task List Feature

#### Implementation

The task list functionality is facilitated by multiple related classes. The `TaskList` can contain any number of `Task` objects. The `TaskList` is implemented as a component of each Lesson so that each lesson will have its own individual Task List.

The `FullTaskList` class is implemented as a list to view all the collective tasks of all the lessons. It uses a similar structure to `ScheduleList` to display the list.

#### Design considerations:

**Aspect: How the Task List structure should be implemented:**

* **Alternative 1 (current choice):** Each individual Lesson will contain a Task List.
    * Pros: Easy to implement.
    * Cons: May be hard to increase navigability from students to lessons and tasks.

* **Alternative 2:** Create a central Task List and filter the tasks for each lesson.
    * Pros: May be easier to increase navigability.
    * Cons: May increase code complexity due to filtering the respective tasks.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](https://ay2324s1-cs2103t-t11-3.github.io/tp/Documentation.html)
* [Testing guide](https://ay2324s1-cs2103t-t11-3.github.io/tp/Testing.html)
* [Logging guide](https://ay2324s1-cs2103t-t11-3.github.io/tp/Logging.html)
* [Configuration guide](https://ay2324s1-cs2103t-t11-3.github.io/tp/Configuration.html)
* [DevOps guide](https://ay2324s1-cs2103t-t11-3.github.io/tp/DevOps.html)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Private Tuition Teachers
* has a need to manage a significant number of students
* Teaches multiple subjects
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Helps private tuition teachers manage their students more easily. Faster access and storage of students’ contacts and data than a typical mouse/GUI driven app, provides better time management and productivity. The app will help to manage students for a single tuition teacher only.



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​         | I want to …​                                                                  | So that I can…​                                                                               |
|----------|-----------------|-------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| `* * *`  | new user        | see usage instructions                                                        | refer to a user guide when I forget how to use the app                                        |
| `* * *`  | new user        | purge all existing sample data                                                | get rid of the experimental data when exploring the app                                       |
| `* * *`  | private tutor   | install the app on my device with one click                                   | use the app with ease without much trouble building the environment                           |
| `* * *`  | private tutor   | add students by name only without having to include all their contact details | can keep tabs on potential students                                                           |
| `* * *`  | user            | delete a student in the app's students list                                   | remove entries that I no longer need                                                          |
| `* * *`  | user            | find a student by name                                                        | locate details of students without having to go through the entire list                       |
| `* * *`  | tutor           | quickly edit student details                                                  | save time re-adding students if I have added the wrong details by accident                    |
| `* * *`  | busy tutor      | add lessons that fits into my schedule quickly                                | ensure that the newly added lesson do not clash with existing tuition sessions in my schedule |
| `* * *`  | flexible tutor  | edit my student’s lesson timings                                              | accommodate any changes in timing requested by them                                           |
| `* * *`  | private tutor   | see a specific student’s data from the contact list                           | get a more concise and detailed view of the student                                           |
| `* *`    | private tutor   | keep contact details of students hidden unless specified otherwise            | minimize the chances of someone else seeing them by accident                                  |
| `* *`    | tutor           | see my teaching schedule                                                      | complete lesson preparation before the upcoming tuition sessions                              |                                                                                                                |                                                                                    |
| `* *`    | efficient tutor | filter lessons to view lessons on or after a specific date                    | plan my activities with the free time that is not occupied by lessons                         |
| `* *`    | tutor           | link a student to a lesson                                                    | prepare for the lesson according to the student's weaknesses and strengths                    |
| `* *`    | forgetful tutor | see all the planned lessons with a student                                    | plan for additional lessons with the student                                                  |
| `* *`    | tutor           | update my students' details as they progress through learning in remarks      | keep track of their information like test scores and learning styles                          |
| `*`      | forgetful tutor | include certain tasks to do for each lesson                                   | remember to do them in preparation for each lesson                                            |
| `*`      | busy user       | see all the tasks for all my tuition sessions at a glance                     | I know how many tasks I have yet to complete in preparation for all my tuition sessions       |



### Use cases

(For all use cases below, the **System** is `TutorMate` and the **Actor** is the `Tutor`, unless specified otherwise)


### Use case: Add a student [UC01]

**MSS**
1. User chooses to add a new student.
2. User enters the required details to create a student.
3. TutorMate creates the student.

Use Case ends.

**Extensions**
* 2a. Student's name is not specified and / or details specified are incorrect.
  * 2a1. The app informs the user of the error.
  * 2a2. The user enters new data.
  <br> Use case resumes from step 2.


### Use case: Delete a student [UC02]

**MSS**

1. User requests to list students.
2. TutorMate shows a list of students.
3. User requests to delete a student in the list.
4. TutorMate deletes the person.

Use case ends.

**Extensions**

* 2a. The list is empty (no students in student list).
  <br> Use case ends.
* 3a. The given index is invalid.
  * 3a1. TutorMate shows an error message. 
  <br> Use case resumes at step 2.


### Use case: Show a student [UC03]

**MSS**

1. User requests to list students.
2. TutorMate shows a list of students.
3. User requests to see the details of a specific student.
4. TutorMate shows the student.

Use case ends.

**Extensions**

* 2a. The list is empty (no students in student list).
  <br> Use case ends.
* 3a. The given index is invalid.
    * 3a1. TutorMate shows an error message.
    <br>  Use case resumes at step 2.


### Use case: Find & see a student with their details [UC04]

**MSS**
1. User requests to find a student by a specific characteristic e.g. Name.
2. TutorMate shows a list of students that matches the user's input.
3. User <u>shows a student (UC03)</u>

Use Case ends.

**Extensions**

* 2a. The list is empty (no students found that matches the user's input).
  <br> Use case ends.


### Use case: Edit a student's details [UC05]

**MSS**
1. User requests to list students.
2. TutorMate shows the student list.
3. User chooses to edit a student and enters the data.
4. TutorMate edits the student's details.

Use Case ends.

**Extensions**

* 3a. Some details are incorrect/ overlapping with existing app data.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
  <br> Use case resumes from step 3.
* 3b. The given index is invalid.
    * 3b1. The app informs the user of the error.
    * 3b2. The user enters new data.
  <br> Use case resumes from step 3.

### Use case: Add a lesson [UC06]

**MSS**
1. User requests to list schedule.
2. TutorMate shows the schedule list.
3. User enters the required details to create a lesson.
4. TutorMate creates the lesson.

Use Case ends.

**Extensions**

* 3a. Lesson's name is not specified and / or details specified are incorrect.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
  <br>Use case resumes from step 3.

### Use case: Add a task to a lesson [UC07]

**MSS**
1. User requests to list schedule.
2. TutorMate shows the schedule list.
3. User enters the required details to create a task to a lesson.
4. TutorMate creates the task in the task list of the lesson.

Use Case ends.

**Extensions**

* 2a. The list is empty (no lessons in schedule list).
   <br> Use case ends.
* 3a. Description details are missing/ overlapping with existing app data.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data.
  <br>Use case resumes from step 3.
* 3b. The given index is invalid.
    * 3b1. The app informs the user of the error.
    * 3b2. The user enters new data.
  <br>Use case resumes from step 3.

### Use case: Delete a task to a lesson [UC08]

**MSS**
1. User requests to list schedule.
2. TutorMate shows the schedule list.
3. User requests to see the details of a lesson.
4. TutorMate shows the lesson.
5. User requests to delete a specific task in the lesson.
6. TutorMate deletes the task.

Use Case ends.

**Extensions**

* 2a. The list is empty (no lessons in schedule list).
  <br>Use case ends.
* 3a. The given lesson index is invalid.
    * 3a1. The app informs the user of the error.
    * 3a2. The user enters new data. 
  <br>Use case resumes from step 3.
* 5a. The given task index is invalid.
    * 5a1. The app informs the user of the error.
    * 5a2. The user enters new data.
  <br>Use case resumes from step 5.
* 5b. The task list is empty (no tasks in the task list of the lesson).
  <br>Use case ends.


### Non-Functional Requirements

1.  Should work on any Windows, Linux, and MacOs as long as it has Java `11` or above installed.

2.  Should be able to hold up to: 
   * 1000 persons without a noticeable(> 1 second) sluggishness in performance for typical usage.
   * 1000 lessons without a noticeable(> 1 second) sluggishness in performance for typical usage.
   * a total of 1000 tasks without a noticeable(> 1 second) sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

4. The application should respond and load all data within three seconds.
  
5. The response time for adding, updating, or deleting student and lesson records should be within two seconds.
 
6. The system should be usable with a user interface that is intuitive enough for users who have not used similar applications before.

7. Users will only have access to their own students' data.

8. Should there be an irregular termination of the app, the data should not be corrupted.
 
9. The app should respond to user text input within 0.2 second.
 
10. Data should be stored locally.

11. The application is not expected to:
    1. Perform analysis of students' academic performance.
    2. Send real-time notifications when there is an upcoming lesson.
    3. Delete lessons that are past the current date automatically.

    

### Glossary

* **GUI**: Abbreviation for graphical user interface, which allow user to interact with with the application via graphical components such as icons, buttons, and menus.
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and Shutdown

* Initial Launch

   1. Download the latest `tutormate.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-3/tp/releases) and copy it into an empty folder.

   2. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tutormate.jar` command to run the application.<br>

   3. Expected: A GUI similar to the picture below should appear in a few seconds. The app will contain some sample data.<br>
       ![Ui](images/about.png)

* Shutdown
  * Click on the cross at the top of the window.
       * MacOs: Red cross button at top left side of the window.
       * Windows & Linux: Cross button at the top right side of the window.
       <br>
     Expected: The application window disappears. 
   <br><br>
  * Enter `exit` into the command box of the application. <br>
  Expected: The application window disappears.
  <br><br>

* Subsequent Launches

    1. Relaunch the application by `cd` into the folder with `tutormate.jar`.

    2. Use the `java -jar tutormate.jar` command to run the application.

    3. Expected: The list panel will show a list of lessons similar to the picture above (in ___SCHEDULE list___). The data in the application should be the same as the data in the application before it was shut down previously.

* Saving Window Preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the application by using the `java -jar tutormate.jar` command.<br>
       Expected: The most recent window size and location is retained.

### Basic Usage

* The app is split into 3 states:
    1. ___STUDENTS list___
       ![list STUDENTS](images/list/list_student_positive.png)

    2. ___SCHEDULE list___ (default)
       ![list SCHEDULE](images/list/list_schedule_positive.png)

    3. ___TASKS list___
       ![list TASKS](images/list/list_tasks_positive.png)



* Each state has its associated features, while certain features work with all states but has differing functionalities.
* The ___STUDENTS list___ handles student details management, ___SCHEDULE list___ handles lessons, scheduling and the tasks for each lesson while the full ___TASKS list___ is a view to display all tasks.
* The _GUI_ <sup>[1](#glossary)</sup> has several main components (see _GUI_ <sup>[2](#glossary)</sup> image below):
    * The command box is for users to enter and execute commands.
    * The response box is to display responses for command execution, to indicate success or errors.
    * The left side has the list panel, which shows different list types (student, schedule, tasks).
    * The right side has the details panel, which shows details of any specific item in the list.
      ![Ui](images/Ui.png)

### Viewing ___STUDENTS list___, ___SCHEDULE list___ and  ___TASKS list___

* In ___STUDENTS list___:
  * Test case: `list students` <br>
    Expected: A list of all students with their names is displayed in the list panel. Showing list students is shown in the response box.
  
  * Test case: `list students email` <br>
    Expected: A list of all students with their names and emails is displayed in the list panel.

* In ___SCHEDULE list___:
  * Test case: `list` <br>
    Expected: A list of all lessons with their names, date, start and end time is displayed in the list panel. Showing list schedule is shown in the response box.
  
  * Test case: `list schedule` <br>
    Expected: A list of all lessons with their names, date, start and end time is displayed in the list panel. Showing list schedule is shown in the response box.

* In ___TASKS list___:
    * Test case: `list tasks` <br>
      Expected: The full task list with task description is displayed in the list panel. Showing list tasks is shown in the response box.

<div style="page-break-after: always;"></div>

### Show Feature

##### Showing a student in ___STUDENTS list___

1. Prerequisites: List all students using the `list students` command. There are more than 2 and less than 80 students in the displayed list of students.

2. Test case: `show 1`<br>
   Expected: The first student in the displayed list of students is shown in the details panel. The details of the first student in the displayed list is shown in the response box.

3. Test case: `show 80` <br>
   Expected: Details panel remains the same. Error indicating index is invalid is shown in the response box.

##### Showing a lesson in ___SCHEDULE list___

1. Prerequisites: List all lessons using the `list` command. There are more than 2 and less than 80 lessons in the displayed list of lessons.

2. Test case: `show 2`<br>
   Expected: The second lesson in the displayed list of lessons is shown in the details panel. The details of the second lesson in the displayed list is shown in the response box.

3. Test case: `show1` <br>
   Expected: Details panel remains the same. Error indicating unknown command is shown in the response box.

##### Showing a task in ___TASK list___

1. Prerequisites: List all tasks using the `list tasks` command. There are more than 2 and less than 80 tasks in the displayed list of tasks.

2. Test case: `show 2`<br>
   Expected: The second task in the displayed list of tasks is shown in the details panel. The details of the second task in the displayed list is shown in the response box.

3. Test case: `show -2`<br>
   Expected: Details panel remains the same. Error indicating invalid command format with the usage message of show is shown in the response box.

   
### Add Feature

##### Adding a student in ___STUDENTS list___

   1. Prerequisites: List all students using the `list students` command. There is currently no student with the name "Leah", "Riley" and "Max".

   2. Test case: `addPerson -name Leah -phone 88888888 -subject biology -remark new student -tag new`<br>
      Expected: A new student is added, with name "Leah", phone "88888888", subject "BIOLOGY", remark "new student" and tag "new". The details of the added student is shown in the response box.

   3. Test case: `add -name Riley -phone 81818181` (in ___STUDENTS list___) <br>
      Expected: A new student is added, with name "Riley" and phone "81818181". The details of the added student is shown in the response box.

   4. Test case: `addPerson -name Lea_h -phone 88888888 -subject biology -remark new student -tag new`<br>
      Expected: No student is added. Error indicating invalid format with the name constraints shown in the response box.

   5. Test case: `addPerson -name Max -phone abcdefg` <br>
      Expected: No student is added. Error indicating invalid format with the phone number constraints shown in the response box.

   6. Test case: `addPerson` <br>
      Expected: No student is added. Error indicating invalid format and flag name not found shown in the response box.<br><br>

##### Adding a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * There is currently no lessons with the name "Chemistry Lesson at Bedok" and "Biology Lesson at Tai Seng".
    * There are no existing lessons from 12 December 2023 to 15 December 2023 in the application.
    * There is an existing lesson on 11 December 2023 from 11:00 AM to 1:00 PM.<br><br>

2. Test case: `addLesson -name Chemistry Lesson at Bedok -day 2023/12/12 -subject chemistry -start 11:00 -end 13:30`<br>
   Expected: A new lesson is added, with name "Chemistry Lesson at Bedok" from 11:00 AM to 1:30 PM on "2023/12/12" with subject "CHEMISTRY". The details of the added lesson is shown in the response box.

3. Test case: `addLesson -name Biology Lesson at Tai Seng -day 2023/12/13 -start 15:00 -end 14:00`<br>
   Expected: No lesson is added. Error indicating invalid lesson format and that the end time cannot be before start time is shown in the response box.

4. Test case: `addLesson -name Biology Lesson at Tai Seng -day 2023/12/11 -start 12:00 -end 14:00`<br>
   Expected: No lesson is added. Error indicating existing lesson clashes with lesson to be added with the details of the existing lesson in the schedule shown in the response box.

5. Test case: `add -name Biology Lesson at Tai Seng` (in ___SCHEDULE list___) <br>
   Expected: A new lesson is added, with name "Biology Lesson at Tai Seng". The details of the added lesson is shown in the response box.


##### Adding a task to a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * There is currently no tasks with the name "Mark Alkanes Practice" and "Make Forces Notes" in all the lessons in the application.
    * There is a task with the description "Mark Practice Paper" in all the lessons in the application.
    * There are more than 2 and less than 80 lessons in the displayed list of lessons. <br><br>
   
2. Test case: `addTask 2 Mark Alkanes Practice` <br>
   Expected: A new task is added to the second lesson in the displayed list of lessons, with description "Mark Alkanes Practice". The details of the added task is shown in the response box.

3. Test case: `addTask Make Forces Notes` (no lesson is shown in details panel) <br>
   Expected: No task is added to any of the lessons in the list. Error indicating no lesson is displayed and use show lessonIndex before adding task is shown in the response box.

4. Test case: `show 1` followed by `addTask Make Forces Notes` <br>
   Expected: A new task is added to the first lesson in the displayed list of lessons, with description "Make Forces Notes". The details of the added task is shown in the response box.

5. Test case: `addTask 1 Mark Practice Paper` <br>
   Expected: No task is added to the first lesson in the displayed list of lessons. Error indicating existing task with same description in the specified lesson shown in the response box.

6. Test case: `addTask` <br>
   Expected: No task is added to any of the lessons in the list. Error indicating invalid description shown in the response box.

<div style="page-break-after: always;"></div>

### Delete Feature

##### Deleting a student in ___STUDENTS list___

   1. Prerequisites: There are more than 2 students in the displayed list of students.

   2. Test case: `deletePerson 1`<br>
      Expected: First student is deleted from the list. Details of the deleted student is shown in the response box. 

   3. Test case: `delete 1` (in ___STUDENTS list___) <br>
      Expected: First student is deleted from the list. Details of the deleted student is shown in the response box.

   4. Test case: `deletePerson 0`<br>
      Expected: No student is deleted. Error indicating index input cannot be zero is shown in the response box.

   5. Test case: `deletePerson -1` <br>
      Expected: No student is deleted. Error indicating index input cannot be negative is shown in the response box.
   
   6. Test case: `deletePerson` <br>
      Expected: No student is deleted. Error indicating invalid command format is shown in the response box.

##### Deleting a lesson in ___SCHEDULE list___

1. Prerequisites: List all lessons using the `list` command. There are more than 2 and less than 80 students in the displayed list of lessons.<br>

2. Test case: `deleteLesson 1`<br>
   Expected: First lesson is deleted from the list. Details of the deleted lesson is shown in the response box.

3. Test case: `delete 3` (in ___SCHEDULE list___) <br>
   Expected: Third lesson is deleted from the list. Details of the deleted lesson is shown in the response box.

4. Test case: `deleteLesson 80`<br>
   Expected: No lesson is deleted. Error indicating index input is out of bounds and the acceptable range is shown in the response box.

5. Test case: `deleteLesson` <br>
   Expected: No lesson is deleted. Error indicating invalid command format is shown in the response box.

<div style="page-break-after: always;"></div>

##### Deleting a task from a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command. There are more than 2 lessons in the displayed list of lessons.
    * All lessons have more than 2 and less than 10 tasks. <br><br>

2. Test case: `show 1` followed by `deleteTask 2` <br>
   Expected: The second task in the task list of the first lesson in the displayed list of lessons is deleted. Details of the deleted task is shown in the response box.

3. Test case: `deleteTask 1` <br>
   Expected: No task is deleted. Error indicating the use of show lesson before deleting task is shown in the response box.

4. Test case: `deleteTask 1` (in ___STUDENTS list___)
   Expected: No task is deleted. Error indicating deleting of tasks only in schedule list is shown in the response box.

5. Test case: `deleteTask 1` (in ___TASKS list___)
   Expected: No task is deleted. Error indicating deleting of tasks only in schedule list is shown in the response box.

### Edit Feature

##### Editing a student in ___STUDENTS list___

1. Prerequisites:
    * List all students using the `list students` command. 
    * There is currently no student with the name "Leah" and "Max".
    * There is an existing student with the name "Riley".
    * There are more than 2 students in the displayed list of students. <br><br>

2. Test case: `editPerson 1 -name Leah` <br>
   Expected: The name of the first student in the displayed list of students is edited to "Leah". Details of the edited student is shown in the response box.

3. Test case: `edit 2 -name Max -subject biology` (in ___STUDENTS list___) <br>
   Expected: The name of the second student in the displayed list of students is edited to "Max" and the subject of this student is set to "BIOLOGY". Details of the edited student is shown in the response box.

4. Test case: `editPerson 1 -name Riley` <br>
   Expected: No student is edited. Error indicating a clash detected is shown in the response box.

<div style="page-break-after: always;"></div>

##### Editing a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * There is currently no lessons with the name "Chemistry Lesson at Bedok", "Biology Lesson at Tai Seng" and "Lesson".
    * There is an existing lesson on 2023/12/12 from 13:00 to 15:00.
    * There are more than 2 lessons in the displayed list of lessons. <br><br>

2. Test case: `editLesson 1 -name Chemistry Lesson at Bedok` <br>
   Expected: The name of the first lesson in the displayed list of lessons is edited to "Chemistry Lesson at Bedok". Details of the edited lesson is shown in the response box.

3. Test case: `edit 2 -name Biology Lesson at Tai Seng -start 10:00 -end 12:00` (in ___SCHEDULE list___) <br>
   Expected: The name and time of the second lesson in the displayed list of lessons is edited to "Biology Lesson at Tai Seng" and "10:00AM" to "12:00PM" respectively. Details of the edited lesson is shown in the response box.

4. Test case: `editLesson 1 -name Lesson -start 12:00 -end 14:00 -day 2023/12/12` <br>
   Expected: No lesson is edited. Error indicating a clash detected is shown in the response box.

### Find Feature

##### Finding a student by name in ___STUDENTS list___

1. Prerequisites:
    * List all students using the `list students` command.
    * There are currently four students with names "Alex Wong", "Alex Yeoh", "Willy Wonka" and "Wong Max". <br><br>
   
2. Test case: `find Alex` <br>
   Expected: Only students with the name "Alex Wong" and "Alex Yeoh" are shown in the list panel. A message indicating the number of students listed is shown in the response box.

3.  Test case: `find alex` <br>
    Expected: Only students with the name "Alex Wong" and "Alex Yeoh" are shown in the list panel. A message indicating the number of students listed is shown in the response box.

4. Test case: `find won` <br>
   Expected: Only students with the name "Alex Wong", "Willy Wonka"  and "Wong Max" are shown in the list panel. A message indicating the number of students listed is shown in the response box.

5. Test case: `find xyz` <br>
   Expected: No students are shown. A message indicating 0 persons listed is shown in the response box.

##### Finding a lesson by name in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * There are currently three lessons with names "Chemistry Lesson at bedok", "lesson chem at kovan" and "bedok eng". <br><br>

2. Test case: `find chem` <br>
   Expected: Only lessons with the name "Chemistry Lesson at bedok" and "lesson chem at kovan" are shown in the list panel. A message indicating the number of lessons listed is shown in the response box.

3. Test case: `find bedok` <br>
   Expected: Only students with the name "Chemistry Lesson at bedok" and "bedok eng" are shown in the list panel. A message indicating the number of lessons listed is shown in the response box.

4. Test case: `find` <br>
   Expected: List panel remains the same. Error indicating invalid command format is shown in the response box.


### Filter Feature

##### Filtering a student in ___STUDENTS list___

1. Prerequisites:
    * List all students using the `list students` command.
    * There are currently four students with "ENGLISH" as subjects. 
    * There are currently three students with "new" tag. 
    * There are currently two students with "yet to pay" remark. <br><br>

2. Test case: `filter -subject english` <br>
   Expected: Four students are shown in the list panel. A message indicating filtered student list successfully is shown in the response box.

3. Test case: `filter -tag new` <br>
   Expected: Three students are shown in the list panel. A message indicating filtered student list successfully is shown in the response box.

4. Test case: `filter -remark yet to pay` <br>
   Expected: Two students are shown in the list panel. A message indicating filtered student list successfully is shown in the response box.

5. Test case: `filter -subject bio` <br>
   Expected: List panel remains the same. Error indicating invalid filter format and subject constraints is shown in the response box.


##### Filtering a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * There are currently four lessons with "PHYSICS" as subjects.
    * There are currently two lessons on 12/12/2023, one lesson on 13/12/2023, three lessons on 14/12/2023. 
    * There are currently two lessons with no date specified. <br><br>

2. Test case: `filter -subject physics` <br>
   Expected: Four lessons are shown in the list panel. A message indicating filtered schedule list successfully is shown in the response box.

3. Test case: `filter -on 2023/12/12` <br>
   Expected: Two lessons are shown in the list panel. A message indicating filtered schedule list successfully is shown in the response box.

4. Test case: `filter -after 2023/12/13` <br>
   Expected: Five lessons are shown in the list panel (three lessons on 14/12/2023 & two lessons with no date specified). A message indicating filtered schedule list successfully is shown in the response box.

5. Test case: `filter -before 2023/10/10` <br>
   Expected: No lessons are shown in the list panel. A message indicating filtered schedule list successfully is shown in the response box.

### Link Feature

##### Linking a lesson to a student in ___STUDENTS list___

1. Prerequisites:
    * List all students using the `list students` command.
    * The first student in the list has the name "Alex Yeoh" with one lesson named "Bedok Lesson" linked to him.
    * There are currently three lessons with names "Chemistry Lesson at Bedok", "Biology Lesson at Bedok" and "Bedok Lesson".
<br><br>

2. Test case: `show 1` followed by `linkTo Chemistry Lesson at Bedok` <br>
   Expected: Lesson with the name "Chemistry Lesson at Bedok" is linked to the currently shown student ("Alex Yeoh") in the details panel. A message indicating student linked to lesson is shown in the response box.

3. Test case: `link -student alex yeoh -lesson biology lesson at bedok` <br>
   Expected: Student "Alex Yeoh" is linked to the lesson with the name "Biology Lesson at Bedok". A message indicating student linked to lesson is shown in the response box.

4. Test case: `show 1` followed by `linkTo bedok` <br>
   Expected: No lesson is added to the currently shown student in the details panel. Error indicating no such lesson is shown in the response box.

5. Test case: `show 1` followed by `linkTo` <br>
   Expected: No lesson is added to any student. Error indicating name should be alphanumeric and `linkTo` command usage is shown in the response box.


##### Linking a student to a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * The first lesson in the list has the name "Bedok Lesson" with one student named "Alex Wong" linked to it.
    * There are currently four students with names "Alex Wong", "Alex Yeoh", "Willy Wonka" and "Willy Max".
    * Only "Alex Wong" has a lesson linked to him. All other students have no lessons linked to them. <br><br>

2. Test case: `show 1` followed by `linkTo Alex Yeoh` <br>
   Expected: Student "Alex Yeoh" is linked to the currently shown lesson in the details panel. A message indicating student linked to lesson is shown in the response box.

3. Test case: `link -student willy max -lesson bedok lesson` <br>
   Expected: Student "Willy Max" is linked to the lesson with the name "Bedok Lesson". A message indicating student linked to lesson is shown in the response box.

4. Test case: `show 1` followed by `linkTo willy` <br>
   Expected: No student is added to the currently shown lesson in the details panel. Error indicating no student with name "willy" found is shown in the response box.

5. Test case: `show 1` followed by `linkTo alex wong` <br>
   Expected: No student is added to the currently shown lesson in the details panel. A message indicating student is already linked to lesson is shown in the response box.

<div style="page-break-after: always;"></div>

### Navigate Feature

##### Navigating to lessons linked to a student in ___STUDENTS list___

1. Prerequisites:
    * List all students using the `list students` command.
    * The first student in the list is named "Alex Yeoh" with three lessons linked to him.
    * The second student in the list is named "Willy Wonka" with no lessons linked to him.<br><br>

2. Test case: `show 1` followed by `nav` <br>
   Expected: Three lessons are shown in the list panel. A message indicating navigated to student's lesson is shown in the response box.

3. Test case: `show 2` followed by `nav` <br>
   Expected: List panel remains the same. Error indicating no lessons linked to student is shown in the response box.


##### Navigating to students linked to a lesson in ___SCHEDULE list___

1. Prerequisites:
    * List all lessons using the `list` command.
    * The first lesson in the list is named "Lesson at Bedok" with two students linked to it.
    * The second lesson in the list is named "Lesson at Punggol" with no student linked to it.<br><br>

2. Test case: `show 1` followed by `nav` <br>
   Expected: Two students are shown in the list panel. A message indicating navigated to lesson's students is shown in the response box.

3. Test case: `show 2` followed by `nav` <br>
   Expected: List panel remains the same. Error indicating the lesson has no linked students is shown in the response box.


### Command History Feature

1. Prerequisites:
    * Re-launch the application.
    * `list students`, `list schedule`, `list tasks` were entered in this order into the command box.<br><br>

2. Test case: (Steps 1 to 5 below are performed sequentially.)
    * Step 1. Press the arrow up button on keyboard. <br>
      Expected: `list tasks` is shown in the command box.
    * Step 2. Press the arrow up button on keyboard again.<br>
      Expected: `list schedule` is shown in the command box.
    * Step 3. Press the arrow up button on keyboard again.<br>
      Expected: `list students` is shown in the command box.
    * Step 4. Press the arrow up button on keyboard again.<br>
      Expected: `list students` remains in the command box.
    * Step 5. Press the arrow down button on keyboard.<br>
      Expected: `list schedule` is shown in the command box.

### Clear

1. Prerequisites:
    * There are more than 1 student, 1 lesson and 1 task in the application.<br><br>
   
2. Test case: `clear` <br>
   Expected: The list panel becomes empty. A message indicating student, schedule and task list is cleared is shown in the response box.

### Exit   

1. Prerequisites:
    * Run the application.<br><br>

2. Test case: `exit` <br>
   Expected: The application exits.


### Saving data

1. Dealing with missing/corrupted data files
   1. If a missing file is detected, the system will automatically create the missing .json file and fill up with sample data loaded from `SampleDataUtil.java` after user adds data into the application.
   2. If a corrupted file is detected (i.e. missing any key properties), the system will delete the corrupted file, and replace it with a new empty file.

2. Test cases
   1. Prerequisites: There are newly added students, lessons and tasks in the application. There are linked lessons and students.
   2. Test case: Shut down the application and delete `addressbook.json`, `personLessonMap.json` and `schedulelist.json` in the data folder.
      Expected: Upon the next application start and login, new `addressbook.json`, `personLessonMap.json` and `schedulelist.json` files are created.
   3. Test case: Close the application and edit `addressbook.json` by changing the name of the first student to Leah_Loh.
      Expected: Upon the next application start and login, a new `addressbook.json` file is created and replaces the `addressbook.json` corrupted file. 
   4. Test case: Close the application and edit `schedulelist.json` by deleting the start time of the first lesson.
      Expected: Upon the next application start and login, a new `schedulelist.json` file is created and delete the `addressbook.json` corrupted file.


## **Appendix: Planned Enhancements**
### Deleting of tags
- **Relevant feature:** editing of students `editPerson`
- **Current issues:** Once a tag is added to a student, the `editPerson` command prevents you from deleting the tag. This is because by specifying the flag `-tag`, the parameter provided as the tag must be a non-empty string.
- **Proposed new behaviour:** The `-tag` flag can be made to accept an empty string. 
  - Input: `editPerson 1 -tag`
  - Expected output: The person at index 1 should have no more tags set.

### Unlinking of students from lessons and vice versa
- **Relevant feature:** linking `linkTo`, `link`
- **Current issues:** Once a student has been linked to a lesson, there is no way to unlink them.
- **Proposed new behaviour:** A new `unlinkFrom` and `unlink` command that mirrors `linkTo` and `link`, which will unlink the specified lesson and student if it detects that they have already been linked.
  - Input: `linkTo Bernice Yu`
  - Expected output: Bernice Yu is linked to the current lesson
  - Input: `unlinkFrom Bernice Yu`
  - Expected output: Bernice Yu is unlinked from the current lesson.

### Navigating from tasks to lesson
- **Relevant feature:** Task Lists `list tasks`
- **Current issues:** There's no way to see what lesson a task is under.
- **Proposed new behaviour:** The `nav` command should also work when viewing a task.
  - Input: `nav`
  - Expected output: The screen changes to the lesson that the task is assigned to.

