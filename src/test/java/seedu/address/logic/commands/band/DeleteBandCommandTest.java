package seedu.address.logic.commands.band;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MUSICIAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MUSICIAN;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.typicalentities.TypicalBands.ACE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.band.DeleteBandCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.musician.DeleteCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.band.Band;
import seedu.address.model.UserPrefs;
import seedu.address.model.musician.Musician;
import seedu.address.testutil.BandBuilder;

public class DeleteBandCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        Band bandToDelete = model.getFilteredBandList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        DeleteBandCommand deleteBandCommand = new DeleteBandCommand(INDEX_FIRST_MUSICIAN);

        String expectedMessage = String.format(DeleteBandCommand.MESSAGE_DELETE_BAND_SUCCESS,
                Messages.format(bandToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteBand(bandToDelete);

        assertCommandSuccess(deleteBandCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMusicianList().size() + 1);
        DeleteBandCommand deleteBandCommand = new DeleteBandCommand(outOfBoundIndex);

        assertCommandFailure(deleteBandCommand, model, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBandAtIndex(model, INDEX_FIRST_MUSICIAN);

        Band bandToDelete = model.getFilteredBandList().get(INDEX_FIRST_MUSICIAN.getZeroBased());
        DeleteBandCommand deleteBandCommand = new DeleteBandCommand(INDEX_FIRST_MUSICIAN);

        String expectedMessage = String.format(DeleteBandCommand.MESSAGE_DELETE_BAND_SUCCESS,
                Messages.format(bandToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteBand(bandToDelete);

        assertCommandSuccess(deleteBandCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBandAtIndex(model, INDEX_FIRST_MUSICIAN);

        Index outOfBoundIndex = INDEX_SECOND_MUSICIAN;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getBandList().size());

        DeleteBandCommand deleteBandCommand = new DeleteBandCommand(outOfBoundIndex);

        assertCommandFailure(deleteBandCommand, model, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBandCommand deleteFirstCommand = new DeleteBandCommand(INDEX_FIRST_MUSICIAN);
        DeleteBandCommand deleteSecondCommand = new DeleteBandCommand(INDEX_SECOND_MUSICIAN);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBandCommand deleteFirstCommandCopy = new DeleteBandCommand(INDEX_FIRST_MUSICIAN);
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
        DeleteBandCommand deleteBandCommand = new DeleteBandCommand(targetIndex);
        String expected = DeleteBandCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteBandCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBand(Model model) {
        model.updateFilteredBandList(p -> false);

        assertTrue(model.getFilteredBandList().isEmpty());
    }
}

