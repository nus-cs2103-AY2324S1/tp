package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showScheduleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditScheduleCommand.EditScheduleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.ScheduleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTutorCommand.
 */
public class EditScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Schedule editedSchedule = new ScheduleBuilder().build();
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder(editedSchedule).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE, descriptor);

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
            Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSchedule = Index.fromOneBased(model.getFilteredScheduleList().size());
        Schedule lastSchedule = model.getFilteredScheduleList().get(indexLastSchedule.getZeroBased());

        ScheduleBuilder scheduleInList = new ScheduleBuilder(lastSchedule);
        Schedule editedSchedule = scheduleInList.withStartTime(LocalDateTime.parse(VALID_START_TIME_ONE))
            .withEndTime(LocalDateTime.parse(VALID_END_TIME_ONE)).build();

        EditScheduleDescriptor descriptor =
            new EditScheduleDescriptorBuilder().withStartTime(VALID_START_TIME_ONE)
            .withEndTime(VALID_END_TIME_ONE).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(indexLastSchedule, descriptor);

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
            Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(lastSchedule, editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE,
            new EditScheduleDescriptor());
        Schedule editedSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
            Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        Schedule scheduleInFilteredList = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule editedSchedule =
            new ScheduleBuilder(scheduleInFilteredList)
                .withStartTime(LocalDateTime.parse(VALID_START_TIME_ONE)).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE,
            new EditScheduleDescriptorBuilder().withStartTime(VALID_START_TIME_ONE).build());

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_SCHEDULE_SUCCESS,
            Messages.format(editedSchedule));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSchedule(model.getFilteredScheduleList().get(0), editedSchedule);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateScheduleUnfilteredList_failure() {
        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder(firstSchedule).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_SECOND_SCHEDULE, descriptor);

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_duplicateScheduleFilteredList_failure() {
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        // edit schedule in filtered list into a duplicate in address book
        Schedule scheduleInList = model.getAddressBook().getScheduleList().get(INDEX_SECOND_SCHEDULE.getZeroBased());

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE,
            new EditScheduleDescriptorBuilder(scheduleInList).build());

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_DUPLICATE_SCHEDULE);
    }

    @Test
    public void execute_clashingScheduleUnfilteredList_failure() {
        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        Schedule secondSchedule = model.getFilteredScheduleList().get(INDEX_SECOND_SCHEDULE.getZeroBased());
        EditScheduleDescriptor descriptor =
            new EditScheduleDescriptorBuilder(firstSchedule).withEndTime(secondSchedule.getEndTime())
                .build();
        System.out.println(descriptor);
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_SECOND_SCHEDULE, descriptor);

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_CLASHING_SCHEDULE);
    }

    @Test
    public void execute_clashingScheduleFilteredList_failure() {
        Schedule firstSchedule = model.getFilteredScheduleList().get(INDEX_FIRST_SCHEDULE.getZeroBased());
        showScheduleAtIndex(model, INDEX_FIRST_SCHEDULE);

        // edit schedule in filtered list into a duplicate in address book
        Schedule scheduleInList = model.getAddressBook().getScheduleList().get(INDEX_SECOND_SCHEDULE.getZeroBased());

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE,
            new EditScheduleDescriptorBuilder(scheduleInList).withStartTime(firstSchedule.getStartTime())
                .build());

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_CLASHING_SCHEDULE);
    }

    @Test
    public void execute_invalidScheduleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredScheduleList().size() + 1);
        EditScheduleDescriptor descriptor = new EditScheduleDescriptorBuilder()
            .withStartTime(VALID_START_TIME_ONE).build();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
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

        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(outOfBoundIndex,
            new EditScheduleDescriptorBuilder().withStartTime(VALID_START_TIME_ONE).build());

        assertCommandFailure(editScheduleCommand, model, Messages.MESSAGE_INVALID_SCHEDULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditScheduleCommand standardCommand = new EditScheduleCommand(INDEX_FIRST_SCHEDULE, DESC_SCHEDULE_AMY);

        // same values -> returns true
        EditScheduleDescriptor copyDescriptor = new EditScheduleDescriptor(DESC_SCHEDULE_AMY);
        EditScheduleCommand commandWithSameValues = new EditScheduleCommand(INDEX_FIRST_SCHEDULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_SECOND_SCHEDULE, DESC_SCHEDULE_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditScheduleCommand(INDEX_FIRST_SCHEDULE, DESC_SCHEDULE_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditScheduleDescriptor editScheduleDescriptor = new EditScheduleDescriptor();
        EditScheduleCommand editScheduleCommand = new EditScheduleCommand(index, editScheduleDescriptor);
        String expected = EditScheduleCommand.class.getCanonicalName() + "{index=" + index + ", editScheduleDescriptor="
            + editScheduleDescriptor + "}";
        assertEquals(expected, editScheduleCommand.toString());
    }

}
