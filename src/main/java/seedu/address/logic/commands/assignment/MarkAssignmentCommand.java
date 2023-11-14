package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Marks an assignment in the assignment list as complete.
 */
public class MarkAssignmentCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "mark-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an assignment as completed, "
            + "identified by the index number used in the displayed assignments list."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_ASSIGNMENT_SUCCESS = "This assignment has been marked as complete: %1$s";
    public static final String MESSAGE_ASSIGNMENT_ALREADY_COMPLETE = "This assignment is already completed.";

    private final Index targetIndex;

    /**
     * Creates a MarkAssignmentCommand to mark the specified {@code Assignment}
     */
    public MarkAssignmentCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }
        Assignment assignmentToMark = lastShownList.get(targetIndex.getZeroBased());

        if (assignmentToMark.getStatus().isCompleted()) {
            throw new CommandException(MESSAGE_ASSIGNMENT_ALREADY_COMPLETE);
        }

        Assignment markedAssignment = assignmentToMark.mark();
        model.setAssignment(assignmentToMark, markedAssignment);

        return new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_SUCCESS, Messages.format(markedAssignment)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAssignmentCommand)) {
            return false;
        }

        MarkAssignmentCommand otherMarkAssignmentCommand = (MarkAssignmentCommand) other;
        return targetIndex.equals(otherMarkAssignmentCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("mark", targetIndex)
                .toString();
    }
}
