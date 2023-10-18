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
import seedu.address.model.person.Person;

/**
 * Adds a Person to the Attendee list of a Meeting
 */
public class AddMeetingContactCommand extends Command {

    public static final String COMMAND_WORD = "addmc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the contact indicated by the contact index to the attendees list of the meeting "
            + "indicated by the meeting index.\n"
            + "Parameters: MEETING_INDEX ATTENDEE_INDEX \n" + "Example: " + COMMAND_WORD + " 1" + " 1";

    public static final String MESSAGE_ADD_MEETING_CONTACT_SUCCESS = "Added Person (%1$s) to Meeting (%2$s)";
    public static final String MESSAGE_DUPLICATE_ATTENDEE =
            "(%1$s) is already in the attendee list of this meeting (%2$s)";

    private final Index meetingIndex;
    private final Index contactIndex;

    /**
     * Constructor for {@code AddMeetingContactCommand}
     */
    public AddMeetingContactCommand(Index meetingIndex, Index contactIndex) {
        this.meetingIndex = meetingIndex;
        this.contactIndex = contactIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Meeting> lastShownList = model.getFilteredMeetingList();
        if (meetingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }
        Meeting meeting = lastShownList.get(meetingIndex.getZeroBased());

        List<Person> contactList = model.getFilteredPersonList();
        if (contactIndex.getZeroBased() >= contactList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToAdd = contactList.get(contactIndex.getZeroBased());

        Attendee attendeeToAdd = new Attendee(personToAdd.getName().toString());
        Set<Attendee> attendeeList = meeting.getAttendees();
        if (attendeeList.contains(attendeeToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_ATTENDEE,
                    attendeeToAdd.getAttendeeName(), meeting.getTitle()));
        }

        Meeting updatedMeeting = addAttendee(meeting, attendeeToAdd);
        model.setMeeting(meeting, updatedMeeting);

        return new CommandResult(String.format(MESSAGE_ADD_MEETING_CONTACT_SUCCESS,
                attendeeToAdd.getAttendeeName(), meeting.getTitle()));
    }

    /**
     * Creates a new {@code Meeting} that has attendee list with the specified attendee added.
     */
    static Meeting addAttendee(Meeting meeting, Attendee attendeeToAdd) {
        Set<Attendee> updatedAttendees = new LinkedHashSet<>(meeting.getAttendees());
        updatedAttendees.add(attendeeToAdd);
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
        if (!(other instanceof AddMeetingContactCommand)) {
            return false;
        }

        AddMeetingContactCommand otherCommand = (AddMeetingContactCommand) other;
        return meetingIndex.equals(otherCommand.meetingIndex)
                && contactIndex.equals(otherCommand.contactIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("meetingIndex", meetingIndex).add("contactIndex", contactIndex)
                .toString();
    }
}

