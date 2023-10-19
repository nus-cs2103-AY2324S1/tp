package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Command to handle deleting interviews from the address book (using the interview index).
 * Adapted from AB3's "DeleteCommand" class
 */
public class DeleteInterviewCommand extends Command {

    public static final String COMMAND_WORD = "delete-interview";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERVIEW_SUCCESS = "Deleted Interview: %1$s";

    private final Index targetIndex;

    public DeleteInterviewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteInterview(interviewToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INTERVIEW_SUCCESS,
                Messages.formatInterview(interviewToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInterviewCommand)) {
            return false;
        }

        DeleteInterviewCommand otherDeleteCommand = (DeleteInterviewCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
