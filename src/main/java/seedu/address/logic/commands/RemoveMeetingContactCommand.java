package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Meeting;

/**
 * Removes a Person from the Attendee list of a Meeting
 */
public class RemoveMeetingContactCommand extends Command {

    public static final String COMMAND_WORD = "rmmc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the attendee indicated by the attendee index in the attendees list of the meeting "
            + "indicated by the meeting index.\n"
            + "Parameters: MEETING_INDEX ATTENDEE_INDEX \n" + "Example: " + COMMAND_WORD + " 1" + " 1";

    public static final String MESSAGE_REMOVE_MEETING_CONTACT_SUCCESS = "Removed Person (%1$s) from Meeting (%2$s)";

    private final Index meetingIndex;
    private final Index attendeeIndex;

    /**
     * Constructior for {@code RemoveMeetingContactCommand}
     */
    public RemoveMeetingContactCommand(Index meetingIndex, Index attendeeIndex) {
        this.meetingIndex = meetingIndex;
        this.attendeeIndex = attendeeIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getFilteredMeetingList();
        if (meetingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meeting = lastShownList.get(meetingIndex.getZeroBased());

        Set<Attendee> attendees = meeting.getAttendees();
        if (attendeeIndex.getZeroBased() >= attendees.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTENDEE_INDEX);
        }
        Attendee attendeeToRemove = meeting.getAttendee(attendeeIndex);

        Meeting updatedMeeting = removeAttendee(meeting, attendeeToRemove);
        model.setMeeting(meeting, updatedMeeting);

        return new CommandResult(String.format(MESSAGE_REMOVE_MEETING_CONTACT_SUCCESS,
                attendeeToRemove.getAttendeeName(), meeting.getTitle()));
    }

    /**
     * Creates a new {@code Meeting} that has attendee list with the specified attendee removed.
     */
    static Meeting removeAttendee(Meeting meeting, Attendee attendeeToRemove) {
        Set<Attendee> updatedAttendees = new LinkedHashSet<>(meeting.getAttendees());
        updatedAttendees.remove(attendeeToRemove);
        Meeting updatedMeeting = new Meeting(meeting.getTitle(), meeting.getLocation(), meeting.getStart(),
                meeting.getEnd(), updatedAttendees, meeting.getTags());

        return updatedMeeting;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveMeetingContactCommand)) {
            return false;
        }

        RemoveMeetingContactCommand otherRemoveCommand = (RemoveMeetingContactCommand) other;
        return meetingIndex.equals(otherRemoveCommand.meetingIndex)
                && attendeeIndex.equals(otherRemoveCommand.attendeeIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("meetingIndex", meetingIndex).add("attendeeIndex", attendeeIndex)
                .toString();
    }
}
