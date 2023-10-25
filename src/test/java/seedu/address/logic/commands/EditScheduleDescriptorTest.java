package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_TWO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditScheduleDescriptorBuilder;

public class EditScheduleDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditScheduleCommand.EditScheduleDescriptor descriptorWithSameValues =
            new EditScheduleCommand.EditScheduleDescriptor(DESC_SCHEDULE_AMY);
        assertTrue(DESC_SCHEDULE_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SCHEDULE_AMY.equals(DESC_SCHEDULE_AMY));

        // null -> returns false
        assertFalse(DESC_SCHEDULE_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_SCHEDULE_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_SCHEDULE_AMY.equals(DESC_SCHEDULE_BOB));

        // different startTime -> returns false
        EditScheduleCommand.EditScheduleDescriptor
            editedAmy = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_AMY)
            .withStartTime(VALID_START_TIME_TWO).build();
        assertFalse(DESC_SCHEDULE_AMY.equals(editedAmy));

        // different endTime -> returns false
        editedAmy = new EditScheduleDescriptorBuilder(DESC_SCHEDULE_AMY).withEndTime(VALID_END_TIME_TWO).build();
        assertFalse(DESC_SCHEDULE_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditScheduleCommand.EditScheduleDescriptor editScheduleDescriptor = new EditScheduleCommand
            .EditScheduleDescriptor();
        String expected = EditScheduleCommand.EditScheduleDescriptor.class.getCanonicalName() + "{startTime="
            + editScheduleDescriptor.getStartTime().orElse(null) + ", endTime="
            + editScheduleDescriptor.getEndTime().orElse(null) + "}";
        assertEquals(expected, editScheduleDescriptor.toString());
    }
}

