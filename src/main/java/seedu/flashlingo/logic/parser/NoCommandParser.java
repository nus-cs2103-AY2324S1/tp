package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.flashlingo.logic.commands.NoCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new FindCommand object
 */
public class NoCommandParser implements Parser<NoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NoCommand
     * and returns a NoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.equals("no")) {
            throw new ParseException(
              String.format(MESSAGE_INVALID_COMMAND_FORMAT));
        }
        return new NoCommand();
    }

}
