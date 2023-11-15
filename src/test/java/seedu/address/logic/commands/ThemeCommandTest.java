package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ThemeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class ThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_lightTheme_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false, false,
                true, false);
        assertCommandSuccess(new ThemeCommand(true), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_darkTheme_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false, false,
                false, true);
        assertCommandSuccess(new ThemeCommand(false), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_mutlipleThemes_success() {
        CommandResult expectedDarkCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false, false,
                false, true);
        assertCommandSuccess(new ThemeCommand(false), model, expectedDarkCommandResult, expectedModel);
        CommandResult expectedLightCommandResult = new CommandResult(MESSAGE_SUCCESS, false, false, false,
                true, false);
        assertCommandSuccess(new ThemeCommand(true), model, expectedLightCommandResult, expectedModel);
    }

    @Test
    public void equals() {

        boolean isLightTheme = true;
        boolean isNotLightTheme = false;

        ThemeCommand firstCommand = new ThemeCommand(isLightTheme);
        ThemeCommand secondCommand = new ThemeCommand(isNotLightTheme);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ThemeCommand firstCommandCopy = new ThemeCommand(isLightTheme);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different sort commands -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }



}
