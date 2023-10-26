package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typicalentities.TypicalMusicians.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

class FindBandCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_validBandNameNoMusician_success() {
        // expected

        FindBandCommand command = new FindBandCommand(new BandNameContainsKeywordsPredicate("ACE"));

//        String expectedMessage = String.format(MESSAGE_MUSICIANS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = prepareNameKeywordsPredicate("Kurz Elle Kunz");
//
//        HashSet<Predicate<Musician>> predicates = new HashSet<>(Arrays.asList(predicate));
//        FindCommand command = new FindCommand(predicates);
//        expectedModel.updateFilteredMusicianList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredMusicianList());
    }

    @Test
    void execute_validBandNameHasMusician_success() {

    }

    @Test
    void execute_invalidBandNameListLengthOne_exceptionThrown() {

    }

    @Test
    void execute_invalidBandNameListLengthLong_exceptionThrown() {

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
