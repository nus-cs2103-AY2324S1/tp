package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.SortOrder;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    @Override
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim().toLowerCase();

        switch (trimmedArgs) {
        case "d":
        case "desc":
        case "descending":
            return new SortCommand(SortOrder.DESCENDING);

        case "": // if user does not specify order, default to ascending
        case "a":
        case "asc":
        case "ascending":
            return new SortCommand(SortOrder.ASCENDING);

        default:
            throw new ParseException(String.format(SortCommand.MESSAGE_INVALID_SORT_ORDER, SortCommand.MESSAGE_USAGE));
        }
    }
}
