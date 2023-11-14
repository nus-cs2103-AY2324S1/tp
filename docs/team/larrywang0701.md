---
  layout: larrywang0701.md
  title: "Wang Zihan's Project Portfolio Page"
---

# Wang Zihan's Project Portfolio Page

### Project  Overview:
**_KeepInTouch_** is a desktop application designed for job seekers. It can help the job-seekers to manage contacts and events conveniently. Here is my work and contributions to this project:

### Summary of Contributions
  - #### Code Contributed:

    [Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=larrywang0701&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=larrywang0701&tabRepo=AY2324S1-CS2103T-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

  - #### New feature implemented
      - Major parts for the event feature:
          - Commands and features for add, list and delete events [#33](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/33)
            - `add event`, `list events` and `delete event`
          - Filter and sort events [#71](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/71)
          - Relevant test cases for event feature [#33](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/33) [#71](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/71) [#169](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/169)
          - Bug fixes for event feature [#47](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/47) [#85](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/85)

  - #### Enhancements implemented
    - Enhanced the logic of command parser to support secondary command word [#33](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/33)

  - #### Contributions to the UG

      - During drafting stage: Wrote the command formats for most of the commands in the user guide draft.
        - For example: `add contact -n NAME [-p PHONE_NUMBER] [-a ADDRESS] [-e EMAIL]`
        - (in Project File on Google Docs)
      - Add details and tweak the description for `add event`, `list events` and `delete event` commands.
    [#33](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/33/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
    [#71](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/71/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
      - Add glossary part for UG, more hyperlinks for navigation, add more details for transferring data, and various adjustments to UG. [#169](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/169/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
      - Add the line for telling user not to modify the data file directly in FAQ part. [#167](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/167)

  - #### Contributions to the DG

     - Wrote the User Stories part in the DG [#10](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/10)
     - Add details about secondary command parser in DG [#47](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/47/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
     - Add details about implementation on `ListEventCommandParser` and `ListEventCommand` [#206](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/206) [#208](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/208)
     - Add manual test cases for `list events` command [#211](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/211)
     - UML diagrams contributions:
       - UML diagrams that I added:
         - docs/diagrams/event/ListEventsSequenceDiagram.puml [#206](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/206)
         - docs/diagrams/event/ListEventsActivityDiagram.puml [#206](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/206)
         - docs/diagrams/event/DeleteEventSequenceDiagram.puml [#206](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/206)
       - UML diagram that I updated:
         - docs/diagrams/event/AddEventSequenceDiagram.puml [#206](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/206)


  - #### Review/mentoring contributions

     - Pull requests reviewed:
       - [#32](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/32)
       - [#69](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/69)
       - [#163](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/163)
       - [#168](https://github.com/AY2324S1-CS2103T-W16-1/tp/pull/168)
  
  - #### Contributions beyond the project team
    - Reported 6 bugs to other team's product during PE-D
