package profplan.logic.commands;

import static java.util.Objects.requireNonNull;

import profplan.commons.util.ToStringBuilder;
import profplan.logic.commands.exceptions.CommandException;
import profplan.model.Model;
import profplan.model.task.DueDate;

/**
 * Marks Status of task as done.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a task as done.";
    public static final String MESSAGE_DETAILS = "Parameters: [index]";
    public static final String MESSAGE_EXAMPLE = "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_FULL_HELP = MESSAGE_USAGE + "\n" + MESSAGE_DETAILS + "\n" + MESSAGE_EXAMPLE;

    public static final String MESSAGE_SUCCESS = "Task successfully marked as done, Prof! "
                                                    + "Here is your updated task list";

    public static final String MESSAGE_INVALID_NUMBER = "[index] should be greater than or equal to 1";
    public static final String MESSAGE_ALREADY_DONE = "This task is already marked as done";

    private final int taskNumber;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public MarkCommand(int number) {
        assert number > 0;
        taskNumber = number;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (taskNumber > model.getFilteredTaskList().size()) {
            throw new CommandException("Task not found please enter a valid Task Number.");
        }
        try {
            model.markTask(taskNumber - 1);
        } catch (IllegalArgumentException e) { // if adding days to recurring task increases due date past 2030
            throw new CommandException(DueDate.MESSAGE_CONSTRAINTS);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return taskNumber == otherMarkCommand.taskNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("taskNumber", taskNumber)
                .toString();
    }
}
