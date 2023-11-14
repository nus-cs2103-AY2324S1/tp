package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FIELD_BOOKMARKED;
import static seedu.address.logic.parser.CliSyntax.FIELD_HIDDEN;

import java.util.Arrays;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object.
 */
public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        if (args.isEmpty()) {
            return new ListCommand();
        }
        String fieldName = args.trim();
        if (!isFieldNameValid(fieldName, FIELD_HIDDEN, FIELD_BOOKMARKED)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
        return new ListCommand(fieldName);
    }
    /**
     * Returns true if all the field names are valid.
     */
    private static boolean isFieldNameValid(String fieldName, String... fieldNames) {
        return Arrays.asList(fieldNames).contains(fieldName);
    }
}
