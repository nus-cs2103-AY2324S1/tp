package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MarkMeetingCommand}.
 */
public class MarkMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToMark = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(MarkMeetingCommand.MESSAGE_MARK_MEETING_SUCCESS,
                Messages.format(meetingToMark));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Meeting markedMeeting = new MeetingBuilder(meetingToMark).withStatus(true).build();
        expectedModel.setMeeting(meetingToMark, markedMeeting);

        assertCommandSuccess(markMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(outOfBoundIndex);

        assertCommandFailure(markMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingToMark = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        MarkMeetingCommand markMeetingCommand = new MarkMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(MarkMeetingCommand.MESSAGE_MARK_MEETING_SUCCESS,
                Messages.format(meetingToMark));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Meeting markedMeeting = new MeetingBuilder(meetingToMark).withStatus(true).build();
        expectedModel.setMeeting(meetingToMark, markedMeeting);
        showMeetingAtIndex(expectedModel, INDEX_FIRST_MEETING);

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
