package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteTutorCommand object
 */
public class ListScheduleCommandParser implements Parser<ListScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTutorCommand
     * and returns a DeleteTutorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListScheduleCommand parse(String args) throws ParseException {
        if (args == null || args.isBlank() || args.isEmpty()) {
            return new ListScheduleCommand(null);
        } else {
            try {
                Index index = ParserUtil.parseIndex(args);
                return new ListScheduleCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScheduleCommand.MESSAGE_USAGE), pe);
            }
        }
    }

}
