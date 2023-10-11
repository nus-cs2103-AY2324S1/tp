package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MarkAttendanceCommandParser implements Parser<MarkAttendanceCommand> {

    private static final Prefix PREFIX_NAME = new Prefix("/name ");
    private static final Prefix PREFIX_ID = new Prefix("/id ");
    private static final Prefix PREFIX_ATTENDANCE = new Prefix("/attendance ");

    public MarkAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_ATTENDANCE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTENDANCE)
                || (argMultimap.getValue(PREFIX_NAME).isEmpty() && argMultimap.getValue(PREFIX_ID).isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAttendanceCommand.MESSAGE_USAGE));
        }

        String identifier;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            identifier = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName;
        } else {
            identifier = ParserUtil.parseID(argMultimap.getValue(PREFIX_ID).get()).value;
        }

        boolean isPresent = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).get());
        LocalDate date = LocalDate.now(); // Assuming attendance is marked for the current date

        return new MarkAttendanceCommand(identifier, isPresent, date);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
