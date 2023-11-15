package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.ListBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListBuyerCommand object
 */
public class ListBuyerCommandParser implements Parser<ListBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListBuyerCommand
     * and returns a ListBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListBuyerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListBuyerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListBuyerCommand.MESSAGE_USAGE), pe);
        }
    }
}
