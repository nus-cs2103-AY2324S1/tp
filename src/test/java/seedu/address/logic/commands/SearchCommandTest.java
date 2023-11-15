package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_RECIPE_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.RecipeIngredientNameMatchesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchCommand}.
 */
public class SearchCommandTest {
    private Model model;
    private Model expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInventory(), new UserPrefs(), getTypicalRecipeBook());
        expectedModel = new ModelManager(model.getInventory(), new UserPrefs(), getTypicalRecipeBook());
    }

    @Test
    public void equals() {
        RecipeIngredientNameMatchesPredicate firstPredicate =
                new RecipeIngredientNameMatchesPredicate("first");

        RecipeIngredientNameMatchesPredicate secondPredicate =
                new RecipeIngredientNameMatchesPredicate("second");

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different ingredient name -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }
    @Test
    public void execute_searchDisplaysOneItem_success() {
        assertSearchItemsSuccess(1, "eggs");
    }

    @Test
    public void execute_searchDisplaysMultipleItems_success() {
        assertSearchItemsSuccess(2, "flour");
    }

    @Test
    public void execute_searchDisplaysZeroItem_success() {
        assertSearchItemsSuccess(0, "Test");
    }

    @Test
    public void execute_searchNameNotCaseSensitive_success() {
        assertSearchItemsSuccess(2, "fLoUr");
    }
    @Test
    public void toStringMethod() {
        RecipeIngredientNameMatchesPredicate predicate = new RecipeIngredientNameMatchesPredicate("Flour");
        SearchCommand command = new SearchCommand(predicate);
        String expected = SearchCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
    public void assertSearchItemsSuccess(int items, String name) {
        String expectedMessage = String.format(MESSAGE_RECIPE_LISTED_OVERVIEW, items);
        RecipeIngredientNameMatchesPredicate predicate = new RecipeIngredientNameMatchesPredicate(name);
        expectedModel.updateFilteredRecipeList(predicate);
        SearchCommand command = new SearchCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
