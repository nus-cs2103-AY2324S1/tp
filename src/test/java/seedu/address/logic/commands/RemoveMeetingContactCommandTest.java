package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_OUT_OF_BOUNDS;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.TypicalMeetings;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class RemoveMeetingContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_valid_success() {
        model.addMeeting(TypicalMeetings.MEETING1);
        RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);

        Meeting meeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        Attendee attendeeToRemove = meeting.getAttendee(INDEX_FIRST_PERSON);

        // No errors thrown
        String message = assertDoesNotThrow(() -> rmmcCommand.execute(model)).getFeedbackToUser();

        String expectedMessage = String.format(RemoveMeetingContactCommand.MESSAGE_REMOVE_MEETING_CONTACT_SUCCESS,
                attendeeToRemove.getAttendeeName(), meeting.getTitle());

        // Output message is correct
        assertEquals(expectedMessage, message);

        Meeting updatedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        String[] expectedAttendees = Arrays.copyOfRange(TypicalPersons.getTypicalAttendees(), 1, 8);
        Meeting expectedMeeting = new MeetingBuilder(meeting)
            .withAttendees(expectedAttendees)
            .build();

        // updated attendees set is correct
        assertEquals(expectedMeeting, updatedMeeting);
    }

    @Test
    public void execute_invalidMeetingIndex_throwsCommandException() {
        RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_SECOND_MEETING,
                INDEX_FIRST_PERSON);

        // throws error for invalid meeting index
        assertThrows(CommandException.class, () -> rmmcCommand.execute(model));
    }

    @Test
    public void execute_invalidAttendeeIndex_throwsCommandException() {
        model.addMeeting(TypicalMeetings.MEETING1);
        RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_OUT_OF_BOUNDS);

        // throws error for invalid attendee index
        assertThrows(CommandException.class, () -> rmmcCommand.execute(model));
    }

    @Test
    public void equals() {
        RemoveMeetingContactCommand rmmcFirstCommand = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);
        RemoveMeetingContactCommand rmmcSecondCommand = new RemoveMeetingContactCommand(INDEX_SECOND_MEETING,
                INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(rmmcFirstCommand.equals(rmmcFirstCommand));

        // same values -> returns true
        RemoveMeetingContactCommand rmmcFirstCommandCopy = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);
        assertTrue(rmmcFirstCommand.equals(rmmcFirstCommandCopy));

        // different types -> returns false
        assertFalse(rmmcFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rmmcFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(rmmcFirstCommand.equals(rmmcSecondCommand));
    }

    @Test
    public void toStringMethod() {
        RemoveMeetingContactCommand rmmcCommand = new RemoveMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);
        String expected = RemoveMeetingContactCommand.class.getCanonicalName() + "{meetingIndex=" + INDEX_FIRST_MEETING
                + ", " + "attendeeIndex=" + INDEX_FIRST_PERSON + "}";
        assertEquals(expected, rmmcCommand.toString());
    }
}
