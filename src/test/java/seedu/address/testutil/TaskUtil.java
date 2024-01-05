package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.UpdateTaskProgressCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_NAME).append(task.getName().taskName).append(" ");
        sb.append(PREFIX_TASK_DESCRIPTION).append(task.getDescription().description).append(" ");
        sb.append(PREFIX_DATE).append(task.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProgressDescriptor}'s details.
     */
    public static String getUpdateProgressDetails(UpdateTaskProgressCommand.EditProgressDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getProgress().ifPresent(name -> sb.append(PREFIX_TASK_PROGRESS).append(name.name()).append(" "));
        return sb.toString();
    }
}
