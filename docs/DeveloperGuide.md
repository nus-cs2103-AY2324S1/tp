---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# ProjectPRO Developer Guide

<!-- * Table of Contents -->
<page-nav-print />
--------------------------------------------------------------------------------------------------------------------
## **1. Introduction**

### **1.1. Product Overview**
ProjectPro is a contact organisation application designed for university students.

University students often spend a lot of time coordinating project meetup sessions and finding project information, resulting in large amounts of time wasted.

This app can help to save time by:
* listing available time slots of individuals in a team.
* Finding common time slots for meetings.
* Saving extra information regarding a project.

--------------------------------------------------------------------------------------------------------------------

### **1.2. Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

### **1.3. Acknowledgements**

ProjectPro is a brownfield Java Project based on the AB3 project template created by the [SE-EDU initiative](https://se-education.org).


--------------------------------------------------------------------------------------------------------------------

## **2. Design**

### 2.1. Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**2.1.1. Main components of the architecture**

**`Main`** has two main classes called [`Main`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/MainApp.java)). It is responsible for app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.



The rest of the App consists of these main four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

On the other hand, [**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**2.1.2. How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete n/Alex`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### 2.2. UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### 2.3. Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete n/Alex")` API call as an example.

<puml src="diagrams/DeletePersonSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete n/Alex` Command" />

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

### 2.4. Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


<box type="info" seamless>

**Note:** A more in depth model is given below. It has a `Group` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Group` object per unique tag, instead of each `Person` needing their own `Group` objects.<br>
</box>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />


### 2.5. Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T10-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

With the inclusion of `Group` and `TimeIntervals` in our application, more components had to be saved by our storage.

To address this requirement, we updated our storage to save both classes in JSON format.

### 2.6. Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **3. Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Adding a Person

#### Implementation

The `add` feature is facilitated by a number of classes such as `Person` and `Model`

Step 1. The user launches the application for the first time.

Step 2. The user executes `“add n/John Doe p/98765432 e/johnd@example.com g/CS2103T”` command to add a new person. `LogicManager#execute` is called which then calls `AddressBookParser#parseCommand` to decide on the type of command. `AddressBookParse`r` then calls `AddCommandParser`,

Step 3, The `AddCommandParser` is called to read the user input. `AddCommandParser` calls `ArgumentTokenizer#tokenize` to check the prefixes of the user input. `AddCommandParser` then calls `ArgumentMultimap#getValue()` to get inputs after each prefix.
The result of it is then passed to `ParserUtil#parse` methods to parse each attributes such as `Name`. `AddCommandParser` then makes new person object. `AddCommandParser` then calls `AddCommand` and passes `Person` inside.

Step.4 `AddCommand` then calls `Model#addPerson()` which then calls `AddressBook#addPerson()`. The latter method will add person inside the `uniquePersonList` in `addressBook`. `AddCommand` also calls `Model#addGroup` which then calls `AddressBook#addGroup` to add the group inside `grouplist` if the group does not exist.
Lastly, `AddCommand` adds the person inside the group

Note: No duplication is allowed in addressbook for most of Person’s attribute (name, email and phone number.)

The following sequence diagram describes the process of `add` command:
<puml src="diagrams/AddCommandSequenceDiagram.puml" alt="AddCommandSeqDiagram" />

#### Design consideration:

**Aspect: Handling group attribute in user input**

* **Alternative 1 (Current Choice):** Only allow user to add one group for each `add` Command
    * Pros: Conveniently adds a person into a single group while creating a new contact at the same time, relatively easier to implement parser. 
    * Cons: User input may get relatively longer.
* **Alternative 2:** Allow user to add as many groups as required for each `add` Command
    * Pros: Conveniently adds a person into multiple group while creating a new contact at the same time.
    * Cons: User input can get potentially very long, increasing the chance of invalid input, relatively harder to implement parser.

--------------------------------------------------------------------------------------------------------------------
### Adding Time a Person

#### Implementation

The `addtime` feature is facilitated by a number of classes such as `Person`, `Model` and `TimeInterval`

Step 1. User launches the application.

Step 2. The user executes `“addtime n/Alex Yeoh t/mon 1200 - mon 1400 t/tue 1000 - wed 1600”` command to add time slots to the person, Alex Yeoh. `LogicManager#execute` is called which then calls `AddressBookParser#parseCommand` to decide on the type of command. `AddressBookParser` then calls `AddTimeCommandParser`,

Step 3, The `AddTimeCommandParser` is called to read the user input. `AddTimeCommandParser` calls `ArgumentTokenizer#tokenize` to check the prefixes of the user input. `AddTimeCommandParser` then calls `ArgumentMultimap#getValue()` to get inputs after each unique single prefix and `ArgumentMultimap#getAllValues()` to get inputs from prefix that are used more than once.
The result of it is then passed to `ParserUtil#parse()` methods to parse each attributes such as `Name`. `AddTimeCommandParser` then calls `AddTimeCommand`.

Step.4 `AddTimeCommand` then calls `Model#addTimeToPerson()` which then calls `AddressBook#addTimeToPerson()`. The latter method will add time to the person.

The following sequence diagram describes the process of `addtime` command:
<puml src="diagrams/AddTimeCommandSequenceDiagram.puml" alt="AddCommandSeqDiagram" />

#### Design consideration:

**Aspect: Handling group attribute in user input**

* **Alternative 1 (Current Choice):** Allows user to add more than one time intervals in each `addtime` command.
  * Pros: Conveniently adds a multiple time intervals into person. 
  * Cons: User input may get relatively longer, relatively harder to implement parser.
* **Alternative 2:** Allow user to only add single time interval in each `addtime` Command
  * Pros: Conveniently adds a person into group while creating a new contact at the same time, relatively easier to implement parser.
  * Cons: User input can get potentially very long, increasing the chance of invalid input.

--------------------------------------------------------------------------------------------------------------------

### Adding a Group

#### Proposed Implementation

The Add Group mechanism is facilitated by `Group`. It is stored internally as a `Group`. This operation is exposed in the `Model` interface as `Model#addGroup()`.

Given below is an example usage scenario and how the group creation mechanism behaves at each step.

**Step 1:** User launches the application.

**Step 2:** The user executes `new g/GROUPNAME` to create a new group with the name GROUPNAME. `CreateGroupCommandParser` parses the GROUPNAME, ensuring the input is valid, and creates a `CreateGroupCommand`, which calls `Model#addGroup()`. The model retrieves the existing groupList from the addressBook and adds this new group to the groupList.

The following activity diagram summarizes what happens when a user executes a new command:
<puml src="diagrams/CreateGroupActivityDiagram.puml" alt="CreateGroupActivityDiagram"/>

Below is a sequence diagram that summarizes how a user creates a new group:
<puml src="diagrams/CreateGroupSequenceDiagram.puml" alt="CreateGroupSequenceDiagram"/>

#### Design Considerations

**Aspect: Groups with the same name**

* **Alternative 1 (current choice):** Group names are Unique
    * Pros: Allow users to create groups with the same name
    * Cons: User might have to be creative with naming groups

* **Alternative 2:** Group names are not unique but tagged with an id
    * Pros: Users can reuse commonly used group names
    * Cons: Users may get confused as to what each group is meant for

### Delete feature

#### Implementation

The proposed delete person/group mechanism is facilitated by `AddressBook`. It implements the following operations:

* `AddressBook#removePerson(Person p)` — Removes Person p from the address book.
* `AddressBook#removeGroup(Group g)` — Remove Group g from the address book.

These operations are exposed in the `Model` interface as `Model#deletePerson()`, `Model#deleteGroup()` respectively.

##### Delete Person

Given below is an example usage scenario and how the Delete Person mechanism behaves at each step.

Step 1. The user executes `delete n/Alex Yeoh` command to delete a person named 'Alex Yeoh' in the address book. After parsing, a new `DeletePersonCommand` object will be returned.

Step 2. `DeletePersonCommand` is executed, in which `Model#deletePerson()` is called, removing the Person with name 'Alex Yeoh' from the address book while returning the `Person` object.

<box type="info" seamless>

**Note:** If no such person named 'Alex Yeoh' exists, a `CommandException` will be thrown.

</box>

Step 3. `Person#getGroups()` is called to obtain a `GroupList` of groups the target person is part of.

Step 4. The `GroupList` is converted into a stream, where each element is a `Group`. `Group#removePerson()` is called for each Group in the stream, removing the target Person from all `ObserverableList<Person> listOfGroupMates` in `Group`.

The following sequence diagram shows how the Delete Person operation works:

<puml src="diagrams/DeletePersonSequenceDiagram.puml" alt="DeletePersonSequence" />

<box type="info" seamless>

**Note:** The lifeline for `DeletePersonCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

##### Delete Group

Given below is an example usage scenario and how the Delete Group mechanism behaves at each step.

Step 1. The user executes `delete g/CS2100` command to delete a group named 'CS2100' in the address book. After parsing, a new `DeleteGroupCommand` object will be returned.

Step 2. `DeleteGroupCommand` is executed, in which `Model#deleteGroup()` is called, removing the `Group` with name 'CS2100' (the target group) from the address book while returning the `Group` object.

<box type="info" seamless>

**Note:** If no such group named 'CS2100' exists, a `CommandException` will be thrown.

</box>

Step 3. `Group#getListOfGroupMates()` is called to obtain a `ObservableList<Person>` of Persons that are a part of the target group.

Step 4. The `ObservableList<Person>` is converted into a stream, where each element is a `Person`. `Person#removeGroup()` is called for each Person in the stream, removing the target Group from all `GroupList groups` in `Person`.

Step 5. `DeleteGroupCommand` creates a new `CommandResult` with the corresponding message, and returns the result to the `LogicManager`.

The following sequence diagram shows how the Delete Group operation works:

<puml src="diagrams/DeleteGroupSequenceDiagram.puml" alt="DeleteGroupSequence" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteGroupCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>



### Group Remark Feature

#### Implementation

The proposed group remark feature is facilitated by the `Group` class. It includes a `Group Remark` field and implements the `Group#setGroupRemark()` operation. This feature is exposed in the `Model` interface as `Model#addGroupRemark()`.

Here's an example usage scenario and how the group remark mechanism behaves at each step:

**Step 1.** The user creates a group called `CS2103T`. The `Group` is initialized with an empty `groupRemark`.

**Step 2.** The user executes the `remark g/CS2103T r/Quiz tomorrow` command to add the remark "Quiz tomorrow" to the `CS2103T` group. The `GroupRemarkCommandParser` extracts the group and remark from the input and creates a `GroupRemarkCommand`, which calls `Model#addGroupRemark(groupName, groupRemark)`. The model retrieves the existing `CS2103T` group from the database and calls the group's `Group#setRemark(groupRemark)`, adding the `groupRemark` to the group.

**Note:** If the user wants to modify the group remark, they can execute the same command with the new remark. The existing remark will be deleted and overwritten, and the new remark is stored in the group.

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/GroupRemarkSequenceDiagram.puml" alt="GroupRemarkSequenceDiagram" />

#### Design Considerations

**Aspects:**

- **Alternative 1 (current choice):** Overrides original remark
    - Pros: Easy to implement.
    - Cons: May be troublesome if the user wants to keep contents from the original remark.
- **Alternative 2:** Edits original remark
    - Pros: Easy to add more information.
    - Cons: Could be confusing to edit if there are many changes.

### Delete Time Feature

#### Implementation

The proposed delete time feature is facilitated by the `timeIntervalList` and `Person` class. It accesses the `timeIntervalList` from the `Person` class and deletes a time interval with `Person#deleteFreeTime()`. The operation is exposed in the `Model` interface as `Model#deleteTimeFromPerson`.

Step 1. The user launches the application. The `AddressBook` will be initialized with the free time of its contacts.

Step 2. The user executes the command `deleteTime n/Alex Yeoh t/mon 1200 - mon 1400 ;tue 1000 - wed 1600`. The `deleteTimeCommandParser` will be called to parse the inputs and call the `deletePersonTimeCommand`. The `deletePersonTime` command calls `Model#deleteTimeFromPerson()`, which will call `Person#deleteFreeTime()`.

**Note:** Since multiple inputs are allowed, an array list of time intervals are passed around, each of which is to be deleted.

Step 3. The function will be called in the person's `timeInterval` list. The application will loop through each time interval that is to be deleted and in the person's `timeInterval` list. Each time interval will be compared to see whether the `timeIntervalList` contains the time interval to be deleted. Afterwards, the new `timeInterval` list will be saved.

**Note:** If a time interval is not in the person's list, that interval will be collated and printed to specifically notify the user which time intervals are not in the list. The other time intervals that are in the list will still be deleted.

Similarly, the group command does the same, except for the `Group` class.

The following sequence diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/DeletePersonTimeDiagram.puml" alt="DeletePersonTimeDiagram"/>

Below is an activity diagram that illustrates the control flow for Delete Person Time feature.

<puml src="diagrams/DeletePersonTimeActivityDiagram.puml" alt="DeletePersonTimeActivityDiagram"/>

#### Design Considerations

**Aspect: Error Messages**

* Alternative 1 (current choice): Print specific error messages.
  Pros: Allow users to understand which inputs went wrong.
  Cons: May have performance issues in terms of runtime as more functions will be used to craft the error message. Currently, we utilized a `StringBuilder` to craft the message and did extra checks to see whether there had been any errors appended to the error message.

* Alternative 2: Generalized error message.
  Pros: Will be faster to implement.
  Cons: User might be unsure why the function went wrong.

**Aspect: How to Handle Multiple Time Inputs**

* Alternative 1 (current choice): Parse each time input one by one and execute the commands.
  Pros: More user-friendly and efficient as users can delete more time intervals at once.
  Cons: More expensive as more functions will be called to parse the inputs.

* Alternative 2: Allow only single input.
  Pros: Faster as fewer functions are called.
  Cons: Not as user-friendly since users will have to delete time intervals one by one.

**Aspect: How to Handle Errors in Time Intervals**

* Alternative 1 (current choice): Delete the time intervals that are correct and return the intervals that are wrong.
  Pros: Better user experience as users need not rewrite intervals that were right.
  Cons: Increased memory usage to store the errors.

* Alternative 2: Do not carry out the delete at all.
  Pros: More time and memory efficient.
  Cons: Not as user-friendly since users will have to delete time intervals that were originally correct, wasting their time.

--------------------------------------------------------------------------------------------------------------------

### Group Person

#### Proposed Implementation

The group remark mechanism is facilitated by `Group`. It is stored internally as a `Group Remark`. This operation is exposed in the `Model` interface as `Model#groupPerson(personName, groupName)`.

Given below is an example usage scenario and how the group remark mechanism behaves at each step.

**Step 1:** User launches the application.

**Step 2:** The user executes `group n/personName g/groupName` to group a person `personName` into group `groupName`. `GroupPersonCommandParser` parses the personName and groupName ensuring the input is valid and creates a `GroupPersonCommand`, which calls `Model#groupPerson(personName, groupName)`. The model retrieves the existing person and group from the addressBook. Should a person or group not exist, it throws an error. Model calls `Model#assignGroup(Person person, Group group)` which adds a group to a person's groupList and person to the personList in group.

The following activity diagram summarizes what happens when a user executes a new command:

#### Design Considerations:

**Aspect: Whether to store references in both person and group**

* **Alternative 1 (current choice):** Store references in both person and group
    * Pros: Easy to implement. More efficient when searching.
    * Cons: More bug-prone. May have object referencing and loading from storage issues as both the person reference to group, and group reference to person has to be loaded properly.

* **Alternative 2:** Store reference to the other entity, e.g. store a list of groups in person.
    * Pros: Easier to load from storage. One centralized place to store data. Less coupling.
    * Cons: Searching might become more costly.

* --------------------------------------------------------------------------------------------------------------------
## 4. Planned Enhancements

### 4.1. Undo/redo feature

Currently, we do not have undo and redo feature which open rooms for users to accidentally have typos in their command messages, causing certain unwanted commands to be executed. This can cause an array of problems,
from deleting important data along with the contact, to inconvenience for the users themselves. As such, we plan to implement undo and redo feature in the future to provide a better user experience for our users.

### 4.2. `addtime`/`deletetime` feature

Currently, our add and delete time features allow users to input multiple time slots in the command box. Should a certain input encounter an error (clashing time intervals), ProjectPRO adds / deletes the other time intervals that are valid and clears the command box. We recognise that users might encounter accidental typos while typing time intervals. Hence, we plan to disallow the clearing command box when the user typed an invalid input.

### 4.3. Improving our user interface.

Currently, ProjectPRO's output box height is relative short which could lead to user frustration and hindered efficiency. Users may find it challenging to quickly interpret and comprehend the output, especially when certain commands generate outputs with numerous lines. This limitation might result in a suboptimal user experience, making it difficult for users to extract the desired information promptly.Addressing this problem is crucial to ensuring a more user-friendly interface and improving the overall effectiveness of ProjectPRO for its users. Our future implementation involves optimizing the output display by increasing the height of the output box. This enhancement aims to provide users with a more comprehensive and visually accessible view, allowing them to easily grasp the content at a glance.

### 4.4. Allowing extensive modification to command words.

As ProjectPro is a CLI text-based application, it requires extensive typing which might prove troublesome at times. As a result, our users would naturally have their personal preferences regarding the command word inputs.
Enabling users to customize input requirements for functions within ProjectPRO would significantly enhance the flexibility and adaptability of the system. This future implementation empowers users by allowing them to tailor input parameters based on their specific needs and preferences. By accommodating a range of input variations, users can streamline their workflows and optimize the tool to align with diverse use cases.

### 4.5 Improving `addmeeting` feature.

In the `addmeeting` feature, user can add free time intervals to a group. Currently, user can add same free time intervals for 2 separate groups or overlapping time intervals between 2 or more groups, causing a clash in their schedule. (Eg. `Group A: Mon 1200 - Mon 1400`; `Group B: Mon 1300 - Mon 1500`). These clashes are not detected and will allow this state to exist. We plan to not allow this, so user cannot insert time intervals that are overlapping with other groups. Eg. `Group A: Mon 1200 - Mon 1400`; `Group B: Mon 1300 - Mon 1500` will not be allowed.

--------------------------------------------------------------------------------------------------------------------



## **5. Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **6. Appendix: Requirements**

### 6.1. Product scope

**Target user profile**:

* University students often spend a lot of time coordinating project meetup
* sessions and waiting for replies and they are not aware of one another’s schedules.
* This app can help to save time by listing available time slots of individuals in a team.


**Value proposition**: Text-friendly project management tool that helps students schedule meetings with different groups while also keeping track of tasks and
responsibilities of each member. Our app will track the schedule of each contact and tasks individuals have to do for their project.


### 6.2. User stories

| Priority | As a ...                                    | I want to ...                | So that I can ...                                                      |
|----------|--------------------------------------------|------------------------------|------------------------------------------------------------------------|
| `***`    | student                                    | add a new contact            | keep track of any new contacts                                         |
| `***`    | student with many contacts                 | organize contacts into groups | easily keep track and manage my contacts                               |
| `***`    | student with many team members             | record team members' info     | keep track of my team members' contact information                     |
| `***`    | student with many projects                 | delete a group               | avoid clutter and unnecessary attention to completed projects          |
| `***`    | student with many contacts                 | search for group members     | quickly access contact details using name, contact number              |
| `***`    | user                                       | save entered information      | avoid repetitive data entry                                            |
| `**`     | team leader                                | add tasks to contacts        | remember who is responsible for which task                             |
| `**`     | student                                    | filter contacts by project   | easily view tasks for a specific project group                         |
| `**`     | user                                       | prioritize tasks             | work on important tasks first                                          |
| `**`     | student                                    | add time slots of group mates| find a suitable meeting time when everyone is available                |
| `**`     | new user                                   | access a help command        | quickly learn about application functions without reading a long guide |
| `*`      | impatient user                             | access the user guide        | quickly learn how to use the application                               |
| `*`      | technology-challenged student              | read the user guide          | gain a better understanding of how to use the application              |
| `*`      | fast but inaccurate typer                  | undo a previous command      | correct typing mistakes                                                |
| `*`      | forgetful student                          | add a reminder               | ensure attendance at upcoming project meetings                         |
| `*`      | student with many projects                 | color code projects          | differentiate between various project groups                           |
| `*`      | lazy user                                  | minimize typing/clicking     | achieve tasks with minimal effort                                      |
| `*`      | student with an irregular schedule         | edit contact information     | easily manage changes in contact details                               |
| `*`      | user                                       | filter contacts by courses   | view contacts based on shared courses or projects                      |
| `*`      | user                                       | upload attachments/files     | improve collaboration and reference for tasks and projects             |
| `*`      | user                                       | view contact profiles        | access course schedules, contact details, and profile pictures         |

### 6.3. Use cases

(For all use cases below, the **System** is `ProjectPRO` and the **Actor** is the `user`, unless specified otherwise)

**6.3.1. Use case 1: Creating contact**

**MSS**

1. User requests to add contact.
2. ProjectPRO adds new contact.
3. ProjectPRO informs user contact has been successfully added.

   Use Case ends.

**Extensions:**
* 1a.  ProjectPRO detects error in input
    * 1a1. ProjectPRO displays an error message.
    * Use case ends.

* 1b. ProjectPRO detects duplicate contact requested by user.
    * 1b1. ProjectPRO displays an error message.
    * Use case ends.

**6.3.2. Use case 2: Delete contact**

**MSS**

1. User requests to delete contact
2. ProjectPRO deletes contact.
3. ProjectPRO informs user contact has been successfully deleted.

   Use Case ends

**Extensions**
* 1a. Contact details is incorrect.
    * 1a1. ProjectPRO displays an error message.
    * Use case ends.


**6.3.3. Use case 3: Finding a contact**

**MSS**

1. User requests to find a contact.
2. ProjectPRO searches for the contact.
3. ProjectPRO displays the contact details.

Use Case ends.

**Extensions**

* 2a. If the contact is not found.
    * 2a1. ProjectPRO displays an empty list.
    * Use case ends.

**6.3.4. Use case 4: Listing all contacts**

**MSS**

1. User requests to list all contacts.
2. ProjectPRO displays the list of contacts.

Use Case ends.

**6.3.5. Use case 5: Creating a group**

**MSS**

1. User requests to create a group.
2. ProjectPRO creates the group.
3. ProjectPRO displays a success message.

Use Case ends.

**Extensions**

* 1a. If the user provides incomplete or incorrect information.
    * 1a1. ProjectPRO displays an error message.
    * Use case repeats from step 1. 

**6.3.6. Use case 6: Deleting a group**

**MSS**

1. User requests to delete a group.
2. ProjectPRO deletes the group.
3. ProjectPRO displays a success message.

Use Case ends.

**Extensions**

* 1a. If the group does not exist.
    * 1a1. ProjectPRO displays an error message.
    * Use case ends.


**6.3.7. Use case 7: Adding a group remark**

**MSS**

1. User requests to add a remark to a group.
2. ProjectPRO adds the remark to the group.
3. ProjectPRO displays a success message.

**Extensions**

* 1a. If the group does not exist.
  * 1a1. ProjectPRO displays an error message.
  * Use case ends.
  
Use Case ends.

**6.3.8. Use case 8: Finding a group**

**MSS**
1. User requests to find a group.
2. ProjectPRO searches for the contact. 
3. ProjectPRO shows all the contacts from the group and the group remark.

Use Case ends.

**Extensions**

* 2a. If the group does not exist.
    * 2a1. ProjectPRO displays a message indicating that the group does not exist.
    * Use case ends.

**6.3.9. Use case 9: Listing all groups**

**MSS**

1. User requests to list all groups.
2. ProjectPRO displays the list of groups.

Use Case ends.

**6.3.10. Use case 10: Add contact to group**

**MSS**
1. User requests to add a contact to a group.
2. ProjectPRO adds the contact to the group.
3. ProjectPRO displays a success message.

Use Case ends.

**Extensions**

* 1a. If the group does not exist.
  * 1a1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

* 1b. If the contact does not exist.
  * 1b1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

**6.3.11. Use case 11: Removing contact from group**

**MSS**

1. User requests to remove a contact from a group.
2. ProjectPRO removes the contact from the group.
3. ProjectPRO displays a success message.
Use Case ends.

**Extensions**

* 1a. If the group does not exist.
  * 1a1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

* 1b. If the contact does not exist.
  * 1bProjectPRO displays an error message.
  * Use case repeats from step 1.

**6.3.12. Use case 12: Adding free time to contact**

**MSS**

1. User requests to add free time to a contact.
2. ProjectPRO adds the free time to the contact.
3. ProjectPRO displays a success message.

Use Case ends.

**Extensions**

* 1a. If the contact does not exist.
  * 1a1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

* 1b. If the time input does not follow the format.
  * 1b1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

**6.3.13. Use case 13: Removing free time from contact**

**MSS**

1. User requests to remove free time from a contact.
2. ProjectPRO removes the selected free time from the contact.
3. ProjectPRO displays a success message.
Use Case ends.

**Extensions**

* 1a. If the contact does not exist.
  * 1a1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

* 1b. If the time input does not follow the format.
  * 1b1. ProjectPRO displays an error message.
  * Use case repeats from step 1.

**6.3.14. Use case 14: Listing free time of contact**

**MSS**

1. User requests to list the free time of a contact.
2. ProjectPRO displays the list of free time.
Use Case ends.

**Extensions**

* 2a. If the contact has no free time.
  * 2a1. ProjectPRO displays nothing.
  * Use case repeats from step 1.

**6.3.15. Use case 15: Adding meeting time to group**

**MSS**

1. User requests to add meeting time to a group.
2. ProjectPRO adds the meeting time to the group. 
3. ProjectPRO displays a success message.
Use Case ends.

**Extensions**

* 1a. If the user provides incorrect information or the group does not exist.
    * 1a1. ProjectPRO displays an error message.
    * Use case repeats from step 2.

**6.3.16. Use case 16: Remove meeting time from group**

**MSS**

1. User requests to remove meeting time from a group.
2. ProjectPRO removes the selected meeting time from the group.
3. ProjectPRO displays a success message.
Use Case ends.

**Extensions**

* 1a. If the group does not exist.
    * 2a1. ProjectPRO displays an error message.
    * Use case repeats from step 1.
* 1b. If the selected meeting time does not exist for the group.
  * 1b. ProjectPRO displays an error message.
  * Use case repeats from step 1.
* 1c. If the meeting time does not follow the required format.
  * 1b. ProjectPRO displays an error message.
  * Use case repeats from step 1.


**6.3.17. Use case 17: Listing meeting time of group**

**MSS**

1. User requests to list the meeting time of a group.
2. ProjectPRO retrieves the meeting time details for the group.
3. ProjectPRO displays the list of meeting time.
Use Case ends.

**6.3.18. Use case 18: Find free time of group**

**MSS**

1. User requests to find common free time for a group.
2. ProjectPRO identifies common free time slots between all members of the group.
3. ProjectPRO displays the list of common free time slots.
Use Case ends.

**Extensions**

* 2a. If there is no common free time the length of the duration.
  * 2a1. ProjectPRO displays an empty list.
  * Use case repeats from step 1.

### 6.4. Non-Functional Requirements

1. Compatibility: Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Performance: Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. Usability: A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Usability: Should be able to save the state of the user’s actions.
5. Performance: Should be able to handle an increasing number of contacts and events without a significant degradation in performance.
6. Usability: Data loss or corruption should not occur, even in the event of unexpected crashes or system failures.
7. Accessibility: The system shall be operable even without internet connection.

### 6.5. Glossary

| Parameter | Description                      | Constraints                                                                                                                                             | Valid Examples                           | Invalid Examples                                |
|-----------|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------|-------------------------------------------------|
| `n/`      | Contact name of the student      | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                        | John Doe, David Li 2                     | Kishen s/o Kasinathan, ナルト, அசிங்கமான           |
| `p/`      | Phone number of the student      | Positive integer with 3 or more digits                                                                                                                  | 999, 98765432, 18003569377               | 1-800-356-9377, 0, -1, 98431234.5               |
| `e/`      | Email of the student             | Email prefix: Alphanumeric characters (a to z, A to Z, 0 to 9), @, Email Domain                                                                         | example@gmail.com, example@moe.edu.sg    | example@!.com, example@moed.edu.s               |
| `g/`      | Name of the group                | Alphanumeric characters (a to z, A to Z, 0 to 9)                                                                                                        | CS2103T, Group 3                         | Group 3!, 1                                     |
| `r/`      | Group remark                     | N/A                                                                                                                                                     | Zoom link: CS2101.zoom, 123!@#$#@        | N/A                                             |
| `t/`      | Time interval of student / group | timings are written with the first 3 letters of the day and time in 24 hour format, with a `-` between the timings. Start time cannot be after end time | mon 1300 - mon 1400, sat 1000 - sun 1300 | monday 1300 - tuesday 1200, wed 1300 - wed 1000 |

--------------------------------------------------------------------------------------------------------------------

## **7. Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>


### 7.1. Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### 7.2. Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `delete n/Alex Yeoh`<br>
       Expected: Alex is deleted from the list. Details of the deleted person shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `delete n/Alex Yeoh`, after Alex has been deleted <br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete n/x`, `...` (where x is not in the list)<br>
       Expected: Similar to previous.

### 7.3. Finding a contact

1. Find a particular contact in your address book

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `find n/Alex Yeoh`<br>
       Expected: Only contacts with Alex is shown in the list. Number of perosns with that name is shown.

    3. Test case: `find n/Alex Yeoh`, after Alex has been searched <br>
      Expected: No change to the status message.

    4. Other incorrect find commands to try: `find n/`, `find n/x`, `...` (where x is not in the list)<br>
       Expected: Similar to previous.

### 7.4. Listing all contacts

1. Listing all contacts in your address book

    1. Prerequisites: Find a limited number of persons using the `find n/` command. No persons will be listed.

    2. Test case: `list`<br>
     Expected: All contacts will be shown.

    3. Other incorrect find commands to try: `list n/`, `list n/x`, `...`<br>
     Expected: Error message shown.

### 7.5. Creating a group

1. Creating a group that does not exist

    1. Test case: `new g/CS2103`<br>
     Expected: New group called CS2103 is added to ProjectPRO.

    2. Test case: `new g/CS2103`, after CS2103 has been created <br>
     Expected: No group is created. Error details shown in the status message. Status bar remains the same.

    3. Other incorrect delete commands to try: `new g/`, `new g/x`, `...` (where x is a group in the ProjectPRO)<br>
         Expected: Similar to previous.

### 7.6. Deleting a Group

1. Deleting a Group 

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   2. Test case: `delete g/CS2103`<br>
      Expected: CS2103 is deleted from the list. Details of the deleted group shown in the status message. Group tag under contacts in that group is removed.

   3. Test case: `delete g/CS2103`, after CS2103 has been deleted <br>
      Expected: No group is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete n/x`, `...` (where x is not in the list)<br>
      Expected: Similar to previous.

### 7.7. Adding a group remark

1. Adding a group remark 

   1. Test case: `remark g/CS2103 r/Lecture on friday`<br>
      Expected: New remark with "Lecture on friday" is added to CS2103.

   2. Test case: `remark g/CS2103 r/Lecture on thursday`, after CS2103 has a remark added <br>
      Expected: Old remark is replaced with new remark.

   3. Incorrect remark commands to try: `remark g/`, `remark g/x r/Lecture on wednesday`, `...` (where x is a group in the ProjectPRO)<br>
      Expected: Error message displayed.

### 7.8. Finding a group

1. Find a particular group in your address book

  1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

  2. Test case: `find g/CS2103`<br>
     Expected: Only contacts in the group is shown in the list. Group remark of the group is displayed.

  3. Test case: `find g/CS2103`, after CS2103 has been searched <br>
     Expected: No change to the status message.

  4. Other incorrect find commands to try: `find g/`, `...` <br>
     Expected: Error message displayed.

### 7.9. Listing all groups

1. Listing all groups in your address book

   1. Test case: `listgroup`<br>
        Expected: All groups will be shown in the contact box.

   2. Other incorrect find commands to try: `listgroup g/`, `listgroup cs2103`, `...`<br>
        Expected: Error message shown.

### 7.10. Adding contacts to groups

1. Adding a contact to a group

  1. Prerequisite: `list`<br>
     Expected: All contacts will be shown in address book.

  2. Test case: `group n/Alex Yeoh g/CS2103`<br>
     Expected: Alex is added to the group. A CS2103 group tag is added below his name.

  3. Incorrect group commands to try: `group n/ g/`, `group n/Alex Yeoh g/x`, `group n/x g/CS2103` (where x is an unadded contact / group) <br>
       Expected: Error message shown.

### 7.11. Removing a contact from a group

1. Ungrouping a contact

   1. Prerequisite: `list`<br>
      Expected: All contacts will be shown in address book.

   2. Test case: `ungroup n/Alex Yeoh g/CS2103`<br>
      Expected: Alex Yeoh's group tag is removed.

   3. Test case: `ungroup n/Alex Yeoh g/CS2103`, after Alex has been ungrouped <br>
      Expected: No change to the address book, error message shown in output box.

   4. Other incorrect find commands to try: `ungroup n/Alex Yeoh g/`, `ungroup n/ g/CS2103`, `...` <br>
      Expected: Error message displayed.

### 7.12. Adding free time to contact

1. Add free time to contact

   1. Prerequisite: `list`<br>
      Expected: All contacts will be shown in address book.

   2. Test case: `addtime n/Alex Yeoh t/mon 1300 - mon 1400`<br>
      Expected: Free time added to contact, contact has a time interval tag underneath his name.

   3. Test case: `addtime n/Alex Yeoh t/mon 1300 - mon 1400`, after previous command <br>
      Expected: No change to the address book, error message shown in output box stating there is a clash in timing.

   4. Other incorrect find commands to try: `addtime n/Alex Yeoh t/mon 1300 - mon 1200`, `addtime n/Alex Yeoh t/monday 1300 - monday 1400`, `...` <br>
      Expected: Command format error.

### 7.13. Deleting free time from contact

1. Delete free time to contact

  1. 
     2. Prerequisite: `list`<br>
          Expected: All contacts will be shown in address book.
     3. Prerequisite: `addtime n/Alex Yeoh t/mon 1300 - mon 1400`<br>
          Expected: Contact has a time interval tag beneath his name.

  2. Test case: `deletetime n/Alex Yeoh t/mon 1300 - mon 1400`<br>
     Expected: Free time deleted from contact, contact's time interval tag underneath his name is removed.

  3. Test case: `deletetime n/Alex Yeoh t/mon 1300 - mon 1400`, after previous command <br>
     Expected: No change to the address book, error message shown in output box stating time is not in his list.

  4. Other incorrect find commands to try: `deletetime n/Alex Yeoh t/mon 1300 - mon 1200`, `deletetime n/Alex Yeoh t/monday 1300 - monday 1400`, `...` <br>
     Expected: Command format error.

### 7.14. List free time of contact

1. Add free time to contact

   1.
      2. Prerequisite: `list`<br>
         Expected: All contacts will be shown in address book.
      3. Prerequisite: `addtime n/Alex Yeoh t/mon 1300 - mon 1400`<br>
         Expected: Contact has a time interval tag beneath his name.

  2. Test case: `listtime n/Alex Yeoh`<br>
     Expected: All time intervals under contact is listed in output box.

  3. Test case: `listtime n/Alex Yeoh`, after previous command <br>
     Expected: No change to the address book.

  4. Other incorrect find commands to try: `listtime n/Alex Yeoh t/mon 1300 - mon 1200`, `...` <br>
     Expected: Error message.

### 7.15. Adding meeting time to group

1. Add free time to contact

  1. Prerequisite: `list`<br>
     Expected: All contacts will be shown in address book.

  2. Test case: `addmeeting g/CS2103 t/mon 1300 - mon 1400`<br>
     Expected: Meeting time added to group, day card on the right has a time interval tag under monday.

  3. Test case: `addmeeting g/CS2103 t/mon 1300 - mon 1400`, after previous command <br>
     Expected: No change to the address book, error message shown in output box stating there is a clash in timing.

  4. Other incorrect find commands to try: `addmeeting g/CS2103 t/mon 1300 - mon 1200`, `addmeeting g/CS2103 t/monday 1300 - monday 1400`, `addmeeting g/x t/mon 1300 - mon 1400`, `...` (where x is not an existing group)<br>
     Expected: Command format error.

### 7.16. Deleting meeting time from group

1. Delete meeting time from group 

1.
  2. Prerequisite: `list`<br>
     Expected: All contacts will be shown in address book.
  3. Prerequisite: `addmeeting g/CS2103 t/mon 1300 - mon 1400`<br>
     Expected: Meeting time added to group, day card on the right has a time interval tag under monday.

2. Test case: `deletetime g/CS2103 t/mon 1300 - mon 1400`<br>
   Expected: Meeting time deleted from contact, time interval tag under the day card on the right is removed.

3. Test case: `deletetime g/CS2103 t/mon 1300 - mon 1400`, after previous command <br>
   Expected: No change to the address book, error message shown in output box stating time is not in the list.

4. Other incorrect find commands to try: `deletetime g/CS2103 t/mon 1300 - mon 1200`, `deletetime g/CS2103 t/monday 1300 - monday 1400`, `deletetime g/x t/mon 1300 - mon 1400`, `...` (where x is not an existing group)<br>
   Expected: Command format error.

### 7.17. List meeting time of group

1. Add free time to contact

   1.
       2. Prerequisite: `list`<br>
          Expected: All contacts will be shown in address book.
       3. Prerequisite: `addtime g/CS2103 t/mon 1300 - mon 1400`<br>
          Expected: Meeting time added to group, day card on the right has a time interval tag under monday.

2. Test case: `listtime g/CS2103`<br>
   Expected: All time intervals under group is listed in output box.

3. Test case: `listtime g/CS2103`, after previous command <br>
   Expected: No change to the address book.

4. Other incorrect find commands to try: `listtime g/CS2103 t/mon 1300 - mon 1200`, `...` <br>
   Expected: Error message.

### 7.17. Find free time of group
1. Add free time to contact

  1.
     2. Prerequisite: `list`<br>
      Expected: All contacts will be shown in address book.
     3. Prerequisite: `group n/Alex Yeoh g/CS2103`, `group n/Bernice Yu g/CS2103`<br>
      Expected: Both contacts are added to the group.
     4. Prerequisite: `addtime n/Alex Yeoh t/mon 1200 - mon 1300`, `addtime n/Bernice Yu t/mon 1200 - mon 1300`<br>
      Expected: Free time added to both contacts.

2. Test case: `findfreetime g/CS2103 d/60`<br>
   Expected: mon 1200 - mon 1300 is listed in output box

3. Test case: `findfreetime g/CS2103 d/1`, after previous command <br>
   Expected: No change to the address book.

4. Test case: `findfreetime g/CS2103 d/70`, after previous command <br>
   Expected: No available timeslots will be shown in output box.

4. Other incorrect find commands to try: `findfreetime g/CS2103 d/`, `findfreetime g/ d/70`, `findfreetime g/CS2103 d/0`,  `...` <br>
   Expected: Error message.

### 7.18. Saving data

1. Dealing with missing/corrupted data files

    1. Edit the JSON file by adding a number "1" to the top of the file.
    2. Run the file.

--------------------------------------------------------------------------------------------------------------------

## **8. Appendix: Effort**

ProjectPRO is a project built upon AB3, which was built out of the SE-EDU initiative. Our group has been actively working on ProjectPRO this semester, meeting regularly to discuss about our application to meet deadlines punctually.


In this section, we will detail some challenges we faced throughout the process of creating ProjectPRO.

### **8.1 Design Challenges**
As ProjectPRO is a brownfield project built from AB3, it was necessary to identify the pros and cons of AB3, and use them to come up with a better application more suited for university students. Some challenges include:
* Designing a nice user interface with JavaFX: One big change we wanted to incorporate into our application was the change of the User Interface. We wanted a more lively colour scheme to make our product not only convenient, but pretty to create a better user experience. Despite not being fluent in JavaFX, we trialed around with colours and different components and eventually created a mini weekly calendar to document our weekly group meetings, while having a warm colour scheme to make our application enticing to users.
* Creating more relevant functions and fields for university students: Since our application targeted University students, we decided to add group fields to allow students to record who is in their group. After identifying finding meeting time as a common problem faced by university students in group projects, we also added a time field and added functions to help find common free times that groupmates are free to help users better find common free time to hold meetings.

### **8.1 Design Challenges**
* Given the substantial size of AB3, comprehending its code structure and intricacies posed an initial challenge for us. In response, we proactively initiated project meetings early on. This early engagement provided us with additional time to grasp the intricacies of AB3's implementation and facilitated collaborative discussions to clarify any uncertainties we had about AB3. This enhanced understanding played a crucial role in minimizing the learning curve when undertaking the implementation of new features for ProjectPRO. Since some features shared similar concepts, understanding how they were implemented in AB3 significantly eased the difficulty in implementing corresponding methods for ProjectPRO's enhancements.
