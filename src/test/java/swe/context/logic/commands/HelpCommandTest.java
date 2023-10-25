package swe.context.logic.commands;

import static swe.context.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.model.Model;
import swe.context.model.ModelManager;



public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(Messages.HELP_COMMAND_SHOW_HELP, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
