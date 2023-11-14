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
 * Un-marks an assignment in the assignment list,
 * or rather, sets the assignment status as incomplete.
 */
public class UnMarkAssignmentCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "unmark-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks an assignment, "
            + "and sets its status as incomplete, "
            + "identified by the index number used in the displayed assignments list."
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_ASSIGNMENT_SUCCESS = "This assignment has been"
        + " marked as incomplete: %1$s";
    public static final String MESSAGE_ASSIGNMENT_ALREADY_INCOMPLETE = "This assignment is already incomplete.";

    private final Index targetIndex;

    /**
     * Creates an UnMarkAssignmentCommand to unmark the specified {@code Assignment}
     */
    public UnMarkAssignmentCommand(Index targetIndex) {
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
        Assignment assignmentToUnMark = lastShownList.get(targetIndex.getZeroBased());

        if (!assignmentToUnMark.getStatus().isCompleted()) {
            throw new CommandException(MESSAGE_ASSIGNMENT_ALREADY_INCOMPLETE);
        }

        Assignment unmarkedAssignment = assignmentToUnMark.unMark();

        model.setAssignment(assignmentToUnMark, unmarkedAssignment);
        return new CommandResult(String.format(MESSAGE_UNMARK_ASSIGNMENT_SUCCESS, Messages.format(unmarkedAssignment)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnMarkAssignmentCommand)) {
            return false;
        }

        UnMarkAssignmentCommand otherUnMarkAssignmentCommand = (UnMarkAssignmentCommand) other;
        return targetIndex.equals(otherUnMarkAssignmentCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("unmark", targetIndex)
                .toString();
    }
}
