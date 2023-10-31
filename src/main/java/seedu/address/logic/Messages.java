package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.person.Specialist;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_BLANK_ARGUMENTS = "Arguments cannot be blank! \n%1$s";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_TYPE =
            "Invalid person type! Specify either \"-pa\" for patient or \"-sp\" for specialist";
    public static final String MESSAGE_PERSON_TYPE_MISMATCH_INDEX =
            "The person type tag does not match the person type at the specified index.";
    public static final String MESSAGE_ERROR_STATE =
            "Something went wrong :( \n Please contact the developers via email or website.";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    public static final String MESSAGE_DUPLICATE_INDEXES =
            "The following index(es) has been duplicated: ";

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
     * Returns an error message indicating the duplicate indexes.
     */
    public static String getErrorMessageForDuplicateIndexes(String[] duplicatedIndexes) {
        assert duplicatedIndexes.length > 0;
        return MESSAGE_DUPLICATE_INDEXES + String.join(", ", duplicatedIndexes);
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
                .append("; Tags: ");
        person.getTags().forEach(builder::append);

        if (person instanceof Patient) {
            Patient patient = (Patient) person;
            builder.insert(0, "Patient: ")
                    .append("; Age: ")
                    .append(patient.getAge())
                    .append("; Medical History: ")
                    .append(patient.getMedicalHistory());
        }

        if (person instanceof Specialist) {
            Specialist specialist = (Specialist) person;
            builder.insert(0, "Specialist: ")
                    .append("; Specialty: ")
                    .append(specialist.getSpecialty());
        }
        return builder.toString();
    }

    /**
     * Formats a shorten form of {@code person} for display to the user.
     * Displays the {@code Name}, {@code Email} and {@code PersonType} of the person.
     */
    public static String formatShortForm(Person person) {
        final StringBuilder result = new StringBuilder();
        result.append(person.getName()).append("; Phone: ").append(person.getPhone());
        if (person instanceof Patient) {
            result.insert(0, "Patient: ");
        }
        if (person instanceof Specialist) {
            result.insert(0, "Specialist: ");
        }
        return result.toString();
    }
}
