package transact.logic.commands;

import static transact.logic.commands.CommandTestUtil.assertCommandSuccess;
import static transact.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import transact.model.Model;
import transact.model.ModelManager;
import transact.ui.MainWindow.TabWindow;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, TabWindow.CURRENT, false,
                true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
