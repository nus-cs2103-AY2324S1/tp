package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMeetingContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddMeetingContactCommand object
 */
public class AddMeetingContactCommandParser implements Parser<AddMeetingContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddMeetingContactCommand and returns a AddMeetingContactCommand object
     * for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddMeetingContactCommand parse(String args) throws ParseException {
        try {
            List<Index> indexes = ParserUtil.parseIndexes(args);
            if (indexes.size() != 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingContactCommand.MESSAGE_USAGE));
            }

            Index meetingIndex = indexes.get(0);
            Index contactIndex = indexes.get(1);
            return new AddMeetingContactCommand(meetingIndex, contactIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingContactCommand.MESSAGE_USAGE), pe);
        }
    }

}

