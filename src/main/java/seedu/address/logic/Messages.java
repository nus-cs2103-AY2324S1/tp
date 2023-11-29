package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.treatment.Treatment;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_DENTIST_DISPLAYED_INDEX = "The dentist index provided is invalid!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DENTISTS_LISTED_OVERVIEW = "%1$d dentists listed!";

    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
        "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";
    public static final String MESSAGE_DELETE_DENTIST_SUCCESS = "Deleted Dentist: %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";
    public static final String MESSAGE_DELETE_TREATMENT_SUCCESS = "Deleted Treatment: %1$s";
    public static final String MESSAGE_NO_SUCH_PATIENT = "There is no patient in ToothTracker with ID: %1$s";
    public static final String MESSAGE_NO_SUCH_DENTIST = "There is no dentist in ToothTracker with ID: %1$s";
    public static final String MESSAGE_NO_SUCH_APPOINTMENT = "There is no appointment in ToothTracker with ID: %1$s";
    public static final String MESSAGE_NO_SUCH_TREATMENT = "There is no treatment in ToothTracker with the name: %1$s";
    public static final String MESSAGE_INVALID_ATTRIBUTE = "%1$s is not a valid attribute. "
        + "Allowed attributes are: %2$s";
    public static final String MESSAGE_NO_KEYWORDS_PROVIDED = "No keywords provided! "
        + "Please specify keywords for filtering.";
    public static final String MESSAGE_USAGE_FILTER_PATIENT = "Please follow the valid filter command format, "
        + "filter-patient a/(attribute) k/(keywords).";
    public static final String MESSAGE_USAGE_FILTER_PATIENT_FAIL = "No patients found with the %s: %s.";
    public static final String MESSAGE_USAGE_FILTER_PATIENT_SUCCESS = "Filtered patients by %s with keywords: %s.";

    public static final String MESSAGE_USAGE_FILTER_DENTIST = "Please follow the valid filter command format, "
        + "filter-dentist a/(attribute) k/(keywords).";
    public static final String MESSAGE_USAGE_FILTER_DENTIST_FAIL = "No dentists found with the %s: %s.";
    public static final String MESSAGE_USAGE_FILTER_DENTIST_SUCCESS = "Filtered dentists by %s with keywords: %s.";


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
            .append(person.getAddress())
            .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code treatment} for display to the user.
     */
    public static String format(Treatment treatment) {
        final StringBuilder builder = new StringBuilder();
        builder.append(treatment.getName())
            .append("; Cost: ")
            .append(treatment.getCost())
            .append("; Time: ")
            .append(treatment.getTime());
        return builder.toString();
    }

    /**
     * Formats the {@code patient} for display to the user.
     */
    public static String format(Patient person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
            .append("; Phone: ")
            .append(person.getPhone())
            .append("; BirthDate: ")
            .append(person.getBirthdate())
            .append("; Gender: ")
            .append(person.getGender())
            .append("; Remark: ")
            .append(person.getRemark())
            .append("; Treatment: ")
            .append(person.getTreatmentName())
            .append("; Address: ")
            .append(person.getAddress())
            .append("; Email: ")
            .append(person.getEmail())
            .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code dentist} for display to the user.
     */
    public static String format(Dentist dentist) {
        final StringBuilder builder = new StringBuilder();
        builder.append(dentist.getName())
                .append("; Phone: ")
                .append(dentist.getPhone())
                .append("; Email: ")
                .append(dentist.getEmail())
                .append("; Address: ")
                .append(dentist.getAddress())
                .append("; Specialization: ")
                .append(dentist.getSpecialization())
                .append("; Years of Experience: ")
                .append(dentist.getYoe())
                .append("; Dentist ID: ")
                .append(dentist.getId())
                .append("; Tags: ");
        dentist.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the given appointment as a string.
     * The string format includes information about the dentist, patient, appointment start time, and duration.
     *
     * @param appointment The appointment to be formatted.
     * @return A formatted string representation of the appointment.
     * @throws NullPointerException if the appointment is null.
     */
    public static String format(Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Dentist: ")
            .append(appointment.getDentistName())
            .append("; Patient: ")
            .append(appointment.getPatientName())
            .append("; Appointment: ")
            .append(appointment.getAppointmentTime().startString())
            .append("; Duration: ")
            .append(appointment.getAppointmentTime().durationString())
            .append("; Treatment: ")
            .append(appointment.getTreatment())
            .append("; Cost: ")
            .append(appointment.getCost())
            .append("; ID: ")
            .append(appointment.getId());
        return builder.toString();
    }

}
