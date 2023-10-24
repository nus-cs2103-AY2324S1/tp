package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.student.Name;

/**
 * Parses input arguments and creates a new ScheduleCommand object.
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATETIME, CliSyntax.PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATETIME,
                CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DESCRIPTION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATETIME,
                CliSyntax.PREFIX_DESCRIPTION);
        Name studentName = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get());
        Description description = ParserUtil.parseDescription(argMultimap
                .getValue(CliSyntax.PREFIX_DESCRIPTION).get());

        Appointment appointment = new Appointment(dateTime, studentName, description);

        return new ScheduleCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
