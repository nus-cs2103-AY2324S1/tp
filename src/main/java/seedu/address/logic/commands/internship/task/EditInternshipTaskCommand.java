package seedu.address.logic.commands.internship.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.assignment.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.internship.role.CliSyntax.PREFIX_OUTCOME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.internship.InternshipCommand;
import seedu.address.model.Model;
import seedu.address.model.fields.TaskOutcome;
import seedu.address.model.internship.task.InternshipTask;

/**
 * A command that edits the outcome of an InternshipTask when executed.
 */
public class EditInternshipTaskCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "edit-i-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the outcome of an InternshipTask to the app. "
            + "Parameters: "
            + PREFIX_INDEX + "INDEX (must be a positive integer)"
            + PREFIX_OUTCOME + "OUTCOME" + "\n"
            + "Example: " + COMMAND_WORD + " i/ 1" + " o/ follow-up";

    public static final String MESSAGE_SUCCESS = "Internship task outcome updated to: %1$s";

    public static final String MESSAGE_INVALID_TASK = "This internship task does not exist in the address book";

    private final Index index;
    private final TaskOutcome newOutcome;

    /**
     * The constructor for an EditInternshipRoleCommand
     * @param index The index of the InternshipTask to be edited
     * @param newOutcome The new outcome for the target InternshipTask
     */
    public EditInternshipTaskCommand(Index index, TaskOutcome newOutcome) {
        this.index = index;
        this.newOutcome = newOutcome;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<InternshipTask> lastShownList = model.getFilteredInternshipTaskList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERNSHIP_TASK_DISPLAYED_INDEX);
        }

        InternshipTask taskToEdit = lastShownList.get(index.getZeroBased());
        if (!model.hasInternshipTask(taskToEdit)) {
            throw new CommandException(MESSAGE_INVALID_TASK);
        }

        InternshipTask taskWithNewOutcome = taskToEdit.getNewInternshipTaskWithOutcome(newOutcome);
        model.setInternshipTask(taskToEdit, taskWithNewOutcome);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(taskWithNewOutcome)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipTaskCommand)) {
            return false;
        }

        EditInternshipTaskCommand otherEditInternshipTaskCommand = (EditInternshipTaskCommand) other;
        return index.equals(otherEditInternshipTaskCommand.index)
                && newOutcome.equals(otherEditInternshipTaskCommand.newOutcome);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("edit", index)
                .add("outcome", newOutcome)
                .toString();
    }
}
