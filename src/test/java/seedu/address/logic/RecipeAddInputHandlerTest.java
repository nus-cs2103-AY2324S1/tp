package seedu.address.logic;

import static seedu.address.logic.parser.RecipeAddCommandParser.MESSAGE_FAIL_INGREDIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Name;

public class RecipeAddInputHandlerTest {
    private static final String MESSAGE_PROMPT_NAME = "Input the name of the recipe.";
    private static final String MESSAGE_PROMPT_INGREDIENT = "Input an ingredient and it's quantity. Eg. Milk 100g"
            + "\n" + "When you're done, type \"steps start\"";

    private static final String MESSAGE_PROMPT_STEP = "Input the next step in the recipe. Eg. 1. Preheat the oven."
            + "\n" + "When you're done, type \"complete recipe\"";
    @Test
    public void execute_startAddingRecipe_success() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        CommandResult result = handler.handle("addrecipe");
        assert(result.equals(new CommandResult("" + "\n" + MESSAGE_PROMPT_NAME)));
    }
    @Test
    public void execute_addEnglishNameToRecipe_success() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        CommandResult result = handler.handle("Bread");
        assert(result.equals(new CommandResult("Name has been set!" + "\n" + MESSAGE_PROMPT_INGREDIENT)));
    }
    @Test
    public void execute_addNonEnglishNameToRecipe_failure() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        CommandResult result = handler.handle("ひ");
        assert(result.equals(new CommandResult("" + "\n" + Name.MESSAGE_CONSTRAINTS
                + "\n" + MESSAGE_PROMPT_NAME)));
    }
    @Test
    public void execute_addIngredient_success() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        CommandResult result = handler.handle("Milk 100g");
        assert(result.equals(new CommandResult("Ingredient added: Milk 100g"
                + "\n" + MESSAGE_PROMPT_INGREDIENT)));
    }
    @Test
    public void execute_addIngredientDoNotHaveUnit_failure() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        CommandResult result = handler.handle("Milk 100");
        assert(result.equals(new CommandResult(MESSAGE_FAIL_INGREDIENT
                + "\n" + MESSAGE_PROMPT_INGREDIENT)));
    }
    @Test
    public void execute_addIngredientDoFollowFormat_failure() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        CommandResult result = handler.handle("Milk100g");
        assert(result.equals(new CommandResult(MESSAGE_FAIL_INGREDIENT
                + "\n" + MESSAGE_PROMPT_INGREDIENT)));
    }
    @Test
    public void execute_transitionFromIngredientsToSteps_success() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        handler.handle("Milk 100g");
        CommandResult result = handler.handle("steps start");
        assert(result.equals(new CommandResult(""
                + "\n" + MESSAGE_PROMPT_STEP)));
    }
    @Test
    public void execute_addStep_success() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        handler.handle("Milk 100g");
        handler.handle("steps start");
        CommandResult result = handler.handle("1. Mix Flour and Water");
        assert(result.equals(new CommandResult("Step added successfully to recipe"
                + "\n" + MESSAGE_PROMPT_STEP)));
    }
    @Test
    public void execute_addStepWithoutIndex_failure() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        handler.handle("Milk 100g");
        handler.handle("steps start");
        CommandResult result = handler.handle("Mix Flour and Water");
        assert(result.equals(new CommandResult("Failed to add step"
                + "\n" + MESSAGE_PROMPT_STEP)));
    }
    @Test
    public void execute_transitionFromStepsToComplete_success() {
        RecipeAddInputHandler handler = new RecipeAddInputHandler();
        handler.handle("addrecipe");
        handler.handle("Bread");
        handler.handle("Milk 100g");
        handler.handle("steps start");
        handler.handle("1. Mix Flour and Water");
        handler.handle("complete recipe");
        assert(handler.isComplete());
    }
}
