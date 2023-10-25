package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalMusicians.getTypicalAddressBook;

class ListBandCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute() {
    }

    @Test
    public void equals() {
    }

    @Test
    public void toStringMethod() {
        BandNameContainsKeywordsPredicate predicate = new BandNameContainsKeywordsPredicate("Prima Donna");
        ListBandCommand findCommand = new ListBandCommand(predicate);
        String expected = ListBandCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}