package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ThemeCommand.
 */
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

    @Test
    public void equals() {
        String theme = "dark";
        String themePath = "/view/DarkTheme.css";
        final ThemeCommand standardCommand = new ThemeCommand(theme, themePath);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        ThemeCommand themeCommandCopy =
            new ThemeCommand(theme, themePath);
        assertTrue(standardCommand.equals(themeCommandCopy));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different theme
        assertFalse(standardCommand.equals(
            new ThemeCommand("different theme", themePath)));

        // different end time -> returns false
        assertFalse(standardCommand.equals(
            new ThemeCommand(theme, "different theme path")));
    }
}
