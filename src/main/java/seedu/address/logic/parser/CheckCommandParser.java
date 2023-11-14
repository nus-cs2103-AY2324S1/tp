package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new CheckCommand object
 */
public class CheckCommandParser implements Parser<CheckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckCommand
     * and returns a CheckCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CheckCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !trimmedArgs.matches("^\\d+$")) { // checks o\if args is an integer
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }
        return new CheckCommand(Integer.parseInt(trimmedArgs), new GroupContainsKeywordsPredicate(Arrays.asList(
            String.valueOf(Integer.parseInt(trimmedArgs)))));
    }
}
