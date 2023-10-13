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
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.TypicalMeetings;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMeetingContactCommand}.
 */
public class AddMeetingContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_valid_success() {
        model.addMeeting(TypicalMeetings.MEETING2);
        AddMeetingContactCommand addmcCommand = new AddMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);

        Meeting meeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        Person personToAdd = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // No errors thrown
        String message = assertDoesNotThrow(() -> addmcCommand.execute(model)).getFeedbackToUser();

        String expectedMessage = String.format(AddMeetingContactCommand.MESSAGE_ADD_MEETING_CONTACT_SUCCESS,
                personToAdd.getName(), meeting.getTitle());

        // Output message is correct
        assertEquals(expectedMessage, message);

        Meeting updatedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        String[] expectedAttendees = Arrays.copyOfRange(TypicalPersons.getTypicalAttendees(), 0, 7);
        Meeting expectedMeeting = new MeetingBuilder(meeting)
                .withAttendees(expectedAttendees)
                .build();

        // updated attendees set is correct
        assertEquals(expectedMeeting, updatedMeeting);
    }

    @Test
    public void execute_invalidMeetingIndex_throwsCommandException() {
        AddMeetingContactCommand addmcCommand = new AddMeetingContactCommand(INDEX_SECOND_MEETING,
                INDEX_FIRST_PERSON);

        // throws error for invalid meeting index
        assertThrows(CommandException.class, () -> addmcCommand.execute(model));
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        model.addMeeting(TypicalMeetings.MEETING1);
        AddMeetingContactCommand addmcCommand = new AddMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_OUT_OF_BOUNDS);

        // throws error for invalid attendee index
        assertThrows(CommandException.class, () -> addmcCommand.execute(model));
    }

    @Test
    public void execute_duplicateAttendee_throwsCommandException() {
        model.addMeeting(TypicalMeetings.MEETING1);
        AddMeetingContactCommand addmcCommand = new AddMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);

        // throws error for duplicate attendee
        assertThrows(CommandException.class, () -> addmcCommand.execute(model));
    }

    @Test
    public void equals() {
        AddMeetingContactCommand addmcFirstCommand = new AddMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);
        AddMeetingContactCommand addmcSecondCommand = new AddMeetingContactCommand(INDEX_SECOND_MEETING,
                INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(addmcFirstCommand.equals(addmcFirstCommand));

        // same values -> returns true
        AddMeetingContactCommand addmcFirstCommandCopy = new AddMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);
        assertTrue(addmcFirstCommand.equals(addmcFirstCommandCopy));

        // different types -> returns false
        assertFalse(addmcFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addmcFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(addmcFirstCommand.equals(addmcSecondCommand));
    }

    @Test
    public void toStringMethod() {
        AddMeetingContactCommand addmcCommand = new AddMeetingContactCommand(INDEX_FIRST_MEETING,
                INDEX_FIRST_PERSON);
        String expected = AddMeetingContactCommand.class.getCanonicalName() + "{meetingIndex=" + INDEX_FIRST_MEETING
                + ", " + "contactIndex=" + INDEX_FIRST_PERSON + "}";
        assertEquals(expected, addmcCommand.toString());
    }
}

