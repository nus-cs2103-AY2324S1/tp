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
 * Deletes an internship task identified using it's displayed index from the app.
 */
public class DeleteInternshipTaskCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "delete-i-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the internship task identified by the index number used "
            + "in the displayed internship task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERNSHIP_TASK_SUCCESS = "Deleted internship task: %1$s";

    private final Index targetIndex;

    public DeleteInternshipTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipTask> lastShownList = model.getFilteredInternshipTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX);
        }

        InternshipTask internshipTaskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInternshipTask(internshipTaskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERNSHIP_TASK_SUCCESS,
                Messages.format(internshipTaskToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInternshipTaskCommand)) {
            return false;
        }
        DeleteInternshipTaskCommand otherDeleteCommand = (DeleteInternshipTaskCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
