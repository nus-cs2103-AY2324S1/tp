---
  layout: default.md
  title: "Benjamin Koh's Project Portfolio Page"
---

### Project: HomeBoss

HomeBoss is a desktop application used for managing deliveries for home business owners.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **New Feature:** Create a new storage for Deliveries
  * What it does: This feature allows the storage of Delivery data in local data files.
  * Justification: The current existing implementation only allows the storage of Persons and does not allow different
    sets of data to refer to each other, however our application requires the storage of two different types of data, 
    Customer and Deliveries, where Deliveries are reference a specific Customer.
  * Highlights: Update of existing storage system to be more easily extended through the use of generic types, 
    where the storage system now uses `BookStorage`, implemented a new type of storage `BookStorageWithReference` 
    which can reference another `BookStorage`. (E.g. Deliveries referencing Customers)
  * Related Pull Requests: [#118](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/118) </br></br>

* **New Feature:** Added Delivery Update Status Command 
  * What it does: This feature allows the user to update the status of a specific Delivery.
  * Justification: This feature is important as it is a common operation, and it allows a shortcut quickly update 
    the status of a specific delivery without using the full delivery edit command.
  * Highlights: As this command is designed to be short without a prefix, it is unable to use the existing tokenizers 
    to parse the input, instead it uses a custom RegEx pattern to parse the preamble to the correct arguments.
  * Related Pull Requests: [#121](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/121), 
    [#174](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/174),
    [#348](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/348) </br></br>

* **New Feature:** Added Delivery Create Note Command
  * What it does: This feature allows the user to add a small note to a specific delivery.
  * Justification: This feature is important as different delivery orders might have small remarks or changes that do
    not necessitate the addition of another field (E.g. A cake shop might receive a special request to include a
    particular word on the cake).
  * Highlights: As the note may not be present for all orders, special management is done for notes to ensure that
    empty notes do not crash the application.
  * Related Pull Requests: [#123](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/123), 
    [#134](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/134),
    [#327](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/327) </br></br>

* **New Feature:** Added Delivery Find Command
  * What it does: This feature allows the user to quickly search up deliveries by name.
  * Justification: This feature is important as a user would need to find the Delivery ID associated with a Delivery
    to perform other operations, but cannot remember the ID.
  * Related Pull Requests: [#196](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/196), 
    [#197](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/197),
    [#353](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/353) </br></br>

* **New Feature:** Added Customer List Command
  * What it does: This feature allows the user to quickly list all Customers.
  * Justification: This feature is important as the user might want to see an overview of their Customer base. 
  * Related Pull Requests: [#108](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/108), 
    [#206](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/206)</br></br>

* **Code contributed:** [Code contributed](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=b-enguin&breakdown=true)

* **Project Management:**
  * Created and Managed Team Github Repository, performed administrative tasks such as setting up CI, Milestone and 
    label management. (PRs: [#1](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/1),
    [#102](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/102), 
    [#185](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/185))
  * As of 14 November 2023, I have reviewed 63 PRs. [(Github)](https://github.com/AY2324S1-CS2103T-T13-3/tp/pulls?q=is%3Apr+reviewed-by%3Ab-enguin+is%3Aclosed) </br></br>

* **Enhancements to existing features:**
  * Modification of CLI prefix format to `--PREFIX` (PR: [#107](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/107)) </br></br>

* **Documentation:**
  * User Guide
    * Added/Updated documentation for the features `customer view`, `customer list`, `delivery status`, `delivery note`,
      `delivery find`. (PRs: [#147](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/147), 
      [#148](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/148),
      [#212](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/212),
      [#213](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/213),
      [#228](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/228),
      [#250](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/250),
      [#311](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/311),
      [#322](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/322),
      [#372](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/372),
      [#418](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/418))
    * Added the section Getting Started (PRs: [#303](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/303),
      [#379](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/379),
      [#418](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/418))
  * Developer Guide
    * Added implementation details of features `delivery status`, `delivery note` 
      (PRs: [#183](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/183), 
      [#186](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/186),
      [#228](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/228))
    * Update implementation details of Storage (PR: [#195](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/195))
    * Added/updated Instructions for Manual Testing (PR: [#461](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/461))
    * Added Use Cases (PRs: [#94](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/94/files))
    * Added NFRs (PRs: [#75](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/75), 
      [#86](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/86))</br></br>

* **Community:**
  Reported Bugs for other teams during PE Dry Run: Total of 15 issues reported as [issues](https://github.com/B-enguin/ped/issues)
