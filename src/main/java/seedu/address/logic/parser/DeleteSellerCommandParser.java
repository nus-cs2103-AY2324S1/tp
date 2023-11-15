package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.DeleteSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteSellerCommand object
 */
public class DeleteSellerCommandParser implements Parser<DeleteSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSellerCommand
     * and returns a DeleteSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSellerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteSellerCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSellerCommand.MESSAGE_USAGE), pe);
        }
    }
}
