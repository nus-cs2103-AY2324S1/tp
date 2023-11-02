package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exceptions.RandomIndexNotInitialisedException;

public class RandomCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_command_setsRandomIndexCorrectly() {
        RandomCommand randomCommand = new RandomCommand();
        try {
            randomCommand.execute(model);
        } catch (CommandException c) {
            fail();
        }

        try {
            model.getRandomIndex();
        } catch (RandomIndexNotInitialisedException r) {
            fail();
        }
    }

    @Test
    public void equals() {
        final RandomCommand standardCommand = new RandomCommand();

        // same values -> returns false because every RandomCommand has a random outcome
        assertFalse(standardCommand.equals(new RandomCommand()));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));
    }

}
