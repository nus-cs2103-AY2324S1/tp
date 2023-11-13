package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.InternApplication;

/**
 * Deletes an intern application identified using it's displayed index from the intern tracker.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the intern application identified by the index number used "
            + "in the displayed intern application list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERN_APPLICATION_SUCCESS = "Deleted Intern Application: %1$s";
    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInternApplication(internApplicationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERN_APPLICATION_SUCCESS,
                Messages.formatFeedback(internApplicationToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
