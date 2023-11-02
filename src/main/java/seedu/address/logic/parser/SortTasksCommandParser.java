package seedu.address.logic.parser;


import seedu.address.logic.commands.SortTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new SortTasksCommand object.
 */
public class SortTasksCommandParser implements Parser<SortTasksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortTasksCommand
     * and returns a SortTasksCommand object for execution
     * @throws ParseException if no parameters are provided.
     */
    public SortTasksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortTasksCommand.MESSAGE_USAGE));
        }
        return new SortTasksCommand(trimmedArgs);
    }
}
