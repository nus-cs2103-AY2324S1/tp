package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;
import static seedu.address.testutil.TypicalIngredients.EGG;
import static seedu.address.testutil.TypicalIngredients.FLOUR;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code StockCommand}.
 */
public class StockCommandTest {
    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(), new RecipeBook());
    private Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(), new RecipeBook());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        StockCommand stockFirstCommand = new StockCommand(firstPredicate);
        StockCommand stockSecondCommand = new StockCommand(secondPredicate);

        // same object -> returns true
        assertTrue(stockFirstCommand.equals(stockFirstCommand));

        // same values -> returns true
        StockCommand stockFirstCommandCopy = new StockCommand(firstPredicate);
        assertTrue(stockFirstCommand.equals(stockFirstCommandCopy));

        // different types -> returns false
        assertFalse(stockFirstCommand.equals(1));

        // null -> returns false
        assertFalse(stockFirstCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(stockFirstCommand.equals(stockSecondCommand));
    }


    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_zeroKeywords_noIngredientFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        StockCommand command = new StockCommand(predicate);
        expectedModel.updateFilteredIngredientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredIngredientList());
    }

    @Test
    public void execute_zeroKeywords_ingredientFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENTS_LISTED_OVERVIEW, 2);
        StockCommand command = new StockCommand(PREDICATE_SHOW_ALL_INGREDIENTS);
        expectedModel.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLOUR, EGG), model.getFilteredIngredientList());
    }

    @Test
    public void execute_multipleKeywords_multipleIngredientsFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENTS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Flour Egg");
        StockCommand command = new StockCommand(predicate);
        expectedModel.updateFilteredIngredientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLOUR, EGG), model.getFilteredIngredientList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        StockCommand stockCommand = new StockCommand(predicate);
        String expected = StockCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, stockCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
