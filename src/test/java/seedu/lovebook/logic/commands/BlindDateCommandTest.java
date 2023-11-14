package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.Model;


public class BlindDateCommandTest {
    private Model model;
    private Model expectedModel;

    @Test
    public void equals() {
        BlindDateCommand blindDateCommand = new BlindDateCommand();
        BlindDateCommand blindDateCommandCopy = new BlindDateCommand();

        // same object -> returns true
        assertTrue(blindDateCommand.equals(blindDateCommand));

        // same values -> returns true
        assertTrue(blindDateCommand.equals(blindDateCommandCopy));

        // different types -> returns false
        assertFalse(blindDateCommand.equals(1));

        // null -> returns false
        assertFalse(blindDateCommand.equals(null));
    }
}
