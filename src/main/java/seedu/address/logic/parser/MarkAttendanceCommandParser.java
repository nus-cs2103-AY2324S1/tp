package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.week.Week;

/**
 * Parses input arguments and creates a new MarkAttendanceCommand object.
 */
public class MarkAttendanceCommandParser implements Parser<MarkAttendanceCommand> {

    private static final Prefix PREFIX_NAME = new Prefix("/name ");
    private static final Prefix PREFIX_ID = new Prefix("/id ");
    private static final Prefix PREFIX_ATTENDANCE = new Prefix("/attendance ");
    private static final Prefix PREFIX_WEEK = new Prefix("/week ");
    private static final Prefix PREFIX_REASON = new Prefix("/reason ");

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAttendanceCommand
     * and returns a MarkAttendanceCommand object for execution.
     *
     * @param args Parses the user input into a command for execution.
     * @return A MarkAttendanceCommand object.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public MarkAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_ATTENDANCE,
                PREFIX_WEEK, PREFIX_REASON);

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTENDANCE, PREFIX_WEEK)
                || (argMultimap.getValue(PREFIX_NAME).isEmpty() && argMultimap.getValue(PREFIX_ID).isEmpty())
                || (argMultimap.getValue(PREFIX_ATTENDANCE).get().equals("0")
                        && argMultimap.getValue(PREFIX_REASON).isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        List<String> identifiers = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] names = argMultimap.getValue(PREFIX_NAME).get().split(",");
            for (String name : names) {
                identifiers.add(ParserUtil.parseName(name.trim()).fullName);
            }
        } else {
            String[] ids = argMultimap.getValue(PREFIX_ID).get().split(",");
            for (String id : ids) {
                identifiers.add(ParserUtil.parseId(id.trim()).value);
            }
        }

        boolean isPresent = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).get());
        Week week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());

        if (isPresent) {
            return new MarkAttendanceCommand(identifiers, true, week);
        } else {
            String reason = argMultimap.getValue(PREFIX_REASON).get();
            return new MarkAttendanceCommand(identifiers, false, week, reason);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
