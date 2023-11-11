package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Theme;

/**
 * Changes the theme of the application.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String MESSAGE_SUCCESS = "Theme set to %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the theme to that specified by the user.\n"
            + "Parameters: TYPE (either dark or light)\n"
            + "Example: " + COMMAND_WORD + " "
            + "dark";

    private Theme theme;
    /**
     * Creates a ThemeCommand to set the theme to the specified {@code Theme}
     */
    public ThemeCommand(Theme theme) {
        assert theme != null;
        this.theme = theme;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setTheme(theme);
        model.commit();
        return new CommandResult(String.format(MESSAGE_SUCCESS, theme.toString()));
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
        return theme.equals(otherThemeCommand.theme);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("theme", theme)
                .toString();
    }
}
