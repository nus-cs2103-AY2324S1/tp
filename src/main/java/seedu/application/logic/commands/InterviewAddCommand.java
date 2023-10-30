package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;
import seedu.application.model.job.interview.Interview;

import java.util.List;

/**
 * Adds a person to the application book.
 */
public class InterviewAddCommand extends Command {

    public static final String COMMAND_WORD = "interviewAdd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an interview to the application. "
        + "Parameters: INDEX (must be a positive integer)\n"
        + PREFIX_INTERVIEW_TYPE + "INTERVIEW TYPE "
        + PREFIX_INTERVIEW_DATETIME + "INTERVIEW DATE AND TIME "
        + PREFIX_INTERVIEW_ADDRESS + "INTERVIEW ADDRESS\n"
        + "Example: \n"
        + PREFIX_INTERVIEW_TYPE + "Technical "
        + PREFIX_INTERVIEW_DATETIME + "Dec 31 2030 1200 "
        + PREFIX_INTERVIEW_ADDRESS + "Home\n";

    public static final String MESSAGE_SUCCESS = "New interview added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This interview already exists in the application";

    private final Index index;
    private final Interview toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Job}
     */
    public InterviewAddCommand(Index targetIndex, Interview interview) {
        CollectionUtil.requireAllNonNull(targetIndex, interview);
        this.index = targetIndex;
        toAdd = interview;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToAddInterview = lastShownList.get(index.getZeroBased());

        if (jobToAddInterview.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        jobToAddInterview.addInterview(toAdd);
        // model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
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
