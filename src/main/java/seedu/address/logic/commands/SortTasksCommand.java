package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.exceptions.InvalidSortingOrderException;

/**
 * Changes the sorting order of tasks in the task manager.
 */
public class SortTasksCommand extends Command {
    public static final String COMMAND_WORD = "sortTasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the way tasks in the task list are sorted."
            + "Parameters: Description OR Deadline\n"
            + "Example: " + COMMAND_WORD + " Description";

    public static final String MESSAGE_SUCCESS = "Sorting order changed to %s.";

    public static final String MESSAGE_INVALID_TYPE = "Sorting order provided is invalid!\n"
            + "Sorting orders available: DESCRIPTION/DEADLINE.";

    private final String comparatorType;

    public SortTasksCommand(String comparatorType) {
        this.comparatorType = comparatorType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.sortTasksBy(comparatorType);
        } catch (InvalidSortingOrderException e) {
            throw new CommandException(MESSAGE_INVALID_TYPE);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparatorType));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortTasksCommand)) {
            return false;
        }

        SortTasksCommand otherSortTasksCommand = (SortTasksCommand) other;
        return comparatorType.equals((otherSortTasksCommand).comparatorType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparatorType", comparatorType)
                .toString();
    }
}
