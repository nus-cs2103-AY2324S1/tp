package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

/**
 * Views detailed information of meeting identified using it's displayed index from the address book.
 */
public class ViewMeetingCommand extends Command {

    public static final String COMMAND_WORD = "viewm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views detailed information of the meeting identified "
            + "by the index number used in the displayed meeting list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * @param index of the meeting in the filtered meeting list to view detailed information
     */
    public ViewMeetingCommand(Index index) {
        requireNonNull(index);
        this.targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToView = lastShownList.get(targetIndex.getZeroBased());
        model.setViewedMeeting(meetingToView);
        return new CommandResult(String.format(Messages.MESSAGE_MEETING_VIEWED_OVERVIEW, meetingToView.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instance handles nulls
        if (!(other instanceof ViewMeetingCommand)) {
            return false;
        }

        ViewMeetingCommand otherViewMeetingCommand = (ViewMeetingCommand) other;
        return targetIndex.equals(otherViewMeetingCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
