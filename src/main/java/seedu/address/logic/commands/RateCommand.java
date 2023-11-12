package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Rating;

//@@author jonxzx
/**
 * Rate the interview with the given rating.
 */
public class RateCommand extends Command {
    public static final String COMMAND_WORD = "rate";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rate the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INTERVIEW_INDEX (must be a positive integer) "
            + "RATING (must be a non-negative number with 1 decimal place between 0.0 to 5.0 inclusive)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 3.5";
    public static final String MESSAGE_RATE_INTERVIEW_SUCCESS = "Rating Interview: %s is rated.";
    public static final String MESSAGE_NOT_DONE = "Interview: %s must be completed before rating.";
    private final Index targetIndex;
    private final Rating newRating;

    /**
     * @param targetIndex of the interview in the filtered interview list to rate
     * @param rate of the rating to be set
     */
    public RateCommand(Index targetIndex, Rating rate) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.newRating = rate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToRate = lastShownList.get(targetIndex.getZeroBased());

        if (!interviewToRate.isDone()) {
            throw new CommandException(String.format(MESSAGE_NOT_DONE,
                    Messages.formatInterview(interviewToRate)));
        }

        Interview ratedInterview = new Interview(
                interviewToRate.getInterviewApplicant(),
                interviewToRate.getJobRole(),
                newRating,
                interviewToRate.getInterviewStartTime(),
                interviewToRate.getInterviewEndTime(),
                interviewToRate.isDone()
        );
        model.setInterview(interviewToRate, ratedInterview);

        return new CommandResult(
                String.format(MESSAGE_RATE_INTERVIEW_SUCCESS, Messages.formatInterview(interviewToRate)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RateCommand)) {
            return false;
        }

        RateCommand otherRateCommand = (RateCommand) other;
        return targetIndex.equals(otherRateCommand.targetIndex)
                && newRating.equals(otherRateCommand.newRating);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("newRating", newRating)
                .toString();
    }
}
