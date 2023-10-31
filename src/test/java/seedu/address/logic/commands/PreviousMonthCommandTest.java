package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.PreviousMonthCommand.MESSAGE_PREVIOUS_MONTH_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class PreviousMonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_previousMonth_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_PREVIOUS_MONTH_ACKNOWLEDGEMENT, false, false, false, false, true, false);
        assertCommandSuccess(new PreviousMonthCommand(), model, expectedCommandResult, expectedModel);
    }
}
