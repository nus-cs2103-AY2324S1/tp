package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.Status;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkScheduleCommand.
 */
public class MarkScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_missedSuccess() {
        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule = new ScheduleBuilder(firstSchedule).withStatus(Status.MISSED).build();

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 0);

        String expectedMessage = String.format(MarkScheduleCommand.MESSAGE_MARK_MISSED_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(firstSchedule, editedSchedule);

        assertCommandSuccess(markScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredList_completedSuccess() {
        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule = new ScheduleBuilder(firstSchedule).withStatus(Status.COMPLETED).build();

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 1);

        String expectedMessage = String.format(MarkScheduleCommand.MESSAGE_MARK_COMPLETED_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(firstSchedule, editedSchedule);

        assertCommandSuccess(markScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_missedSuccess() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule =
                new ScheduleBuilder(model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased()))
                        .withStatus(Status.MISSED).build();

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 0);

        String expectedMessage = String.format(MarkScheduleCommand.MESSAGE_MARK_MISSED_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(firstSchedule, editedSchedule);

        assertCommandSuccess(markScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_completedSuccess() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule =
                new ScheduleBuilder(model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased()))
                        .withStatus(Status.COMPLETED).build();

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 1);

        String expectedMessage = String.format(MarkScheduleCommand.MESSAGE_MARK_COMPLETED_SUCCESS,
                Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(firstSchedule, editedSchedule);

        assertCommandSuccess(markScheduleCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidScheduleIndexFilteredList_missedFailure() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);
        Index outOfBoundIndex = INDEX_SECOND_SCHEDULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getScheduleList().size());

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(outOfBoundIndex, 0);
        assertCommandFailure(markScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidScheduleIndexFilteredList_completedFailure() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);
        Index outOfBoundIndex = INDEX_SECOND_SCHEDULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getScheduleList().size());

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(outOfBoundIndex, 1);
        assertCommandFailure(markScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidScheduleStatusFilteredList_failure() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        MarkScheduleCommand markScheduleCommand = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 2);
        assertCommandFailure(markScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_STATUS_INDEX);
    }

    @Test
    public void equals() {
        final MarkScheduleCommand standardCommand = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 0);

        // same values -> returns true
        MarkScheduleCommand commandWithSameValues = new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 0);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MarkScheduleCommand(INDEX_SECOND_SCHEDULE, 0)));

        // different status -> false
        assertFalse(standardCommand.equals(new MarkScheduleCommand(INDEX_FIRST_SCHEDULE, 1)));

        // different index and status -> false
        assertFalse(standardCommand.equals(new MarkScheduleCommand(INDEX_SECOND_SCHEDULE, 1)));
    }
}
