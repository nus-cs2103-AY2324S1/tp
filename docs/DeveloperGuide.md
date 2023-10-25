---
layout: page
title: Developer Guide
---

# Table of Contents
1. [Acknowledgements](#acknowledgements)
2. [Setting up, getting started](#setting-up-getting-started)
3. [Design](#design)
   - [Architecture](#architecture)
   - [UI component](#ui-component)
   - [Logic component](#logic-component)
   - [Model component](#model-component)
   - [Storage component](#storage-component)
   - [Common classes](#common-classes)
4. [Implementation](#implementation)
   - [Undo/Redo](#proposed-undoredo-feature)
   - [Filter](#proposed-filter-by-tag-feature)
   - [Markdown Support](#proposed-markdown-support-feature)
5. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
6. [Appendix: Requirements](#appendix-requirements)
   - [Product scope](#product-scope)
   - [User stories](#user-stories)
   - [Use cases](#use-cases)
   - [Non-Functional Requirements](#non-functional-requirements)
   - [Glossary](#glossary)
7. [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is a **part of the se-education.org** initiative. If you would like to contribute code to this project, see [se-education.org](https://se-education.org#https://se-education.org/#contributing) for more info.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

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

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `CardListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Card` object residing in the `Model`.
* answer of the Card created is hidden from the user when they browse the Deck 
* user can scroll to see the different `Card` listed in lesSON

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `DeckParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a card).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `DeckParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Card` objects (which are contained in a `UniqueCardList` object).
* stores the currently 'selected' `Card` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Card>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Deck`, which `Card` references. This allows `Deck` to only require one `Tag` object per unique tag, instead of each `Card` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both deck data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `DeckStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()`Saves the current address book state in its history.
* `VersionedAddressBook#undo()`Restores the previous address book state from its history.
* `VersionedAddressBook#redo()`Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
   * Pros: Easy to implement.
   * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
   * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
   * Cons: We must ensure that the implementation of each individual command are correct.

### \[Proposed\] Filter by Tag feature

#### Proposed Implementation

The proposed feature aims to filter the flashcards and display cards of a specific `tag`. This allows the users to 
view specific groups of cards under the same `tag`, granting them more control over their study material.

Given below is an example usage of the filter feature.

Step 1: User creates various cards.

Step 2: User executes `edit 1 t/CS2103T`. This causes the first card to be tagged with the tag `CS2103T`.

Step 3: User repeats step 2 for all the cards associated with `CS2103T`.

Step 4: User now wants to display only cards with the `CS2103T` tag. User will execute the `filter t/CS2103T`.

Step 5: Deck will now display only the cards with the `CS2103T` tag.

Step 6: Should user want to see the full deck, they will execute `list` to view their full deck of cards.

#### Design considerations:

**Aspect: How filter executes:**

* **Alternative 1 (current choice):** Filter through the whole deck using the filter method on a stream of cards.
    * Pros: Easy to implement.
    * Cons:
      1. May have performance issues when Deck eventually gets too big.
      2. Inefficient if specific tag is a small fraction of Deck.
* **Alternative 2:** Construct a \'mini-deck\' for each tag.
    * Pros: Quick search for all cards with specific tag.
    * Cons: 
      1. Will use more memory.
      2. Adding/Deleting/Editing cards will require modifications to \'mini-deck\'.

### \[Proposed\] Search Filter feature

Introducing a search feature that allows users to search for specific flashcards based on their questions. This feature empowers users with greater navigability over their study materials.

Below is an example of the usage of the Search filter

Step 1: Assuming the user has existing cards in lesson, with their own set of questions and answers

Step 2: When you want to search for cards with a particular staring phrase, execute the `search q/What` command.

Step 3: The system will then display the cards that match the starting phrase.

Step 4: To return to viewing your full deck of cards, simply execute the `list` command to view all cards stored in lesSON

Step 5: If the user wishes to practise from this view, simply `practise index` for the index of the card

### \[Proposed\] Markdown support feature

#### Proposed Implementation

The proposed feature aims to support Markdown based language for inputs and renders the corresponding display (i.e. ** Bold ** will become **Bold**). 
This provides users the freedom to adapt the content within the card, granting them more control over their study material. Users would be able to highlight more specific 
part of the `Answer` which would be the key concept tested in the exam.

Given below is an example usage of the Markdown support feature.

Step 1: User creates a card.

Step 2: User executes `add q/ What base is hexadecimal in? a/ Hexadecimal is in **Base 16**`

Step 3: `AddCommandParser` will parse in the input and then, parse in the values collected in the multimap to a `MarkdownParser`
to create a new `Card`.

Step 4: The `Card` is added to the `Model` to be added to the `Deck`.

Step 5: The `UI` renders the `Card` with the relevant fields meant to be written in Markdown.

#### Design considerations:

**Aspect: How Markdown support executes:**

* **Alternative 1 :** Use existing libraries that support Markdown using JavaFX.
    * Pros: Easy to implement.
    * Cons: Dependency on third party library.
* **Alternative 2:** Individually support each type of Markdown language.
    * Pros: Scope of support can be determined by developer.
    * Cons:
        1. Time-consuming.
        2. More checks and assertions required for increased edge cases.
        3. More testing. 

### Spaced Repetition Feature

#### Implementation

This features aims to implement a Spaced Repetition system to schedule cards such that more difficult cards are 
prioritised for studying, and less difficult cards appear less frequently. Spaced Repetition makes users give more of 
their attention to more difficult cards, and thus has been shown to greatly improve memory retention when used as a 
scheduling tool for flashcards.

Given below is an example usage of the Spaced Repetition Feature.

Step 1: Assuming the user has existing cards in lesson, with their own set of questions and answers. These 
questions are sorted by a due date `nextPracticeDate`. Cards also have a hidden field known as `lastPracticeDate`.

Step 2: After the user uses the `practise` command and `solve` command, he uses the `set` command to set how difficult 
he felt the card was when he was practising the card. There are 3 difficulties: `easy`, `medium`, and `hard`.

Step 3: After setting the difficulty, the system will calculate a new `nextPracticeDate`. 
Firstly, it applies a multiplier (based on difficulty: 3, 1.5, 0.5 for easy, medium, hard respectively) 
to the amount of time between `lastPracticeDate` and `nextPracticeDate`, obtaining a duration. 
This duration is then added to the current `nextPracticeDate` to calculate the new `nextPracticeDate`. 
If there is no suitable `lastPracticeDate` to use, then this calculation alternatively 
adds a base duration (of 4 hours) * multiplier to `nextPracticeDate`.

Step 4: The card's `nextPracticeDate` and `lastPracticeDate` is then updated with the new fields. 

Step 5: The card is automatically sorted in the list according to the new `nextPracticeDate`.


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

* is a Computer Science University student in NUS
* wishes to excel in their studies
* requires a learning aid that enforces their understanding and memorisation of the content taught
* may take several courses at once and each course is expected to have multiple topics/concepts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* decently familiar with the idea of flashcards and their usages in an academic setting

**Value proposition**: For Computing students in University who struggle with memorisation and consolidation of knowledge, our app provides users the ability to create categorised flashcards to organise concepts taught to them fast. Optimised for CLI and users who type fast.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                | I want to …​                                                                                    | So that I can…​                                  |
|----------|----------------------------------------|-------------------------------------------------------------------------------------------------|--------------------------------------------------|
| `* * *`  | new user                               | create a new flashcard                                                                          | start adding content                             |
| `* * *`  | user                                   | edit the content of an existing flashcard                                                       | update my study materials                        |
| `* * *`  | user                                   | delete a flashcard                                                                              | remove irrelevant content                        |
| `* * *`  | user                                   | view a list of all my flashcards                                                                | quickly access my study materials                |
| `* * *`  | user                                   | organise my flashcards with tags                                                                | manage my study materials efficiently            |
| `*`      | user                                   | search for a specific flashcard                                                                 | edit the card accordingly                        |
| `* *`    | user                                   | sort my flashcards by date created or modified                                                  | view them in an organised manner                 |
| `*`      | user who learns better with pictures   | add images to my flashcards                                                                     | enhance my learning experience                   |
| `* * *`  | user who wants to update my flashcards | modify answers to my flashcard                                                                  | make sure that my content is up to date          |
| `* * *`  | user                                   | practise with my flashcards                                                                     | test my knowledge                                |
| `* *`    | user                                   | shuffle the order of my flashcards during practice                                              | avoid memorising in a fixed sequence             |
| `* * *`  | user                                   | mark a flashcard as "learnt" during practice                                                    | track my progress                                |
| `*`      | user                                   | see statistics on my practice sessions, such as the number of cards reviewed and the time spent |                                                  |
| `* * *`  | user                                   | tag my flashcards with course IDs and coursework                                                | easily categorise them by subject                |
| `* * *`  | user                                   | tag my flashcards with the type of concept (e.g., definitions, formulas, field)                 | filter them for specific study needs             |
| `* *`    | user                                   | can change my tag colours                                                                       | personalise my studying methods                  |
| `* *`    | user                                   | enable spaced repetition for my flashcards                                                      | optimise my memorization process                 |
| `* *`    | user                                   | adjust the spacing intervals for spaced repetition                                              | personalise my learning experience               |
| `* *`    | diligent user                          | review flashcards due for repetition                                                            | reinforce my memory                              |
| `* *`    | experienced user                       | format the text on my flashcards using Markdown or a similar markup language                    | create personalised rich content                 |
| `*`      | experienced user                       | preview the formatted text on my flashcards                                                     | ensure it appears as intended                    |
| `* *`    | picky user                             | customise the app's colour scheme and interface                                                 | personalise my learning environment              |
| `* *`    | user                                   | reset my progress and start fresh with my flashcards                                            | can reattempt my studies                         |
| `* *`    | user                                   | export my flashcards to a file                                                                  | back up my data or share it with others          |
| `* *`    | user                                   | import flashcards from an external file                                                         | easily migrate or collaborate on study materials |
| `* * *`  | user                                   | access a user guide                                                                             | learn how to use the app effectively             |
| `* *`    | user                                   | receive notifications to remind me of my study sessions and flashcard reviews                   |                                                  |
| `* *`    | user                                   | set daily or weekly study goals                                                                 | track my progress toward my learning objectives  |
| `*`      | user                                   | provide feedback or report issues to the app developers                                         | contribute to app improvement                    |
| `*`      | multilingual user                      | input non-english characters                                                                    | study in other languages                         |

### Use cases

(For all use cases below, the **System** is `lesSON` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 Creating a card**

**MSS:**

1. User inputs command to create a card, along with the necessary details.
2. lesSON displays a success message.
3. The answer generated is not visible to the user until card selected is practised.

   Use case ends.

**Use case: UC02 deleting a card**

**MSS:**

1. User displays all cards (UC04).
2. User inputs command to delete a card, along with the index of the card.
3. lesSON displays a success message.

   Use case ends.


**Use case: UC03 viewing a card**

**MSS:**

1. User displays all cards (UC04).
2. User inputs command to view a card, along with the index of the card.
3. lesSON displays the selected card.

   Use case ends.


**Use case: UC04 displaying all cards**

**MSS:**

1. User inputs command to view all cards.
2. lesSON displays all cards.

   Use case ends.


**Use case: UC05 editing a card**

**MSS:**

1. User displays all cards (UC04).
2. User inputs command to edit a card, along with the necessary details.
3. lesSON displays a success message.

   Use case ends.


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 flashcards without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  User must be educated and willing to use flashcards to learn the content
5.  Display box should be able to show the user's full input

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Flashcard**: A two-sided card containing a question and an answer
* **Tag**: A label used to categorise flashcards
* **Deck**: A series of cards that is stored in lesSON

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a card

1. Deleting a card while all card are being shown

   1. Prerequisites: List all cards using the `list` command. Multiple cards in the list.

   1. Test case: `delete 1`<br>
      Expected: First card is deleted from the list. Details of the deleted card shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No card is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
