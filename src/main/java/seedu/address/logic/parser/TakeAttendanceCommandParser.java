package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_PRESENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.TakeAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.AttendancePresence;
import seedu.address.model.person.Name;
import seedu.address.model.session.SessionNumber;

/**
 * Parses input arguments and creates a new TakeAttendanceCommand object.
 */
public class TakeAttendanceCommandParser implements Parser<TakeAttendanceCommand> {

    /**
     * Parses the given `args` and creates a new `TakeAttendanceCommand` object.
     *
     * @param args The input arguments provided by the user.
     * @return A `TakeAttendanceCommand` object.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public TakeAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION, PREFIX_NAME, PREFIX_ATTENDANCE_PRESENCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION, PREFIX_NAME, PREFIX_ATTENDANCE_PRESENCE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, TakeAttendanceCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_SESSION);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTENDANCE_PRESENCE);
        SessionNumber sessionNumber = ParserUtil.parseSessionNumber(argMultimap.getValue(PREFIX_SESSION).get());
        Set<Name> names = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        AttendancePresence attendancePresence =
                ParserUtil.parseAttendancePresence(argMultimap.getValue(PREFIX_ATTENDANCE_PRESENCE).get());

        return new TakeAttendanceCommand(sessionNumber, names, attendancePresence);
    }

    /**
     * Checks if the specified prefixes are present in the given ArgumentMultimap.
     *
     * @param argumentMultimap The ArgumentMultimap to check for prefix presence.
     * @param prefixes The prefixes to be checked.
     * @return `true` if all specified prefixes are present, `false` otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
