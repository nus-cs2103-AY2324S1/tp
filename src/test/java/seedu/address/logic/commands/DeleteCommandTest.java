package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtUuid;
import static seedu.address.testutil.TypicalIngredients.getTypicalInventory;
import static seedu.address.testutil.TypicalRecipe.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueId;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalInventory(), new UserPrefs(), getTypicalRecipeBook());

    @Test
    public void execute_validUuidUnfilteredList_success() {
        Recipe recipeToDelete = model.getRecipe(new UniqueId(1));
        DeleteCommand deleteCommand = new DeleteCommand(new UniqueId(1));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RECIPE_SUCCESS,
                new UniqueId(1));

        ModelManager expectedModel = new ModelManager(model.getInventory(), new UserPrefs(), model.getRecipeBook());
        expectedModel.deleteRecipe(recipeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidUuidUnfilteredList_throwsCommandException() {
        int outOfBound = model.getFilteredRecipeList().size() + 1;
        DeleteCommand deleteCommand = new DeleteCommand(new UniqueId(outOfBound));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_RECIPE_DOES_NOT_EXIST);
    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showRecipeAtUuid(model, new UniqueId(1));
        Recipe recipeToDelete = model.getFilteredRecipeList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(new UniqueId(1));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_RECIPE_SUCCESS, 1);


        Model expectedModel = new ModelManager(model.getInventory(), new UserPrefs(), model.getRecipeBook());
        expectedModel.deleteRecipe(recipeToDelete);
        showNoRecipe(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        UniqueId outOfBoundUuid = new UniqueId(3);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundUuid);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_RECIPE_DOES_NOT_EXIST);
    }


    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(new UniqueId(1));
        DeleteCommand deleteSecondCommand = new DeleteCommand(new UniqueId(2));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(new UniqueId(1));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteCommand deleteCommand = new DeleteCommand(new UniqueId(1));
        String expected = DeleteCommand.class.getCanonicalName() + "{uuid=" + 1 + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRecipe(Model model) {
        model.updateFilteredRecipeList(p -> false);

        assertTrue(model.getFilteredRecipeList().isEmpty());
    }
}
