---
  layout: default.md
  title: "Adhitya's Project Portfolio Page"
---

### Project: LinkTree

LinkTree is the top contact solution for software professionals. 
Using our unique tag-based system, access contacts by roles and responsibilities instantly. 
With LinkTree, swiftly connect with the right stakeholders, ensuring smooth project execution and superior collaboration.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=adhigop13&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=adhigop13&tabRepo=AY2324S1-CS2103T-W11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- **New Feature**: Create a new team
  * Implemented an AddTeamCommand class which creates a new team.
  * This command takes 2 parameters - A teamname, and a teamleader name. It then creates a new team subject to certain constraints that are detailed [here](http://127.0.0.1:8080/DeveloperGuide.html#create-a-new-team)
- **New Feature**: Add a developer to a team
  * Implemented an AddDevToTeam Command which allows users to add the developers to a team after it has been created.
  * This command takes 2 parameters - A teamname, and a developer name. It adds the given developer to the team(again subject to constraints). These constraints are detailed [here](http://127.0.0.1:8080/DeveloperGuide.html#add-developers-to-an-existing-team)

* **Enhancements to existing features**:
  * Made changes to the Model and ModelManager classes to add support for team creation.
  * Created methods to work with the new IdentityCode that is provided to every new Developer object.

**Documentation**:
* User Guide:
  * Updated the user guide for "add" related features such as the AddCommand, AddTeamCommand and AddDevToTeamCommand.[#131](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/131) 
  * Fixed styling/spelling errors or other mistakes with examples provided/commands.
  * Fixed up formatting in the UG for a more cohesive and unified look for the team.[#239](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/239) [#241](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/241) [#243](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/243)
  * Added the future enhancements appendix to provide more information on feature flaws that would be fixed in the future.[#243](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/243)

* Developer Guide:
  * Added implementation details for adding a new developer, creating a new team and adding a developer to a team. [#115](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/115) 
  * Added activity diagrams to better explain the flow of certain functions like creating a new team and adding a developer to team. [#246](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/246)
  * Modified all the User stories, Use cases, and Non-Functional Requirements to reflect the changes made in our project.[#40](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/40) [#44](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/44)
  [#115](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/115) 
  * Fixed up formatting in the DG for a more cohesive and unified look for the team.[#251](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/251)
  * Added the future enhancements appendix to provide more information on feature flaws that would be fixed in the future.[#247](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/247)


* **Project management**:
  * Assigned issues to teammates for tracking and resolution.
  * Created new project milestones for tp.
  * Fixed testcases for project builds.
  * Routinely fixed bugs and styling errors for code upkeep and quality.
  
*
* **Community**:
  * Reviewed PRs for the team [#63](https://github.com/AY2324S1-CS2103T-W11-4/tp/pull/63)
  * Participated in the PE Dry Run and reported bugs.
  * Regularly reviewed features/issues with team members during meetings/discussions.


* **Tools**:
  * Used the IntelliJ tool to generate JavaDocs for selected elements.

