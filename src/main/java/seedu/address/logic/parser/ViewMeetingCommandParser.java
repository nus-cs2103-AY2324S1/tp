package seedu.address.logic.parser;

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
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMeetingCommand parse(String args) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndexes(args, ViewMeetingCommand.EXPECTED_INDEXES).get(0);
        } catch (ParseException pe) {
            throw new ParseException(ViewMeetingCommand.MESSAGE_USAGE, pe);
        }

        return new ViewMeetingCommand(index);
    }
}
