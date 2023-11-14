---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# LoveBook Developer Guide

## **Overview**

LoveBook, is a **dating-focused** application, revolving around providing **online daters** with a **convenient**
and **enjoyable** tool to enhance their dating experiences. Featuring **user preferences management**, **date
organization**, **customizable filtering options** and **best match algorithms**, LoveBook enhances the **efficiency**
of your online dating journey.

[//]: # "<!-- * Table of Contents -->"
[//]: # "<page-nav-print />"

## **Acknowledgements**

- Code reused from here to disable click feature of `ListView`:
  https://stackoverflow.com/questions/20621752/javafx-make-listview-not-selectable-via-mouse
- Result Display FXML code inspired from here:
  https://github.com/AY1920S2-CS2103T-F09-3/main

## **Table of Contents**

- [**Overview**](#overview)
- [**Acknowledgements**](#acknowledgements)
- [**Table of Contents**](#table-of-contents)
- [**Setting up, getting started**](#setting-up-getting-started)
- [**Design**](#design)
  - [Architecture](#architecture)
  - [UI component](#ui-component)
  - [Logic component](#logic-component)
  - [Model component](#model-component)
  - [Storage component](#storage-component)
  - [Common classes](#common-classes)
- [**Implementation**](#implementation)
  - [Add Dates](#add-dates)
  - [List Dates](#list-dates)
  - [Filter dates](#filter-dates)
  - [Sort dates](#sort-dates)
  - [Get Blind Date](#get-blind-date)
  - [Get best match](#get-best-match)
  - [Set preferences](#set-preferences)
  - [Star dates](#star-dates)
  - [Unstar dates](#unstar-dates)
- [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
- [**Appendix: Requirements**](#appendix-requirements)
  - [Product scope](#product-scope)
  - [User stories](#user-stories)
  - [Use cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [**Appendix: Instructions for Manual Testing**](#appendix-instructions-for-manual-testing)
  - [Launch](#launch)
  - [Viewing Help](#viewing-help)
  - [Adding Dates](#adding-dates)
  - [Editing Dates](#editing-dates)
  - [Finding Dates](#finding-dates)
  - [Filtering Dates](#filtering-dates)
  - [Finding a Blind Date](#finding-a-blind-date)
  - [Exiting the Application](#exiting-the-application)
  - [Saving Data](#saving-data)
- [**Appendix: Effort**](#appendix-effort)
  - [Evolving of AB3 into LoveBook](#evolving-of-ab3-into-lovebook)
  - [Revamping of UI](#revamping-of-ui)
- [**Appendix: Planned Enhancements**](#appendix-planned-enhancements)

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---

<div style="page-break-after: always;"></div>

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/MainApp.java)) is
in charge of the app launch and shut down.

- At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
- At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

This application attempts to separate the `UI`, `Logic`, `Model` and `Storage` components to reduce coupling between the
components.
This is done by using interfaces to define the API of each component and using classes to implement the functionality of
each component.
This allows the components to be easily added and replaced with other implementations without affecting the other
components.
For example, new features can be added to the `Storage` component without affecting the other components.

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts
e.g.`CommandBox`, `ResultDisplay`, `DateListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the
visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/LoveBook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, as it displays `Date` object residing in the `Model`.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Logic component

**API
** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" />

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API
call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info">

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of
PlantUML, the lifeline reaches the end of diagram.

</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `LoveBookParser` object which in turn creates a
   parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which
   is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a date).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" />

How the parsing works:

- When called upon to parse a user command, the `LoveBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `LoveBookParser` returns back as
  a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Model component

**API
** : [`Model.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" />

The `Model` component,

- stores the LoveBook data i.e., all `Date` objects (which are contained in a `UniqueDateList` object).
- stores the currently 'selected' `Date` objects (e.g., results of a search query) as a separate _filtered_ list which
  is exposed to outsiders as an unmodifiable `ObservableList<Date>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
- stores a `UserPrefs` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPrefs` objects.
- stores a `DatePrefs` object that represents the user’s preferences for dates. This is exposed to the outside as
  a `ReadOnlyDatePrefs` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Storage component

**API
** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-F10-2/tp/blob/master/src/main/java/seedu/lovebook/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" />

The `Storage` component,

- saves LoveBook data, date preferences data and user preference data in JSON format, and reads them back into
  corresponding objects.
- inherits from `LoveBookStorage`, `UserPrefsStorage` and `DatePrefsStorage`, which means it can be treated as
  either one of three (if the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

[Scroll back to _Table of Contents_](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.LoveBook.commons` package.

<div style="page-break-after: always;"></div>

---

## **Implementation**

Before diving into the implementation details, here's an overview of what changed from the AB-3 codebase.

- Address, Phone Number, Email, Tags fields have been replaced with Gender, Age, Horoscope, Height and Income fields.
- The `Person` class has been renamed to `Date` class, and most of the classes named `Personxxx` have been renamed
  accordingly.
- The GUI has been updated to make the application more visually appealing and user-friendly.
- Several other commands like `filter`, `star`, `bestMatch` (not exhaustive) have been added to the application.

The following class diagram shows the new `Date` class after the changes mentioned above.

<puml src="diagrams/DateClassDiagram.puml"/>

Moving on to the implementation details, the following sections describe how and why the main features of the app work.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Add Dates

#### Implementation

1. The add dates feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates an `AddCommandParser` object for add command
   specific parsing.
4. The `LoveBookParser` class also separates the command word from the user input and passes the arguments from the user
   input to the `AddCommandParser` object created above for parsing.
5. The `AddCommandParser` carries out it's validation checks and creates new `Date`, `AddCommand` objects if the
   validation checks pass.
6. The `AddCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which adds the new `Date` object created into the existing `Model` component.

<box type="info">
    The delete, edit and find features are also implemented in a similar manner.
</box>

The sequence diagram notation of the above steps is shown below. <br>

<puml src="diagrams/AddSequenceDiagram.puml" />

The activity diagram notation of the above steps is shown below. <br>

<puml src="diagrams/AddActivityDiagram.puml" />

<box type="info">
    The UI and Storage components are not shown in the sequence and activity diagrams above for simplicity.
</box>

#### Design Considerations

**Aspect: Adding dates with same name**

- **Alternative 1 (current choice):** Don't allow dates with the same name to be added.

  - Pros: Easy to implement (since all you have to do is find the name in the existing list of dates)
  - Cons: Not very user-friendly (since the user may be dating multiple people with the same name)

- **Alternative 2:** Allow dates with the same name to be added.
  - Pros: More user-friendly (since user has more flexibility in adding dates)
  - Cons: Slightly harder to implement (equality check will now take into account other details like age, gender,
    etc.)

**Aspect: Allowing users to add dates without specifying all fields**

- **Alternative 1 (current choice):** Require users to specify all fields.

  - Pros: Easy to implement (since all you have to do is check if all fields are present)
  - Cons: Not very user-friendly (since the user may not know all the details of the date)

- **Alternative 2:** Allow users to specify only some fields, and adding placeholder inputs to the remaining fields.
  - Pros: More user-friendly (since user has more flexibility in adding dates)
  - Cons: Slightly harder to implement (since you have to check which fields are present). Will also affect the
    matching algorithm since these placeholder inputs have to be omitted.

**Aspect: Adding dates to existing date list**

- **Alternative 1 (current choice):** Sorts the list on every addition, maintaining a lexically sorted list.

  - Pros: Easier to implement (since all you have to do is sort the list by name on every addition) and more visually
    appealing (since the user can easily see the list sorted by name)
  - Cons: Not very user-friendly (since the user may want to see the date being added to the end of the list)

- **Alternative 2:** Adds the date to the end of the list.
  - Pros: More user-friendly (since user can easily see the date being added to the end of the list)
  - Cons: Not very visually appealing (since the list appears to be unsorted and non uniform)

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### List Dates

1. The list dates feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `ListCommand` object for list specific commands.
   One thing to note here is that inputs like `list 12` and `list x` are accepted since they still contain `list`.
4. The `ListCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which sorts the list of dates in the `Model` component and returns the sorted list to the `Ui` component.

The sequence diagram notation of the above steps is shown below. <br>

<puml src="diagrams/ListSequenceDiagram.puml" />

#### Design Considerations

**Aspect: Ordering of dates**

- **Alternative 1 (current choice):** Sorts the list of dates by name.

  - Pros: Easier to implement (since all you have to do is sort the list by name on every addition) and more visually
    appealing (since the user can easily see the list sorted by name)
  - Cons: Not very user-friendly (since the user may want to see the date being added to the end of the list) and less
    efficient (since you have to sort the list on every command).

- **Alternative 2:** List the dates in the order they were added.
  - Pros: More user-friendly (since user can easily see the date being added to the end of the list). More efficient (
    since you don't have to sort the list on every list command)
  - Cons: Not very visually appealing (since the list appears to be unsorted and non uniform)

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Filter dates

1. The filter dates feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `FilterCommandParser` object for filter command parsing.
4. The `FilterCommandParser` carries out it's validation checks and creates a `FilterCommand` object containing a
   list of `MetricContainsKeywordPredicate` objects if the checks pass.
5. The `FilterCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function which
   filters the list of dates in the `Model` component and returns the filtered list to the `Ui` component.

The _Activity_ diagram summarises what happens after the user enters a filter command. <br>

<puml src="diagrams/FilterActivity.puml" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `filter name/John`. <br>

<puml src="diagrams/FilterSequence.puml" />

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Sort dates

1. The sort dates feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `SortCommandParser` object for sort command parsing.
4. The `SortCommandParser` object carries out its validation checks and creates a `SortCommand` object containing a
   String prefix (eg. name/) and String sequence (increasing/decreasing) if all checks pass. One thing to note here is
   that the sequence and prefix are case-sensitive.
5. The `SortCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function which
   sorts the list of dates based on a specified metric in the specified order in the `Model` component and returns the sorted
   list to the `Ui` component.

The _Activity_ diagram summarises what happens after the user enters a sort command. <br>

<puml src="diagrams/SortActivity.puml" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `sort name/increasing`. <br>

<puml src="diagrams/SortSequence.puml" />

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Get Blind Date

1. The blind date feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `BlindDateCommand` object.
   One thing to note here is that inputs like `blindDate 12` and `blindDate x` are accepted since they still contain `list`.
4. The `BlindDateCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which sorts the list of dates in the `Model` component and returns the sorted list to the `Ui` component.

The _Activity_ diagram summarises what happens after the user enters a blindDate command. <br>

<puml src="diagrams/BlindDateActivity.puml" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `blindDate`. <br>

<puml src="diagrams/BlindDateSequence.puml" />

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Get best match

The best match feature is implemented using the `BestMatchCommand` class. The `BestMatchCommand` class iterates
through the list of Dates, and calls `GetScore` to get the score of the date based on height, age, horoscope and
income.

1. The best match feature begins by passing the user input obtained from the `CommandBox` class in the `Ui`
   component to the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `BestMatchCommand` object.
4. The `BestMatchCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which then picks the `Date` with the highest score in the `Model` component by calling the `getScore` function for all `Date` objects in the `dateList`.

**Scoring Dates**

- Dates are scored upon 40, where each factor (age, height, income, horoscope) contributing 10 points each.
- The date's attributes are compared to the user's set preferences.
- Score for age = 10 - 2 \* (age difference)
- Score for height = 10 - (height difference)
- Score for income = 10 - (income difference) / 250
- Score for horoscope = 10 if horoscope is the same, 0 if horoscope is different

**Ranking of Scores**

- When the user requests for a Best Match, the Date with the highest score is taken.
- A Date with 0 points can be chosen.
- In cases where Dates are tied in score, the Date that was first input into LoveBook is chosen.

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `bestMatch`

<puml src="diagrams/BestMatchSequence.puml" />

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Set preferences

#### Implementation

1. The set preferences feature begins by passing the user input obtained from the `CommandBox` class in the `Ui`
   component to the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `SetPrefCommandParser` object for SetPrefCommand
   specific parsing.
4. The `LoveBookParser` class also separates the command word from the user input and passes the arguments from the user
   input to the `SetPrefCommandParser` object created above for parsing.
5. The `SetPrefCommandParser` carries out it's validation checks and creates a new `SetPrefCommand` object if the
   validation checks pass.
6. The `SetPrefCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which then updates the date preferences in the `Model` component.

The `edit` feature is also implemented in a similar manner.

The _Activity_ Diagram notation of the above steps is shown below.

<puml src="diagrams/SetPrefActivity.puml" />

The _Sequence_ Diagram notation of the above steps is shown below.

<puml src="diagrams/SetPrefSequence.puml" />

<div style="page-break-after: always;"></div>

#### Design Considerations

**Aspect: Allowing users to set their date preferences on launch**

- **Alternative 1 (current choice):** Have default date preference (
  see [this](UserGuide.md#managing-preferences-and-getting-matches))
  - Pros: Easy to implement (since all you have to do is set the default date preference). `bestMatch` works
    immediately from the start.
  - Cons: Not very user-friendly (since the user may not know all the details of the date)
- **Alternative 2:** Allow users to set their date preferences on launch
  - Pros: More user-friendly (since user has more flexibility in setting their date preferences)
  - Cons: Slightly harder to implement (since you have to check which fields are present). Will also affect the
    bestMatch
    algorithm since users may not know how to set their date preferences first.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Show preferences

#### Implementation

1. The show preferences feature begins by passing the user input obtained from the `CommandBox` class in the `Ui`
   component to the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `ShowPrefCommand` object.
4. The `ShowPrefCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which then returns the preferences to the `Ui` component.

The _Sequence_ Diagram notation of the above steps is shown below.

<puml src="diagrams/ShowPrefSequenceDiagram.puml" />

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Star dates

#### Implementation

1. The star dates feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `StarCommandParser` object for StarCommand specific parsing.
4. The `LoveBookParser` class also separates the command word from the user input and passes the arguments from the user input to the `StarCommandParser` object created above for parsing.
5. The `StarCommandParser` carries out it's validation checks and creates a new `StarCommand` object if the validation checks pass.
6. The `StarCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function which then updates the isStarred field for the date object with the respective index.

The _Activity_ diagram summarises what happens after the user enters a star command.

<puml src="diagrams/StarActivity.puml" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `star 1`

<puml src="diagrams/StarSequence.puml" />

#### Design Considerations

**Aspect: Choosing star to be an additional field for the add dates command**

- **Alternative 1 (current choice):** Dates are starred only through the star command
  - Pros: Users can easily modify the starred status for any one of the dates by calling `star INDEX`
  - Cons: Users cannot create a date and star it in one go using a single add dates command
- **Alternative 2:** Let star be a field for the add dates command
  - Pros: You can create a date and star the date in one go
  - Cons: Makes the add command extra lengthy. Furthermore, the premise of the star command is that it's to be used on
    an exceptional few dates. Hence, the time taken to fill in the extra star field, each time the user uses the
    add dates command, will exceed any potential time savings.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Unstar dates

#### Implementation

1. The star dates feature begins by passing the user input obtained from the `CommandBox` class in the `Ui` component to
   the `LogicManager` class in the `Logic` component by invoking the `execute` function.
2. The `LogicManager` class then passes the user input to the `LoveBookParser` class for parsing and validation.
3. The `LoveBookParser` class then performs polymorphism and creates a `UnstarCommandParser` object for UnstarCommand
   specific parsing.
4. The `LoveBookParser` class also separates the command word from the user input and passes the arguments from the user
   input to the `UnstarCommandParser` object created above for parsing.
5. The `UnstarCommandParser` carries out it's validation checks and creates a new `UnstarCommand` object if the
   validation checks pass.
6. The `UnstarCommand` object is then passed back to the `LogicManager` class for invocation of the `execute` function
   which then updates the isStarred field for the date object with the respective index.

The _Activity_ diagram summarises what happens after the user enters a star command.

<puml src="diagrams/UnstarActivity.puml" />

The _Sequence_ Diagram below shows how the components interact with each other for the scenario where the user issues
the command `unstar 1`

<puml src="diagrams/UnstarSequence.puml" />

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- active online dater
- has a need to manage a number of dates
- prefer desktop apps over other types
- can find, filter and organize their dates for better compatibility
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**:
LoveBook simplifies the process of storing information of dates and assessing compatibility between user and his/her
dates by taking into account the user’s preferences, thereby enhancing the efficiency and effectiveness of finding the
perfect match.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​   | I want to …​                                                                                    | So that I can…​                                                         |
| -------- | --------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `* *`    | new user  | be greeted with a welcome message when i launch the app                                         | feel welcome                                                            |
| `* *`    | new user  | be able to get help when i'm stuck                                                              | better navigate through the application                                 |
| `* * *`  | dater     | be able to key my preferred height in my preferences                                            | get recommended a suitable date based on our height compatibility       |
| `* * *`  | dater     | be able to key in my preferred horoscope in my preferences                                      | get recommended a suitable date based on our horoscope compatibility    |
| `* * *`  | dater     | be able to key in my preferred income in my preferences                                         | get recommended a suitable date based on our income compatibility       |
| `* * *`  | dater     | be able to key in my preferred age in my preferences                                            | get recommended a suitable date based on our age compatibility          |
| `* * *`  | dater     | be able to edit my entered preferences                                                          | change my preferences in the event I change my mind                     |
| `* * *`  | dater     | be able to view my preferences                                                                  | know what my preferences are in the event I forget                      |
| `* * *`  | dater     | be able to pull up a list of my previous dates                                                  | keep track of who I have dated in the past                              |
| `* * *`  | dater     | be able to delete dates from my list                                                            | limit my dating list to those who I am still interested in              |
| `* * *`  | dater     | to be able create a new date entry with his/her gender, name, income, height, horoscope and age | keep my list growing                                                    |
| `* * *`  | dater     | to be able to edit the details of my date                                                       | keep my dates details up to date                                        |
| `* * *`  | dater     | to be able to be recommended a complete random date                                             | have an exciting surprise date that day                                 |
| `* * *`  | dater     | to be able to filter my dates based on a particular metric                                      | find dates that I am interested in amidst my long and ever growing list |
| `* * *`  | dater     | to be able to be recommended the most compatible date for me                                    | optimize my chance of finding my one true love                          |
| `* * *`  | dater     | to be able to star dates                                                                        | keep track of outstanding dates                                         |
| `* * *`  | dater     | to be able to unstar dates                                                                      | keep focused on people who are still outstanding to me                  |
| `* * *`  | dater     | to be able to sort my dates based on a particular metric                                        | find dates that I am interested in amidst my long and ever growing list |
| `* * *`  | dater     | to be able to find dates based on their name                                                    | locate a date easily                                                    |
| `* *`    | lazy user | to be able to clear all the dates in my list                                                    | start afresh with a new date list                                       |

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Use cases

(For all use cases below, the **System** is the `LoveBook` and the **Actor** is the `user`, unless specified otherwise)

#### Use Case: List All Dates

**Main Success Scenario (MSS):**

1. User requests to list all dates.
2. LoveBook shows a list of dates.

   Use case ends. <br>

**Extensions:**

2a. The list is empty.

    2a1. Use case ends.

#### Use Case: Add a Date

**Main Success Scenario (MSS):**

1. User inputs command for adding date to LoveBook.
2. LoveBook validates the command.
3. LoveBook adds the new date to the LoveBook.
4. LoveBook displays a confirmation message.

   Use case ends. <br>

**Extensions:**

2a. The command is invalid (Fields do not exist, invalid format).

    2a1. LoveBook displays an error message.
    2a2. Use case ends.

#### Use Case: Finding a Date

**Main Success Scenario (MSS):**

1. User inputs command to search for a date in LoveBook.
2. LoveBook displays all Dates with names that match the keyword.

Use case ends. <br>

**Extensions:**

2a. No dates match the search query.

    2a1. LoveBook displays an error message.
    2a2. Use case ends.

#### Use Case: Edit a Date's Details

**Main Success Scenario (MSS):**

1. User inputs command to edit a Date's details.
2. LoveBook validates the command.
3. LoveBook updates the Date's details with the new information.
4. LoveBook displays a confirmation message.

Use case ends. <br>

**Extensions:**

2a. The command is invalid (Fields do not exist, invalid format).

    2a1. LoveBook shows an error message.
    2a2. Use case ends.

2b. No fields to edit are given.

    2b1. LoveBook displays an error message.
    2b2. Use case ends.

2c. Edited field(s) are invalid.

    2c1. LoveBook displays an error message.
    2c2 Use case ends.

2d. Index provided is invalid.

    2d1. LoveBook displays an error message.
    2d2. Use case ends.

#### Use Case: Delete a Date

**Main Success Scenario (MSS):**

1. User inputs command to delete Date.
2. Date is deleted from LoveBook.
3. LoveBook displays a confirmation message.

Use case ends. <br>

**Extensions:**

2a. Index provided is invalid.

    2a1. LoveBook displays an error message.
    2a2. Use case ends.

#### Use Case: Set Preferences

**Main Success Scenario (MSS):**

1. User inputs command to set Preferences.
2. LoveBook validates the command.
3. LoveBook updates the preferences with the new information.
4. LoveBook displays a confirmation message.

Use case ends. <br>

**Extensions:**

2a. The command is invalid (Preference does not exist, invalid format).

    2a1. LoveBook displays an error message.
    2a2. Use case ends.

2b. No Preferences to set are given.

    2b1. LoveBook displays an error message.
    2b2. Use case ends.

2c. Field(s) are invalid.

    2c1. LoveBook displays an error message.
    2c2. Use case ends.

2d. There are multiple values for a single field

- LoveBook displays a message indicating that there are multiple values for a single field.
- Use case ends.

#### Use Case: Show Preferences

**Main Success Scenario (MSS):**

1. User inputs command to show preferences.
2. LoveBook displays the preferences.

Use case ends. <br>

**Extensions:**

1a. No Preferences were previously set.

    1a1. LoveBook displays the default preferences.
    1a2. Use case ends.

#### Use Case: Get Blind Date

**Main Success Scenario (MSS):**

1. User inputs command to get a blind Date.
2. LoveBook displays a random Date.
3. LoveBook displays a confirmation message.

Use case ends. <br>

**Extensions:**

1a. The list is empty.

    1a1. LoveBook displays an error message.
    1a2. Use case ends.

#### Use Case: Filter Dates

**Main Success Scenario (MSS):**

1. User inputs command to filter dates.
2. LoveBook validates the command.
3. LoveBook shows a list of dates which are filtered based on the metric(s) given.

Use case ends. <br>

**Extensions:**

2a. The list is empty.

    2a1. Use case ends.

2b. The command is invalid (Metrics do not exist, invalid format).

    2b1.LoveBook displays an error message.
    2b2. Use case ends.

2c. No metric to filter by are given.

    2c1. LoveBook displays an error message.
    2c2. Use case ends.

2d. Field(s) are invalid.

    2d1. LoveBook displays an error message.
    2d2. Use case ends.

#### Use Case: Sort Dates

**Main Success Scenario (MSS):**

1. User inputs command to sort Dates
2. LoveBook validated the command.
3. LoveBook shows a list of dates which are sorted based on the comparator given.

Use case ends. <br>

**Extensions:**

2a. The list is empty.

    2a1. Use case ends.

2b. The command is invalid (Comparator does not exist, invalid format).

    2b1. LoveBook displays an error message.
    2b2. Use case ends.

2c. No comparator to sort is given.

    2c1. LoveBook displays an error message.
    2c2. Use case ends.

2d. Comparator is invalid.

    2d1. LoveBook displays an error message.
    2d2. Use case ends.

#### Use Case: Get Best Match

**Main Success Scenario (MSS):**

1. User requests for their best match.
2. LoveBook displays the best match.

Use case ends. <br>

**Extensions:**

1a. The list is empty.

    1a1. LoveBook displays an error message.
    1a2. Use case ends.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1. Usability and Accessibility: The application should provide clear and user-friendly CLI prompts and menus.
   It should support keyboard shortcuts for navigation to enhance accessibility.
2. Scalability: The LoveBook should be capable of storing at least 10,000 contacts without a significant decrease in
   performance.
3. Reliability and Availability: The application should have a 99.9% uptime, ensuring that users can access their
   contacts reliably.
   It should automatically back up LoveBook data daily to prevent data loss.
4. Portability: The CLI application should be compatible with multiple operating systems, including Windows, macOS, and
   Linux.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

### Glossary

| Term          | Definition                                                                                                        |
| ------------- | ----------------------------------------------------------------------------------------------------------------- |
| Date          | A person that the user is interested in and is currently seeing.                                                  |
| Metric        | A certain characteristic of a date. (e.g. Gender, Height)                                                         |
| Command       | Text that the user types into the application to perform an action.                                               |
| Parameter     | A value that the user provides to the application when executing a command. (e.g. in `gender/M` M is a parameter) |
| GUI           | Graphical User Interface                                                                                          |
| CLI           | Command Line Interface                                                                                            |
| Mainstream OS | Windows, Linux, Unix, OS-X                                                                                        |

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

---

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.
You are recommended to start with an empty LoveBook and follow the instructions sequentially
in order for the example commands provided to be relevant.
You can refer to the user guide for more details on the features.

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more _exploratory_ testing.

### Launch

1. Initial Launch.
2. Download the jar file and copy it into an empty folder.
3. Double-click the jar file.
4. Expected output: The program runs and shows the GUI. Note that the window size may not be optimum.

### Viewing Help

1. Opening the Help window.
2. Type the following help command into the text field.<br>
   `help`
3. Press enter.
4. Expected Output: The GUI shows a popup with a message and a link to the User Guide.

### Adding Dates

1. Adding a Date.
2. Type the following add command into the text field.<br>
   `add name/Cleon age/22 gender/F height/176 horoscope/Taurus income/3000`
3. Press enter.
4. Expected Output: The GUI shows the added date in the LoveBook.

### Editing Dates

<box type="tip">

_Prerequisites:_ Have at least 1 date in the LoveBook.

</box>

1. Editing a Date by index.
2. Type the following edit date command into the text field.<br>
   `edit 1 horoscope/Cancer name/John`
3. Press enter.
4. Expected Output: The GUI shows the newly-edited fields for the Date at the specified index. (Sequence doesn't matter)

### Finding Dates

#### Finding Dates by single name

<box type="tip">

_Prerequisites:_ Have at least 1 date with name containing "John" in the LoveBook.

</box>

1. Type the following find command into the text field.<br>
   `find John`
2. Press enter.
3. Expected Output: The date with specified name shown.

#### Finding Dates by multiple names

<box type="tip">

_Prerequisites:_ Have at least 2 dates (named "John" and "Cleon") in the LoveBook.

</box>

1. Type the following find command into the text field.<br>
   `find John Cleon`
2. Press enter.
3. Expected Output: The dates with the specified names shown.

#### Listing all Dates

1. Type the following list command into the text field.<br>
   `list`
2. Press enter.
3. Expected Output: The GUI shows all dates in LoveBook.

### Filtering Dates

1. Filtering Dates by metric (e.g. name, age, height).
2. Type the following filter command into the text field.<br>
   `filter age/22`
3. Press enter.
4. Expected Output: The GUI shows all dates with age 22 in LoveBook.

### Finding a Blind Date

<box type="tip">

_Prerequisites:_ Have at least 1 date in the LoveBook. Best to have more than 5 dates.

</box>

1. Finding a Blind Date
2. Type the following command into the text field.<br>
   `blindDate`
3. Press enter.
4. Expected Output: The GUI shows a blind date in LoveBook.

### Exiting the Application

1. Exiting the app
2. Use the `exit` command or click the 'X' button in the top right corner.
3. Expected: The app closes.

### Saving Data

1. Saving window preferences

   1. Resize the window to an optimum size, preferably full screen. Close the window.
   2. Re-launch the app by double-clicking the jar file.
   3. Expected Output: The most recent window size and location is retained.
   4. Note: The window looks best under 1920 x 1080 resolution, 125% scale.

2. Saving data
   1. Launch the app by double-clicking the jar file.
   2. Execute an add command to add a `Date` in the database.
   3. Close the app.
   4. Expected: A `data` folder is created under the current repository where the jar file is located.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

---

## **Appendix: Effort**

Implementing LoveBook was not straightforward and often required us to brainstorm as a team to solve the challenges faced.
Given below is a summary of the effort our team has put into developing LoveBook as well as challenges faced.

### Evolving of AB3 into LoveBook

As we wanted to morph AB3 to fit into our idea of LoveBook, we had to refactor a large portion of the initial codebase
and implement several new classes to get the basic minimum viable product of LoveBook.
This included creating the Date class and its associated inner field classes. We also had to update how the application
will store Dates and DatePrefs separately. In addition, many previous commands of AB3 had to be refactored or changed,
and we added several new commands to LoveBook as well. Eventually, we were able to successfully implement both the Date
and DatePref classes and all their associated commands.

One challenge we faced was implementing the bestMatch feature. Given the tight timeline of the team project, we decided
to do a simple implementation of this feature that does not provide user with much flexibility. If given more time, we would like
to develop this further by incorporating the use of artificial intelligence, as it is a relevant feature that will be used in the real
world and brings value to our target users.

### Revamping of UI

Our dedication to enhancing the aesthetics of LoveBook is highlighted by our attention to visual representation.
We went beyond mere textual displays, incorporating visually engaging elements such as gender icons, horoscope symbols,
and star command visual cues, all implemented using JavaFX. These features not only contribute to the overall visual
appeal of the application but also serve a functional purpose in providing users with quick and intuitive insights into
important date attributes. As our team was unfamiliar with JavaFX initially, it took us a great amount of time and
effort to produce an eventual satisfactory and working UI that we were proud to adopt and incorporate into our application.

[Scroll back to _Table of Contents_](#table-of-contents)

<div style="page-break-after: always;"></div>

## **Appendix: Planned Enhancements**

1. Improve the command parser to be more robust. Some examples include:

   - Gender entered while adding a new date is case-sensitive. We are planning to make it case-insensitive.
   - Adding dates with the same name is currently not allowed. We are planning to allow this.
   - Allowing some fields to be optional if user doesn't have access to the information. For example, if the user does
     not know the income of the date, he/she can leave it blank. Currently, this is not allowed. We are planning to
     allow this.

2. Improve the income field to be more robust

   - Currently, the income field is unable to accept a range of values, which might not be inclusive of people who
     have careers with variable income.
   - Additionally, right now the income field does not accept a value of 0. This is not inclusive towards students
     who may not have any income. Hence, we plan to modify the field to accept a value of 0, on top of supporting a
     range of values

3. Improve issues regarding sorting stability

   - Currently, you are able to sort the dates by a specific field. However, if we sort by income for instance, and two
     dates have the same value, then when one of those two dates are modified by operations such as star or edit, the
     order of the two of them can change. The dates are still sorted in order, just that the stability is disrupted. We
     intend to improve upon our star and edit commands such that they do not disrupt the stability in the future.

4. Improve the filter feature to be more robust. Some examples include:

   - Currently, the filter feature only allows the user to filter by metrics limited to name, age, gender and height.
     We are planning to allow the user to filter by other metrics such as horoscope and income.
   - Currently, the filter feature only allows the user to filter by a single keyword for a single metric. We are
     planning to allow the user to filter by multiple keywords.

5. Improve the error message to be more comprehensive

   - Currently, the error message for user that key in multiple invalid keywords only spots the first invalid keyword.
     We are planning to allow the user to know all the invalid keywords that he/she has keyed in.
   - Currently, the error message for filter command with duplicate fields only display the invalid field value error. We are
     planning to allow the user to know that he/she has keyed in duplicate fields.

6. Improve the message displayed when the user tries to perform an operation on an empty list

   - Currently, for some commands like edit and sort, the message displayed is simply based on the validity check of the parameters and it does not tell the user that the
     list is empty. For instance, when the user sorts an empty list, the message displayed is "Sorted!" which is not very helpful. Hence, we are planning to tell the user that the list is empty and that the operation cannot be performed.

7. Improve the presets bar feature to be more comprehensive and clear

   - Currently, the presets bar feature only accommodates for the commands: `add`, `edit`, `delete`, `setP` and `showP`.
   - In the future, we plan to add more presets buttons for all 16 commands in the application.
   - Furthermore, even though there's `clear` command, the button "clear" removes all text in the command box, making
     it ambiguous. We plan to change this in a future iteration like a trash can icon.

8. Improve the filter and sort command formats

   - Currently, the filter and sort commands do not have a strict format. For example, if the user enters a filter command
     with at least one valid metric and valid metric value, the filter command will still be executed successfully. We plan
     to come up with a stricter filter and sort format in the future to make it more user-friendly.

9. Improve the best match feature to be more flexible
   - Currently, the best match feature gives equal weightage to all characteristics: `Income`, `Age`, `Height` and `Horoscope`
   - To better suit the user preferences, we plan to make the weightage customizable, so users can change the weightage for each characteristic

[Scroll back to _Table of Contents_](#table-of-contents)
