package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    public void equals() {
        HelpCommand helpCommand = new HelpCommand(true);
        HelpCommand helpCommandAdd = new HelpCommand(true, AddCommand.COMMAND_WORD);
        HelpCommand helpCommandDelete = new HelpCommand(true, DeleteCommand.COMMAND_WORD);

        // same object -> returns true
        assertTrue(helpCommand.equals(helpCommand));

        // same values -> returns true
        assertTrue(helpCommand.equals(new HelpCommand(true)));

        // different types -> returns false
        assertFalse(helpCommand.equals(1));

        // null -> returns false
        assertFalse(helpCommand.equals(null));

        // different values -> returns false
        assertFalse(helpCommand.equals(helpCommandAdd));
        assertFalse(helpCommandAdd.equals(helpCommandDelete));
    }
}
