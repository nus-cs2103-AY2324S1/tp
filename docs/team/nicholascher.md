---
layout: default.md
title: "Nicholas Cher's Project Portfolio Page"
---

### Project: FreelanceBuddy

**Overview:** FreelanceBuddy is a **powerful and efficient Command Line Interface (CLI) 
optimised app designed for freelancers**.
It's a one-stop solution for freelancers, helping them manage multiple clients, track project statuses, 
and stay on top of their financial reports. 

Given below are my contributions to the project.

* **New Feature**: Finance UI
  * What it does: Displays the finances that the users input into FreelanceBuddy
  * Justification: Tracking and managing finances is one of the most important aspects of a freelancer's job. 
  Our aim was to provide a clean and simple interface, so that freelancers are able to easily view all their finances.
  * Highlights: The finances are color coded based on their type. Expenses are given a red font while commissions 
  are given a green font. This helped to provide a more intuitive overview of the finances.

* **New Feature**: Adding a new commission
    * What it does: Adds a new commission to the Finance Tab
    * Justification: Tracking of commissions is essential to a freelancer. This feature was essential in ensuring that
    our app was tailored to freelancers.
    * Highlights: In FreelanceBuddy, our commissions objects help capture the most important aspects of a commission, the
        client, amount, description and time due. 

* **New Feature**: Filter by client name
  * What it does: Filters all the finances by client name
  * Justification: We expect full time freelancers to have many clients, hence there will be a need for them to 
  filter all the finances by the client name.
  * Highlights: Provides users with a clear breakdown of their finances by client.

* **New Feature**: Filter by time frame
  * What it does: Filters all the finances by the given time frame
  * Justification: Freelancers will want to view all the finances that they have in a given time frame. For example,
  they might want to view all the finances they had over the past month. 
  * Highlights: Provides users with a clear overview of their finances within a given timeframe.

* **New Feature**: Summary command
  * What it does: Provides a summary of the finances for a given client
  * Justification: It is often difficult for freelancers to accurately gauge whether a client is worth their time.
  Hence, we felt that it would be beneficial for us to provide a simple command that will help provide a quick overview
  of a client's finances.
  * Highlights: Provides the user with a comprehensive breakdown, showing a total profit/loss, and the totals for 
  commission and expenses respectively. 

    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nicholascher&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**:
    * Made the labels for tagging
    * Helped to set up and close milestones v1.1, v1.2, v1.2b and v1.4

* **Enhancements to existing features**:
  * **Add ParseCommandHandler**
    * What it does: Helps to manage which parser is used for each tab
    * Justification: We have three separate tabs, and the tabs have some overlapping commands. For example filter-n
      should have a different response depending on if the user is in the Finance or Events tab.

* **Documentation**:
    * User Guide:
        * Added documentation for the following features: `add-c`, `filter-c`, `filter-t`, `summary`
        * Added the `Finance Tab` section in the `Command Summary`
        * Helped standardise the ordering of commands throughout the UG
    * Developer Guide:
        * Updated the Acknowledgements section
        * Updated the `Logic` section to match FreelanceBuddy
        * Added the `Filtering Lists` section to `Implementation`
        * Added points 1-3 in the `Planned Enhancements` section
        * Added the `Appendix: Effort` section
        * Added some Use cases for Finance
        * Added the Manual Test cases for `list`, `add-c`, `add-e` and `edit` for Finance

* **Community**:
    * [PR reviews for group mates](https://github.com/AY2324S1-CS2103T-W09-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Anicholascher)
    * Helping group mates:
      * After I implemented the ParseCommandHandler, there were instances where my group mates were unsure of how to use it. 
      I stepped in and helped them when they encountered problems with it. They also provided me with feedback on how I
      could improve the ParseCommandHandler.

* **Tools**:
    * Intellij
    * MarkBind

