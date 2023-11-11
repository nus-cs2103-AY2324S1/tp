---
layout: page
title: Mark Garcera's Project Portfolio Page
---

### Project: CheckMate

CheckMate is designed to streamline the process of room bookings for hotel employees. With a Graphical User Interface (GUI) that provides the necessary information needed at a glance, and the application
being optimised for use via a Command Line Interface (CLI), we have combined elegance and efficiency when it comes to
the process of room booking for hotel receptionists.

Given below are my contributions to the project.

* **New Feature**: Room Statistics Pie Chart (The class DoughnutPieChart.java which creates the pie chart with an empty centre is taken fromuser`jewelsea`'s comment in this Stack Overflow [post](https://stackoverflow.com/questions/24121580/can-piechart-from-javafx-be-displayed-as-a-doughnut), but the class RoomPieChart.java is self created). (Pull request [#148](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/148))
  * What it does: Allows the user to see the overall distribution of available room types and occupied rooms at a glance using the pie chart, as well as see the exact numbers using the legend of the pie chart.
  * Justification: This feature greatly enhances user experience and the ability of the user to do their jobs as hotel receptionists by allowing them an overview of the types of rooms available and occupied rooms.
  * Highlights: Implementing this feature required an understanding of both the UI components and logic of the commands.
  It involved changing the constructor of the CommandResult.java class to accept a boolean `showRoomStatistics` parameter, which essentially enables me to integrate the pie chart to every single one of the commands and to manipulate the behaviour of the pie chart for each command.

* **New Feature**: JSON Injection Parser. (Pull request [#179](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/179))
  * What it does: Prevents the user from inputting any characters that may constitute a JSON command which could be executed when data is being read or written to the JSON storage file.
  * Justification: This feature is a simple cybersecurity feature meant to prevent any manipulation in the backend of the application.
  * Highlights: Simple to implement, and picked this up during the training provided for NUS Bug Bounty 2023.

* **Code contributed**: [Reposense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=markgcera&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

* **Project management**: 
  * Created the UI Mockup at the start of the project to give the team direction in what we want our first iteration to look like (Pull request [#78](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/78/files)).
  * Provided tasks done and possible next tasks to be done via the team's group chat as well as in PRs eg: (Pull request [#100](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/100))
  * Created and assigned some issues to teammates.

* **Enhancements to existing features**:
  * Created the initial skeleton for CheckMate which included: (Pull request [#100](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/100))
    * Refactoring AddressBook and adding a Room class
    * Integrating all existing methods to work with the new Room class
    * Editing the FXML files to allow the new Room field to be displayed
  * Created the BookingPeriod class based on the initial skeleton provided by Ji Hoon (Pull request [#123](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/123))
  * Created the User Interface (UI) Overhaul which is the foundation of our application's current UI (Pull request [#166](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/166)).
  * Abstracted out the Command Parse Exceptions into their own package for OOP purposes (Pull request [#250](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/250))

* **Documentation**:
  * User Guide:
    * Added a Table of Contents for the UG and updated it to match the then-capabilities of our application (Pull request [#127](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/127)).
    * Added a part under the Quick Start section showing and explaining the GUI Layout (Pull request [#260](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/260)).
  * Developer Guide:
    * Added implementation details of the `Room Statistics PieChart`, `JsonInjectionParser` features.
    * Added `Appendix: Effort` and `Appendix: Planned Enhancements`. (Pull request [#268](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/268))
    * Added `Appendix: Instructions for manual testing`. (Pull request [#270](https://github.com/AY2324S1-CS2103T-F10-1/tp/pull/270))
* **Community**:
  * Provided 9 bug reports for the team I was assigned during the PE-D.
