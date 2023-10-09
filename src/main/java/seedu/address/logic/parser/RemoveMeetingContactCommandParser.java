package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveMeetingContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveMeetingContactCommand object
 */
public class RemoveMeetingContactCommandParser implements Parser<RemoveMeetingContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * RemoveMeetingContactCommand and returns a RemoveMeetingContactCommand object
     * for execution.
     * 
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveMeetingContactCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = ParserUtil.parseIndexes(args);
            if (indexes.size() != 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingContactCommand.MESSAGE_USAGE));
            }

            Index meetingIndex = indexes.get(0);
            Index attendeeIndex = indexes.get(1);
            return new RemoveMeetingContactCommand(meetingIndex, attendeeIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
