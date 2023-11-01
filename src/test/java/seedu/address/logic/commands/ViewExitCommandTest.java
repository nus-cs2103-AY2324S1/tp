package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewExitCommand.MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ViewExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_viewExit_detailsNotFilled() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS,
                null,
                null,
                CommandType.VIEW_EXIT,
                false
        );
        assertCommandSuccess(new ViewExitCommand(null), model, expectedCommandResult, expectedModel);
    }
}
