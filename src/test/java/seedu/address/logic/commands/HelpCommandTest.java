package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;

public class HelpCommandTest {
    private SeplendidModel model = new SeplendidModelManager();
    private SeplendidModel expectedModel = new SeplendidModelManager();


    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertSeplendidCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
