package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT_IC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Ic;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     *
     * @param args The input arguments string to be parsed.
     * @return An AddAppointmentCommand object representing the parsed command.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
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
        AppointmentTime dateTime = ParserUtil.parseAppointmentTime(argMultimap.getValue(PREFIX_APPOINTMENT_TIME).get());

        Appointment appointment = new Appointment(doctorIc, patientIc, dateTime);
        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap The parsed argument multimap.
     * @param prefixes The prefixes to check.
     * @return True if all prefixes are present and have non-empty values, false otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
