package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListConflictsCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new ListConflictsCommand object
 */
public class ListConflictsCommandParser implements Parser<ListConflictsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListConflictsCommand
     * and returns a ListConflictsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListConflictsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListConflictsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListConflictsCommand.MESSAGE_USAGE), pe);
        }
    }
}
