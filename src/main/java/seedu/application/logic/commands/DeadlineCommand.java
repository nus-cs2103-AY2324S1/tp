package seedu.application.logic.commands;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Job;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets submission deadline for an existing application in the list. \n"
            + "by the index number used in the last Job listing. "
            + "Existing deadline will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) d/[DEADLINE]\n"
            + "Example: " + COMMAND_WORD + " 1 " + "d/Dec 31 2030 1200.";

    public static final String MESSAGE_SET_DEADLINE_SUCCESS = "Set deadline to specified application.";
    private static final String MESSAGE_SET_DEADLINE_FAILURE = "Failure to set deadline.";

    private final Index index;
    private final Deadline deadline;

    /**
     * @param index    of the job in the job list to edit the deadline
     * @param deadline of the job to be updated to
     */
    public DeadlineCommand(Index index, Deadline deadline) {
        requireAllNonNull(index, deadline);
        this.index = index;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToEdit = lastShownList.get(index.getZeroBased());
        Job editJob = new Job(
                jobToEdit.getRole(), jobToEdit.getCompany(), deadline);

        model.setJob(jobToEdit, editJob);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);

        return new CommandResult(generateSuccessMessage(editJob));
    }

    /**
     * Generates a command execution success message based on whether
     * the deadline is added to or removed from
     * {@code jobToEdit}.
     */
    private String generateSuccessMessage(Job jobToEdit) {
        String message = !deadline.deadline.isEmpty()
                ? MESSAGE_SET_DEADLINE_SUCCESS
                : MESSAGE_SET_DEADLINE_FAILURE;
        return String.format(message, jobToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }

        DeadlineCommand e = (DeadlineCommand) other;
        return index.equals(e.index)
                && deadline.equals(e.deadline);
    }
}
