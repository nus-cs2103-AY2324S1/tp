package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;

/**
 * Parses the input arguments and creates a new ScheduleCommand object
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to expected format.
     */
    public ScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT, PREFIX_APPOINTMENT_DATE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT, PREFIX_APPOINTMENT_DATE);
        Appointment appointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT).get(),
                argMultimap.getValue(PREFIX_APPOINTMENT_DATE).get());

        return new ScheduleCommand(index, appointment);
    }
}
