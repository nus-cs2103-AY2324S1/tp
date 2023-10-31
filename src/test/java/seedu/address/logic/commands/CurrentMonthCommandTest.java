package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CurrentMonthCommand.MESSAGE_CURRENT_MONTH_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class CurrentMonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_current_month_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_CURRENT_MONTH_ACKNOWLEDGEMENT, false, false, false, false, false, true);
        assertCommandSuccess(new CurrentMonthCommand(), model, expectedCommandResult, expectedModel);
    }
}
