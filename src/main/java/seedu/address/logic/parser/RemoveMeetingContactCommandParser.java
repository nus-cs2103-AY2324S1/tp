package seedu.address.logic.parser;

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
        List<Index> indexes;
        try {
            indexes = ParserUtil.parseIndexes(args, RemoveMeetingContactCommand.EXPECTED_INDEXES);
        } catch (ParseException pe) {
            throw new ParseException(RemoveMeetingContactCommand.MESSAGE_USAGE, pe);
        }

        Index meetingIndex = indexes.get(0);
        Index attendeeIndex = indexes.get(1);

        return new RemoveMeetingContactCommand(meetingIndex, attendeeIndex);
    }
}
