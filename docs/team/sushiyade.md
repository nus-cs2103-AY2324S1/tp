---
layout: default.md
title: "Ito Tetsushi's Project Portfolio Page"
---

### Project: FreelanceBuddy

**Overview:** FreelanceBuddy is a **powerful and efficient Command Line Interface (CLI)
optimised app designed for freelancers**.
It's a one-stop solution for freelancers, helping them manage multiple clients, track project statuses,
and stay on top of their financial reports.

Given below are my contributions to the project.

* **New Feature**: Events
  * What it does: Displays events that the user inputs into the application. 
  * Justification: Events are a crucial part of a freelancer's life. They need to be able to keep track of their 
  events and deadlines. The Event logging allows them to be on task and not miss any deadlines.
  * Highlights: This feature allows the user to view all their upcoming events in chronological order.

* **New Feature**: Event Filter by name
  * What it does: Allows users to filter their events by their name
  * Justification: As freelancers have many events, they need to be able to filter their events by name to find the 
  relevant events.
  * Highlights: Results are chronological and the user can view all their events in one place. It is case-insensitive.

* **New Feature**: Event Filter by date
  * What it does: Allows users to filter their events by their time
  * Justification: As freelancers have many events, they need to be able to filter their events by time to find the
    relevant events.
  * Highlights: Results are chronological and the user can view all their searched events in one place from the 
  specified date to today.

* **New Feature**: Event Card
  * What it does: Allows users view important information about their events in a card format
  * Justification: We needed to display the information in a more user-friendly way. The card format allows the user to
    view the information in a more concise manner.
  * Highlights: Only details which exist are shown in the card.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sushiyade&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
  * Created demo video for Freelance Buddy

* **Enhancements to existing features**:
* **Updated**: Add Contact Parser
  * What it does: Allows users to add contacts to the application, with additional and optional information such as
    address and Telegram handle.
  * Justification: We felt that the fields which were optional were widely used in the freelancing
    space, but were not exactly necessary. Hence, we included them but made them optional.
  * Highlights: optionality of certain fields.

* **Documentation**:
  * User Guide:
    * Added documentation for Contacts
      *  Parameter Fields
    * Added documentation for the features in Events
      * `add`
      * `delete`
      * `filter-n`
      * `filter-t`
      * `list`
      * `list-all`
    
  * Developer Guide:
    * Added UML Diagrams for Tabs and UI
    * Updated UI design description
    * Added use cases for Events
    * Added Manual Tests for Events
      * `add`
      * `edit`
      * `delete`
      * `filter-n`
      * `filter-t`
      * `list`
      * `list-all`
    * Added Documentation for planned enhancements
      * `Duplicate contact names`
      * `Duplicate phone numbers`

* **Community**:
  * Reported bugs and suggestions for other teams in the class
  * Reviewed non-trivial number of PRs
  * I collaborated closely with Daniel Loh (dloh2236) to build the base Logic and Model for the Events section in our application.
  Throughout our work, we frequently supported each other and had good communication which resulted in an efficient workflow,
  allowing us to implement commands and refine the UI in timely fashion. Additionally, I assisted Daniel by creating test cases, 
  and he aided me in resolving certain merge conflicts.


* **Tools**:
  * IntelliJ
