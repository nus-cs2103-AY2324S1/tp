package seedu.address.logic.commands.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.fields.Description;

/**
 * A command that edits the description of an assignment when executed.
 */
public class EditAssignmentCommand extends AssignmentCommand {

    public static final String COMMAND_WORD = "edit-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the description of an assignment to the app. "
            + "Parameters: "
            + "[" + PREFIX_INDEX + "INDEX (must be a positive integer)" + "]\n"
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION" + "]\n"
            + "Example: " + COMMAND_WORD + " i/1" + " /d This is the new description";

    public static final String MESSAGE_SUCCESS = "Assignment has been edited: %1$s";

    private final Index index;
    private final Description newDescription;

    /**
     * The constructor for an EditAssignmentCommand
     * @param index The index of the assignment to be edited
     * @param newDescription The new description for the target assignment
     */
    public EditAssignmentCommand(Index index, Description newDescription) {
        this.index = index;
        this.newDescription = newDescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }
        Assignment assignmentToEdit = lastShownList.get(index.getZeroBased());
        Assignment assignmentWithNewDesc = assignmentToEdit.getNewAssignmentWithDescription(newDescription);

        model.setAssignment(assignmentToEdit, assignmentWithNewDesc);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(assignmentWithNewDesc)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAssignmentCommand)) {
            return false;
        }

        EditAssignmentCommand otherEditAssignmentCommand = (EditAssignmentCommand) other;
        return index.equals(otherEditAssignmentCommand.index)
                && newDescription.equals(otherEditAssignmentCommand.newDescription);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("edit", index)
                .add("description", newDescription)
                .toString();
    }
}
