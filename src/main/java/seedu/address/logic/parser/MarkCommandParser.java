package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.attendance.AttendanceType;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ATTENDANCE_TYPE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ATTENDANCE_TYPE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            AttendanceType attendanceType =
                    ParserUtil.parseAttendanceType(argMultimap.getValue(PREFIX_ATTENDANCE_TYPE).get());
            return new MarkCommand(index, attendanceType);
        } catch (ParseException e) {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                String name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).toString();
                String[] nameKeywords = name.split("\\s+");
                AttendanceType attendanceType =
                        ParserUtil.parseAttendanceType(argMultimap.getValue(PREFIX_ATTENDANCE_TYPE).get());

                return new MarkCommand(
                        new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        attendanceType
                );


            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE_FOR_NAME));
            }

        }



    }


}
