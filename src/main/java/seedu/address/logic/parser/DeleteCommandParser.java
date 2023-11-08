package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.command.parse.exceptions.DeleteCommandParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        String[] indexArgs = args.trim().split("\\s+");
        Index[] indices = new Index[indexArgs.length];

        for (int i = 0; i < indexArgs.length; i++) {
            try {
                indices[i] = ParserUtil.parseDeleteIndex(indexArgs[i]);
            } catch (DeleteCommandParseException de) {
                throw new DeleteCommandParseException();
            }
        }

        if (indices.length == 0) {
            throw new DeleteCommandParseException("At least one index must be provided. ");
        }

        return new DeleteCommand(indices);
    }
}
