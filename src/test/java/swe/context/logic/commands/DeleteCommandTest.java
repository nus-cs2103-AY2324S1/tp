package swe.context.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.logic.commands.CommandTestUtil.assertCommandFailure;
import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.logic.commands.CommandTestUtil.showContactAtIndex;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;
import swe.context.testutil.TestData;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TestData.Valid.Contact.getTypicalContacts(), new Settings());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TestData.IndexContact.FIRST_CONTACT);

        String expectedMessage = String.format(Messages.DELETE_COMMAND_SUCCESS,
                Contact.format(contactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(contactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);

        Contact contactToDelete =
                model.getFilteredContactList().get(TestData.IndexContact.FIRST_CONTACT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(TestData.IndexContact.FIRST_CONTACT);

        String expectedMessage = String.format(Messages.DELETE_COMMAND_SUCCESS,
                Contact.format(contactToDelete));

        Model expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(contactToDelete);
        showNoContact(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, TestData.IndexContact.FIRST_CONTACT);

        Index outOfBoundIndex = TestData.IndexContact.SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContacts().getUnmodifiableList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(TestData.IndexContact.FIRST_CONTACT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(TestData.IndexContact.SECOND_CONTACT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(TestData.IndexContact.FIRST_CONTACT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoContact(Model model) {
        model.setContactsFilter(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }
}
