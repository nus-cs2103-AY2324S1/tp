package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_SOURCE;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;
import seedu.application.model.job.interview.Interview;

/**
 * Deletes an interview of a job in the application book.
 */
public class InterviewDeleteCommand extends InterviewCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = InterviewCommand.COMMAND_WORD + " "
        + COMMAND_WORD + ": Deletes an interview from the job. \n"
        + "Parameters: INDEX (of interview) and INDEX (of job)\n"
        + "Example: 1 " + PREFIX_JOB_SOURCE + "2\n"
        + "deletes 1st interview from 2nd job";

    public static final String MESSAGE_SUCCESS = "Interview deleted: %1$s";

    private final Index jobIndex;
    private final Index interviewIndex;

    /**
     * Creates an InterviewDeleteCommand with the specified job and interview
     *
     * @param jobIndex       The index of the job to be deleted.
     * @param interviewIndex The index of the interview to be deleted.
     */
    public InterviewDeleteCommand(Index jobIndex, Index interviewIndex) {
        requireAllNonNull(jobIndex, interviewIndex);
        this.jobIndex = jobIndex;
        this.interviewIndex = interviewIndex;
    }

    /**
     * Executes the delete command to remove an interview from a job.
     *
     * @param model The model containing the application list.
     * @return A CommandResult indicating the result of the execution.
     * @throws CommandException If the command execution encounters an error.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (jobIndex.getZeroBased() >= model.getFilteredJobList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }
        Job jobToDeleteInterview = getJob(model, jobIndex);
        if (interviewIndex.getZeroBased() >= jobToDeleteInterview.interviewLength()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW);
        }
        Interview interviewToDelete = jobToDeleteInterview.getInterview(interviewIndex);
        model.deleteInterview(jobToDeleteInterview, interviewToDelete);

        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(interviewToDelete)),
            false, false, false, jobIndex.getZeroBased());
    }

    /**
     * Checks if this InterviewDeleteCommand is equal to another object.
     *
     * @param other The object to compare with this InterviewDeleteCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewDeleteCommand)) {
            return false;
        }

        InterviewDeleteCommand otherInterviewDeleteCommand = (InterviewDeleteCommand) other;
        return jobIndex.equals(otherInterviewDeleteCommand.jobIndex)
            && interviewIndex.equals(otherInterviewDeleteCommand.interviewIndex);
    }

    /**
     * Generates a string representation of this InterviewDeleteCommand.
     *
     * @return A string representing the InterviewDeleteCommand.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("jobIndex", jobIndex)
            .add("interviewIndex", interviewIndex)
            .toString();
    }

}
