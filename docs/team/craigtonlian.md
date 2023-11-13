---
  layout: default.md
  title: "Project Portfolio Page"
---

# Project: Staff-Snap

> ***Craigton Lian's*** *Project Portfolio Page*<br>

## Overview
**Staff-Snap** is a desktop hiring management application used for managing applicants during the recruitment cycle. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9,000 lines of code.

<br>

## Summary of Contributions

<box type="tip">

The tags with a G-prefix [[G\#267](https://github.com/nus-cs2103-AY2324S1/forum/issues/267)] represents an external link to the corresponding GitHub Issue.<br>
The tags with a P-prefix [[P\#80](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/80)] represents an external link to the corresponding Pull Request.<br>

</box>

* **Interview Management Features**: Added the `addi`, `editi`, and `deletei` command.
  * **What it does**: Allows the user to add, edit, and delete interviews for each applicant.
  * **Justification**: This feature forms the core functionality of Staff-Snap, as it allows the user to manage the interview process for each applicant, while updating the interview rating progressively.
  * **Highlights**: This enhancement had to be carefully designed to ensure that the interview data is stored in a way that is easily accessible and modifiable by the user. Input validation and duplicate handling was also implemented to ensure that the data is consistent and accurate.
  * **Credits**: Automatic duplicate handling under the `addi` function was **not** implemented by me. This was implemented by my teammate [Austin Huang](https://ay2324s1-cs2103t-w08-1.github.io/tp/team/austinhuang1203.html).

<br>

* **Import CSV Feature**: Added the `import` command.
  * **What it does**: Allows the user to import a correctly-formatted CSV file containing applicant data into Staff-Snap.
  * **Justification**: This feature allows the users to directly import large amounts of applicant data into Staff-Snap using a common file format like CSV instead of manually keying in the data.
  * **Highlights**: This enhancement required careful and thorough error handling and input validation in order to prevent erroneous data from being added into Staff-Snap. 
  * **Credits**: The third-party library [OpenCSV](https://opencsv.sourceforge.net/) was used to parse the input CSV in the implementation of this feature.

<br>

<div style="page-break-after: always;"></div>

* **Code Contributed**: [Craigton Lian's RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=craigtonlian&breakdown=true)

<br>

* **Project Management**:
  * Set up GitHub [team organisation](https://github.com/orgs/AY2324S1-CS2103T-W08-1/people) and [repo](https://github.com/AY2324S1-CS2103T-W08-1/tp)
  * Set up CodeCov GitHub Action and Gradle Build
  * Managed [releases](https://github.com/AY2324S1-CS2103T-W08-1/tp/releases) `v1.2`, `v1.2.1`, `v1.3`, and `v1.4` on GitHub
  * Managed team's [Project Board](https://github.com/orgs/AY2324S1-CS2103T-W08-1/projects/1) on GitHub to ensure steady and consistent progress

<br>

* **Enhancements to Existing Features**:
  * Updated the logo for Staff-Snap [[P\#74](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/74)]
  * Updated the Applicant Card to display icons [[P\#105](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/105)]
  * Updated the GUI to display Interview Cards [[P\#111](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/111)]
  * Updated the `help` command to open the User Guide in the browser [[P\#75](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/75)]

<br>

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addi`, `editi`, `deletei`, and `import` [[P\#80](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/80)] [[P\#90](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/90)] [[P\#117](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/117)] [[P\#140](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/140)]
    * Did cosmetic tweaks to existing documentation of features `help` and `edit` [[P\#67](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/67)] [[P\#138](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/138)] [[P\#145](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/145)]
  * Developer Guide:
    * Added implementation details of the Interview Management features such as `addi`, `editi`, and `deletei` [[P\#118](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/118)]
    * Added implementation details of the Import CSV feature [[P\#228](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/228)]
    * Added sequence diagram for `import` command [[P\#228](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/228)]
    * Added activity diagram for `deletei` command [[P\#228](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/228)]

<br>

* **Community**:
  * PRs reviewed (with non-trivial review comments) [[P\#110](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/110)] [[P\#121](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/121)] [[P\#125](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/125)] [[P\#129](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/129)]
  * Contributed to forum discussions [[G\#121](https://github.com/nus-cs2103-AY2324S1/forum/issues/121)] [[G\#158](https://github.com/nus-cs2103-AY2324S1/forum/issues/158)] [[G\#228](https://github.com/nus-cs2103-AY2324S1/forum/issues/228)] [[G\#267](https://github.com/nus-cs2103-AY2324S1/forum/issues/267)]

<br>

* **Tools**:
  * Integrated a third-party library ([OpenCSV](https://opencsv.sourceforge.net/)) to the project [[G\#300](https://github.com/nus-cs2103-AY2324S1/forum/issues/300)] [[P\#126](https://github.com/AY2324S1-CS2103T-W08-1/tp/pull/126)]
  * Integrated a new GitHub CD workflow ([Netlify Deploy Previews](https://docs.netlify.com/site-deploys/deploy-previews/)) to the team repo to preview deployments to the User Guide and Developer Guide [[G\#228](https://github.com/nus-cs2103-AY2324S1/forum/issues/228)]
