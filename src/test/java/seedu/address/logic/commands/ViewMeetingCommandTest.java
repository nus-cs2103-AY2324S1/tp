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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewMeetingCommand}.
 */
public class ViewMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToView = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(Messages.MESSAGE_MEETING_VIEWED_OVERVIEW, meetingToView.getTitle());
        String expectedDisplayString = "Title: CS2103T meeting\n"
                + "Location: Zoom call url\n"
                + "Start: 20 September 2023, 1000\n"
                + "End: 20 September 2023, 1200\n"
                + "Attendees: [Alice Pauline , Benson Meier , Carl Kurz , Daniel Meier , Elle Meyer , Fiona Kunz"
                + " , George Best , Hoon Meier ]\n";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(viewMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(meetingToView.toDisplayString(), expectedDisplayString);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(outOfBoundIndex);

        assertCommandFailure(viewMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingToView = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(Messages.MESSAGE_MEETING_VIEWED_OVERVIEW, meetingToView.getTitle());
        String expectedDisplayString = "Title: CS2103T meeting\n"
                + "Location: Zoom call url\n"
                + "Start: 20 September 2023, 1000\n"
                + "End: 20 September 2023, 1200\n"
                + "Attendees: [Alice Pauline , Benson Meier , Carl Kurz , Daniel Meier , Elle Meyer , Fiona Kunz"
                + " , George Best , Hoon Meier ]\n";

        // Model displaying filtered list should not change
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showMeetingAtIndex(expectedModel, INDEX_FIRST_MEETING);

        assertCommandSuccess(viewMeetingCommand, model, expectedMessage, expectedModel);
        assertEquals(meetingToView.toDisplayString(), expectedDisplayString);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMeetingList().size());

        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(outOfBoundIndex);

        assertCommandFailure(viewMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewMeetingCommand viewMeetingFirstCommand = new ViewMeetingCommand(INDEX_FIRST_MEETING);
        ViewMeetingCommand viewMeetingSecondCommand = new ViewMeetingCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(viewMeetingFirstCommand.equals(viewMeetingFirstCommand));

        // same values -> returns true
        ViewMeetingCommand viewMeetingFirstCommandCopy = new ViewMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(viewMeetingFirstCommand.equals(viewMeetingFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewMeetingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewMeetingFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewMeetingFirstCommand.equals(viewMeetingSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewMeetingCommand viewMeetingCommand = new ViewMeetingCommand(targetIndex);
        String expected = ViewMeetingCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewMeetingCommand.toString());
    }
}
