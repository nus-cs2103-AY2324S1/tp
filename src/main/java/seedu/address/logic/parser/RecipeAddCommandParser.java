package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.logic.commands.RecipeAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Name;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeStep;

/**
 * Parses input arguments and creates a new AddRecipeCommand object
 */
public class RecipeAddCommandParser implements Parser<RecipeAddCommand> {
    public static final String MESSAGE_SUCCESS_NAME = "Name has been set!";

    public static final String MESSAGE_SUCCESS_INGREDIENT = "Ingredient added: %1$s";
    public static final String MESSAGE_FAIL_INGREDIENT = "Failed to add ingredient";
    public static final String MESSAGE_SUCCESS_STEP = "Step added successfully to recipe";
    public static final String MESSAGE_FAIL_STEP = "Failed to add step";

    private Name name;
    private final List<Ingredient> ingredients;
    private final List<RecipeStep> recipeSteps;

    /**
     * Initialises the lists
     */
    public RecipeAddCommandParser() {
        ingredients = new ArrayList<>();
        recipeSteps = new ArrayList<>();
    }

    /**
     * Adds a name to the parameter buffer
     * @param name The name of the recipe
     * @return The success message upon adding the name
     */
    public String addName(String name) throws ParseException {
        this.name = ParserUtil.parseName(name);
        return MESSAGE_SUCCESS_NAME;
    }

    /**
     * Adds a step into the parameter buffer
     * @param step A step in the recipe
     * @return The outcome of parsing and adding the step
     */
    public String addStep(String step) {
        try {
            RecipeStep toAdd = ParserUtil.parseRecipeStep(step);
            recipeSteps.add(toAdd);
            return MESSAGE_SUCCESS_STEP;
        } catch (ParseException pe) {
            return MESSAGE_FAIL_STEP;
        }
    }

    /**
     * Adds an ingredient into the parameter buffer
     * @param ingredient AN ingredient in the recipe
     * @return The outcome of parsing and adding the ingredient
     */
    public String addIngredient(String ingredient) {
        try {
            Ingredient toAdd = ParserUtil.parseRecipeIngredient(ingredient);
            ingredients.add(toAdd);
            return String.format(MESSAGE_SUCCESS_INGREDIENT, ingredient);
        } catch (ParseException pe) {
            return MESSAGE_FAIL_INGREDIENT;
        }
    }

    /**
     * Generate the RecipeAddCommand from the buffered parameters
     * @return The Command instance
     */
    public RecipeAddCommand generateCommand() {
        recipeSteps.sort(Comparator.comparingInt(RecipeStep::getStepNumber));
        Recipe recipe = new Recipe(name, List.copyOf(ingredients), List.copyOf(recipeSteps));
        return new RecipeAddCommand(recipe);
    }

    // Method unused. Each line must be parsed in sequence to detect parsing errors on a line-by-line basis.

    @Override
    public RecipeAddCommand parse(String args) throws ParseException {
        String[] lines = args.split("\\r?\\n");

        Name name = new Name(lines[0]);
        List<String> ingredientStrings = new ArrayList<>();
        List<String> stepStrings = new ArrayList<>();

        boolean stepstarted = false;
        for (int i = 1; i < lines.length; i++) {
            if (lines[i].equals("steps start") || lines[i].equals("step start")) {
                stepstarted = true;
            } else if (stepstarted) {
                stepStrings.add(lines[i]);
            } else {
                ingredientStrings.add(lines[i]);
            }
        }

        System.out.println(args);
        List<Ingredient> ingredientList = new ArrayList<>();
        List<RecipeStep> stepList = new ArrayList<>();
        for (String ingredientString : ingredientStrings) {
            ingredientList.add(ParserUtil.parseRecipeIngredient(ingredientString));
        }
        for (String recipeString : stepStrings) {
            stepList.add(ParserUtil.parseRecipeStep(recipeString));
        }

        stepList.sort(Comparator.comparingInt(RecipeStep::getStepNumber));
        Recipe recipe = new Recipe(name, ingredientList, stepList);

        return new RecipeAddCommand(recipe);
    }

    /**
     * Reset the parser for a new recipe to add.
     */
    public void reset() {
        ingredients.clear();
        recipeSteps.clear();
    }

}
