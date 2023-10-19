package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteInterviewCommand object
 * Adapted from AB3's AddCommandParser class
 */
public class DeleteInterviewCommandParser implements Parser<DeleteInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInterviewCommand
     * and returns a DeleteInterviewCommand object for execution.
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     *     the expected format
     */
    public DeleteInterviewCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteInterviewCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInterviewCommand.MESSAGE_USAGE), pe);
        }
    }

}
