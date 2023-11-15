package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.RecipeAddCommandParser.MESSAGE_FAIL_INGREDIENT;
import static seedu.address.logic.parser.RecipeAddCommandParser.MESSAGE_FAIL_STEP;
import static seedu.address.logic.parser.RecipeAddCommandParser.MESSAGE_SUCCESS_INGREDIENT;
import static seedu.address.logic.parser.RecipeAddCommandParser.MESSAGE_SUCCESS_NAME;
import static seedu.address.logic.parser.RecipeAddCommandParser.MESSAGE_SUCCESS_STEP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.RecipeAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.Unit;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.IngredientBuilder;
import seedu.address.testutil.RecipeBuilder;

public class RecipeAddCommandParserTest {
    private final RecipeAddCommandParser parser = new RecipeAddCommandParser();

    @Test
    public void parse_nameFieldPresent_success() {

        Recipe expectedRecipe = new RecipeBuilder().withId(2).withName("cookie").build();
        parser.reset();
        try {
            parser.addName("cookie");
        } catch (ParseException pe) {
            // Should never reach here
            assert false;
        }
        Command a = parser.generateCommand();
        Command b = new RecipeAddCommand(expectedRecipe);
        assertEquals(a.toString(), b.toString());
    }

    @Test
    public void parse_ingredientFieldPresent_success() {
        Ingredient expectedIngredient = new IngredientBuilder().withName("flour").withQuantity(100, Unit.GRAM).build();
        Recipe expectedRecipe = new RecipeBuilder()
                .withName("cookie").withId(2).withIngredient(expectedIngredient).build();
        parser.reset();
        assertEquals(parser.addIngredient("flour 100g"), String.format(MESSAGE_SUCCESS_INGREDIENT, "flour 100g"));
        try {
            parser.addName("cookie");
        } catch (ParseException pe) {
            // Should never reach here
            assert false;
        }
        Command a = parser.generateCommand();
        Command b = new RecipeAddCommand(expectedRecipe);
        assertEquals(a.toString(), b.toString());
        try {
            assertEquals(parser.addName("cookie"), MESSAGE_SUCCESS_NAME);
        } catch (ParseException pe) {
            // Should never reach here
            assert false;
        }
    }
    @Test
    public void parse_stepFieldPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder()
                .withName("cookie").withId(2).withSteps("eat cookie", 1).build();
        parser.reset();
        assertEquals(parser.addStep("1. eat cookie"), MESSAGE_SUCCESS_STEP);
        try {
            parser.addName("cookie");
        } catch (ParseException pe) {
            // Should never reach here
            assert false;
        }
        Command a = parser.generateCommand();
        Command b = new RecipeAddCommand(expectedRecipe);
        assertEquals(a.toString(), b.toString());
    }


    @Test
    public void parse_invalidValue_failure() {
        assertEquals(parser.addStep("no number at beginning"), MESSAGE_FAIL_STEP);

        assertEquals(parser.addIngredient("no quantity specified"), MESSAGE_FAIL_INGREDIENT);
    }
}
