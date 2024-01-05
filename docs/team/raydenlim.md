---
layout: default.md
title: "Jian Song (rayden)'s Project Portfolio Page"
---

### Project: F.A.K.E.J.A.R.V.I.S.

F.A.K.E.J.A.R.V.I.S. is a brownfield project built on Address Book 3 (AB3), with the intention to help CS1101S Avengers manage their Teaching Assistant (TA) duties.

Given below are my contributions to the project.

* **New Feature 1**: Added the Graded Test component
  * **What it does**: Enables users to view the graded tests of their students at a glance.
  * **Justification**: Currently in CS1101S, the Avengers would have to navigate through the many folders in Canvas to access their student's results 1 by 1. This process is time-consuming as the grades are not consolidated in one single platform.
  * **Highlights**: The graded tests are displayed within the Student's card, enabling the Avengers to retrieve their student's grades at a glance. This provides a super convenient way for Avengers to better track their student's performance.
  * **Credits**: No reuse of code or third-party libraries used.

<br>

* **New Feature 2**: Added the ability to edit graded tests.
  * **What it does**: Enables users to dynamically edit their student's graded test scores.
  * **Justification**: The ability to edit graded test scores is essential to accommodate situations where grades may need to be adjusted, updated, or corrected.
  * **Highlights**: As the fields are optional, users can easily modify the scores for individual components of a student's graded test, without having to key in the scores for the unconcerned fields. Moreover, graded test scores can be edited  via the `edit` and `editgradedtest` command.
  * **Credits**: No reuse of code or third-party libraries used.
  
|                                                                            **Type**                                                                             | **Justification**                                                                                                                                                                                                                                                                                 |
|:---------------------------------------------------------------------------------------------------------------------------------------------------------------:|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <md><code style="color: #A46AFF;"> gt/RA1:&lt;SCORE> &#124; RA2:&lt;SCORE> &#124; MidTerms:&lt;SCORE> &#124; Finals:&lt;SCORE> &#124; PE:&lt;SCORE></code></md> | <ul><li> **Readability:** The command is more descriptive, making it clear that it is editing a person's graded test.</li><li>**Flexibility:** Users can potentially edit other person-related attributes if needed in the future by extending the command.</li><ul>                              |
|                                            `editgradedtest ra1/<SCORE> ra2/<SCORE> mt/<SCORE> f/<SCORE> pe/<SCORE>`                                             | <ul><li> **Conciseness:** It's shorter and easier to type, which can be beneficial if users need to edit graded test scores frequently.</li><li>**Consistency:** If the primary use case for this command is editing graded test scores, it might provide a more streamlined experience.</li><ul> |
    
<br>

* **Code contributed**: [raydenlim's RepoSense](https://tinyurl.com/CS2103T15raydenlim)
  <br>

* **Project management**:
  * Reviewed and approved pull requests.
  * Organised Git Issues.
  * Updated team [weekly meeting notes](https://docs.google.com/document/d/1QKJGL88ciMlyb5MfS2XyThnuUBGdwFIULFgP9MLTFEA/edit).


* **Enhancements to existing features**:
  * Enhanced the GUI by appending images next to the email, phone number and telegram fields.

* **Enhancements implemented**:

* **Documentation**:
  * **Contributions to User Guide**:
    * Added documentation for Graded Test Management Section.
    * Organised the format of the User Guide.
    * Expanded on the command summary with examples.
    * Updated the Tables of Content.

    <br>
  * **Contributions Developer Guide**:
    * Designed the class diagram for `GradedTest`.
    * Designed the sequence diagram for `EditGradedTestCommand`.
    * Designed the activity diagram for `createEditedGradedTestPerson`.
    * Added the Use cases for edit graded test.
    * Added the Planned Enhancement Appendix.
    * Organised the Use cases.


* **Community**:
  * **Contributions to team-based tasks**:
    * Updated User Guide and Error Messages to be more lucid.
    * Set up a Google SpreadSheet for PE-D [PE-D Excelsheet](https://docs.google.com/spreadsheets/d/1O9mGiqRIjbg8E9W5kGl0biqmFpexlLQspCeSyn15r_Q/edit?usp=sharing).
    * Made the logo for F.A.K.E.J.A.R.V.I.S.
    * Update the logo of the UI.
    * Bug Hunting.

    <br>

  * **Review/mentoring contributions**:
    * [raydenlim's tp comments](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/tp-comments.html#9-lim-song-raydenlim-73-comments).

  <br>

  * **Contributions beyond the project team**:
    * [raydenlim's forum post](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/forum-activities.html#60-lim-song-raydenlim-6-posts)
    * Contributed to forum discussions, and shared some unusual bugs and how to resolve them (examples: [Assisting](https://github.com/nus-cs2103-AY2324S1/forum/issues/122#issuecomment-1711100005),
      [Unusual Bugs](https://github.com/nus-cs2103-AY2324S1/forum/issues/274#issuecomment-1770647394)).
    * Helped to identify 12 bugs during practical dry run. PED-Repo available [here](https://github.com/raydenlim/ped/issues).

<br>

* **Tools**:
  * Java
  * IntelliJ
  * FXML
  * PlantUML
  * GitHub
  * CSS
  * Markbind
  * CATcher Smoke Test
