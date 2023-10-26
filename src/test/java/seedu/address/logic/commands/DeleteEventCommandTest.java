package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteEventCommand}.
 */
public class DeleteEventCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getEventList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.formatEvent(eventToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getEventList().size() + 1);
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_MEETING);

        Event eventToDelete = model.getEventList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.formatEvent(eventToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    private void showNoEvent(Model model) {
        model.updateFilteredEventList(e -> false);

        assertTrue(model.getEventList().isEmpty());
    }
}
