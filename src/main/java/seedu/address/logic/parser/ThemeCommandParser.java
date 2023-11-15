package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input to create a ThemeCommand for changing the application's theme.
 * This class implements the Parser interface for parsing user input and creating ThemeCommand objects.
 */
public class ThemeCommandParser implements Parser<ThemeCommand> {

    private final String darkTheme = "DARK";
    private final String lightTheme = "LIGHT";
    /**
     * Parses the given {@code String} of arguments in the context of the ThemeCommand
     * and returns a ThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ThemeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toUpperCase();

        boolean isDarkTheme = trimmedArgs.equals(darkTheme);
        boolean isLightTheme = trimmedArgs.equals(lightTheme);
        if (isDarkTheme) {
            return new ThemeCommand(false);
        } else if (isLightTheme) {
            return new ThemeCommand(true);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
        }
    }
}

