package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.prescription.Prescription;


/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX = "The prescription index "
            + "provided is invalid";
    public static final String MESSAGE_PRESCRIPTIONS_LISTED_OVERVIEW = "%1$d prescriptions listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

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
     * Formats the {@code prescription} for display to the user.
     */
    public static String format(Prescription prescription) {
        final StringBuilder builder = new StringBuilder();
        builder.append(prescription.getName());
        if (prescription.getDosage().isPresent()) {
            builder.append("; Dosage: ")
                .append(prescription.getDosage().get());
        }

        if (prescription.getFrequency().isPresent()) {
            builder.append("; Frequency: ")
                .append(prescription.getFrequency().get());
        }

        builder.append("; Start Date: ").append(prescription.getStartDate());

        if (prescription.getEndDate().isPresent()) {
            builder.append("; End Date: ")
                .append(prescription.getEndDate().get());
        }

        if (prescription.getExpiryDate().isPresent()) {
            builder.append("; Expiry Date: ")
                .append(prescription.getExpiryDate().get());
        }

        if (prescription.getTotalStock().isPresent()) {
            builder.append("; Total stock: ")
                .append(prescription.getTotalStock().get());
        }

        builder.append("; ConsumptionCount: ")
            .append(prescription.getConsumptionCount());

        builder.append("; isCompleted: ")
            .append(prescription.getIsCompleted());

        if (prescription.getNote().isPresent()) {
            builder.append("; Note: ")
                .append(prescription.getNote().get());
        }
        return builder.toString();
    }

}
