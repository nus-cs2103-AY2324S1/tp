package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashlingo.logic.commands.YesCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class YesCommandParser implements Parser<YesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the YesCommand
     * and returns a YesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public YesCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.equals("yes")) {
            throw new ParseException(
              String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
        return new YesCommand(trimmedArgs);
    }

}
