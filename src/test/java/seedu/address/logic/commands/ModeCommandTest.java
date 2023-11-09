package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ModeCommand.MESSAGE_TOGGLED;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ModeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_toggleMode_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_TOGGLED, false, false, true);
        assertCommandSuccess(new ModeCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        ModeCommand firstModeCommand = new ModeCommand();
        ModeCommand secondModeCommand = new ModeCommand();

        // same object -> returns true
        assertTrue(firstModeCommand.equals(firstModeCommand));

        // same type -> returns true
        assertTrue(firstModeCommand.equals(secondModeCommand));

        // different types -> returns false
        assertFalse(firstModeCommand.equals(1));

        // null -> returns false
        assertFalse(firstModeCommand.equals(null));
    }
}
