package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTutorialCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTutorialCommand object
 */
public class DeleteTutorialCommandParser implements Parser<DeleteTutorialCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTutorialCommand
     * and returns an DeleteTutorialCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTutorialCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteTutorialCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTutorialCommand.MESSAGE_USAGE), pe);
        }
    }
}
