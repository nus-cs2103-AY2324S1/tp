package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListAllConflictsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListAllConflictsCommand object
 */
public class ListAllConflictsCommandParser implements Parser<ListAllConflictsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListAllConflictsCommand
     * and returns a ListAllConflictsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListAllConflictsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListAllConflictsCommand.MESSAGE_USAGE));
        }

        return new ListAllConflictsCommand();
    }
}
