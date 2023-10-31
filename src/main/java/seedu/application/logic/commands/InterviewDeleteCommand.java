package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DELETE;

import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;

/**
 * Deletes an interview of a job in the application book.
 */
public class InterviewDeleteCommand extends InterviewCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an interview from the job\n"
            + "Parameters: INDEX (of interview) and\n INDEX (of job application)\n"
            + "Example: 1 " + PREFIX_INTERVIEW_DELETE + "2\n"
            + "deletes 1st interview from 2nd job";

    public static final String MESSAGE_SUCCESS = "Interview deleted: %1$s";

    private final Index jobIndex;
    private final Index interviewIndex;

    /**
     * Creates an InterviewDeleteCommand with the specified job and interview
     *
     * @param jobIndex The index of the job to be deleted.
     * @param interviewIndex The index of the interview to be deleted.
     */
    public InterviewDeleteCommand(Index jobIndex, Index interviewIndex) {
        requireNonNull(jobIndex);
        requireNonNull(interviewIndex);
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
        List<Job> lastShownList = model.getFilteredJobList();

        if (jobIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToDeleteInterview = lastShownList.get(jobIndex.getZeroBased());

        jobToDeleteInterview.deleteInterview(interviewIndex);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(jobToDeleteInterview)));
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
                .add("Job index", jobIndex)
                .add("Interview index", interviewIndex)
                .toString();
    }

}
