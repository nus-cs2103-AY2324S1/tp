---
layout: page
title: User Guide
---
# lesSON User Guide
lesSON is a **flashcard software aimed to help individuals with their memory work in school, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, lesSON can get your contact management tasks done faster than traditional GUI apps.

# Table of Contents
1. [Feature List](#feature-list)
    - [Adding a FlashCard](#adding-a-flashcard-add)
    - [Deleting a Flashcard](#deleting-a-flashcard--delete)
    - [View All Flashcards](#view-all-flashcards--list)
    - [Editing a Specific Flashcard](#editing-a-specific-flashcard--edit)
    - [Practise Flashcards](#practise-flashcards-practise)

--------------------------------------------------------------------------------------------------------------------

## Feature List

### Adding a Flashcard `add`
Adds a flashcard to the deck for the user.

Format: `add q/question a/answer [t/TAG]​`

**Tip**: Tagging is not supported in v1.2 and earlier

Examples:

```
- add q/What are the three ways to implement binary systems? a/1s Complement, 2s Complement, and Sign and Magnitude

- add q/How do you convert from binary to 1s Complement? a/By inverting all the bits, i.e. 0 to 1 and vice versa t/CS2100 t/Number Systems
```

#### Acceptable values for each parameters:
1. No Empty Input after q/, a/ and t/.
2. t/ is optional and not neccessary.
#### Expected outputs:
```
1. add q/What are the three ways to implement binary systems? a/1s Complement, 2s Complement, and Sign and Magnitude
   “New Card added: Question: What are the three ways to implement binary systems?; Answer: 1s Complement, 2s Complement, and Sign and Magnitude “

2. add q/What are the three ways to implement binary systems? a/
   Answers should only contain alphanumeric characters, some special characters and spaces, and it should not be blank

3. add a/10111
   Invalid command format! 
   add: Adds a card to the deck. Parameters: q/QUESTION a/ANSWER
```
#### Usage
1. User Input
![usage of add command](./images/UserGuide/add_1.2.png)

2. Successful Output
![result of add command](./images/UserGuide/add_1.2_ans.png)


### Deleting a Flashcard : `delete`
Deletes a flashcard in the deck

Format: `delete INDEX`
Examples:
```
- delete 2
 (deletes the 2nd flashcard in the deck)
```

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck

#### Expected outputs:
```
1. delete 2
   Deleted Card: Question: <provided question>; Answer: <provided answer>

2. delete -100
   Invalid command format! 
   delete: Deletes the deck identified by the index number used in the displayed card list.
   Parameters: INDEX (must be a positive integer)
   Example: delete 1
```
#### Usage:
1. User Input
   ![usage of delete command](./images/UserGuide/delete_1.2.png)

2. Successful Output
   ![result of delete command](./images/UserGuide/delete_1.2_ans.png)

### View All Flashcards : `list`
Shows a list of all flashcards in the deck.

Format: `list`
Examples:
```
(list shows the full list of flashcards.)
```

#### Acceptable values for each parameters:
No parameters are needed

#### Expected output:
```
1. list
   (Questions to all the flashcards that are added with their index)

2. list potato
   “No parameters are allowed for this command.”
```

#### Usage
![usage of list command](./images/UserGuide/list_1.2.png)

### Editing a Specific Flashcard : `edit`
Edits an existing person in the address book.

Format: `edit INDEX (q/a)/ (question/answer)`

Examples:
```
1. edit 1 q/ What is the colour of the sun?
   (changes the question at index 1 to “What is the colour of the sun?”)

2. edit 1 a/ Red
   (changes the answer at index 1 to “Red”)
```
#### Expected output:
```
“Successfully edited flashcard” message will be returned to the user via the CLI
“The card index provided is invalid"
```
#### Usage:
1. User Input
   ![usage of edit command](./images/UserGuide/edit_1.2.png)

2. Successful Output
   ![result of edit command](./images/UserGuide/edit_1.2_ans.png)



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
   ![usage of practise command](./images/UserGuide/practise_1.2.png)

2. Successful Output
   ![result of practise command](./images/UserGuide/practise_1.2_ans.png)

### Practise Flashcards: `solve`
Solves the question at the given index

Format: `solve INDEX d/DIFFICULTY`

#### Acceptable values for each parameters:
1. Index must be positive integer
2. Index cannot exceed size of the deck
3. Difficulty must be either `easy`, `medium`, `hard`.

#### Examples:
```
solve 1 d/ easy
(marks the priority of question to be easy)
```
#### Expected outputs:
```
solve 1 
"Solved Question 1 (Difficulty level: easy)"

solve 10 d/easy
"The card index provided is invalid"
```

#### Usage:
1. User Input
   ![usage of practise command](./images/UserGuide/solve_1.2.png)

2. Successful Output
   ![result of practise command](./images/UserGuide/solve_1.2_ans.png)
