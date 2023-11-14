---
layout: page
title: Developer Guide
---
## Table of Contents
1. [Acknowledgements](#acknowledgements)
1. [Setting up](#setting-up-getting-started)
1. [Design](#design)
    1. [Architecture](#architecture)
    2. [UI](#ui-component)
    3. [Logic](#logic-component)
    4. [Model](#model-component)
    5. [Storage](#storage-component)
    6. [Common Classes](#common-classes)
1. [Implementation](#implementation)
    1. [Listing](#listx-feature)
    2. [Viewing](#viewx-feature)
    3. [Edit Person](#edit-person-feature)
    4. [Add Medicine Short Form](#add-medicine-short-form-feature)
    5. [Delete Medicine Short Form](#delete-medicine-short-form-feature)
    6. [Adding an Order](#adding-an-order-feature)
    7. [Finding an Order Feature](#finding-an-order-feature)
1. [Documentation](#documentation-logging-testing-configuration-dev-ops)
1. [Appendix: Requirements](#appendix-requirements)
    1. [Product scope](#product-scope)
    2. [User Stories](#user-stories)
    3. [Use Cases](#use-cases)
    4. [Non-Functional Requirements](#non-functional-requirements)
    5. [Glossary](#glossary)
1. [Appendix: Instruction for Manual Testing](#appendix-instructions-for-manual-testing)
1. [Appendix: Planned Enhancements](#appendix-planned-enhancements)
1. [Appendix: Effort](#appendix-effort)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

PharmHub is built upon [AddressBook-Level3](https://se-education.org/addressbook-level3/) project created by the [SE-EDU initiative](https://se-education.org).  
The following libraries have been used
* [JavaFX](https://openjfx.io/)
* [Jackson](https://github.com/FasterXML/jackson) 
* [JUnit5](https://junit.org/junit5/)

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

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deletep 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("deletep 1")` API call as an example.

![Interactions Inside the Logic Component for the `deletep 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeletePersonCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `PharmHubParser` object which in turn creates a parser that matches the command (e.g., `DeletePersonCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeletePersonCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `PharmHubParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPersonCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPersonCommand`) which the `PharmHubParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddOrderCommandParser`, `DeletePersonCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" /> <br />
<img src="images/ModelObjectsDiagram.png" width="700" />

The `Model` component,

* stores the PharmHub data i.e., all `Person`, `Order`, and `Medicine` objects.
* stores the currently 'selected' `Person`, `Order`, `Medicine` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)



### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-W08-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/PharmHubStorageClassDiagram.png" width="900" />

The `Storage` component,
* can save both PharmHub data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `PharmHubStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)
    * The Main Application has 3 JsonAdapted component in storage: `JsonAdaptedPerson`, `JsonAdaptedOrder`, `JsonAdaptedMedicine`.
      * `JsonAdaptedPerson` further contains `JsonAdaptedTag` and `JsonAdaptedAllergy`.
      * `JsonAdaptedOrder` further contains `JsonAdaptedStatus` and `JsonAdaptedMedicine`.`

### Common classes

Classes used by multiple components are in the `seedu.pharmHub.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented. 

### ListX feature

The listing functionality is supported by the listPanelPlaceholder in the Ui.

On start of application, two listPanels (PersonListPanel, OrderListPanel) are created, and person list panel is attached
to the ListPanel Placeholder as default.

On execution of the any listing commands (`listo` or `listp`), the resultant CommandResult contains details on which
panel to choose to display.

These are 3 different options for these details:
* Person - Panel will display list of people
* Order - Panel will display list of orders
* NoChange - Panel will keep whatever was there previously

Using these specifications, the CommandResult from executing `listo` and `listp` will inform the Ui of which
panel to attach to the listPanelPlaceHolder

### ViewX feature

The `viewp/ viewo` feature allows users to view details of a person/ order in the info display panel. This is facilitated with the new interface, `InfoObject`, which classes that want to be displayed are required to implement.

To support this feature, `CommandResult` has the field `InfoObject`. If present (not null), the UI will create and attach a view for that `InfoObject` onto the Info Display.

The code excerpt for `MainWindow#handleDisplayInfo(InfoObject)` below shows how the InfoDisplay is rendered:

```java
    @FXML
    protected void handleDisplayInfo(InfoObject objectToDisplay) {
        assert(objectToDisplay instanceof Order || objectToDisplay instanceof Person);

        if (objectToDisplay instanceof Order) {
            Order order = (Order) objectToDisplay;
            OrderDisplay orderDisplay = new OrderDisplay(order);
            infoDisplay.attach(orderDisplay)
        } else if (objectToDisplay instanceof Person) {
            Person person = (Person) objectToDisplay;
            PersonDisplay personDisplay = new PersonDisplay(person);
            infoDisplay.attach(personDisplay)
        } else {
            throw new RuntimeException("Invalid object to display");
        }
    }
```


The following sequence diagram shows how the `viewp` operation works:

![ViewpSequenceDiagram](images/ViewpSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/ViewActivityDiagram.png" width="450" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** As it currently stands in PharmHub, InfoObject can only be a Person or an Order.

</div>

#### Design considerations:
* **Aspect: How the UI is informed about the object to display:**

    * **Alternative 1 (current choice):** Require Classes that want to be displayed to implement `InfoObject`
        * Pros: Allows `CommandResult` to contain a uniform type (`InfoObject`), which scales easily. Also gives control over which classes can be displayed, and which classes cannot (yet).
        * Cons: Empty `InfoObject` interface may lead to confusion for new developers.

    * **Alternative 2:** Have `CommandResult` contain all types of objects that we want displayed, ie `Person` and `Order`
        * Pros: Possibly clearer in intent
        * Cons: Not easily scalable

* **Aspect: Creation of relevant `InfoObject` UI component:**

    * **Alternative 1 (current choice):** If-else checks in `MainWindow#handleDisplayInfo`, and create the required UI component for each clause
        * Pros: Reduces coupling between `Person` (and `Order`) model and UI
        * Cons: Less scalable

    * **Alternative 2:** Create abstract method `InfoObject#createUIComponent`, and have Classes that wish to be displayed implement this method and create their own UI components
        * Pros: Easily scalable - each class implements their own UI display to attach into the placeholder
        * Cons: Increased coupling between UI and Model. UI-creation code exists inside the `Person` (or `Order`) Class

### Edit person feature

#### Implementation

After adding a person in PharmHub, the user would be able to edit the person using the `editp` command with the following format:

`editp INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]… [no/ALLERGY]…​ [ia/]`

The command edits the person information based on the given fields. At least one field must be updated.
Existing values will be replaced with the new input values.
If the edits cause the person to be allergic to any orders associated with them, a warning will be raised. Using the `ia/` flag will override this warning.
This command cannot add or delete orders for this person. It's limited to editing the person's information only.

Prerequisite: There should be a person at index 1, with "Aspirin" in the PharmHub medicine list and also among one or more of his/her orders.

The following sequence diagram illustrates some of the steps that happen when user execute `editp 1 no/Aspirin`
![editp](images/EditPersonSequenceDiagram.png)

#### Steps to trigger

Step 1: The user executes `editp 1 no/Aspirin` to add the allergy "Aspirin" to the person at index 1.

Step 2: Logic Manager calls `PharmHubParser#parse` which extracts the arguments and calls `EditPersonCommandParser`. 

Step 3: `EditPersonCommandParser` parses the index and the allergy and returns an `EditPersonCommand`.

Step 4: `LogicManager` calls `EditPersonCommand#execute` to edit the details of the person.

Step 5:  Since an allergy is added in this command, and an `ia/` flag is not passed as an argument, `EditPersonCommand` checks among the list of order of the person whether there are any medications that match this allergy by calling the `hasOrderConflict()` function.

Step 6: Since there are medications that match this allergy, a `CommandException` is returned.

Step 7: An error message is shown to warn the user that the person's new allergy conflicts with existing orders, and hints the user about adding the `ia/` flag to add the allergy to the person anyway.


`EditPersonCommand#execute` utilizes the following method `Person#hasOrderConflicts` to check if there are any conflicts between the person's allergy and his/her orders.
```java
    public boolean hasOrderConflicts() {
        return orders.stream().anyMatch(o -> isAllergicToAny(o.getMedicines()));
    }

}
```

`Person#isAllergicToAny(medicines)` returns true if the person is allergic to any of the given medicines.

```java
    public boolean isAllergicToAny(Set<Medicine> medicines) {
        return medicines.stream().anyMatch(medicine -> isAllergicTo(medicine));
    }
```

### Add medicine short form feature

#### Implementation
After the creation of new `Medicine`, a short form can be assigned to that `Medicine` using the `sfm` command.  
After this command, the user would be able to use this short form interchangeably with the full name in fields requiring medicine or allergy names.    
Currently, only one short form can be assigned to one `Medicine` at a time.  

#### Steps to trigger
Step 1: The User launches the application.
Step 2: The user executes `sfm 1 m/pan` to add the short form of `pan` to the medicine at index 1 in the last shown medicine list.  
Step 3: Logic Manager calls `PharmHubParser#parse` which extracts the arguments and calls `ShortFormCommandParser`  
Step 4: `ShortFormCommandParser` parses the index, short form name and returns a `ShortFormComamnd`   
Step 5: `LogicManager` calls `ShortFormCommand#execute` to assign the short form to the medicine.   
Step 6: `ShortFormCommand` checks if an existing medicine with the same name or same short form is already present using `Model#hasMedicine(m)`.   
Step 7: The `Medicine` at index 1 is replaced with a new `Medicine` which has same medicine name but with `pan` as the short form.   

The following sequence diagram illustrates some of the steps that happen when user execute `sfm 1 m/pan`

![ShortFormSequenceDiagram](images/ShortFormSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifelines should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>  
 
`Model#hasMedicine(m)` utilizes the following method `Medicine#isSameMedicine` to check equality of two `Medicine`.  
```java
public boolean isSameMedicine(Medicine m) {
        if (m == this) {
            return true;
        }
        if (m == null) {
            return false;
        }
        return (medicineName.equalsIgnoreCase(m.medicineName)
                || medicineName.equalsIgnoreCase(m.shortForm)
                || m.medicineName.equalsIgnoreCase(shortForm));
}
```

### Delete medicine short form feature

#### Implementation
`sfm` can also be used to delete the short form of a medicine by providing the `d/` flag.
If the `d/` flag is provided any provided medicine names using `m/` will be ignored.   
Hence `sfm 1 m/pan d/` will
be treated as deleting the short form of medicine at index 1 in the last shown medicine list. 

#### Steps to trigger
Given below is an example scenario to delete the short form of medicine at index 1. 

Step 1. The user lists all medicines using the `listm` command.  
Step 2. The user deletes the medicine at 1st index using `sfm 1 m/pan d/`  
Step 3: Logic Manager calls `PharmHubParser#parse` which extracts the arguments and calls `ShortFormCommandParser`  
Step 4: `ShortFormCommandParser` parses the index, ignores the short form and returns a `ShortFormComamnd`   
Step 5: `LogicManager` calls `ShortFormCommand#execute` to delete the short form of medicine at index 1.   
Step 6: The `Medicine` at index 1 is replaced with a new `Medicine` which has same medicine name but with no short form.


The following activity diagram summarises the sequence of steps for the whole `sfm` command.

<img src="images/ShortFormActivityDiagram.png" width="400" />


### Adding an Order feature

#### Implementation

The adding an Order feature allows the user to add an order to a person.
<br>
1. The add order feature includes the ignore allergy flag to allow further flexibility for the pharmacist when assigning orders
   to people. This is because they may have other considerations when it comes to assigning a medication for a person.
2. The add order feature do not allow duplicated order as we believe that each order should be distinct base on
   their order number, ensuring that no orders go missing or forgotten.

#### Steps to Trigger

1. The User launches the application.
2. The User executes "addo 1 m/aspirin o/1" to add a new order.
3. The `AddOrderParser#parse()` checks whether all the prefixes and the required values are provided.
4. If the check is successful, the `ParseUtil#parseOrderNumber`, `ParseUtil#parseOrderNumber` and `ParseUtil#parseMedicine` 
will then check and create an `Index`, `OrderNumber` and `Medicine` passing it to `AddOrderCommand`.
5. Depending on whether an ignore allergy flag `ia/` is added in the command input, user can input medications in the order that the person is allergic to. 
6. `AddOrderCommand#execute` then checks if the person exist base on index and creates an Order with the
`person`, `orderNumber`, `medicine` and a `PENDING` Status, then `Model#hasOrder` also checks whether the order is a duplicated order.
7. If the order does not exist, then the `Model#addOrder` adds the order into the order list.

The following sequence diagram shows how `addo` works on an example input. `addo 1 m/aspirin o/1000`

<img src="images/AddoSequenceDiagram.png" width="1400" />


### Finding an Order Feature

#### Implementation

The finding an Order feature allows the user to find other base on either the orderStatus, medication in the order or both.
<br>
1. The find order feature allows independent Predicate finding.
    1. i.e. you can find order base on only `Status` or `Medicine`.
    2. We implemented this format to expend the utility of the feature.

#### Steps to Trigger

1. The User Launches the application. 
2. The User decides to find/filter through the order list after adding multiple orders.
3. The User executes "findo s/pd m/panadol" to find orders that has `PENDING` status and `PANADOL` in the order.
4. The `FindOrderCommandParser#parse()` checks whether all the prefixes and the required values are provided.
5. If the check is successful, depending on the User input if `s/` is present `ParseUtil#parseStatus` will return a
`Status`, and if `m/` is present `ParseUtil#parseMedicines` will return the `Medicine`.
6. The `Status` and `Medicine` will then be passed to the FindOrderCommand.
7. The `Model#updateFilteredOrder` will then take either or both `Status` and `Medicine` as predicates to filter through the order list and return valid orders.
8. The filtered order list will then be returned and shown on the Displayed list.

The following sequence diagram shows how `findo` works on an example input. `findo s/pd m/panadol`

<img src="images/FindoSequenceDiagram.png" width="1200" />


### Undo/redo feature

The undo/redo mechanism is facilitated by `VersionedPharmHub`. It extends `PharmHub` with an undo/redo history, stored internally as an `undoHistory Deque` and `redoHistory Deque`. Additionally, it implements the following operations:

* `VersionedPharmHub#canUndo()` —  Checks if there is a previous PharmHub state to revert to
* `VersionedPharmHub#undo()` — Restores the previous PharmHub state from its `undoHistory.
* `VersionedPharmHub#canRedo()` —  Checks if there is a future PharmHub state to revert to.
* `VersionedPharmHub#redo()` — Restores a previously undone PharmHub state from its `redoHistory`.

These operations are exposed in the `Model` interface as `Model#canUndo()`, `Model#undo()`, `Model#canRedo()` and `Model#redo()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedPharmHub` will be initialized with the initial pharmHub state, and the `CurrentState` points to that single pharmHub state. Both `undoHistory` and `redoHistory` will be empty. 

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `deletep 5` command to delete the 5th person in PharmHub. The `deletep` command calls `Model#deletePerson`, which in turn calls `VersionedPharmHub#deletePerson`. This causes the `VersionedPharmHub` to save the current state into `undoHistory`, before calling `PharmHub#deletePerson`, which deletes the person from PharmHub

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `addp n/David …​` to add a new person. The `addp` command calls `Model#addPerson`, which in turn calls `VersionedPharmHub#addPerson` repeating the process in step 2.

![UndoRedoState2](images/UndoRedoState2.png)

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undo()`, which does the following things in order. It saves the current state into `redoHistory`, updates `Current State` to the last state in `undoHistory`, and removes that last state from `undoHistory`.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Before the undo operation, the `Model` will first check if an undo is possible in the first place, and it does so by calling `VersionedPharmHub#canUndo()`, which checks if `undoHistory` is empty. If an undo operation cannot be done, an error is thrown by the `Model`.</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redo()`, which saves the `Current State` into `undoHistory`, sets the `Current State` as the last state in `redoHistory`, and removes said state from `redoHistory`. 

<div markdown="span" class="alert alert-info">:information_source: **Note:** Similar to `undo`, if `Model#canRedo` returns `False`, ie. the `redoHistory` is empty, then the `Model` throws an error.

</div>

Step 5. The user then decides to execute the command `listp`. Commands that do not modify data in PharmHub, such as `listp`, will not change the `undoHistory` or `redoHistory`

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#Clear()`, which in turn calls `VersionedPharmHub#Clear()`. As before, the `Current State` is added to `undoHistory`, and then `PharmHub#clear()` is called, which changes the state. Moreover, the `redoHistory` will be cleared, preventing users from running `redo` after a data-modifying non-undo command. Reason: This is how most applications handle undo/redo.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommandActivityDiagram.png" width="400" />

#### Design considerations:

* **Aspect: How undo & redo executes:**

  * **Alternative 1 (current choice):** Saves the entire pharmHub.
      * Pros: Easy to implement.
      * Cons: May have performance issues in terms of memory usage.

  * **Alternative 2:** Individual command knows how to undo/redo by itself.
      * Pros: Will use less memory (e.g. for `deletep`, just save the person being deleted).
      * Cons: We must ensure that the implementation of each individual command are correct.

* **Aspect: Effects (Scale) of undo & redo:**
  * **Alternative 1 (current choice):** Only affects data-modifying operations
      * Pros: Simple to implement, provides essential functionality
      * Cons: Non-data-modifying commands cannot be undone, eg. `listp`

  * **Alternative 2:** Affects all commands
      * Pros: Changes in view can be undone, leading to greater convenience. Easily extensible to undo certain views
      * Cons: Versioning has to be extended to keep track of UI, which may lead to increased coupling.

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

Tech-savvy fresh-graduate remote pharmacist managing medication orders for a small-medium sized business, looking to move up the corporate ladder quickly.

**Value proposition**: 

PharmHub provides an efficient yet guarded approach towards processing orders. It allows users to go through the lifecycle of order-fulfillment quickly, whilst protecting against erroneous entries and providing a quick and efficient methods of correcting mistakes.

It is also optimised for CLI use. 


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                    | I want to …​                                                                    | So that I can…​                                                             |
|----------|----------------------------|---------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
|          |                            |                                                                                 |                                                                             |
| `* * *`  | Pharmacist                 | Add a new patient into PharmHub                                                 | Keep track of all information of my patients                                |
| `* * *`  | Pharmacist                 | Add a medication order for a patient                                            | Process new orders                                                          |
| `* * *`  | Pharmacist                 | Add a medicine into PharmHub                                                    | Update the inventory of my pharmacy                                         |
| `* * *`  | Pharmacist                 | Add allergies of a patient towards certain medications                          | Keep track of patient allergies                                             |
| `* * *`  | Pharmacist                 | View the status of an order                                                     | I am able to keep track of the state of an order                            |
| `* * *`  | Pharmacist                 | Update the status of an order                                                   | Update the order records with the most current information                  |
| `* * *`  | Pharmacist                 | Edit the details of a patient                                                   | Update patient records with the most current information                    |
| `* * *`  | Pharmacist                 | View all medication orders                                                      | Have an overview of all medication orders                                   |
| `* * *`  | Pharmacist                 | Delete a patient from PharmHub                                                  | Remove a patient that is no longer under my care                            |
| `* * *`  | New user                   | View a summary of all the commands                                              | Recall how to use the application                                           |
| `* * *`  | Pharmacist                 | Delete an order from the system                                                 | Remove erroneous orders                                                     |
| `* * *`  | Detail-oriented Pharmacist | Be alerted if a patient is given a medication that he is allergic to            | Detect and prevent erroneous orders from going through                      |
| `* * *`  | Time-efficient Pharmacist  | Be alerted if a medication in an order is nonsensical                           | Waste less time looking for a non-existent medication                       |
| `* * *`  | Mistake-prone Pharmacist   | Undo/Redo my last action                                                        | Correct mistakes made into the system quickly and effectively               |
| `* *`    | Busy Pharmacist            | Abbreviate medication names                                                     | Maximise my efficiency and type less                                        |
| `* *`    | Pharmacist                 | View all details of a patient (including past/ present orders for that patient) | Easily track orders for that patient                                        |
| `* *`    | Forgetful Pharmacist       | Search for orders by their status                                               | Find out which orders to prepare next                                       |
| `* *`    | Time-efficient Pharmacist  | Search for orders by their medication name                                      | Prepare all orders that contain a particular medicine simultaneously        |
| `* *`    | Result-oriented Pharmacist | See the number of unfulfilled orders                                            | Keep track of my progress in dispensing orders                              |
| `* *`    | Pharmacist                 | Add priorities to orders                                                        | Keep track of an order's priority                                           |
| `* *`    | Pharmacist                 | Sort orders on various categories (eg. Priority, Time)                          | Fulfill the important orders first                                          |
| `*`      | Pharmacist                 | Edit an order                                                                   | Correct a mistake made in an order                                          |
| `*`      | Pharmacist                 | Track my medication inventory                                                   | I have easy access to the amount of medication I have                       |
| `*`      | Pharmacist                 | Calculate estimated time an order will take to ship                             | Notify patients of an estimated wait time before receiving their medication |

### Use cases

For all use cases below, the **Software System** is the `PharmHub` and the **Actor** is the `user`, unless specified otherwise.

#### **Use case: UC01 - Adding a person**

Guarantees: The new person(patient) will be added to the list of Person only if the person does not exist in the system.

**MSS**

1. User chooses to add a person.
2. PharmHub adds the new person to the person list.
3. PharmHub displays the updated list and person.
<br>**Use case ends**

**Extensions**

* 1a. PharmHub detects an error in the input.

    * 1a1. PharmHub shows error message.
    * 1a2. User enters new input.
    * Steps 1a1-1a2 are repeated until the input are correct.
    <br>**Use case resumes from step 2.**
     
#### **Use case: UC02 - Listing Person list**

Guarantees: The Person List would be displayed.

**MSS**

1. User adds a person [UC01](#use-case-uc01---adding-a-person).
2. User chooses to view the list.
3. PharmHub shows the Person list.
   <br>**Use case ends**
    
**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
    <br>**Use case resumes from step 3.**


* 2a. The list is empty.
  <br>**Use case ends**
  

#### **Use case: UC03 - Editing Person Detail**

Guarantees: The Person's detail would be updated only if the person exists in the system.

**MSS**

1. User adds a person [UC01](#use-case-uc01---adding-a-person).
2. User views the person list [UC02](#use-case-uc02---listing-person-list).
3. User chooses to edit a person's detail.
4. PharmHub updates the Person list with the updated information
5. PharmHub displays the person.
<br>**Use case ends**


**Extensions**

* 3a. PharmHub detects an error in the input.

    * 3a1. PharmHub shows error message.
    * 3a2. User enters new input.
    * Steps 3a1-3a2 are repeated until the input are correct.
    <br>**Use case resumes from step 4.**

#### **Use case: UC04 - Adding a medicine**

Guarantees: The new medicine will be added to the list of medicines only if the medicine does not exist in the system.

**MSS**

1. User chooses to add a medicine.
2. PharmHub adds the new medicine to the medicine list.
   <br>**Use case ends**


**Extensions**

* 1a. Medicine already exists in the system.

    * 1a1. PharmHub shows error message.
    * 1a2. User enters new input.
    * Steps 1a1-1a2 are repeated until the input is correct.
    <br>**Use case resumes from step 2.**


#### **Use case: UC05 - Listing Medicine list**

Guarantees: The Medicine List would be displayed.

**MSS**

1. User adds a medicine [UC04](#use-case-uc04---adding-a-medicine).
2. User chooses to view the medicine list.
3. PharmHub shows the Medicine list.
   <br>**Use case ends**

**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**

* 2b. The list is empty.
  <br>**Use case ends**


#### **Use case: UC06 - Adding a medicine short form**

Guarantees: The Medicine would have a short form only if the medicine exist in the system and the short form is unique.

**MSS**

1. User adds a Medicine [UC04](#use-case-uc04---adding-a-medicine).
2. User views the person list [UC05](#use-case-uc05---listing-medicine-list).
3. User chooses to add a short form for medicine.
4. PharmHub updates Medicine list with short form.
   <br>**Use case ends**

**Extensions**

* 3a. PharmHub detects an error in the input.

    * 3a1. PharmHub shows error message.
    * 3a2. User enters new input.
    * Steps 3a1-3a2 are repeated until the input are correct.
      <br>**Use case resumes from step 4.**

#### **Use case: UC07 - Deleting a medicine short form**

Guarantees: The short form for the medicine will be removed.

**MSS**

1. User adds a short form [U06](#use-case-uc06---adding-a-medicine-short-form).
2. User chooses to delete the short form.
3. PharmHub updates Medicine list with an empty short form.
<br>**Use case ends**

**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**

#### **Use Case: UC08 - Add an Order for a Person**

Guarantees: The order is added to the person only if the person exist while the order does not exist in the system.

**MSS**

1. User views the person list [UC02](#use-case-uc02---listing-person-list).
2. User chooses to add an order.
3. PharmHub adds the new order to the order list.
4. PharmHub displays the order.
   <br>**Use case ends**


**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**
 
* 2b. The person is allergic to the input medication.
    * 2b1. The user input the wrong medication resulting in the error.
      * 2b1a. PharmHub warns the user.
        <br>**Use case resumes from step 2.**
    * 2b2. The User wants to ignore the warning.
      * 2b2a. PharmHub warns the user
      * 2b2b. User acknowledges the allergy warning but chooses to ignore it.
      * 2b2c. PharmHub creates the order with the provided information.
        <br>**Use case resumes from step 3.**

In this use case, a pharmacist adds a medication order for a patient using PharmHub. 
The system first allows the pharmacist to select a patient from the list and provide order details. 
It then checks for potential contraindications based on the patient's allergies and issues a warning if necessary. 
The pharmacist can acknowledge the warning and proceed with the order by adding an "IA" flag to the command. 
Once confirmed, the system records the order and sends a confirmation to the pharmacist.

#### **Use case: UC09 - Listing Order list**

Guarantees: The Order List would be displayed.

**MSS**

1. User adds an order [UC08](#use-case-uc08---add-an-order-for-a-person).
2. User chooses to view the order list.
3. PharmHub shows the Order list.
   <br>**Use case ends**

**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**

* 2b. The list is empty.
  <br>**Use case ends**


#### **Use case: UC10 - Update Order Status**

Guarantees: The order status will be updated only if the order exist in the system and the new status is of chronological order.

**MSS**

1. User views the order list [UC09](#use-case-uc09---listing-order-list)
2. User chooses to update the order status.
3. PharmHub updates the order status.
4. PharmHub displays updated order
    <br>**Use case ends**

**Extensions**

* 1a. The list is empty.
  <br>**Use case ends**


* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**

#### **Use case: UC11 - Finding person**

Guarantees: The list of person that contains the keywords would be displayed.

**MSS**

1. User added multiple person [UC01](#use-case-uc01---adding-a-person).
2. User chooses to the find person command.
3. PharmHub filters through the person list.
4. PharmHub then displays the filtered person list.
   <br>**Use case ends**

**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**


* 3a. The keywords given does not match with any person.

    * 3a1. Empty list is shown.
    <br>**Use case ends.**

#### **Use case: UC12 - Finding orders**

Guarantees: The list of Orders that fulfills the status or medicine or both will be displayed.


**MSS**

1. User added multiple order [UC08](#use-case-uc08---add-an-order-for-a-person).
2. User chooses to find orders base on their status or medicine or both.
3. PharmHub filters through the order list.
4. PharmHub then displays the filtered order list.
   <br>**Use case ends**


**Extensions**

* 2a. PharmHub detects an error in the input.

    * 2a1. PharmHub shows error message.
    * 2a2. User enters new input.
    * Steps 2a1-2a2 are repeated until the input are correct.
      <br>**Use case resumes from step 3.**

* 3a. The status and medicine keyword given does not match with any order.

    * 3a1. Empty list is shown.
      <br>**Use case Ends.**


* 3b. User input invalid fields.

    * 3b1. PharmHub detects an error in the input.

      * 3b1a. PharmHub shows error message.
      * 3b1b. User enters new input.
      * Steps 3b1a-3b1b are repeated until the input are correct.
        <br>**Use case resumes from step 4.**


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1. Each command should take no longer than 0.1s to execute.
1. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
1. Should be able to hold up to 1000 orders without a noticeable sluggishness in performance for typical usage.
1. Should be able to hold up to 1000 medicines without a noticeable sluggishness in performance for typical usage.
1. Application should be a standalone executable so that it doesn't require the user to install other libraries to run.
1. Application should be smaller than 100mb so that application can be run on space constrained systems.
1. Generated storage file shouldn't take up more than 100mb of storage so that the application can be run on space constrained systems.

### Glossary

#### Application
* **Patient**: A person who is under the care of a **Pharmacist**.
* **Pharmacist**: A person who is responsible for dispensing **Medicine** to **Patient**s.
* **Order**: An order by a **Patient** for one or multiple **Medicine**s from a Pharmacy.
* **Medicine**: A substance used for medical treatment.
* **Allergy**: A Medicine that a **Patient** is allergic to.

#### General

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command Line Interface
* **GUI**: Graphical User Interface
* **API**: Application Programming Interface
* **MSS**: Main Success Scenario (or, Happy Path)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: The application opens. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.
   
       Expected: The most recent window size and location is retained.

### Adding a person

1. Adding a Person with Valid Details

    1. Test Case: `addp n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
 
       Expected: John Doe is added as a new person to PharmHub. All provided details, including the name, phone number, email, and address, are correctly saved. No errors or warnings are displayed.

    1. Test Case: `addp n/Betsy Crowe t/diabetic e/betsycrowe@example.com a/19 Kent Ridge Crescent p/1234567 t/senior`
       
        Expected: Betsy Crowe is added as a new person to PharmHub. All provided details, including the name, phone number, email, and address, tags, and allergies, are correctly saved. No errors or warnings are displayed.
   
2.  Adding a Person with Incorrect/Incomplete Information

    1. `addp n/Jane Doe`
    
       Expected: An error message is displayed due to the absence of the phone number, email, and address. The person isn’t added as these details are mandatory.
    
3. Adding a Person with Duplicate Name 

    1. Prerequisites: A person with name "John Doe" already exists in PharmHub. This can be done using the command in 1.i.

    1. Test case: `addp n/John Doe p/9872 e/anotherjohnd@example.com a/Jane street, block 42, #03-02`
    
       Expected: An error message is displayed due to the presence of a person with the same name. The person isn’t added as names must be unique.
   
4. Adding a Person with an allergy which is not yet in the Medicine list

   1. Prerequisites: A persons list without the person with name "John Doe". A medicine list without the medicine "Aspirin".

   1. Test case: `addp n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 no/Aspirin`

      Expected: An error message is displayed due to the presence of an allergy which is not in the medicine list. The person isn’t added as the allergy must be in the medicine list.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `listp` command. Multiple persons in the list.

    1. Test case: `deletep 1`<br>
   
       Expected: First contact is deleted from the list. Details of the deleted contact is shown in Info Display.

    1. Test case: `deletep 0`<br>
       Expected: No person is deleted. An error message is displayed due to an invalid index being used.

    1. Other incorrect delete commands to try: `deletep`, `deletep x`, `...` (where x is larger than the list size)
   
       Expected: Similar to previous.

### Viewing a person

1. Viewing a person with valid index

    1. Prerequisites: There is at least 1 person in the persons list of PharmHub. This can be done using the command in 1.i. This can be checked using the command: `listp`
   
    1. Test case: `viewp 1`
   
       Expected: First contact is shown in the Info Display.

    1. Test case: `viewp 0`
   
       Expected: No person is shown. An error message is displayed due to an invalid index being used.

    1. Other incorrect view commands to try: `viewp`, `viewp x` (where x is larger than the list size)
   
       Expected: No person is shown. An error message id displayed due to an invalid index being used.

### Editing a person 

1. Editing a person using valid fields

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list. No person named 'Johnson Doe' in PharmHub.
   
    1. Test case: `editp 1 n/Johnson Doe p/98765432`
 
       Expected: First person is edited. Details of the edited person is shown in the Info Display.

### Finding person(s)
1. Finding person(s) using valid fields 

    1. Test case: `findp n/John`
   
       Expected: The person(s) with the word "John" or "john" in his/her name are listed in the display list panel.

    1. Test case: `findp n/Alex John`
   
       Expected: All person(s) with name containing "Alex", "alex", "John", or "john" are listed in the display list panel.
   
    1. Test case: `findp no/Aspirin Paracetamol`
   
       Expected: All person(s) with allergy containing "Aspirin" or "Paracetamol" are listed in the display list panel.
   
    1. Test case: `findp p/123456`
   
       Expected: All person(s) with phone number exactly equal to "123456" are listed in the display list panel.
   
    1. Test case: `findp e/john@gmail.com`
   
       Expected: All person(s) with email exactly equal to "john@gmail.com" are listed in the display list panel.

2. Finding person(s) using invalid input

    1. Test case: `findp`
   
       Expected: An error message is displayed due to the absence of any field. No person is shown.
   
    1. Test case: `findp n/`
   
       Expected: An error message is displayed due to the absence of the name. No person is shown.

### Listing all medicines

1. Test case: `listm` <br>

   Expected: All medicines listed in the display panel.

### Finding medicine(s)

1. Test case: `findm pan` 

   Expected: All medicine whose names have `pan` as a substring listed in the list display. The number of medicines listed shown in result display box.

2. Test case: `findm pan ol`

   Expected: All medicine whose names have either `pan` or `ol`  as a substring are listed in the list display. The number of medicines listed shown in result display box.


### Adding a medicine

1. Adding a medicine that doesn't exist

    1. Prerequisites: List all medicines using the `listm` command. No medicine named `metformin` should be in the list.

    1. Test case: `addm m/metformin`
   
       Expected: Medicine is added.

    1. Test case: `addm m/`
   
       Expected: No medicine is added. An error message is displayed due to the invalid command format.

    1. Other incorrect `addm` commands to try: `addm `, `addm p/`, `addm 1`
   
       Expected: Similar to previous.

2. Adding a medicine that already exists

    1. Prerequisites: List all medicines using the `listm` command. A medicine named `metformin` should be in the list.
   
    1. Test case: `addm m/metformin`
   
       Expected: Medicine is not added. An error message is displayed because 'metformin' already exists in PharmHub.
    


### Deleting a medicine

1. Deleting a medicine no person is allergic to and not part of any order. 

    1. Prerequisites: `metformin` should be in the medicine list. Find it using `findm metformin`. No `Order` contains `metformin` and no `Person` has an allergy to `metformin`.

    1. Test case: `deletem 1`
   
       Expected: First medicine is deleted from the list.

    1. Test case: `deletem 0`
   
       Expected: No medicine is deleted. An error message is shown due to an invalid index being used.

    1. Other incorrect delete commands to try: `deletem`, `deletem x`, `...` (where x is larger than the list size)
   
       Expected: Similar to previous.

2. Deleting a medicine a person is allergic to.
    
    1. Prerequisites: `panadol` should be in the medicine list. If not add it using `addm`. At least one person should be allergic to `panadol`.
       If not use `editp` to make a person allergic to `panadol`. `panadol` should be the 1st medicine in the list. If not, find `panadol` using `findm panadol`
   
    1. Test case: `deletem 1`
   
       Expected: No medicine is deleted. An error message is shown as a `Person` or and `Order` uses this medicine.

### Short form of medicine

1. Adding a short form to a medicine 

    1. Prerequisites: `metformin` should be in the medicine list, it should have no short form and no medicine should have `met` as the short form. It should be the 1st medicine in the medicine list. If not, find it using `findm metformin`

    1. Test case: `sfm 1 m/met`
   
       Expected: Short form of `met` is added to the `metformin` medicine.
   
    1. Test case: `sfm 1 met`
   
    Expected: Short form is not added to the `metformin` medicine. An error message is shown due to an invalid command format used.

2. Deleting a short form of a medicine.

    1. Prerequisites: `metformin` should be in the medicine list, it should have a short form. It should be the first medicine in the medicine list. If not, find it using `findm metformin`
   
    1. Test case: `sfm 1 d/`
   
       Expected: Short form of `metformin` medicine is deleted.

    1. Test case: `sfm 1 m/ d/`
   
         Expected: Short form of `metformin` medicine is deleted.
   
    1. Test case: `sfm m/ d/`

         Expected: Short form of `metformin` medicine is not deleted. An error message is shown due to an invalid format being used.


### Listing all orders

1. Test case: `listo`

 Expected: All orders listed in the display list panel.

### Finding order(s)

1. Finding an order by status short form only

   1. Test case: `findo s/pd`

      Expected: All orders with `PENDING` Status will be listed in the display list panel. The number of orders listed shown in result display box.

1. Finding an order by status full form only

   1. Test case: `findo s/pending`
   
      Expected: All orders with `PENDING` Status will be listed in the display list panel. The number of orders listed shown in result display box.

1. Finding an order by multiple medicine keywords

    1. Test case: `findo m/pan m/ol`
   
   Expected: All orders with medicine whose names have `pan` or `ol`  as a substring will be listed in the display list panel. The number of orders listed shown in result display box.

1. Finding an order by both status and medicine keywords

   1. Test case: `findo s/pd m/pan m/ol` 
   
   Expected: All orders that has both `PENDING` status and with medicine whose names have either `pan` or `ol` as a substring 
       will be listed in the display list panel(both conditions - Status and Medicine - must be fulfilled). The number of orders listed shown in result display box. 

1. Finding an order with an invalid status

   1. Test case: `findo s/wowow` 
   
      Expected: No orders will be listed in the display list panel. An error message will be shown due to an invalid status searched for

### Adding an order

1. Adding an order that doesn't exist 

   1. Prerequisites: List all order using the `listo` command. No order with the order number `1234` should be in the list.
   
   2. Prerequisites: List all person using the `listp` command. Person base on index that is assigned order to should exist.
   
   3. Prerequisites: List all medicine using the `listm` command. Medicine that will be in the order must be in the list.
   
   4. Prerequisites: Person should not be assigned to medicine they are allergy to.
   
   5. Test case: `addo 1 m/pan o/1234`
   
      Expected: Order is added. Details of the order shown in the Information display.
   
   6. Test case: `addo 1 o/1234`, `addo 1 m/pan`
   
      Expected: No Order is added due to missing details. An error is displayed due to an invalid format being used.

   7. Test case: `addo m/`, `addo`
   
      Expected: Similar to previous.

2. Adding an order that already exists (i.e. OrderNumber already exist)

   1. Prerequisites: List all orders using the `listo` command. An order with order number `1234` should be in the list.
   
   2. Test case: `addo 1 m/pan o/1234`
   
      Expected: Order is not added. An error message is shown because an order with the same order number already exists.

3. Adding an order with medicine the person is allergy to.

   1. Prerequisites: List all orders using the `listo` command. An order with order number `2222` should not be in the list.

   2. Prerequisites: List all person using the `listp` command. The 1st person in the person list should be allergic to `paracetamol`. If not, edit his details to make him so.
   
   3. Test case: `addo 1 m/paracetamol`
   
      Expected: Order is not added. A warning message is displayed as the patient is allergic to `paracetamol`.

   4. Test case: `addo 1 m/paracetamol ia/` (ignore allergy flag `ia`)
   
      Expected: Order is added. Details of tht order shown in the Information display.

### Updating the order status

1. Updating an order status with valid chronological order.

   1. Prerequisites: List all orders using the `listo` command. The status of the 1st order should be `PENDING`. If not, add a new order and make that order the 1st in the order list using `findo`.
   
   2. Test case: `updates 1 s/pr`
   
      Expected: Order status updated to `PREPARING`. Details of the updated order is shown in the Information display box.
   
   3. Test case: `updates 1 s/completed`
   
      Expected: Order status updated to `COMPLETED`. Details of the updated order is shown in the Information display box.

   4. Test case: `updates 1 s/pending`
   
      Expected: Order status not updated. An error message is shown as orders can only be updated to a status of higher hierarchy.

   5. Test case: `updates 1 s/pwpw` 
   
      Expected: Order status not updated. An error message is shown due to an invalid status being entered.

### Deleting an order

1. Deleting an order

   1. Prerequisites: List all orders using the `listo` command. The order to delete should be the 1st in the list shown.

   2. Test case: `deleteo 1`
   
      Expected: First order is deleted from the list. Details of the deleted order is shown in Information display box.

   3. Test case: `deleteo `
   
      Expected: No order deleted. An error message is shown due to an invalid command format being used.

       
### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_


## **Appendix: Planned Enhancements**

1. Currently, to delete a short form a medicine the user has to provide `d/` flag through the `sfm` command to delete the short form
   of a medicine at a specified index. However, if any short form name is provided using the `m/` flag, it will be ignored without any
   warning. Thus, `sfm 1 m/met d/` deletes the short form the medicine at index 1 which ignoring `met`. We plan to make the `sfm` 
   command accept exactly one of `m/` or `d/` flags. This way the command `sfm 1 m/met d/` would be regarded as an invalid command 
   rather than one which deletes the short form. 

1. Currently, when adding a medicine there is no way for the user to specify the short form right then. Hence, the user has to first add
   the medicine followed by using `sfm` command to add a short form to it. We plan to allow `addm` accept an optional parameter for 
   the short form of the medicine being added. 

1. Currently, the sample data shows incorrect values for tags of persons. We plan to fix this by replacing the current sample tags with 
   tags that are appropriate in a pharmacy setting like `Elderly`, `Diabetic` or `Child` and so on.

1. Currently, there are no sample data for orders. We plan to add some sample orders so that users are better equipped to play around with the application on first start.

1. Currently, some error messages are not standardised. We plan to standardise all error messages across the application with the following format. All error messages will 1. Give an explanation of what went wrong, 2. Provide the appropriate formats and examples for the command that the user is trying to enter.

1. Currently, Undo and Redo commands do not affect the UI. As such, commands such as `listp` and `findp` cannot be undone. Moreover, this makes it such that a recently added person, that is later deleted by `undo`, will still be visible in the Info Display, until another command (eg. `viewp`) displaces it.<br />
Example:<br />
Originally, PharmHub has the following display.<br />
<img src="images/BetterUndo-0.png" width="574" /> <br />
After adding a 'newguy2' using `addp`, he shows up in the Info Display.<br />
<img src="images/BetterUndo-1.png" width="574" /><br />
On `undo`, the person's information still stays on the Info Display, even though he no longer exists in PharmHub.<br />
<img src="images/BetterUndo-2.png" width="574" /><br />
We plan to enhance the Undo/Redo feature to allow UI changes to be undone/ redone. In this way, UI states are saved along with the data, and can be reverted with `undo` and `redo`.<br />
In the case of the example given, after the implementation of this planned enhancement, the UI should revert back to an empty display after the `undo` command is ran.



## **Appendix: Effort**

### **Challenges**
* As a four-man team, each of us had to do take on a heavier workload to hit the necessary level of functionality in our product.  
* We also had to pick up many new things along the way. Some of this include Json for storage, JavaFx for Ui and PlantUml for documentation.
* Unexpected dependencies in code between different team members significantly slowed down speed of development as one member had to wait for another before proceeding.

### **Effort Required**
Original AB3 only tracked one entity type, `person`. Our application however, tracks three different entity types, `person`, `order` and `medicine`.  
This required significant effort.
* In Storage, we had to ensure that the data is valid in all the different places. For example, a `medicine` could appear as an entity in PharmHub itself,
  or in the allergies or a `person` or in an `order`. Similar checking had to be done for `person` and `order`. Validating all this and ensuring that referential integrity was upheld took significant effort.
* In Ui, due to the coupling between the three entities, it once again took a lot of effort to ensure all the UI information is being displayed correctly. 
  For example, when a `person` is edited we had to ensure the corresponding UI for `order` reflects it as well.
* In Logic, we had to ensure no regressions took place due to user behaviour. For example, we had to ensure a `medicine` can't be deleted when there is a `person` 
  allergic to it or when an `order` contains it. We also had to check if editing a `person` will result in them being allergic to a `medicine` in their `order`. There were several other such instances.


### **Achievements**
Despite the steep learning curve and difficulties, we managed to build a product that we believe will be beneficial to our target audience.  
It has features that  cater to their needs and solves their pain points.
