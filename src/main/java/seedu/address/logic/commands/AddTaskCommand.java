package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;

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
            + PREFIX_EVENT_DESCRIPTION + "Caffeinate "
            + PREFIX_EVENT_END_DATE_TIME + "2024-01-01 08:00";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists!\n"
            + "Duplicate task: %s";
    public static final String MESSAGE_INVALID_DESCRIPTION = "Description cannot be empty!\n%s";

    private final Task toAdd;

    /**
     * Constructs an AddTaskCommand to add a task into the task list.
     * @param task the task to add.
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);

        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.addTask(toAdd);
        } catch (DuplicateTaskException e) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK, Messages.format(toAdd)));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
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
