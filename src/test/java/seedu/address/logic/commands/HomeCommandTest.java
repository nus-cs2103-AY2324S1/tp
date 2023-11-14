package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HomeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HomeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_home_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false);
        assertCommandSuccess(new HomeCommand(), model, expectedCommandResult, expectedModel);
    }
}
