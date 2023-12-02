---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# HomeBoss Developer Guide
---

<!-- * Table of Contents -->
<page-nav-print />


<div style="page-break-after: always;"></div><br/>

## **Acknowledgements**

HomeBoss is a software project adapted from the
[AddressBook-Level3 project](https://se-education.org/addressbook-level3/) created by
the [SE-EDU initiative](https://se-education.org).

Libraries used in this project:

* [Jackson](https://github.com/FasterXML/jackson)
* [JavaFX](https://openjfx.io/)
* [JUnit5](https://github.com/junit-team/junit5)

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---
<div style="page-break-after: always;"></div><br/>

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of
classes [`Main`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is
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

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding
  API `interface` mentioned in the previous point.

<div style="page-break-after: always;"></div><br/>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

<br>

<div style="page-break-after: always;"></div><br/>

### UI component

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ListPanel`
, `StatusBarFooter`, `HelpWindow` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class
which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands through the `Logic` component.
- listens for changes to `Model` data through the `Logic` component so that the UI can be updated with the modified
  data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, referenced through the `Logic` component as it displays `Customer`
  and `Delivery` objects residing in the
  `Model`.

<br>

<div style="page-break-after: always;"></div><br/>

### Logic component

**API** :
[`Logic.java`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component,
taking `execute("customer delete 1")` API
call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** The lifeline for `CustomerDeleteCommandParser` and `CustomerDeleteCommand` should end at the destroy marker (
X) but due to a limitation of
PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates
   a parser that matches the command (e.g., `CustomerDeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `CustomerDeleteCommand`)
   which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a customer).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:

- when called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `CustomerAddCommandParser`) which uses the other classes shown above
  to parse the user command and create a `XYZCommand` object (e.g., `CustomerAddCommand`) which the `AddressBookParser`
  returns back as a `Command` object.
- all `XYZCommandParser` classes (e.g., `CustomerAddCommandParser`, `DeliveryDeleteCommandParser`, ...) inherit from
  the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<br>

<div style="page-break-after: always;"></div><br/>

### Model component

**API** :
[`Model.java`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/model/Model.java)


<puml src="diagrams/ModelClassDiagram.puml" width="900" />

The `Model` component,

* stores the address book data i.e., all `Customer` objects. (See the [ReadOnlyBook Model](#readonlybook-model) section
  below for more details)
* stores the delivery book data i.e., all `Delivery` objects. (See the [ReadOnlyBook Model](#readonlybook-model) section
  below for more details)
* stores the currently filtered `Customer` objects (See the [Customer Model](#customer-model)) as a separate
  _filteredCustomers_ list. (e.g., results of a `customer list` command)
* stores the currently filtered `Delivery` objects (See the [Delivery Model](#delivery-model)) as a separate
  _filteredDeliveries_ list. (e.g., results of a `delivery list --status COMPLETED` command)
* stores the currently sorted `Delivery` objects as a separate _sortedDeliveries_ list. (eg., results of
  a `delivery list --sort ASC` command)
* stores a `User` object that represents the stored user's data (See the [User Model](#user-model)).
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` object.
* stores an unmodifiable `ObservableList<ListItem>` that exposes the `Customer` or `Delivery`  details that is
  shown on the UI list panel that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically
  updates when the data in the list change.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** Due to a limitation of PlantUML, we are putting both multiplicity and association role on the same line.
</box>


<br>

#### ReadOnlyBook Model

<puml src="diagrams/ReadOnlyBookClassDiagram.puml" width="350" />

The `ReadOnlyBook` model,

* exposes the `AddressBook` and `DeliveryBook` to the outside.
* The `AddressBook` class stores the address book data i.e., all `Customer` that are contained through
  the `UniqueCustomerList`.
* the `DeliveryBook` class stores the delivery book data i.e., all `Delivery` that are contained through
  the `UniqueDeliveryList`.

<br>

#### User Model

<puml src="diagrams/UserClassDiagram.puml" width="300" />

The `User` model,

* stores the user data i.e, the username, password, secret question and answer of the user.

<br>

#### Delivery Model

<puml src="diagrams/DeliveryClassDiagram.puml" width="600" />

The `Delivery` model,

* stores the delivery data i.e, the delivery ID, delivery name, customer, delivery status, order date,
  expected delivery date and note for the delivery.

<br>

#### Customer Model

<puml src="diagrams/CustomerClassDiagram.puml" width="350" />

The `Customer` model,

* stores the customer data i.e, the customer ID, phone, email, name and address.

<br>

<div style="page-break-after: always;"></div><br/>

### Storage component

**API** :
[`Storage.java`](https://github.com/AY2324S1-CS2103T-T13-3/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,

* can save user preference data, address book data and delivery book data in JSON format,
* and read them back into corresponding objects.
* inherits from  `UserPrefStorage`
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
  that belong to the `Model`)

The concrete implementation of storage is done through `StorageManger`, which holds an instance of `UserPrefsStorage`,
`BookStorage` and `BookStorageWithReference`. Which represents the User Preference Data, Address Book and Delivery Book
respectively.

<br>

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

---

<div style="page-break-after: always;"></div><br/>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

- [Register Command](#register-command)
- [Login Command](#login-command)
- [Logout Command](#logout-command)
- [Customer Add Command](#customer-add-command)
- [Customer Edit Command](#customer-edit-command)
- [Delivery Add Command](#delivery-add-command)
- [Delivery View Command](#delivery-view-command)
- [Delivery List Command](#delivery-list-command)
- [Delivery Status Command](#delivery-status-command)
- [Delivery Note Command](#delivery-note-command)

---

<div style="page-break-after: always;"></div><br/>

### **Register Command**

#### Overview

The `register` command is used to register a new user account.
Once registered, the user will be able to log in to the account and access all the commands available.

The format for the `register` command can be found [here](UserGuide.md#register).

#### Feature Details

1. The user executes the `register` command.
2. If a stored user exists, a `CommandException` will be thrown.
3. If the command completes successfully, the user will be registered and logged in.

The following activity diagram shows the logic of a user registering an account:

<puml src="diagrams/UserRegisterActivityDiagram.puml" alt="UserRegisterActivityDiagram" />

The sequence of the `register` command is as follows:

1. The command `register --user USERNAME --password PASSWORD --confirmPass CONFIRM_PASSWORD
   --secretQn SECRET_QUESTION --answer ANSWER`
   is entered by the user (i.e., `register --user gab --password pass1234 --confirmPass
   pass1234 --secretQn First Pet? --answer Koko`).
2. `LogicManager` calls the `AddressBookParser#parseCommand` with the full user input.
3. The `AddressBookParser` will parse the command, creating a new instance of `UserRegisterCommandParser`,
   thereafter calling `UserRegisterCommandParser#parse` to parse the command arguments.
4. `UserRegisterCommandParser` will parse the arguments, create a new `User` and return a new instance
   of `UserRegisterCommand`,
   which is in turn passed back to `AddressBookParser` then back to `LogicManager`.
5. `LogicManager` calls `UserRegisterCommand#execute` with the `Model` instance as input,
   which checks if a user is already registered by calling `Model#getStoredUser`.
6. If no user is registered, `UserRegisterCommand` calls `Model#registerUser` to store the user.
7. Finally, `UserRegisterCommand` creates a new `CommandResult` instance with the result of the execution
   and returns it back to `LogicManager` which then returns it to the UI.

The following sequence diagram shows how the `register` command works:

<puml src="diagrams/UserRegisterSequenceDiagram.puml" alt="UserRegisterSequenceDiagram" />

---

<br/>

<div style="page-break-after: always;"></div>

### **Login Command**

#### Overview

The `login` command is used to log in to the user's account.
Once logged in, the user will have access to all the commands available.

The format for the `login` command can be found [here](UserGuide.md#login).

#### Feature Details

1. The user specifies the `Username` and `Password` in the `login` command.
2. If there is no registered account found, a `CommandException` will be thrown.
3. If the user is currently already logged in, a `CommandException` will be thrown.
4. The `User` is then cross-referenced with the stored user in `Model` to check if the credentials match.
   If incorrect credentials are provided, a `CommandException` will be thrown.
5. If the command completes successfully, the user will be logged in and the
   `isLoggedIn` status in `Model` will be updated to `true`.

<div style="page-break-after: always;"></div>

The following activity diagram shows the logic of a user logging in:

<puml src="diagrams/UserLoginActivityDiagram.puml" alt="UserLoginActivityDiagram" />

The sequence of the `login` command is as follows:

1. Upon launching the application, the `ModelManager` will be initialized with
   the `User` constructed with details from the authentication.json file.
2. The command `login --user USERNAME --password PASSWORD` is entered by the user (
   e.g. `login --user Gabriel435 --password 12345678`)
3. `LogicManager` calls the `AddressBookParser#parseCommand` with `login --user USERNAME --password PASSWORD` as input.
4. `AddressBookParser` will parse the command and creates a new instance of `UserLoginCommandParser`, thereafter
   calling `UserLoginCommandParser#parse` to parse the command arguments.
5. `UserLoginCommandParser` will parse the arguments, create a new `User` instance, and return a new instance
   of `UserLoginCommand`. This `UserLoginCommand` instance is then returned to `LogicManager`.
6. `LogicManager` calls `UserLoginCommand#execute` with the Model instance as input, which checks whether there is a
   registered account stored by calling
   `Model#getStoredUser`.
7. The `UserLoginCommand` then checks whether the user is currently logged in by calling `Model#getUserLoginStatus`.
8. After that, the `UserLoginCommand` checks if the user credentials match the stored user by
   calling `Model#userMatches`.
9. If the user is not logged in and the credentials match, the `UserLoginCommand` calls `Model#setLoginSuccess`,
   changing the login status to true and giving the user access to all the commands.
10. The `userLoginCommand` also calls `Model#showAllFilteredCustomerList` to display the list of customers.
11. Finally, `UserLoginCommand` creates a new `CommandResult` instance with the result of the execution and returns it
    back to `LogicManager` which then returns it to the UI.

The following sequence diagram shows how the `login` command works:

<puml src="diagrams/UserLoginSequenceDiagram.puml" alt="UserLoginSequenceDiagram" />

---

<br>

<div style="page-break-after: always;"></div>

### **Logout Command**

#### Overview

The `logout` command is used to log out from the user's account.
Once logged out, the user will have no access to all the commands available, except for `help`, `exit`,
`register`, `login`, `recover` and `delete account`.

The format for the `logout` command can be found [here](UserGuide.md#logout).

#### Feature Details

1. The user executes the `logout` command.
2. If the user is already logged out, a `CommandException` will be thrown.
3. If the command completes successfully, the user will be logged out and the
   `isLoggedIn` status in `Model` will be updated to `false`.

The following activity diagram shows the logic of a user logging out:

<puml src="diagrams/UserLogoutActivityDiagram.puml" alt="UserLogoutActivityDiagram" />

The sequence of the `logout` command is as follows:

1. The command `logout` is entered by the user.
2. `LogicManager` calls the `AddressBookParser#parseCommand` with `logout` as input.
3. `AddressBookParser` will parse the command, and create a new instance of
   `UserLogoutCommand`. This instance is then returned to `LogicManager`.
4. Then, `LogicManager` calls `UserLogoutCommand#execute` with the Model instance as input, which checks if the user is
   logged in by calling `Model#getUserLoginStatus`.
5. If the user is currently logged in, the `UserLogoutCommand` calls `Model#setLogoutSuccess`,
   changing the login status to false and restricting the user access to most commands.
6. The `UserLogoutCommand` also calls `Model#clearFilteredDeliveryList` and `Model#clearFilteredCustomerList` to hide
   the list of deliveries and customers.
7. Finally, `UserLogoutCommand` creates a new `CommandResult` instance with the result of the execution and returns it
   back to `LogicManager` which then returns it to the UI.

The following sequence diagram shows how the `logout` command works:


<puml src="diagrams/UserLogoutSequenceDiagram.puml" alt="UserLogoutSequenceDiagram" width="900" />

---

<br>

<div style="page-break-after: always;"></div><br/>

### **Customer Add Command**

#### Overview

The `customer add` command is used to create a new customer with information fields `Name`, `Phone`, `Email` and
`Address`. A unique `ID` will be assigned to the customer upon creation.

The format for the `customer add` command can be found [here](UserGuide.md#add-a-customer).

#### Feature Details

1. The user executes the `customer add` command.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the Customer already exists in the database, a `CommandException` will be thrown.
4. If the command completes successfully, the `Customer` will be added to the `Customer` database.

The following activity diagram shows the logic of adding a `Customer` into the database:

<puml src="diagrams/CustomerAddActivityDiagram.puml" alt="CustomerAddActivityDiagram" />

The sequence of the `customer add` command is as follows:

1. The User executes the `customer add` command.
2. `LogicManager` calls the `AddressBookParser#parseCommand` on the command.
3. `AddressBookParser` will parse the command, and creates a new instance of `CustomerAddCommandParser`, thereafter
   calling
   `CustomerAddCommandParser#parse` to parse the command arguments.
4. `CustomerAddCommandParser` will parse the arguments, and creates a new `Customer` instance, followed by a new
   instance of `CustomerAddCommand` using the `Customer` instance as a parameter.
5. `CustomerAddCommand` instance is then passed back to `AddressBookParser` instance, which then passes it back
   to `LogicManager`.
6. `LogicManager` calls `CustomerAddCommand#execute` with the `Model` instance as input, which checks if the user is
   logged in by calling the
   `Model#getUserLoginStatus`.
7. Thereafter, the `CustomerAddCommand` instance checks if the `Customer` exists in `Model` using `Model#hasCustomer`.
8. If the `Customer` does not exist, `CustomerAddCommand` calls `Model#addCustomer` to add the `Customer` into the
   database.
9. Finally, `CustomerAddCommand` creates a new `CommandResult` instance with the result of the execution and returns it
   back to `LogicManager` which then returns it to the UI.

The following sequence diagram shows how the `customer add` command works:

<puml src="diagrams/CustomerAddSequenceDiagram.puml" alt="CustomerAddSequenceDiagram" />

---

<br>

<div style="page-break-after: always;"></div>

### **Customer Edit Command**

#### Overview

The `customer edit` command is used to edit an existing Customer with at least one of the information fields
specified by the user, namely the customer's `Name`, `Phone`, `Email` or/and `Address`.

The format for the `customer edit` command can be found [here](UserGuide.md#update-details-of-a-customer).

#### Feature Details

1. The user executes the `customer edit` command.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the provided `ID` does not match any of the existing Customer IDs, a `CommandException` will be thrown.
4. If the edits result in a duplicate Customer, a `CommandException` will be thrown.
5. If the command completes successfully, the Customer will be edited with the new information provided by the user.

<div style="page-break-after: always;"></div>

The following activity diagram shows the logic of a user editing a customer's information:

<puml src="diagrams/Gabriels Diagrams/CustomerEditActivityDiagram.puml" alt="CustomerEditActivityDiagram" />

The sequence of the `customer edit` command is as follows:

1. The command `customer edit CUSTOMER_ID --name NAME --phone PHONE --email EMAIL --address ADDRESS` is entered by the
   user.
2. LogicManager calls the `AddressBookParser#parseCommand`
   with `customer edit CUSTOMER_ID --name NAME --phone PHONE --email EMAIL --address ADDRESS` as input.
3. `AddressBookParser` will parse the command, and creates a new instance of `CustomerEditCommandParser`, thereafter
   calling `CustomerEditCommandParser#parse` to parse the command arguments.
4. `CustomerEditCommandParser` will call its own `CustomerEditCommandParser#createCustomerEditDescriptor` method which
   in turn creates a new `CustomerEditDescriptor` instance with the parsed arguments and returns it
   to `CustomerEditCommandParser`.
5. `CustomerEditCommandParser` then creates a `CustomerEditCommand` instance and returns it to `AddressBookParser`.
6. `AddressBookParser` then passes the `CustomerEditCommand` instance to `LogicManager`.
7. `LogicManager` then calls `CustomerEditCommand#execute` with the `Model` instance as input.
8. The `CustomerEditCommand` instance then checks if the user is logged in by calling `Model#getUserLoginStatus`.
9. Thereafter, the `CustomerEditCommand` calls its own `CustomerEditCommand#createEditedCustomer` method which in turn
   creates a new `Customer` instance with the edited information provided by the user.
10. This `Customer` instance is returned to `CustomerEditCommand`. `CustomerEditCommand` then calls `Model#setCustomer`
    to update the existing `Customer` with the new information provided by the user.
11. The `CustomerEditCommand` then calls `CustomerEditCommand#updateDelivery` to update `Deliveries` associated with
    this updated `Customer`.
12. `Model#showAllFilteredCustomerList` is then called to display the updated list of customers.
13. Finally, `CustomerEditCommand` creates a new `CommandResult` instance with the result of the execution and returns
    it back to `LogicManager` which then returns it to the UI.

The following sequence diagram shows how the `customer edit` command works:

<puml src="diagrams/Gabriels Diagrams/CustomerEditSequenceDiagram.puml" alt="CustomerEditSequenceDiagram" />

---

<br>

<div style="page-break-after: always;"></div>

### **Delivery Add Command**

#### Overview

The `delivery add` command is used to add a new Delivery with all the given information fields
specified by the user, namely the delivery's `DeliveryName`, customer id of a `Customer` and `DeliveryDate`. All fields
are compulsory.

The format for the `delivery add` command can be found [here](UserGuide.md#add-a-delivery).

#### Feature Details

1. The user executes the `delivery add` command.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the provided Customer ID does not match any of the existing Customer IDs, a `CommandException` will be thrown.
4. If the expected Delivery date is before the current date, a `CommandException` will be thrown.
5. If the command completes successfully, the Delivery will be added to the Delivery database.

The following activity diagram shows the logic of a user adding a delivery:

<puml src="diagrams/Gabriels Diagrams/DeliveryAddActivityDiagram.puml" alt="DeliveryAddActivityDiagram" />

The sequence of the `delivery add` command is as follows:

1. The user inputs the `delivery add` command with `input` as the `DeliveryName`, Customer ID of a `Customer` and
   `DeliveryDate`.
   e.g.(`delivery add --name Chocolate Cake --customer 1 --date 2024-10-10`)
2. The `LogicManager` calls `AddressBookParser#parseCommand` with `input` as input.
3. The `AddressBookParser` in turn creates an instance of `DeliveryAddCommandParser` and calls
   `DeliveryAddCommandParser#parse` to parse the command arguments.
4. The `DeliveryAddCommandParser` then creates a `DeliveryAddDescriptor` instance.
5. A new `DeliveryAddCommand` instance is then created by `DeliveryAddCommandParser`.
6. The `DeliveryAddCommand` instance is then returned to `AddressBookParser`, and finally to the `LogicManager`
   where `DeliveryAddCommand#execute` method is called with `Model` instance as input.
7. The `DeliveryAddCommand` then calls upon `Model#getUserLoginStatus` to check if the user is logged in.
8. The `DeliveryAddCommand` then calls its own `DeliveryAddCommand#createDelivery` method which creates a new `Delivery`
   instance using the `DeliveryAddDescriptor` instance created earlier.
9. The `DeliveryAddCommand` then calls the `Model#addDelivery` method to add the newly created `Delivery` instance into
   the database.
10. Finally, the `DeliveryAddCommand` creates a new `CommandResult` instance with the result of the execution and
    returns it back to `LogicManager` which then returns it to the UI.

The following sequence diagram shows how the `delivery add` command works:

<puml src="diagrams/Gabriels Diagrams/DeliveryAddDiagram.puml" alt="DeliveryAddSequenceDiagram" />

---

<div style="page-break-after: always;"></div>

### **Delivery View Command**

#### Overview

The `delivery view` command is used to view a selected delivery with the id specified by the user.

The format of the `delivery view` command can be found
[here](./UserGuide.md#view-details-of-a-delivery).

#### Feature Details

1. The user will specify a `Delivery` through its `ID`.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the provided `ID` does not match any of the existing Delivery IDs, a `CommandException` will be thrown.
4. If the command completes successfully, the details of the specified `Delivery` will be shown.

<div style="page-break-after: always;"></div>

The following activity diagram illustrates the logic of viewing a `Delivery`.

<puml src="diagrams/implementation/delivery/DeliveryViewActivityDiagram.puml" width="600" />

The sequence of the `delivery view` command is as follows:

1. The command `delivery view DELIVERY_ID` is entered by the user (e.g. `delivery view 1`).
2. `LogicManager` calls the `AddressBookParser#parseCommand` with `delivery view DELIVERY_ID` as input.
3. `AddressBookParser` will parse the command, and creates a new instance of `DeliveryViewCommandParser` thereafter
   calling
   `DeliveryViewCommandParser#parse` to parse the command arguments.
4. `DeliveryViewCommandParser` will parse the arguments, and return a new instance of `DeliveryViewCommand`. This
   instance is then returned to the `LogicManager`.
5. `LogicManager` calls `DeliveryViewCommand#execute` with the Model instance as input, which checks if the user is
   logged in by calling
   `Model#getUserLoginStatus`.
6. Thereafter, the `DeliveryViewCommand` instance fetches the `Delivery` with the specified `DELIVERY_ID` from `Model`,
   calling `Model#getDelivery`.
7. Finally, `DeliveryViewCommand` creates a new `CommandResult` instance with the result of the execution and returns it
   back to `LogicManager` which then returns it to the UI.

The following sequence diagram illustrates the `delivery view` command sequence:

<puml src="diagrams/implementation/delivery/DeliveryViewSequenceDiagram.puml" width="900" />

---

<br>
<div style="page-break-after: always;"></div>

### **Delivery List Command**

#### Overview

The `delivery list` command is used to list all deliveries in the delivery book.

The format of the `delivery list` command can be found
[here](./UserGuide.md#view-a-list-of-deliveries).

#### Feature Details

1. The user can optionally specify a `status`, `customer id`, `date` and `sort` to filter and sort the
   current delivery list in any combination.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the status was provided, the status is added as a filter.
4. If the Customer ID was provided, the Customer ID is added as a filter.
5. If the date was provided, the date is added as an expected delivery date filter.
6. The delivery list will be filtered by the filters created, if any.
7. If the filtered list is empty, the application informs the user of empty list and the command execution ends.
8. If the sort was provided, the sort provided is added as the sort. By default, a descending order sort is
   added as a sort.
9. The delivery list will be sorted by the sort created.
10. If the command completes successfully, the list of deliveries is displayed to the user.

<div style="page-break-after: always;"></div>

The following activity diagram illustrates the logic for listing `Delivery`:

<puml src="diagrams/implementation/delivery/DeliveryListActivityDiagram.puml" width="800"> </puml>

<div style="page-break-after: always;"></div>

The sequence of the `delivery list` command is as follows:

1. The command `delivery list --status STATUS --customer CUSTOMER_ID --date EXPECTED_DELIVERY_DATE --sort SORT` is
   entered by the user
   (e.g. `delivery list --status created --customer 1 --date 2023-12-12 --sort ASC`).
2. The `LogicManager` calls the `AddressBookParser#parseCommand` with the full user input.
3. The `AddressBookParser` will parse the command, creating a new instance of `DeliveryListCommandParser`,
   thereafter calling `DeliveryListCommandParser#parse` to parse the command arguments.
4. `DeliveryListCommandParser#parse` will call `DeliveryListCommandParser#parseDeliveryListCommand`to parse the
   arguments and return a new instance of `DeliveryListCommand`, which is in turn passed back to `AddressBookParser`
   then back to `LogicManager`.
5. `LogicManager` calls `DeliveryListCommand#execute` with the `Model` as input, which checks if the user is logged in
   by calling `Model#getUserLoginStatus`.
6. Thereafter, `DeliveryListCommand` will call `DeliveryListCommand#createDeliveryListFilters` to create
   the filters based on the parsed arguments, if any.
7. `DeliveryListCommand` will call `Model#updateFilteredDeliveryList(Predicate)` to filter the delivery list by
   the filters created.
8. `DeliveryListCommand` will call `DeliveryListCommand#createDeliveryListSort()` to create the sort for the delivery
   list by expected delivery date based on the parsed arguments, if any, or by default, create the sort in descending
   expected delivery date.
9. `DeliveryListCommand` will call `Model#updateSortedDeliveryList(Comparator)` to sort the delivery list by the sort
   created.
10. Finally, `DeliveryListCommand` creates a new `CommandResult` instance with the result of the execution
    and returns it back to `LogicManager` which then returns it to the UI.

The default delivery sort is `desc` which sorts the delivery list by expected delivery date in descending order from
the latest date to the earliest date.

<div style="page-break-after: always;"></div>

The following sequence diagram illustrates the `delivery list` command sequence:

<puml src="diagrams/implementation/delivery/DeliveryListSequenceDiagram.puml" />

---

<br>
<div style="page-break-after: always;"></div>

### **Delivery Status Command**

#### Overview

The `delivery status` command is used to update the `DeliveryStatus` of a selected delivery with the new status
specified by the user.

The format of the `delivery status` command can be found
[here](./UserGuide.md#update-status-of-a-delivery).

#### Feature Details

1. The user will specify a `Delivery` through its `ID`. The user must specify a `DeliveryStatus` to replace the
   current status of the selected delivery.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the provided `ID` does not match any of the existing Delivery IDs, a `CommandException` will be thrown.
4. If the command completes successfully, the status of the `Delivery` with the specified Delivery ID will be updated to
   the new status.

The following activity diagram illustrates the logic of updating the `DeliveryStatus` of a `Delivery`:

<puml src="diagrams/DeliveryStatusCommandActivityDiagram.puml" width="450" />

The sequence of the `delivery status` command is as follows:

1. The command `delivery status DELIVERY_ID STATUS` is entered by the user (e.g. `delivery status 1 completed`)
2. `LogicManager` calls the `AddressBookParser#parseCommand` with `delivery status DELIVERY_ID STATUS` as input.
3. `AddressBookParser` will parse the command, and creates a new instance of `DeliveryStatusCommandParser`, thereafter
   calling
   `DeliveryStatusCommandParser#parse` to parse the command arguments.
4. `DeliveryStatusCommandParser` will parse the arguments, and return a new instance of `DeliveryStatusCommand` which is
   in turn passed back to `AddressBookParser` then back to `LogicManager`.
5. `LogicManager` calls `DeliveryStatusCommand#execute` with the Model instance as input, which checks if the user is
   logged in by calling
   `Model#getUserLoginStatus()`.
6. Thereafter, the `DeliveryStatusCommand` instance fetches the `Delivery` with the specified `DELIVERY_ID` from `Model`
   , then calls it's own `DeliveryStatusCommand#createDeliveryWithNewStatus` method to create a new `Delivery` with the
   updated `DeliveryStatus`.
7. It then replaces the existing `Delivery` with the same ID in the `Model`
   using `Model#setDelivery` with the newly created `Delivery` with identical fields except for its
   updated `DeliveryStatus`.
8. Finally, `DeliveryStatusCommand` creates a new `CommandResult` instance with the result of the execution and returns
   it back to `LogicManager` which then returns it to the UI.

The following sequence diagram illustrates the `delivery status` command sequence:


<puml src="diagrams/DeliveryStatusCommandSequenceDiagram.puml" width="900" />

---

<br>
<div style="page-break-after: always;"></div>

### **Delivery Note Command**

#### Overview

The `delivery note` command is used to create a new `Note` a selected delivery with the new note
specified by the user

The format of the `delivery note` command can be found
[here](./UserGuide.md#create-a-note-for-a-delivery).

#### Feature Details

1. The user will specify a `Delivery` through its `id`. The user must specify a non-empty `Note` to replace the
   current status of the selected delivery.
2. If the user is not logged in during command execution, a `CommandException` will be thrown.
3. If the provided `ID` does not match any of the existing Delivery IDs, a `CommandException` will be thrown.
4. If the command completes successfully, the selected `Delivery` will be updated with the new `Note`.

The following activity diagram illustrates the logic of creating a `Note` for a `Delivery`:

<puml src="diagrams/DeliveryCreateNoteActivityDiagram.puml" width="450" />

The sequence of the `delivery note` command is as follows:

1. The command `delivery note DELIVERY_ID --note NOTE` is entered by the user
   (e.g. `delivery note 1 --note This is a note`).
2. The `LogicManager` calls the `AddressBookParser#parseCommand` with `delivery note DELIVERY_ID --note NOTE` as input.
3. `AddressBookParser` will parse the command, and creates a new instance of `DeliveryCreateNoteCommandParser`,
   thereafter calling `DeliveryCreateNoteCommandParser#parse` to parse the command arguments.
4. `DeliveryCreateNoteCommandParser` will parse the arguments, and return a new instance of `DeliveryCreateNoteCommand`,
   which is in turn passed back to `AddressBookParser` then back to `LogicManager`.
5. `LogicManager` calls `DeliveryCreateNoteCommand#execute` with the `Model` instance as input,
   which checks if the user is logged in by calling `Model#getUserLoginStatus`.
6. Thereafter, the `DeliveryCreateNoteCommand` instance fetchs the `Delivery` with the specified `DELIVERY_ID`,
   from `Model` then calls it's own `DeliveryCreateNoteCommand#createDeliveryWithNewNote` method
   to create a new `Delivery` with the updated `Note`.
7. It then replaces the existing `Delivery` with the same ID in the `Model` using `Model#setDelivery` with
   the newly created `Delivery` with identical fields except for its updated `Note`.
8. Finally, `DeliveryCreateNoteCommand` creates a new `CommandResult` instance with the result of the execution and
   returns it back to `LogicManager` which then returns it to the UI.

If the specified `Delivery` already has an existing `Note`, it will be overridden by the new `Note` supplied if the
command executes successfully.

The following diagram illustrates the `delivery note` command sequence:

<puml src="diagrams/DeliveryCreateNoteSequenceDiagram.puml" width="1000" />

---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

<div style="page-break-after: always;"></div><br/>

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- has a home business
- want to oversee customers in an organised manner
- want to manage deliveries efficiently and effectively
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**:
Home-based business owners can have a huge base of customers.
HomeBoss streamlines and simplifies the management of customer contacts and deliveries,
thereby improving efficiency for business owners.

### User stories

Priorities: High (must have) - `***`, Medium (nice to have) - `**`, Low (unlikely to have) - `*`

| Priority | As …​              | I want to …​                                                             | So that …​                                                                                                                                                                              |
|----------|--------------------|--------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `***`    | an owner           | create a local account                                                   | I can personalise my account and secure my data.                                                                                                                                        | 
| `***`    | a registered owner | log in to my local account                                               | I can access my data.                                                                                                                                                                   |
| `***`    | a forgetful owner  | retrieve my account                                                      | I can still recover my data if I forget my password.                                                                                                                                    |
| `***`    | a logged-in owner  | log out of my account                                                    | I can keep my data secure.                                                                                                                                                              |
| `***`    | a registered owner | delete my account                                                        | I can clear all personal information or data from HomeBoss for privacy and security reasons.                                                                                            |
| `***`    | a registered owner | update my details                                                        | I can change my personalisation and enhance security.                                                                                                                                                        |
| `***`    | a registered owner | create a customer                                                        | I can tie deliveries to customers’ information.                                                                                                                                         |
| `***`    | a registered owner | view a customer                                                          | I can see their detailed information.                                                                                                                                                   |
| `***`    | a registered owner | see a customer's list of deliveries                                      | I can easily see all the deliveries of a certain customer.                                                                                                                              |
| `***`    | a registered owner | quickly search for the details of a client                               | I can monitor the progress of an order efficiently and effectively.                                                                                                                     |
| `***`    | a registered owner | update a customer's details                                              | I can change details if keyed in wrongly.                                                                                                                                               |
| `***`    | a registered owner | delete a customer                                                        | I can remove redundant or incorrect customer records, especially when unforeseen errors occur.                                                                                          |
| `***`    | a registered owner | view a list of customers                                                 | I can have a comprehensive overview of my customer base.                                                                                                                                |
| `***`    | a registered owner | create a delivery                                                        | I can efficiently organise and access delivery information.                                                                                                                             |
| `***`    | a registered owner | create notes about deliveries                                            | I can add additional information about deliveries.                                                                                                                                      |
| `***`    | a registered owner | view a list of deliveries                                                | I can see a comprehensive overview of my deliveries.                                                                                                                                    |
| `***`    | a registered owner | see the list of deliveries that would be delivered for the day           | I can prioritise particular orders.                                                                                                                                                     |
| `***`    | a registered owner | add a customer to a delivery                                             | I can know who the delivery is for.                                                                                                                                                     |
| `***`    | a registered owner | quickly search for the name of a delivery                                | I can monitor the progress of delivery.                                                                                                                                                 |
| `***`    | a registered owner | see a list of deliveries sorted by their expected date of delivery       | I can see a more organised list and easier for me to get an overview of all orders.                                                                                                     |
| `***`    | a registered owner | view the details of a delivery                                           | I can know what the order is and where to deliver it to.                                                                                                                                |
| `***`    | a registered owner | update the status of the delivery                                        | I can keep track of the delivery progress and notify my client.                                                                                                                         |
| `***`    | a registered owner | update delivery details                                                  | I can change any information if there was an error from                                                                                                                        me. |
| `***`    | a registered owner | delete a delivery                                                        | I can get rid of deliveries that are redundant.                                                                                                                                         |
| `*`      | a registered owner | relate my inventory to my orders                                         | I can keep track of my inventory.                                                                                                                                                       |
| `*`      | a registered owner | know the sum of all the materials required for a fixed delivery schedule | I can plan my inventory.                                                                                                                                                                |
| `*`      | a registered owner | have different user authorisation levels                                 | I can control who has access to what.                                                                                                                                                   |

<div style="page-break-after: always;"></div><br/>

### Planned Enhancements

1. Our ID generation is not consistent (e.g., If there are 5 customers with `ID` 1-5 and the last 4 are deleted, the
   next
   `ID` will be `6`. However, if you restart the application, the next created Customer will have `ID` of `2`.)
   Currently, we
   only reset the next available ID to (max `ID` + `1`) when the user closes and launches the program again. Other
   times, it
   might skip a few numbers depending on the operations performed. We plan to perform this clean up operation (
   where `ID`
   resets to (max `ID` + `1`)) after every command that modifies the data. This will ensure that the next available `ID`
   is
   always (max `ID` + 1) and also help in keeping the `ID` generation more consistent and be more space efficient.

2. Currently, you are unable to key in special characters for the name of a customer or for delivery notes. We plan to
   allow certain special characters only such as `/` for the name of a customer as some people have special characters
   eg. `Gabriel s/o Bryan` in their name or require special characters when taking notes.

3. Currently, there is an inconsistency in Command Format in terms of whether a prefix is required in a command despite
   having the same number of parameters which may cause confusion for the user. For example, `delivery note` requires
   a `--note` prefix while `delivery status` does not require a prefix at all even though both have 1 parameter. We plan
   to make our command format for consistent by having delivery status require a `--status` prefix to make it more
   predictable.

4. Currently, the `find` command requires exact match of keywords and returns results that matches any of those
   keywords. This potentially results in numerous in unwanted data to be shown if there are multiple matching keywords.
   Or, if there are no matching keywords, no results would be shown. For example, if you have `100` Chocolate Cake
   and `100` Strawberry Buns and `1` Chocolate Buns, and you search for Chocolate Buns, the result would be `100`
   Chocolate Cake and `1` Chocolate Buns and `100` Strawberry Buns. Or, if you misspelled your search as `Chcolate Bns`,
   you would receive no result. We plan to make the `find` command have more options to do more complex search
   functionalities such as fuzzy search and exact match search. For example, if you search for Chocolate Buns, with
   exact search, the result would be `1` Chocolate Buns. Or, if you misspelled your search as `Chcolate Bns`, with fuzzy
   search, the result would be `1` Chocolate Buns.

5. Currently, there is no proper precedence of errors which may cause confusion among users. For example, keying in an
   invalid command format for `customer edit` while logged out will show all the errors regarding the format first
   before it tells that you are logged out (once the command format is valid and evaluated). This may result in time
   wasted as you need to then login first before re-typing your command again. Thus, we plan to set a precedence of
   error
   so that more important errors such as authentication errors are shown first and make errors more consistent in the
   order of how it is displayed.

6. Currently, if you input an invalid ID, it simply
   states `"ID must be an integer more than 0 and less than 2147483648."`
   but does not state whether it is a Delivery ID or Customer ID.
   We plan to make the error message more specific by stating whether it is a Delivery ID or Customer ID.

7. Currently, only the user password is the only thing that is hashed in the authentication file. We plan to encrypt the
   whole JSON authentication file and the data to prevent unauthorised access to the user's account and data
   instead of only hashing the user password.

8. Currently, if you input an invalid day, month or year such as `2023-02-30`, it simply states
   date `"Dates should be in
   the format yyyy-MM-dd"` but does not specify why it is invalid. We plan to make the error more specific by stating
   what is the actual invalid input of the date, in this case, the day is invalid as 30th February does not exist.

9. Currently, you can put duplicate prefixes for delivery list to filter or sort the deliveries that may arise in
   confusion by which filter is applied. We plan to disallow duplicate prefixes for delivery list so that users would
   not be confused by the output.

10. Currently, inputting a negative value or zero for some numerical parameters that requires a positive integer simply
    states `"Invalid Command Format"`. The error message
    should be more specific and state `"Please enter a positive integer that is more than 0 and less than 2147483648."`.

<div style="page-break-after: always;"></div><br/>

### Use cases

For all use cases below, the **System** is `HomeBoss` and the **Actor** is the `user`, unless specified
otherwise.

#### **Use Case: UC01 - Register an Account**

**System:** User System (US)

**Actor:** Unregistered owner

**Preconditions:** User system has no account.

**Guarantees:**

- Account is created if the command is executed successfully.
- Unregistered owner is registered and logged in if the command is executed successfully.

**MSS:**

1. Unregistered owner enters register command with registration details.
2. US creates an account and logs-in the user.

   Use case ends.

**Extensions:**

* 1a. US detects error in entered command.

    * 1a1. US states error in command.

      Use case ends.

---

#### **Use Case: UC02 - Login**

**System:** User System (US)

**Actor:** Registered owner

**Preconditions:** Registered owner is logged out.

**Guarantees:**

- Registered owner is logged in.

**MSS:**

1. Registered owner enters the login command with login details.
2. US logs-in the user.

   Use case ends.

**Extensions:**

* 1a. US detects an error in the entered command.

    * 1a1. US states error in command.

      Use case ends.

---

#### **Use Case: UC03 - Account Recovery**

**System:** User System (US)

**Actor:** Registered owner

**Preconditions:** An owner is registered with HomeBoss.

**Guarantees:**

- Password will be changed.

**MSS:**

1. Registered owner enters the account recovery command.
2. US states the secret question that the user set during account registration.
3. Registered owner enters the account recovery details.
4. US logs-in the user

   Use case ends.

**Extensions:**

* 1a. US detects an error in the entered command.
    * 1a1. US states error in command.

      Use case ends.

* 3a. US detects an error in the entered command.
    * 3a1. US states error in command.

      Use case ends.

* 3b. US detects an error in the recovery details.
    * 3b1. US states error in recovery details.

      Use case ends.

---

#### **Use Case: UC04 - Logout**

**System:** User System (US)

**Actor:** Logged-In owner

**Preconditions:** Owner is logged in.

**Guarantees:**

- Logged-in owner would be logged out.

**MSS:**

1. Logged-In owner types logout
2. US logs owner out.

   Use case ends.

---

#### **Use Case: UC05 - Delete Account**

**System:** User System (US)

**Actor:** Registered owner.

**Preconditions:** Account is present.

**Guarantees:**

* User account is deleted.

**MSS:**

1. Registered owner types command to delete his account.
2. User system deletes his account.

   Use case ends.

---

#### **Use Case: UC06 - Update User Details**

**System:** User System (US)

**Actor:** Logged-in owner

**Preconditions:** Owner is logged in

**Guarantees:**

* Old details will be changed to the new details keyed in only if the command is executed successfully

**MSS:**

1. Logged-in Owner types in command and new details to update details.
2. US updates the details of Owner.

   Use Case ends.

**Extensions:**

* 1a. US detects an error in the entered command.
    * 1a1. US states error in command.

      Use case ends.

---

#### **Use Case: UC07 - Create Customer**

**System:** Customer Management System (CMS)

**Actor:** Logged-in owner

**Preconditions:** Owner is logged in

**Guarantees**

- Customer is created and added to Customer database if the command is executed successfully.

**MSS:**

1. Logged-in Owner types in command and customer’s details to create a customer.
2. CMS adds the Customer to the Customer database.

   Use Case ends.

**Extensions:**

- 1a. CMS detects error in entered command.

    - 1a1. CMS states error in command.

  Use case ends.

- 1b. CMS detects duplicated customer.

    - 1b1. CMS states that customer already exists.

  Use case ends.

---

#### **Use Case: UC08 - View customer’s details**

**System:** Customer Management System (CMS)

**Actor:** Logged-in owner

**Preconditions:** Owner is logged in

**Guarantees**

- Shows customer’s details if the command is executed successfully.

**MSS:**

1. Logged-in Owner types in command specifying Customer’s ID.
2. CMS shows all of the specified Customer’s details.

   Use Case ends.

**Extensions:**

- 1a. CMS detects error in entered command.

    - 1a1. CMS states error in command.

  Use case ends.

- 1b. CMS detects Customer does not exist.

    - 1b1. CMS states that Customer does not exist.

  Use case ends.

---

#### **Use Case: UC09 - Search for a Customer**

**System:** Customer Management System (CMS)

**Actor:** Logged-in owner

**Preconditions:** Owner is logged in

**Guarantees**

- List of customers with the specified keyword will be shown only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types in the command and keyword to search for a customer.
2. US shows a list of customers, with that keyword, their details.

   Use Case ends.

**Extensions**

- 1a. CMS detects error in entered command.

    - 1a1. CMS states error in command.

  Use case ends.

---

#### **Use case: UC10 - Customer Update Details**

**System:** Customer Management System (CMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- Selected customer’s details are updated only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command to update a customer’s details with at least one field specified.
2. CMS updates the details of the specified customer.

   Use Case Ends.

**Extensions:**

- 1a. CMS detects error in entered command.

    - 1a1. CMS states error in command.

  Use case ends.

- 1b. CMS detects customer does not exist.

    - 1b1. CMS states that customer does not exist.

  Use case ends.

- 1c. CMS detects there are duplicated customers.

    - 1c1. CMS states that edited details match existing Customer.

  Use case ends.

---

#### **Use case: UC11 - Customer Deletion**

**System:** Customer Management System (CMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- Selected customer is deleted if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command to delete a customer.
2. CMS deletes specified customer from the customer database.

   Use Case Ends.

**Extensions:**

- 1a. CMS detects error in entered command.

    - 1a1. CMS states error in command.

  Use case ends.

- 1b. CMS detects Customer does not exist.

    - 1b1. CMS states that Customer does not exist.

  Use case ends.

---

#### **Use case: UC12 - List Customers**

**System:** Customer Management System (CMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- All Customers are listed only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command to list all customers.
2. CMS shows list of all customers.

   Use Case Ends.

---

#### **Use case: UC13 - Delivery Creation**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- A new Delivery is created only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command to create a delivery.
2. DMS adds the delivery to the delivery database.

   Use Case Ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in command.

      Use Case Ends.

- 1b. DMS detects Customer linked to Delivery does not exist.

    - 1b1. DMS states that the specified Customer does not exist.

      Use Case Ends.

---

#### **Use case: UC14 - Delivery Notes Creation**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- A new note is added to a delivery only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command to create a note for a delivery.
2. DMS updates the delivery with the created note.

   Use Case Ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in command.

      Use Case Ends.

- 1b. DMS detects that the specified Delivery does not exist.

    - 1b1. DMS states that the specified Delivery does not exist.

      Use Case Ends.

--- 

#### **Use case: UC15 - Delivery List**

**System:** Delivery Management System (DMS)
**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- A list of deliveries is displayed only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command to view a list of deliveries.
2. DMS displays a list of all deliveries where the expected delivery dates are sorted in descending order.

   Use Case Ends.

**Extensions:**

- 1a. DMS detects filters or sort options in the entered command.

    - 1a1. DMS displays a list of deliveries filtered and sorted by the specified filters and sort order detected.

  Use Case Ends.

- 1b. DMS detects an error in the entered command.

    - 1b1. DMS states the error in the entered command.

  Use Case Ends.

---

#### **Use case: UC16 - Search for Delivery**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged in.

**Guarantees:**

- A delivery is searched for only if the command is executed successfully.

**MSS:**

1. Logged-in Owner types command and keywords to search for a delivery.
2. DMS displays a list of deliveries that match the keywords in the search query.

   Use Case Ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in entered command.

      Use case ends.

---

#### **Use Case: UC17 - View details of delivery**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in Owner.

**Preconditions:** Owner is logged-in.

**Guarantees:**

- Details of the delivery are displayed only if the command is executed successfully.

**MSS:**

1. Logged-in owner types command to view details of delivery.
2. DMS shows details of the specified delivery.

   Use case ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in entered command.

      Use case ends.

- 1b. DMS detects that the specified Delivery does not exist.

    - 1b1. DMS states that the Delivery does not exist.

      Use case ends.

---

#### **Use Case: UC18 - Update delivery status**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged-in.

**Guarantees:**

- The status of the delivery is updated only if the command is executed successfully.

**MSS:**

1. Logged-in owner types command to update the status of a delivery.
2. DMS updates the status of the delivery.

   Use case ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in entered command.

      Use case ends.

- 1b. DMS detects that the specified Delivery does not exist.

    - 1b1. DMS states that the Delivery does not exist.

      Use case ends.

---

#### **Use Case: UC19 - Update delivery details**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged-in.

**Guarantees:**

- The details of the delivery is updated only if the command is executed successfully.

**MSS:**

1. Logged-in owner types command to update the details of a delivery.
2. DMS updates the details of the delivery.

   Use case ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in entered command.

      Use case ends.

- 1b. DMS detects that the specified Delivery does not exist.

    - 1b1. DMS states that the Delivery does not exist.

      Use case ends.

- 1c. DMS detects update to Customer linked to Delivery but specified Customer does not exist.

    - 1c1. DMS states that the Customer does not exist.

      Use case ends.

---

#### **Use Case: UC20 - Delete delivery**

**System:** Delivery Management System (DMS)

**Actor:** Logged-in owner.

**Preconditions:** Owner is logged-in.

**Guarantees:**

- The delivery is deleted only if the command is executed successfully.

**MSS:**

1. Logged-in owner types command to delete a delivery.
2. DMS deletes the delivery.

   Use case ends.

**Extensions:**

- 1a. DMS detects error in entered command.

    - 1a1. DMS states error in entered command.

      Use case ends.

- 1b. DMS detects that the specified Delivery does not exist.

    - 1b1. DMS states that the Delivery does not exist.

      Use case ends.

---

<div style="page-break-after: always;"></div>

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to
    1. 1000 customers without a noticeable sluggishness in performance for typical usage.
    2. 1000 deliveries without a noticeable sluggishness in performance for typical usage.
3. The system should be easily picked up by a novice with no experience with delivery management software.
4. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.
5. Data stored should be persistent until removal by user, and Private Contact Details should be secure.
6. Data should be stored locally.
7. The GUI should not cause any resolution-related inconveniences to the user for standard screen resolutions of
   1920x1080 and higher, and should be usable for screen resolutions of 1280x720 and higher.
8. The application should be packaged into a single JAR file with size not exceeding 100MB.
9. The project is expected to adhere to a schedule which delivers a feature set every milestone up to _V1.3_
10. The application is not expected to
    1. Perform Inventory Management
    2. Perform Route Planning

<br>

<div style="page-break-after: always;"></div><br/>

### Glossary

| Term                           | Definition                                                                              |
|--------------------------------|-----------------------------------------------------------------------------------------|
| Alphanumeric                   | Consisting of only letters and numbers                                                  |
| Command Line Interface (CLI)   | A text-based user interface used to run programs                                        |
| Graphical User Interface (GUI) | A visual interface where you can interact with the program through graphical components |
| JSON                           | Short for JavaScript Object Notation, a lightweight format for storing your data        |
| Owner                          | The individual who owns the home-based business and who uses the HomeBoss app           |
| Mainstream OS                  | Windows, Linux, Unix, OS-X                                                              |
| Parameter                      | Inputs to customise the command to your needs                                           |
| Prefix                         | Special markers for HomeBoss to understand your inputs                                  |
| Private Contact Detail         | A contact detail that is not meant to be shared with others                             |

---

<div style="page-break-after: always;"></div><br/>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="note" background-color="#dff0d8" border-color="#d6e9c6" icon=":information_source:">

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more _exploratory_ testing.

</box> 

### Launch and shutdown

1. Initial launch.

    1. Download the jar file and copy it into an empty folder.

    2. Run `HomeBoss.jar`{.swift}. If you are unsure how to run a `.jar` file, you may refer to this helpful
       [guide](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Run-JAR-file-example-windows-linux-ubuntu)
       .

    3. First register for HomeBoss using the `register`{.swift} command. So, for example, if you want to register an
       account
       with the following details:

       > `USERNAME`: Alex123
       > `PASSWORD`: AlexIsGreat
       > `CONFIRM_PASSWORD`: AlexIsGreat
       > `SECRET_QUESTION`: First Pet Name?
       > `ANSWER`: KoKo

       Type `register --user Alex123 --password AlexIsGreat --confirmPass AlexIsGreat --secretQn First Pet Name?
       --answer Koko`{.swift} into the Command Box and hit enter. More details on the command can be
       found [here](#register).

    4. Expected: Should see the HomeBoss Homepage.

2. Subsequent launches.

    1. Relaunch the application by running `HomeBoss.jar`{.swift}.

    2. Using the `login`{.swift} command, log in into HomeBoss with the same user details entered earlier.
       Expected: User is able to log in successfully and see the HomeBoss homepage.

<br>

### Register

1. Registering for an account.

    1. Prerequisites: None

    2. Test
       Case: `register --user Gabriel --password GabrielIsGreat --confirmPass GabrielIsGreat --secretQn First Pet Name? --answer Koko`
       {.swift}.</br>
       Expected: A new user account is registered with username `Gabriel`{.swift}, password `GabrielIsGreat`{.swift},
       secret question `First Pet Name?`{.swift} and answer `Koko`{.swift}.

    3. Test
       Case: `register --user Ga_briel --password GabrielIsGreat --confirmPass GabrielIsGreat --secretQn First Pet Name? --answer Koko`
       {.swift}.</br>
       Expected: No new user is registered. Error indicating username constraints is
       shown in the feedback message.

    4. Test
       Case: `register --user Gabriel --password Gabriel_IsGreat --confirmPass Gabriel_IsGreat --secretQn First Pet Name? --answer Koko`
       {.swift}.</br>
       Expected: No new user is registered. Error indicating password constraints is
       shown in the feedback message.

    5. Test
       Case: `register --user Gabriel --password GabrielIsGreat --confirmPass GabrielIsGreat1 --secretQn First Pet Name? --answer Koko`
       {.swift}.</br>
       Expected: No new user is registered. Error indicating password and confirm password do not match is
       shown in the feedback message.

    6. Test
       Case: `register --user Gabriel --password GabrielIsGreat --confirmPass GabrielIsGreat --secretQn First Pet Name?`
       {.swift}.</br>
       Expected: No new user is registered. Error indicating invalid command format is
       shown in the feedback message.

    7. Test Case: There already exists a user account stored in the application.</br>
       Expected: No new user is registered. Error indicating existing account is
       shown in the feedback message.

<br>

### Login

1. Login to an account.

    1. Prerequisites: There exists a stored user in the application, with the username `Gabriel`{.swift} and
       password `GabrielIsGreat`{.swift}. The user is currently logged-out of the application.

    2. Test Case: `login --user Gabriel --password GabrielIsGreat`{.swift}.</br>
       Expected: The user is logged-in into the application. A welcome message is shown in the result message.

    3. Test Case: `login --user Ga_briel --password GabrielIsGreat`{.swift}.</br>
       Expected: The user does not get logged-in. Error indicating username constraints is
       shown in the feedback message.

    4. Test Case: `login --user Gabriel --password Gabriel_IsGreat`{.swift}.</br>
       Expected: The user does not get logged-in. Error indicating password constraints is
       shown in the feedback message.

    5. Test Case: `login --user Gabriel1 --password GabrielIsGreat`{.swift}.</br>
       Expected: The user does not get logged-in. Error indicating wrong username or password is
       shown in the feedback message.

    6. Test Case: `login --user Gabriel --password GabrielIsGreat1`{.swift}.</br>
       Expected: Similar to previous.

    7. Test Case: `login --user Gabriel`{.swift}.</br>
       Expected: The user does not get logged-in. Error indicating invalid command format is
       shown in the feedback message.

<br>

### Update Account Details

1. Updating account details.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.

    2. Test Case: `update --user Gabriel123`{.swift}.</br>
       Expected: The username of the currently logged-in user is updated to `Gabriel123`{.swift}.

    3. Test Case: `update --password GabrielIsCool --confirmPass GabrielIsCool`{.swift}.</br>
       Expected: The password of the currently logged-in user is updated to `GabrielIsCool`{.swift}.

    4. Test Case: `update --secretQn Favourite Pet --answer Bobo`{.swift}.</br>
       Expected: The secret question of the currently logged-in user is updated to `GabrielIsCool`{.swift} and the
       answer is updated to `Bobo`{.swift}.

    5. Test Case: `update`{.swift}.</br>
       Expected: No user details are updated. Error indicating that at least one field must be specified is
       shown in the feedback message.

    6. Test Case: `update --user G_briel123`{.swift}.</br>
       Expected: No user details are updated. Error indicating username constraints is
       shown in the feedback message.

    7. Test Case: `update --password Gabriel_IsCool --confirmPass Gabriel_IsCool`{.swift}.</br>
       Expected: No user details are updated. Error indicating password constraints is
       shown in the feedback message.

    8. Test Case: `update --password GabrielIsCool --confirmPass GabrielIsNotCool`{.swift}.</br>
       Expected: No user details are updated. Error indicating password and confirm password do not match is
       shown in the feedback message.

    9. Test Case: `update --password GabrielIsCool`{.swift}.</br>
       Expected: No user details are updated. Error indicating that password and confirm password must be both present
       or both absent is shown in the feedback message.

    10. Test Case: `update --secretQn Favourite Pet`{.swift}.</br>
        Expected: No user details are updated. Error indicating that secret question and answer must be both present
        or both absent is shown in the feedback message.

<br>

### Logout

1. Logging out of the application.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.

    2. Test Case: `logout`{.swift}.</br>
       Expected: The currently logged-in user is logged out of the application, currently displayed Customers/Deliveries
       are hidden.

    3. Test Case: `logout extra`{.swift} or other extra arguments.</br>
       Expected: Similar to previous.

<br>

### Recover Account

1. Recovering user account.

    1. Prerequisites: There exists a stored user in the application, and the answer of the currently
       stored user's secret question is "Koko".

    2. Test Case: `recover account`{.swift}.</br>
       Expected: The currently stored user's secret question is displayed.

    3. Test Case: `recover account --answer Koko --password NewPassword123 --confirmPass NewPassword123`{.swift}.</br>
       Expected: The currently stored user's password is updated to `NewPassword123`{.swift}

    4. Test Case: `recover account --answer NotKoko --password NewPassword123 --confirmPass NewPassword123`{.swift}</br>
       Expected: No user details are updated. Error indicating that answer is incorrect is shown in the feedback
       message.

    5. Test Case: `recover account --answer Koko --password NewPassword_123 --confirmPass NewPassword_123`{.swift}.</br>
       Expected: No user details are updated. Error indicating password constraints is
       shown in the feedback message.

    6. Test Case: `recover account --answer Koko --password NewPassword123 --confirmPass NewPassword1234`{.swift}.</br>
       Expected: No user details are updated. Error indicating password and confirm password do not match is
       shown in the feedback message.

    7. Test Case: `recover account --answer Koko --password NewPassword123`{.swift}</br>
       Expected: No user details are updated. Error indicating invalid command format is
       shown in the feedback message.

<br>

### Delete Account

1. Delete currently stored user account.

    1. Prerequisites: There exists a user account currently stored in the application

    2. Test Case: `delete account`{.swift}.</br>
       Expected: The currently stored user is deleted and all stored Customer and Delivery Data is deleted.

    3. Test Case: `delete account extra`{.swift} or other extra arguments.</br>
       Expected: Similar to previous.

<br>

### Add Customer

1. Adding a Customer to the application.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command. There is currently no stored
       Customer with a phone number of 87654321. There is currently a stored Customer with a phone number of 87651234.

    2. Test Case: `customer add --name Gabriel --phone 87654321 --email Gabrielrocks@gmail.com --address RVRC Block B`
       {.swift}.</br>
       Expected: A new Customer is added, with name `Gabriel`{.swift}, phone `87654321`{.swift},
       email `Gabrielrocks@gmail.com`{.swift} and address `RVRC Block B`{.swift}. The details of the added Customer is
       shown in the feedback message.

    3. Test Case: `customer add --name G_briel --phone 87654321 --email Gabrielrocks@gmail.com --address RVRC Block B`
       {.swift}.</br>
       Expected: No new Customer is added. Error indicating customer name constraints is
       shown in the feedback message.

    4. Test Case: `customer add --name Gabriel --phone 987654321 --email Gabrielrocks@gmail.com --address RVRC Block B`
       {.swift}.</br>
       Expected: No new Customer is added. Error indicating phone number constraints is
       shown in the feedback message.

    5. Test Case: `customer add --name Gabriel --phone abcdefgh --email Gabrielrocks@gmail.com --address RVRC Block B`
       {.swift}.</br>
       Expected: No new Customer is added. Error indicating phone number constraints is
       shown in the feedback message.

    6. Test Case: `customer add --name Gabriel --phone 87651234 --email Gabrielrocks@gmail.com --address RVRC Block B`
       {.swift}.</br>
       Expected: No new Customer is added. Error indicating that the Customer already exists is
       shown in the feedback message.

    7. Test Case: `customer add --name Gabriel --phone 987654321 --email Gabrielrocks --address RVRC Block B`
       {.swift}.</br>
       Expected: No new Customer is added. Error indicating email constraints is
       shown in the feedback message.

    8. Test Case: `customer add --name Gabriel --phone 987654321 --email Gabrielrocks@gmail.com`{.swift}.</br>
       Expected: No new Customer is added. Error indicating invalid command format is
       shown in the feedback message.

<br>

### View Details of Customer

1. View the details of a Customer.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Customer with an ID of 1. There is only one Customer stored in the application.

    2. Test Case: `customer view 1`{.swift}.</br>
       Expected: The details of the Customer with an ID of 1 is shown in the result message.

    3. Test Case: `customer view 0`{.swift}.</br>
       Expected: No new Customer details are shown. Error indicating customer ID constraints is
       shown in the feedback message.

    4. Test Case: `customer view -1`{.swift}.</br>
       Expected: No new Customer details are shown. Error indicating invalid command format is
       shown in the feedback message.

    5. Test Case: `customer view a`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `customer view 2`{.swift}.</br>
       Expected: No new Customer details are shown. Error indicating invalid customer ID is
       shown in the feedback message.

<br>

### List Customers

1. List the Customers stored in the application.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is at least one Customer Stored in the application.

    2. Test Case: `customer list`{.swift}.</br>
       Expected: All customers are listed. A message indicating that Customers have been listed is
       shown in the feedback message.

    3. Test Case: `customer list extra`{.swift}.</br>
       Expected: Similar to previous.

<br>

### Find Customers

1. Find a Customers matching query.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There are currently three stored Customers with names `Alex Wong`{.swift}, `Alex Tan`{.swift} and `Tan Ah Meng`
       {.swift}.

    2. Test Case: `customer find Alex`{.swift}.</br>
       Expected: Only the Customers `Alex Wong`{.swift} and `Alex Tan`{.swift} are shown.
       A message indicating the number of Customers listed is shown in the result message.

    3. Test Case: `customer find Alex Tan`{.swift}.</br>
       Expected: All three Customers, `Alex Wong`{.swift}, `Alex Tan`{.swift} and `Tan Ah Meng`{.swift} are shown.
       A message indicating the number of Customers listed is shown in the result message.

    4. Test Case: `customer find Ale`{.swift}.</br>
       Expected: No customers are shown. A message indicating the number of Customers listed
       is shown in the result message.

    5. Test Case: `customer find`{.swift}.</br>
       Expected: No customers are shown. An error indicating invalid command format is shown in the feedback message.

    6. Test Case: `customer find Al_x`{.swift}.</br>
       Expected: No customers are shown. A message indicating the number of Customers listed
       is shown in the result message.

<br>

### Update Customer Details

1. Update the details of a specific Customer.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Customer with an ID of 1. There is only one Customer stored in the application.

    2. Test Case: `customer edit 1 --name Gabriel`{.swift}.</br>
       Expected: The name of the Customer with an ID of 1 is updated to `Gabriel`{.swift}.
       The updated details of the Customer with an ID of 1 is shown in the feedback message.

    3. Test Case: `customer edit 1 --phone 98761234`{.swift}.</br>
       Expected: The phone number of the Customer with an ID of 1 is updated to `98761234`{.swift}.
       The updated details of the Customer with an ID of 1 is shown in the feedback message.

    4. Test Case: `customer edit 1 --email GabrielIsCool@gmail.com`{.swift}.</br>
       Expected: The email of the Customer with an ID of 1 is updated to `GabrielIsCool@gmail.com`{.swift}.
       The updated details of the Customer with an ID of 1 is shown in the feedback message.

    5. Test Case: `customer edit 1 --address RVRC Block E`{.swift}.</br>
       Expected: The address of the Customer with an ID of 1 is updated to `RVRC Block E`{.swift}.
       The updated details of the Customer with an ID of 1 is shown in the feedback message.

    6. Test Case: `customer edit 1`{.swift}.</br>
       Expected: No Customer details are updated. An error indicating that at least one field must be provided is shown
       in the feedback message.

    7. Test Case: `customer edit 2 --name Gabriel`{.swift}.</br>
       Expected: No Customer details are updated. An error indicating invalid customer ID is shown
       in the feedback message.

    8. Test Case: `customer edit 0 --name Gabriel`{.swift}.</br>
       Expected: No Customer details are updated. An error indicating invalid command format is shown
       in the feedback message.

<br>

### Delete Customers

1. Delete a specified Customer.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Customer with an ID of 1. There is only one Customer stored in the application.

    2. Test Case: `customer delete 1`{.swift}.</br>
       Expected: The Customer with an ID of 1 is deleted. The details of the deleted Customer is shown
       in the feedback message.

    3. Test Case: `customer delete 0`{.swift}.</br>
       Expected: No Customer is deleted. Error indicating invalid command format is
       shown in the feedback message.

    4. Test Case: `customer delete -1`{.swift}.</br>
       Expected: Similar to previous.

    5. Test Case: `customer delete a`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `customer delete 2`{.swift}.</br>
       Expected: No Customer is deleted. Error indicating invalid Customer ID is
       shown in the feedback message.

<br>

### Add Delivery

1. Adding a Delivery to the application.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a Customer stored with an ID of 1. And the current date is 2023-12-02.

    2. Test Case: `delivery add Chocolate Cake --customer 1 --date 2023-12-12`{.swift}.</br>
       Expected: A new Delivery is added, with name `Chocolate Cake`{.swift}, Customer ID `1`{.swift},
       expected delivery date `2023-12-12`{.swift}, order date as today's date, delivery status as `CREATED`{.swift},
       and the address as the address of the Customer with an ID of 1.
       The details of the added Customer is shown in the feedback message.

    3. Test Case: `delivery add Chocolate_Cake --customer 1 --date 2023-12-12`{.swift}.</br>
       Expected: No new Delivery is added. An Error indicating delivery name constraints is
       shown in the feedback message.

    4. Test Case: `delivery add Chocolate Cake --customer 0 --date 2023-12-12`{.swift}.</br>
       Expected: No new Delivery is added. An Error indicating customer ID constraints is
       shown in the feedback message.

    5. Test Case: `delivery add Chocolate Cake --customer a --date 2023-12-12`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `delivery add Chocolate Cake --customer 2 --date 2023-12-12`{.swift}.</br>
       Expected: No new Delivery is added. An Error indicating invalid Customer ID is
       shown in the feedback message.

    7. Test Case: `delivery add Chocolate Cake --customer 1 --date 2023-12-01`{.swift}.</br>
       Expected: No new Delivery is added. An Error indicating expected delivery date constraints is
       shown in the feedback message.

    8. Test Case: `delivery add Chocolate Cake --customer 1 --date 2023-13-01`{.swift}.</br>
       Expected: No new Delivery is added. An Error indicating date constraints is
       shown in the feedback message.

    9. Test Case: `delivery add Chocolate Cake --date 2023-13-01`{.swift}.</br>
       Expected: No new Delivery is added. An Error indicating invalid command format is
       shown in the feedback message.

<br>

### View Details of a Delivery

1. View the details of a Delivery.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Delivery with an ID of 1. There is only one Delivery stored in the application.

    2. Test Case: `delivery view 1`{.swift}.</br>
       Expected: The details of the Delivery with an ID of 1 is shown in the result message.

    3. Test Case: `delivery view 0`{.swift}.</br>
       Expected: No new Delivery details are shown. Error indicating delivery ID constraints is
       shown in the feedback message.

    4. Test Case: `delivery view -1`{.swift}.</br>
       Expected: No new Delivery details are shown. Error indicating invalid command format is
       shown in the feedback message.

    5. Test Case: `delivery view a`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `delivery view 2`{.swift}.</br>
       Expected: No new Delivery details are shown. Error indicating invalid delivery ID is
       shown in the feedback message.

<br>

### List Deliveries

1. List the Deliveries stored in the application.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There are two Customers with an ID of 1 and 2 stored in the application.
       There are three deliveries stored in the application.
       The first with ID 1, Customer ID of 1, delivery status `CREATED`{.swift}, expected delivery date `2023-12-03`
       {.swift}.
       The second with ID 2, Customer ID of 2, delivery status `SHIPPED`{.swift}, expected delivery date `2023-12-04`
       {.swift}.
       The third with ID 3, Customer ID of 2, delivery status `SHIPPED`{.swift}, expected delivery date `2023-12-05`
       {.swift}.
       The current date is `2023-12-03`{.swift}.

    2. Test Case: `delivery list`{.swift}.</br>
       Expected: All Deliveries are listed sorted in descending expected delivery date.
       A message indicating that deliveries have been listen is shown in the feedback message.

    3. Test Case: `delivery list --status CREATED`{.swift}.</br>
       Expected: The Delivery with an ID of 1 is listed. A message indicating that Deliveries have been listed is
       shown in the feedback message.

    4. Test Case: `delivery list --customer 1`{.swift}.</br>
       Expected: Similar to previous.

    5. Test Case: `delivery list --date today`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `delivery list --date 2023-12-04`{.swift}.</br>
       Expected: The Delivery with an ID of 2 is listed. A message indicating that Deliveries have been listed is
       shown in the feedback message.

    7. Test Case: `delivery list --sort ASC`{.swift}.</br>
       Expected: All Deliveries are listed sorted in ascending expected delivery date.
       A message indicating that deliveries have been listen is shown in the feedback message.

    8. Test Case: `delivery list --customer 2 --status SHIPPED --sort ASC`{.swift}.</br>
       Expected: The deliveries with ID 2 and 3 are listed in ascending expected delivery date.
       A message indicating that deliveries have been listen is shown in the feedback message.

    9. Test Case: `delivery list --status INVALID`{.swift}.</br>
       Expected: No Deliveries are listed. An Error indicating delivery status constraints
       is shown in the feedback message.

    10. Test Case: `delivery list --customer 0`{.swift}.</br>
        Expected: No Deliveries are listed. An Error indicating Customer ID constraints
        is shown in the feedback message.

    11. Test Case: `delivery list --date 2023-13-04`{.swift}.</br>
        Expected: No Deliveries are listed. An Error indicating expected delivery date constraints
        is shown in the feedback message.

    12. Test Case: `delivery list --sort random`{.swift}.</br>
        Expected: No Deliveries are listed. An Error indicating sort constraints
        is shown in the feedback message.

<br>

### Find Deliveries

1. Find Deliveries matching query.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There are currently three stored Deliveries with names `Chocolate Cake`{.swift}, `Chocolate Bun`{.swift} and
       `Strawberry Bun`{.swift}

    2. Test Case: `delivery find Chocolate`{.swift}.</br>
       Expected: Only the Deliveries `Chocolate Cake`{.swift} and `Chocolate Bun`{.swift} are shown.
       A message indicating the number of Deliveries listed is shown in the result message.

    3. Test Case: `delivery find Choclate Bun`{.swift}.</br>
       Expected: All three Deliveries, `Chocolate Cake`{.swift}, `Chocolate Bun`{.swift} and `Strawberry Bun`{.swift}
       are shown. A message indicating the number of Customers listed is shown in the result message.

    4. Test Case: `delivery find Choc`{.swift}.</br>
       Expected: No Deliveries are shown. A message indicating the number of Deliveries listed
       is shown in the result message.

    5. Test Case: `delivery find`{.swift}.</br>
       Expected: No Deliveries are shown. An error indicating invalid command format is shown in the feedback message.

    6. Test Case: `delivery find Chocolate_Cake`{.swift}.</br>
       Expected: No Deliveries are shown. A message indicating the number of Deliveries listed
       is shown in the result message.

<br>

### Update details of a Delivery

1. Update the details of a specific Delivery.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There are currently two stored Customers with an ID of 1 and 2.
       There is currently a stored Delivery with an ID of 1, Customer ID of 1.
       There is only one stored Delivery in the application.
       The current date is `2023-12-12`{.swift}.

    2. Test Case: `delivery edit 1 --name Vanilla Cake`{.swift}.</br>
       Expected: The name of the Delivery with an ID of 1 is updated to `Vanilla Cake`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    3. Test Case: `delivery edit 1 --customer 2`{.swift}.</br>
       Expected: The Customer ID of the Delivery with an ID of 1 is updated to `2`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    4. Test Case: `delivery edit 1 --date 2023-12-13`{.swift}.</br>
       Expected: The expected delivery date of the Delivery with an ID of 1 is updated to `2023-12-13`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    5. Test Case: `delivery edit 1 --status CANCELLED`{.swift}.</br>
       Expected: The delivery status of the Delivery with an ID of 1 is updated to `CANCELLED`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    6. Test Case: `delivery edit 1 --note By FedEx`{.swift}.</br>
       Expected: The note of the Delivery with an ID of 1 is updated to `By FedEx`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    7. Test Case: `delivery edit 1`{.swift}.</br>
       Expected: No Delivery details are updated. An error indicating that at least one field must be provided is shown
       in the feedback message.

    8. Test Case: `delivery edit 1 --name Vanilla_Cake`{.swift}.</br>
       Expected: No Delivery details are updated. An error indicating delivery name constraints is shown
       in the feedback message.

    9. Test Case: `delivery edit 1 --customer 0`{.swift}.</br>
       Expected: No Delivery details are updated. An error indicating invalid command format is shown
       in the feedback message.

    10. Test Case: `delivery edit 1 --customer 3`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating invalid Customer ID is shown
        in the feedback message.

    11. Test Case: `delivery edit 1 --date 2023-13-13`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating expected delivery date constraints is shown
        in the feedback message.

    12. Test Case: `delivery edit 1 --date 2023-12-11`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating expected delivery date constraints is shown
        in the feedback message.

    13. Test Case: `delivery edit 1 --status INVALID`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating delivery status constraints is shown
        in the feedback message.

    14. Test Case: `delivery edit 1 --note By_FedEx`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating note constraints is shown
        in the feedback message.

    15. Test Case: `delivery edit 2 --name Vanilla Cake`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating invalid Delivery ID is shown
        in the feedback message.

    16. Test Case: `delivery edit a --name Vanilla Cake`{.swift}.</br>
        Expected: No Delivery details are updated. An error indicating invalid command format is shown
        in the feedback message.

<br>

### Update delivery status

1. Update the status of a specific Delivery.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Delivery with an ID of 1.
       There is only one stored Delivery in the application.

    2. Test Case: `delivery status 1 SHIPPED`{.swift}.</br>
       Expected: The delivery status of the Delivery with an ID of 1 is updated to `SHIPPED`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    3. Test Case: `delivery status 1 INVALID`{.swift}.</br>
       Expected: No delivery statuses are updated. An error indicating delivery status constraints is shown
       in the feedback message.

    4. Test Case: `delivery status 0 SHIPPED`{.swift}.</br>
       Expected: No delivery statuses are updated. An error indicating delivery ID constraints is shown
       in the feedback message.

    5. Test Case: `delivery status 2 SHIPPED`{.swift}.</br>
       Expected: No delivery statuses are updated. An error indicating invalid delivery ID is shown
       in the feedback message.

    6. Test Case: `delivery status SHIPPED 1`{.swift}.</br>
       Expected: No delivery statuses are updated. An error indicating invalid command format is shown
       in the feedback message.

<br>

### Create a note for a Delivery

1. Create a note for a specific delivery.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Delivery with an ID of 1.
       There is only one stored Delivery in the application.

    2. Test Case: `delivery note 1 --note By FedEx`{.swift}.</br>
       Expected: The note of the Delivery with an ID of 1 is updated to `By FedEx`{.swift}.
       The updated details of the Delivery with an ID of 1 is shown in the feedback message.

    3. Test Case: `delivery note 1 --note By_FedEx`{.swift}.</br>
       Expected: No delivery notes are updated. An error indicating note constraints is shown
       in the feedback message.

    4. Test Case: `delivery note 0 --note By FedEx`{.swift}.</br>
       Expected: No delivery notes are updated. An error indicating invalid command format is shown
       in the feedback message.

    5. Test Case: `delivery note a --note By FedEx`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `delivery note 1`{.swift}.</br>
       Expected: Similar to previous.

    7. Test Case: `delivery note 2 --note By FedEx`{.swift}.</br>
       Expected: No delivery notes are updated. An error indicating invalid delivery ID is shown
       in the feedback message.

<br>

### Delete Delivery

1. Delete a specific delivery.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.
       There is currently a stored Delivery with an ID of 1.
       There is only one stored Delivery in the application.

    2. Test Case: `delivery delete 1`{.swift}.</br>
       Expected: The Delivery with an ID of 1 is deleted. The details of the deleted Delivery is shown
       in the feedback message.

    3. Test Case: `delivery delete 0`{.swift}.</br>
       Expected: No Delivery is deleted. An Error indicating invalid command format is
       shown in the feedback message.

    4. Test Case: `delivery delete -1`{.swift}.</br>
       Expected: Similar to previous.

    5. Test Case: `delivery delete a`{.swift}.</br>
       Expected: Similar to previous.

    6. Test Case: `delivery delete 2`{.swift}.</br>
       Expected: No Delivery is deleted. An Error indicating invalid Delivery ID is
       shown in the feedback message.

<br>

### Help

1. Shows the help information to the user.

    1. Prerequisites: None.

    2. Test Case: `help`{.swift}.</br>
       Expected: Help message is shown in the feedback message. A new window is created with the link to the User Guide.

    3. Test Case: `help extra`{.swift}.</br>
       Expected: Similar to previous.

<br>

### Exit

1. Exits the application.

    1. Prerequisites: None.

    2. Test Case: `exit`{.swift}.</br>
       Expected: The application exits.

    3. Test Case: `exit extra`{.swift}.</br>
       Expected: Similar to previous.

<br>

### Clear

1. Clears all Customer and Delivery data.

    1. Prerequisites: Logged-in into the application with the `login`{.swift} command.

    2. Test Case: `clear`{.swift}.</br>
       Expected: All stored Customer and Delivery data is deleted from the application. A message indicating that data
       is cleared is shown in the feedback message.

    3. Test Case: `clear extra`{.swift}.</br>
       Expected: Similar to previous.

<br>

### Saving data

1. Dealing with missing/corrupted data files.

    1. Prerequisites: There are existing Customer and Delivery Data files with existing stored Customers and Deliveries.

    2. Test Case: Close the application and delete `addressbook.json`{.swift}. <br/>
       Expected: Upon the next application start and login, a new `addressbook.json`{.swift} is created and
       `deliverybook.json`{.swift} is cleared.

    3. Test Case: Close the application and delete `addressbook.json`{.swift}.<br/>
       Expected: Upon the next application start and login, a new `deliverybook.json`{.swift} is created.

    4. Test Case: Close the application and edit `addressbook.json`{.swift} by changing the name of the first Customer
       to `John_Doe`{.swift}.<br/>
       Expected: Upon the next application start and login, `addressbook.json`{.swift} and
       `deliverybook.json`{.swift} is cleared.

    5. Test Case: Close the application and edit `deliverybook.json`{.swift} by changing the name of the first Delivery
       to `Chocolate_Cake`{.swift}.<br/>
       Expected: Upon the next application start and login, `deliverybook.json`{.swift} is cleared.

---

## **Appendix: Effort**

<br>

### Difficulty level

This project was moderately challenging as we had to deal with a large existing code base.
It took us some time to understand how the different components interact with each other.
Furthermore, our team was not familiar with frameworks such as JavaFX prior to this, and we had to learn how to use it.

<br>

### Challenges faced

* Understanding and refactoring the code base
    * As HomeBoss deals with Customers and Deliveries, we had to refactor `Person` into `Customer`, and irrelevant
      classes such as `Tag` has to be removed.
* Creating storage for Deliveries
    * AB3 deals with only one storage. However, HomeBoss has two storages, one for Customers and one for Deliveries.
      Furthermore, there is a dependency between the two entities, requiring a new `BookStorageWithReference`
      class that accepts two type parameters to be created.
* Adapting the `PersonListPanel` to `ListPanel`
    * The `PersonListPanel` was designed to contain a list of `Person`. However, as we decided to display both
      `Customer` and `Delivery` in the same list, we had to adapt the `PersonListPanel` to `ListPanel` to
      accommodate both types of entities.
* Figuring out how to implement a secure login/logout system
    * As one of the feature of HomeBoss is security, we had to figure out and implement the hashing of user password
      and how to store the data related to the account.

<br>

### Achievements

* Added security feature to prevent unauthorised access to the application.
* Displaying the list of Customers and list of Deliveries using the same panel.
* Creating a new storage for Deliveries that has references to Customer.
* A refreshing look of the UI.
* New features such as filtering and sorting of deliveries.
* Increasing code coverage to 83.5%.
