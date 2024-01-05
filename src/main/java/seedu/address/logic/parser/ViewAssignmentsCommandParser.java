package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewAssignmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewAssignmentsCommand object
 */
public class ViewAssignmentsCommandParser implements Parser<ViewAssignmentsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewAssignmentsCommand
     * and returns a ViewAssignmentsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewAssignmentsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewAssignmentsCommand.MESSAGE_USAGE), pe);
        }

        return new ViewAssignmentsCommand(index);
    }
}
