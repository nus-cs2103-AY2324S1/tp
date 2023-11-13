package seedu.address.logic.parser;

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
        List<Index> indexes;
        try {
            indexes = ParserUtil.parseIndexes(args, AddMeetingContactCommand.EXPECTED_INDEXES);
        } catch (ParseException pe) {
            throw new ParseException(AddMeetingContactCommand.MESSAGE_USAGE, pe);
        }

        Index meetingIndex = indexes.get(0);
        Index contactIndex = indexes.get(1);

        return new AddMeetingContactCommand(meetingIndex, contactIndex);
    }
}

