package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.ListSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListSellerCommand object
 */
public class ListSellerCommandParser implements Parser<ListSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListSellerCommand
     * and returns a ListSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListSellerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListSellerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListSellerCommand.MESSAGE_USAGE), pe);
        }
    }
}
