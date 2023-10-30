package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ThemeCommand.SHOWING_THEME_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {
    private final Model model = new ModelManager();
    private final Model expectedModel = new ModelManager();
    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_THEME_MESSAGE,
                false, false, false, true);
        assertCommandSuccess(new ThemeCommand(), model, expectedCommandResult, expectedModel, commandHistory);
    }
}
