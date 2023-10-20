package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.Model;


public class RandomCommandTest {
    private Model model;
    private Model expectedModel;

    @Test
    public void equals() {
        RandomCommand randomCommand = new RandomCommand();
        RandomCommand randomCommandCopy = new RandomCommand();

        // same object -> returns true
        assertTrue(randomCommand.equals(randomCommand));

        // same values -> returns true
        assertTrue(randomCommand.equals(randomCommandCopy));

        // different types -> returns false
        assertFalse(randomCommand.equals(1));

        // null -> returns false
        assertFalse(randomCommand.equals(null));
    }
}
