package swe.context.logic.commands;

import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.logic.commands.CommandResult;
import swe.context.logic.commands.ExitCommand;
import swe.context.model.Model;
import swe.context.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(Messages.COMMAND_EXIT_SUCCESS, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
