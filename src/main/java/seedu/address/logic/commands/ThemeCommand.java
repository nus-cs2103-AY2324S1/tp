package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.model.Model;

/**
 * Change theme of the application.
 */
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes programme theme.\n"
        + "Example: " + COMMAND_WORD + " dark";

    private final String themePath;
    private final String theme;


    /**
     * Constructs a {@code ThemeCommand} with the filepath of theme styling and the name of theme.
     * @param themePath is the filepath of theme styling.
     * @param theme is the name of the theme to change to.
     */
    public ThemeCommand(String themePath, String theme) {
        this.themePath = themePath;
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(String.format(Messages.MESSAGE_THEME_SHOWN, theme), themePath);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ThemeCommand)) {
            return false;
        }

        ThemeCommand otherThemeCommand = (ThemeCommand) other;
        return themePath.equals(otherThemeCommand.themePath) && theme.equals(otherThemeCommand.theme);
    }
}





