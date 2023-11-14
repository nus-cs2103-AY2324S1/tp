package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.task.MarkInternshipTaskCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new MarkInternshipTaskCommand object
 */
public class MarkInternshipTaskParser implements Parser<MarkInternshipTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkInternshipTaskCommand
     * and returns a MarkInternshipTaskCommand object for execution.
     *
     */
    public MarkInternshipTaskCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            MarkInternshipTaskCommand.MESSAGE_USAGE)
            );
        }
        Index index = ParserUtil.parseIndex(args);
        return new MarkInternshipTaskCommand(index);
    }
}
