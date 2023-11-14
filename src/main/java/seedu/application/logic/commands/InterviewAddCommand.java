package seedu.application.logic.commands;

import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATETIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TYPE;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;
import seedu.application.model.job.interview.Interview;

/**
 * Adds an interview to a job in the application book.
 */
public class InterviewAddCommand extends InterviewCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = InterviewCommand.COMMAND_WORD
        + " " + COMMAND_WORD + ": Adds an interview to the job. "
        + "Parameters: INDEX (must be a positive integer)\n"
        + PREFIX_INTERVIEW_TYPE + "INTERVIEW TYPE "
        + PREFIX_INTERVIEW_DATETIME + "INTERVIEW DATE AND TIME "
        + PREFIX_INTERVIEW_ADDRESS + "INTERVIEW ADDRESS\n"
        + "Example: \n"
        + PREFIX_INTERVIEW_TYPE + "Technical "
        + PREFIX_INTERVIEW_DATETIME + "Dec 31 2030 1200 "
        + PREFIX_INTERVIEW_ADDRESS + "Home\n";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the application";

    private final Index jobIndex;
    private final Interview toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Job}
     */
    public InterviewAddCommand(Index jobIndex, Interview interview) {
        CollectionUtil.requireAllNonNull(jobIndex, interview);
        this.jobIndex = jobIndex;
        toAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Job jobToAddInterview = getJob(model, jobIndex);

        if (jobToAddInterview.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        jobToAddInterview.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)), false,
            false, false, jobIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InterviewAddCommand)) {
            return false;
        }

        InterviewAddCommand otherInterviewAddCommand = (InterviewAddCommand) other;
        return toAdd.equals(otherInterviewAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("toAdd", toAdd)
            .toString();
    }
}
