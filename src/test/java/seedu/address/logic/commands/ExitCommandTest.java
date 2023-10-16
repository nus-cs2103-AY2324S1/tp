package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand2.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model2;
import seedu.address.model.ModelManager2;

public class ExitCommandTest {
    private Model2 model = new ModelManager2();
    private Model2 expectedModel = new ModelManager2();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand2(), model, expectedCommandResult, expectedModel);
    }
}
