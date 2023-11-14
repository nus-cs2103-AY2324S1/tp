package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertSeplendidCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.SeplendidModel;
import seedu.address.model.SeplendidModelManager;

public class ExitCommandTest {
    private SeplendidModel model = new SeplendidModelManager();
    private SeplendidModel expectedModel = new SeplendidModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertSeplendidCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
