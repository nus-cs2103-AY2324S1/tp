---
layout: page\
title: Winson's Project Portfolio Page
---

### Project: lesSON

lesSON is a desktop flashcard application for Computing students in University who struggle with
memorisation and consolidation of knowledge. The user interacts with it using a CLI, and it has a
GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.


* **New Feature**: Practise
  * What it does: Allows user to look at the question of a specific card  using its index, and indicate its difficulity level
  * Justification: It is the main practical function of a flashcard application for user to learn
  * Highlights: Practise can change the current difficulty level of a card to be shown.
  * Credits: Winson8222

* **New Feature** MarkDown Syntax
  * What it does: For advanced user who wish to stylised their flashcard or are visual learners, having ways to differentiate
  words by having different style can greatly enhance their learning process.
  * Justification: It serves as a good supplementary function to enhance to current revision process for user.
  * Highlights: Based on the syntax entered by the user, the corresponding styling will be reflected on 
  the display card and the result view
  * Credits: Winson8222
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=winson8222&breakdown=true)

* **Project management**:


* **Enhancements to existing features**:
  * Card Class is changed to include difficulty attribute
  * Deck Class is created to contain a list of Cards
  * PractiseParser is created to parse practise commands
  * PractiseCommand is created handle practise commands
  * DisplayResult can now support MarkDown syntax: bold, italic and underline.
  * CardDisplay can now support MarkDown syntax: bold, italic and underline.

* **Documentation**:
  * User Guide:
    * Added documentations for the features `edit` and `add`.
    * Added documentations for markdown support, including screenshots of the expected 
    output based on MarkDown syntax added in the command

* **Community**:


* **Tools**:
  * Integrated a third party module flexmark for display of MarkDown styles in the UI




