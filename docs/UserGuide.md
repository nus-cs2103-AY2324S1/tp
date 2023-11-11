---
layout: default
title: User Guide
pageNav: 3
---

# User Guide for <span style="color: green;">lesSON</span>

## Table of Contents
1. [Introduction](#introduction)
2. [How to use the guide](#how-to-use-this-guide)
3. [Glossary](#glossary)
4. [Quick start](#quick-start)
5. [Feature list](#feature-list)
   - [Adding a FlashCard](#adding-a-flashcard-add)
   - [Deleting a Flashcard](#deleting-a-flashcard--delete)
   - [View All Flashcards](#view-all-flashcards--list)
   - [Editing a Specific Flashcard](#editing-a-specific-flashcard--edit)
   - [Practise Flashcards](#practise-flashcards-practise)
   - [Hints for Flashcards](#hints-for-flashcards-hint)
   - [Solve Flashcards](#solving-flashcards-solve)
   - [Setting Difficulty for Flashcards](#setting-difficulty-of-flashcards-set)
   - [Randomly Practise Flashcards]()
   - [Clear all Flashcards](#clear-all-flashcards-clear)
   - [Setting goals](#setting-goals-goal)
   - [Getting help](#getting-help-help)
6. [Additional Features](#additional-features)
   - [Markdown Syntax](#markdown-syntax)
   - [Importing and Exporting Decks](#importing-and-exporting-decks)
7. [FAQ](#frequently-asked-questions)
8. [Known Issues](#known-issues)
9. [Contact Us](#contact-us)

--------------------------------------------------------------------------------------------------------------------

## Introduction

#### ***Revolutionise your studying experience with <span style="color: green;">lesSON</span>!***

Welcome to the user guide for <span style="color: green;">lesSON</span>, your ultimate companion in mastering the
Computer Science curriculum at the National University of Singapore (NUS). This guide is designed to provide you with a
comprehensive understanding of the app and how to leverage its powerful features for your academic success.

#### What is lesSON?

<span style="color: green;">lesSON</span> is not just another study app; it's a game-changer in the world of education.
Tailored specifically for NUS Computer Science students, this **desktop application** enables incredibly easy creation
of flashcards for revision. It employs the efficacy of [active recall](#definitions) when you practise with the
[flashcards](#definitions) you made to increase memory retention. Say goodbye to time-consuming note-taking,
and say hello to a more efficient, effective, and enjoyable study experience.

Made with the intention to maximise your study experience, here are some features we believe you'll love:
1. **Enhanced Memory Retention:** Thanks to lesSON's inbuilt [spaced repetition](#definitions) system, you can
supercharge your memory and recall the important details needed. The app optimizes when you should review your
flashcards, ensuring you remember and grasp complex concepts with ease.
2. **Intuitive Card Categorisation:** lesSON helps you categorize your flashcards based on subject. This feature
makes it a breeze to organize and access specific topics, keeping your study sessions focused and productive.

*... and many more!*

#### Prerequisites

Before you get started with <span style="color: green;">lesSON</span>, here's what we recommend you need to make
full use of the app:

- **Comfortable with English**: Since <span style="color: green;">lesSON</span> is an English-based app, a basic
understanding of the language will ensure a smooth and enjoyable experience.
- **Typing Proficiency**: <span style="color: green;">lesSON</span> primarily uses a
[Command Line Interface (CLI)](#definitions) on top of a [Graphic User Interface (GUI)](#definitions). This means that
most of the interaction with <span style="color: green;">lesSON</span> is through the use of [keystrokes](#definitions).
Being comfortable and efficient at typing will help you make the most of the app and enhance your user experience.

[Go to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## How to use this guide?

### Directory
If you are a first time user, do visit our seamless onboarding process explained in the
[quick start](#quick-start) section. <br>
<br>
For beginners, we recommend familiarising yourself with the basic commands shown in our [tutorial](#tutorial)
for new users. <br>
<br>
If you are already comfortable using <span style="color: green;">lesSON</span>, do try out all of our other [features](#feature-list). <br> 
For a summary of available commands, visit [here]().
>>>>>>> master

### Legend
Throughout this user guide, you will encounter some of these colored blocks highlighting some important information.

<div markdown="block" class="alert alert-success">
    💡 Green blocks contain tips to enhance your experience using lesSON.
</div>

<div markdown="block" class="alert alert-info">
    ℹ️ Blue blocks contain useful information to address doubts you might have.
</div>

<div markdown="span" class="alert alert-danger">
    ⚠️ Red blocks contain warnings that you must heed so that lesSON works as intended.
</div>

<span style="color: red;">This is red text</span>

[Go to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Glossary

### Definitions
|             Term             | Definition                                                                                                                                                                                     |
|:----------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|        Active recall         | A popular method to strengthen memory retention by actively testing the memory to retrieve a piece of information.                                                                             |
| Command Line Interface (CLI) | A text-based interface where you interact with the software by typing commands into a terminal or command prompt, providing more direct and precise control over the system.                   |
|          Flashcard           | A card created by the user containing a question, answer (not shown) and due date.                                                                                                             |
| Graphic User Interface (GUI) | A user-friendly interface that allows you to interact with the software through visual elements like windows, icons, buttons, and menus, making it easy to navigate with a mouse and keyboard. |
|          Keystrokes          | Act of pressing a key on a computer keyboard                                                                                                                                                   |
|      Spaced repetition       | An evidence-based learning technique where newly created and more difficult flashcards are shown more frequently than older and less difficult flashcards                                      |

[Go to Table of Contents](#table-of-contents)
>>>>>>> master

--------------------------------------------------------------------------------------------------------------------

## Quick Start

Get ready to embark on your productive study session with <span style="color: green;">lesSON</span>!

### Installation

1. Make sure that you have [Java 11 or above](https://www.java.com/en/download/) installed on your computer.
   - [How do I check my version of Java installed?](#check-version-of-java-installed)
   - For macOS users, follow the instructions listed [here](https://nus-cs2103-ay2324s1.github.io/website/admin/programmingLanguages.html) instead to install Java.
2. Download the latest `lesSON.jar` file from [here](https://github.com/AY2324S1-CS2103T-W17-4/tp/releases/tag/v1.3(trial)).
3. Move the jar file to a working folder where you would store all of your flashcards.
4. Launch lesSON.
   - For Windows users:
      - Open the folder where `lesSON.jar` was moved to.
      - Double-click `lesSON.jar` to start the application.
   - For macOS users,
     - Open a new Terminal window<br>
     Press <kbd>Command</kbd> + <kbd>Space</kbd> and type Terminal, then press <kbd>Enter</kbd>.
     - Navigate to the folder where you have stored the jar file using the `cd` command.
       - [Not sure how to use cd?](https://www.ibm.com/docs/en/aix/7.2?topic=directories-changing-another-directory-cd-command)
     - Type `java -jar lesSON.jar` and press <kbd>Enter</kbd>.
5. You should be able to see the GUI pop up here.
   - If you are unable to see the GUI or are stuck anywhere in the installation process, follow a more detailed set of
   instructions [here]().

### Navigating the app

<span style="color: green;">lesSON</span> uses a [Graphical User Interface (GUI)](#glossary) to display the flashcards
you have created to you. Here is a brief overview of the GUI to help you familiarise yourself with the key elements and
their locations.

![gui_overview](./images/UserGuide/1.3b_ui_overview.png)

Here is a summary of what each component in the GUI displays.

| Name of component | Description                                                                                                                                                |
|:-----------------:|------------------------------------------------------------------------------------------------------------------------------------------------------------|
|    Menu button    | Lists the Help, Import, Export and Exit buttons when pressed.                                                                                              |
|       Goal        | Displays the number of flashcards to practise for the session.                                                                                             |
| Command Line Box  | Interface for users to input commands.                                                                                                                     |
|    Result Box     | Displays the results of inputted command back to the user. Error messages will be shown to guide the user to the correct input if the input was incorrect. |
|       Deck        | Lists all the flashcards in a scrollable view. The deck can be filtered using commands.                                                                    |
|       Card        | Displays the key information of a flashcard such as its question, tags and index.                                                                          |
|      Footer       | Displays where the storage of flashcards is located at.                                                                                                    |

### Tutorial

This is a tutorial for **first-time** and **beginner** <span style="color: green;">lesSON</span> users. For more advanced users,
feel free to explore the other features in the [feature list](#feature-list) instead. In this tutorial, we will explain how to
*add, edit, tag, and practise* your flashcards.

1. Launch <span style="color: green;">lesSON</span>.
   - Refer to the instructions [here](#installation) if you are unsure how to launch
   <span style="color: green;">lesSON</span>.
   - Note that users who are launching <span style="color: green;">lesSON</span> for the first time will have an
   empty deck.
2. First, let's try to add your very first flashcard. Type the following command and hit <kbd>Enter</kbd>: <br>
`add q/what does + mean in boolean algebra? a/it means OR.`
3. Congratulations, you have created your first flashcard! Now, let's try to edit the card created from the
previous input by capitalising it! Note that the flashcard just created is at index `1`. Type the following command
and hit <kbd>Enter</kbd>: <br>`edit 1 q/What does + mean in boolean algebra? a/It means OR.`
4. Next, let's tag it under the module CS1231S. Type the following command and hit <kbd>Enter</kbd>:
<br>`edit 1 t/CS1231S`
5. Let's add another flashcard. Type the following command and hit <kbd>Enter</kbd>: <br>
`add q/What are the different hazards to consider when using pipelining? a/Structural, Data and Control t/CS2100`
6. Feel free to add a few more cards by following steps 1 to 4!
7. Next, to practise the first card, do: <br> `practise`
8. Try to recall the answer, and when you are ready to reveal the answer, type the following command and hit
<kbd>Enter</kbd>: <br>`solve`
9. How hard was the answer to recall? Set the difficulty of the flashcard by typing any of the following command and hit
<kbd>Enter</kbd>: <br>
`set d/easy`<br>
`set d/medium`<br>
`set d/hard`
10. The flashcard will automatically be reinserted into deck. This is based on how difficult you found the question
to be. With our inbuilt [spaced repetition](#glossary) system, as you practise, flashcards you find more difficult will
surface more at the top.
11. Note that the `practise`, `solve`, and `set` commands support indexes, but we recommended to use them without
indexes as shown in this tutorial. This is to optimise the effectiveness of the [spaced repetition](#glossary) by
testing your memory with the most difficult flashcard.
12. Get familiar with these commands and when you are ready, move on to the other commands listed [here]().

[Go to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Feature list

### Adding a Flashcard `add`

Adds a flashcard to the deck for the user.

**Format:**

`add q/question a/answer [t/TAG] [h/HINT]`

**Examples:**

_A flashcard with only a question and answer field._

`add q/What are the three ways to implement binary systems? a/1s Complement, 2s Complement, and Sign and Magnitude`

_A flashcard with a question, answer and tag field._

`add q/How do you convert from binary to 1s Complement? a/By inverting all the bits, i.e. 0 to 1 and vice versa t/CS2100 t/Number Systems`

_A flashcard with a question, answer, tag and hint field._
`add q/What are the 5 stages of MIPS? a/Fetch, Decode, Execute, Memory, Write Back t/CS2100 h/5 stages: IF, ID, EX, MEM, WB`

#### To Note:
1. No empty input or input with only whitespace after `q/`, `a/`, `t/` and `h/`.
2. `t/` and `h/` is optional and not necessary.
3. Inputs are case-sensitive (cards with the same input but different case will be recognised as different cards).
4. Prefixes (such as `q/`, `a/`, `t/`, `h/`) are not allow in the input fields.
5. Tagging is not supported in v1.2 and earlier.
6. Hint is not supported before v1.3.

#### Expected outputs:

1. Given a correct input, a success message will be shown containing the user's input.
   1. `“New Card added: Question: (question); Answer: (answer)“`
2. Given an incorrect input, an error message will be shown, detailing how the error can be fixed.
   1. ```
      Answers should only contain alphanumeric characters, some special characters and spaces, and it should not be blank
      ```
   2. ```
       Invalid command format!
       add: Adds a card to the deck. Parameters: q/QUESTION a/ANSWER
      ```
#### Usage
1. User Input: `add q/opcode for R format instructions a/000000 t/CS2100 t/MIPS`

2. Successful Output

![result of add command](./images/UserGuide/1.3b_add.png)


### Deleting a Flashcard : `delete`
Deletes a flashcard in the deck

**Format:**

`delete INDEX`

**Examples:**

_Deleting the card in th deck with an index of 2._

`delete 2`

#### To Note:
1. Index must be positive integer.
2. Index cannot exceed size of the deck.

#### Expected outputs:
1. Given a correct input, a success message will be shown containing the details of the deleted flashcard.
   1. `Deleted Card: Question: <provided question>; Answer: <provided answer>`
2. Given an incorrect input, an error message will be shown, detailing how the error can be fixed.
   1. `The card index provided is invalid`
   2. ```
      Invalid command format!
      delete: Deletes the deck identified by the index number used in the displayed card list.
      Parameters: INDEX (must be a positive integer)
      Example: delete 1
      ```

#### Usage:
1. User Input: `delete 1`

2. Successful Output

![result of delete command](./images/UserGuide/1.3b_del.png)

### View All Flashcards : `list`
Shows a list of all flashcards in the deck. A keyword may be specified to filter out the list.

Format: `list (q/t)/(prefix question starts with/ tag)`

Note : Listing questions for markdown syntax should include their relevant markdown notation
Examples:
```
1. list
   (list shows the full list of flashcards)

2. list q/what
   (list shows all flashcards with questions starting with 'What')

3. list t/CS2100
   (list shows all flashcards with the CS2100 Tag)

4. list t/CS2100 t/MIPS
   (list shows all flashcards with both MIPS and CS2100 Tag)

5. list q/what t/CS2100
   (list shows all flashcards with questions starting with 'What' and has the CS2100 Tag)
```

#### To note:
1. No Empty Input after q/ and t/.
2. q/ and t/ is optional.
3. Inputs are case-sensitive (cards/tags with the same input but different case will be recognised as different cards/tags)

#### Expected output:
```
1. list
   "All cards listed" message will be returned to the user via the CLI
```

#### Usage
![usage of list command](./images/UserGuide/1.3b_list_ans.png)

### Editing a Specific Flashcard : `edit`
Edits an existing Flashcard in the deck.

Format: `edit INDEX (q/a/t/h)/(question/answer/tag/hint)`

Examples:
```
1. edit 1 q/What is the colour of the sun?
   (changes the question at index 1 to “What is the colour of the sun?”)

2. edit 1 a/Red
   (changes the answer at index 1 to “Red”)

3. edit 1 t/Weather t/Geogaphy
   (changes the tag at index 1 to “Weather” and "Geography")

4. edit 1 h/Apple
   (changes the hint at index 1 to “Apple")
```

#### To Note:
1. No empty input or input with only whitespace after `q/`, `a/`
2. Empty input after `t/` and `h/` will remove existing tags or hint respectively.
3. Inputs are case-sensitive (cards with the same input but different case will be recognised as different cards).
4. Prefixes (such as `q/`, `a/`, `t/`, `h/`) are not allow in the input fields.
5. Tagging is not supported in v1.2 and earlier.
6. Hint is not supported before v1.3.

#### Expected output:
```
“Successfully edited flashcard” message will be returned to the user via the CLI
“The card index provided is invalid"
```
#### Usage:
1. User Input
   ![usage of edit command](./images/UserGuide/1.3b_edit.png)

2. Successful Output
   ![result of edit command](./images/UserGuide/1.3b_edit_ans.png)


### Practise Flashcards: `practise`
Practise a single Flashcard in the deck

Format: `practise INDEX`

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck

#### Examples:
```
practise 1
(showcases the question at index 1)
```
#### Expected outputs:
```
practise 1
"Practising question 1 : <provided question>"

practise 10
"The card index provided is invalid"
```

#### Usage:
1. User Input
   ![usage of practise command](./images/UserGuide/1.3b_practise.png)

2. Successful Output
   ![result of practise command](./images/UserGuide/1.3b_practise_ans.png)

### Hints for Flashcards: `hint`
See the hint for a question at the given index

Format: `hint INDEX`

#### Examples:
```
hint 1
```
#### Expected outputs:
```
hint 1
"Hint for Question 1: 5 stages: IF, ID, EX, MEM, WB "

hint 2
"Hint for Question 2: No hint was provided."

hint 10
"The card index provided is invalid"
```

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck


### Solving Flashcards: `solve`
Solves the question at the given index

Format: `solve INDEX`

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck

#### Examples:
```
solve 1
```
#### Expected outputs:
```
solve 1
"Solved Question 1: What pipline protocols are covered
Answer:2"

solve 10
"The card index provided is invalid"
```

#### Usage:
1. User Input
   ![usage of solve command](./images/UserGuide/1.3b_solve.png)

2. Successful Output
   ![result of solve command](./images/UserGuide/1.3b_solve_ans.png)


### Setting Difficulty of Flashcards: `set`
Setting the difficulty of a flashcard

This difficulty refers to how difficult you found the flashcard.
This flashcard will be rescheduled based on the difficulty, as indicated by their due date.
More difficult cards will appear more often in the practice rotation.
This also takes into account past practices, so the more a card is practised,
the less it will appear in the practice rotation.

Format: set INDEX DIFFICULTY

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck
3. Difficulty must be either ‘easy’, ‘medium’, ‘hard’

#### Example:
```
set 1
```

#### Expected outputs:
```
set 1 d/easy
"Set Difficulty for Question 1 (Diffculty Level: easy)

set 10 d/easy
"The card index provided is invalid"
```

#### Usage:
1. User Input
   ![usage of set command](./images/UserGuide/1.3b_set.png)

2. Successful Output
   ![result of set command](./images/UserGuide/1.3b_set_ans.png)

### Randomly Practise Flashcards: `random`
Practise a single Flashcard in the deck. This command chooses a random flashcard,
as opposed to `practise` command which chooses the first (highest priority) flashcard.
Use `r` index with `solve` and `set` for this random card.

Format: `random`

#### Acceptable values for each parameters:
* There are no parameters.

#### Examples:
```
random
(showcases a random question)
```
#### Expected outputs:
```
random
"Practising question 1 : <provided question>"
```
Note: since the output is random, the above can be any question.


### Clear all flashcards: `clear`
Clears all flashcards found in the Deck. Deck is reset back to empty.

Format: clear

#### Example:
```
clear
```

#### Expected outputs:
```
clear
"Deck has been cleared!"
```
### Setting goals: `goal`
Set a goal for the current studying session.
Initially, the goal is set to 0, and out of the number of cards due that day

ie. If you have 5 cards due that day, it will be set to 0/5 initially

Format: goal NUMBER

#### Example:
```
goal 5
```

### Important Note:
Do not stack MarkDown Syntax

Example:
`` edit 1 q/ *** ``

This may lead to unexpected behavior of text in the display view.

>>>>>>> master
### Getting help: `help`
Seek more details from a link provided leading to the User Guide.
Users can also access this function by clicking on the **File** button located at the top
left of the application, and then navigating to the **Help** tab.

Format: help

#### Example:
```
help
```

#### Expected outputs:
```
help
(A window with the URL leading to the User Guide will pop out.
Users can copy the URL by simply clicking on the 'Copy URL' button.)
```

--------------------------------------------------------------------------------------------------------------------

## Additional Features

### MarkDown Syntax

For user who wish to incorporate styling in lesSON, there are 3 font styles currently supported:
1. Bold
2. Italic
3. Underline

#### Bold
To bold a line of text, wrap text with `**`

##### Example:
```
edit 1 q/How many bits can a **Half Adder** add up
```
##### Expected Result:
![usage of bold syntax](./images/UserGuide/1.3b_bold.png)

#### Italic
To italicise a line of text, wrap text with `*`

##### Example:
```
edit 1 q/How many bits can a *Half Adder* add up
```
##### Expected Result:
![usage of bold syntax](./images/UserGuide/1.3b_italics.png)

#### Underline
To underline a line of text, insert `<u>` at the beginning of the text,
and end with `</u>` at the end of the underlined text.

##### Example:
```
edit 1 q/How many bits can a <u>Half Adder</u> add up
```
##### Expected Result:
![usage of bold syntax](./images/UserGuide/1.3b_underline.png)


### Important Note:
Do not stack MarkDown Syntaxes

Example:
`` edit 1 q/ *** ``

This may lead to unexpected behavior of text in the display view. In the case when unexpected MarkDown format is observed, edit the flashcard again with the without the MarkDown syntax/ with appropriate syntaxes.

## Importing and Exporting Decks

<div markdown="block" class="alert alert-info">

**:information_source: Notes about Export and Import**<br>

The import and export functions are meant to be used before and after revision respectively <br>

They are not to be confused as commands input by user, but rather as a method to transfer and import data easily <br>

</div>

Users can share their own Decks with others or download their Decks to their own.
Both import and export features reside in the **File** menu.

### Export
Users can share their own Deck by clicking on the `Export` button. Their own Deck
will be displayed as a JSON file which can be easily copied either through the `Copy Data`
button provided or other means.

### Import
Users can enjoy the Decks of others by clicking on the `Import` button. A text field
is displayed for users to paste in the JSON file containing the decks of others. The
app will close upon clicking the `Import Data` button. Users will have to rerun the app
to see their new decks.

### Caution
All data is saved as a JSON file at this specified path: [JAR file location]/data/deck.json. <br>
It is **NOT** recommended for users to modify data directly as it can cause the app to malfunction. <br>
If you still wish to proceed with modifying the data directly, lesSON would not be held accountable of the risks of such actions.

--------------------------------------------------------------------------------------------------------------------

## Frequently Asked Questions (FAQ)

### Check version of Java installed

**Q:** How do I check my version of Java I have installed on my computer? <br>
**A:** Open up a command prompt and type `java -version` and hit <kbd>Enter</kbd>.

Shortcut to open the command prompt:
- For Windows: Press <kbd>Windows</kbd> + <kbd>R</kbd> and enter `cmd`.
- For macOS: Press <kbd>Command</kbd> + <kbd>Space</kbd> and enter `Terminal`.

### App not showing

**Q:** The application is running fine, but I am unable to see the [GUI](#definitions). What should I do? <br>
**A:** This is a currently [known issue](#known-issues) with a temporary hotfix. We are working on a solution.

[Go to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Known Issues

1. As of v1.4, users can create duplicate tags for the same flashcard. We plan to enforce uniqueness of tags for a
flashcard in future enhancements. Until then, please do take caution to avoid adding duplicate tags for the same
flashcard.
2. As of v1.4, the app may not show for users with multiple displays. If you relocate the app to a secondary display and
subsequently cease using the secondary display, the app will continue to run but will no longer be visible on your
primary display. Closing the app and re-running it will not solve the issue. We are working on a solution but until then,
we recommend relocating the app back to your main display before disconnecting your secondary display.

If you encountered any other bugs or errors, do let us know with the relevant details (e.g. screenshots) via our
official channels found [here](#contact-us).

[Go to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Contact Us

For any feedback or relevant queries, do contact us via our [official channels](https://ay2324s1-cs2103t-w17-4.github.io/tp/AboutUs.html).

[Go to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<p style="text-align: center;">— End of User Guide —</p>
