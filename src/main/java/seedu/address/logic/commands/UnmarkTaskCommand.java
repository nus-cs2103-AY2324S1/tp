package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Status.STATUS_NOT_DONE;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Marks the status of an existing task in the task list as not done.
 */
public class UnmarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "unmarkTask";
    public static final String SHORTENED_COMMAND_WORD = "ut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " (alias: " + SHORTENED_COMMAND_WORD + ")"
            + ": Mark status as not done for the task specified "
            + "by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Marked as Not Done: %1$s";
    public static final String MESSAGE_HAS_BEEN_MARKED = "This task is already marked as not done in the task list.";

    private final Index index;

    /**
     * @param index of the task in the filtered task list to mark as not done
     */
    public UnmarkTaskCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(index.getZeroBased());

        if (taskToMark.getStatus().equals(STATUS_NOT_DONE)) {
            throw new CommandException(MESSAGE_HAS_BEEN_MARKED);
        }

        Task unmarkedTask = model.unmarkTask(taskToMark);
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, Messages.format(unmarkedTask)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkTaskCommand)) {
            return false;
        }

        UnmarkTaskCommand otherUnmarkTaskCommand = (UnmarkTaskCommand) other;
        return index.equals(otherUnmarkTaskCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", index)
                .toString();
    }
}
