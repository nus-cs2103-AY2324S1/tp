---
layout: default.md
title: "Julius Gambe's Project Portfolio Page"
---

### Project: HomeBoss

HomeBoss is a desktop application used for managing deliveries for home business owners.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **New Feature:** Added the Delivery model

    * What it does: This feature allows the user to store deliveries in the application
    * Justification: This feature is important because it allows the user to store their deliveries in the application
      as
      our application is centered around managing deliveries.
    * Highlights: This required the creation of the `Delivery` model, which stores the various fields needed for the
      delivery commands. As the delivery commands are based on the `Delivery` model, the creation of the `Delivery`
      model
      was the first step in the implementation of the delivery commands.
    * Related Pull Requests: [\#114](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/114)

* **New Feature:** Added the ability to list deliveries with filter and sort functionalities

    * What it does: Allows the user to list deliveries with a filter and sort their deliveries
    * Justification: This feature improves the product significantly because it allows the user to view their deliveries
      in a more organised manner.
    * Highlights: This required two separate lists to be created, one for the filtered deliveries and one for the sorted
      deliveries. The filtered deliveries list is created by iterating through the original list and adding deliveries
      that
      match the filter criteria. The sorted deliveries list is created by sorting the filtered deliveries list using a
      comparator. The filtered deliveries list is then displayed to the user.
    * Related Pull Requests: [\#158](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/158)
      , [#202](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/202)

* **New Feature:** Added Delivery View

    * What it does: Allows the user to view a delivery in detail
    * Justification: This feature improves the product significantly because it allows the user to view their deliveries
      with details.
    * Highlights: This required a new class to be created to handle the view delivery command. The view delivery command
      takes in the id of the delivery to be viewed and displays the delivery details including the date, address,
      delivery
      status and more to the user.
    * Related Pull Requests: [\#158](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/158)

* **Major Enhancement:** Enhanced UI List to show Deliveries

    * What it does: Allows the user to view their deliveries in the UI List.
    * Justification: This feature improves the product significantly because it allows the user to view their deliveries
      in the UI List as the current implementation only allows customers to be listed out.
    * Highlights: This required changing the data structure used to represent an item in the list to be more general
      rather
      than directly using a specific model. This allows the UI List to be used for both customers and deliveries.
    * Related Pull Requests: [\#130](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/130)

* **Code
  Contributed:**  [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=juliusgambe&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Contributions to team-based tasks:**
    * Managed and facilitated team meetings and discussions.
    * Managed issues and pull requests on GitHub.
    * Managed release of `v1.3` on GitHub.
    * Checked code quality of team members' pull requests.

* **Documentation**
    * User Guide:
        * Added the sections for the features for "View a list of Deliveries" and "View details of a Delivery",
          (Pull request [#310](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/310))
        * Added the sections "FAQ" and "Command Summary" in UserGuide.md
          (Pull request [#228](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/288)
          , [#289](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/289))
    * Developer Guide:
        * Modified Model class diagram and created separate class diagrams for different models.
          (Pull request [#180](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/180))
        * Added the sections, Use Cases for User, List of Deliveries, View Details of a Delivery, User Stories and
          Planned
          Enhancements
          (Pull request [#95](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/95/files))
        * Added User Stories
          (Pull request [#68](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/68)
          , [#272](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/272))
        * Added Planned Enhancements
          (Pull request [#440](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/440))

* **Community:**
    * PRs Reviewed: As of 12th November, I have reviewed 101 PRs
      [(Github)](https://github.com/AY2324S1-CS2103T-T13-3/tp/pulls?q=is%3Apr+reviewed-by%3Ajuliusgambe+is%3Aclosed+)
    * Reported bugs for other teams during PE Dry Run:
      reported 26 bugs posted as [issues](https://github.com/juliusgambe/ped/issues)
