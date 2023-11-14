package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HistoryCommand object
 */
public class HistoryCommandParser implements Parser<HistoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HistoryCommand
     * and returns a HistoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public HistoryCommand parse(String args) throws ParseException {
        try {
            int numberOfUserCommands = ParserUtil.parseHistory(args);
            return new HistoryCommand(numberOfUserCommands);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HistoryCommand.MESSAGE_USAGE), pe);
        }
    }

}
