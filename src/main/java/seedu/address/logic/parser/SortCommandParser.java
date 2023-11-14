package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FIELD_COMMENT;
import static seedu.address.logic.parser.CliSyntax.FIELD_EMAIL;
import static seedu.address.logic.parser.CliSyntax.FIELD_GPA;
import static seedu.address.logic.parser.CliSyntax.FIELD_INTERVIEW_SCORE;
import static seedu.address.logic.parser.CliSyntax.FIELD_NAME;
import static seedu.address.logic.parser.CliSyntax.FIELD_PHONE;
import static seedu.address.logic.parser.CliSyntax.FIELD_PREVIOUS_GRADE;
import static seedu.address.logic.parser.CliSyntax.FIELD_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.FIELD_TAGS;

import java.util.Arrays;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String fieldName = args.trim();
        if (!isFieldNameValid(fieldName, FIELD_STUDENT_NUMBER, FIELD_NAME, FIELD_PHONE, FIELD_EMAIL, FIELD_GPA,
                FIELD_COMMENT, FIELD_PREVIOUS_GRADE, FIELD_INTERVIEW_SCORE, FIELD_TAGS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(fieldName);
    }

    /**
     * Returns true if all the field names are valid.
     */
    private static boolean isFieldNameValid(String fieldName, String... fieldNames) {
        return Arrays.asList(fieldNames).contains(fieldName);
    }
}
