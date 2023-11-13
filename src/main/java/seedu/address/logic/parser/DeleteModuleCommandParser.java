package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteModuleCommand object
 */
public class DeleteModuleCommandParser implements Parser<DeleteModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddModuleCommand
     * and returns an AddModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteModuleCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteModuleCommand.MESSAGE_USAGE), pe);
        }
    }
}
