---
layout: default.md
title: "Sean Koh's Project Portfolio Page"
---

# Project: JABPRO

## Overview
JABPro aims to solve the problem of HR managers having to manage and sort through tons of job applications.

The problem that we have identified included:
1. Visual Noise and Clutter (Through the use of Excel or other programmes where rows of data are displayed)
2. Lack of a way to easily compare candidates (Having to manually compare rows of data or need to search up statistical formulas)

These are the two problems I have seeked to tackle in this TP project.  
The features that I have implemented to tackle these problems are:
1. `view` command to view a candidate's details in a separate panel (to reduce visual noise and clutter) whereby the candidate's details are displayed in full in a very organised and formatted manner.  
This creates a very clean and organised view for the user to easily read and process the information stored for the viewed person.

2. `filter` command to filter candidates based on statistical metrics and values. Whereby the user can filter and display candidates whose value is greater than or equal to the specified value for the specified statistic metric.
This allows the user to easily compare candidates based on their performance for a particular tag. We use tag as a way to tag assessments that the candidate has taken. This allows the user to easily compare candidates based on their performance for a particular assessment.

In order to implement filter, there was a need to implement summary statistics. This is because the filter command relies on the summary statistics to filter candidates based on their performance for a particular assessment.
Additionally, we needed to implement additional parameters to `edit` command to allow the user to edit the score of a candidate for a particular assessment. This is because the summary statistics relies on the score of the candidate for a particular assessment to calculate the summary statistics.

Thereby additional enhancement that I have implemented to support filter are:
1. Overloading the current `edit` command to edit the score of a candidate for a particular assessment.
2. Summary Statistic

For this TP project, I have mainly been in charge of the UI. This is due to extensive interaction needed for the `View` and `Summary Statistic` panels
Nonetheless, I have also contributed greatly to backend features such as `Remark`, `Edit`, `Filter`, `Summary Statistic` 
This has allowed me to gain experience and understanding of the backend and frontend of the application and thus can be considered a full stack developer.

For the team administration, I have acted as the work manager, ensuring that everyone is on track to meet the deadlines and objectives. 
In terms of the UG and DG, I have been in charge of the overall structure and formatting of the UG and DG. This is to ensure that the UG and DG is consistent and easy to read for the user.


## Summary of Contributions
**Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sk2001git&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=sk2001git&tabRepo=AY2324S1-CS2103T-W09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

**Enhancement implemented**:
* Remark command
* Edit command (for scores)
* Filter command
* View command
* Summary Statistic
* UI for View Panel and Summary statistic panel


**Contribution to the UG**:
* Updated UG for the `add` command
* Updated UG for the `remark` command
* Updated UG for the `filter` command
* Updated UG for the `view` command
* Updated UG for the `edit` command
* Updated UG for the `Summary Statistic` section

**Contribution to DG**:
* Contributed to DG for writing User Stories
* Contributed to DG for non-functional requirements
* Contributed to DG for use cases regarding to add and remark features
* Contributed to DG for architecture diagram for UI, Storage, Model
* Contributed to DG for feature implementation details for `view`.

**Contribution to team-based tasks**:
* Keeping track of deadlines and objectives
* Separating tasks into workable units for team members each week
* Managing the team's progress and ensuring that everyone is on track
* In charge of overall UG and DG structure and formatting for the team


**Review/mentoring contributions**:
* Reviewed PRs for team members


**Contributions beyond the project team**:
* Participated in the PE Dry Run and gave feedback to the other team

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

![Ui](../images/Ui.png)

Step 2. The user wants to see the full information displayed for the first person in the displayed list. The user enters the command `view 1` to view the first person in the list.

The following sequence diagram shows how the view operation works:

<puml src="diagrams/ViewSequenceDiagram.puml" alt="ViewSequenceDiagram" />

**Note:** The lifeline for `RemarkCommand` and `RemarkCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

User should see the UI as shown below after entering `View 1`

![View](../images/viewState.png)

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

### Introduction
1. Table of Contents
2. Overview of Main Features

### Adding an applicant: `add`

Adds a person to JABPro.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/[CATEGORY] TAGNAME]…​`

Type | Prefix                  | Constraints                                                                                                                                               
----------|-------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------
Mandatory  | `n/NAME`                |  `NAME` must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...)
Mandatory| `p/PHONE_NUMBER`        | `PHONE_NUMBER` must contain numbers only and should be at-least 3 digits long
Mandatory| `e/EMAIL`               | `EMAIL` must be the standard email address format (There must be an email-prefix followed by  `@` symbol and email domain)                                                                  
Mandatory| `a/ADDRESS`             | `ADDRESS` can be any value, including special characters such as `#`, `,` ...
 Optional|  `t/[CATEGORY] TAGNAME` | `TAGNAME` must be alphanumeric with no spaces. Any details after the space will be ignored. 



**Notes regarding additional constraint on `add` command:**
* The uniqueness of the person is determined by the name only. This means that you cannot have 2 persons with the same name in the application book.
* All other fields other than name can be identical between different people in JABPro.
* Persons added using the `add` command will be added to the end of the list.

**Notes on adding tags:**
* If you would like to tag a user with a tag that has not been categorised yet using the `create` command,
  you can specify the category that you would like it to be categorised to in the `add` command. e.g. `...t/role swe`
* If you are using a tag that has not been categorised yet and you did not specify its category in the `add` command,
  the tag would still be saved but it would be "uncategorised" by default.
* If you have multiple tags in different categories with the same name, you must specify the category when you want to
  add one of these tags to the candidate you are adding.

<box type="tip" seamless>

**Tip:**
* A person can have any number of tags (including 0)!
  </box>



An example of the `add` command being successfully executed:
1. Enter the command `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/dept finance`
2. This is the result of the successful `add` command (Take note that command entered will not be shown in the result):
   ![Add-Success](images/add-command-success.png)
   <br>

An example of the `add` command failing to execute due to missing mandatory fields:
1. Enter the command `add n/Betsy Crowe t/friend` (**Missing mandatory fields**)
2. This is the result of the failed `add` command:
   ![Add-Fail](images/add-command-failure.png)
   <br>

An example of trying to add a person with the same name as an existing person:
1. Enter the command `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/dept finance` (**Same name as existing applicant**)
2. This is the result of the failed `add` command:
   ![Add-Fail](images/add-command-duplicate-person.png)




### Adding a remark to a applicant: `remark`

Edits a remark of an existing applicant in JABPro.
Format: `remark INDEX r/REMARK`


Type | Prefix      | Constraints                                                                                                                                               
----------|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------
Mandatory  | `INDEX`     |  `INDEX` must be an existing index in the displayed applicant list
Optional | `r/ REMARK` | `REMARK` can be any value, including special characters such as `#`, `,` ... 


**Notes regarding `remark` command:**
* The previous remark is not saved, and instead is replaced by the inputted remark. The command does not add to the existing remark.
* You can empty out a remark by inputting `r/` without any text after it or by omitting the `r/` prefix.
* You can get the remark previously inputted by using the **REMARK** keyword. It will be replaced with the previous remark. The keyword **REMARK** is case-sensitive. This means that `remark 1 r/**remark**` will just replace the remark with the word `**remark**`.

An example of the `remark` command being successfully executed:
1. Enter the command `remark 1 r/Great attitude, hardworking`
2. This is the result of the successful `remark` command (Take note that command entered will not be shown in the result):
   ![Remark-Success](images/remark-command-success.png)
   <br>


An example of the `remark` command being successfully executed with the **REMARK** keyword:
1. Enter the command `remark 1 r/**REMARK** furthermore he is great at teamwork`
2. This is the result of the successful `remark` command (Take note that command entered will not be shown in the result):
   ![Remark-Success](images/remark-command-enhanced-success.png)
   <br>





An example of the `remark` command failing to execute due to wrong index:
1. Enter the command `remark 10 r/Great attitude, hardworking` (**Index does not exist on applicant list panel**)
2. This is the result of the failed `remark` command:
   ![Remark-Fail](images/remark-command-clear-remark.png)
   <br>




Additional Examples:
*  `remark 1` Empties the remark of the 1st person. It is equivalent to `remark 1 r/`.



### Viewing a person's details: `view`

Creates a complete view for details of an applicant in the second main panel and summary statistics (if applicable) of a candidate in the third main panel.

Format: `view INDEX`

Type | Prefix      | Constraints                                                                                                                                               
----------|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------
Mandatory  | `INDEX`     |  `INDEX` must be an existing index in the displayed applicant list

**Notes regarding `view` command:**
* The index used will be the same index as the one shown in the displayed applicant list.
* Compatible with search and other features that changes the displayed applicant list. Index always follows the index shown in the displayed applicant list.
* Refer to the [Summary Statistics](#summary-statistics) section for more details on the summary statistics.

<box type="tip" seamless>

**Tip:** Other operations that affect user's data will trigger a refresh of the view.
These include `add`, `edit`, `set`, `remark`, `addL`, `addG`.
This means that the view will be updated to reflect the latest changes to the data for that particular applicant.

</box>

An example of the `view` command being successfully executed:
1. Enter the command `view 3`
2. This is the result of the successful `view` command (Take note that command entered will not be shown in the result):
   ![View-Success](../images/view-command-success.png)
   <br>

An example of the `view` command being successfully executed for person with tags and score:
1. Enter the command `view 2` (**Person with tags and score**)
2. This is the result of the successful `view` command (Take note that command entered will not be shown in the result):
   ![View-Success](../images/view-command-with-stats-success.png)
   <br>

An example of the `view` command failing to execute due to wrong index:
1. Enter the command `view 0` (**Index does not exist on applicant list panel**)
2. This is the result of the failed `view` command:
   ![View-Fail](../images/view-command-failure.png)
   <br>

### Editing a person : `edit`

Edits an existing applicant's detail in JABPro

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAGNAME]…​ [sc/TAGNAME SCORE]`

Type | Prefix                | Constraints                                                                                                                                               
----------|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------
Mandatory  | `INDEX`               |  `INDEX` must be an existing index in the displayed applicant list
Optional | `n/NAME`              |  `NAME` must be alphanumeric (Letters and numbers, no symbols allowed such as `/`, `,` ...)
Optional| `p/PHONE_NUMBER`      | `PHONE_NUMBER` must contain numbers only and should be at-least 3 digits long
Optional| `e/EMAIL`             | `EMAIL` must be the standard email address format (There must be an email-prefix followed by  `@` symbol and email domain)
Optional| `a/ADDRESS`           | `ADDRESS` can be any value, including special characters such as `#`, `,` ...
Optional| `t/TAGNAME`           | `TAGNAME` must be alphanumeric with no spaces. Any details after the space will be ignored.
Optional| `sc/TAGNAME SCORE`  . | `TAGNAME` a tag that is being created or already exist for that applicant. `SCORE` must be a non-negative integer.


**Notes regarding `edit` command:**
* At least one of the optional fields must be provided.
* Existing attributes will be updated to the input values.
* There is a way to edit tags and their categories at the same time. Look at the notes for editing tags with categories `t/[CATEGORY] TAGNAME` for more details.

**Notes on editing the tags of the specified person for `t/TAGNAME`**:
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.
* There is no current way to keep the existing tags and add new tags to the person. You will have to re-tag the person with the existing tags and the new tags.


**Notes on editing the score of the specified person for `sc/TAGNAME SCORE`**:
* The `TAG` in `sc/TAG SCORE` must be a tag of the category `assessment`. You cannot use the `sc/TAG SCORE` field for tags that are not of the `assessment` category.
* The `sc/TAG SCORE` field can only be used after the `t/TAG` field is used if the tag has not been created  or the `TAG` already exist on the applicant
* The `SCORE` in `sc/TAG SCORE` is non-negative, that is `SCORE` must be more than or equal to 0
* To clear a tag's score, just re-tag it with the same tag name, but without using the `sc/TAG SCORE` field

<box type="tip" seamless>

1. Editing an applicant's details will trigger a refresh of the view. This means that the view will be updated to reflect the latest changes to the data for that particular applicant.
2. We strongly recommend that you categorise tags using `create` before using `edit` to tag candidates. This is to reduce the confusion of having two ways to tag applicants.
   </box>

An example of the `edit` command being successfully executed:
1. Enter the command `edit 1 n/Alex Ho p/91234567` (**Edit name and phone number**)
2. This is the result of the successful `edit` command (Take note that command entered will not be shown in the result):
   ![Edit-Success](images/edit-command-success.png)
   <br>

An example of the `edit` command being successfully executed with tags and score:
1. Ensure that you have created a tag `Interview` under the `assessment` category using the `create` command. That is, enter the command `create t/assessment Interview`
2. Enter the command `edit 1 t/Interview sc/Interview 80` (**Edit tag and score**)
3. This is the result of the successful `edit` command (Take note that command entered will not be shown in the result):
   ![Edit-Success](images/edit-command-with-stats-success.png)
   <br>


An example of the `edit` command being successfully executed to clear a tags and score:
1. Enter the command `edit 1 t/` (**Clear all tags**)
2. This is the result of the successful `edit` command (Take note that command entered will not be shown in the result):
   ![Edit-Success](images/edit-command-clear-tags-success.png)
   <br>


An example of the `edit` command being wrongly executed due to trying to attach a score to a tag that is not of the `assessment` category:
1. Enter the command `edit 1 t/TechLead sc/TechLead 80` (**Tag `TechLead` is not of the assessment category**)
2. This is the result of the failed `edit` command:
   ![Edit-Fail](images/edit-command-failure.png)
   <br>




### Filter job applicants by statistics: `filter`

Filters and display applicants in the current displayed applicant list using statistical metrics and values.

Format:`filter t/TAGNAME met/METRIC val/VALUE` or `filter t/TAGNAME met/METRIC`

Type | Prefix                | Constraints                                                                                                                                               
----------|-----------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------
Mandatory  | `t/TAGNAME`           |  `TAGNAME` must be a tag that is of the category `assessment`
Mandatory| `met/METRIC`          | `METRIC` must be either `score`, `percentile`, `mean`, `median`
Optional| `val/VALUE`           | Optional only for `mean` and `median`. Otherwise, VALUE` must be a non-negative integer and is a mandatory field.

**Notes regarding `filter` command:**
* Filter works only on the current list of job applicants displayed. It is essential that you enter `list` before using `filter` to ensure that you are filtering the correct list of job applicants.
* It is strongly recommended that you use `filter` after you have tagged most of the job applicants with a tag that has a score.
* Filters and displays job applicants whose **value** is **greater than or equal** to the specified value for the specified statistic metric.
* For `METRIC` that is `mean` or `median`, the `VALUE` is optional. Specifying a `VALUE` here will be ignored accordingly. `filter t/TAGNAME met/METRIC` is equivalent to `filter t/TAGNAME met/METRIC val/X` where `X` is any positive integer.
* Filter does not edit, update or in any way change the data of the job applicants. It only filters and displays the job applicants.
* Filter does not trigger view, that is your view panels represent the previous candidate you viewed before filtering.
* To get back the **original list with all the applicants**, simply type `list` again.

**Notes on the different metrics:**
Do look at the [Summary Statistics](#summary-statistics) section for more details on the summary statistics metrics.

<box type="tip" seamless>

You should use `filter` after you have tagged most of the job applicants with a tag that has a score.   
This is because some of the metrics such as `percentile`, `mean` and `median` require a certain number of scores to be considered meaningful.
Read more about this in the [Summary Statistics](#summary-statistics) section.

</box>

Set up for examples when you first start JABPro with default data:
1. `list`
2. `create t/assessment interview` to create a tag `interview` under the `assessment` category.
3. `edit 1 t/interview sc/interview 80`
4. `edit 2 t/interview sc/interview 90`
5. `edit 3 t/interview sc/interview 70`
6. The result of the above commands should look like this:
   ![Filter-Setup](images/filter-setup.png)
   <br>

An example of the `filter` command being successfully executed:
1. Enter the command `list`
2. Enter the command `filter t/interview met/percentile val/80` (**Filter by percentile**)
3. This is the result of the successful `filter` command (Take note that command entered will not be shown in the result):
   ![Filter-Success](images/filter-command-success.png)
   <br>

An example of the `filter` command being successfully executed with `median`:
1. Enter the command `list`
2. Enter the command `filter t/interview met/median` (**Filter by median**)
3. This is the result of the successful `filter` command (Take note that command entered will not be shown in the result):
   ![Filter-Success](images/filter-command-median-success.png)
   <br>

An example of the `filter` command being incorrectly executed due to non-existent tag:
1. Enter the command `list`
2. Enter the command `filter t/techlead met/percentile val/80` (**Tag `techlead` does not exist**)
3. This is the result of the failed `filter` command:
   ![Filter-Fail](images/filter-command-failure.png)
   <br>

An example of the `filter` command being incorrectly executed due to an invalid value for `val/VALUE`:
1. Enter the command `list`
2. Enter the command `filter t/interview met/percentile val/-1` (**Negative value for percentile**)
3. This is the result of the failed `filter` command:
   ![Filter-Fail](images/filter-command-failure-2.png)
   <br>

**Significance of using `filter` with the metrics `score`, `percentile`, `mean` and `median`:**
In essence, this allows you to find job applicants whose performance rating is above a certain percentile, score or mean/median score for that tag.  
Ideally, this feature can then be used to find the best candidates easily and quickly without having to manually look through the list of candidates.



## Summary Statistics

Summary Statistics is a table generated by JABPro that displays the following information about an applicant:
It is generated for tags that are categorised under the `assessment` category.

 Statistic / Metric                | Description                                                                                                                                              
--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------
`score`                   | The score of the candidate for the tag
`mean`                    | The mean score of candidates with that tag
`median`                  | The median score of candidates with that tag
`minimum`                 | The minimum score of candidates with that tag
`maximum`                 | The maximum score of candidates with that tag
`percentile`              | The percentile of the candidate for that tag



<box type="warning" seamless>
You should ensure that you have **sufficient candidates of more than 20** with a score for the tag you are interested in, before using the summary statistics to make comparisons.  
</box> 

**Notes on why you should have sufficient candidates with a score for the tag you are interested in:**
1. This is due to the fact that these summary statistics rely on concepts such as mean, median and percentile, which are statistical concepts that require a sufficient sample size to be meaningful.
2. For example, if you have only assigned 5 out of 100 candidates, the summary statistics will not be representative of the actual mean, median and percentile for that tag.
3. In this case, you should assign more candidates with a score for that tag, before using the summary statistics to make comparisons.
5. If you have assigned a sufficient number of candidates with a score for that tag, you can use the summary statistics to make comparisons. For example, you want to check if a candidate's score for a tag is more than or equal to half of all the candidates who have a score for that tag, you can use the median to make this comparison.
* A **sufficient number** could be deemed as **any number that is more than 20**, but this is not a hard and fast rule. You should use your own discretion to determine if the number of candidates with a score for that tag is sufficient.


<box type="tip" seamless>

1. Use mostly `median` and `percentile` to make your judgement on the performance of a candidate.
2. `median` to find candidates who are the better performing half
3. `percentile` as where this candidate stands among all other candidates (treat it like a ranking system, the higher the percentile, the better the candidate is performing)

</box>




**Advanced users**
* Understand that `percentile` has limited functionality in some context. This is because if two applicants have the same score, they are `rank` the same. This means that the percentile of both applicants will be the same.
    * If all applicants have the same score, their percentile will all be 0.0. This is because they are all `rank` the same.
    * Additionally, when the spread of scores is small, the percentile will not be able to differentiate between applicants with similar scores.


**Formula used to calculate the summary statistics:**
**mean** is calculated by using the formula `sum of all scores with that tag/ number of candidates with that tag`
**median** is calculated by using the formula `middle score of all scores with that tag`
**minimum** is calculated by using the formula `lowest score of all scores with that tag`
**maximum** is calculated by using the formula `highest score of all scores with that tag`
**percentile** is calculated by using the formula `number of candidates with a score strictly lower than the candidate/ total number of candidates with that tag`

