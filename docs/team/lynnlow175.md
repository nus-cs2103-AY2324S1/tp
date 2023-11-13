---
layout: default.md
title: "Lynn's Project Portfolio Page"
---

# Lynn's Project Portfolio Page

### Project: LoveBook

LoveBook, is a dating-focused application, revolving around providing users with a convenient
and enjoyable tool to enhance their dating experiences. Featuring user preferences management, date organization,
customizable filtering options and best match algorithms, LoveBook enhances the efficiency and effectiveness of your
online dating journey.

Given below are my contributions to the project.

* **New Feature**: Filter Dates by `Date` Profile Attributes
    * What it does: Allows users to filter `Date` objects by their attributes such as `Age`, `Height`, `Gender` and `Name`.
    * Justification: This feature is useful because it allows user to find the specific dates with the attributes that the user is looking for. This helps to save time and effort for the user.
    * Highlights: This enhancement requires the understanding of the `Model` and the creation of `PredicatesUtil` to wrap an array of predicates into one `Predicate` so that the code conforms to the SLAP principle. `PredicatesUtil` implements the `Predicate` interface and is used in the `Model` to update list of `Date` objects visible to user.

* **New Feature**: Sort Dates by `Date` Profile Attributes
    * What it does: Allows users to sort `Date` objects by their attributes such as `Age`, `Height`, `Gender`, `Horoscope`, `Income` and `Name`.
    * Justification: This feature is useful because it allows user to view the list of `Date` objects in a specified (increasing/decreasing) order based on a specified attribute.
    * Highlights: This enhancement requires the understanding of the `Comparator` interface in order to allow each valid attribute to be sorted in a specific order.

* **New Feature**: Random Date command
    * What it does: Allows users to randomly select a `Date` from the list of `Date` objects.
    * Justification: This feature is useful because helps to solve the issue of indecisiveness when choosing a `Date` to go out with. It saves time and effort for the user and instills a sense of excitement.
    * Highlights: This enhancement requires the understanding of how to obtain the complete list of `Dates` objects and generating a random number to select a `Date` from the list.

* **New Feature**: Welcome message
  * What it does: Allows users to see a welcome message when they start the application.
  * Justification: This feature is useful because it makes users feel welcomed when they start the application and conveys the app's purpose.
  * Highlights: This enhancement requires the understanding of how Javafx works in order to display the message to the user. It also requires the knowledge of where the initial stage is set in order to display the message when the application starts.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lynnlow175&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Documentation**:
    * User Guide: 
      * Added documentation for own features like `filter`, `sort` and `blindDate`.
    * Developer Guide: 
      * Added implementation details of commands like `filter`, `sort` and `blindDate`.
      * Contributed to Planned Enhancement section of Developer Guide.
      * Contributed to the Effort section.

* **Community**:
  * PRs reviewed: #89, #13
  * Submitted PE-D on behalf of the group
  * Took down notes for team meetings
