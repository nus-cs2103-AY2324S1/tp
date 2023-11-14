---
layout: page
title: Tran Ha Thu's Project Portfolio Page
---
### Project: SOCareers

SOCareers is an all-in-one app that helps CS majors to keep track of job/internship-related content.
The app allows students to keep track of their networks, companies and their internship interview times.

**Summary of Contributions**:
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=oeggy03&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Enhancements implemented (Linked to relevant Pull Requests)**:
  * Enhance the existing AB3 to support another entity: `Company` along with Shuyao. My part included adding JSON loading/saving support for companies:
    * [Enhance AddressBook: Add CRUD support for company database](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/91)
    * [Update the loading of Sample Data to support company sample data](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/93)
  * Enhance the existing AB3 to support each company having a list of internships. This includes implementing JSON support and creating the `Internship` class:
    * [Add support for Internship classes within a Company class, and json support for Internships](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/108)
  * Enhance the UI of AB3 to better support the display of persons, companies and internships:
    * [Enhance UI to support displaying a list of companies](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/91)
    * [Enhance the UI to display the full details of companies/persons in the display box](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/127)
    * [Enhance the UI of each listed company to have a "Next:" field](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/127)
  * Implemented the following commands:
    * [List Persons - `list p` command](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/81)
    * [Delete Company - `delete c [INDEX]` command](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/95)
  * Writing of tests and debugging, mostly for what I implement. This often accompanies the PRs that I do for different features.

* **Contribution to the UG (Linked to relevant Pull Requests)**:
  * Update the screenshots of the updated UI for the different features:
    * [Update screenshots for commands with new UI](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/212)
  * Wrote some descriptions for a few commands:
    * [Wrote descriptions for `delete p`, `list p`, `find p` as of 27 October](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/120)
  * Standardize and format some parts of the UG (e.g. the "sorting company command" section)
    * [Formatting the UG for readability, addition of tips and some other standardization](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/212)

* **Contribution to the DG**:
  * Edited existing AB3 UML Diagrams for the DG for v1.4. These include all the diagrams for the Design section as of [this Pull Request](https://github.com/AY2324S1-CS2103T-T10-4/tp/pull/250), save for the Architecture Diagram. 
  * Updated the contents of the Design to reflect our enhancements to the code:
    * The documentation of CompanyListPanel, CompanyCard, InternshipListPanel, InternshipCard.
    * The documentation of making `XYZCommand` abstract, and having `XYZPersonCommand` and `XYZCompanyCommand` inherit from it, and the grouping of these classes into 1 package.
    * The documentation of how `XYZCommandParser` works to differentiate between executing the command on the `Person` or `Company` entity, and its accompanying UML Class diagram.
    * Documented the new additions to the model as of v1.3 and v1.4, including the `Company`, `Internship` and their accompanying classes.
    * Documented the new `JsonAdapted` classes (`JsonAdaptedCompany` and `JsonAdaptedInternship`) in the Class diagram.
    * Added short descriptions to each class in the commons package.
  * Added the Appendix: Effort section, and updated the Appendix: Planned Enhancements section.
  * Updated some screenshots.

* **Contribution to team-based tasks**:
  * Set up the organization and team for the group.
  * Set up codecov and the codecov badge in the README.
  * Changed existing mentions of AB3 to SOCareers to match our product name. 
  * Added some issues to the issue tracker as needed. [Here are the ones that I added.](https://github.com/AY2324S1-CS2103T-T10-4/tp/issues?q=is%3Aissue+author%3Aoeggy03+is%3Aclosed)

* **Review/mentoring contributions**:
  * Reviewed and merged pull requests from teammates: [Link to reviewed PRs](https://github.com/AY2324S1-CS2103T-T10-4/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)

* **Contributions beyond the project team**:
  * Gave debugging suggestions to T14-3 in [forum issue #253](https://github.com/nus-cs2103-AY2324S1/forum/issues/253)
  * Gave some advice regarding v1.4 in [forum issue #382](https://github.com/nus-cs2103-AY2324S1/forum/issues/382) and [forum issue #458](https://github.com/nus-cs2103-AY2324S1/forum/issues/458).
  * Gave some advice regarding valid emails in [forum issue #336](https://github.com/nus-cs2103-AY2324S1/forum/issues/336)
