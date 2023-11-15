package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format theme of the address book.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the theme of NUSCoursemates "
            + "to dark or light mode.\n"
            + "Parameters: THEME \n"
            + "Example: " + COMMAND_WORD + " dark";

    public static final String MESSAGE_SUCCESS = "Changed theme.";

    public final boolean isLight;

    /**
     * Constructs a ThemeCommand to change the theme of NUSCoursemates.
     *
     * @param isLight A boolean indicating whether the theme should be set to light mode.
     *                If true, the theme will be set to light mode; if false, it will be set to dark mode.
     * @throws AssertionError if isLight is not a valid boolean value (either true or false).
     */
    public ThemeCommand(boolean isLight) {
        assert (isLight == true || isLight == false) : "isLight should be a valid boolean value";
        this.isLight = isLight;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, false,
                isLight, !isLight);

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
        return isLight == otherThemeCommand.isLight;
    }

}
