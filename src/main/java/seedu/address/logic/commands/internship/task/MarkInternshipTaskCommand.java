package seedu.address.logic.commands.internship.task;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.internship.task.InternshipTask;


/**
 * Marks an internship task in the internship task list as complete.
 */
public class MarkInternshipTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "mark-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an internship task as completed, "
            + "identified by the index number used in the displayed internship tasks list."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "This task has been marked as complete: %1$s";
    public static final String MESSAGE_TASK_ALREADY_COMPLETE = "This task is already completed.";

    private final Index targetIndex;

    /**
     * Creates a MarkAssignmentCommand to mark the specified {@code Assignment}
     */
    public MarkInternshipTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipTask> lastShownList = model.getFilteredInternshipTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX);
        }
        InternshipTask taskToMark = lastShownList.get(targetIndex.getZeroBased());

        if (taskToMark.getStatus().isCompleted()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_COMPLETE);
        }

        InternshipTask markedInternshipTask = taskToMark.mark();
        model.setInternshipTask(taskToMark, markedInternshipTask);

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, Messages.format(markedInternshipTask)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkInternshipTaskCommand)) {
            return false;
        }

        MarkInternshipTaskCommand otherMarkInternshipTaskCommand = (MarkInternshipTaskCommand) other;
        return targetIndex.equals(otherMarkInternshipTaskCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mark-task", targetIndex)
                .toString();
    }
}
