package seedu.address.logic.parser.appointmentparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_START;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.RescheduleCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentTime;

/**
 * Parses input arguments and creates a new RescheduleCommand object
 */
public class RescheduleCommandParser implements Parser<RescheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RescheduleCommand
     * and returns a RescheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RescheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_START, PREFIX_APPOINTMENT_END);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RescheduleCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.arePrefixesPresent(PREFIX_APPOINTMENT_START, PREFIX_APPOINTMENT_END)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RescheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPOINTMENT_START, PREFIX_APPOINTMENT_END);

        LocalDateTime startTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_START).get());
        LocalDateTime endTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_APPOINTMENT_END).get());
        AppointmentTime appointmentTime;
        try {
            appointmentTime = new AppointmentTime(startTime, endTime);
        } catch (IllegalArgumentException e) {
            throw new ParseException(AppointmentTime.MESSAGE_CONSTRAINTS);
        }

        return new RescheduleCommand(index, appointmentTime);
    }

}
