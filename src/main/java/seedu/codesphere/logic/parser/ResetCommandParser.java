package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.codesphere.logic.commands.ResetCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ResetCommand object
 */
public class ResetCommandParser implements Parser<ResetCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ResetCommand
     * and returns a ResetCommand object for execution.
     * @throws ParseException If the user input does not conform to the expected format
     */
    public ResetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        if (argMultimap.getPreamble().isEmpty()) {
            return new ResetCommand();
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResetCommand.MESSAGE_USAGE));
    }
}
