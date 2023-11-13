---
layout: page
title: MagnificentCreature's Project Portfolio Page
---

### Project: Connectify

Connectify is a desktop app for managing clients. Connectify helps salespersons manage their clients information records and data, boosting their efficiency in building customer relationships! It has GUI created with JavaFX, and is written in OOP fashion with about 6kLoC of additions above [se-edu AB3](https://github.com/se-edu/addressbook-level3).

Given below are my contributions to the project.

* **New Feature**: Added the logic to keep track of past interactions with clients.
  * What it does: allows the user to keep notes of their interactions with clients, when they happened, and a tag for the general outcome of it.
  * Justification: This feature is a core part of what makes a customer relations manager (CRM) apps work! Remembering the past interactions with clients, so that the user can better manage their relationship with them.
  * Highlights: This enhancement requires composition of many classes, and to display them in a list. The implementation required some thorough planning and incremental design approaches as I also had to integrate the feature with the reminder/follow-ups feature. (Pull requests [\#30](), [\#67](), [\#74]())
  * Highlights: The feature was also made with user experience in mind, and the implementation of "no prefixes needed for notes" and "only either outcome or note is needed" without major bugs took careful and defensive coding. ([\#49](), [\#185]())
  * This also required the ability to store the data in a file. It was also a good opportunity to learn about how to store data in a file, and how to read it back. , ([\#94]())
  * Credits: Tester-D for suggesting interaction outcomes to be not case sensitive. ([\#164]())

* **New Feature**: Reminder Scheduler logic and Reminders
  * What it does: Automatically update reminders for follow-ups with clients every day. Reminders are displayed in the Dashboard GUI. 
  * Justification: This feature is another core part of what makes CRMs apps work! Remembering to follow-up with clients is a key part of maintaining a good relationship with them and ensuring you don't miss out by forgetting to contact your clients.
  * Highlights: This enhancement required coordination with my teammate @lilozz2 who was working on reminders in parallel. I became accustomed to a different workflow of pulling from a teammates branch into a local fork and have gotten very accustomed to handling merge conflicts and resolving them. I also learnt how to use schedulers and threads to implement the reminder scheduler.
  * Credits: The implementation of the reminder was first done by @lilozz2 however there were some bugs with the implementation. I fixed the bugs and refactored the code to make it more readable and maintainable. (Pull requests [\#107](), [\#108](), [\#112]())

* **New Feature**: Single field Edit macro
  * What it does: QoL change that allows the user to edit a single field of a client. By entering it as a command word.
  * Justification: This feature is a QoL change that allows the user to edit a single field of a client without having to re-enter all the other fields and remember prefixes. This is especially useful when the user only wants to change a single field of a client.
  * Highlights: This change was accomplished with very little lines of code, rather than having to create a new command class for each field, I was able to reuse the existing edit command class and efficiently parse new command words. (Pull requests [\#126]())

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=totalCommits&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=MagnificentCreature&tabRepo=AY2324S1-CS2103T-F11-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false%23%2F)


Miscellaneous:
Formatted meeting note docs
Prepared v1.2 submission
Compiled bug reports in tP documents

