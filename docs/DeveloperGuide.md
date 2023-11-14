---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# FreelanceBuddy Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

Application icon was taken from [Flaticon](https://www.flaticon.com/free-icon/duck_1993713?term=duck&page=1&position=6&origin=tag&related_id=1993713)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

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

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component" width="1000"/>

In the class diagram above, it is showing the state where the `Finance` tab is selected.

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ContactsTab`, `FinanceTab`, `EventsTab`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

Depending on the Tab currently selected by the user, the `MainWindow` will display the corresponding `PersonListPanel`, `FinanceListPanel` or `EventListPanel`.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<box type="info" seamless>

In the future, we aim to be able to abstract out the Card, ListPanel, and Tab (UI) accordingly, to make adding tabs much easier and convenient.

</box>

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component within a Tab:

<puml src="diagrams/LogicClassDiagram.puml" width="750"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.</br>
**Note:** This takes place within the ContactsTab. 

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteContactCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:
1. All three tabs have their own `Logic` objects. These `Logic` objects share the same `Model` and `Storage`, but have different `Parsers`
1. When `Logic` is called upon to execute a command, it is passed to that Tab's `Parser` (e.g. in our class diagram above the `ContactParser` receives the command). 
1. This parser then creates a parser that matches the command (e.g., `DeleteContactCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteContactCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a contact).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

<div style="page-break-after: always;"></div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the respective Parsers (i.e. `ContactParser`, `FinanceParser`, `EventParser`) class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddEventCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddContactCommand`) which the `ContactParser` returns back as a `Command` object.
  * `DateTimeParser` is included here to show all Parser Classes. However, only a few Command Parsers interact with the `DateTimeParser` (when date-time inputs are involved).
* All `XYZCommandParser` classes (e.g., `AddContactCommandParser`, `DeleteFinanceCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W09-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="600" />


The `Model` component,

* stores the address book data, event book data and finance book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable i.e., `ObservableList<Person>` where `Person` objects can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change. This is the same for `Event` and `Finance`.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other seven components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div style="page-break-after: always;"></div>

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below.


**For AddressBook:**

<puml src="diagrams/BetterModelClassDiagramAddressBook.puml" width="450" />

**For EventsBook:** note that multiple `Person` objects are can be associated to an Event as clients

<puml src="diagrams/BetterModelClassDiagramEventsBook.puml" width="450" />

**For FinanceBook:**

<puml src="diagrams/BetterModelClassDiagramFinancesBook.puml" width="450" />


</box>

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="1000" />

The `Storage` component,
* can save the following data in JSON format, and read them back into corresponding objects.
  * address book data
  * events book data
  * finance book data
  * user preference data
* inherits from `AddressBookStorage`, `EventsBookStorage`, `FinanceBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add Finance feature

In FreelanceBuddy, the creation of Finance entries (commissions and expenses) includes validation for clients. In the case that the user attempts to create a Finance entry with an associated client, FreelanceBuddy will check if the client exists in the Contacts Tab and only create the entry if it does.

Both `Commission` and `Expense` classes have the same attributes as `Finance` (as a result of inheritance), while both `AddCommissionCommand` and `AddExpenseCommand` classes handle the validation in similar ways. Hence, for simplicity, we will only discuss how a `Commission` is added to the `Model`, as the implementation is similar for Expense.

Given below is an example usage scenario and how the command behaves at each step.

**Step 1.** We assume that a client named `John` already exists in the Contacts Tab. The user attempts to create a new Commission from `John Doe`, with the amount `20` and the description `Chatbot`.
He enters the command `add-c c/John a/20 d/Chatbot` in the Finance Tab. The `LogicManager` passes this to the `FinanceParser`, which identifies it as a command to add a commission. Then it creates a `AddCommissionCommandParser` and calls the `AddCommissionCommandParser#parse()` method.

<puml src="diagrams/AddFinanceSequenceDiagram1.puml" alt="AddFinanceSequenceDiagram1" />

**Step 2.** The `AddCommissionCommandParser` parses the given command into an `AddCommissionCommand` with a `Commission` that contains the following attributes: `Amount` containing `20`, `Description` containing `Chatbot`, `TimeDue` containing the time of creation (by default), and most notably, a **dummy** `Person` object containing placeholder values for all its attributes other than the name which is `John Doe`.
This dummy `Person` object is important as it allows us to fetch the **actual** `Person` object from the `Model` later on.

<box type="info" seamless>

**Note:** Only the instantiation of the dummy `Person` object is included in the diagram.

</box>

<puml src="diagrams/AddFinanceSequenceDiagram2.puml" alt="AddFinanceSequenceDiagram2" />

**Step 3.** The `LogicManager` then calls `AddCommissionCommand#execute()`. If the client exists (this is always the case for `Commission`), it will call the `Model#isValidClient()` method to check if the client exists in `Model`. If it does not, a `CommandException` will be thrown.

**Step 4.** Having verified that the client exists in `Model`, `AddCommissionCommand` then calls the `Model#getMatchedClient()` method to fetch the actual `Client` object in `Model`. This `Client` field in the `Commission` is then set to this actual `Client`.

**Step 5.** Lastly, the `Commission` in `AddCommissionCommand` is added to the `Model`, while returning a `CommandResult` with the details of the `Commission` formatted into a result `String`. This result `String` is then printed in the status box.

<puml src="diagrams/AddFinanceSequenceDiagram3.puml" alt="AddFinanceSequenceDiagram3" /> 

<div style="page-break-after: always;"></div>

### Filtering Lists

In FreelanceBuddy, we have multiple commands that make use of a `Predicate` to filter the lists. </br>
(e.g. `filter-c`, `filter-t`, `summary`...) 

In this example, we will use the `summary` command from the finance tab to demonstrate how FreelanceBuddy 
uses a `Predicate` to update a `FilteredList` in the `Model`. 

Given below is an example usage scenario and how the command behaves at each step.

**Step 1.** We assume that the finance tab is already populated with finances from multiple clients. The user now wants to 
generate a summary report for the client `John Doe`. He enters the command `summary John Doe` into the command line. 

**Step 2.** A `ClientNameExactMatchPredicate` is created with the argument `"John Doe"`. This predicate will be used to
filter the `financeList` later on.

<puml src="diagrams/SummarySequenceDiagram1.puml" alt="SummarySequenceDiagram1" />

<box type="info" seamless>

**Note:** The activation bar of the LogicManager continues to the next diagram.

</box>

**Step 3.** `SummaryCommand` is then executed by the `LogicManager`. The model updates the `financeList` with the given
`namePredicate` (i.e. `ClientNameExactMatchPredicate("John Doe")`). The `filteredFinances` list is then fetched from the model.

**Step 4.** With the `filteredFinances`, we then call the static `FinanceSummary#generateSummary` method to create a summary
report. The method returns the summary as a string which is then passed as an argument into the `CommandResult` constructor.

<puml src="diagrams/SummarySequenceDiagram2.puml" alt="SummarySequenceDiagram2" />

<box type="info" seamless>

**Note:** The activation bar of the LogicManager continues from the previous diagram.

</box>

**Step 5.** The summary report is then displayed in the status box. The list of finances displayed in the finance tab will
also update to only show finances related to the given client. 

<div style="page-break-after: always;"></div>

### Changing Tabs

Use of the `tab` command to switch between tabs in the UI. 

Currently, the application uses the existing [TabPane from JavaFX](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TabPane.html), and it makes use of the tab index to switch tabs. 

#### Tab object

The `commons.core.Tab` object stores both the index and tab name. It also contains the standard index and tab name constants of each tab which can be used by other classes.

<box type= "tip" seamless>

You will need to update these constants in `commons.core.Tab` if you wish to add/edit/delete a tab from the application. 
</box>

To create the `Tab` object, you need to use the `Tab#fromParameter("tabName")` as the constructor is private.
Note that the `Tab` object will convert and store the index based on the given tab name(assuming it is valid).

<box type= "warning" seamless>

Note that `ui.ContactsTab`, `ui.EventsTab` and `ui.FinanceTab` does not implement from `core.tab.Tab` as they have different purposes.
</box>

#### Usage scenario

Given below is an example usage scenario and how the tab mechanism behaves at each step.

**Step 1.** The user launches the application, and is on the contacts tab.  

**Step 2.** The _user executes_ `tab events` command to switch from the contacts tab to the events tab.
The `TabCommandParser` creates the `Tab` object using the `ParserUtil#parseTab("events")` method.

**Step 3.** The method _checks if it is a valid input_ using `Tab#isValidParameter("events")` **before** creating the `Tab` object.

**Step 4.** The `Tab` object _converts the given parameter into the appropriate tab index_, and stores both the parameter and index within the object. 
The `Tab` object is passed from the `TabCommandParser` to the `TabCommand`, 
which returns the tab index from the `Tab` object using `Tab#getZeroBasedTabIndex()`.

In this case, the return value is 2 since the index for the Events Tab is 2. 

**Step 5.** The `ContactsTab` switches tab in the GUI using the `ContactsTab#handleTabChange(2)`

The following sequence diagram represents the user is changing from the Contacts tab to the Events tab as mentioned above.

<puml src="diagrams/ChangeTabSequenceDiagram.puml" alt="ChangeTabSequenceDiagram" />

The following activity diagram summarise what happens when a user changes a tab.

<puml src="diagrams/TabActivityDiagram.puml" alt="TabActivityDiagram" />

<div style="page-break-after: always;"></div>

### Date-time Parsing

Other than the basic understanding of how the Date-time inputs are determined, the rules and assumptions it has. [Read here for more info: Accepted Date-time Formats](https://ay2324s1-cs2103t-w09-2.github.io/tp/UserGuide.html#accepted-date-time-formats).
It would be important to understand the inner-workings of the date-time parser to appreciate certain design choices made when implementing features.

#### Motivations Behind the Design

The choice to implement a more advanced date-time parser than what is available in the Java `LocalDateTime` library or other external libraries is:
1. **Convenient** and **faster** input of date-time input.
2. **More conventional** date-time formats (akin to verbal communication)
3. **Smarter** date-time parser that helps **predict** dates or time that are *usually* assumed.
4. date-time parser that is **fast** and **lean** – does not require unnecessarily long inputs from user that may be ambiguous.

##### Accepting More `DateTimeFormatter` Formats

First, we start off by accepting more formats of `Date` and `Time` that Java's `LocalDateTime` library provides (Numbered Date-time formats). All summarised in the [User Guide](https://ay2324s1-cs2103t-w09-2.github.io/tp/UserGuide.html#accepted-date-time-formats).

Parsing for this is done by "brute force" handled by the number of elements the date-time format has (the reason it is done this way will be explained in a later section):

<div style="page-break-after: always;"></div>

To illustrate how parsing of multiple formats is done, the sequence diagram shows `parseTwoElementsNumberTimeFormat()`, one of the methods that parses multiple formats, and how it matches the input with a format and parses it:

<puml src="diagrams/ExampleParsingNumberFormats.puml" alt="ExampleParsingNumberFormats" />

In words:
1. `DateTimeParser` will call the relevant `private static` method to parse the respective input (in this case `parseTwoElementsNumberTimeFormat()`)
2. It will check if the input is of the right length. (Omitted above as it is not as important).
3. For this particular example, there are 3 formats of `LocalTime`, the program will loop through each format. (`h a`, `h:mm a` and `h.mm a`)
4. A `DateTimeFormatter` is built to be case-insensitive, with the format at that point of execution and to language English (relevant to formats that have letters).
5. `LocalTime.parse()` is called with the `DateTimeFormatter` and the program attempts to parse the input with the specified format.
6. If parse is successful, a `LocalTime` instance is returned.
7. Else, the program catches the exception and continues.
8. If nothing is returned after all formats have been looped through, the method returns `null` to indicate a failed parse.

This method is repeated with the other Numbered Time and Numbered Date formats. 

> Of note as well, is the formats that do not contain year inputs e.g. `d/M`, `d MMM`, etc. In these cases, the year will be set to the current year that the method is called.
> This is done with building the `DateTimeFormatter` with the year using method `parseDefaulting(ChronoField.YEAR, getToday().getYear())`

With this, this will enable date-time inputs to be **faster** and **more convenient** as users have a wide variety of formats to choose from.

<div style="page-break-after: always;"></div>

##### Accepting More Formats - Natural Language

Another feature we wanted to achieve is for users to use natural language formats that maybe not be as easily expressed in a date or a time.
Formats that are usually used when conversing. As there are a lot of possibilities it is impossible to accept all forms, we had to sieve out several more important formats that Freelancers may use more often.

**Common references of time**: noon, midnight, in _ minutes.

**Common references of dates**: tomorrow, next week, next Monday, in _ weeks.

**Common references that imply both date and time**: now, _ from now.

These make sense as scheduling meetings with a client may sound like: *"Let's follow-up next week, 4:30pm?"*

Similarly, we can achieve this by parsing these inputs based on the number of words it has. For natural language formats as you can from the [User Guide](https://ay2324s1-cs2103t-w09-2.github.io/tp/UserGuide.html#accepted-date-time-formats),
has formats that correspond to the number of words (For example, 3 word date formats are in the formt of "in _ days/months/years"). Hence, we parsed these inputs using switch cases.

To explain, we have described the implementation of one of methods that parse english date time formats, `parseThreeElementsEnglishDateFormat()` in the activity diagram below:

<puml src="diagrams/EnglishFormatsParsingActivityDiagram.puml" alt="ParsingAD" />

<div style="page-break-after: always;"></div>

##### Parsing Date-Time Instance

Now we have built the base of how to parse Date, Time and Date Time inputs (both Numbered and Natural Language formats) by their elements. We need a master method to pull all these together. 
Given a string, how does the program choose the right kind of methods to parse the input?

The `parseDateTimeInstance()` method brings together all of this to parse any given string.

To demonstrate how a string is parsed into a `LocalDateTime` value given that it can be any combination of `Date`, `Time` or `DateTime` formats. We use the below activity diagram to demonstrate:

<puml src="diagrams/ParseDateTimeInstanceAD.puml" alt="DTIAD" />

<div style="page-break-after: always;"></div>

To further explain what happens within a "Parse X Inputs" we take a look at one of the implementations, `parseThreeElements`.

<puml src="diagrams/ParseThreeElementsAD.puml" alt="ParseThreeElementsActivityDiagram" width="600"/>

As you can see this is how given the number of words of input, date-time can be parsed even if input can be a date, time or date-time.

<div style="page-break-after: always;"></div>

**On a higher level, a general flow will look something like this:** (note that this is greatly simplified)

<puml src="diagrams/InstanceDateTimeHighLevel.puml" alt="InstanceDateTimeHighLevel" />

<div style="page-break-after: always;"></div>

##### Parsing Smartly

In one the above diagram where we talked about `parseThreeElements()` we got a glimpse of how the parser parses date-time inputs smartly.

In the event either the date or time is not specified, the parser will assume with a "future-bias" prediction on the unspecified date or time.

For example, when time is not specified, it will be set to the 00:00, signifying the start of the day. Likewise, if the date is not specified, the next occurrence of the date with the specified time will be chosen.

****Parsing Durations****

This is further shown when parsing durations using `parseDateTimeDuration()` 
1. When parsing `<TIME>` to `<TIME>` only durations, date will be set to next occurrence of the end time. (Future-Bias assumptions)
2. When parsing `<DATE>` to `<DATE>` only durations, time will be set from 00:00 for start date and 23:59 for end date. (Assuming whole day durations)
3. When parsing `<DATE><TIME>` to `<TIME>`, time will be assumed to be the same date as the start `<DATE>`. (Conventional way of communicating e.g. Next Monday 4-6pm)

For more information on the assumptions that the parser makes when there are missing `<DATE>` or `<TIME>` inputs for either start or end time, you can check out the User guide section for this [here](https://ay2324s1-cs2103t-w09-2.github.io/tp/UserGuide.html#accepted-date-time-combinations-of-s-and-e).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Planned Enhancements**

In this section, we will go through some current Feature Flaws that FreelanceBuddy currently has and our plans to fix it.

#### 1. Lack of cross-tab updating

Currently, when a user deletes or edits a client in the contacts tab, this change is not reflected in the Finance and Events
tabs. For example, when the client `John Doe` is deleted from our contacts tab, Finances and Events that were previously tagged
with the client `John Doe` will remain unaffected. The user will have to manually change the entries in the Finance and Events
tab to reflect the necessary changes. 

We plan on making the edits and deletes from the contacts tab cascade to the other tabs. This will ensure that changes made
in the contacts tab will be reflected in the other two tabs, avoiding any potential confusion. 

#### 2. Missing client validation when loading from storage

Currently, we do not perform any client validation checks on data that is being loaded in from the json files. For example, 
if the user decides to edit a Finance entry from the json files, they will be able to change the tagged client to a client 
that does not exist in their contacts.

We plan on adding an extra layer of client validation checks for when data is being loaded in from the storage. This will
help to ensure that the data remains consistent even if the user decides to manually change the json files. 

#### 3. Missing protection for preferences and config json files

Currently, if user changes the values in the `preferences.json` or `config.json` files to an illegal value, this will cause 
the application to not be able to start. For example, if the user changes the `userPrefsFilePath` value in the `config.json`
file to `null`, the application will not be able to start. 

We plan on handling this issue by resetting the files to their default values if an invalid input is detected. This will
prevent a scenario where the user is unable to launch the app.

#### 4. Duplicates of contact names

Currently, the system allows duplicate contact names due to case sensitivity,
which is inconsistent with real-world scenarios where case sensitivity isn't considered.

We plan to implement a case-insensitive check for contact names to prevent duplicate entries.
Freelancebuddy will recognize "John" and "john" as the same entry, ensuring uniqueness irrespective of case,
preventing the addition of duplicates names based solely on case variation.


#### 5. Duplicates of contact phone numbers
Currently, the system allows duplicate contact phone numbers
which is inconsistent with real-world scenarios where phone numbers are unique and tied to a single person.

We plan to implement a check for contact phone numbers to prevent duplicate entries, including phone numbers which include country codes
For example, `+6598765432` will be considered the same as `98765432`, where `+65` is the country code for Singapore.

#### 6. Duplicates of contact telegram names
Currently, the system allows duplicate telegram names
which is inconsistent with real-world scenarios where telegram names are unique and tied to a single person.

We plan to implement a check for telegram names to prevent duplicate entries.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**: Freelancers

| Characteristics                                   | What **FreelanceBuddy** offers                                                                                              |
|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| May have multiple concurrent clients and projects | One stop app for managing your contacts, finances and day-to-day events in a easy and structured way                        |
| Good at touch-typing, efficient with keyboard     | CLI interface enables quick input and retrieval of your contacts, finances and events without the need of moving your mouse |
| Potentially busy and want quick updates           | Users can get summary statistics, organise their data and much more all with just one command                               |

**Value proposition**: Manage all your freelancing needs in an app that is faster than your typical GUI driven apps.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

#### General

| Priority |  As a …​  | I want to …​                          | So that I can…​                                                                                   |
|:--------:|:---------:|---------------------------------------|---------------------------------------------------------------------------------------------------|
| `* * *`  | new user  | see usage instructions                | refer to instructions when I forget how to use the App                                            |
| `* * *`  |   user    | switch between the different tabs     | view Contacts, Events and Finances in their respective tabs                                       |
|  `* *`   |   user    | see an overview of all my tabs        | quickly get a look at the important details without needing to navigating into tabs unnecessarily |
|   `* `   | lazy user | FreelanceBuddy to predict my commands | quickly input commands without typing the entire command                                          |

<div style="page-break-after: always;"></div>

#### For Client Contact Management

| Priority |     As a …​      | I want to …​                            | So that I can…​                                                     |
|:--------:|:----------------:|-----------------------------------------|---------------------------------------------------------------------|
| `* * *`  |       user       | add a new client contact                |                                                                     |
| `* * *`  |       user       | delete a client contact                 | remove entries that I no longer need                                |
| `* * *`  |       user       | view all my saved clients contact       | have an overview of all my clients contacts                         |
| `* * *`  |       user       | find a client contact by name           | locate clients by name without having to go through the entire list |
|  `* *`   |       user       | edit a saved client contact             | change any details that are wrong or have changed                   |
|  `* *`   | experienced user | see statistics of a client              | see how much money and time is spent on respective client projects  |
|  `* *`   |       user       | add favourite clients                   | see all important clients in the same place                         |
|   `*`    |  long-term user  | see if clients have been worth the time | better choose my clients for the future                             |
|   `*`    |  long-term user  | be able to manage client notes          | keep important notes about clients for future references            |
|   `*`    |       user       | create invoices with client details     | save time with manual inputting of client details                   |

<div style="page-break-after: always;"></div>

#### For Finance Management

> This covers both commission and expense. We will refer to both as C/E.

| Priority |        As a …​         | I want to …​                                    | So that I can…​                                              |
|:--------:|:----------------------:|-------------------------------------------------|--------------------------------------------------------------|
| `* * *`  |          user          | add a new C/E                                   |                                                              |
| `* * *`  |          user          | delete an old C/E                               | remove entries that I no longer need                         |
| `* * *`  |          user          | view all my saved C/E                           | have an overview of all my C/E                               |
|  `* *`   |          user          | filter by C/E                                   |                                                              |
|  `* *`   |          user          | edit a saved C/E                                | change any details that are wrong or have changed            |
|  `* *`   | user with many clients | tag clients to C/E                              | see which client is involved in a particular C/E             |
|  `* *`   | user with many clients | filter by tagged client                         |                                                              |
|  `* *`   | user with many clients | get a summary of total C/E by tagged client     | know how valuable a client is                                |
|  `* *`   | financially savvy user | add time due for C/E                            | know when to expect cash inflow/outflow                      |
|  `* *`   | financially savvy user | filter by a timeframe                           | know what C/E i received in that given timeframe             |
|  `* *`   | financially savvy user | get a summary of total C/E in a given day/month | get an idea of my financial situation for the time period    |

<div style="page-break-after: always;"></div>

#### For Events Management

| Priority |     As a …​      | I want to …​                       | So that I can…​                                          |
|:--------:|:----------------:|------------------------------------|----------------------------------------------------------|
| `* * *`  |       user       | add a new event                    |                                                          |
| `* * *`  |       user       | delete an old event                | remove entries that I no longer need                     |
| `* * *`  |       user       | view all my saved events           | have an overview of all my events sorted by date         |
| `* * *`  |       user       | see most urgent events             | know what is coming up soon or needs to be done urgently |
|  `* *`   |       user       | edit a saved event                 | change any details that are wrong or have changed        |
|  `* *`   | experienced user | tag clients to events              | see which client is involved in a particular event       |
|   `*`    |  forgetful user  | set recurring reminders for events | be alerted to task that I might forget                   |
|   `*`    |       user       | add location of events             | know where this event is taking place                    |

<div style="page-break-after: always;"></div>

### Use cases

#### Use Case: UC1 - Get help

**Precondition**: -

**MSS**

1. User requests for help.
2. FreelanceBuddy shows a pop-up with a link to the user guide.
3. User copies the URL and references the user guide.

   Use case ends.

#### Use Case: UC2 - Navigating between tabs

**Precondition**: User can be on **any** tab

**MSS**

1. FreelanceBuddy shows the current tab user is on.
2. User requests to switch to another tab (that is not the current one).
3. FreelanceBuddy switches to the specified tab by the user.
4. FreelanceBuddy shows the desired tab.

   Use case ends.

**Extensions**

* 2a. The user inputs an invalid syntax.
    * 2a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.
  
* 2b. User decides to stay on the current tab.

    User case resumes at step 4.

<div style="page-break-after: always;"></div>

#### Use Case: UC3 - Add a client contact

**Precondition**: User is on **Contacts** tab

**MSS**

1. User requests to add a new contact with details.
2. FreelanceBuddy creates a new contact and shows it within the list.

   Use case ends.

**Extensions**
  
* 1a. The user inputs invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1. 
  

#### Use Case: UC4 - Delete a client contact

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to delete specific entry in the list.
2. FreelanceBuddy deletes the entry.

   Use case ends.

**Extensions**

* 1a. The user inputs with invalid syntax.
    * 1a1. FreelanceBuddy shows error message.

      Use case resumes at step 1. 

#### Use Case: UC5 - View all client contacts

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to view all Contacts.
2. FreelanceBuddy shows a list of all Contacts.

   Use case ends.

<div style="page-break-after: always;"></div>

#### Use Case: UC6 - Find a specific client contact by name

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to find contacts using name keywords.
2. FreelanceBuddy shows a list of Contacts that contains given name keywords.

   Use case ends.

**Extensions** 

* 1a. No contacts found that contains given name keywords.
  * 1a1. FreelanceBuddy shows 0 contacts.


    Use case ends.

#### Use Case: UC7 - Find a specific client contact by company

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to find contacts using company keywords.
2. FreelanceBuddy shows a list of Contacts that contains given company keywords.

   Use case ends.

**Extensions**

* 1a. No contacts found that contains given company keywords.
    * 1a1. FreelanceBuddy shows 0 contacts.

      Use case ends.

<div style="page-break-after: always;"></div>

#### Use Case: UC8 - Editing a client contact

**Precondition**: User is on **Contacts** tab, **Contacts** list must have at least one entry

**MSS**

1. User requests to edit a contact they see in the contacts tab.
2. FreelanceBuddy changes the contact fields to the new modified values.

   Use case ends.

**Extensions**

* 1a. User input is invalid
    * 1a1. FreelanceBuddy shows error message.

      Use case resumes at step 1.

#### Use Case: UC9 - Add an event

**Precondition**: User is on **Events** tab

**MSS**

1. User requests to add a new event.
2. FreelanceBuddy creates the Event entry and shows it within the list.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

<div style="page-break-after: always;"></div>

#### Use Case: UC10 - Delete an event

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to delete a specific entry in the list.
2. FreelanceBuddy deletes the entry.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.


#### Use Case: UC11 - View all events

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to view all events.
2. FreelanceBuddy shows a list of all events.

Use case ends.

#### Use Case: UC12 - View most urgent events

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to view most urgent events.
2. FreelanceBuddy shows a list of most urgent events.

Use case ends.

<div style="page-break-after: always;"></div>

#### Use Case: UC13 - Filter events by name

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to find events using name keywords.
2. FreelanceBuddy shows a list of Events that contains given name keywords.

   Use case ends

**Extensions**

* 1a. No events found that contains given name keywords.
    * 1a1. FreelanceBuddy shows an empty list.

      Use case ends.

#### Use Case: UC14 - Filter events by time

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to find events before a specific time.
2. FreelanceBuddy shows a list of Events that are after now and before the requested time.

   Use case ends

**Extensions**

* 1a. No Events found that are after now and before the requested time.
    * 1a1. FreelanceBuddy shows an empty list.

      Use case ends.
  
* 1b. The user inputs an invalid time.
    * 1b1. FreelanceBuddy shows an error message.
  
      Use case resumes at step 1.

<div style="page-break-after: always;"></div>

#### Use Case: UC15 - Filter events by client

**Precondition**: User is on **Events** tab, **Events** list must have at least one entry

**MSS**

1. User requests to find events with a specified client.
2. FreelanceBuddy shows a list of Events which have the client tagged.

   Use case ends

**Extensions**

* 1a. No Events found that have the client tagged.
    * 1a1. FreelanceBuddy shows an empty list.

      Use case ends.

* 1b. The user inputs a client not registered.
    * 1b1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC16 - Add a finance entry

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to add a new finance entry.
2. FreelanceBuddy adds the new finance entry to the top of the list.

    Use case ends.

**Extensions** 

* 1a. The user inputs an invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

        Use case resumes at step 1.

<div style="page-break-after: always;"></div>

#### Use Case: UC17 - Delete a finance entry

**Precondition**: User is on **Finance** tab, **Finance** list must have at least one entry

**MSS**

1. User requests to delete a specific entry in the list.
2. FreelanceBuddy deletes the entry.

    Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
  * 1a1. FreelanceBuddy shows an error message.
    
    Use case resumes at step 1.

#### Use Case: UC18 - View all finance entries

**Precondition**: User is on **Finance** tab, **Finance** list must have at least one entry

**MSS**

1. User requests to view all finance entries.
2. FreelanceBuddy shows a list of all finance entries.

Use case ends.

<div style="page-break-after: always;"></div>

#### Use Case: UC19 - Filter by finance entry type

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to filter finance entries by entry type.
2. FreelanceBuddy shows a list of finance entries of the given type.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid entry type.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC20 - Edit a finance entry

**Precondition**: User is on **Finance** tab, **Finance** list must have at least one entry

**MSS**

1. User requests to edit a specific entry in the list.
2. FreelanceBuddy edits the entry.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid index.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

* 1b. The user inputs an invalid syntax.
    * 1b1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

<div style="page-break-after: always;"></div>

#### Use Case: UC21 - Filter finance entries by client

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to filter finance entries by client.
2. FreelanceBuddy shows a list of finance entries tagged to the given client.

   Use case ends.

**Extensions**

* 1a. The user inputs a client that does not exist.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

#### Use Case: UC22 - Get finance summary for tagged client

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to see finance summary for a client.
2. FreelanceBuddy shows the finance summary for the given client.

   Use case ends.

**Extensions**

* 1a. The user inputs a client that does not exist.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

<div style="page-break-after: always;"></div>

#### Use Case: UC23 - Filter finance entries by a timeframe

**Precondition**: User is on **Finance** tab

**MSS**

1. User requests to filter finance entries by a timeframe.
2. FreelanceBuddy shows a list of finance entries for the given timeframe.

   Use case ends.

**Extensions**

* 1a. The user inputs an invalid syntax.
    * 1a1. FreelanceBuddy shows an error message.

      Use case resumes at step 1.

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

|      Aspect       | Description                                                                                                                                                                                                                                                                                                                                     |
|:-----------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Compatibility** | Should work on any _mainstream OS_ as long as it has Java `11` or above installed.                                                                                                                                                                                                                                                              |
|  **Performance**  | 1. Should be able to to hold up to 1000 entires in all tabs without much _performance degradation_.<br/><br/>2. Should respond to commands within 1s on average for any type of task.                                                                                                                                                           |
|   **Usability**   | 1. User with above average typing speed should be able to accomplish most tasks faster than using mouse and GUIs.<br><br>2. CLI commands should be intuitive and easy for the user.<br></br>3. Error messages should be informative so users can troubleshoot effectively.<br><br>4. Clear and concise documentation available to assist users. |
|  **Reliability**  | 1. App should be robust and resilient to minimize any crashes or errors.<br><br> 2. App should have have regular automated backup procedures to ensure data is saved in the case of accidental shut down or crashes to the app.                                                                                                                 |

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Performance degradation**: Slowdown in performance, particularly in task execution times or data save/load times

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Appendix: Effort**

This section aims to showcase the effort that we put into FreelanceBuddy as a team. We will highlight the challenges
and efforts we faced for each component of FreelanceBuddy.

With FreelanceBuddy, we added the `Finance` and `Event` entities on to the existing AB3 `Person` entity. 
These entities and their models were made from scratch, with minimal code reuse from AB3. 

With the addition of these entities, we felt that it made the most sense to create two new tabs. This meant that new UI 
components had to be created for each tab. For this, the main structure of each tab was somewhat similar to the original AB3 UI,
however slight adjustments were made to cater to the needs of each tab.

With the creation of three tabs, we realised that separate parsers had to be made. This is because the same command should
have a different result depending on which tab you are on. To achieve this, we made three new parsers - `ContactParser`,
`FinanceParser` and `EventParser` which all extend the `ParseCommandHandlers` class. This helped us to manage how commands
were handled in each tab. `ContactParser` was adapted from the original AB3 `AddressBookParser` while the other two parsers
had very minimal code reuse, due to the fact that they handled very different commands.

For the commands, many of the basic commands (i.e. add, delete and edit) that we added to `Finance` and `Events` made 
use of the existing AB3 commands. Many of the new commands we added (i.e. `filter-n`, `summary`) relied on the 
use of the `Predicate` classes. This was adapted from the existing `find` command of AB3. While the new commands were not exactly
the same as the `find` command, we were able to extract the idea of how to filter a list from the `find` command.

We also made an effort to cater to more intuitive user inputs through the implementation of the `DateTimeParser`. 
We felt that the addition of this `DateTimeParser` greatly improved the user experience since many of our commands made 
use of both a start and end time. The implementation of this was not trivial and none of the code for this was taken from AB3.

The main challenge we faced with FreelanceBuddy was that we were now handling three separate entity types, all of which had
different behaviours and functions. This made integration between the three tabs more challenging. As an example, we
were unable to implement the cascading update and delete from the `Contacts` tab to the `Finance` and `Events` tabs 
([more on this](#1-lack-of-cross-tab-updating)). 

Overall, we felt that we the effort that we have put into creating FreelanceBuddy is more than sufficient. This is justified
by the sheer amount of new features we have added (i.e. all the `Finance` and `Events` features). We also felt that many
of the features we added (e.g. `summary`, `filter-t`) were different from all the AB3 features. 

--------------------------------------------------------------------------------------------------------------------

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

   2. Double-click the jar file 
   
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

<div style="page-break-after: always;"></div>

## Test cases for Contacts

For the following tests we assume that you are on the Contacts tab.

### Listing Contacts

1. Viewing all contacts in FreelanceBuddy

    1. Prerequisites: There are contacts that are already saved in FreelanceBuddy. 

    2. Test case: `list`

       Expected: all previously saved contacts will be shown only in the contacts tab. There will be a success message of "Listed all persons".
   
    3. Test case: `list 1234`

       Expected: all previously saved contacts will be shown only in the contacts tab. There will be a success message of "Listed all persons".

### Adding a Contact

1. Add a new contact into FreelanceBuddy

    1. Test case (only compulsory fields): `add n/‘Chewbaca’ The 1st p/+659123139 e/chewie@gmail.com`

       Expected: a new `contact` with the corresponding details will be added to the bottom of the list.

    2. Test case (with all fields): `add n/Annie Dunkins p/+610489630614 e/ann1e@gmail.com a/Opera house c/NAB t/@anniebirds`

       Expected: a new `contact` with the corresponding details will be added to the bottom of the list.

    3. Test case (missing compulsory field): `add n/Abbie Dunkins p/+610489630614 a/Opera house c/NAB t/@anniebirds`
    
       Expected: No `contact` added. Error details shown in the status message. List remains unchanged.

    4. Test case (repeated fields): `add n/Abbie Dunkins p/+610489630614 e/ann1e@gmail.com a/Opera house c/NAB c/Atlassian t/@anniebirds`

       Expected: No `contact` added. Error details shown in the status message. List remains unchanged.

    5. Test case (invalid format for one field): `add n/Abbie Dunkins p/123 e/ann1e@gmail.com`

       Expected: No `contact` added. Error details shown in the status message. List remains unchanged.
   
    6. Pre-requisites: Another contact exactly names `Annie Dunkins` must exist.

    7. Test case (duplicate name): `add n/Annie Dunkins p/+555 555-5555 e/annie@gmail.com`

       Expected: No `contact` added. Error details shown in the status message. List remains unchanged.

### Editing a Contact

1. Editing a contact in FreelanceBuddy

    1. Pre-requisites: There must be at least one contact that is already saved in FreelanceBuddy and no other contact can have the name `‘Chewbaca’ The 1st`. List all persons using 'list' command.

    2. Test case (valid edit): `edit 1 n/‘Chewbaca’ The 1st`

       Expected: contact at index 1 has name field modified to ‘Chewbaca’ The 1st. 

    3. Pre-requisites: Contact at index 1 must have a stored telegram handle.

    4. Test case (removing optional fields): `edit 1 n/Alex Yeoh t/` 

       Expected: contact at index 1 has name field modified to Alex Yeoh and telegram handle stored is removed.

    5. Test case (removing compulsory fields): `edit 1 n/`
        
       Expected: contact will not be edited. Error details shown in the status message.

    6. Other incorrect delete commands to try: `edit 1 p/`, `edit 1 e/`, `edit x t/` (where x is larger than the list size)<br>
            Expected: Similar to previous.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    2. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

    3. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous. 

### Filtering a person

1. Filter a contact by name

    1. Pre-requisites: There must be at least one contact that is already saved in FreelanceBuddy. Ensure one of them has the name `Alex Yeoh`

    2. Test case (valid filter): `filter-n Alex`

        Expected: Only contacts with the key word `Alex` (not case-sensitive) will be shown in the list.

    3. Pre-requisites: There is no one in FreelanceBuddy that has the name that consist of `Alex`

    4. Test case (invalid keyword): `filter-n Alex`

        Expected: No contacts are shown in the list.

    5. Test case (without keyword): `filter-n`

       Expected: Error details shown in the status message. List remains the same.


1. Filter a contact by company

    1. Pre-requisites: There must be at least one contact that is already saved in FreelanceBuddy. Ensure one of them has the company `Google`

    2. Test case (valid filter): `filter-c google`

       Expected: Only contacts with the key word `google` in the company name (not case-sensitive) will be shown in the list.

    3. Pre-requisites: There is no one in FreelanceBuddy that has the company that consist of `google`

    4. Test case (invalid keyword): `filter-c google`

       Expected: No contacts are shown in the list.

    5. Test case (without keyword): `filter-c`

        Expected: Error details shown in the status message. List remains the same.

<div style="page-break-after: always;"></div>

## Test cases for Finance

For the following tests, we assume that you are on the Finance tab.

### Listing all finances

1. Listing all finances in FreelanceBuddy

   1. Prerequisites: We assume that there are multiple expenses and commissions in the finance tab. 

   2. Test case: `list`<br>
   Expected: Lists all finances

   3. Test case: `list commission`<br>
   Expected: List all commissions

   4. Test case: `list expense`<br>
   Expected: List all expenses

### Adding a commission

1. Adding a commission to FreelanceBuddy

   1. Prerequisites: There has to be at least one client saved in FreelanceBuddy. For our example, we shall assume the client `John Doe` exists in our contacts.

   2. Test case: `add-c a/900 c/John Doe d/ChatBot commission t/next week`<br>
      Expected: A new `commission` with the corresponding details is added to the list. The amount should be highlighted in green. The time displayed should be a week from the current time.

   3. Test case (no time input): `add-c a/50 c/John Doe d/ChatBot commission`<br>
      Expected: A new `commission` with the corresponding details is added to the list. The amount should be highlighted in green. The time displayed should be the current time.

   4. Test case (Amy Smith is not in the contacts): `add-c a/50 c/Amy Smith`<br>
      Expected: No `commission` added. Error details shown in the status message. List remains unchanged.

   5. Test case: `add-c a/50 c/John Doe`<br>
      Expected: No `commission` added. Error details shown in the status message. List remains unchanged.

   6. Other incorrect delete commands to try: `add-c a/50 c/John Doe`, `add-c a/-50 c/John Doe d/ChatBot commission`<br>
      Expected: Similar to previous.

### Adding an expense

1. Adding an expense to FreelanceBuddy

   1. Prerequisites: There has to be at least one client saved in FreelanceBuddy. For our example, we shall assume the client `John Doe` exists in our contacts.

   2. Test case: `add-e a/900 c/John Doe d/ChatBot commission t/next week`<br>
      Expected: A new `commission` with the corresponding details is added to the list. The amount should be highlighted in red. The time displayed should be a week from the current time.

   3. Test case (no time input): `add-e a/50 c/John Doe d/ChatBot commission`<br>
      Expected: A new `commission` with the corresponding details is added to the list. The amount should be highlighted in red. The time displayed should be the current time.

   4. Test case (Amy Smith is not in the contacts): `add-e a/50 c/Amy Smith`<br>
      Expected: No `commission` added. Error details shown in the status message. List remains unchanged.

   5. Test case: `add-e a/50 c/John Doe`<br>
      Expected: No `commission` added. Error details shown in the status message. List remains unchanged.

   6. Other incorrect `add-e` commands to try: `add-e a/50 c/John Doe`, `add-e a/-50 c/John Doe d/ChatBot commission`,<br>
      Expected: Similar to previous.
       
### Editing a finance

1. Editing a Finance Entry in FreelanceBuddy

   1. Prerequisite: For our example, we shall assume that the following finances are present in the finance tab:
       * `commission` with the client `John Doe`, amount `900` and description `ChatBot UI` is at `index 1`
       * `expense` with the client `John Doe`, amount `50` and description `Adobe Photoshop subscription` is at `index 2`.<br>

       We also assume that `Adam Smith` is in our contacts and `Betsy Crowder` is not.<br>

   2. Test case: `edit 1 c/Adam Smith`<br>
      Expected: The client tagged to the commission should now be `Adam Smith`. Rest of the commission remains unchanged. 

   3. Test case: `edit 1 a/100`<br>
      Expected: The amount is changed to `100`. Rest of the commission remains unchanged.

   4. Test case: `edit 1 d/ChatBot commission payment`<br>
      Expected: The description is changed to `ChatBot commission payment`. Rest of the commission remains unchanged.

   5. Test case: `edit 1 t/tmr`<br>
      Expected: The time due is changed to the next day. Rest of the commission remains unchanged.

   6. Test case: `edit 1 c/`<br>
      Expected: No changes to the `commission`. Error details shown in the status message. 

   7. Test case: `edit 1 a/-900`<br>
      Expected: No changes to the `commission`. Error details shown in the status message.

   8. Test case: `edit 1 d/`<br>
      Expected: No changes to the `commission`. Error details shown in the status message.

   9. Test case: `edit 2 c/`<br>
      Expected:  There is now no client tagged to the `expense.` Rest of the expense remains unchanged.

   10. Other test cases: Repeat steps 2-5 for the expense entry (i.e. Replace index 1 with 2)

### Deleting a Finance Entry

1. Deleting a Finance Entry in FreelanceBuddy

   1. Prerequisites: There has to be at least one finance entry saved in FreelanceBuddy, but less than 999. Assuming the client `John Doe` exists in our contacts, we can set up an entry with the command `add-c d/Test c/John Doe a/20`.

   2. Test case: `delete 1`<br>
      Expected: The entry corresponding to the index `1` is deleted. The index of every entry after `1` is updated to form a continuous list.

   3. Test case: `delete 999`<br>
      Expected: Deletion fails. Error details shown in the status message. List remains unchanged.

   4. Test case: `delete 0`<br>
      Expected: Deletion fails. Error details shown in the status message. List remains unchanged.

   5. Other incorrect delete commands to try: `delete -1`, `delete 5.99`<br>
      Expected: Deletion fails. Error details shown in the status message. List remains unchanged.

### Filtering Finance Entries

1. Filtering finance entries by Client

   1. Prerequisites: There has to be at least one finance entry saved in FreelanceBuddy, but less than 999. Assuming the clients `John Doe` and `Mary Sue` exist in our contacts, we can set up entries with the commands `add-c d/Test c/John Doe a/20` and `add-e d/Test2 c/Mary Sue a/50`.

   2. Test case: `filter-c john`<br>
      Expected: Only entries that belong to a client whose name contains `john` (case-insensitive) will be displayed. Number of entries shown in the status message.

   3. Test case: `filter-c John mary`<br>
      Expected: Only entries that belong to a client whose names contain `John` or `mary` (case-insensitive) will be displayed. Number of entries shown in the status message.

   4. Test case (no finance entry has a client with name containing `Hello`): `filter-c Hello`<br>
      Expected: No entry will be displayed. Number of entries (0) shown in the status message.

   5. Test case: `filter-c`<br>
      Expected: Filter fails. Error details shown in the status message. List remains unchanged.

2. Filtering finance entries by Time Frame

   1. Prerequisites: There has to be at least one finance entry saved in FreelanceBuddy. Assuming the client `John Doe` exists in our contacts, we can set up entries with the commands `add-c d/Test c/John Doe a/20 t/2023-10-10` and `add-e d/Test2 c/John Doe a/50 t/2024-10-10`.

   2. Test case: `filter-t s/2023-10-09 e/2023-10-11`<br>
      Expected: Only entries between `2023-10-09` and `2023-10-11` will be displayed. Number of entries shown in the status message. If using the above setup, only the former entry will be displayed.

   3. Test case: `filter-t s/2023-10-09 e/2024-10-12`<br>
      Expected: Only entries between `2023-10-09` and `2024-10-12` will be displayed. Number of entries shown in the status message. If using the above setup, both entries will be displayed.

   4. Test case (no entries in 2099): `filter-t s/2099-01-01 e/2099-12-31`<br>
      Expected: No entry will be displayed. Number of entries (0) shown in the status message.

   5. Test case: `filter-t s/test e/test`<br>
      Expected: Filter fails. Error details shown in the status message. List remains unchanged.

   6. Test case: `filter-t s/2023-10-09`<br>
      Expected: Filter fails. Error details shown in the status message. List remains unchanged.

   7. Test case: `filter-t`<br>
      Expected: Filter fails. Error details shown in the status message. List remains unchanged.

### Generating a finance summary of a client

1. Generating finance summary of a client in FreelanceBuddy

   1. Prerequisites: There has to be at least one client saved in FreelanceBuddy. For our example, we shall assume the clients `John Doe` and `Mary Sue` exist in our contacts. We can set up entries with the commands `add-c d/Test c/John Doe a/20` and `add-e d/Test2 c/John Doe a/50`.

   2. Test case: `summary John Doe`<br>
      Expected: Only entries that belong to `John Doe` will be displayed. Cumulative value of finance entries, commissions, expenses, and number of commissions and expenses shown in the status message.

   3. Test case (no entries by `Mary Sue`): `summary Mary Sue`<br>
      Expected: No entry will be displayed. Status message should be `You currently have no finances for this client`.

   4. Test case (`Hello` does not exists in contacts): `summary Hello`<br>
      Expected: Summary fails. Error details shown in the status message. List remains unchanged.

   5. Test case: `summary`<br>
      Expected: Summary fails. Error details shown in the status message. List remains unchanged.

<div style="page-break-after: always;"></div>

## Test cases for Events

For the following tests, we assume that you are on the Events tab.

### Listing events

1. Viewing upcoming events in FreelanceBuddy

    1. Prerequisites: There has to be at least one event saved in FreelanceBuddy.

    2. Test case: `list`<br>
       Expected: A list of all upcoming events is shown.

    3. Test case: `list 123`<br>
       Expected: A list of all upcoming events is shown.

2. Viewing all events in FreelanceBuddy

    1. Prerequisites: There has to be at least one event saved in FreelanceBuddy.

    2. Test case: `list-all`<br>
       Expected: A list of all events is shown.

    3. Test case: `list-all 123`<br>
       Expected: A list of all events is shown.

### Adding an event

1. Adding a new event into FreelanceBuddy
    
    1. Prerequisites: There has to be at least 2 clients saved in FreelanceBuddy. For our example, we shall assume the clients `John Doe` and `Amy Bee` exists in our contacts.

    2. Test case (only compulsory fields): `add n/Dinner s/24-01-2024 20:30 e/24-01-2024 21:30`<br>
       Expected: A new `event` with the corresponding details is added chronologically to the list.

    3. Test case (with all fields): `add n/Team meeting s/23-01-2024 19:30 e/23-01-2024 21:30 c/John Doe l/20 Lower Kent Ridge Road, 119080 d/Bring laptop`<br>
       Expected: A new `event` with the corresponding details is added chronologically to the list. 

    4. Test case (multiple client input): `add n/Yoga s/25-01-2024 12:30 e/25-01-2024 13:30 c/John Doe c/Amy Bee`<br>
       Expected: A new `event` with the corresponding details is added chronologically to the list.

    5. Test case (missing compulsory field): `add n/Prepare for exams e/29-01-2024 21:30 c/John Doe l/home d/study on entropy`<br>
       Expected: No `event` added. Error details shown in the status message. List remains unchanged.

    6. Test case (repeated fields): `add n/Company party n/Charity run s/19-01-2024 17:30 e/19-01-2024 21:30 c/Amy Bee l/Office d/have fun!`<br>
       Expected: No `event` added. Error details shown in the status message. List remains unchanged.

    7. Test case (invalid date): `add n/Baby shower party s/00-01-2024 17:30 e/00-01-2024 21:30 c/Amy Bee l/nana's house d/bring gifts`<br>
       Expected: No `event` added. Error details shown in the status message. List remains unchanged.

    8. Test case (Amy Smith not in contacts): `add n/Company Cleanup s/20-01-2024 17:30 e/20-01-2024 20:30 c/Amy Smith l/Office d/bring mop`<br>
       Expected: No `event` added. Error details shown in the status message. List remains unchanged.


### Editing an event

1. Editing an event in FreelanceBuddy

    1. Prerequisites: There exists an event made with the command `add n/Dinner s/24-01-2024 20:30 e/24-01-2024 21:30` and its index in the events list is `1`.
       There has to be at least 2 clients saved in FreelanceBuddy. For our example, we shall assume the client `John Doe` and `Amy Bee` exists in our contacts.

    2. Test case (compulsory field edit): `edit 1 n/Watch Television`<br>
       Expected: The `event` edited will now reflect the corresponding details.

    3. Test case (optional field edit): `edit 1 l/Nacho's Bar`<br>
       Expected: The `event` edited will now reflect the corresponding details.

    4. Test case (multiple client input): `edit 1 c/John Doe c/Amy Bee`<br>
       Expected: The `event` edited will now reflect the corresponding details.

    5. Test case (invalid date): `edit 1 s/29-01-2024 21:30`<br>
       Expected: No `event` edited. Error details shown in the status message. List remains unchanged.

    6. Test case (repeated fields): `edit 1 n/Company party n/Charity run`<br>
       Expected: No `event` edited. Error details shown in the status message. List remains unchanged.

    7. Test case (Amy Smith not in contacts): `edit 1 c/Amy Smith`<br>
       Expected: No `event` edited. Error details shown in the status message. List remains unchanged.

### Deleting an event

1. Deleting an event from FreelanceBuddy

    1. Prerequisites: There exists an event made with the command `add n/Dinner s/24-01-2024 20:30 e/24-01-2024 21:30` and its index in the events list is `1`.
       There has to be at least 2 clients saved in FreelanceBuddy. For our example, we shall assume the clients `John Doe` and `Amy Bee` exists in our contacts.

    2. Test case: `delete 1`<br>
       Expected: The `event` deleted will be removed from the list. 

    3. Test case (invalid index): `delete 0`<br>
       Expected: No `event` deleted. Error details shown in the status message. List remains unchanged.

### Filtering an event

1. Filtering events in FreelanceBuddy

    1. Prerequisites: There exists an event made with the command `add n/Dinner s/24-01-2024 20:30 e/24-01-2024 21:30 c/John Doe`.
       There has to be at least 2 clients saved in FreelanceBuddy. For our example, we shall assume the clients `John Doe` and `Amy Bee` exists in our contacts.

    2. Test case: `filter-n Dinner`<br>
       Expected: The events list will only show `events` with the name `Dinner`.

    3. Test case: `filter-t 29-01-2024 20:30`<br>
       Expected: The events list will only show `events` before `24-01-2024 20:30`.

    4. Test case: `filter-c John`<br>
       Expected: The events list will only show `events` with `John Doe` tagged as a client.

    5. Test case (invalid date): `filter-t 01230time`<br>
       Expected: No `event` filtered. Error details shown in the status message. List remains unchanged.

    6. Test case (No Event with Amy Bee): `filter-c Amy`<br> 
       Expected: The events list will be empty.

<div style="page-break-after: always;"></div>

## Test cases for Storage

Note that the following test cases involves manipulating the JSON files.

<box type="warning">

The following tests should be performed with the **default set of dummy data** that is generated when you first open the app (and run at least one command).

Dummy data can also generate by just deleting the json files.
</box>

### Deleting data

1. Deleting the JSON files while app is closed
   > test that deleting json file won't affect other json files

    1. Prerequisites: App must be closed, using dummy data

    2. Delete `addressbook.json`, run the app <br>
       Expected: Default dummy data for contacts reappears on GUI

    3. Delete `addressbook.json`, `eventsbook.json`, `financebook.json` <br>
       Expected: Default dummy data for contacts, events, and finance reappears on GUI

    4. Delete `addressbook.json`, run the app, and enter `list` in any tab <br>
       Expected: You should be able to see `addressbook.json` regenerate in the file explorer

1. Deleting the JSON files while app is running
   > test that deleting the json files while using the app won't cause it to crash

    1. Prerequisites: App must be running, using dummy data

    2. Delete `addressbook.json`, then enter command in Contacts tab `add n/Test Person p/838383838 e/asdf@gmail.com` <br>
       Expected: New contact "Test Person" is successfully created and shown in GUI, `addressbook.json` regenerates

    3. Delete `addressbook.json`, then enter command in Contacts tab `filter-n Alex` <br>
       Expected: Contact "Alex Yeoh" is shown, `addressbook.json` regenerates

    2. Delete `eventsbook.json`, then enter command in Events tab `add n/Test Meeting s/tdy e/tmr` <br>
       Expected: New event "Test Meeting" is successfully created and shown in GUI, `eventsbook.json` regenerates

    3. Delete `eventsbook.json`, then enter command in Events tab `filter-n Alex` <br>
       Expected: Event "Meeting with Alex" is shown, `eventsbook.json` regenerates

    2. Delete `financebook.json`, then enter command in Finance tab `add-c d/Painting a/100 c/David Li` <br>
       Expected: New commission "Painting" is successfully created and shown in GUI, `financebook.json` regenerates

    3. Delete `financebook.json`, then enter command in Finance tab `filter-c Bernice Yu` <br>
       Expected: All finances regarding "Bernice Yu" is shown, `financebook.json` regenerates


### Editing data

1. Editing contents in `addressbook.json`
    1. Prerequisites: Default dummy data in `addressbook.json` must exists, app is closed

    2. Test case: Edit name in the first entry of `addressbook.json` to "poop" and run the app <br>
       Expected: Data still exists, first entry name is now "poop". Note that it didn't cause the other two tabs to crash even though Alex Yeoh no longer exists

    3. Test case: Edit name in the first entry of `addressbook.json` to an empty string "" and run the app<br>
       Expected: All data is erased

    3. Test case: Edit telegram name in the first entry of `addressbook.json` to an empty string "" and run the app<br>
       Expected: Data still exists, Alex Yeoh now has no telegram name

    4. Test case: Edit telegram name in the first entry of `addressbook.json` to "..." and run the app<br>
       Expected: All data is erased

2. Editing contents in `financebook.json`
    1. Prerequisites: Default dummy data in `financebook.json` must exists

    2. Test case: Edit @type in the first entry of `financebook.json` to "Expense" instead of "Commission" and run the app <br>
       Expected: Finance entry "Artwork" successfully changed from commission to expense

    2. Test case: Edit amount in the first entry of `financebook.json` to "0" and run the app <br>
       Expected: All data is erased

    3. Test case: Edit client in the third entry(expense) of `financebook.json` to null and run the app <br>
       Expected: Data still exists, third finance entry now no longer has a client tagged

3. Editing contents in `eventsbook.json`
    1. Prerequisites: Default dummy data in `eventsbook.json` must exists

    2. Test case: Edit timeStart in the first entry of `eventsbook.json` to a date time that is later than timeEnd. <br>
       Expected: All data is erased

    2. Test case: Edit timeEnd in the first entry of `eventsbook.json` to "tomorrow" <br>
       Expected: All data is erased

<div style="page-break-after: always;"></div>
 
### Loading data

<box type="tip" seamless>

**How to load data from a different file?**

Change the file location that you want the app to target through the `preferences.json` file. Make sure that it is a valid file path.
</box>

1. Loading data from a different book file
    1. Prerequisites: Create your own dummy set of data that is distinguishable to you, and rename the file to something else. Set `preferences.json` to target your own dummy files.

    2. Test case: Load data from a different AddressBook file (e.g., `addressybooky.json`) <br>
       Expected: You should see your own dummy data in the app.

    3. Test case: Load data from a different FinanceBook file (e.g., `financeybooky.json`) <br>
       Expected: You should see your own dummy data in the app.

    4. Test case: Load data from a different EventsBook file (e.g., `eventsybooky.json`) <br>
       Expected: You should see your own dummy data in the app.
