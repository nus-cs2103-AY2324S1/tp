package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand firstHelpCommand = new HelpCommand();
        HelpCommand secondHelpCommand = new HelpCommand();

        // same object -> returns true
        assertTrue(firstHelpCommand.equals(firstHelpCommand));

        // same type -> returns true
        assertTrue(firstHelpCommand.equals(secondHelpCommand));

        // different types -> returns false
        assertFalse(firstHelpCommand.equals(1));

        // null -> returns false
        assertFalse(firstHelpCommand.equals(null));
    }
}
