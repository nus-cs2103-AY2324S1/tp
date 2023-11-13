package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.task.Task;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Oh no! I am unable to understand the "
            + " \" %1$s \" command you have entered!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Oh no! I do not recognise the command: \"%1$s\"!";
    public static final String MESSAGE_UNKNOWN_COMMAND_FORMAT = "Oh no! I do not understand the format of "
            + "the command you just entered! Use the \"help\" command to find out what the valid commands are! "
            + "\nUsage: %1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "Oh no! The task index you provided is out "
            + "of bounds! Make sure that you are entering in a number that corresponds to a valid index on your "
            + "task list!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
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
     * Formats the {@code task} for display to the user.
     */
    public static String format(Task task) {
        final StringBuilder builder = new StringBuilder();
        builder.append(task.getDescription());
        return builder.toString();
    }

}
