package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.ReadCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReadCommand object
 */
public class ReadCommandParser implements Parser<ReadCommand> {

    private static final String p_field_name = "phone";
    private static final String e_field_name = "email";
    private static final String j_field_name = "join date";
    private static final String a_field_name = "address";
    private static final String s_field_name = "salary";
    private static final String l_field_name = "annual leave";
    private static final String b_field_name = "bank account";

    /**
     * Parses the given {@code String} of arguments in the context of the ReadCommand
     * and returns a ReadCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_USAGE));
        }

        String[] fields = trimmedArgs.split("/");

        if (fields.length < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(fields[0].trim());
        String fieldName = fieldNameToString(fields[1]);
        return new ReadCommand(index, fieldName);
    }

    /**
     * Returns a string representation of the specific field name of a person.
     *
     * @param fieldToRead The field name.
     * @return The information specified by the field.
     * @throws CommandException if the field is invalid.
     */
    public String fieldNameToString(String fieldToRead) throws ParseException {
        switch (fieldToRead) {
        case "p":
            return p_field_name;
        case "a":
            return a_field_name;
        case "e":
            return e_field_name;
        case "b":
            return b_field_name;
        case "l":
            return l_field_name;
        case "j":
            return j_field_name;
        case "s":
            return s_field_name;
        default:
            throw new ParseException(Messages.MESSAGE_INVALID_FIELD_TO_READ);
        }
    }
}


