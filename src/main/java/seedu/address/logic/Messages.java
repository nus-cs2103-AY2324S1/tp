package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INTERVIEWS_LISTED_OVERVIEW = "%1$d interview(s) listed!";
    public static final String MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX = "The applicant index provided is invalid";
    public static final String MESSAGE_APPLICANTS_LISTED_OVERVIEW = "%1$d applicant(s) listed!";
    public static final String MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX =
            "The interview index provided is invalid";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_PAST_DATE = "Input date cannot be in the past!";

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
     * Formats the {@code applicant} for display to the user.
     */
    public static String formatApplicant(Applicant applicant) {
        final StringBuilder builder = new StringBuilder();
        builder.append(applicant.getName())
                .append("; Phone: ")
                .append(applicant.getPhone())
                .append("; Email: ")
                .append(applicant.getEmail())
                .append("; Address: ")
                .append(applicant.getAddress())
                .append("; Tags: ");
        applicant.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code interview} for display to the user.
     */
    public static String formatInterview(Interview interview) {
        return "Applicant: "
                + interview.getInterviewApplicant().getName()
                + "; Role: "
                + interview.getJobRole()
                + "; Scheduled for: "
                + interview.getInterviewStartTimeAsString();
    }

}
