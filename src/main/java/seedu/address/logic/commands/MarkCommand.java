package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Marks interview as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks as done the interview identified by the index number used in the displayed interview list.\n"
            + "Parameters: INTERVIEW_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_MARK_INTERVIEW_SUCCESS = "Marked Interview: %s as done.";
    public static final String MESSAGE_ALREADY_DONE = "Interview: %s is already done.";
    private final Index index;

    /**
     * @param targetIndex of the applicant in the filtered applicant list to edit
     */
    public MarkCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToMark = lastShownList.get(index.getZeroBased());

        if (interviewToMark.isDone()) {
            throw new CommandException(String.format(MESSAGE_ALREADY_DONE,
                    Messages.formatInterview(interviewToMark)));
        }

        Interview markedInterview = new Interview(
                interviewToMark.getInterviewApplicant(),
                interviewToMark.getJobRole(),
                interviewToMark.getRating(),
                interviewToMark.getInterviewStartTime(),
                interviewToMark.getInterviewEndTime(),
                true
        );
        model.setInterview(interviewToMark, markedInterview);
        return new CommandResult(
                String.format(MESSAGE_MARK_INTERVIEW_SUCCESS, Messages.formatInterview(interviewToMark)));
    }

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

        return index.equals(otherMarkCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
