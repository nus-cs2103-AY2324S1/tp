---
layout: default.md
title: "Kam Jia Yue's Project Portfolio Page"
---

### Project: Tutorium

#### Overview:
Tutorium is a desktop application for tuition centre staff to obtain data analysis to plan marketing strategies. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

#### Summary of Contributions:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=kamjiayue&breakdown=true)

* **Enhancements implemented**:
    * Added three more informative attributes to the Student class
        * Gender: So that users could keep track of the gender of the student and understand more about the trend of tuition enrolments in terms of gender. 
        * Phone Number: So that users could keep track of student's phone number and make urgent calls whenever they need to.
        * Nearest MRT Station: So that users could keep track of the convenience of a student attending the tuition physically.
        * This is the hardest part of the whole tp. It is hard to implement because there are too many dependencies on the Student class (formally Person class)
          Therefore, adding only three attributes to the Student class would also require updates from all class that have dependencies on the Student class. 
          The files that have dependencies to the Student class includes the test files, and also the data files (.json) used by the test files. Hence, the amount
          of updates is unimaginably huge. Moreover, this might also highlight one of the disadvantages of having many classes. Although the code is cleaner,
          and the abstraction is better, it becomes hard to do modifications in the future if there are many dependencies between classes. Hence, it is important
          for us to learn to decrease dependencies between classes.
    * Developed the "trend" feature.
        * To allow users generate a line chart to analyse the trend of the data of a specific year.
        * This implementation is not trivial as well. Our goal is to create a pop up as our line graph so that users can see the overall
          trend of the data. This is something to do with JavaFX, which we are still not very familiar with. It requires a lot of Google Searching, trying and error,
          while coping with the high dependencies between classes. Although it is not easy, it has been a fun challenge for me to learn more about JavaFX.

* **Documentation**:
    * User Guide:
        * Renamed every Person object to Student object
        * Updated add command to match our product Tutorium.
        * Updated student fields to match our product. (Adding three more attributes: Gender, Phone Number, and Nearest MRT Station)
        * Updated add command examples as the old ones might cause unwanted errors.
        * Added a new section for the trend feature.
        * Checked for potential errors in the User Guide and update them accordingly.
    * Developer Guide:
        * Updated product name to Tutorium.
        * Added activity diagrams for the add feature.
        * Added activity and sequence diagrams for the trend feature.
        * Checked for potential errors in the Developer Guide and update them accordingly.

* **Team-based tasks**:
    * Set up the GitHub team org/repo.
    * Maintained the issue tracker.
    * Updated Markbind attributes.
    * Added and updated student attributes.
    * Updated `help` User Guide link.
    * Hosted at least 5 team meetings.
    * Wrote and improved JUnit tests for enhancements implemented.

* **Review/mentoring contributions**:
    * Reviewed PRs created by Tiw Kang Xu and other team members, providing feedbacks where appropriate.
    * PRs merged and reviewed:
        * [89](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/89)
        * [97](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/97)
        * [106](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/106)
        * [113](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/113)
        * [116](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/116)
        * [119](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/119)
        * [123](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/123)
        * [177](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/177)
        * [179](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/179)
        * [181](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/181)
        * [182](https://github.com/AY2324S1-CS2103T-W13-2/tp/pull/182)

* **Contributions beyond the project team**:
    * Reported bugs found in other teams' JAR files and User Guide during the PE Dry Run. [Link to issues reported](https://github.com/KamJiaYue/ped/issues?q=is:issue+is:open)
    * Represents the whole team whenever contacting with tutor and prof.

