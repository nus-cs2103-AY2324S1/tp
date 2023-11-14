---
  layout: default.md
  title: "ChongWeiJie29's Project Portfolio Page"
---

### Project: BayMeds v.2103

BayMeds v.2103 is a desktop application used for tracking medications. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=chongweijie29&breakdown=true)

* **Enhancements implemented**:
  * Implemented add feature (Pull request [\#49]()).
  * Implemented optional fields for prescriptions and handling of them (Pull request [\#88]()).
  * Implemented a new storage and model architecture, similar to the existing one, to handle completed prescriptions (Pull request [\#103]()).
  * Update code where necessary to remove AB3 and align with BayMeds, including fixing style and logic bugs (Pull requests [\#49]() [\#122](), [\#158]()).
  * Update implementation of predicates (Pull request [\#160]()).
    * Update predicates to follow the singleton pattern, so that there will not be multiple predicates created for the same purpose.

* **Documentation**:
  * User Guide:
    * Standardise general formatting and notations in the User Guide (Pull requests [\#136](), [\#219]())
    * Modify intro section of BayMeds (Pull request [\#151]())
    * Include and modify add command (Pull request [\#152]())
  * Developer Guide:
    * Add in implementation of add command (Pull request [\#114]())
    * Add in listCompleted feature (Pull request [\#239]())
    * Add in user stories (Pull request [\#237]())
    * Add in use cases (Pull request [\#239]())
    * Add in consumption count reset feature (Pull request [\#239]())

* **Team-based tasks**:
  * Minor admin matters such as milestone set up and issue tracking
  * Prepare and upload JAR file for v1.3 (Pull request [\#131]())

* **Review and mentoring**:
  * Helped with simplifying implementation of date parsing (Pull request [\#54]()).
  * Helped with implementation of `UI` to reduce coupling with `Logic` (Pull request [\#142]()).
  * Helped with the implementation of consumption count reset (Pull request [\#163]()).

* **Community**:
  * Reported more than 5 bugs and gave suggestions during the PE Dry Run.
