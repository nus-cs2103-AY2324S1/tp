package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_OUT_OF_BOUNDS;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;

import java.time.LocalDateTime;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkMeetingCommand}.
 */
public class MarkMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToMark = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(INDEX_FIRST_MEETING);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Meeting markedMeeting = new MeetingBuilder(meetingToMark).withStatus(true).build();
        String expectedMessage = String.format(MarkMeetingCommand.MESSAGE_MARK_MEETING_SUCCESS,
                Messages.format(markedMeeting));

        expectedModel.setMeeting(meetingToMark, markedMeeting);

        assertCommandSuccess(markMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(INDEX_OUT_OF_BOUNDS);

        assertCommandFailure(markMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingToMark = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(INDEX_FIRST_MEETING);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Meeting markedMeeting = new MeetingBuilder(meetingToMark).withStatus(true).build();
        expectedModel.setMeeting(meetingToMark, markedMeeting);
        showMeetingAtIndex(expectedModel, INDEX_FIRST_MEETING);
        String expectedMessage = String.format(MarkMeetingCommand.MESSAGE_MARK_MEETING_SUCCESS,
                Messages.format(markedMeeting));

        assertCommandSuccess(markMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Index outOfBoundIndex = INDEX_SECOND_MEETING;
        System.out.println("hi");
        System.out.println(outOfBoundIndex.getZeroBased());
        System.out.println(model.getAddressBook().getMeetingList().size());
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMeetingList().size());

        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(outOfBoundIndex);

        assertCommandFailure(markMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_updatesLastContactedTime_success() {
        Meeting meetingToMark = model.getFilteredMeetingList().get(INDEX_SECOND_MEETING.getZeroBased());
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(INDEX_SECOND_MEETING);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Meeting markedMeeting = new MeetingBuilder(meetingToMark).withStatus(true).build();
        String expectedMessage = String.format(MarkMeetingCommand.MESSAGE_MARK_MEETING_SUCCESS,
                Messages.format(markedMeeting));

        expectedModel.setMeeting(meetingToMark, markedMeeting);
        Iterator<Attendee> attendeeIterator = meetingToMark.getAttendees().iterator();
        while (attendeeIterator.hasNext()) {
            Attendee attendee = attendeeIterator.next();
            Person person = expectedModel.getPerson(attendee.getAttendeeName());
            Person expectedPerson = new PersonBuilder(person)
                    .withLastContactedTime(DateTimeUtil.format(meetingToMark.getEnd())).build();
            expectedModel.setPerson(person, expectedPerson);
        }

        assertCommandSuccess(markMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void updateLastContactedTime_doesNotUpdateForEarlierTime_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person updatedPerson = MarkMeetingCommand.updateLastContactedTime(person, LocalDateTime.MIN);
        assertEquals(person, updatedPerson);
    }

    @Test
    public void equals() {
        MarkMeetingCommand markFirstCommand = new MarkMeetingCommand(INDEX_FIRST_MEETING);
        MarkMeetingCommand markSecondCommand = new MarkMeetingCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkMeetingCommand markFirstCommandCopy = new MarkMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(targetIndex);
        String expected = MarkMeetingCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, markMeetingCommand.toString());
    }
}
