package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class TreeCommandTest {

    @Test
    public void execute_treeCommand_success() {
        Model model = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(
                TreeCommand.MESSAGE_SUCCESS, false, false,
                false, false, true, false, false, false);
        TreeCommand command = new TreeCommand();

        CommandResult result = command.execute(model);

        assertEquals(expectedCommandResult, result);
    }
}
