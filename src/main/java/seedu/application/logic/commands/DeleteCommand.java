package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;

/**
 * Deletes a person identified using it's displayed index from the application book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the job identified by the index number used in the displayed job list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_JOB_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    /**
     * Constructs a DeleteCommand with the specified target index.
     *
     * @param targetIndex The index of the job to be deleted.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the delete command to remove an application from the application list.
     *
     * @param model The model containing the application list.
     * @return A CommandResult indicating the result of the execution.
     * @throws CommandException If the command execution encounters an error.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteJob(jobToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_JOB_SUCCESS, Messages.format(jobToDelete)));
    }

    /**
     * Checks if this DeleteCommand is equal to another object.
     *
     * @param other The object to compare with this DeleteCommand.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Generates a string representation of this DeleteCommand.
     *
     * @return A string representing the DeleteCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
