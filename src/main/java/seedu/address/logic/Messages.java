package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.common.Prefix;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX = "The assignment index provided is invalid";
    public static final String MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW = "%1$d assignments listed!";
    public static final String MESSAGE_ASSIGNMENT_PLANNED_DATE_AFTER_END_DATE =
            "Planned end date cannot be after deadline, do your homework on time!";
    public static final String MESSAGE_INTERNSHIPS_LISTED_OVERVIEW = "%1$d internship roles and tasks listed!";
    public static final String MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX = "The internship task index"
            + " provided is invalid";

    public static final String MESSAGE_INVALID_INTERNSHIP_ROLE_DISPLAYED_INDEX = "The internship role index"
            + " provided is invalid";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_EDIT_LEADS_TO_DUPLICATE_ROLES = "This will lead to duplicate internship roles"
            + " existing!";
    public static final String MESSAGE_EDITED_ROLE_IS_THE_SAME = "This does not result in any changes to the role!";
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
     * Formats the {@code assignment} for display to the user.
     */
    public static String format(Assignment assignment) {
        final StringBuilder builder = new StringBuilder();
        builder.append(assignment.getName())
                .append("; Description: ")
                .append(assignment.getDescription())
                .append("; Status: ")
                .append(assignment.getStatus())
                .append("; Deadline: ")
                .append(assignment.getEnd())
                .append("; Planned End date: ")
                .append(assignment.getPlannedFinishDate())
                .append("; Tags: ");
        assignment.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code assignment} for display to the user.
     */
    public static String format(InternshipRole role) {
        final StringBuilder builder = new StringBuilder();
        builder.append(role.getName())
                .append("; role: ")
                .append(role.getRole())
                .append("; cycle: ")
                .append(role.getRole())
                .append("; description: ")
                .append(role.getDescription())
                .append("; pay: ")
                .append(role.getPay())
                .append("; outcome: ")
                .append(role.getApplicationOutcome())
                .append("; location: ")
                .append(role.getLocation())
                .append("; Tags: ");
        role.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code assignment} for display to the user.
     */
    public static String format(InternshipTask internshipTask) {
        final StringBuilder builder = new StringBuilder();
        builder.append(internshipTask.getTaskName())
                .append("; Internship role name: ")
                .append(internshipTask.getInternshipRole().getName())
                .append("; Status: ")
                .append(internshipTask.getStatus())
                .append("; Deadline: ")
                .append(internshipTask.getDeadline())
                .append("; Outcome: ")
                .append(internshipTask.getOutcome())
                .append("; Tags: ");
        internshipTask.getTags().forEach(builder::append);
        return builder.toString();
    }
}
