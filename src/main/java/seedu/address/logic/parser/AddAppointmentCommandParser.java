package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_IC;
import static seedu.address.model.appointment.Appointment.FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Ic;

/**
 * Parses input arguments and creates a new AddPatientCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddPatientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATIENT_IC, PREFIX_DOCTOR_IC, PREFIX_APPOINTMENT_TIME);
        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT_IC, PREFIX_DOCTOR_IC, PREFIX_APPOINTMENT_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT_IC, PREFIX_DOCTOR_IC, PREFIX_APPOINTMENT_TIME);

        Ic patientIc = ParserUtil.parseIc(argMultimap.getValue(PREFIX_PATIENT_IC).get());
        Ic doctorIc = ParserUtil.parseIc(argMultimap.getValue(PREFIX_DOCTOR_IC).get());
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(argMultimap.getValue(PREFIX_APPOINTMENT_TIME).get(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage());
        }
        Appointment appointment = new Appointment(doctorIc, patientIc, dateTime);
        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
