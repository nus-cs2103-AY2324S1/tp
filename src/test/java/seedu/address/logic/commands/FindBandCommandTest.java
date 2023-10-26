package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

class FindBandCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute() {

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
