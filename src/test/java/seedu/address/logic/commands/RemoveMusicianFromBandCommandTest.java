package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.band.RemoveMusicianFromBandCommand.MESSAGE_MUSICIAN_NOT_IN_BAND;
import static seedu.address.logic.commands.band.RemoveMusicianFromBandCommand.MESSAGE_REMOVE_MUSICIAN_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getOneBandAddressBook;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.typicalentities.TypicalBands.ACE;
import static seedu.address.testutil.typicalentities.TypicalBands.DRAGON;
import static seedu.address.testutil.typicalentities.TypicalBands.ELISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.band.RemoveMusicianFromBandCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;
import seedu.address.model.musician.Musician;

public class RemoveMusicianFromBandCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullMusician_throwsNullPointerException() {
        Index bandIndex = Index.fromOneBased(1);
        assertThrows(NullPointerException.class, () -> new RemoveMusicianFromBandCommand(bandIndex, null));
    }

    @Test
    public void constructor_nullBand_throwsNullPointerException() {
        Index musicianIndex = Index.fromOneBased(1);
        assertThrows(NullPointerException.class, () -> new RemoveMusicianFromBandCommand(null, musicianIndex));
    }

    @Test
    public void constructor_nullBandAndMusician_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveMusicianFromBandCommand(null, null));
    }

    @Test
    public void execute_bandIndexOutOfRange_throwsCommandException() {
        Index outOfBoundBandIndex = Index.fromOneBased(model.getFilteredBandList().size() + 1);
        Index validMusicianIndex = Index.fromOneBased(model.getFilteredMusicianList().size());
        RemoveMusicianFromBandCommand command = new RemoveMusicianFromBandCommand(
            outOfBoundBandIndex, validMusicianIndex);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
    }

    @Test
    public void execute_musicianIndexOutOfRange_throwsCommandException() {
        Index validBandIndex = Index.fromOneBased(model.getFilteredBandList().size());
        Index outOfBoundMusicianIndex = Index.fromOneBased(model.getFilteredMusicianList().size() + 1);
        RemoveMusicianFromBandCommand command = new RemoveMusicianFromBandCommand(
            validBandIndex, outOfBoundMusicianIndex);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_MUSICIAN_DISPLAYED_INDEX);
    }

    @Test
    public void execute_bandAndMusicianIndexOutOfRange_throwsCommandException() {
        Index outOfBoundBandIndex = Index.fromOneBased(model.getFilteredBandList().size() + 1);
        Index outOfBoundMusicianIndex = Index.fromOneBased(model.getFilteredMusicianList().size() + 1);
        RemoveMusicianFromBandCommand command = new RemoveMusicianFromBandCommand(
            outOfBoundBandIndex, outOfBoundMusicianIndex);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_BAND_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyBand_throwsCommandException() {
        // Update FilteredBandList to only contain an empty band
        model.updateFilteredBandList(new BandNameContainsKeywordsPredicate(ACE.getName().fullName));

        // Attempts to remove a musician from the empty band.
        // CommandException should be thrown.
        RemoveMusicianFromBandCommand command = new RemoveMusicianFromBandCommand(
            Index.fromOneBased(1), Index.fromOneBased(1));

        // Get the first musician in the FilteredMusicianList
        Musician musicianToRemove = model.getFilteredMusicianList().get(0);
        assertCommandFailure(command, model,
            String.format(MESSAGE_MUSICIAN_NOT_IN_BAND, Messages.format(musicianToRemove)));

        // Resets the model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_musicianNotInBand_throwsCommandException() {
        // Update FilteredBandList to only contain band ELISE
        model.updateFilteredBandList(new BandNameContainsKeywordsPredicate(ELISE.getName().fullName));

        // Attempts to remove a musician from the empty band.
        // CommandException should be thrown.
        RemoveMusicianFromBandCommand command = new RemoveMusicianFromBandCommand(
            Index.fromOneBased(1), Index.fromOneBased(1));

        // Get the first musician in the FilteredMusicianList
        Musician musicianToRemove = model.getFilteredMusicianList().get(0);
        assertCommandFailure(command, model,
            String.format(MESSAGE_MUSICIAN_NOT_IN_BAND, Messages.format(musicianToRemove)));

        // Resets the model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_musicianInBand_success() throws CommandException {

        // Initialises the model
        model = new ModelManager(getOneBandAddressBook(), new UserPrefs());
        model.updateFilteredBandMusicianList(new BandNameContainsKeywordsPredicate(DRAGON.getName().fullName));

        // Instantiates and executes a RemoveMusicianFromBandCommand
        RemoveMusicianFromBandCommand command = new RemoveMusicianFromBandCommand(
            Index.fromOneBased(1), Index.fromOneBased(1));
        Musician musicianToRemove = model.getFilteredMusicianList().get(0);
        CommandResult commandResult = command.execute(model);

        // Tests if command result conforms to the expected output, and that the FilteredMusicianList
        // no longer contains the musicianToRemove.
        assertEquals(String.format(MESSAGE_REMOVE_MUSICIAN_SUCCESS, Messages.format(musicianToRemove)),
            commandResult.getFeedbackToUser());
        assertFalse(model.getFilteredMusicianList().contains(musicianToRemove));

        // Now set the FilteredMusicianList to display all musicians
        model.updateFilteredMusicianList(v -> true);

        // Tests if the musician is still in the address book
        assertTrue(model.getFilteredMusicianList().contains(musicianToRemove));

        // Resets the model
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }
}
