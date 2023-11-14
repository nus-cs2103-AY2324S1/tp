package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";
    public static final String MESSAGE_INVALID_RECORD_DISPLAYED_INDEX = "The record index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_RECORDS_LISTED_OVERVIEW = "%1$d records listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields = Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; NRIC: ")
                .append(person.getNric())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Gender: ")
                .append(person.getGender())
                .append("; Age: ")
                .append(person.getAge())
                .append("; BloodType: ")
                .append(person.getBloodType())
                .append("; Allergies: ");
        person.getAllergies().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code appointment} for display to the user.
     */
    public static String format(Appointment appointment, Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(person.getName())
                .append("; Appointment: ")
                .append(appointment.getName())
                .append("; Date & Time: ")
                .append(appointment.getDateTime());

        return builder.toString();
    }

    /**
     * Formats the {@code record} for display to the user.
     */
    public static String format(Record record, Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(person.getName())
                .append("; Conditions: ")
                .append(record.getConditions())
                .append("; Date & Time: ")
                .append(record.getDateTime())
                .append("; Medications: ")
                .append(record.getMedications());
        return builder.toString();
    }
}
