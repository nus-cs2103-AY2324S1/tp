package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_showAll_success() {
        assertCommandSuccess(new HelpCommand(true), model, Messages.getHelpMessageForAll(), expectedModel);
    }

    @Test
    public void execute_unrecognizable_success() {
        assertCommandSuccess(new HelpCommand(false, AddCommand.COMMAND_WORD),
            model,
            Messages.getHelpMessageForUnrecognizableCommand(AddCommand.COMMAND_WORD),
            expectedModel);
    }
}
