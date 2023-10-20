package seedu.address.logic.commands.musician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMusicianAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MUSICIAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MUSICIAN;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.musician.Musician;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Musician musicianToDelete = model.getFilteredMusicianList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MUSICIAN);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MUSICIAN_SUCCESS,
                Messages.format(musicianToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteMusician(musicianToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMusicianList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMusicianAtIndex(model, INDEX_FIRST_MUSICIAN);

        Musician musicianToDelete = model.getFilteredMusicianList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_MUSICIAN);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MUSICIAN_SUCCESS,
                Messages.format(musicianToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteMusician(musicianToDelete);
        showNoMusician(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMusicianAtIndex(model, INDEX_FIRST_MUSICIAN);

        Index outOfBoundIndex = INDEX_SECOND_MUSICIAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMusicianList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_MUSICIAN);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_MUSICIAN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_MUSICIAN);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different musician -> returns false
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
    private void showNoMusician(Model model) {
        model.updateFilteredMusicianList(p -> false);

        assertTrue(model.getFilteredMusicianList().isEmpty());
    }
}
