package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.NextMonthCommand.MESSAGE_NEXT_MONTH_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class NextMonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_current_month_success() {
        CommandResult expectedCommandResult = new CommandResult(
               MESSAGE_NEXT_MONTH_ACKNOWLEDGEMENT, false, false, false, true, false, false);
        assertCommandSuccess(new NextMonthCommand(), model, expectedCommandResult, expectedModel);
    }
}
