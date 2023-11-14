---
layout: page
title: Ng Yin Joe's Project Portfolio Page
---

### Project: Pharmhub

PharmHub is an order and patient tracking application for small remote pharmacist to implement an all-in-one application to improve precision and efficiency. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Feature**: Added the ability to add Allergy attribute for Patients

* What it does: The new feature enables users to add one or multiple allergies to a patient. Unlike tags, allergies serve as a special attribute that triggers warnings when adding an order for a medicine that the patient is allergic to.

* Justification: Allergies are critical health information that can significantly impact patient safety. By incorporating this attribute, pharmacists can proactively avoid prescribing medications that may cause adverse reactions.

* Highlights: None

* Credits: None


**New Feature**: Added Allergy Alert for Medication Orders

* What it does: The newly added feature raises a warning when users attempt to add an order for a medicine that the patient is allergic to. Before confirming the order, the system checks the patient’s allergy profile and alerts the user if any potential allergens are detected. The user can then decide whether to proceed with the order by adding an additional flag in the command.

* Justification: Patient safety is paramount in healthcare systems. By incorporating this allergy alert, we proactively prevent adverse reactions due to medication allergies. It ensures that healthcare providers make informed decisions while prescribing treatments.

* Highlights: None

* Credits: None


**New Feature**: Added the ability to find a person based on their name, phone number, email, and allergies.

* What it does: The enhanced person search allows users to find individuals based on various criteria, including their name, phone number, email address, and allergies. Name search supports multiple keyword matches, allergies support multiple exact(case-insensitive) matches, while email and phone require a single exact(case-sensitive) match. Users can quickly locate relevant patient records or personnel within the system. 

* Justification: Efficient person search is essential for streamlined workflows in healthcare, administrative tasks, and other contexts. By expanding the search capabilities, we empower users to retrieve accurate information promptly.

* Highlights: None

* Credits: None

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=joeng03&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

**Testing for non-Assigned features**:

* [Add tests for findo command](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/171)

* [Add tests for Medicine model](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/192)

**Team-based tasks**:

* Renaming of AddressBook to PharmHub

**Documentation**:

User Guide:

* Add documentation for the features `addp`, `editp`, `findp`

* Add examples for the features `addm`, `findm`, `deletem`, `sfm`

* Smoke test and update all documentation description and examples

Developer Guide:

* Add MSS (Main Success Scenario) for Adding Medication Order for a Patient.

* Add Design of Logic Component 

* Add Implementation of Edit Person feature

* Add Manual Testing Guide for Person

* Add Glossary

**Community**:

* Contributions on the forum: [\#6](https://github.com/nus-cs2103-AY2324S1/forum/issues/6), [\#7](https://github.com/nus-cs2103-AY2324S1/forum/issues/7), [\#46](https://github.com/nus-cs2103-AY2324S1/forum/issues/46), [\#100](https://github.com/nus-cs2103-AY2324S1/forum/issues/100)

* PRs reviewed: [\#167](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/167), [\#184](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/184), [\#185](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/191), [\#186](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/186), [\#188](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/191), [\#191](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/191)

* Reported bugs and suggestions for other teams in the class: [\#1](https://github.com/joeng03/ped/issues/1), [\#2](https://github.com/joeng03/ped/issues/2), [\#3](https://github.com/joeng03/ped/issues/3), [\#4](https://github.com/joeng03/ped/issues/4), [\#5](https://github.com/joeng03/ped/issues/5), [\#6](https://github.com/joeng03/ped/issues/6), [\#7](https://github.com/joeng03/ped/issues/7)
