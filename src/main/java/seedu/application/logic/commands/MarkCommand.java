package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;
import seedu.application.model.job.Status;

/**
 * Adds a person to the application book.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the status of the job identified by the index number used in the displayed job list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " 1" + " s/pending.";

    public static final String MESSAGE_MARK_JOB_SUCCESS = "Marked job status of the specified application.";
    public static final String MESSAGE_MARK_JOB_FAILURE = "Unable to mark the job status of the specified application.";

    private final Index targetIndex;

    private final Status status;

    /**
     * Constructs a DeleteCommand with the specified target index.
     *
     * @param targetIndex The index of the job to be deleted.
     */
    public MarkCommand(Index targetIndex, Status status) {
        this.targetIndex = targetIndex;
        this.status = status;
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

        Job jobToMark = lastShownList.get(targetIndex.getZeroBased());
        Job markedJob = new Job(
                jobToMark.getRole(), jobToMark.getCompany(), status,
                jobToMark.getDeadline(), jobToMark.getJobType());

        model.setJob(jobToMark, markedJob);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_MARK_JOB_SUCCESS, Messages.format(markedJob)));
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
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        MarkCommand otherMarkCommand = (MarkCommand) other;
        return targetIndex.equals(otherMarkCommand.targetIndex) && status.equals(otherMarkCommand.status);
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
