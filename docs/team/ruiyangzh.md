---
  layout: default.md
  title: "Zhao Ruiyang's Project Portfolio Page"
---

### Project: RealtorTrackerPlusMax

RealtorTrackerPlusMax is a desktop application made for usage by real estate agents to keep track of their
contacts, such as buyers and sellers.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java,
and has about 10 kLoC.

Given below are my contributions to this project.

* **New Feature**: Implemented the CommandWarnings feature to provide two tiers of strictness in regard to fields [\#127](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/127)
    * What it does: Allows warnings to be passed through execution, for inputs that are not entirely valid 
  but still can be carried out.
    * Justification: Exceptions halt execution, unless you use unwieldy try/catch blocks, and also force the app to switch to
  kernel mode to handle them. By using a class that contains warnings, we allow execution to carry on as per normal and return the
  invalid input into the `logger` and `CommandResult`. The new class also allows us to return multiple warnings. If you were using exceptions,
  the command execution would halt at the first one thrown, not giving you any information about any other mistakes made.
    * Highlights: Required changing the method signature of many commands, decisions had to be made regarding whether all
  commands should have a CommandWarnings or only certain ones (we decided that only implementations of parser needed a 
  CommandWarnings, since other commands fail only due to system error; there is no ambiguity in user input.)
  We also needed to add a lesser check, which we had the method name of isAppropriate```Field``` as a convention (in 
  contrast to the previous isValid```Field```).


* **New Feature**: Implemented a fuzzy name check to allow for a lesser equality check [\#153](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/153)
    * What it does: When provided two displayables, checks if their names are similar and produces a boolean which indicates this.
  (Similarity is defined in appendix C of the user guide.)
    * Justification: Provides a tiered equality check system, to reduce the chance that users will create two of the same/similar
  clients unintentionally. Imagine the case where a user creates two buyers `John` and `John Doe`. This may or may not
  be the same user. It would be inappropriate to prevent them from entering these two buyers together, since it is entirely
  possible that they are different people. However, if they were, the previous system would not indicate the user of
  this potential issue. Hence, in conjunction with the CommandWarnings class detailed above, we use this lesser check to
  warn the user in case this was unintended.
    * Highlights: Some difficulty in the initial process in how to fuzzily match names; stumbled upon the website below and used
  as reference to build the method and integrate it into commands.
    * Credits: https://rosettacode.org/wiki/Levenshtein_distance#Java for implementation of Levenshtein distance.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=ruiyangzh&tabRepo=AY2324S1-CS2103T-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
<br /><br /><br />
* **Project management**:
  * Team lead for Week 10 (for v1.3)
     * Created, assigned and tagged issues for the milestone 
     * Arranged v1.2 postmortem to discuss issues and how to improve for later iterations
  * Wrote test cases to boost code coverage back to AB3 levels after restructuring e.g.
  ([\#206](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/206))

* **Enhancements to existing features**:
  * Restructured the system to allow for multiple types of displayable objects, instead of only allowing a single list
  of person objects. ([\#47](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/47), 
  [\#48](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/48), 
  [\#51](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/51)) All Displayable objects have names and a display method
  (hence the name.) Buyers and sellers both 
  inherit from an abstract Person, 
  which implements Displayable. In turn changes were also made to JsonAdaptedPerson and
  UniquePersonList(Now UniqueDisplayableList<T extends Displayable>). 
  Also made changes to fxml cards and UI to properly
  display the new elements.
  

* **Documentation**:
    * User Guide:
      * Wrote the table in appendix B on field formats and restrictions.
      * Added appendix A on the warning system.
      (As putting it in the main body would be repetitive and may overload users.)
      * Added appendix C on how similar names are detected.
    * Developer Guide:
      * Wrote up the implementation details for the warnings feature, with a PUML sequence diagrams
      * Added some planned enhancements.
      * Rewrote the Design section and its PUML diagrams to adhere to the new architecture.


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#200](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/200), [\#131](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/131), [\#119](https://github.com/AY2324S1-CS2103T-F11-3/tp/pull/119)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/350), [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/102), [3](https://github.com/nus-cs2103-AY2324S1/forum/issues/317))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/ruiyangzh/ped/issues/2), [2](https://github.com/ruiyangzh/ped/issues/9), [3](https://github.com/ruiyangzh/ped/issues/5))
<br />
