---
layout: default.md
title: "Gabriel Seethor's Project Portfolio Page"
---

### Project: HomeBoss

HomeBoss is a desktop application used for managing deliveries for home business owners.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 19 kLoC.

- ##### Code Contributed
  [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=gabriel4357&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

- ##### Enhancements Implemented
Add a Delivery, Update details of a Delivery, Delete a Delivery, Update details of a Customer and Test Cases for all 
implemented features 

* **New Feature:** Add a Delivery
  * What it does: This feature allows the user to create Deliveries by specifying the expected Delivery date, 
    Customer ID of the Customer they would like to tie the Delivery to as well as the Delivery name.
  * Highlights: This feature has a dependency to Customer and required the use of a `DeliveryAddDescriptor` to help 
    create `Deliveries`.
  * Justification: This feature is important because it allows the user to create Deliveries. With this, Users can 
    use other methods to keep track and manage their Deliveries easily. It is also an essential operation as seen as it is 
    part of the 
    CRUD operations.
  * Credits: The implementation of this feature was inspired from the AB3 `edit` and `add` command.
  * Related pull request: [#140](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/140)
    [#179](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/179)
    [#188](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/188)

* **New Feature:** Update details of a Delivery
  * What it does: This feature allows the user to edit the details of Deliveries (e.g. name, expected delivery date, 
    status, note, customer ID etc.).
  * Highlights: This feature required the use of a `DeliveryEditDescriptor` to help
    create `Deliveries`.
  * Justification:  This feature is important because it allows the user to edit their Delivery details to ensure that
    Delivery details are up to date and accurate, reducing the likelihood of wrong/messed up orders.
  * Credits: The implementation of this feature was inspired from the AB3 `edit` command.
  * Related pull request: [#231](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/231)

* **New Feature:** Delete a Delivery
  * What it does: This feature allows the user to delete Deliveries that they feel are redundant or no longer needed.
  * Justification: This feature is important because it allows the user to get rid of Deliveries to make their list 
    of Deliveries neater and more organised.
  * Credits: The implementation of this feature was inspired from the AB3 `delete` command.
  * Related pull request: [#140](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/140)
   

* **Feature:** Update details of a Customer
  * What it does: This feature allows the user to update their Customer's details (e.g. name, phone number, address,
    email).
  * Justification: This feature is important because it allows the user to edit their Customers data to ensure that
    their data is up to date and accurate. 
  * Credits: The implementation of this feature was tweaked from the AB3 `edit` command.
  * Related pull requests: [#111](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/111)

* Added JUnit tests for all features implemented by me. This included the creation of the necessary `Builder`
  classes such as `DeliveryBuilder`, `DeliveryEditDescriptorBuilder`, `DeliveryAddDescriptorBuilder` and adding to
  the `CommandTestUtil` to include valid and invalid parameters to different commands.

- ##### Contributions to the User Guide
    * Structuring the UG, Introduction, Table of Contents, Editing the data file , Feature - Update details of a 
      Customer, Feature - View a list of Customers, Feature - Add a Delivery , Feature - Update details of a 
      Delivery, Feature - Update status of a Delivery, Feature - Delete a Delivery, Formatting and styling of the UG, 
      Ensuring consistency and correctness of the UG
Related pull requests: 
[#38](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/38)
[#73](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/73)
[#83](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/83)
[#203](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/203) 
[#300](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/300) 
[#378](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/378) 
[#416](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/416) 
[#477](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/477) 

- ##### Contributions to the Developer Guide
Did 1/5 of the Use Cases, Customer Edit Feature, Customer Edit Activity Diagram , Customer Edit Sequence Diagram, 
Delivery Add Feature, Delivery Add Activity Diagram, Delivery Add Sequence Diagram, Checked through the DG for 
correctness and made refinements/adjustments

Related pull requests: 
[#72](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/72)
[#74](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/74)
[#208](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/208)
[#475](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/475)
[#532](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/532)


- ##### Contributions to team-based tasks
* Added Logging and Assertions for the portion of my features
  [#526](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/526)
* Maintained code quality for the portion of my features
  [#137](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/137)
  [#515](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/515)
* Fix various bugs along the way, some examples: [#321](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/321)
  [#337](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/337)

- ##### Review/mentoring contribution
    * Created issues, reviewed, approved and merged pull requests.
    * Left comments on PRs to suggest improvements.
    * Reviewed 32 PRs
  [GitHub](https://github.com/AY2324S1-CS2103T-T13-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)

