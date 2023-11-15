package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UUID;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.NameContainsKeywordsPredicate;
import seedu.address.model.ingredient.exceptions.IngredientNotFoundException;
import seedu.address.model.recipe.RecipeUuidMatchesPredicate;
import seedu.address.model.recipe.UniqueId;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_FLOUR = "Flour";
    public static final String VALID_NAME_EGG = "Egg";
    public static final String VALID_AMOUNT_FLOUR = "100";
    public static final String VALID_AMOUNT_EGG = "100";
    public static final String VALID_UNIT_EGG = "GRAM";
    public static final String VALID_UNIT_FLOUR = "GRAM";
    public static final String VALID_NAME_BUTTER = "Butter";
    public static final String VALID_AMOUNT_BUTTER = "100";
    public static final String VALID_UNIT_BUTTER = "GRAM";
    public static final String VALID_UUID = "1";
    public static final String INVALID_ZERO_UUID = "0";
    public static final String INVALID_NEG_UUID = "-100";

    public static final String NAME_DESC_FLOUR = " " + PREFIX_NAME + VALID_NAME_FLOUR;
    public static final String NAME_DESC_EGG = " " + PREFIX_NAME + VALID_NAME_EGG;
    public static final String NAME_DESC_BUTTER = " " + PREFIX_NAME + VALID_NAME_BUTTER;
    public static final String QUANTITY_DESC_FLOUR = " " + PREFIX_QUANTITY + VALID_AMOUNT_FLOUR;
    public static final String QUANTITY_DESC_EGG = " " + PREFIX_QUANTITY + VALID_AMOUNT_EGG;
    public static final String QUANTITY_DESC_BUTTER = " " + PREFIX_QUANTITY + VALID_AMOUNT_BUTTER;
    public static final String UNIT_DESC_FLOUR = " " + PREFIX_UNIT + VALID_UNIT_FLOUR;
    public static final String UNIT_DESC_EGG = " " + PREFIX_UNIT + VALID_UNIT_EGG;
    public static final String UNIT_DESC_BUTTER = " " + PREFIX_UNIT + VALID_UNIT_BUTTER;
    public static final String UUID_DESC_RECIPE = " " + PREFIX_UUID + VALID_UUID;
    public static final String INVALID_ZERO_UUID_DESC_RECIPE = " " + PREFIX_UUID + INVALID_ZERO_UUID;
    public static final String INVALID_NEG_UUID_DESC_RECIPE = " " + PREFIX_UUID + INVALID_NEG_UUID;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "hundred";
    public static final String INVALID_UNIT_DESC = " " + PREFIX_UNIT + "ml";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the inventory, filtered ingredient list and selected ingredient in {@code actualModel} remain unchanged
     */

    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Inventory expectedInventory = new Inventory(actualModel.getInventory());
        List<Ingredient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredIngredientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInventory, actualModel.getInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredIngredientList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the IngredientNotFound message matches {@code expectedMessage} <br>
     * - the inventory, filtered ingredient list and selected ingredient in {@code actualModel} remain unchanged
     */
    public static void assertIngredientFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Inventory expectedInventory = new Inventory(actualModel.getInventory());
        List<Ingredient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredIngredientList());

        assertThrows(IngredientNotFoundException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInventory, actualModel.getInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredIngredientList());
    }


    /**
     * Updates {@code model}'s filtered list to show only the ingredient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */

    public static void showIngredientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredIngredientList().size());

        Ingredient ingredient = model.getFilteredIngredientList().get(targetIndex.getZeroBased());
        final String[] splitName = ingredient.getName().fullName.split("\\s+");
        model.updateFilteredIngredientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredIngredientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the recipe with the given {@code uuid} in the
     * {@code model}'s recipe book.
     */

    public static void showRecipeAtUuid(Model model, UniqueId uuid) {
        model.updateFilteredRecipeList(new RecipeUuidMatchesPredicate(uuid));
        assertEquals(1, model.getFilteredRecipeList().size());
    }
}
