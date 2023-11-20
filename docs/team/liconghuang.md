---
layout: default.md
title: "Licong`s Project Portfolio Page"
---

### Project: D.A.V.E.


D.A.V.E. is a desktop information storing application used for assisting student financial advisor. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 8 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added the ability to view list of leads / clients command.
    - **What it does:** Allows the user to view a filtered list of leads and clients.
    - **Justification:** This feature improves the product significantly because a user would be able to view leads and clients seperately easily.
    - **Difficulty:** This feature was not really hard, but it too a lot of time as it was the first feature I implemented, and code tracing took sometime to fully understand how the base app implement the list of persons and how filtering worked.
<p></p>

- **New Feature**: Added a command to convert leads to clients and vice versa.
    - **What it does**: Allows user to convert leads to client.
    - **Justification**: This feature improves the product signficantly because a user would be able to convert leads to clients, and client to leads without having to manually add them into their respective category of client or lead. This is to take into account for when the leads becomes the client of the student financial advisor, or when the student financial advisor wants to convert a client back to a lead.
    - **Difficulty:** It was quite challenging as I need to coordinate with other developers to integrate this feature with the backend refactoring. This is due to the fact that there are changes to leads and clients, thus constant updating is needed to to ensure that it works as intended whenever there is a change to leads or clients.

<p></p>

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=f08&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=LicongHuang&tabRepo=AY2324S1-CS2103T-F08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
<p></p>

- **Project management**:
    - Set up codecov for the workflow of the project.
<p></p>


- **Documentation**:
    - User Guide:
        - Added documentation for the features `converttoclient` and `converttolead`: [\#125](https://github.com/AY2324S1-CS2103T-F08-2/tp/pull/125)
        - Added documentation for the features `listclient` and `listlead`: [\#81](https://github.com/AY2324S1-CS2103T-F08-2/tp/pull/81)
    - Developer Guide:
        - Added implementation details of the `listclient`, `listlead`, `converttoclient`, `converttolead` feature.

<p></p>

- **Community**:
    - Bug catches for other teams, notably: [\#1](https://github.com/LicongHuang/ped/issues/1), [\#2](https://github.com/LicongHuang/ped/issues/2), [\#3](https://github.com/LicongHuang/ped/issues/3), [\#4](https://github.com/LicongHuang/ped/issues/4), [\#5](https://github.com/LicongHuang/ped/issues/5), [\#6](https://github.com/LicongHuang/ped/issues/6)
