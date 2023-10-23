package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DENTIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.appointments.AppointmentTime;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object.
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code args} of arguments into an AddAppointmentCommand.
     *
     * @param args User input string.
     * @return The parsed AddAppointmentCommand.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DENTIST, PREFIX_PATIENT, PREFIX_START, PREFIX_SERVICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DENTIST, PREFIX_PATIENT, PREFIX_START, PREFIX_SERVICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        long dentist = Long.parseLong(argMultimap.getValue(PREFIX_DENTIST).get());
        long patient = Long.parseLong(argMultimap.getValue(PREFIX_PATIENT).get());
        String start = argMultimap.getValue(PREFIX_START).get();
        String[] parts = start.split("\\s+");
        String startParsed = null;
        if (parts.length == 2) {
            startParsed = parts[0] + "T" + parts[1];
        } else {
            throw new ParseException("Please enter start time in correct format: yyyy-MM-dd HH:mm");
        }
        //String duration = argMultimap.getValue(PREFIX_DURATION).get();
        String treatment = argMultimap.getValue(PREFIX_SERVICE).get();
        Appointment appointment = new Appointment(dentist, patient, startParsed, treatment);
        return new AddAppointmentCommand(appointment);
    }

    /**
     * Checks if all the specified prefixes are present in the given {@code argumentMultimap}.
     *
     * @param argumentMultimap The argumentMultimap to check.
     * @param prefixes The prefixes to check for presence.
     * @return True if all specified prefixes are present, false otherwise.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                              Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
