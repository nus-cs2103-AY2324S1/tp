package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_RECIPE_DOES_NOT_EXIST;
import static seedu.address.logic.Messages.MESSAGE_RECIPE_LISTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipeUuidMatchesPredicate;
import seedu.address.model.recipe.UniqueId;

/**
 * Contains integration tests (interaction with the Model) for {@code RecipeViewCommand}.
 */
public class RecipeViewCommandTest {
    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(), getTypicalRecipeBook());
    private Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(), getTypicalRecipeBook());

    @Test
    public void equals() {
        UniqueId uuid1 = new UniqueId(1);
        UniqueId uuid2 = new UniqueId(2);

        RecipeUuidMatchesPredicate firstPredicate = new RecipeUuidMatchesPredicate(uuid1);
        RecipeUuidMatchesPredicate secondPredicate = new RecipeUuidMatchesPredicate(uuid2);

        RecipeViewCommand recipeViewFirstCommand = new RecipeViewCommand(firstPredicate);
        RecipeViewCommand recipeViewSecondCommand = new RecipeViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(recipeViewFirstCommand.equals(recipeViewFirstCommand));

        // same values -> returns true
        RecipeViewCommand recipeViewFirstCommandCopy = new RecipeViewCommand(firstPredicate);
        assertTrue(recipeViewFirstCommand.equals(recipeViewFirstCommandCopy));

        // different types -> returns false
        assertFalse(recipeViewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(recipeViewFirstCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(recipeViewFirstCommand.equals(recipeViewSecondCommand));
    }

    //TODO may not be the correct way to test groups of inputs might need to change test
    @Test
    public void execute_validUuid_recipeFound() {
        UniqueId uuid1 = new UniqueId(1);
        UniqueId uuid2 = new UniqueId(2);

        String expectedMessage1 = String.format(MESSAGE_RECIPE_LISTED, uuid1);
        String expectedMessage2 = String.format(MESSAGE_RECIPE_LISTED, uuid2);

        // boundary value analysis, using uuid 1 and 2
        RecipeUuidMatchesPredicate predicate1 = new RecipeUuidMatchesPredicate(uuid1);
        RecipeUuidMatchesPredicate predicate2 = new RecipeUuidMatchesPredicate(uuid2);
        RecipeViewCommand command1 = new RecipeViewCommand(predicate1);
        RecipeViewCommand command2 = new RecipeViewCommand(predicate2);

        // testing command1
        expectedModel.updateFilteredRecipeList(predicate1);
        assertCommandSuccess(command1, model, expectedMessage1, expectedModel);

        // testing command2
        expectedModel.updateFilteredRecipeList(predicate2);
        assertCommandSuccess(command2, model, expectedMessage2, expectedModel);
    }

    @Test
    public void execute_invalidUuid_throwsInvalidUuidException() {
        String expectedMessage = MESSAGE_RECIPE_DOES_NOT_EXIST;

        UniqueId uuid1 = new UniqueId(0);
        UniqueId uuid2 = new UniqueId(-1);

        // boundary value analysis, using uuid 0 and -1
        RecipeUuidMatchesPredicate predicate1 = new RecipeUuidMatchesPredicate(uuid1);
        RecipeUuidMatchesPredicate predicate2 = new RecipeUuidMatchesPredicate(uuid2);
        RecipeViewCommand command1 = new RecipeViewCommand(predicate1);
        RecipeViewCommand command2 = new RecipeViewCommand(predicate2);

        // testing command1
        expectedModel.updateFilteredRecipeList(predicate1);
        assertCommandFailure(command1, model, expectedMessage);

        // testing command2
        expectedModel.updateFilteredRecipeList(predicate2);
        assertCommandFailure(command2, model, expectedMessage);
    }

    /*
    todo not sure if should have separate exceptions for searching uuid that are deleted using index for
    todo getrecipe may not be appropriate either for the recipe is dictated by uuid
    */
    @Test
    public void execute_deletedUuidRecipe_throwsInvalidUuidException() {
        String expectedMessage = MESSAGE_RECIPE_DOES_NOT_EXIST;

        UniqueId uuid1 = new UniqueId(1);

        Recipe deletedRecipe = model.getRecipe(uuid1);
        model.deleteRecipe(deletedRecipe);

        RecipeUuidMatchesPredicate predicate = new RecipeUuidMatchesPredicate(uuid1);
        RecipeViewCommand command = new RecipeViewCommand(predicate);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void toStringMethod() {
        UniqueId uuid1 = new UniqueId(1);
        RecipeUuidMatchesPredicate predicate = new RecipeUuidMatchesPredicate(uuid1);
        RecipeViewCommand recipeViewCommand = new RecipeViewCommand(predicate);
        String expected = RecipeViewCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, recipeViewCommand.toString());
    }
}
