---
layout: page
title: Miguel's Project Portfolio Page
---

### Project: npc_track

npc_track is a desktop app for managing student contacts, optimized for use via a Command Line Interface (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, Npc_Track can get your contact
management tasks done faster than traditional GUI apps.


Given below are my contributions to the project:

### Code Contributions
- [Link to My Code Contributins on tP Code Dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jose%20miguel&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

### Enhancements Implemented
	- Added a Ui unique to npc_track
		- Decided the color palette of the overall application (inspired by the hit video game 'Garry's Mod')
		- Came up with the color palette of the person cards.
		- Added the final UI logo to the application.
		- Edited MainWindow.fxml to make a new UI for v1.2
		- Edited DarkMode.css to create a a more stylized UI for our application for v1.2
	- Refactored the attendance class to support different attendance statuses (Present, Absent, VR, and Unmarked attendances)
	- Added tutorial attendance status support to the attendance class.
	- Added a new parameter in the mark attendance commands (`markAtd` and `markGroupAtd`) to support a status parameter.
	- Fix `markAtd` error throwing if the markAtd format was incorrect/missing parameters.
	- Fix `markGroupAtd` error throwing if the markGroupAtd format format was incorrect/missing parameters.
	- Fix `unmarkAtd` error throwing if the markGroupAtd format format was incorrect/missing parameters.
	- Fix `unmarkGroupAtd` error throwing if the markGroupAtd format format was incorrect/missing parameters.
	- Helped to add test cases to the markAttendance using partition testing principles and made sure that all inputted statuses were valid, building on the already existing test cases made by fellow teammates Choon Yan and Si Yuan.

### Contributions to the User Guide (UG)

- Updated the `markAttendance` and `markGroupAttendance` commands to include the parameter for status.
- Added tables for what statuses are considered valid as parameters for the command.
- Added FAQ to the UG about the number of tutorials/weeks to be marked in the programme, as well as handling students with the same name.
- Added glossaries for the UG and status.
- Made the initial UI mockup.

### Contributions to Team-Based Tasks

- Helped to contribute user stories, as well as filtering out what was needed for our project iterations for v1.2 and v1.3
- Helped to make the video demos for both v1.2 and v1.3
- Helped to facilitate meetings and helping to set out goals for each iteration
- Contributed to ideas given during meetings.

### Contributions to the Developer Guide (DG) and UML Diagrams
- Added extensions for use cases for attendance-marking commands.
	- Specifically, added extensions for invalid statuses.
- Added activity PUML diagrams for marking attendance and inputting participation.
- Added considerations for implementing the attendance class with statuses, as well as other considerations.

### Review and Mentoring Contributions

- Reviewed PR's given by teammates.
- Facilitated, vetoed and helped to contribute ideas during meetings.
- Merged PR's into the overall master branch/project.
- Fixed and reasoned with some bugs given during the PE.

### Contributions Beyond the Project Team

- Helped in the product demo and presentation for CS2101.
- Gave my support ;)


