package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMeetingCommand object
 */
public class DeleteMeetingCommandParser implements Parser<DeleteMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMeetingCommand
     * and returns a DeleteMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMeetingCommand parse(String args) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndexes(args, DeleteMeetingCommand.EXPECTED_INDEXES).get(0);
        } catch (ParseException pe) {
            throw new ParseException(DeleteMeetingCommand.MESSAGE_USAGE, pe);
        }

        return new DeleteMeetingCommand(index);
    }

}
