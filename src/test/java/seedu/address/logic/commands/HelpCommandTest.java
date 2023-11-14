package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertRegularCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandresults.RegularCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        RegularCommandResult expectedCommandResult = new RegularCommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertRegularCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
