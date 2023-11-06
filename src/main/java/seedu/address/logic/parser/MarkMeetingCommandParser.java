package seedu.address.logic.parser;

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
        Index index;
        try {
            index = ParserUtil.parseIndexes(args, MarkMeetingCommand.EXPECTED_INDEXES).get(0);
        } catch (ParseException pe) {
            throw new ParseException(MarkMeetingCommand.MESSAGE_USAGE, pe);
        }

        return new MarkMeetingCommand(index);
    }

}
