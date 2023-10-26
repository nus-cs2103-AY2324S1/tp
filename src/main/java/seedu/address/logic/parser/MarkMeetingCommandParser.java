package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkMeetingCommand object
 */
public class MarkMeetingCommandParser implements Parser<MarkMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkMeetingCommand
     * and returns a MarkMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkMeetingCommand.MESSAGE_USAGE), pe);
        }
    }

}
