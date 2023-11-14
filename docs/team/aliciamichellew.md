---
  layout: default.md
  title: "Alicia Michelle Wang's Project Portfolio Page"
---

### Project: SEPlendid

**SEPlendid** is a versatile application used by prospective exchangers to plan their Student Exchange Programme (SEP)
study plans. Students from NUS Computing can use this CLI-based application, with a beautiful GUI, to view partner
universities and course mappings. The application is also bundled with many other useful features.

Given below are my contributions to the project. 
For a summary, I am responsible for every feature inside the `note` section. Every command is coded out and 
planned out by me.

* **Note Feature High Level Idea**
    * Justification: Notes feature is essential in our product because we want the users to be able to keep everything 
      related to their SEP planning in one place.
    * Structure: Each note in our product is equipped with the content of the note and the tags of the note, as well as
      the index of the note. The tags feature uses the AB3 Tag class where it allows alphanumeric tags with no 
      whitespaces. The content part of the note is built such that it allows alphanumeric and special symbols, 
      such as  `.`, `?`, `_` and others. The index of the note is used for users to identify the note easily.


* **Command #1**: `note list`
    * What it does: Allows the user to list all notes that they make. 
    * Justification: This feature is essential as it allows the user to see all the notes they have made. 
    * PR: [\#141](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/141)


* **Command #2**: `note add`
  * What it does: Allows user to add note to the system.
  * How it does: User needs to specify the content of the note and the tag of the note. The index will be auto-generated 
    by the system depending on the number of notes they have. 
  * PR: [\#76](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/76)


* **Command #3**: `note delete`
  * What it does: Allows user to delete note from the system. 
  * How it does: User needs to specify the index of the note that will be deleted. The deletion of a note is final and 
    irreversible. 
  * PR: [\#165](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/165)


* **Command #4**: `note update`
  * What it does: Allows user to update the content of the note in the system. 
  * How it does: User needs to specify the index of the note that will be updated. The new content that the user input 
    will overwrite the existing content of the note. 
  * PR: [\#169](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/169)


* **Command #5**: `note tag`
  * What it does: Allows user to add more tags to the notes in the system. 
  * How it does: User needs to specify the index of the note that will be updated. The new tag will be added to the note
    the user specify. Old tags will remain inside the note.
  * PR: [\#173](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/173)


* **Command #6**: `note cleartag`
  * What it does: Allows user to clear all tags to the notes in the system.
  * How it does: User needs to specify the index of the note that will be updated. This command will remove all tags 
    related to the note.
  * PR: [\#174](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/174)


* **Command #7**: `note search`
  * What it does: Allows the user to search for notes based on the tags of their notes. 
  * Justification: As the user's number of notes grow, the users might want to filter the notes listed based on the tags 
    they give to the notes. This feature allows user to see all their notes based on the tags. 
  * How it does: User needs to specify the tag of the note that they want to search. The system will show all notes that
    have that tag. 
  * PR: [\#144](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/144)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=aliciamichellew&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&zFR=false&tabType=authorship&tabAuthor=aliciamichellew&tabRepo=AY2324S1-CS2103T-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
    * Make issues for every feature that I need to have and link it to the PRs. 
    * Did my fair share of reviewing and merging PRs. 
    * Become the mediator in the discussion of deciding the command format as there are disputes among team members. 
    * Closed milestone `v1.1`. Other milestones include ensuring that milestone is closed by the deadline when Cheng Hou 
      is not around.


* **Enhancements to existing features**:
    * Add error handling in the existing Tag class. 
    * Our team decides to do morphing, which leads to fewer enhancements to the existing features. I am a part of the 
      team that decides on how we should change the codebase to morph.


* **Documentation**:
    * User Guide:
        * Added documentation for the `note` features.
        * PRs: [\#186](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/186), 
          [\#255](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/255)
    * Developer Guide:
        * Add use cases in the developer guide for `v1.1`. 
          PR: [\#56](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/56/files)
        * Add user stores in the developer guide
          PR: [\#55](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/55)
        * Add Architecture, SeplendidLogic component
          PR: [\#264](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/264)
        * Add instructions for manual testing
          PR: [\#274](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/274)
      

* **Community**:
    * Examples PRs reviewed (with non-trivial review comments): [\#80](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/80),
      [\#73](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/73), 
      [\#68](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/68)
    * Contributed to forum discussions: Refer to the 
      [forum dashboard](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/forum-activities.html) and search 
      for`aliciamichellew`
    * Reported bugs for other teams in the class: [PED repo](https://github.com/aliciamichellew/ped/issues)

