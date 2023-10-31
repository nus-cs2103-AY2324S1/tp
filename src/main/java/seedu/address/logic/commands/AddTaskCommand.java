package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;

/**
 * Adds a Task to the TaskList
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: "
            + PREFIX_EVENT_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_EVENT_END_DATE_TIME + "DEADLINE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_DESCRIPTION + "Caffeinate"
            + PREFIX_EVENT_END_DATE_TIME + "2024-01-01 08:00";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    public static final String MESSAGE_INVALID_DEADLINE = "Invalid Deadline!\n"
            + "Deadline should be in the format 'yyyy-MM-dd HH:mm' where:\n"
            + "    -'yyyy' is the year.\n"
            + "    -'MM' is the month.\n"
            + "    -'dd' is the day.\n"
            + "    -'HH:mm' is the time in 24-hour format.";

    private final Task toAdd;

    public AddTaskCommand(Task task) {
        requireNonNull(task);

        toAdd = task;
    }

    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        AddTaskCommand otherAddTaskCommand = (AddTaskCommand) other;
        return toAdd.equals(otherAddTaskCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("taskToAdd", toAdd)
                .toString();
    }
}
