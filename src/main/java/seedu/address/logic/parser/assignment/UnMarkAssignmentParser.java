package seedu.address.logic.parser.assignment;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.assignment.UnMarkAssignmentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkAssignmentCommand object
 */
public class UnMarkAssignmentParser implements Parser<UnMarkAssignmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnMarkAssignmentCommand
     * and returns a UnMarkAssignmentCommand object for execution.
     *
     */
    public UnMarkAssignmentCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            UnMarkAssignmentCommand.MESSAGE_USAGE)
            );
        }
        Index index = ParserUtil.parseIndex(args);
        return new UnMarkAssignmentCommand(index);
    }
}
