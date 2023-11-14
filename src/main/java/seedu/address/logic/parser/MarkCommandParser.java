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
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns an MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ATTENDANCE_TYPE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ATTENDANCE_TYPE);

        if (!argMultimap.getValue(PREFIX_ATTENDANCE_TYPE).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE)
            );
        }

        Index index;

        try {
            AttendanceType attendanceType =
                    ParserUtil.parseAttendanceType(argMultimap.getValue(PREFIX_ATTENDANCE_TYPE).get());
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new MarkCommand(index, attendanceType);
        } catch (ParseException e) {
            if (e.getMessage().equals(AttendanceType.MESSAGE_CONSTRAINTS)) {
                throw new ParseException(AttendanceType.MESSAGE_CONSTRAINTS);
            } else {
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
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
                }

            }
        }
    }

}
