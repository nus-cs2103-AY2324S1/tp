package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Rating;

/**
 * Rate the interview with the given rating.
 */
public class RateCommand extends Command {
    public static final String COMMAND_WORD = "rate";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rate the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INDEX (must be a positive integer) Rating\n"
            + "Example: " + COMMAND_WORD + " 1" + " 3.5";
    public static final String MESSAGE_MARK_INTERVIEW_SUCCESS = "Marked Interview: %s as done.";
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
                interviewToRate.getInterviewStartTime(),
                interviewToRate.getInterviewEndTime(),
                newRating,
                interviewToRate.isDone()
        );
        model.setInterview(interviewToRate, ratedInterview);

        return new CommandResult(
                String.format(MESSAGE_MARK_INTERVIEW_SUCCESS, Messages.formatInterview(interviewToRate)));
    }
}
