package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Theme;

/**
 * Parses input arguments and creates a new ThemeCommand object
 */
public class ThemeCommandParser implements ParserBasic<ThemeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ThemeCommand
     * and returns a ThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ThemeCommand parse(String userInput) throws ParseException {
        try {
            Theme theme = ParserUtil.parseTheme(userInput);
            return new ThemeCommand(theme);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE), pe);
        }
    }
}
