# Riya's Project Portfolio Page

## Project: Advanced&Efficient (A&E)

Advanced&Efficient helps Emergency Department (ED) doctors in logging patient reports and connecting patients with
relevant departments and doctors under time pressure during an emergency.

Given below are my contributions to the project.

* **Code contributed**:
    * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=riyamehta2211&breakdown=true)


-------

### New Features

1. **New Feature**: Added Redo and Undo commands to convert to 
    the previous or the next state of the patient AddressBook
    ([#167](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/167))

   1. **What Undo does:** Allows commands which modify state of the AddressBook to be undone
   
   2. **Justification:** Users may mistakenly enter commands which modify the state of the AddressBook. 
   The Undo feature gives the facility of undoing the state of the AddressBook 
   by reversing the commands performed.
   
   3. **Additional Details:**
      * **What Redo does:** Acts as a complement for the Undo feature
      * **Justification:** Users may accidentally undo a significant change done to 
      the AddressBook. Redo feature acts as a complement and helps redo a previously 
      undone modification to the AddressBook. 
      * Added comprehensive Testing for Undo and Redo Command Classes
      ([#224](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/224))

   4. **Highlights:** Modified execution of the different Commands to commit 
   to the AddressBook history. This way, the Undo and Redo functions
   are implemented to inherently modify the AddressBook state itself,
   rather than individual Commands.

2. **New Attributes:** Added Birthday and IcNumber Attributes
   ([#89](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/89))
   * **Justification:** Birthdates and IC Numbers are compulsory fields of information 
   which have to be stored in the records of the patients.
   * **Highlight:** Used LocalDateTime to accept valid birthdates of patients for the logging of 
    patients into the AddressBook. Valid user inputs would be automatically formatted into valid dates.

3. **New Feature**: Added AssignedDepartment into Storage
   ([#107](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/107))
   * **Justification:** This is to ensure that the department the patient has been assigned to is also saved
   in Storage and available for access when A&E is closed and reopened.
   

-------

### Enhancements to existing features

1. **Enhancements to existing features**: Enhanced Delete and Edit features
   * **Explanation:** Delete and Edit features now access patient list using IC Number instead of list index
   * **Highlight:** More user-friendly as users can now delete and edit patients using their
   IC Number instead of the list index. Especially useful in extremely large record systems 
   where it can be troublesome to locate the exact index of the patient. It is more convenient to search 
   and delete or edit the patient using the IC Number.
   * **Enhanced Edit Feature:** ([#145](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/145))
   * **Enhanced Delete Feature:** ([#120](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/120))


-------

### Contributions to Documentation
1. **Contributions to the UG**: ([#247](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/247))
   + Added Documentation for the features `edit`, `delete`, `undo` and `redo`
   + Proofread and formatted sections on `Saving the program`, `Editing the data file`, and `Glossary`

2. **Contributions to the DG**: ([#250](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/250))
   + Added implementation details of `edit` and `delete`
   + Extended on the implementation description of `undo`&`redo`
   + Added on to the User Stories and Use Cases
   + Added on to the planned enhancements section


-------

### Contributions to team-based tasks

* **Contributions to team-based tasks**:
    * Maintaining the issue tracker
    * Updating user/developer docs
    * Helped proofread and format the final version of the Developer Guide and User Guide


-------

### Review/Mentoring contributions

* **Review/mentoring contributions**:
  * Reviewed and merged various PRs made by team members
    * Here are some of the PRs I have reviewed (with non-trivial review comments):
      ([#240](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/240)),
      ([#102](https://github.com/AY2324S1-CS2103T-T14-2/tp/pull/102))