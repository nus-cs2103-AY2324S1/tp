package seedu.letsgethired.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.letsgethired.logic.parser.Prefix;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX =
            "The intern application index provided is invalid";
    public static final String MESSAGE_INTERN_APPLICATIONS_LISTED_OVERVIEW = "%1$d intern applications listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_MULTIPLE_FIELDS =
            "Multiple field(s) specified for a single field command";

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
     * Formats the {@code internApplication} for feedback to the user.
     */
    public static String formatFeedback(InternApplication internApplication) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ")
                .append(internApplication.getCompany())
                .append("; Role: ")
                .append(internApplication.getRole())
                .append("; Cycle: ")
                .append(internApplication.getCycle())
                .append("; Status: ")
                .append(internApplication.getStatus())
                .append("; Deadline: ")
                .append(internApplication.getDeadline())
                .append("; Notes: ")
                .append(internApplication.getNotes());
        return builder.toString();
    }
}
