package seedu.address.logic.commands.internship.role;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;
import seedu.address.model.internship.task.InternshipTaskHasSameInternshipRolePredicate;

/**
 * Deletes an internship task identified using its displayed index from the app.
 */
public class DeleteInternshipRoleCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "delete-i-role";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship role identified by the index number used "
            + "in the displayed internship role list, and all its associated tasks.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS = "Deleted internship role: %1$s"
            + "\n" + "and its associated tasks";

    private final Index targetIndex;

    public DeleteInternshipRoleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipRole> lastShownRoleList = model.getFilteredInternshipRoleList();
        List<InternshipTask> unfilteredTaskList = model.getUnfilteredInternshipTaskList();
        List<InternshipTask> taskListToDelete = new ArrayList<>();

        if (targetIndex.getZeroBased() >= lastShownRoleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_ROLE_DISPLAYED_INDEX);
        }
        InternshipRole internshipRoleToDelete = lastShownRoleList.get(targetIndex.getZeroBased());

        unfilteredTaskList.stream()
                .forEach(task -> {
                    if (new InternshipTaskHasSameInternshipRolePredicate(internshipRoleToDelete).test(task)) {
                        taskListToDelete.add(task);
                    }
                });

        taskListToDelete.forEach(task -> model.deleteInternshipTask(task));
        model.deleteInternshipRole(internshipRoleToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_ROLE_SUCCESS,
                Messages.format(internshipRoleToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInternshipRoleCommand)) {
            return false;
        }
        DeleteInternshipRoleCommand otherDeleteCommand = (DeleteInternshipRoleCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
