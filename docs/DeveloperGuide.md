---
  layout: default.md 
  title: "Developer Guide"
  pageNav: 3
---

# Staff-Snap Developer Guide

<!-- * Table of Contents -->

<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

Staff-Snap is based on the [AddressBook-Level3](https://se-education.org/addressbook-level3/) project created by the [SE-EDU initiative](https://se-education.org), and it incorporates the following third-party libraries: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://junit.org/junit5/), [OpenCSV](https://opencsv.sourceforge.net/project-info.html), [TestFX](https://testfx.github.io/TestFX/docs/javadoc/testfx-core/javadoc/org.testfx/module-summary.html).


--------------------------------------------------------------------------------------------------------------------

## **Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is
in charge of the app launch and shut down.

* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `ApplicantListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFX UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Applicant` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>
Note: The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of
PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `ApplicantBookParser` object which in turn creates
   a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a applicant).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `ApplicantBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ApplicantBookParser` returns back as
  a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the applicant book data i.e., all `Applicant` objects (which are contained in a `UniqueApplicantList` object).
* stores the currently 'selected' `Applicant` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Applicant>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save both applicant book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `ApplicantBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
  the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.staffsnap.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add applicant feature

#### Implementation
The add applicant feature allows users to add an applicant to the applicant list.

#### Steps to trigger
1. The user launches the application.
2. The user executes `add n/John Doe hp/98765432 e/jd@gmail.com p/software engineer` to add a new applicant.
3. The `AddCommandParser#parse()` checks whether all the prefixes and the required values are provided.
4. If the check is successful, the `AddCommand#execute()` checks if the applicant exists in the applicant list.
5. If the applicant does not exist, the `Model#addApplicant()` adds the applicant to the applicant list.

#### Design considerations

##### Aspect: Format for adding applicants and interviews

- Alternative 1 (current choice): Separate command `add` and `addi` for adding applicants and interviews respectively.
    - Pros: Shorter command for user and easier to handle exceptions.
    - Cons: Harder to implement.

- Alternative 2: Same command `add` for adding applicants and interviews.
    - Pros: More convenient for user to add an applicant with interviews.
    - Cons: Still necessary to have a separate `addi` command for user to add a new interview to an existing applicant.

### Edit applicant feature

#### Implementation
The edit applicant feature allows users to edit the details of an applicant.

#### Steps to trigger
1. The user launches the application.
2. The user executes `edit 1 hp/87654321 p/front-end engineer` to edit the phone number and position of the first applicant.
3. The `EditCommandParser#parse()` checks whether the index of the applicant is valid and at least one prefix with the required values are provided.
4. If the check is successful, the `EditCommand#execute()` checks if the identity of the applicant after the edit is the same as the identity of another existing applicant. 
5. If the identity is not the same, the `Model#setApplicant()` updates the details of the applicant while the `Model#updateFilteredApplicantList()` updates applicant list to display the updated applicant list.

#### Design considerations

##### Aspect: Syntax for editing applicants

- Alternative 1 (current choice): At least one field to edit has to be provided.
    - Pros: More convenient for user to edit an applicant's details and shorter command. Ensures that field not meant to be edited will remain unchanged.
    - Cons: Harder to implement due to more validation and checks needed.

- Alternative 2:  All fields of the applicant has to be provided, regardless of whether it is edited.
    - Pros: Easier to implement due to similarity to add feature.
    - Cons: More areas for error as fields not meant to be edited might be edited due to a typo.

### Help feature


#### Implementation

1. When the user enters the term help. it triggers the help feature in the parser under the switch case.
2. After it is triggered, it will display a short list of possible commands that the user can use.
3. The user guide will also be opened in their browser


#### Steps to trigger

1. User opens the app
2. User keys in `help`
3. Command list is shown and opens user guide in browser

#### Notes

1. Help can be called anytime and has no format to follow. The popup screen is disabled to avoid confusion but can be
   enabled in the future if need be.

### Confirmation + Clear command

#### Implementation

1. This features requires the state of the parser to be known.
2. The parser is modified to store the previous taken in command, in this case whether the previous command was a successful clear command.
3. If the previous command is not a clear command, it looks for the keyword clear. Otherwise, it looks for the keyword yes.
4. Hence, the user will first need to call clear, before calling yes to invoke the clear mechanism, ensuring safety of data.

#### Steps to trigger

1. User opens the app
2. User enters `clear` (and subsequently sees a message asking to confirm)
3. User enters `yes` to confirm the clear

#### Notes

1. If you would like to extend the code for more features that require state, please do change the case condition for this feature.
2. Currently, it follows the default commands if a word other than yes is given. But this will be improved in a future update.
3. The state of the parser, rather than the app is used to reduce the chances of accidental clears.

### Interview feature

#### Purpose

As a hiring management software, we need to perform CRUD operations for the interviews of applicants. This allows us to add new 
interviews, view existing interviews, edit current interviews, and delete interviews. As we aim so make our program intuitive 
and efficient, the UI design and data structure used to store these interview objects were crucial considerations in the 
implementation process.

#### Implementation

The `Interview` class is used to store the information of each interview. It contains the following attributes: `type` and `rating`. The `type` attribute represents the type of interview, while the `rating` attribute represents how well the applicant performed in an interview (out of 10). The CRUD commands involving `Interview` includes the `AddInterviewCommand`, `EditInterviewCommand`, and `DeleteInterviewCommand`. These are implementation in a largely similar manner to the `Applicant` class. The main difference is in how an `EditInterviewDescriptor` class facilitates the editing of an interview and how the edit and delete commands requires 2 indices: the applicant index as well as the chosen interview index.

#### Design Considerations

In deciding the data structure to house our Interview objects, we were torn between using a `PriorityQueue` and a `List`. A `PriorityQueue` would have been useful in sorting the interviews by rating, but it would have been difficult to implement the `EditInterviewCommand` and `DeleteInterviewCommand` as the `PriorityQueue` does not have a `get()` method. Also, if we wanted to extend a sorting function for interviews in the future, a `PriorityQueue` would make it more difficult for us to change the comparator for `Interview` objects. For the sake of extensibility of the codebase, we decided to use a `List` instead. This is because a `List` provides us with greater abstraction and code flexibility in extending various functions for the `Interview` class. 

### Sort feature

#### Implementation

The sort feature is facilitated by `Descriptor`, an enumeration which describes the valid fields which can be used to sort an applicant.

To enable sorting, `Applicant` implements `Comparable<Applicant>`, to allow for comparison between applicants. To allow for applicants to be sorted by different descriptors, `Applicant` is augmented to contain a static `Descriptor` field. This is used in `Applicant#compareTo()`, where a switch case checking the state of the `Descriptor` field will then compare the specified field of both applicants.

In order to enable comparison of each valid field, these fields will implement the `Comparable` interface. Currently valid fields for sorting are

1. Name
2. Phone

#### Steps to trigger

1. User opens the app
2. User enters `sort d/ [valid field]`, where valid field is one of the fields listed above to be sorted by

Once step 2 has been completed, the GUI will update and refresh the applicant list to be sorted by the specified field.

The following diagram summarises what happens when a user executes a Sort command:
<puml src="diagrams/SortCommandActivityDiagram.puml" alt="SortCommandActivityDiagram" />

#### Design considerations

##### Aspect: How to compare applicants

- Alternative 1 (current choice): use Comparable interface
    - Pros: Standard method of comparison between objects in Java and implementing it will make it compatible with most
      other sorting functions in Java. Easily extensible by adding more cases to the switch statement, to compare by
      other fields when it becomes supported.
    - Cons: Applicant#compareTo has to return different values depending on which descriptor has been chosen, causing
      some bugs when working with other Java functions as the order of Objects compared to each other is not meant to
      change during runtime.

##### Aspect: Command syntax

- Alternative 1 (current choice): `sort d/ [valid field]`
    - Pros: Simple and minimal text fields, with a single prefix required to enable sorting.
    - Cons: Only able to sort in ascending order.
- Alternative 2: `sort d/ [valid field] o/ [a/d]`
    - Pros: Able to sort in either ascending or descending order.
    - Cons: Requires additional input from the user, slowing down the use of the command.
- Alternative 3: `sort d/ [valid field] o/ [a/d]` where `o/` is optional
    - Pros: Retains the ability to sort in either order, but also the conciseness of Alternative 1.
    - Cons: Users who are not aware of the `o/` feature may not use it.

### Filter feature

#### Implementation

The filter feature works by updating the `Predicate` used in the `FilteredList<Applicant>` of `ModelManager`. Using the predicate, minimal changes to the implementation of StaffSnap is required.

To create a single predicate that is able to search and filter for multiple fields, a `CustomFilterPredicate` class is created. It currently contains the following fields and is able to filter for applicants which match all specified fields.

1. Name
2. Phone
3. Email
4. Position
5. Status
6. Less than score
7. Greater than score

When `CustomFilterPredicate#test` is called, it will check if the specified fields are a substring of the same field of
the applicant, returning true if all specified fields match, and false otherwise.

#### Steps to trigger

1. User opens the app
2. User enters `filter [n/, e/, p/, hp/, s/, lts/, gts/] [term]`, where one or more of the prefixes can be specified to
   be filtered by

Once step 2 is complete, the GUI will update and refresh the applicant list with only applicants which match all
specified fields. 
The following diagram summarises what happens when a user executes a Filter command:

<puml src="diagrams/FilterCommandActivityDiagram.puml" alt="FilterCommandActivityDiagram" />

#### Design considerations

##### Aspect: How to filter applicants

- Alternative 1 (current choice): use a custom predicate and FilteredList, **compare using strings**
    - Pros: Current implementation of ModelManager already uses FilteredList, making a custom predicate an easy
      extension.
      The `CustomFilterPredicate` can easily be extended when more applicant fields are ready to expand the utility of
      the
      filter command.
    - Cons: Comparison of predicate fields to applicant fields are done using string comparison.
- Alternative 2: use a custom predicate and FilteredList, **compare within field classes**
    - Pros: Same as alternative 1, and definition of what is considered a match can be defined in the field class (e.g.
      Name, Phone, etc).
    - Cons: Will require greater complexity than alternative 1 in implementation. May be slower to integrate new classes
      in the future.

##### Aspect: Command syntax

- Alternative 1: `filter n/ [Name] e/ [Email] p/ [Position] hp/ [Phone] s/ [Status] lts/ [Score] gts/ [Score]`
    - Pros: Unambiguous and forces all fields to be entered, preventing errors.
    - Cons: Users cannot filter by single fields. Requires more key presses to enter a filter command.
- Alternative 2: `filter [n/, e/, p/, hp/, s/, lts/, gts/] [term]`, where only one term is allowed
    - Pros: Quicker to key in command than alternative 1.
    - Cons: Only allows users to filter by one field at a time, limiting utility of filter command.
- Alternative 3 (current choice): `filter [n/, e/, p/, hp/, s/, lts/, gts/] [term]`, where at least one term is required
  and the others
  are optional
    - Pros: Provides flexibility in the filter command to filter by one or more fields, while still retaining the speed
      of alternative 2 when few fields are required.
    - Cons: Unfamiliar users may not know that fields can be optional anc continue to key in the full command at all
      times.

### Find feature

#### Purpose
The find feature allows HR managers to find applicants by name, allowing for a faster and more efficient way of 
finding and tracking specific candidates.

#### Implementation
After the user enters the find command in the format `find KEYWORD [MORE_KEYWORDS]`,
the input is passed to the `ApplicantBookParser` class which calls `FindCommandParser#parse()` which parses the keywords
in the input and creates a list of keywords.

`FindCommandParser` then creates a new instance of `NameContainsKeywordsPredicate` with this list of keywords.
This `NameContainsKeywordsPredicate` object is then used as the parameter to instantiate a new `FindComand` object.
`LogicManager#execute()` then calls `FindCommand#execute()` and the current applicant book is updated by calling
`ModelManager#updateFilteredApplicantList()` which checks which applicant's name contains any of the keywords.

An instance of `CommandResult` is then created which contains the message and information that will be displayed to the user.
The GUI then updates to show this information to the user.

<puml src="diagrams/FindCommandSequenceDiagram.puml" alt="FindCommandSequenceDiagram" />

#### Notes

1. The search is case-insensitive, e.g. `find JOHN` will return both john and John.
2. The order of the keywords does not matter. e.g. `find Alice Tan` will match Tan Alice.
3. Only the applicant name is searched.
4. Any applicant whose name contains the sequence of characters given as the keyword will be given as a result. e.g. Ed will match both Edward and Ed.
   Applicants matching at least one keyword will be returned (i.e. OR search).
   e.g. `find Ben Bobby` will return Ben Yang, Bobby Chin.

#### Steps to trigger
1. User opens the app
2. User keys in `find KEYWORD [MORE_KEYWORDS]`
3. The GUI will update to show a list of applicants with name containing any of the keywords.

<puml src="diagrams/FindCommandActivityDiagram.puml" alt="FindCommandActivityDiagram" />

#### Design considerations

##### Aspect: How to find applicants

- Alternative 1: Find applicants by name using exact match search 
(e.g. `find Eddy` will only result in applicants whose name is Eddy)
  - Pros: Users can find applicants by their exact name, 
  allowing for a more specific search
  - Cons: Users would have to type in the entire name exactly in order to
  get their desired result. This can result in higher user error and takes more time to type.
  
- Alternative 2 (current choice): Find applicants by name using partial match search (or "fuzzy" search)
  - Pros: More inclusive, can find matches that are related but not exactly the same as `KEYWORD`.
  This is more user-friendly and allows for faster typing as users do not need to type the exact name out in order to find
  an applicant.

  - Cons: May return a larger number of results, some of which may not be relevant (false positives), 
  potentially requiring additional filtering or sorting which can be inconvenient and time-consuming.

##### Aspect: Command syntax 

- Alternative 1: `find n/NAME` where `n/` represents the name to be searched
  - Pros: Unambiguous that the term to be searched for is the name.
  - Cons: As the current find function only supports searching by name, adding the additional `n/` is unnecessary.

- Alternative 2 (current chocie): `find KEYWORDS [MORE_KEYWORDS]`
  - Pros: Allows for faster typing as users do not need to input the unnecessary `n/` tag.
  - Cons: Not immediately clear that the `find` command finds applications by name. 
  This will have to be explained in the user guide.

#### Future Extension:
A future extension to this find command is to allow it to find applicants by other fields such as their email or their
handphone number. 
As handphone numbers and emails are likely to be distinct for each applicant, it is possible to
enhance the find feature so that it can also search for applicants by these fields.

   
--------------------------------------------------------------------------------------------------------------------

## **Documentation, Logging, Testing, Configuration, DevOps**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

<br>

### Product scope

**Target user profile**:
* has a need to manage a significant number of applicants (around 250 - 500 applicants)
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: 
* Introduces organisation to applicant management, recruitment processes and streamlines hiring decisions for hiring managers

<br>

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | <div style="width:50px">As a …​</div> | I want to …​                                               | So that I can…​                                             |
|----------|---------------------------------------|------------------------------------------------------------|-------------------------------------------------------------|
| `* * *`  | user                                  | view all the available commands                            | know how to use the app                                     |
| `* * *`  | user                                  | add a new applicant                                        | track the the progress of all applicants                    |
| `* * *`  | user                                  | edit an applicant descriptor                               | maintain an updated database of all applicants              |
| `* * *`  | user                                  | view the full list of applicants                           | view the overall progress and performance of all applicants |
| `* * *`  | user                                  | delete an applicant entry                                  | only track valid applicants                                 |
| `* * *`  | user                                  | add an interview for an applicant                          | plan screenings and keep track of an applicant's interviews |
| `* * *`  | user                                  | edit an interview for an applicant                         | keep accurate data on an applicant's interview              |
| `* * *`  | user                                  | delete an interview for an applicant                       | delete incorrect or unnecessary interviews                  |
| `* * *`  | user                                  | store data locally                                         | use it on a daily basis consistently                        |
| `* *`    | user                                  | find a specific applicant                                  | access the applicant's information quickly                  |
| `* *`    | user                                  | sort applicants by a descriptor                            | find relevant applicants quickly                            |
| `* *`    | user                                  | filter applicants by a descriptor                          | look at applicants of a specific category                   |
| `* *`    | user                                  | purge all existing data                                    | remove sample data and populate real data                   |
| `* *`    | user                                  | exit the program                                           | close the program                                           |
| `* *`    | user                                  | import data from CSV file                                  | access all applicants' details                              |
| `* *`    | user                                  | mark an applicant as undecided, offered or rejected        | keep track of applicants' application status                |
| `*`      | user                                  | schedule a date for an interview                           | keep track of all interview timings                         |
| `*`      | user                                  | view a graphical representation of each applicant's rating | get a quick idea of each applicant's ability                |



### Use cases

(For all use cases below, the **System** is `Staff-Snap` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Add an applicant**

Guarantees: The new applicant will be added to the list of applicants.

**MSS**

1. User inputs the command to add an applicant.
2. Staff-Snap adds the new applicant to the list and displays the updated list.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends

* 1b. User enters an applicant that already exists.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. User does not enter a required field.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

* 1d. User enters an invalid value for a field.

    * 1d1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC02 - Edit applicant's information**

Guarantees: The applicant's information will be updated.

**MSS**

1. User inputs the command to edit an applicant's information.
2. Staff-Snap updates the applicant list with the updated applicant information.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. User enters an invalid index for the applicant.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. User does not enter at least one field to edit.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

* 1d. User enters an invalid value for a field.

    * 1d1. Staff-Snap shows an error message.

      Use case ends.

* 1e. User edits the applicant's phone number or email to be the same as another applicant's.

    * 1e1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC03 - Delete an applicant**

Guarantees: The applicant will be removed from the list of applicants.

**MSS**

1. User inputs the command to delete an applicant.
2. Staff-Snap removes the applicant from the list of applicants.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. User enters an invalid index for the applicant.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC04 - List all applicants**

Guarantees: All applicants will be listed.

**MSS**

1. User inputs the command to view the list of all applicants.
2. Staff-Snap displays the list of all applicants.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. Applicant list is empty.

    * 1b1. Staff-Snap shows an empty applicant list.

      Use case ends.

**Use case: UC05 - Change an applicant's status**

Guarantees: The applicant's status will be updated.

**MSS**

1. User inputs the command to edit the status of an applicant.
2. Staff-Snap updates the applicant list to display the updated status of the applicant.

Use case ends.

**Extensions**

* 1a. User enters an invalid command.

  * 1a1. Staff-Snap shows an error message.

    Use case ends.

* 1b. User enters an invalid index for the applicant.

  * 1b1. Staff-Snap shows an error message.

    Use case ends.

* 1c. User enters an invalid status.

  * 1c1. Staff-Snap shows an error message.

    Use case ends.

**Use case: UC06 - Add an interview to an applicant**

Guarantees: A new interview will be added to the applicant.

**MSS**

1. User inputs the command to add an interview to an applicant.
2. Staff-Snap updates the applicant information with the new interview.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends

* 1b. User enters an invalid index for the applicant.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. User does not enter a required field.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

* 1d. User enters an invalid value for a field.

    * 1d1. Staff-Snap shows an error message.

      Use case ends.

* 1e. User enters a duplicate interview type for the applicant that does not exceeds the maximum length after duplicate handling.

    * 1e1. Staff-Snap increment the last number in the interview type until it hits a unique input, or add 1 if there is no number at the end of the interview type.

      Use case continues at step 2.

* 1f. User enters a duplicate interview type for the applicant that exceeds the maximum length after duplicate handling.

    * 1f1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC07 - Edit an interview of an applicant**

Guarantees: The specified interview will be updated.

**MSS**

1. User inputs the command to edit an interview of an applicant.
2. Staff-Snap updates the interview with the new details and updates the applicant information with the new interview.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends

* 1b. User enters an invalid index for the applicant.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. User enters an invalid index for the interview.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

* 1d. User does not enter a field to edit.

    * 1d1. Staff-Snap shows an error message.

      Use case ends.

* 1e. User enters an invalid value for a field.

    * 1e1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC08 - Delete an interview from an applicant**

Guarantees: The specified interview will be deleted from the applicant.

**MSS**

1. User inputs the command to delete an interview from an applicant.
2. Staff-Snap removes the interview from the applicant and updates the applicant information.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends

* 1b. User enters an invalid index for the applicant.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. User enters an invalid index for the interview.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.


**Use case: UC09 - Find an applicant by name**

Guarantees: The applicants with name matching the search will be listed.

**MSS**

1. User inputs the command to find an applicant by name.
2. Staff-Snap displays the list of all applicants that match the search.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. No applicant found.

    * 1b1. Staff-Snap shows an empty applicant list.

      Use case ends.

* 1c. No search term provided.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC10 - Sort applicants**

Guarantees: The list of applicants will be sorted by the descriptor.

**MSS**

1. User inputs the command to sort the applicants by a particular descriptor.
2. Staff-Snap displays the list of applicants sorted by the descriptor.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. User enters an invalid descriptor.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. No descriptor provided.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC11 - Filter applicants**

Guarantees: Only applicants that satisfies the specified criterion will be listed.

**MSS**

1. User inputs the command to filter the list of applicants by a specified criterion.
2. Staff-Snap displays the list of all applicants that satisfies the specified criterion.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. User enters an invalid criterion.

    * 1b1. Staff-Snap shows an error message.

      Use case ends.

* 1c. No criterion provided.

    * 1c1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC12 - import CSV file**

Guarantees: The applicant list will be populated with data from the imported CSV file.

**MSS**

1. User inputs the command to import a CSV file.
2. Staff-Snap updates the applicant list to show the applicant data from the CSV file.

    Use case ends.

**Extensions**

* 1a. User enters an invalid command.
    
    * 1a1. Staff-Snap shows an error message.
  
        Use case ends.

* 1b. User enters an invalid filename.

  * 1b1. Staff-Snap shows an error message.
  
    Use case ends.

* 1c. User uses a file with incorrect headers.

  * 1c1. Staff-Snap shows an error message.
  
    Use case ends.

* 1d. User uses an file with duplicate applicants.

    * 1d1. Staff-Snap shows an error message.

      Use case ends.

* 1e. User uses an file with applicants that are already in Staff-Snap.

    * 1e1. Staff-Snap shows an error message.

      Use case ends.

* 1f. User uses an file with invalid fields.

    * 1f1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC13 - List all commands**

Guarantees: The list of all available commands will be made accessible.

**MSS**

1. User inputs the command to view the list of all available commands.
2. Staff-Snap opens the user guide in the default browser.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

**Use case: UC14 - Clear list of applicants**

Guarantees: The list of applicants will be cleared.

**MSS**

1. User inputs the command to clear the list of applicants.
2. Staff-Snap prompts for confirmation
3. User confirms the action.
4. Staff-Snap clears the list of applicants and displays an empty list.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 2a. User rejects the confirmation.

    * 2a1. Staff-Snap clears confirmation message.

      Use case ends.

**Use case: UC15 - Exit the program**

Guarantees: Staff-Snap exits.

**MSS**

1. User inputs the command to exit the program.
2. Staff-Snap exits and closes.

   Use case ends.

**Extensions**

* 1a. User enters an invalid command.

    * 1a1. Staff-Snap shows an error message.

      Use case ends.

* 1b. User closes the application window.

  Use case resumes at step 2.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. The entire software should be able to be packaged into a single _JAR file_ for users to download.
3. The file size of the JAR file should not exceed 100MB.
4. A user who can type fast should be able to accomplish most tasks faster via a _command line interface (CLI)_,
   compared to a hypothetical GUI-only version of the app.
5. The product is for single-users. The application should not be running in a shared computer and with
   different people using it at different times.
6. The software should respond to user input within 2 seconds under normal load conditions.
7. There should be no shared file storage mechanism. The data file created by one user should not be accessed by
   another user during regular operations.
8. The data should be stored locally and should be in a _human editable text file_.
9. The software should work without requiring an installer.
10. The software should not depend on a remote server so that anyone can use the app at anytime.
11. The _GUI_ should not cause any resolution-related inconveniences to the user for
    standard screen resolutions 1920x1080 and higher, and for screen scales 100% and 125%.
12. All functions can be used via the GUI, even if the user experience is not optimal, for resolutions 1280x720 and
    higher,
    and for screen scales 150%.
13. The software should provide error messages which clearly states the error and provides guidance on correcting the
    error.
14. The software should provide easily accessible help in the form of documentation for users unfamiliar with the
    commands.
15. The software should include automated tests to ensure correctness and reliability.

### Glossary

* **Mainstream OS**: Windows, Linux, macOS
* **JAR file**: A package file format that bundles all the components of a Java application into a single file for
  distribution.
* **Command Line Interface (CLI)**: A means for users to interact with a software by inputting commands
* **Human editable text file**: A text file that can be viewed and modified using a standard text editor by a user.
  (e.g. a `.txt` file)
* **Graphical User Interface (GUI)**: A type of user interface that allows users to interact with software through
  graphical icons and visual indicators.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch 
   1. Download the jar file and copy into an empty folder.
   2. Double-click the jar file. <br> Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences 
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   2. Re-launch the app by double-clicking the jar file.<br> Expected: The most recent window size and location is retained.

### Adding an applicant

1. Adding an applicant

    1. Test case: `add n/Jane Greenwood p/Project Manager e/janeg@yahoo.com hp/81234567`<br>
       Expected: Applicant is added to the list. Details of the new applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    2. Test case: `add n/Jane Greenwood p/Project Manager e/janeg@yahoo.com hp/81234567` again (Duplicate applicant)<br>
       Expected: No applicant is added. Error details shown in the response area. Applicant list in applicant area remains the same.

    3. Test case: `add n/Jane Greenwood p/Project Manager e/janeg@yahoo.com hp/81234567` with any of the fields missing<br>
       Expected: Similar to previous.

    4. Other incorrect add commands to try: `add`, `add x`, `add n/John Doe e/johndoe@gmail.com p/Software Engineer hp/x`<br>
       Expected: Similar to previous.

### Editing an applicant

1. Editing an applicant while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

    2. Test case: `edit 1 n/Tom Greenwood`<br>
       Expected: The name of the first applicant is updated to *Tom Greenwood*. Updated details of the applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    3. Test case: `edit n/Pop Greenwood`<br>
       Expected: No applicant is edited. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect edit commands to try: `edit`, `edit x n/Jane Doe`, `edit e/email` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Deleting an applicant

1. Deleting an applicant while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

    2. Test case: `delete 1`<br>
       Expected: First applicant is deleted from the list. Details of the deleted applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    3. Test case: `delete 0`<br>
       Expected: No applicant is deleted. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete x`, `delete a` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Editing an applicant's status

1. Editing an applicant's status while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

    2. Test case: `status 1 s/o`<br>
       Expected: The status of the first applicant is updated to *OFFERED*. Updated details of the applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    3. Test case: `status 1 s/l`<br>
       Expected: No applicant's status is edited. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect edit status commands to try: `status`, `status x s/o`, `status 1 s/` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Adding an interview to an applicant

1. Adding an interview to an applicant while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

    2. Test case: `addi 1 t/technical r/8.6`<br>
       Expected: A technical interview with rating 8.6 is added to the first applicant in the list. Updated details of the applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    3. Test case: `addi 0`<br>
       Expected: No interview is added to any applicant. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect add interview commands to try: `addi`, `addi x`, `addi r/6.0`, `addi 1 t/toolonginterviewtypeeeeeeeeeeeeeeee` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Editing an interview of an applicant

1. Editing an interview to an applicant while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list. The first applicant has at least one interview.

    2. Test case: `editi 1 i/1 t/technical r/8.6`<br>
       Expected: The first interview of the first applicant in the list is updated to a technical interview with rating 8.6. Updated details of the applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    3. Test case: `editi 0`<br>
       Expected: No interview is added to any applicant. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect edit interview commands to try: `editi`, `editi x`, `editi 1 i/x t/technical`, `editi 1 i/1 r/y` (where x is larger than the list size and y is larger than 10.0)<br>
       Expected: Similar to previous.

### Deleting an interview from an applicant

1. Deleting an interview from an applicant while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list. The first applicant has at least one interview.

    2. Test case: `deletei 1 i/1`<br>
       Expected: First interview is deleted from the first applicant in the list. Updated details of the applicant shown in the response area.
       Applicant area shows the updated list of applicants.

    3. Test case: `deletei 0`<br>
       Expected: No interview is deleted from any applicant. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect delete interview commands to try: `deletei`, `deletei x`, `deletei 1 i/x` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Sorting applicants

1. Sorting applicants while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

    2. Test case: `sort d/status`<br>
       Expected: The applicants in the list are sorted by their status, in the order UNDECIDED, OFFERED, REJECTED. Success message shown in the response area.
       Applicant area shows the updated list of applicants in the sorted order.

    3. Test case: `sort d/i`<br>
       Expected: The list of applicants is not sorted. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect sort commands to try: `sort`, `sort d/`<br>
       Expected: Similar to previous.

### Filtering applicants

1. Filtering applicants while all applicants are being shown

    1. Prerequisites: List all applicants using the `list` command. Multiple applicants in the list.

    2. Test case: `filter gts/5.0`<br>
       Expected: The applicants in the list are filtered by their score, and the updated list contains only applicants with score of at least 5.0.
       Success message shown in the response area. Applicant area shows the filtered list of applicants.

    3. Test case: `filter name`<br>
       Expected: The list of applicants is not filtered. Error details shown in the response area. Applicant list in applicant area remains the same.

    4. Other incorrect filter commands to try: `filter`, `filter n/`<br>
       Expected: Similar to previous.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

1. Allow users to add applicants whose names include non-alphanumeric characters such as hyphens `-` and slashes `/`.
2. Provide an error message to the user if the selected CSV file for the `import` command does not contain the correct headers as specified.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

1. very effortful
