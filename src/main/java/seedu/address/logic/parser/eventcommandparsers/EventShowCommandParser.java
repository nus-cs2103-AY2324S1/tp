package seedu.address.logic.parser.eventcommandparsers;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.eventcommands.EventShowCommand;
import seedu.address.logic.commands.volunteercommands.VolunteerDeleteCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new EventShowCommand object
 */
public class EventShowCommandParser implements Parser<EventShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EventShowCommand
     * and returns a EventShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventShowCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EventShowCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventShowCommand.MESSAGE_USAGE), pe);
        }
    }

}
