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
 * Un-marks an internship task in the internship task list,
 * or rather, sets the internship task status as incomplete.
 */
public class UnMarkInternshipTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "unmark-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an internship task's status "
            + "as incomplete, "
            + "identified by the index number used in the displayed internship tasks list."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "This task has been"
            + " marked as incomplete: %1$s";
    public static final String MESSAGE_TASK_ALREADY_INCOMPLETE = "This task is already incomplete.";

    private final Index targetIndex;

    /**
     * Creates an UnMarkAssignmentCommand to unmark the specified {@code Assignment}
     */
    public UnMarkInternshipTaskCommand(Index targetIndex) {
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
        InternshipTask taskToUnmark = lastShownList.get(targetIndex.getZeroBased());

        if (!taskToUnmark.getStatus().isCompleted()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_INCOMPLETE);
        }

        InternshipTask unMarkedInternshipTask = taskToUnmark.unMark();
        model.setInternshipTask(taskToUnmark, unMarkedInternshipTask);

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, Messages.format(unMarkedInternshipTask)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnMarkInternshipTaskCommand)) {
            return false;
        }

        UnMarkInternshipTaskCommand otherUnMarkInternshipTaskCommand = (UnMarkInternshipTaskCommand) other;
        return targetIndex.equals(otherUnMarkInternshipTaskCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("unmark", targetIndex)
                .toString();
    }
}
