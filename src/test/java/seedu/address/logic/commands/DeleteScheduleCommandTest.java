package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.TypicalSchedules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTutorCommand}.
 */
public class DeleteScheduleCommandTest {

    private Model model = new ModelManager(TypicalSchedules.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Schedule scheduleToDelete = model.getFilteredScheduleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteScheduleCommand.MESSAGE_DELETE_SCHEDULE_SUCCESS,
                Messages.format(scheduleToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteSchedule(scheduleToDelete);

        assertCommandSuccess(deleteScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteScheduleCommand deleteFirstCommand = new DeleteScheduleCommand(INDEX_FIRST_PERSON);
        DeleteScheduleCommand deleteSecondCommand = new DeleteScheduleCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteScheduleCommand deleteFirstCommandCopy = new DeleteScheduleCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(targetIndex);
        String expected = DeleteScheduleCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteScheduleCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSchedule(Model model) {
        model.updateFilteredScheduleList(p -> false);

        assertTrue(model.getFilteredScheduleList().isEmpty());
    }
}
