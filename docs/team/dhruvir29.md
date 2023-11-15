## Dhruvi Ketan Rathod's Project Portfolio Page

### Project: [Ba]king [Br]ead

[Ba]king [Br]ead is a desktop inventory and recipe book management application that allows users to add ingredients and view
recipes. The user interacts with it through a CLI. The GUI is implemented using JavaFX and the project is written in Java.

Given below are my contributions to the project.

* **New Feature:** Added the ability to delete specific recipes.
    * What it does: Allows the user to delete a specific recipe by using the recipe's unique identifier.
    * Justification: This feature is critical to the working of the product as a user needs to be able to delete recipes 
      they no longer want.
    * Highlights: By using the UniqueId class to identify our recipes, deletion means that the id of the recipe does not 
      change even when recipes that have a lower uuid are deleted.
* **New Feature:** Added the ability to modify the ingredients of specific recipes.
  * What it does: Allows the user to modify the ingredients of a recipe 
  * Justification: This feature is critical to the working of the product as a user might want to adjust the quantity 
    and unit of measurement of an ingredient required for the recipe or a user might want to add more ingredients to the
    list of ingredients needed for that recipe.
* **Enhancement:** 
  * Implemented the parsers for the commands used for the ingredient list (AddCommand, AddCommandParser, StockCommand, 
    StockCommandParser, UseCommand, UseCommandParser, ListCommand).

* **Code Contribution:** [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=dhruvir29&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Contribution to UG:**
    - Addition of the delete and modify recipe feature.
    - Updating of documentation of certain restrictions of the different commands
    - Updating of the description of the app
    - Updating of the description of the UI
    - Updating of Command Summary Table

* **Contribution to DG:**
    - Wrote the base documentation for the target user profile, value proposition and user stories
    - Wrote the use cases for many of the key ingredient commands as well as modify and delete
    - Wrote the implementation for the delete recipe feature
    - Addition of sequence diagram for the delete recipe feature
    - Wrote the implementation for the modify recipe feature
    - Addition of sequence diagram for the modify recipe feature

* **Contribution to team-based tasks**
    - Helped merge and review other team members' code
    - General updates to the UG and DG
