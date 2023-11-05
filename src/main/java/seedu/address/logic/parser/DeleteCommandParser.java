package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndexes(args, DeleteCommand.EXPECTED_INDEXES).get(0);
        } catch (ParseException pe) {
            throw new ParseException(DeleteCommand.MESSAGE_USAGE, pe);
        }

        return new DeleteCommand(index);
    }

}
