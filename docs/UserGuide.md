---
layout: page
title: User Guide
---

Flashlingo is a **desktop app for learning words by flashcard, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Flashlingo can help you to schedule your language learning plan.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `flashlingo.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-4/tp/releases) (Not finished yet).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar flashlingo.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/MainUi.png)

1. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:

   * `help` : Shows the User Guide.

   * `list` : Shows the list of cards.

   * `add w/WORD wl/WORD LANGUAGE t/TRANSLATION tl/TRANSLATION LANGUAGE` :Adds a word to the flashcard with its translation.

   * `del index` : Deletes a words and its related information at the given index.

   * `edit index w/WORD t/TRANSALTION` : Edits the word and its translation at the given index.

   * `find KEYWORD` : Finds words whose original word or translation contains the given keyword.

   * `start` : Shows flashcard, starts today’s flashcard session.

   * `flip` : Shows the other side of the flash card.

   * `yes` :  Indicates user has memorized the word.

   * `no` :  Indicates user has forgotten the word.

   * `stats` : Displays learning statistics for current user.

   * `stop` :  Stops the current flashcard session.

   * `exit` : Terminates the program.

2. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add w/WORD t/TRANSLATION`, `WORD` and `TRANSLATION` are a parameter which can be used as `add w/regarder t/look`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Opens a browser with the help page (User Guide).

Format: `help`

### Listing all cards : `list`

Shows the list of cards with both the original word and the corresponding translation.

Format: `list`

Output:
* `There are no words in the list! Add your flashcards now!`
* `Here are the words:`
  `1. ORIGINAL_WORD - TRANSLATION`
  `2. ORIGINAL_WORD - TRANSLATION`
  `3. ORIGINAL_WORD - TRANSLATION`
  `...`

![img.png](images/ListUi.png)

### Adding a word card: `add`

Adds a word to the flashcard with its translation.
* Creates a wild card.
* Works to add a word with its translation in their respective languages.
* The already saved translation can be overridden with a new translation in a different language.

Format: `add w/WORD wl/WORD LANGUAGE t/TRANSLATION tl/TRANSLATION LANGUAGE`

Examples:
* `add w/regarder wl/French t/look tl/English` saves the translation of regarder as look
* `add w/entschuldigung wl/Deutsch t/sorry tl/English` saves the translation of entschuldigung as sorry


### Deleting a word card : `delete`

Deletes a words and its related information
* Deletes a flashcard.

<<<<<<< HEAD
Format: `deletle index`

Examples:
* `delete 1` deletes the word and its translation at index 1

### Editing a word card : `edit`

Edits the word and its translation at the given index.
* Edits a wild card.

Format: `edit index w/WORD t/TRANSLATION`

Examples:
* `edit 1 w/Bye t/再见` edits the word and its translation at index 1

### Finding a word card : `find`

Finds words whose original word or translation contains the given keyword.
* Finds a flashcard.
* The search is insensitive. e.g `look` will match `Look`

Format: `find KEYWORD`

Examples:
* `find look` returns the flashcard list  and its translation that contains the keyword `look`


###  Showing flashcard, starts today’s flashcard session : `start`

Shows the words the user is going to study.

Format: `start`

Output:
![img.png](images/StartUi.png)


### Showsing the other side of the flash card : `flip`

Shows the meaning of the word.

Format: `flip`/`f`

Output :
![img.png](images/StartUi.png)

### Indicating user has memorized the word : `yes`

Marks the word as memorized and pushes the word into the next retention stage.

Format: `yes`


###  Indicating user has forgotten the word : `no`

Marks the word as not grasped and leaves it in its current retention stage.

Format: `no`

### Show learning statistics : `stats`

Displays learning statistics, i.e, the total number of flashcards and the number of words remembered.

Format: `stats`

### Stopping the current flashcard session : `stop`

Stops the current flashcard session and returns to the main menu.

Format: `stop`

Output: `Congratulations! You have completed FINISHED_WORDS out of TOTAL_WORDS words!`

### Exiting the program : `exit`

Closes the GUI and terminates the Java program

Format: `exit`



### Saving the data

Flashlingo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Flashlingo data are saved automatically as a JSON file `[JAR file location]/data/flashlingo.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
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

Action | Format, Examples
--------|------------------
**Help** | `help`
**List** | `list`
**Add** | `add w/WORD t/TRANSLATION` <br> e.g., `add w/regarder t/look`
**Delete** | `delete w/WORD`<br> e.g., `del w/look`
**Edit** | `edit index w/WORD t/TRANSLATION`<br> e.g., `edit 1 w/bye t/再见`
**Find** | `find KEYWORD`<br> e.g., `find bye`
**Start** | `start`
**Flip** | `flip`/`f`
**Yes** | `yes`
**No** | `no`
**Learning Statistics** | `stats`
**Stop** | `stop`
**Exit** | `exit`
