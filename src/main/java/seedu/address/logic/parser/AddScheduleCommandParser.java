package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_INDEX;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.TutorIndex;

/**
 * Parses input arguments and creates a new AddScheduleCommand object
 */
public class AddScheduleCommandParser implements Parser<AddScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddScheduleCommand
     * and returns an AddScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTOR_INDEX, PREFIX_START_TIME, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTOR_INDEX, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TUTOR_INDEX, PREFIX_START_TIME, PREFIX_END_TIME);
        TutorIndex tutorIndex = ParserUtil.parseTutorIndex(argMultimap.getValue(PREFIX_TUTOR_INDEX).get());
        StartTime startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());
        EndTime endTime = ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_END_TIME).get());

        Schedule schedule = new Schedule(tutorIndex, startTime, endTime);

        return new AddScheduleCommand(schedule);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
