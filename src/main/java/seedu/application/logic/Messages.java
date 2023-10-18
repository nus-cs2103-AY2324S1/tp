package seedu.application.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.parser.Prefix;
import seedu.application.model.job.Job;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown Command \n" + HelpCommand.MESSAGE_USAGE;
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s\n";
    public static final String MESSAGE_INVALID_JOB_DISPLAYED_INDEX = "The job index provided is invalid";
    public static final String MESSAGE_JOBS_LISTED_OVERVIEW = "%1$d jobs listed!";
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
     * Formats the {@code job} for display to the user.
     */
    public static String format(Job job) {
        final StringBuilder builder = new StringBuilder();
        builder.append(job.getCompany())
            .append("; Role: ")
            .append(job.getRole())
            .append("; Status: ")
            .append(job.getStatus())
            .append("; Deadline: ")
            .append(job.getDeadline());
        return builder.toString();
    }

}
