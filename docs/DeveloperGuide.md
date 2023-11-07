---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# LinkTree Developer Guide

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

![UI Class Diagram](images/UML_images/UiDiagram.png)

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, 
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures 
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in
[`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands, and it also 
displays the statistical information of how many developers and teams are there at the moment.
* depends on some classes in the `Model` component, as it displays `Person` and `Team` object residing in the `Model`.


### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W11-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

![Logic Class Diagram](images/UML_images/LogicClassDiagram.png)

The display in UI is depended on the `CommandResult` returned by Logic component. 
For example, UI will have a **new window** for displaying "LinkTree" if command
`Tree` is received by the Logic component.

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Delete Sequence Diagram](images/UML_images/DeleteSequenceDiagram.png)
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

![Parser Class Image](images/UML_images/ParserClasses.png)

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W11-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />

The `Model` component:
* stores the addressbook and teambook date i.e., all `Person` objects and `Team` objects (which are contained in a `UniquePersonList` and `UniqueTeamList` respectively).
* maintains a filtered list of `Person`, `Team` objects and stores the currently 'selected' `Person` or `Team` objects (eg., results of a search query) as a separate filtered list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` and `ObservableList<Team>` that can be observed.
* stores a `UserPref` object that represents the user’s preferences. This part is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, this means that model should make sense on their own without depending on other components).
<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API**: [`Storage.java`](https://github.com/AY2324S1-CS2103T-W11-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

![Storage UML Diagram](images/UML_images/StorageUML.jpg)  

The `Storage` component:
- Can save address book data, user preference data, and team book data in JSON format, and read them back into corresponding objects.
- Inherits from `AddressBookStorage`, `UserPrefStorage`, and `TeamBookStorage`, which means it can be treated as any one of them (if only the functionality of one is needed).
- Depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`).


### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Feature: Create a new team

####  Introduction

The create new team feature is facilitated by the AddTeamCommand. It extends `Command` class.

The operations are exposed in the `Model` interface as `Model#addTeam()`.

#### Usage
Given below is an example usage scenario and how the add team behaves at each step.

Step 1. The user launches the application and uses the `newteam` command and specifies a `teamname` and `teamLeader` name.



Step 2. The user executes the `newteam` command `newteam tn/Team1 tl/John` to create a new team `Team1` with `John` set as team leader.



Step 3. LinkTree provides a feedback based on whether the operation was successful or not.



<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#addTeam()`, so the `team` will not be saved to `TeamBook`.

#### Step-by-Step Implementation

1. Create a newteam parser class called `AddTeamCommandParser` to parse input from user. This implements the `Parser` interface for type `AddTeamCommand`
2. Create a `parse` method in `AddTeamCommandParser` that parses the user input and specify the user flags that are used `tn/` for teamName and `tl/` for teamLeader.
3. The flags for user input can be added to class `CliSyntax`.
4. For the `AddTeamCommand` class, specify the Command Word. In this case, it is `newteam`.
5. Add relevant messages for use cases like `Duplicate team creation` and `Person not found error`.
6. Implement the `execute` method in `AddTeamCommand`. Handle the cases where a team with specified `teamName` already exists and also one where specified `person` with given `teamLeaderName` does not exist.
7. Use the `Model#addTeam` and `Model#containsPerson` to do these checks.
8. Throw exception in the case where adding new team is not possible.
9. If not such exception is thrown, create the new team at this point. 
10. Run the `Model#addTeam` method to add the created team to TeamBook.
11. `Model#addTeam` calls `TeamBook#addTeam` which in turn calls `UniqueTeamList#add`. Finally this method calls the `ObservableList#add` which adds the `team` to the list.
</box>

#### Design considerations:

**Aspect: Where the team is stored:**

* **Alternative 1 (current choice):** Store the teams in a separate class called UniqueTeamList.
  * Pros: Better use of OOP.
  * Cons: Multiple layers of abstraction(through Model, TeamBook, UniqueTeamList).

* **Alternative 2:** Store the teams in the same class as Persons in the class AddressBook.
  * Pros: Easier implementation.
  * Cons: Breaks principle of abstraction and OOP. Information hiding is also not done.

### Feature: Remove an existing Team

#### Introduction

Removing an existing team feature is facilitated by the `DeleteTeamCommand`. It extends the `Command` class. 

The operations are exposed in the `Model` interface as `Model#deleteTeam()`.

#### Usage

Given below is an example usage scenario and how the "Remove an existing team" feature behaves at each step.

##### Step 1

The user launches the application and uses the `deleteteam` command to specify the `teamname` they want to delete.

##### Step 2

The user executes the `deleteteam` command `deleteteam tn/Team1` to delete the team named `Team1`.

##### Step 3

LinkTree provides feedback based on whether the operation was successful or not.

**Note:** If a command fails its execution, it will not call `Model#deleteTeam()`, so the team will not be removed from `TeamBook`.

#### Step-by-Step Implementation

1. Create a `deleteteam` parser class called `DeleteTeamCommandParser` to parse input from the user. This class implements the `Parser` interface for the type `DeleteTeamCommand`.

2. Create a `parse` method in `DeleteTeamCommandParser` that parses the user input and specifies the user flag used for `teamName` (e.g., `tn/` for teamName).

3. Add the flag for user input to the `CliSyntax` class.

4. For the `DeleteTeamCommand` class, specify the Command Word, which is `deleteteam`.

5. Add relevant error messages for use cases like "The team name provided is invalid".

6. Implement the `execute` method in `DeleteTeamCommand`. Handle cases where a team with the specified `teamName` does not exist.

7. Use the `Model#deleteTeam` method to perform these checks.

8. Throw exceptions in the case where deleting the team is not possible.

9. If no such exception is thrown, proceed to delete the team.

10. Run the `Model#deleteTeam` method to remove the specified team from TeamBook.

11. `Model#deleteTeam` calls `TeamBook#removeTeamByName`, which in turn calls `UniqueTeamList#remove`. Finally, this method calls `ObservableList#remove` to remove the team from the list.

#### Design Considerations

**Aspect: Where the teams are stored:**

- **Alternative 1 (current choice):** Store the teams in a separate class called `UniqueTeamList`.

    - Pros: Better use of object-oriented programming.
    - Cons: Multiple layers of abstraction (through Model, TeamBook, UniqueTeamList).

- **Alternative 2:** Store the teams in the same class as persons in the `AddressBook` class.

    - Pros: Easier implementation.
    - Cons: Breaks the principle of abstraction and object-oriented programming. Information hiding is also not maintained.

The choice between these alternatives depends on your specific project requirements and architectural preferences.


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

* Project managers engaged in software projects at the startup/small level companies; 
* Collaborates frequently with multiple teams or departments; 
* Requires quick access to contact details of other team members based on their roles and responsibilities; 
* Prefers an organized and streamlined method for contact management; 
* Tech-savvy and open to adopting new tools for enhancing productivity.

**Value proposition**: LinkTree is the top contact solution for software professionals. Using our unique tag-based system, access contacts by roles and responsibilities instantly. With LinkTree, swiftly connect with the right stakeholder, ensuring smooth project execution and superior collaboration.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …          | I want to …                                                   | So that I can…​                                                           |
|----------|-----------------|---------------------------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | project manager | view all members of my project                                | get their email address/contact                                           |
| `* * *`  | project manager | search contacts using a tag-based system                      | swiftly find contacts relevant to specific responsibilities               |
| `* * *`  | project manager | view the leader of any team                                   | contact him when I want to find out about the status of a task            |
| `* *`    | project manager | edit or update my information                                 | easily update my info                                                     |
| `* *`    | project manager | search contact with partial information                       | find the person without remembering their full name                       |
| `* *`    | project manager | add a tag/short bio about every developer                     | I can know the specialities/traits of every developer                     |
| `* * *`  | project manager | access the names of all my developers                         | contact them regarding the project                                        |
| `* * *`  | project manager | view all my team leaders                                      | contact them when collaborating on developing a certain feature           |
| `* * *`  | project manager | assign a node for a team leader                               | target team leaders can fill in their information by themselves           |
| `* * *`  | project manager | remove another user                                           | if he leaves the team/company                                             |
| `*`      | project manager | archive old contacts or groups                                | keep active list clutter-free while retaining old information             |
| `*`      | project manager | get notifications on birthdays                                | maintain relationships through personal touches                           |
| `*`      | project manager | create a public profile for my project                        | external stakeholders can find and reach out to the right member          |
| `* * *`  | project manager | use a help command                                            | understand available functions of the app                                 |
| `* *`    | project manager | use a bye command                                             | close the app easily                                                      |
| `*`      | project manager | receive request to change contact information                 | stay updated on team changes                                              |
| `* * *`  | project manager | view my teams and all members in my organisation side by side | have a glance at the current structure of my organisation                 |
| `* * *`  | project manager | remove all the users and teams                                | create a new project with new team members.                               |
| `* * *`  | project manager | create a new team                                             | reflect the changes done in actual project structure accurately           |
| `* * *`  | project manager | add new developers after a team is created                    | reflect the growth in size of my teams                                    |
| `* * *`  | project manager | remove developers from a team                                 | keep my information up to date when a developer shifts to another project |
| `* * *`  | project manager | receive request to change contact information                 | stay updated on team changes                                              |




### Use cases

(For all use cases below, the **System** is the `LinkTree` and the **Actor** is the `user`, unless specified otherwise)

**Use case: View all members of my project**
**Actor: Project Manager**

**MSS**

1. Project manager requests to list members.
2. LinkTree displays a list of team members.
3. Project manager views the members' contact information.

    Use case ends.

**Extensions**

* 2a. The member list is empty.
  * 2a1. LinkTree indicates there are no members currently.
    Use case ends.

---

**Use case: Search for team leaders**
**Actor: Project Manager **

**MSS**

1. User wants to find team leader of a given team.
2. User inputs the desired team name into LinkTree.
3. LinkTree displays the name of the team leader.

    Use case ends.

**Extensions**

* 2a. No such team exists
  * 2a1. LinkTree shows a message that no such team exists.
    Use case ends.

---

**Use case: Access names of developers**
**Actor: Project Manager**

**MSS**

1. Project manager requests a list of all developers.
2. LinkTree displays the list of developers.

    Use case ends.

---

**Use case: Edit or update information**
**Actor: Project Manager**

**MSS**

1. User wants to update the contact info of a developer.
2. User provides the name of person and info to edit
3. LinkTree confirms the updates.

    Use case ends.

**Extensions**

* 2a. No such person exists
    * 2a1. LinkTree shows a message that no such person exists.
      Use case ends.

---

**Use case: Change the team leader**
**Actor: Project Manager**

**MSS**

1. Project manager wants to change the team leader of a team.
2. Project manager selects the team and provides a new team leader name.
3. LinkTree confirms the change.

    Use case ends.

**Extensions**

* 2a. No such team exists
    * 2a1. LinkTree shows a message that no such team exists.
      Use case ends.
  2b. No such person exists
    * 2b1. LinkTree shows a message that no such person exists.
    * Use case ends.
---


**Use case: Remove a person from project**
**Actor: Project Manager**

**MSS**

1. Project manager wants to remove a person from project.
2. Project manager provides name and requests the removal.
3. LinkTree confirms the removal of the person.
    
    Use case ends.

**Extensions**

* 2a. No such person exists
    * 2a1. LinkTree shows a message that no such person exists.
      Use case ends.

---

**Extensions**

* 2a. No such team exists
    * 2a1. LinkTree shows a message that no such team exists.
      Use case ends.

---

---

**Use case: Create a new team**

**MSS**

1. User provides a team name and team leader name.
2. LinkTree creates team with given name and with team leader set to the provided name.
3. LinkTree confirms creation of team

    Use case ends.

**Extensions**

* 1a. Team already exists
    * 1a1. LinkTree shows a message that you are trying to create a duplicate team.
      Use case ends.
  * 1b. No such person with given name exists
      * 1b1. LinkTree shows a message that the person you are trying to set as team leader does not exist.
        Use case ends.
---

**Use case: Display the tree of organisation**

**MSS**

1. User requests for tree to be shown.
2. LinkTree displays existing tree
3. User views the tree.

    Use case ends.

4. **Extensions**

* 1a. Tree is empty
    * 1a1. LinkTree shows a message that the tree is empty.
      Use case ends.
---

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 500 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to generate output in less than 5 seconds.
5. Should be able to show correct input format in case the user enters the input in wrong format.
6. The system should be usable by a new project manager who has not used such a software before.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Team Directory**: The system or platform where all the contact details of developers, team leaders, and project managers are stored.
* **Tag-Based System**: A system in LinkTree that allows contacts to be tagged with specific roles or responsibilities, like "Database Management" or "Code Review".
* **Public Profile**: A profile in the Team Directory visible to all users, containing non-sensitive information about a team or individual.
* **Status Feature**: A tool enabling users to set and display their current status or availability (e.g., online, busy, away) within the Team Directory.
* **Inventory Checking**: A feature that logs the state of an item (like an apartment) at a specific time, allowing for easy comparison at a later date.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

< box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</ box>

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
