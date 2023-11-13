---
layout: page
title: Andre's Project Portfolio Page
---

## Project: CCACommander Ultra Promax Xtra 9000PLUS

### Overview

CCACommander Ultra Promax Xtra 9000PLUS is a one-stop application for CCA Heads to manage CCA members and events, optimised for CCA Heads who prefer to use command line interface.
My team and I adapted the product from an existing Java application called [Address Book (Level 3)](https://se-education.org/addressbook-level3/) over a span of 1.5 months.

Given below is a summary of my contributions to the project. All of my code contributions can be found in this [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=andrechuakj&breakdown=true#/)

### Features Implemented
* `createMember` [#113](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/113)
  * **Feature details:** Allows a user to create a member in CCACommander.
  * **Justifications:** Provides users with the capability to add new members.
* Create `Enrolment`, `Hours`, `Remark`, `UniqueEnrolmentList` classes [#144](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/144) (previously `Attendance`)
    * **Feature details:** Introduces the fundamental classes to encapsulate an enrolment object.
    * **Justifications:** Establishes the core structure for tracking enrolment, hours, and remarks for CCA members, enhancing data organization and management.

### Enhancements to existing features
* Add `Gender` field to `createMember` command [#113](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/113)
    * **Feature details:** Introduces a `Gender` field to the `createMember` command, allowing users to specify the gender of the created member.
    * **Justifications:** Provides additional information in member creation, accommodating more detailed member profiles.
* Refactor `AddressBook` to `CCACommander` [#132](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/132)
    * **Feature details:** Renames AddressBook to CCACommander for improved relevance and clarity.
    * **Justifications:** Aligns the naming with the application's domain, making the codebase more intuitive and reflective of its purpose.
* Refactor `Attendance` to `Enrolment` [#177](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/177)
    * **Feature details:** Renames Attendance to Enrolment for better representation of the class's purpose.
    * **Justifications:** Enhances code readability and semantic accuracy, making it easier for developers to understand the class's role in managing enrolment data.
* Optional fields [#183](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/183)
    * **Feature details:** Introduces optional fields to relevant commands, allowing users to include additional information based on their needs.
    * **Justifications:** Offers increased customization and adaptability, catering to various scenarios where optional details might be necessary.
* Logging for `ParserUtil` [#294](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/294)
  * **Feature details:**  Introduces log messages to display when a parser method encounters failures.
  * **Justifications:** Provides a more comprehensive understanding of the parser's behavior, facilitating quicker identification and resolution during debugging.

Relevant tests were added for features implemented and feature enhancements.

### Project management
* In my role overseeing documentation, I meticulously ensure the user guide and developer guide are not only well-formatted but also maintain a consistent and professional appearance. Check out some examples from these pull requests:
  * [#98](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/98/files)
  * [#301](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/301)

### Documentation
* **User Guide**
    * Added command details and summaries for the following commands: `createMember` [#88](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/88), `clear` [#168](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/168).
    * Updated command details for the following commands: `deleteMember`, `editMember`, `createEvent`, `deleteEvent`,
 `editEvent`, `editEnrolment`, `viewMember`, `viewEvent`.
    * Added list of commands that can be undone under the `undo` command section. [#251](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/251)
    * Added screenshots for the following commands (subsequently replaced): `list`, `viewMember`, `viewEvent`. [#256](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/256)
    * Created table to organise list of acceptable values for each command. [#254](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/254)
    * Enhanced formatting of the user guide to ensure consistency and well-structured presentation. [#301](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/301)
      * Credits: Styling for table of contents was adapted from [StackOverflow](https://stackoverflow.com/questions/19999696/are-numbered-headings-in-markdown-rdiscount-possible).

* **Developer Guide**
  * Added target user profile and value proposition [#90](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/90)
  * Added 1 use case:
    * UC01 - Create a member [#90](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/90)
  * Added a dedicated section to explain the `Enrolment` model, supplemented with a diagram [#173](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/173)

### Community
* This is the full [list](https://github.com/AY2324S1-CS2103T-F11-1/tp/pulls?q=is%3Apr+reviewed-by%3Aandrechuakj) of pull requests which I have reviewed.
* A non-exhaustive list of non-trivial pull requests which I have reviewed:
    * [#84](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/84)
    * [#99](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/99)
    * [#110](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/110)
    * [#149](https://github.com/AY2324S1-CS2103T-F11-1/tp/pull/149)
* Bugs found:
  * [#257](https://github.com/AY2324S1-CS2103T-F11-1/tp/issues/257)
