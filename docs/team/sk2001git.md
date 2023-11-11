---
layout: default.md
title: "Sean Koh's Project Portfolio Page"
---

# Project: JABPRO

## Overview
JABPro aims to solve the problem of HR managers having to sort through tons of job applications.

It makes their life easier by allowing them to easily fetch important info about job applicants such as their contact details, application status etc. It serves as a one-stop addressbook for job applications.

## Summary of Contributions
**Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sk2001git&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=sk2001git&tabRepo=AY2324S1-CS2103T-W09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Enhancement implemented**:
* Remark command
* Edit command (for remark, scores)
* Filter command
* View command
* Summary statistic panel
* UI for View Panel and Summary statistic panel


**Contribution to the UG**:
* Updated UG for the `remark` command
* Updated UG for the `filter` command
* Updated UG for the `view` command
* Updated UG for the `edit` command
* Updated UG for the `Summary Statistic` section
* Updated UG for the `add` command

**Contribution to DG**:
* Contributed to DG for writing in User Stories for week 7
* Contributed to DG for non-functional requirements
* Contributed to DG for glossary
* Contributed to DG for use cases regarding to add and remark features
* Contributed to DG for architecture diagram for UI
* Contributed to DG for feature implementation details for view.

**Contribution to team-based tasks**:
* Keeping track of deadlines and objectives
* Separating tasks into workable units for team members each week
* Managing the team's progress and ensuring that everyone is on track
* In charge of overall UG and DG structure and formatting for the team


**Review/mentoring contributions**:
* Generally main reviewer for PR contributions

**Contributions beyond the project team**:
* Admin tracking for tasks needed to be done

### Contributions to Developer Guide(Extracts)

### View feature

#### Implementation

The view feature is implemented using the `ViewCommand` class. It extends `Command` and overrides the `execute()` method to display the person's details in full in a new window.

Like every other command class, it involves a command `ViewCommand` class and a parser `ViewCommandParser`. `ViewCommand Parser` takes in the user input and returns a `ViewCommand` object.

When executed, `ViewCommand` saves the index of the person to be viewed as `LastViewedPersonIndex` in the `Model` and returns a `CommandResult` object with `isView` property being true.

By having a `isView` property in `CommandResult`, the `MainWindow` component is able to toggle the `UI` to the view the person of the `LastViewedPersonIndex` after the command has been executed.





Given below is an example usage scenario and how the view feature behaves at each step.

Step 1. The user launches the application. The `AddressBook` will be initialized with the current saved address book state

User should see the UI as shown below.

![Ui](images/Ui.png)

Step 2. The user wants to see the full information displayed for the first person in the displayed list. The user enters the command `view 1` to view the first person in the list.

The following sequence diagram shows how the view operation works:

<puml src="diagrams/ViewSequenceDiagram.puml" alt="ViewSequenceDiagram" />

**Note:** The lifeline for `RemarkCommand` and `RemarkCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

User should see the UI as shown below after entering `View 1`

![View](images/viewState.png)

Step 3. The user can then read or process the information stored for the viewed person.



**Note:** The view command can be most effectively used with `search` and `list`. Since the view index is dependent on the Index on the filtered list shown, the user can view the profile after filtering for specific properties in a person using `search` and sorting them using `list`.

Alternatives considered

Alternative 1 (Chosen):   
The view feature is implemented using the `ViewCommand` class. It extends `Command` and overrides the `execute()` method to display the person's details in full in a new window.

Pros: Follows the Software Design Patterns of Command. This is the same pattern used for all other commands thus creating consistency.

Cons: Tougher to implement since other commands do not have the ability to trigger the `ViewCommand` in their execution.  That is we specifically need to set the isView property to true if we want the `ViewCommand` to occur simultaneously with another command.

Alternative 2 (Not Chosen):  
The view feature is implemented using the `ViewCommand` class. It extends `Command` and overrides the `execute()` method to display the person's details in full in a new window.  
Commands that involved viewing will extend `ViewCommand` instead of the `Command` class. All of them are returned as `ViewCommand` to ensure toggling of the UI after command is executed.

Pros: Arguably a more OOP approach since all commands that trigger view IS-A `ViewCommand`.

Cons: You cannot implement any command that does not involve viewing but inherits from any command that is a children of `ViewCommand`.  
An example could be trying to create identical commands that does not toggle the UI after execution. This would require duplication of the exact same command code but inheriting from `Command` instead of `ViewCommand`.




### Contributions to User Guide(Extracts)

Adds a person to JABPro.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/[CATEGORY] TAGNAME]…​`

**Notes regarding the design of the `add` command:**
* The uniqueness of the person is determined by the name only. This means that you cannot have 2 persons with the same name in the application book.
* All other fields other than name can be identical between different people in JABPro.
* `n/NAME` - `NAME` must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...)
* `p/PHONE_NUMBER` - `PHONE_NUMBER` must contain numbers only and should be at-least 3 digits long
* `e/EMAIL` - `EMAIL` must be the standard email address format (There must be an email-prefix followed by  `@` symbol and email domain)
* `a/ADDRESS` - `ADDRESS` can be any value, including special characters such as `#`, `,` ...
* `n/NAME`, `p/PHONE_NUMBER`, `e/EMAIL`, `a/ADDRESS` are mandatory fields.  
  They cannot be blank and must follow the convention as mentioned above.
* `t/[CATEGORY] TAGNAME` - `TAGNAME` must be alphanumeric with no spaces. Any details after the space will be ignored.
* The `t/[CATEGORY] TAGNAME` field is optional. You can add as many tags as you want, including 0 tags.
* Persons added using the `add` command will be added to the end of the list.


### Adding a remark to a person: `remark`

Edits a remark of an existing person in JABPro.

Format: `remark INDEX r/REMARK`

* Edits the remark for the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The previous remark is not saved, and instead is replaced by the inputted remark. The command does not add to the existing remark.
* You can empty out a remark by inputting an empty string.
* You can get the remark previously inputted by using the **REMARK** keyword. It will be replaced with the previous remark. The keyword **REMARK** is case-sensitive. This means that `remark 1 r/**remark**` will just replace the remark with the word `**remark**`.
* `r/` is optional, however omitting it will clear the remark of the person at that `INDEX`.

Examples:
*  `remark 1 r/Great attitude, hardworking` Edits the remark of the 1st person on the list to have a remark `Great attitude, hardworking`.
*  `remark 1 r/**REMARK** furthermore he is great at teamwork` Edits the remark of the 1st person to have a remark `Great attitude, hardworking furthermore he is great at teamwork`.
*  `remark 1 r/` Empties the remark of the 1st person.
*  `remark 1` Empties the mark of the 1st person.

An example of the `remark` command in action:
![Remark](images/remark.png)
An example of the `remark` command in action with the **REMARK** keyword:
![Enhanced Remark](images/enhancedremark.png)

### Viewing a person's details: `view`

Creates a complete view for details of a candidate in the second main panel and summary statistics of a candidate in the third main panel.

Format: `view INDEX`

* Shows the complete details of the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* The index used will be the same as the index used in the `list` command.
* Compatible with search and other features that change the order and content of the list.
* Refer to the [Summary Statistics](#summary-statistics) section for more details on the summary statistics.

Examples:
* `view 1` Shows the complete details of the 1st person on the list.

An example of the `view` command in action:
![View](images/view.png)

<box type="tip" seamless>

**Tip:** Other operations that affect user's data will trigger a refresh of the view.
These include `add`, `edit`, `set`, `remark`, `addL`, `addG`.


</box>


### Editing a person : `edit`

Edits an existing person in JABPro

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/[CATEGORY] TAGNAME]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Notes on editing the tags of the specified person:
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Notes on editing the score of the specified person:
* The `sc/TAG SCORE` field is only applicable for the `edit` command, and not for the `add` command.
* The `sc/TAG SCORE` field can only be used if the `t/TAG` field is used before it or the `TAG` already exist
* The `SCORE` in `sc/TAG SCORE` is non-negative, that is `SCORE` must be more than or equal to 0
* To clear a tag's score, just re-tag it with the same tag name, but without using the `sc/TAG SCORE` field

Notes on rules for `edit` command involving tags with categories:
* Consequently, similar rules for `add` apply to the `edit` command involving tags:
    * If you would like to tag a user with a tag that has not been categorised yet using the `create` command,
      you can specify the category that you would like it to be categorised to in the `edit` command. e.g. `edit 1 t/role swe`
    * If you are using a tag that has not been categorised yet and you did not specify its category in the `add` command,
      the tag would still be saved but it would be "uncategorised" by default.
    * If you have multiple tags in different categories with the same name, you must specify the category when you want to
      tag the specified candidate with one of these tags.


Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 2 t/Interview sc/Interview 80` Edits the tag of the 2nd person to have a tag `Interview` with a score of 80.
* `edit 1 t/role swe`
* `edit 1 t/swe`

An example of the `edit` command in action for editing `tag` and `score`:
![Edit](images/editscore.png)

<box type="tip" seamless>

**Note:** Editing the tags of a person or adding a score to a tag will trigger a refresh of the summary statistics table.

To find out more about the summary statistics table, refer to the [Summary Statistics](#summary-statistics) section.

</box>



### Filter job applicants by statistics: `filter`

Filters and display job applicants using statistical metrics and values.

Format:
`filter t/TAGNAME met/METRIC val/VALUE` or `filter t/TAGNAME met/METRIC`

* Filter works only on the current list of job applicants displayed. It is highly recommended that you enter `list` before using `filter` to ensure that you are filtering the correct list of job applicants.
* It is strongly recommended that you use `filter` after you have tagged most of the job applicants with a tag that has a score. Read more about this in the [Summary Statistics](#summary-statistics) section.
* Filters and displays job applicants whose **value** is **greater than or equal** to the specified value for the specified statistic metric.
* The `TAGNAME` must be a name of a tag that has been created using the `create` command with the `assessment` category.
* The `METRIC` must be a name of a metric that is either `score`, `percentile`, `mean`, `median`.
* The `VALUE` must be a non-negative integer.
* For `METRIC` that is `mean` or `median`, the `VALUE` is optional. Specifying a `VALUE` here will be ignored accordingly.
* This does not edit, update or in any way change the data of the job applicants. It only filters and displays the job applicants.
* Filter does not trigger view, that is your view panels represent the previous candidate you viewed before filtering.
* To get back the **original list with all the candidates**, simply type `list` again.

Examples:
* `filter t/Interview met/score val/80` filters and displays job applicants whose score tied to `interview` tag  is greater than or equal to 80.
* `filter t/Interview met/percentile val/80` filters and displays job applicants whose percentile tied to `interview` tag  is greater than or equal to 80.
* `filter t/Interview met/mean` filters and displays job applicants whose score tied to `interview` tag is greater than or equal to the mean score for `interview` tag.
* `filter t/Interview met/median` filters and displays job applicants whose score tied to `interview` tag is greater than or equal to the median score for `interview` tag.

An example of the `filter` command in action:
![Filter](images/filter.png)

A more complete example guide on how to use filter effectively from when you first start JABPro:
1. `create t/assessment interview` creates a tag `interview` under the `assessment` category.
   ** Take note, only edit if the index exists, adapt this guide accordingly **
2. `edit 1 t/interview sc/interview 80` edits the tag of the 1st person to have a tag `interview` with a score of 80.
3. `edit 2 t/interview sc/interview 90` edits the tag of the 2nd person to have a tag `interview` with a score of 90.
4. `edit 3 t/interview sc/interview 70` edits the tag of the 3rd person to have a tag `interview` with a score of 70.
5. `filter t/interview met/percentile val/80` filters and displays job applicants whose score tied to `interview` tag  is greater than or equal to 80.
6. `filter t/interview met/median` filters and displays job applicants whose score tied to `interview` tag is greater than or equal to the median score for `interview` tag.

In essence, this allows you to find job applicants whose performance rating is above a certain percentile, score or mean/median score for that tag.  
Ideally, this feature can then be used to find the best candidates easily without manual comparison



### Summary Statistics

Summary Statistics is a table generated by JABPro that displays the following information about a candidate:
* Tags that are categorised under the `assessment` category and **have a score**
* The **score** of the candidate for the tag
* The **mean** score of candidates with that tag
* The **median** score of candidates with that tag
* The **minimum** score of candidates with that tag
* The **maximum** score of candidates with that tag
* The **percentile** of the candidate for that tag

Understanding how to use these summary statistics meaningfully:
* You should ensure that you have **sufficient candidates** with a score for the tag you are interested in, before using the summary statistics to make comparisons.
    * This is due to the fact that these summary statistics rely on concepts such as mean, median and percentile, which are statistical concepts that require a sufficient sample size to be meaningful.
    * For example, if you have only assigned 5 out of 100 candidates, the summary statistics will not be representative of the actual mean, median and percentile for that tag.
    * In this case, you should assign more candidates with a score for that tag, before using the summary statistics to make comparisons.
    * If you have n number candidates of the same score, their percentile will all be 0.0. This is because they are both the best and the worst performing candidate for that tag. Thus, a placeholder value of 0.0 is used to represent this.
    * If you have assigned a sufficient number of candidates with a score for that tag, you can use the summary statistics to make comparisons. For example, you want to check if a candidate's score for a tag is more than or equal to half of all the candidates who have a score for that tag, you can use the median to make this comparison.
    * A **sufficient number** could be deemed as **any number that is more than 20**, but this is not a hard and fast rule. You should use your own discretion to determine if the number of candidates with a score for that tag is sufficient.

* Use mostly `median` and `percentile` to make your judgements
    * `median` to find candidates who are the better performing half
    * `percentile` as where this candidate stands among all other candidates (treat it like a ranking system, the higher the percentile, the better the candidate is performing)
    * `percentile` 100.0 would represent the best performing candidate for that tag and `percentile` 0.0 would represent the worst performing candidate for that tag

**Advanced users**
* Understand that `percentile` has limited functionality in certain context. Suppose you have 6 candidates with the scores `{80, 90, 100, 100, 100, 100}`
    * Median would be 90 in this case and percentile would be 50.0 for the candidate with a score of 90, however the upper half of the candidates are all 100.0 percentile
    * This comes as a consequence of the implementation where given you have the same score, you should have the same percentile / ranking
    * This is one of the root reasons why your sample size should be sufficiently large before using the summary statistics to make comparisons, this reduces the chances of having candidates with the same score

In-depth explanation of the statistics:
**mean** is calculated by using the formula `sum of all scores with that tag/ number of candidates with that tag`
**median** is calculated by using the formula `middle score of all scores with that tag`
**minimum** is calculated by using the formula `lowest score of all scores with that tag`
**maximum** is calculated by using the formula `highest score of all scores with that tag`
**percentile** is calculated by using the formula `number of candidates with a score strictly lower than the candidate/ total number of candidates with that tag`


