package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TabIndex;
import seedu.address.logic.commands.exceptions.CommandException;


public class VersionedAddressBookTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private VersionedAddressBook versionedAddressBook = new VersionedAddressBook(model.getAddressBook());

    @Test
    public void commit_validModelState_successfullyCommits() {
        versionedAddressBook.commit(model, "Committing state", TabIndex.Client);

        // Ensure that the model's address book is updated
        assertEquals(model.getAddressBook(), versionedAddressBook.getCurrentState(1));

        // Ensure that the state pointer has moved to the latest state
        assertEquals(1, versionedAddressBook.getCurrentStatePointer());
    }

    @Test
    public void undo_validModelState_successfullyUndoes() throws CommandException {
        versionedAddressBook.commit(model, "Committing state", TabIndex.Client);
        versionedAddressBook.undo(model);

        // Ensure that the model's address book is updated to the previous state
        assertEquals(model.getAddressBook(), versionedAddressBook.getCurrentState(1));

        // Ensure that the state pointer has moved to the previous state
        assertEquals(0, versionedAddressBook.getCurrentStatePointer());

        // Ensure that the success message and tab index are retrieved
        assertEquals("Committing state", versionedAddressBook.getPreviousMessage());
        assertEquals(TabIndex.Client, versionedAddressBook.getPreviousTabIndex());
    }

    @Test
    public void redo_validModelState_successfullyRedoes() throws CommandException {
        versionedAddressBook.commit(model, "Committing state", TabIndex.Client);
        versionedAddressBook.undo(model);
        versionedAddressBook.redo(model);

        // Ensure that the model's address book is updated to the next state
        assertEquals(model.getAddressBook(), versionedAddressBook.getCurrentState(1));

        // Ensure that the state pointer has moved to the next state
        assertEquals(1, versionedAddressBook.getCurrentStatePointer());

        // Ensure that the success message and tab index are retrieved
        assertEquals("Committing state", versionedAddressBook.getPreviousMessageForRedo());
        assertEquals(TabIndex.Client, versionedAddressBook.getPreviousTabIndexForRedo());
    }

    @Test
    public void canUndo_noStatesToUndo_returnsFalse() {
        assertFalse(versionedAddressBook.canUndo());
    }

    @Test
    public void canRedo_noStatesToRedo_returnsFalse() {
        assertFalse(versionedAddressBook.canRedo());
    }

    @Test
    public void canUndo_hasStatesToUndo_returnsTrue() throws CommandException {
        versionedAddressBook.commit(model, "Committing state", TabIndex.Client);
        assertTrue(versionedAddressBook.canUndo());
    }

    @Test
    public void canRedo_hasStatesToRedo_returnsTrue() throws CommandException {
        versionedAddressBook.commit(model, "Committing state", TabIndex.Client);
        versionedAddressBook.undo(model);
        assertTrue(versionedAddressBook.canRedo());
    }
}
