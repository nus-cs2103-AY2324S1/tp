package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingStatus;

/**
 * Marks a meeting as complete.
 */
public class MarkMeetingCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_MARK_MEETING_SUCCESS = "Meeting marked as complete: %1$s";

    public static final String MESSAGE_MEETING_ALREADY_COMPLETE = "Meeting has already been marked as complete.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the meeting identified by the index number used in the displayed meetings list as complete.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: " + COMMAND_WORD + "1";

    private final Index targetIndex;

    public MarkMeetingCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToMark = lastShownList.get(targetIndex.getZeroBased());
        Meeting updatedMeeting = markMeeting(meetingToMark);
        model.setMeeting(meetingToMark, updatedMeeting);

        return new CommandResult(String.format(MESSAGE_MARK_MEETING_SUCCESS, Messages.format(updatedMeeting)));
    }

    static Meeting markMeeting(Meeting meeting) throws CommandException{
        if(meeting.getStatus().isComplete) {
            throw new CommandException(MESSAGE_MEETING_ALREADY_COMPLETE);
        }

        Meeting markedMeeting = new Meeting(meeting.getTitle(), meeting.getLocation(), meeting.getStart(),
                meeting.getEnd(), meeting.getAttendees(), meeting.getTags(), new MeetingStatus(true));

        return markedMeeting;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkMeetingCommand)) {
            return false;
        }

        MarkMeetingCommand otherMarkMeetingCommand = (MarkMeetingCommand) other;
        return targetIndex.equals(otherMarkMeetingCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("targetIndex", targetIndex).toString();
    }
}
