package swe.context.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swe.context.logic.commands.CommandTestUtil.assertCommandFailure;
import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swe.context.logic.commands.CommandTestUtil.showContactAtIndex;
import static swe.context.testutil.TestData.IndexContact.FIRST_CONTACT;
import static swe.context.testutil.TestData.IndexContact.SECOND_CONTACT;
import static swe.context.testutil.TestData.Valid.Contact.getTypicalContacts;

import java.util.List;

import org.junit.jupiter.api.Test;

import swe.context.commons.core.index.Index;
import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;
import swe.context.model.Settings;
import swe.context.model.contact.Contact;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalContacts(), new Settings());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToDelete = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(List.of(FIRST_CONTACT));

        String expectedMessage = String.format(Messages.DELETE_COMMAND_SUCCESS,
                Contact.format(contactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(contactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, FIRST_CONTACT);

        Contact contactToDelete = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(List.of(FIRST_CONTACT));

        String expectedMessage = String.format(Messages.DELETE_COMMAND_SUCCESS,
                Contact.format(contactToDelete));

        Model expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(contactToDelete);
        showNoContact(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, FIRST_CONTACT);

        Index outOfBoundIndex = SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContacts().getUnmodifiableList().size());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(List.of(FIRST_CONTACT));
        DeleteCommand deleteSecondCommand = new DeleteCommand(List.of(SECOND_CONTACT));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(List.of(FIRST_CONTACT));
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
        List<Index> targetIndices = List.of(Index.fromOneBased(1), Index.fromOneBased(2));
        DeleteCommand deleteCommand = new DeleteCommand(targetIndices);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndices=" + targetIndices + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoContact(Model model) {
        model.setContactsFilter(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }

    @Test
    public void execute_validIndicesUnfilteredList_success() {
        Contact firstContactToDelete = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());
        Contact secondContactToDelete = model.getFilteredContactList().get(SECOND_CONTACT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(List.of(FIRST_CONTACT, SECOND_CONTACT));

        String expectedMessage = String.format(Messages.DELETE_COMMAND_SUCCESS,
                Contact.format(firstContactToDelete) + ",\n" + Contact.format(secondContactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(firstContactToDelete);
        expectedModel.removeContact(secondContactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unorderedIndicesUnfilteredList_success() {
        Contact firstContactToDelete = model.getFilteredContactList().get(FIRST_CONTACT.getZeroBased());
        Contact secondContactToDelete = model.getFilteredContactList().get(SECOND_CONTACT.getZeroBased());

        // Providing the SECOND_CONTACT before the FIRST_CONTACT in the list.
        List<Index> unorderedIndices = List.of(SECOND_CONTACT, FIRST_CONTACT);
        DeleteCommand deleteCommand = new DeleteCommand(unorderedIndices);

        String expectedMessage = String.format(Messages.DELETE_COMMAND_SUCCESS,
                Contact.format(secondContactToDelete) + ",\n" + Contact.format(firstContactToDelete));

        ModelManager expectedModel = new ModelManager(model.getContacts(), new Settings());
        expectedModel.removeContact(firstContactToDelete);
        expectedModel.removeContact(secondContactToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someInvalidIndicesUnfilteredList_throwsCommandException() {
        Index validIndex = FIRST_CONTACT;
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);

        // Providing a valid index followed by an invalid one.
        List<Index> indices = List.of(validIndex, outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indices);

        assertCommandFailure(deleteCommand, model, Messages.INVALID_CONTACT_DISPLAYED_INDEX);
    }
}
