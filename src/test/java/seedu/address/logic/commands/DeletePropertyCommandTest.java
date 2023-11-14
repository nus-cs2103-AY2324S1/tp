package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandPropertyTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandPropertyTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePropertyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Property propertyToDelete = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        DeletePropertyCommand deleteCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);

        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS,
                Messages.format(propertyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
        expectedModel.deleteProperty(propertyToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        DeletePropertyCommand deleteCommand = new DeletePropertyCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property propertyToDelete = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        DeletePropertyCommand deleteCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);

        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS,
                Messages.format(propertyToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getPropertyBook(), new UserPrefs());
        expectedModel.deleteProperty(propertyToDelete);
        showNoProperty(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of budget book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyBook().getPropertyList().size());

        DeletePropertyCommand deleteCommand = new DeletePropertyCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePropertyCommand deleteFirstCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);
        DeletePropertyCommand deleteSecondCommand = new DeletePropertyCommand(INDEX_SECOND_PROPERTY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePropertyCommand deleteFirstCommandCopy = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different property -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePropertyCommand deleteCommand = new DeletePropertyCommand(targetIndex);
        String expected = DeletePropertyCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProperty(Model model) {
        model.updateFilteredPropertyList(p -> false);

        assertTrue(model.getFilteredPropertyList().isEmpty());
    }
}
