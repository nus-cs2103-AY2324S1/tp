---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* The foundational code for add, find and Interview's CRUD features are adapted from [AB-3](https://github.com/nus-cs2103-AY2324S1/tp).
* The formatting for the developer guide is inspired by [NUSCoursemates](https://ay2324s1-cs2103t-t17-4.github.io/tp/DeveloperGuide.html).
* The formatting for the developer guide is inspired by [InTrack](https://ay2223s1-cs2103t-t11-2.github.io/tp/DeveloperGuide.html).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the
[`docs/diagrams`](https://github.com/AY2324S1-CS2103T-T11-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple components above.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete-a 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside components being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ApplicantListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the [`src/main/resources/view`](https://github.com/AY2324S1-CS2103T-T11-2/tp/tree/master/src/main/resources/view) folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Applicant` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete-a 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to delete an applicant).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Applicant` objects (which are contained in a `UniqueApplicantList` object) as well as all `Interview` objects (which are contained in a `UniqueInterviewList` object).
* stores the currently 'selected' `Applicant` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Applicant>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Applicant` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Applicant` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-T11-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add interview feature
The add interview feature allows users to quickly add a scheduled interview into the application via the command
`add-i app/APPLICANT_INDEX jr/JOB_ROLE start/START_DATE_AND_TIME end/END_DATE_AND_TIME`

#### Implementation
The `add-i` command is facilitated by the `AddInterviewCommand` and the `AddInterviewCommandParser`.
It uses `ArgumentTokenizer#tokenize(String argString, Prefix... prefixes)` to extract the corresponding inputs for each field defined.
The Applicant is then obtained from the model using `Model#getFilteredApplicantList()` to get the Applicant List,
followed by `List#get(int index)` where the index is the provided Applicant Index.
A new `Interview` object is then created with the obtained applicant, the inputted job role, start time and end time.
By default, the rating field will be set to `0.0` and the isDone field set to `false`.
`Model#setApplicant(Applicant target, Applicant editedApplicant)` is then called to update the target applicant's `hasInterview` status to `true`.
`Model#addInterview(Interview interview)` is then called to add the new `Interview` object into the list of interviews.

#### How is the command executed
1. The user inputs the `add-i` command with correct parameters provided
2. The `AddressBookParser` processes the input and creates a new `AddInterviewCommandParser`.
3. The `AddInterviewCommandParser` then extracts the relevant inputs for each prefix.
If any of the compulsory prefixes have an absent or invalid input, a ParseException will be thrown.
4. The `AddInterviewCommandParser` then creates the `AddInterviewCommand` based on the processed input.
5. The `LogicManager` executes the `AddInterviewCommand`.
6. The `AddInterviewCommand` then creates a new `Interview` object with the corresponding parsed inputs for each field.
7. The `AddInterviewCommand` then calls `Model#setApplicant(Applicant target, Applicant editedApplicant)` to update the Applicant in the Applicant list
8. The `AddInterviewCommand` then calls `Model#addInterview(Interview interview)` to add the new `Interview` object to the list of interviews.
9. Finally, the `AddInterviewCommand` creates a `CommandResult` with the success message and returns it to the `LogicManager` to complete the command execution.
The GUI would then be updated to display the updated lists and success message.

For easier of viewing, 2 sequence diagrams will be used to show how the `add-i` command works.
Step 1 to 4:

![AddInterviewSequenceDiagram1](images/AddInterviewSequenceDiagram1.png)
The activation bar of `LogicManager` is intended to not end since it continues being in the activated state for the steps that follow.

Step 5 to 9:

![AddInterviewSequenceDiagram2](images/AddInterviewSequenceDiagram2.png)

### Find interview by job role feature

#### Implementation

The find interview by job role feature allows users to query the list of added interview for interviews that match the job role via the command `find-i KEYWORD(S)`, where `KEYWORD(S)` must not
be an empty string.

The `find-i` command is facilitated by the `FindInterviewCommand`, `FindInterviewCommandParser`, and `JobContainsKeywordsPredicate`.
It uses `Model#updateFilteredInterviewList(Predicate<Interview> predicate)` to apply the `JobContainsKeywordsPredicate`
in order to produce a filtered list containing only entries whose job correspond to `KEYWORD(S)`.

#### How is the command executed
1. The user inputs the `find-i` command together with non-empty job role as keyword(s);
2. The `LogicManager` receives the command string and forwarded it to the `AddressBookParser`.
3. The `AddressBookParser` checks the type of command and constructs `FindInterviewCommandParser` to parse the keyword(s).
4. The `FindInterviewCommandParser` constructs `JobContainsKeywordsPredicate` containing the keyword as predicate.
5. The `LogicManager` executes the `FindInterviewCommand` which calls the `Model#updateFilteredInterviewList(Predicate<Interview> predicate)` method.
6. The `FindInterviewCommand` construct `CommandResult` containing the number of successful interviews filtered in the final list and returns it to `LogicManager`.
7. The GUI will be updated automatically when the list changes.

The following sequence diagram shows how the `find-i` operation works:

![FindInterviewSequenceDiagram](images/FindInterviewSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for FindInterviewCommandParser should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes a new command:

![FindInterviewActivity](images/FindInterviewActivityDiagram.png)

#### Design Consideration
**Aspect: Case-sensitivity in search:**

* **Alternative 1 (current choice):** Case-insensitive search.
    * Pros: 
      * Offers flexibility and speed up the input process without worrying on casing.
    * Cons: 
      * A less accurate filters as it will return any matched keyword regardless of casing which might not be intended by the user.

* **Alternative 2:** Case-sensitive search.
    * Pros: 
      * A more precise filtering as the string is exact match
    * Cons: 
      * Less flexible as the users need to be precise with the input.

**Aspect: Accept multiple keywords:**

* **Alternative 1 (current choice):** Accept multiple keywords.
    * Pros: 
      * Offers a stronger filter to search the interview easily.
    * Cons: 
      * Requires more care to parse the input correctly in order to not miss any keywords.

* **Alternative 2:** Accept one keyword.
    * Pros: 
      * Easier to control the input and filtering.
    * Cons: 
      * Less powerful since users can only search job based on one keyword.

### Find applicant feature
#### Implementation

The find applicant feature allows users to query the list of applicants for applicants
whose name, phone, email, address and tags match the given arguments.

This can be done
via the command `find-a [n/KEYWORD(S)] [p/NUMBER]
[e/KEYWORD(S)] [a/KEYWORD(S)] [t/KEYWORD(S)]`.

The find applicant feature is facilitated by `FindApplicantCommand`, `FindApplicantCommandParser`,
`AddressContainsKeywordsPredicate`, `EmailContainsKeywordsPredicate`, `NameContainsKeywordsPredicate`,
`PhoneContainsNumberPredicate`, `TagContainsKeywordsPredicate` and `ApplicantPredicate`.
It uses `Model#updateFilteredApplicantList(Predicate<Applicant>)` to apply the predicates
in order to produce a filtered list containing only the filtered entries.

#### How is the command executed
1. The user inputs the `find-a` command together with arguments.
2. The `LogicManager` receives The LogicManager receives the command string and forwards it to the AddressBookParser.
3. The `AddressBookParser` checks the type of command and constructs
   `FindCommandParser` to parse the arguments.
4. If the arguments contain the `n/` prefix, a `NameContainsKeywordsPredicate` is created with the keywords.
5. If the arguments contain the `p/` prefix, a `PhoneContainsNumberPredicate` is created with the number.
6. If the arguments contain the `e/` prefix, a `EmailContainsKeywordsPredicate` is created with the keywords.
7. If the arguments contain the `a/` prefix, a `AddressContainsKeywordsPredicate` is created with the keywords.
8. If the arguments contain the `t/` prefix, a `TagContainsKeywordsPredicate` is created with the keywords.
9. An `ApplicantPredicate` is created with all the predicates created above.
10. The `FindCommandParser` constructs the `FindCommand` with the `ApplicantPredicate` 
and returns it to `LogicManager`.
11. The `LogicManager` executes the `FindCommand` which calls the 
`Model#updateFilteredApplicantList(Predicate<Interview> predicate)` method.
12. The `FindCommand` constructs a `CommandResult` containing the number of applicants
found and the success message. 
13. `FindCommand` returns the `CommandResult` to LogicManager.
14. The GUI updates automatically when the list changes.

The following sequence diagram shows how the `find-a` operation works:
![FindApplicantSequenceDiagram](images/FindApplicantSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for FindCommandParser should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes a new command:
![FindApplicantActivityDiagram](images/FindApplicantActivityDiagram.png)


#### Design Considerations

Aspect: How find applicant command filters applicants:
* **Alternative 1 (current choice):** Use a predicate for each field
    * Pros:
        * Decrease coupling and increases extensibility.
        * Easier to maintain
    * Cons:
        * More difficult and tedious to implement
        * More test cases needed for each predicate

* **Alternative 2:** Use one predicate for the entire applicant
    * Pros:
        * Easier to code and less code to write
    * Cons:
        * Harder to maintain
        * More coupling as predicates for different fields are not abstracted out

Aspect: Which find command format
* **Alternative 1 (current choice):** Accepts multiple space or comma separated keywords for each prefix
    * Pros:
        * Allows filtering using multiple keywords in a single find command
        * User can type the command quickly
    * Cons:
        * Only allows filtering by words and not phrases
* **Alternative 2:** Accepts one keyword for each prefix
    * Pros:
        * Easy to implement
        * User can type the command quickly
    * Cons:
        * Can only find using one keyword for each applicant field in a single find command
* **Alternative 3:** Accepts duplicate prefixes and a keyphrase for each prefix
    * Pros:
        * Allows filtering of multiple keywords or keyphrase in a single find command
        * The most specific filter out of all the alternatives
    * Cons:
        * Most difficult to implement of all alternatives considered
        * The command can be slow to type due to the need to type many prefixes

Aspect: How find command matches the arguments for name
* **Alternative 1 (current choice):** Match keywords to words in the name
    * Pros:
        * Can find a person with only the first name or last name
    * Cons:
        * Less specific than exact matching
* **Alternative 2:** Require exact match between arguments and name
    * Pros:
        * More specific search
    * Cons:
        * Users need to type longer commands to search for an applicant
        * Less flexibility
* **Alternative 3:** Check if argument is substring of the name
    * Pros:
        * Users can find an applicant without typing an entire word
        * More flexibility
    * Cons:
        * Too many applicants might show up in a single find command which defeats
          the purpose of the find command

### parseDate API

#### Implementation
This feature is implemented though the `TimeParser` class. This class contains several public static methods related to manipulating time:
- `TimeParser#parseDate(String date, boolean dateOnly)`  —  Takes in a time String as input, and returns a `Time` instance, which is a wrapper class for a LocalDateTime object which will store the time information (i.e. day, month, year, hour, minute) as well as providing utility methods for manipulating Time.
  
  - The `dateOnly` parameter is a flag to indicate how to parse the given `date`. If `dateOnly` is set to false, then the TimeParser will parse valid dates that are in the list of accepted date (without time) formats. Otherwise, if `dateOnly` is set to true, then the TimeParser will parse valid dates that are in the list of accepted date (with time) formats.
  - Accepted time formats:
    * DD/MM/YYYY and time:
        * `16 May 2024 TIME`
        * `16-05-2024 TIME`
        * `16-05-24 TIME`
        * `16/05/2024 TIME`
        * `16/05/24 TIME`
    * MM, DD and time:
        * `16 May TIME`
        * `16 January TIME`
        * `16/5 TIME`
        * `16/05 TIME`
    * The `TIME` placeholder can be replaced with the formats below:
        * `1515`
        * `3.15pm`
        * `3pm`
  - Accepted date formats
    * DD/MM/YYYY:
      * `16-05-2024`
      * `16/05/2024`
    * DD/MM:
      * `16/05`
      * `16 May`
        * _Must be a prefix of a valid month of at least 3 characters_
  - The Sequence Diagram below illustrates how other classes interact with `TimeParser` when `parseDate(date, false)` is called.
    ![TimeParserSequenceDiagram.png](images/TimeParserSequenceDiagram.png)

#### How is the command executed
1. The caller passes in the `date` string, which contains the date information. The caller also passes in the boolean flag `dateOnly`, which will indicate whether the string should be parsed into a `Time` instance containing date and time, or strictly date only.
2. If the parsing was successful, a `Time` instance containing the Time info will be returned.

#### Design considerations

**Aspect: How `TimeParser#parseDate(String date, boolean dateOnly)` works:**

* **Alternative 1 (current choice):** Have two hardcoded list of acceptable time formats. One with date and time, the other for time
    * Pros:
      * Easy to implement
      * Avoids code duplication
    * Cons:
      * May have performance issues in terms of time (i.e. might have to loop through the whole list to find a suitable format)
      * Huge number of time formats available, hence there is a need to update the list of acceptable time formats in future iterations
      * Many errors possible due to the many time fields that the user could format wrongly, which makes implementation difficult
      * Using the same method to parse strings with date & time and strings with date only might be prone to bugs


* **Alternative 2 :** Have two hardcoded list of acceptable time formats. One with date and time, the other for time, but parse the two types of time string (i.e. strings with date & time, and strings with date only separately)
    * Pros:
        * Easy to implement
        * Less prone to bugs since there are now separate methods for parsing the two types of strings
    * Cons:
        * Causes code duplication since the algorithm is virtually the same for both `parseDate` methods 
        * Not an optimal implementation (i.e. might have to loop through the whole list to find a suitable format)
        * Huge number of time formats available, hence there is a need to update the list of acceptable time formats in future iterations if needed
        * Many errors possible due to the many time fields that the user could format wrongly, which makes implementation difficult


* **Alternative 3 (alternate choice):** Use other time libraries
    * Pros:
        * Might be a better alternative to alternative 1
        * Error checking already implemented
    * Cons:
        * Will have to overhaul the entire TimeParser class, which might be impractical
        * High risk; not guaranteed to be better after overhaul
        * Developer is not familiar with other time libraries

### List all free timing for a given day feature

#### Implementation
The list free times for a given day feature allows the user to list all the blocks of time that are not taken by a scheduled interview. This command is in the format `list-freetime DATE` where `DATE` is a valid date string.

The Sequence Diagram below illustrates the interactions within the `Logic` component when `execute("list-freetime 12/12/2099")` is called.
![images/ListFreeTimeSequenceDiagram.png](images/ListFreeTimeSequenceDiagram.png)

The `list-freetime DATE` command is facilitated by the `ListFreeTimeCommand`, `ListFreeTimeCommandParser`, along with the other internal classes omitted for brevity.
#### How is the command executed
1. The user inputs the `list-freetime DATE` command.
2. The `LogicManager` receives the command string and forwards it to the `AddressBookParser`.
3. The `AddressBookParser` checks the type of command and constructs `ListFreeTimeCommandParser` to parse the keyword(s).
4. The `execute` method of the `ListFreeTimeCommandParser` is called, which will call the `parseDate` method of the `TimeParser` API class.
5. `parseDate` will take in the date string as argument. If valid, it will return a `Time` instance containing a LocalDateTime containing the date information.
6. The `ListFreeTimeCommandParser` constructs `ListFreeTimeCommand` with the Time instance (created by `parseDate` earlier) passed into its constructor.
7. The `LogicManager` executes the `ListFreeTimeCommand` which calls the `Model#listPocketsOfTimeOnGivenDay(Time givenDay)` method.
8. The `ListFreeTimeCommand` will then use an internal method, formatFreeTime(List<Pair<Time, Time>> freeTimes), to parse the list of free times into a string.
9. The `ListFreeTimeCommand` construct `CommandResult` containing the free times on the given day, and returns it to `LogicManager`.
10. The GUI will be updated automatically by when the list changes.

#### Design consideration
Aspect: How the command finds free times:
* **Alternative 1 (current choice):** implement a method in the `ModelManager` class
    * Pros:
        * Can access all the interviews that the user has scheduled that is within the `AddressBook` object instance, which maintains the information hiding principle
    * Cons:
        * The command will cause the control to be passed to `ModelManager` first, then to `AddressBook` where the logic for looking for free times happens. This results in an additional layer of abstraction, which increases the complexity of the command slightly

* **Alternative 2:** Use the getter methods of the `AddressBook` class
    * Pros:
        * One less layer of abstraction, reducing complexity
        * Easier to implement
    * Cons:
        * The `ListFreeTimeCommand` will have to call the `getAddressBook` method of the `ModelManager` object instance, and then use the getter method of the `AddressBook` object instance. Violates the _Law of Demeter_ principle since the methods of a stranger (i.e. `AddressBook`) is being called, which `ListFreeTimeCommand` is not closely related to
        * Increases coupling since `ListFreeTimeCommand` now has a dependency with `AddressBook`
      
### List all interviews for today feature

#### Implementation
The list all interviews today feature allows users to list all the interviews that are scheduled on the day the user
executes the command. 

#### How is the command executed
1. The user inputs the `list-i-today` command.
2. The `LogicManager` receives the command string and forwards it to `AddressBookParser`.
3. The `AddressBookParser` checks the type of command and constructs a `ListInterviewsTodayCommand` instance.
4. `AddressBookParser` returns the `ListInterviewsToday` instance to `LogicManager`.
5. The `LogicManager` executes the `ListInterviewsToday` command.
6. The execute method in `ListInterviewsToday` calls the 
`Model#updateFilteredInterviewList(Predicate<Interview> predicate)` method and
passes in a predicate which tests if an interview is scheduled for today.
7. `ListInterviewsToday` constructs a `CommandResult` containing the success message 
and returns it `LogicManager`.
8. The GUI updates automatically when the list changes.

The following sequence shows how the `list-i-today` command works:
![ListInterviewsTodaySequenceDiagram](images/ListInterviewsTodaySequenceDiagram.png)

#### Design consideration
Aspect: Command format
* **Alternative 1 (current choice):** `list-i-today`
  * Pros:
    * Easy and fast to type
    * Easy to implement
  * Cons:
    * Can only list interviews today
    * Need to type a few hyphens which can be less intuitive than space

* **Alternative 2:** `list-i DATE`
  * Pros:
    * Can list interviews on any date
    * More flexible
  * Cons:
    * Can be annoying to type exact date
    * Listing interviews today requires the user to know the date today
    * Harder to implement
* **Alternative 3:** `list-i today`
  * Pros:
    * Some users may prefer typing spaces instead of hyphens
  * Cons:
    * Harder to implement as the parser needs to differentiate between 
`list-i` and `list-i today`
    * Some users may prefer the hyphen instead of a space






### List interviews done/not done feature

#### Implementation
The list interviews done/not done feature allows the user to see all the interviews that are done or not done in a single command. The command format is `list-i-done` to show all the interviews that are done, and `list-i-not-done` to show all interviews that are not done.

The Sequence Diagram below illustrates the interactions within the `Logic` component when `execute("list-i-done")` is called.
![ListIDone.png](images/ListIDoneSequenceDiagram.png)

#### How is the command executed
1. The user inputs `list-i-done` or `list-i-not-done` 
2. The `LogicManager` receives the command string and forwards it to the `AddressBookParser`.
3. The `AddressBookParser` checks the type of command and returns a `ListInterviewsDoneCommand` instance or `ListInterviewsNotDoneCommand` instance
4. The `LogicManager` executes the `ListInterviewsDoneCommand` or `ListInterviewsNotDoneCommand`
5. The `execute` method of `ListInterviewsDoneCommand` or `ListInterviewsNotDoneCommand` will call `Model#updateFilteredInterviewList(Predicate<Interview> predicate)`, where an `InterviewIsDonePredicate` or `InterviewNotDonePredicate` is passed as the argument
6. `Model#updateFilteredInterviewList` will be called with the given predicate, thus updating the internal `FilteredList` of interviews to show only those that are done, or those that are not done. The `CommandResult` containing the success message will be returned to `LogicManager`.
7. The GUI will be updated automatically by when the list changes.

#### Design consideration
Aspect: How the command is implemented
* **Alternative 1 (current choice):** Use the existing open-closed principle of AB3 to add these new commands
    * Pros:
        * Reduces this to a problem that AB3 has already solved
        * Maintains consistency with the AB3 format
        * Consequently, it is easy to implement
    * Cons:
        * More command classes have to be added, which can increase coupling

### Rate interview feature

#### Implementation
the rate feature allows users to assign a rating value to the specified interview via the command `rate INTERVIEW_INDEX RATING`, where `INTERVIEW_INDEX` must be a positive unsigned integer within the list and `RATING` must be a positive unsigned one decimal place number between 0.0 to 5.0 inclusive.

The `rate` feature is facilitated by the `Rating` class, `RateCommand` and the `RateCommandParser`. The `RateCommandParser` utilises the  `ParseUtil#parseIndex` and `ParseUtil#parseRating` to check the validity of the arguments given. Once the validity of the input is confirmed, the `Model#setInterview` will update the rating by replacing the old interview with the new interview that has the updated `RATING`.

#### How is the command executed
1. The user inputs the `rate` command together with the valid parameters.
2. The `LogicManager` receives the command string and forwards it to the `AddressBookParser`.
3. The `AddressBookParser` checks the type of command and constructs `RateCommandParser` to parse the parameters.
4. The `RateCommandParser` will pass the `INTERVIEW_INDEX` and `RATING` to the `ParseUtil` to check for validity and creates `RateCommand`.
5. The `LogicManager` executes the `RateCommand` which calls the `Model#getFilteredInterviewList` to get the size of the current list to ensure the `INTERVIEW_INDEX` is within the interview list size.
6. The `Model#setInterview` method will be called next in `RateCommand` to update the new interview with the new rating.
7. The `RateCommand` constructs `CommandResult`to return the success message to be displayed by the GUI.

The following sequence diagram shows how the `rate` operation works:

![RateInterviewSequenceDiagram](images/RateInterviewSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for RateCommandParser should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes a new command:

![RateInterviewActivity](images/RateInterviewActivityDiagram.png)

#### Design consideration
* **Alternative 1 (current choice):** Stricter rating value with specific format
    * Pros: 
      * Offer simplicity in terms of usage as the users does not have to come out with a rating system.
    * Cons: 
      * Less flexibility as the users are restricted to rate it within the valid range and format

* **Alternative 2:** Allow any rating value with no format restriction
  * Pros: 
    * Allow greater flexibility as the users can decide what is a good range for themselves.
  * Cons: 
    * The users’ responsibility is higher to ensure the rating system is suitable for ranking applicants.

### Sort interview feature

#### Implementation
The sort interview feature allows the user to sort all the interviews that have scheduled via the commands `sort-SORT_PARAMETER`, where `SORT_PARAMETER` can either be `rate` or `time`.

The `sort` command is facilitated by the `SortRateCommand` and the `SortTimeCommand`. It enables the user to sort all the scheduled interviews by rating or timing. For rating, the interviews will be sorted in descending order of rating. For interview times, the interviews will be sorted in ascending chronological order of start time.

The Sequence Diagram below illustrates the interactions within the `Logic` component when `execute("sort-rate")` is called.
![images/SortISequenceDiagram.png](images/SortISequenceDiagram.png)

#### How is the command executed
1. The user inputs the `sort-rate` or `sort-time` command
2. The `LogicManager` receives the `sort-rate` or `sort-time` command string and forwards it to the `AddressBookParser`.
3. The `AddressBookParser` checks the type of command and returns either `SortRateCommand` or `SortTimeCommand`.
4. The `LogicManager` executes the `SortRateCommand` or `SortTimeCommand` which calls the `Model#sortInterviewList(Comparator<Interview> comparator)` method.
5. The `SortRateCommand` or `SortTimeCommand` has their own `Comparator` object instance. Their respective comparators will be passed into the `Model#sortInterviewList(Comparator<Interview> comparator)`
6. `Model#sortInterviewList(Comparator<Interview> comparator)` will then call the `AddressBook#sortInterview(Comparator<Interview> comparator)`, passing in the given comparator as argument. 
7. `Model#sortInterviewList(Comparator<Interview> comparator)` will call the `UniqueInterviewList#sort(Comparator<Interview> comparator)`, which will call the built-in `FXCollections#sort(Comparator<T> comparator)` method, which will then sort the internal list of interviews by either rating or timing. Note that `FXCollections#sort(Comparator<T> comparator)` is used since the list of interviews is implemented as an `ObservableList`
8. The GUI will be updated automatically by when the list changes.

#### Design consideration
Aspect: How the sort command works
* **Alternative 1 (current choice):** Implement the sort method in the `ModelManager`, `AddressBook`, and the `UniqueInterviewList` class
    * Pros:
        * Does not violate the _Law of Demeter_ principle, since the sort is called through `Model` in the `SortRateCommand` or `SortTimeCommand`, where the `Model` instance is passed as a parameter to the respective `execute` commands
        * Does not violate the _information hiding_ principle. This is because the internal list of interviews is never accessed nor modified directly by the `execute` command of the respective command objects
    * Cons:
        * The command will cause the control to be passed to `ModelManager` first, then to `AddressBook` and then finally to `UniqueInterviewList` where only in `UniqueInterviewList` is the internal list sorted. This results in an additional layer of abstraction, which increases the complexity of the command slightly

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

* is a hiring manager working in Technology/Engineering field
* has a need to manage a significant number of job applicants and interviews
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: 
* Manage applicants and interviews faster than a typical mouse/GUI driven contact.
* Easy viewing of top applicants.
* Easy scheduling of interviews without time clash.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                      | I want to …​                             | So that I can…​                                                           |
|----------|--------------------------------------------------------------|------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | New user of the app                                          | see usage instructions                   | refer to instructions when I first started to use the App                 |
| `* * *`  | Engineering Hiring Manager ready for job applicant           | add a new applicant                      | save their contact details into the App                                   |
| `* * *`  | Engineering Hiring Manager with many applicants              | rate an applicant                        | keep track of the applicant performance for the interview                 |
| `* * *`  | Engineering Hiring Manager ready to start an interview       | schedule an interview                    | save the interview information into the application                       |
| `* * *`  | Engineering Hiring Manager ready for next round of interview | delete an applicant                      | remove their contact details that I no longer need                        |
| `* * *`  | Engineering Hiring Manager that finished an interview        | delete an interview                      | remove the interview that has already been completed                      |
| `* * *`  | Busy Engineering Hiring Manager                              | find an applicant by name                | locate details of applicants without having to go through the entire list |
| `* * *`  | Busy Engineering Hiring Manager                              | find a interview by job role             | easily locate the interview for the particular job role                   |
| `* *`    | Busy Engineering Hiring Manager                              | set reminder of interview                | stay organised and track upcoming interview                               |
| `* *`    | Engineering Hiring Manager with sensitive information        | hide private contact details             | protect the privacy of the applicants information in the App              |
| `* *`    | Engineering Hiring Manager with many applicants              | sort the applicants by skill             | prioritise and focus on the most promising candidates                     |
| `* *`    | Busy Engineering Hiring Manager                              | find free time slot                      | schedule an interview without time clash                                  |
| `* *`    | Engineering Hiring Manager                                   | update an applicant details              | easily update their information on the App                                |
| `* *`    | Engineering Hiring Manager                                   | update a job role                        | easily change the details about the job role                              |
| `* *`    | Engineering Hiring Manager with limited budget               | track the cost per hire                  | ensure that the company budget is not exceeded                            |
| `* *`    | Team-Oriented Engineering Hiring Manager                     | add other interviewer                    | facilitate collaboration and delegation of responsibilities               |
| `*`      | Organised Engineering Hiring Manager                         | sort applicants by name                  | locate an applicant easily                                                |
| `*`      | Engineering Hiring Manager with many contacts                | import contacts from other file          | add multiple contacts into the App smoothly                               |
| `*`      | Meticulous Engineering Hiring Manager                        | store the applicant's background         | make a more informed choice to benefit the company                        |
| `*`      | Engineering Hiring Manager with multiple rounds of interview | track the progress of each candidate     | maintain a clear overview of our recruitment efforts                      |
| `*`      | New Engineering Hiring Manager                               | analyse the performance of the interview | make improvements to my interview processes                               |
| `*`      | Helpful Engineering Hiring Manager                           | provide feedback to the applicant        | offer constructive advice to help them improve their skills               |
| `*`      | Long user of the app                                         | provide feedback to the developer        | suggest improvements and enhancements for the app                         |

### Use cases

(For all use cases below, the **System** is the `InterviewHub` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - View help**

**MSS**

1. User requests to view help.
2. InterviewHub displays the help instructors.

    Use case ends.

---

**Use case: UC02 - Clear all data**

**MSS**

1. User requests to clear all data.
2. InterviewHub deletes all data.

   Use case ends.

---

**Use case: UC03 - View all applicants**

**MSS**

1. User requests to list all applicants.
2. InterviewHub displays a list of all applicants.

   Use case ends.

---

**Use case: UC04 - View all interviews**

**MSS**

1. User requests to list all interviews.
2. InterviewHub displays a list of all interviews.

   Use case ends.

---

**Use case: UC05 - Exit the application**

**MSS**

1. User requests to exit the application.
2. InterviewHub terminates gracefully.

   Use case ends.

---

**Use case: UC06 Add a new applicant**

**MSS**

1. User requests to add a new applicant.
2. InterviewHub adds the new applicant.

    Use case ends.

**Extensions**
* 1a. The given command is invalid or one of the user-provided parameters is invalid.
    * 1a1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1b. The given applicant already exists in InterviewHub.
    * 1b1. InterviewHub shows an error message.

      Use case resumes at step 1.
---

**Use case: UC07 Add a new interview**

**MSS**

1. User requests to add a new interview.
2. InterviewHub adds the new interview.

   Use case ends.

**Extensions**
* 1a. The given command is invalid or one of the user-provided parameters is invalid.
    * 1a1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1b. The given interview already exists in InterviewHub.
    * 1b1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1c. The given interview causes a schedule clash.
    * 1c1. InterviewHub shows an error message.

      Use case resumes at step 1.
---

**Use case: UC08 Delete an applicant**

**MSS**

1. User <u> views the list of all applicants (UC03).</u>
2. User requests to delete a specific applicant.
3. InterviewHub deletes the specified applicant and any interviews the applicant was associated with.

    Use case ends.

**Extensions**

* 2a. The provided index is invalid.
  * 2a1. InterviewHub shows an error message.

    Use case resumes at step 2.

--- 

**Use case: UC09 Delete an interview**

**MSS**

1. User <u> views the list of all interviews (UC04).</u>
2. User requests to delete a specific interview.
3. InterviewHub deletes the specified interview.

   Use case ends.

**Extensions**

* 2a. The provided index is invalid.
    * 2a1. InterviewHub shows an error message.

      Use case resumes at step 2.

--- 

**Use case: UC10 Edit an applicant**

**MSS**

1. User <u> views the list of all applicants (UC03).</u>
2. User requests to edit a specific applicant.
3. InterviewHub edits the specified applicant and any interviews the applicant was associated with.

   Use case ends.

**Extensions**

* 2a. The given command is invalid or one of the user-provided parameters is invalid.
    * 2a1. InterviewHub shows an error message.

      Use case resumes at step 2.

* 2b. The edited applicant already exists in InterviewHub
    * 2b1. InterviewHub shows an error message.

      Use case resumes at step 2.

--- 

**Use case: UC11 Edit an interview**

**MSS**

1. User <u> views the list of all interviews (UC04).</u>
2. User requests to edit a specific interview.
3. InterviewHub edits the specified interview.

   Use case ends.

**Extensions**
* 2a. The given command is invalid or one of the user-provided parameters is invalid.
    * 2a1. InterviewHub shows an error message.

      Use case resumes at step 2.

* 2b. The edited interview already exists in InterviewHub.
    * 2b1. InterviewHub shows an error message.

      Use case resumes at step 2.

* 2c. The edited interview causes a schedule clash.
    * 2c1. InterviewHub shows an error message.

      Use case resumes at step 2.

* 2d. The selected interview to be edited is marked as done.
    * 2d1. InterviewHub shows an error message.

      Use case resumes at step 2.

--- 

**Use case: UC12 - Find applicants**

**MSS**

1. User requests to find applicants.
2. InterviewHub finds and displays the applicants that match the user provided input.

   Use case ends.

---

**Use case: UC13 - Find interviews**

**MSS**

1. User requests to find interviews.
2. InterviewHub finds and displays the interviews that match the user provided input.

   Use case ends.

---

**Use case: UC14 - Marking an interview as done**

**MSS**

1. User requests to mark an interview.
2. InterviewHub marks the selected interview as done.

   Use case ends.

**Extensions**

* 1a. The provided index is invalid.
    * 1a1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1b. The selected interview is already marked as done.
    * 1b1. InterviewHub shows an error message.

      Use case resumes at step 1.

---

**Use case: UC15 - Rating an interview**

**MSS**

1. User requests to rate an interview.
2. InterviewHub updates the rating of the selected interview.

   Use case ends.

**Extensions**

* 1a. The provided index is invalid.
    * 1a1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1b. The selected interview is not yet marked as done.
    * 1b1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1c. The provided rating is an invalid value
    * 1c1. InterviewHub shows an error message.

      Use case resumes at step1.

---

**Use case: UC16 - View all completed interviews**

**MSS**

1. User requests to list all completed interviews.
2. InterviewHub displays a list of all completed interviews.

   Use case ends.

---

**Use case: UC17 - View all incomplete interviews**

**MSS**

1. User requests to list all incomplete interviews.
2. InterviewHub displays a list of all incomplete interviews.

   Use case ends.

---

**Use case: UC18 - View all interviews scheduled for today**

**MSS**

1. User requests to list all interviews scheduled for today.
2. InterviewHub displays a list of all interviews scheduled for today.

   Use case ends.

---

**Use case: UC19 - View all free time on a given date**

**MSS**

1. User requests to list all free time on a given date.
2. InterviewHub displays a list of all free time on a given date.

   Use case ends.

**Extensions**

* 1a. The provided date is invalid.
    * 1a1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1b. The provided date is in the past.
    * 1b1. InterviewHub shows an error message.

      Use case resumes at step 1.

* 1c. The provided date contains no free time.
    * 1c1. InterviewHub shows a blank message to denote that there is no free time.

      Use case ends.

---

**Use case: UC20 - Sort interviews in descending order of rating**

**MSS**

1. User requests to sort interviews in descending order of rating.
2. InterviewHub sorts the current list of interviews in descending order of rating.

   Use case ends.

---

**Use case: UC21 - Sort interviews in chronological order**

**MSS**

1. User requests to sort interviews in chronological order.
2. InterviewHub sorts the current list of interviews in chronological order.

   Use case ends.

---

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to handle as many applicants as the user is able to manage in their workday/workweek.
3.  The app should be reasonably responsive to the entire set of user requests(i.e. within 1 second at maximum load).
4.  The system should have an interface that is very easy to pick up for our target audience(i.e. Engineering Hiring Managers
that have many years of programming experience).

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X/MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Applicant**: The applicant applying to a particular job role.
* **Hiring manager**: The manager interviewing the applicant.
This manager is familiar with the technical aspects of the role. Also called engineering hiring manager.
* **MSS**: Main Success Scenario. It describes the most straightforward
interaction in a use case where nothing goes wrong.
* **Extensions**: In a use case, an extension describes an alternative flow of events
that are different from the MSS.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancement**
1. The current error message when adding an interview where the start and/or end date string is a valid date but is missing time is too general.
We plan to make the error message also mention why the command is not accepted and the reason for the failure:
Please enter an interview time!

2. The current implementation of mark-i allows the user to mark interviews before the interview has been completed,
based on the end time of the interview. We plan to not allow the user to mark interviews that have not passed.
So, interviews whose end time is after the current time cannot be marked as done.

3. The current implementation of the find-i and find-a commands does not display a constant status of whether the find
filter is being applied to the current list. E.g. When you perform a find-i command followed by some other command that
changes the interview list, you have no way to tell if the previous "find-i" filter is still being applied.
We plan to update the UI to display a constant status of what filter is currently being applied to the applicant and
interview list.

4. The current implementation of the rating will set it to 0.0 when an interview object is first created. This is not ideal as
the user is unable to differentiate whether the interview rating is truly 0.0. Therefore, the future plan is displaying the preset rating
as N/A and the subsequent user rating will be the same as the current model.

5. The current implementation of applicant allows duplicate phone number and duplicate email to be entered into the system. Moreover, it prohibits
name with the exact same string to be added. As the interview is identifiable mostly based on the name, allowing the same name string will result in
confusion with the interview object. The future plan is to increase the visibility of the connection between applicant and interview, to remove dependency on the name itself.
On the other hand, the duplication of phone number and email will be handled in future implementation and no longer allowing such duplication to be entered.

6. The current implementation of find-i does not allow searching for an empty string, which represents the job role when an applicant is applying to the
company in general. We plan to allow find-i to search for "" (empty string) and " " (whitespaces) so that interviews with applicants that are applying to the
company in general can be found with the command.

7. Currently there is no way to unmark an interview after marking the interview since an interview's status cannot change once it is done. 
However, some users may accidentally mark an unfinished interview as done. In the future, an unmark feature will
be implemented to allow users to change the status of interviews from done to not done.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
Please refer to the [User Guide](https://ay2324s1-cs2103t-t11-2.github.io/tp/UserGuide.html) if you need more information on the command constrains
</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Viewing help
Command: `help`

1. Test case: `help`<br>Expected: Help window with the User Guide URL is displayed. Status message remained unchanged.

### Clearing all the data
Command: `clear`

1. Test case: `clear`<br>Expected: All the applicants and interviews will be cleared. Success message is displayed.

### Exiting the program
Command: `exit`

1. Test case: `exit`<br>Expected: Exits InterviewHub and all data is saved automatically.

### Adding an applicant
Command: `add-a`

1. Adding an applicant
   1. Test case: `add-a n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/engineer t/frontend` <br>
       Expected: An applicant `John Doe` is added with the phone number `98765432`, email `johnd@example.com`, 
       address `311, Clementi Ave 2, #02-25` and the tags `engineer` and `frontend`.
   2. Test case: `add-a n/John Doe` <br>
       Expected: No applicant is added. Error details shown in the status message.

### Deleting an applicant
Command: `delete-a`

1. Deleting an applicant while all applicants are being shown
    1. Prerequisites: List all applicants using the `list-a` command. Multiple applicants in the list.
    2. Test case: `delete-a 1` <br>
       Expected: First applicant is deleted from the list. Details of the deleted applicant shown in the status message. 
    3. Test case: `delete-a 0` <br>
       Expected: No applicant is deleted. Error details shown in the status message.
2. Deleting an applicant while no applicants are in the list
   1. Prerequisites: Clear all applicants using the `clear` command. List is empty.
   2. Test case: `delete-a 1` <br>
      Expected: No applicant is deleted. Error details shown in the status message. 

### Editing an applicant
Command: `edit-a`

1. Editing an applicant while all applicants are being shown
   1. Prerequisites: List all applicants using the `list-a` command. Multiple applicants in the list.
   2. Test case: `edit-a 1 n/Bob` <br>
       Expected: First applicant's name is edited to `Bob`. Details of the edited applicant shown in the status message.
   3. Test case: `edit-a 0` <br>
       Expected: No applicant is edited. Error details shown in the status message.
   4. Test case: `edit-a 1` <br>
       Expected: No applicant is edited. Error details shown in the status message.

### Finding applicants from the list
Command: `find-a`

1. Finding an applicant while all applicants are being shown
   1. Prerequisites: List all applicants using the `list-a` command. Multiple applicants in the list. 
    One of the applicants is named `John Doe`. Another applicant `Alex Yeoh` has phone number `87438807`. 
    No other applicant has `John` as part of their name. No other applicant has the number `874` as part of their phone number.
   2. Test case: `find-a n/John p/874` <br>
       Expected: Two applicants are shown in the applicant list. The applicants shown are `John Doe` and `Alex Yeoh`.
        The number of applicants found is shown in the status message.
   3. Test case: `find-a` <br>
       Expected: List shown on GUI does not change. Error details shown in the status message.
   4. Test case: `find-a n/` <br>
      Expected: List shown on GUI does not change. Error details shown in the status message.

### Listing all applicants
Command: `list-a`

1. Listing all the applicants
   1. Test case: `list-a`<br>Expected: Shows all the applicants in the applicant list. Success message is displayed.

### Adding an interview
Command: `add-i`

1. Adding an interview
    1. Prerequisites: List all applicants using the `list-a` command. Have at least one applicant that does not have an interview scheduled.
       Ensure that the input applicant ID chosen for step `ii` belongs to an applicant which does not have an interview scheduled. (The border around the applicant should be red)
    2. Test case: `add-i app/3 jr/Software Engineer start/12-12-2024 1500 end/12-12-2024 1600` <br>
        Expected: An interview is added with the applicant at the target index on the applicant list, with the
        job role `Software Engineer`, starting datetime of `12-12-2024 1500` and ending datetime of `12-12-2024 1600`.
    3. Test case: `add-i jr/PE Tester` <br>
       Expected: No interview is added. Error details shown in the status message.

### Deleting an interview
Command: `delete-i`

1. Deleting an interview while all interviews are being shown
    1. Prerequisites: List all interviews using the `list-i` command. List all applicants using the `list-a` command. Multiple interviews in the list.
    2. Test case: `delete-i 1` <br>
       Expected: First interview is deleted from the list. Details of the deleted interview shown in the status message.
       The corresponding applicant associated with that interview should have their border turn from green to red
    3. Test case: `delete-i 0` <br>
       Expected: No interview is deleted. Error details shown in the status message.
2. Deleting an interview while no interviews are in the interview list
    1. Prerequisites: Clear all interviews (and applicants) using the `clear` command (Note that this is irreversible). Interview list is empty.
    2. Test case: `delete-i 1` <br>
       Expected: No interview is deleted. Error details shown in the status message.

### Editing an interview
Command: `edit-i`

1. Editing an interview while all interviews are being shown
    1. Prerequisites: List all interviews using the `list-i` command. Multiple interviews in the list.
       Ensure that the selected interview index chosen for step `ii` belongs to an interview that is not marked as done (border around interview is red). 
    2. Test case: `edit-i 2 jr/PE Tester` <br>
       Expected: The second interview's job role is edited to `PE Tester`. Details of the edited interview shown in the status message.
    3. Test case: `edit-i 0` <br>
       Expected: No interview is edited. Error details shown in the status message.
    4. Test case: `edit-i 1` <br>
       Expected: No interview is edited. Error details shown in the status message.

### Finding interviews from the list
Command: `find-i`

1. Finding interviews by job role
   1. Prerequisite: The interview with the indicated job role has matching keyword
   2. Test case: `find-i software`<br>Expected: All interviews with at least one `software`(case-insensitive) as keyword in the job role are shown in the interview list. The amount of interview listed is shown on the status box.
   3. Test case: `find-i` <br>Expected: The interview list remained unchanged. Error message is displayed.

### Listing all interviews
Command: `list-i`

1. Listing all the interviews
   1. Test case: `list-i`<br>Expected: Shows all the interviews in the interview list. Success message is displayed.

### Listing all free timing for the given day
Command: `list-freetime`<br>

1. Listing the free time for a day using `DD/MM/YYYY` or `DD-MM-YYYY` format
   1. Prerequisites: The date string must be in the DD/MM/YYYY or DD-MM-YYYY format, and there should not be any interviews scheduled for the given date
   2. Test case: `list-freetime 12/12/2099`<br>Expected: There should be 1 block of free time listed, which will span from 9am to 5pm on 12/12/2099
   3. Test case: `list-freetime 12-12-2099`<br>Expected: There should be 1 block of free time listed, which will span from 9am to 5pm on 12/12/2099
   4. Success message shown in command box for both cases:<br> `Free times on 12/12/2099:`<br>`from: 09:00 to: 17:00`
2. Listing the free time for a day using `DD/MM` or `DD MMM` format, where 
   1. Prerequisites: The date string must be in the DD/MM or DD MMM format, and there should not be any interviews scheduled for the given date
   2. Test case: `list-freetime 12/12`<br>Expected: There should be 1 block of free time listed, which will span from 9am to 5pm on 12/12/2023
   3. Test case: `list-freetime 12 Dec`<br>Expected: There should be 1 block of free time listed, which will span from 9am to 5pm on 12/12/2023
   4. Success message shown in command box for both cases:<br> `Free times on 12/12/2023:`<br>`from: 09:00 to: 17:00`
3. Listing the free time for a date in the past
   1. Prerequisite: The date string must follow one of the accepted date formats
   2. Test case: `list-freetime 12-12-1970`<br>Expected: Command does not execute.
   3. Error message shown in command box:<br>`Input date cannot be in the past!`

### Listing all interviews for today
Command: `list-i-today`
1. Listing all interviews today
    1. Test case: `list-a`<br>Expected: Shows all interviews scheduled for today (i.e. the date when the command was executed) in the interview list. Success message is displayed.

### Marking an interview as done
Command: `mark`

### Rating an interview
Command: `rate`

1. Rating an existing interview
   1. Prerequisites: the interview to be rated has been marked as done, and it exists in the list.
   2. Test case: `rate 1 2.0`<br>Expected: The interview rating at index one has been updated to 2.0. Success message is displayed.
   3. Test case: `rate 1 10.0`<br>Expected: The interview rating at index one is not changed. Error message is displayed.

### Listing all completed interview
Command: `list-i-done`

1. Listing completed interviews with exact command
    1. Test case: `list-i-done`<br>Expected: The interview list should only show interviews which have been completed (i.e. those that are green). If there are no completed interviews, the interviews box will be empty 
2. Listing completed interviews with the command along with nonsensical parameters appended to the end
   1. Test case: `list-i-done I am a cat`<br>Expected: Same expected result as test case 1
   
### Listing all incomplete interview
Command: `list-i-not-done`

1. Listing completed interviews with exact command
    1. Test case: `list-i-not-done`<br>Expected: The interview list should only show interviews which have been completed (i.e. those that are green). If there are no completed interviews, the interviews box will be empty
2. Listing completed interviews with the command along with nonsensical parameters appended to the end
    1. Test case: `list-i-not-done I am a cat`<br>Expected: Same expected result as test case 1
   
### Sorting the interview list by rating
Command: `sort-rate`

1. Sorting the current interview list by rating.
   1. Test case: `sort-rate`<br>Expected: Sorts the current interview list by rating in descending order. Success message is displayed.

### Sorting the interview list by start-time
Command: `sort-time`

1. Listing completed interviews with exact command
   1. Test case: `sort-time`<br>Expected: The interview list will be sorted in chronological order of start times. If there are no interviews scheduled, the interviews box will be empty. In the case that the list is filtered in some way, the sort will only sort on the filtered interview list
2. Listing completed interviews with the command along with nonsensical parameters appended to the end
    1. Test case: `sort-time I am a cat`<br>Expected: Same expected result as test case 1
