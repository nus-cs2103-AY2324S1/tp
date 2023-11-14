package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertRegularCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        RegularCommandResult expectedCommandResult =
                new RegularCommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertRegularCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
