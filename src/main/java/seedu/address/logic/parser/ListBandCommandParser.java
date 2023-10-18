package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListBandCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.band.BandNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListBandCommand object.
 */
public class ListBandCommandParser implements Parser<ListBandCommand> {

    @Override
    public ListBandCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListBandCommand.MESSAGE_USAGE));
        }

        return new ListBandCommand(new BandNameContainsKeywordsPredicate(trimmedArgs));
    }
}
