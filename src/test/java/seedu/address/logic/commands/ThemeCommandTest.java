package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_changeTheme_success() {
        String theme = "dark";
        String themePath = "/view/DarkTheme.css";
        CommandResult expectedCommandResult = new CommandResult(String.format(Messages.MESSAGE_THEME_SHOWN, theme),
            themePath);
        assertCommandSuccess(new ThemeCommand(themePath, theme), model, expectedCommandResult, expectedModel);
    }
}
