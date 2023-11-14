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
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UnmarkScheduleCommand.
 */
public class UnmarkScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule = new ScheduleBuilder(firstSchedule).build();

        UnmarkScheduleCommand unmarkScheduleCommand = new UnmarkScheduleCommand(INDEX_FIRST_SCHEDULE);

        String expectedMessage = String.format(UnmarkScheduleCommand.MESSAGE_UNMARK_SUCCESS,
            Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(firstSchedule, editedSchedule);

        assertCommandSuccess(unmarkScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule =
            new ScheduleBuilder(model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased()))
            .build();

        UnmarkScheduleCommand unmarkScheduleCommand = new UnmarkScheduleCommand(INDEX_FIRST_SCHEDULE);

        String expectedMessage = String.format(UnmarkScheduleCommand.MESSAGE_UNMARK_SUCCESS,
            Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(firstSchedule, editedSchedule);

        assertCommandSuccess(unmarkScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidScheduleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        UnmarkScheduleCommand unmarkScheduleCommand = new UnmarkScheduleCommand(outOfBoundIndex);

        assertCommandFailure(unmarkScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidScheduleIndexFilteredList_failure() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);
        Index outOfBoundIndex = INDEX_SECOND_SCHEDULE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getScheduleList().size());

        UnmarkScheduleCommand unmarkScheduleCommand = new UnmarkScheduleCommand(outOfBoundIndex);

        assertCommandFailure(unmarkScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UnmarkScheduleCommand standardCommand = new UnmarkScheduleCommand(INDEX_FIRST_SCHEDULE);

        // same values -> returns true
        UnmarkScheduleCommand commandWithSameValues = new UnmarkScheduleCommand(INDEX_FIRST_SCHEDULE);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UnmarkScheduleCommand(INDEX_SECOND_SCHEDULE)));
    }
}
