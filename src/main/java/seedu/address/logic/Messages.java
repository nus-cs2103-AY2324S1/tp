package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Person with specified nric cannot be found!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_APPOINTMENT_NOT_FOUND = "Specified appointment does not exist!";
    public static final String MESSAGE_APPOINTMENTS_FOUND_OVERVIEW = "%1d appointments found!";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress());
        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            builder.append("; Emergency Contact: ")
                    .append(patient.getEmergencyContact())
                    .append("; Blood Type: ")
                    .append(patient.getBloodType())
                    .append("; Condition: ")
                    .append(patient.getCondition());
        }
        builder.append("; Tags: ");
        person.getTags().forEach(builder::append);
        builder.append("; Appointments: ");
        person.getAppointments().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code appointment} for display to the user.
     */
    public static String format(Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient involved: ")
                .append(appointment.getPatient())
                .append("; Doctor involved: ")
                .append(appointment.getDoctor())
                .append("; Time of appointment: ")
                .append(appointment.getAppointmentTime());
        return builder.toString();
    }

}
