---
layout: page
title: Rayner Toh Jing Xiang's Project Portfolio Page
---

### Project: TAManager

TAManager is a desktop address book application made for professors to manage their teaching assistants (TA) under their charge. It provides fast access to TAs' contact details and their availabilities for tutorials/labs. You can track teaching and claimable hours among your TAs and find relief TAs for impromptu events or unforeseen circumstances.

Given below are my contributions to the project.

* **New Feature**: Modified the `FindCommand` that allows you to search for TAs.
    * What it does: Allows the user to search for TA by name, course or free time.
    * Justification: A professor may want to quickly retrieve a TA of a specific name, fetch a list of TAs teaching a certain course or find who's available during a certain time period.
    * Highlights: This required me to tweak the parsing of the `FindCommand` to allow for the TAs to be searched by name, course or free time.
      This also required me to add more tests to `FindCommand` to ensure that our additional functionalities work as intended.

* **New Feature**: Added the `courses.json` file to hold lesson data for the respective courses.
    * What it does: Stores the lesson data for the respective courses in JSON format.
    * Justification: This ensures that the lesson data for courses is stored in a human editable file format.
    * Highlights: Professors can edit the courses.json file directly to store more lesson data for courses they're teaching.
      This implementation was challenging as I had to ensure that the new courses.json file created would not overwrite any existing data in addressbook.json.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=raynertjx&breakdown=true)


* **Project management**:
    * Led the brainstorming of user stories for our project
    * Designed the skeleton PPP used by teammates
    * Created labels that enabled us to track the different types of issues and PRs
    * Gathered tasks and improvements for the UG
  
<br>
* **Documentation for Developer Guide**:
    * Edited developer guide based on changes to `FindCommand` [\#64](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/64)
    * Modified Storage component diagram due to addition of `CoursesStorage` [\#150](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/150)

* **Documentation for User Guide**:
    * Updated UG based on changes made to `FindCommand` [\#60](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/60)
    * Add annotated screenshots and fixed inconsistent formatting in UG [\#75](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/75), [\#156](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/156)
    * Apply more user-centric "you" language to the entire UG [\#146](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/146)
    * Add understanding GUI section in UG [\#156](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/156)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#50](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/50), [\#39](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/39), [\#165](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/165), [\#166](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/166)
