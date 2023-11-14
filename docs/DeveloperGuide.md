---
title: Developer Guide
show-sticky-toc: true
---

{% include toc.md header=true show-in-toc=true ordered=true %}

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

* Reused code from [ToothTracker](https://github.com/AY2324S1-CS2103T-W10-3/tp) for table of contents, sticky navigation and auto-numbering of headers for website

--------------------------------------------------------------------------------------------------------------------

## Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## Design

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

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

[**`commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">
:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

--------------------------------------------------------------------------------------------------------------------

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## Implementation

This section describes some noteworthy details on how certain features are implemented.

### Hide/unhide feature

The hide/unhide mechanism introduces the capability to selectively hide certain applicants from view, improving user experience and providing greater control over the displayed information. This also includes a way to view all hidden applicants in a list. 

#### Implementation

This feature implements the following operations:

* `HideCommand#execute()` — Hides the specified applicant from the list.
* `UnhideCommand#execute()` — Unhides the specified applicant from the list.
* `UnhideAllCommand#execute()` — Unhides all hidden applicants.
* `ListHiddenCommand#execute()` — Displays a list of all hidden applicants.

Given below is an example usage scenario and how the hide/unhide mechanism behaves at each step.

Step 1. The user launches the application for the first time. The applicant list displays all applicants without any hidden applicants.

Step 2. The user decides to hide the 3rd applicant in the applicant list by executing hide 3. The `hide` command calls `Model#setPerson()` to replace the applicant with a hidden version.  `Model#updateFilteredPersonList()` to update the list of applicants displayed in the UI to exclude the hidden applicant.

Step 3. The user executes `list hidden` to view all hidden applicants. The `list hidden` command calls `Model#updateFilteredPersonList()` to update the list of applicants displayed in the UI to include only hidden applicants.

Step 4. The user decides to unhide the 1st applicant in the hidden applicant list by executing unhide 1. The `hide` command calls `Model#setPerson()` to replace the hidden applicant with a non-hidden version. The `unhide` command calls `Model#updateFilteredPersonList()` to update the list of applicants displayed in the UI to include the unhidden applicant.

Step 5. Alternatively, the user can choose to unhide all hidden applicants by executing unhide all. The `unhide all` command creates a copy of the model and calls `Model#updateFilteredPersonList()` and `Model#getFilteredPersonList()` on that model to receive a list of hidden applicants. It then replaces every hidden person in the original model with a non-hidden version by calling `Model#setPerson()`. The `unhide all` command calls `Model#updateFilteredPersonList()` to update the list of applicants displayed in the UI to include all unhidden applicants.

The following sequence diagram shows how the undo operation works:

![HideSequenceDiagram](images/HideSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/HideActivityDiagram.png" width="250" />

### Bookmark/Unbookmark feature

The bookmark/unbookmark mechanism gives users the ability to bookmark or unbookmark certain applicants they want to take note of, as well as list these bookmarked applicants. This allows users to better differentiate between a long list of applicants, improving the ease of usage of this application and user experience.

#### Implementation

The bookmark/unbookmark mechanism is mainly facilitated by the `BookmarkCommand/UnbookmarkCommand`, `BookmarkCommandParser/UnbookmarkCommandParser`, and `isBookmarked` classes. It extends the abstract class `Command` with an `execute` functionality, to facilitate the execution of the command. Specifically, the bookmark/unbookmark feature is implemented through the following components and operations:
- `BookmarkCommand/UnbookmarkCommand` — Core component responsible for executing the bookmarking/unbookmarking of a specific applicant.
- `BookmarkCommandParser/UnbookmarkCommandParser` — Contains the functionalities for user input parsing. It ensures that user input is valid as a bookmark/unbookmark command by meeting specific requirements.
- `Person` — Represents the TA applicants with their respective fields, such as `isBookmarked`.
- `isBookmarked` — Represents whether an applicant is bookmarked or not through the use of a Boolean value.

Given below is an example usage scenario and how the bookmark/unbookmark mechanism behaves at each step.

Step 0. Assume that there is an existing list of applicants in the application after launch.

Step 1. The user launches the application. Applicant list is displayed.

Step 2. The user executes `bookmark 5` command to bookmark the 5th applicant in TAfinder. The `BookmarkCommmandParser` is invoked to parse the user's input.

Step 3. `BookmarkCommandParser` will then invoke ParserUtil for parsing of the index and check for index errors. If the index is invalid, the system will generate an error message. The error message will be displayed to the user, providing clear feedback about the issue and the specific constraints that are not met.

Step 4. If the index is valid, `BookmarkCommand#execute()` fetches the intended applicant from the currently visible list and bookmarks the applicant with the corresponding index.

Step 5. Then, `BookmarkCommand#execute()` updates `Model#setModel` with the updated Person. Displaying the updated applicant list with a bookmark indicator.

Step 6. A success message is displayed to the user to confirm that the applicant has been bookmarked successfully.

Step 7. The user then decides to view all bookmarked applicants by executing the `list-bookmarked` command. The `list-bookmarked` command calls `Model#updateFilteredPersonList()`, which updates the list of applicants presented in the UI to only include bookmarked applicants.

The following sequence diagram shows how the bookmark operation works:

![BookmarkSequenceDiagram](images/BookmarkSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a `bookmark` command:

![BookmarkActivityDiagram](images/BookmarkActivityDiagram.png)

### View feature

The view mechanism gives users the ability to view a specific applicant's details. This allows users to better focus on and evaluate a singular applicant when they need to, improving the ease of usage of this application and user experience.

#### Implementation

The view mechanism is mainly facilitated by the `ViewCommand` and `ViewCommandParser` classes. It extends the abstract class `Command` with an `execute` functionality, to facilitate the execution of the command. Specifically, the view feature is implemented through the following components and operations:
- `ViewCommand` — Core component responsible for executing the the viewing of a singular applicant's details from the list.
- `ViewCommandParser` — Contains the functionalities for user input parsing. It ensures that user input is valid as a view command by meeting specific requirements.
- `Person` — Represents the TA applicants with their respective fields.

Given below is an example usage scenario and how the view mechanism behaves at each step.

Step 0. Assume that there is an existing list of applicants in the application after launch.

Step 1. The user launches the application. Applicant list is displayed.

Step 2. The user executes `view 5` command to view the details of the 5th applicant in TAfinder. The `ViewCommmandParser` is invoked to parse the user's input.

Step 3. `ViewCommandParser` will then invoke ParserUtil for parsing of the index and check for index errors. If the index is invalid, the system will generate an error message. The error message will be displayed to the user, providing clear feedback about the issue and the specific constraints that are not met.

Step 4. If the index is valid, `ViewCommand#execute()` fetches the intended applicant.

Step 5. Then, `CommentCommand#execute()` calls `Model#showPersonAtIndex` to set `Model#currentPerson` with the intended applicant (in the form of a `Person`). `PersonDetailPanel` displays details of the intended applicant corresponding to the given index.

Step 6. A success message is displayed to the user to confirm that specified applicant's details has been displayed successfully.

The following sequence diagram shows how the `view` operation works:

![ViewSequenceDiagram](images/ViewSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a `view` command:

![ViewActivityDiagram](images/ViewActivityDiagram.png)


### Compare feature

The compare mechanism allows users to compare two distinct TA applicants in the TAfinder app.

#### Implementation

The compare mechanism is mainly facilitated by the `CompareCommand`, `CompareCommandParser`, and `CompareWindow` classes. It extends the abstract class `Command` with an `execute` functionality, to facilitate the execution of the command. Specifically, the compare feature is implemented through the following components and operations:

- `CompareCommand` — Core component responsible for executing the comparison of two TA applicants in the list.
- `Person` — Represents the TA applicants with their respective fields, such as `Gpa`, to be used for comparison.
- `CompareCommandParser` — Contains the functionalities for user input parsing. It ensures that user input is valid as a compare command by meeting specific requirements.
- `CompareWindow` — Main User Interface (UI) for after a compare command is successfully executed. It will display the content of the two TA applicants side by side.


Given below is an example usage scenario and how the compare mechanism behaves at each step.

Step 0. Assume that there is an existing list of applicants in the application after launch.

Step 1. The user enters the compare command `compare 1 2` to compare the first and second applicants in the existing list. The `CompareCommandParser` is invoked to parse the user's input.

Step 2. `CompareCommandParser` will then invoke `ParserUtil` for parsing of the indices and check for index errors. If indices are invalid, the system will generate an error message. The error message will be displayed to the user, providing clear feedback about the issue and the specific constraints that are not met.

Step 3. If indices are valid, `CompareCommand#execute()` fetches the two intended applicants from the currently visible list and ensures that both indexes do not point to the same applicant.

Step 4. Then, `CompareCommand#execute()` creates a new `CompareWindow` instance which is immediately shown, with the two applicants' information passed to `CompareWindow`, in the form of the `Person` model.

The following sequence diagram displays how the compare function works until Step 4:

![CompareSequenceDiagram](images/CompareSequenceDiagram.png)

Step 5. `CompareWindow` handles the GUI presentation aspects, in the form of a pop-up window. It uses the JavaFX `GridPane` layout to display the respective `Person` attributes side-by-side.

Step 6. A success message is displayed to the user to confirm that the comparison of applicants is successful.

The following activity diagram summarizes what happens when a user executes a `compare` command:

![CompareActivityDiagram](images/CompareActivityDiagram.png)

### Comment feature

The comment command allows users to insert a comment on TA applicants in TAfinder app.

#### Implementation

The comment mechanism is mainly facilitated by the `CommentCommand` and `CommentCommandParser` classes. It extends the abstract class `Command` with an `execute` functionality, to facilitate the execution of the command. Specifically, the comment feature is implemented through the following components and operations:

- `CommentCommand` — Core component responsible for executing the adding of comments to a TA applicant in the list.
- `Person` — Represents the TA applicants with their respective fields, which includes the `comment` field.
- `CommentCommandParser` — Contains the functionalities for user input parsing. It ensures that user input is valid as a compare command by meeting specific requirements.
- `MainWindow` — Main User Interface (UI) for after a comment command is successfully executed. It will display the content of the TA applicant with the comment added.'

Given below is an example usage scenario and how the comment mechanism behaves at each step.

Step 0. Assume that there is an existing list of applicants in the application after launch.

Step 1. The user enters the comment command `comment 3 c/Hardworking` to comment on the 3rd applicant in the applicant 
list with `Hardworking` as the comment by executing. The `CommentCommmandParser` is invoked to parse the user's input.

Step 2. If the index is valid, `CommentCommand#execute()` fetches the intended applicant from the currently visible list 
and adds the comment `Hardworking` to the applicant with the corresponding index. The adding of comment is destructive, 
meaning if the specified applicant has an existing comment, it will be overwritten.

Step 3. `CommmentCommandParser` will then invoke ParserUtil for parsing of the index and check for index errors. 
If the index is invalid, the system will generate an error message. The error message will be displayed to the user, 
providing clear feedback about the issue and the specific constraints that are not met.

Step 4. Then, `CommentCommand#execute()` updates `Model#setModel` with the updated Person. 
Displaying the updated applicant list in `MainWindow` with the comment added.

Step 5. A success message is displayed to the user to confirm that the comment has been added to the applicant successfully.

![CommentSequenceDiagram](images/CommentSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a `comment` command:

![CommentActivityDiagram](images/CommentActivityDiagram.png)

#### Design considerations

**Aspect: Comparison GUI:**

* **Alternative 1 (current choice):** Compare two applicants in one pop-up window.
    * Pros:
      1. Easier to implement the pop-up window
      2. Able to avoid over-dependencies within the UI component.
    * Cons: Design may not be uniform with main window.

* **Alternative 2:** Compare two applicants in the main window.
    * Pros:
      1. Easy view of information.
    * Cons:
      1. Difficult to implement the UI change when a compare command is inputted, as whole window needs to be modified.
      2. Inconvenient to refer back to list of applicants if needed.

* **Alternative 3:** Compare multiple (two or more) applicants.
    * Pros: More convenient to choose between applicants.
    * Cons: Harder to implement the UI.


### Import feature

The import feature allows users to mass-create applicants within the application, from a list of applicants in a CSV file. This file could be exported from mySoC or elsewhere, as long as it is in the correct format.

#### Implementation

The format of the CSV file is as follows:

1. The file must only contain text separated by commas and newlines — a limited subset of the true CSV file format. No escaping or quoting is allowed.
2. The first line must be the header row, containing column names. The columns can be in any order, but must contain the following (case-sensitive):
   - studentNo
   - name
   - phone
   - email
   - gpa
   - previousGrade
   - tags
3. The following lines contain one applicant per row. The data of the applicant should be in the order of which the header row specified the columns i.e. if column 1 is `studentNo`, the first cell of each row should contain the applicant's student number.

Here is an example CSV:

```csv
studentNo,name,phone,email,gpa,previousGrade,tags
A0123486A,Jasmine David,98472983,jasmine_david@u.nus.edu,4.3,B+,deansList;pastTA
A0456123A,Sandeep Kopparthi,86753746,sandeep@u.nus.edu,5.0,B+,pastTA
A0775848D,Lim Boon Kong,97777777,boonkong@u.nus.edu,3.5,C,deansList
A0483910A,Mohammed Taufiq bin Rozaini,85535252,taufiq@u.nus.edu,4.2,A+,
```

The import mechanism is mainly facilitated by the `ImportCommand`, `ImportCommandParser`, and `Attachment` classes. It extends the abstract class `Command` with an `execute` functionality, to facilitate the execution of the command. Specifically, the import feature is implemented through the following components and operations:

- `ImportCommand` — Core component responsible for executing the import of TA applicants from the CSV file.
- `ImportCommandParser` — Contains the functionalities for user input parsing. It ensures that user input is valid as an import command by meeting specific requirements.
- `Person` — Represents the TA applicants with their respective fields, such as `Attachment`, which are created and added to the main model.
- `Attachment` — Represents a reference to a file. This is merely used as a representation and handle to a file rather than to attach to any `Person` in particular.

Given below is an example usage scenario and how the attach mechanism behaves at each step.

Step 0. Assume that there is a valid CSV file to import named `applicants.csv`, such as the one above, and that it is in the same directory as the `tafinder.jar` file that was executed.

Step 1. The user enters the import command `import applicants.csv` to import applicants from the CSV file specified in the previous step.

Step 2. `ImportCommandParser` will then invoke `ParserUtil` for parsing of the file path to check for any invalid path characters. If the file path contains invalid path characters, the system will generate an error message. The error message will be displayed to the user, providing clear feedback about the issue and the specific constraints that are not met.

Step 3. If indices are valid, `ImportCommand#execute()` opens the file and creates a `Scanner` over the file handle.

Step 4. Then, the header row of the CSV is read and the order of the headers is saved into a map for later reference.

Step 5. Next, a loop is executed to read through the rows of the CSV one-by-one. Each row is split via the comma, then parsed in the order that was recorded in the previous step. Any validation errors found cause a new error line number to be reported as an error, but no exception is thrown as we want to parse as many applicants as possible. A `Person` instance is created with the relevant data for each successful line i.e. no validation error.

Step 6. Finally, a message is displayed to the user indicating the number of applicants successfully imported, the number of applicants that failed to be imported, and the line numbers containing those failed applicants.


### Attach feature

The attach feature allows users to attach files to TA applicants in the app. Attaching files *copies* these files into the `data` directory and adds a reference to those files to that `Person` model. This means that even if the original files are deleted, TAfinder would still have access to the copies of those files.

#### Implementation

The attachment mechanism is mainly facilitated by the `AttachCommand`, `AttachCommandParser`, and `Attachment` classes. It extends the abstract class `Command` with an `execute` functionality, to facilitate the execution of the command. Specifically, the attach feature is implemented through the following components and operations:

- `AttachCommand` — Core component responsible for executing the attachment of a file to an applicant.
- `AttachCommandParser` — Contains the functionalities for user input parsing. It ensures that user input is valid as an attach command by meeting specific requirements.
- `Person` — Represents the TA applicants with their respective fields, such as `Attachment`, to attach files to.
- `Attachment` — Represents a reference to a file. This can be a file that has been "attached" to a `Person`, or just a file within the file system of the computer.

Given below is an example usage scenario and how the attach mechanism behaves at each step.

Step 0. Assume that there is an existing list of applicants in the application after launch.

Step 1. The user enters the attach command `attach 1 f/Downloads/resume.pdf f/Downloads/cv.txt` to attach the files `resume.pdf` and `cv.txt` in the `Downloads` directory to the first user in the visible list.

Step 2. `AttachCommandParser` will then invoke `ParserUtil` for parsing of the index and check for index errors, and then parses the file paths to check for any invalid path characters. If index is invalid or the file path contains invalid path characters, the system will generate an error message. The error message will be displayed to the user, providing clear feedback about the issue and the specific constraints that are not met.

Step 3. If indices are valid, `AttachCommand#execute()` fetches the intended applicant from the currently visible list.

Step 4. Then, `AttachCommand#execute()` copies each attachment into the `data` directory, to the path `data/attachments/{student number}/{filename}`.

Step 5. Finally, a success message is displayed to the user indicating the number of attachments that have been copied.

The following sequence diagram shows how the `attach` operation works:

![AttachSequenceDiagram](images/AttachSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a `attach` command:

![AttachActivityDiagram](images/AttachActivityDiagram.png)

--------------------------------------------------------------------------------------------------------------------

## Documentation, logging, testing, configuration, dev-ops

We've broken these out into separate guides, do check them out.

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## Appendix: Requirements

### Product scope

**Target user profile**:

* **University Professors**, specifically:
   * Tech-savvy
   * Within NUS SoC
   * Responsible for selecting student TAs
   * Managing a significant number of TA applicants
   * Can type fast
   * Prefers typing over all other means of input

**Value proposition**:

* **Efficient TA Selection:**
  * Simplify the process of identifying and selecting qualified TAs within NUS SoC. Find the most suitable TAs from a diverse pool of applicants efficiently.

* **Enhanced Convenience:**
  * Conveniently connect with students and potential TAs, streamlining communication and making the TA selection process smoother.

* **Effective Evaluation:**
  * Assess TA applicants based on a range of holistic rubrics, including grades, student life involvement, past TA experiences, etc.


### User Stories

#### High Priority (Must-Have)

| As a...                        | I want to...                                        | So that I can...                                      |
| ------------------------------ | -------------------------------------------------- | ---------------------------------------------------- |
| NUS SOC professor              | add a TA applicant and details to the list         | see the TA applicant in the list                      |
| NUS SOC professor              | hide a TA applicant from the list                  | narrow down the list of applicants for review         |
| NUS SOC professor              | edit the details of a TA applicant                 | keep their information up-to-date or correct errors    |
| NUS SOC professor              | click to view the full details of a TA applicant    | determine if they’re a fit for the position accordingly |
| NUS SOC professor              | search for a specific TA applicant by name or details | easily find their information without scrolling        |
| NUS SOC professor              | delete a TA applicant                               | remove entries that are no longer needed               |

#### Medium Priority (Nice-to-Have)

| As a...                        | I want to...                                              | So that I can...                                            |
| ------------------------------ | -------------------------------------------------------- | --------------------------------------------------------- |
| NUS SOC professor who wants the best for my students              | sort TA applicants by grades or other factors             | find the best TA applicant for my module                   |
| NUS SOC professor              | add comments/notes for myself on a TA applicant          | refer to them when comparing applicants                     |
| NUS SOC professor              | export the list of TA applicants to a spreadsheet        | easily share the list with colleagues or refer to it offline |
| NUS SOC professor              | compare between two TA applicants on the same screen     | make a final decision on selecting the TA for my module     |
| NUS SOC professor              | rate and provide feedback on TA applicants after interviews or assessments | collaborate with colleagues on the hiring decision.   |
| NUS SOC professor              | attach files (e.g., resumes) to TA applicant profiles       | keep all pertinent information in one place.                  |

#### Low Priority (Unlikely to Have)

| As a...                        | I want to...                                                | So that I can...                                              |
| ------------------------------ | ---------------------------------------------------------- | ----------------------------------------------------------- |
| NUS SOC professor              | set up automated notifications for when new TA applicants apply | stay informed without constantly checking the list.        |
| NUS SOC professor              | send TA applicants invitations and reminders                | schedule interviews with TA applicants directly through the system. |
| NUS SOC professor              | track the progress and performance of hired TAs throughout the semester | provide feedback and assess their contributions.      |
| NUS SOC professor              | filter applicants by availability and schedule             | match them with suitable time slots for my module            |
| NUS SOC professor              | set up automatic reminders for TA performance evaluations | stay organized and proactive in managing my teaching assistants. |
| NUS SOC professor              | track the number of applicants for each TA position       | gauge the level of interest and competition for specific roles. |
| NUS SOC professor              | archive or store past TA applicant data                   | reference them in future semesters or academic years.       |
| NUS SOC professor              | sort the list of TA applicants by their application status | streamline the selection process.                            |
| NUS SOC professor              | archive previous TA applicant profiles                     | maintain a historical record of applicants.                   |
| NUS SOC professor              | create and manage multiple TA applicant lists               | keep information organized for different modules or terms.   |
| NUS SOC professor              | batch process approvals or rejections of TA applications     | save time during the selection process.                        |
| NUS SOC professor              | access a help feature within the platform                   | understand how to use different functionalities efficiently.  |
| NUS SOC professor              | customize the appearance and layout of the TA applicant list | enhance the user experience.                                  |
| NUS SOC professor              | flag TA applicants for further review                       | remember to revisit certain profiles as the process continues. |
| NUS SOC professor              | generate reports summarizing TA applicant data              | easier overview and presentation to department heads or committees. |
| NUS SOC professor              | retrieve deleted or archived TA applicant data              | recover information if needed.                                |
| NUS SOC professor              | access a log of all actions performed on the system          | enhance security and accountability.                          |
| NUS SOC professor              | receive recommendations on TA applicants based on AI analysis | facilitate a smarter selection process.                       |
| NUS SOC professor              | import a spreadsheet of TA applicants to my module       | save time by not adding them individually                    |
| NUS SOC professor              | search for a TA applicant using an identifier             | contact the TA directly if needed                            |

### Use cases

(For all use cases below, the **System** is `TAfinder` and the **Actor** is `NUS SOC professor`, unless specified otherwise)

**UC01 - Edit an applicant**

**Preconditions:**

* User is logged into the system.
* There is at least one applicant in the list.

**MSS**

1. User requests to edit an applicant's information.
2. System requests the index of the applicant to edit.
3. User specifies the index and information.
4. System validates the input.
5. System updates the applicant's information.
6. System shows the updated list of applicants.

    Use case ends.

**Extensions**

* 4a. The given index or information is invalid.

    * 4a1. System shows an error message.

      Use case resumes at step 2.


**UC02 - Compare Two Applicants**

**Preconditions:**

* User is logged into the system.
* There are at least two applicants in the list.

**MSS**

1. User requests to compare two applicants.
2. System requests the indices of the first and second applicants to compare.
3. User specifies the indices.
4. System validates the input.
5. System retrieves the information of both applicants.

   Use case ends.

**Extensions:**

* 4a. The given indices are missing or invalid.
   * 4a1. System shows an error message

   Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should be able to process user inputs in at most 100 milliseconds.
4.  Should have intuitive and easy-to-use commands that corresponds closely to their use cases (i.e. 'add' to add an item).
5.  Non-trivial methods and classes should be well-documented.
6.  Should provide a comprehensive help section accessible to professors for all the different features.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **TA (Teaching Assistant)**: A graduate or undergraduate student who assists a professor in teaching activities, such as grading, leading discussions or conducting tutorials.
* **NUS professor**: A faculty member at the National University of Singapore (NUS) responsible for teaching and academic activities.

--------------------------------------------------------------------------------------------------------------------

## Appendix: Instructions for Manual Testing

### Launch and shutdown

1. Initial launch
    1. Download the jar file and copy into an empty folder
    2. Open a command-line and navigate to the directory the jar file is in
    3. Run the command `java -jar tafinder.jar`. Expected: Shows the GUI with a set of sample TA applicants. The window size may not be optimum.
2. Shutdown
    1. Click “File”
    2. Click “Exit”. Expected: The app closes.

### Hide and unhide

1. Hide the first applicant
    1. Run `hide 1`. Expected: The first applicant disappears.
    2. Run `list hidden`. Expected: Only the first applicant is shown.
2. Unhide the hidden applicant
    1. Run the commands above beforehand to have a hidden applicant
    2. Run `list hidden`
    3. Run `unhide 1`. Expected: All the applicants are shown including the unhidden first applicant.
3. Unhide all applicants
    1. Ensure there are at least 2 applicants in the current view.
    2. Run `list`
    3. Run `hide 1` then `hide 1` again. Expected: The first 2 applicants disappear.
    4. Run `unhide-all`. Expected: The first 2 applicants appear in the list again.

### Bookmark and unbookmark

1. Bookmark the first applicant
    1. Run `bookmark 1`. Expected: The bookmark icon next to the first applicant becomes filled.
    2. Run `list bookmarked`. Expected: All the applicants that are bookmarked are shown.
2. Unbookmark the first applicant
    1. Run the commands above beforehand to have a bookmarked applicant
    2. Run `list bookmarked`
    3. Run `unbookmark 1`. Expected: The unbookmarked applicant disappears from the list, so only the remaining bookmarked applicants are shown.

### Sort

1. Run `list`.
2. Run `sort gpa`. Expected: The applicants should be sorted by GPA in descending order. 

### View

1. Run `view 1`. Expected: All the details of the applicant with the index 1 are shown in the detail view.

### Compare

1. Ensure that there are at least 2 applicants in the current view
2. Run `compare 1 2`. Expected: A new window is created with details from both applicants.

### Import

1. Copy and paste the file below into a file called `sample.csv` in the **same directory** as the jar file 
2. Run `import sample.csv`. Expected: 4 applicants are successfully imported into TAfinder.

```
studentNo,name,phone,email,gpa,previousGrade,tags
A0123486A,Jasmine David,98472983,jasmine_david@u.nus.edu,4.3,B+,deansList;pastTA
A0456123A,Sandeep Kopparthi,86753746,sandeep@u.nus.edu,5.0,B+,pastTA
A0775848D,Lim Boon Kong,97777777,boonkong@u.nus.edu,3.5,C,deansList
A0483910A,Mohammed Taufiq bin Rozaini,85535252,taufiq@u.nus.edu,4.2,A+,
```

### Attach

1. Download or create a sample PDF file (you can use the exported user guide as a sample) and place it in the **same directory** as the jar file
2. Ensure there is at least one applicant in the list
3. Run `attach 1 f/name-of-file.pdf`. Expected: The file is attached to the first applicant by indication of filename in applicant details. The details can be viewed by running the command `view 1`.

--------------------------------------------------------------------------------------------------------------------

## Appendix: Effort

### Difficulty Level

- Numerous challenges were encountered during the project's execution.
- One of the challenges we faced was working together as a team and coordinating efforts to minimize merge conflicts. We held regular team meetings to discuss progress and identify conflicts. We used version control tools like Git to track changes and resolve conflicts early. We divided tasks among team members and established coding conventions for consistency. When conflicts occurred, we had a designated merge manager to resolve them. By prioritizing communication, planning, and conflict resolution, we minimized the impact of merge conflicts on our project.
- Another major challenge we faced was managing and meeting tough deadlines as the project became more complex. To address this, we prioritized tasks based on importance and urgency, adjusted our timeline as needed, communicated openly within the team, and optimized productivity by leveraging our strengths. With this proactive and strategic approach, we successfully met the deadlines and delivered high-quality results.
- Overall, we found this project to be challenging but rewarding. It provided us with opportunities to enhance our technical skills, collaborate effectively as a team, and overcome various obstacles. Despite the difficulties, we are proud of the accomplishments and growth we achieved throughout the project.

### Effort Required

- Reused `model` classes that were already implemented (e.g. `AddressBookParser`, `Person`)
- Reused `ui` classes and FXML files for certain parts of the overall GUI (eg. input box, message box)
- Reused `Storage` classes that saves and loads data from files

### Achievements

- For most of us, this was our first experience contributing pull requests (PRs) to a project that was already established and had some existing code (known as a brownfield project).
- This opportunity allowed us to expand our skills and knowledge beyond just working on Orbital, and gave us valuable experience in working with a larger and more complex codebase.
- By contributing to a brownfield project, we were able to gain insights into the challenges of maintaining and improving existing code, and learn from the expertise of other developers who had worked on the project before us.

--------------------------------------------------------------------------------------------------------------------

## Appendix: Planned Enhancements

1. **Limiting GPA Field to 2 Decimal Places**
    - The current `GPA` field can accept more than 2 decimal places. We plan to limit the field to strictly 2 decimal places, and anything more than that will generate an error message.
2. **Limiting Phone Number Field to At Most 8 Numbers**
    - The current `phone number` field accepts unlimited numbers. We plan to limit the field to at most 8 numbers (length of phone number in Singapore), as our target audience is NUS professors and Teaching Assistants (TAs).
3. **Requiring Alphabetical Characters in the Name Field**
    - The current `name` field accepts a string of only numbers as a name, which should not be the case. We plan to limit the field to require alphabetical characters, and anything else would result in an error message.
4. **Improving Display of Added Applicant Message**
    - When a person is successfully added using the `add` command but there is no `comment` field for the person, the current main UI window will display a message `New applicant added: erferferferf Doe; Phone: 98765432; Email: [johnd@example.com](mailto:johnd@example.com); GPA: 4.9; Comment: Optional.empty; Tags: [pastTA]`, with a default `Optional.empty` string for the Comment field. We plan to enhance the message by removing the display of the `Comment` field, when none is added.
5. **Improving Error Messages for Commands Requiring an Index**
    - Currently, our application only accepts positive numbers (1, 2, 3…) as acceptable inputs that conform to the command format for commands that require indexes (it then validates these numbers to check the range etc.). Therefore, non-positive numbers (0, -1…) are considered as invalid formatting of the errors. Thus, the current error message is ‘`Invalid command format!'`. We plan to change the error message to better reflect the issue with non-positive numbers rather than have a generic invalid command format message.
6. **Making Applicants in List Clickable**
    - Although there is visual feedback on clicking each of the applicants, the details shown on the right side of the application does not change. We plan to allow the clicking of applicants in addition to the command `view INDEX` to show the details of each applicant.
7. **Enhancing Compare Command Popup Window**
    - Upon successfully invoking the `compare` command, a popup window will be displayed. However, if any of the applicants’ details (fields) are too long, the details will not be able to be displayed fully. We plan to enhance the popup window by allowing the details to be displayed fully by implementing scroll panels for each field instead.
8. **Adjusting Color Schemes of Help and Compare Windows**
    - The current UI color scheme for the help and compare windows, currently grey, differ quite a bit from the main window, where the color scheme is purple-centric. We plan to adjust the color schemes of these 2 pop-up windows to match that of the main window.
9. **Limiting Interview Score Field to 2 Decimal Places**
    - The current `Interview Score` field can accept more than 2 decimal places. We plan to limit the field to strictly 2 decimal places, and anything more than that will generate an error message.
10. **Preventing Integer Overflow When Adding Applicants**
    - If the user were to add more than 2147483647 (max integer for Java), an integer overflow error may result on the input of any command. Our enhancement would be to prevent users from adding more applicants when 2147483647 applicants are already in the list.
