package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PRIORITY;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a new task with the specified description.\n"
            + "Parameters: "
            + PREFIX_TASK_NAME + "TASK_NAME "
            + "[" + PREFIX_TASK_DESCRIPTION + "TASK_DESCRIPTION] "
            + "[" + PREFIX_DATE + "DATE]\n"
            + "[" + PREFIX_TASK_PRIORITY + "TASK_PRIORITY] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_NAME + "Do laundry "
            + PREFIX_TASK_DESCRIPTION + "Wash the clothes and pants in basket "
            + PREFIX_DATE + "22/10/2023 "
            + PREFIX_TASK_PRIORITY + "medium ";

    public static final String MESSAGE_SUCCESS = "Task has been added: %1$s";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";

    public static final CommandType COMMAND_TYPE = CommandType.ADD_TASK;

    private final Task task;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(task)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(task)), COMMAND_TYPE);
    }

    @Override
    public CommandType getCommandType() {
        return COMMAND_TYPE;
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

        AddTaskCommand e = (AddTaskCommand) other;
        return task.equals(e.task);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("task", task)
                .toString();
    }

}
