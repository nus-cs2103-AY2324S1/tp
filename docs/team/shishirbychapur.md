---
layout: default.md
title: "Shishir Bychapur's Project Portfolio Page"
---

<!-- Table of Contents -->
<page-nav-print />

# Shishir's Project Portfolio Page

## Project: LoveBook

LoveBook, is a dating-focused application, revolving around providing users with a convenient
and enjoyable tool to enhance their dating experiences. Featuring user preferences management, date organization,
customizable filtering options and best match algorithms, LoveBook enhances the efficiency and effectiveness of your
online dating journey.

Given below are my contributions to the project.

### **New Features**

#### **Age Field**
* What it does: Added age field for each date added by the user
* Justification: Age plays an important role in dating.
* Highlights: Age can take any value between 18 and 150.

#### **Gender Field**
* What it does: Added gender field for each date's gender
* Justification: Gender is an important factor as well in dating.
* Highlights: Gender can either be male or female. Gender icon is displayed when dates are displayed.

#### **Store User Preferences** 
* What it does: Added the ability to store preferences for the user.
* Justification: For our matchmaking algorithm to work, we need to store the user's preferences.
* Highlights: Created a new classes and interfaces to store user preferences in a JSON file.

#### **Avatar Field** 
* What it does: Added an avatar for each date added by the user
* Justification: To make a more visual association with the date.
* Highlights: Avatar is randomly generated based on the user's gender.

#### **Added Icons For Horoscope and Gender** 
* What it does: Displays the respective icon depending on the date's gender and horoscope.
* Justification: To make the application more visually appealing.
* Highlights: The icons are displayed in the date list view.

### **Code Contributions**

My code contributions can be accessed using this [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=shishir&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=false).

### **Project management**
* Managed release `v1.3 trial`on GitHub.
* Assigned milestones and issues to teammates on GitHub.
* Triaged and Assigned PE-D issues to respective teammates on GitHub.

### **Enhancements to existing features**

* Updated the GUI [Color Scheme, Orientation, Icons, Naming] to make it match the purpose of our application. 
    - Took inspiration from [here](https://github.com/AY1920S2-CS2103T-F09-3/main) for showing the logo beside the command result display.
    - Disabled the on click feature on dates due to a flickering bug using the code from [here](https://stackoverflow.com/questions/20621752/javafx-make-listview-not-selectable-via-mouse)
    - Improved the presets bar to make it more user friendly.
* Conducted Intensive Testing on the application to ensure that the application is robust and bug free.
    - Added more UI tests to ensure UI is bug free.
    - Added tests to ensure user's preferences are being stored correctly.
    - Added tests to ensure user's gender and age are accepting only valid input.
    - Applied the equivalence partitioning and boundary value analysis techniques to ensure that the application is robust.
    - Added assertions to ensure defensive programming.

### **Documentation**

#### **User Guide**
- Updated documentation for add, edit, delete dates.
- Added a command summary table for the documentation.
- Added introduction section to the documentation.
- Updated the quick start section.
- Updated the FAQ section.
- Updated the Glossary section.
- Added the Common Symbols section.
- Added the Navigating the GUI section.

#### **Developer Guide**
- Updated the existing UML models to reflect the current state of the application.
- Added UML diagrams for add, edit, delete dates.
- Added the target user profile section.

### **Community**
* Reviewed PRs and gave constructive feedback to my teammates:
  - [#247](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/247)
  - [#246](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/246)
  - [#245](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/245)
  - [#225](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/225)
  - [#149](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/149)
  - [#131](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/131)
  - [#123](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/123)
  - [#119](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/119)
  - [#101](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/101)
  - [#100](https://github.com/AY2324S1-CS2103T-F10-2/tp/pull/100)
* Posed queries in the forum to ensure PR's dont violate course guidelines.
  - [Query](https://github.com/nus-cs2103-AY2324S1/forum/issues/394)
