package seedu.pharmhub.logic.parser;

import seedu.pharmhub.commons.core.index.Index;
import seedu.pharmhub.logic.Messages;
import seedu.pharmhub.logic.commands.DeleteOrderCommand;
import seedu.pharmhub.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteOrderCommand object
 */
public class DeleteOrderCommandParser implements Parser<DeleteOrderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOrderCommand
     * and returns a DeleteOrderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
