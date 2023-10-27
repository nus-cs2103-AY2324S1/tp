---
layout: page
title: User Guide
---

Flashlingo is a **desktop app for learning words by flashcard, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Flashlingo is designed with beginner language learners in mind, specialising in helping them expand their vocabulary.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `flashlingo.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-4/tp/releases) (Not finished yet).

1. Copy the file to the folder you want to use as the _home folder_ for your Flashlingo.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar flashlingo.jar` command to run the application.
   Below shows the steps to perform such a task
   1. Open up the terminal. For mac users, open up `terminal` through the finder. For windows users, search `cmd` and run it.
  <img width="674" alt="Screenshot 2023-10-27 at 2 36 16 PM" src="https://github.com/itsNatTan/tp/assets/64185574/039d9f37-e45e-410f-b819-117ff312e13b">

   3. Navigate to the folder containing the jar file. In this example, it is in the Downloads folder.
<img width="219" alt="Screenshot 2023-10-27 at 2 41 08 PM" src="https://github.com/itsNatTan/tp/assets/64185574/6f723fd8-0f43-456a-b566-9073334831e3">

   4. Simply type in java -jar flashlingo.jar to get started!
<img width="361" alt="Screenshot 2023-10-27 at 2 39 31 PM" src="https://github.com/itsNatTan/tp/assets/64185574/e65a1562-9af9-4290-b5e0-b5a53e4d52c8">
   <br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/MainUi.png)

### Using the application

1. Type the command in the command box and press Enter to execute it.
   <img width="728" alt="Screenshot 2023-10-27 at 3 14 07 PM 1" src="https://github.com/itsNatTan/tp/assets/64185574/a4a8dbd2-94ff-417d-84a4-175bf85ff100">
   
2. The image below shows the result of typing in a command. A log message will be displayed below the command box to give information about the outcome of the command.
   <img width="729" alt="Screenshot 2023-10-27 at 3 11 50 PM" src="https://github.com/itsNatTan/tp/assets/64185574/48b74efc-2a39-48d5-b0aa-70f165484c5e">

   * `delete index` : Deletes a words and its related information at the given index.
   * `edit index w/WORD t/TRANSALTION` : Edits the word and its translation at the given index.
   * `find KEYWORD` : Finds words whose original word or translation contains the given keyword.
   * `start` : Starts today’s flashcard session.
   * `end` :  Ends the current flashcard review session.
   * `reveal` : Shows the other side of the flashcard.
   * `yes` :  Indicates user has memorized the word.
   * `no` :  Indicates user has forgotten the word.
   * `stats` : Displays learning statistics for current user.
   * `language` : Filters the list with specified language.
   * `review` : Returns a list of words that the user should revise today.
   * `switch` : Switches between light/dark color theme.
   * `exit` : Terminates the program.

2. Refer to the [Commands](#commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `delete <INDEX>`, index is a parameter which needs to be added to the command, without the <>. `delete 1` is an example of the usage.

* Words in square brackets, ie. `[<UPPER_CASE>]` indicate that the parameter is optional and can be omitted if deemed unnecessary
  e.g. in `add w/<WORD> t/<TRANSLATION> [wl/WORD_LANGUAGE] [tl/TRANSLATION_LANGUAGE]`, the `WORD` and `TRANSLATION` parameters are *MANDATORY*, whereas the `WORD_LANGUAGE` and `TRANSLATION_LANGUAGE` do not need to be in the command.
  For example, both `add w/entschuldigung wl/Deutsch t/sorry tl/English` and `add w/regarder t/look` are valid usage of the command

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Opens a browser with the help page (User Guide).

Format: `help`

### Listing all flashcards : `list`

Shows the list of flashcards with both the original word and the corresponding translation.

Format: `list`

Output:
* `Listed all flashcards`
`1. ORIGINAL_WORD - TRANSLATION`
  `2. ORIGINAL_WORD - TRANSLATION`
  `3. ORIGINAL_WORD - TRANSLATION`
  `...`


### Adding a flashcard: `add`

Adds a word to the flashcard with its translation.
* Creates a wild flashcard.
* Works to add a word with its translation in their respective languages.
* The already saved translation can be overridden with a new translation in a different language.

Format: `add w/<WORD> t/<TRANSLATION> [wl/<WORD_LANGUAGE>] [tl/<TRANSLATION_LANGUAGE>]`

Examples:
* `add w/regarder t/look` saves the translation of regarder as look
* `add w/entschuldigung wl/Deutsch t/sorry tl/English` saves the translation of the Deutsch word entschuldigung as an English word sorry


### Deleting a flashcard : `delete`

Deletes a words and its related information
* Deletes a flashcard.

Format: `delete <INDEX>`

Examples:
* `delete 1` deletes the word and its translation at index 1

### Editing a flashcard : `edit`

Edits the word and its translation at the given index.
* Edits a wild flashcard.

Format: `edit <INDEX> [w/<WORD>] [t/<TRANSLATION>]`

Examples:
* `edit 1 w/Bye t/再见` edits the word and its translation at index 1

### Finding a flashcard : `find`

Finds words whose original word or translation contains the given keyword.
* Finds a flashcard.
* The search is insensitive. e.g `look` will match `Look`

Format: `find <KEYWORD>`

Examples:
* `find look` returns the flashcard list  and its translation that contains the keyword `look`


###  Starts review session : `start`

To start a review session, user simply needs to type in `start` command.

Format: `start`

Output: `Review Session has been started.`

**Note** 
* Users are not allowed to start a new review session if they are already in one. In this case,   
`Sorry, currently you are in a review session. Your command is not supported.`   
`Please end the review session first.` will be prompted.
* If there are no words to review, users will not be able to start review session. `There's no FlashCards to review. Well done!`  
will be displayed.


### Ending the current review session : `end`

Ends the current flashcard session and returns to the main menu.

Format: `end`

Output: `Review Session has ended.`

**Note** 
* Users are not allowed to end a review session if the session hasn't been started yet. The message of `You are not in a review session.`  
will be given.

### Revealing translation of the flashcard : `reveal`

To show the translation of the flashcard in 

Format: `reveal`

Output : `The translation is [CURRENT FLASHCARD'S TRANSLATION]`  

Examples:![img.png](images/Reveal.png)

**Note**
* `reveal` command will only take effect during review session. Otherwise, error message `You are not in a review session.`  
will be printed out.
* Pressing `reveal` button will have the same effect, and users can reveal the translation without the constrain of review session.

### Indicating user has memorized the word : `yes`

Marks the word as memorized and advances the word into the next retention stage. If there are still remaining words to review,
they will be automatically shown in the section below. Otherwise, review session will be closed by default.

Format: `yes`

Output: ![img.png](images/Yes.png)
or
![img.png](images/Yes2.png)
if there's no word left in the review session.

**Note**
* `yes` command will only take effect during review session. Otherwise, error message `You are not in a review session.`  
  will be printed out.
* Pressing `yes` button will have the same effect. 

###  Indicating user has forgotten the word : `no`

Marks the word as not grasped and leaves it in its current retention stage. If there are still remaining words to review,
they will be automatically shown in the section below. Otherwise, review session will be closed by default.

Format: `no`

Output: ![img.png](images/No.png)
or
![img.png](images/No2.png)
if there's no word left in the review session.
**Note**
* `no` command will only take effect during review session. Otherwise, error message`You are not in a review session.`  
  will be printed out.
* Pressing `no` button will have the same effect.

### Show learning statistics : `stats`

Displays learning statistics, i.e, the total number of flashcards and the number of words remembered.

Format: `stats`



### Filtering list with specified language : `language`

Displays a list where each word is from specified language.

Format: `language <SPECIFIED_LANGUAGE>`

### Getting list for revision : `review`

* `review` : Returns a list of words that the user should revise today.

Format: `review`

### Switching color theme : `switch`
* `switch` : Switches between light and dark appearance for UI.

Format: `switch`

Output:![img.png](images/DarkTheme.png)

### Exiting the program : `exit`

Closes the GUI and terminates the Java program

Format: `exit`



### Saving the data

Flashlingo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Flashlingo data are saved automatically as a JSON file `[JAR file location]/data/flashlingo.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Flashlingo will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Flashlingo home folder.
... (to be completed)

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. ... (to be completed)

--------------------------------------------------------------------------------------------------------------------

## Command summary

 Action                 | Format, Examples                                                
------------------------|-----------------------------------------------------------------
 **Help**               | `help`  
 **List**               | `list`
 **Add**                | `add w/WORD t/TRANSLATION` <br> e.g., `add w/regarder t/look`
 **Delete**             | `delete w/WORD`<br> e.g., `del w/look`
 **Edit**               | `edit index w/WORD t/TRANSLATION`<br> e.g., `edit 1 w/bye t/再见`
 **Find**               | `find KEYWORD`<br> e.g., `find bye`
 **Start**              | `start`
 **Reveal**             | `reveal`
 **Yes**                | `yes`
 **No**                 | `no` 
 **End**               | `end`
 **Language**           | `language SPECIFIED_LANGUAGE`<br> e.g., `language French`
 **Review**             | `review`
 **Learning Statistics** | `stats`
 **Switch**              | `switch`
 **Exit**                | `exit`
