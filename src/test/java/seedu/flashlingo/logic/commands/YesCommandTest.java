package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.commands.exceptions.CommandException;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.testutil.Assert;
import seedu.flashlingo.testutil.TypicalFlashCards;

/**
 * Indicates user has memorized the word.
 */
public class YesCommandTest {

    private Model model = new ModelManager(TypicalFlashCards.getTypicalFlashlingo(), new UserPrefs());
    @Test
    public void execute_yes_success() {

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());

        Assert.assertThrows(CommandException.class, () -> new YesCommand().execute(model));
    }
    @Test
    public void equals() {
        YesCommand yesFirstCommand = new YesCommand();

        // same object -> returns true
        assertTrue(yesFirstCommand.equals(yesFirstCommand));

        // same values -> returns true
        YesCommand yesFirstCommandCopy = new YesCommand();
        assertTrue(yesFirstCommand.equals(yesFirstCommandCopy));

        // different types -> returns false
        assertFalse(yesFirstCommand.equals(1));

        // null -> returns false
        assertFalse(yesFirstCommand.equals(null));

    }
}