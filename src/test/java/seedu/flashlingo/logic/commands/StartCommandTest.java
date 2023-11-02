package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.testutil.Assert.assertThrows;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;

/**
 * Starts a new session of reviewing.
 */
public class StartCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_start_success() {
        assertThrows(CommandException.class, () -> new StartCommand().execute(model));
    }
    @Test
    public void equals() {
        StartCommand startFirstCommand = new StartCommand();

        // same object -> returns true
        assertTrue(startFirstCommand.equals(startFirstCommand));

        // same values -> returns true
        StartCommand startFirstCommandCopy = new StartCommand();
        assertTrue(startFirstCommand.equals(startFirstCommandCopy));

        // different types -> returns false
        assertFalse(startFirstCommand.equals(1));

        // null -> returns false
        assertFalse(startFirstCommand.equals(null));
    }
}
