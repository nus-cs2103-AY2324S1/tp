package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles different themes for the class manager.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles different themes for the class manager.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_THEME_MESSAGE = "Different theme has been toggled.";

    @Override
    public CommandResult execute(Model model) {
        model.toggleColorTheme();
        return new CommandResult(SHOWING_THEME_MESSAGE, false, false, false, true);
    }
}
