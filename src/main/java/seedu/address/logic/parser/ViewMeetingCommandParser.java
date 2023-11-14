package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ViewMeetingCommand object
 */
public class ViewMeetingCommandParser implements Parser<ViewMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewMeetingCommand
     * and returns a ViewMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            assert index.getZeroBased() >= 0;
            assert index.getOneBased() >= 1;
            return new ViewMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ViewMeetingCommand.MESSAGE_USAGE), pe);
        }
    }
}
