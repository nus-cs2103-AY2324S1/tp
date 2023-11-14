---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# InsuraHub Developer Guide

## **Table of contents**

1. [Setting up, getting started](#setting-up-getting-started)
2. [Design](#design)
   1. [Architecture](#architecture)
   2. [UI Component](#ui-component)
   3. [Logic Component](#logic-component)
   4. [Model Component](#model-component)
   5. [Storage Component](#storage-component)
   6. [Common Classes](#common-classes)
3. [Implementation](#implementation)
   1. [Preferred Contact Feature](#preferredcontact-feature)
   2. [AddTag Feature](#addtag-feature)
   3. [DeleteTag Feature](#deletetag-feature)
   4. [Filter by Tags Feature](#filtering-by-tag-feature)
   5. [File Feature](#file-feature)
   6. [GroupMeeting Feature](#groupmeeting-feature)
   7. [Add Policy Feature](#add-policy-feature)
   8. [Remove Policy Feature](#remove-policy-feature)
   9. [View Policy Feature](#view-policy-feature)
   10. [Filter Policy Description Feature](#filter-policy-description-feature)
   11. [Toggle Mode Feature](#toggle-mode-feature)
   12. [Change Password Feature](#change-password-feature)
   13. [Proposed Undo/Redo feature](#proposed-undoredo-feature)
4. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
5. [Acknowledgements](#acknowledgements)
6. [Appendix: Requirements](#appendix-requirements)
   1. [Product Scope](#product-scope)
   2. [User Stories](#user-stories)
   3. [Use Cases](#use-cases)
      1. [Use Case 1: Adding more tags to client records](#use-case-1---adding-more-tags-to-client-records)
      2. [Use Case 2: Deleting tags from client records](#use-case-2---deleting-tags-from-client-records)
      3. [Use Case 3: Adding client preferred form of contact](#use-case-3---adding-client-preferred-form-of-contact)
      4. [Use Case 4: Filtering client information through tags](#use-case-4---filtering-client-information-using-tags)
      5. [Use Case 5: Storing client documents nearly in a folder](#use-case-5---storing-client-documents-neatly-in-a-folder)
      6. [Use Case 6: Grouping clients based on the nearest MRT station fromm their residence to a region](#use-case-6---grouping-clients-based-on-the-nearest-mrt-station-from-their-residence-to-a-region)
      7. [Use Case 7: Delete a person](#use-case-7---delete-a-person)
      8. [Use Case 8: Filter Policy](#use-case-8---filter-policy)
   4. [Non-Functional Requirements](#non-functional-requirements)
   5. [Glossary](#glossary)
7. [Appendix: Instructions for Manual Testing](#appendix-instructions-for-manual-testing)
8. [Appendix: Planned Enhancements](#appendix-planned-enhancements)
   <page-nav-print />

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

Return to [Table Of Contents](#table-of-contents)

---

## **Design**

### Architecture

<img src="diagrams/ArchitectureDiagram.png" width="280" />
<br>
The Architecture Diagram given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.

- At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
- At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="diagrams/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="diagrams/ComponentManagers.png" width="300" />

The sections below give more details of each component.

Return to [Table Of Contents](#table-of-contents)

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<img src="diagrams/UiClassDiagram.png" alt="Structure of the UI Component" width="600"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

Return to [Table Of Contents](#table-of-contents)

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="diagrams/LogicClassDiagram.png" width="600"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<img src="diagrams/DeleteSequenceDiagram.png" alt="Interactions Inside the Logic Component for the `delete 1` Command" width = "600"/>

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="diagrams/ParserClasses.png" width="600"/>

How the parsing works:

- When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

Return to [Table Of Contents](#table-of-contents)

### Model component

**API** : [`Model.java`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="diagrams/ModelClassDiagram.png" width="650" />

The `Model` component,

- stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
- stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="diagrams/BetterModelClassDiagram.png" width="650" />

</box>

Return to [Table Of Contents](#table-of-contents)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103-W14-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="diagrams/StorageClassDiagram.png" width="625" />

The `Storage` component,

- can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
- inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

Return to [Table Of Contents](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

Return to [Table Of Contents](#table-of-contents)

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### PreferredContact feature

#### Current Implementation

InsuraHub allow users to add/edit the preferred contact method of the client
using their index relative to the current list shown in InsuraHub

There is only 2 preferred contact methods

1. phone number
2. email

Given below is an example usage scenario and how the PreferredContact mechanism behaves at each step

1. The user launches the application and wants to add a preferred contact method for a client, `Alex Yeoh`, who is already stored in the application.
2. The user tries to add a preferred contact method using `preferredContact 1`.
3. InsuraHub displays a error message stating `"At least one field must be provided"`.
4. The user then tries to add a preferred contact method using `preferredContact 1 pc/phone`.
5. `PreferredContactCommandParser` and `AddressBookParser` will check if the command format provided is valid before `PreferredContactCommand#execute()` is called.
6. InsuraHub will check if the client exists in the `UniquePersonList`.
7. If the client exist, the preferred contact method of the client will be updated.
8. The result of the execution of the command will then be used to create a `CommandResult` object.
9. This object will then be passed to `Logic`.
10. The execution will then be over as the preferred contact method will be highlighted and the adding preferred contact command successful message will then be displayed.

The following activity diagram shows how the Preferred Contact operation works:
<img src="diagrams/PreferredContactActivityDiagram.png" width="550" />

The following sequence diagram shows how the Preferred Contact operation works:
<img src="diagrams/PreferredContactSequenceDiagram.png" width="600" />

Return to [Table Of Contents](#table-of-contents)

### AddTag feature

#### Current Implementation

The AddTag feature allows users to add tags under a certain client by indexing the client.

Given below is an example usage scenario and how the AddTag mechanism behaves at each step

1. The user launches the application and wants to delete a tag for `Alex Yeoh` who is the first client listed in InsuraHub
2. The user wants to add the tag `friend` to the client `Alex Yeoh` by entering the command `addTag 1 t/friend`
3. The `execute` method of the command will then be used to create a `CommandResult` object
4. This calls the `createPersonWithAddedTag` method which creates a new client with the same details as `Alex Yeoh` but with the newly added tag
5. The `model` calls the `setPerson` method and updates the targetted client with the newly created client from the previous step
6. The `CommandResult` is then returned by the `execute` method and the UI will display the updated list of clients with `Alex Yeoh` having the newly added tag and a success message is displayed on the UI

The following activity diagram shows how the AddTag operation works:
<img src="diagrams/AddTagActivityDiagram.png" width="650">

The following sequence diagram shows how the AddTag operation works:
<img src="diagrams/AddTagSequenceDiagram.png" width="550">

Return to [Table Of Contents](#table-of-contents)

### DeleteTag feature

#### Current Implementation

The DeleteTag feature allows users to delete tags under a certain client by indexing the client.

Given below is an example usage scenario and how the DeleteTag mechanism behaves at each step

1. The user launches the application and wants to delete a tag for `Alex Yeoh` who is the first person in the InsuraHub
2. The user tries to delete tag with command `DeleteTag 1 t/friends`
3. InsuraHub displays an error message `Tags provided do not exist. Please provide an existing tag.`
4. Realising that the tag he wants to delete is `friend`, the user tries to type `DeleteTag 1 t/friend` instead
5. The result of the execution of the command will then be used to create a `CommandResult` object
6. This will then be passed to `Logic`
7. Upon confirmation that the tag exist in the first client of the address book, in this case `Alex Yeoh`, the tag is deleted from the UI of `Alex Yeoh` client
8. A successful message is returned, in this case `Deleted tags successfully for person Alex Yeoh; Phone: 87438807; Email: alexyeoh@example.com; Address: Blk 30 Geylang Street 29, #06-40; Tags: `

The following activity diagram shows how the DeleteTag operation works:
<img src="diagrams/DeleteTagActivityDiagram.png" width="650">

The following sequence diagram shows how the DeleteTag operation works:
<img src="diagrams/DeleteTagSequenceDiagram.png" width="550">

Return to [Table Of Contents](#table-of-contents)

### Filtering by Tag feature

#### Implementation

The proposed tag filtering mechanism is facilitated by `FilterCommandParser`, `FilterCommand`, and `FilterTagPredicate`.
The `FilterContainsKeywordsPredicate` implements the `Predicate<Person>`class which implements the test operation:

- `test(Person)` - Checks through the `Set<Tag>` of the Person passed to the method for the target tag being filtered.

The `FilterCommandParser` created by the `AddressBookParser` parses any `filter` command to create
a `FilterCommand` object which calls its `execute` method and the `updateFilteredPersonsList` method of the Model is
called with the `FilterContainsKeywordsPredicate` object as its parameter.

Given below is an example usage scenario and how the tag filtering mechanism behaves at each step.

1. The user launches the application. The current `filteredPersonList` is simply a list of all Person objects
   in the `AddressBook`.

2. The user executes `filter t/Friend` command to filter for all Person objects in the address book
   with the tag `Friend'`. The `filter` command calls the `ParseCommand` method of the `AddressBookParser` which
   returns a `FilterCommandParser` object.

3. The `FilterCommandParser` object then calls its `parse` method, returning a `FilterCommand` object
   which is executed by the `LogicManager`, calling the `updateFilteredPersonList` method of the `Model`.

4. The update list of filtered `Person` objects are then displayed on the ui.

The following activity diagram shows how the Filter tag operation works:
<img src="diagrams/FilterTagActivityDiagram.png" width="450">

The following sequence diagram shows how the Filter tag operation works:
<img src="diagrams/FilterTagSequenceDiagram.png" width="650" />

Return to [Table Of Contents](#table-of-contents)

### File feature

#### Current Implementation

InsuraHub allow users to open a folder unique to each client to store their files
using their index relative to the current list shown in InsuraHub

these folders are stored in a main folder called ClientFiles in the main directory of InsuraHub

Given below is an example usage scenario and how the file mechanism behaves at each step

1. The user launches the application and wants to file certain documents for a client, `Alex Yeoh`, who is already stored in the application shown as the first person on InsuraHub.
2. The user tries to open a folder for the client with command `file 1`
3. InsuraHub will fetch the 1st client currently shown, if the index 1 is not out of bound in the list.
4. If the index is valid, the `Name` of the client will be used as the folder name for the client.
5. If the folder with the folder name does not exist then the folder will be created and placed in the ClientFiles folder.
6. The folder in the ClientFiles folder with the folder name will be opened.
7. The execution will then be over as the user can now drop files for the client into this opened folder, file command successful message will be displayed.

Activity diagram for File Command:<br>
<img src="diagrams/FileActivityDiagram.png" width="650" />

Return to [Table Of Contents](#table-of-contents)

### GroupMeeting feature

#### Current Implementation

InsuraHub allow users to filter based on the client preferred meeting region

There are only 5 preferred meeting regions:

1. north
2. south
3. east
4. west
5. central

Given below is an example usage scenario and how the Group Meeting mechanism behaves at each step

1. The user launches the application and wants to group all clients who prefer to meet in the west as he/she is planning to meet clients who live in the west.
2. The user tries to filter clients using `groupmeeting`.
3. InsuraHub displays a error message stating `"At least one region must be included"`.
4. The user then tries to filter clients using `groupmeeting west`.
5. `GroupMeetingCommandParser` and `AddressBookParser` will check if the command format provided is valid before `GroupMeetingCommand#execute()` is called.
6. InsuraHub will check if users in the list fulfills the `GroupMeetingContainsKeywordPredicate`
7. The result of the execution of the command will then be used to create a `CommandResult` object.
8. This object will then be passed to `Logic`.
9. The execution will then be over as the updated list of filtered `Person` objects are displayed on the Ui

Activity diagram for filtering clients based on preferred meeting region:
<img src="diagrams/GroupMeetingActivityDiagram.png" width="550" />

Sequence diagram for filtering clients based on preferred meeting region:
<img src="diagrams/GroupMeetingSequenceDiagram.png" width="550" />

Return to [Table Of Contents](#table-of-contents)

### Add Policy feature

#### Current implementation

InsuraHub allows users to add insurance policies to keep track of new policies that clients have purchased through them

There are 5 attributes for each policy:

1. Policy Name (pn)
2. Policy Description (pd)
3. Policy Value (pv)
4. Policy Start Date (psd)
5. Policy End Date (ped)

Given below is an example usage scenario and how the Add Policy Mechanism works:

1. The user launches the application and wants to add a policy for `Alex Yeoh` who is the first client listed in InsuraHub
2. The user wants to add a policy with `policyName: Health Insurance`, `policyDescription: Cancer Plan`, `policyValue: 2000.00`, `policyStartDate: 2023-01-01`, `policyEndDate: 2024-12-12`
3. The user enters the command `addPolicy 1 pn/Health Insurance pd/Cancer Plan pv/2000.00 psd/2023-01-01 ped/2024-12-12`
4. The `execute` method of the `AddPolicyCommand` will be called
5. This calls the `createPersonWithAddedPolicy` method, creating a new Person object with the same details as `Alex Yeoh` but with the newly added policy
6. The `model` calls the `setPerson` method and updates the targetted client with the newly created client from the previous step
7. The `CommanResult` is then returned by the `execute` method and the UI will display the updated list of clients with `Alex Yeoh` having the newly added policy and a success message is displayed on the UI

The following activity diagram shows how the AddPolicy operation works:
<img src="diagrams/AddPolicyActivityDiagram.png" width = "650">

The following sequence diagram shows how the AddPolicy operation works:
<img src="diagrams/AddPolicySequenceDiagram.png" width = "650">

Return to [Table Of Contents](#table-of-contents)

### Remove Policy feature

#### Current Implementation

The Remove Policy feature allows users to remove policies under a certain client by indexing the client and indexing the policy to be deleted

Given below is an example usage scenario and how the Remove Policy mechanism behaves at each step

1. The user launches the application and wants to remove a policy from `Alex Yeoh` who is the first client listed in the InsuraHub UI
2. The user wants to remove the first (right-most) policy of `Alex Yeoh` with command `removePolicy 1 1`
3. The `execute` method of the `RemovePolicyCommand` object will be called
4. That will then call the `removePolicy` method on the existing Person object identified by the indexes
5. The model will then call the `setPerson` method and updates the targetted client with the policy removed from the previous step
6. The `CommandResult` is then returned by the `execute` method and the UI will display the updated list of clients with `Alex Yeoh` not having the policy that was removed and a success message is displayed on the UI

The following activity diagram shows how the RemovePolicy operation works:
<img src="diagrams/RemovePolicyActivityDiagram.png" width = "650">

The following sequence diagram shows how the RemovePolicy operation works:
<img src="diagrams/RemovePolicySequenceDiagram.png" width = "500">

Return to [Table Of Contents](#table-of-contents)

### View Policy Feature

#### Current Implementation

The View Policy feature allows users to view the details of policies under a certain client by indexing the client and the policy

Given below is an example usage scenario and how the View Policy mechanism behaves at each step

1. The user launches the application and wants to view the policies of `Alex Yeoh` who is the first client listed in the InsuraHub UI
2. The user wants to view the first (right-most) policy of the client `Alex Yeoh` by entering the command `viewPolicy 1 1`
3. The `execute` method of the `ViewPolicyCommand` will then be used to create a `CommandResult` object
4. This converts the `policyList` from a `Set` to a `List` and uses a `stream` to index the target policy to be removed
5. The `CommandResult` is then returned by the `execute` method with the `toString()` of the `policy` indexed from the previous step
6. The UI will display the details of the policy specified by the index and a success message is displayed on the UI

The following activity diagram for viewing policy of a particular Client:
<img src="diagrams/ViewPolicyActivityDiagram.png"/>

The following sequence diagram shows how the View Policy operation works:
<img src="diagrams/ViewPolicySequenceDiagram.png" width="650" />

Return to [Table Of Contents](#table-of-contents)

### Filter Policy Description feature

#### Current Implementation

InsuraHub allow users to filter clients based on their policy details, currently only filtering policy description

Given below is an example usage scenario and how the Filter Policy mechanism behaves at each step

1. The user launches the application and wants to filter clients who have Cancer Plans
2. The user tries to filter clients using `filterpolicydescription`.
3. InsuraHub displays a error message stating `"Invalid Command format"` with examples of how to use the command.
4. The user then tries to filter clients using `filterpolicydescription Cancer Plan`.
5. `FilterPolicyDescriptionCommandParser` and `AddressBookParser` will check if the command format provided is valid before `FilterPolicyDescriptionCommand#execute()` is called.
6. InsuraHub will check if users in the list fulfills the `FilterPolicyDescriptionPredicate`
7. The result of the execution of the command will then be used to create a `CommandResult` object.
8. This object will then be passed to `Logic`.
9. The execution will then be over as the updated list of filtered `Person` objects are displayed on the Ui

The following activity diagram shows how the Filter Policy Description operation works:
<img src="diagrams/FilterPolicyActivityDiagram.png"/>

The following sequence diagram shows how the Filter Policy Description operation works:
<img src="diagrams/FilterPolicySequenceDiagram.png" width ="500">

Return to [Table Of Contents](#table-of-contents)

### Toggle Mode feature

#### Current Implementation

InsuraHub allows users to toggle between a dark(default) or light mode to their preference to maximise their productivity

Given below is an example usage scenario and how the Toggle Mode mechanism behaves at each step

1. The user launches the application in the default dark mode and wants to toggle it to light mode and enters the command `toggleMode`
2. The `execute` method of the `ToggleModeCommand` is called
3. The `uiModeManager` calls its `getUiMode` method and stores the current `uiMode` in a string
4. The `uiMode` is detected to be the default value of `MainWindow.fxml` and is updated to `LightWindow.fxml`
5. The `CommandResult` is returned by the `execute` method and the mode of the UI will be switched to Light Mode on the user's next start up of the application
6. The UI will continue displaying the list of clients and a success message is displayed on the UI

The following activity diagram shows how the Toggle Mode operation works:
<img src="diagrams/ToggleModeActivityDiagram.png" width="650" />

The following sequence diagram shows how the Toggle Mode operation works:
<img src="diagrams/ToggleModeSequenceDiagram.png" width = "500">

Return to [Table Of Contents](#table-of-contents)

### Change Password feature

#### Current Implementation

InsuraHub allows users to change the required password to enter InsuraHub

Given below is an example usage scenario and how the changePassword command behaves

1. The user launches the application and sets the password as "oldPW1" before entering InsuraHub with the password.
2. The user wants to change the password and enters the commant `changePassword op/oldPW1 np/newPW2`
3. The `passwordManager` calls its `check` method on the old password given "oldPW1" to determine if the current password saved in encoded.txt in the data folder is indeed "oldPW1"
4. The `passwordManager` detects that the old password is indeed correct and calls its `set` method to set the new password as "newPW2" by modifying the string saved in encoded.txt in the data folder.
5. The UI will continue displaying the list of clients and a success message is displayed on the UI.

Activity Diagram for changePassword Command:
<img src="diagrams/ChangePasswordActivityDiagram.png" width="650" />

The following sequence diagram shows how the changePassword operation works:
<img src="diagrams/ChangePasswordSequenceDiagram.png" width="500" />

Return to [Table Of Contents](#table-of-contents)

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

- `VersionedAddressBook#commit()` — Saves the current address book state in its history.
- `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
- `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<img src="diagrams/UndoRedoState0-Initial_state.png" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<img src="diagrams/UndoRedoState1-After_command__delete_5_.png" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<img src="diagrams/UndoRedoState2-After_command__add_n_David_.png" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<img src="diagrams/UndoRedoState3-After_command__undo_.png" alt="UndoRedoState3" />

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<img src="diagrams/UndoSequenceDiagram.png" alt="UndoSequenceDiagram" width="650"/>

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<img src="diagrams/UndoRedoState4-After_command__list_.png" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<img src="diagrams/UndoRedoState5-After_command__clear_.png" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<img src="diagrams/CommitActivityDiagram.png" width="450" />

#### Design considerations:

**Aspect: How undo & redo executes:**

- **Alternative 1 (current choice):** Saves the entire address book.

  - Pros: Easy to implement.
  - Cons: May have performance issues in terms of memory usage.

- **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  - Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  - Cons: We must ensure that the implementation of each individual command are correct.

Return to [Table Of Contents](#table-of-contents)

---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

Return to [Table Of Contents](#table-of-contents)

---

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

Return to [Table Of Contents](#table-of-contents)

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- has a need to manage a significant number of client's contacts
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps
- has a need to be able to easily find different clients contacts
- remembers some of their client's only by their firstname,lastname,nickname or initials
- needs to handle a large amount of documents that are stored for different clients

**Value proposition**: manage client's contacts faster than a typical mouse/GUI driven app while providing ways to efficiently find/store specific client and their documents

Return to [Table Of Contents](#table-of-contents)

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                       | So that I can…​                                                          |
| -------- | ------------------------------------------ | ------------------------------------------------------------------ | ------------------------------------------------------------------------ |
| `* * *`  | new user                                   | see usage instructions                                             | refer to instructions when I forget how to use the App                   |
| `* * *`  | user                                       | add a new person                                                   |                                                                          |
| `* * *`  | user                                       | delete a person                                                    | remove entries that I no longer need                                     |
| `* * *`  | user                                       | find a person by name                                              | locate details of persons without having to go through the entire list   |
| `* *`    | user                                       | hide private contact details                                       | minimize chance of someone else seeing them by accident                  |
| `*`      | user with many persons in the address book | sort persons by name                                               | locate a person easily                                                   |
| `* * *`  | insurance agent                            | Add more tags to my clients                                        | easily find them later                                                   |
| `* * *`  | insurance agent with multiple documents    | Store documents along with client contact information              | in an orderly manner                                                     |
| `* * *`  | insurance agent                            | Highlight the preferred contact details of my clients              | quickly contact them for any matters                                     |
| `* * *`  | insurance agent using the CLI              | Input and update client information through text commands          | provide personalized and efficient service without a graphical interface |
| `* * *`  | insurance agent                            | Filter interested clients                                          | focus my time on providing value to their lives                          |
| `* *`    | insurance agent                            | Organize my clients’ records based on tags                         | filter for specific groups of clients                                    |
| `* *`    | insurance agent                            | Cluster my clients into areas they want to meet                    | set up meetings that minimize my travel time                             |
| `* *`    | busy insurance agent                       | Load client data quickly                                           | use the app even with high traffic and not waste time waiting            |
| `* *`    | insurance agent                            | Sort client priorities                                             | attend to their needs first                                              |
| `* *`    | insurance agent prioritizing data security | Create secure log-in passwords and authentication                  | protect client data integrity                                            |
| `* *`    | non-technical insurance agent              | Access comprehensive help documentation or a built-in help command | understand available commands, their syntax, and purpose in CLI          |
| `* *`    | forgetful insurance agent                  | Have important todos in the homepage                               | not forget to do them, such as client meetings and applying for claims   |
| `* *`    | life insurance agent                       | Easily filter clients with policy updates                          | inform them more timely on the updates                                   |
| `* *`    | insurance agent                            | Track progress of insurance claims through text-based commands     | provide timely updates and ensure a smooth claims process via CLI        |
| `* *`    | insurance agent                            | Receive alerts for expiring insurance policies                     | prioritize meeting clients with expiring policies                        |
| `*`      | insurance agent with traditional clients   | Export a client’s policy summary to a spreadsheet                  | print it out for clients                                                 |
| `*`      | artistic insurance agent                   | Customize the UI                                                   | feel better using a more unique UI                                       |
| `*`      | insurance agent                            | Create new insurance policies for my clients                       | accommodate changes or updates requested by clients                      |
| `*`      | insurance agent working with others        | Send and receive client details with other users                   | take over/hand over clients from other agents                            |
| `*`      | data-driven insurance agent                | Access a variety of reports and analytics                          | make informed decisions to improve my business                           |

Return to [Table Of Contents](#table-of-contents)

### Use cases

If not explicitly mentioned, the actor will be a Financial Advisor and InsuraHub as the System.

#### **Use Case 1** - Adding more tags to client records

Precondition: User knows the client index relative to the list and the client is added into the list of clients

MSS:

1. User keys in all tags that is tied to the particular client
2. Systems displays the tags that have been added to the particular client<br>
   Use case ends

**Extensions:**

1a. User did not add a tag<br>
1a1. System displays an error message indicating that user have to key in at least one tag<br>
Use case ends

Return to [Table Of Contents](#table-of-contents)

#### **Use Case 2** - Deleting tags from client records

Precondition: User knows the client index relative to the list and the client is added into the list of clients, clients must also have the tag/tags listed in one of their tags.

MSS:

1. User keys in tags that he/she want to be removed from the client tags
2. Systems returns a new client without the tags that the user wants removed.<br>
   Use case ends

**Extensions:**

1a. User keys in a tag that is not in the tags that the client originally have.<br>
1a1. System returns an error message stating that the tag is not present and that he/she needs to give a tag that is in the client list of tags.<br>
Use case ends.

1b. User did not provide any tag to be removed<br>
1b1. System returns an error message stating that one tag must be provided.<br>
Use case ends

Return to [Table Of Contents](#table-of-contents)

#### **Use Case 3** - Adding client preferred form of contact

Precondition: User knows the client index relative to the list and the client is added into the list of clients

MSS:

1. User adds in the clients preferred form of contact.
2. System updates with the preferred form of contact.<br>
   Use case ends

**Extensions**

1a. User adds in multiple forms of contacts<br>
1a1. System displays an error message to tell the user to select only one form of preferred contact and that the
process of selecting preferred form of contact have failed.<br>
Use case ends

1b. User adds in a invalid preferred form of contact.<br>
1b1. System displays an error message indicating that user can only put in a preferred form of contact with a valid form of contact.<br>
Use case ends

1c. User did not add in any preferred form of contact<br>
1c1. System displays an error message indicating that the user have to put in at least one form of contact.<br>
User case ends

Return to [Table Of Contents](#table-of-contents)

#### **Use Case 4** - Filtering client information using tags

MSS:

1. User filters using tags.
2. System updates with a list of clients that fulfills the tags to be filtered.<br>
   Use case ends

Return to [Table Of Contents](#table-of-contents)

#### **Use Case 5** - Storing client documents neatly in a folder

Precondition: User knows the client index relative to the list and the client is added into the list of clients

MSS:

1. User keys in `file` followed by index<br>
2. System opens up the folder of the particular client with the relevant files inside the folder<br>
   Use case ends

**Extensions**

1a. User keys in invalid index<br>
1a1. System displays an error message indicating that the process of creating a file for the user is stopped.<br>
Use case ends

Return to [Table Of Contents](#table-of-contents)

#### **Use Case 6** - Grouping clients based on the nearest MRT station from their residence to a region

MSS:

1. User provides a region to be filtered
2. System returns a list of clients who live in a particular region.<br>
   Use case ends.

**Extensions:**

1a. User keys in an invalid region<br>
1a1. System returns an error message stating to put in a valid region to be filtered.<br>
Use Case Ends

Return to [Table Of Contents](#table-of-contents)

#### **Use case 7** - Delete a person

MSS:

1.  User requests to delete a specific person in the list
2.  InsuraHub deletes the person<br>
    Use case ends.

**Extensions**

1a. The given index is invalid.

1a1. InsuraHub shows an error message.<br>
Use case resumes at step 2.

Return to [Table Of Contents](#table-of-contents)

#### **Use case 8** - Filter Policy

MSS:

1.  User request to filter based on policy description
2.  InsuraHub show the list of people who have the policy<br>
    Use case ends.

**Extensions**

2a. The list is empty.<br>
Use case ends.

Return to [Table Of Contents](#table-of-contents)

### Non-Functional Requirements

1.  The application should be compatible with mainstream operating systems such as Windows, Linux, Unix, and macOS, as long as they have Java 11 or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application should ensure the integrity and consistency of data stored in the address book. Data should not be lost or corrupted during normal usage or unexpected errors.
5.  Any commands should be executed within 1s.
6.  The address book should be able to store 2000 clients' information.

Return to [Table Of Contents](#table-of-contents)

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Private contact detail**: A contact detail that is not meant to be shared with others
- **Tag**: A tag object assigned to a client that is a categorical description of the client
- **Client Priorities**: Priority of the client that the insurance agent has to attend to, where each priority is its own tag
- **Usage Instructions**: A quickstart guide with a short list of basic commands for users' reference within the App.

Return to [Table Of Contents](#table-of-contents)

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more _exploratory_ testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file <br>
      **OR**
      Open the command terminal, `cd` into the folder you put the jar in and use `java -jar InsuraHub.jar` command to run the application.<br>Expected: Shows the GUI shown below to ask you to set a password for first timers, first timers will then be asked to key in the password to enter the application.<br> Users who are not first timers will only be asked to enter the password that is previously saved.
      ![SetPassword](images/setPassword.png)

   1. After the password is entered A GUI similar to the below should appear in a few seconds.
      ![Ui](images/Ui.png)

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

### Adding a client

1. Adding a person while all persons are being shown
   1. Prerequisites: There should be no clients with the same name as the client you are adding. <br>
   2. List all persons using the `list` command.
   3. Test case: `add n/John p/61234567 e/john@email.com a/Blk 312 Choa Chu Kang St 32 pmr/west`<br>
      Expected: A new client is added and displayed in the client list.
   4. Test case: `add n/John p/61234567 e/john@email.com a/Blk 312 Choa Chu Kang St 32 t/Friends t/Colleagues pmr/west`<br>
      Expected: A new client with optional details is added and displayed in the client list.

### Deleting a client

1. Deleting a client while all clients are being shown

   1. Prerequisites: List all clients using the `list` command.

   2. Test case: `delete 1`<br>
      Expected: First client is deleted from the list. Details of the deleted client shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No client is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Adding Tags to a client

1. Adding tags to an existing client
   1. Prerequisites: The client that is going to have the new tag is in the client list.<br>
   2. List all clients using the `list` command.
   3. Test case: `addTag 1 t/Friends`<br>
      Expected: The first client in the list will have the friends tag added, if it is already there, the new tag will replace the old tag.

### Delete Tags from a client

1. Deleting tags from an existing client

   1. Prerequisites: The client that the user wants to remove the tag from is in the list, the client currently has the tag that have to be removed.
   2. List all clients using the `list` command.
   3. Test case: `deleteTag 1 t/friend`<br>
      Expected: The first client in the list will have his friend tag removed from the client list.

### Change Password

1. The user will change the password that is required to enter the application. User still remember the old password, users who have forgotten the old password will need to retrieve it by going to `data/encoded.txt`.
   1. Test case: `changePassword op/OLD PASSWORD np/NEW PASSWORD`
      <br>Do put in your current password into `OLD PASSWORD` and the password you wish to have as `NEW PASSWORD`.
      Expected: After leaving the application, you will need to use the new password to enter into the application.

### Adding a policy

1. Adding a policy into an existing client
   1. Prerequisites: The client that is going to have the new policy is in the client list.<br>
   2. List all clients using the `list` command.
   3. Test case: `addPolicy 1 pn/Health Insurance pd/Cancer Plan pv/2000.00 psd/2023-01-01 ped/2024-12-12`<br>
      Expected: A new policy is added into the first client, the client details are shown, do note that you have to use viewPolicy to verify the policy you are adding is correct.

### Removing Policy from a client

1. Removing a policy from an existing client
   1. Prerequisites: The client that the user wants to remove the policy from is in the list, the client currently has policies to remove.
   2. List all clients using the `list` command.
   3. Test case: `removePolicy 1 1`<br>
      Expected: The first client in the list will have his first (right-most) policy removed from his policy list.
   4. Test case: `removePolicy 1 0`<br>
      Expected: No policy is deleted. Error details shown in the status message. Status bar remains the same.

### Adding a file for a client

1. Adding a file for an existing client
   1. Prerequisites: The client that the user wants to add the file is in the list.
   2. List all clients using the `list` command.
   3. Test case: `file 1`<br>
      Expected: There will be a file that contains the first client's name added into the `Client Files` folder, if the file is already there, it will be opened.
   4. Test case: `file 0`<br>
      Expected: No file is opened. Error details shown in the status message. Status bar remains the same.

### Filter by tags

1. Filter clients by tags.
   1. List all clients using the `list` command.
   2. Test case: `filter t/friend`<br>
      Expected: Clients with the tag `friend` will be filtered and shown in the filtered list, status message will return the number of people with the tag.
   3. Test case: `filter`<br>
      Expected: There will be an error message stating incorrect command format, the example test case will be in the status message.

### Filter clients by policy description

1. Filter clients by policy description.
   1. List all clients using the `list` command.
   2. Test case: `filterpolicydescription Cancer Plan`<br>
      Expected: Clients with policies containing policy description `Cancer Plan` will be filtered and shown in the filtered list, status message will return the number of people with the policy, do note that it is case sensative.
   3. Test case: `filterpolicydescription`<br>
      Expected: There will be an error message stating incorrect command format, the example test case will be in the status message.

### Group Meeting

1. Filter clients by preferred meeting region.
   1. Prerequisites: There are clients that have the region as their preferred meeting region.
   2. Test case: `groupmeeting north`
      Expected: Clients with the preferred meeting location `west` will be filtered and shown in the filtered list, status message will return the number of people listed with that preferred meeting location.
   3. Test case: `groupmeeting`<br>
      Expected: There will be an error message stating incorrect command format, the example test case will be in the status message.

### Viewing details of client's policy

1. Viewing the details of a client's policy.
   1. Prerequisites: The client that the user wants to view the policy of is in the list, the client currently has policies to remove.
   2. Test case: `viewPolicy 1 1`
      Expected: The policy details (policy name, policy description, policy value, start date and end date) will be displayed in the status message.
   3. Test case: `viewPolicy`
      Expected: There will be an error message stating incorrect command format and that both indexes are required.

### Preferred Contact

1. Setting the preferred contact method for a client.
   1. Prerequisites: The client that the user wants to set the preferred contact method for is in the list.
   2. List all clients using the `list` command.
   3. Test case: `preferredContact 1 pc/phone`
      Expected: The preferred contact method of the first client in the list will be highlighted in yellow.
   4. Test case: `preferredContact`
      Expected: There will be an error message stating incorrect command format and an example command will be shown in the status message.

### Toggle UI Mode

1. Toggling the UI Mode between light mode or dark mode.
   1. Test case: `toggleMode`
      Expected: Success message stating the mode has been changed and InsuraHub will be switched to the other mode upon the next launch.

## **Appendix: Planned Enhancements**

### 1. Add Feature - Email Validation

#### Current State

The `email` parameter for adding a new client to InsuraHub currently only allows alphanumeric characters in the local-part for email addresses in the format local-part@domain.com

#### Planned Enhancement

The local-part will allow special characters which are commonly used in email addresses with the limitation of having no consecutive special characters together

### 2. Add Policy Feature - Invalid parameter and prefix name

#### Current State

No errors for Invalid prefixes:

- Having 2 `pn` prefixes (policy name) returns missing prefixes error
- No error message on the UI for empty parameters such as an empty policy name `pn`
- The `policy description` should be of the prefix `pd` in the `addPolicy` command but using an unknown `pr` prefix that precedes the policy description does not throw any error
- If `psd` and `ped` are missing, it does not return an error on the UI and silently fails as a runtime error

##### Planned Enhancement

- The prefixes will be checked to ensure that the `addPolicy` command entered by the user is a valid command with the correct prefixes

### 3. Add Policy Feature - Special characters allowed

#### Current State

The Add Policy command currently allows for special characters such as `;;` which is not how policies would be named

#### Planned Enhancement

Policy name and description will be checked through for special characters and corresponding error messages will be returned in the UI

### 4. Remove Policy Feature - Success message incorrectly formatted

#### Current State

The success message is currently not formatted properly with the details of the client wrapped in braces preceded by `seedu.address.model…​.`

#### Planned Enhancement

The success message will be formatted properly

### 5. Preferred Contact Feature - Parameters must be lowercase

#### Current state

The Preferred Contact command only accepts parameters in lower-case but there is no warning when the user enters a parameter in uppercase

#### Planned Enhancement

There will be error message returned in the UI when the user enters the parameters not in lowercase (either `email` or `phone`)

### 6. Add Policy Feature - End date can be earlier than start date

#### Current state

The end date of the policy can be earlier than the start date of the policy.

#### Planned Enhancement

There will be error message returned when the end date is earlier than the start date of the policy.

### 7. Client files is retained when clients are deleted or edited

#### Current state

Clients files in the client files folder is retained when clients are deleted or edited

#### Planned Enhancement

They will be edited or users will be reminded to edit or delete the files according when the client names are edited or deleted accordingly.

Return to [Table Of Contents](#table-of-contents)
