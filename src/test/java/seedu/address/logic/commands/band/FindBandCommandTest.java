package seedu.address.logic.commands.band;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.band.FindBandCommand.MESSAGE_NO_BAND_IN_LIST;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getOneBandAddressBook;
import static seedu.address.testutil.typicalentities.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.typicalentities.TypicalBands.ACE;
import static seedu.address.testutil.typicalentities.TypicalBands.BOOM;
import static seedu.address.testutil.typicalentities.TypicalBands.CANDY;
import static seedu.address.testutil.typicalentities.TypicalBands.DRAGON;
import static seedu.address.testutil.typicalentities.TypicalBands.ELISE;
import static seedu.address.testutil.typicalentities.TypicalMusicians.ALICE;
import static seedu.address.testutil.typicalentities.TypicalMusicians.BENSON;
import static seedu.address.testutil.typicalentities.TypicalMusicians.CARL;
import static seedu.address.testutil.typicalentities.TypicalMusicians.DANIEL;
import static seedu.address.testutil.typicalentities.TypicalMusicians.ELLE;
import static seedu.address.testutil.typicalentities.TypicalMusicians.FIONA;
import static seedu.address.testutil.typicalentities.TypicalMusicians.GEORGE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;


class FindBandCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validBandNameNoMusician_success() {
        String expectedMessage = String.format(FindBandCommand.MESSAGE_SUCCESS, 0, "Ace Jazz");
        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("ACE JAZZ");
        expectedModel.updateFilteredBandMusicianList(predicate);

        FindBandCommand command = new FindBandCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredMusicianList());
        assertEquals(Arrays.asList(ACE), model.getFilteredBandList());
    }

    @Test
    void execute_validBandNameHasMusician_success() {
        String expectedMessage = String.format(FindBandCommand.MESSAGE_SUCCESS, 2, "Dragon Metal");
        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("dragon metal");
        expectedModel.updateFilteredBandMusicianList(predicate);

        FindBandCommand command = new FindBandCommand(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL), model.getFilteredMusicianList());
        assertEquals(Arrays.asList(DRAGON), model.getFilteredBandList());
    }

    @Test
    void execute_noBandInList_throwsCommandException() {
        // First initialise empty AddressBooks
        model = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("test");
        expectedModel.updateFilteredBandMusicianList(predicate);

        // Message should be shown, and no runtime exception should be thrown.
        FindBandCommand command = new FindBandCommand(predicate);
        assertCommandFailure(command, model, MESSAGE_NO_BAND_IN_LIST);
        assertEquals(List.of(), model.getFilteredMusicianList()); // Empty musician list
        assertEquals(List.of(), model.getFilteredBandList()); // Empty band list

        // Reset model and expectedModel back for other tests
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_invalidBandNameListLengthOne_throwsCommandException() {
        // Reset model and expectedModel to contain only one band
        model = new ModelManager(getOneBandAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getOneBandAddressBook(), new UserPrefs());

        // unit test
        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("Black Pink");
        expectedModel.updateFilteredBandMusicianList(predicate);

        FindBandCommand command = new FindBandCommand(predicate);
        assertCommandFailure(command, model, Messages.MESSAGE_UNKNOWN_BAND);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredMusicianList());
        assertEquals(Arrays.asList(DRAGON), model.getFilteredBandList());

        // Reset model and expectedModel back for other tests
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_invalidBandNameListLengthMoreThanOne_throwsCommandException() {
        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("Meowmeow");
        expectedModel.updateFilteredBandMusicianList(predicate);

        FindBandCommand command = new FindBandCommand(predicate);
        assertCommandFailure(command, model, Messages.MESSAGE_UNKNOWN_BAND);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredMusicianList());
        assertEquals(Arrays.asList(ACE, BOOM, CANDY, DRAGON, ELISE), model.getFilteredBandList());
    }



    @Test
    public void equals() {
        BandNameContainsKeywordsPredicate firstPredicate =
                new BandNameContainsKeywordsPredicate("first_band");
        BandNameContainsKeywordsPredicate secondPredicate =
                new BandNameContainsKeywordsPredicate("second_band");

        FindBandCommand listBandFirstCommand = new FindBandCommand(firstPredicate);
        FindBandCommand listBandSecondCommand = new FindBandCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listBandFirstCommand.equals(listBandFirstCommand));

        // same values -> returns true
        BandNameContainsKeywordsPredicate firstPredicateCopy =
                new BandNameContainsKeywordsPredicate("first_band");
        FindBandCommand listBandFirstCommandDeepCopy = new FindBandCommand(firstPredicateCopy);
        FindBandCommand listBandFirstCommandCopy = new FindBandCommand(firstPredicate);
        assertTrue(listBandFirstCommand.equals(listBandFirstCommandDeepCopy));
        assertTrue(listBandFirstCommand.equals(listBandFirstCommandCopy));

        // different types -> returns false
        assertFalse(listBandFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listBandFirstCommand.equals(null));

        // different musician -> returns false
        assertFalse(listBandFirstCommand.equals(listBandSecondCommand));
    }

    @Test
    public void toStringMethod() {
        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("Prima Donna");
        FindBandCommand findBandCommand = new FindBandCommand(predicate);
        String expected = FindBandCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findBandCommand.toString());
    }
}
