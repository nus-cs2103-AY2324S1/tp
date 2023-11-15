package seedu.cc.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.cc.logic.parser.Prefix;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.medicalhistory.MedicalHistoryEvent;
import seedu.cc.model.patient.Patient;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_PATIENT_NOT_DISPLAYED_INDEX_ERROR =
            "Selected Patient's appointment list is not shown";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    public static final String MESSAGE_INVALID_MEDICAL_HISTORY_EVENT_DISPLAYED_INDEX = "The medical history event "
            + "index provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_EVENT_DISPLAYED_INDEX = "The appointment event "
            + "index provided is invalid";

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
    public static String format(Patient patient) {
        final StringBuilder builder = new StringBuilder();
        builder.append(patient.getName())
                .append("; NRIC: ")
                .append(patient.getNric())
                .append("; Phone: ")
                .append(patient.getPhone())
                .append("; Email: ")
                .append(patient.getEmail())
                .append("; Age: ")
                .append(patient.getAge())
                .append("; Tags: ");
        patient.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code event} for display to the user.
     */
    public static String format(MedicalHistoryEvent event, Patient patient) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(patient.getName())
                .append("\nDate: ")
                .append(event.getDate())
                .append("\nMedical Condition: ")
                .append(event.getMedicalCondition())
                .append("\nTreatment: ")
                .append(event.getTreatment());
        return builder.toString();
    }

    /**
     * Formats the {@code event} for display to the user.
     */
    public static String format(AppointmentEvent event, Patient patient) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient: ")
                .append(patient.getName())
                .append("\nDate: ")
                .append(event.getDate())
                .append("\nTime: ")
                .append(event.getTime());
        return builder.toString();
    }
}
